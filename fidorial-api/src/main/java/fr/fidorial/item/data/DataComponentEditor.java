package fr.fidorial.item.data;

import org.jspecify.annotations.Nullable;

import java.util.Objects;

/**
 * The writing half of the component model: a mutable, chainable editor over one
 * holder's components.
 *
 * @since 0.1.0
 */
public final class DataComponentEditor implements DataComponentHolder {

    private DataComponentMap components;

    private DataComponentEditor(final DataComponentMap components) {
        this.components = Objects.requireNonNull(components, "components");
    }

    /**
     * @return an editor patching nothing
     * @since 0.1.0
     */
    public static DataComponentEditor empty() {
        return new DataComponentEditor(DataComponentMap.EMPTY);
    }

    /**
     * @param components the patch to start from
     * @return an editor over that patch
     * @since 0.1.0
     */
    public static DataComponentEditor of(final DataComponentMap components) {
        return new DataComponentEditor(components);
    }

    @Override
    public DataComponentMap components() {
        return components;
    }

    /**
     * @return an independent editor holding the same components
     * @since 0.1.0
     */
    public DataComponentEditor copy() {
        return new DataComponentEditor(components);
    }

    /**
     * @return {@code true} when this editor patches nothing
     * @since 0.1.0
     */
    public boolean isEmpty() {
        return components.isEmpty();
    }

    /**
     * @param type  the component to set
     * @param value the value
     * @param <T>   the component's value type
     * @return this editor
     * @since 0.1.0
     */
    public <T> DataComponentEditor set(final DataComponentType<T> type, final T value) {
        components = components.with(type, value);
        return this;
    }

    /**
     * @param type  the component to set
     * @param value the value, or {@code null} to stop patching the component
     * @param <T>   the component's value type
     * @return this editor
     * @since 0.1.0
     */
    public <T> DataComponentEditor setOrReset(final DataComponentType<T> type, final @Nullable T value) {
        return value == null ? reset(type) : set(type, value);
    }

    /**
     * Takes the item's own default away, so the component ends up absent from the
     * item rather than back at its default.
     *
     * @param type the component to remove
     * @return this editor
     * @since 0.1.0
     */
    public DataComponentEditor remove(final DataComponentType<?> type) {
        components = components.without(type);
        return this;
    }

    /**
     * Drops this editor's opinion on a component, letting the item's default show
     * through again.
     *
     * @param type the component to stop patching
     * @return this editor
     * @since 0.1.0
     */
    public DataComponentEditor reset(final DataComponentType<?> type) {
        components = components.reset(type);
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof final DataComponentEditor other && components.equals(other.components));
    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }

    @Override
    public String toString() {
        return "DataComponentEditor{" + components + "}";
    }
}
