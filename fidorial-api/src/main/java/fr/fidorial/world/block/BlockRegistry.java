package fr.fidorial.world.block;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public interface BlockRegistry {

    Optional<BlockType> type(Key key);

    default Optional<BlockType> type(@KeyPattern final String key) {
        return type(Key.key(key));
    }

    @Nullable BlockData fromNetworkId(int networkId);

    void register(BlockType type);

    default void register(final BlockBehaviour behaviour) {
        register(behaviour.type());
    }

    default Optional<BlockBehaviour> behaviour(final Key key) {
        return Optional.empty();
    }

    default Optional<BlockBehaviour> behaviour(final BlockData data) {
        return behaviour(data.key());
    }

    Collection<BlockType> types();

    @SuppressWarnings("PatternValidation")
    default @Nullable BlockData parse(final String input) {
        String name = input;
        Map<String, String> values = Map.of();
        final int bracket = input.indexOf('[');
        if (bracket >= 0) {
            if (!input.endsWith("]")) {
                throw new IllegalArgumentException("Missing closing ']' in '" + input + "'");
            }
            name = input.substring(0, bracket);
            values = new LinkedHashMap<>();
            final String body = input.substring(bracket + 1, input.length() - 1);
            if (!body.isEmpty()) {
                for (final String pair : body.split(",")) {
                    final int eq = pair.indexOf('=');
                    if (eq < 0) {
                        throw new IllegalArgumentException("Invalid property '" + pair + "' in '" + input + "'");
                    }
                    values.put(pair.substring(0, eq).trim(), pair.substring(eq + 1).trim());
                }
            }
        }
        final BlockType type = type(name).orElse(null);
        return type == null ? null : type.data(values);
    }
}
