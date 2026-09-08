package fr.euphyllia.fidorial.server.codecs.item;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.RecordBuilder;
import fr.euphyllia.fidorial.server.codecs.adventure.ComponentCodecs;
import fr.fidorial.item.component.ItemLore;
import fr.fidorial.item.data.DataComponentMap;
import fr.fidorial.item.data.DataComponentType;
import fr.fidorial.item.data.DataComponentTypes;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static fr.euphyllia.fidorial.server.codecs.CommonCodecs.KEY_CODEC;

public final class ItemStackCodecs {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ItemStackCodecs.class);
    public static final String REMOVED_PREFIX = "!";
    private static final Map<DataComponentType<?>, Codec<?>> CODECS = new LinkedHashMap<>();

    public static final Codec<ItemLore> LORE_CODEC = ComponentCodecs.COMPONENT_CODEC
            .listOf()
            .comapFlatMap(
                    lines -> lines.size() > ItemLore.MAX_LINES
                            ? DataResult.error(() -> "Lore has " + lines.size()
                                                     + " lines, the most allowed is " + ItemLore.MAX_LINES)
                            : DataResult.success(new ItemLore(lines)),
                    ItemLore::lines);

    static {
        register(DataComponentTypes.MAX_STACK_SIZE, Codec.INT);
        register(DataComponentTypes.MAX_DAMAGE, Codec.INT);
        register(DataComponentTypes.DAMAGE, Codec.INT);
        register(DataComponentTypes.ITEM_MODEL, KEY_CODEC);
        register(DataComponentTypes.CUSTOM_NAME, ComponentCodecs.COMPONENT_CODEC);
        register(DataComponentTypes.ITEM_NAME, ComponentCodecs.COMPONENT_CODEC);
        register(DataComponentTypes.LORE, LORE_CODEC);
    }

    public static final Codec<DataComponentMap> COMPONENT_MAP_CODEC = new Codec<>() {

        @Override
        public <T> DataResult<Pair<DataComponentMap, T>> decode(final DynamicOps<T> ops, final T input) {

            return ops.getMapValues(input).map(entries -> {

                final DataComponentMap.Builder builder = DataComponentMap.builder();

                entries.forEach(entry -> {
                    final Optional<String> name = ops.getStringValue(entry.getFirst()).result();
                    if (name.isEmpty()) {
                        LOGGER.warn("Skipping component with a non-string key");
                        return;
                    }
                    decodeEntry(ops, builder, name.get(), entry.getSecond());
                });

                return Pair.of(builder.build(), ops.empty());
            });
        }

        @Override
        public <T> DataResult<T> encode(final DataComponentMap input, final DynamicOps<T> ops, final T prefix) {

            final RecordBuilder<T> record = ops.mapBuilder();

            for (final Map.Entry<DataComponentType<?>, Object> entry : input.entries()) {
                final DataComponentType<?> type = entry.getKey();

                if (!type.persistent()) {
                    continue;
                }

                final Codec<?> codec = CODECS.get(type);
                if (codec == null) {
                    LOGGER.debug("Not persisting component {}: no NBT codec", type);
                    continue;
                }

                record.add(type.key().asString(), encodeErased(ops, type, codec, entry.getValue()));
            }

            for (final DataComponentType<?> removed : input.removedTypes()) {
                record.add(REMOVED_PREFIX + removed.key().asString(), ops.emptyMap());
            }

            return record.build(prefix);
        }
    };

    private ItemStackCodecs() {
        throw new UnsupportedOperationException("ItemStackCodecs cannot be instantiated.");
    }

    private static <T> void register(final DataComponentType<T> type, final Codec<T> codec) {
        CODECS.put(type, codec);
    }

    @SuppressWarnings("unchecked")
    public static <T> @Nullable Codec<T> codec(final DataComponentType<T> type) {
        return (Codec<T>) CODECS.get(Objects.requireNonNull(type, "type"));
    }

    private static <T> void decodeEntry(final DynamicOps<T> ops,
                                        final DataComponentMap.Builder builder,
                                        final String rawKey,
                                        final T value) {

        if (rawKey.startsWith(REMOVED_PREFIX)) {
            final String stripped = rawKey.substring(REMOVED_PREFIX.length());
            final DataComponentType<?> removed = lookup(stripped);
            if (removed != null) {
                builder.remove(removed);
            }
            return;
        }

        final DataComponentType<?> type = lookup(rawKey);
        if (type == null) {
            return;
        }

        final Codec<?> codec = CODECS.get(type);
        if (codec == null) {
            LOGGER.warn("Dropping component {} on load: no NBT codec", rawKey);
            return;
        }

        decodeInto(ops, builder, type, codec, value);
    }

    /**
     * Ties the two wildcards together so the decoded value and the component agree.
     */
    @SuppressWarnings("unchecked")
    private static <T, V> void decodeInto(final DynamicOps<T> ops,
                                          final DataComponentMap.Builder builder,
                                          final DataComponentType<V> type,
                                          final Codec<?> codec,
                                          final T value) {

        final DataResult<Pair<V, T>> decoded = ((Codec<V>) codec).decode(ops, value);
        final Optional<Pair<V, T>> result = decoded.result();

        if (result.isEmpty()) {
            LOGGER.warn("Dropping component {} on load: {}", type,
                    decoded.error().map(DataResult.Error::message).orElse("unknown error"));
            return;
        }

        builder.set(type, result.get().getFirst());
    }

    @SuppressWarnings("unchecked")
    private static <T, V> T encodeErased(final DynamicOps<T> ops,
                                         final DataComponentType<V> type,
                                         final Codec<?> codec,
                                         final Object value) {

        return ((Codec<V>) codec)
                .encodeStart(ops, type.valueType().cast(value))
                .resultOrPartial(message -> LOGGER.warn("Failed to encode component {}: {}", type, message))
                .orElseGet(ops::emptyMap);
    }

    private static @Nullable DataComponentType<?> lookup(final String rawKey) {

        if (!Key.parseable(rawKey)) {
            LOGGER.warn("Skipping unparseable component key: {}", rawKey);
            return null;
        }

        final DataComponentType<?> type = DataComponentTypes.byKey(Key.key(rawKey));
        if (type == null) {
            LOGGER.warn("Skipping unmodelled component: {}", rawKey);
        }
        return type;
    }
}
