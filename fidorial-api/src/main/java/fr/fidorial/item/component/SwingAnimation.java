package fr.fidorial.item.component;

import java.util.Objects;

/**
 * The animation played when an item is used to attack or interact with something,
 * able to be specified using {@code minecraft:attack_animation} and {@code minecraft:interact_animation}.
 *
 * @param type     the kind of swing to play
 * @param duration how long the animation runs, in ticks
 * @since 0.1.0
 */
public record SwingAnimation(SwingAnimationType type, int duration) {

    public static final SwingAnimation DEFAULT = new SwingAnimation(SwingAnimationType.WHACK, 6);

    public SwingAnimation {
        Objects.requireNonNull(type, "type");

        if (duration < 0) {
            throw new IllegalArgumentException("Duration cannot be negative, got " + duration);
        }
    }

    /**
     * The kind of swing an item's attack or interact animation plays.
     *
     * @since 0.1.0
     */
    public enum SwingAnimationType {
        WHACK,
        STAB
    }
}
