package fr.euphyllia.fidorial.server.util.threading;

import fr.euphyllia.fidorial.server.plugin.PluginStackWalker;
import fr.euphyllia.fidorial.server.schedulers.ThreadedRegionRegionizer;
import fr.euphyllia.fidorial.server.world.ServerChunk;
import fr.fidorial.entity.Entity;
import fr.fidorial.scheduler.RegionizedScheduler;
import fr.fidorial.scheduler.SchedulerSource;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.Locale;

public final class ThreadContexts {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ThreadContexts.class);

    private ThreadContexts() {
    }

    public static final class ThreadViolationException extends IllegalStateException {
        public ThreadViolationException(final String message) {
            super(message);
        }
    }

    public static String describeCurrentThread() {
        final Thread thread = Thread.currentThread();
        return "[thread=" + thread.getName() + ",class=" + thread.getClass().getName() + "]";
    }

    private static String describeOwningThread(final RegionizedScheduler scheduler, final Key world, final ChunkPos pos) {
        if (!(scheduler instanceof final ThreadedRegionRegionizer regionizer)) {
            return "<unavailable for scheduler " + scheduler.getClass().getName() + ">";
        }
        final String owner = regionizer.describeOwningThread(world, pos);
        return owner != null ? owner : "<no active region>";
    }

    public static String describeEntity(final @Nullable Entity entity) {
        if (entity == null) {
            return "{null}";
        }
        final Location loc = entity.location();
        return "{type=" + entity.type().key()
                + ",class=" + entity.getClass().getName()
                + ",entity_id=" + entity.entityId()
                + ",uuid=" + entity.uuid()
                + ",world=" + entity.world().key()
                + ",chunk=" + entity.chunk()
                + ",pos=" + formatVec(loc.x(), loc.y(), loc.z())
                + ",removed=" + entity.isRemoved()
                + "}";
    }

    private static String describePluginCaller() {
        final String pluginId = PluginStackWalker.firstPluginCaller();
        return pluginId != null ? pluginId : "<server>";
    }

    private static String formatVec(final double x, final double y, final double z) {
        return "(" + fmt(x) + "," + fmt(y) + "," + fmt(z) + ")";
    }

    private static String fmt(final double value) {
        return String.format(Locale.ROOT, "%.2f", value);
    }

    public static void checkOwnedByCurrentThread(final Entity entity, final String reason) {
        if (entity.isOwnedByCurrentThread()) {
            return;
        }
        final String message = "Thread failed region ownership check: " + reason
                + ", context=" + describeCurrentThread()
                + ", owner=" + describeOwningThread(entity.world().scheduler(), entity.world().key(), entity.chunk())
                + ", plugin=" + describePluginCaller()
                + ", entity=" + describeEntity(entity);

        final ThreadViolationException violation = new ThreadViolationException(message);
        LOGGER.error(message, violation);
        throw violation;
    }

    public static void checkOwnedByCurrentThread(final World world, final BlockPos pos, final String reason) {
        final ChunkPos chunkPos = ChunkPos.fromBlock(pos.x(), pos.z());
        if (world.scheduler().isOwnedByCurrentThread(world.key(), chunkPos)) {
            return;
        }
        final String message = "Thread failed region ownership check: " + reason
                + ", context=" + describeCurrentThread()
                + ", owner=" + describeOwningThread(world.scheduler(), world.key(), chunkPos)
                + ", plugin=" + describePluginCaller()
                + ", world=" + world.key()
                + ", chunk_pos=" + chunkPos
                + ", pos=" + formatVec(pos.x(), pos.y(), pos.z());

        final ThreadViolationException violation = new ThreadViolationException(message);
        LOGGER.error(message, violation);
        throw violation;
    }

    public static void checkOwnedByCurrentThread(final World world, final ChunkPos pos, final String reason) {
        if (world.scheduler().isOwnedByCurrentThread(world.key(), pos)) {
            return;
        }
        final String message = "Thread failed region ownership check: " + reason
                + ", context=" + describeCurrentThread()
                + ", owner=" + describeOwningThread(world.scheduler(), world.key(), pos)
                + ", plugin=" + describePluginCaller()
                + ", world=" + world.key()
                + ", chunk_pos=" + pos;

        final ThreadViolationException violation = new ThreadViolationException(message);
        LOGGER.error(message, violation);
        throw violation;
    }

    public static void checkOwnedByCurrentThread(final SchedulerSource source, final String reason) {
        if (source instanceof final Entity entity) {
            checkOwnedByCurrentThread(entity, reason);
            return;
        }

        if (source.isOwnedByCurrentThread()) {
            return;
        }

        final String owner;
        final String pos;
        if (source instanceof final ServerChunk chunk) {
            owner = describeOwningThread(chunk.world().scheduler(), chunk.world().key(), chunk.pos());
            pos = chunk.pos().toString();
        } else {
            owner = "<unavailable>";
            pos = "<unavailable>";
        }

        final String message = "Thread failed region ownership check: " + reason
                + ", context=" + describeCurrentThread()
                + ", owner=" + owner
                + ", plugin=" + describePluginCaller()
                + ", source_pos=" + pos
                + ", source=" + source;

        final ThreadViolationException violation = new ThreadViolationException(message);
        LOGGER.error(message, violation);
        throw violation;
    }
}
