package fr.fidorial.item.data;

import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * An immutable <em>patch</em> over an item's default components.
 *
 * @since 0.1.0
 */
public final class DataComponentMap {

    public static final DataComponentMap EMPTY = new DataComponentMap(Map.of(), Set.of());

    private final Map<DataComponentType<?>, Object> values;
    private final Set<DataComponentType<?>> removed;

    private DataComponentMap(final Map<DataComponentType<?>, Object> values, final Set<DataComponentType<?>> removed) {
        this.values = values;
        this.removed = removed;
    }

    /**
     * @return a new builder starting from the empty patch
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Looks up a component.
     *
     * @param type the component to read
     * @param <T>  the component's value type
     * @return the patched value, or {@code null} if this patch neither sets nor removes it
     */
    @SuppressWarnings("unchecked")
    public <T> @Nullable T get(final DataComponentType<T> type) {
        return (T) values.get(Objects.requireNonNull(type, "type"));
    }

    /**
     * Looks up a component, falling back when it is absent.
     *
     * @param type     the component to read
     * @param fallback returned when the component is not set by this patch
     * @param <T>      the component's value type
     * @return the patched value, or {@code fallback}
     */
    public <T> T getOrDefault(final DataComponentType<T> type, final T fallback) {
        final T value = get(type);
        return value != null ? value : fallback;
    }

    /**
     * @param type the component to test
     * @return {@code true} if this patch sets the component
     */
    public boolean has(final DataComponentType<?> type) {
        return values.containsKey(Objects.requireNonNull(type, "type"));
    }

    /**
     * @param type the component to test
     * @return {@code true} if this patch explicitly removes the item's default
     */
    public boolean isRemoved(final DataComponentType<?> type) {
        return removed.contains(Objects.requireNonNull(type, "type"));
    }

    /**
     * Sets a component, replacing any previous value and cancelling a pending removal.
     *
     * @param type  the component to set
     * @param value the value to set it to
     * @param <T>   the component's value type
     * @return a new patch
     */
    public <T> DataComponentMap with(final DataComponentType<T> type, final T value) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(value, "value");

        final Map<DataComponentType<?>, Object> copy = new LinkedHashMap<>(values);
        copy.put(type, value);

        if (!removed.contains(type)) {
            return new DataComponentMap(Collections.unmodifiableMap(copy), removed);
        }

        final Set<DataComponentType<?>> removedCopy = new LinkedHashSet<>(removed);
        removedCopy.remove(type);
        return new DataComponentMap(Collections.unmodifiableMap(copy), Collections.unmodifiableSet(removedCopy));
    }

    /**
     * Removes the item's default for a component, and clears any value this patch set.
     *
     * <p>This is not the same as {@link #reset(DataComponentType)}: the component ends
     * up <em>absent from the item</em>, not merely back at its default.
     *
     * @param type the component to remove
     * @return a new patch
     */
    public DataComponentMap without(final DataComponentType<?> type) {
        Objects.requireNonNull(type, "type");

        final Map<DataComponentType<?>, Object> copy = new LinkedHashMap<>(values);
        copy.remove(type);

        final Set<DataComponentType<?>> removedCopy = new LinkedHashSet<>(removed);
        removedCopy.add(type);

        return new DataComponentMap(Collections.unmodifiableMap(copy), Collections.unmodifiableSet(removedCopy));
    }

    /**
     * Drops this patch's opinion on a component entirely, letting the item's default
     * show through again.
     *
     * @param type the component to reset
     * @return a new patch
     */
    public DataComponentMap reset(final DataComponentType<?> type) {
        Objects.requireNonNull(type, "type");

        if (!values.containsKey(type) && !removed.contains(type)) {
            return this;
        }

        final Map<DataComponentType<?>, Object> copy = new LinkedHashMap<>(values);
        copy.remove(type);

        final Set<DataComponentType<?>> removedCopy = new LinkedHashSet<>(removed);
        removedCopy.remove(type);

        return new DataComponentMap(Collections.unmodifiableMap(copy), Collections.unmodifiableSet(removedCopy));
    }

    /**
     * @return the added/overridden components, in insertion order
     */
    public Set<Map.Entry<DataComponentType<?>, Object>> entries() {
        return values.entrySet();
    }

    /**
     * @return the component types this patch removes, in insertion order
     */
    public Set<DataComponentType<?>> removedTypes() {
        return removed;
    }

    /**
     * @return the number of added/overridden components
     */
    public int size() {
        return values.size();
    }

    /**
     * @return {@code true} if this patch adds nothing and removes nothing
     */
    public boolean isEmpty() {
        return values.isEmpty() && removed.isEmpty();
    }

    /**
     * @return a builder pre-populated with this patch
     */
    public Builder toBuilder() {
        return new Builder(values, removed);
    }

    @Override
    public boolean equals(final Object o) {
        return this == o
                || (o instanceof final DataComponentMap other
                && values.equals(other.values)
                && removed.equals(other.removed));
    }

    @Override
    public int hashCode() {
        return 31 * values.hashCode() + removed.hashCode();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "DataComponentMap{}";
        }
        final StringBuilder builder = new StringBuilder("DataComponentMap{");
        boolean first = true;
        for (final Map.Entry<DataComponentType<?>, Object> entry : values.entrySet()) {
            if (!first) {
                builder.append(", ");
            }
            builder.append(entry.getKey()).append('=').append(entry.getValue());
            first = false;
        }
        for (final DataComponentType<?> type : removed) {
            if (!first) {
                builder.append(", ");
            }
            builder.append('!').append(type);
            first = false;
        }
        return builder.append('}').toString();
    }

    /**
     * Accumulates several component changes without allocating a patch per step.
     *
     * @since 0.1.0
     */
    public static final class Builder {

        private final Map<DataComponentType<?>, Object> values;
        private final Set<DataComponentType<?>> removed;

        private Builder() {
            this.values = new LinkedHashMap<>();
            this.removed = new LinkedHashSet<>();
        }

        private Builder(final Map<DataComponentType<?>, Object> values, final Set<DataComponentType<?>> removed) {
            this.values = new LinkedHashMap<>(values);
            this.removed = new LinkedHashSet<>(removed);
        }

        /**
         * @param type  the component to set
         * @param value the value to set it to
         * @param <T>   the component's value type
         * @return this builder
         */
        public <T> Builder set(final DataComponentType<T> type, final T value) {
            Objects.requireNonNull(type, "type");
            Objects.requireNonNull(value, "value");
            values.put(type, value);
            removed.remove(type);
            return this;
        }

        /**
         * Sets a component only when {@code value} is non-{@code null}.
         *
         * @param type  the component to set
         * @param value the value, or {@code null} to leave the component untouched
         * @param <T>   the component's value type
         * @return this builder
         */
        public <T> Builder setIfPresent(final DataComponentType<T> type, final @Nullable T value) {
            return value == null ? this : set(type, value);
        }

        /**
         * @param type the component whose default to remove
         * @return this builder
         */
        public Builder remove(final DataComponentType<?> type) {
            Objects.requireNonNull(type, "type");
            values.remove(type);
            removed.add(type);
            return this;
        }

        /**
         * @param type the component to stop patching
         * @return this builder
         */
        public Builder reset(final DataComponentType<?> type) {
            Objects.requireNonNull(type, "type");
            values.remove(type);
            removed.remove(type);
            return this;
        }

        /**
         * @return the built patch, or {@link DataComponentMap#EMPTY} when nothing was set
         */
        public DataComponentMap build() {
            if (values.isEmpty() && removed.isEmpty()) {
                return EMPTY;
            }
            return new DataComponentMap(
                    Collections.unmodifiableMap(new LinkedHashMap<>(values)),
                    Collections.unmodifiableSet(new LinkedHashSet<>(removed)));
        }
    }
}
