package fr.fidorial.registry.keys;

import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.BlockTransformer;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.adventure.key.KeyPattern;

/**
 * Typed keys for entries in the {@code minecraft:block_transformer} registry.
 */
public final class BlockTransformerKeys {
    /**
     * Key for {@code minecraft:axe}.
     */
    public static final TypedKey<BlockTransformer> AXE = create("axe");

    /**
     * Key for {@code minecraft:hoe}.
     */
    public static final TypedKey<BlockTransformer> HOE = create("hoe");

    /**
     * Key for {@code minecraft:shovel}.
     */
    public static final TypedKey<BlockTransformer> SHOVEL = create("shovel");

    private static final List<TypedKey<BlockTransformer>> VALUES = List.of(
        AXE,
        HOE,
        SHOVEL
    );

    private BlockTransformerKeys() {
        throw new UnsupportedOperationException("BlockTransformerKeys cannot be instantiated.");
    }

    private static TypedKey<BlockTransformer> create(@KeyPattern final String value) {
        return TypedKey.create(RegistryKey.BLOCK_TRANSFORMER, value);
    }

    /**
     * Returns a stream containing all keys declared by this class.
     *
     * @return a stream of registry keys
     */
    public static Stream<TypedKey<BlockTransformer>> values() {
        return VALUES.stream();
    }
}
