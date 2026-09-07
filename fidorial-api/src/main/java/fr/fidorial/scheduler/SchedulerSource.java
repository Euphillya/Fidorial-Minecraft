package fr.fidorial.scheduler;

/**
 * Represents a {@code SchedulerSource}, capable of having tasks scheduled to it.
 * @since 0.1.0
 */
public interface SchedulerSource {
    /**
     * @return {@code false} if the source is removed and the task was not scheduled
     * @since 0.1.0
     */
    boolean execute(Runnable task);

    /**
     * @return {@code false} if the source is removed and the task was not scheduled
     * @since 0.1.0
     */
    boolean executeDelayed(Runnable task, long delayTicks);

    /**
     * @return {@code false} if the source is not owned by the current thread
     * @since 0.1.0
     */
    boolean isOwnedByCurrentThread();
}
