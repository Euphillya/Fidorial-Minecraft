package fr.fidorial.entity.ai;

import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

/**
 * The prioritized list of {@link Goal goals} driving a mob.
 *
 * @since 0.1.0
 */
public interface Goals {

    /**
     * Adds a goal and re-sorts the container by priority.
     *
     * @param goal the goal to add
     * @since 0.1.0
     */
    void add(Goal goal);

    /**
     * @param goal the goal to drop
     * @return {@code true} if the goal was present
     * @since 0.1.0
     */
    boolean remove(Goal goal);

    /**
     * @param filter selects the goals to drop
     * @return {@code true} if at least one goal was dropped
     * @since 0.1.0
     */
    boolean removeIf(Predicate<Goal> filter);

    /**
     * @param type the goal implementation to drop
     * @return {@code true} if at least one goal was dropped
     * @since 0.1.0
     */
    default boolean removeType(final Class<? extends Goal> type) {
        return removeIf(type::isInstance);
    }

    /**
     * Drops every goal, stopping the active one first.
     *
     * @since 0.1.0
     */
    void clear();

    /**
     * @return an immutable snapshot of the goals, sorted by priority
     * @since 0.1.0
     */
    List<Goal> all();

    /**
     * @return the goal currently running, or {@code null} when the mob is idle
     * @since 0.1.0
     */
    @Nullable Goal active();

    /**
     * Stops the active goal without removing anything.
     *
     * @since 0.1.0
     */
    void stopAll();
}
