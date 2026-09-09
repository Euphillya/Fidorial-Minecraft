package fr.fidorial.item.data;

import org.jspecify.annotations.Nullable;

/**
 * Something that carries a patch over an item's default components.
 *
 * @since 0.1.0
 */
public interface DataComponentHolder {

    /**
     * @return the patch this holder carries
     * @since 0.1.0
     */
    DataComponentMap components();

    /**
     * Reads a component off this holder.
     *
     * @param type the component to read
     * @param <T>  the component's value type
     * @return the value, or {@code null}
     * @since 0.1.0
     */
    default <T> @Nullable T get(final DataComponentType<T> type) {
        return components().get(type);
    }

    /**
     * @param type     the component to read
     * @param fallback returned when the component is unset
     * @param <T>      the component's value type
     * @return the value, or {@code fallback}
     * @since 0.1.0
     */
    default <T> T getOrDefault(final DataComponentType<T> type, final T fallback) {
        return components().getOrDefault(type, fallback);
    }

    /**
     * @param type the component to test
     * @return {@code true} when this holder patches it
     * @since 0.1.0
     */
    default boolean has(final DataComponentType<?> type) {
        return components().has(type);
    }

}
