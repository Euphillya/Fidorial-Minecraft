package fr.fidorial.scheduler;

import fr.fidorial.world.ChunkPos;
import net.kyori.adventure.key.Key;

import java.util.List;

/**
 * Schedules tasks to run on the region thread that owns a given world position.
 *
 * <p>The server world is split into independently-ticked regions, each owned by a single worker
 * thread. Tasks submitted through this scheduler run on whichever thread currently owns the
 * targeted {@code (worldName, pos)}, rather than on the calling thread.</p>
 *
 * @since 0.1.0
 */
public interface RegionizedScheduler {

    /**
     * Schedules {@code task} to run as soon as possible on the region thread owning {@code pos}.
     *
     * @param worldName the key of the world the position belongs to
     * @param pos       the chunk position identifying the target region
     * @param task      the task to run
     * @since 0.1.0
     */
    void execute(Key worldName, ChunkPos pos, Runnable task);

    /**
     * Schedules {@code task} to run on the region thread owning {@code pos}, after at least
     * {@code delayTicks} server ticks have elapsed.
     *
     * @param worldName  the key of the world the position belongs to
     * @param pos        the chunk position identifying the target region
     * @param task       the task to run
     * @param delayTicks the minimum number of ticks to wait before running the task
     * @since 0.1.0
     */
    void executeDelayed(Key worldName, ChunkPos pos, Runnable task, long delayTicks);

    /**
     * @param worldName the key of the world the position belongs to
     * @param pos       the chunk position identifying the target region
     * @return {@code true} if the calling thread is the region thread that currently owns
     * {@code pos}
     * @since 0.1.0
     */
    boolean isOwnedByCurrentThread(Key worldName, ChunkPos pos);

    /**
     * Takes a snapshot of the current per-region tick performance.
     *
     * @return the tick performance of every currently active region, ordered by ascending TPS
     * (worst-performing regions first)
     * @since 0.1.0
     */
    List<? extends RegionTps> tpsSnapshots();
}
