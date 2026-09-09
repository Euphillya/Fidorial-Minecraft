package fr.fidorial.scheduler;

import net.kyori.adventure.key.Key;

/**
 * Represents performance and state information for a scheduler region.
 *
 * <p>A region corresponds to a group of chunks processed by a dedicated
 * scheduler thread. This interface exposes metrics such as TPS, MSPT,
 * CPU usage, queued tasks and ticket count.</p>
 */
public interface RegionTps {

    /**
     * Returns the Key of the world containing this region.
     *
     * @return the world key
     */
    Key world();

    /**
     * Returns the X coordinate of the region section.
     *
     * @return the region section X coordinate
     */
    int sectionX();

    /**
     * Returns the Z coordinate of the region section.
     *
     * @return the region section Z coordinate
     */
    int sectionZ();

    /**
     * Returns the X coordinate of the origin chunk of this region.
     *
     * @return the origin chunk X coordinate
     */
    int originChunkX();

    /**
     * Returns the Z coordinate of the origin chunk of this region.
     *
     * @return the origin chunk Z coordinate
     */
    int originChunkZ();

    /**
     * Returns the current ticks per second (TPS) of this region.
     *
     * <p>The ideal value is {@code 20.0}.</p>
     *
     * @return the current TPS
     */
    double tps();

    /**
     * Returns the average milliseconds per tick (MSPT) for this region.
     *
     * <p>Lower values indicate better performance. A value around
     * {@code 50.0 ms} corresponds to the limit for maintaining 20 TPS.</p>
     *
     * @return the average MSPT
     */
    double msptAvg();

    /**
     * Returns the CPU time consumed by the region thread over the measured
     * window, relative to the wall-clock time elapsed during that same window.
     *
     * <p>A value of {@code 100.0} means the region fully saturates a single CPU
     * core, spending the entire measurement window executing. Values are not
     * capped and may exceed {@code 100.0} if the region falls behind and
     * accumulates additional work.</p>
     *
     * @return the CPU usage of the region, expressed as a percentage of one CPU
     *         core
     */
    double cpuPercent();

    /**
     * Returns the number of tasks currently queued for this region.
     *
     * @return the queued task count
     */
    int queuedTasks();

    /**
     * Returns the number of active tickets associated with this region.
     *
     * <p>Tickets are used internally to keep chunks loaded and scheduled.</p>
     *
     * @return the active ticket count
     */
    int tickets();
}
