package fr.fidorial.world.environment;

import net.kyori.adventure.text.Component;

import java.util.Optional;

/**
 * Controls whether beds can be used to sleep or set a spawn point in a dimension.
 *
 * @since 0.1.0
 */
public record BedRule(
        AccessCondition sleepAllowed,
        AccessCondition spawnAllowed,
        boolean destroysOnUse,
        boolean destroysOnLeave,
        Optional<Component> failureMessage) {

    /**
     * Allows beds to always be used to sleep and set a spawn point.
     *
     * @return bed rule
     * @since 0.1.0
     */
    public static BedRule alwaysAllowed() {
        return new BedRule(AccessCondition.ALWAYS, AccessCondition.ALWAYS, false, false, Optional.empty());
    }

    /**
     * Allows beds to only be used to sleep and set a spawn point when its nighttime.
     *
     * @return bed rule
     * @since 0.1.0
     */
    public static BedRule allowedAtNight() {
        return new BedRule(AccessCondition.WHEN_DARK, AccessCondition.ALWAYS, false, false, Optional.empty());
    }

    /**
     * Allows beds to only be used to sleep and set a spawn point when its nighttime.
     *
     * @param reason the reason shown to the player for why sleep is not allowed
     * @return bed rule
     * @since 0.1.0
     */
    public static BedRule allowedAtNight(final Component reason) {
        return new BedRule(AccessCondition.WHEN_DARK, AccessCondition.ALWAYS, false, false, Optional.of(reason));
    }

    /**
     * Forbids beds from ever being used to sleep or set a spawn point; attempting to use one
     * destroys it immediately.
     *
     * @return bed rule
     * @since 0.1.0
     */
    public static BedRule neverAllowed() {
        return new BedRule(AccessCondition.NEVER, AccessCondition.NEVER, true, false, Optional.empty());
    }

    /**
     * Allows sleeping at night but never setting a spawn point; the bed is destroyed once the
     * player leaves it (e.g. straw beds).
     *
     * @return bed rule
     * @since 0.1.0
     */
    public static BedRule destroyedOnLeave() {
        return new BedRule(AccessCondition.WHEN_DARK, AccessCondition.NEVER, false, true, Optional.empty());
    }

    /**
     * Same as {@link #destroyedOnLeave()}, with a message shown when sleep is refused outside the
     * allowed window.
     *
     * @param reason the reason shown to the player for why sleep is not allowed
     * @return bed rule
     * @since 0.1.0
     */
    public static BedRule destroyedOnLeave(final Component reason) {
        return new BedRule(AccessCondition.WHEN_DARK, AccessCondition.NEVER, false, true, Optional.of(reason));
    }

    /**
     * Controls when a particular bed action is permitted.
     *
     * @since 0.1.0
     */
    public enum AccessCondition {
        /**
         * Always permitted, regardless of the day time.
         */
        ALWAYS("always"),

        /**
         * Only permitted during nighttime.
         */
        WHEN_DARK("when_dark"),

        /**
         * Never permitted.
         */
        NEVER("never");

        private final String id;

        AccessCondition(final String id) {
            this.id = id;
        }

        public String id() {
            return this.id;
        }

        public static Optional<AccessCondition> fromId(final String id) {
            for (final AccessCondition access : values()) {
                if (access.id.equals(id)) {
                    return Optional.of(access);
                }
            }
            return Optional.empty();
        }
    }
}
