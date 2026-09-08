package fr.fidorial.item.data;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public final class DataComponentType<T> implements Keyed {

    private final Key key;
    private final Class<T> valueType;
    private final boolean persistent;

    private DataComponentType(final Key key, final Class<T> valueType, final boolean persistent) {
        this.key = Objects.requireNonNull(key, "key");
        this.valueType = Objects.requireNonNull(valueType, "valueType");
        this.persistent = persistent;
    }


    /**
     * Creates a component type that is both sent to clients and written to disk.
     *
     * @param key       registry key, e.g. {@code minecraft:lore}
     * @param valueType the class of the value this component carries
     * @param <T>       the value this component carries
     * @return a new component type
     */
    public static <T> DataComponentType<T> of(final Key key, final Class<T> valueType) {
        return new DataComponentType<>(key, valueType, true);
    }

    /**
     * @return the class of the value this component carries
     */
    public Class<T> valueType() {
        return valueType;
    }

    /**
     * @return {@code true} if this component survives a server restart
     */
    public boolean persistent() {
        return persistent;
    }

    @Override
    public Key key() {
        return key;
    }

    /**
     * Narrows an untyped value to this component's value type.
     *
     * @param value the value to cast, may be {@code null}
     * @return the value, typed
     * @throws ClassCastException if {@code value} is not of this component's value type
     */
    public @Nullable T cast(final @Nullable Object value) {
        return value == null ? null : valueType.cast(value);
    }

    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof final DataComponentType<?> other && key.equals(other.key));
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return key.asString();
    }
}
