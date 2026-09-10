package fr.fidorial.registry.keys;

import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.BlockType;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.adventure.key.KeyPattern;

/**
 * Typed keys for entries in the {@code minecraft:block} registry.
 */
public final class BlockTypeKeys {
    /**
     * Key for {@code minecraft:acacia_button}.
     */
    public static final TypedKey<BlockType> ACACIA_BUTTON = create("acacia_button");

    /**
     * Key for {@code minecraft:acacia_door}.
     */
    public static final TypedKey<BlockType> ACACIA_DOOR = create("acacia_door");

    /**
     * Key for {@code minecraft:acacia_fence}.
     */
    public static final TypedKey<BlockType> ACACIA_FENCE = create("acacia_fence");

    /**
     * Key for {@code minecraft:acacia_fence_gate}.
     */
    public static final TypedKey<BlockType> ACACIA_FENCE_GATE = create("acacia_fence_gate");

    /**
     * Key for {@code minecraft:acacia_hanging_sign}.
     */
    public static final TypedKey<BlockType> ACACIA_HANGING_SIGN = create("acacia_hanging_sign");

    /**
     * Key for {@code minecraft:acacia_leaves}.
     */
    public static final TypedKey<BlockType> ACACIA_LEAVES = create("acacia_leaves");

    /**
     * Key for {@code minecraft:acacia_log}.
     */
    public static final TypedKey<BlockType> ACACIA_LOG = create("acacia_log");

    /**
     * Key for {@code minecraft:acacia_planks}.
     */
    public static final TypedKey<BlockType> ACACIA_PLANKS = create("acacia_planks");

    /**
     * Key for {@code minecraft:acacia_pressure_plate}.
     */
    public static final TypedKey<BlockType> ACACIA_PRESSURE_PLATE = create("acacia_pressure_plate");

    /**
     * Key for {@code minecraft:acacia_sapling}.
     */
    public static final TypedKey<BlockType> ACACIA_SAPLING = create("acacia_sapling");

    /**
     * Key for {@code minecraft:acacia_shelf}.
     */
    public static final TypedKey<BlockType> ACACIA_SHELF = create("acacia_shelf");

    /**
     * Key for {@code minecraft:acacia_sign}.
     */
    public static final TypedKey<BlockType> ACACIA_SIGN = create("acacia_sign");

    /**
     * Key for {@code minecraft:acacia_slab}.
     */
    public static final TypedKey<BlockType> ACACIA_SLAB = create("acacia_slab");

    /**
     * Key for {@code minecraft:acacia_stairs}.
     */
    public static final TypedKey<BlockType> ACACIA_STAIRS = create("acacia_stairs");

    /**
     * Key for {@code minecraft:acacia_trapdoor}.
     */
    public static final TypedKey<BlockType> ACACIA_TRAPDOOR = create("acacia_trapdoor");

    /**
     * Key for {@code minecraft:acacia_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> ACACIA_WALL_HANGING_SIGN = create("acacia_wall_hanging_sign");

    /**
     * Key for {@code minecraft:acacia_wall_sign}.
     */
    public static final TypedKey<BlockType> ACACIA_WALL_SIGN = create("acacia_wall_sign");

    /**
     * Key for {@code minecraft:acacia_wood}.
     */
    public static final TypedKey<BlockType> ACACIA_WOOD = create("acacia_wood");

    /**
     * Key for {@code minecraft:activator_rail}.
     */
    public static final TypedKey<BlockType> ACTIVATOR_RAIL = create("activator_rail");

    /**
     * Key for {@code minecraft:air}.
     */
    public static final TypedKey<BlockType> AIR = create("air");

    /**
     * Key for {@code minecraft:allium}.
     */
    public static final TypedKey<BlockType> ALLIUM = create("allium");

    /**
     * Key for {@code minecraft:amethyst_block}.
     */
    public static final TypedKey<BlockType> AMETHYST_BLOCK = create("amethyst_block");

    /**
     * Key for {@code minecraft:amethyst_cluster}.
     */
    public static final TypedKey<BlockType> AMETHYST_CLUSTER = create("amethyst_cluster");

    /**
     * Key for {@code minecraft:ancient_debris}.
     */
    public static final TypedKey<BlockType> ANCIENT_DEBRIS = create("ancient_debris");

    /**
     * Key for {@code minecraft:andesite}.
     */
    public static final TypedKey<BlockType> ANDESITE = create("andesite");

    /**
     * Key for {@code minecraft:andesite_slab}.
     */
    public static final TypedKey<BlockType> ANDESITE_SLAB = create("andesite_slab");

    /**
     * Key for {@code minecraft:andesite_stairs}.
     */
    public static final TypedKey<BlockType> ANDESITE_STAIRS = create("andesite_stairs");

    /**
     * Key for {@code minecraft:andesite_wall}.
     */
    public static final TypedKey<BlockType> ANDESITE_WALL = create("andesite_wall");

    /**
     * Key for {@code minecraft:anvil}.
     */
    public static final TypedKey<BlockType> ANVIL = create("anvil");

    /**
     * Key for {@code minecraft:attached_melon_stem}.
     */
    public static final TypedKey<BlockType> ATTACHED_MELON_STEM = create("attached_melon_stem");

    /**
     * Key for {@code minecraft:attached_pumpkin_stem}.
     */
    public static final TypedKey<BlockType> ATTACHED_PUMPKIN_STEM = create("attached_pumpkin_stem");

    /**
     * Key for {@code minecraft:azalea}.
     */
    public static final TypedKey<BlockType> AZALEA = create("azalea");

    /**
     * Key for {@code minecraft:azalea_leaves}.
     */
    public static final TypedKey<BlockType> AZALEA_LEAVES = create("azalea_leaves");

    /**
     * Key for {@code minecraft:azure_bluet}.
     */
    public static final TypedKey<BlockType> AZURE_BLUET = create("azure_bluet");

    /**
     * Key for {@code minecraft:bamboo}.
     */
    public static final TypedKey<BlockType> BAMBOO = create("bamboo");

    /**
     * Key for {@code minecraft:bamboo_block}.
     */
    public static final TypedKey<BlockType> BAMBOO_BLOCK = create("bamboo_block");

    /**
     * Key for {@code minecraft:bamboo_button}.
     */
    public static final TypedKey<BlockType> BAMBOO_BUTTON = create("bamboo_button");

    /**
     * Key for {@code minecraft:bamboo_door}.
     */
    public static final TypedKey<BlockType> BAMBOO_DOOR = create("bamboo_door");

    /**
     * Key for {@code minecraft:bamboo_fence}.
     */
    public static final TypedKey<BlockType> BAMBOO_FENCE = create("bamboo_fence");

    /**
     * Key for {@code minecraft:bamboo_fence_gate}.
     */
    public static final TypedKey<BlockType> BAMBOO_FENCE_GATE = create("bamboo_fence_gate");

    /**
     * Key for {@code minecraft:bamboo_hanging_sign}.
     */
    public static final TypedKey<BlockType> BAMBOO_HANGING_SIGN = create("bamboo_hanging_sign");

    /**
     * Key for {@code minecraft:bamboo_mosaic}.
     */
    public static final TypedKey<BlockType> BAMBOO_MOSAIC = create("bamboo_mosaic");

    /**
     * Key for {@code minecraft:bamboo_mosaic_slab}.
     */
    public static final TypedKey<BlockType> BAMBOO_MOSAIC_SLAB = create("bamboo_mosaic_slab");

    /**
     * Key for {@code minecraft:bamboo_mosaic_stairs}.
     */
    public static final TypedKey<BlockType> BAMBOO_MOSAIC_STAIRS = create("bamboo_mosaic_stairs");

    /**
     * Key for {@code minecraft:bamboo_planks}.
     */
    public static final TypedKey<BlockType> BAMBOO_PLANKS = create("bamboo_planks");

    /**
     * Key for {@code minecraft:bamboo_pressure_plate}.
     */
    public static final TypedKey<BlockType> BAMBOO_PRESSURE_PLATE = create("bamboo_pressure_plate");

    /**
     * Key for {@code minecraft:bamboo_sapling}.
     */
    public static final TypedKey<BlockType> BAMBOO_SAPLING = create("bamboo_sapling");

    /**
     * Key for {@code minecraft:bamboo_shelf}.
     */
    public static final TypedKey<BlockType> BAMBOO_SHELF = create("bamboo_shelf");

    /**
     * Key for {@code minecraft:bamboo_sign}.
     */
    public static final TypedKey<BlockType> BAMBOO_SIGN = create("bamboo_sign");

    /**
     * Key for {@code minecraft:bamboo_slab}.
     */
    public static final TypedKey<BlockType> BAMBOO_SLAB = create("bamboo_slab");

    /**
     * Key for {@code minecraft:bamboo_stairs}.
     */
    public static final TypedKey<BlockType> BAMBOO_STAIRS = create("bamboo_stairs");

    /**
     * Key for {@code minecraft:bamboo_trapdoor}.
     */
    public static final TypedKey<BlockType> BAMBOO_TRAPDOOR = create("bamboo_trapdoor");

    /**
     * Key for {@code minecraft:bamboo_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> BAMBOO_WALL_HANGING_SIGN = create("bamboo_wall_hanging_sign");

    /**
     * Key for {@code minecraft:bamboo_wall_sign}.
     */
    public static final TypedKey<BlockType> BAMBOO_WALL_SIGN = create("bamboo_wall_sign");

    /**
     * Key for {@code minecraft:barrel}.
     */
    public static final TypedKey<BlockType> BARREL = create("barrel");

    /**
     * Key for {@code minecraft:barrier}.
     */
    public static final TypedKey<BlockType> BARRIER = create("barrier");

    /**
     * Key for {@code minecraft:basalt}.
     */
    public static final TypedKey<BlockType> BASALT = create("basalt");

    /**
     * Key for {@code minecraft:beacon}.
     */
    public static final TypedKey<BlockType> BEACON = create("beacon");

    /**
     * Key for {@code minecraft:bedrock}.
     */
    public static final TypedKey<BlockType> BEDROCK = create("bedrock");

    /**
     * Key for {@code minecraft:bee_nest}.
     */
    public static final TypedKey<BlockType> BEE_NEST = create("bee_nest");

    /**
     * Key for {@code minecraft:beehive}.
     */
    public static final TypedKey<BlockType> BEEHIVE = create("beehive");

    /**
     * Key for {@code minecraft:beetroots}.
     */
    public static final TypedKey<BlockType> BEETROOTS = create("beetroots");

    /**
     * Key for {@code minecraft:bell}.
     */
    public static final TypedKey<BlockType> BELL = create("bell");

    /**
     * Key for {@code minecraft:big_dripleaf}.
     */
    public static final TypedKey<BlockType> BIG_DRIPLEAF = create("big_dripleaf");

    /**
     * Key for {@code minecraft:big_dripleaf_stem}.
     */
    public static final TypedKey<BlockType> BIG_DRIPLEAF_STEM = create("big_dripleaf_stem");

    /**
     * Key for {@code minecraft:birch_button}.
     */
    public static final TypedKey<BlockType> BIRCH_BUTTON = create("birch_button");

    /**
     * Key for {@code minecraft:birch_door}.
     */
    public static final TypedKey<BlockType> BIRCH_DOOR = create("birch_door");

    /**
     * Key for {@code minecraft:birch_fence}.
     */
    public static final TypedKey<BlockType> BIRCH_FENCE = create("birch_fence");

    /**
     * Key for {@code minecraft:birch_fence_gate}.
     */
    public static final TypedKey<BlockType> BIRCH_FENCE_GATE = create("birch_fence_gate");

    /**
     * Key for {@code minecraft:birch_hanging_sign}.
     */
    public static final TypedKey<BlockType> BIRCH_HANGING_SIGN = create("birch_hanging_sign");

    /**
     * Key for {@code minecraft:birch_leaves}.
     */
    public static final TypedKey<BlockType> BIRCH_LEAVES = create("birch_leaves");

    /**
     * Key for {@code minecraft:birch_log}.
     */
    public static final TypedKey<BlockType> BIRCH_LOG = create("birch_log");

    /**
     * Key for {@code minecraft:birch_planks}.
     */
    public static final TypedKey<BlockType> BIRCH_PLANKS = create("birch_planks");

    /**
     * Key for {@code minecraft:birch_pressure_plate}.
     */
    public static final TypedKey<BlockType> BIRCH_PRESSURE_PLATE = create("birch_pressure_plate");

    /**
     * Key for {@code minecraft:birch_sapling}.
     */
    public static final TypedKey<BlockType> BIRCH_SAPLING = create("birch_sapling");

    /**
     * Key for {@code minecraft:birch_shelf}.
     */
    public static final TypedKey<BlockType> BIRCH_SHELF = create("birch_shelf");

    /**
     * Key for {@code minecraft:birch_sign}.
     */
    public static final TypedKey<BlockType> BIRCH_SIGN = create("birch_sign");

    /**
     * Key for {@code minecraft:birch_slab}.
     */
    public static final TypedKey<BlockType> BIRCH_SLAB = create("birch_slab");

    /**
     * Key for {@code minecraft:birch_stairs}.
     */
    public static final TypedKey<BlockType> BIRCH_STAIRS = create("birch_stairs");

    /**
     * Key for {@code minecraft:birch_trapdoor}.
     */
    public static final TypedKey<BlockType> BIRCH_TRAPDOOR = create("birch_trapdoor");

    /**
     * Key for {@code minecraft:birch_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> BIRCH_WALL_HANGING_SIGN = create("birch_wall_hanging_sign");

    /**
     * Key for {@code minecraft:birch_wall_sign}.
     */
    public static final TypedKey<BlockType> BIRCH_WALL_SIGN = create("birch_wall_sign");

    /**
     * Key for {@code minecraft:birch_wood}.
     */
    public static final TypedKey<BlockType> BIRCH_WOOD = create("birch_wood");

    /**
     * Key for {@code minecraft:black_banner}.
     */
    public static final TypedKey<BlockType> BLACK_BANNER = create("black_banner");

    /**
     * Key for {@code minecraft:black_bed}.
     */
    public static final TypedKey<BlockType> BLACK_BED = create("black_bed");

    /**
     * Key for {@code minecraft:black_candle}.
     */
    public static final TypedKey<BlockType> BLACK_CANDLE = create("black_candle");

    /**
     * Key for {@code minecraft:black_candle_cake}.
     */
    public static final TypedKey<BlockType> BLACK_CANDLE_CAKE = create("black_candle_cake");

    /**
     * Key for {@code minecraft:black_carpet}.
     */
    public static final TypedKey<BlockType> BLACK_CARPET = create("black_carpet");

    /**
     * Key for {@code minecraft:black_concrete}.
     */
    public static final TypedKey<BlockType> BLACK_CONCRETE = create("black_concrete");

    /**
     * Key for {@code minecraft:black_concrete_powder}.
     */
    public static final TypedKey<BlockType> BLACK_CONCRETE_POWDER = create("black_concrete_powder");

    /**
     * Key for {@code minecraft:black_concrete_slab}.
     */
    public static final TypedKey<BlockType> BLACK_CONCRETE_SLAB = create("black_concrete_slab");

    /**
     * Key for {@code minecraft:black_concrete_stairs}.
     */
    public static final TypedKey<BlockType> BLACK_CONCRETE_STAIRS = create("black_concrete_stairs");

    /**
     * Key for {@code minecraft:black_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> BLACK_GLAZED_TERRACOTTA = create("black_glazed_terracotta");

    /**
     * Key for {@code minecraft:black_shulker_box}.
     */
    public static final TypedKey<BlockType> BLACK_SHULKER_BOX = create("black_shulker_box");

    /**
     * Key for {@code minecraft:black_stained_glass}.
     */
    public static final TypedKey<BlockType> BLACK_STAINED_GLASS = create("black_stained_glass");

    /**
     * Key for {@code minecraft:black_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> BLACK_STAINED_GLASS_PANE = create("black_stained_glass_pane");

    /**
     * Key for {@code minecraft:black_terracotta}.
     */
    public static final TypedKey<BlockType> BLACK_TERRACOTTA = create("black_terracotta");

    /**
     * Key for {@code minecraft:black_wall_banner}.
     */
    public static final TypedKey<BlockType> BLACK_WALL_BANNER = create("black_wall_banner");

    /**
     * Key for {@code minecraft:black_wool}.
     */
    public static final TypedKey<BlockType> BLACK_WOOL = create("black_wool");

    /**
     * Key for {@code minecraft:black_wool_slab}.
     */
    public static final TypedKey<BlockType> BLACK_WOOL_SLAB = create("black_wool_slab");

    /**
     * Key for {@code minecraft:black_wool_stairs}.
     */
    public static final TypedKey<BlockType> BLACK_WOOL_STAIRS = create("black_wool_stairs");

    /**
     * Key for {@code minecraft:blackstone}.
     */
    public static final TypedKey<BlockType> BLACKSTONE = create("blackstone");

    /**
     * Key for {@code minecraft:blackstone_slab}.
     */
    public static final TypedKey<BlockType> BLACKSTONE_SLAB = create("blackstone_slab");

    /**
     * Key for {@code minecraft:blackstone_stairs}.
     */
    public static final TypedKey<BlockType> BLACKSTONE_STAIRS = create("blackstone_stairs");

    /**
     * Key for {@code minecraft:blackstone_wall}.
     */
    public static final TypedKey<BlockType> BLACKSTONE_WALL = create("blackstone_wall");

    /**
     * Key for {@code minecraft:blast_furnace}.
     */
    public static final TypedKey<BlockType> BLAST_FURNACE = create("blast_furnace");

    /**
     * Key for {@code minecraft:blue_banner}.
     */
    public static final TypedKey<BlockType> BLUE_BANNER = create("blue_banner");

    /**
     * Key for {@code minecraft:blue_bed}.
     */
    public static final TypedKey<BlockType> BLUE_BED = create("blue_bed");

    /**
     * Key for {@code minecraft:blue_candle}.
     */
    public static final TypedKey<BlockType> BLUE_CANDLE = create("blue_candle");

    /**
     * Key for {@code minecraft:blue_candle_cake}.
     */
    public static final TypedKey<BlockType> BLUE_CANDLE_CAKE = create("blue_candle_cake");

    /**
     * Key for {@code minecraft:blue_carpet}.
     */
    public static final TypedKey<BlockType> BLUE_CARPET = create("blue_carpet");

    /**
     * Key for {@code minecraft:blue_concrete}.
     */
    public static final TypedKey<BlockType> BLUE_CONCRETE = create("blue_concrete");

    /**
     * Key for {@code minecraft:blue_concrete_powder}.
     */
    public static final TypedKey<BlockType> BLUE_CONCRETE_POWDER = create("blue_concrete_powder");

    /**
     * Key for {@code minecraft:blue_concrete_slab}.
     */
    public static final TypedKey<BlockType> BLUE_CONCRETE_SLAB = create("blue_concrete_slab");

    /**
     * Key for {@code minecraft:blue_concrete_stairs}.
     */
    public static final TypedKey<BlockType> BLUE_CONCRETE_STAIRS = create("blue_concrete_stairs");

    /**
     * Key for {@code minecraft:blue_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> BLUE_GLAZED_TERRACOTTA = create("blue_glazed_terracotta");

    /**
     * Key for {@code minecraft:blue_ice}.
     */
    public static final TypedKey<BlockType> BLUE_ICE = create("blue_ice");

    /**
     * Key for {@code minecraft:blue_orchid}.
     */
    public static final TypedKey<BlockType> BLUE_ORCHID = create("blue_orchid");

    /**
     * Key for {@code minecraft:blue_shulker_box}.
     */
    public static final TypedKey<BlockType> BLUE_SHULKER_BOX = create("blue_shulker_box");

    /**
     * Key for {@code minecraft:blue_stained_glass}.
     */
    public static final TypedKey<BlockType> BLUE_STAINED_GLASS = create("blue_stained_glass");

    /**
     * Key for {@code minecraft:blue_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> BLUE_STAINED_GLASS_PANE = create("blue_stained_glass_pane");

    /**
     * Key for {@code minecraft:blue_terracotta}.
     */
    public static final TypedKey<BlockType> BLUE_TERRACOTTA = create("blue_terracotta");

    /**
     * Key for {@code minecraft:blue_wall_banner}.
     */
    public static final TypedKey<BlockType> BLUE_WALL_BANNER = create("blue_wall_banner");

    /**
     * Key for {@code minecraft:blue_wool}.
     */
    public static final TypedKey<BlockType> BLUE_WOOL = create("blue_wool");

    /**
     * Key for {@code minecraft:blue_wool_slab}.
     */
    public static final TypedKey<BlockType> BLUE_WOOL_SLAB = create("blue_wool_slab");

    /**
     * Key for {@code minecraft:blue_wool_stairs}.
     */
    public static final TypedKey<BlockType> BLUE_WOOL_STAIRS = create("blue_wool_stairs");

    /**
     * Key for {@code minecraft:bone_block}.
     */
    public static final TypedKey<BlockType> BONE_BLOCK = create("bone_block");

    /**
     * Key for {@code minecraft:bookshelf}.
     */
    public static final TypedKey<BlockType> BOOKSHELF = create("bookshelf");

    /**
     * Key for {@code minecraft:brain_coral}.
     */
    public static final TypedKey<BlockType> BRAIN_CORAL = create("brain_coral");

    /**
     * Key for {@code minecraft:brain_coral_block}.
     */
    public static final TypedKey<BlockType> BRAIN_CORAL_BLOCK = create("brain_coral_block");

    /**
     * Key for {@code minecraft:brain_coral_fan}.
     */
    public static final TypedKey<BlockType> BRAIN_CORAL_FAN = create("brain_coral_fan");

    /**
     * Key for {@code minecraft:brain_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> BRAIN_CORAL_WALL_FAN = create("brain_coral_wall_fan");

    /**
     * Key for {@code minecraft:brewing_stand}.
     */
    public static final TypedKey<BlockType> BREWING_STAND = create("brewing_stand");

    /**
     * Key for {@code minecraft:brick_slab}.
     */
    public static final TypedKey<BlockType> BRICK_SLAB = create("brick_slab");

    /**
     * Key for {@code minecraft:brick_stairs}.
     */
    public static final TypedKey<BlockType> BRICK_STAIRS = create("brick_stairs");

    /**
     * Key for {@code minecraft:brick_wall}.
     */
    public static final TypedKey<BlockType> BRICK_WALL = create("brick_wall");

    /**
     * Key for {@code minecraft:bricks}.
     */
    public static final TypedKey<BlockType> BRICKS = create("bricks");

    /**
     * Key for {@code minecraft:brown_banner}.
     */
    public static final TypedKey<BlockType> BROWN_BANNER = create("brown_banner");

    /**
     * Key for {@code minecraft:brown_bed}.
     */
    public static final TypedKey<BlockType> BROWN_BED = create("brown_bed");

    /**
     * Key for {@code minecraft:brown_candle}.
     */
    public static final TypedKey<BlockType> BROWN_CANDLE = create("brown_candle");

    /**
     * Key for {@code minecraft:brown_candle_cake}.
     */
    public static final TypedKey<BlockType> BROWN_CANDLE_CAKE = create("brown_candle_cake");

    /**
     * Key for {@code minecraft:brown_carpet}.
     */
    public static final TypedKey<BlockType> BROWN_CARPET = create("brown_carpet");

    /**
     * Key for {@code minecraft:brown_concrete}.
     */
    public static final TypedKey<BlockType> BROWN_CONCRETE = create("brown_concrete");

    /**
     * Key for {@code minecraft:brown_concrete_powder}.
     */
    public static final TypedKey<BlockType> BROWN_CONCRETE_POWDER = create("brown_concrete_powder");

    /**
     * Key for {@code minecraft:brown_concrete_slab}.
     */
    public static final TypedKey<BlockType> BROWN_CONCRETE_SLAB = create("brown_concrete_slab");

    /**
     * Key for {@code minecraft:brown_concrete_stairs}.
     */
    public static final TypedKey<BlockType> BROWN_CONCRETE_STAIRS = create("brown_concrete_stairs");

    /**
     * Key for {@code minecraft:brown_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> BROWN_GLAZED_TERRACOTTA = create("brown_glazed_terracotta");

    /**
     * Key for {@code minecraft:brown_mushroom}.
     */
    public static final TypedKey<BlockType> BROWN_MUSHROOM = create("brown_mushroom");

    /**
     * Key for {@code minecraft:brown_mushroom_block}.
     */
    public static final TypedKey<BlockType> BROWN_MUSHROOM_BLOCK = create("brown_mushroom_block");

    /**
     * Key for {@code minecraft:brown_shulker_box}.
     */
    public static final TypedKey<BlockType> BROWN_SHULKER_BOX = create("brown_shulker_box");

    /**
     * Key for {@code minecraft:brown_stained_glass}.
     */
    public static final TypedKey<BlockType> BROWN_STAINED_GLASS = create("brown_stained_glass");

    /**
     * Key for {@code minecraft:brown_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> BROWN_STAINED_GLASS_PANE = create("brown_stained_glass_pane");

    /**
     * Key for {@code minecraft:brown_terracotta}.
     */
    public static final TypedKey<BlockType> BROWN_TERRACOTTA = create("brown_terracotta");

    /**
     * Key for {@code minecraft:brown_wall_banner}.
     */
    public static final TypedKey<BlockType> BROWN_WALL_BANNER = create("brown_wall_banner");

    /**
     * Key for {@code minecraft:brown_wool}.
     */
    public static final TypedKey<BlockType> BROWN_WOOL = create("brown_wool");

    /**
     * Key for {@code minecraft:brown_wool_slab}.
     */
    public static final TypedKey<BlockType> BROWN_WOOL_SLAB = create("brown_wool_slab");

    /**
     * Key for {@code minecraft:brown_wool_stairs}.
     */
    public static final TypedKey<BlockType> BROWN_WOOL_STAIRS = create("brown_wool_stairs");

    /**
     * Key for {@code minecraft:bubble_column}.
     */
    public static final TypedKey<BlockType> BUBBLE_COLUMN = create("bubble_column");

    /**
     * Key for {@code minecraft:bubble_coral}.
     */
    public static final TypedKey<BlockType> BUBBLE_CORAL = create("bubble_coral");

    /**
     * Key for {@code minecraft:bubble_coral_block}.
     */
    public static final TypedKey<BlockType> BUBBLE_CORAL_BLOCK = create("bubble_coral_block");

    /**
     * Key for {@code minecraft:bubble_coral_fan}.
     */
    public static final TypedKey<BlockType> BUBBLE_CORAL_FAN = create("bubble_coral_fan");

    /**
     * Key for {@code minecraft:bubble_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> BUBBLE_CORAL_WALL_FAN = create("bubble_coral_wall_fan");

    /**
     * Key for {@code minecraft:budding_amethyst}.
     */
    public static final TypedKey<BlockType> BUDDING_AMETHYST = create("budding_amethyst");

    /**
     * Key for {@code minecraft:bush}.
     */
    public static final TypedKey<BlockType> BUSH = create("bush");

    /**
     * Key for {@code minecraft:cactus}.
     */
    public static final TypedKey<BlockType> CACTUS = create("cactus");

    /**
     * Key for {@code minecraft:cactus_flower}.
     */
    public static final TypedKey<BlockType> CACTUS_FLOWER = create("cactus_flower");

    /**
     * Key for {@code minecraft:cake}.
     */
    public static final TypedKey<BlockType> CAKE = create("cake");

    /**
     * Key for {@code minecraft:calcite}.
     */
    public static final TypedKey<BlockType> CALCITE = create("calcite");

    /**
     * Key for {@code minecraft:calibrated_sculk_sensor}.
     */
    public static final TypedKey<BlockType> CALIBRATED_SCULK_SENSOR = create("calibrated_sculk_sensor");

    /**
     * Key for {@code minecraft:campfire}.
     */
    public static final TypedKey<BlockType> CAMPFIRE = create("campfire");

    /**
     * Key for {@code minecraft:candle}.
     */
    public static final TypedKey<BlockType> CANDLE = create("candle");

    /**
     * Key for {@code minecraft:candle_cake}.
     */
    public static final TypedKey<BlockType> CANDLE_CAKE = create("candle_cake");

    /**
     * Key for {@code minecraft:carrots}.
     */
    public static final TypedKey<BlockType> CARROTS = create("carrots");

    /**
     * Key for {@code minecraft:cartography_table}.
     */
    public static final TypedKey<BlockType> CARTOGRAPHY_TABLE = create("cartography_table");

    /**
     * Key for {@code minecraft:carved_pumpkin}.
     */
    public static final TypedKey<BlockType> CARVED_PUMPKIN = create("carved_pumpkin");

    /**
     * Key for {@code minecraft:cauldron}.
     */
    public static final TypedKey<BlockType> CAULDRON = create("cauldron");

    /**
     * Key for {@code minecraft:cave_air}.
     */
    public static final TypedKey<BlockType> CAVE_AIR = create("cave_air");

    /**
     * Key for {@code minecraft:cave_vines}.
     */
    public static final TypedKey<BlockType> CAVE_VINES = create("cave_vines");

    /**
     * Key for {@code minecraft:cave_vines_plant}.
     */
    public static final TypedKey<BlockType> CAVE_VINES_PLANT = create("cave_vines_plant");

    /**
     * Key for {@code minecraft:chain_command_block}.
     */
    public static final TypedKey<BlockType> CHAIN_COMMAND_BLOCK = create("chain_command_block");

    /**
     * Key for {@code minecraft:cherry_button}.
     */
    public static final TypedKey<BlockType> CHERRY_BUTTON = create("cherry_button");

    /**
     * Key for {@code minecraft:cherry_door}.
     */
    public static final TypedKey<BlockType> CHERRY_DOOR = create("cherry_door");

    /**
     * Key for {@code minecraft:cherry_fence}.
     */
    public static final TypedKey<BlockType> CHERRY_FENCE = create("cherry_fence");

    /**
     * Key for {@code minecraft:cherry_fence_gate}.
     */
    public static final TypedKey<BlockType> CHERRY_FENCE_GATE = create("cherry_fence_gate");

    /**
     * Key for {@code minecraft:cherry_hanging_sign}.
     */
    public static final TypedKey<BlockType> CHERRY_HANGING_SIGN = create("cherry_hanging_sign");

    /**
     * Key for {@code minecraft:cherry_leaves}.
     */
    public static final TypedKey<BlockType> CHERRY_LEAVES = create("cherry_leaves");

    /**
     * Key for {@code minecraft:cherry_log}.
     */
    public static final TypedKey<BlockType> CHERRY_LOG = create("cherry_log");

    /**
     * Key for {@code minecraft:cherry_planks}.
     */
    public static final TypedKey<BlockType> CHERRY_PLANKS = create("cherry_planks");

    /**
     * Key for {@code minecraft:cherry_pressure_plate}.
     */
    public static final TypedKey<BlockType> CHERRY_PRESSURE_PLATE = create("cherry_pressure_plate");

    /**
     * Key for {@code minecraft:cherry_sapling}.
     */
    public static final TypedKey<BlockType> CHERRY_SAPLING = create("cherry_sapling");

    /**
     * Key for {@code minecraft:cherry_shelf}.
     */
    public static final TypedKey<BlockType> CHERRY_SHELF = create("cherry_shelf");

    /**
     * Key for {@code minecraft:cherry_sign}.
     */
    public static final TypedKey<BlockType> CHERRY_SIGN = create("cherry_sign");

    /**
     * Key for {@code minecraft:cherry_slab}.
     */
    public static final TypedKey<BlockType> CHERRY_SLAB = create("cherry_slab");

    /**
     * Key for {@code minecraft:cherry_stairs}.
     */
    public static final TypedKey<BlockType> CHERRY_STAIRS = create("cherry_stairs");

    /**
     * Key for {@code minecraft:cherry_trapdoor}.
     */
    public static final TypedKey<BlockType> CHERRY_TRAPDOOR = create("cherry_trapdoor");

    /**
     * Key for {@code minecraft:cherry_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> CHERRY_WALL_HANGING_SIGN = create("cherry_wall_hanging_sign");

    /**
     * Key for {@code minecraft:cherry_wall_sign}.
     */
    public static final TypedKey<BlockType> CHERRY_WALL_SIGN = create("cherry_wall_sign");

    /**
     * Key for {@code minecraft:cherry_wood}.
     */
    public static final TypedKey<BlockType> CHERRY_WOOD = create("cherry_wood");

    /**
     * Key for {@code minecraft:chest}.
     */
    public static final TypedKey<BlockType> CHEST = create("chest");

    /**
     * Key for {@code minecraft:chipped_anvil}.
     */
    public static final TypedKey<BlockType> CHIPPED_ANVIL = create("chipped_anvil");

    /**
     * Key for {@code minecraft:chiseled_bookshelf}.
     */
    public static final TypedKey<BlockType> CHISELED_BOOKSHELF = create("chiseled_bookshelf");

    /**
     * Key for {@code minecraft:chiseled_cinnabar}.
     */
    public static final TypedKey<BlockType> CHISELED_CINNABAR = create("chiseled_cinnabar");

    /**
     * Key for {@code minecraft:chiseled_copper}.
     */
    public static final TypedKey<BlockType> CHISELED_COPPER = create("chiseled_copper");

    /**
     * Key for {@code minecraft:chiseled_deepslate}.
     */
    public static final TypedKey<BlockType> CHISELED_DEEPSLATE = create("chiseled_deepslate");

    /**
     * Key for {@code minecraft:chiseled_nether_bricks}.
     */
    public static final TypedKey<BlockType> CHISELED_NETHER_BRICKS = create("chiseled_nether_bricks");

    /**
     * Key for {@code minecraft:chiseled_polished_blackstone}.
     */
    public static final TypedKey<BlockType> CHISELED_POLISHED_BLACKSTONE = create("chiseled_polished_blackstone");

    /**
     * Key for {@code minecraft:chiseled_quartz_block}.
     */
    public static final TypedKey<BlockType> CHISELED_QUARTZ_BLOCK = create("chiseled_quartz_block");

    /**
     * Key for {@code minecraft:chiseled_red_sandstone}.
     */
    public static final TypedKey<BlockType> CHISELED_RED_SANDSTONE = create("chiseled_red_sandstone");

    /**
     * Key for {@code minecraft:chiseled_resin_bricks}.
     */
    public static final TypedKey<BlockType> CHISELED_RESIN_BRICKS = create("chiseled_resin_bricks");

    /**
     * Key for {@code minecraft:chiseled_sandstone}.
     */
    public static final TypedKey<BlockType> CHISELED_SANDSTONE = create("chiseled_sandstone");

    /**
     * Key for {@code minecraft:chiseled_stone_bricks}.
     */
    public static final TypedKey<BlockType> CHISELED_STONE_BRICKS = create("chiseled_stone_bricks");

    /**
     * Key for {@code minecraft:chiseled_sulfur}.
     */
    public static final TypedKey<BlockType> CHISELED_SULFUR = create("chiseled_sulfur");

    /**
     * Key for {@code minecraft:chiseled_tuff}.
     */
    public static final TypedKey<BlockType> CHISELED_TUFF = create("chiseled_tuff");

    /**
     * Key for {@code minecraft:chiseled_tuff_bricks}.
     */
    public static final TypedKey<BlockType> CHISELED_TUFF_BRICKS = create("chiseled_tuff_bricks");

    /**
     * Key for {@code minecraft:chorus_flower}.
     */
    public static final TypedKey<BlockType> CHORUS_FLOWER = create("chorus_flower");

    /**
     * Key for {@code minecraft:chorus_plant}.
     */
    public static final TypedKey<BlockType> CHORUS_PLANT = create("chorus_plant");

    /**
     * Key for {@code minecraft:cinnabar}.
     */
    public static final TypedKey<BlockType> CINNABAR = create("cinnabar");

    /**
     * Key for {@code minecraft:cinnabar_brick_slab}.
     */
    public static final TypedKey<BlockType> CINNABAR_BRICK_SLAB = create("cinnabar_brick_slab");

    /**
     * Key for {@code minecraft:cinnabar_brick_stairs}.
     */
    public static final TypedKey<BlockType> CINNABAR_BRICK_STAIRS = create("cinnabar_brick_stairs");

    /**
     * Key for {@code minecraft:cinnabar_brick_wall}.
     */
    public static final TypedKey<BlockType> CINNABAR_BRICK_WALL = create("cinnabar_brick_wall");

    /**
     * Key for {@code minecraft:cinnabar_bricks}.
     */
    public static final TypedKey<BlockType> CINNABAR_BRICKS = create("cinnabar_bricks");

    /**
     * Key for {@code minecraft:cinnabar_slab}.
     */
    public static final TypedKey<BlockType> CINNABAR_SLAB = create("cinnabar_slab");

    /**
     * Key for {@code minecraft:cinnabar_stairs}.
     */
    public static final TypedKey<BlockType> CINNABAR_STAIRS = create("cinnabar_stairs");

    /**
     * Key for {@code minecraft:cinnabar_wall}.
     */
    public static final TypedKey<BlockType> CINNABAR_WALL = create("cinnabar_wall");

    /**
     * Key for {@code minecraft:clay}.
     */
    public static final TypedKey<BlockType> CLAY = create("clay");

    /**
     * Key for {@code minecraft:closed_eyeblossom}.
     */
    public static final TypedKey<BlockType> CLOSED_EYEBLOSSOM = create("closed_eyeblossom");

    /**
     * Key for {@code minecraft:coal_block}.
     */
    public static final TypedKey<BlockType> COAL_BLOCK = create("coal_block");

    /**
     * Key for {@code minecraft:coal_ore}.
     */
    public static final TypedKey<BlockType> COAL_ORE = create("coal_ore");

    /**
     * Key for {@code minecraft:coarse_dirt}.
     */
    public static final TypedKey<BlockType> COARSE_DIRT = create("coarse_dirt");

    /**
     * Key for {@code minecraft:cobbled_deepslate}.
     */
    public static final TypedKey<BlockType> COBBLED_DEEPSLATE = create("cobbled_deepslate");

    /**
     * Key for {@code minecraft:cobbled_deepslate_slab}.
     */
    public static final TypedKey<BlockType> COBBLED_DEEPSLATE_SLAB = create("cobbled_deepslate_slab");

    /**
     * Key for {@code minecraft:cobbled_deepslate_stairs}.
     */
    public static final TypedKey<BlockType> COBBLED_DEEPSLATE_STAIRS = create("cobbled_deepslate_stairs");

    /**
     * Key for {@code minecraft:cobbled_deepslate_wall}.
     */
    public static final TypedKey<BlockType> COBBLED_DEEPSLATE_WALL = create("cobbled_deepslate_wall");

    /**
     * Key for {@code minecraft:cobblestone}.
     */
    public static final TypedKey<BlockType> COBBLESTONE = create("cobblestone");

    /**
     * Key for {@code minecraft:cobblestone_slab}.
     */
    public static final TypedKey<BlockType> COBBLESTONE_SLAB = create("cobblestone_slab");

    /**
     * Key for {@code minecraft:cobblestone_stairs}.
     */
    public static final TypedKey<BlockType> COBBLESTONE_STAIRS = create("cobblestone_stairs");

    /**
     * Key for {@code minecraft:cobblestone_wall}.
     */
    public static final TypedKey<BlockType> COBBLESTONE_WALL = create("cobblestone_wall");

    /**
     * Key for {@code minecraft:cobweb}.
     */
    public static final TypedKey<BlockType> COBWEB = create("cobweb");

    /**
     * Key for {@code minecraft:cocoa}.
     */
    public static final TypedKey<BlockType> COCOA = create("cocoa");

    /**
     * Key for {@code minecraft:command_block}.
     */
    public static final TypedKey<BlockType> COMMAND_BLOCK = create("command_block");

    /**
     * Key for {@code minecraft:comparator}.
     */
    public static final TypedKey<BlockType> COMPARATOR = create("comparator");

    /**
     * Key for {@code minecraft:composter}.
     */
    public static final TypedKey<BlockType> COMPOSTER = create("composter");

    /**
     * Key for {@code minecraft:conduit}.
     */
    public static final TypedKey<BlockType> CONDUIT = create("conduit");

    /**
     * Key for {@code minecraft:copper_bars}.
     */
    public static final TypedKey<BlockType> COPPER_BARS = create("copper_bars");

    /**
     * Key for {@code minecraft:copper_block}.
     */
    public static final TypedKey<BlockType> COPPER_BLOCK = create("copper_block");

    /**
     * Key for {@code minecraft:copper_bulb}.
     */
    public static final TypedKey<BlockType> COPPER_BULB = create("copper_bulb");

    /**
     * Key for {@code minecraft:copper_chain}.
     */
    public static final TypedKey<BlockType> COPPER_CHAIN = create("copper_chain");

    /**
     * Key for {@code minecraft:copper_chest}.
     */
    public static final TypedKey<BlockType> COPPER_CHEST = create("copper_chest");

    /**
     * Key for {@code minecraft:copper_door}.
     */
    public static final TypedKey<BlockType> COPPER_DOOR = create("copper_door");

    /**
     * Key for {@code minecraft:copper_golem_statue}.
     */
    public static final TypedKey<BlockType> COPPER_GOLEM_STATUE = create("copper_golem_statue");

    /**
     * Key for {@code minecraft:copper_grate}.
     */
    public static final TypedKey<BlockType> COPPER_GRATE = create("copper_grate");

    /**
     * Key for {@code minecraft:copper_lantern}.
     */
    public static final TypedKey<BlockType> COPPER_LANTERN = create("copper_lantern");

    /**
     * Key for {@code minecraft:copper_ore}.
     */
    public static final TypedKey<BlockType> COPPER_ORE = create("copper_ore");

    /**
     * Key for {@code minecraft:copper_torch}.
     */
    public static final TypedKey<BlockType> COPPER_TORCH = create("copper_torch");

    /**
     * Key for {@code minecraft:copper_trapdoor}.
     */
    public static final TypedKey<BlockType> COPPER_TRAPDOOR = create("copper_trapdoor");

    /**
     * Key for {@code minecraft:copper_wall_torch}.
     */
    public static final TypedKey<BlockType> COPPER_WALL_TORCH = create("copper_wall_torch");

    /**
     * Key for {@code minecraft:cornflower}.
     */
    public static final TypedKey<BlockType> CORNFLOWER = create("cornflower");

    /**
     * Key for {@code minecraft:cracked_deepslate_bricks}.
     */
    public static final TypedKey<BlockType> CRACKED_DEEPSLATE_BRICKS = create("cracked_deepslate_bricks");

    /**
     * Key for {@code minecraft:cracked_deepslate_tiles}.
     */
    public static final TypedKey<BlockType> CRACKED_DEEPSLATE_TILES = create("cracked_deepslate_tiles");

    /**
     * Key for {@code minecraft:cracked_nether_bricks}.
     */
    public static final TypedKey<BlockType> CRACKED_NETHER_BRICKS = create("cracked_nether_bricks");

    /**
     * Key for {@code minecraft:cracked_polished_blackstone_bricks}.
     */
    public static final TypedKey<BlockType> CRACKED_POLISHED_BLACKSTONE_BRICKS = create("cracked_polished_blackstone_bricks");

    /**
     * Key for {@code minecraft:cracked_stone_bricks}.
     */
    public static final TypedKey<BlockType> CRACKED_STONE_BRICKS = create("cracked_stone_bricks");

    /**
     * Key for {@code minecraft:crafter}.
     */
    public static final TypedKey<BlockType> CRAFTER = create("crafter");

    /**
     * Key for {@code minecraft:crafting_table}.
     */
    public static final TypedKey<BlockType> CRAFTING_TABLE = create("crafting_table");

    /**
     * Key for {@code minecraft:creaking_heart}.
     */
    public static final TypedKey<BlockType> CREAKING_HEART = create("creaking_heart");

    /**
     * Key for {@code minecraft:creeper_head}.
     */
    public static final TypedKey<BlockType> CREEPER_HEAD = create("creeper_head");

    /**
     * Key for {@code minecraft:creeper_wall_head}.
     */
    public static final TypedKey<BlockType> CREEPER_WALL_HEAD = create("creeper_wall_head");

    /**
     * Key for {@code minecraft:crimson_button}.
     */
    public static final TypedKey<BlockType> CRIMSON_BUTTON = create("crimson_button");

    /**
     * Key for {@code minecraft:crimson_door}.
     */
    public static final TypedKey<BlockType> CRIMSON_DOOR = create("crimson_door");

    /**
     * Key for {@code minecraft:crimson_fence}.
     */
    public static final TypedKey<BlockType> CRIMSON_FENCE = create("crimson_fence");

    /**
     * Key for {@code minecraft:crimson_fence_gate}.
     */
    public static final TypedKey<BlockType> CRIMSON_FENCE_GATE = create("crimson_fence_gate");

    /**
     * Key for {@code minecraft:crimson_fungus}.
     */
    public static final TypedKey<BlockType> CRIMSON_FUNGUS = create("crimson_fungus");

    /**
     * Key for {@code minecraft:crimson_hanging_sign}.
     */
    public static final TypedKey<BlockType> CRIMSON_HANGING_SIGN = create("crimson_hanging_sign");

    /**
     * Key for {@code minecraft:crimson_hyphae}.
     */
    public static final TypedKey<BlockType> CRIMSON_HYPHAE = create("crimson_hyphae");

    /**
     * Key for {@code minecraft:crimson_nylium}.
     */
    public static final TypedKey<BlockType> CRIMSON_NYLIUM = create("crimson_nylium");

    /**
     * Key for {@code minecraft:crimson_planks}.
     */
    public static final TypedKey<BlockType> CRIMSON_PLANKS = create("crimson_planks");

    /**
     * Key for {@code minecraft:crimson_pressure_plate}.
     */
    public static final TypedKey<BlockType> CRIMSON_PRESSURE_PLATE = create("crimson_pressure_plate");

    /**
     * Key for {@code minecraft:crimson_roots}.
     */
    public static final TypedKey<BlockType> CRIMSON_ROOTS = create("crimson_roots");

    /**
     * Key for {@code minecraft:crimson_shelf}.
     */
    public static final TypedKey<BlockType> CRIMSON_SHELF = create("crimson_shelf");

    /**
     * Key for {@code minecraft:crimson_sign}.
     */
    public static final TypedKey<BlockType> CRIMSON_SIGN = create("crimson_sign");

    /**
     * Key for {@code minecraft:crimson_slab}.
     */
    public static final TypedKey<BlockType> CRIMSON_SLAB = create("crimson_slab");

    /**
     * Key for {@code minecraft:crimson_stairs}.
     */
    public static final TypedKey<BlockType> CRIMSON_STAIRS = create("crimson_stairs");

    /**
     * Key for {@code minecraft:crimson_stem}.
     */
    public static final TypedKey<BlockType> CRIMSON_STEM = create("crimson_stem");

    /**
     * Key for {@code minecraft:crimson_trapdoor}.
     */
    public static final TypedKey<BlockType> CRIMSON_TRAPDOOR = create("crimson_trapdoor");

    /**
     * Key for {@code minecraft:crimson_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> CRIMSON_WALL_HANGING_SIGN = create("crimson_wall_hanging_sign");

    /**
     * Key for {@code minecraft:crimson_wall_sign}.
     */
    public static final TypedKey<BlockType> CRIMSON_WALL_SIGN = create("crimson_wall_sign");

    /**
     * Key for {@code minecraft:crying_obsidian}.
     */
    public static final TypedKey<BlockType> CRYING_OBSIDIAN = create("crying_obsidian");

    /**
     * Key for {@code minecraft:cut_copper}.
     */
    public static final TypedKey<BlockType> CUT_COPPER = create("cut_copper");

    /**
     * Key for {@code minecraft:cut_copper_slab}.
     */
    public static final TypedKey<BlockType> CUT_COPPER_SLAB = create("cut_copper_slab");

    /**
     * Key for {@code minecraft:cut_copper_stairs}.
     */
    public static final TypedKey<BlockType> CUT_COPPER_STAIRS = create("cut_copper_stairs");

    /**
     * Key for {@code minecraft:cut_red_sandstone}.
     */
    public static final TypedKey<BlockType> CUT_RED_SANDSTONE = create("cut_red_sandstone");

    /**
     * Key for {@code minecraft:cut_red_sandstone_slab}.
     */
    public static final TypedKey<BlockType> CUT_RED_SANDSTONE_SLAB = create("cut_red_sandstone_slab");

    /**
     * Key for {@code minecraft:cut_sandstone}.
     */
    public static final TypedKey<BlockType> CUT_SANDSTONE = create("cut_sandstone");

    /**
     * Key for {@code minecraft:cut_sandstone_slab}.
     */
    public static final TypedKey<BlockType> CUT_SANDSTONE_SLAB = create("cut_sandstone_slab");

    /**
     * Key for {@code minecraft:cyan_banner}.
     */
    public static final TypedKey<BlockType> CYAN_BANNER = create("cyan_banner");

    /**
     * Key for {@code minecraft:cyan_bed}.
     */
    public static final TypedKey<BlockType> CYAN_BED = create("cyan_bed");

    /**
     * Key for {@code minecraft:cyan_candle}.
     */
    public static final TypedKey<BlockType> CYAN_CANDLE = create("cyan_candle");

    /**
     * Key for {@code minecraft:cyan_candle_cake}.
     */
    public static final TypedKey<BlockType> CYAN_CANDLE_CAKE = create("cyan_candle_cake");

    /**
     * Key for {@code minecraft:cyan_carpet}.
     */
    public static final TypedKey<BlockType> CYAN_CARPET = create("cyan_carpet");

    /**
     * Key for {@code minecraft:cyan_concrete}.
     */
    public static final TypedKey<BlockType> CYAN_CONCRETE = create("cyan_concrete");

    /**
     * Key for {@code minecraft:cyan_concrete_powder}.
     */
    public static final TypedKey<BlockType> CYAN_CONCRETE_POWDER = create("cyan_concrete_powder");

    /**
     * Key for {@code minecraft:cyan_concrete_slab}.
     */
    public static final TypedKey<BlockType> CYAN_CONCRETE_SLAB = create("cyan_concrete_slab");

    /**
     * Key for {@code minecraft:cyan_concrete_stairs}.
     */
    public static final TypedKey<BlockType> CYAN_CONCRETE_STAIRS = create("cyan_concrete_stairs");

    /**
     * Key for {@code minecraft:cyan_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> CYAN_GLAZED_TERRACOTTA = create("cyan_glazed_terracotta");

    /**
     * Key for {@code minecraft:cyan_shulker_box}.
     */
    public static final TypedKey<BlockType> CYAN_SHULKER_BOX = create("cyan_shulker_box");

    /**
     * Key for {@code minecraft:cyan_stained_glass}.
     */
    public static final TypedKey<BlockType> CYAN_STAINED_GLASS = create("cyan_stained_glass");

    /**
     * Key for {@code minecraft:cyan_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> CYAN_STAINED_GLASS_PANE = create("cyan_stained_glass_pane");

    /**
     * Key for {@code minecraft:cyan_terracotta}.
     */
    public static final TypedKey<BlockType> CYAN_TERRACOTTA = create("cyan_terracotta");

    /**
     * Key for {@code minecraft:cyan_wall_banner}.
     */
    public static final TypedKey<BlockType> CYAN_WALL_BANNER = create("cyan_wall_banner");

    /**
     * Key for {@code minecraft:cyan_wool}.
     */
    public static final TypedKey<BlockType> CYAN_WOOL = create("cyan_wool");

    /**
     * Key for {@code minecraft:cyan_wool_slab}.
     */
    public static final TypedKey<BlockType> CYAN_WOOL_SLAB = create("cyan_wool_slab");

    /**
     * Key for {@code minecraft:cyan_wool_stairs}.
     */
    public static final TypedKey<BlockType> CYAN_WOOL_STAIRS = create("cyan_wool_stairs");

    /**
     * Key for {@code minecraft:damaged_anvil}.
     */
    public static final TypedKey<BlockType> DAMAGED_ANVIL = create("damaged_anvil");

    /**
     * Key for {@code minecraft:dandelion}.
     */
    public static final TypedKey<BlockType> DANDELION = create("dandelion");

    /**
     * Key for {@code minecraft:dark_oak_button}.
     */
    public static final TypedKey<BlockType> DARK_OAK_BUTTON = create("dark_oak_button");

    /**
     * Key for {@code minecraft:dark_oak_door}.
     */
    public static final TypedKey<BlockType> DARK_OAK_DOOR = create("dark_oak_door");

    /**
     * Key for {@code minecraft:dark_oak_fence}.
     */
    public static final TypedKey<BlockType> DARK_OAK_FENCE = create("dark_oak_fence");

    /**
     * Key for {@code minecraft:dark_oak_fence_gate}.
     */
    public static final TypedKey<BlockType> DARK_OAK_FENCE_GATE = create("dark_oak_fence_gate");

    /**
     * Key for {@code minecraft:dark_oak_hanging_sign}.
     */
    public static final TypedKey<BlockType> DARK_OAK_HANGING_SIGN = create("dark_oak_hanging_sign");

    /**
     * Key for {@code minecraft:dark_oak_leaves}.
     */
    public static final TypedKey<BlockType> DARK_OAK_LEAVES = create("dark_oak_leaves");

    /**
     * Key for {@code minecraft:dark_oak_log}.
     */
    public static final TypedKey<BlockType> DARK_OAK_LOG = create("dark_oak_log");

    /**
     * Key for {@code minecraft:dark_oak_planks}.
     */
    public static final TypedKey<BlockType> DARK_OAK_PLANKS = create("dark_oak_planks");

    /**
     * Key for {@code minecraft:dark_oak_pressure_plate}.
     */
    public static final TypedKey<BlockType> DARK_OAK_PRESSURE_PLATE = create("dark_oak_pressure_plate");

    /**
     * Key for {@code minecraft:dark_oak_sapling}.
     */
    public static final TypedKey<BlockType> DARK_OAK_SAPLING = create("dark_oak_sapling");

    /**
     * Key for {@code minecraft:dark_oak_shelf}.
     */
    public static final TypedKey<BlockType> DARK_OAK_SHELF = create("dark_oak_shelf");

    /**
     * Key for {@code minecraft:dark_oak_sign}.
     */
    public static final TypedKey<BlockType> DARK_OAK_SIGN = create("dark_oak_sign");

    /**
     * Key for {@code minecraft:dark_oak_slab}.
     */
    public static final TypedKey<BlockType> DARK_OAK_SLAB = create("dark_oak_slab");

    /**
     * Key for {@code minecraft:dark_oak_stairs}.
     */
    public static final TypedKey<BlockType> DARK_OAK_STAIRS = create("dark_oak_stairs");

    /**
     * Key for {@code minecraft:dark_oak_trapdoor}.
     */
    public static final TypedKey<BlockType> DARK_OAK_TRAPDOOR = create("dark_oak_trapdoor");

    /**
     * Key for {@code minecraft:dark_oak_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> DARK_OAK_WALL_HANGING_SIGN = create("dark_oak_wall_hanging_sign");

    /**
     * Key for {@code minecraft:dark_oak_wall_sign}.
     */
    public static final TypedKey<BlockType> DARK_OAK_WALL_SIGN = create("dark_oak_wall_sign");

    /**
     * Key for {@code minecraft:dark_oak_wood}.
     */
    public static final TypedKey<BlockType> DARK_OAK_WOOD = create("dark_oak_wood");

    /**
     * Key for {@code minecraft:dark_prismarine}.
     */
    public static final TypedKey<BlockType> DARK_PRISMARINE = create("dark_prismarine");

    /**
     * Key for {@code minecraft:dark_prismarine_slab}.
     */
    public static final TypedKey<BlockType> DARK_PRISMARINE_SLAB = create("dark_prismarine_slab");

    /**
     * Key for {@code minecraft:dark_prismarine_stairs}.
     */
    public static final TypedKey<BlockType> DARK_PRISMARINE_STAIRS = create("dark_prismarine_stairs");

    /**
     * Key for {@code minecraft:daylight_detector}.
     */
    public static final TypedKey<BlockType> DAYLIGHT_DETECTOR = create("daylight_detector");

    /**
     * Key for {@code minecraft:dead_brain_coral}.
     */
    public static final TypedKey<BlockType> DEAD_BRAIN_CORAL = create("dead_brain_coral");

    /**
     * Key for {@code minecraft:dead_brain_coral_block}.
     */
    public static final TypedKey<BlockType> DEAD_BRAIN_CORAL_BLOCK = create("dead_brain_coral_block");

    /**
     * Key for {@code minecraft:dead_brain_coral_fan}.
     */
    public static final TypedKey<BlockType> DEAD_BRAIN_CORAL_FAN = create("dead_brain_coral_fan");

    /**
     * Key for {@code minecraft:dead_brain_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> DEAD_BRAIN_CORAL_WALL_FAN = create("dead_brain_coral_wall_fan");

    /**
     * Key for {@code minecraft:dead_bubble_coral}.
     */
    public static final TypedKey<BlockType> DEAD_BUBBLE_CORAL = create("dead_bubble_coral");

    /**
     * Key for {@code minecraft:dead_bubble_coral_block}.
     */
    public static final TypedKey<BlockType> DEAD_BUBBLE_CORAL_BLOCK = create("dead_bubble_coral_block");

    /**
     * Key for {@code minecraft:dead_bubble_coral_fan}.
     */
    public static final TypedKey<BlockType> DEAD_BUBBLE_CORAL_FAN = create("dead_bubble_coral_fan");

    /**
     * Key for {@code minecraft:dead_bubble_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> DEAD_BUBBLE_CORAL_WALL_FAN = create("dead_bubble_coral_wall_fan");

    /**
     * Key for {@code minecraft:dead_bush}.
     */
    public static final TypedKey<BlockType> DEAD_BUSH = create("dead_bush");

    /**
     * Key for {@code minecraft:dead_fire_coral}.
     */
    public static final TypedKey<BlockType> DEAD_FIRE_CORAL = create("dead_fire_coral");

    /**
     * Key for {@code minecraft:dead_fire_coral_block}.
     */
    public static final TypedKey<BlockType> DEAD_FIRE_CORAL_BLOCK = create("dead_fire_coral_block");

    /**
     * Key for {@code minecraft:dead_fire_coral_fan}.
     */
    public static final TypedKey<BlockType> DEAD_FIRE_CORAL_FAN = create("dead_fire_coral_fan");

    /**
     * Key for {@code minecraft:dead_fire_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> DEAD_FIRE_CORAL_WALL_FAN = create("dead_fire_coral_wall_fan");

    /**
     * Key for {@code minecraft:dead_horn_coral}.
     */
    public static final TypedKey<BlockType> DEAD_HORN_CORAL = create("dead_horn_coral");

    /**
     * Key for {@code minecraft:dead_horn_coral_block}.
     */
    public static final TypedKey<BlockType> DEAD_HORN_CORAL_BLOCK = create("dead_horn_coral_block");

    /**
     * Key for {@code minecraft:dead_horn_coral_fan}.
     */
    public static final TypedKey<BlockType> DEAD_HORN_CORAL_FAN = create("dead_horn_coral_fan");

    /**
     * Key for {@code minecraft:dead_horn_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> DEAD_HORN_CORAL_WALL_FAN = create("dead_horn_coral_wall_fan");

    /**
     * Key for {@code minecraft:dead_tube_coral}.
     */
    public static final TypedKey<BlockType> DEAD_TUBE_CORAL = create("dead_tube_coral");

    /**
     * Key for {@code minecraft:dead_tube_coral_block}.
     */
    public static final TypedKey<BlockType> DEAD_TUBE_CORAL_BLOCK = create("dead_tube_coral_block");

    /**
     * Key for {@code minecraft:dead_tube_coral_fan}.
     */
    public static final TypedKey<BlockType> DEAD_TUBE_CORAL_FAN = create("dead_tube_coral_fan");

    /**
     * Key for {@code minecraft:dead_tube_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> DEAD_TUBE_CORAL_WALL_FAN = create("dead_tube_coral_wall_fan");

    /**
     * Key for {@code minecraft:decorated_pot}.
     */
    public static final TypedKey<BlockType> DECORATED_POT = create("decorated_pot");

    /**
     * Key for {@code minecraft:deepslate}.
     */
    public static final TypedKey<BlockType> DEEPSLATE = create("deepslate");

    /**
     * Key for {@code minecraft:deepslate_brick_slab}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_BRICK_SLAB = create("deepslate_brick_slab");

    /**
     * Key for {@code minecraft:deepslate_brick_stairs}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_BRICK_STAIRS = create("deepslate_brick_stairs");

    /**
     * Key for {@code minecraft:deepslate_brick_wall}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_BRICK_WALL = create("deepslate_brick_wall");

    /**
     * Key for {@code minecraft:deepslate_bricks}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_BRICKS = create("deepslate_bricks");

    /**
     * Key for {@code minecraft:deepslate_coal_ore}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_COAL_ORE = create("deepslate_coal_ore");

    /**
     * Key for {@code minecraft:deepslate_copper_ore}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_COPPER_ORE = create("deepslate_copper_ore");

    /**
     * Key for {@code minecraft:deepslate_diamond_ore}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_DIAMOND_ORE = create("deepslate_diamond_ore");

    /**
     * Key for {@code minecraft:deepslate_emerald_ore}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_EMERALD_ORE = create("deepslate_emerald_ore");

    /**
     * Key for {@code minecraft:deepslate_gold_ore}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_GOLD_ORE = create("deepslate_gold_ore");

    /**
     * Key for {@code minecraft:deepslate_iron_ore}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_IRON_ORE = create("deepslate_iron_ore");

    /**
     * Key for {@code minecraft:deepslate_lapis_ore}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_LAPIS_ORE = create("deepslate_lapis_ore");

    /**
     * Key for {@code minecraft:deepslate_redstone_ore}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_REDSTONE_ORE = create("deepslate_redstone_ore");

    /**
     * Key for {@code minecraft:deepslate_tile_slab}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_TILE_SLAB = create("deepslate_tile_slab");

    /**
     * Key for {@code minecraft:deepslate_tile_stairs}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_TILE_STAIRS = create("deepslate_tile_stairs");

    /**
     * Key for {@code minecraft:deepslate_tile_wall}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_TILE_WALL = create("deepslate_tile_wall");

    /**
     * Key for {@code minecraft:deepslate_tiles}.
     */
    public static final TypedKey<BlockType> DEEPSLATE_TILES = create("deepslate_tiles");

    /**
     * Key for {@code minecraft:detector_rail}.
     */
    public static final TypedKey<BlockType> DETECTOR_RAIL = create("detector_rail");

    /**
     * Key for {@code minecraft:diamond_block}.
     */
    public static final TypedKey<BlockType> DIAMOND_BLOCK = create("diamond_block");

    /**
     * Key for {@code minecraft:diamond_ore}.
     */
    public static final TypedKey<BlockType> DIAMOND_ORE = create("diamond_ore");

    /**
     * Key for {@code minecraft:diorite}.
     */
    public static final TypedKey<BlockType> DIORITE = create("diorite");

    /**
     * Key for {@code minecraft:diorite_slab}.
     */
    public static final TypedKey<BlockType> DIORITE_SLAB = create("diorite_slab");

    /**
     * Key for {@code minecraft:diorite_stairs}.
     */
    public static final TypedKey<BlockType> DIORITE_STAIRS = create("diorite_stairs");

    /**
     * Key for {@code minecraft:diorite_wall}.
     */
    public static final TypedKey<BlockType> DIORITE_WALL = create("diorite_wall");

    /**
     * Key for {@code minecraft:dirt}.
     */
    public static final TypedKey<BlockType> DIRT = create("dirt");

    /**
     * Key for {@code minecraft:dirt_path}.
     */
    public static final TypedKey<BlockType> DIRT_PATH = create("dirt_path");

    /**
     * Key for {@code minecraft:dispenser}.
     */
    public static final TypedKey<BlockType> DISPENSER = create("dispenser");

    /**
     * Key for {@code minecraft:dragon_egg}.
     */
    public static final TypedKey<BlockType> DRAGON_EGG = create("dragon_egg");

    /**
     * Key for {@code minecraft:dragon_head}.
     */
    public static final TypedKey<BlockType> DRAGON_HEAD = create("dragon_head");

    /**
     * Key for {@code minecraft:dragon_wall_head}.
     */
    public static final TypedKey<BlockType> DRAGON_WALL_HEAD = create("dragon_wall_head");

    /**
     * Key for {@code minecraft:dried_ghast}.
     */
    public static final TypedKey<BlockType> DRIED_GHAST = create("dried_ghast");

    /**
     * Key for {@code minecraft:dried_kelp_block}.
     */
    public static final TypedKey<BlockType> DRIED_KELP_BLOCK = create("dried_kelp_block");

    /**
     * Key for {@code minecraft:dripstone_block}.
     */
    public static final TypedKey<BlockType> DRIPSTONE_BLOCK = create("dripstone_block");

    /**
     * Key for {@code minecraft:dropper}.
     */
    public static final TypedKey<BlockType> DROPPER = create("dropper");

    /**
     * Key for {@code minecraft:emerald_block}.
     */
    public static final TypedKey<BlockType> EMERALD_BLOCK = create("emerald_block");

    /**
     * Key for {@code minecraft:emerald_ore}.
     */
    public static final TypedKey<BlockType> EMERALD_ORE = create("emerald_ore");

    /**
     * Key for {@code minecraft:enchanting_table}.
     */
    public static final TypedKey<BlockType> ENCHANTING_TABLE = create("enchanting_table");

    /**
     * Key for {@code minecraft:end_gateway}.
     */
    public static final TypedKey<BlockType> END_GATEWAY = create("end_gateway");

    /**
     * Key for {@code minecraft:end_portal}.
     */
    public static final TypedKey<BlockType> END_PORTAL = create("end_portal");

    /**
     * Key for {@code minecraft:end_portal_frame}.
     */
    public static final TypedKey<BlockType> END_PORTAL_FRAME = create("end_portal_frame");

    /**
     * Key for {@code minecraft:end_rod}.
     */
    public static final TypedKey<BlockType> END_ROD = create("end_rod");

    /**
     * Key for {@code minecraft:end_stone}.
     */
    public static final TypedKey<BlockType> END_STONE = create("end_stone");

    /**
     * Key for {@code minecraft:end_stone_brick_slab}.
     */
    public static final TypedKey<BlockType> END_STONE_BRICK_SLAB = create("end_stone_brick_slab");

    /**
     * Key for {@code minecraft:end_stone_brick_stairs}.
     */
    public static final TypedKey<BlockType> END_STONE_BRICK_STAIRS = create("end_stone_brick_stairs");

    /**
     * Key for {@code minecraft:end_stone_brick_wall}.
     */
    public static final TypedKey<BlockType> END_STONE_BRICK_WALL = create("end_stone_brick_wall");

    /**
     * Key for {@code minecraft:end_stone_bricks}.
     */
    public static final TypedKey<BlockType> END_STONE_BRICKS = create("end_stone_bricks");

    /**
     * Key for {@code minecraft:ender_chest}.
     */
    public static final TypedKey<BlockType> ENDER_CHEST = create("ender_chest");

    /**
     * Key for {@code minecraft:exposed_chiseled_copper}.
     */
    public static final TypedKey<BlockType> EXPOSED_CHISELED_COPPER = create("exposed_chiseled_copper");

    /**
     * Key for {@code minecraft:exposed_copper}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER = create("exposed_copper");

    /**
     * Key for {@code minecraft:exposed_copper_bars}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_BARS = create("exposed_copper_bars");

    /**
     * Key for {@code minecraft:exposed_copper_bulb}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_BULB = create("exposed_copper_bulb");

    /**
     * Key for {@code minecraft:exposed_copper_chain}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_CHAIN = create("exposed_copper_chain");

    /**
     * Key for {@code minecraft:exposed_copper_chest}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_CHEST = create("exposed_copper_chest");

    /**
     * Key for {@code minecraft:exposed_copper_door}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_DOOR = create("exposed_copper_door");

    /**
     * Key for {@code minecraft:exposed_copper_golem_statue}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_GOLEM_STATUE = create("exposed_copper_golem_statue");

    /**
     * Key for {@code minecraft:exposed_copper_grate}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_GRATE = create("exposed_copper_grate");

    /**
     * Key for {@code minecraft:exposed_copper_lantern}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_LANTERN = create("exposed_copper_lantern");

    /**
     * Key for {@code minecraft:exposed_copper_trapdoor}.
     */
    public static final TypedKey<BlockType> EXPOSED_COPPER_TRAPDOOR = create("exposed_copper_trapdoor");

    /**
     * Key for {@code minecraft:exposed_cut_copper}.
     */
    public static final TypedKey<BlockType> EXPOSED_CUT_COPPER = create("exposed_cut_copper");

    /**
     * Key for {@code minecraft:exposed_cut_copper_slab}.
     */
    public static final TypedKey<BlockType> EXPOSED_CUT_COPPER_SLAB = create("exposed_cut_copper_slab");

    /**
     * Key for {@code minecraft:exposed_cut_copper_stairs}.
     */
    public static final TypedKey<BlockType> EXPOSED_CUT_COPPER_STAIRS = create("exposed_cut_copper_stairs");

    /**
     * Key for {@code minecraft:exposed_lightning_rod}.
     */
    public static final TypedKey<BlockType> EXPOSED_LIGHTNING_ROD = create("exposed_lightning_rod");

    /**
     * Key for {@code minecraft:farmland}.
     */
    public static final TypedKey<BlockType> FARMLAND = create("farmland");

    /**
     * Key for {@code minecraft:fern}.
     */
    public static final TypedKey<BlockType> FERN = create("fern");

    /**
     * Key for {@code minecraft:fire}.
     */
    public static final TypedKey<BlockType> FIRE = create("fire");

    /**
     * Key for {@code minecraft:fire_coral}.
     */
    public static final TypedKey<BlockType> FIRE_CORAL = create("fire_coral");

    /**
     * Key for {@code minecraft:fire_coral_block}.
     */
    public static final TypedKey<BlockType> FIRE_CORAL_BLOCK = create("fire_coral_block");

    /**
     * Key for {@code minecraft:fire_coral_fan}.
     */
    public static final TypedKey<BlockType> FIRE_CORAL_FAN = create("fire_coral_fan");

    /**
     * Key for {@code minecraft:fire_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> FIRE_CORAL_WALL_FAN = create("fire_coral_wall_fan");

    /**
     * Key for {@code minecraft:firefly_bush}.
     */
    public static final TypedKey<BlockType> FIREFLY_BUSH = create("firefly_bush");

    /**
     * Key for {@code minecraft:fletching_table}.
     */
    public static final TypedKey<BlockType> FLETCHING_TABLE = create("fletching_table");

    /**
     * Key for {@code minecraft:flower_pot}.
     */
    public static final TypedKey<BlockType> FLOWER_POT = create("flower_pot");

    /**
     * Key for {@code minecraft:flowering_azalea}.
     */
    public static final TypedKey<BlockType> FLOWERING_AZALEA = create("flowering_azalea");

    /**
     * Key for {@code minecraft:flowering_azalea_leaves}.
     */
    public static final TypedKey<BlockType> FLOWERING_AZALEA_LEAVES = create("flowering_azalea_leaves");

    /**
     * Key for {@code minecraft:frogspawn}.
     */
    public static final TypedKey<BlockType> FROGSPAWN = create("frogspawn");

    /**
     * Key for {@code minecraft:frosted_ice}.
     */
    public static final TypedKey<BlockType> FROSTED_ICE = create("frosted_ice");

    /**
     * Key for {@code minecraft:furnace}.
     */
    public static final TypedKey<BlockType> FURNACE = create("furnace");

    /**
     * Key for {@code minecraft:gilded_blackstone}.
     */
    public static final TypedKey<BlockType> GILDED_BLACKSTONE = create("gilded_blackstone");

    /**
     * Key for {@code minecraft:glass}.
     */
    public static final TypedKey<BlockType> GLASS = create("glass");

    /**
     * Key for {@code minecraft:glass_pane}.
     */
    public static final TypedKey<BlockType> GLASS_PANE = create("glass_pane");

    /**
     * Key for {@code minecraft:glow_lichen}.
     */
    public static final TypedKey<BlockType> GLOW_LICHEN = create("glow_lichen");

    /**
     * Key for {@code minecraft:glowstone}.
     */
    public static final TypedKey<BlockType> GLOWSTONE = create("glowstone");

    /**
     * Key for {@code minecraft:gold_block}.
     */
    public static final TypedKey<BlockType> GOLD_BLOCK = create("gold_block");

    /**
     * Key for {@code minecraft:gold_ore}.
     */
    public static final TypedKey<BlockType> GOLD_ORE = create("gold_ore");

    /**
     * Key for {@code minecraft:golden_dandelion}.
     */
    public static final TypedKey<BlockType> GOLDEN_DANDELION = create("golden_dandelion");

    /**
     * Key for {@code minecraft:granite}.
     */
    public static final TypedKey<BlockType> GRANITE = create("granite");

    /**
     * Key for {@code minecraft:granite_slab}.
     */
    public static final TypedKey<BlockType> GRANITE_SLAB = create("granite_slab");

    /**
     * Key for {@code minecraft:granite_stairs}.
     */
    public static final TypedKey<BlockType> GRANITE_STAIRS = create("granite_stairs");

    /**
     * Key for {@code minecraft:granite_wall}.
     */
    public static final TypedKey<BlockType> GRANITE_WALL = create("granite_wall");

    /**
     * Key for {@code minecraft:grass_block}.
     */
    public static final TypedKey<BlockType> GRASS_BLOCK = create("grass_block");

    /**
     * Key for {@code minecraft:gravel}.
     */
    public static final TypedKey<BlockType> GRAVEL = create("gravel");

    /**
     * Key for {@code minecraft:gray_banner}.
     */
    public static final TypedKey<BlockType> GRAY_BANNER = create("gray_banner");

    /**
     * Key for {@code minecraft:gray_bed}.
     */
    public static final TypedKey<BlockType> GRAY_BED = create("gray_bed");

    /**
     * Key for {@code minecraft:gray_candle}.
     */
    public static final TypedKey<BlockType> GRAY_CANDLE = create("gray_candle");

    /**
     * Key for {@code minecraft:gray_candle_cake}.
     */
    public static final TypedKey<BlockType> GRAY_CANDLE_CAKE = create("gray_candle_cake");

    /**
     * Key for {@code minecraft:gray_carpet}.
     */
    public static final TypedKey<BlockType> GRAY_CARPET = create("gray_carpet");

    /**
     * Key for {@code minecraft:gray_concrete}.
     */
    public static final TypedKey<BlockType> GRAY_CONCRETE = create("gray_concrete");

    /**
     * Key for {@code minecraft:gray_concrete_powder}.
     */
    public static final TypedKey<BlockType> GRAY_CONCRETE_POWDER = create("gray_concrete_powder");

    /**
     * Key for {@code minecraft:gray_concrete_slab}.
     */
    public static final TypedKey<BlockType> GRAY_CONCRETE_SLAB = create("gray_concrete_slab");

    /**
     * Key for {@code minecraft:gray_concrete_stairs}.
     */
    public static final TypedKey<BlockType> GRAY_CONCRETE_STAIRS = create("gray_concrete_stairs");

    /**
     * Key for {@code minecraft:gray_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> GRAY_GLAZED_TERRACOTTA = create("gray_glazed_terracotta");

    /**
     * Key for {@code minecraft:gray_shulker_box}.
     */
    public static final TypedKey<BlockType> GRAY_SHULKER_BOX = create("gray_shulker_box");

    /**
     * Key for {@code minecraft:gray_stained_glass}.
     */
    public static final TypedKey<BlockType> GRAY_STAINED_GLASS = create("gray_stained_glass");

    /**
     * Key for {@code minecraft:gray_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> GRAY_STAINED_GLASS_PANE = create("gray_stained_glass_pane");

    /**
     * Key for {@code minecraft:gray_terracotta}.
     */
    public static final TypedKey<BlockType> GRAY_TERRACOTTA = create("gray_terracotta");

    /**
     * Key for {@code minecraft:gray_wall_banner}.
     */
    public static final TypedKey<BlockType> GRAY_WALL_BANNER = create("gray_wall_banner");

    /**
     * Key for {@code minecraft:gray_wool}.
     */
    public static final TypedKey<BlockType> GRAY_WOOL = create("gray_wool");

    /**
     * Key for {@code minecraft:gray_wool_slab}.
     */
    public static final TypedKey<BlockType> GRAY_WOOL_SLAB = create("gray_wool_slab");

    /**
     * Key for {@code minecraft:gray_wool_stairs}.
     */
    public static final TypedKey<BlockType> GRAY_WOOL_STAIRS = create("gray_wool_stairs");

    /**
     * Key for {@code minecraft:green_banner}.
     */
    public static final TypedKey<BlockType> GREEN_BANNER = create("green_banner");

    /**
     * Key for {@code minecraft:green_bed}.
     */
    public static final TypedKey<BlockType> GREEN_BED = create("green_bed");

    /**
     * Key for {@code minecraft:green_candle}.
     */
    public static final TypedKey<BlockType> GREEN_CANDLE = create("green_candle");

    /**
     * Key for {@code minecraft:green_candle_cake}.
     */
    public static final TypedKey<BlockType> GREEN_CANDLE_CAKE = create("green_candle_cake");

    /**
     * Key for {@code minecraft:green_carpet}.
     */
    public static final TypedKey<BlockType> GREEN_CARPET = create("green_carpet");

    /**
     * Key for {@code minecraft:green_concrete}.
     */
    public static final TypedKey<BlockType> GREEN_CONCRETE = create("green_concrete");

    /**
     * Key for {@code minecraft:green_concrete_powder}.
     */
    public static final TypedKey<BlockType> GREEN_CONCRETE_POWDER = create("green_concrete_powder");

    /**
     * Key for {@code minecraft:green_concrete_slab}.
     */
    public static final TypedKey<BlockType> GREEN_CONCRETE_SLAB = create("green_concrete_slab");

    /**
     * Key for {@code minecraft:green_concrete_stairs}.
     */
    public static final TypedKey<BlockType> GREEN_CONCRETE_STAIRS = create("green_concrete_stairs");

    /**
     * Key for {@code minecraft:green_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> GREEN_GLAZED_TERRACOTTA = create("green_glazed_terracotta");

    /**
     * Key for {@code minecraft:green_shulker_box}.
     */
    public static final TypedKey<BlockType> GREEN_SHULKER_BOX = create("green_shulker_box");

    /**
     * Key for {@code minecraft:green_stained_glass}.
     */
    public static final TypedKey<BlockType> GREEN_STAINED_GLASS = create("green_stained_glass");

    /**
     * Key for {@code minecraft:green_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> GREEN_STAINED_GLASS_PANE = create("green_stained_glass_pane");

    /**
     * Key for {@code minecraft:green_terracotta}.
     */
    public static final TypedKey<BlockType> GREEN_TERRACOTTA = create("green_terracotta");

    /**
     * Key for {@code minecraft:green_wall_banner}.
     */
    public static final TypedKey<BlockType> GREEN_WALL_BANNER = create("green_wall_banner");

    /**
     * Key for {@code minecraft:green_wool}.
     */
    public static final TypedKey<BlockType> GREEN_WOOL = create("green_wool");

    /**
     * Key for {@code minecraft:green_wool_slab}.
     */
    public static final TypedKey<BlockType> GREEN_WOOL_SLAB = create("green_wool_slab");

    /**
     * Key for {@code minecraft:green_wool_stairs}.
     */
    public static final TypedKey<BlockType> GREEN_WOOL_STAIRS = create("green_wool_stairs");

    /**
     * Key for {@code minecraft:grindstone}.
     */
    public static final TypedKey<BlockType> GRINDSTONE = create("grindstone");

    /**
     * Key for {@code minecraft:hanging_roots}.
     */
    public static final TypedKey<BlockType> HANGING_ROOTS = create("hanging_roots");

    /**
     * Key for {@code minecraft:hay_block}.
     */
    public static final TypedKey<BlockType> HAY_BLOCK = create("hay_block");

    /**
     * Key for {@code minecraft:heavy_core}.
     */
    public static final TypedKey<BlockType> HEAVY_CORE = create("heavy_core");

    /**
     * Key for {@code minecraft:heavy_weighted_pressure_plate}.
     */
    public static final TypedKey<BlockType> HEAVY_WEIGHTED_PRESSURE_PLATE = create("heavy_weighted_pressure_plate");

    /**
     * Key for {@code minecraft:honey_block}.
     */
    public static final TypedKey<BlockType> HONEY_BLOCK = create("honey_block");

    /**
     * Key for {@code minecraft:honeycomb_block}.
     */
    public static final TypedKey<BlockType> HONEYCOMB_BLOCK = create("honeycomb_block");

    /**
     * Key for {@code minecraft:hopper}.
     */
    public static final TypedKey<BlockType> HOPPER = create("hopper");

    /**
     * Key for {@code minecraft:horn_coral}.
     */
    public static final TypedKey<BlockType> HORN_CORAL = create("horn_coral");

    /**
     * Key for {@code minecraft:horn_coral_block}.
     */
    public static final TypedKey<BlockType> HORN_CORAL_BLOCK = create("horn_coral_block");

    /**
     * Key for {@code minecraft:horn_coral_fan}.
     */
    public static final TypedKey<BlockType> HORN_CORAL_FAN = create("horn_coral_fan");

    /**
     * Key for {@code minecraft:horn_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> HORN_CORAL_WALL_FAN = create("horn_coral_wall_fan");

    /**
     * Key for {@code minecraft:ice}.
     */
    public static final TypedKey<BlockType> ICE = create("ice");

    /**
     * Key for {@code minecraft:infested_chiseled_stone_bricks}.
     */
    public static final TypedKey<BlockType> INFESTED_CHISELED_STONE_BRICKS = create("infested_chiseled_stone_bricks");

    /**
     * Key for {@code minecraft:infested_cobblestone}.
     */
    public static final TypedKey<BlockType> INFESTED_COBBLESTONE = create("infested_cobblestone");

    /**
     * Key for {@code minecraft:infested_cracked_stone_bricks}.
     */
    public static final TypedKey<BlockType> INFESTED_CRACKED_STONE_BRICKS = create("infested_cracked_stone_bricks");

    /**
     * Key for {@code minecraft:infested_deepslate}.
     */
    public static final TypedKey<BlockType> INFESTED_DEEPSLATE = create("infested_deepslate");

    /**
     * Key for {@code minecraft:infested_mossy_stone_bricks}.
     */
    public static final TypedKey<BlockType> INFESTED_MOSSY_STONE_BRICKS = create("infested_mossy_stone_bricks");

    /**
     * Key for {@code minecraft:infested_stone}.
     */
    public static final TypedKey<BlockType> INFESTED_STONE = create("infested_stone");

    /**
     * Key for {@code minecraft:infested_stone_bricks}.
     */
    public static final TypedKey<BlockType> INFESTED_STONE_BRICKS = create("infested_stone_bricks");

    /**
     * Key for {@code minecraft:iron_bars}.
     */
    public static final TypedKey<BlockType> IRON_BARS = create("iron_bars");

    /**
     * Key for {@code minecraft:iron_block}.
     */
    public static final TypedKey<BlockType> IRON_BLOCK = create("iron_block");

    /**
     * Key for {@code minecraft:iron_chain}.
     */
    public static final TypedKey<BlockType> IRON_CHAIN = create("iron_chain");

    /**
     * Key for {@code minecraft:iron_door}.
     */
    public static final TypedKey<BlockType> IRON_DOOR = create("iron_door");

    /**
     * Key for {@code minecraft:iron_ore}.
     */
    public static final TypedKey<BlockType> IRON_ORE = create("iron_ore");

    /**
     * Key for {@code minecraft:iron_trapdoor}.
     */
    public static final TypedKey<BlockType> IRON_TRAPDOOR = create("iron_trapdoor");

    /**
     * Key for {@code minecraft:jack_o_lantern}.
     */
    public static final TypedKey<BlockType> JACK_O_LANTERN = create("jack_o_lantern");

    /**
     * Key for {@code minecraft:jigsaw}.
     */
    public static final TypedKey<BlockType> JIGSAW = create("jigsaw");

    /**
     * Key for {@code minecraft:jukebox}.
     */
    public static final TypedKey<BlockType> JUKEBOX = create("jukebox");

    /**
     * Key for {@code minecraft:jungle_button}.
     */
    public static final TypedKey<BlockType> JUNGLE_BUTTON = create("jungle_button");

    /**
     * Key for {@code minecraft:jungle_door}.
     */
    public static final TypedKey<BlockType> JUNGLE_DOOR = create("jungle_door");

    /**
     * Key for {@code minecraft:jungle_fence}.
     */
    public static final TypedKey<BlockType> JUNGLE_FENCE = create("jungle_fence");

    /**
     * Key for {@code minecraft:jungle_fence_gate}.
     */
    public static final TypedKey<BlockType> JUNGLE_FENCE_GATE = create("jungle_fence_gate");

    /**
     * Key for {@code minecraft:jungle_hanging_sign}.
     */
    public static final TypedKey<BlockType> JUNGLE_HANGING_SIGN = create("jungle_hanging_sign");

    /**
     * Key for {@code minecraft:jungle_leaves}.
     */
    public static final TypedKey<BlockType> JUNGLE_LEAVES = create("jungle_leaves");

    /**
     * Key for {@code minecraft:jungle_log}.
     */
    public static final TypedKey<BlockType> JUNGLE_LOG = create("jungle_log");

    /**
     * Key for {@code minecraft:jungle_planks}.
     */
    public static final TypedKey<BlockType> JUNGLE_PLANKS = create("jungle_planks");

    /**
     * Key for {@code minecraft:jungle_pressure_plate}.
     */
    public static final TypedKey<BlockType> JUNGLE_PRESSURE_PLATE = create("jungle_pressure_plate");

    /**
     * Key for {@code minecraft:jungle_sapling}.
     */
    public static final TypedKey<BlockType> JUNGLE_SAPLING = create("jungle_sapling");

    /**
     * Key for {@code minecraft:jungle_shelf}.
     */
    public static final TypedKey<BlockType> JUNGLE_SHELF = create("jungle_shelf");

    /**
     * Key for {@code minecraft:jungle_sign}.
     */
    public static final TypedKey<BlockType> JUNGLE_SIGN = create("jungle_sign");

    /**
     * Key for {@code minecraft:jungle_slab}.
     */
    public static final TypedKey<BlockType> JUNGLE_SLAB = create("jungle_slab");

    /**
     * Key for {@code minecraft:jungle_stairs}.
     */
    public static final TypedKey<BlockType> JUNGLE_STAIRS = create("jungle_stairs");

    /**
     * Key for {@code minecraft:jungle_trapdoor}.
     */
    public static final TypedKey<BlockType> JUNGLE_TRAPDOOR = create("jungle_trapdoor");

    /**
     * Key for {@code minecraft:jungle_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> JUNGLE_WALL_HANGING_SIGN = create("jungle_wall_hanging_sign");

    /**
     * Key for {@code minecraft:jungle_wall_sign}.
     */
    public static final TypedKey<BlockType> JUNGLE_WALL_SIGN = create("jungle_wall_sign");

    /**
     * Key for {@code minecraft:jungle_wood}.
     */
    public static final TypedKey<BlockType> JUNGLE_WOOD = create("jungle_wood");

    /**
     * Key for {@code minecraft:kelp}.
     */
    public static final TypedKey<BlockType> KELP = create("kelp");

    /**
     * Key for {@code minecraft:kelp_plant}.
     */
    public static final TypedKey<BlockType> KELP_PLANT = create("kelp_plant");

    /**
     * Key for {@code minecraft:ladder}.
     */
    public static final TypedKey<BlockType> LADDER = create("ladder");

    /**
     * Key for {@code minecraft:lantern}.
     */
    public static final TypedKey<BlockType> LANTERN = create("lantern");

    /**
     * Key for {@code minecraft:lapis_block}.
     */
    public static final TypedKey<BlockType> LAPIS_BLOCK = create("lapis_block");

    /**
     * Key for {@code minecraft:lapis_ore}.
     */
    public static final TypedKey<BlockType> LAPIS_ORE = create("lapis_ore");

    /**
     * Key for {@code minecraft:large_amethyst_bud}.
     */
    public static final TypedKey<BlockType> LARGE_AMETHYST_BUD = create("large_amethyst_bud");

    /**
     * Key for {@code minecraft:large_fern}.
     */
    public static final TypedKey<BlockType> LARGE_FERN = create("large_fern");

    /**
     * Key for {@code minecraft:lava}.
     */
    public static final TypedKey<BlockType> LAVA = create("lava");

    /**
     * Key for {@code minecraft:lava_cauldron}.
     */
    public static final TypedKey<BlockType> LAVA_CAULDRON = create("lava_cauldron");

    /**
     * Key for {@code minecraft:leaf_litter}.
     */
    public static final TypedKey<BlockType> LEAF_LITTER = create("leaf_litter");

    /**
     * Key for {@code minecraft:lectern}.
     */
    public static final TypedKey<BlockType> LECTERN = create("lectern");

    /**
     * Key for {@code minecraft:lever}.
     */
    public static final TypedKey<BlockType> LEVER = create("lever");

    /**
     * Key for {@code minecraft:light}.
     */
    public static final TypedKey<BlockType> LIGHT = create("light");

    /**
     * Key for {@code minecraft:light_blue_banner}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_BANNER = create("light_blue_banner");

    /**
     * Key for {@code minecraft:light_blue_bed}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_BED = create("light_blue_bed");

    /**
     * Key for {@code minecraft:light_blue_candle}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_CANDLE = create("light_blue_candle");

    /**
     * Key for {@code minecraft:light_blue_candle_cake}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_CANDLE_CAKE = create("light_blue_candle_cake");

    /**
     * Key for {@code minecraft:light_blue_carpet}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_CARPET = create("light_blue_carpet");

    /**
     * Key for {@code minecraft:light_blue_concrete}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_CONCRETE = create("light_blue_concrete");

    /**
     * Key for {@code minecraft:light_blue_concrete_powder}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_CONCRETE_POWDER = create("light_blue_concrete_powder");

    /**
     * Key for {@code minecraft:light_blue_concrete_slab}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_CONCRETE_SLAB = create("light_blue_concrete_slab");

    /**
     * Key for {@code minecraft:light_blue_concrete_stairs}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_CONCRETE_STAIRS = create("light_blue_concrete_stairs");

    /**
     * Key for {@code minecraft:light_blue_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_GLAZED_TERRACOTTA = create("light_blue_glazed_terracotta");

    /**
     * Key for {@code minecraft:light_blue_shulker_box}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_SHULKER_BOX = create("light_blue_shulker_box");

    /**
     * Key for {@code minecraft:light_blue_stained_glass}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_STAINED_GLASS = create("light_blue_stained_glass");

    /**
     * Key for {@code minecraft:light_blue_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_STAINED_GLASS_PANE = create("light_blue_stained_glass_pane");

    /**
     * Key for {@code minecraft:light_blue_terracotta}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_TERRACOTTA = create("light_blue_terracotta");

    /**
     * Key for {@code minecraft:light_blue_wall_banner}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_WALL_BANNER = create("light_blue_wall_banner");

    /**
     * Key for {@code minecraft:light_blue_wool}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_WOOL = create("light_blue_wool");

    /**
     * Key for {@code minecraft:light_blue_wool_slab}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_WOOL_SLAB = create("light_blue_wool_slab");

    /**
     * Key for {@code minecraft:light_blue_wool_stairs}.
     */
    public static final TypedKey<BlockType> LIGHT_BLUE_WOOL_STAIRS = create("light_blue_wool_stairs");

    /**
     * Key for {@code minecraft:light_gray_banner}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_BANNER = create("light_gray_banner");

    /**
     * Key for {@code minecraft:light_gray_bed}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_BED = create("light_gray_bed");

    /**
     * Key for {@code minecraft:light_gray_candle}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_CANDLE = create("light_gray_candle");

    /**
     * Key for {@code minecraft:light_gray_candle_cake}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_CANDLE_CAKE = create("light_gray_candle_cake");

    /**
     * Key for {@code minecraft:light_gray_carpet}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_CARPET = create("light_gray_carpet");

    /**
     * Key for {@code minecraft:light_gray_concrete}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_CONCRETE = create("light_gray_concrete");

    /**
     * Key for {@code minecraft:light_gray_concrete_powder}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_CONCRETE_POWDER = create("light_gray_concrete_powder");

    /**
     * Key for {@code minecraft:light_gray_concrete_slab}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_CONCRETE_SLAB = create("light_gray_concrete_slab");

    /**
     * Key for {@code minecraft:light_gray_concrete_stairs}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_CONCRETE_STAIRS = create("light_gray_concrete_stairs");

    /**
     * Key for {@code minecraft:light_gray_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_GLAZED_TERRACOTTA = create("light_gray_glazed_terracotta");

    /**
     * Key for {@code minecraft:light_gray_shulker_box}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_SHULKER_BOX = create("light_gray_shulker_box");

    /**
     * Key for {@code minecraft:light_gray_stained_glass}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_STAINED_GLASS = create("light_gray_stained_glass");

    /**
     * Key for {@code minecraft:light_gray_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_STAINED_GLASS_PANE = create("light_gray_stained_glass_pane");

    /**
     * Key for {@code minecraft:light_gray_terracotta}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_TERRACOTTA = create("light_gray_terracotta");

    /**
     * Key for {@code minecraft:light_gray_wall_banner}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_WALL_BANNER = create("light_gray_wall_banner");

    /**
     * Key for {@code minecraft:light_gray_wool}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_WOOL = create("light_gray_wool");

    /**
     * Key for {@code minecraft:light_gray_wool_slab}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_WOOL_SLAB = create("light_gray_wool_slab");

    /**
     * Key for {@code minecraft:light_gray_wool_stairs}.
     */
    public static final TypedKey<BlockType> LIGHT_GRAY_WOOL_STAIRS = create("light_gray_wool_stairs");

    /**
     * Key for {@code minecraft:light_weighted_pressure_plate}.
     */
    public static final TypedKey<BlockType> LIGHT_WEIGHTED_PRESSURE_PLATE = create("light_weighted_pressure_plate");

    /**
     * Key for {@code minecraft:lightning_rod}.
     */
    public static final TypedKey<BlockType> LIGHTNING_ROD = create("lightning_rod");

    /**
     * Key for {@code minecraft:lilac}.
     */
    public static final TypedKey<BlockType> LILAC = create("lilac");

    /**
     * Key for {@code minecraft:lily_of_the_valley}.
     */
    public static final TypedKey<BlockType> LILY_OF_THE_VALLEY = create("lily_of_the_valley");

    /**
     * Key for {@code minecraft:lily_pad}.
     */
    public static final TypedKey<BlockType> LILY_PAD = create("lily_pad");

    /**
     * Key for {@code minecraft:lime_banner}.
     */
    public static final TypedKey<BlockType> LIME_BANNER = create("lime_banner");

    /**
     * Key for {@code minecraft:lime_bed}.
     */
    public static final TypedKey<BlockType> LIME_BED = create("lime_bed");

    /**
     * Key for {@code minecraft:lime_candle}.
     */
    public static final TypedKey<BlockType> LIME_CANDLE = create("lime_candle");

    /**
     * Key for {@code minecraft:lime_candle_cake}.
     */
    public static final TypedKey<BlockType> LIME_CANDLE_CAKE = create("lime_candle_cake");

    /**
     * Key for {@code minecraft:lime_carpet}.
     */
    public static final TypedKey<BlockType> LIME_CARPET = create("lime_carpet");

    /**
     * Key for {@code minecraft:lime_concrete}.
     */
    public static final TypedKey<BlockType> LIME_CONCRETE = create("lime_concrete");

    /**
     * Key for {@code minecraft:lime_concrete_powder}.
     */
    public static final TypedKey<BlockType> LIME_CONCRETE_POWDER = create("lime_concrete_powder");

    /**
     * Key for {@code minecraft:lime_concrete_slab}.
     */
    public static final TypedKey<BlockType> LIME_CONCRETE_SLAB = create("lime_concrete_slab");

    /**
     * Key for {@code minecraft:lime_concrete_stairs}.
     */
    public static final TypedKey<BlockType> LIME_CONCRETE_STAIRS = create("lime_concrete_stairs");

    /**
     * Key for {@code minecraft:lime_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> LIME_GLAZED_TERRACOTTA = create("lime_glazed_terracotta");

    /**
     * Key for {@code minecraft:lime_shulker_box}.
     */
    public static final TypedKey<BlockType> LIME_SHULKER_BOX = create("lime_shulker_box");

    /**
     * Key for {@code minecraft:lime_stained_glass}.
     */
    public static final TypedKey<BlockType> LIME_STAINED_GLASS = create("lime_stained_glass");

    /**
     * Key for {@code minecraft:lime_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> LIME_STAINED_GLASS_PANE = create("lime_stained_glass_pane");

    /**
     * Key for {@code minecraft:lime_terracotta}.
     */
    public static final TypedKey<BlockType> LIME_TERRACOTTA = create("lime_terracotta");

    /**
     * Key for {@code minecraft:lime_wall_banner}.
     */
    public static final TypedKey<BlockType> LIME_WALL_BANNER = create("lime_wall_banner");

    /**
     * Key for {@code minecraft:lime_wool}.
     */
    public static final TypedKey<BlockType> LIME_WOOL = create("lime_wool");

    /**
     * Key for {@code minecraft:lime_wool_slab}.
     */
    public static final TypedKey<BlockType> LIME_WOOL_SLAB = create("lime_wool_slab");

    /**
     * Key for {@code minecraft:lime_wool_stairs}.
     */
    public static final TypedKey<BlockType> LIME_WOOL_STAIRS = create("lime_wool_stairs");

    /**
     * Key for {@code minecraft:lodestone}.
     */
    public static final TypedKey<BlockType> LODESTONE = create("lodestone");

    /**
     * Key for {@code minecraft:loom}.
     */
    public static final TypedKey<BlockType> LOOM = create("loom");

    /**
     * Key for {@code minecraft:magenta_banner}.
     */
    public static final TypedKey<BlockType> MAGENTA_BANNER = create("magenta_banner");

    /**
     * Key for {@code minecraft:magenta_bed}.
     */
    public static final TypedKey<BlockType> MAGENTA_BED = create("magenta_bed");

    /**
     * Key for {@code minecraft:magenta_candle}.
     */
    public static final TypedKey<BlockType> MAGENTA_CANDLE = create("magenta_candle");

    /**
     * Key for {@code minecraft:magenta_candle_cake}.
     */
    public static final TypedKey<BlockType> MAGENTA_CANDLE_CAKE = create("magenta_candle_cake");

    /**
     * Key for {@code minecraft:magenta_carpet}.
     */
    public static final TypedKey<BlockType> MAGENTA_CARPET = create("magenta_carpet");

    /**
     * Key for {@code minecraft:magenta_concrete}.
     */
    public static final TypedKey<BlockType> MAGENTA_CONCRETE = create("magenta_concrete");

    /**
     * Key for {@code minecraft:magenta_concrete_powder}.
     */
    public static final TypedKey<BlockType> MAGENTA_CONCRETE_POWDER = create("magenta_concrete_powder");

    /**
     * Key for {@code minecraft:magenta_concrete_slab}.
     */
    public static final TypedKey<BlockType> MAGENTA_CONCRETE_SLAB = create("magenta_concrete_slab");

    /**
     * Key for {@code minecraft:magenta_concrete_stairs}.
     */
    public static final TypedKey<BlockType> MAGENTA_CONCRETE_STAIRS = create("magenta_concrete_stairs");

    /**
     * Key for {@code minecraft:magenta_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> MAGENTA_GLAZED_TERRACOTTA = create("magenta_glazed_terracotta");

    /**
     * Key for {@code minecraft:magenta_shulker_box}.
     */
    public static final TypedKey<BlockType> MAGENTA_SHULKER_BOX = create("magenta_shulker_box");

    /**
     * Key for {@code minecraft:magenta_stained_glass}.
     */
    public static final TypedKey<BlockType> MAGENTA_STAINED_GLASS = create("magenta_stained_glass");

    /**
     * Key for {@code minecraft:magenta_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> MAGENTA_STAINED_GLASS_PANE = create("magenta_stained_glass_pane");

    /**
     * Key for {@code minecraft:magenta_terracotta}.
     */
    public static final TypedKey<BlockType> MAGENTA_TERRACOTTA = create("magenta_terracotta");

    /**
     * Key for {@code minecraft:magenta_wall_banner}.
     */
    public static final TypedKey<BlockType> MAGENTA_WALL_BANNER = create("magenta_wall_banner");

    /**
     * Key for {@code minecraft:magenta_wool}.
     */
    public static final TypedKey<BlockType> MAGENTA_WOOL = create("magenta_wool");

    /**
     * Key for {@code minecraft:magenta_wool_slab}.
     */
    public static final TypedKey<BlockType> MAGENTA_WOOL_SLAB = create("magenta_wool_slab");

    /**
     * Key for {@code minecraft:magenta_wool_stairs}.
     */
    public static final TypedKey<BlockType> MAGENTA_WOOL_STAIRS = create("magenta_wool_stairs");

    /**
     * Key for {@code minecraft:magma_block}.
     */
    public static final TypedKey<BlockType> MAGMA_BLOCK = create("magma_block");

    /**
     * Key for {@code minecraft:mangrove_button}.
     */
    public static final TypedKey<BlockType> MANGROVE_BUTTON = create("mangrove_button");

    /**
     * Key for {@code minecraft:mangrove_door}.
     */
    public static final TypedKey<BlockType> MANGROVE_DOOR = create("mangrove_door");

    /**
     * Key for {@code minecraft:mangrove_fence}.
     */
    public static final TypedKey<BlockType> MANGROVE_FENCE = create("mangrove_fence");

    /**
     * Key for {@code minecraft:mangrove_fence_gate}.
     */
    public static final TypedKey<BlockType> MANGROVE_FENCE_GATE = create("mangrove_fence_gate");

    /**
     * Key for {@code minecraft:mangrove_hanging_sign}.
     */
    public static final TypedKey<BlockType> MANGROVE_HANGING_SIGN = create("mangrove_hanging_sign");

    /**
     * Key for {@code minecraft:mangrove_leaves}.
     */
    public static final TypedKey<BlockType> MANGROVE_LEAVES = create("mangrove_leaves");

    /**
     * Key for {@code minecraft:mangrove_log}.
     */
    public static final TypedKey<BlockType> MANGROVE_LOG = create("mangrove_log");

    /**
     * Key for {@code minecraft:mangrove_planks}.
     */
    public static final TypedKey<BlockType> MANGROVE_PLANKS = create("mangrove_planks");

    /**
     * Key for {@code minecraft:mangrove_pressure_plate}.
     */
    public static final TypedKey<BlockType> MANGROVE_PRESSURE_PLATE = create("mangrove_pressure_plate");

    /**
     * Key for {@code minecraft:mangrove_propagule}.
     */
    public static final TypedKey<BlockType> MANGROVE_PROPAGULE = create("mangrove_propagule");

    /**
     * Key for {@code minecraft:mangrove_roots}.
     */
    public static final TypedKey<BlockType> MANGROVE_ROOTS = create("mangrove_roots");

    /**
     * Key for {@code minecraft:mangrove_shelf}.
     */
    public static final TypedKey<BlockType> MANGROVE_SHELF = create("mangrove_shelf");

    /**
     * Key for {@code minecraft:mangrove_sign}.
     */
    public static final TypedKey<BlockType> MANGROVE_SIGN = create("mangrove_sign");

    /**
     * Key for {@code minecraft:mangrove_slab}.
     */
    public static final TypedKey<BlockType> MANGROVE_SLAB = create("mangrove_slab");

    /**
     * Key for {@code minecraft:mangrove_stairs}.
     */
    public static final TypedKey<BlockType> MANGROVE_STAIRS = create("mangrove_stairs");

    /**
     * Key for {@code minecraft:mangrove_trapdoor}.
     */
    public static final TypedKey<BlockType> MANGROVE_TRAPDOOR = create("mangrove_trapdoor");

    /**
     * Key for {@code minecraft:mangrove_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> MANGROVE_WALL_HANGING_SIGN = create("mangrove_wall_hanging_sign");

    /**
     * Key for {@code minecraft:mangrove_wall_sign}.
     */
    public static final TypedKey<BlockType> MANGROVE_WALL_SIGN = create("mangrove_wall_sign");

    /**
     * Key for {@code minecraft:mangrove_wood}.
     */
    public static final TypedKey<BlockType> MANGROVE_WOOD = create("mangrove_wood");

    /**
     * Key for {@code minecraft:medium_amethyst_bud}.
     */
    public static final TypedKey<BlockType> MEDIUM_AMETHYST_BUD = create("medium_amethyst_bud");

    /**
     * Key for {@code minecraft:melon}.
     */
    public static final TypedKey<BlockType> MELON = create("melon");

    /**
     * Key for {@code minecraft:melon_stem}.
     */
    public static final TypedKey<BlockType> MELON_STEM = create("melon_stem");

    /**
     * Key for {@code minecraft:moss_block}.
     */
    public static final TypedKey<BlockType> MOSS_BLOCK = create("moss_block");

    /**
     * Key for {@code minecraft:moss_carpet}.
     */
    public static final TypedKey<BlockType> MOSS_CARPET = create("moss_carpet");

    /**
     * Key for {@code minecraft:mossy_cobblestone}.
     */
    public static final TypedKey<BlockType> MOSSY_COBBLESTONE = create("mossy_cobblestone");

    /**
     * Key for {@code minecraft:mossy_cobblestone_slab}.
     */
    public static final TypedKey<BlockType> MOSSY_COBBLESTONE_SLAB = create("mossy_cobblestone_slab");

    /**
     * Key for {@code minecraft:mossy_cobblestone_stairs}.
     */
    public static final TypedKey<BlockType> MOSSY_COBBLESTONE_STAIRS = create("mossy_cobblestone_stairs");

    /**
     * Key for {@code minecraft:mossy_cobblestone_wall}.
     */
    public static final TypedKey<BlockType> MOSSY_COBBLESTONE_WALL = create("mossy_cobblestone_wall");

    /**
     * Key for {@code minecraft:mossy_stone_brick_slab}.
     */
    public static final TypedKey<BlockType> MOSSY_STONE_BRICK_SLAB = create("mossy_stone_brick_slab");

    /**
     * Key for {@code minecraft:mossy_stone_brick_stairs}.
     */
    public static final TypedKey<BlockType> MOSSY_STONE_BRICK_STAIRS = create("mossy_stone_brick_stairs");

    /**
     * Key for {@code minecraft:mossy_stone_brick_wall}.
     */
    public static final TypedKey<BlockType> MOSSY_STONE_BRICK_WALL = create("mossy_stone_brick_wall");

    /**
     * Key for {@code minecraft:mossy_stone_bricks}.
     */
    public static final TypedKey<BlockType> MOSSY_STONE_BRICKS = create("mossy_stone_bricks");

    /**
     * Key for {@code minecraft:moving_piston}.
     */
    public static final TypedKey<BlockType> MOVING_PISTON = create("moving_piston");

    /**
     * Key for {@code minecraft:mud}.
     */
    public static final TypedKey<BlockType> MUD = create("mud");

    /**
     * Key for {@code minecraft:mud_brick_slab}.
     */
    public static final TypedKey<BlockType> MUD_BRICK_SLAB = create("mud_brick_slab");

    /**
     * Key for {@code minecraft:mud_brick_stairs}.
     */
    public static final TypedKey<BlockType> MUD_BRICK_STAIRS = create("mud_brick_stairs");

    /**
     * Key for {@code minecraft:mud_brick_wall}.
     */
    public static final TypedKey<BlockType> MUD_BRICK_WALL = create("mud_brick_wall");

    /**
     * Key for {@code minecraft:mud_bricks}.
     */
    public static final TypedKey<BlockType> MUD_BRICKS = create("mud_bricks");

    /**
     * Key for {@code minecraft:muddy_mangrove_roots}.
     */
    public static final TypedKey<BlockType> MUDDY_MANGROVE_ROOTS = create("muddy_mangrove_roots");

    /**
     * Key for {@code minecraft:mushroom_stem}.
     */
    public static final TypedKey<BlockType> MUSHROOM_STEM = create("mushroom_stem");

    /**
     * Key for {@code minecraft:mycelium}.
     */
    public static final TypedKey<BlockType> MYCELIUM = create("mycelium");

    /**
     * Key for {@code minecraft:nether_brick_fence}.
     */
    public static final TypedKey<BlockType> NETHER_BRICK_FENCE = create("nether_brick_fence");

    /**
     * Key for {@code minecraft:nether_brick_slab}.
     */
    public static final TypedKey<BlockType> NETHER_BRICK_SLAB = create("nether_brick_slab");

    /**
     * Key for {@code minecraft:nether_brick_stairs}.
     */
    public static final TypedKey<BlockType> NETHER_BRICK_STAIRS = create("nether_brick_stairs");

    /**
     * Key for {@code minecraft:nether_brick_wall}.
     */
    public static final TypedKey<BlockType> NETHER_BRICK_WALL = create("nether_brick_wall");

    /**
     * Key for {@code minecraft:nether_bricks}.
     */
    public static final TypedKey<BlockType> NETHER_BRICKS = create("nether_bricks");

    /**
     * Key for {@code minecraft:nether_gold_ore}.
     */
    public static final TypedKey<BlockType> NETHER_GOLD_ORE = create("nether_gold_ore");

    /**
     * Key for {@code minecraft:nether_portal}.
     */
    public static final TypedKey<BlockType> NETHER_PORTAL = create("nether_portal");

    /**
     * Key for {@code minecraft:nether_quartz_ore}.
     */
    public static final TypedKey<BlockType> NETHER_QUARTZ_ORE = create("nether_quartz_ore");

    /**
     * Key for {@code minecraft:nether_sprouts}.
     */
    public static final TypedKey<BlockType> NETHER_SPROUTS = create("nether_sprouts");

    /**
     * Key for {@code minecraft:nether_wart}.
     */
    public static final TypedKey<BlockType> NETHER_WART = create("nether_wart");

    /**
     * Key for {@code minecraft:nether_wart_block}.
     */
    public static final TypedKey<BlockType> NETHER_WART_BLOCK = create("nether_wart_block");

    /**
     * Key for {@code minecraft:netherite_block}.
     */
    public static final TypedKey<BlockType> NETHERITE_BLOCK = create("netherite_block");

    /**
     * Key for {@code minecraft:netherrack}.
     */
    public static final TypedKey<BlockType> NETHERRACK = create("netherrack");

    /**
     * Key for {@code minecraft:note_block}.
     */
    public static final TypedKey<BlockType> NOTE_BLOCK = create("note_block");

    /**
     * Key for {@code minecraft:oak_button}.
     */
    public static final TypedKey<BlockType> OAK_BUTTON = create("oak_button");

    /**
     * Key for {@code minecraft:oak_door}.
     */
    public static final TypedKey<BlockType> OAK_DOOR = create("oak_door");

    /**
     * Key for {@code minecraft:oak_fence}.
     */
    public static final TypedKey<BlockType> OAK_FENCE = create("oak_fence");

    /**
     * Key for {@code minecraft:oak_fence_gate}.
     */
    public static final TypedKey<BlockType> OAK_FENCE_GATE = create("oak_fence_gate");

    /**
     * Key for {@code minecraft:oak_hanging_sign}.
     */
    public static final TypedKey<BlockType> OAK_HANGING_SIGN = create("oak_hanging_sign");

    /**
     * Key for {@code minecraft:oak_leaves}.
     */
    public static final TypedKey<BlockType> OAK_LEAVES = create("oak_leaves");

    /**
     * Key for {@code minecraft:oak_log}.
     */
    public static final TypedKey<BlockType> OAK_LOG = create("oak_log");

    /**
     * Key for {@code minecraft:oak_planks}.
     */
    public static final TypedKey<BlockType> OAK_PLANKS = create("oak_planks");

    /**
     * Key for {@code minecraft:oak_pressure_plate}.
     */
    public static final TypedKey<BlockType> OAK_PRESSURE_PLATE = create("oak_pressure_plate");

    /**
     * Key for {@code minecraft:oak_sapling}.
     */
    public static final TypedKey<BlockType> OAK_SAPLING = create("oak_sapling");

    /**
     * Key for {@code minecraft:oak_shelf}.
     */
    public static final TypedKey<BlockType> OAK_SHELF = create("oak_shelf");

    /**
     * Key for {@code minecraft:oak_sign}.
     */
    public static final TypedKey<BlockType> OAK_SIGN = create("oak_sign");

    /**
     * Key for {@code minecraft:oak_slab}.
     */
    public static final TypedKey<BlockType> OAK_SLAB = create("oak_slab");

    /**
     * Key for {@code minecraft:oak_stairs}.
     */
    public static final TypedKey<BlockType> OAK_STAIRS = create("oak_stairs");

    /**
     * Key for {@code minecraft:oak_trapdoor}.
     */
    public static final TypedKey<BlockType> OAK_TRAPDOOR = create("oak_trapdoor");

    /**
     * Key for {@code minecraft:oak_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> OAK_WALL_HANGING_SIGN = create("oak_wall_hanging_sign");

    /**
     * Key for {@code minecraft:oak_wall_sign}.
     */
    public static final TypedKey<BlockType> OAK_WALL_SIGN = create("oak_wall_sign");

    /**
     * Key for {@code minecraft:oak_wood}.
     */
    public static final TypedKey<BlockType> OAK_WOOD = create("oak_wood");

    /**
     * Key for {@code minecraft:observer}.
     */
    public static final TypedKey<BlockType> OBSERVER = create("observer");

    /**
     * Key for {@code minecraft:obsidian}.
     */
    public static final TypedKey<BlockType> OBSIDIAN = create("obsidian");

    /**
     * Key for {@code minecraft:ochre_froglight}.
     */
    public static final TypedKey<BlockType> OCHRE_FROGLIGHT = create("ochre_froglight");

    /**
     * Key for {@code minecraft:open_eyeblossom}.
     */
    public static final TypedKey<BlockType> OPEN_EYEBLOSSOM = create("open_eyeblossom");

    /**
     * Key for {@code minecraft:orange_banner}.
     */
    public static final TypedKey<BlockType> ORANGE_BANNER = create("orange_banner");

    /**
     * Key for {@code minecraft:orange_bed}.
     */
    public static final TypedKey<BlockType> ORANGE_BED = create("orange_bed");

    /**
     * Key for {@code minecraft:orange_candle}.
     */
    public static final TypedKey<BlockType> ORANGE_CANDLE = create("orange_candle");

    /**
     * Key for {@code minecraft:orange_candle_cake}.
     */
    public static final TypedKey<BlockType> ORANGE_CANDLE_CAKE = create("orange_candle_cake");

    /**
     * Key for {@code minecraft:orange_carpet}.
     */
    public static final TypedKey<BlockType> ORANGE_CARPET = create("orange_carpet");

    /**
     * Key for {@code minecraft:orange_concrete}.
     */
    public static final TypedKey<BlockType> ORANGE_CONCRETE = create("orange_concrete");

    /**
     * Key for {@code minecraft:orange_concrete_powder}.
     */
    public static final TypedKey<BlockType> ORANGE_CONCRETE_POWDER = create("orange_concrete_powder");

    /**
     * Key for {@code minecraft:orange_concrete_slab}.
     */
    public static final TypedKey<BlockType> ORANGE_CONCRETE_SLAB = create("orange_concrete_slab");

    /**
     * Key for {@code minecraft:orange_concrete_stairs}.
     */
    public static final TypedKey<BlockType> ORANGE_CONCRETE_STAIRS = create("orange_concrete_stairs");

    /**
     * Key for {@code minecraft:orange_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> ORANGE_GLAZED_TERRACOTTA = create("orange_glazed_terracotta");

    /**
     * Key for {@code minecraft:orange_poplar_leaves}.
     */
    public static final TypedKey<BlockType> ORANGE_POPLAR_LEAVES = create("orange_poplar_leaves");

    /**
     * Key for {@code minecraft:orange_shulker_box}.
     */
    public static final TypedKey<BlockType> ORANGE_SHULKER_BOX = create("orange_shulker_box");

    /**
     * Key for {@code minecraft:orange_stained_glass}.
     */
    public static final TypedKey<BlockType> ORANGE_STAINED_GLASS = create("orange_stained_glass");

    /**
     * Key for {@code minecraft:orange_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> ORANGE_STAINED_GLASS_PANE = create("orange_stained_glass_pane");

    /**
     * Key for {@code minecraft:orange_terracotta}.
     */
    public static final TypedKey<BlockType> ORANGE_TERRACOTTA = create("orange_terracotta");

    /**
     * Key for {@code minecraft:orange_tulip}.
     */
    public static final TypedKey<BlockType> ORANGE_TULIP = create("orange_tulip");

    /**
     * Key for {@code minecraft:orange_wall_banner}.
     */
    public static final TypedKey<BlockType> ORANGE_WALL_BANNER = create("orange_wall_banner");

    /**
     * Key for {@code minecraft:orange_wool}.
     */
    public static final TypedKey<BlockType> ORANGE_WOOL = create("orange_wool");

    /**
     * Key for {@code minecraft:orange_wool_slab}.
     */
    public static final TypedKey<BlockType> ORANGE_WOOL_SLAB = create("orange_wool_slab");

    /**
     * Key for {@code minecraft:orange_wool_stairs}.
     */
    public static final TypedKey<BlockType> ORANGE_WOOL_STAIRS = create("orange_wool_stairs");

    /**
     * Key for {@code minecraft:oxeye_daisy}.
     */
    public static final TypedKey<BlockType> OXEYE_DAISY = create("oxeye_daisy");

    /**
     * Key for {@code minecraft:oxidized_chiseled_copper}.
     */
    public static final TypedKey<BlockType> OXIDIZED_CHISELED_COPPER = create("oxidized_chiseled_copper");

    /**
     * Key for {@code minecraft:oxidized_copper}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER = create("oxidized_copper");

    /**
     * Key for {@code minecraft:oxidized_copper_bars}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_BARS = create("oxidized_copper_bars");

    /**
     * Key for {@code minecraft:oxidized_copper_bulb}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_BULB = create("oxidized_copper_bulb");

    /**
     * Key for {@code minecraft:oxidized_copper_chain}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_CHAIN = create("oxidized_copper_chain");

    /**
     * Key for {@code minecraft:oxidized_copper_chest}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_CHEST = create("oxidized_copper_chest");

    /**
     * Key for {@code minecraft:oxidized_copper_door}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_DOOR = create("oxidized_copper_door");

    /**
     * Key for {@code minecraft:oxidized_copper_golem_statue}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_GOLEM_STATUE = create("oxidized_copper_golem_statue");

    /**
     * Key for {@code minecraft:oxidized_copper_grate}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_GRATE = create("oxidized_copper_grate");

    /**
     * Key for {@code minecraft:oxidized_copper_lantern}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_LANTERN = create("oxidized_copper_lantern");

    /**
     * Key for {@code minecraft:oxidized_copper_trapdoor}.
     */
    public static final TypedKey<BlockType> OXIDIZED_COPPER_TRAPDOOR = create("oxidized_copper_trapdoor");

    /**
     * Key for {@code minecraft:oxidized_cut_copper}.
     */
    public static final TypedKey<BlockType> OXIDIZED_CUT_COPPER = create("oxidized_cut_copper");

    /**
     * Key for {@code minecraft:oxidized_cut_copper_slab}.
     */
    public static final TypedKey<BlockType> OXIDIZED_CUT_COPPER_SLAB = create("oxidized_cut_copper_slab");

    /**
     * Key for {@code minecraft:oxidized_cut_copper_stairs}.
     */
    public static final TypedKey<BlockType> OXIDIZED_CUT_COPPER_STAIRS = create("oxidized_cut_copper_stairs");

    /**
     * Key for {@code minecraft:oxidized_lightning_rod}.
     */
    public static final TypedKey<BlockType> OXIDIZED_LIGHTNING_ROD = create("oxidized_lightning_rod");

    /**
     * Key for {@code minecraft:packed_ice}.
     */
    public static final TypedKey<BlockType> PACKED_ICE = create("packed_ice");

    /**
     * Key for {@code minecraft:packed_mud}.
     */
    public static final TypedKey<BlockType> PACKED_MUD = create("packed_mud");

    /**
     * Key for {@code minecraft:pale_hanging_moss}.
     */
    public static final TypedKey<BlockType> PALE_HANGING_MOSS = create("pale_hanging_moss");

    /**
     * Key for {@code minecraft:pale_moss_block}.
     */
    public static final TypedKey<BlockType> PALE_MOSS_BLOCK = create("pale_moss_block");

    /**
     * Key for {@code minecraft:pale_moss_carpet}.
     */
    public static final TypedKey<BlockType> PALE_MOSS_CARPET = create("pale_moss_carpet");

    /**
     * Key for {@code minecraft:pale_oak_button}.
     */
    public static final TypedKey<BlockType> PALE_OAK_BUTTON = create("pale_oak_button");

    /**
     * Key for {@code minecraft:pale_oak_door}.
     */
    public static final TypedKey<BlockType> PALE_OAK_DOOR = create("pale_oak_door");

    /**
     * Key for {@code minecraft:pale_oak_fence}.
     */
    public static final TypedKey<BlockType> PALE_OAK_FENCE = create("pale_oak_fence");

    /**
     * Key for {@code minecraft:pale_oak_fence_gate}.
     */
    public static final TypedKey<BlockType> PALE_OAK_FENCE_GATE = create("pale_oak_fence_gate");

    /**
     * Key for {@code minecraft:pale_oak_hanging_sign}.
     */
    public static final TypedKey<BlockType> PALE_OAK_HANGING_SIGN = create("pale_oak_hanging_sign");

    /**
     * Key for {@code minecraft:pale_oak_leaves}.
     */
    public static final TypedKey<BlockType> PALE_OAK_LEAVES = create("pale_oak_leaves");

    /**
     * Key for {@code minecraft:pale_oak_log}.
     */
    public static final TypedKey<BlockType> PALE_OAK_LOG = create("pale_oak_log");

    /**
     * Key for {@code minecraft:pale_oak_planks}.
     */
    public static final TypedKey<BlockType> PALE_OAK_PLANKS = create("pale_oak_planks");

    /**
     * Key for {@code minecraft:pale_oak_pressure_plate}.
     */
    public static final TypedKey<BlockType> PALE_OAK_PRESSURE_PLATE = create("pale_oak_pressure_plate");

    /**
     * Key for {@code minecraft:pale_oak_sapling}.
     */
    public static final TypedKey<BlockType> PALE_OAK_SAPLING = create("pale_oak_sapling");

    /**
     * Key for {@code minecraft:pale_oak_shelf}.
     */
    public static final TypedKey<BlockType> PALE_OAK_SHELF = create("pale_oak_shelf");

    /**
     * Key for {@code minecraft:pale_oak_sign}.
     */
    public static final TypedKey<BlockType> PALE_OAK_SIGN = create("pale_oak_sign");

    /**
     * Key for {@code minecraft:pale_oak_slab}.
     */
    public static final TypedKey<BlockType> PALE_OAK_SLAB = create("pale_oak_slab");

    /**
     * Key for {@code minecraft:pale_oak_stairs}.
     */
    public static final TypedKey<BlockType> PALE_OAK_STAIRS = create("pale_oak_stairs");

    /**
     * Key for {@code minecraft:pale_oak_trapdoor}.
     */
    public static final TypedKey<BlockType> PALE_OAK_TRAPDOOR = create("pale_oak_trapdoor");

    /**
     * Key for {@code minecraft:pale_oak_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> PALE_OAK_WALL_HANGING_SIGN = create("pale_oak_wall_hanging_sign");

    /**
     * Key for {@code minecraft:pale_oak_wall_sign}.
     */
    public static final TypedKey<BlockType> PALE_OAK_WALL_SIGN = create("pale_oak_wall_sign");

    /**
     * Key for {@code minecraft:pale_oak_wood}.
     */
    public static final TypedKey<BlockType> PALE_OAK_WOOD = create("pale_oak_wood");

    /**
     * Key for {@code minecraft:pearlescent_froglight}.
     */
    public static final TypedKey<BlockType> PEARLESCENT_FROGLIGHT = create("pearlescent_froglight");

    /**
     * Key for {@code minecraft:peony}.
     */
    public static final TypedKey<BlockType> PEONY = create("peony");

    /**
     * Key for {@code minecraft:petrified_oak_slab}.
     */
    public static final TypedKey<BlockType> PETRIFIED_OAK_SLAB = create("petrified_oak_slab");

    /**
     * Key for {@code minecraft:piglin_head}.
     */
    public static final TypedKey<BlockType> PIGLIN_HEAD = create("piglin_head");

    /**
     * Key for {@code minecraft:piglin_wall_head}.
     */
    public static final TypedKey<BlockType> PIGLIN_WALL_HEAD = create("piglin_wall_head");

    /**
     * Key for {@code minecraft:pink_banner}.
     */
    public static final TypedKey<BlockType> PINK_BANNER = create("pink_banner");

    /**
     * Key for {@code minecraft:pink_bed}.
     */
    public static final TypedKey<BlockType> PINK_BED = create("pink_bed");

    /**
     * Key for {@code minecraft:pink_candle}.
     */
    public static final TypedKey<BlockType> PINK_CANDLE = create("pink_candle");

    /**
     * Key for {@code minecraft:pink_candle_cake}.
     */
    public static final TypedKey<BlockType> PINK_CANDLE_CAKE = create("pink_candle_cake");

    /**
     * Key for {@code minecraft:pink_carpet}.
     */
    public static final TypedKey<BlockType> PINK_CARPET = create("pink_carpet");

    /**
     * Key for {@code minecraft:pink_concrete}.
     */
    public static final TypedKey<BlockType> PINK_CONCRETE = create("pink_concrete");

    /**
     * Key for {@code minecraft:pink_concrete_powder}.
     */
    public static final TypedKey<BlockType> PINK_CONCRETE_POWDER = create("pink_concrete_powder");

    /**
     * Key for {@code minecraft:pink_concrete_slab}.
     */
    public static final TypedKey<BlockType> PINK_CONCRETE_SLAB = create("pink_concrete_slab");

    /**
     * Key for {@code minecraft:pink_concrete_stairs}.
     */
    public static final TypedKey<BlockType> PINK_CONCRETE_STAIRS = create("pink_concrete_stairs");

    /**
     * Key for {@code minecraft:pink_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> PINK_GLAZED_TERRACOTTA = create("pink_glazed_terracotta");

    /**
     * Key for {@code minecraft:pink_petals}.
     */
    public static final TypedKey<BlockType> PINK_PETALS = create("pink_petals");

    /**
     * Key for {@code minecraft:pink_shulker_box}.
     */
    public static final TypedKey<BlockType> PINK_SHULKER_BOX = create("pink_shulker_box");

    /**
     * Key for {@code minecraft:pink_stained_glass}.
     */
    public static final TypedKey<BlockType> PINK_STAINED_GLASS = create("pink_stained_glass");

    /**
     * Key for {@code minecraft:pink_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> PINK_STAINED_GLASS_PANE = create("pink_stained_glass_pane");

    /**
     * Key for {@code minecraft:pink_terracotta}.
     */
    public static final TypedKey<BlockType> PINK_TERRACOTTA = create("pink_terracotta");

    /**
     * Key for {@code minecraft:pink_tulip}.
     */
    public static final TypedKey<BlockType> PINK_TULIP = create("pink_tulip");

    /**
     * Key for {@code minecraft:pink_wall_banner}.
     */
    public static final TypedKey<BlockType> PINK_WALL_BANNER = create("pink_wall_banner");

    /**
     * Key for {@code minecraft:pink_wool}.
     */
    public static final TypedKey<BlockType> PINK_WOOL = create("pink_wool");

    /**
     * Key for {@code minecraft:pink_wool_slab}.
     */
    public static final TypedKey<BlockType> PINK_WOOL_SLAB = create("pink_wool_slab");

    /**
     * Key for {@code minecraft:pink_wool_stairs}.
     */
    public static final TypedKey<BlockType> PINK_WOOL_STAIRS = create("pink_wool_stairs");

    /**
     * Key for {@code minecraft:piston}.
     */
    public static final TypedKey<BlockType> PISTON = create("piston");

    /**
     * Key for {@code minecraft:piston_head}.
     */
    public static final TypedKey<BlockType> PISTON_HEAD = create("piston_head");

    /**
     * Key for {@code minecraft:pitcher_crop}.
     */
    public static final TypedKey<BlockType> PITCHER_CROP = create("pitcher_crop");

    /**
     * Key for {@code minecraft:pitcher_plant}.
     */
    public static final TypedKey<BlockType> PITCHER_PLANT = create("pitcher_plant");

    /**
     * Key for {@code minecraft:player_head}.
     */
    public static final TypedKey<BlockType> PLAYER_HEAD = create("player_head");

    /**
     * Key for {@code minecraft:player_wall_head}.
     */
    public static final TypedKey<BlockType> PLAYER_WALL_HEAD = create("player_wall_head");

    /**
     * Key for {@code minecraft:podzol}.
     */
    public static final TypedKey<BlockType> PODZOL = create("podzol");

    /**
     * Key for {@code minecraft:pointed_dripstone}.
     */
    public static final TypedKey<BlockType> POINTED_DRIPSTONE = create("pointed_dripstone");

    /**
     * Key for {@code minecraft:polished_andesite}.
     */
    public static final TypedKey<BlockType> POLISHED_ANDESITE = create("polished_andesite");

    /**
     * Key for {@code minecraft:polished_andesite_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_ANDESITE_SLAB = create("polished_andesite_slab");

    /**
     * Key for {@code minecraft:polished_andesite_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_ANDESITE_STAIRS = create("polished_andesite_stairs");

    /**
     * Key for {@code minecraft:polished_basalt}.
     */
    public static final TypedKey<BlockType> POLISHED_BASALT = create("polished_basalt");

    /**
     * Key for {@code minecraft:polished_blackstone}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE = create("polished_blackstone");

    /**
     * Key for {@code minecraft:polished_blackstone_brick_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_BRICK_SLAB = create("polished_blackstone_brick_slab");

    /**
     * Key for {@code minecraft:polished_blackstone_brick_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_BRICK_STAIRS = create("polished_blackstone_brick_stairs");

    /**
     * Key for {@code minecraft:polished_blackstone_brick_wall}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_BRICK_WALL = create("polished_blackstone_brick_wall");

    /**
     * Key for {@code minecraft:polished_blackstone_bricks}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_BRICKS = create("polished_blackstone_bricks");

    /**
     * Key for {@code minecraft:polished_blackstone_button}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_BUTTON = create("polished_blackstone_button");

    /**
     * Key for {@code minecraft:polished_blackstone_pressure_plate}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_PRESSURE_PLATE = create("polished_blackstone_pressure_plate");

    /**
     * Key for {@code minecraft:polished_blackstone_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_SLAB = create("polished_blackstone_slab");

    /**
     * Key for {@code minecraft:polished_blackstone_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_STAIRS = create("polished_blackstone_stairs");

    /**
     * Key for {@code minecraft:polished_blackstone_wall}.
     */
    public static final TypedKey<BlockType> POLISHED_BLACKSTONE_WALL = create("polished_blackstone_wall");

    /**
     * Key for {@code minecraft:polished_cinnabar}.
     */
    public static final TypedKey<BlockType> POLISHED_CINNABAR = create("polished_cinnabar");

    /**
     * Key for {@code minecraft:polished_cinnabar_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_CINNABAR_SLAB = create("polished_cinnabar_slab");

    /**
     * Key for {@code minecraft:polished_cinnabar_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_CINNABAR_STAIRS = create("polished_cinnabar_stairs");

    /**
     * Key for {@code minecraft:polished_cinnabar_wall}.
     */
    public static final TypedKey<BlockType> POLISHED_CINNABAR_WALL = create("polished_cinnabar_wall");

    /**
     * Key for {@code minecraft:polished_deepslate}.
     */
    public static final TypedKey<BlockType> POLISHED_DEEPSLATE = create("polished_deepslate");

    /**
     * Key for {@code minecraft:polished_deepslate_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_DEEPSLATE_SLAB = create("polished_deepslate_slab");

    /**
     * Key for {@code minecraft:polished_deepslate_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_DEEPSLATE_STAIRS = create("polished_deepslate_stairs");

    /**
     * Key for {@code minecraft:polished_deepslate_wall}.
     */
    public static final TypedKey<BlockType> POLISHED_DEEPSLATE_WALL = create("polished_deepslate_wall");

    /**
     * Key for {@code minecraft:polished_diorite}.
     */
    public static final TypedKey<BlockType> POLISHED_DIORITE = create("polished_diorite");

    /**
     * Key for {@code minecraft:polished_diorite_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_DIORITE_SLAB = create("polished_diorite_slab");

    /**
     * Key for {@code minecraft:polished_diorite_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_DIORITE_STAIRS = create("polished_diorite_stairs");

    /**
     * Key for {@code minecraft:polished_granite}.
     */
    public static final TypedKey<BlockType> POLISHED_GRANITE = create("polished_granite");

    /**
     * Key for {@code minecraft:polished_granite_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_GRANITE_SLAB = create("polished_granite_slab");

    /**
     * Key for {@code minecraft:polished_granite_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_GRANITE_STAIRS = create("polished_granite_stairs");

    /**
     * Key for {@code minecraft:polished_sulfur}.
     */
    public static final TypedKey<BlockType> POLISHED_SULFUR = create("polished_sulfur");

    /**
     * Key for {@code minecraft:polished_sulfur_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_SULFUR_SLAB = create("polished_sulfur_slab");

    /**
     * Key for {@code minecraft:polished_sulfur_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_SULFUR_STAIRS = create("polished_sulfur_stairs");

    /**
     * Key for {@code minecraft:polished_sulfur_wall}.
     */
    public static final TypedKey<BlockType> POLISHED_SULFUR_WALL = create("polished_sulfur_wall");

    /**
     * Key for {@code minecraft:polished_tuff}.
     */
    public static final TypedKey<BlockType> POLISHED_TUFF = create("polished_tuff");

    /**
     * Key for {@code minecraft:polished_tuff_slab}.
     */
    public static final TypedKey<BlockType> POLISHED_TUFF_SLAB = create("polished_tuff_slab");

    /**
     * Key for {@code minecraft:polished_tuff_stairs}.
     */
    public static final TypedKey<BlockType> POLISHED_TUFF_STAIRS = create("polished_tuff_stairs");

    /**
     * Key for {@code minecraft:polished_tuff_wall}.
     */
    public static final TypedKey<BlockType> POLISHED_TUFF_WALL = create("polished_tuff_wall");

    /**
     * Key for {@code minecraft:poplar_button}.
     */
    public static final TypedKey<BlockType> POPLAR_BUTTON = create("poplar_button");

    /**
     * Key for {@code minecraft:poplar_door}.
     */
    public static final TypedKey<BlockType> POPLAR_DOOR = create("poplar_door");

    /**
     * Key for {@code minecraft:poplar_fence}.
     */
    public static final TypedKey<BlockType> POPLAR_FENCE = create("poplar_fence");

    /**
     * Key for {@code minecraft:poplar_fence_gate}.
     */
    public static final TypedKey<BlockType> POPLAR_FENCE_GATE = create("poplar_fence_gate");

    /**
     * Key for {@code minecraft:poplar_hanging_sign}.
     */
    public static final TypedKey<BlockType> POPLAR_HANGING_SIGN = create("poplar_hanging_sign");

    /**
     * Key for {@code minecraft:poplar_log}.
     */
    public static final TypedKey<BlockType> POPLAR_LOG = create("poplar_log");

    /**
     * Key for {@code minecraft:poplar_planks}.
     */
    public static final TypedKey<BlockType> POPLAR_PLANKS = create("poplar_planks");

    /**
     * Key for {@code minecraft:poplar_pressure_plate}.
     */
    public static final TypedKey<BlockType> POPLAR_PRESSURE_PLATE = create("poplar_pressure_plate");

    /**
     * Key for {@code minecraft:poplar_sapling}.
     */
    public static final TypedKey<BlockType> POPLAR_SAPLING = create("poplar_sapling");

    /**
     * Key for {@code minecraft:poplar_shelf}.
     */
    public static final TypedKey<BlockType> POPLAR_SHELF = create("poplar_shelf");

    /**
     * Key for {@code minecraft:poplar_sign}.
     */
    public static final TypedKey<BlockType> POPLAR_SIGN = create("poplar_sign");

    /**
     * Key for {@code minecraft:poplar_slab}.
     */
    public static final TypedKey<BlockType> POPLAR_SLAB = create("poplar_slab");

    /**
     * Key for {@code minecraft:poplar_stairs}.
     */
    public static final TypedKey<BlockType> POPLAR_STAIRS = create("poplar_stairs");

    /**
     * Key for {@code minecraft:poplar_trapdoor}.
     */
    public static final TypedKey<BlockType> POPLAR_TRAPDOOR = create("poplar_trapdoor");

    /**
     * Key for {@code minecraft:poplar_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> POPLAR_WALL_HANGING_SIGN = create("poplar_wall_hanging_sign");

    /**
     * Key for {@code minecraft:poplar_wall_sign}.
     */
    public static final TypedKey<BlockType> POPLAR_WALL_SIGN = create("poplar_wall_sign");

    /**
     * Key for {@code minecraft:poplar_wood}.
     */
    public static final TypedKey<BlockType> POPLAR_WOOD = create("poplar_wood");

    /**
     * Key for {@code minecraft:poppy}.
     */
    public static final TypedKey<BlockType> POPPY = create("poppy");

    /**
     * Key for {@code minecraft:potatoes}.
     */
    public static final TypedKey<BlockType> POTATOES = create("potatoes");

    /**
     * Key for {@code minecraft:potent_sulfur}.
     */
    public static final TypedKey<BlockType> POTENT_SULFUR = create("potent_sulfur");

    /**
     * Key for {@code minecraft:potted_acacia_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_ACACIA_SAPLING = create("potted_acacia_sapling");

    /**
     * Key for {@code minecraft:potted_allium}.
     */
    public static final TypedKey<BlockType> POTTED_ALLIUM = create("potted_allium");

    /**
     * Key for {@code minecraft:potted_azalea_bush}.
     */
    public static final TypedKey<BlockType> POTTED_AZALEA_BUSH = create("potted_azalea_bush");

    /**
     * Key for {@code minecraft:potted_azure_bluet}.
     */
    public static final TypedKey<BlockType> POTTED_AZURE_BLUET = create("potted_azure_bluet");

    /**
     * Key for {@code minecraft:potted_bamboo}.
     */
    public static final TypedKey<BlockType> POTTED_BAMBOO = create("potted_bamboo");

    /**
     * Key for {@code minecraft:potted_birch_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_BIRCH_SAPLING = create("potted_birch_sapling");

    /**
     * Key for {@code minecraft:potted_blue_orchid}.
     */
    public static final TypedKey<BlockType> POTTED_BLUE_ORCHID = create("potted_blue_orchid");

    /**
     * Key for {@code minecraft:potted_brown_mushroom}.
     */
    public static final TypedKey<BlockType> POTTED_BROWN_MUSHROOM = create("potted_brown_mushroom");

    /**
     * Key for {@code minecraft:potted_cactus}.
     */
    public static final TypedKey<BlockType> POTTED_CACTUS = create("potted_cactus");

    /**
     * Key for {@code minecraft:potted_cherry_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_CHERRY_SAPLING = create("potted_cherry_sapling");

    /**
     * Key for {@code minecraft:potted_closed_eyeblossom}.
     */
    public static final TypedKey<BlockType> POTTED_CLOSED_EYEBLOSSOM = create("potted_closed_eyeblossom");

    /**
     * Key for {@code minecraft:potted_cornflower}.
     */
    public static final TypedKey<BlockType> POTTED_CORNFLOWER = create("potted_cornflower");

    /**
     * Key for {@code minecraft:potted_crimson_fungus}.
     */
    public static final TypedKey<BlockType> POTTED_CRIMSON_FUNGUS = create("potted_crimson_fungus");

    /**
     * Key for {@code minecraft:potted_crimson_roots}.
     */
    public static final TypedKey<BlockType> POTTED_CRIMSON_ROOTS = create("potted_crimson_roots");

    /**
     * Key for {@code minecraft:potted_dandelion}.
     */
    public static final TypedKey<BlockType> POTTED_DANDELION = create("potted_dandelion");

    /**
     * Key for {@code minecraft:potted_dark_oak_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_DARK_OAK_SAPLING = create("potted_dark_oak_sapling");

    /**
     * Key for {@code minecraft:potted_dead_bush}.
     */
    public static final TypedKey<BlockType> POTTED_DEAD_BUSH = create("potted_dead_bush");

    /**
     * Key for {@code minecraft:potted_fern}.
     */
    public static final TypedKey<BlockType> POTTED_FERN = create("potted_fern");

    /**
     * Key for {@code minecraft:potted_flowering_azalea_bush}.
     */
    public static final TypedKey<BlockType> POTTED_FLOWERING_AZALEA_BUSH = create("potted_flowering_azalea_bush");

    /**
     * Key for {@code minecraft:potted_golden_dandelion}.
     */
    public static final TypedKey<BlockType> POTTED_GOLDEN_DANDELION = create("potted_golden_dandelion");

    /**
     * Key for {@code minecraft:potted_jungle_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_JUNGLE_SAPLING = create("potted_jungle_sapling");

    /**
     * Key for {@code minecraft:potted_lily_of_the_valley}.
     */
    public static final TypedKey<BlockType> POTTED_LILY_OF_THE_VALLEY = create("potted_lily_of_the_valley");

    /**
     * Key for {@code minecraft:potted_mangrove_propagule}.
     */
    public static final TypedKey<BlockType> POTTED_MANGROVE_PROPAGULE = create("potted_mangrove_propagule");

    /**
     * Key for {@code minecraft:potted_oak_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_OAK_SAPLING = create("potted_oak_sapling");

    /**
     * Key for {@code minecraft:potted_open_eyeblossom}.
     */
    public static final TypedKey<BlockType> POTTED_OPEN_EYEBLOSSOM = create("potted_open_eyeblossom");

    /**
     * Key for {@code minecraft:potted_orange_tulip}.
     */
    public static final TypedKey<BlockType> POTTED_ORANGE_TULIP = create("potted_orange_tulip");

    /**
     * Key for {@code minecraft:potted_oxeye_daisy}.
     */
    public static final TypedKey<BlockType> POTTED_OXEYE_DAISY = create("potted_oxeye_daisy");

    /**
     * Key for {@code minecraft:potted_pale_oak_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_PALE_OAK_SAPLING = create("potted_pale_oak_sapling");

    /**
     * Key for {@code minecraft:potted_pink_tulip}.
     */
    public static final TypedKey<BlockType> POTTED_PINK_TULIP = create("potted_pink_tulip");

    /**
     * Key for {@code minecraft:potted_poplar_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_POPLAR_SAPLING = create("potted_poplar_sapling");

    /**
     * Key for {@code minecraft:potted_poppy}.
     */
    public static final TypedKey<BlockType> POTTED_POPPY = create("potted_poppy");

    /**
     * Key for {@code minecraft:potted_red_mushroom}.
     */
    public static final TypedKey<BlockType> POTTED_RED_MUSHROOM = create("potted_red_mushroom");

    /**
     * Key for {@code minecraft:potted_red_tulip}.
     */
    public static final TypedKey<BlockType> POTTED_RED_TULIP = create("potted_red_tulip");

    /**
     * Key for {@code minecraft:potted_spruce_sapling}.
     */
    public static final TypedKey<BlockType> POTTED_SPRUCE_SAPLING = create("potted_spruce_sapling");

    /**
     * Key for {@code minecraft:potted_torchflower}.
     */
    public static final TypedKey<BlockType> POTTED_TORCHFLOWER = create("potted_torchflower");

    /**
     * Key for {@code minecraft:potted_warped_fungus}.
     */
    public static final TypedKey<BlockType> POTTED_WARPED_FUNGUS = create("potted_warped_fungus");

    /**
     * Key for {@code minecraft:potted_warped_roots}.
     */
    public static final TypedKey<BlockType> POTTED_WARPED_ROOTS = create("potted_warped_roots");

    /**
     * Key for {@code minecraft:potted_white_tulip}.
     */
    public static final TypedKey<BlockType> POTTED_WHITE_TULIP = create("potted_white_tulip");

    /**
     * Key for {@code minecraft:potted_wither_rose}.
     */
    public static final TypedKey<BlockType> POTTED_WITHER_ROSE = create("potted_wither_rose");

    /**
     * Key for {@code minecraft:powder_snow}.
     */
    public static final TypedKey<BlockType> POWDER_SNOW = create("powder_snow");

    /**
     * Key for {@code minecraft:powder_snow_cauldron}.
     */
    public static final TypedKey<BlockType> POWDER_SNOW_CAULDRON = create("powder_snow_cauldron");

    /**
     * Key for {@code minecraft:powered_rail}.
     */
    public static final TypedKey<BlockType> POWERED_RAIL = create("powered_rail");

    /**
     * Key for {@code minecraft:prismarine}.
     */
    public static final TypedKey<BlockType> PRISMARINE = create("prismarine");

    /**
     * Key for {@code minecraft:prismarine_brick_slab}.
     */
    public static final TypedKey<BlockType> PRISMARINE_BRICK_SLAB = create("prismarine_brick_slab");

    /**
     * Key for {@code minecraft:prismarine_brick_stairs}.
     */
    public static final TypedKey<BlockType> PRISMARINE_BRICK_STAIRS = create("prismarine_brick_stairs");

    /**
     * Key for {@code minecraft:prismarine_bricks}.
     */
    public static final TypedKey<BlockType> PRISMARINE_BRICKS = create("prismarine_bricks");

    /**
     * Key for {@code minecraft:prismarine_slab}.
     */
    public static final TypedKey<BlockType> PRISMARINE_SLAB = create("prismarine_slab");

    /**
     * Key for {@code minecraft:prismarine_stairs}.
     */
    public static final TypedKey<BlockType> PRISMARINE_STAIRS = create("prismarine_stairs");

    /**
     * Key for {@code minecraft:prismarine_wall}.
     */
    public static final TypedKey<BlockType> PRISMARINE_WALL = create("prismarine_wall");

    /**
     * Key for {@code minecraft:pumpkin}.
     */
    public static final TypedKey<BlockType> PUMPKIN = create("pumpkin");

    /**
     * Key for {@code minecraft:pumpkin_stem}.
     */
    public static final TypedKey<BlockType> PUMPKIN_STEM = create("pumpkin_stem");

    /**
     * Key for {@code minecraft:purple_banner}.
     */
    public static final TypedKey<BlockType> PURPLE_BANNER = create("purple_banner");

    /**
     * Key for {@code minecraft:purple_bed}.
     */
    public static final TypedKey<BlockType> PURPLE_BED = create("purple_bed");

    /**
     * Key for {@code minecraft:purple_candle}.
     */
    public static final TypedKey<BlockType> PURPLE_CANDLE = create("purple_candle");

    /**
     * Key for {@code minecraft:purple_candle_cake}.
     */
    public static final TypedKey<BlockType> PURPLE_CANDLE_CAKE = create("purple_candle_cake");

    /**
     * Key for {@code minecraft:purple_carpet}.
     */
    public static final TypedKey<BlockType> PURPLE_CARPET = create("purple_carpet");

    /**
     * Key for {@code minecraft:purple_concrete}.
     */
    public static final TypedKey<BlockType> PURPLE_CONCRETE = create("purple_concrete");

    /**
     * Key for {@code minecraft:purple_concrete_powder}.
     */
    public static final TypedKey<BlockType> PURPLE_CONCRETE_POWDER = create("purple_concrete_powder");

    /**
     * Key for {@code minecraft:purple_concrete_slab}.
     */
    public static final TypedKey<BlockType> PURPLE_CONCRETE_SLAB = create("purple_concrete_slab");

    /**
     * Key for {@code minecraft:purple_concrete_stairs}.
     */
    public static final TypedKey<BlockType> PURPLE_CONCRETE_STAIRS = create("purple_concrete_stairs");

    /**
     * Key for {@code minecraft:purple_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> PURPLE_GLAZED_TERRACOTTA = create("purple_glazed_terracotta");

    /**
     * Key for {@code minecraft:purple_shulker_box}.
     */
    public static final TypedKey<BlockType> PURPLE_SHULKER_BOX = create("purple_shulker_box");

    /**
     * Key for {@code minecraft:purple_stained_glass}.
     */
    public static final TypedKey<BlockType> PURPLE_STAINED_GLASS = create("purple_stained_glass");

    /**
     * Key for {@code minecraft:purple_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> PURPLE_STAINED_GLASS_PANE = create("purple_stained_glass_pane");

    /**
     * Key for {@code minecraft:purple_terracotta}.
     */
    public static final TypedKey<BlockType> PURPLE_TERRACOTTA = create("purple_terracotta");

    /**
     * Key for {@code minecraft:purple_wall_banner}.
     */
    public static final TypedKey<BlockType> PURPLE_WALL_BANNER = create("purple_wall_banner");

    /**
     * Key for {@code minecraft:purple_wool}.
     */
    public static final TypedKey<BlockType> PURPLE_WOOL = create("purple_wool");

    /**
     * Key for {@code minecraft:purple_wool_slab}.
     */
    public static final TypedKey<BlockType> PURPLE_WOOL_SLAB = create("purple_wool_slab");

    /**
     * Key for {@code minecraft:purple_wool_stairs}.
     */
    public static final TypedKey<BlockType> PURPLE_WOOL_STAIRS = create("purple_wool_stairs");

    /**
     * Key for {@code minecraft:purpur_block}.
     */
    public static final TypedKey<BlockType> PURPUR_BLOCK = create("purpur_block");

    /**
     * Key for {@code minecraft:purpur_pillar}.
     */
    public static final TypedKey<BlockType> PURPUR_PILLAR = create("purpur_pillar");

    /**
     * Key for {@code minecraft:purpur_slab}.
     */
    public static final TypedKey<BlockType> PURPUR_SLAB = create("purpur_slab");

    /**
     * Key for {@code minecraft:purpur_stairs}.
     */
    public static final TypedKey<BlockType> PURPUR_STAIRS = create("purpur_stairs");

    /**
     * Key for {@code minecraft:quartz_block}.
     */
    public static final TypedKey<BlockType> QUARTZ_BLOCK = create("quartz_block");

    /**
     * Key for {@code minecraft:quartz_bricks}.
     */
    public static final TypedKey<BlockType> QUARTZ_BRICKS = create("quartz_bricks");

    /**
     * Key for {@code minecraft:quartz_pillar}.
     */
    public static final TypedKey<BlockType> QUARTZ_PILLAR = create("quartz_pillar");

    /**
     * Key for {@code minecraft:quartz_slab}.
     */
    public static final TypedKey<BlockType> QUARTZ_SLAB = create("quartz_slab");

    /**
     * Key for {@code minecraft:quartz_stairs}.
     */
    public static final TypedKey<BlockType> QUARTZ_STAIRS = create("quartz_stairs");

    /**
     * Key for {@code minecraft:rail}.
     */
    public static final TypedKey<BlockType> RAIL = create("rail");

    /**
     * Key for {@code minecraft:raw_copper_block}.
     */
    public static final TypedKey<BlockType> RAW_COPPER_BLOCK = create("raw_copper_block");

    /**
     * Key for {@code minecraft:raw_gold_block}.
     */
    public static final TypedKey<BlockType> RAW_GOLD_BLOCK = create("raw_gold_block");

    /**
     * Key for {@code minecraft:raw_iron_block}.
     */
    public static final TypedKey<BlockType> RAW_IRON_BLOCK = create("raw_iron_block");

    /**
     * Key for {@code minecraft:red_banner}.
     */
    public static final TypedKey<BlockType> RED_BANNER = create("red_banner");

    /**
     * Key for {@code minecraft:red_bed}.
     */
    public static final TypedKey<BlockType> RED_BED = create("red_bed");

    /**
     * Key for {@code minecraft:red_candle}.
     */
    public static final TypedKey<BlockType> RED_CANDLE = create("red_candle");

    /**
     * Key for {@code minecraft:red_candle_cake}.
     */
    public static final TypedKey<BlockType> RED_CANDLE_CAKE = create("red_candle_cake");

    /**
     * Key for {@code minecraft:red_carpet}.
     */
    public static final TypedKey<BlockType> RED_CARPET = create("red_carpet");

    /**
     * Key for {@code minecraft:red_concrete}.
     */
    public static final TypedKey<BlockType> RED_CONCRETE = create("red_concrete");

    /**
     * Key for {@code minecraft:red_concrete_powder}.
     */
    public static final TypedKey<BlockType> RED_CONCRETE_POWDER = create("red_concrete_powder");

    /**
     * Key for {@code minecraft:red_concrete_slab}.
     */
    public static final TypedKey<BlockType> RED_CONCRETE_SLAB = create("red_concrete_slab");

    /**
     * Key for {@code minecraft:red_concrete_stairs}.
     */
    public static final TypedKey<BlockType> RED_CONCRETE_STAIRS = create("red_concrete_stairs");

    /**
     * Key for {@code minecraft:red_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> RED_GLAZED_TERRACOTTA = create("red_glazed_terracotta");

    /**
     * Key for {@code minecraft:red_mushroom}.
     */
    public static final TypedKey<BlockType> RED_MUSHROOM = create("red_mushroom");

    /**
     * Key for {@code minecraft:red_mushroom_block}.
     */
    public static final TypedKey<BlockType> RED_MUSHROOM_BLOCK = create("red_mushroom_block");

    /**
     * Key for {@code minecraft:red_nether_brick_slab}.
     */
    public static final TypedKey<BlockType> RED_NETHER_BRICK_SLAB = create("red_nether_brick_slab");

    /**
     * Key for {@code minecraft:red_nether_brick_stairs}.
     */
    public static final TypedKey<BlockType> RED_NETHER_BRICK_STAIRS = create("red_nether_brick_stairs");

    /**
     * Key for {@code minecraft:red_nether_brick_wall}.
     */
    public static final TypedKey<BlockType> RED_NETHER_BRICK_WALL = create("red_nether_brick_wall");

    /**
     * Key for {@code minecraft:red_nether_bricks}.
     */
    public static final TypedKey<BlockType> RED_NETHER_BRICKS = create("red_nether_bricks");

    /**
     * Key for {@code minecraft:red_poplar_leaves}.
     */
    public static final TypedKey<BlockType> RED_POPLAR_LEAVES = create("red_poplar_leaves");

    /**
     * Key for {@code minecraft:red_sand}.
     */
    public static final TypedKey<BlockType> RED_SAND = create("red_sand");

    /**
     * Key for {@code minecraft:red_sandstone}.
     */
    public static final TypedKey<BlockType> RED_SANDSTONE = create("red_sandstone");

    /**
     * Key for {@code minecraft:red_sandstone_slab}.
     */
    public static final TypedKey<BlockType> RED_SANDSTONE_SLAB = create("red_sandstone_slab");

    /**
     * Key for {@code minecraft:red_sandstone_stairs}.
     */
    public static final TypedKey<BlockType> RED_SANDSTONE_STAIRS = create("red_sandstone_stairs");

    /**
     * Key for {@code minecraft:red_sandstone_wall}.
     */
    public static final TypedKey<BlockType> RED_SANDSTONE_WALL = create("red_sandstone_wall");

    /**
     * Key for {@code minecraft:red_shrub}.
     */
    public static final TypedKey<BlockType> RED_SHRUB = create("red_shrub");

    /**
     * Key for {@code minecraft:red_shulker_box}.
     */
    public static final TypedKey<BlockType> RED_SHULKER_BOX = create("red_shulker_box");

    /**
     * Key for {@code minecraft:red_stained_glass}.
     */
    public static final TypedKey<BlockType> RED_STAINED_GLASS = create("red_stained_glass");

    /**
     * Key for {@code minecraft:red_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> RED_STAINED_GLASS_PANE = create("red_stained_glass_pane");

    /**
     * Key for {@code minecraft:red_terracotta}.
     */
    public static final TypedKey<BlockType> RED_TERRACOTTA = create("red_terracotta");

    /**
     * Key for {@code minecraft:red_tulip}.
     */
    public static final TypedKey<BlockType> RED_TULIP = create("red_tulip");

    /**
     * Key for {@code minecraft:red_wall_banner}.
     */
    public static final TypedKey<BlockType> RED_WALL_BANNER = create("red_wall_banner");

    /**
     * Key for {@code minecraft:red_wool}.
     */
    public static final TypedKey<BlockType> RED_WOOL = create("red_wool");

    /**
     * Key for {@code minecraft:red_wool_slab}.
     */
    public static final TypedKey<BlockType> RED_WOOL_SLAB = create("red_wool_slab");

    /**
     * Key for {@code minecraft:red_wool_stairs}.
     */
    public static final TypedKey<BlockType> RED_WOOL_STAIRS = create("red_wool_stairs");

    /**
     * Key for {@code minecraft:redstone_block}.
     */
    public static final TypedKey<BlockType> REDSTONE_BLOCK = create("redstone_block");

    /**
     * Key for {@code minecraft:redstone_lamp}.
     */
    public static final TypedKey<BlockType> REDSTONE_LAMP = create("redstone_lamp");

    /**
     * Key for {@code minecraft:redstone_ore}.
     */
    public static final TypedKey<BlockType> REDSTONE_ORE = create("redstone_ore");

    /**
     * Key for {@code minecraft:redstone_torch}.
     */
    public static final TypedKey<BlockType> REDSTONE_TORCH = create("redstone_torch");

    /**
     * Key for {@code minecraft:redstone_wall_torch}.
     */
    public static final TypedKey<BlockType> REDSTONE_WALL_TORCH = create("redstone_wall_torch");

    /**
     * Key for {@code minecraft:redstone_wire}.
     */
    public static final TypedKey<BlockType> REDSTONE_WIRE = create("redstone_wire");

    /**
     * Key for {@code minecraft:reinforced_deepslate}.
     */
    public static final TypedKey<BlockType> REINFORCED_DEEPSLATE = create("reinforced_deepslate");

    /**
     * Key for {@code minecraft:repeater}.
     */
    public static final TypedKey<BlockType> REPEATER = create("repeater");

    /**
     * Key for {@code minecraft:repeating_command_block}.
     */
    public static final TypedKey<BlockType> REPEATING_COMMAND_BLOCK = create("repeating_command_block");

    /**
     * Key for {@code minecraft:resin_block}.
     */
    public static final TypedKey<BlockType> RESIN_BLOCK = create("resin_block");

    /**
     * Key for {@code minecraft:resin_brick_slab}.
     */
    public static final TypedKey<BlockType> RESIN_BRICK_SLAB = create("resin_brick_slab");

    /**
     * Key for {@code minecraft:resin_brick_stairs}.
     */
    public static final TypedKey<BlockType> RESIN_BRICK_STAIRS = create("resin_brick_stairs");

    /**
     * Key for {@code minecraft:resin_brick_wall}.
     */
    public static final TypedKey<BlockType> RESIN_BRICK_WALL = create("resin_brick_wall");

    /**
     * Key for {@code minecraft:resin_bricks}.
     */
    public static final TypedKey<BlockType> RESIN_BRICKS = create("resin_bricks");

    /**
     * Key for {@code minecraft:resin_clump}.
     */
    public static final TypedKey<BlockType> RESIN_CLUMP = create("resin_clump");

    /**
     * Key for {@code minecraft:respawn_anchor}.
     */
    public static final TypedKey<BlockType> RESPAWN_ANCHOR = create("respawn_anchor");

    /**
     * Key for {@code minecraft:rooted_dirt}.
     */
    public static final TypedKey<BlockType> ROOTED_DIRT = create("rooted_dirt");

    /**
     * Key for {@code minecraft:rose_bush}.
     */
    public static final TypedKey<BlockType> ROSE_BUSH = create("rose_bush");

    /**
     * Key for {@code minecraft:sand}.
     */
    public static final TypedKey<BlockType> SAND = create("sand");

    /**
     * Key for {@code minecraft:sandstone}.
     */
    public static final TypedKey<BlockType> SANDSTONE = create("sandstone");

    /**
     * Key for {@code minecraft:sandstone_slab}.
     */
    public static final TypedKey<BlockType> SANDSTONE_SLAB = create("sandstone_slab");

    /**
     * Key for {@code minecraft:sandstone_stairs}.
     */
    public static final TypedKey<BlockType> SANDSTONE_STAIRS = create("sandstone_stairs");

    /**
     * Key for {@code minecraft:sandstone_wall}.
     */
    public static final TypedKey<BlockType> SANDSTONE_WALL = create("sandstone_wall");

    /**
     * Key for {@code minecraft:scaffolding}.
     */
    public static final TypedKey<BlockType> SCAFFOLDING = create("scaffolding");

    /**
     * Key for {@code minecraft:sculk}.
     */
    public static final TypedKey<BlockType> SCULK = create("sculk");

    /**
     * Key for {@code minecraft:sculk_catalyst}.
     */
    public static final TypedKey<BlockType> SCULK_CATALYST = create("sculk_catalyst");

    /**
     * Key for {@code minecraft:sculk_sensor}.
     */
    public static final TypedKey<BlockType> SCULK_SENSOR = create("sculk_sensor");

    /**
     * Key for {@code minecraft:sculk_shrieker}.
     */
    public static final TypedKey<BlockType> SCULK_SHRIEKER = create("sculk_shrieker");

    /**
     * Key for {@code minecraft:sculk_vein}.
     */
    public static final TypedKey<BlockType> SCULK_VEIN = create("sculk_vein");

    /**
     * Key for {@code minecraft:sea_lantern}.
     */
    public static final TypedKey<BlockType> SEA_LANTERN = create("sea_lantern");

    /**
     * Key for {@code minecraft:sea_pickle}.
     */
    public static final TypedKey<BlockType> SEA_PICKLE = create("sea_pickle");

    /**
     * Key for {@code minecraft:seagrass}.
     */
    public static final TypedKey<BlockType> SEAGRASS = create("seagrass");

    /**
     * Key for {@code minecraft:shelf_mushroom}.
     */
    public static final TypedKey<BlockType> SHELF_MUSHROOM = create("shelf_mushroom");

    /**
     * Key for {@code minecraft:short_dry_grass}.
     */
    public static final TypedKey<BlockType> SHORT_DRY_GRASS = create("short_dry_grass");

    /**
     * Key for {@code minecraft:short_grass}.
     */
    public static final TypedKey<BlockType> SHORT_GRASS = create("short_grass");

    /**
     * Key for {@code minecraft:shroomlight}.
     */
    public static final TypedKey<BlockType> SHROOMLIGHT = create("shroomlight");

    /**
     * Key for {@code minecraft:shulker_box}.
     */
    public static final TypedKey<BlockType> SHULKER_BOX = create("shulker_box");

    /**
     * Key for {@code minecraft:skeleton_skull}.
     */
    public static final TypedKey<BlockType> SKELETON_SKULL = create("skeleton_skull");

    /**
     * Key for {@code minecraft:skeleton_wall_skull}.
     */
    public static final TypedKey<BlockType> SKELETON_WALL_SKULL = create("skeleton_wall_skull");

    /**
     * Key for {@code minecraft:slime_block}.
     */
    public static final TypedKey<BlockType> SLIME_BLOCK = create("slime_block");

    /**
     * Key for {@code minecraft:small_amethyst_bud}.
     */
    public static final TypedKey<BlockType> SMALL_AMETHYST_BUD = create("small_amethyst_bud");

    /**
     * Key for {@code minecraft:small_dripleaf}.
     */
    public static final TypedKey<BlockType> SMALL_DRIPLEAF = create("small_dripleaf");

    /**
     * Key for {@code minecraft:smithing_table}.
     */
    public static final TypedKey<BlockType> SMITHING_TABLE = create("smithing_table");

    /**
     * Key for {@code minecraft:smoker}.
     */
    public static final TypedKey<BlockType> SMOKER = create("smoker");

    /**
     * Key for {@code minecraft:smooth_basalt}.
     */
    public static final TypedKey<BlockType> SMOOTH_BASALT = create("smooth_basalt");

    /**
     * Key for {@code minecraft:smooth_quartz}.
     */
    public static final TypedKey<BlockType> SMOOTH_QUARTZ = create("smooth_quartz");

    /**
     * Key for {@code minecraft:smooth_quartz_slab}.
     */
    public static final TypedKey<BlockType> SMOOTH_QUARTZ_SLAB = create("smooth_quartz_slab");

    /**
     * Key for {@code minecraft:smooth_quartz_stairs}.
     */
    public static final TypedKey<BlockType> SMOOTH_QUARTZ_STAIRS = create("smooth_quartz_stairs");

    /**
     * Key for {@code minecraft:smooth_red_sandstone}.
     */
    public static final TypedKey<BlockType> SMOOTH_RED_SANDSTONE = create("smooth_red_sandstone");

    /**
     * Key for {@code minecraft:smooth_red_sandstone_slab}.
     */
    public static final TypedKey<BlockType> SMOOTH_RED_SANDSTONE_SLAB = create("smooth_red_sandstone_slab");

    /**
     * Key for {@code minecraft:smooth_red_sandstone_stairs}.
     */
    public static final TypedKey<BlockType> SMOOTH_RED_SANDSTONE_STAIRS = create("smooth_red_sandstone_stairs");

    /**
     * Key for {@code minecraft:smooth_sandstone}.
     */
    public static final TypedKey<BlockType> SMOOTH_SANDSTONE = create("smooth_sandstone");

    /**
     * Key for {@code minecraft:smooth_sandstone_slab}.
     */
    public static final TypedKey<BlockType> SMOOTH_SANDSTONE_SLAB = create("smooth_sandstone_slab");

    /**
     * Key for {@code minecraft:smooth_sandstone_stairs}.
     */
    public static final TypedKey<BlockType> SMOOTH_SANDSTONE_STAIRS = create("smooth_sandstone_stairs");

    /**
     * Key for {@code minecraft:smooth_stone}.
     */
    public static final TypedKey<BlockType> SMOOTH_STONE = create("smooth_stone");

    /**
     * Key for {@code minecraft:smooth_stone_slab}.
     */
    public static final TypedKey<BlockType> SMOOTH_STONE_SLAB = create("smooth_stone_slab");

    /**
     * Key for {@code minecraft:sniffer_egg}.
     */
    public static final TypedKey<BlockType> SNIFFER_EGG = create("sniffer_egg");

    /**
     * Key for {@code minecraft:snow}.
     */
    public static final TypedKey<BlockType> SNOW = create("snow");

    /**
     * Key for {@code minecraft:snow_block}.
     */
    public static final TypedKey<BlockType> SNOW_BLOCK = create("snow_block");

    /**
     * Key for {@code minecraft:soul_campfire}.
     */
    public static final TypedKey<BlockType> SOUL_CAMPFIRE = create("soul_campfire");

    /**
     * Key for {@code minecraft:soul_fire}.
     */
    public static final TypedKey<BlockType> SOUL_FIRE = create("soul_fire");

    /**
     * Key for {@code minecraft:soul_lantern}.
     */
    public static final TypedKey<BlockType> SOUL_LANTERN = create("soul_lantern");

    /**
     * Key for {@code minecraft:soul_sand}.
     */
    public static final TypedKey<BlockType> SOUL_SAND = create("soul_sand");

    /**
     * Key for {@code minecraft:soul_soil}.
     */
    public static final TypedKey<BlockType> SOUL_SOIL = create("soul_soil");

    /**
     * Key for {@code minecraft:soul_torch}.
     */
    public static final TypedKey<BlockType> SOUL_TORCH = create("soul_torch");

    /**
     * Key for {@code minecraft:soul_wall_torch}.
     */
    public static final TypedKey<BlockType> SOUL_WALL_TORCH = create("soul_wall_torch");

    /**
     * Key for {@code minecraft:spawner}.
     */
    public static final TypedKey<BlockType> SPAWNER = create("spawner");

    /**
     * Key for {@code minecraft:sponge}.
     */
    public static final TypedKey<BlockType> SPONGE = create("sponge");

    /**
     * Key for {@code minecraft:spore_blossom}.
     */
    public static final TypedKey<BlockType> SPORE_BLOSSOM = create("spore_blossom");

    /**
     * Key for {@code minecraft:spruce_button}.
     */
    public static final TypedKey<BlockType> SPRUCE_BUTTON = create("spruce_button");

    /**
     * Key for {@code minecraft:spruce_door}.
     */
    public static final TypedKey<BlockType> SPRUCE_DOOR = create("spruce_door");

    /**
     * Key for {@code minecraft:spruce_fence}.
     */
    public static final TypedKey<BlockType> SPRUCE_FENCE = create("spruce_fence");

    /**
     * Key for {@code minecraft:spruce_fence_gate}.
     */
    public static final TypedKey<BlockType> SPRUCE_FENCE_GATE = create("spruce_fence_gate");

    /**
     * Key for {@code minecraft:spruce_hanging_sign}.
     */
    public static final TypedKey<BlockType> SPRUCE_HANGING_SIGN = create("spruce_hanging_sign");

    /**
     * Key for {@code minecraft:spruce_leaves}.
     */
    public static final TypedKey<BlockType> SPRUCE_LEAVES = create("spruce_leaves");

    /**
     * Key for {@code minecraft:spruce_log}.
     */
    public static final TypedKey<BlockType> SPRUCE_LOG = create("spruce_log");

    /**
     * Key for {@code minecraft:spruce_planks}.
     */
    public static final TypedKey<BlockType> SPRUCE_PLANKS = create("spruce_planks");

    /**
     * Key for {@code minecraft:spruce_pressure_plate}.
     */
    public static final TypedKey<BlockType> SPRUCE_PRESSURE_PLATE = create("spruce_pressure_plate");

    /**
     * Key for {@code minecraft:spruce_sapling}.
     */
    public static final TypedKey<BlockType> SPRUCE_SAPLING = create("spruce_sapling");

    /**
     * Key for {@code minecraft:spruce_shelf}.
     */
    public static final TypedKey<BlockType> SPRUCE_SHELF = create("spruce_shelf");

    /**
     * Key for {@code minecraft:spruce_sign}.
     */
    public static final TypedKey<BlockType> SPRUCE_SIGN = create("spruce_sign");

    /**
     * Key for {@code minecraft:spruce_slab}.
     */
    public static final TypedKey<BlockType> SPRUCE_SLAB = create("spruce_slab");

    /**
     * Key for {@code minecraft:spruce_stairs}.
     */
    public static final TypedKey<BlockType> SPRUCE_STAIRS = create("spruce_stairs");

    /**
     * Key for {@code minecraft:spruce_trapdoor}.
     */
    public static final TypedKey<BlockType> SPRUCE_TRAPDOOR = create("spruce_trapdoor");

    /**
     * Key for {@code minecraft:spruce_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> SPRUCE_WALL_HANGING_SIGN = create("spruce_wall_hanging_sign");

    /**
     * Key for {@code minecraft:spruce_wall_sign}.
     */
    public static final TypedKey<BlockType> SPRUCE_WALL_SIGN = create("spruce_wall_sign");

    /**
     * Key for {@code minecraft:spruce_wood}.
     */
    public static final TypedKey<BlockType> SPRUCE_WOOD = create("spruce_wood");

    /**
     * Key for {@code minecraft:sticky_piston}.
     */
    public static final TypedKey<BlockType> STICKY_PISTON = create("sticky_piston");

    /**
     * Key for {@code minecraft:stone}.
     */
    public static final TypedKey<BlockType> STONE = create("stone");

    /**
     * Key for {@code minecraft:stone_brick_slab}.
     */
    public static final TypedKey<BlockType> STONE_BRICK_SLAB = create("stone_brick_slab");

    /**
     * Key for {@code minecraft:stone_brick_stairs}.
     */
    public static final TypedKey<BlockType> STONE_BRICK_STAIRS = create("stone_brick_stairs");

    /**
     * Key for {@code minecraft:stone_brick_wall}.
     */
    public static final TypedKey<BlockType> STONE_BRICK_WALL = create("stone_brick_wall");

    /**
     * Key for {@code minecraft:stone_bricks}.
     */
    public static final TypedKey<BlockType> STONE_BRICKS = create("stone_bricks");

    /**
     * Key for {@code minecraft:stone_button}.
     */
    public static final TypedKey<BlockType> STONE_BUTTON = create("stone_button");

    /**
     * Key for {@code minecraft:stone_pressure_plate}.
     */
    public static final TypedKey<BlockType> STONE_PRESSURE_PLATE = create("stone_pressure_plate");

    /**
     * Key for {@code minecraft:stone_slab}.
     */
    public static final TypedKey<BlockType> STONE_SLAB = create("stone_slab");

    /**
     * Key for {@code minecraft:stone_stairs}.
     */
    public static final TypedKey<BlockType> STONE_STAIRS = create("stone_stairs");

    /**
     * Key for {@code minecraft:stonecutter}.
     */
    public static final TypedKey<BlockType> STONECUTTER = create("stonecutter");

    /**
     * Key for {@code minecraft:straw_bed}.
     */
    public static final TypedKey<BlockType> STRAW_BED = create("straw_bed");

    /**
     * Key for {@code minecraft:stripped_acacia_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_ACACIA_LOG = create("stripped_acacia_log");

    /**
     * Key for {@code minecraft:stripped_acacia_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_ACACIA_WOOD = create("stripped_acacia_wood");

    /**
     * Key for {@code minecraft:stripped_bamboo_block}.
     */
    public static final TypedKey<BlockType> STRIPPED_BAMBOO_BLOCK = create("stripped_bamboo_block");

    /**
     * Key for {@code minecraft:stripped_birch_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_BIRCH_LOG = create("stripped_birch_log");

    /**
     * Key for {@code minecraft:stripped_birch_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_BIRCH_WOOD = create("stripped_birch_wood");

    /**
     * Key for {@code minecraft:stripped_cherry_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_CHERRY_LOG = create("stripped_cherry_log");

    /**
     * Key for {@code minecraft:stripped_cherry_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_CHERRY_WOOD = create("stripped_cherry_wood");

    /**
     * Key for {@code minecraft:stripped_crimson_hyphae}.
     */
    public static final TypedKey<BlockType> STRIPPED_CRIMSON_HYPHAE = create("stripped_crimson_hyphae");

    /**
     * Key for {@code minecraft:stripped_crimson_stem}.
     */
    public static final TypedKey<BlockType> STRIPPED_CRIMSON_STEM = create("stripped_crimson_stem");

    /**
     * Key for {@code minecraft:stripped_dark_oak_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_DARK_OAK_LOG = create("stripped_dark_oak_log");

    /**
     * Key for {@code minecraft:stripped_dark_oak_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_DARK_OAK_WOOD = create("stripped_dark_oak_wood");

    /**
     * Key for {@code minecraft:stripped_jungle_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_JUNGLE_LOG = create("stripped_jungle_log");

    /**
     * Key for {@code minecraft:stripped_jungle_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_JUNGLE_WOOD = create("stripped_jungle_wood");

    /**
     * Key for {@code minecraft:stripped_mangrove_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_MANGROVE_LOG = create("stripped_mangrove_log");

    /**
     * Key for {@code minecraft:stripped_mangrove_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_MANGROVE_WOOD = create("stripped_mangrove_wood");

    /**
     * Key for {@code minecraft:stripped_oak_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_OAK_LOG = create("stripped_oak_log");

    /**
     * Key for {@code minecraft:stripped_oak_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_OAK_WOOD = create("stripped_oak_wood");

    /**
     * Key for {@code minecraft:stripped_pale_oak_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_PALE_OAK_LOG = create("stripped_pale_oak_log");

    /**
     * Key for {@code minecraft:stripped_pale_oak_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_PALE_OAK_WOOD = create("stripped_pale_oak_wood");

    /**
     * Key for {@code minecraft:stripped_poplar_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_POPLAR_LOG = create("stripped_poplar_log");

    /**
     * Key for {@code minecraft:stripped_poplar_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_POPLAR_WOOD = create("stripped_poplar_wood");

    /**
     * Key for {@code minecraft:stripped_spruce_log}.
     */
    public static final TypedKey<BlockType> STRIPPED_SPRUCE_LOG = create("stripped_spruce_log");

    /**
     * Key for {@code minecraft:stripped_spruce_wood}.
     */
    public static final TypedKey<BlockType> STRIPPED_SPRUCE_WOOD = create("stripped_spruce_wood");

    /**
     * Key for {@code minecraft:stripped_warped_hyphae}.
     */
    public static final TypedKey<BlockType> STRIPPED_WARPED_HYPHAE = create("stripped_warped_hyphae");

    /**
     * Key for {@code minecraft:stripped_warped_stem}.
     */
    public static final TypedKey<BlockType> STRIPPED_WARPED_STEM = create("stripped_warped_stem");

    /**
     * Key for {@code minecraft:structure_block}.
     */
    public static final TypedKey<BlockType> STRUCTURE_BLOCK = create("structure_block");

    /**
     * Key for {@code minecraft:structure_void}.
     */
    public static final TypedKey<BlockType> STRUCTURE_VOID = create("structure_void");

    /**
     * Key for {@code minecraft:sugar_cane}.
     */
    public static final TypedKey<BlockType> SUGAR_CANE = create("sugar_cane");

    /**
     * Key for {@code minecraft:sulfur}.
     */
    public static final TypedKey<BlockType> SULFUR = create("sulfur");

    /**
     * Key for {@code minecraft:sulfur_brick_slab}.
     */
    public static final TypedKey<BlockType> SULFUR_BRICK_SLAB = create("sulfur_brick_slab");

    /**
     * Key for {@code minecraft:sulfur_brick_stairs}.
     */
    public static final TypedKey<BlockType> SULFUR_BRICK_STAIRS = create("sulfur_brick_stairs");

    /**
     * Key for {@code minecraft:sulfur_brick_wall}.
     */
    public static final TypedKey<BlockType> SULFUR_BRICK_WALL = create("sulfur_brick_wall");

    /**
     * Key for {@code minecraft:sulfur_bricks}.
     */
    public static final TypedKey<BlockType> SULFUR_BRICKS = create("sulfur_bricks");

    /**
     * Key for {@code minecraft:sulfur_slab}.
     */
    public static final TypedKey<BlockType> SULFUR_SLAB = create("sulfur_slab");

    /**
     * Key for {@code minecraft:sulfur_spike}.
     */
    public static final TypedKey<BlockType> SULFUR_SPIKE = create("sulfur_spike");

    /**
     * Key for {@code minecraft:sulfur_stairs}.
     */
    public static final TypedKey<BlockType> SULFUR_STAIRS = create("sulfur_stairs");

    /**
     * Key for {@code minecraft:sulfur_wall}.
     */
    public static final TypedKey<BlockType> SULFUR_WALL = create("sulfur_wall");

    /**
     * Key for {@code minecraft:sunflower}.
     */
    public static final TypedKey<BlockType> SUNFLOWER = create("sunflower");

    /**
     * Key for {@code minecraft:suspicious_gravel}.
     */
    public static final TypedKey<BlockType> SUSPICIOUS_GRAVEL = create("suspicious_gravel");

    /**
     * Key for {@code minecraft:suspicious_sand}.
     */
    public static final TypedKey<BlockType> SUSPICIOUS_SAND = create("suspicious_sand");

    /**
     * Key for {@code minecraft:sweet_berry_bush}.
     */
    public static final TypedKey<BlockType> SWEET_BERRY_BUSH = create("sweet_berry_bush");

    /**
     * Key for {@code minecraft:tall_dry_grass}.
     */
    public static final TypedKey<BlockType> TALL_DRY_GRASS = create("tall_dry_grass");

    /**
     * Key for {@code minecraft:tall_grass}.
     */
    public static final TypedKey<BlockType> TALL_GRASS = create("tall_grass");

    /**
     * Key for {@code minecraft:tall_seagrass}.
     */
    public static final TypedKey<BlockType> TALL_SEAGRASS = create("tall_seagrass");

    /**
     * Key for {@code minecraft:target}.
     */
    public static final TypedKey<BlockType> TARGET = create("target");

    /**
     * Key for {@code minecraft:terracotta}.
     */
    public static final TypedKey<BlockType> TERRACOTTA = create("terracotta");

    /**
     * Key for {@code minecraft:test_block}.
     */
    public static final TypedKey<BlockType> TEST_BLOCK = create("test_block");

    /**
     * Key for {@code minecraft:test_instance_block}.
     */
    public static final TypedKey<BlockType> TEST_INSTANCE_BLOCK = create("test_instance_block");

    /**
     * Key for {@code minecraft:tinted_glass}.
     */
    public static final TypedKey<BlockType> TINTED_GLASS = create("tinted_glass");

    /**
     * Key for {@code minecraft:tnt}.
     */
    public static final TypedKey<BlockType> TNT = create("tnt");

    /**
     * Key for {@code minecraft:torch}.
     */
    public static final TypedKey<BlockType> TORCH = create("torch");

    /**
     * Key for {@code minecraft:torchflower}.
     */
    public static final TypedKey<BlockType> TORCHFLOWER = create("torchflower");

    /**
     * Key for {@code minecraft:torchflower_crop}.
     */
    public static final TypedKey<BlockType> TORCHFLOWER_CROP = create("torchflower_crop");

    /**
     * Key for {@code minecraft:trapped_chest}.
     */
    public static final TypedKey<BlockType> TRAPPED_CHEST = create("trapped_chest");

    /**
     * Key for {@code minecraft:trial_spawner}.
     */
    public static final TypedKey<BlockType> TRIAL_SPAWNER = create("trial_spawner");

    /**
     * Key for {@code minecraft:tripwire}.
     */
    public static final TypedKey<BlockType> TRIPWIRE = create("tripwire");

    /**
     * Key for {@code minecraft:tripwire_hook}.
     */
    public static final TypedKey<BlockType> TRIPWIRE_HOOK = create("tripwire_hook");

    /**
     * Key for {@code minecraft:tube_coral}.
     */
    public static final TypedKey<BlockType> TUBE_CORAL = create("tube_coral");

    /**
     * Key for {@code minecraft:tube_coral_block}.
     */
    public static final TypedKey<BlockType> TUBE_CORAL_BLOCK = create("tube_coral_block");

    /**
     * Key for {@code minecraft:tube_coral_fan}.
     */
    public static final TypedKey<BlockType> TUBE_CORAL_FAN = create("tube_coral_fan");

    /**
     * Key for {@code minecraft:tube_coral_wall_fan}.
     */
    public static final TypedKey<BlockType> TUBE_CORAL_WALL_FAN = create("tube_coral_wall_fan");

    /**
     * Key for {@code minecraft:tuff}.
     */
    public static final TypedKey<BlockType> TUFF = create("tuff");

    /**
     * Key for {@code minecraft:tuff_brick_slab}.
     */
    public static final TypedKey<BlockType> TUFF_BRICK_SLAB = create("tuff_brick_slab");

    /**
     * Key for {@code minecraft:tuff_brick_stairs}.
     */
    public static final TypedKey<BlockType> TUFF_BRICK_STAIRS = create("tuff_brick_stairs");

    /**
     * Key for {@code minecraft:tuff_brick_wall}.
     */
    public static final TypedKey<BlockType> TUFF_BRICK_WALL = create("tuff_brick_wall");

    /**
     * Key for {@code minecraft:tuff_bricks}.
     */
    public static final TypedKey<BlockType> TUFF_BRICKS = create("tuff_bricks");

    /**
     * Key for {@code minecraft:tuff_slab}.
     */
    public static final TypedKey<BlockType> TUFF_SLAB = create("tuff_slab");

    /**
     * Key for {@code minecraft:tuff_stairs}.
     */
    public static final TypedKey<BlockType> TUFF_STAIRS = create("tuff_stairs");

    /**
     * Key for {@code minecraft:tuff_wall}.
     */
    public static final TypedKey<BlockType> TUFF_WALL = create("tuff_wall");

    /**
     * Key for {@code minecraft:turtle_egg}.
     */
    public static final TypedKey<BlockType> TURTLE_EGG = create("turtle_egg");

    /**
     * Key for {@code minecraft:twisting_vines}.
     */
    public static final TypedKey<BlockType> TWISTING_VINES = create("twisting_vines");

    /**
     * Key for {@code minecraft:twisting_vines_plant}.
     */
    public static final TypedKey<BlockType> TWISTING_VINES_PLANT = create("twisting_vines_plant");

    /**
     * Key for {@code minecraft:vault}.
     */
    public static final TypedKey<BlockType> VAULT = create("vault");

    /**
     * Key for {@code minecraft:verdant_froglight}.
     */
    public static final TypedKey<BlockType> VERDANT_FROGLIGHT = create("verdant_froglight");

    /**
     * Key for {@code minecraft:vine}.
     */
    public static final TypedKey<BlockType> VINE = create("vine");

    /**
     * Key for {@code minecraft:void_air}.
     */
    public static final TypedKey<BlockType> VOID_AIR = create("void_air");

    /**
     * Key for {@code minecraft:wall_torch}.
     */
    public static final TypedKey<BlockType> WALL_TORCH = create("wall_torch");

    /**
     * Key for {@code minecraft:warped_button}.
     */
    public static final TypedKey<BlockType> WARPED_BUTTON = create("warped_button");

    /**
     * Key for {@code minecraft:warped_door}.
     */
    public static final TypedKey<BlockType> WARPED_DOOR = create("warped_door");

    /**
     * Key for {@code minecraft:warped_fence}.
     */
    public static final TypedKey<BlockType> WARPED_FENCE = create("warped_fence");

    /**
     * Key for {@code minecraft:warped_fence_gate}.
     */
    public static final TypedKey<BlockType> WARPED_FENCE_GATE = create("warped_fence_gate");

    /**
     * Key for {@code minecraft:warped_fungus}.
     */
    public static final TypedKey<BlockType> WARPED_FUNGUS = create("warped_fungus");

    /**
     * Key for {@code minecraft:warped_hanging_sign}.
     */
    public static final TypedKey<BlockType> WARPED_HANGING_SIGN = create("warped_hanging_sign");

    /**
     * Key for {@code minecraft:warped_hyphae}.
     */
    public static final TypedKey<BlockType> WARPED_HYPHAE = create("warped_hyphae");

    /**
     * Key for {@code minecraft:warped_nylium}.
     */
    public static final TypedKey<BlockType> WARPED_NYLIUM = create("warped_nylium");

    /**
     * Key for {@code minecraft:warped_planks}.
     */
    public static final TypedKey<BlockType> WARPED_PLANKS = create("warped_planks");

    /**
     * Key for {@code minecraft:warped_pressure_plate}.
     */
    public static final TypedKey<BlockType> WARPED_PRESSURE_PLATE = create("warped_pressure_plate");

    /**
     * Key for {@code minecraft:warped_roots}.
     */
    public static final TypedKey<BlockType> WARPED_ROOTS = create("warped_roots");

    /**
     * Key for {@code minecraft:warped_shelf}.
     */
    public static final TypedKey<BlockType> WARPED_SHELF = create("warped_shelf");

    /**
     * Key for {@code minecraft:warped_sign}.
     */
    public static final TypedKey<BlockType> WARPED_SIGN = create("warped_sign");

    /**
     * Key for {@code minecraft:warped_slab}.
     */
    public static final TypedKey<BlockType> WARPED_SLAB = create("warped_slab");

    /**
     * Key for {@code minecraft:warped_stairs}.
     */
    public static final TypedKey<BlockType> WARPED_STAIRS = create("warped_stairs");

    /**
     * Key for {@code minecraft:warped_stem}.
     */
    public static final TypedKey<BlockType> WARPED_STEM = create("warped_stem");

    /**
     * Key for {@code minecraft:warped_trapdoor}.
     */
    public static final TypedKey<BlockType> WARPED_TRAPDOOR = create("warped_trapdoor");

    /**
     * Key for {@code minecraft:warped_wall_hanging_sign}.
     */
    public static final TypedKey<BlockType> WARPED_WALL_HANGING_SIGN = create("warped_wall_hanging_sign");

    /**
     * Key for {@code minecraft:warped_wall_sign}.
     */
    public static final TypedKey<BlockType> WARPED_WALL_SIGN = create("warped_wall_sign");

    /**
     * Key for {@code minecraft:warped_wart_block}.
     */
    public static final TypedKey<BlockType> WARPED_WART_BLOCK = create("warped_wart_block");

    /**
     * Key for {@code minecraft:water}.
     */
    public static final TypedKey<BlockType> WATER = create("water");

    /**
     * Key for {@code minecraft:water_cauldron}.
     */
    public static final TypedKey<BlockType> WATER_CAULDRON = create("water_cauldron");

    /**
     * Key for {@code minecraft:waxed_chiseled_copper}.
     */
    public static final TypedKey<BlockType> WAXED_CHISELED_COPPER = create("waxed_chiseled_copper");

    /**
     * Key for {@code minecraft:waxed_copper_bars}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_BARS = create("waxed_copper_bars");

    /**
     * Key for {@code minecraft:waxed_copper_block}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_BLOCK = create("waxed_copper_block");

    /**
     * Key for {@code minecraft:waxed_copper_bulb}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_BULB = create("waxed_copper_bulb");

    /**
     * Key for {@code minecraft:waxed_copper_chain}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_CHAIN = create("waxed_copper_chain");

    /**
     * Key for {@code minecraft:waxed_copper_chest}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_CHEST = create("waxed_copper_chest");

    /**
     * Key for {@code minecraft:waxed_copper_door}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_DOOR = create("waxed_copper_door");

    /**
     * Key for {@code minecraft:waxed_copper_golem_statue}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_GOLEM_STATUE = create("waxed_copper_golem_statue");

    /**
     * Key for {@code minecraft:waxed_copper_grate}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_GRATE = create("waxed_copper_grate");

    /**
     * Key for {@code minecraft:waxed_copper_lantern}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_LANTERN = create("waxed_copper_lantern");

    /**
     * Key for {@code minecraft:waxed_copper_trapdoor}.
     */
    public static final TypedKey<BlockType> WAXED_COPPER_TRAPDOOR = create("waxed_copper_trapdoor");

    /**
     * Key for {@code minecraft:waxed_cut_copper}.
     */
    public static final TypedKey<BlockType> WAXED_CUT_COPPER = create("waxed_cut_copper");

    /**
     * Key for {@code minecraft:waxed_cut_copper_slab}.
     */
    public static final TypedKey<BlockType> WAXED_CUT_COPPER_SLAB = create("waxed_cut_copper_slab");

    /**
     * Key for {@code minecraft:waxed_cut_copper_stairs}.
     */
    public static final TypedKey<BlockType> WAXED_CUT_COPPER_STAIRS = create("waxed_cut_copper_stairs");

    /**
     * Key for {@code minecraft:waxed_exposed_chiseled_copper}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_CHISELED_COPPER = create("waxed_exposed_chiseled_copper");

    /**
     * Key for {@code minecraft:waxed_exposed_copper}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER = create("waxed_exposed_copper");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_bars}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_BARS = create("waxed_exposed_copper_bars");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_bulb}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_BULB = create("waxed_exposed_copper_bulb");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_chain}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_CHAIN = create("waxed_exposed_copper_chain");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_chest}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_CHEST = create("waxed_exposed_copper_chest");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_door}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_DOOR = create("waxed_exposed_copper_door");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_golem_statue}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_GOLEM_STATUE = create("waxed_exposed_copper_golem_statue");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_grate}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_GRATE = create("waxed_exposed_copper_grate");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_lantern}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_LANTERN = create("waxed_exposed_copper_lantern");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_trapdoor}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_COPPER_TRAPDOOR = create("waxed_exposed_copper_trapdoor");

    /**
     * Key for {@code minecraft:waxed_exposed_cut_copper}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_CUT_COPPER = create("waxed_exposed_cut_copper");

    /**
     * Key for {@code minecraft:waxed_exposed_cut_copper_slab}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_CUT_COPPER_SLAB = create("waxed_exposed_cut_copper_slab");

    /**
     * Key for {@code minecraft:waxed_exposed_cut_copper_stairs}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_CUT_COPPER_STAIRS = create("waxed_exposed_cut_copper_stairs");

    /**
     * Key for {@code minecraft:waxed_exposed_lightning_rod}.
     */
    public static final TypedKey<BlockType> WAXED_EXPOSED_LIGHTNING_ROD = create("waxed_exposed_lightning_rod");

    /**
     * Key for {@code minecraft:waxed_lightning_rod}.
     */
    public static final TypedKey<BlockType> WAXED_LIGHTNING_ROD = create("waxed_lightning_rod");

    /**
     * Key for {@code minecraft:waxed_oxidized_chiseled_copper}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_CHISELED_COPPER = create("waxed_oxidized_chiseled_copper");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER = create("waxed_oxidized_copper");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_bars}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_BARS = create("waxed_oxidized_copper_bars");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_bulb}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_BULB = create("waxed_oxidized_copper_bulb");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_chain}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_CHAIN = create("waxed_oxidized_copper_chain");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_chest}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_CHEST = create("waxed_oxidized_copper_chest");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_door}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_DOOR = create("waxed_oxidized_copper_door");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_golem_statue}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_GOLEM_STATUE = create("waxed_oxidized_copper_golem_statue");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_grate}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_GRATE = create("waxed_oxidized_copper_grate");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_lantern}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_LANTERN = create("waxed_oxidized_copper_lantern");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_trapdoor}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_COPPER_TRAPDOOR = create("waxed_oxidized_copper_trapdoor");

    /**
     * Key for {@code minecraft:waxed_oxidized_cut_copper}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_CUT_COPPER = create("waxed_oxidized_cut_copper");

    /**
     * Key for {@code minecraft:waxed_oxidized_cut_copper_slab}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_CUT_COPPER_SLAB = create("waxed_oxidized_cut_copper_slab");

    /**
     * Key for {@code minecraft:waxed_oxidized_cut_copper_stairs}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_CUT_COPPER_STAIRS = create("waxed_oxidized_cut_copper_stairs");

    /**
     * Key for {@code minecraft:waxed_oxidized_lightning_rod}.
     */
    public static final TypedKey<BlockType> WAXED_OXIDIZED_LIGHTNING_ROD = create("waxed_oxidized_lightning_rod");

    /**
     * Key for {@code minecraft:waxed_weathered_chiseled_copper}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_CHISELED_COPPER = create("waxed_weathered_chiseled_copper");

    /**
     * Key for {@code minecraft:waxed_weathered_copper}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER = create("waxed_weathered_copper");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_bars}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_BARS = create("waxed_weathered_copper_bars");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_bulb}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_BULB = create("waxed_weathered_copper_bulb");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_chain}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_CHAIN = create("waxed_weathered_copper_chain");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_chest}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_CHEST = create("waxed_weathered_copper_chest");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_door}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_DOOR = create("waxed_weathered_copper_door");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_golem_statue}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_GOLEM_STATUE = create("waxed_weathered_copper_golem_statue");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_grate}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_GRATE = create("waxed_weathered_copper_grate");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_lantern}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_LANTERN = create("waxed_weathered_copper_lantern");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_trapdoor}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_COPPER_TRAPDOOR = create("waxed_weathered_copper_trapdoor");

    /**
     * Key for {@code minecraft:waxed_weathered_cut_copper}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_CUT_COPPER = create("waxed_weathered_cut_copper");

    /**
     * Key for {@code minecraft:waxed_weathered_cut_copper_slab}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_CUT_COPPER_SLAB = create("waxed_weathered_cut_copper_slab");

    /**
     * Key for {@code minecraft:waxed_weathered_cut_copper_stairs}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_CUT_COPPER_STAIRS = create("waxed_weathered_cut_copper_stairs");

    /**
     * Key for {@code minecraft:waxed_weathered_lightning_rod}.
     */
    public static final TypedKey<BlockType> WAXED_WEATHERED_LIGHTNING_ROD = create("waxed_weathered_lightning_rod");

    /**
     * Key for {@code minecraft:weathered_chiseled_copper}.
     */
    public static final TypedKey<BlockType> WEATHERED_CHISELED_COPPER = create("weathered_chiseled_copper");

    /**
     * Key for {@code minecraft:weathered_copper}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER = create("weathered_copper");

    /**
     * Key for {@code minecraft:weathered_copper_bars}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_BARS = create("weathered_copper_bars");

    /**
     * Key for {@code minecraft:weathered_copper_bulb}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_BULB = create("weathered_copper_bulb");

    /**
     * Key for {@code minecraft:weathered_copper_chain}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_CHAIN = create("weathered_copper_chain");

    /**
     * Key for {@code minecraft:weathered_copper_chest}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_CHEST = create("weathered_copper_chest");

    /**
     * Key for {@code minecraft:weathered_copper_door}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_DOOR = create("weathered_copper_door");

    /**
     * Key for {@code minecraft:weathered_copper_golem_statue}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_GOLEM_STATUE = create("weathered_copper_golem_statue");

    /**
     * Key for {@code minecraft:weathered_copper_grate}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_GRATE = create("weathered_copper_grate");

    /**
     * Key for {@code minecraft:weathered_copper_lantern}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_LANTERN = create("weathered_copper_lantern");

    /**
     * Key for {@code minecraft:weathered_copper_trapdoor}.
     */
    public static final TypedKey<BlockType> WEATHERED_COPPER_TRAPDOOR = create("weathered_copper_trapdoor");

    /**
     * Key for {@code minecraft:weathered_cut_copper}.
     */
    public static final TypedKey<BlockType> WEATHERED_CUT_COPPER = create("weathered_cut_copper");

    /**
     * Key for {@code minecraft:weathered_cut_copper_slab}.
     */
    public static final TypedKey<BlockType> WEATHERED_CUT_COPPER_SLAB = create("weathered_cut_copper_slab");

    /**
     * Key for {@code minecraft:weathered_cut_copper_stairs}.
     */
    public static final TypedKey<BlockType> WEATHERED_CUT_COPPER_STAIRS = create("weathered_cut_copper_stairs");

    /**
     * Key for {@code minecraft:weathered_lightning_rod}.
     */
    public static final TypedKey<BlockType> WEATHERED_LIGHTNING_ROD = create("weathered_lightning_rod");

    /**
     * Key for {@code minecraft:weeping_vines}.
     */
    public static final TypedKey<BlockType> WEEPING_VINES = create("weeping_vines");

    /**
     * Key for {@code minecraft:weeping_vines_plant}.
     */
    public static final TypedKey<BlockType> WEEPING_VINES_PLANT = create("weeping_vines_plant");

    /**
     * Key for {@code minecraft:wet_sponge}.
     */
    public static final TypedKey<BlockType> WET_SPONGE = create("wet_sponge");

    /**
     * Key for {@code minecraft:wheat}.
     */
    public static final TypedKey<BlockType> WHEAT = create("wheat");

    /**
     * Key for {@code minecraft:white_banner}.
     */
    public static final TypedKey<BlockType> WHITE_BANNER = create("white_banner");

    /**
     * Key for {@code minecraft:white_bed}.
     */
    public static final TypedKey<BlockType> WHITE_BED = create("white_bed");

    /**
     * Key for {@code minecraft:white_candle}.
     */
    public static final TypedKey<BlockType> WHITE_CANDLE = create("white_candle");

    /**
     * Key for {@code minecraft:white_candle_cake}.
     */
    public static final TypedKey<BlockType> WHITE_CANDLE_CAKE = create("white_candle_cake");

    /**
     * Key for {@code minecraft:white_carpet}.
     */
    public static final TypedKey<BlockType> WHITE_CARPET = create("white_carpet");

    /**
     * Key for {@code minecraft:white_concrete}.
     */
    public static final TypedKey<BlockType> WHITE_CONCRETE = create("white_concrete");

    /**
     * Key for {@code minecraft:white_concrete_powder}.
     */
    public static final TypedKey<BlockType> WHITE_CONCRETE_POWDER = create("white_concrete_powder");

    /**
     * Key for {@code minecraft:white_concrete_slab}.
     */
    public static final TypedKey<BlockType> WHITE_CONCRETE_SLAB = create("white_concrete_slab");

    /**
     * Key for {@code minecraft:white_concrete_stairs}.
     */
    public static final TypedKey<BlockType> WHITE_CONCRETE_STAIRS = create("white_concrete_stairs");

    /**
     * Key for {@code minecraft:white_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> WHITE_GLAZED_TERRACOTTA = create("white_glazed_terracotta");

    /**
     * Key for {@code minecraft:white_shulker_box}.
     */
    public static final TypedKey<BlockType> WHITE_SHULKER_BOX = create("white_shulker_box");

    /**
     * Key for {@code minecraft:white_stained_glass}.
     */
    public static final TypedKey<BlockType> WHITE_STAINED_GLASS = create("white_stained_glass");

    /**
     * Key for {@code minecraft:white_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> WHITE_STAINED_GLASS_PANE = create("white_stained_glass_pane");

    /**
     * Key for {@code minecraft:white_terracotta}.
     */
    public static final TypedKey<BlockType> WHITE_TERRACOTTA = create("white_terracotta");

    /**
     * Key for {@code minecraft:white_tulip}.
     */
    public static final TypedKey<BlockType> WHITE_TULIP = create("white_tulip");

    /**
     * Key for {@code minecraft:white_wall_banner}.
     */
    public static final TypedKey<BlockType> WHITE_WALL_BANNER = create("white_wall_banner");

    /**
     * Key for {@code minecraft:white_wool}.
     */
    public static final TypedKey<BlockType> WHITE_WOOL = create("white_wool");

    /**
     * Key for {@code minecraft:white_wool_slab}.
     */
    public static final TypedKey<BlockType> WHITE_WOOL_SLAB = create("white_wool_slab");

    /**
     * Key for {@code minecraft:white_wool_stairs}.
     */
    public static final TypedKey<BlockType> WHITE_WOOL_STAIRS = create("white_wool_stairs");

    /**
     * Key for {@code minecraft:wildflowers}.
     */
    public static final TypedKey<BlockType> WILDFLOWERS = create("wildflowers");

    /**
     * Key for {@code minecraft:wither_rose}.
     */
    public static final TypedKey<BlockType> WITHER_ROSE = create("wither_rose");

    /**
     * Key for {@code minecraft:wither_skeleton_skull}.
     */
    public static final TypedKey<BlockType> WITHER_SKELETON_SKULL = create("wither_skeleton_skull");

    /**
     * Key for {@code minecraft:wither_skeleton_wall_skull}.
     */
    public static final TypedKey<BlockType> WITHER_SKELETON_WALL_SKULL = create("wither_skeleton_wall_skull");

    /**
     * Key for {@code minecraft:yellow_banner}.
     */
    public static final TypedKey<BlockType> YELLOW_BANNER = create("yellow_banner");

    /**
     * Key for {@code minecraft:yellow_bed}.
     */
    public static final TypedKey<BlockType> YELLOW_BED = create("yellow_bed");

    /**
     * Key for {@code minecraft:yellow_candle}.
     */
    public static final TypedKey<BlockType> YELLOW_CANDLE = create("yellow_candle");

    /**
     * Key for {@code minecraft:yellow_candle_cake}.
     */
    public static final TypedKey<BlockType> YELLOW_CANDLE_CAKE = create("yellow_candle_cake");

    /**
     * Key for {@code minecraft:yellow_carpet}.
     */
    public static final TypedKey<BlockType> YELLOW_CARPET = create("yellow_carpet");

    /**
     * Key for {@code minecraft:yellow_concrete}.
     */
    public static final TypedKey<BlockType> YELLOW_CONCRETE = create("yellow_concrete");

    /**
     * Key for {@code minecraft:yellow_concrete_powder}.
     */
    public static final TypedKey<BlockType> YELLOW_CONCRETE_POWDER = create("yellow_concrete_powder");

    /**
     * Key for {@code minecraft:yellow_concrete_slab}.
     */
    public static final TypedKey<BlockType> YELLOW_CONCRETE_SLAB = create("yellow_concrete_slab");

    /**
     * Key for {@code minecraft:yellow_concrete_stairs}.
     */
    public static final TypedKey<BlockType> YELLOW_CONCRETE_STAIRS = create("yellow_concrete_stairs");

    /**
     * Key for {@code minecraft:yellow_glazed_terracotta}.
     */
    public static final TypedKey<BlockType> YELLOW_GLAZED_TERRACOTTA = create("yellow_glazed_terracotta");

    /**
     * Key for {@code minecraft:yellow_poplar_leaves}.
     */
    public static final TypedKey<BlockType> YELLOW_POPLAR_LEAVES = create("yellow_poplar_leaves");

    /**
     * Key for {@code minecraft:yellow_shulker_box}.
     */
    public static final TypedKey<BlockType> YELLOW_SHULKER_BOX = create("yellow_shulker_box");

    /**
     * Key for {@code minecraft:yellow_stained_glass}.
     */
    public static final TypedKey<BlockType> YELLOW_STAINED_GLASS = create("yellow_stained_glass");

    /**
     * Key for {@code minecraft:yellow_stained_glass_pane}.
     */
    public static final TypedKey<BlockType> YELLOW_STAINED_GLASS_PANE = create("yellow_stained_glass_pane");

    /**
     * Key for {@code minecraft:yellow_terracotta}.
     */
    public static final TypedKey<BlockType> YELLOW_TERRACOTTA = create("yellow_terracotta");

    /**
     * Key for {@code minecraft:yellow_wall_banner}.
     */
    public static final TypedKey<BlockType> YELLOW_WALL_BANNER = create("yellow_wall_banner");

    /**
     * Key for {@code minecraft:yellow_wool}.
     */
    public static final TypedKey<BlockType> YELLOW_WOOL = create("yellow_wool");

    /**
     * Key for {@code minecraft:yellow_wool_slab}.
     */
    public static final TypedKey<BlockType> YELLOW_WOOL_SLAB = create("yellow_wool_slab");

    /**
     * Key for {@code minecraft:yellow_wool_stairs}.
     */
    public static final TypedKey<BlockType> YELLOW_WOOL_STAIRS = create("yellow_wool_stairs");

    /**
     * Key for {@code minecraft:zombie_head}.
     */
    public static final TypedKey<BlockType> ZOMBIE_HEAD = create("zombie_head");

    /**
     * Key for {@code minecraft:zombie_wall_head}.
     */
    public static final TypedKey<BlockType> ZOMBIE_WALL_HEAD = create("zombie_wall_head");

    private static final List<TypedKey<BlockType>> VALUES = List.of(
        ACACIA_BUTTON,
        ACACIA_DOOR,
        ACACIA_FENCE,
        ACACIA_FENCE_GATE,
        ACACIA_HANGING_SIGN,
        ACACIA_LEAVES,
        ACACIA_LOG,
        ACACIA_PLANKS,
        ACACIA_PRESSURE_PLATE,
        ACACIA_SAPLING,
        ACACIA_SHELF,
        ACACIA_SIGN,
        ACACIA_SLAB,
        ACACIA_STAIRS,
        ACACIA_TRAPDOOR,
        ACACIA_WALL_HANGING_SIGN,
        ACACIA_WALL_SIGN,
        ACACIA_WOOD,
        ACTIVATOR_RAIL,
        AIR,
        ALLIUM,
        AMETHYST_BLOCK,
        AMETHYST_CLUSTER,
        ANCIENT_DEBRIS,
        ANDESITE,
        ANDESITE_SLAB,
        ANDESITE_STAIRS,
        ANDESITE_WALL,
        ANVIL,
        ATTACHED_MELON_STEM,
        ATTACHED_PUMPKIN_STEM,
        AZALEA,
        AZALEA_LEAVES,
        AZURE_BLUET,
        BAMBOO,
        BAMBOO_BLOCK,
        BAMBOO_BUTTON,
        BAMBOO_DOOR,
        BAMBOO_FENCE,
        BAMBOO_FENCE_GATE,
        BAMBOO_HANGING_SIGN,
        BAMBOO_MOSAIC,
        BAMBOO_MOSAIC_SLAB,
        BAMBOO_MOSAIC_STAIRS,
        BAMBOO_PLANKS,
        BAMBOO_PRESSURE_PLATE,
        BAMBOO_SAPLING,
        BAMBOO_SHELF,
        BAMBOO_SIGN,
        BAMBOO_SLAB,
        BAMBOO_STAIRS,
        BAMBOO_TRAPDOOR,
        BAMBOO_WALL_HANGING_SIGN,
        BAMBOO_WALL_SIGN,
        BARREL,
        BARRIER,
        BASALT,
        BEACON,
        BEDROCK,
        BEE_NEST,
        BEEHIVE,
        BEETROOTS,
        BELL,
        BIG_DRIPLEAF,
        BIG_DRIPLEAF_STEM,
        BIRCH_BUTTON,
        BIRCH_DOOR,
        BIRCH_FENCE,
        BIRCH_FENCE_GATE,
        BIRCH_HANGING_SIGN,
        BIRCH_LEAVES,
        BIRCH_LOG,
        BIRCH_PLANKS,
        BIRCH_PRESSURE_PLATE,
        BIRCH_SAPLING,
        BIRCH_SHELF,
        BIRCH_SIGN,
        BIRCH_SLAB,
        BIRCH_STAIRS,
        BIRCH_TRAPDOOR,
        BIRCH_WALL_HANGING_SIGN,
        BIRCH_WALL_SIGN,
        BIRCH_WOOD,
        BLACK_BANNER,
        BLACK_BED,
        BLACK_CANDLE,
        BLACK_CANDLE_CAKE,
        BLACK_CARPET,
        BLACK_CONCRETE,
        BLACK_CONCRETE_POWDER,
        BLACK_CONCRETE_SLAB,
        BLACK_CONCRETE_STAIRS,
        BLACK_GLAZED_TERRACOTTA,
        BLACK_SHULKER_BOX,
        BLACK_STAINED_GLASS,
        BLACK_STAINED_GLASS_PANE,
        BLACK_TERRACOTTA,
        BLACK_WALL_BANNER,
        BLACK_WOOL,
        BLACK_WOOL_SLAB,
        BLACK_WOOL_STAIRS,
        BLACKSTONE,
        BLACKSTONE_SLAB,
        BLACKSTONE_STAIRS,
        BLACKSTONE_WALL,
        BLAST_FURNACE,
        BLUE_BANNER,
        BLUE_BED,
        BLUE_CANDLE,
        BLUE_CANDLE_CAKE,
        BLUE_CARPET,
        BLUE_CONCRETE,
        BLUE_CONCRETE_POWDER,
        BLUE_CONCRETE_SLAB,
        BLUE_CONCRETE_STAIRS,
        BLUE_GLAZED_TERRACOTTA,
        BLUE_ICE,
        BLUE_ORCHID,
        BLUE_SHULKER_BOX,
        BLUE_STAINED_GLASS,
        BLUE_STAINED_GLASS_PANE,
        BLUE_TERRACOTTA,
        BLUE_WALL_BANNER,
        BLUE_WOOL,
        BLUE_WOOL_SLAB,
        BLUE_WOOL_STAIRS,
        BONE_BLOCK,
        BOOKSHELF,
        BRAIN_CORAL,
        BRAIN_CORAL_BLOCK,
        BRAIN_CORAL_FAN,
        BRAIN_CORAL_WALL_FAN,
        BREWING_STAND,
        BRICK_SLAB,
        BRICK_STAIRS,
        BRICK_WALL,
        BRICKS,
        BROWN_BANNER,
        BROWN_BED,
        BROWN_CANDLE,
        BROWN_CANDLE_CAKE,
        BROWN_CARPET,
        BROWN_CONCRETE,
        BROWN_CONCRETE_POWDER,
        BROWN_CONCRETE_SLAB,
        BROWN_CONCRETE_STAIRS,
        BROWN_GLAZED_TERRACOTTA,
        BROWN_MUSHROOM,
        BROWN_MUSHROOM_BLOCK,
        BROWN_SHULKER_BOX,
        BROWN_STAINED_GLASS,
        BROWN_STAINED_GLASS_PANE,
        BROWN_TERRACOTTA,
        BROWN_WALL_BANNER,
        BROWN_WOOL,
        BROWN_WOOL_SLAB,
        BROWN_WOOL_STAIRS,
        BUBBLE_COLUMN,
        BUBBLE_CORAL,
        BUBBLE_CORAL_BLOCK,
        BUBBLE_CORAL_FAN,
        BUBBLE_CORAL_WALL_FAN,
        BUDDING_AMETHYST,
        BUSH,
        CACTUS,
        CACTUS_FLOWER,
        CAKE,
        CALCITE,
        CALIBRATED_SCULK_SENSOR,
        CAMPFIRE,
        CANDLE,
        CANDLE_CAKE,
        CARROTS,
        CARTOGRAPHY_TABLE,
        CARVED_PUMPKIN,
        CAULDRON,
        CAVE_AIR,
        CAVE_VINES,
        CAVE_VINES_PLANT,
        CHAIN_COMMAND_BLOCK,
        CHERRY_BUTTON,
        CHERRY_DOOR,
        CHERRY_FENCE,
        CHERRY_FENCE_GATE,
        CHERRY_HANGING_SIGN,
        CHERRY_LEAVES,
        CHERRY_LOG,
        CHERRY_PLANKS,
        CHERRY_PRESSURE_PLATE,
        CHERRY_SAPLING,
        CHERRY_SHELF,
        CHERRY_SIGN,
        CHERRY_SLAB,
        CHERRY_STAIRS,
        CHERRY_TRAPDOOR,
        CHERRY_WALL_HANGING_SIGN,
        CHERRY_WALL_SIGN,
        CHERRY_WOOD,
        CHEST,
        CHIPPED_ANVIL,
        CHISELED_BOOKSHELF,
        CHISELED_CINNABAR,
        CHISELED_COPPER,
        CHISELED_DEEPSLATE,
        CHISELED_NETHER_BRICKS,
        CHISELED_POLISHED_BLACKSTONE,
        CHISELED_QUARTZ_BLOCK,
        CHISELED_RED_SANDSTONE,
        CHISELED_RESIN_BRICKS,
        CHISELED_SANDSTONE,
        CHISELED_STONE_BRICKS,
        CHISELED_SULFUR,
        CHISELED_TUFF,
        CHISELED_TUFF_BRICKS,
        CHORUS_FLOWER,
        CHORUS_PLANT,
        CINNABAR,
        CINNABAR_BRICK_SLAB,
        CINNABAR_BRICK_STAIRS,
        CINNABAR_BRICK_WALL,
        CINNABAR_BRICKS,
        CINNABAR_SLAB,
        CINNABAR_STAIRS,
        CINNABAR_WALL,
        CLAY,
        CLOSED_EYEBLOSSOM,
        COAL_BLOCK,
        COAL_ORE,
        COARSE_DIRT,
        COBBLED_DEEPSLATE,
        COBBLED_DEEPSLATE_SLAB,
        COBBLED_DEEPSLATE_STAIRS,
        COBBLED_DEEPSLATE_WALL,
        COBBLESTONE,
        COBBLESTONE_SLAB,
        COBBLESTONE_STAIRS,
        COBBLESTONE_WALL,
        COBWEB,
        COCOA,
        COMMAND_BLOCK,
        COMPARATOR,
        COMPOSTER,
        CONDUIT,
        COPPER_BARS,
        COPPER_BLOCK,
        COPPER_BULB,
        COPPER_CHAIN,
        COPPER_CHEST,
        COPPER_DOOR,
        COPPER_GOLEM_STATUE,
        COPPER_GRATE,
        COPPER_LANTERN,
        COPPER_ORE,
        COPPER_TORCH,
        COPPER_TRAPDOOR,
        COPPER_WALL_TORCH,
        CORNFLOWER,
        CRACKED_DEEPSLATE_BRICKS,
        CRACKED_DEEPSLATE_TILES,
        CRACKED_NETHER_BRICKS,
        CRACKED_POLISHED_BLACKSTONE_BRICKS,
        CRACKED_STONE_BRICKS,
        CRAFTER,
        CRAFTING_TABLE,
        CREAKING_HEART,
        CREEPER_HEAD,
        CREEPER_WALL_HEAD,
        CRIMSON_BUTTON,
        CRIMSON_DOOR,
        CRIMSON_FENCE,
        CRIMSON_FENCE_GATE,
        CRIMSON_FUNGUS,
        CRIMSON_HANGING_SIGN,
        CRIMSON_HYPHAE,
        CRIMSON_NYLIUM,
        CRIMSON_PLANKS,
        CRIMSON_PRESSURE_PLATE,
        CRIMSON_ROOTS,
        CRIMSON_SHELF,
        CRIMSON_SIGN,
        CRIMSON_SLAB,
        CRIMSON_STAIRS,
        CRIMSON_STEM,
        CRIMSON_TRAPDOOR,
        CRIMSON_WALL_HANGING_SIGN,
        CRIMSON_WALL_SIGN,
        CRYING_OBSIDIAN,
        CUT_COPPER,
        CUT_COPPER_SLAB,
        CUT_COPPER_STAIRS,
        CUT_RED_SANDSTONE,
        CUT_RED_SANDSTONE_SLAB,
        CUT_SANDSTONE,
        CUT_SANDSTONE_SLAB,
        CYAN_BANNER,
        CYAN_BED,
        CYAN_CANDLE,
        CYAN_CANDLE_CAKE,
        CYAN_CARPET,
        CYAN_CONCRETE,
        CYAN_CONCRETE_POWDER,
        CYAN_CONCRETE_SLAB,
        CYAN_CONCRETE_STAIRS,
        CYAN_GLAZED_TERRACOTTA,
        CYAN_SHULKER_BOX,
        CYAN_STAINED_GLASS,
        CYAN_STAINED_GLASS_PANE,
        CYAN_TERRACOTTA,
        CYAN_WALL_BANNER,
        CYAN_WOOL,
        CYAN_WOOL_SLAB,
        CYAN_WOOL_STAIRS,
        DAMAGED_ANVIL,
        DANDELION,
        DARK_OAK_BUTTON,
        DARK_OAK_DOOR,
        DARK_OAK_FENCE,
        DARK_OAK_FENCE_GATE,
        DARK_OAK_HANGING_SIGN,
        DARK_OAK_LEAVES,
        DARK_OAK_LOG,
        DARK_OAK_PLANKS,
        DARK_OAK_PRESSURE_PLATE,
        DARK_OAK_SAPLING,
        DARK_OAK_SHELF,
        DARK_OAK_SIGN,
        DARK_OAK_SLAB,
        DARK_OAK_STAIRS,
        DARK_OAK_TRAPDOOR,
        DARK_OAK_WALL_HANGING_SIGN,
        DARK_OAK_WALL_SIGN,
        DARK_OAK_WOOD,
        DARK_PRISMARINE,
        DARK_PRISMARINE_SLAB,
        DARK_PRISMARINE_STAIRS,
        DAYLIGHT_DETECTOR,
        DEAD_BRAIN_CORAL,
        DEAD_BRAIN_CORAL_BLOCK,
        DEAD_BRAIN_CORAL_FAN,
        DEAD_BRAIN_CORAL_WALL_FAN,
        DEAD_BUBBLE_CORAL,
        DEAD_BUBBLE_CORAL_BLOCK,
        DEAD_BUBBLE_CORAL_FAN,
        DEAD_BUBBLE_CORAL_WALL_FAN,
        DEAD_BUSH,
        DEAD_FIRE_CORAL,
        DEAD_FIRE_CORAL_BLOCK,
        DEAD_FIRE_CORAL_FAN,
        DEAD_FIRE_CORAL_WALL_FAN,
        DEAD_HORN_CORAL,
        DEAD_HORN_CORAL_BLOCK,
        DEAD_HORN_CORAL_FAN,
        DEAD_HORN_CORAL_WALL_FAN,
        DEAD_TUBE_CORAL,
        DEAD_TUBE_CORAL_BLOCK,
        DEAD_TUBE_CORAL_FAN,
        DEAD_TUBE_CORAL_WALL_FAN,
        DECORATED_POT,
        DEEPSLATE,
        DEEPSLATE_BRICK_SLAB,
        DEEPSLATE_BRICK_STAIRS,
        DEEPSLATE_BRICK_WALL,
        DEEPSLATE_BRICKS,
        DEEPSLATE_COAL_ORE,
        DEEPSLATE_COPPER_ORE,
        DEEPSLATE_DIAMOND_ORE,
        DEEPSLATE_EMERALD_ORE,
        DEEPSLATE_GOLD_ORE,
        DEEPSLATE_IRON_ORE,
        DEEPSLATE_LAPIS_ORE,
        DEEPSLATE_REDSTONE_ORE,
        DEEPSLATE_TILE_SLAB,
        DEEPSLATE_TILE_STAIRS,
        DEEPSLATE_TILE_WALL,
        DEEPSLATE_TILES,
        DETECTOR_RAIL,
        DIAMOND_BLOCK,
        DIAMOND_ORE,
        DIORITE,
        DIORITE_SLAB,
        DIORITE_STAIRS,
        DIORITE_WALL,
        DIRT,
        DIRT_PATH,
        DISPENSER,
        DRAGON_EGG,
        DRAGON_HEAD,
        DRAGON_WALL_HEAD,
        DRIED_GHAST,
        DRIED_KELP_BLOCK,
        DRIPSTONE_BLOCK,
        DROPPER,
        EMERALD_BLOCK,
        EMERALD_ORE,
        ENCHANTING_TABLE,
        END_GATEWAY,
        END_PORTAL,
        END_PORTAL_FRAME,
        END_ROD,
        END_STONE,
        END_STONE_BRICK_SLAB,
        END_STONE_BRICK_STAIRS,
        END_STONE_BRICK_WALL,
        END_STONE_BRICKS,
        ENDER_CHEST,
        EXPOSED_CHISELED_COPPER,
        EXPOSED_COPPER,
        EXPOSED_COPPER_BARS,
        EXPOSED_COPPER_BULB,
        EXPOSED_COPPER_CHAIN,
        EXPOSED_COPPER_CHEST,
        EXPOSED_COPPER_DOOR,
        EXPOSED_COPPER_GOLEM_STATUE,
        EXPOSED_COPPER_GRATE,
        EXPOSED_COPPER_LANTERN,
        EXPOSED_COPPER_TRAPDOOR,
        EXPOSED_CUT_COPPER,
        EXPOSED_CUT_COPPER_SLAB,
        EXPOSED_CUT_COPPER_STAIRS,
        EXPOSED_LIGHTNING_ROD,
        FARMLAND,
        FERN,
        FIRE,
        FIRE_CORAL,
        FIRE_CORAL_BLOCK,
        FIRE_CORAL_FAN,
        FIRE_CORAL_WALL_FAN,
        FIREFLY_BUSH,
        FLETCHING_TABLE,
        FLOWER_POT,
        FLOWERING_AZALEA,
        FLOWERING_AZALEA_LEAVES,
        FROGSPAWN,
        FROSTED_ICE,
        FURNACE,
        GILDED_BLACKSTONE,
        GLASS,
        GLASS_PANE,
        GLOW_LICHEN,
        GLOWSTONE,
        GOLD_BLOCK,
        GOLD_ORE,
        GOLDEN_DANDELION,
        GRANITE,
        GRANITE_SLAB,
        GRANITE_STAIRS,
        GRANITE_WALL,
        GRASS_BLOCK,
        GRAVEL,
        GRAY_BANNER,
        GRAY_BED,
        GRAY_CANDLE,
        GRAY_CANDLE_CAKE,
        GRAY_CARPET,
        GRAY_CONCRETE,
        GRAY_CONCRETE_POWDER,
        GRAY_CONCRETE_SLAB,
        GRAY_CONCRETE_STAIRS,
        GRAY_GLAZED_TERRACOTTA,
        GRAY_SHULKER_BOX,
        GRAY_STAINED_GLASS,
        GRAY_STAINED_GLASS_PANE,
        GRAY_TERRACOTTA,
        GRAY_WALL_BANNER,
        GRAY_WOOL,
        GRAY_WOOL_SLAB,
        GRAY_WOOL_STAIRS,
        GREEN_BANNER,
        GREEN_BED,
        GREEN_CANDLE,
        GREEN_CANDLE_CAKE,
        GREEN_CARPET,
        GREEN_CONCRETE,
        GREEN_CONCRETE_POWDER,
        GREEN_CONCRETE_SLAB,
        GREEN_CONCRETE_STAIRS,
        GREEN_GLAZED_TERRACOTTA,
        GREEN_SHULKER_BOX,
        GREEN_STAINED_GLASS,
        GREEN_STAINED_GLASS_PANE,
        GREEN_TERRACOTTA,
        GREEN_WALL_BANNER,
        GREEN_WOOL,
        GREEN_WOOL_SLAB,
        GREEN_WOOL_STAIRS,
        GRINDSTONE,
        HANGING_ROOTS,
        HAY_BLOCK,
        HEAVY_CORE,
        HEAVY_WEIGHTED_PRESSURE_PLATE,
        HONEY_BLOCK,
        HONEYCOMB_BLOCK,
        HOPPER,
        HORN_CORAL,
        HORN_CORAL_BLOCK,
        HORN_CORAL_FAN,
        HORN_CORAL_WALL_FAN,
        ICE,
        INFESTED_CHISELED_STONE_BRICKS,
        INFESTED_COBBLESTONE,
        INFESTED_CRACKED_STONE_BRICKS,
        INFESTED_DEEPSLATE,
        INFESTED_MOSSY_STONE_BRICKS,
        INFESTED_STONE,
        INFESTED_STONE_BRICKS,
        IRON_BARS,
        IRON_BLOCK,
        IRON_CHAIN,
        IRON_DOOR,
        IRON_ORE,
        IRON_TRAPDOOR,
        JACK_O_LANTERN,
        JIGSAW,
        JUKEBOX,
        JUNGLE_BUTTON,
        JUNGLE_DOOR,
        JUNGLE_FENCE,
        JUNGLE_FENCE_GATE,
        JUNGLE_HANGING_SIGN,
        JUNGLE_LEAVES,
        JUNGLE_LOG,
        JUNGLE_PLANKS,
        JUNGLE_PRESSURE_PLATE,
        JUNGLE_SAPLING,
        JUNGLE_SHELF,
        JUNGLE_SIGN,
        JUNGLE_SLAB,
        JUNGLE_STAIRS,
        JUNGLE_TRAPDOOR,
        JUNGLE_WALL_HANGING_SIGN,
        JUNGLE_WALL_SIGN,
        JUNGLE_WOOD,
        KELP,
        KELP_PLANT,
        LADDER,
        LANTERN,
        LAPIS_BLOCK,
        LAPIS_ORE,
        LARGE_AMETHYST_BUD,
        LARGE_FERN,
        LAVA,
        LAVA_CAULDRON,
        LEAF_LITTER,
        LECTERN,
        LEVER,
        LIGHT,
        LIGHT_BLUE_BANNER,
        LIGHT_BLUE_BED,
        LIGHT_BLUE_CANDLE,
        LIGHT_BLUE_CANDLE_CAKE,
        LIGHT_BLUE_CARPET,
        LIGHT_BLUE_CONCRETE,
        LIGHT_BLUE_CONCRETE_POWDER,
        LIGHT_BLUE_CONCRETE_SLAB,
        LIGHT_BLUE_CONCRETE_STAIRS,
        LIGHT_BLUE_GLAZED_TERRACOTTA,
        LIGHT_BLUE_SHULKER_BOX,
        LIGHT_BLUE_STAINED_GLASS,
        LIGHT_BLUE_STAINED_GLASS_PANE,
        LIGHT_BLUE_TERRACOTTA,
        LIGHT_BLUE_WALL_BANNER,
        LIGHT_BLUE_WOOL,
        LIGHT_BLUE_WOOL_SLAB,
        LIGHT_BLUE_WOOL_STAIRS,
        LIGHT_GRAY_BANNER,
        LIGHT_GRAY_BED,
        LIGHT_GRAY_CANDLE,
        LIGHT_GRAY_CANDLE_CAKE,
        LIGHT_GRAY_CARPET,
        LIGHT_GRAY_CONCRETE,
        LIGHT_GRAY_CONCRETE_POWDER,
        LIGHT_GRAY_CONCRETE_SLAB,
        LIGHT_GRAY_CONCRETE_STAIRS,
        LIGHT_GRAY_GLAZED_TERRACOTTA,
        LIGHT_GRAY_SHULKER_BOX,
        LIGHT_GRAY_STAINED_GLASS,
        LIGHT_GRAY_STAINED_GLASS_PANE,
        LIGHT_GRAY_TERRACOTTA,
        LIGHT_GRAY_WALL_BANNER,
        LIGHT_GRAY_WOOL,
        LIGHT_GRAY_WOOL_SLAB,
        LIGHT_GRAY_WOOL_STAIRS,
        LIGHT_WEIGHTED_PRESSURE_PLATE,
        LIGHTNING_ROD,
        LILAC,
        LILY_OF_THE_VALLEY,
        LILY_PAD,
        LIME_BANNER,
        LIME_BED,
        LIME_CANDLE,
        LIME_CANDLE_CAKE,
        LIME_CARPET,
        LIME_CONCRETE,
        LIME_CONCRETE_POWDER,
        LIME_CONCRETE_SLAB,
        LIME_CONCRETE_STAIRS,
        LIME_GLAZED_TERRACOTTA,
        LIME_SHULKER_BOX,
        LIME_STAINED_GLASS,
        LIME_STAINED_GLASS_PANE,
        LIME_TERRACOTTA,
        LIME_WALL_BANNER,
        LIME_WOOL,
        LIME_WOOL_SLAB,
        LIME_WOOL_STAIRS,
        LODESTONE,
        LOOM,
        MAGENTA_BANNER,
        MAGENTA_BED,
        MAGENTA_CANDLE,
        MAGENTA_CANDLE_CAKE,
        MAGENTA_CARPET,
        MAGENTA_CONCRETE,
        MAGENTA_CONCRETE_POWDER,
        MAGENTA_CONCRETE_SLAB,
        MAGENTA_CONCRETE_STAIRS,
        MAGENTA_GLAZED_TERRACOTTA,
        MAGENTA_SHULKER_BOX,
        MAGENTA_STAINED_GLASS,
        MAGENTA_STAINED_GLASS_PANE,
        MAGENTA_TERRACOTTA,
        MAGENTA_WALL_BANNER,
        MAGENTA_WOOL,
        MAGENTA_WOOL_SLAB,
        MAGENTA_WOOL_STAIRS,
        MAGMA_BLOCK,
        MANGROVE_BUTTON,
        MANGROVE_DOOR,
        MANGROVE_FENCE,
        MANGROVE_FENCE_GATE,
        MANGROVE_HANGING_SIGN,
        MANGROVE_LEAVES,
        MANGROVE_LOG,
        MANGROVE_PLANKS,
        MANGROVE_PRESSURE_PLATE,
        MANGROVE_PROPAGULE,
        MANGROVE_ROOTS,
        MANGROVE_SHELF,
        MANGROVE_SIGN,
        MANGROVE_SLAB,
        MANGROVE_STAIRS,
        MANGROVE_TRAPDOOR,
        MANGROVE_WALL_HANGING_SIGN,
        MANGROVE_WALL_SIGN,
        MANGROVE_WOOD,
        MEDIUM_AMETHYST_BUD,
        MELON,
        MELON_STEM,
        MOSS_BLOCK,
        MOSS_CARPET,
        MOSSY_COBBLESTONE,
        MOSSY_COBBLESTONE_SLAB,
        MOSSY_COBBLESTONE_STAIRS,
        MOSSY_COBBLESTONE_WALL,
        MOSSY_STONE_BRICK_SLAB,
        MOSSY_STONE_BRICK_STAIRS,
        MOSSY_STONE_BRICK_WALL,
        MOSSY_STONE_BRICKS,
        MOVING_PISTON,
        MUD,
        MUD_BRICK_SLAB,
        MUD_BRICK_STAIRS,
        MUD_BRICK_WALL,
        MUD_BRICKS,
        MUDDY_MANGROVE_ROOTS,
        MUSHROOM_STEM,
        MYCELIUM,
        NETHER_BRICK_FENCE,
        NETHER_BRICK_SLAB,
        NETHER_BRICK_STAIRS,
        NETHER_BRICK_WALL,
        NETHER_BRICKS,
        NETHER_GOLD_ORE,
        NETHER_PORTAL,
        NETHER_QUARTZ_ORE,
        NETHER_SPROUTS,
        NETHER_WART,
        NETHER_WART_BLOCK,
        NETHERITE_BLOCK,
        NETHERRACK,
        NOTE_BLOCK,
        OAK_BUTTON,
        OAK_DOOR,
        OAK_FENCE,
        OAK_FENCE_GATE,
        OAK_HANGING_SIGN,
        OAK_LEAVES,
        OAK_LOG,
        OAK_PLANKS,
        OAK_PRESSURE_PLATE,
        OAK_SAPLING,
        OAK_SHELF,
        OAK_SIGN,
        OAK_SLAB,
        OAK_STAIRS,
        OAK_TRAPDOOR,
        OAK_WALL_HANGING_SIGN,
        OAK_WALL_SIGN,
        OAK_WOOD,
        OBSERVER,
        OBSIDIAN,
        OCHRE_FROGLIGHT,
        OPEN_EYEBLOSSOM,
        ORANGE_BANNER,
        ORANGE_BED,
        ORANGE_CANDLE,
        ORANGE_CANDLE_CAKE,
        ORANGE_CARPET,
        ORANGE_CONCRETE,
        ORANGE_CONCRETE_POWDER,
        ORANGE_CONCRETE_SLAB,
        ORANGE_CONCRETE_STAIRS,
        ORANGE_GLAZED_TERRACOTTA,
        ORANGE_POPLAR_LEAVES,
        ORANGE_SHULKER_BOX,
        ORANGE_STAINED_GLASS,
        ORANGE_STAINED_GLASS_PANE,
        ORANGE_TERRACOTTA,
        ORANGE_TULIP,
        ORANGE_WALL_BANNER,
        ORANGE_WOOL,
        ORANGE_WOOL_SLAB,
        ORANGE_WOOL_STAIRS,
        OXEYE_DAISY,
        OXIDIZED_CHISELED_COPPER,
        OXIDIZED_COPPER,
        OXIDIZED_COPPER_BARS,
        OXIDIZED_COPPER_BULB,
        OXIDIZED_COPPER_CHAIN,
        OXIDIZED_COPPER_CHEST,
        OXIDIZED_COPPER_DOOR,
        OXIDIZED_COPPER_GOLEM_STATUE,
        OXIDIZED_COPPER_GRATE,
        OXIDIZED_COPPER_LANTERN,
        OXIDIZED_COPPER_TRAPDOOR,
        OXIDIZED_CUT_COPPER,
        OXIDIZED_CUT_COPPER_SLAB,
        OXIDIZED_CUT_COPPER_STAIRS,
        OXIDIZED_LIGHTNING_ROD,
        PACKED_ICE,
        PACKED_MUD,
        PALE_HANGING_MOSS,
        PALE_MOSS_BLOCK,
        PALE_MOSS_CARPET,
        PALE_OAK_BUTTON,
        PALE_OAK_DOOR,
        PALE_OAK_FENCE,
        PALE_OAK_FENCE_GATE,
        PALE_OAK_HANGING_SIGN,
        PALE_OAK_LEAVES,
        PALE_OAK_LOG,
        PALE_OAK_PLANKS,
        PALE_OAK_PRESSURE_PLATE,
        PALE_OAK_SAPLING,
        PALE_OAK_SHELF,
        PALE_OAK_SIGN,
        PALE_OAK_SLAB,
        PALE_OAK_STAIRS,
        PALE_OAK_TRAPDOOR,
        PALE_OAK_WALL_HANGING_SIGN,
        PALE_OAK_WALL_SIGN,
        PALE_OAK_WOOD,
        PEARLESCENT_FROGLIGHT,
        PEONY,
        PETRIFIED_OAK_SLAB,
        PIGLIN_HEAD,
        PIGLIN_WALL_HEAD,
        PINK_BANNER,
        PINK_BED,
        PINK_CANDLE,
        PINK_CANDLE_CAKE,
        PINK_CARPET,
        PINK_CONCRETE,
        PINK_CONCRETE_POWDER,
        PINK_CONCRETE_SLAB,
        PINK_CONCRETE_STAIRS,
        PINK_GLAZED_TERRACOTTA,
        PINK_PETALS,
        PINK_SHULKER_BOX,
        PINK_STAINED_GLASS,
        PINK_STAINED_GLASS_PANE,
        PINK_TERRACOTTA,
        PINK_TULIP,
        PINK_WALL_BANNER,
        PINK_WOOL,
        PINK_WOOL_SLAB,
        PINK_WOOL_STAIRS,
        PISTON,
        PISTON_HEAD,
        PITCHER_CROP,
        PITCHER_PLANT,
        PLAYER_HEAD,
        PLAYER_WALL_HEAD,
        PODZOL,
        POINTED_DRIPSTONE,
        POLISHED_ANDESITE,
        POLISHED_ANDESITE_SLAB,
        POLISHED_ANDESITE_STAIRS,
        POLISHED_BASALT,
        POLISHED_BLACKSTONE,
        POLISHED_BLACKSTONE_BRICK_SLAB,
        POLISHED_BLACKSTONE_BRICK_STAIRS,
        POLISHED_BLACKSTONE_BRICK_WALL,
        POLISHED_BLACKSTONE_BRICKS,
        POLISHED_BLACKSTONE_BUTTON,
        POLISHED_BLACKSTONE_PRESSURE_PLATE,
        POLISHED_BLACKSTONE_SLAB,
        POLISHED_BLACKSTONE_STAIRS,
        POLISHED_BLACKSTONE_WALL,
        POLISHED_CINNABAR,
        POLISHED_CINNABAR_SLAB,
        POLISHED_CINNABAR_STAIRS,
        POLISHED_CINNABAR_WALL,
        POLISHED_DEEPSLATE,
        POLISHED_DEEPSLATE_SLAB,
        POLISHED_DEEPSLATE_STAIRS,
        POLISHED_DEEPSLATE_WALL,
        POLISHED_DIORITE,
        POLISHED_DIORITE_SLAB,
        POLISHED_DIORITE_STAIRS,
        POLISHED_GRANITE,
        POLISHED_GRANITE_SLAB,
        POLISHED_GRANITE_STAIRS,
        POLISHED_SULFUR,
        POLISHED_SULFUR_SLAB,
        POLISHED_SULFUR_STAIRS,
        POLISHED_SULFUR_WALL,
        POLISHED_TUFF,
        POLISHED_TUFF_SLAB,
        POLISHED_TUFF_STAIRS,
        POLISHED_TUFF_WALL,
        POPLAR_BUTTON,
        POPLAR_DOOR,
        POPLAR_FENCE,
        POPLAR_FENCE_GATE,
        POPLAR_HANGING_SIGN,
        POPLAR_LOG,
        POPLAR_PLANKS,
        POPLAR_PRESSURE_PLATE,
        POPLAR_SAPLING,
        POPLAR_SHELF,
        POPLAR_SIGN,
        POPLAR_SLAB,
        POPLAR_STAIRS,
        POPLAR_TRAPDOOR,
        POPLAR_WALL_HANGING_SIGN,
        POPLAR_WALL_SIGN,
        POPLAR_WOOD,
        POPPY,
        POTATOES,
        POTENT_SULFUR,
        POTTED_ACACIA_SAPLING,
        POTTED_ALLIUM,
        POTTED_AZALEA_BUSH,
        POTTED_AZURE_BLUET,
        POTTED_BAMBOO,
        POTTED_BIRCH_SAPLING,
        POTTED_BLUE_ORCHID,
        POTTED_BROWN_MUSHROOM,
        POTTED_CACTUS,
        POTTED_CHERRY_SAPLING,
        POTTED_CLOSED_EYEBLOSSOM,
        POTTED_CORNFLOWER,
        POTTED_CRIMSON_FUNGUS,
        POTTED_CRIMSON_ROOTS,
        POTTED_DANDELION,
        POTTED_DARK_OAK_SAPLING,
        POTTED_DEAD_BUSH,
        POTTED_FERN,
        POTTED_FLOWERING_AZALEA_BUSH,
        POTTED_GOLDEN_DANDELION,
        POTTED_JUNGLE_SAPLING,
        POTTED_LILY_OF_THE_VALLEY,
        POTTED_MANGROVE_PROPAGULE,
        POTTED_OAK_SAPLING,
        POTTED_OPEN_EYEBLOSSOM,
        POTTED_ORANGE_TULIP,
        POTTED_OXEYE_DAISY,
        POTTED_PALE_OAK_SAPLING,
        POTTED_PINK_TULIP,
        POTTED_POPLAR_SAPLING,
        POTTED_POPPY,
        POTTED_RED_MUSHROOM,
        POTTED_RED_TULIP,
        POTTED_SPRUCE_SAPLING,
        POTTED_TORCHFLOWER,
        POTTED_WARPED_FUNGUS,
        POTTED_WARPED_ROOTS,
        POTTED_WHITE_TULIP,
        POTTED_WITHER_ROSE,
        POWDER_SNOW,
        POWDER_SNOW_CAULDRON,
        POWERED_RAIL,
        PRISMARINE,
        PRISMARINE_BRICK_SLAB,
        PRISMARINE_BRICK_STAIRS,
        PRISMARINE_BRICKS,
        PRISMARINE_SLAB,
        PRISMARINE_STAIRS,
        PRISMARINE_WALL,
        PUMPKIN,
        PUMPKIN_STEM,
        PURPLE_BANNER,
        PURPLE_BED,
        PURPLE_CANDLE,
        PURPLE_CANDLE_CAKE,
        PURPLE_CARPET,
        PURPLE_CONCRETE,
        PURPLE_CONCRETE_POWDER,
        PURPLE_CONCRETE_SLAB,
        PURPLE_CONCRETE_STAIRS,
        PURPLE_GLAZED_TERRACOTTA,
        PURPLE_SHULKER_BOX,
        PURPLE_STAINED_GLASS,
        PURPLE_STAINED_GLASS_PANE,
        PURPLE_TERRACOTTA,
        PURPLE_WALL_BANNER,
        PURPLE_WOOL,
        PURPLE_WOOL_SLAB,
        PURPLE_WOOL_STAIRS,
        PURPUR_BLOCK,
        PURPUR_PILLAR,
        PURPUR_SLAB,
        PURPUR_STAIRS,
        QUARTZ_BLOCK,
        QUARTZ_BRICKS,
        QUARTZ_PILLAR,
        QUARTZ_SLAB,
        QUARTZ_STAIRS,
        RAIL,
        RAW_COPPER_BLOCK,
        RAW_GOLD_BLOCK,
        RAW_IRON_BLOCK,
        RED_BANNER,
        RED_BED,
        RED_CANDLE,
        RED_CANDLE_CAKE,
        RED_CARPET,
        RED_CONCRETE,
        RED_CONCRETE_POWDER,
        RED_CONCRETE_SLAB,
        RED_CONCRETE_STAIRS,
        RED_GLAZED_TERRACOTTA,
        RED_MUSHROOM,
        RED_MUSHROOM_BLOCK,
        RED_NETHER_BRICK_SLAB,
        RED_NETHER_BRICK_STAIRS,
        RED_NETHER_BRICK_WALL,
        RED_NETHER_BRICKS,
        RED_POPLAR_LEAVES,
        RED_SAND,
        RED_SANDSTONE,
        RED_SANDSTONE_SLAB,
        RED_SANDSTONE_STAIRS,
        RED_SANDSTONE_WALL,
        RED_SHRUB,
        RED_SHULKER_BOX,
        RED_STAINED_GLASS,
        RED_STAINED_GLASS_PANE,
        RED_TERRACOTTA,
        RED_TULIP,
        RED_WALL_BANNER,
        RED_WOOL,
        RED_WOOL_SLAB,
        RED_WOOL_STAIRS,
        REDSTONE_BLOCK,
        REDSTONE_LAMP,
        REDSTONE_ORE,
        REDSTONE_TORCH,
        REDSTONE_WALL_TORCH,
        REDSTONE_WIRE,
        REINFORCED_DEEPSLATE,
        REPEATER,
        REPEATING_COMMAND_BLOCK,
        RESIN_BLOCK,
        RESIN_BRICK_SLAB,
        RESIN_BRICK_STAIRS,
        RESIN_BRICK_WALL,
        RESIN_BRICKS,
        RESIN_CLUMP,
        RESPAWN_ANCHOR,
        ROOTED_DIRT,
        ROSE_BUSH,
        SAND,
        SANDSTONE,
        SANDSTONE_SLAB,
        SANDSTONE_STAIRS,
        SANDSTONE_WALL,
        SCAFFOLDING,
        SCULK,
        SCULK_CATALYST,
        SCULK_SENSOR,
        SCULK_SHRIEKER,
        SCULK_VEIN,
        SEA_LANTERN,
        SEA_PICKLE,
        SEAGRASS,
        SHELF_MUSHROOM,
        SHORT_DRY_GRASS,
        SHORT_GRASS,
        SHROOMLIGHT,
        SHULKER_BOX,
        SKELETON_SKULL,
        SKELETON_WALL_SKULL,
        SLIME_BLOCK,
        SMALL_AMETHYST_BUD,
        SMALL_DRIPLEAF,
        SMITHING_TABLE,
        SMOKER,
        SMOOTH_BASALT,
        SMOOTH_QUARTZ,
        SMOOTH_QUARTZ_SLAB,
        SMOOTH_QUARTZ_STAIRS,
        SMOOTH_RED_SANDSTONE,
        SMOOTH_RED_SANDSTONE_SLAB,
        SMOOTH_RED_SANDSTONE_STAIRS,
        SMOOTH_SANDSTONE,
        SMOOTH_SANDSTONE_SLAB,
        SMOOTH_SANDSTONE_STAIRS,
        SMOOTH_STONE,
        SMOOTH_STONE_SLAB,
        SNIFFER_EGG,
        SNOW,
        SNOW_BLOCK,
        SOUL_CAMPFIRE,
        SOUL_FIRE,
        SOUL_LANTERN,
        SOUL_SAND,
        SOUL_SOIL,
        SOUL_TORCH,
        SOUL_WALL_TORCH,
        SPAWNER,
        SPONGE,
        SPORE_BLOSSOM,
        SPRUCE_BUTTON,
        SPRUCE_DOOR,
        SPRUCE_FENCE,
        SPRUCE_FENCE_GATE,
        SPRUCE_HANGING_SIGN,
        SPRUCE_LEAVES,
        SPRUCE_LOG,
        SPRUCE_PLANKS,
        SPRUCE_PRESSURE_PLATE,
        SPRUCE_SAPLING,
        SPRUCE_SHELF,
        SPRUCE_SIGN,
        SPRUCE_SLAB,
        SPRUCE_STAIRS,
        SPRUCE_TRAPDOOR,
        SPRUCE_WALL_HANGING_SIGN,
        SPRUCE_WALL_SIGN,
        SPRUCE_WOOD,
        STICKY_PISTON,
        STONE,
        STONE_BRICK_SLAB,
        STONE_BRICK_STAIRS,
        STONE_BRICK_WALL,
        STONE_BRICKS,
        STONE_BUTTON,
        STONE_PRESSURE_PLATE,
        STONE_SLAB,
        STONE_STAIRS,
        STONECUTTER,
        STRAW_BED,
        STRIPPED_ACACIA_LOG,
        STRIPPED_ACACIA_WOOD,
        STRIPPED_BAMBOO_BLOCK,
        STRIPPED_BIRCH_LOG,
        STRIPPED_BIRCH_WOOD,
        STRIPPED_CHERRY_LOG,
        STRIPPED_CHERRY_WOOD,
        STRIPPED_CRIMSON_HYPHAE,
        STRIPPED_CRIMSON_STEM,
        STRIPPED_DARK_OAK_LOG,
        STRIPPED_DARK_OAK_WOOD,
        STRIPPED_JUNGLE_LOG,
        STRIPPED_JUNGLE_WOOD,
        STRIPPED_MANGROVE_LOG,
        STRIPPED_MANGROVE_WOOD,
        STRIPPED_OAK_LOG,
        STRIPPED_OAK_WOOD,
        STRIPPED_PALE_OAK_LOG,
        STRIPPED_PALE_OAK_WOOD,
        STRIPPED_POPLAR_LOG,
        STRIPPED_POPLAR_WOOD,
        STRIPPED_SPRUCE_LOG,
        STRIPPED_SPRUCE_WOOD,
        STRIPPED_WARPED_HYPHAE,
        STRIPPED_WARPED_STEM,
        STRUCTURE_BLOCK,
        STRUCTURE_VOID,
        SUGAR_CANE,
        SULFUR,
        SULFUR_BRICK_SLAB,
        SULFUR_BRICK_STAIRS,
        SULFUR_BRICK_WALL,
        SULFUR_BRICKS,
        SULFUR_SLAB,
        SULFUR_SPIKE,
        SULFUR_STAIRS,
        SULFUR_WALL,
        SUNFLOWER,
        SUSPICIOUS_GRAVEL,
        SUSPICIOUS_SAND,
        SWEET_BERRY_BUSH,
        TALL_DRY_GRASS,
        TALL_GRASS,
        TALL_SEAGRASS,
        TARGET,
        TERRACOTTA,
        TEST_BLOCK,
        TEST_INSTANCE_BLOCK,
        TINTED_GLASS,
        TNT,
        TORCH,
        TORCHFLOWER,
        TORCHFLOWER_CROP,
        TRAPPED_CHEST,
        TRIAL_SPAWNER,
        TRIPWIRE,
        TRIPWIRE_HOOK,
        TUBE_CORAL,
        TUBE_CORAL_BLOCK,
        TUBE_CORAL_FAN,
        TUBE_CORAL_WALL_FAN,
        TUFF,
        TUFF_BRICK_SLAB,
        TUFF_BRICK_STAIRS,
        TUFF_BRICK_WALL,
        TUFF_BRICKS,
        TUFF_SLAB,
        TUFF_STAIRS,
        TUFF_WALL,
        TURTLE_EGG,
        TWISTING_VINES,
        TWISTING_VINES_PLANT,
        VAULT,
        VERDANT_FROGLIGHT,
        VINE,
        VOID_AIR,
        WALL_TORCH,
        WARPED_BUTTON,
        WARPED_DOOR,
        WARPED_FENCE,
        WARPED_FENCE_GATE,
        WARPED_FUNGUS,
        WARPED_HANGING_SIGN,
        WARPED_HYPHAE,
        WARPED_NYLIUM,
        WARPED_PLANKS,
        WARPED_PRESSURE_PLATE,
        WARPED_ROOTS,
        WARPED_SHELF,
        WARPED_SIGN,
        WARPED_SLAB,
        WARPED_STAIRS,
        WARPED_STEM,
        WARPED_TRAPDOOR,
        WARPED_WALL_HANGING_SIGN,
        WARPED_WALL_SIGN,
        WARPED_WART_BLOCK,
        WATER,
        WATER_CAULDRON,
        WAXED_CHISELED_COPPER,
        WAXED_COPPER_BARS,
        WAXED_COPPER_BLOCK,
        WAXED_COPPER_BULB,
        WAXED_COPPER_CHAIN,
        WAXED_COPPER_CHEST,
        WAXED_COPPER_DOOR,
        WAXED_COPPER_GOLEM_STATUE,
        WAXED_COPPER_GRATE,
        WAXED_COPPER_LANTERN,
        WAXED_COPPER_TRAPDOOR,
        WAXED_CUT_COPPER,
        WAXED_CUT_COPPER_SLAB,
        WAXED_CUT_COPPER_STAIRS,
        WAXED_EXPOSED_CHISELED_COPPER,
        WAXED_EXPOSED_COPPER,
        WAXED_EXPOSED_COPPER_BARS,
        WAXED_EXPOSED_COPPER_BULB,
        WAXED_EXPOSED_COPPER_CHAIN,
        WAXED_EXPOSED_COPPER_CHEST,
        WAXED_EXPOSED_COPPER_DOOR,
        WAXED_EXPOSED_COPPER_GOLEM_STATUE,
        WAXED_EXPOSED_COPPER_GRATE,
        WAXED_EXPOSED_COPPER_LANTERN,
        WAXED_EXPOSED_COPPER_TRAPDOOR,
        WAXED_EXPOSED_CUT_COPPER,
        WAXED_EXPOSED_CUT_COPPER_SLAB,
        WAXED_EXPOSED_CUT_COPPER_STAIRS,
        WAXED_EXPOSED_LIGHTNING_ROD,
        WAXED_LIGHTNING_ROD,
        WAXED_OXIDIZED_CHISELED_COPPER,
        WAXED_OXIDIZED_COPPER,
        WAXED_OXIDIZED_COPPER_BARS,
        WAXED_OXIDIZED_COPPER_BULB,
        WAXED_OXIDIZED_COPPER_CHAIN,
        WAXED_OXIDIZED_COPPER_CHEST,
        WAXED_OXIDIZED_COPPER_DOOR,
        WAXED_OXIDIZED_COPPER_GOLEM_STATUE,
        WAXED_OXIDIZED_COPPER_GRATE,
        WAXED_OXIDIZED_COPPER_LANTERN,
        WAXED_OXIDIZED_COPPER_TRAPDOOR,
        WAXED_OXIDIZED_CUT_COPPER,
        WAXED_OXIDIZED_CUT_COPPER_SLAB,
        WAXED_OXIDIZED_CUT_COPPER_STAIRS,
        WAXED_OXIDIZED_LIGHTNING_ROD,
        WAXED_WEATHERED_CHISELED_COPPER,
        WAXED_WEATHERED_COPPER,
        WAXED_WEATHERED_COPPER_BARS,
        WAXED_WEATHERED_COPPER_BULB,
        WAXED_WEATHERED_COPPER_CHAIN,
        WAXED_WEATHERED_COPPER_CHEST,
        WAXED_WEATHERED_COPPER_DOOR,
        WAXED_WEATHERED_COPPER_GOLEM_STATUE,
        WAXED_WEATHERED_COPPER_GRATE,
        WAXED_WEATHERED_COPPER_LANTERN,
        WAXED_WEATHERED_COPPER_TRAPDOOR,
        WAXED_WEATHERED_CUT_COPPER,
        WAXED_WEATHERED_CUT_COPPER_SLAB,
        WAXED_WEATHERED_CUT_COPPER_STAIRS,
        WAXED_WEATHERED_LIGHTNING_ROD,
        WEATHERED_CHISELED_COPPER,
        WEATHERED_COPPER,
        WEATHERED_COPPER_BARS,
        WEATHERED_COPPER_BULB,
        WEATHERED_COPPER_CHAIN,
        WEATHERED_COPPER_CHEST,
        WEATHERED_COPPER_DOOR,
        WEATHERED_COPPER_GOLEM_STATUE,
        WEATHERED_COPPER_GRATE,
        WEATHERED_COPPER_LANTERN,
        WEATHERED_COPPER_TRAPDOOR,
        WEATHERED_CUT_COPPER,
        WEATHERED_CUT_COPPER_SLAB,
        WEATHERED_CUT_COPPER_STAIRS,
        WEATHERED_LIGHTNING_ROD,
        WEEPING_VINES,
        WEEPING_VINES_PLANT,
        WET_SPONGE,
        WHEAT,
        WHITE_BANNER,
        WHITE_BED,
        WHITE_CANDLE,
        WHITE_CANDLE_CAKE,
        WHITE_CARPET,
        WHITE_CONCRETE,
        WHITE_CONCRETE_POWDER,
        WHITE_CONCRETE_SLAB,
        WHITE_CONCRETE_STAIRS,
        WHITE_GLAZED_TERRACOTTA,
        WHITE_SHULKER_BOX,
        WHITE_STAINED_GLASS,
        WHITE_STAINED_GLASS_PANE,
        WHITE_TERRACOTTA,
        WHITE_TULIP,
        WHITE_WALL_BANNER,
        WHITE_WOOL,
        WHITE_WOOL_SLAB,
        WHITE_WOOL_STAIRS,
        WILDFLOWERS,
        WITHER_ROSE,
        WITHER_SKELETON_SKULL,
        WITHER_SKELETON_WALL_SKULL,
        YELLOW_BANNER,
        YELLOW_BED,
        YELLOW_CANDLE,
        YELLOW_CANDLE_CAKE,
        YELLOW_CARPET,
        YELLOW_CONCRETE,
        YELLOW_CONCRETE_POWDER,
        YELLOW_CONCRETE_SLAB,
        YELLOW_CONCRETE_STAIRS,
        YELLOW_GLAZED_TERRACOTTA,
        YELLOW_POPLAR_LEAVES,
        YELLOW_SHULKER_BOX,
        YELLOW_STAINED_GLASS,
        YELLOW_STAINED_GLASS_PANE,
        YELLOW_TERRACOTTA,
        YELLOW_WALL_BANNER,
        YELLOW_WOOL,
        YELLOW_WOOL_SLAB,
        YELLOW_WOOL_STAIRS,
        ZOMBIE_HEAD,
        ZOMBIE_WALL_HEAD
    );

    private BlockTypeKeys() {
        throw new UnsupportedOperationException("BlockTypeKeys cannot be instantiated.");
    }

    private static TypedKey<BlockType> create(@KeyPattern final String value) {
        return TypedKey.create(RegistryKey.BLOCK, value);
    }

    /**
     * Returns a stream containing all keys declared by this class.
     *
     * @return a stream of registry keys
     */
    public static Stream<TypedKey<BlockType>> values() {
        return VALUES.stream();
    }
}
