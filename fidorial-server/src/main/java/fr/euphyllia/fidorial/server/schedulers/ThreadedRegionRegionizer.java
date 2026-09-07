package fr.euphyllia.fidorial.server.schedulers;

import ca.spottedleaf.common.time.TickData;
import ca.spottedleaf.common.time.TickTime;
import ca.spottedleaf.common.util.TimeUtil;
import ca.spottedleaf.concurrentutil.collection.MultiThreadedQueue;
import ca.spottedleaf.concurrentutil.executor.queue.PrioritisedTaskQueue;
import ca.spottedleaf.concurrentutil.list.COWArrayList;
import ca.spottedleaf.concurrentutil.numa.OSNuma;
import ca.spottedleaf.concurrentutil.scheduler.SchedulableTick;
import ca.spottedleaf.concurrentutil.scheduler.StealingScheduledThreadPool;
import ca.spottedleaf.concurrentutil.util.Priority;
import fr.fidorial.scheduler.RegionTickHandler;
import fr.fidorial.scheduler.RegionTps;
import fr.fidorial.scheduler.RegionizedScheduler;
import fr.fidorial.world.ChunkPos;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

public final class ThreadedRegionRegionizer implements RegionizedScheduler {

    public static int SECTION_SHIFT = 5;

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ThreadedRegionRegionizer.class);

    private static final long TICK_INTERVAL_NS = TimeUnit.MILLISECONDS.toNanos(50L);

    private static final long TPS_WINDOW_NS = TimeUnit.SECONDS.toNanos(5L);

    private static final int MAX_EMPTY_TICKS = 20 * 60;

    private static final int MAX_IMMEDIATE_PER_TICK = 4096;

    private static final long STEAL_THRESHOLD_NS = TimeUnit.MILLISECONDS.toNanos(5L);

    private static final long TASK_TIME_SLICE_NS = TimeUnit.MILLISECONDS.toNanos(15L);

    private static final ThreadMXBean THREADS = ManagementFactory.getThreadMXBean();
    private static final boolean CPU_TIME_SUPPORTED = cpuTimeSupported();

    private final StealingScheduledThreadPool scheduler;
    private final ConcurrentMap<RegionKey, Region> regions = new ConcurrentHashMap<>();
    private final COWArrayList<RegionTickHandler> tickHandlers = new COWArrayList<>(RegionTickHandler.class);

    private volatile boolean shutdown;

    private volatile RegionTickProfiler tickProfiler = RegionTickProfiler.NO_OP;

    public ThreadedRegionRegionizer(final int workerThreads, final int sectionShift) {
        SECTION_SHIFT = sectionShift;

        final AtomicInteger id = new AtomicInteger();
        this.scheduler = new StealingScheduledThreadPool(
                r -> new RegionTickThread(r, "fidorial-region-worker-" + id.incrementAndGet()),
                OSNuma.getNativeInstance());
        this.scheduler.setThreadAllocation(
                allocateThreads(workerThreads), STEAL_THRESHOLD_NS, TASK_TIME_SLICE_NS);

        LOGGER.info("Region pool started with {} workers and section shift: {}", workerThreads, SECTION_SHIFT);
    }

    private static Int2IntMap allocateThreads(final int workerThreads) {
        final int threads = Math.max(1, workerThreads);
        final int nodes = Math.max(1, OSNuma.getNativeInstance().getTotalNumaNodes());
        final Int2IntMap allocation = new Int2IntOpenHashMap(nodes);
        for (int i = 0; i < threads; i++) {
            allocation.mergeInt(i % nodes, 1, Integer::sum);
        }
        return allocation;
    }

    @Override
    public void execute(final Key worldName, final ChunkPos pos, final Runnable task) {
        enqueue(worldName, pos, task, 0L);
    }

    @Override
    public void executeDelayed(final Key worldName, final ChunkPos pos, final Runnable task, final long delayTicks) {
        enqueue(worldName, pos, task, Math.max(0L, delayTicks));
    }

    @Override
    public boolean isOwnedByCurrentThread(final Key worldName, final ChunkPos pos) {
        return RegionTickThread.owns(worldName, pos);
    }

    public void registerTickHandler(final RegionTickHandler handler) {
        tickHandlers.add(handler);
    }

    public void setTickProfiler(final @Nullable RegionTickProfiler profiler) {
        tickProfiler = profiler == null ? RegionTickProfiler.NO_OP : profiler;
    }

    public void addTicket(final Key worldName, final ChunkPos pos) {
        if (shutdown) return;

        final RegionKey key = RegionKey.of(worldName, pos);
        final Region[] fresh = new Region[1];

        final Region region = regions.compute(key, (k, existing) -> {
            if (existing == null) {
                existing = fresh[0] = new Region(k);
            }
            existing.tickets.incrementAndGet();
            existing.emptyTicks.set(0);
            return existing;
        });

        if (fresh[0] != null) {
            schedule(fresh[0]);
        } else if (region != null) {
            scheduler.notifyTasks(region);
        }
    }

    public void removeTicket(final Key worldName, final ChunkPos pos) {
        final Region region = regions.get(RegionKey.of(worldName, pos));
        if (region != null) {
            region.tickets.updateAndGet(t -> Math.max(0, t - 1));
        }
    }

    public void moveTicket(final Key worldName, final ChunkPos from, final ChunkPos to) {
        if (RegionKey.of(worldName, from).equals(RegionKey.of(worldName, to))) return;
        addTicket(worldName, to);
        removeTicket(worldName, from);
    }

    private void enqueue(final Key worldName, final ChunkPos pos, final Runnable task, final long delayTicks) {
        if (shutdown) return;

        final RegionKey key = RegionKey.of(worldName, pos);
        final Region[] fresh = new Region[1];

        final Region region = regions.compute(key, (k, existing) -> {
            if (existing == null) {
                existing = fresh[0] = new Region(k);
            }
            existing.emptyTicks.set(0);
            existing.submit(task, delayTicks);
            return existing;
        });

        if (fresh[0] != null) {
            schedule(fresh[0]);
        } else if (region != null) {
            scheduler.notifyTasks(region);
        }
    }

    private void schedule(final Region region) {
        region.initScheduledStart(System.nanoTime());
        scheduler.schedule(region);
        LOGGER.debug("Region created: {}", region.key);
    }

    private boolean tryRetire(final Region region) {
        final boolean[] removed = new boolean[1];
        regions.compute(region.key, (k, existing) -> {
            if (existing != region) return existing;
            if (region.isIdle() && region.emptyTicks.get() >= MAX_EMPTY_TICKS) {
                removed[0] = true;
                return null;
            }
            return existing;
        });

        if (removed[0]) {
            LOGGER.debug("Region removed: {}", region.key);
        }
        return removed[0];
    }


    private static boolean cpuTimeSupported() {
        try {
            if (!THREADS.isThreadCpuTimeSupported()) return false;
            if (!THREADS.isThreadCpuTimeEnabled()) THREADS.setThreadCpuTimeEnabled(true);
            return THREADS.isThreadCpuTimeEnabled();
        } catch (final UnsupportedOperationException | SecurityException e) {
            LOGGER.warn("Per-thread CPU time is unavailable, falling back to tick occupancy", e);
            return false;
        }
    }

    private static long currentThreadCpuNanos() {
        if (!CPU_TIME_SUPPORTED) return -1L;
        final long cpu = THREADS.getCurrentThreadCpuTime();
        return cpu < 0 ? -1L : cpu;
    }

    /**
     * Snapshot of the region that owns the given chunk, or {@code null} when
     * no region is active there (or when it has not ticked enough yet).
     */
    public @Nullable RegionTpsSnapshot snapshotAt(final Key worldName, final ChunkPos pos) {
        final Region region = regions.get(RegionKey.of(worldName, pos));
        return region == null ? null : region.snapshot();
    }

    public List<RegionTpsSnapshot> tpsSnapshots() {
        final List<RegionTpsSnapshot> out = new ArrayList<>(regions.size());
        for (final Region region : regions.values()) {
            final RegionTpsSnapshot snapshot = region.snapshot();
            if (snapshot != null) out.add(snapshot);
        }
        out.sort(Comparator.comparingDouble(RegionTpsSnapshot::tps));
        return out;
    }

    public @Nullable String describeOwningThread(final Key worldName, final ChunkPos pos) {
        final Region region = regions.get(RegionKey.of(worldName, pos));
        if (region == null) {
            return null;
        }
        final Thread owner = region.tickingThread;
        return owner == null
                ? "region " + region.key + " is idle (no thread currently ticking it)"
                : "[thread=" + owner.getName() + ",class=" + owner.getClass().getName() + "]";
    }

    public void shutdown() {
        shutdown = true;
        scheduler.halt();
        if (!scheduler.join(TimeUnit.SECONDS.toMillis(5L))) {
            LOGGER.warn("Region workers did not stop within 5s");
        }
        regions.clear();
        LOGGER.info("Region workers stopped");
    }

    public record RegionTpsSnapshot(
            Key world,
            int sectionX,
            int sectionZ,
            double tps,
            double msptAvg,
            double cpuPercent,
            int queuedTasks,
            int tickets)
            implements RegionTps {

        public int originChunkX() {
            return sectionX << SECTION_SHIFT;
        }

        public int originChunkZ() {
            return sectionZ << SECTION_SHIFT;
        }
    }

    private record RegionKey(Key world, int sectionX, int sectionZ) {
        static RegionKey of(final Key world, final ChunkPos pos) {
            return new RegionKey(world, pos.x() >> SECTION_SHIFT, pos.z() >> SECTION_SHIFT);
        }
    }

    private record DelayedTask(Runnable task, long delayTicks, long sequence) {
    }

    private record ScheduledDelayedTask(Runnable task, long executeAtTick, long sequence) {
        static final Comparator<ScheduledDelayedTask> ORDER = Comparator
                .comparingLong(ScheduledDelayedTask::executeAtTick)
                .thenComparingLong(ScheduledDelayedTask::sequence);
    }

    final class Region extends SchedulableTick {
        final RegionKey key;
        final AtomicInteger emptyTicks = new AtomicInteger();
        final AtomicInteger tickets = new AtomicInteger();

        private final PrioritisedTaskQueue immediate = new PrioritisedTaskQueue();

        private final MultiThreadedQueue<DelayedTask> incomingDelayed = new MultiThreadedQueue<>();

        private final PriorityQueue<ScheduledDelayedTask> delayed =
                new PriorityQueue<>(ScheduledDelayedTask.ORDER);

        private final AtomicInteger pendingDelayed = new AtomicInteger();
        private final AtomicLong sequence = new AtomicLong();

        private final Object tpsLock = new Object();
        private final TickData tickData = new TickData(TPS_WINDOW_NS);

        volatile @Nullable Thread tickingThread;

        private long currentTick;

        private long previousTickStart = TimeUtil.DEADLINE_NOT_SET;
        private long intermediateTimeNS;
        private long intermediateTimeCpuNS;

        Region(final RegionKey key) {
            this.key = key;
        }

        boolean covers(final Key world, final ChunkPos pos) {
            return key.sectionX() == (pos.x() >> SECTION_SHIFT)
                    && key.sectionZ() == (pos.z() >> SECTION_SHIFT)
                    && key.world().equals(world);
        }

        void initScheduledStart(final long nanos) {
            setScheduledStart(nanos);
        }

        void submit(final Runnable task, final long delayTicks) {
            if (delayTicks <= 0L) {
                immediate.queueTask(task, Priority.NORMAL);
            } else {
                pendingDelayed.incrementAndGet();
                incomingDelayed.add(new DelayedTask(task, delayTicks, sequence.getAndIncrement()));
            }
        }

        boolean isIdle() {
            return tickets.get() == 0
                    && immediate.hasNoScheduledTasks()
                    && pendingDelayed.get() == 0
                    && delayed.isEmpty();
        }

        @Override
        public boolean hasTasks() {
            return !immediate.hasNoScheduledTasks();
        }

        @Override
        public boolean runTasks(final BooleanSupplier canContinue) {
            final long start = System.nanoTime();
            final long startCpu = currentThreadCpuNanos();

            enter();
            try {
                do {
                    if (!immediate.executeTask()) break;
                } while (canContinue.getAsBoolean());
            } finally {
                exit();
                final long endCpu = currentThreadCpuNanos();
                intermediateTimeNS += System.nanoTime() - start;
                if (startCpu >= 0 && endCpu >= 0) {
                    intermediateTimeCpuNS += Math.max(0L, endCpu - startCpu);
                }
            }

            return true;
        }

        @Override
        public boolean runTick() {
            final RegionTickProfiler profiler = tickProfiler;
            profiler.heartbeat();

            final long scheduledStart = getScheduledStart();
            final long tickStart = System.nanoTime();
            final long tickStartCpu = currentThreadCpuNanos();

            boolean keepScheduled = true;

            enter();
            try {
                currentTick++;

                drainImmediate();
                drainDelayed();
                runTickHandlers();

                if (isIdle()) {
                    if (emptyTicks.incrementAndGet() >= MAX_EMPTY_TICKS && tryRetire(this)) {
                        keepScheduled = false;
                    }
                } else {
                    emptyTicks.set(0);
                }
            } finally {
                exit();
                final long durationNanos = recordTick(scheduledStart, tickStart, tickStartCpu);
                profiler.reportRegionTick(durationNanos / 1_000_000.0D);
                scheduleNextTick(scheduledStart);
            }

            return keepScheduled;
        }

        private void enter() {
            final Thread self = Thread.currentThread();
            tickingThread = self;
            if (self instanceof final RegionTickThread tickThread) {
                tickThread.currentRegion = this;
            }
        }

        private void exit() {
            if (Thread.currentThread() instanceof final RegionTickThread tickThread) {
                tickThread.currentRegion = null;
            }
            tickingThread = null;
        }

        private void drainImmediate() {
            for (int i = 0; i < MAX_IMMEDIATE_PER_TICK; i++) {
                if (!immediate.executeTask()) break;
            }
        }

        private void drainDelayed() {
            DelayedTask incoming;
            while ((incoming = incomingDelayed.poll()) != null) {
                pendingDelayed.decrementAndGet();
                delayed.add(new ScheduledDelayedTask(
                        incoming.task(), currentTick + incoming.delayTicks(), incoming.sequence()));
            }

            ScheduledDelayedTask due;
            while ((due = delayed.peek()) != null && due.executeAtTick() <= currentTick) {
                delayed.poll();
                try {
                    due.task().run();
                } catch (final Throwable ex) {
                    LOGGER.error("Error in a task of {}", key, ex);
                }
            }
        }

        private void runTickHandlers() {
            for (final RegionTickHandler handler : tickHandlers.getArray()) {
                try {
                    handler.tick(key.world(), key.sectionX(), key.sectionZ(), currentTick);
                } catch (final Throwable ex) {
                    LOGGER.error("Error in a tick handler of {}", key, ex);
                }
            }
        }

        private void scheduleNextTick(final long scheduledStart) {
            final long target = scheduledStart + TICK_INTERVAL_NS;
            final long floor = System.nanoTime() - TICK_INTERVAL_NS;
            setScheduledStart(TimeUtil.getGreatestTime(target, floor));
        }

        private long recordTick(final long scheduledStart, final long tickStart, final long tickStartCpu) {
            final long tickEnd = System.nanoTime();
            final long tickEndCpu = currentThreadCpuNanos();
            final boolean cpuMeasured = CPU_TIME_SUPPORTED && tickStartCpu >= 0 && tickEndCpu >= 0;

            final TickTime time = new TickTime(
                    previousTickStart,
                    scheduledStart,
                    tickStart,
                    tickStartCpu,
                    tickEnd,
                    tickEndCpu,
                    intermediateTimeNS,
                    intermediateTimeCpuNS,
                    cpuMeasured);

            previousTickStart = tickStart;
            intermediateTimeNS = 0L;
            intermediateTimeCpuNS = 0L;

            synchronized (tpsLock) {
                tickData.addDataFrom(time);
            }

            return tickEnd - tickStart;
        }

        @Nullable RegionTpsSnapshot snapshot() {
            final TickData.TickReportData report;
            synchronized (tpsLock) {
                report = tickData.generateTickReport(null, System.nanoTime(), TICK_INTERVAL_NS);
            }
            if (report == null || report.collectedTicks() < 2) return null;

            final double tps = report.tpsData().segmentAll().average();
            final double msptAvg = report.timePerTickData().segmentAll().average() / 1_000_000.0;

            return new RegionTpsSnapshot(
                    key.world(),
                    key.sectionX(),
                    key.sectionZ(),
                    Math.min(tps, 20.0),
                    msptAvg,
                    report.utilisation() * 100.0,
                    queuedTaskCount(),
                    tickets.get());
        }

        private int queuedTaskCount() {
            final long queued = immediate.getTotalTasksScheduled() - immediate.getTotalTasksExecuted();
            return (int) Math.min(
                    Integer.MAX_VALUE, Math.max(0L, queued) + pendingDelayed.get() + delayed.size());
        }
    }

}
