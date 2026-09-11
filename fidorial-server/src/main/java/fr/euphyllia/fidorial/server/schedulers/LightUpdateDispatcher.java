package fr.euphyllia.fidorial.server.schedulers;

import ca.spottedleaf.concurrentutil.collection.MultiThreadedQueue;
import ca.spottedleaf.concurrentutil.executor.PrioritisedExecutor;
import ca.spottedleaf.concurrentutil.executor.thread.BalancedPrioritisedThreadPool;
import ca.spottedleaf.concurrentutil.util.Priority;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundLightUpdatePacket;
import fr.euphyllia.fidorial.server.util.ConcurrentLongSet;
import fr.euphyllia.fidorial.server.world.ChunkNetworkSerializer;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.chunk.ChunkColumn;
import fr.euphyllia.fidorial.server.world.light.FloodFillLightEngine;
import fr.euphyllia.fidorial.server.world.light.LightEnginePool;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.ChunkPos;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongIterable;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class LightUpdateDispatcher {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(LightUpdateDispatcher.class);

    private static final int CLUSTER_SHIFT = 4; // seems to give the most optimal CPS

    private final int lightWorkers;
    private final BalancedPrioritisedThreadPool lightPool;
    private final Map<Bounds, LightEnginePool> enginePools = new ConcurrentHashMap<>();
    private final PrioritisedExecutor lightExecutor;

    private final ChunkRegionScheduler regionScheduler;

    private final AtomicInteger lightThreadId = new AtomicInteger(0);
    private final Consumer<ClientboundPacket> broadcaster;
    private final ChunkNetworkSerializer serializer;
    private final Function<Key, @Nullable ServerWorld> worldLookup;

    private final Map<Key, WorldLightState> states = new ConcurrentHashMap<>();

    private final Map<Key, MultiThreadedQueue<BlockPos>> pendingBlocks = new ConcurrentHashMap<>();
    private final Set<Key> scheduledBlocks = ConcurrentHashMap.newKeySet();
    private final Map<Key, ConcurrentLongSet> pendingChunks = new ConcurrentHashMap<>();
    private final Set<Key> scheduledChunks = ConcurrentHashMap.newKeySet();

    public LightUpdateDispatcher(
            final int lightWorkers,
            final Consumer<ClientboundPacket> broadcaster,
            final ChunkNetworkSerializer serializer,
            final Function<Key, @Nullable ServerWorld> worldLookup
    ) {
        this.lightWorkers = lightWorkers;
        this.lightPool = new BalancedPrioritisedThreadPool(
                BalancedPrioritisedThreadPool.DEFAULT_GROUP_TIME_SLICE,
                r -> Thread.ofPlatform()
                        .name("fidorial-light-worker-" + lightThreadId.incrementAndGet())
                        .unstarted(r)
        );
        this.lightExecutor = lightPool.createOrderedStreamGroup().createExecutor();
        this.regionScheduler = new ChunkRegionScheduler(lightExecutor);
        this.lightPool.adjustThreadCount(Math.max(1, lightWorkers));
        this.broadcaster = broadcaster;
        this.serializer = serializer;
        this.worldLookup = worldLookup;
        LOGGER.info("Light pool started with {} workers", lightWorkers);
    }

    private LightEnginePool poolFor(final ServerWorld serverWorld) {
        final Bounds bounds = new Bounds(serverWorld.minY(), serverWorld.height());
        return enginePools.computeIfAbsent(bounds, b -> new LightEnginePool(lightWorkers, b.minY(), b.height()));
    }

    private static void decrementLocked(final WorldLightState state, final long key) {
        final int count = state.refCounts.get(key);

        if (count == 0) return;

        if (count <= 1) {
            state.refCounts.remove(key);

            final CompletableFuture<@Nullable Void> waiter = state.waiters.remove(key);
            if (waiter != null) {
                waiter.complete(null);
            }
        } else {
            state.refCounts.put(key, count - 1);
        }
    }

    public void queueBlockChange(final Key world, final int x, final int y, final int z) {
        incrementArea(world, x >> 4, z >> 4);

        pendingBlocks
                .computeIfAbsent(world, _ -> new MultiThreadedQueue<>())
                .add(new BlockPos(x, y, z));

        scheduleBlocks(world);
    }

    public void queueChunkLoad(final Key world, final int chunkX, final int chunkZ) {
        final ConcurrentLongSet set = pendingChunks.computeIfAbsent(world, _ -> new ConcurrentLongSet());
        final WorldLightState state = stateFor(world);

        synchronized (state) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    final long key = ChunkPos.chunkKey(chunkX + dx, chunkZ + dz);
                    state.refCounts.addTo(key, 1);
                    if (!set.add(key)) {
                        state.refCounts.addTo(key, -1);
                    }
                }
            }
        }

        scheduleChunks(world);
    }

    private void incrementArea(final Key world, final int chunkX, final int chunkZ) {
        final WorldLightState state = stateFor(world);

        synchronized (state) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    state.refCounts.addTo(ChunkPos.chunkKey(chunkX + dx, chunkZ + dz), 1);
                }
            }
        }
    }

    private void decrementArea(final Key world, final int chunkX, final int chunkZ) {
        final WorldLightState state = states.get(world);
        if (state == null) return;

        synchronized (state) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    decrementLocked(state, ChunkPos.chunkKey(chunkX + dx, chunkZ + dz));
                }
            }
        }
    }

    private void drainBlocks(final Key world) {
        final MultiThreadedQueue<BlockPos> queue = pendingBlocks.get(world);
        if (queue == null) {
            scheduledBlocks.remove(world);
            return;
        }

        final ServerWorld serverWorld = worldLookup.apply(world);
        if (!queue.isEmpty() && serverWorld != null) {
            final LightEnginePool pool = poolFor(serverWorld);
            final Long2ObjectOpenHashMap<List<BlockPos>> byCluster = new Long2ObjectOpenHashMap<>();
            int processed = 0;
            BlockPos pos;
            while (processed < 4096 && (pos = queue.poll()) != null) {
                processed++;
                final int cx = pos.x() >> 4;
                final int cz = pos.z() >> 4;
                final long clusterKey = ChunkPos.chunkKey(cx >> CLUSTER_SHIFT, cz >> CLUSTER_SHIFT);
                byCluster.computeIfAbsent(clusterKey, _ -> new ArrayList<>()).add(pos);
            }

            for (final Long2ObjectOpenHashMap.Entry<List<BlockPos>> entry : byCluster.long2ObjectEntrySet()) {
                final List<BlockPos> positions = entry.getValue();
                final LongOpenHashSet chunkKeys = new LongOpenHashSet();
                for (final BlockPos p : positions) {
                    chunkKeys.add(ChunkPos.chunkKey(p.x() >> 4, p.z() >> 4));
                }
                queueAreaTask(chunkKeys, () -> {
                    FloodFillLightEngine engine = null;
                    try {
                        engine = pool.acquire();
                        final LongOpenHashSet dirtyChunks = new LongOpenHashSet();
                        for (final BlockPos p : positions) {
                            dirtyChunks.addAll(serverWorld.checkBlockLight(p.x(), p.y(), p.z(), engine));
                        }
                        if (!dirtyChunks.isEmpty()) {
                            final LongSet viewedChunks = serverWorld.collectAllViewedChunks();
                            final LongIterator dirtyIt = dirtyChunks.iterator();
                            while (dirtyIt.hasNext()) {
                                final long key = dirtyIt.nextLong();
                                if (!viewedChunks.contains(key)) continue;
                                final ChunkColumn column = serverWorld.loadedColumn((int) (key >> 32), (int) key);
                                if (column == null) {
                                    continue;
                                }
                                broadcaster.accept(new ClientboundLightUpdatePacket(serializer, column, serverWorld.dimensionType().hasSkylight()));
                            }
                        }
                    } catch (final InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        for (final BlockPos p : positions) {
                            decrementArea(world, p.x() >> 4, p.z() >> 4);
                        }
                        if (engine != null) {
                            pool.release(engine);
                        }
                    }
                });
            }
        }
        scheduledBlocks.remove(world);
        if (!queue.isEmpty()) {
            scheduleBlocks(world);
        }
    }

    private void queueAreaTask(final LongOpenHashSet chunkKeys, final Runnable task) {
        if (chunkKeys.isEmpty()) {
            task.run();
            return;
        }

        final LongSet lockKeys = withNeighborRing(chunkKeys);
        regionScheduler.submit(lockKeys, task, Priority.NORMAL);
    }

    private static LongSet withNeighborRing(final LongOpenHashSet chunkKeys) {
        final LongOpenHashSet ring = new LongOpenHashSet(chunkKeys);
        final LongIterator it = chunkKeys.iterator();
        while (it.hasNext()) {
            final long key = it.nextLong();
            final int cx = (int) (key >> 32), cz = (int) key;
            for (int dx = -1; dx <= 1; dx++)
                for (int dz = -1; dz <= 1; dz++) {
                    final long nk = ChunkPos.chunkKey(cx + dx, cz + dz);
                    if (!chunkKeys.contains(nk)) ring.add(nk);
                }
        }
        return ring;
    }

    private void drainChunks(final Key world) {
        final ConcurrentLongSet pending = pendingChunks.get(world);
        if (pending == null) {
            scheduledChunks.remove(world);
            return;
        }

        if (!pending.isEmpty()) {
            final LongSet snapshot = pending.snapshot();
            pending.removeAll(snapshot);

            final ServerWorld serverWorld = worldLookup.apply(world);
            if (serverWorld == null) {
                decrement(world, snapshot.toLongArray());
            } else if (!snapshot.isEmpty()) {
                final Long2ObjectOpenHashMap<LongOpenHashSet> clusters = new Long2ObjectOpenHashMap<>();
                final LongIterator snapIt = snapshot.iterator();
                while (snapIt.hasNext()) {
                    final long key = snapIt.nextLong();
                    final int cx = (int) (key >> 32);
                    final int cz = (int) key;
                    final long clusterKey = ChunkPos.chunkKey(cx >> CLUSTER_SHIFT, cz >> CLUSTER_SHIFT);
                    clusters.computeIfAbsent(clusterKey, _ -> new LongOpenHashSet()).add(key);
                }
                for (final LongOpenHashSet subBatch : clusters.values()) {
                    submitRelightTask(world, serverWorld, subBatch);
                }
            }
        }

        scheduledChunks.remove(world);
        if (!pending.isEmpty()) {
            scheduleChunks(world);
        }
    }

    private void submitRelightTask(final Key world, final ServerWorld serverWorld, final LongOpenHashSet subBatch) {
        final LightEnginePool pool = poolFor(serverWorld);
        queueAreaTask(subBatch, () -> {
            FloodFillLightEngine engine = null;
            try {
                engine = pool.acquire();
                final Set<Long> dirtyChunks = serverWorld.relightChunks(subBatch, engine);
                if (!dirtyChunks.isEmpty()) {
                    final LongSet viewedChunks = serverWorld.collectAllViewedChunks();
                    for (final long key : dirtyChunks) {
                        if (!viewedChunks.contains(key)) continue;
                        final ChunkColumn column = serverWorld.loadedColumn((int) (key >> 32), (int) key);
                        if (column == null) {
                            continue;
                        }
                        broadcaster.accept(new ClientboundLightUpdatePacket(serializer, column, serverWorld.dimensionType().hasSkylight()));
                    }
                }
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (final Throwable t) {
                LOGGER.error("Lighting recalculation impossible for world {}", world, t);
            } finally {
                if (engine != null) pool.release(engine);
                decrement(world, subBatch.toLongArray());
            }
        });
    }

    private void scheduleBlocks(final Key world) {
        if (scheduledBlocks.add(world)) lightExecutor.queueTask(() -> drainBlocks(world), Priority.HIGH);
    }

    private void scheduleChunks(final Key world) {
        if (scheduledChunks.add(world)) lightExecutor.queueTask(() -> drainChunks(world), Priority.HIGH);
    }

    private WorldLightState stateFor(final Key world) {
        return states.computeIfAbsent(world, _ -> new WorldLightState());
    }

    private void increment(final Key world, final long[] chunkKeys, final int length) {
        final WorldLightState state = stateFor(world);
        synchronized (state) {
            for (int i = 0; i < length; i++) {
                state.refCounts.addTo(chunkKeys[i], 1);
            }
        }
    }

    private void decrement(final Key world, final long[] chunkKeys) {
        final WorldLightState state = states.get(world);
        if (state == null) return;
        synchronized (state) {
            for (final long key : chunkKeys) {
                final int count = state.refCounts.get(key);
                if (count == 0) continue;
                if (count <= 1) {
                    state.refCounts.remove(key);
                    final CompletableFuture<@Nullable Void> waiter = state.waiters.remove(key);
                    if (waiter != null) waiter.complete(null);
                } else {
                    state.refCounts.put(key, count - 1);
                }
            }
        }
    }

    public CompletableFuture<Void> flush(final Key world, final LongIterable chunkKeys) {
        final WorldLightState state = states.get(world);
        if (state == null) {
            return CompletableFuture.completedFuture(null);
        }
        final List<CompletableFuture<Void>> futures = new ArrayList<>();
        synchronized (state) {
            for (final long key : chunkKeys) {
                if (state.refCounts.containsKey(key)) {
                    futures.add(state.waiters.computeIfAbsent(key, k -> new CompletableFuture<>()));
                }
            }
        }
        return futures.isEmpty()
                ? CompletableFuture.completedFuture(null)
                : CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    public void shutdown() {
        lightPool.shutdown(false);
        if (!lightPool.join(TimeUnit.SECONDS.toMillis(5L))) {
            lightPool.halt(true);
        }
        LOGGER.info("Light workers stopped");
    }

    private static final class WorldLightState {
        private final Long2IntOpenHashMap refCounts = new Long2IntOpenHashMap();
        private final Long2ObjectOpenHashMap<@Nullable CompletableFuture<Void>> waiters = new Long2ObjectOpenHashMap<>();

        WorldLightState() {
            refCounts.defaultReturnValue(0);
        }
    }

    private record Bounds(int minY, int height) {
    }
}
