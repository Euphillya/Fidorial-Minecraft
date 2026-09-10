package fr.fidorial.registry.keys;

import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.Biome;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.adventure.key.KeyPattern;

/**
 * Typed keys for entries in the {@code minecraft:worldgen/biome} registry.
 */
public final class BiomeKeys {
    /**
     * Key for {@code minecraft:badlands}.
     */
    public static final TypedKey<Biome> BADLANDS = create("badlands");

    /**
     * Key for {@code minecraft:bamboo_jungle}.
     */
    public static final TypedKey<Biome> BAMBOO_JUNGLE = create("bamboo_jungle");

    /**
     * Key for {@code minecraft:basalt_deltas}.
     */
    public static final TypedKey<Biome> BASALT_DELTAS = create("basalt_deltas");

    /**
     * Key for {@code minecraft:beach}.
     */
    public static final TypedKey<Biome> BEACH = create("beach");

    /**
     * Key for {@code minecraft:birch_forest}.
     */
    public static final TypedKey<Biome> BIRCH_FOREST = create("birch_forest");

    /**
     * Key for {@code minecraft:cherry_grove}.
     */
    public static final TypedKey<Biome> CHERRY_GROVE = create("cherry_grove");

    /**
     * Key for {@code minecraft:cold_ocean}.
     */
    public static final TypedKey<Biome> COLD_OCEAN = create("cold_ocean");

    /**
     * Key for {@code minecraft:crimson_forest}.
     */
    public static final TypedKey<Biome> CRIMSON_FOREST = create("crimson_forest");

    /**
     * Key for {@code minecraft:dappled_forest}.
     */
    public static final TypedKey<Biome> DAPPLED_FOREST = create("dappled_forest");

    /**
     * Key for {@code minecraft:dark_forest}.
     */
    public static final TypedKey<Biome> DARK_FOREST = create("dark_forest");

    /**
     * Key for {@code minecraft:deep_cold_ocean}.
     */
    public static final TypedKey<Biome> DEEP_COLD_OCEAN = create("deep_cold_ocean");

    /**
     * Key for {@code minecraft:deep_dark}.
     */
    public static final TypedKey<Biome> DEEP_DARK = create("deep_dark");

    /**
     * Key for {@code minecraft:deep_frozen_ocean}.
     */
    public static final TypedKey<Biome> DEEP_FROZEN_OCEAN = create("deep_frozen_ocean");

    /**
     * Key for {@code minecraft:deep_lukewarm_ocean}.
     */
    public static final TypedKey<Biome> DEEP_LUKEWARM_OCEAN = create("deep_lukewarm_ocean");

    /**
     * Key for {@code minecraft:deep_ocean}.
     */
    public static final TypedKey<Biome> DEEP_OCEAN = create("deep_ocean");

    /**
     * Key for {@code minecraft:desert}.
     */
    public static final TypedKey<Biome> DESERT = create("desert");

    /**
     * Key for {@code minecraft:dripstone_caves}.
     */
    public static final TypedKey<Biome> DRIPSTONE_CAVES = create("dripstone_caves");

    /**
     * Key for {@code minecraft:end_barrens}.
     */
    public static final TypedKey<Biome> END_BARRENS = create("end_barrens");

    /**
     * Key for {@code minecraft:end_highlands}.
     */
    public static final TypedKey<Biome> END_HIGHLANDS = create("end_highlands");

    /**
     * Key for {@code minecraft:end_midlands}.
     */
    public static final TypedKey<Biome> END_MIDLANDS = create("end_midlands");

    /**
     * Key for {@code minecraft:eroded_badlands}.
     */
    public static final TypedKey<Biome> ERODED_BADLANDS = create("eroded_badlands");

    /**
     * Key for {@code minecraft:flower_forest}.
     */
    public static final TypedKey<Biome> FLOWER_FOREST = create("flower_forest");

    /**
     * Key for {@code minecraft:forest}.
     */
    public static final TypedKey<Biome> FOREST = create("forest");

    /**
     * Key for {@code minecraft:frozen_ocean}.
     */
    public static final TypedKey<Biome> FROZEN_OCEAN = create("frozen_ocean");

    /**
     * Key for {@code minecraft:frozen_peaks}.
     */
    public static final TypedKey<Biome> FROZEN_PEAKS = create("frozen_peaks");

    /**
     * Key for {@code minecraft:frozen_river}.
     */
    public static final TypedKey<Biome> FROZEN_RIVER = create("frozen_river");

    /**
     * Key for {@code minecraft:grove}.
     */
    public static final TypedKey<Biome> GROVE = create("grove");

    /**
     * Key for {@code minecraft:ice_spikes}.
     */
    public static final TypedKey<Biome> ICE_SPIKES = create("ice_spikes");

    /**
     * Key for {@code minecraft:jagged_peaks}.
     */
    public static final TypedKey<Biome> JAGGED_PEAKS = create("jagged_peaks");

    /**
     * Key for {@code minecraft:jungle}.
     */
    public static final TypedKey<Biome> JUNGLE = create("jungle");

    /**
     * Key for {@code minecraft:lukewarm_ocean}.
     */
    public static final TypedKey<Biome> LUKEWARM_OCEAN = create("lukewarm_ocean");

    /**
     * Key for {@code minecraft:lush_caves}.
     */
    public static final TypedKey<Biome> LUSH_CAVES = create("lush_caves");

    /**
     * Key for {@code minecraft:mangrove_swamp}.
     */
    public static final TypedKey<Biome> MANGROVE_SWAMP = create("mangrove_swamp");

    /**
     * Key for {@code minecraft:meadow}.
     */
    public static final TypedKey<Biome> MEADOW = create("meadow");

    /**
     * Key for {@code minecraft:mushroom_fields}.
     */
    public static final TypedKey<Biome> MUSHROOM_FIELDS = create("mushroom_fields");

    /**
     * Key for {@code minecraft:nether_wastes}.
     */
    public static final TypedKey<Biome> NETHER_WASTES = create("nether_wastes");

    /**
     * Key for {@code minecraft:ocean}.
     */
    public static final TypedKey<Biome> OCEAN = create("ocean");

    /**
     * Key for {@code minecraft:old_growth_birch_forest}.
     */
    public static final TypedKey<Biome> OLD_GROWTH_BIRCH_FOREST = create("old_growth_birch_forest");

    /**
     * Key for {@code minecraft:old_growth_pine_taiga}.
     */
    public static final TypedKey<Biome> OLD_GROWTH_PINE_TAIGA = create("old_growth_pine_taiga");

    /**
     * Key for {@code minecraft:old_growth_spruce_taiga}.
     */
    public static final TypedKey<Biome> OLD_GROWTH_SPRUCE_TAIGA = create("old_growth_spruce_taiga");

    /**
     * Key for {@code minecraft:pale_garden}.
     */
    public static final TypedKey<Biome> PALE_GARDEN = create("pale_garden");

    /**
     * Key for {@code minecraft:plains}.
     */
    public static final TypedKey<Biome> PLAINS = create("plains");

    /**
     * Key for {@code minecraft:river}.
     */
    public static final TypedKey<Biome> RIVER = create("river");

    /**
     * Key for {@code minecraft:savanna}.
     */
    public static final TypedKey<Biome> SAVANNA = create("savanna");

    /**
     * Key for {@code minecraft:savanna_plateau}.
     */
    public static final TypedKey<Biome> SAVANNA_PLATEAU = create("savanna_plateau");

    /**
     * Key for {@code minecraft:small_end_islands}.
     */
    public static final TypedKey<Biome> SMALL_END_ISLANDS = create("small_end_islands");

    /**
     * Key for {@code minecraft:snowy_beach}.
     */
    public static final TypedKey<Biome> SNOWY_BEACH = create("snowy_beach");

    /**
     * Key for {@code minecraft:snowy_plains}.
     */
    public static final TypedKey<Biome> SNOWY_PLAINS = create("snowy_plains");

    /**
     * Key for {@code minecraft:snowy_slopes}.
     */
    public static final TypedKey<Biome> SNOWY_SLOPES = create("snowy_slopes");

    /**
     * Key for {@code minecraft:snowy_taiga}.
     */
    public static final TypedKey<Biome> SNOWY_TAIGA = create("snowy_taiga");

    /**
     * Key for {@code minecraft:soul_sand_valley}.
     */
    public static final TypedKey<Biome> SOUL_SAND_VALLEY = create("soul_sand_valley");

    /**
     * Key for {@code minecraft:sparse_jungle}.
     */
    public static final TypedKey<Biome> SPARSE_JUNGLE = create("sparse_jungle");

    /**
     * Key for {@code minecraft:stony_peaks}.
     */
    public static final TypedKey<Biome> STONY_PEAKS = create("stony_peaks");

    /**
     * Key for {@code minecraft:stony_shore}.
     */
    public static final TypedKey<Biome> STONY_SHORE = create("stony_shore");

    /**
     * Key for {@code minecraft:sulfur_caves}.
     */
    public static final TypedKey<Biome> SULFUR_CAVES = create("sulfur_caves");

    /**
     * Key for {@code minecraft:sunflower_plains}.
     */
    public static final TypedKey<Biome> SUNFLOWER_PLAINS = create("sunflower_plains");

    /**
     * Key for {@code minecraft:swamp}.
     */
    public static final TypedKey<Biome> SWAMP = create("swamp");

    /**
     * Key for {@code minecraft:taiga}.
     */
    public static final TypedKey<Biome> TAIGA = create("taiga");

    /**
     * Key for {@code minecraft:the_end}.
     */
    public static final TypedKey<Biome> THE_END = create("the_end");

    /**
     * Key for {@code minecraft:the_void}.
     */
    public static final TypedKey<Biome> THE_VOID = create("the_void");

    /**
     * Key for {@code minecraft:warm_ocean}.
     */
    public static final TypedKey<Biome> WARM_OCEAN = create("warm_ocean");

    /**
     * Key for {@code minecraft:warped_forest}.
     */
    public static final TypedKey<Biome> WARPED_FOREST = create("warped_forest");

    /**
     * Key for {@code minecraft:windswept_forest}.
     */
    public static final TypedKey<Biome> WINDSWEPT_FOREST = create("windswept_forest");

    /**
     * Key for {@code minecraft:windswept_gravelly_hills}.
     */
    public static final TypedKey<Biome> WINDSWEPT_GRAVELLY_HILLS = create("windswept_gravelly_hills");

    /**
     * Key for {@code minecraft:windswept_hills}.
     */
    public static final TypedKey<Biome> WINDSWEPT_HILLS = create("windswept_hills");

    /**
     * Key for {@code minecraft:windswept_savanna}.
     */
    public static final TypedKey<Biome> WINDSWEPT_SAVANNA = create("windswept_savanna");

    /**
     * Key for {@code minecraft:wooded_badlands}.
     */
    public static final TypedKey<Biome> WOODED_BADLANDS = create("wooded_badlands");

    private static final List<TypedKey<Biome>> VALUES = List.of(
        BADLANDS,
        BAMBOO_JUNGLE,
        BASALT_DELTAS,
        BEACH,
        BIRCH_FOREST,
        CHERRY_GROVE,
        COLD_OCEAN,
        CRIMSON_FOREST,
        DAPPLED_FOREST,
        DARK_FOREST,
        DEEP_COLD_OCEAN,
        DEEP_DARK,
        DEEP_FROZEN_OCEAN,
        DEEP_LUKEWARM_OCEAN,
        DEEP_OCEAN,
        DESERT,
        DRIPSTONE_CAVES,
        END_BARRENS,
        END_HIGHLANDS,
        END_MIDLANDS,
        ERODED_BADLANDS,
        FLOWER_FOREST,
        FOREST,
        FROZEN_OCEAN,
        FROZEN_PEAKS,
        FROZEN_RIVER,
        GROVE,
        ICE_SPIKES,
        JAGGED_PEAKS,
        JUNGLE,
        LUKEWARM_OCEAN,
        LUSH_CAVES,
        MANGROVE_SWAMP,
        MEADOW,
        MUSHROOM_FIELDS,
        NETHER_WASTES,
        OCEAN,
        OLD_GROWTH_BIRCH_FOREST,
        OLD_GROWTH_PINE_TAIGA,
        OLD_GROWTH_SPRUCE_TAIGA,
        PALE_GARDEN,
        PLAINS,
        RIVER,
        SAVANNA,
        SAVANNA_PLATEAU,
        SMALL_END_ISLANDS,
        SNOWY_BEACH,
        SNOWY_PLAINS,
        SNOWY_SLOPES,
        SNOWY_TAIGA,
        SOUL_SAND_VALLEY,
        SPARSE_JUNGLE,
        STONY_PEAKS,
        STONY_SHORE,
        SULFUR_CAVES,
        SUNFLOWER_PLAINS,
        SWAMP,
        TAIGA,
        THE_END,
        THE_VOID,
        WARM_OCEAN,
        WARPED_FOREST,
        WINDSWEPT_FOREST,
        WINDSWEPT_GRAVELLY_HILLS,
        WINDSWEPT_HILLS,
        WINDSWEPT_SAVANNA,
        WOODED_BADLANDS
    );

    private BiomeKeys() {
        throw new UnsupportedOperationException("BiomeKeys cannot be instantiated.");
    }

    private static TypedKey<Biome> create(@KeyPattern final String value) {
        return TypedKey.create(RegistryKey.BIOME, value);
    }

    /**
     * Returns a stream containing all keys declared by this class.
     *
     * @return a stream of registry keys
     */
    public static Stream<TypedKey<Biome>> values() {
        return VALUES.stream();
    }
}
