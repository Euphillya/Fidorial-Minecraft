package fr.fidorial.registry.keys;

import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.Item;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.adventure.key.KeyPattern;

/**
 * Typed keys for entries in the {@code minecraft:item} registry.
 */
public final class ItemKeys {
    /**
     * Key for {@code minecraft:abandoned_camp_map}.
     */
    public static final TypedKey<Item> ABANDONED_CAMP_MAP = create("abandoned_camp_map");

    /**
     * Key for {@code minecraft:acacia_boat}.
     */
    public static final TypedKey<Item> ACACIA_BOAT = create("acacia_boat");

    /**
     * Key for {@code minecraft:acacia_button}.
     */
    public static final TypedKey<Item> ACACIA_BUTTON = create("acacia_button");

    /**
     * Key for {@code minecraft:acacia_chest_boat}.
     */
    public static final TypedKey<Item> ACACIA_CHEST_BOAT = create("acacia_chest_boat");

    /**
     * Key for {@code minecraft:acacia_door}.
     */
    public static final TypedKey<Item> ACACIA_DOOR = create("acacia_door");

    /**
     * Key for {@code minecraft:acacia_fence}.
     */
    public static final TypedKey<Item> ACACIA_FENCE = create("acacia_fence");

    /**
     * Key for {@code minecraft:acacia_fence_gate}.
     */
    public static final TypedKey<Item> ACACIA_FENCE_GATE = create("acacia_fence_gate");

    /**
     * Key for {@code minecraft:acacia_hanging_sign}.
     */
    public static final TypedKey<Item> ACACIA_HANGING_SIGN = create("acacia_hanging_sign");

    /**
     * Key for {@code minecraft:acacia_leaves}.
     */
    public static final TypedKey<Item> ACACIA_LEAVES = create("acacia_leaves");

    /**
     * Key for {@code minecraft:acacia_log}.
     */
    public static final TypedKey<Item> ACACIA_LOG = create("acacia_log");

    /**
     * Key for {@code minecraft:acacia_planks}.
     */
    public static final TypedKey<Item> ACACIA_PLANKS = create("acacia_planks");

    /**
     * Key for {@code minecraft:acacia_pressure_plate}.
     */
    public static final TypedKey<Item> ACACIA_PRESSURE_PLATE = create("acacia_pressure_plate");

    /**
     * Key for {@code minecraft:acacia_sapling}.
     */
    public static final TypedKey<Item> ACACIA_SAPLING = create("acacia_sapling");

    /**
     * Key for {@code minecraft:acacia_shelf}.
     */
    public static final TypedKey<Item> ACACIA_SHELF = create("acacia_shelf");

    /**
     * Key for {@code minecraft:acacia_sign}.
     */
    public static final TypedKey<Item> ACACIA_SIGN = create("acacia_sign");

    /**
     * Key for {@code minecraft:acacia_slab}.
     */
    public static final TypedKey<Item> ACACIA_SLAB = create("acacia_slab");

    /**
     * Key for {@code minecraft:acacia_stairs}.
     */
    public static final TypedKey<Item> ACACIA_STAIRS = create("acacia_stairs");

    /**
     * Key for {@code minecraft:acacia_trapdoor}.
     */
    public static final TypedKey<Item> ACACIA_TRAPDOOR = create("acacia_trapdoor");

    /**
     * Key for {@code minecraft:acacia_wood}.
     */
    public static final TypedKey<Item> ACACIA_WOOD = create("acacia_wood");

    /**
     * Key for {@code minecraft:activator_rail}.
     */
    public static final TypedKey<Item> ACTIVATOR_RAIL = create("activator_rail");

    /**
     * Key for {@code minecraft:air}.
     */
    public static final TypedKey<Item> AIR = create("air");

    /**
     * Key for {@code minecraft:allay_spawn_egg}.
     */
    public static final TypedKey<Item> ALLAY_SPAWN_EGG = create("allay_spawn_egg");

    /**
     * Key for {@code minecraft:allium}.
     */
    public static final TypedKey<Item> ALLIUM = create("allium");

    /**
     * Key for {@code minecraft:amethyst_block}.
     */
    public static final TypedKey<Item> AMETHYST_BLOCK = create("amethyst_block");

    /**
     * Key for {@code minecraft:amethyst_cluster}.
     */
    public static final TypedKey<Item> AMETHYST_CLUSTER = create("amethyst_cluster");

    /**
     * Key for {@code minecraft:amethyst_shard}.
     */
    public static final TypedKey<Item> AMETHYST_SHARD = create("amethyst_shard");

    /**
     * Key for {@code minecraft:ancient_debris}.
     */
    public static final TypedKey<Item> ANCIENT_DEBRIS = create("ancient_debris");

    /**
     * Key for {@code minecraft:andesite}.
     */
    public static final TypedKey<Item> ANDESITE = create("andesite");

    /**
     * Key for {@code minecraft:andesite_slab}.
     */
    public static final TypedKey<Item> ANDESITE_SLAB = create("andesite_slab");

    /**
     * Key for {@code minecraft:andesite_stairs}.
     */
    public static final TypedKey<Item> ANDESITE_STAIRS = create("andesite_stairs");

    /**
     * Key for {@code minecraft:andesite_wall}.
     */
    public static final TypedKey<Item> ANDESITE_WALL = create("andesite_wall");

    /**
     * Key for {@code minecraft:angler_pottery_sherd}.
     */
    public static final TypedKey<Item> ANGLER_POTTERY_SHERD = create("angler_pottery_sherd");

    /**
     * Key for {@code minecraft:anvil}.
     */
    public static final TypedKey<Item> ANVIL = create("anvil");

    /**
     * Key for {@code minecraft:apple}.
     */
    public static final TypedKey<Item> APPLE = create("apple");

    /**
     * Key for {@code minecraft:archer_pottery_sherd}.
     */
    public static final TypedKey<Item> ARCHER_POTTERY_SHERD = create("archer_pottery_sherd");

    /**
     * Key for {@code minecraft:armadillo_scute}.
     */
    public static final TypedKey<Item> ARMADILLO_SCUTE = create("armadillo_scute");

    /**
     * Key for {@code minecraft:armadillo_spawn_egg}.
     */
    public static final TypedKey<Item> ARMADILLO_SPAWN_EGG = create("armadillo_spawn_egg");

    /**
     * Key for {@code minecraft:armor_stand}.
     */
    public static final TypedKey<Item> ARMOR_STAND = create("armor_stand");

    /**
     * Key for {@code minecraft:arms_up_pottery_sherd}.
     */
    public static final TypedKey<Item> ARMS_UP_POTTERY_SHERD = create("arms_up_pottery_sherd");

    /**
     * Key for {@code minecraft:arrow}.
     */
    public static final TypedKey<Item> ARROW = create("arrow");

    /**
     * Key for {@code minecraft:axolotl_bucket}.
     */
    public static final TypedKey<Item> AXOLOTL_BUCKET = create("axolotl_bucket");

    /**
     * Key for {@code minecraft:axolotl_spawn_egg}.
     */
    public static final TypedKey<Item> AXOLOTL_SPAWN_EGG = create("axolotl_spawn_egg");

    /**
     * Key for {@code minecraft:azalea}.
     */
    public static final TypedKey<Item> AZALEA = create("azalea");

    /**
     * Key for {@code minecraft:azalea_leaves}.
     */
    public static final TypedKey<Item> AZALEA_LEAVES = create("azalea_leaves");

    /**
     * Key for {@code minecraft:azure_bluet}.
     */
    public static final TypedKey<Item> AZURE_BLUET = create("azure_bluet");

    /**
     * Key for {@code minecraft:baked_potato}.
     */
    public static final TypedKey<Item> BAKED_POTATO = create("baked_potato");

    /**
     * Key for {@code minecraft:bamboo}.
     */
    public static final TypedKey<Item> BAMBOO = create("bamboo");

    /**
     * Key for {@code minecraft:bamboo_block}.
     */
    public static final TypedKey<Item> BAMBOO_BLOCK = create("bamboo_block");

    /**
     * Key for {@code minecraft:bamboo_button}.
     */
    public static final TypedKey<Item> BAMBOO_BUTTON = create("bamboo_button");

    /**
     * Key for {@code minecraft:bamboo_chest_raft}.
     */
    public static final TypedKey<Item> BAMBOO_CHEST_RAFT = create("bamboo_chest_raft");

    /**
     * Key for {@code minecraft:bamboo_door}.
     */
    public static final TypedKey<Item> BAMBOO_DOOR = create("bamboo_door");

    /**
     * Key for {@code minecraft:bamboo_fence}.
     */
    public static final TypedKey<Item> BAMBOO_FENCE = create("bamboo_fence");

    /**
     * Key for {@code minecraft:bamboo_fence_gate}.
     */
    public static final TypedKey<Item> BAMBOO_FENCE_GATE = create("bamboo_fence_gate");

    /**
     * Key for {@code minecraft:bamboo_hanging_sign}.
     */
    public static final TypedKey<Item> BAMBOO_HANGING_SIGN = create("bamboo_hanging_sign");

    /**
     * Key for {@code minecraft:bamboo_mosaic}.
     */
    public static final TypedKey<Item> BAMBOO_MOSAIC = create("bamboo_mosaic");

    /**
     * Key for {@code minecraft:bamboo_mosaic_slab}.
     */
    public static final TypedKey<Item> BAMBOO_MOSAIC_SLAB = create("bamboo_mosaic_slab");

    /**
     * Key for {@code minecraft:bamboo_mosaic_stairs}.
     */
    public static final TypedKey<Item> BAMBOO_MOSAIC_STAIRS = create("bamboo_mosaic_stairs");

    /**
     * Key for {@code minecraft:bamboo_planks}.
     */
    public static final TypedKey<Item> BAMBOO_PLANKS = create("bamboo_planks");

    /**
     * Key for {@code minecraft:bamboo_pressure_plate}.
     */
    public static final TypedKey<Item> BAMBOO_PRESSURE_PLATE = create("bamboo_pressure_plate");

    /**
     * Key for {@code minecraft:bamboo_raft}.
     */
    public static final TypedKey<Item> BAMBOO_RAFT = create("bamboo_raft");

    /**
     * Key for {@code minecraft:bamboo_shelf}.
     */
    public static final TypedKey<Item> BAMBOO_SHELF = create("bamboo_shelf");

    /**
     * Key for {@code minecraft:bamboo_sign}.
     */
    public static final TypedKey<Item> BAMBOO_SIGN = create("bamboo_sign");

    /**
     * Key for {@code minecraft:bamboo_slab}.
     */
    public static final TypedKey<Item> BAMBOO_SLAB = create("bamboo_slab");

    /**
     * Key for {@code minecraft:bamboo_stairs}.
     */
    public static final TypedKey<Item> BAMBOO_STAIRS = create("bamboo_stairs");

    /**
     * Key for {@code minecraft:bamboo_trapdoor}.
     */
    public static final TypedKey<Item> BAMBOO_TRAPDOOR = create("bamboo_trapdoor");

    /**
     * Key for {@code minecraft:barrel}.
     */
    public static final TypedKey<Item> BARREL = create("barrel");

    /**
     * Key for {@code minecraft:barrier}.
     */
    public static final TypedKey<Item> BARRIER = create("barrier");

    /**
     * Key for {@code minecraft:basalt}.
     */
    public static final TypedKey<Item> BASALT = create("basalt");

    /**
     * Key for {@code minecraft:bat_spawn_egg}.
     */
    public static final TypedKey<Item> BAT_SPAWN_EGG = create("bat_spawn_egg");

    /**
     * Key for {@code minecraft:beacon}.
     */
    public static final TypedKey<Item> BEACON = create("beacon");

    /**
     * Key for {@code minecraft:bedrock}.
     */
    public static final TypedKey<Item> BEDROCK = create("bedrock");

    /**
     * Key for {@code minecraft:bee_nest}.
     */
    public static final TypedKey<Item> BEE_NEST = create("bee_nest");

    /**
     * Key for {@code minecraft:bee_spawn_egg}.
     */
    public static final TypedKey<Item> BEE_SPAWN_EGG = create("bee_spawn_egg");

    /**
     * Key for {@code minecraft:beef}.
     */
    public static final TypedKey<Item> BEEF = create("beef");

    /**
     * Key for {@code minecraft:beehive}.
     */
    public static final TypedKey<Item> BEEHIVE = create("beehive");

    /**
     * Key for {@code minecraft:beetroot}.
     */
    public static final TypedKey<Item> BEETROOT = create("beetroot");

    /**
     * Key for {@code minecraft:beetroot_seeds}.
     */
    public static final TypedKey<Item> BEETROOT_SEEDS = create("beetroot_seeds");

    /**
     * Key for {@code minecraft:beetroot_soup}.
     */
    public static final TypedKey<Item> BEETROOT_SOUP = create("beetroot_soup");

    /**
     * Key for {@code minecraft:bell}.
     */
    public static final TypedKey<Item> BELL = create("bell");

    /**
     * Key for {@code minecraft:big_dripleaf}.
     */
    public static final TypedKey<Item> BIG_DRIPLEAF = create("big_dripleaf");

    /**
     * Key for {@code minecraft:birch_boat}.
     */
    public static final TypedKey<Item> BIRCH_BOAT = create("birch_boat");

    /**
     * Key for {@code minecraft:birch_button}.
     */
    public static final TypedKey<Item> BIRCH_BUTTON = create("birch_button");

    /**
     * Key for {@code minecraft:birch_chest_boat}.
     */
    public static final TypedKey<Item> BIRCH_CHEST_BOAT = create("birch_chest_boat");

    /**
     * Key for {@code minecraft:birch_door}.
     */
    public static final TypedKey<Item> BIRCH_DOOR = create("birch_door");

    /**
     * Key for {@code minecraft:birch_fence}.
     */
    public static final TypedKey<Item> BIRCH_FENCE = create("birch_fence");

    /**
     * Key for {@code minecraft:birch_fence_gate}.
     */
    public static final TypedKey<Item> BIRCH_FENCE_GATE = create("birch_fence_gate");

    /**
     * Key for {@code minecraft:birch_hanging_sign}.
     */
    public static final TypedKey<Item> BIRCH_HANGING_SIGN = create("birch_hanging_sign");

    /**
     * Key for {@code minecraft:birch_leaves}.
     */
    public static final TypedKey<Item> BIRCH_LEAVES = create("birch_leaves");

    /**
     * Key for {@code minecraft:birch_log}.
     */
    public static final TypedKey<Item> BIRCH_LOG = create("birch_log");

    /**
     * Key for {@code minecraft:birch_planks}.
     */
    public static final TypedKey<Item> BIRCH_PLANKS = create("birch_planks");

    /**
     * Key for {@code minecraft:birch_pressure_plate}.
     */
    public static final TypedKey<Item> BIRCH_PRESSURE_PLATE = create("birch_pressure_plate");

    /**
     * Key for {@code minecraft:birch_sapling}.
     */
    public static final TypedKey<Item> BIRCH_SAPLING = create("birch_sapling");

    /**
     * Key for {@code minecraft:birch_shelf}.
     */
    public static final TypedKey<Item> BIRCH_SHELF = create("birch_shelf");

    /**
     * Key for {@code minecraft:birch_sign}.
     */
    public static final TypedKey<Item> BIRCH_SIGN = create("birch_sign");

    /**
     * Key for {@code minecraft:birch_slab}.
     */
    public static final TypedKey<Item> BIRCH_SLAB = create("birch_slab");

    /**
     * Key for {@code minecraft:birch_stairs}.
     */
    public static final TypedKey<Item> BIRCH_STAIRS = create("birch_stairs");

    /**
     * Key for {@code minecraft:birch_trapdoor}.
     */
    public static final TypedKey<Item> BIRCH_TRAPDOOR = create("birch_trapdoor");

    /**
     * Key for {@code minecraft:birch_wood}.
     */
    public static final TypedKey<Item> BIRCH_WOOD = create("birch_wood");

    /**
     * Key for {@code minecraft:black_banner}.
     */
    public static final TypedKey<Item> BLACK_BANNER = create("black_banner");

    /**
     * Key for {@code minecraft:black_bed}.
     */
    public static final TypedKey<Item> BLACK_BED = create("black_bed");

    /**
     * Key for {@code minecraft:black_bundle}.
     */
    public static final TypedKey<Item> BLACK_BUNDLE = create("black_bundle");

    /**
     * Key for {@code minecraft:black_candle}.
     */
    public static final TypedKey<Item> BLACK_CANDLE = create("black_candle");

    /**
     * Key for {@code minecraft:black_carpet}.
     */
    public static final TypedKey<Item> BLACK_CARPET = create("black_carpet");

    /**
     * Key for {@code minecraft:black_concrete}.
     */
    public static final TypedKey<Item> BLACK_CONCRETE = create("black_concrete");

    /**
     * Key for {@code minecraft:black_concrete_powder}.
     */
    public static final TypedKey<Item> BLACK_CONCRETE_POWDER = create("black_concrete_powder");

    /**
     * Key for {@code minecraft:black_concrete_slab}.
     */
    public static final TypedKey<Item> BLACK_CONCRETE_SLAB = create("black_concrete_slab");

    /**
     * Key for {@code minecraft:black_concrete_stairs}.
     */
    public static final TypedKey<Item> BLACK_CONCRETE_STAIRS = create("black_concrete_stairs");

    /**
     * Key for {@code minecraft:black_cushion}.
     */
    public static final TypedKey<Item> BLACK_CUSHION = create("black_cushion");

    /**
     * Key for {@code minecraft:black_dye}.
     */
    public static final TypedKey<Item> BLACK_DYE = create("black_dye");

    /**
     * Key for {@code minecraft:black_glazed_terracotta}.
     */
    public static final TypedKey<Item> BLACK_GLAZED_TERRACOTTA = create("black_glazed_terracotta");

    /**
     * Key for {@code minecraft:black_harness}.
     */
    public static final TypedKey<Item> BLACK_HARNESS = create("black_harness");

    /**
     * Key for {@code minecraft:black_shulker_box}.
     */
    public static final TypedKey<Item> BLACK_SHULKER_BOX = create("black_shulker_box");

    /**
     * Key for {@code minecraft:black_stained_glass}.
     */
    public static final TypedKey<Item> BLACK_STAINED_GLASS = create("black_stained_glass");

    /**
     * Key for {@code minecraft:black_stained_glass_pane}.
     */
    public static final TypedKey<Item> BLACK_STAINED_GLASS_PANE = create("black_stained_glass_pane");

    /**
     * Key for {@code minecraft:black_terracotta}.
     */
    public static final TypedKey<Item> BLACK_TERRACOTTA = create("black_terracotta");

    /**
     * Key for {@code minecraft:black_wool}.
     */
    public static final TypedKey<Item> BLACK_WOOL = create("black_wool");

    /**
     * Key for {@code minecraft:black_wool_slab}.
     */
    public static final TypedKey<Item> BLACK_WOOL_SLAB = create("black_wool_slab");

    /**
     * Key for {@code minecraft:black_wool_stairs}.
     */
    public static final TypedKey<Item> BLACK_WOOL_STAIRS = create("black_wool_stairs");

    /**
     * Key for {@code minecraft:blackstone}.
     */
    public static final TypedKey<Item> BLACKSTONE = create("blackstone");

    /**
     * Key for {@code minecraft:blackstone_slab}.
     */
    public static final TypedKey<Item> BLACKSTONE_SLAB = create("blackstone_slab");

    /**
     * Key for {@code minecraft:blackstone_stairs}.
     */
    public static final TypedKey<Item> BLACKSTONE_STAIRS = create("blackstone_stairs");

    /**
     * Key for {@code minecraft:blackstone_wall}.
     */
    public static final TypedKey<Item> BLACKSTONE_WALL = create("blackstone_wall");

    /**
     * Key for {@code minecraft:blade_pottery_sherd}.
     */
    public static final TypedKey<Item> BLADE_POTTERY_SHERD = create("blade_pottery_sherd");

    /**
     * Key for {@code minecraft:blast_furnace}.
     */
    public static final TypedKey<Item> BLAST_FURNACE = create("blast_furnace");

    /**
     * Key for {@code minecraft:blaze_powder}.
     */
    public static final TypedKey<Item> BLAZE_POWDER = create("blaze_powder");

    /**
     * Key for {@code minecraft:blaze_rod}.
     */
    public static final TypedKey<Item> BLAZE_ROD = create("blaze_rod");

    /**
     * Key for {@code minecraft:blaze_spawn_egg}.
     */
    public static final TypedKey<Item> BLAZE_SPAWN_EGG = create("blaze_spawn_egg");

    /**
     * Key for {@code minecraft:blue_banner}.
     */
    public static final TypedKey<Item> BLUE_BANNER = create("blue_banner");

    /**
     * Key for {@code minecraft:blue_bed}.
     */
    public static final TypedKey<Item> BLUE_BED = create("blue_bed");

    /**
     * Key for {@code minecraft:blue_bundle}.
     */
    public static final TypedKey<Item> BLUE_BUNDLE = create("blue_bundle");

    /**
     * Key for {@code minecraft:blue_candle}.
     */
    public static final TypedKey<Item> BLUE_CANDLE = create("blue_candle");

    /**
     * Key for {@code minecraft:blue_carpet}.
     */
    public static final TypedKey<Item> BLUE_CARPET = create("blue_carpet");

    /**
     * Key for {@code minecraft:blue_concrete}.
     */
    public static final TypedKey<Item> BLUE_CONCRETE = create("blue_concrete");

    /**
     * Key for {@code minecraft:blue_concrete_powder}.
     */
    public static final TypedKey<Item> BLUE_CONCRETE_POWDER = create("blue_concrete_powder");

    /**
     * Key for {@code minecraft:blue_concrete_slab}.
     */
    public static final TypedKey<Item> BLUE_CONCRETE_SLAB = create("blue_concrete_slab");

    /**
     * Key for {@code minecraft:blue_concrete_stairs}.
     */
    public static final TypedKey<Item> BLUE_CONCRETE_STAIRS = create("blue_concrete_stairs");

    /**
     * Key for {@code minecraft:blue_cushion}.
     */
    public static final TypedKey<Item> BLUE_CUSHION = create("blue_cushion");

    /**
     * Key for {@code minecraft:blue_dye}.
     */
    public static final TypedKey<Item> BLUE_DYE = create("blue_dye");

    /**
     * Key for {@code minecraft:blue_egg}.
     */
    public static final TypedKey<Item> BLUE_EGG = create("blue_egg");

    /**
     * Key for {@code minecraft:blue_glazed_terracotta}.
     */
    public static final TypedKey<Item> BLUE_GLAZED_TERRACOTTA = create("blue_glazed_terracotta");

    /**
     * Key for {@code minecraft:blue_harness}.
     */
    public static final TypedKey<Item> BLUE_HARNESS = create("blue_harness");

    /**
     * Key for {@code minecraft:blue_ice}.
     */
    public static final TypedKey<Item> BLUE_ICE = create("blue_ice");

    /**
     * Key for {@code minecraft:blue_orchid}.
     */
    public static final TypedKey<Item> BLUE_ORCHID = create("blue_orchid");

    /**
     * Key for {@code minecraft:blue_shulker_box}.
     */
    public static final TypedKey<Item> BLUE_SHULKER_BOX = create("blue_shulker_box");

    /**
     * Key for {@code minecraft:blue_stained_glass}.
     */
    public static final TypedKey<Item> BLUE_STAINED_GLASS = create("blue_stained_glass");

    /**
     * Key for {@code minecraft:blue_stained_glass_pane}.
     */
    public static final TypedKey<Item> BLUE_STAINED_GLASS_PANE = create("blue_stained_glass_pane");

    /**
     * Key for {@code minecraft:blue_terracotta}.
     */
    public static final TypedKey<Item> BLUE_TERRACOTTA = create("blue_terracotta");

    /**
     * Key for {@code minecraft:blue_wool}.
     */
    public static final TypedKey<Item> BLUE_WOOL = create("blue_wool");

    /**
     * Key for {@code minecraft:blue_wool_slab}.
     */
    public static final TypedKey<Item> BLUE_WOOL_SLAB = create("blue_wool_slab");

    /**
     * Key for {@code minecraft:blue_wool_stairs}.
     */
    public static final TypedKey<Item> BLUE_WOOL_STAIRS = create("blue_wool_stairs");

    /**
     * Key for {@code minecraft:bogged_spawn_egg}.
     */
    public static final TypedKey<Item> BOGGED_SPAWN_EGG = create("bogged_spawn_egg");

    /**
     * Key for {@code minecraft:bolt_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> BOLT_ARMOR_TRIM_SMITHING_TEMPLATE = create("bolt_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:bone}.
     */
    public static final TypedKey<Item> BONE = create("bone");

    /**
     * Key for {@code minecraft:bone_block}.
     */
    public static final TypedKey<Item> BONE_BLOCK = create("bone_block");

    /**
     * Key for {@code minecraft:bone_meal}.
     */
    public static final TypedKey<Item> BONE_MEAL = create("bone_meal");

    /**
     * Key for {@code minecraft:book}.
     */
    public static final TypedKey<Item> BOOK = create("book");

    /**
     * Key for {@code minecraft:bookshelf}.
     */
    public static final TypedKey<Item> BOOKSHELF = create("bookshelf");

    /**
     * Key for {@code minecraft:bordure_indented_banner_pattern}.
     */
    public static final TypedKey<Item> BORDURE_INDENTED_BANNER_PATTERN = create("bordure_indented_banner_pattern");

    /**
     * Key for {@code minecraft:bow}.
     */
    public static final TypedKey<Item> BOW = create("bow");

    /**
     * Key for {@code minecraft:bowl}.
     */
    public static final TypedKey<Item> BOWL = create("bowl");

    /**
     * Key for {@code minecraft:brain_coral}.
     */
    public static final TypedKey<Item> BRAIN_CORAL = create("brain_coral");

    /**
     * Key for {@code minecraft:brain_coral_block}.
     */
    public static final TypedKey<Item> BRAIN_CORAL_BLOCK = create("brain_coral_block");

    /**
     * Key for {@code minecraft:brain_coral_fan}.
     */
    public static final TypedKey<Item> BRAIN_CORAL_FAN = create("brain_coral_fan");

    /**
     * Key for {@code minecraft:bread}.
     */
    public static final TypedKey<Item> BREAD = create("bread");

    /**
     * Key for {@code minecraft:breeze_rod}.
     */
    public static final TypedKey<Item> BREEZE_ROD = create("breeze_rod");

    /**
     * Key for {@code minecraft:breeze_spawn_egg}.
     */
    public static final TypedKey<Item> BREEZE_SPAWN_EGG = create("breeze_spawn_egg");

    /**
     * Key for {@code minecraft:brewer_pottery_sherd}.
     */
    public static final TypedKey<Item> BREWER_POTTERY_SHERD = create("brewer_pottery_sherd");

    /**
     * Key for {@code minecraft:brewing_stand}.
     */
    public static final TypedKey<Item> BREWING_STAND = create("brewing_stand");

    /**
     * Key for {@code minecraft:brick}.
     */
    public static final TypedKey<Item> BRICK = create("brick");

    /**
     * Key for {@code minecraft:brick_slab}.
     */
    public static final TypedKey<Item> BRICK_SLAB = create("brick_slab");

    /**
     * Key for {@code minecraft:brick_stairs}.
     */
    public static final TypedKey<Item> BRICK_STAIRS = create("brick_stairs");

    /**
     * Key for {@code minecraft:brick_wall}.
     */
    public static final TypedKey<Item> BRICK_WALL = create("brick_wall");

    /**
     * Key for {@code minecraft:bricks}.
     */
    public static final TypedKey<Item> BRICKS = create("bricks");

    /**
     * Key for {@code minecraft:brown_banner}.
     */
    public static final TypedKey<Item> BROWN_BANNER = create("brown_banner");

    /**
     * Key for {@code minecraft:brown_bed}.
     */
    public static final TypedKey<Item> BROWN_BED = create("brown_bed");

    /**
     * Key for {@code minecraft:brown_bundle}.
     */
    public static final TypedKey<Item> BROWN_BUNDLE = create("brown_bundle");

    /**
     * Key for {@code minecraft:brown_candle}.
     */
    public static final TypedKey<Item> BROWN_CANDLE = create("brown_candle");

    /**
     * Key for {@code minecraft:brown_carpet}.
     */
    public static final TypedKey<Item> BROWN_CARPET = create("brown_carpet");

    /**
     * Key for {@code minecraft:brown_concrete}.
     */
    public static final TypedKey<Item> BROWN_CONCRETE = create("brown_concrete");

    /**
     * Key for {@code minecraft:brown_concrete_powder}.
     */
    public static final TypedKey<Item> BROWN_CONCRETE_POWDER = create("brown_concrete_powder");

    /**
     * Key for {@code minecraft:brown_concrete_slab}.
     */
    public static final TypedKey<Item> BROWN_CONCRETE_SLAB = create("brown_concrete_slab");

    /**
     * Key for {@code minecraft:brown_concrete_stairs}.
     */
    public static final TypedKey<Item> BROWN_CONCRETE_STAIRS = create("brown_concrete_stairs");

    /**
     * Key for {@code minecraft:brown_cushion}.
     */
    public static final TypedKey<Item> BROWN_CUSHION = create("brown_cushion");

    /**
     * Key for {@code minecraft:brown_dye}.
     */
    public static final TypedKey<Item> BROWN_DYE = create("brown_dye");

    /**
     * Key for {@code minecraft:brown_egg}.
     */
    public static final TypedKey<Item> BROWN_EGG = create("brown_egg");

    /**
     * Key for {@code minecraft:brown_glazed_terracotta}.
     */
    public static final TypedKey<Item> BROWN_GLAZED_TERRACOTTA = create("brown_glazed_terracotta");

    /**
     * Key for {@code minecraft:brown_harness}.
     */
    public static final TypedKey<Item> BROWN_HARNESS = create("brown_harness");

    /**
     * Key for {@code minecraft:brown_mushroom}.
     */
    public static final TypedKey<Item> BROWN_MUSHROOM = create("brown_mushroom");

    /**
     * Key for {@code minecraft:brown_mushroom_block}.
     */
    public static final TypedKey<Item> BROWN_MUSHROOM_BLOCK = create("brown_mushroom_block");

    /**
     * Key for {@code minecraft:brown_shulker_box}.
     */
    public static final TypedKey<Item> BROWN_SHULKER_BOX = create("brown_shulker_box");

    /**
     * Key for {@code minecraft:brown_stained_glass}.
     */
    public static final TypedKey<Item> BROWN_STAINED_GLASS = create("brown_stained_glass");

    /**
     * Key for {@code minecraft:brown_stained_glass_pane}.
     */
    public static final TypedKey<Item> BROWN_STAINED_GLASS_PANE = create("brown_stained_glass_pane");

    /**
     * Key for {@code minecraft:brown_terracotta}.
     */
    public static final TypedKey<Item> BROWN_TERRACOTTA = create("brown_terracotta");

    /**
     * Key for {@code minecraft:brown_wool}.
     */
    public static final TypedKey<Item> BROWN_WOOL = create("brown_wool");

    /**
     * Key for {@code minecraft:brown_wool_slab}.
     */
    public static final TypedKey<Item> BROWN_WOOL_SLAB = create("brown_wool_slab");

    /**
     * Key for {@code minecraft:brown_wool_stairs}.
     */
    public static final TypedKey<Item> BROWN_WOOL_STAIRS = create("brown_wool_stairs");

    /**
     * Key for {@code minecraft:brush}.
     */
    public static final TypedKey<Item> BRUSH = create("brush");

    /**
     * Key for {@code minecraft:bubble_coral}.
     */
    public static final TypedKey<Item> BUBBLE_CORAL = create("bubble_coral");

    /**
     * Key for {@code minecraft:bubble_coral_block}.
     */
    public static final TypedKey<Item> BUBBLE_CORAL_BLOCK = create("bubble_coral_block");

    /**
     * Key for {@code minecraft:bubble_coral_fan}.
     */
    public static final TypedKey<Item> BUBBLE_CORAL_FAN = create("bubble_coral_fan");

    /**
     * Key for {@code minecraft:bucket}.
     */
    public static final TypedKey<Item> BUCKET = create("bucket");

    /**
     * Key for {@code minecraft:budding_amethyst}.
     */
    public static final TypedKey<Item> BUDDING_AMETHYST = create("budding_amethyst");

    /**
     * Key for {@code minecraft:bundle}.
     */
    public static final TypedKey<Item> BUNDLE = create("bundle");

    /**
     * Key for {@code minecraft:buried_ancient_city_map}.
     */
    public static final TypedKey<Item> BURIED_ANCIENT_CITY_MAP = create("buried_ancient_city_map");

    /**
     * Key for {@code minecraft:buried_mineshaft_map}.
     */
    public static final TypedKey<Item> BURIED_MINESHAFT_MAP = create("buried_mineshaft_map");

    /**
     * Key for {@code minecraft:buried_treasure_map}.
     */
    public static final TypedKey<Item> BURIED_TREASURE_MAP = create("buried_treasure_map");

    /**
     * Key for {@code minecraft:buried_trial_chambers_map}.
     */
    public static final TypedKey<Item> BURIED_TRIAL_CHAMBERS_MAP = create("buried_trial_chambers_map");

    /**
     * Key for {@code minecraft:burn_pottery_sherd}.
     */
    public static final TypedKey<Item> BURN_POTTERY_SHERD = create("burn_pottery_sherd");

    /**
     * Key for {@code minecraft:bush}.
     */
    public static final TypedKey<Item> BUSH = create("bush");

    /**
     * Key for {@code minecraft:cactus}.
     */
    public static final TypedKey<Item> CACTUS = create("cactus");

    /**
     * Key for {@code minecraft:cactus_flower}.
     */
    public static final TypedKey<Item> CACTUS_FLOWER = create("cactus_flower");

    /**
     * Key for {@code minecraft:cake}.
     */
    public static final TypedKey<Item> CAKE = create("cake");

    /**
     * Key for {@code minecraft:calcite}.
     */
    public static final TypedKey<Item> CALCITE = create("calcite");

    /**
     * Key for {@code minecraft:calibrated_sculk_sensor}.
     */
    public static final TypedKey<Item> CALIBRATED_SCULK_SENSOR = create("calibrated_sculk_sensor");

    /**
     * Key for {@code minecraft:camel_husk_spawn_egg}.
     */
    public static final TypedKey<Item> CAMEL_HUSK_SPAWN_EGG = create("camel_husk_spawn_egg");

    /**
     * Key for {@code minecraft:camel_spawn_egg}.
     */
    public static final TypedKey<Item> CAMEL_SPAWN_EGG = create("camel_spawn_egg");

    /**
     * Key for {@code minecraft:campfire}.
     */
    public static final TypedKey<Item> CAMPFIRE = create("campfire");

    /**
     * Key for {@code minecraft:candle}.
     */
    public static final TypedKey<Item> CANDLE = create("candle");

    /**
     * Key for {@code minecraft:carrot}.
     */
    public static final TypedKey<Item> CARROT = create("carrot");

    /**
     * Key for {@code minecraft:carrot_on_a_stick}.
     */
    public static final TypedKey<Item> CARROT_ON_A_STICK = create("carrot_on_a_stick");

    /**
     * Key for {@code minecraft:cartography_table}.
     */
    public static final TypedKey<Item> CARTOGRAPHY_TABLE = create("cartography_table");

    /**
     * Key for {@code minecraft:carved_pumpkin}.
     */
    public static final TypedKey<Item> CARVED_PUMPKIN = create("carved_pumpkin");

    /**
     * Key for {@code minecraft:cat_spawn_egg}.
     */
    public static final TypedKey<Item> CAT_SPAWN_EGG = create("cat_spawn_egg");

    /**
     * Key for {@code minecraft:cauldron}.
     */
    public static final TypedKey<Item> CAULDRON = create("cauldron");

    /**
     * Key for {@code minecraft:cave_spider_spawn_egg}.
     */
    public static final TypedKey<Item> CAVE_SPIDER_SPAWN_EGG = create("cave_spider_spawn_egg");

    /**
     * Key for {@code minecraft:chain_command_block}.
     */
    public static final TypedKey<Item> CHAIN_COMMAND_BLOCK = create("chain_command_block");

    /**
     * Key for {@code minecraft:chainmail_boots}.
     */
    public static final TypedKey<Item> CHAINMAIL_BOOTS = create("chainmail_boots");

    /**
     * Key for {@code minecraft:chainmail_chestplate}.
     */
    public static final TypedKey<Item> CHAINMAIL_CHESTPLATE = create("chainmail_chestplate");

    /**
     * Key for {@code minecraft:chainmail_helmet}.
     */
    public static final TypedKey<Item> CHAINMAIL_HELMET = create("chainmail_helmet");

    /**
     * Key for {@code minecraft:chainmail_leggings}.
     */
    public static final TypedKey<Item> CHAINMAIL_LEGGINGS = create("chainmail_leggings");

    /**
     * Key for {@code minecraft:charcoal}.
     */
    public static final TypedKey<Item> CHARCOAL = create("charcoal");

    /**
     * Key for {@code minecraft:cherry_boat}.
     */
    public static final TypedKey<Item> CHERRY_BOAT = create("cherry_boat");

    /**
     * Key for {@code minecraft:cherry_button}.
     */
    public static final TypedKey<Item> CHERRY_BUTTON = create("cherry_button");

    /**
     * Key for {@code minecraft:cherry_chest_boat}.
     */
    public static final TypedKey<Item> CHERRY_CHEST_BOAT = create("cherry_chest_boat");

    /**
     * Key for {@code minecraft:cherry_door}.
     */
    public static final TypedKey<Item> CHERRY_DOOR = create("cherry_door");

    /**
     * Key for {@code minecraft:cherry_fence}.
     */
    public static final TypedKey<Item> CHERRY_FENCE = create("cherry_fence");

    /**
     * Key for {@code minecraft:cherry_fence_gate}.
     */
    public static final TypedKey<Item> CHERRY_FENCE_GATE = create("cherry_fence_gate");

    /**
     * Key for {@code minecraft:cherry_hanging_sign}.
     */
    public static final TypedKey<Item> CHERRY_HANGING_SIGN = create("cherry_hanging_sign");

    /**
     * Key for {@code minecraft:cherry_leaves}.
     */
    public static final TypedKey<Item> CHERRY_LEAVES = create("cherry_leaves");

    /**
     * Key for {@code minecraft:cherry_log}.
     */
    public static final TypedKey<Item> CHERRY_LOG = create("cherry_log");

    /**
     * Key for {@code minecraft:cherry_planks}.
     */
    public static final TypedKey<Item> CHERRY_PLANKS = create("cherry_planks");

    /**
     * Key for {@code minecraft:cherry_pressure_plate}.
     */
    public static final TypedKey<Item> CHERRY_PRESSURE_PLATE = create("cherry_pressure_plate");

    /**
     * Key for {@code minecraft:cherry_sapling}.
     */
    public static final TypedKey<Item> CHERRY_SAPLING = create("cherry_sapling");

    /**
     * Key for {@code minecraft:cherry_shelf}.
     */
    public static final TypedKey<Item> CHERRY_SHELF = create("cherry_shelf");

    /**
     * Key for {@code minecraft:cherry_sign}.
     */
    public static final TypedKey<Item> CHERRY_SIGN = create("cherry_sign");

    /**
     * Key for {@code minecraft:cherry_slab}.
     */
    public static final TypedKey<Item> CHERRY_SLAB = create("cherry_slab");

    /**
     * Key for {@code minecraft:cherry_stairs}.
     */
    public static final TypedKey<Item> CHERRY_STAIRS = create("cherry_stairs");

    /**
     * Key for {@code minecraft:cherry_trapdoor}.
     */
    public static final TypedKey<Item> CHERRY_TRAPDOOR = create("cherry_trapdoor");

    /**
     * Key for {@code minecraft:cherry_wood}.
     */
    public static final TypedKey<Item> CHERRY_WOOD = create("cherry_wood");

    /**
     * Key for {@code minecraft:chest}.
     */
    public static final TypedKey<Item> CHEST = create("chest");

    /**
     * Key for {@code minecraft:chest_minecart}.
     */
    public static final TypedKey<Item> CHEST_MINECART = create("chest_minecart");

    /**
     * Key for {@code minecraft:chicken}.
     */
    public static final TypedKey<Item> CHICKEN = create("chicken");

    /**
     * Key for {@code minecraft:chicken_spawn_egg}.
     */
    public static final TypedKey<Item> CHICKEN_SPAWN_EGG = create("chicken_spawn_egg");

    /**
     * Key for {@code minecraft:chipped_anvil}.
     */
    public static final TypedKey<Item> CHIPPED_ANVIL = create("chipped_anvil");

    /**
     * Key for {@code minecraft:chiseled_bookshelf}.
     */
    public static final TypedKey<Item> CHISELED_BOOKSHELF = create("chiseled_bookshelf");

    /**
     * Key for {@code minecraft:chiseled_cinnabar}.
     */
    public static final TypedKey<Item> CHISELED_CINNABAR = create("chiseled_cinnabar");

    /**
     * Key for {@code minecraft:chiseled_copper}.
     */
    public static final TypedKey<Item> CHISELED_COPPER = create("chiseled_copper");

    /**
     * Key for {@code minecraft:chiseled_deepslate}.
     */
    public static final TypedKey<Item> CHISELED_DEEPSLATE = create("chiseled_deepslate");

    /**
     * Key for {@code minecraft:chiseled_nether_bricks}.
     */
    public static final TypedKey<Item> CHISELED_NETHER_BRICKS = create("chiseled_nether_bricks");

    /**
     * Key for {@code minecraft:chiseled_polished_blackstone}.
     */
    public static final TypedKey<Item> CHISELED_POLISHED_BLACKSTONE = create("chiseled_polished_blackstone");

    /**
     * Key for {@code minecraft:chiseled_quartz_block}.
     */
    public static final TypedKey<Item> CHISELED_QUARTZ_BLOCK = create("chiseled_quartz_block");

    /**
     * Key for {@code minecraft:chiseled_red_sandstone}.
     */
    public static final TypedKey<Item> CHISELED_RED_SANDSTONE = create("chiseled_red_sandstone");

    /**
     * Key for {@code minecraft:chiseled_resin_bricks}.
     */
    public static final TypedKey<Item> CHISELED_RESIN_BRICKS = create("chiseled_resin_bricks");

    /**
     * Key for {@code minecraft:chiseled_sandstone}.
     */
    public static final TypedKey<Item> CHISELED_SANDSTONE = create("chiseled_sandstone");

    /**
     * Key for {@code minecraft:chiseled_stone_bricks}.
     */
    public static final TypedKey<Item> CHISELED_STONE_BRICKS = create("chiseled_stone_bricks");

    /**
     * Key for {@code minecraft:chiseled_sulfur}.
     */
    public static final TypedKey<Item> CHISELED_SULFUR = create("chiseled_sulfur");

    /**
     * Key for {@code minecraft:chiseled_tuff}.
     */
    public static final TypedKey<Item> CHISELED_TUFF = create("chiseled_tuff");

    /**
     * Key for {@code minecraft:chiseled_tuff_bricks}.
     */
    public static final TypedKey<Item> CHISELED_TUFF_BRICKS = create("chiseled_tuff_bricks");

    /**
     * Key for {@code minecraft:chorus_flower}.
     */
    public static final TypedKey<Item> CHORUS_FLOWER = create("chorus_flower");

    /**
     * Key for {@code minecraft:chorus_fruit}.
     */
    public static final TypedKey<Item> CHORUS_FRUIT = create("chorus_fruit");

    /**
     * Key for {@code minecraft:chorus_plant}.
     */
    public static final TypedKey<Item> CHORUS_PLANT = create("chorus_plant");

    /**
     * Key for {@code minecraft:cinnabar}.
     */
    public static final TypedKey<Item> CINNABAR = create("cinnabar");

    /**
     * Key for {@code minecraft:cinnabar_brick_slab}.
     */
    public static final TypedKey<Item> CINNABAR_BRICK_SLAB = create("cinnabar_brick_slab");

    /**
     * Key for {@code minecraft:cinnabar_brick_stairs}.
     */
    public static final TypedKey<Item> CINNABAR_BRICK_STAIRS = create("cinnabar_brick_stairs");

    /**
     * Key for {@code minecraft:cinnabar_brick_wall}.
     */
    public static final TypedKey<Item> CINNABAR_BRICK_WALL = create("cinnabar_brick_wall");

    /**
     * Key for {@code minecraft:cinnabar_bricks}.
     */
    public static final TypedKey<Item> CINNABAR_BRICKS = create("cinnabar_bricks");

    /**
     * Key for {@code minecraft:cinnabar_slab}.
     */
    public static final TypedKey<Item> CINNABAR_SLAB = create("cinnabar_slab");

    /**
     * Key for {@code minecraft:cinnabar_stairs}.
     */
    public static final TypedKey<Item> CINNABAR_STAIRS = create("cinnabar_stairs");

    /**
     * Key for {@code minecraft:cinnabar_wall}.
     */
    public static final TypedKey<Item> CINNABAR_WALL = create("cinnabar_wall");

    /**
     * Key for {@code minecraft:clay}.
     */
    public static final TypedKey<Item> CLAY = create("clay");

    /**
     * Key for {@code minecraft:clay_ball}.
     */
    public static final TypedKey<Item> CLAY_BALL = create("clay_ball");

    /**
     * Key for {@code minecraft:clock}.
     */
    public static final TypedKey<Item> CLOCK = create("clock");

    /**
     * Key for {@code minecraft:closed_eyeblossom}.
     */
    public static final TypedKey<Item> CLOSED_EYEBLOSSOM = create("closed_eyeblossom");

    /**
     * Key for {@code minecraft:coal}.
     */
    public static final TypedKey<Item> COAL = create("coal");

    /**
     * Key for {@code minecraft:coal_block}.
     */
    public static final TypedKey<Item> COAL_BLOCK = create("coal_block");

    /**
     * Key for {@code minecraft:coal_ore}.
     */
    public static final TypedKey<Item> COAL_ORE = create("coal_ore");

    /**
     * Key for {@code minecraft:coarse_dirt}.
     */
    public static final TypedKey<Item> COARSE_DIRT = create("coarse_dirt");

    /**
     * Key for {@code minecraft:coast_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> COAST_ARMOR_TRIM_SMITHING_TEMPLATE = create("coast_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:cobbled_deepslate}.
     */
    public static final TypedKey<Item> COBBLED_DEEPSLATE = create("cobbled_deepslate");

    /**
     * Key for {@code minecraft:cobbled_deepslate_slab}.
     */
    public static final TypedKey<Item> COBBLED_DEEPSLATE_SLAB = create("cobbled_deepslate_slab");

    /**
     * Key for {@code minecraft:cobbled_deepslate_stairs}.
     */
    public static final TypedKey<Item> COBBLED_DEEPSLATE_STAIRS = create("cobbled_deepslate_stairs");

    /**
     * Key for {@code minecraft:cobbled_deepslate_wall}.
     */
    public static final TypedKey<Item> COBBLED_DEEPSLATE_WALL = create("cobbled_deepslate_wall");

    /**
     * Key for {@code minecraft:cobblestone}.
     */
    public static final TypedKey<Item> COBBLESTONE = create("cobblestone");

    /**
     * Key for {@code minecraft:cobblestone_slab}.
     */
    public static final TypedKey<Item> COBBLESTONE_SLAB = create("cobblestone_slab");

    /**
     * Key for {@code minecraft:cobblestone_stairs}.
     */
    public static final TypedKey<Item> COBBLESTONE_STAIRS = create("cobblestone_stairs");

    /**
     * Key for {@code minecraft:cobblestone_wall}.
     */
    public static final TypedKey<Item> COBBLESTONE_WALL = create("cobblestone_wall");

    /**
     * Key for {@code minecraft:cobweb}.
     */
    public static final TypedKey<Item> COBWEB = create("cobweb");

    /**
     * Key for {@code minecraft:cocoa_beans}.
     */
    public static final TypedKey<Item> COCOA_BEANS = create("cocoa_beans");

    /**
     * Key for {@code minecraft:cod}.
     */
    public static final TypedKey<Item> COD = create("cod");

    /**
     * Key for {@code minecraft:cod_bucket}.
     */
    public static final TypedKey<Item> COD_BUCKET = create("cod_bucket");

    /**
     * Key for {@code minecraft:cod_spawn_egg}.
     */
    public static final TypedKey<Item> COD_SPAWN_EGG = create("cod_spawn_egg");

    /**
     * Key for {@code minecraft:command_block}.
     */
    public static final TypedKey<Item> COMMAND_BLOCK = create("command_block");

    /**
     * Key for {@code minecraft:command_block_minecart}.
     */
    public static final TypedKey<Item> COMMAND_BLOCK_MINECART = create("command_block_minecart");

    /**
     * Key for {@code minecraft:comparator}.
     */
    public static final TypedKey<Item> COMPARATOR = create("comparator");

    /**
     * Key for {@code minecraft:compass}.
     */
    public static final TypedKey<Item> COMPASS = create("compass");

    /**
     * Key for {@code minecraft:composter}.
     */
    public static final TypedKey<Item> COMPOSTER = create("composter");

    /**
     * Key for {@code minecraft:conduit}.
     */
    public static final TypedKey<Item> CONDUIT = create("conduit");

    /**
     * Key for {@code minecraft:cooked_beef}.
     */
    public static final TypedKey<Item> COOKED_BEEF = create("cooked_beef");

    /**
     * Key for {@code minecraft:cooked_chicken}.
     */
    public static final TypedKey<Item> COOKED_CHICKEN = create("cooked_chicken");

    /**
     * Key for {@code minecraft:cooked_cod}.
     */
    public static final TypedKey<Item> COOKED_COD = create("cooked_cod");

    /**
     * Key for {@code minecraft:cooked_mutton}.
     */
    public static final TypedKey<Item> COOKED_MUTTON = create("cooked_mutton");

    /**
     * Key for {@code minecraft:cooked_porkchop}.
     */
    public static final TypedKey<Item> COOKED_PORKCHOP = create("cooked_porkchop");

    /**
     * Key for {@code minecraft:cooked_rabbit}.
     */
    public static final TypedKey<Item> COOKED_RABBIT = create("cooked_rabbit");

    /**
     * Key for {@code minecraft:cooked_salmon}.
     */
    public static final TypedKey<Item> COOKED_SALMON = create("cooked_salmon");

    /**
     * Key for {@code minecraft:cookie}.
     */
    public static final TypedKey<Item> COOKIE = create("cookie");

    /**
     * Key for {@code minecraft:copper_axe}.
     */
    public static final TypedKey<Item> COPPER_AXE = create("copper_axe");

    /**
     * Key for {@code minecraft:copper_bars}.
     */
    public static final TypedKey<Item> COPPER_BARS = create("copper_bars");

    /**
     * Key for {@code minecraft:copper_block}.
     */
    public static final TypedKey<Item> COPPER_BLOCK = create("copper_block");

    /**
     * Key for {@code minecraft:copper_boots}.
     */
    public static final TypedKey<Item> COPPER_BOOTS = create("copper_boots");

    /**
     * Key for {@code minecraft:copper_bulb}.
     */
    public static final TypedKey<Item> COPPER_BULB = create("copper_bulb");

    /**
     * Key for {@code minecraft:copper_chain}.
     */
    public static final TypedKey<Item> COPPER_CHAIN = create("copper_chain");

    /**
     * Key for {@code minecraft:copper_chest}.
     */
    public static final TypedKey<Item> COPPER_CHEST = create("copper_chest");

    /**
     * Key for {@code minecraft:copper_chestplate}.
     */
    public static final TypedKey<Item> COPPER_CHESTPLATE = create("copper_chestplate");

    /**
     * Key for {@code minecraft:copper_door}.
     */
    public static final TypedKey<Item> COPPER_DOOR = create("copper_door");

    /**
     * Key for {@code minecraft:copper_golem_spawn_egg}.
     */
    public static final TypedKey<Item> COPPER_GOLEM_SPAWN_EGG = create("copper_golem_spawn_egg");

    /**
     * Key for {@code minecraft:copper_golem_statue}.
     */
    public static final TypedKey<Item> COPPER_GOLEM_STATUE = create("copper_golem_statue");

    /**
     * Key for {@code minecraft:copper_grate}.
     */
    public static final TypedKey<Item> COPPER_GRATE = create("copper_grate");

    /**
     * Key for {@code minecraft:copper_helmet}.
     */
    public static final TypedKey<Item> COPPER_HELMET = create("copper_helmet");

    /**
     * Key for {@code minecraft:copper_hoe}.
     */
    public static final TypedKey<Item> COPPER_HOE = create("copper_hoe");

    /**
     * Key for {@code minecraft:copper_horse_armor}.
     */
    public static final TypedKey<Item> COPPER_HORSE_ARMOR = create("copper_horse_armor");

    /**
     * Key for {@code minecraft:copper_ingot}.
     */
    public static final TypedKey<Item> COPPER_INGOT = create("copper_ingot");

    /**
     * Key for {@code minecraft:copper_lantern}.
     */
    public static final TypedKey<Item> COPPER_LANTERN = create("copper_lantern");

    /**
     * Key for {@code minecraft:copper_leggings}.
     */
    public static final TypedKey<Item> COPPER_LEGGINGS = create("copper_leggings");

    /**
     * Key for {@code minecraft:copper_nautilus_armor}.
     */
    public static final TypedKey<Item> COPPER_NAUTILUS_ARMOR = create("copper_nautilus_armor");

    /**
     * Key for {@code minecraft:copper_nugget}.
     */
    public static final TypedKey<Item> COPPER_NUGGET = create("copper_nugget");

    /**
     * Key for {@code minecraft:copper_ore}.
     */
    public static final TypedKey<Item> COPPER_ORE = create("copper_ore");

    /**
     * Key for {@code minecraft:copper_pickaxe}.
     */
    public static final TypedKey<Item> COPPER_PICKAXE = create("copper_pickaxe");

    /**
     * Key for {@code minecraft:copper_shovel}.
     */
    public static final TypedKey<Item> COPPER_SHOVEL = create("copper_shovel");

    /**
     * Key for {@code minecraft:copper_spear}.
     */
    public static final TypedKey<Item> COPPER_SPEAR = create("copper_spear");

    /**
     * Key for {@code minecraft:copper_sword}.
     */
    public static final TypedKey<Item> COPPER_SWORD = create("copper_sword");

    /**
     * Key for {@code minecraft:copper_torch}.
     */
    public static final TypedKey<Item> COPPER_TORCH = create("copper_torch");

    /**
     * Key for {@code minecraft:copper_trapdoor}.
     */
    public static final TypedKey<Item> COPPER_TRAPDOOR = create("copper_trapdoor");

    /**
     * Key for {@code minecraft:cornflower}.
     */
    public static final TypedKey<Item> CORNFLOWER = create("cornflower");

    /**
     * Key for {@code minecraft:cow_spawn_egg}.
     */
    public static final TypedKey<Item> COW_SPAWN_EGG = create("cow_spawn_egg");

    /**
     * Key for {@code minecraft:cracked_deepslate_bricks}.
     */
    public static final TypedKey<Item> CRACKED_DEEPSLATE_BRICKS = create("cracked_deepslate_bricks");

    /**
     * Key for {@code minecraft:cracked_deepslate_tiles}.
     */
    public static final TypedKey<Item> CRACKED_DEEPSLATE_TILES = create("cracked_deepslate_tiles");

    /**
     * Key for {@code minecraft:cracked_nether_bricks}.
     */
    public static final TypedKey<Item> CRACKED_NETHER_BRICKS = create("cracked_nether_bricks");

    /**
     * Key for {@code minecraft:cracked_polished_blackstone_bricks}.
     */
    public static final TypedKey<Item> CRACKED_POLISHED_BLACKSTONE_BRICKS = create("cracked_polished_blackstone_bricks");

    /**
     * Key for {@code minecraft:cracked_stone_bricks}.
     */
    public static final TypedKey<Item> CRACKED_STONE_BRICKS = create("cracked_stone_bricks");

    /**
     * Key for {@code minecraft:crafter}.
     */
    public static final TypedKey<Item> CRAFTER = create("crafter");

    /**
     * Key for {@code minecraft:crafting_table}.
     */
    public static final TypedKey<Item> CRAFTING_TABLE = create("crafting_table");

    /**
     * Key for {@code minecraft:creaking_heart}.
     */
    public static final TypedKey<Item> CREAKING_HEART = create("creaking_heart");

    /**
     * Key for {@code minecraft:creaking_spawn_egg}.
     */
    public static final TypedKey<Item> CREAKING_SPAWN_EGG = create("creaking_spawn_egg");

    /**
     * Key for {@code minecraft:creeper_banner_pattern}.
     */
    public static final TypedKey<Item> CREEPER_BANNER_PATTERN = create("creeper_banner_pattern");

    /**
     * Key for {@code minecraft:creeper_head}.
     */
    public static final TypedKey<Item> CREEPER_HEAD = create("creeper_head");

    /**
     * Key for {@code minecraft:creeper_spawn_egg}.
     */
    public static final TypedKey<Item> CREEPER_SPAWN_EGG = create("creeper_spawn_egg");

    /**
     * Key for {@code minecraft:crimson_button}.
     */
    public static final TypedKey<Item> CRIMSON_BUTTON = create("crimson_button");

    /**
     * Key for {@code minecraft:crimson_door}.
     */
    public static final TypedKey<Item> CRIMSON_DOOR = create("crimson_door");

    /**
     * Key for {@code minecraft:crimson_fence}.
     */
    public static final TypedKey<Item> CRIMSON_FENCE = create("crimson_fence");

    /**
     * Key for {@code minecraft:crimson_fence_gate}.
     */
    public static final TypedKey<Item> CRIMSON_FENCE_GATE = create("crimson_fence_gate");

    /**
     * Key for {@code minecraft:crimson_fungus}.
     */
    public static final TypedKey<Item> CRIMSON_FUNGUS = create("crimson_fungus");

    /**
     * Key for {@code minecraft:crimson_hanging_sign}.
     */
    public static final TypedKey<Item> CRIMSON_HANGING_SIGN = create("crimson_hanging_sign");

    /**
     * Key for {@code minecraft:crimson_hyphae}.
     */
    public static final TypedKey<Item> CRIMSON_HYPHAE = create("crimson_hyphae");

    /**
     * Key for {@code minecraft:crimson_nylium}.
     */
    public static final TypedKey<Item> CRIMSON_NYLIUM = create("crimson_nylium");

    /**
     * Key for {@code minecraft:crimson_planks}.
     */
    public static final TypedKey<Item> CRIMSON_PLANKS = create("crimson_planks");

    /**
     * Key for {@code minecraft:crimson_pressure_plate}.
     */
    public static final TypedKey<Item> CRIMSON_PRESSURE_PLATE = create("crimson_pressure_plate");

    /**
     * Key for {@code minecraft:crimson_roots}.
     */
    public static final TypedKey<Item> CRIMSON_ROOTS = create("crimson_roots");

    /**
     * Key for {@code minecraft:crimson_shelf}.
     */
    public static final TypedKey<Item> CRIMSON_SHELF = create("crimson_shelf");

    /**
     * Key for {@code minecraft:crimson_sign}.
     */
    public static final TypedKey<Item> CRIMSON_SIGN = create("crimson_sign");

    /**
     * Key for {@code minecraft:crimson_slab}.
     */
    public static final TypedKey<Item> CRIMSON_SLAB = create("crimson_slab");

    /**
     * Key for {@code minecraft:crimson_stairs}.
     */
    public static final TypedKey<Item> CRIMSON_STAIRS = create("crimson_stairs");

    /**
     * Key for {@code minecraft:crimson_stem}.
     */
    public static final TypedKey<Item> CRIMSON_STEM = create("crimson_stem");

    /**
     * Key for {@code minecraft:crimson_trapdoor}.
     */
    public static final TypedKey<Item> CRIMSON_TRAPDOOR = create("crimson_trapdoor");

    /**
     * Key for {@code minecraft:crossbow}.
     */
    public static final TypedKey<Item> CROSSBOW = create("crossbow");

    /**
     * Key for {@code minecraft:crying_obsidian}.
     */
    public static final TypedKey<Item> CRYING_OBSIDIAN = create("crying_obsidian");

    /**
     * Key for {@code minecraft:cut_copper}.
     */
    public static final TypedKey<Item> CUT_COPPER = create("cut_copper");

    /**
     * Key for {@code minecraft:cut_copper_slab}.
     */
    public static final TypedKey<Item> CUT_COPPER_SLAB = create("cut_copper_slab");

    /**
     * Key for {@code minecraft:cut_copper_stairs}.
     */
    public static final TypedKey<Item> CUT_COPPER_STAIRS = create("cut_copper_stairs");

    /**
     * Key for {@code minecraft:cut_red_sandstone}.
     */
    public static final TypedKey<Item> CUT_RED_SANDSTONE = create("cut_red_sandstone");

    /**
     * Key for {@code minecraft:cut_red_sandstone_slab}.
     */
    public static final TypedKey<Item> CUT_RED_SANDSTONE_SLAB = create("cut_red_sandstone_slab");

    /**
     * Key for {@code minecraft:cut_sandstone}.
     */
    public static final TypedKey<Item> CUT_SANDSTONE = create("cut_sandstone");

    /**
     * Key for {@code minecraft:cut_sandstone_slab}.
     */
    public static final TypedKey<Item> CUT_SANDSTONE_SLAB = create("cut_sandstone_slab");

    /**
     * Key for {@code minecraft:cyan_banner}.
     */
    public static final TypedKey<Item> CYAN_BANNER = create("cyan_banner");

    /**
     * Key for {@code minecraft:cyan_bed}.
     */
    public static final TypedKey<Item> CYAN_BED = create("cyan_bed");

    /**
     * Key for {@code minecraft:cyan_bundle}.
     */
    public static final TypedKey<Item> CYAN_BUNDLE = create("cyan_bundle");

    /**
     * Key for {@code minecraft:cyan_candle}.
     */
    public static final TypedKey<Item> CYAN_CANDLE = create("cyan_candle");

    /**
     * Key for {@code minecraft:cyan_carpet}.
     */
    public static final TypedKey<Item> CYAN_CARPET = create("cyan_carpet");

    /**
     * Key for {@code minecraft:cyan_concrete}.
     */
    public static final TypedKey<Item> CYAN_CONCRETE = create("cyan_concrete");

    /**
     * Key for {@code minecraft:cyan_concrete_powder}.
     */
    public static final TypedKey<Item> CYAN_CONCRETE_POWDER = create("cyan_concrete_powder");

    /**
     * Key for {@code minecraft:cyan_concrete_slab}.
     */
    public static final TypedKey<Item> CYAN_CONCRETE_SLAB = create("cyan_concrete_slab");

    /**
     * Key for {@code minecraft:cyan_concrete_stairs}.
     */
    public static final TypedKey<Item> CYAN_CONCRETE_STAIRS = create("cyan_concrete_stairs");

    /**
     * Key for {@code minecraft:cyan_cushion}.
     */
    public static final TypedKey<Item> CYAN_CUSHION = create("cyan_cushion");

    /**
     * Key for {@code minecraft:cyan_dye}.
     */
    public static final TypedKey<Item> CYAN_DYE = create("cyan_dye");

    /**
     * Key for {@code minecraft:cyan_glazed_terracotta}.
     */
    public static final TypedKey<Item> CYAN_GLAZED_TERRACOTTA = create("cyan_glazed_terracotta");

    /**
     * Key for {@code minecraft:cyan_harness}.
     */
    public static final TypedKey<Item> CYAN_HARNESS = create("cyan_harness");

    /**
     * Key for {@code minecraft:cyan_shulker_box}.
     */
    public static final TypedKey<Item> CYAN_SHULKER_BOX = create("cyan_shulker_box");

    /**
     * Key for {@code minecraft:cyan_stained_glass}.
     */
    public static final TypedKey<Item> CYAN_STAINED_GLASS = create("cyan_stained_glass");

    /**
     * Key for {@code minecraft:cyan_stained_glass_pane}.
     */
    public static final TypedKey<Item> CYAN_STAINED_GLASS_PANE = create("cyan_stained_glass_pane");

    /**
     * Key for {@code minecraft:cyan_terracotta}.
     */
    public static final TypedKey<Item> CYAN_TERRACOTTA = create("cyan_terracotta");

    /**
     * Key for {@code minecraft:cyan_wool}.
     */
    public static final TypedKey<Item> CYAN_WOOL = create("cyan_wool");

    /**
     * Key for {@code minecraft:cyan_wool_slab}.
     */
    public static final TypedKey<Item> CYAN_WOOL_SLAB = create("cyan_wool_slab");

    /**
     * Key for {@code minecraft:cyan_wool_stairs}.
     */
    public static final TypedKey<Item> CYAN_WOOL_STAIRS = create("cyan_wool_stairs");

    /**
     * Key for {@code minecraft:damaged_anvil}.
     */
    public static final TypedKey<Item> DAMAGED_ANVIL = create("damaged_anvil");

    /**
     * Key for {@code minecraft:dandelion}.
     */
    public static final TypedKey<Item> DANDELION = create("dandelion");

    /**
     * Key for {@code minecraft:danger_pottery_sherd}.
     */
    public static final TypedKey<Item> DANGER_POTTERY_SHERD = create("danger_pottery_sherd");

    /**
     * Key for {@code minecraft:dark_oak_boat}.
     */
    public static final TypedKey<Item> DARK_OAK_BOAT = create("dark_oak_boat");

    /**
     * Key for {@code minecraft:dark_oak_button}.
     */
    public static final TypedKey<Item> DARK_OAK_BUTTON = create("dark_oak_button");

    /**
     * Key for {@code minecraft:dark_oak_chest_boat}.
     */
    public static final TypedKey<Item> DARK_OAK_CHEST_BOAT = create("dark_oak_chest_boat");

    /**
     * Key for {@code minecraft:dark_oak_door}.
     */
    public static final TypedKey<Item> DARK_OAK_DOOR = create("dark_oak_door");

    /**
     * Key for {@code minecraft:dark_oak_fence}.
     */
    public static final TypedKey<Item> DARK_OAK_FENCE = create("dark_oak_fence");

    /**
     * Key for {@code minecraft:dark_oak_fence_gate}.
     */
    public static final TypedKey<Item> DARK_OAK_FENCE_GATE = create("dark_oak_fence_gate");

    /**
     * Key for {@code minecraft:dark_oak_hanging_sign}.
     */
    public static final TypedKey<Item> DARK_OAK_HANGING_SIGN = create("dark_oak_hanging_sign");

    /**
     * Key for {@code minecraft:dark_oak_leaves}.
     */
    public static final TypedKey<Item> DARK_OAK_LEAVES = create("dark_oak_leaves");

    /**
     * Key for {@code minecraft:dark_oak_log}.
     */
    public static final TypedKey<Item> DARK_OAK_LOG = create("dark_oak_log");

    /**
     * Key for {@code minecraft:dark_oak_planks}.
     */
    public static final TypedKey<Item> DARK_OAK_PLANKS = create("dark_oak_planks");

    /**
     * Key for {@code minecraft:dark_oak_pressure_plate}.
     */
    public static final TypedKey<Item> DARK_OAK_PRESSURE_PLATE = create("dark_oak_pressure_plate");

    /**
     * Key for {@code minecraft:dark_oak_sapling}.
     */
    public static final TypedKey<Item> DARK_OAK_SAPLING = create("dark_oak_sapling");

    /**
     * Key for {@code minecraft:dark_oak_shelf}.
     */
    public static final TypedKey<Item> DARK_OAK_SHELF = create("dark_oak_shelf");

    /**
     * Key for {@code minecraft:dark_oak_sign}.
     */
    public static final TypedKey<Item> DARK_OAK_SIGN = create("dark_oak_sign");

    /**
     * Key for {@code minecraft:dark_oak_slab}.
     */
    public static final TypedKey<Item> DARK_OAK_SLAB = create("dark_oak_slab");

    /**
     * Key for {@code minecraft:dark_oak_stairs}.
     */
    public static final TypedKey<Item> DARK_OAK_STAIRS = create("dark_oak_stairs");

    /**
     * Key for {@code minecraft:dark_oak_trapdoor}.
     */
    public static final TypedKey<Item> DARK_OAK_TRAPDOOR = create("dark_oak_trapdoor");

    /**
     * Key for {@code minecraft:dark_oak_wood}.
     */
    public static final TypedKey<Item> DARK_OAK_WOOD = create("dark_oak_wood");

    /**
     * Key for {@code minecraft:dark_prismarine}.
     */
    public static final TypedKey<Item> DARK_PRISMARINE = create("dark_prismarine");

    /**
     * Key for {@code minecraft:dark_prismarine_slab}.
     */
    public static final TypedKey<Item> DARK_PRISMARINE_SLAB = create("dark_prismarine_slab");

    /**
     * Key for {@code minecraft:dark_prismarine_stairs}.
     */
    public static final TypedKey<Item> DARK_PRISMARINE_STAIRS = create("dark_prismarine_stairs");

    /**
     * Key for {@code minecraft:daylight_detector}.
     */
    public static final TypedKey<Item> DAYLIGHT_DETECTOR = create("daylight_detector");

    /**
     * Key for {@code minecraft:dead_brain_coral}.
     */
    public static final TypedKey<Item> DEAD_BRAIN_CORAL = create("dead_brain_coral");

    /**
     * Key for {@code minecraft:dead_brain_coral_block}.
     */
    public static final TypedKey<Item> DEAD_BRAIN_CORAL_BLOCK = create("dead_brain_coral_block");

    /**
     * Key for {@code minecraft:dead_brain_coral_fan}.
     */
    public static final TypedKey<Item> DEAD_BRAIN_CORAL_FAN = create("dead_brain_coral_fan");

    /**
     * Key for {@code minecraft:dead_bubble_coral}.
     */
    public static final TypedKey<Item> DEAD_BUBBLE_CORAL = create("dead_bubble_coral");

    /**
     * Key for {@code minecraft:dead_bubble_coral_block}.
     */
    public static final TypedKey<Item> DEAD_BUBBLE_CORAL_BLOCK = create("dead_bubble_coral_block");

    /**
     * Key for {@code minecraft:dead_bubble_coral_fan}.
     */
    public static final TypedKey<Item> DEAD_BUBBLE_CORAL_FAN = create("dead_bubble_coral_fan");

    /**
     * Key for {@code minecraft:dead_bush}.
     */
    public static final TypedKey<Item> DEAD_BUSH = create("dead_bush");

    /**
     * Key for {@code minecraft:dead_fire_coral}.
     */
    public static final TypedKey<Item> DEAD_FIRE_CORAL = create("dead_fire_coral");

    /**
     * Key for {@code minecraft:dead_fire_coral_block}.
     */
    public static final TypedKey<Item> DEAD_FIRE_CORAL_BLOCK = create("dead_fire_coral_block");

    /**
     * Key for {@code minecraft:dead_fire_coral_fan}.
     */
    public static final TypedKey<Item> DEAD_FIRE_CORAL_FAN = create("dead_fire_coral_fan");

    /**
     * Key for {@code minecraft:dead_horn_coral}.
     */
    public static final TypedKey<Item> DEAD_HORN_CORAL = create("dead_horn_coral");

    /**
     * Key for {@code minecraft:dead_horn_coral_block}.
     */
    public static final TypedKey<Item> DEAD_HORN_CORAL_BLOCK = create("dead_horn_coral_block");

    /**
     * Key for {@code minecraft:dead_horn_coral_fan}.
     */
    public static final TypedKey<Item> DEAD_HORN_CORAL_FAN = create("dead_horn_coral_fan");

    /**
     * Key for {@code minecraft:dead_tube_coral}.
     */
    public static final TypedKey<Item> DEAD_TUBE_CORAL = create("dead_tube_coral");

    /**
     * Key for {@code minecraft:dead_tube_coral_block}.
     */
    public static final TypedKey<Item> DEAD_TUBE_CORAL_BLOCK = create("dead_tube_coral_block");

    /**
     * Key for {@code minecraft:dead_tube_coral_fan}.
     */
    public static final TypedKey<Item> DEAD_TUBE_CORAL_FAN = create("dead_tube_coral_fan");

    /**
     * Key for {@code minecraft:debug_stick}.
     */
    public static final TypedKey<Item> DEBUG_STICK = create("debug_stick");

    /**
     * Key for {@code minecraft:decorated_pot}.
     */
    public static final TypedKey<Item> DECORATED_POT = create("decorated_pot");

    /**
     * Key for {@code minecraft:deepslate}.
     */
    public static final TypedKey<Item> DEEPSLATE = create("deepslate");

    /**
     * Key for {@code minecraft:deepslate_brick_slab}.
     */
    public static final TypedKey<Item> DEEPSLATE_BRICK_SLAB = create("deepslate_brick_slab");

    /**
     * Key for {@code minecraft:deepslate_brick_stairs}.
     */
    public static final TypedKey<Item> DEEPSLATE_BRICK_STAIRS = create("deepslate_brick_stairs");

    /**
     * Key for {@code minecraft:deepslate_brick_wall}.
     */
    public static final TypedKey<Item> DEEPSLATE_BRICK_WALL = create("deepslate_brick_wall");

    /**
     * Key for {@code minecraft:deepslate_bricks}.
     */
    public static final TypedKey<Item> DEEPSLATE_BRICKS = create("deepslate_bricks");

    /**
     * Key for {@code minecraft:deepslate_coal_ore}.
     */
    public static final TypedKey<Item> DEEPSLATE_COAL_ORE = create("deepslate_coal_ore");

    /**
     * Key for {@code minecraft:deepslate_copper_ore}.
     */
    public static final TypedKey<Item> DEEPSLATE_COPPER_ORE = create("deepslate_copper_ore");

    /**
     * Key for {@code minecraft:deepslate_diamond_ore}.
     */
    public static final TypedKey<Item> DEEPSLATE_DIAMOND_ORE = create("deepslate_diamond_ore");

    /**
     * Key for {@code minecraft:deepslate_emerald_ore}.
     */
    public static final TypedKey<Item> DEEPSLATE_EMERALD_ORE = create("deepslate_emerald_ore");

    /**
     * Key for {@code minecraft:deepslate_gold_ore}.
     */
    public static final TypedKey<Item> DEEPSLATE_GOLD_ORE = create("deepslate_gold_ore");

    /**
     * Key for {@code minecraft:deepslate_iron_ore}.
     */
    public static final TypedKey<Item> DEEPSLATE_IRON_ORE = create("deepslate_iron_ore");

    /**
     * Key for {@code minecraft:deepslate_lapis_ore}.
     */
    public static final TypedKey<Item> DEEPSLATE_LAPIS_ORE = create("deepslate_lapis_ore");

    /**
     * Key for {@code minecraft:deepslate_redstone_ore}.
     */
    public static final TypedKey<Item> DEEPSLATE_REDSTONE_ORE = create("deepslate_redstone_ore");

    /**
     * Key for {@code minecraft:deepslate_tile_slab}.
     */
    public static final TypedKey<Item> DEEPSLATE_TILE_SLAB = create("deepslate_tile_slab");

    /**
     * Key for {@code minecraft:deepslate_tile_stairs}.
     */
    public static final TypedKey<Item> DEEPSLATE_TILE_STAIRS = create("deepslate_tile_stairs");

    /**
     * Key for {@code minecraft:deepslate_tile_wall}.
     */
    public static final TypedKey<Item> DEEPSLATE_TILE_WALL = create("deepslate_tile_wall");

    /**
     * Key for {@code minecraft:deepslate_tiles}.
     */
    public static final TypedKey<Item> DEEPSLATE_TILES = create("deepslate_tiles");

    /**
     * Key for {@code minecraft:desert_pyramid_map}.
     */
    public static final TypedKey<Item> DESERT_PYRAMID_MAP = create("desert_pyramid_map");

    /**
     * Key for {@code minecraft:desert_village_map}.
     */
    public static final TypedKey<Item> DESERT_VILLAGE_MAP = create("desert_village_map");

    /**
     * Key for {@code minecraft:detector_rail}.
     */
    public static final TypedKey<Item> DETECTOR_RAIL = create("detector_rail");

    /**
     * Key for {@code minecraft:diamond}.
     */
    public static final TypedKey<Item> DIAMOND = create("diamond");

    /**
     * Key for {@code minecraft:diamond_axe}.
     */
    public static final TypedKey<Item> DIAMOND_AXE = create("diamond_axe");

    /**
     * Key for {@code minecraft:diamond_block}.
     */
    public static final TypedKey<Item> DIAMOND_BLOCK = create("diamond_block");

    /**
     * Key for {@code minecraft:diamond_boots}.
     */
    public static final TypedKey<Item> DIAMOND_BOOTS = create("diamond_boots");

    /**
     * Key for {@code minecraft:diamond_chestplate}.
     */
    public static final TypedKey<Item> DIAMOND_CHESTPLATE = create("diamond_chestplate");

    /**
     * Key for {@code minecraft:diamond_helmet}.
     */
    public static final TypedKey<Item> DIAMOND_HELMET = create("diamond_helmet");

    /**
     * Key for {@code minecraft:diamond_hoe}.
     */
    public static final TypedKey<Item> DIAMOND_HOE = create("diamond_hoe");

    /**
     * Key for {@code minecraft:diamond_horse_armor}.
     */
    public static final TypedKey<Item> DIAMOND_HORSE_ARMOR = create("diamond_horse_armor");

    /**
     * Key for {@code minecraft:diamond_leggings}.
     */
    public static final TypedKey<Item> DIAMOND_LEGGINGS = create("diamond_leggings");

    /**
     * Key for {@code minecraft:diamond_nautilus_armor}.
     */
    public static final TypedKey<Item> DIAMOND_NAUTILUS_ARMOR = create("diamond_nautilus_armor");

    /**
     * Key for {@code minecraft:diamond_ore}.
     */
    public static final TypedKey<Item> DIAMOND_ORE = create("diamond_ore");

    /**
     * Key for {@code minecraft:diamond_pickaxe}.
     */
    public static final TypedKey<Item> DIAMOND_PICKAXE = create("diamond_pickaxe");

    /**
     * Key for {@code minecraft:diamond_shovel}.
     */
    public static final TypedKey<Item> DIAMOND_SHOVEL = create("diamond_shovel");

    /**
     * Key for {@code minecraft:diamond_spear}.
     */
    public static final TypedKey<Item> DIAMOND_SPEAR = create("diamond_spear");

    /**
     * Key for {@code minecraft:diamond_sword}.
     */
    public static final TypedKey<Item> DIAMOND_SWORD = create("diamond_sword");

    /**
     * Key for {@code minecraft:diorite}.
     */
    public static final TypedKey<Item> DIORITE = create("diorite");

    /**
     * Key for {@code minecraft:diorite_slab}.
     */
    public static final TypedKey<Item> DIORITE_SLAB = create("diorite_slab");

    /**
     * Key for {@code minecraft:diorite_stairs}.
     */
    public static final TypedKey<Item> DIORITE_STAIRS = create("diorite_stairs");

    /**
     * Key for {@code minecraft:diorite_wall}.
     */
    public static final TypedKey<Item> DIORITE_WALL = create("diorite_wall");

    /**
     * Key for {@code minecraft:dirt}.
     */
    public static final TypedKey<Item> DIRT = create("dirt");

    /**
     * Key for {@code minecraft:dirt_path}.
     */
    public static final TypedKey<Item> DIRT_PATH = create("dirt_path");

    /**
     * Key for {@code minecraft:disc_fragment_5}.
     */
    public static final TypedKey<Item> DISC_FRAGMENT_5 = create("disc_fragment_5");

    /**
     * Key for {@code minecraft:dispenser}.
     */
    public static final TypedKey<Item> DISPENSER = create("dispenser");

    /**
     * Key for {@code minecraft:dolphin_spawn_egg}.
     */
    public static final TypedKey<Item> DOLPHIN_SPAWN_EGG = create("dolphin_spawn_egg");

    /**
     * Key for {@code minecraft:donkey_spawn_egg}.
     */
    public static final TypedKey<Item> DONKEY_SPAWN_EGG = create("donkey_spawn_egg");

    /**
     * Key for {@code minecraft:dragon_breath}.
     */
    public static final TypedKey<Item> DRAGON_BREATH = create("dragon_breath");

    /**
     * Key for {@code minecraft:dragon_egg}.
     */
    public static final TypedKey<Item> DRAGON_EGG = create("dragon_egg");

    /**
     * Key for {@code minecraft:dragon_head}.
     */
    public static final TypedKey<Item> DRAGON_HEAD = create("dragon_head");

    /**
     * Key for {@code minecraft:dried_ghast}.
     */
    public static final TypedKey<Item> DRIED_GHAST = create("dried_ghast");

    /**
     * Key for {@code minecraft:dried_kelp}.
     */
    public static final TypedKey<Item> DRIED_KELP = create("dried_kelp");

    /**
     * Key for {@code minecraft:dried_kelp_block}.
     */
    public static final TypedKey<Item> DRIED_KELP_BLOCK = create("dried_kelp_block");

    /**
     * Key for {@code minecraft:dripstone_block}.
     */
    public static final TypedKey<Item> DRIPSTONE_BLOCK = create("dripstone_block");

    /**
     * Key for {@code minecraft:dropper}.
     */
    public static final TypedKey<Item> DROPPER = create("dropper");

    /**
     * Key for {@code minecraft:drowned_spawn_egg}.
     */
    public static final TypedKey<Item> DROWNED_SPAWN_EGG = create("drowned_spawn_egg");

    /**
     * Key for {@code minecraft:dune_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> DUNE_ARMOR_TRIM_SMITHING_TEMPLATE = create("dune_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:echo_shard}.
     */
    public static final TypedKey<Item> ECHO_SHARD = create("echo_shard");

    /**
     * Key for {@code minecraft:egg}.
     */
    public static final TypedKey<Item> EGG = create("egg");

    /**
     * Key for {@code minecraft:elder_guardian_spawn_egg}.
     */
    public static final TypedKey<Item> ELDER_GUARDIAN_SPAWN_EGG = create("elder_guardian_spawn_egg");

    /**
     * Key for {@code minecraft:elytra}.
     */
    public static final TypedKey<Item> ELYTRA = create("elytra");

    /**
     * Key for {@code minecraft:emerald}.
     */
    public static final TypedKey<Item> EMERALD = create("emerald");

    /**
     * Key for {@code minecraft:emerald_block}.
     */
    public static final TypedKey<Item> EMERALD_BLOCK = create("emerald_block");

    /**
     * Key for {@code minecraft:emerald_ore}.
     */
    public static final TypedKey<Item> EMERALD_ORE = create("emerald_ore");

    /**
     * Key for {@code minecraft:enchanted_book}.
     */
    public static final TypedKey<Item> ENCHANTED_BOOK = create("enchanted_book");

    /**
     * Key for {@code minecraft:enchanted_golden_apple}.
     */
    public static final TypedKey<Item> ENCHANTED_GOLDEN_APPLE = create("enchanted_golden_apple");

    /**
     * Key for {@code minecraft:enchanting_table}.
     */
    public static final TypedKey<Item> ENCHANTING_TABLE = create("enchanting_table");

    /**
     * Key for {@code minecraft:end_crystal}.
     */
    public static final TypedKey<Item> END_CRYSTAL = create("end_crystal");

    /**
     * Key for {@code minecraft:end_portal_frame}.
     */
    public static final TypedKey<Item> END_PORTAL_FRAME = create("end_portal_frame");

    /**
     * Key for {@code minecraft:end_rod}.
     */
    public static final TypedKey<Item> END_ROD = create("end_rod");

    /**
     * Key for {@code minecraft:end_stone}.
     */
    public static final TypedKey<Item> END_STONE = create("end_stone");

    /**
     * Key for {@code minecraft:end_stone_brick_slab}.
     */
    public static final TypedKey<Item> END_STONE_BRICK_SLAB = create("end_stone_brick_slab");

    /**
     * Key for {@code minecraft:end_stone_brick_stairs}.
     */
    public static final TypedKey<Item> END_STONE_BRICK_STAIRS = create("end_stone_brick_stairs");

    /**
     * Key for {@code minecraft:end_stone_brick_wall}.
     */
    public static final TypedKey<Item> END_STONE_BRICK_WALL = create("end_stone_brick_wall");

    /**
     * Key for {@code minecraft:end_stone_bricks}.
     */
    public static final TypedKey<Item> END_STONE_BRICKS = create("end_stone_bricks");

    /**
     * Key for {@code minecraft:ender_chest}.
     */
    public static final TypedKey<Item> ENDER_CHEST = create("ender_chest");

    /**
     * Key for {@code minecraft:ender_dragon_spawn_egg}.
     */
    public static final TypedKey<Item> ENDER_DRAGON_SPAWN_EGG = create("ender_dragon_spawn_egg");

    /**
     * Key for {@code minecraft:ender_eye}.
     */
    public static final TypedKey<Item> ENDER_EYE = create("ender_eye");

    /**
     * Key for {@code minecraft:ender_pearl}.
     */
    public static final TypedKey<Item> ENDER_PEARL = create("ender_pearl");

    /**
     * Key for {@code minecraft:enderman_spawn_egg}.
     */
    public static final TypedKey<Item> ENDERMAN_SPAWN_EGG = create("enderman_spawn_egg");

    /**
     * Key for {@code minecraft:endermite_spawn_egg}.
     */
    public static final TypedKey<Item> ENDERMITE_SPAWN_EGG = create("endermite_spawn_egg");

    /**
     * Key for {@code minecraft:evoker_spawn_egg}.
     */
    public static final TypedKey<Item> EVOKER_SPAWN_EGG = create("evoker_spawn_egg");

    /**
     * Key for {@code minecraft:experience_bottle}.
     */
    public static final TypedKey<Item> EXPERIENCE_BOTTLE = create("experience_bottle");

    /**
     * Key for {@code minecraft:explorer_pottery_sherd}.
     */
    public static final TypedKey<Item> EXPLORER_POTTERY_SHERD = create("explorer_pottery_sherd");

    /**
     * Key for {@code minecraft:exposed_chiseled_copper}.
     */
    public static final TypedKey<Item> EXPOSED_CHISELED_COPPER = create("exposed_chiseled_copper");

    /**
     * Key for {@code minecraft:exposed_copper}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER = create("exposed_copper");

    /**
     * Key for {@code minecraft:exposed_copper_bars}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_BARS = create("exposed_copper_bars");

    /**
     * Key for {@code minecraft:exposed_copper_bulb}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_BULB = create("exposed_copper_bulb");

    /**
     * Key for {@code minecraft:exposed_copper_chain}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_CHAIN = create("exposed_copper_chain");

    /**
     * Key for {@code minecraft:exposed_copper_chest}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_CHEST = create("exposed_copper_chest");

    /**
     * Key for {@code minecraft:exposed_copper_door}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_DOOR = create("exposed_copper_door");

    /**
     * Key for {@code minecraft:exposed_copper_golem_statue}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_GOLEM_STATUE = create("exposed_copper_golem_statue");

    /**
     * Key for {@code minecraft:exposed_copper_grate}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_GRATE = create("exposed_copper_grate");

    /**
     * Key for {@code minecraft:exposed_copper_lantern}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_LANTERN = create("exposed_copper_lantern");

    /**
     * Key for {@code minecraft:exposed_copper_trapdoor}.
     */
    public static final TypedKey<Item> EXPOSED_COPPER_TRAPDOOR = create("exposed_copper_trapdoor");

    /**
     * Key for {@code minecraft:exposed_cut_copper}.
     */
    public static final TypedKey<Item> EXPOSED_CUT_COPPER = create("exposed_cut_copper");

    /**
     * Key for {@code minecraft:exposed_cut_copper_slab}.
     */
    public static final TypedKey<Item> EXPOSED_CUT_COPPER_SLAB = create("exposed_cut_copper_slab");

    /**
     * Key for {@code minecraft:exposed_cut_copper_stairs}.
     */
    public static final TypedKey<Item> EXPOSED_CUT_COPPER_STAIRS = create("exposed_cut_copper_stairs");

    /**
     * Key for {@code minecraft:exposed_lightning_rod}.
     */
    public static final TypedKey<Item> EXPOSED_LIGHTNING_ROD = create("exposed_lightning_rod");

    /**
     * Key for {@code minecraft:eye_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> EYE_ARMOR_TRIM_SMITHING_TEMPLATE = create("eye_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:farmland}.
     */
    public static final TypedKey<Item> FARMLAND = create("farmland");

    /**
     * Key for {@code minecraft:feather}.
     */
    public static final TypedKey<Item> FEATHER = create("feather");

    /**
     * Key for {@code minecraft:fermented_spider_eye}.
     */
    public static final TypedKey<Item> FERMENTED_SPIDER_EYE = create("fermented_spider_eye");

    /**
     * Key for {@code minecraft:fern}.
     */
    public static final TypedKey<Item> FERN = create("fern");

    /**
     * Key for {@code minecraft:field_masoned_banner_pattern}.
     */
    public static final TypedKey<Item> FIELD_MASONED_BANNER_PATTERN = create("field_masoned_banner_pattern");

    /**
     * Key for {@code minecraft:filled_map}.
     */
    public static final TypedKey<Item> FILLED_MAP = create("filled_map");

    /**
     * Key for {@code minecraft:fire_charge}.
     */
    public static final TypedKey<Item> FIRE_CHARGE = create("fire_charge");

    /**
     * Key for {@code minecraft:fire_coral}.
     */
    public static final TypedKey<Item> FIRE_CORAL = create("fire_coral");

    /**
     * Key for {@code minecraft:fire_coral_block}.
     */
    public static final TypedKey<Item> FIRE_CORAL_BLOCK = create("fire_coral_block");

    /**
     * Key for {@code minecraft:fire_coral_fan}.
     */
    public static final TypedKey<Item> FIRE_CORAL_FAN = create("fire_coral_fan");

    /**
     * Key for {@code minecraft:firefly_bush}.
     */
    public static final TypedKey<Item> FIREFLY_BUSH = create("firefly_bush");

    /**
     * Key for {@code minecraft:firework_rocket}.
     */
    public static final TypedKey<Item> FIREWORK_ROCKET = create("firework_rocket");

    /**
     * Key for {@code minecraft:firework_star}.
     */
    public static final TypedKey<Item> FIREWORK_STAR = create("firework_star");

    /**
     * Key for {@code minecraft:fishing_rod}.
     */
    public static final TypedKey<Item> FISHING_ROD = create("fishing_rod");

    /**
     * Key for {@code minecraft:fletching_table}.
     */
    public static final TypedKey<Item> FLETCHING_TABLE = create("fletching_table");

    /**
     * Key for {@code minecraft:flint}.
     */
    public static final TypedKey<Item> FLINT = create("flint");

    /**
     * Key for {@code minecraft:flint_and_steel}.
     */
    public static final TypedKey<Item> FLINT_AND_STEEL = create("flint_and_steel");

    /**
     * Key for {@code minecraft:flow_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> FLOW_ARMOR_TRIM_SMITHING_TEMPLATE = create("flow_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:flow_banner_pattern}.
     */
    public static final TypedKey<Item> FLOW_BANNER_PATTERN = create("flow_banner_pattern");

    /**
     * Key for {@code minecraft:flow_pottery_sherd}.
     */
    public static final TypedKey<Item> FLOW_POTTERY_SHERD = create("flow_pottery_sherd");

    /**
     * Key for {@code minecraft:flower_banner_pattern}.
     */
    public static final TypedKey<Item> FLOWER_BANNER_PATTERN = create("flower_banner_pattern");

    /**
     * Key for {@code minecraft:flower_pot}.
     */
    public static final TypedKey<Item> FLOWER_POT = create("flower_pot");

    /**
     * Key for {@code minecraft:flowering_azalea}.
     */
    public static final TypedKey<Item> FLOWERING_AZALEA = create("flowering_azalea");

    /**
     * Key for {@code minecraft:flowering_azalea_leaves}.
     */
    public static final TypedKey<Item> FLOWERING_AZALEA_LEAVES = create("flowering_azalea_leaves");

    /**
     * Key for {@code minecraft:fox_spawn_egg}.
     */
    public static final TypedKey<Item> FOX_SPAWN_EGG = create("fox_spawn_egg");

    /**
     * Key for {@code minecraft:friend_pottery_sherd}.
     */
    public static final TypedKey<Item> FRIEND_POTTERY_SHERD = create("friend_pottery_sherd");

    /**
     * Key for {@code minecraft:frog_spawn_egg}.
     */
    public static final TypedKey<Item> FROG_SPAWN_EGG = create("frog_spawn_egg");

    /**
     * Key for {@code minecraft:frogspawn}.
     */
    public static final TypedKey<Item> FROGSPAWN = create("frogspawn");

    /**
     * Key for {@code minecraft:furnace}.
     */
    public static final TypedKey<Item> FURNACE = create("furnace");

    /**
     * Key for {@code minecraft:furnace_minecart}.
     */
    public static final TypedKey<Item> FURNACE_MINECART = create("furnace_minecart");

    /**
     * Key for {@code minecraft:ghast_spawn_egg}.
     */
    public static final TypedKey<Item> GHAST_SPAWN_EGG = create("ghast_spawn_egg");

    /**
     * Key for {@code minecraft:ghast_tear}.
     */
    public static final TypedKey<Item> GHAST_TEAR = create("ghast_tear");

    /**
     * Key for {@code minecraft:gilded_blackstone}.
     */
    public static final TypedKey<Item> GILDED_BLACKSTONE = create("gilded_blackstone");

    /**
     * Key for {@code minecraft:glass}.
     */
    public static final TypedKey<Item> GLASS = create("glass");

    /**
     * Key for {@code minecraft:glass_bottle}.
     */
    public static final TypedKey<Item> GLASS_BOTTLE = create("glass_bottle");

    /**
     * Key for {@code minecraft:glass_pane}.
     */
    public static final TypedKey<Item> GLASS_PANE = create("glass_pane");

    /**
     * Key for {@code minecraft:glistering_melon_slice}.
     */
    public static final TypedKey<Item> GLISTERING_MELON_SLICE = create("glistering_melon_slice");

    /**
     * Key for {@code minecraft:globe_banner_pattern}.
     */
    public static final TypedKey<Item> GLOBE_BANNER_PATTERN = create("globe_banner_pattern");

    /**
     * Key for {@code minecraft:glow_berries}.
     */
    public static final TypedKey<Item> GLOW_BERRIES = create("glow_berries");

    /**
     * Key for {@code minecraft:glow_ink_sac}.
     */
    public static final TypedKey<Item> GLOW_INK_SAC = create("glow_ink_sac");

    /**
     * Key for {@code minecraft:glow_item_frame}.
     */
    public static final TypedKey<Item> GLOW_ITEM_FRAME = create("glow_item_frame");

    /**
     * Key for {@code minecraft:glow_lichen}.
     */
    public static final TypedKey<Item> GLOW_LICHEN = create("glow_lichen");

    /**
     * Key for {@code minecraft:glow_squid_spawn_egg}.
     */
    public static final TypedKey<Item> GLOW_SQUID_SPAWN_EGG = create("glow_squid_spawn_egg");

    /**
     * Key for {@code minecraft:glowstone}.
     */
    public static final TypedKey<Item> GLOWSTONE = create("glowstone");

    /**
     * Key for {@code minecraft:glowstone_dust}.
     */
    public static final TypedKey<Item> GLOWSTONE_DUST = create("glowstone_dust");

    /**
     * Key for {@code minecraft:goat_horn}.
     */
    public static final TypedKey<Item> GOAT_HORN = create("goat_horn");

    /**
     * Key for {@code minecraft:goat_spawn_egg}.
     */
    public static final TypedKey<Item> GOAT_SPAWN_EGG = create("goat_spawn_egg");

    /**
     * Key for {@code minecraft:gold_block}.
     */
    public static final TypedKey<Item> GOLD_BLOCK = create("gold_block");

    /**
     * Key for {@code minecraft:gold_ingot}.
     */
    public static final TypedKey<Item> GOLD_INGOT = create("gold_ingot");

    /**
     * Key for {@code minecraft:gold_nugget}.
     */
    public static final TypedKey<Item> GOLD_NUGGET = create("gold_nugget");

    /**
     * Key for {@code minecraft:gold_ore}.
     */
    public static final TypedKey<Item> GOLD_ORE = create("gold_ore");

    /**
     * Key for {@code minecraft:golden_apple}.
     */
    public static final TypedKey<Item> GOLDEN_APPLE = create("golden_apple");

    /**
     * Key for {@code minecraft:golden_axe}.
     */
    public static final TypedKey<Item> GOLDEN_AXE = create("golden_axe");

    /**
     * Key for {@code minecraft:golden_boots}.
     */
    public static final TypedKey<Item> GOLDEN_BOOTS = create("golden_boots");

    /**
     * Key for {@code minecraft:golden_carrot}.
     */
    public static final TypedKey<Item> GOLDEN_CARROT = create("golden_carrot");

    /**
     * Key for {@code minecraft:golden_chestplate}.
     */
    public static final TypedKey<Item> GOLDEN_CHESTPLATE = create("golden_chestplate");

    /**
     * Key for {@code minecraft:golden_dandelion}.
     */
    public static final TypedKey<Item> GOLDEN_DANDELION = create("golden_dandelion");

    /**
     * Key for {@code minecraft:golden_helmet}.
     */
    public static final TypedKey<Item> GOLDEN_HELMET = create("golden_helmet");

    /**
     * Key for {@code minecraft:golden_hoe}.
     */
    public static final TypedKey<Item> GOLDEN_HOE = create("golden_hoe");

    /**
     * Key for {@code minecraft:golden_horse_armor}.
     */
    public static final TypedKey<Item> GOLDEN_HORSE_ARMOR = create("golden_horse_armor");

    /**
     * Key for {@code minecraft:golden_leggings}.
     */
    public static final TypedKey<Item> GOLDEN_LEGGINGS = create("golden_leggings");

    /**
     * Key for {@code minecraft:golden_nautilus_armor}.
     */
    public static final TypedKey<Item> GOLDEN_NAUTILUS_ARMOR = create("golden_nautilus_armor");

    /**
     * Key for {@code minecraft:golden_pickaxe}.
     */
    public static final TypedKey<Item> GOLDEN_PICKAXE = create("golden_pickaxe");

    /**
     * Key for {@code minecraft:golden_shovel}.
     */
    public static final TypedKey<Item> GOLDEN_SHOVEL = create("golden_shovel");

    /**
     * Key for {@code minecraft:golden_spear}.
     */
    public static final TypedKey<Item> GOLDEN_SPEAR = create("golden_spear");

    /**
     * Key for {@code minecraft:golden_sword}.
     */
    public static final TypedKey<Item> GOLDEN_SWORD = create("golden_sword");

    /**
     * Key for {@code minecraft:granite}.
     */
    public static final TypedKey<Item> GRANITE = create("granite");

    /**
     * Key for {@code minecraft:granite_slab}.
     */
    public static final TypedKey<Item> GRANITE_SLAB = create("granite_slab");

    /**
     * Key for {@code minecraft:granite_stairs}.
     */
    public static final TypedKey<Item> GRANITE_STAIRS = create("granite_stairs");

    /**
     * Key for {@code minecraft:granite_wall}.
     */
    public static final TypedKey<Item> GRANITE_WALL = create("granite_wall");

    /**
     * Key for {@code minecraft:grass_block}.
     */
    public static final TypedKey<Item> GRASS_BLOCK = create("grass_block");

    /**
     * Key for {@code minecraft:gravel}.
     */
    public static final TypedKey<Item> GRAVEL = create("gravel");

    /**
     * Key for {@code minecraft:gray_banner}.
     */
    public static final TypedKey<Item> GRAY_BANNER = create("gray_banner");

    /**
     * Key for {@code minecraft:gray_bed}.
     */
    public static final TypedKey<Item> GRAY_BED = create("gray_bed");

    /**
     * Key for {@code minecraft:gray_bundle}.
     */
    public static final TypedKey<Item> GRAY_BUNDLE = create("gray_bundle");

    /**
     * Key for {@code minecraft:gray_candle}.
     */
    public static final TypedKey<Item> GRAY_CANDLE = create("gray_candle");

    /**
     * Key for {@code minecraft:gray_carpet}.
     */
    public static final TypedKey<Item> GRAY_CARPET = create("gray_carpet");

    /**
     * Key for {@code minecraft:gray_concrete}.
     */
    public static final TypedKey<Item> GRAY_CONCRETE = create("gray_concrete");

    /**
     * Key for {@code minecraft:gray_concrete_powder}.
     */
    public static final TypedKey<Item> GRAY_CONCRETE_POWDER = create("gray_concrete_powder");

    /**
     * Key for {@code minecraft:gray_concrete_slab}.
     */
    public static final TypedKey<Item> GRAY_CONCRETE_SLAB = create("gray_concrete_slab");

    /**
     * Key for {@code minecraft:gray_concrete_stairs}.
     */
    public static final TypedKey<Item> GRAY_CONCRETE_STAIRS = create("gray_concrete_stairs");

    /**
     * Key for {@code minecraft:gray_cushion}.
     */
    public static final TypedKey<Item> GRAY_CUSHION = create("gray_cushion");

    /**
     * Key for {@code minecraft:gray_dye}.
     */
    public static final TypedKey<Item> GRAY_DYE = create("gray_dye");

    /**
     * Key for {@code minecraft:gray_glazed_terracotta}.
     */
    public static final TypedKey<Item> GRAY_GLAZED_TERRACOTTA = create("gray_glazed_terracotta");

    /**
     * Key for {@code minecraft:gray_harness}.
     */
    public static final TypedKey<Item> GRAY_HARNESS = create("gray_harness");

    /**
     * Key for {@code minecraft:gray_shulker_box}.
     */
    public static final TypedKey<Item> GRAY_SHULKER_BOX = create("gray_shulker_box");

    /**
     * Key for {@code minecraft:gray_stained_glass}.
     */
    public static final TypedKey<Item> GRAY_STAINED_GLASS = create("gray_stained_glass");

    /**
     * Key for {@code minecraft:gray_stained_glass_pane}.
     */
    public static final TypedKey<Item> GRAY_STAINED_GLASS_PANE = create("gray_stained_glass_pane");

    /**
     * Key for {@code minecraft:gray_terracotta}.
     */
    public static final TypedKey<Item> GRAY_TERRACOTTA = create("gray_terracotta");

    /**
     * Key for {@code minecraft:gray_wool}.
     */
    public static final TypedKey<Item> GRAY_WOOL = create("gray_wool");

    /**
     * Key for {@code minecraft:gray_wool_slab}.
     */
    public static final TypedKey<Item> GRAY_WOOL_SLAB = create("gray_wool_slab");

    /**
     * Key for {@code minecraft:gray_wool_stairs}.
     */
    public static final TypedKey<Item> GRAY_WOOL_STAIRS = create("gray_wool_stairs");

    /**
     * Key for {@code minecraft:green_banner}.
     */
    public static final TypedKey<Item> GREEN_BANNER = create("green_banner");

    /**
     * Key for {@code minecraft:green_bed}.
     */
    public static final TypedKey<Item> GREEN_BED = create("green_bed");

    /**
     * Key for {@code minecraft:green_bundle}.
     */
    public static final TypedKey<Item> GREEN_BUNDLE = create("green_bundle");

    /**
     * Key for {@code minecraft:green_candle}.
     */
    public static final TypedKey<Item> GREEN_CANDLE = create("green_candle");

    /**
     * Key for {@code minecraft:green_carpet}.
     */
    public static final TypedKey<Item> GREEN_CARPET = create("green_carpet");

    /**
     * Key for {@code minecraft:green_concrete}.
     */
    public static final TypedKey<Item> GREEN_CONCRETE = create("green_concrete");

    /**
     * Key for {@code minecraft:green_concrete_powder}.
     */
    public static final TypedKey<Item> GREEN_CONCRETE_POWDER = create("green_concrete_powder");

    /**
     * Key for {@code minecraft:green_concrete_slab}.
     */
    public static final TypedKey<Item> GREEN_CONCRETE_SLAB = create("green_concrete_slab");

    /**
     * Key for {@code minecraft:green_concrete_stairs}.
     */
    public static final TypedKey<Item> GREEN_CONCRETE_STAIRS = create("green_concrete_stairs");

    /**
     * Key for {@code minecraft:green_cushion}.
     */
    public static final TypedKey<Item> GREEN_CUSHION = create("green_cushion");

    /**
     * Key for {@code minecraft:green_dye}.
     */
    public static final TypedKey<Item> GREEN_DYE = create("green_dye");

    /**
     * Key for {@code minecraft:green_glazed_terracotta}.
     */
    public static final TypedKey<Item> GREEN_GLAZED_TERRACOTTA = create("green_glazed_terracotta");

    /**
     * Key for {@code minecraft:green_harness}.
     */
    public static final TypedKey<Item> GREEN_HARNESS = create("green_harness");

    /**
     * Key for {@code minecraft:green_shulker_box}.
     */
    public static final TypedKey<Item> GREEN_SHULKER_BOX = create("green_shulker_box");

    /**
     * Key for {@code minecraft:green_stained_glass}.
     */
    public static final TypedKey<Item> GREEN_STAINED_GLASS = create("green_stained_glass");

    /**
     * Key for {@code minecraft:green_stained_glass_pane}.
     */
    public static final TypedKey<Item> GREEN_STAINED_GLASS_PANE = create("green_stained_glass_pane");

    /**
     * Key for {@code minecraft:green_terracotta}.
     */
    public static final TypedKey<Item> GREEN_TERRACOTTA = create("green_terracotta");

    /**
     * Key for {@code minecraft:green_wool}.
     */
    public static final TypedKey<Item> GREEN_WOOL = create("green_wool");

    /**
     * Key for {@code minecraft:green_wool_slab}.
     */
    public static final TypedKey<Item> GREEN_WOOL_SLAB = create("green_wool_slab");

    /**
     * Key for {@code minecraft:green_wool_stairs}.
     */
    public static final TypedKey<Item> GREEN_WOOL_STAIRS = create("green_wool_stairs");

    /**
     * Key for {@code minecraft:grindstone}.
     */
    public static final TypedKey<Item> GRINDSTONE = create("grindstone");

    /**
     * Key for {@code minecraft:guardian_spawn_egg}.
     */
    public static final TypedKey<Item> GUARDIAN_SPAWN_EGG = create("guardian_spawn_egg");

    /**
     * Key for {@code minecraft:gunpowder}.
     */
    public static final TypedKey<Item> GUNPOWDER = create("gunpowder");

    /**
     * Key for {@code minecraft:guster_banner_pattern}.
     */
    public static final TypedKey<Item> GUSTER_BANNER_PATTERN = create("guster_banner_pattern");

    /**
     * Key for {@code minecraft:guster_pottery_sherd}.
     */
    public static final TypedKey<Item> GUSTER_POTTERY_SHERD = create("guster_pottery_sherd");

    /**
     * Key for {@code minecraft:hanging_roots}.
     */
    public static final TypedKey<Item> HANGING_ROOTS = create("hanging_roots");

    /**
     * Key for {@code minecraft:happy_ghast_spawn_egg}.
     */
    public static final TypedKey<Item> HAPPY_GHAST_SPAWN_EGG = create("happy_ghast_spawn_egg");

    /**
     * Key for {@code minecraft:hay_block}.
     */
    public static final TypedKey<Item> HAY_BLOCK = create("hay_block");

    /**
     * Key for {@code minecraft:heart_of_the_sea}.
     */
    public static final TypedKey<Item> HEART_OF_THE_SEA = create("heart_of_the_sea");

    /**
     * Key for {@code minecraft:heart_pottery_sherd}.
     */
    public static final TypedKey<Item> HEART_POTTERY_SHERD = create("heart_pottery_sherd");

    /**
     * Key for {@code minecraft:heartbreak_pottery_sherd}.
     */
    public static final TypedKey<Item> HEARTBREAK_POTTERY_SHERD = create("heartbreak_pottery_sherd");

    /**
     * Key for {@code minecraft:heavy_core}.
     */
    public static final TypedKey<Item> HEAVY_CORE = create("heavy_core");

    /**
     * Key for {@code minecraft:heavy_weighted_pressure_plate}.
     */
    public static final TypedKey<Item> HEAVY_WEIGHTED_PRESSURE_PLATE = create("heavy_weighted_pressure_plate");

    /**
     * Key for {@code minecraft:hoglin_spawn_egg}.
     */
    public static final TypedKey<Item> HOGLIN_SPAWN_EGG = create("hoglin_spawn_egg");

    /**
     * Key for {@code minecraft:honey_block}.
     */
    public static final TypedKey<Item> HONEY_BLOCK = create("honey_block");

    /**
     * Key for {@code minecraft:honey_bottle}.
     */
    public static final TypedKey<Item> HONEY_BOTTLE = create("honey_bottle");

    /**
     * Key for {@code minecraft:honeycomb}.
     */
    public static final TypedKey<Item> HONEYCOMB = create("honeycomb");

    /**
     * Key for {@code minecraft:honeycomb_block}.
     */
    public static final TypedKey<Item> HONEYCOMB_BLOCK = create("honeycomb_block");

    /**
     * Key for {@code minecraft:hopper}.
     */
    public static final TypedKey<Item> HOPPER = create("hopper");

    /**
     * Key for {@code minecraft:hopper_minecart}.
     */
    public static final TypedKey<Item> HOPPER_MINECART = create("hopper_minecart");

    /**
     * Key for {@code minecraft:horn_coral}.
     */
    public static final TypedKey<Item> HORN_CORAL = create("horn_coral");

    /**
     * Key for {@code minecraft:horn_coral_block}.
     */
    public static final TypedKey<Item> HORN_CORAL_BLOCK = create("horn_coral_block");

    /**
     * Key for {@code minecraft:horn_coral_fan}.
     */
    public static final TypedKey<Item> HORN_CORAL_FAN = create("horn_coral_fan");

    /**
     * Key for {@code minecraft:horse_spawn_egg}.
     */
    public static final TypedKey<Item> HORSE_SPAWN_EGG = create("horse_spawn_egg");

    /**
     * Key for {@code minecraft:host_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> HOST_ARMOR_TRIM_SMITHING_TEMPLATE = create("host_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:howl_pottery_sherd}.
     */
    public static final TypedKey<Item> HOWL_POTTERY_SHERD = create("howl_pottery_sherd");

    /**
     * Key for {@code minecraft:husk_spawn_egg}.
     */
    public static final TypedKey<Item> HUSK_SPAWN_EGG = create("husk_spawn_egg");

    /**
     * Key for {@code minecraft:ice}.
     */
    public static final TypedKey<Item> ICE = create("ice");

    /**
     * Key for {@code minecraft:infested_chiseled_stone_bricks}.
     */
    public static final TypedKey<Item> INFESTED_CHISELED_STONE_BRICKS = create("infested_chiseled_stone_bricks");

    /**
     * Key for {@code minecraft:infested_cobblestone}.
     */
    public static final TypedKey<Item> INFESTED_COBBLESTONE = create("infested_cobblestone");

    /**
     * Key for {@code minecraft:infested_cracked_stone_bricks}.
     */
    public static final TypedKey<Item> INFESTED_CRACKED_STONE_BRICKS = create("infested_cracked_stone_bricks");

    /**
     * Key for {@code minecraft:infested_deepslate}.
     */
    public static final TypedKey<Item> INFESTED_DEEPSLATE = create("infested_deepslate");

    /**
     * Key for {@code minecraft:infested_mossy_stone_bricks}.
     */
    public static final TypedKey<Item> INFESTED_MOSSY_STONE_BRICKS = create("infested_mossy_stone_bricks");

    /**
     * Key for {@code minecraft:infested_stone}.
     */
    public static final TypedKey<Item> INFESTED_STONE = create("infested_stone");

    /**
     * Key for {@code minecraft:infested_stone_bricks}.
     */
    public static final TypedKey<Item> INFESTED_STONE_BRICKS = create("infested_stone_bricks");

    /**
     * Key for {@code minecraft:ink_sac}.
     */
    public static final TypedKey<Item> INK_SAC = create("ink_sac");

    /**
     * Key for {@code minecraft:iron_axe}.
     */
    public static final TypedKey<Item> IRON_AXE = create("iron_axe");

    /**
     * Key for {@code minecraft:iron_bars}.
     */
    public static final TypedKey<Item> IRON_BARS = create("iron_bars");

    /**
     * Key for {@code minecraft:iron_block}.
     */
    public static final TypedKey<Item> IRON_BLOCK = create("iron_block");

    /**
     * Key for {@code minecraft:iron_boots}.
     */
    public static final TypedKey<Item> IRON_BOOTS = create("iron_boots");

    /**
     * Key for {@code minecraft:iron_chain}.
     */
    public static final TypedKey<Item> IRON_CHAIN = create("iron_chain");

    /**
     * Key for {@code minecraft:iron_chestplate}.
     */
    public static final TypedKey<Item> IRON_CHESTPLATE = create("iron_chestplate");

    /**
     * Key for {@code minecraft:iron_door}.
     */
    public static final TypedKey<Item> IRON_DOOR = create("iron_door");

    /**
     * Key for {@code minecraft:iron_golem_spawn_egg}.
     */
    public static final TypedKey<Item> IRON_GOLEM_SPAWN_EGG = create("iron_golem_spawn_egg");

    /**
     * Key for {@code minecraft:iron_helmet}.
     */
    public static final TypedKey<Item> IRON_HELMET = create("iron_helmet");

    /**
     * Key for {@code minecraft:iron_hoe}.
     */
    public static final TypedKey<Item> IRON_HOE = create("iron_hoe");

    /**
     * Key for {@code minecraft:iron_horse_armor}.
     */
    public static final TypedKey<Item> IRON_HORSE_ARMOR = create("iron_horse_armor");

    /**
     * Key for {@code minecraft:iron_ingot}.
     */
    public static final TypedKey<Item> IRON_INGOT = create("iron_ingot");

    /**
     * Key for {@code minecraft:iron_leggings}.
     */
    public static final TypedKey<Item> IRON_LEGGINGS = create("iron_leggings");

    /**
     * Key for {@code minecraft:iron_nautilus_armor}.
     */
    public static final TypedKey<Item> IRON_NAUTILUS_ARMOR = create("iron_nautilus_armor");

    /**
     * Key for {@code minecraft:iron_nugget}.
     */
    public static final TypedKey<Item> IRON_NUGGET = create("iron_nugget");

    /**
     * Key for {@code minecraft:iron_ore}.
     */
    public static final TypedKey<Item> IRON_ORE = create("iron_ore");

    /**
     * Key for {@code minecraft:iron_pickaxe}.
     */
    public static final TypedKey<Item> IRON_PICKAXE = create("iron_pickaxe");

    /**
     * Key for {@code minecraft:iron_shovel}.
     */
    public static final TypedKey<Item> IRON_SHOVEL = create("iron_shovel");

    /**
     * Key for {@code minecraft:iron_spear}.
     */
    public static final TypedKey<Item> IRON_SPEAR = create("iron_spear");

    /**
     * Key for {@code minecraft:iron_sword}.
     */
    public static final TypedKey<Item> IRON_SWORD = create("iron_sword");

    /**
     * Key for {@code minecraft:iron_trapdoor}.
     */
    public static final TypedKey<Item> IRON_TRAPDOOR = create("iron_trapdoor");

    /**
     * Key for {@code minecraft:item_frame}.
     */
    public static final TypedKey<Item> ITEM_FRAME = create("item_frame");

    /**
     * Key for {@code minecraft:jack_o_lantern}.
     */
    public static final TypedKey<Item> JACK_O_LANTERN = create("jack_o_lantern");

    /**
     * Key for {@code minecraft:jigsaw}.
     */
    public static final TypedKey<Item> JIGSAW = create("jigsaw");

    /**
     * Key for {@code minecraft:jukebox}.
     */
    public static final TypedKey<Item> JUKEBOX = create("jukebox");

    /**
     * Key for {@code minecraft:jungle_boat}.
     */
    public static final TypedKey<Item> JUNGLE_BOAT = create("jungle_boat");

    /**
     * Key for {@code minecraft:jungle_button}.
     */
    public static final TypedKey<Item> JUNGLE_BUTTON = create("jungle_button");

    /**
     * Key for {@code minecraft:jungle_chest_boat}.
     */
    public static final TypedKey<Item> JUNGLE_CHEST_BOAT = create("jungle_chest_boat");

    /**
     * Key for {@code minecraft:jungle_door}.
     */
    public static final TypedKey<Item> JUNGLE_DOOR = create("jungle_door");

    /**
     * Key for {@code minecraft:jungle_fence}.
     */
    public static final TypedKey<Item> JUNGLE_FENCE = create("jungle_fence");

    /**
     * Key for {@code minecraft:jungle_fence_gate}.
     */
    public static final TypedKey<Item> JUNGLE_FENCE_GATE = create("jungle_fence_gate");

    /**
     * Key for {@code minecraft:jungle_hanging_sign}.
     */
    public static final TypedKey<Item> JUNGLE_HANGING_SIGN = create("jungle_hanging_sign");

    /**
     * Key for {@code minecraft:jungle_leaves}.
     */
    public static final TypedKey<Item> JUNGLE_LEAVES = create("jungle_leaves");

    /**
     * Key for {@code minecraft:jungle_log}.
     */
    public static final TypedKey<Item> JUNGLE_LOG = create("jungle_log");

    /**
     * Key for {@code minecraft:jungle_planks}.
     */
    public static final TypedKey<Item> JUNGLE_PLANKS = create("jungle_planks");

    /**
     * Key for {@code minecraft:jungle_pressure_plate}.
     */
    public static final TypedKey<Item> JUNGLE_PRESSURE_PLATE = create("jungle_pressure_plate");

    /**
     * Key for {@code minecraft:jungle_pyramid_map}.
     */
    public static final TypedKey<Item> JUNGLE_PYRAMID_MAP = create("jungle_pyramid_map");

    /**
     * Key for {@code minecraft:jungle_sapling}.
     */
    public static final TypedKey<Item> JUNGLE_SAPLING = create("jungle_sapling");

    /**
     * Key for {@code minecraft:jungle_shelf}.
     */
    public static final TypedKey<Item> JUNGLE_SHELF = create("jungle_shelf");

    /**
     * Key for {@code minecraft:jungle_sign}.
     */
    public static final TypedKey<Item> JUNGLE_SIGN = create("jungle_sign");

    /**
     * Key for {@code minecraft:jungle_slab}.
     */
    public static final TypedKey<Item> JUNGLE_SLAB = create("jungle_slab");

    /**
     * Key for {@code minecraft:jungle_stairs}.
     */
    public static final TypedKey<Item> JUNGLE_STAIRS = create("jungle_stairs");

    /**
     * Key for {@code minecraft:jungle_trapdoor}.
     */
    public static final TypedKey<Item> JUNGLE_TRAPDOOR = create("jungle_trapdoor");

    /**
     * Key for {@code minecraft:jungle_wood}.
     */
    public static final TypedKey<Item> JUNGLE_WOOD = create("jungle_wood");

    /**
     * Key for {@code minecraft:kelp}.
     */
    public static final TypedKey<Item> KELP = create("kelp");

    /**
     * Key for {@code minecraft:knowledge_book}.
     */
    public static final TypedKey<Item> KNOWLEDGE_BOOK = create("knowledge_book");

    /**
     * Key for {@code minecraft:ladder}.
     */
    public static final TypedKey<Item> LADDER = create("ladder");

    /**
     * Key for {@code minecraft:lantern}.
     */
    public static final TypedKey<Item> LANTERN = create("lantern");

    /**
     * Key for {@code minecraft:lapis_block}.
     */
    public static final TypedKey<Item> LAPIS_BLOCK = create("lapis_block");

    /**
     * Key for {@code minecraft:lapis_lazuli}.
     */
    public static final TypedKey<Item> LAPIS_LAZULI = create("lapis_lazuli");

    /**
     * Key for {@code minecraft:lapis_ore}.
     */
    public static final TypedKey<Item> LAPIS_ORE = create("lapis_ore");

    /**
     * Key for {@code minecraft:large_amethyst_bud}.
     */
    public static final TypedKey<Item> LARGE_AMETHYST_BUD = create("large_amethyst_bud");

    /**
     * Key for {@code minecraft:large_fern}.
     */
    public static final TypedKey<Item> LARGE_FERN = create("large_fern");

    /**
     * Key for {@code minecraft:lava_bucket}.
     */
    public static final TypedKey<Item> LAVA_BUCKET = create("lava_bucket");

    /**
     * Key for {@code minecraft:lead}.
     */
    public static final TypedKey<Item> LEAD = create("lead");

    /**
     * Key for {@code minecraft:leaf_litter}.
     */
    public static final TypedKey<Item> LEAF_LITTER = create("leaf_litter");

    /**
     * Key for {@code minecraft:leather}.
     */
    public static final TypedKey<Item> LEATHER = create("leather");

    /**
     * Key for {@code minecraft:leather_boots}.
     */
    public static final TypedKey<Item> LEATHER_BOOTS = create("leather_boots");

    /**
     * Key for {@code minecraft:leather_chestplate}.
     */
    public static final TypedKey<Item> LEATHER_CHESTPLATE = create("leather_chestplate");

    /**
     * Key for {@code minecraft:leather_helmet}.
     */
    public static final TypedKey<Item> LEATHER_HELMET = create("leather_helmet");

    /**
     * Key for {@code minecraft:leather_horse_armor}.
     */
    public static final TypedKey<Item> LEATHER_HORSE_ARMOR = create("leather_horse_armor");

    /**
     * Key for {@code minecraft:leather_leggings}.
     */
    public static final TypedKey<Item> LEATHER_LEGGINGS = create("leather_leggings");

    /**
     * Key for {@code minecraft:lectern}.
     */
    public static final TypedKey<Item> LECTERN = create("lectern");

    /**
     * Key for {@code minecraft:lever}.
     */
    public static final TypedKey<Item> LEVER = create("lever");

    /**
     * Key for {@code minecraft:light}.
     */
    public static final TypedKey<Item> LIGHT = create("light");

    /**
     * Key for {@code minecraft:light_blue_banner}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_BANNER = create("light_blue_banner");

    /**
     * Key for {@code minecraft:light_blue_bed}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_BED = create("light_blue_bed");

    /**
     * Key for {@code minecraft:light_blue_bundle}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_BUNDLE = create("light_blue_bundle");

    /**
     * Key for {@code minecraft:light_blue_candle}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_CANDLE = create("light_blue_candle");

    /**
     * Key for {@code minecraft:light_blue_carpet}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_CARPET = create("light_blue_carpet");

    /**
     * Key for {@code minecraft:light_blue_concrete}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_CONCRETE = create("light_blue_concrete");

    /**
     * Key for {@code minecraft:light_blue_concrete_powder}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_CONCRETE_POWDER = create("light_blue_concrete_powder");

    /**
     * Key for {@code minecraft:light_blue_concrete_slab}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_CONCRETE_SLAB = create("light_blue_concrete_slab");

    /**
     * Key for {@code minecraft:light_blue_concrete_stairs}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_CONCRETE_STAIRS = create("light_blue_concrete_stairs");

    /**
     * Key for {@code minecraft:light_blue_cushion}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_CUSHION = create("light_blue_cushion");

    /**
     * Key for {@code minecraft:light_blue_dye}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_DYE = create("light_blue_dye");

    /**
     * Key for {@code minecraft:light_blue_glazed_terracotta}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_GLAZED_TERRACOTTA = create("light_blue_glazed_terracotta");

    /**
     * Key for {@code minecraft:light_blue_harness}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_HARNESS = create("light_blue_harness");

    /**
     * Key for {@code minecraft:light_blue_shulker_box}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_SHULKER_BOX = create("light_blue_shulker_box");

    /**
     * Key for {@code minecraft:light_blue_stained_glass}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_STAINED_GLASS = create("light_blue_stained_glass");

    /**
     * Key for {@code minecraft:light_blue_stained_glass_pane}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_STAINED_GLASS_PANE = create("light_blue_stained_glass_pane");

    /**
     * Key for {@code minecraft:light_blue_terracotta}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_TERRACOTTA = create("light_blue_terracotta");

    /**
     * Key for {@code minecraft:light_blue_wool}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_WOOL = create("light_blue_wool");

    /**
     * Key for {@code minecraft:light_blue_wool_slab}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_WOOL_SLAB = create("light_blue_wool_slab");

    /**
     * Key for {@code minecraft:light_blue_wool_stairs}.
     */
    public static final TypedKey<Item> LIGHT_BLUE_WOOL_STAIRS = create("light_blue_wool_stairs");

    /**
     * Key for {@code minecraft:light_gray_banner}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_BANNER = create("light_gray_banner");

    /**
     * Key for {@code minecraft:light_gray_bed}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_BED = create("light_gray_bed");

    /**
     * Key for {@code minecraft:light_gray_bundle}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_BUNDLE = create("light_gray_bundle");

    /**
     * Key for {@code minecraft:light_gray_candle}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_CANDLE = create("light_gray_candle");

    /**
     * Key for {@code minecraft:light_gray_carpet}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_CARPET = create("light_gray_carpet");

    /**
     * Key for {@code minecraft:light_gray_concrete}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_CONCRETE = create("light_gray_concrete");

    /**
     * Key for {@code minecraft:light_gray_concrete_powder}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_CONCRETE_POWDER = create("light_gray_concrete_powder");

    /**
     * Key for {@code minecraft:light_gray_concrete_slab}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_CONCRETE_SLAB = create("light_gray_concrete_slab");

    /**
     * Key for {@code minecraft:light_gray_concrete_stairs}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_CONCRETE_STAIRS = create("light_gray_concrete_stairs");

    /**
     * Key for {@code minecraft:light_gray_cushion}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_CUSHION = create("light_gray_cushion");

    /**
     * Key for {@code minecraft:light_gray_dye}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_DYE = create("light_gray_dye");

    /**
     * Key for {@code minecraft:light_gray_glazed_terracotta}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_GLAZED_TERRACOTTA = create("light_gray_glazed_terracotta");

    /**
     * Key for {@code minecraft:light_gray_harness}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_HARNESS = create("light_gray_harness");

    /**
     * Key for {@code minecraft:light_gray_shulker_box}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_SHULKER_BOX = create("light_gray_shulker_box");

    /**
     * Key for {@code minecraft:light_gray_stained_glass}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_STAINED_GLASS = create("light_gray_stained_glass");

    /**
     * Key for {@code minecraft:light_gray_stained_glass_pane}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_STAINED_GLASS_PANE = create("light_gray_stained_glass_pane");

    /**
     * Key for {@code minecraft:light_gray_terracotta}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_TERRACOTTA = create("light_gray_terracotta");

    /**
     * Key for {@code minecraft:light_gray_wool}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_WOOL = create("light_gray_wool");

    /**
     * Key for {@code minecraft:light_gray_wool_slab}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_WOOL_SLAB = create("light_gray_wool_slab");

    /**
     * Key for {@code minecraft:light_gray_wool_stairs}.
     */
    public static final TypedKey<Item> LIGHT_GRAY_WOOL_STAIRS = create("light_gray_wool_stairs");

    /**
     * Key for {@code minecraft:light_weighted_pressure_plate}.
     */
    public static final TypedKey<Item> LIGHT_WEIGHTED_PRESSURE_PLATE = create("light_weighted_pressure_plate");

    /**
     * Key for {@code minecraft:lightning_rod}.
     */
    public static final TypedKey<Item> LIGHTNING_ROD = create("lightning_rod");

    /**
     * Key for {@code minecraft:lilac}.
     */
    public static final TypedKey<Item> LILAC = create("lilac");

    /**
     * Key for {@code minecraft:lily_of_the_valley}.
     */
    public static final TypedKey<Item> LILY_OF_THE_VALLEY = create("lily_of_the_valley");

    /**
     * Key for {@code minecraft:lily_pad}.
     */
    public static final TypedKey<Item> LILY_PAD = create("lily_pad");

    /**
     * Key for {@code minecraft:lime_banner}.
     */
    public static final TypedKey<Item> LIME_BANNER = create("lime_banner");

    /**
     * Key for {@code minecraft:lime_bed}.
     */
    public static final TypedKey<Item> LIME_BED = create("lime_bed");

    /**
     * Key for {@code minecraft:lime_bundle}.
     */
    public static final TypedKey<Item> LIME_BUNDLE = create("lime_bundle");

    /**
     * Key for {@code minecraft:lime_candle}.
     */
    public static final TypedKey<Item> LIME_CANDLE = create("lime_candle");

    /**
     * Key for {@code minecraft:lime_carpet}.
     */
    public static final TypedKey<Item> LIME_CARPET = create("lime_carpet");

    /**
     * Key for {@code minecraft:lime_concrete}.
     */
    public static final TypedKey<Item> LIME_CONCRETE = create("lime_concrete");

    /**
     * Key for {@code minecraft:lime_concrete_powder}.
     */
    public static final TypedKey<Item> LIME_CONCRETE_POWDER = create("lime_concrete_powder");

    /**
     * Key for {@code minecraft:lime_concrete_slab}.
     */
    public static final TypedKey<Item> LIME_CONCRETE_SLAB = create("lime_concrete_slab");

    /**
     * Key for {@code minecraft:lime_concrete_stairs}.
     */
    public static final TypedKey<Item> LIME_CONCRETE_STAIRS = create("lime_concrete_stairs");

    /**
     * Key for {@code minecraft:lime_cushion}.
     */
    public static final TypedKey<Item> LIME_CUSHION = create("lime_cushion");

    /**
     * Key for {@code minecraft:lime_dye}.
     */
    public static final TypedKey<Item> LIME_DYE = create("lime_dye");

    /**
     * Key for {@code minecraft:lime_glazed_terracotta}.
     */
    public static final TypedKey<Item> LIME_GLAZED_TERRACOTTA = create("lime_glazed_terracotta");

    /**
     * Key for {@code minecraft:lime_harness}.
     */
    public static final TypedKey<Item> LIME_HARNESS = create("lime_harness");

    /**
     * Key for {@code minecraft:lime_shulker_box}.
     */
    public static final TypedKey<Item> LIME_SHULKER_BOX = create("lime_shulker_box");

    /**
     * Key for {@code minecraft:lime_stained_glass}.
     */
    public static final TypedKey<Item> LIME_STAINED_GLASS = create("lime_stained_glass");

    /**
     * Key for {@code minecraft:lime_stained_glass_pane}.
     */
    public static final TypedKey<Item> LIME_STAINED_GLASS_PANE = create("lime_stained_glass_pane");

    /**
     * Key for {@code minecraft:lime_terracotta}.
     */
    public static final TypedKey<Item> LIME_TERRACOTTA = create("lime_terracotta");

    /**
     * Key for {@code minecraft:lime_wool}.
     */
    public static final TypedKey<Item> LIME_WOOL = create("lime_wool");

    /**
     * Key for {@code minecraft:lime_wool_slab}.
     */
    public static final TypedKey<Item> LIME_WOOL_SLAB = create("lime_wool_slab");

    /**
     * Key for {@code minecraft:lime_wool_stairs}.
     */
    public static final TypedKey<Item> LIME_WOOL_STAIRS = create("lime_wool_stairs");

    /**
     * Key for {@code minecraft:lingering_potion}.
     */
    public static final TypedKey<Item> LINGERING_POTION = create("lingering_potion");

    /**
     * Key for {@code minecraft:llama_spawn_egg}.
     */
    public static final TypedKey<Item> LLAMA_SPAWN_EGG = create("llama_spawn_egg");

    /**
     * Key for {@code minecraft:lodestone}.
     */
    public static final TypedKey<Item> LODESTONE = create("lodestone");

    /**
     * Key for {@code minecraft:loom}.
     */
    public static final TypedKey<Item> LOOM = create("loom");

    /**
     * Key for {@code minecraft:mace}.
     */
    public static final TypedKey<Item> MACE = create("mace");

    /**
     * Key for {@code minecraft:magenta_banner}.
     */
    public static final TypedKey<Item> MAGENTA_BANNER = create("magenta_banner");

    /**
     * Key for {@code minecraft:magenta_bed}.
     */
    public static final TypedKey<Item> MAGENTA_BED = create("magenta_bed");

    /**
     * Key for {@code minecraft:magenta_bundle}.
     */
    public static final TypedKey<Item> MAGENTA_BUNDLE = create("magenta_bundle");

    /**
     * Key for {@code minecraft:magenta_candle}.
     */
    public static final TypedKey<Item> MAGENTA_CANDLE = create("magenta_candle");

    /**
     * Key for {@code minecraft:magenta_carpet}.
     */
    public static final TypedKey<Item> MAGENTA_CARPET = create("magenta_carpet");

    /**
     * Key for {@code minecraft:magenta_concrete}.
     */
    public static final TypedKey<Item> MAGENTA_CONCRETE = create("magenta_concrete");

    /**
     * Key for {@code minecraft:magenta_concrete_powder}.
     */
    public static final TypedKey<Item> MAGENTA_CONCRETE_POWDER = create("magenta_concrete_powder");

    /**
     * Key for {@code minecraft:magenta_concrete_slab}.
     */
    public static final TypedKey<Item> MAGENTA_CONCRETE_SLAB = create("magenta_concrete_slab");

    /**
     * Key for {@code minecraft:magenta_concrete_stairs}.
     */
    public static final TypedKey<Item> MAGENTA_CONCRETE_STAIRS = create("magenta_concrete_stairs");

    /**
     * Key for {@code minecraft:magenta_cushion}.
     */
    public static final TypedKey<Item> MAGENTA_CUSHION = create("magenta_cushion");

    /**
     * Key for {@code minecraft:magenta_dye}.
     */
    public static final TypedKey<Item> MAGENTA_DYE = create("magenta_dye");

    /**
     * Key for {@code minecraft:magenta_glazed_terracotta}.
     */
    public static final TypedKey<Item> MAGENTA_GLAZED_TERRACOTTA = create("magenta_glazed_terracotta");

    /**
     * Key for {@code minecraft:magenta_harness}.
     */
    public static final TypedKey<Item> MAGENTA_HARNESS = create("magenta_harness");

    /**
     * Key for {@code minecraft:magenta_shulker_box}.
     */
    public static final TypedKey<Item> MAGENTA_SHULKER_BOX = create("magenta_shulker_box");

    /**
     * Key for {@code minecraft:magenta_stained_glass}.
     */
    public static final TypedKey<Item> MAGENTA_STAINED_GLASS = create("magenta_stained_glass");

    /**
     * Key for {@code minecraft:magenta_stained_glass_pane}.
     */
    public static final TypedKey<Item> MAGENTA_STAINED_GLASS_PANE = create("magenta_stained_glass_pane");

    /**
     * Key for {@code minecraft:magenta_terracotta}.
     */
    public static final TypedKey<Item> MAGENTA_TERRACOTTA = create("magenta_terracotta");

    /**
     * Key for {@code minecraft:magenta_wool}.
     */
    public static final TypedKey<Item> MAGENTA_WOOL = create("magenta_wool");

    /**
     * Key for {@code minecraft:magenta_wool_slab}.
     */
    public static final TypedKey<Item> MAGENTA_WOOL_SLAB = create("magenta_wool_slab");

    /**
     * Key for {@code minecraft:magenta_wool_stairs}.
     */
    public static final TypedKey<Item> MAGENTA_WOOL_STAIRS = create("magenta_wool_stairs");

    /**
     * Key for {@code minecraft:magma_block}.
     */
    public static final TypedKey<Item> MAGMA_BLOCK = create("magma_block");

    /**
     * Key for {@code minecraft:magma_cream}.
     */
    public static final TypedKey<Item> MAGMA_CREAM = create("magma_cream");

    /**
     * Key for {@code minecraft:magma_cube_spawn_egg}.
     */
    public static final TypedKey<Item> MAGMA_CUBE_SPAWN_EGG = create("magma_cube_spawn_egg");

    /**
     * Key for {@code minecraft:mangrove_boat}.
     */
    public static final TypedKey<Item> MANGROVE_BOAT = create("mangrove_boat");

    /**
     * Key for {@code minecraft:mangrove_button}.
     */
    public static final TypedKey<Item> MANGROVE_BUTTON = create("mangrove_button");

    /**
     * Key for {@code minecraft:mangrove_chest_boat}.
     */
    public static final TypedKey<Item> MANGROVE_CHEST_BOAT = create("mangrove_chest_boat");

    /**
     * Key for {@code minecraft:mangrove_door}.
     */
    public static final TypedKey<Item> MANGROVE_DOOR = create("mangrove_door");

    /**
     * Key for {@code minecraft:mangrove_fence}.
     */
    public static final TypedKey<Item> MANGROVE_FENCE = create("mangrove_fence");

    /**
     * Key for {@code minecraft:mangrove_fence_gate}.
     */
    public static final TypedKey<Item> MANGROVE_FENCE_GATE = create("mangrove_fence_gate");

    /**
     * Key for {@code minecraft:mangrove_hanging_sign}.
     */
    public static final TypedKey<Item> MANGROVE_HANGING_SIGN = create("mangrove_hanging_sign");

    /**
     * Key for {@code minecraft:mangrove_leaves}.
     */
    public static final TypedKey<Item> MANGROVE_LEAVES = create("mangrove_leaves");

    /**
     * Key for {@code minecraft:mangrove_log}.
     */
    public static final TypedKey<Item> MANGROVE_LOG = create("mangrove_log");

    /**
     * Key for {@code minecraft:mangrove_planks}.
     */
    public static final TypedKey<Item> MANGROVE_PLANKS = create("mangrove_planks");

    /**
     * Key for {@code minecraft:mangrove_pressure_plate}.
     */
    public static final TypedKey<Item> MANGROVE_PRESSURE_PLATE = create("mangrove_pressure_plate");

    /**
     * Key for {@code minecraft:mangrove_propagule}.
     */
    public static final TypedKey<Item> MANGROVE_PROPAGULE = create("mangrove_propagule");

    /**
     * Key for {@code minecraft:mangrove_roots}.
     */
    public static final TypedKey<Item> MANGROVE_ROOTS = create("mangrove_roots");

    /**
     * Key for {@code minecraft:mangrove_shelf}.
     */
    public static final TypedKey<Item> MANGROVE_SHELF = create("mangrove_shelf");

    /**
     * Key for {@code minecraft:mangrove_sign}.
     */
    public static final TypedKey<Item> MANGROVE_SIGN = create("mangrove_sign");

    /**
     * Key for {@code minecraft:mangrove_slab}.
     */
    public static final TypedKey<Item> MANGROVE_SLAB = create("mangrove_slab");

    /**
     * Key for {@code minecraft:mangrove_stairs}.
     */
    public static final TypedKey<Item> MANGROVE_STAIRS = create("mangrove_stairs");

    /**
     * Key for {@code minecraft:mangrove_trapdoor}.
     */
    public static final TypedKey<Item> MANGROVE_TRAPDOOR = create("mangrove_trapdoor");

    /**
     * Key for {@code minecraft:mangrove_wood}.
     */
    public static final TypedKey<Item> MANGROVE_WOOD = create("mangrove_wood");

    /**
     * Key for {@code minecraft:map}.
     */
    public static final TypedKey<Item> MAP = create("map");

    /**
     * Key for {@code minecraft:medium_amethyst_bud}.
     */
    public static final TypedKey<Item> MEDIUM_AMETHYST_BUD = create("medium_amethyst_bud");

    /**
     * Key for {@code minecraft:melon}.
     */
    public static final TypedKey<Item> MELON = create("melon");

    /**
     * Key for {@code minecraft:melon_seeds}.
     */
    public static final TypedKey<Item> MELON_SEEDS = create("melon_seeds");

    /**
     * Key for {@code minecraft:melon_slice}.
     */
    public static final TypedKey<Item> MELON_SLICE = create("melon_slice");

    /**
     * Key for {@code minecraft:milk_bucket}.
     */
    public static final TypedKey<Item> MILK_BUCKET = create("milk_bucket");

    /**
     * Key for {@code minecraft:minecart}.
     */
    public static final TypedKey<Item> MINECART = create("minecart");

    /**
     * Key for {@code minecraft:miner_pottery_sherd}.
     */
    public static final TypedKey<Item> MINER_POTTERY_SHERD = create("miner_pottery_sherd");

    /**
     * Key for {@code minecraft:mojang_banner_pattern}.
     */
    public static final TypedKey<Item> MOJANG_BANNER_PATTERN = create("mojang_banner_pattern");

    /**
     * Key for {@code minecraft:mooshroom_spawn_egg}.
     */
    public static final TypedKey<Item> MOOSHROOM_SPAWN_EGG = create("mooshroom_spawn_egg");

    /**
     * Key for {@code minecraft:moss_block}.
     */
    public static final TypedKey<Item> MOSS_BLOCK = create("moss_block");

    /**
     * Key for {@code minecraft:moss_carpet}.
     */
    public static final TypedKey<Item> MOSS_CARPET = create("moss_carpet");

    /**
     * Key for {@code minecraft:mossy_cobblestone}.
     */
    public static final TypedKey<Item> MOSSY_COBBLESTONE = create("mossy_cobblestone");

    /**
     * Key for {@code minecraft:mossy_cobblestone_slab}.
     */
    public static final TypedKey<Item> MOSSY_COBBLESTONE_SLAB = create("mossy_cobblestone_slab");

    /**
     * Key for {@code minecraft:mossy_cobblestone_stairs}.
     */
    public static final TypedKey<Item> MOSSY_COBBLESTONE_STAIRS = create("mossy_cobblestone_stairs");

    /**
     * Key for {@code minecraft:mossy_cobblestone_wall}.
     */
    public static final TypedKey<Item> MOSSY_COBBLESTONE_WALL = create("mossy_cobblestone_wall");

    /**
     * Key for {@code minecraft:mossy_stone_brick_slab}.
     */
    public static final TypedKey<Item> MOSSY_STONE_BRICK_SLAB = create("mossy_stone_brick_slab");

    /**
     * Key for {@code minecraft:mossy_stone_brick_stairs}.
     */
    public static final TypedKey<Item> MOSSY_STONE_BRICK_STAIRS = create("mossy_stone_brick_stairs");

    /**
     * Key for {@code minecraft:mossy_stone_brick_wall}.
     */
    public static final TypedKey<Item> MOSSY_STONE_BRICK_WALL = create("mossy_stone_brick_wall");

    /**
     * Key for {@code minecraft:mossy_stone_bricks}.
     */
    public static final TypedKey<Item> MOSSY_STONE_BRICKS = create("mossy_stone_bricks");

    /**
     * Key for {@code minecraft:mourner_pottery_sherd}.
     */
    public static final TypedKey<Item> MOURNER_POTTERY_SHERD = create("mourner_pottery_sherd");

    /**
     * Key for {@code minecraft:mud}.
     */
    public static final TypedKey<Item> MUD = create("mud");

    /**
     * Key for {@code minecraft:mud_brick_slab}.
     */
    public static final TypedKey<Item> MUD_BRICK_SLAB = create("mud_brick_slab");

    /**
     * Key for {@code minecraft:mud_brick_stairs}.
     */
    public static final TypedKey<Item> MUD_BRICK_STAIRS = create("mud_brick_stairs");

    /**
     * Key for {@code minecraft:mud_brick_wall}.
     */
    public static final TypedKey<Item> MUD_BRICK_WALL = create("mud_brick_wall");

    /**
     * Key for {@code minecraft:mud_bricks}.
     */
    public static final TypedKey<Item> MUD_BRICKS = create("mud_bricks");

    /**
     * Key for {@code minecraft:muddy_mangrove_roots}.
     */
    public static final TypedKey<Item> MUDDY_MANGROVE_ROOTS = create("muddy_mangrove_roots");

    /**
     * Key for {@code minecraft:mule_spawn_egg}.
     */
    public static final TypedKey<Item> MULE_SPAWN_EGG = create("mule_spawn_egg");

    /**
     * Key for {@code minecraft:mushroom_stem}.
     */
    public static final TypedKey<Item> MUSHROOM_STEM = create("mushroom_stem");

    /**
     * Key for {@code minecraft:mushroom_stew}.
     */
    public static final TypedKey<Item> MUSHROOM_STEW = create("mushroom_stew");

    /**
     * Key for {@code minecraft:music_disc_11}.
     */
    public static final TypedKey<Item> MUSIC_DISC_11 = create("music_disc_11");

    /**
     * Key for {@code minecraft:music_disc_13}.
     */
    public static final TypedKey<Item> MUSIC_DISC_13 = create("music_disc_13");

    /**
     * Key for {@code minecraft:music_disc_5}.
     */
    public static final TypedKey<Item> MUSIC_DISC_5 = create("music_disc_5");

    /**
     * Key for {@code minecraft:music_disc_blocks}.
     */
    public static final TypedKey<Item> MUSIC_DISC_BLOCKS = create("music_disc_blocks");

    /**
     * Key for {@code minecraft:music_disc_bounce}.
     */
    public static final TypedKey<Item> MUSIC_DISC_BOUNCE = create("music_disc_bounce");

    /**
     * Key for {@code minecraft:music_disc_cat}.
     */
    public static final TypedKey<Item> MUSIC_DISC_CAT = create("music_disc_cat");

    /**
     * Key for {@code minecraft:music_disc_chirp}.
     */
    public static final TypedKey<Item> MUSIC_DISC_CHIRP = create("music_disc_chirp");

    /**
     * Key for {@code minecraft:music_disc_creator}.
     */
    public static final TypedKey<Item> MUSIC_DISC_CREATOR = create("music_disc_creator");

    /**
     * Key for {@code minecraft:music_disc_creator_music_box}.
     */
    public static final TypedKey<Item> MUSIC_DISC_CREATOR_MUSIC_BOX = create("music_disc_creator_music_box");

    /**
     * Key for {@code minecraft:music_disc_far}.
     */
    public static final TypedKey<Item> MUSIC_DISC_FAR = create("music_disc_far");

    /**
     * Key for {@code minecraft:music_disc_lava_chicken}.
     */
    public static final TypedKey<Item> MUSIC_DISC_LAVA_CHICKEN = create("music_disc_lava_chicken");

    /**
     * Key for {@code minecraft:music_disc_mall}.
     */
    public static final TypedKey<Item> MUSIC_DISC_MALL = create("music_disc_mall");

    /**
     * Key for {@code minecraft:music_disc_mellohi}.
     */
    public static final TypedKey<Item> MUSIC_DISC_MELLOHI = create("music_disc_mellohi");

    /**
     * Key for {@code minecraft:music_disc_otherside}.
     */
    public static final TypedKey<Item> MUSIC_DISC_OTHERSIDE = create("music_disc_otherside");

    /**
     * Key for {@code minecraft:music_disc_pigstep}.
     */
    public static final TypedKey<Item> MUSIC_DISC_PIGSTEP = create("music_disc_pigstep");

    /**
     * Key for {@code minecraft:music_disc_precipice}.
     */
    public static final TypedKey<Item> MUSIC_DISC_PRECIPICE = create("music_disc_precipice");

    /**
     * Key for {@code minecraft:music_disc_relic}.
     */
    public static final TypedKey<Item> MUSIC_DISC_RELIC = create("music_disc_relic");

    /**
     * Key for {@code minecraft:music_disc_stal}.
     */
    public static final TypedKey<Item> MUSIC_DISC_STAL = create("music_disc_stal");

    /**
     * Key for {@code minecraft:music_disc_strad}.
     */
    public static final TypedKey<Item> MUSIC_DISC_STRAD = create("music_disc_strad");

    /**
     * Key for {@code minecraft:music_disc_tears}.
     */
    public static final TypedKey<Item> MUSIC_DISC_TEARS = create("music_disc_tears");

    /**
     * Key for {@code minecraft:music_disc_wait}.
     */
    public static final TypedKey<Item> MUSIC_DISC_WAIT = create("music_disc_wait");

    /**
     * Key for {@code minecraft:music_disc_ward}.
     */
    public static final TypedKey<Item> MUSIC_DISC_WARD = create("music_disc_ward");

    /**
     * Key for {@code minecraft:mutton}.
     */
    public static final TypedKey<Item> MUTTON = create("mutton");

    /**
     * Key for {@code minecraft:mycelium}.
     */
    public static final TypedKey<Item> MYCELIUM = create("mycelium");

    /**
     * Key for {@code minecraft:name_tag}.
     */
    public static final TypedKey<Item> NAME_TAG = create("name_tag");

    /**
     * Key for {@code minecraft:nautilus_shell}.
     */
    public static final TypedKey<Item> NAUTILUS_SHELL = create("nautilus_shell");

    /**
     * Key for {@code minecraft:nautilus_spawn_egg}.
     */
    public static final TypedKey<Item> NAUTILUS_SPAWN_EGG = create("nautilus_spawn_egg");

    /**
     * Key for {@code minecraft:nether_brick}.
     */
    public static final TypedKey<Item> NETHER_BRICK = create("nether_brick");

    /**
     * Key for {@code minecraft:nether_brick_fence}.
     */
    public static final TypedKey<Item> NETHER_BRICK_FENCE = create("nether_brick_fence");

    /**
     * Key for {@code minecraft:nether_brick_slab}.
     */
    public static final TypedKey<Item> NETHER_BRICK_SLAB = create("nether_brick_slab");

    /**
     * Key for {@code minecraft:nether_brick_stairs}.
     */
    public static final TypedKey<Item> NETHER_BRICK_STAIRS = create("nether_brick_stairs");

    /**
     * Key for {@code minecraft:nether_brick_wall}.
     */
    public static final TypedKey<Item> NETHER_BRICK_WALL = create("nether_brick_wall");

    /**
     * Key for {@code minecraft:nether_bricks}.
     */
    public static final TypedKey<Item> NETHER_BRICKS = create("nether_bricks");

    /**
     * Key for {@code minecraft:nether_gold_ore}.
     */
    public static final TypedKey<Item> NETHER_GOLD_ORE = create("nether_gold_ore");

    /**
     * Key for {@code minecraft:nether_quartz_ore}.
     */
    public static final TypedKey<Item> NETHER_QUARTZ_ORE = create("nether_quartz_ore");

    /**
     * Key for {@code minecraft:nether_sprouts}.
     */
    public static final TypedKey<Item> NETHER_SPROUTS = create("nether_sprouts");

    /**
     * Key for {@code minecraft:nether_star}.
     */
    public static final TypedKey<Item> NETHER_STAR = create("nether_star");

    /**
     * Key for {@code minecraft:nether_wart}.
     */
    public static final TypedKey<Item> NETHER_WART = create("nether_wart");

    /**
     * Key for {@code minecraft:nether_wart_block}.
     */
    public static final TypedKey<Item> NETHER_WART_BLOCK = create("nether_wart_block");

    /**
     * Key for {@code minecraft:netherite_axe}.
     */
    public static final TypedKey<Item> NETHERITE_AXE = create("netherite_axe");

    /**
     * Key for {@code minecraft:netherite_block}.
     */
    public static final TypedKey<Item> NETHERITE_BLOCK = create("netherite_block");

    /**
     * Key for {@code minecraft:netherite_boots}.
     */
    public static final TypedKey<Item> NETHERITE_BOOTS = create("netherite_boots");

    /**
     * Key for {@code minecraft:netherite_chestplate}.
     */
    public static final TypedKey<Item> NETHERITE_CHESTPLATE = create("netherite_chestplate");

    /**
     * Key for {@code minecraft:netherite_helmet}.
     */
    public static final TypedKey<Item> NETHERITE_HELMET = create("netherite_helmet");

    /**
     * Key for {@code minecraft:netherite_hoe}.
     */
    public static final TypedKey<Item> NETHERITE_HOE = create("netherite_hoe");

    /**
     * Key for {@code minecraft:netherite_horse_armor}.
     */
    public static final TypedKey<Item> NETHERITE_HORSE_ARMOR = create("netherite_horse_armor");

    /**
     * Key for {@code minecraft:netherite_ingot}.
     */
    public static final TypedKey<Item> NETHERITE_INGOT = create("netherite_ingot");

    /**
     * Key for {@code minecraft:netherite_leggings}.
     */
    public static final TypedKey<Item> NETHERITE_LEGGINGS = create("netherite_leggings");

    /**
     * Key for {@code minecraft:netherite_nautilus_armor}.
     */
    public static final TypedKey<Item> NETHERITE_NAUTILUS_ARMOR = create("netherite_nautilus_armor");

    /**
     * Key for {@code minecraft:netherite_pickaxe}.
     */
    public static final TypedKey<Item> NETHERITE_PICKAXE = create("netherite_pickaxe");

    /**
     * Key for {@code minecraft:netherite_scrap}.
     */
    public static final TypedKey<Item> NETHERITE_SCRAP = create("netherite_scrap");

    /**
     * Key for {@code minecraft:netherite_shovel}.
     */
    public static final TypedKey<Item> NETHERITE_SHOVEL = create("netherite_shovel");

    /**
     * Key for {@code minecraft:netherite_spear}.
     */
    public static final TypedKey<Item> NETHERITE_SPEAR = create("netherite_spear");

    /**
     * Key for {@code minecraft:netherite_sword}.
     */
    public static final TypedKey<Item> NETHERITE_SWORD = create("netherite_sword");

    /**
     * Key for {@code minecraft:netherite_upgrade_smithing_template}.
     */
    public static final TypedKey<Item> NETHERITE_UPGRADE_SMITHING_TEMPLATE = create("netherite_upgrade_smithing_template");

    /**
     * Key for {@code minecraft:netherrack}.
     */
    public static final TypedKey<Item> NETHERRACK = create("netherrack");

    /**
     * Key for {@code minecraft:note_block}.
     */
    public static final TypedKey<Item> NOTE_BLOCK = create("note_block");

    /**
     * Key for {@code minecraft:oak_boat}.
     */
    public static final TypedKey<Item> OAK_BOAT = create("oak_boat");

    /**
     * Key for {@code minecraft:oak_button}.
     */
    public static final TypedKey<Item> OAK_BUTTON = create("oak_button");

    /**
     * Key for {@code minecraft:oak_chest_boat}.
     */
    public static final TypedKey<Item> OAK_CHEST_BOAT = create("oak_chest_boat");

    /**
     * Key for {@code minecraft:oak_door}.
     */
    public static final TypedKey<Item> OAK_DOOR = create("oak_door");

    /**
     * Key for {@code minecraft:oak_fence}.
     */
    public static final TypedKey<Item> OAK_FENCE = create("oak_fence");

    /**
     * Key for {@code minecraft:oak_fence_gate}.
     */
    public static final TypedKey<Item> OAK_FENCE_GATE = create("oak_fence_gate");

    /**
     * Key for {@code minecraft:oak_hanging_sign}.
     */
    public static final TypedKey<Item> OAK_HANGING_SIGN = create("oak_hanging_sign");

    /**
     * Key for {@code minecraft:oak_leaves}.
     */
    public static final TypedKey<Item> OAK_LEAVES = create("oak_leaves");

    /**
     * Key for {@code minecraft:oak_log}.
     */
    public static final TypedKey<Item> OAK_LOG = create("oak_log");

    /**
     * Key for {@code minecraft:oak_planks}.
     */
    public static final TypedKey<Item> OAK_PLANKS = create("oak_planks");

    /**
     * Key for {@code minecraft:oak_pressure_plate}.
     */
    public static final TypedKey<Item> OAK_PRESSURE_PLATE = create("oak_pressure_plate");

    /**
     * Key for {@code minecraft:oak_sapling}.
     */
    public static final TypedKey<Item> OAK_SAPLING = create("oak_sapling");

    /**
     * Key for {@code minecraft:oak_shelf}.
     */
    public static final TypedKey<Item> OAK_SHELF = create("oak_shelf");

    /**
     * Key for {@code minecraft:oak_sign}.
     */
    public static final TypedKey<Item> OAK_SIGN = create("oak_sign");

    /**
     * Key for {@code minecraft:oak_slab}.
     */
    public static final TypedKey<Item> OAK_SLAB = create("oak_slab");

    /**
     * Key for {@code minecraft:oak_stairs}.
     */
    public static final TypedKey<Item> OAK_STAIRS = create("oak_stairs");

    /**
     * Key for {@code minecraft:oak_trapdoor}.
     */
    public static final TypedKey<Item> OAK_TRAPDOOR = create("oak_trapdoor");

    /**
     * Key for {@code minecraft:oak_wood}.
     */
    public static final TypedKey<Item> OAK_WOOD = create("oak_wood");

    /**
     * Key for {@code minecraft:observer}.
     */
    public static final TypedKey<Item> OBSERVER = create("observer");

    /**
     * Key for {@code minecraft:obsidian}.
     */
    public static final TypedKey<Item> OBSIDIAN = create("obsidian");

    /**
     * Key for {@code minecraft:ocean_monument_map}.
     */
    public static final TypedKey<Item> OCEAN_MONUMENT_MAP = create("ocean_monument_map");

    /**
     * Key for {@code minecraft:ocelot_spawn_egg}.
     */
    public static final TypedKey<Item> OCELOT_SPAWN_EGG = create("ocelot_spawn_egg");

    /**
     * Key for {@code minecraft:ochre_froglight}.
     */
    public static final TypedKey<Item> OCHRE_FROGLIGHT = create("ochre_froglight");

    /**
     * Key for {@code minecraft:ominous_bottle}.
     */
    public static final TypedKey<Item> OMINOUS_BOTTLE = create("ominous_bottle");

    /**
     * Key for {@code minecraft:ominous_trial_key}.
     */
    public static final TypedKey<Item> OMINOUS_TRIAL_KEY = create("ominous_trial_key");

    /**
     * Key for {@code minecraft:open_eyeblossom}.
     */
    public static final TypedKey<Item> OPEN_EYEBLOSSOM = create("open_eyeblossom");

    /**
     * Key for {@code minecraft:orange_banner}.
     */
    public static final TypedKey<Item> ORANGE_BANNER = create("orange_banner");

    /**
     * Key for {@code minecraft:orange_bed}.
     */
    public static final TypedKey<Item> ORANGE_BED = create("orange_bed");

    /**
     * Key for {@code minecraft:orange_bundle}.
     */
    public static final TypedKey<Item> ORANGE_BUNDLE = create("orange_bundle");

    /**
     * Key for {@code minecraft:orange_candle}.
     */
    public static final TypedKey<Item> ORANGE_CANDLE = create("orange_candle");

    /**
     * Key for {@code minecraft:orange_carpet}.
     */
    public static final TypedKey<Item> ORANGE_CARPET = create("orange_carpet");

    /**
     * Key for {@code minecraft:orange_concrete}.
     */
    public static final TypedKey<Item> ORANGE_CONCRETE = create("orange_concrete");

    /**
     * Key for {@code minecraft:orange_concrete_powder}.
     */
    public static final TypedKey<Item> ORANGE_CONCRETE_POWDER = create("orange_concrete_powder");

    /**
     * Key for {@code minecraft:orange_concrete_slab}.
     */
    public static final TypedKey<Item> ORANGE_CONCRETE_SLAB = create("orange_concrete_slab");

    /**
     * Key for {@code minecraft:orange_concrete_stairs}.
     */
    public static final TypedKey<Item> ORANGE_CONCRETE_STAIRS = create("orange_concrete_stairs");

    /**
     * Key for {@code minecraft:orange_cushion}.
     */
    public static final TypedKey<Item> ORANGE_CUSHION = create("orange_cushion");

    /**
     * Key for {@code minecraft:orange_dye}.
     */
    public static final TypedKey<Item> ORANGE_DYE = create("orange_dye");

    /**
     * Key for {@code minecraft:orange_glazed_terracotta}.
     */
    public static final TypedKey<Item> ORANGE_GLAZED_TERRACOTTA = create("orange_glazed_terracotta");

    /**
     * Key for {@code minecraft:orange_harness}.
     */
    public static final TypedKey<Item> ORANGE_HARNESS = create("orange_harness");

    /**
     * Key for {@code minecraft:orange_poplar_leaves}.
     */
    public static final TypedKey<Item> ORANGE_POPLAR_LEAVES = create("orange_poplar_leaves");

    /**
     * Key for {@code minecraft:orange_shulker_box}.
     */
    public static final TypedKey<Item> ORANGE_SHULKER_BOX = create("orange_shulker_box");

    /**
     * Key for {@code minecraft:orange_stained_glass}.
     */
    public static final TypedKey<Item> ORANGE_STAINED_GLASS = create("orange_stained_glass");

    /**
     * Key for {@code minecraft:orange_stained_glass_pane}.
     */
    public static final TypedKey<Item> ORANGE_STAINED_GLASS_PANE = create("orange_stained_glass_pane");

    /**
     * Key for {@code minecraft:orange_terracotta}.
     */
    public static final TypedKey<Item> ORANGE_TERRACOTTA = create("orange_terracotta");

    /**
     * Key for {@code minecraft:orange_tulip}.
     */
    public static final TypedKey<Item> ORANGE_TULIP = create("orange_tulip");

    /**
     * Key for {@code minecraft:orange_wool}.
     */
    public static final TypedKey<Item> ORANGE_WOOL = create("orange_wool");

    /**
     * Key for {@code minecraft:orange_wool_slab}.
     */
    public static final TypedKey<Item> ORANGE_WOOL_SLAB = create("orange_wool_slab");

    /**
     * Key for {@code minecraft:orange_wool_stairs}.
     */
    public static final TypedKey<Item> ORANGE_WOOL_STAIRS = create("orange_wool_stairs");

    /**
     * Key for {@code minecraft:oxeye_daisy}.
     */
    public static final TypedKey<Item> OXEYE_DAISY = create("oxeye_daisy");

    /**
     * Key for {@code minecraft:oxidized_chiseled_copper}.
     */
    public static final TypedKey<Item> OXIDIZED_CHISELED_COPPER = create("oxidized_chiseled_copper");

    /**
     * Key for {@code minecraft:oxidized_copper}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER = create("oxidized_copper");

    /**
     * Key for {@code minecraft:oxidized_copper_bars}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_BARS = create("oxidized_copper_bars");

    /**
     * Key for {@code minecraft:oxidized_copper_bulb}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_BULB = create("oxidized_copper_bulb");

    /**
     * Key for {@code minecraft:oxidized_copper_chain}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_CHAIN = create("oxidized_copper_chain");

    /**
     * Key for {@code minecraft:oxidized_copper_chest}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_CHEST = create("oxidized_copper_chest");

    /**
     * Key for {@code minecraft:oxidized_copper_door}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_DOOR = create("oxidized_copper_door");

    /**
     * Key for {@code minecraft:oxidized_copper_golem_statue}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_GOLEM_STATUE = create("oxidized_copper_golem_statue");

    /**
     * Key for {@code minecraft:oxidized_copper_grate}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_GRATE = create("oxidized_copper_grate");

    /**
     * Key for {@code minecraft:oxidized_copper_lantern}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_LANTERN = create("oxidized_copper_lantern");

    /**
     * Key for {@code minecraft:oxidized_copper_trapdoor}.
     */
    public static final TypedKey<Item> OXIDIZED_COPPER_TRAPDOOR = create("oxidized_copper_trapdoor");

    /**
     * Key for {@code minecraft:oxidized_cut_copper}.
     */
    public static final TypedKey<Item> OXIDIZED_CUT_COPPER = create("oxidized_cut_copper");

    /**
     * Key for {@code minecraft:oxidized_cut_copper_slab}.
     */
    public static final TypedKey<Item> OXIDIZED_CUT_COPPER_SLAB = create("oxidized_cut_copper_slab");

    /**
     * Key for {@code minecraft:oxidized_cut_copper_stairs}.
     */
    public static final TypedKey<Item> OXIDIZED_CUT_COPPER_STAIRS = create("oxidized_cut_copper_stairs");

    /**
     * Key for {@code minecraft:oxidized_lightning_rod}.
     */
    public static final TypedKey<Item> OXIDIZED_LIGHTNING_ROD = create("oxidized_lightning_rod");

    /**
     * Key for {@code minecraft:packed_ice}.
     */
    public static final TypedKey<Item> PACKED_ICE = create("packed_ice");

    /**
     * Key for {@code minecraft:packed_mud}.
     */
    public static final TypedKey<Item> PACKED_MUD = create("packed_mud");

    /**
     * Key for {@code minecraft:painting}.
     */
    public static final TypedKey<Item> PAINTING = create("painting");

    /**
     * Key for {@code minecraft:pale_hanging_moss}.
     */
    public static final TypedKey<Item> PALE_HANGING_MOSS = create("pale_hanging_moss");

    /**
     * Key for {@code minecraft:pale_moss_block}.
     */
    public static final TypedKey<Item> PALE_MOSS_BLOCK = create("pale_moss_block");

    /**
     * Key for {@code minecraft:pale_moss_carpet}.
     */
    public static final TypedKey<Item> PALE_MOSS_CARPET = create("pale_moss_carpet");

    /**
     * Key for {@code minecraft:pale_oak_boat}.
     */
    public static final TypedKey<Item> PALE_OAK_BOAT = create("pale_oak_boat");

    /**
     * Key for {@code minecraft:pale_oak_button}.
     */
    public static final TypedKey<Item> PALE_OAK_BUTTON = create("pale_oak_button");

    /**
     * Key for {@code minecraft:pale_oak_chest_boat}.
     */
    public static final TypedKey<Item> PALE_OAK_CHEST_BOAT = create("pale_oak_chest_boat");

    /**
     * Key for {@code minecraft:pale_oak_door}.
     */
    public static final TypedKey<Item> PALE_OAK_DOOR = create("pale_oak_door");

    /**
     * Key for {@code minecraft:pale_oak_fence}.
     */
    public static final TypedKey<Item> PALE_OAK_FENCE = create("pale_oak_fence");

    /**
     * Key for {@code minecraft:pale_oak_fence_gate}.
     */
    public static final TypedKey<Item> PALE_OAK_FENCE_GATE = create("pale_oak_fence_gate");

    /**
     * Key for {@code minecraft:pale_oak_hanging_sign}.
     */
    public static final TypedKey<Item> PALE_OAK_HANGING_SIGN = create("pale_oak_hanging_sign");

    /**
     * Key for {@code minecraft:pale_oak_leaves}.
     */
    public static final TypedKey<Item> PALE_OAK_LEAVES = create("pale_oak_leaves");

    /**
     * Key for {@code minecraft:pale_oak_log}.
     */
    public static final TypedKey<Item> PALE_OAK_LOG = create("pale_oak_log");

    /**
     * Key for {@code minecraft:pale_oak_planks}.
     */
    public static final TypedKey<Item> PALE_OAK_PLANKS = create("pale_oak_planks");

    /**
     * Key for {@code minecraft:pale_oak_pressure_plate}.
     */
    public static final TypedKey<Item> PALE_OAK_PRESSURE_PLATE = create("pale_oak_pressure_plate");

    /**
     * Key for {@code minecraft:pale_oak_sapling}.
     */
    public static final TypedKey<Item> PALE_OAK_SAPLING = create("pale_oak_sapling");

    /**
     * Key for {@code minecraft:pale_oak_shelf}.
     */
    public static final TypedKey<Item> PALE_OAK_SHELF = create("pale_oak_shelf");

    /**
     * Key for {@code minecraft:pale_oak_sign}.
     */
    public static final TypedKey<Item> PALE_OAK_SIGN = create("pale_oak_sign");

    /**
     * Key for {@code minecraft:pale_oak_slab}.
     */
    public static final TypedKey<Item> PALE_OAK_SLAB = create("pale_oak_slab");

    /**
     * Key for {@code minecraft:pale_oak_stairs}.
     */
    public static final TypedKey<Item> PALE_OAK_STAIRS = create("pale_oak_stairs");

    /**
     * Key for {@code minecraft:pale_oak_trapdoor}.
     */
    public static final TypedKey<Item> PALE_OAK_TRAPDOOR = create("pale_oak_trapdoor");

    /**
     * Key for {@code minecraft:pale_oak_wood}.
     */
    public static final TypedKey<Item> PALE_OAK_WOOD = create("pale_oak_wood");

    /**
     * Key for {@code minecraft:panda_spawn_egg}.
     */
    public static final TypedKey<Item> PANDA_SPAWN_EGG = create("panda_spawn_egg");

    /**
     * Key for {@code minecraft:paper}.
     */
    public static final TypedKey<Item> PAPER = create("paper");

    /**
     * Key for {@code minecraft:parched_spawn_egg}.
     */
    public static final TypedKey<Item> PARCHED_SPAWN_EGG = create("parched_spawn_egg");

    /**
     * Key for {@code minecraft:parrot_spawn_egg}.
     */
    public static final TypedKey<Item> PARROT_SPAWN_EGG = create("parrot_spawn_egg");

    /**
     * Key for {@code minecraft:pearlescent_froglight}.
     */
    public static final TypedKey<Item> PEARLESCENT_FROGLIGHT = create("pearlescent_froglight");

    /**
     * Key for {@code minecraft:peony}.
     */
    public static final TypedKey<Item> PEONY = create("peony");

    /**
     * Key for {@code minecraft:petrified_oak_slab}.
     */
    public static final TypedKey<Item> PETRIFIED_OAK_SLAB = create("petrified_oak_slab");

    /**
     * Key for {@code minecraft:phantom_membrane}.
     */
    public static final TypedKey<Item> PHANTOM_MEMBRANE = create("phantom_membrane");

    /**
     * Key for {@code minecraft:phantom_spawn_egg}.
     */
    public static final TypedKey<Item> PHANTOM_SPAWN_EGG = create("phantom_spawn_egg");

    /**
     * Key for {@code minecraft:pig_spawn_egg}.
     */
    public static final TypedKey<Item> PIG_SPAWN_EGG = create("pig_spawn_egg");

    /**
     * Key for {@code minecraft:piglin_banner_pattern}.
     */
    public static final TypedKey<Item> PIGLIN_BANNER_PATTERN = create("piglin_banner_pattern");

    /**
     * Key for {@code minecraft:piglin_brute_spawn_egg}.
     */
    public static final TypedKey<Item> PIGLIN_BRUTE_SPAWN_EGG = create("piglin_brute_spawn_egg");

    /**
     * Key for {@code minecraft:piglin_head}.
     */
    public static final TypedKey<Item> PIGLIN_HEAD = create("piglin_head");

    /**
     * Key for {@code minecraft:piglin_spawn_egg}.
     */
    public static final TypedKey<Item> PIGLIN_SPAWN_EGG = create("piglin_spawn_egg");

    /**
     * Key for {@code minecraft:pillager_spawn_egg}.
     */
    public static final TypedKey<Item> PILLAGER_SPAWN_EGG = create("pillager_spawn_egg");

    /**
     * Key for {@code minecraft:pink_banner}.
     */
    public static final TypedKey<Item> PINK_BANNER = create("pink_banner");

    /**
     * Key for {@code minecraft:pink_bed}.
     */
    public static final TypedKey<Item> PINK_BED = create("pink_bed");

    /**
     * Key for {@code minecraft:pink_bundle}.
     */
    public static final TypedKey<Item> PINK_BUNDLE = create("pink_bundle");

    /**
     * Key for {@code minecraft:pink_candle}.
     */
    public static final TypedKey<Item> PINK_CANDLE = create("pink_candle");

    /**
     * Key for {@code minecraft:pink_carpet}.
     */
    public static final TypedKey<Item> PINK_CARPET = create("pink_carpet");

    /**
     * Key for {@code minecraft:pink_concrete}.
     */
    public static final TypedKey<Item> PINK_CONCRETE = create("pink_concrete");

    /**
     * Key for {@code minecraft:pink_concrete_powder}.
     */
    public static final TypedKey<Item> PINK_CONCRETE_POWDER = create("pink_concrete_powder");

    /**
     * Key for {@code minecraft:pink_concrete_slab}.
     */
    public static final TypedKey<Item> PINK_CONCRETE_SLAB = create("pink_concrete_slab");

    /**
     * Key for {@code minecraft:pink_concrete_stairs}.
     */
    public static final TypedKey<Item> PINK_CONCRETE_STAIRS = create("pink_concrete_stairs");

    /**
     * Key for {@code minecraft:pink_cushion}.
     */
    public static final TypedKey<Item> PINK_CUSHION = create("pink_cushion");

    /**
     * Key for {@code minecraft:pink_dye}.
     */
    public static final TypedKey<Item> PINK_DYE = create("pink_dye");

    /**
     * Key for {@code minecraft:pink_glazed_terracotta}.
     */
    public static final TypedKey<Item> PINK_GLAZED_TERRACOTTA = create("pink_glazed_terracotta");

    /**
     * Key for {@code minecraft:pink_harness}.
     */
    public static final TypedKey<Item> PINK_HARNESS = create("pink_harness");

    /**
     * Key for {@code minecraft:pink_petals}.
     */
    public static final TypedKey<Item> PINK_PETALS = create("pink_petals");

    /**
     * Key for {@code minecraft:pink_shulker_box}.
     */
    public static final TypedKey<Item> PINK_SHULKER_BOX = create("pink_shulker_box");

    /**
     * Key for {@code minecraft:pink_stained_glass}.
     */
    public static final TypedKey<Item> PINK_STAINED_GLASS = create("pink_stained_glass");

    /**
     * Key for {@code minecraft:pink_stained_glass_pane}.
     */
    public static final TypedKey<Item> PINK_STAINED_GLASS_PANE = create("pink_stained_glass_pane");

    /**
     * Key for {@code minecraft:pink_terracotta}.
     */
    public static final TypedKey<Item> PINK_TERRACOTTA = create("pink_terracotta");

    /**
     * Key for {@code minecraft:pink_tulip}.
     */
    public static final TypedKey<Item> PINK_TULIP = create("pink_tulip");

    /**
     * Key for {@code minecraft:pink_wool}.
     */
    public static final TypedKey<Item> PINK_WOOL = create("pink_wool");

    /**
     * Key for {@code minecraft:pink_wool_slab}.
     */
    public static final TypedKey<Item> PINK_WOOL_SLAB = create("pink_wool_slab");

    /**
     * Key for {@code minecraft:pink_wool_stairs}.
     */
    public static final TypedKey<Item> PINK_WOOL_STAIRS = create("pink_wool_stairs");

    /**
     * Key for {@code minecraft:piston}.
     */
    public static final TypedKey<Item> PISTON = create("piston");

    /**
     * Key for {@code minecraft:pitcher_plant}.
     */
    public static final TypedKey<Item> PITCHER_PLANT = create("pitcher_plant");

    /**
     * Key for {@code minecraft:pitcher_pod}.
     */
    public static final TypedKey<Item> PITCHER_POD = create("pitcher_pod");

    /**
     * Key for {@code minecraft:plains_village_map}.
     */
    public static final TypedKey<Item> PLAINS_VILLAGE_MAP = create("plains_village_map");

    /**
     * Key for {@code minecraft:player_head}.
     */
    public static final TypedKey<Item> PLAYER_HEAD = create("player_head");

    /**
     * Key for {@code minecraft:plenty_pottery_sherd}.
     */
    public static final TypedKey<Item> PLENTY_POTTERY_SHERD = create("plenty_pottery_sherd");

    /**
     * Key for {@code minecraft:podzol}.
     */
    public static final TypedKey<Item> PODZOL = create("podzol");

    /**
     * Key for {@code minecraft:pointed_dripstone}.
     */
    public static final TypedKey<Item> POINTED_DRIPSTONE = create("pointed_dripstone");

    /**
     * Key for {@code minecraft:poisonous_potato}.
     */
    public static final TypedKey<Item> POISONOUS_POTATO = create("poisonous_potato");

    /**
     * Key for {@code minecraft:polar_bear_spawn_egg}.
     */
    public static final TypedKey<Item> POLAR_BEAR_SPAWN_EGG = create("polar_bear_spawn_egg");

    /**
     * Key for {@code minecraft:polished_andesite}.
     */
    public static final TypedKey<Item> POLISHED_ANDESITE = create("polished_andesite");

    /**
     * Key for {@code minecraft:polished_andesite_slab}.
     */
    public static final TypedKey<Item> POLISHED_ANDESITE_SLAB = create("polished_andesite_slab");

    /**
     * Key for {@code minecraft:polished_andesite_stairs}.
     */
    public static final TypedKey<Item> POLISHED_ANDESITE_STAIRS = create("polished_andesite_stairs");

    /**
     * Key for {@code minecraft:polished_basalt}.
     */
    public static final TypedKey<Item> POLISHED_BASALT = create("polished_basalt");

    /**
     * Key for {@code minecraft:polished_blackstone}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE = create("polished_blackstone");

    /**
     * Key for {@code minecraft:polished_blackstone_brick_slab}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_BRICK_SLAB = create("polished_blackstone_brick_slab");

    /**
     * Key for {@code minecraft:polished_blackstone_brick_stairs}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_BRICK_STAIRS = create("polished_blackstone_brick_stairs");

    /**
     * Key for {@code minecraft:polished_blackstone_brick_wall}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_BRICK_WALL = create("polished_blackstone_brick_wall");

    /**
     * Key for {@code minecraft:polished_blackstone_bricks}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_BRICKS = create("polished_blackstone_bricks");

    /**
     * Key for {@code minecraft:polished_blackstone_button}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_BUTTON = create("polished_blackstone_button");

    /**
     * Key for {@code minecraft:polished_blackstone_pressure_plate}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_PRESSURE_PLATE = create("polished_blackstone_pressure_plate");

    /**
     * Key for {@code minecraft:polished_blackstone_slab}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_SLAB = create("polished_blackstone_slab");

    /**
     * Key for {@code minecraft:polished_blackstone_stairs}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_STAIRS = create("polished_blackstone_stairs");

    /**
     * Key for {@code minecraft:polished_blackstone_wall}.
     */
    public static final TypedKey<Item> POLISHED_BLACKSTONE_WALL = create("polished_blackstone_wall");

    /**
     * Key for {@code minecraft:polished_cinnabar}.
     */
    public static final TypedKey<Item> POLISHED_CINNABAR = create("polished_cinnabar");

    /**
     * Key for {@code minecraft:polished_cinnabar_slab}.
     */
    public static final TypedKey<Item> POLISHED_CINNABAR_SLAB = create("polished_cinnabar_slab");

    /**
     * Key for {@code minecraft:polished_cinnabar_stairs}.
     */
    public static final TypedKey<Item> POLISHED_CINNABAR_STAIRS = create("polished_cinnabar_stairs");

    /**
     * Key for {@code minecraft:polished_cinnabar_wall}.
     */
    public static final TypedKey<Item> POLISHED_CINNABAR_WALL = create("polished_cinnabar_wall");

    /**
     * Key for {@code minecraft:polished_deepslate}.
     */
    public static final TypedKey<Item> POLISHED_DEEPSLATE = create("polished_deepslate");

    /**
     * Key for {@code minecraft:polished_deepslate_slab}.
     */
    public static final TypedKey<Item> POLISHED_DEEPSLATE_SLAB = create("polished_deepslate_slab");

    /**
     * Key for {@code minecraft:polished_deepslate_stairs}.
     */
    public static final TypedKey<Item> POLISHED_DEEPSLATE_STAIRS = create("polished_deepslate_stairs");

    /**
     * Key for {@code minecraft:polished_deepslate_wall}.
     */
    public static final TypedKey<Item> POLISHED_DEEPSLATE_WALL = create("polished_deepslate_wall");

    /**
     * Key for {@code minecraft:polished_diorite}.
     */
    public static final TypedKey<Item> POLISHED_DIORITE = create("polished_diorite");

    /**
     * Key for {@code minecraft:polished_diorite_slab}.
     */
    public static final TypedKey<Item> POLISHED_DIORITE_SLAB = create("polished_diorite_slab");

    /**
     * Key for {@code minecraft:polished_diorite_stairs}.
     */
    public static final TypedKey<Item> POLISHED_DIORITE_STAIRS = create("polished_diorite_stairs");

    /**
     * Key for {@code minecraft:polished_granite}.
     */
    public static final TypedKey<Item> POLISHED_GRANITE = create("polished_granite");

    /**
     * Key for {@code minecraft:polished_granite_slab}.
     */
    public static final TypedKey<Item> POLISHED_GRANITE_SLAB = create("polished_granite_slab");

    /**
     * Key for {@code minecraft:polished_granite_stairs}.
     */
    public static final TypedKey<Item> POLISHED_GRANITE_STAIRS = create("polished_granite_stairs");

    /**
     * Key for {@code minecraft:polished_sulfur}.
     */
    public static final TypedKey<Item> POLISHED_SULFUR = create("polished_sulfur");

    /**
     * Key for {@code minecraft:polished_sulfur_slab}.
     */
    public static final TypedKey<Item> POLISHED_SULFUR_SLAB = create("polished_sulfur_slab");

    /**
     * Key for {@code minecraft:polished_sulfur_stairs}.
     */
    public static final TypedKey<Item> POLISHED_SULFUR_STAIRS = create("polished_sulfur_stairs");

    /**
     * Key for {@code minecraft:polished_sulfur_wall}.
     */
    public static final TypedKey<Item> POLISHED_SULFUR_WALL = create("polished_sulfur_wall");

    /**
     * Key for {@code minecraft:polished_tuff}.
     */
    public static final TypedKey<Item> POLISHED_TUFF = create("polished_tuff");

    /**
     * Key for {@code minecraft:polished_tuff_slab}.
     */
    public static final TypedKey<Item> POLISHED_TUFF_SLAB = create("polished_tuff_slab");

    /**
     * Key for {@code minecraft:polished_tuff_stairs}.
     */
    public static final TypedKey<Item> POLISHED_TUFF_STAIRS = create("polished_tuff_stairs");

    /**
     * Key for {@code minecraft:polished_tuff_wall}.
     */
    public static final TypedKey<Item> POLISHED_TUFF_WALL = create("polished_tuff_wall");

    /**
     * Key for {@code minecraft:poplar_boat}.
     */
    public static final TypedKey<Item> POPLAR_BOAT = create("poplar_boat");

    /**
     * Key for {@code minecraft:poplar_button}.
     */
    public static final TypedKey<Item> POPLAR_BUTTON = create("poplar_button");

    /**
     * Key for {@code minecraft:poplar_chest_boat}.
     */
    public static final TypedKey<Item> POPLAR_CHEST_BOAT = create("poplar_chest_boat");

    /**
     * Key for {@code minecraft:poplar_door}.
     */
    public static final TypedKey<Item> POPLAR_DOOR = create("poplar_door");

    /**
     * Key for {@code minecraft:poplar_fence}.
     */
    public static final TypedKey<Item> POPLAR_FENCE = create("poplar_fence");

    /**
     * Key for {@code minecraft:poplar_fence_gate}.
     */
    public static final TypedKey<Item> POPLAR_FENCE_GATE = create("poplar_fence_gate");

    /**
     * Key for {@code minecraft:poplar_hanging_sign}.
     */
    public static final TypedKey<Item> POPLAR_HANGING_SIGN = create("poplar_hanging_sign");

    /**
     * Key for {@code minecraft:poplar_log}.
     */
    public static final TypedKey<Item> POPLAR_LOG = create("poplar_log");

    /**
     * Key for {@code minecraft:poplar_planks}.
     */
    public static final TypedKey<Item> POPLAR_PLANKS = create("poplar_planks");

    /**
     * Key for {@code minecraft:poplar_pressure_plate}.
     */
    public static final TypedKey<Item> POPLAR_PRESSURE_PLATE = create("poplar_pressure_plate");

    /**
     * Key for {@code minecraft:poplar_sapling}.
     */
    public static final TypedKey<Item> POPLAR_SAPLING = create("poplar_sapling");

    /**
     * Key for {@code minecraft:poplar_shelf}.
     */
    public static final TypedKey<Item> POPLAR_SHELF = create("poplar_shelf");

    /**
     * Key for {@code minecraft:poplar_sign}.
     */
    public static final TypedKey<Item> POPLAR_SIGN = create("poplar_sign");

    /**
     * Key for {@code minecraft:poplar_slab}.
     */
    public static final TypedKey<Item> POPLAR_SLAB = create("poplar_slab");

    /**
     * Key for {@code minecraft:poplar_stairs}.
     */
    public static final TypedKey<Item> POPLAR_STAIRS = create("poplar_stairs");

    /**
     * Key for {@code minecraft:poplar_trapdoor}.
     */
    public static final TypedKey<Item> POPLAR_TRAPDOOR = create("poplar_trapdoor");

    /**
     * Key for {@code minecraft:poplar_wood}.
     */
    public static final TypedKey<Item> POPLAR_WOOD = create("poplar_wood");

    /**
     * Key for {@code minecraft:popped_chorus_fruit}.
     */
    public static final TypedKey<Item> POPPED_CHORUS_FRUIT = create("popped_chorus_fruit");

    /**
     * Key for {@code minecraft:poppy}.
     */
    public static final TypedKey<Item> POPPY = create("poppy");

    /**
     * Key for {@code minecraft:porkchop}.
     */
    public static final TypedKey<Item> PORKCHOP = create("porkchop");

    /**
     * Key for {@code minecraft:potato}.
     */
    public static final TypedKey<Item> POTATO = create("potato");

    /**
     * Key for {@code minecraft:potent_sulfur}.
     */
    public static final TypedKey<Item> POTENT_SULFUR = create("potent_sulfur");

    /**
     * Key for {@code minecraft:potion}.
     */
    public static final TypedKey<Item> POTION = create("potion");

    /**
     * Key for {@code minecraft:powder_snow_bucket}.
     */
    public static final TypedKey<Item> POWDER_SNOW_BUCKET = create("powder_snow_bucket");

    /**
     * Key for {@code minecraft:powered_rail}.
     */
    public static final TypedKey<Item> POWERED_RAIL = create("powered_rail");

    /**
     * Key for {@code minecraft:prismarine}.
     */
    public static final TypedKey<Item> PRISMARINE = create("prismarine");

    /**
     * Key for {@code minecraft:prismarine_brick_slab}.
     */
    public static final TypedKey<Item> PRISMARINE_BRICK_SLAB = create("prismarine_brick_slab");

    /**
     * Key for {@code minecraft:prismarine_brick_stairs}.
     */
    public static final TypedKey<Item> PRISMARINE_BRICK_STAIRS = create("prismarine_brick_stairs");

    /**
     * Key for {@code minecraft:prismarine_bricks}.
     */
    public static final TypedKey<Item> PRISMARINE_BRICKS = create("prismarine_bricks");

    /**
     * Key for {@code minecraft:prismarine_crystals}.
     */
    public static final TypedKey<Item> PRISMARINE_CRYSTALS = create("prismarine_crystals");

    /**
     * Key for {@code minecraft:prismarine_shard}.
     */
    public static final TypedKey<Item> PRISMARINE_SHARD = create("prismarine_shard");

    /**
     * Key for {@code minecraft:prismarine_slab}.
     */
    public static final TypedKey<Item> PRISMARINE_SLAB = create("prismarine_slab");

    /**
     * Key for {@code minecraft:prismarine_stairs}.
     */
    public static final TypedKey<Item> PRISMARINE_STAIRS = create("prismarine_stairs");

    /**
     * Key for {@code minecraft:prismarine_wall}.
     */
    public static final TypedKey<Item> PRISMARINE_WALL = create("prismarine_wall");

    /**
     * Key for {@code minecraft:prize_pottery_sherd}.
     */
    public static final TypedKey<Item> PRIZE_POTTERY_SHERD = create("prize_pottery_sherd");

    /**
     * Key for {@code minecraft:pufferfish}.
     */
    public static final TypedKey<Item> PUFFERFISH = create("pufferfish");

    /**
     * Key for {@code minecraft:pufferfish_bucket}.
     */
    public static final TypedKey<Item> PUFFERFISH_BUCKET = create("pufferfish_bucket");

    /**
     * Key for {@code minecraft:pufferfish_spawn_egg}.
     */
    public static final TypedKey<Item> PUFFERFISH_SPAWN_EGG = create("pufferfish_spawn_egg");

    /**
     * Key for {@code minecraft:pumpkin}.
     */
    public static final TypedKey<Item> PUMPKIN = create("pumpkin");

    /**
     * Key for {@code minecraft:pumpkin_pie}.
     */
    public static final TypedKey<Item> PUMPKIN_PIE = create("pumpkin_pie");

    /**
     * Key for {@code minecraft:pumpkin_seeds}.
     */
    public static final TypedKey<Item> PUMPKIN_SEEDS = create("pumpkin_seeds");

    /**
     * Key for {@code minecraft:purple_banner}.
     */
    public static final TypedKey<Item> PURPLE_BANNER = create("purple_banner");

    /**
     * Key for {@code minecraft:purple_bed}.
     */
    public static final TypedKey<Item> PURPLE_BED = create("purple_bed");

    /**
     * Key for {@code minecraft:purple_bundle}.
     */
    public static final TypedKey<Item> PURPLE_BUNDLE = create("purple_bundle");

    /**
     * Key for {@code minecraft:purple_candle}.
     */
    public static final TypedKey<Item> PURPLE_CANDLE = create("purple_candle");

    /**
     * Key for {@code minecraft:purple_carpet}.
     */
    public static final TypedKey<Item> PURPLE_CARPET = create("purple_carpet");

    /**
     * Key for {@code minecraft:purple_concrete}.
     */
    public static final TypedKey<Item> PURPLE_CONCRETE = create("purple_concrete");

    /**
     * Key for {@code minecraft:purple_concrete_powder}.
     */
    public static final TypedKey<Item> PURPLE_CONCRETE_POWDER = create("purple_concrete_powder");

    /**
     * Key for {@code minecraft:purple_concrete_slab}.
     */
    public static final TypedKey<Item> PURPLE_CONCRETE_SLAB = create("purple_concrete_slab");

    /**
     * Key for {@code minecraft:purple_concrete_stairs}.
     */
    public static final TypedKey<Item> PURPLE_CONCRETE_STAIRS = create("purple_concrete_stairs");

    /**
     * Key for {@code minecraft:purple_cushion}.
     */
    public static final TypedKey<Item> PURPLE_CUSHION = create("purple_cushion");

    /**
     * Key for {@code minecraft:purple_dye}.
     */
    public static final TypedKey<Item> PURPLE_DYE = create("purple_dye");

    /**
     * Key for {@code minecraft:purple_glazed_terracotta}.
     */
    public static final TypedKey<Item> PURPLE_GLAZED_TERRACOTTA = create("purple_glazed_terracotta");

    /**
     * Key for {@code minecraft:purple_harness}.
     */
    public static final TypedKey<Item> PURPLE_HARNESS = create("purple_harness");

    /**
     * Key for {@code minecraft:purple_shulker_box}.
     */
    public static final TypedKey<Item> PURPLE_SHULKER_BOX = create("purple_shulker_box");

    /**
     * Key for {@code minecraft:purple_stained_glass}.
     */
    public static final TypedKey<Item> PURPLE_STAINED_GLASS = create("purple_stained_glass");

    /**
     * Key for {@code minecraft:purple_stained_glass_pane}.
     */
    public static final TypedKey<Item> PURPLE_STAINED_GLASS_PANE = create("purple_stained_glass_pane");

    /**
     * Key for {@code minecraft:purple_terracotta}.
     */
    public static final TypedKey<Item> PURPLE_TERRACOTTA = create("purple_terracotta");

    /**
     * Key for {@code minecraft:purple_wool}.
     */
    public static final TypedKey<Item> PURPLE_WOOL = create("purple_wool");

    /**
     * Key for {@code minecraft:purple_wool_slab}.
     */
    public static final TypedKey<Item> PURPLE_WOOL_SLAB = create("purple_wool_slab");

    /**
     * Key for {@code minecraft:purple_wool_stairs}.
     */
    public static final TypedKey<Item> PURPLE_WOOL_STAIRS = create("purple_wool_stairs");

    /**
     * Key for {@code minecraft:purpur_block}.
     */
    public static final TypedKey<Item> PURPUR_BLOCK = create("purpur_block");

    /**
     * Key for {@code minecraft:purpur_pillar}.
     */
    public static final TypedKey<Item> PURPUR_PILLAR = create("purpur_pillar");

    /**
     * Key for {@code minecraft:purpur_slab}.
     */
    public static final TypedKey<Item> PURPUR_SLAB = create("purpur_slab");

    /**
     * Key for {@code minecraft:purpur_stairs}.
     */
    public static final TypedKey<Item> PURPUR_STAIRS = create("purpur_stairs");

    /**
     * Key for {@code minecraft:quartz}.
     */
    public static final TypedKey<Item> QUARTZ = create("quartz");

    /**
     * Key for {@code minecraft:quartz_block}.
     */
    public static final TypedKey<Item> QUARTZ_BLOCK = create("quartz_block");

    /**
     * Key for {@code minecraft:quartz_bricks}.
     */
    public static final TypedKey<Item> QUARTZ_BRICKS = create("quartz_bricks");

    /**
     * Key for {@code minecraft:quartz_pillar}.
     */
    public static final TypedKey<Item> QUARTZ_PILLAR = create("quartz_pillar");

    /**
     * Key for {@code minecraft:quartz_slab}.
     */
    public static final TypedKey<Item> QUARTZ_SLAB = create("quartz_slab");

    /**
     * Key for {@code minecraft:quartz_stairs}.
     */
    public static final TypedKey<Item> QUARTZ_STAIRS = create("quartz_stairs");

    /**
     * Key for {@code minecraft:rabbit}.
     */
    public static final TypedKey<Item> RABBIT = create("rabbit");

    /**
     * Key for {@code minecraft:rabbit_foot}.
     */
    public static final TypedKey<Item> RABBIT_FOOT = create("rabbit_foot");

    /**
     * Key for {@code minecraft:rabbit_hide}.
     */
    public static final TypedKey<Item> RABBIT_HIDE = create("rabbit_hide");

    /**
     * Key for {@code minecraft:rabbit_spawn_egg}.
     */
    public static final TypedKey<Item> RABBIT_SPAWN_EGG = create("rabbit_spawn_egg");

    /**
     * Key for {@code minecraft:rabbit_stew}.
     */
    public static final TypedKey<Item> RABBIT_STEW = create("rabbit_stew");

    /**
     * Key for {@code minecraft:rail}.
     */
    public static final TypedKey<Item> RAIL = create("rail");

    /**
     * Key for {@code minecraft:raiser_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> RAISER_ARMOR_TRIM_SMITHING_TEMPLATE = create("raiser_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:ravager_spawn_egg}.
     */
    public static final TypedKey<Item> RAVAGER_SPAWN_EGG = create("ravager_spawn_egg");

    /**
     * Key for {@code minecraft:raw_copper}.
     */
    public static final TypedKey<Item> RAW_COPPER = create("raw_copper");

    /**
     * Key for {@code minecraft:raw_copper_block}.
     */
    public static final TypedKey<Item> RAW_COPPER_BLOCK = create("raw_copper_block");

    /**
     * Key for {@code minecraft:raw_gold}.
     */
    public static final TypedKey<Item> RAW_GOLD = create("raw_gold");

    /**
     * Key for {@code minecraft:raw_gold_block}.
     */
    public static final TypedKey<Item> RAW_GOLD_BLOCK = create("raw_gold_block");

    /**
     * Key for {@code minecraft:raw_iron}.
     */
    public static final TypedKey<Item> RAW_IRON = create("raw_iron");

    /**
     * Key for {@code minecraft:raw_iron_block}.
     */
    public static final TypedKey<Item> RAW_IRON_BLOCK = create("raw_iron_block");

    /**
     * Key for {@code minecraft:recovery_compass}.
     */
    public static final TypedKey<Item> RECOVERY_COMPASS = create("recovery_compass");

    /**
     * Key for {@code minecraft:red_banner}.
     */
    public static final TypedKey<Item> RED_BANNER = create("red_banner");

    /**
     * Key for {@code minecraft:red_bed}.
     */
    public static final TypedKey<Item> RED_BED = create("red_bed");

    /**
     * Key for {@code minecraft:red_bundle}.
     */
    public static final TypedKey<Item> RED_BUNDLE = create("red_bundle");

    /**
     * Key for {@code minecraft:red_candle}.
     */
    public static final TypedKey<Item> RED_CANDLE = create("red_candle");

    /**
     * Key for {@code minecraft:red_carpet}.
     */
    public static final TypedKey<Item> RED_CARPET = create("red_carpet");

    /**
     * Key for {@code minecraft:red_concrete}.
     */
    public static final TypedKey<Item> RED_CONCRETE = create("red_concrete");

    /**
     * Key for {@code minecraft:red_concrete_powder}.
     */
    public static final TypedKey<Item> RED_CONCRETE_POWDER = create("red_concrete_powder");

    /**
     * Key for {@code minecraft:red_concrete_slab}.
     */
    public static final TypedKey<Item> RED_CONCRETE_SLAB = create("red_concrete_slab");

    /**
     * Key for {@code minecraft:red_concrete_stairs}.
     */
    public static final TypedKey<Item> RED_CONCRETE_STAIRS = create("red_concrete_stairs");

    /**
     * Key for {@code minecraft:red_cushion}.
     */
    public static final TypedKey<Item> RED_CUSHION = create("red_cushion");

    /**
     * Key for {@code minecraft:red_dye}.
     */
    public static final TypedKey<Item> RED_DYE = create("red_dye");

    /**
     * Key for {@code minecraft:red_glazed_terracotta}.
     */
    public static final TypedKey<Item> RED_GLAZED_TERRACOTTA = create("red_glazed_terracotta");

    /**
     * Key for {@code minecraft:red_harness}.
     */
    public static final TypedKey<Item> RED_HARNESS = create("red_harness");

    /**
     * Key for {@code minecraft:red_mushroom}.
     */
    public static final TypedKey<Item> RED_MUSHROOM = create("red_mushroom");

    /**
     * Key for {@code minecraft:red_mushroom_block}.
     */
    public static final TypedKey<Item> RED_MUSHROOM_BLOCK = create("red_mushroom_block");

    /**
     * Key for {@code minecraft:red_nether_brick_slab}.
     */
    public static final TypedKey<Item> RED_NETHER_BRICK_SLAB = create("red_nether_brick_slab");

    /**
     * Key for {@code minecraft:red_nether_brick_stairs}.
     */
    public static final TypedKey<Item> RED_NETHER_BRICK_STAIRS = create("red_nether_brick_stairs");

    /**
     * Key for {@code minecraft:red_nether_brick_wall}.
     */
    public static final TypedKey<Item> RED_NETHER_BRICK_WALL = create("red_nether_brick_wall");

    /**
     * Key for {@code minecraft:red_nether_bricks}.
     */
    public static final TypedKey<Item> RED_NETHER_BRICKS = create("red_nether_bricks");

    /**
     * Key for {@code minecraft:red_poplar_leaves}.
     */
    public static final TypedKey<Item> RED_POPLAR_LEAVES = create("red_poplar_leaves");

    /**
     * Key for {@code minecraft:red_sand}.
     */
    public static final TypedKey<Item> RED_SAND = create("red_sand");

    /**
     * Key for {@code minecraft:red_sandstone}.
     */
    public static final TypedKey<Item> RED_SANDSTONE = create("red_sandstone");

    /**
     * Key for {@code minecraft:red_sandstone_slab}.
     */
    public static final TypedKey<Item> RED_SANDSTONE_SLAB = create("red_sandstone_slab");

    /**
     * Key for {@code minecraft:red_sandstone_stairs}.
     */
    public static final TypedKey<Item> RED_SANDSTONE_STAIRS = create("red_sandstone_stairs");

    /**
     * Key for {@code minecraft:red_sandstone_wall}.
     */
    public static final TypedKey<Item> RED_SANDSTONE_WALL = create("red_sandstone_wall");

    /**
     * Key for {@code minecraft:red_shrub}.
     */
    public static final TypedKey<Item> RED_SHRUB = create("red_shrub");

    /**
     * Key for {@code minecraft:red_shulker_box}.
     */
    public static final TypedKey<Item> RED_SHULKER_BOX = create("red_shulker_box");

    /**
     * Key for {@code minecraft:red_stained_glass}.
     */
    public static final TypedKey<Item> RED_STAINED_GLASS = create("red_stained_glass");

    /**
     * Key for {@code minecraft:red_stained_glass_pane}.
     */
    public static final TypedKey<Item> RED_STAINED_GLASS_PANE = create("red_stained_glass_pane");

    /**
     * Key for {@code minecraft:red_terracotta}.
     */
    public static final TypedKey<Item> RED_TERRACOTTA = create("red_terracotta");

    /**
     * Key for {@code minecraft:red_tulip}.
     */
    public static final TypedKey<Item> RED_TULIP = create("red_tulip");

    /**
     * Key for {@code minecraft:red_wool}.
     */
    public static final TypedKey<Item> RED_WOOL = create("red_wool");

    /**
     * Key for {@code minecraft:red_wool_slab}.
     */
    public static final TypedKey<Item> RED_WOOL_SLAB = create("red_wool_slab");

    /**
     * Key for {@code minecraft:red_wool_stairs}.
     */
    public static final TypedKey<Item> RED_WOOL_STAIRS = create("red_wool_stairs");

    /**
     * Key for {@code minecraft:redstone}.
     */
    public static final TypedKey<Item> REDSTONE = create("redstone");

    /**
     * Key for {@code minecraft:redstone_block}.
     */
    public static final TypedKey<Item> REDSTONE_BLOCK = create("redstone_block");

    /**
     * Key for {@code minecraft:redstone_lamp}.
     */
    public static final TypedKey<Item> REDSTONE_LAMP = create("redstone_lamp");

    /**
     * Key for {@code minecraft:redstone_ore}.
     */
    public static final TypedKey<Item> REDSTONE_ORE = create("redstone_ore");

    /**
     * Key for {@code minecraft:redstone_torch}.
     */
    public static final TypedKey<Item> REDSTONE_TORCH = create("redstone_torch");

    /**
     * Key for {@code minecraft:reinforced_deepslate}.
     */
    public static final TypedKey<Item> REINFORCED_DEEPSLATE = create("reinforced_deepslate");

    /**
     * Key for {@code minecraft:repeater}.
     */
    public static final TypedKey<Item> REPEATER = create("repeater");

    /**
     * Key for {@code minecraft:repeating_command_block}.
     */
    public static final TypedKey<Item> REPEATING_COMMAND_BLOCK = create("repeating_command_block");

    /**
     * Key for {@code minecraft:resin_block}.
     */
    public static final TypedKey<Item> RESIN_BLOCK = create("resin_block");

    /**
     * Key for {@code minecraft:resin_brick}.
     */
    public static final TypedKey<Item> RESIN_BRICK = create("resin_brick");

    /**
     * Key for {@code minecraft:resin_brick_slab}.
     */
    public static final TypedKey<Item> RESIN_BRICK_SLAB = create("resin_brick_slab");

    /**
     * Key for {@code minecraft:resin_brick_stairs}.
     */
    public static final TypedKey<Item> RESIN_BRICK_STAIRS = create("resin_brick_stairs");

    /**
     * Key for {@code minecraft:resin_brick_wall}.
     */
    public static final TypedKey<Item> RESIN_BRICK_WALL = create("resin_brick_wall");

    /**
     * Key for {@code minecraft:resin_bricks}.
     */
    public static final TypedKey<Item> RESIN_BRICKS = create("resin_bricks");

    /**
     * Key for {@code minecraft:resin_clump}.
     */
    public static final TypedKey<Item> RESIN_CLUMP = create("resin_clump");

    /**
     * Key for {@code minecraft:respawn_anchor}.
     */
    public static final TypedKey<Item> RESPAWN_ANCHOR = create("respawn_anchor");

    /**
     * Key for {@code minecraft:rib_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> RIB_ARMOR_TRIM_SMITHING_TEMPLATE = create("rib_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:rooted_dirt}.
     */
    public static final TypedKey<Item> ROOTED_DIRT = create("rooted_dirt");

    /**
     * Key for {@code minecraft:rose_bush}.
     */
    public static final TypedKey<Item> ROSE_BUSH = create("rose_bush");

    /**
     * Key for {@code minecraft:rotten_flesh}.
     */
    public static final TypedKey<Item> ROTTEN_FLESH = create("rotten_flesh");

    /**
     * Key for {@code minecraft:saddle}.
     */
    public static final TypedKey<Item> SADDLE = create("saddle");

    /**
     * Key for {@code minecraft:salmon}.
     */
    public static final TypedKey<Item> SALMON = create("salmon");

    /**
     * Key for {@code minecraft:salmon_bucket}.
     */
    public static final TypedKey<Item> SALMON_BUCKET = create("salmon_bucket");

    /**
     * Key for {@code minecraft:salmon_spawn_egg}.
     */
    public static final TypedKey<Item> SALMON_SPAWN_EGG = create("salmon_spawn_egg");

    /**
     * Key for {@code minecraft:sand}.
     */
    public static final TypedKey<Item> SAND = create("sand");

    /**
     * Key for {@code minecraft:sandstone}.
     */
    public static final TypedKey<Item> SANDSTONE = create("sandstone");

    /**
     * Key for {@code minecraft:sandstone_slab}.
     */
    public static final TypedKey<Item> SANDSTONE_SLAB = create("sandstone_slab");

    /**
     * Key for {@code minecraft:sandstone_stairs}.
     */
    public static final TypedKey<Item> SANDSTONE_STAIRS = create("sandstone_stairs");

    /**
     * Key for {@code minecraft:sandstone_wall}.
     */
    public static final TypedKey<Item> SANDSTONE_WALL = create("sandstone_wall");

    /**
     * Key for {@code minecraft:savanna_village_map}.
     */
    public static final TypedKey<Item> SAVANNA_VILLAGE_MAP = create("savanna_village_map");

    /**
     * Key for {@code minecraft:scaffolding}.
     */
    public static final TypedKey<Item> SCAFFOLDING = create("scaffolding");

    /**
     * Key for {@code minecraft:scrape_pottery_sherd}.
     */
    public static final TypedKey<Item> SCRAPE_POTTERY_SHERD = create("scrape_pottery_sherd");

    /**
     * Key for {@code minecraft:sculk}.
     */
    public static final TypedKey<Item> SCULK = create("sculk");

    /**
     * Key for {@code minecraft:sculk_catalyst}.
     */
    public static final TypedKey<Item> SCULK_CATALYST = create("sculk_catalyst");

    /**
     * Key for {@code minecraft:sculk_sensor}.
     */
    public static final TypedKey<Item> SCULK_SENSOR = create("sculk_sensor");

    /**
     * Key for {@code minecraft:sculk_shrieker}.
     */
    public static final TypedKey<Item> SCULK_SHRIEKER = create("sculk_shrieker");

    /**
     * Key for {@code minecraft:sculk_vein}.
     */
    public static final TypedKey<Item> SCULK_VEIN = create("sculk_vein");

    /**
     * Key for {@code minecraft:sea_lantern}.
     */
    public static final TypedKey<Item> SEA_LANTERN = create("sea_lantern");

    /**
     * Key for {@code minecraft:sea_pickle}.
     */
    public static final TypedKey<Item> SEA_PICKLE = create("sea_pickle");

    /**
     * Key for {@code minecraft:seagrass}.
     */
    public static final TypedKey<Item> SEAGRASS = create("seagrass");

    /**
     * Key for {@code minecraft:sentry_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE = create("sentry_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:shaper_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE = create("shaper_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:sheaf_pottery_sherd}.
     */
    public static final TypedKey<Item> SHEAF_POTTERY_SHERD = create("sheaf_pottery_sherd");

    /**
     * Key for {@code minecraft:shears}.
     */
    public static final TypedKey<Item> SHEARS = create("shears");

    /**
     * Key for {@code minecraft:sheep_spawn_egg}.
     */
    public static final TypedKey<Item> SHEEP_SPAWN_EGG = create("sheep_spawn_egg");

    /**
     * Key for {@code minecraft:shelf_mushroom}.
     */
    public static final TypedKey<Item> SHELF_MUSHROOM = create("shelf_mushroom");

    /**
     * Key for {@code minecraft:shelter_pottery_sherd}.
     */
    public static final TypedKey<Item> SHELTER_POTTERY_SHERD = create("shelter_pottery_sherd");

    /**
     * Key for {@code minecraft:shield}.
     */
    public static final TypedKey<Item> SHIELD = create("shield");

    /**
     * Key for {@code minecraft:short_dry_grass}.
     */
    public static final TypedKey<Item> SHORT_DRY_GRASS = create("short_dry_grass");

    /**
     * Key for {@code minecraft:short_grass}.
     */
    public static final TypedKey<Item> SHORT_GRASS = create("short_grass");

    /**
     * Key for {@code minecraft:shroomlight}.
     */
    public static final TypedKey<Item> SHROOMLIGHT = create("shroomlight");

    /**
     * Key for {@code minecraft:shulker_box}.
     */
    public static final TypedKey<Item> SHULKER_BOX = create("shulker_box");

    /**
     * Key for {@code minecraft:shulker_shell}.
     */
    public static final TypedKey<Item> SHULKER_SHELL = create("shulker_shell");

    /**
     * Key for {@code minecraft:shulker_spawn_egg}.
     */
    public static final TypedKey<Item> SHULKER_SPAWN_EGG = create("shulker_spawn_egg");

    /**
     * Key for {@code minecraft:silence_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE = create("silence_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:silverfish_spawn_egg}.
     */
    public static final TypedKey<Item> SILVERFISH_SPAWN_EGG = create("silverfish_spawn_egg");

    /**
     * Key for {@code minecraft:skeleton_horse_spawn_egg}.
     */
    public static final TypedKey<Item> SKELETON_HORSE_SPAWN_EGG = create("skeleton_horse_spawn_egg");

    /**
     * Key for {@code minecraft:skeleton_skull}.
     */
    public static final TypedKey<Item> SKELETON_SKULL = create("skeleton_skull");

    /**
     * Key for {@code minecraft:skeleton_spawn_egg}.
     */
    public static final TypedKey<Item> SKELETON_SPAWN_EGG = create("skeleton_spawn_egg");

    /**
     * Key for {@code minecraft:skull_banner_pattern}.
     */
    public static final TypedKey<Item> SKULL_BANNER_PATTERN = create("skull_banner_pattern");

    /**
     * Key for {@code minecraft:skull_pottery_sherd}.
     */
    public static final TypedKey<Item> SKULL_POTTERY_SHERD = create("skull_pottery_sherd");

    /**
     * Key for {@code minecraft:slime_ball}.
     */
    public static final TypedKey<Item> SLIME_BALL = create("slime_ball");

    /**
     * Key for {@code minecraft:slime_block}.
     */
    public static final TypedKey<Item> SLIME_BLOCK = create("slime_block");

    /**
     * Key for {@code minecraft:slime_spawn_egg}.
     */
    public static final TypedKey<Item> SLIME_SPAWN_EGG = create("slime_spawn_egg");

    /**
     * Key for {@code minecraft:small_amethyst_bud}.
     */
    public static final TypedKey<Item> SMALL_AMETHYST_BUD = create("small_amethyst_bud");

    /**
     * Key for {@code minecraft:small_dripleaf}.
     */
    public static final TypedKey<Item> SMALL_DRIPLEAF = create("small_dripleaf");

    /**
     * Key for {@code minecraft:smithing_table}.
     */
    public static final TypedKey<Item> SMITHING_TABLE = create("smithing_table");

    /**
     * Key for {@code minecraft:smoker}.
     */
    public static final TypedKey<Item> SMOKER = create("smoker");

    /**
     * Key for {@code minecraft:smooth_basalt}.
     */
    public static final TypedKey<Item> SMOOTH_BASALT = create("smooth_basalt");

    /**
     * Key for {@code minecraft:smooth_quartz}.
     */
    public static final TypedKey<Item> SMOOTH_QUARTZ = create("smooth_quartz");

    /**
     * Key for {@code minecraft:smooth_quartz_slab}.
     */
    public static final TypedKey<Item> SMOOTH_QUARTZ_SLAB = create("smooth_quartz_slab");

    /**
     * Key for {@code minecraft:smooth_quartz_stairs}.
     */
    public static final TypedKey<Item> SMOOTH_QUARTZ_STAIRS = create("smooth_quartz_stairs");

    /**
     * Key for {@code minecraft:smooth_red_sandstone}.
     */
    public static final TypedKey<Item> SMOOTH_RED_SANDSTONE = create("smooth_red_sandstone");

    /**
     * Key for {@code minecraft:smooth_red_sandstone_slab}.
     */
    public static final TypedKey<Item> SMOOTH_RED_SANDSTONE_SLAB = create("smooth_red_sandstone_slab");

    /**
     * Key for {@code minecraft:smooth_red_sandstone_stairs}.
     */
    public static final TypedKey<Item> SMOOTH_RED_SANDSTONE_STAIRS = create("smooth_red_sandstone_stairs");

    /**
     * Key for {@code minecraft:smooth_sandstone}.
     */
    public static final TypedKey<Item> SMOOTH_SANDSTONE = create("smooth_sandstone");

    /**
     * Key for {@code minecraft:smooth_sandstone_slab}.
     */
    public static final TypedKey<Item> SMOOTH_SANDSTONE_SLAB = create("smooth_sandstone_slab");

    /**
     * Key for {@code minecraft:smooth_sandstone_stairs}.
     */
    public static final TypedKey<Item> SMOOTH_SANDSTONE_STAIRS = create("smooth_sandstone_stairs");

    /**
     * Key for {@code minecraft:smooth_stone}.
     */
    public static final TypedKey<Item> SMOOTH_STONE = create("smooth_stone");

    /**
     * Key for {@code minecraft:smooth_stone_slab}.
     */
    public static final TypedKey<Item> SMOOTH_STONE_SLAB = create("smooth_stone_slab");

    /**
     * Key for {@code minecraft:sniffer_egg}.
     */
    public static final TypedKey<Item> SNIFFER_EGG = create("sniffer_egg");

    /**
     * Key for {@code minecraft:sniffer_spawn_egg}.
     */
    public static final TypedKey<Item> SNIFFER_SPAWN_EGG = create("sniffer_spawn_egg");

    /**
     * Key for {@code minecraft:snort_pottery_sherd}.
     */
    public static final TypedKey<Item> SNORT_POTTERY_SHERD = create("snort_pottery_sherd");

    /**
     * Key for {@code minecraft:snout_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE = create("snout_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:snow}.
     */
    public static final TypedKey<Item> SNOW = create("snow");

    /**
     * Key for {@code minecraft:snow_block}.
     */
    public static final TypedKey<Item> SNOW_BLOCK = create("snow_block");

    /**
     * Key for {@code minecraft:snow_golem_spawn_egg}.
     */
    public static final TypedKey<Item> SNOW_GOLEM_SPAWN_EGG = create("snow_golem_spawn_egg");

    /**
     * Key for {@code minecraft:snowball}.
     */
    public static final TypedKey<Item> SNOWBALL = create("snowball");

    /**
     * Key for {@code minecraft:snowy_village_map}.
     */
    public static final TypedKey<Item> SNOWY_VILLAGE_MAP = create("snowy_village_map");

    /**
     * Key for {@code minecraft:soul_campfire}.
     */
    public static final TypedKey<Item> SOUL_CAMPFIRE = create("soul_campfire");

    /**
     * Key for {@code minecraft:soul_lantern}.
     */
    public static final TypedKey<Item> SOUL_LANTERN = create("soul_lantern");

    /**
     * Key for {@code minecraft:soul_sand}.
     */
    public static final TypedKey<Item> SOUL_SAND = create("soul_sand");

    /**
     * Key for {@code minecraft:soul_soil}.
     */
    public static final TypedKey<Item> SOUL_SOIL = create("soul_soil");

    /**
     * Key for {@code minecraft:soul_torch}.
     */
    public static final TypedKey<Item> SOUL_TORCH = create("soul_torch");

    /**
     * Key for {@code minecraft:spawner}.
     */
    public static final TypedKey<Item> SPAWNER = create("spawner");

    /**
     * Key for {@code minecraft:spectral_arrow}.
     */
    public static final TypedKey<Item> SPECTRAL_ARROW = create("spectral_arrow");

    /**
     * Key for {@code minecraft:spider_eye}.
     */
    public static final TypedKey<Item> SPIDER_EYE = create("spider_eye");

    /**
     * Key for {@code minecraft:spider_spawn_egg}.
     */
    public static final TypedKey<Item> SPIDER_SPAWN_EGG = create("spider_spawn_egg");

    /**
     * Key for {@code minecraft:spire_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE = create("spire_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:splash_potion}.
     */
    public static final TypedKey<Item> SPLASH_POTION = create("splash_potion");

    /**
     * Key for {@code minecraft:sponge}.
     */
    public static final TypedKey<Item> SPONGE = create("sponge");

    /**
     * Key for {@code minecraft:spore_blossom}.
     */
    public static final TypedKey<Item> SPORE_BLOSSOM = create("spore_blossom");

    /**
     * Key for {@code minecraft:spruce_boat}.
     */
    public static final TypedKey<Item> SPRUCE_BOAT = create("spruce_boat");

    /**
     * Key for {@code minecraft:spruce_button}.
     */
    public static final TypedKey<Item> SPRUCE_BUTTON = create("spruce_button");

    /**
     * Key for {@code minecraft:spruce_chest_boat}.
     */
    public static final TypedKey<Item> SPRUCE_CHEST_BOAT = create("spruce_chest_boat");

    /**
     * Key for {@code minecraft:spruce_door}.
     */
    public static final TypedKey<Item> SPRUCE_DOOR = create("spruce_door");

    /**
     * Key for {@code minecraft:spruce_fence}.
     */
    public static final TypedKey<Item> SPRUCE_FENCE = create("spruce_fence");

    /**
     * Key for {@code minecraft:spruce_fence_gate}.
     */
    public static final TypedKey<Item> SPRUCE_FENCE_GATE = create("spruce_fence_gate");

    /**
     * Key for {@code minecraft:spruce_hanging_sign}.
     */
    public static final TypedKey<Item> SPRUCE_HANGING_SIGN = create("spruce_hanging_sign");

    /**
     * Key for {@code minecraft:spruce_leaves}.
     */
    public static final TypedKey<Item> SPRUCE_LEAVES = create("spruce_leaves");

    /**
     * Key for {@code minecraft:spruce_log}.
     */
    public static final TypedKey<Item> SPRUCE_LOG = create("spruce_log");

    /**
     * Key for {@code minecraft:spruce_planks}.
     */
    public static final TypedKey<Item> SPRUCE_PLANKS = create("spruce_planks");

    /**
     * Key for {@code minecraft:spruce_pressure_plate}.
     */
    public static final TypedKey<Item> SPRUCE_PRESSURE_PLATE = create("spruce_pressure_plate");

    /**
     * Key for {@code minecraft:spruce_sapling}.
     */
    public static final TypedKey<Item> SPRUCE_SAPLING = create("spruce_sapling");

    /**
     * Key for {@code minecraft:spruce_shelf}.
     */
    public static final TypedKey<Item> SPRUCE_SHELF = create("spruce_shelf");

    /**
     * Key for {@code minecraft:spruce_sign}.
     */
    public static final TypedKey<Item> SPRUCE_SIGN = create("spruce_sign");

    /**
     * Key for {@code minecraft:spruce_slab}.
     */
    public static final TypedKey<Item> SPRUCE_SLAB = create("spruce_slab");

    /**
     * Key for {@code minecraft:spruce_stairs}.
     */
    public static final TypedKey<Item> SPRUCE_STAIRS = create("spruce_stairs");

    /**
     * Key for {@code minecraft:spruce_trapdoor}.
     */
    public static final TypedKey<Item> SPRUCE_TRAPDOOR = create("spruce_trapdoor");

    /**
     * Key for {@code minecraft:spruce_wood}.
     */
    public static final TypedKey<Item> SPRUCE_WOOD = create("spruce_wood");

    /**
     * Key for {@code minecraft:spyglass}.
     */
    public static final TypedKey<Item> SPYGLASS = create("spyglass");

    /**
     * Key for {@code minecraft:squid_spawn_egg}.
     */
    public static final TypedKey<Item> SQUID_SPAWN_EGG = create("squid_spawn_egg");

    /**
     * Key for {@code minecraft:stick}.
     */
    public static final TypedKey<Item> STICK = create("stick");

    /**
     * Key for {@code minecraft:sticky_piston}.
     */
    public static final TypedKey<Item> STICKY_PISTON = create("sticky_piston");

    /**
     * Key for {@code minecraft:stone}.
     */
    public static final TypedKey<Item> STONE = create("stone");

    /**
     * Key for {@code minecraft:stone_axe}.
     */
    public static final TypedKey<Item> STONE_AXE = create("stone_axe");

    /**
     * Key for {@code minecraft:stone_brick_slab}.
     */
    public static final TypedKey<Item> STONE_BRICK_SLAB = create("stone_brick_slab");

    /**
     * Key for {@code minecraft:stone_brick_stairs}.
     */
    public static final TypedKey<Item> STONE_BRICK_STAIRS = create("stone_brick_stairs");

    /**
     * Key for {@code minecraft:stone_brick_wall}.
     */
    public static final TypedKey<Item> STONE_BRICK_WALL = create("stone_brick_wall");

    /**
     * Key for {@code minecraft:stone_bricks}.
     */
    public static final TypedKey<Item> STONE_BRICKS = create("stone_bricks");

    /**
     * Key for {@code minecraft:stone_button}.
     */
    public static final TypedKey<Item> STONE_BUTTON = create("stone_button");

    /**
     * Key for {@code minecraft:stone_hoe}.
     */
    public static final TypedKey<Item> STONE_HOE = create("stone_hoe");

    /**
     * Key for {@code minecraft:stone_pickaxe}.
     */
    public static final TypedKey<Item> STONE_PICKAXE = create("stone_pickaxe");

    /**
     * Key for {@code minecraft:stone_pressure_plate}.
     */
    public static final TypedKey<Item> STONE_PRESSURE_PLATE = create("stone_pressure_plate");

    /**
     * Key for {@code minecraft:stone_shovel}.
     */
    public static final TypedKey<Item> STONE_SHOVEL = create("stone_shovel");

    /**
     * Key for {@code minecraft:stone_slab}.
     */
    public static final TypedKey<Item> STONE_SLAB = create("stone_slab");

    /**
     * Key for {@code minecraft:stone_spear}.
     */
    public static final TypedKey<Item> STONE_SPEAR = create("stone_spear");

    /**
     * Key for {@code minecraft:stone_stairs}.
     */
    public static final TypedKey<Item> STONE_STAIRS = create("stone_stairs");

    /**
     * Key for {@code minecraft:stone_sword}.
     */
    public static final TypedKey<Item> STONE_SWORD = create("stone_sword");

    /**
     * Key for {@code minecraft:stonecutter}.
     */
    public static final TypedKey<Item> STONECUTTER = create("stonecutter");

    /**
     * Key for {@code minecraft:straw_bed}.
     */
    public static final TypedKey<Item> STRAW_BED = create("straw_bed");

    /**
     * Key for {@code minecraft:stray_spawn_egg}.
     */
    public static final TypedKey<Item> STRAY_SPAWN_EGG = create("stray_spawn_egg");

    /**
     * Key for {@code minecraft:strider_spawn_egg}.
     */
    public static final TypedKey<Item> STRIDER_SPAWN_EGG = create("strider_spawn_egg");

    /**
     * Key for {@code minecraft:string}.
     */
    public static final TypedKey<Item> STRING = create("string");

    /**
     * Key for {@code minecraft:stripped_acacia_log}.
     */
    public static final TypedKey<Item> STRIPPED_ACACIA_LOG = create("stripped_acacia_log");

    /**
     * Key for {@code minecraft:stripped_acacia_wood}.
     */
    public static final TypedKey<Item> STRIPPED_ACACIA_WOOD = create("stripped_acacia_wood");

    /**
     * Key for {@code minecraft:stripped_bamboo_block}.
     */
    public static final TypedKey<Item> STRIPPED_BAMBOO_BLOCK = create("stripped_bamboo_block");

    /**
     * Key for {@code minecraft:stripped_birch_log}.
     */
    public static final TypedKey<Item> STRIPPED_BIRCH_LOG = create("stripped_birch_log");

    /**
     * Key for {@code minecraft:stripped_birch_wood}.
     */
    public static final TypedKey<Item> STRIPPED_BIRCH_WOOD = create("stripped_birch_wood");

    /**
     * Key for {@code minecraft:stripped_cherry_log}.
     */
    public static final TypedKey<Item> STRIPPED_CHERRY_LOG = create("stripped_cherry_log");

    /**
     * Key for {@code minecraft:stripped_cherry_wood}.
     */
    public static final TypedKey<Item> STRIPPED_CHERRY_WOOD = create("stripped_cherry_wood");

    /**
     * Key for {@code minecraft:stripped_crimson_hyphae}.
     */
    public static final TypedKey<Item> STRIPPED_CRIMSON_HYPHAE = create("stripped_crimson_hyphae");

    /**
     * Key for {@code minecraft:stripped_crimson_stem}.
     */
    public static final TypedKey<Item> STRIPPED_CRIMSON_STEM = create("stripped_crimson_stem");

    /**
     * Key for {@code minecraft:stripped_dark_oak_log}.
     */
    public static final TypedKey<Item> STRIPPED_DARK_OAK_LOG = create("stripped_dark_oak_log");

    /**
     * Key for {@code minecraft:stripped_dark_oak_wood}.
     */
    public static final TypedKey<Item> STRIPPED_DARK_OAK_WOOD = create("stripped_dark_oak_wood");

    /**
     * Key for {@code minecraft:stripped_jungle_log}.
     */
    public static final TypedKey<Item> STRIPPED_JUNGLE_LOG = create("stripped_jungle_log");

    /**
     * Key for {@code minecraft:stripped_jungle_wood}.
     */
    public static final TypedKey<Item> STRIPPED_JUNGLE_WOOD = create("stripped_jungle_wood");

    /**
     * Key for {@code minecraft:stripped_mangrove_log}.
     */
    public static final TypedKey<Item> STRIPPED_MANGROVE_LOG = create("stripped_mangrove_log");

    /**
     * Key for {@code minecraft:stripped_mangrove_wood}.
     */
    public static final TypedKey<Item> STRIPPED_MANGROVE_WOOD = create("stripped_mangrove_wood");

    /**
     * Key for {@code minecraft:stripped_oak_log}.
     */
    public static final TypedKey<Item> STRIPPED_OAK_LOG = create("stripped_oak_log");

    /**
     * Key for {@code minecraft:stripped_oak_wood}.
     */
    public static final TypedKey<Item> STRIPPED_OAK_WOOD = create("stripped_oak_wood");

    /**
     * Key for {@code minecraft:stripped_pale_oak_log}.
     */
    public static final TypedKey<Item> STRIPPED_PALE_OAK_LOG = create("stripped_pale_oak_log");

    /**
     * Key for {@code minecraft:stripped_pale_oak_wood}.
     */
    public static final TypedKey<Item> STRIPPED_PALE_OAK_WOOD = create("stripped_pale_oak_wood");

    /**
     * Key for {@code minecraft:stripped_poplar_log}.
     */
    public static final TypedKey<Item> STRIPPED_POPLAR_LOG = create("stripped_poplar_log");

    /**
     * Key for {@code minecraft:stripped_poplar_wood}.
     */
    public static final TypedKey<Item> STRIPPED_POPLAR_WOOD = create("stripped_poplar_wood");

    /**
     * Key for {@code minecraft:stripped_spruce_log}.
     */
    public static final TypedKey<Item> STRIPPED_SPRUCE_LOG = create("stripped_spruce_log");

    /**
     * Key for {@code minecraft:stripped_spruce_wood}.
     */
    public static final TypedKey<Item> STRIPPED_SPRUCE_WOOD = create("stripped_spruce_wood");

    /**
     * Key for {@code minecraft:stripped_warped_hyphae}.
     */
    public static final TypedKey<Item> STRIPPED_WARPED_HYPHAE = create("stripped_warped_hyphae");

    /**
     * Key for {@code minecraft:stripped_warped_stem}.
     */
    public static final TypedKey<Item> STRIPPED_WARPED_STEM = create("stripped_warped_stem");

    /**
     * Key for {@code minecraft:structure_block}.
     */
    public static final TypedKey<Item> STRUCTURE_BLOCK = create("structure_block");

    /**
     * Key for {@code minecraft:structure_void}.
     */
    public static final TypedKey<Item> STRUCTURE_VOID = create("structure_void");

    /**
     * Key for {@code minecraft:sugar}.
     */
    public static final TypedKey<Item> SUGAR = create("sugar");

    /**
     * Key for {@code minecraft:sugar_cane}.
     */
    public static final TypedKey<Item> SUGAR_CANE = create("sugar_cane");

    /**
     * Key for {@code minecraft:sulfur}.
     */
    public static final TypedKey<Item> SULFUR = create("sulfur");

    /**
     * Key for {@code minecraft:sulfur_brick_slab}.
     */
    public static final TypedKey<Item> SULFUR_BRICK_SLAB = create("sulfur_brick_slab");

    /**
     * Key for {@code minecraft:sulfur_brick_stairs}.
     */
    public static final TypedKey<Item> SULFUR_BRICK_STAIRS = create("sulfur_brick_stairs");

    /**
     * Key for {@code minecraft:sulfur_brick_wall}.
     */
    public static final TypedKey<Item> SULFUR_BRICK_WALL = create("sulfur_brick_wall");

    /**
     * Key for {@code minecraft:sulfur_bricks}.
     */
    public static final TypedKey<Item> SULFUR_BRICKS = create("sulfur_bricks");

    /**
     * Key for {@code minecraft:sulfur_cube_bucket}.
     */
    public static final TypedKey<Item> SULFUR_CUBE_BUCKET = create("sulfur_cube_bucket");

    /**
     * Key for {@code minecraft:sulfur_cube_spawn_egg}.
     */
    public static final TypedKey<Item> SULFUR_CUBE_SPAWN_EGG = create("sulfur_cube_spawn_egg");

    /**
     * Key for {@code minecraft:sulfur_slab}.
     */
    public static final TypedKey<Item> SULFUR_SLAB = create("sulfur_slab");

    /**
     * Key for {@code minecraft:sulfur_spike}.
     */
    public static final TypedKey<Item> SULFUR_SPIKE = create("sulfur_spike");

    /**
     * Key for {@code minecraft:sulfur_stairs}.
     */
    public static final TypedKey<Item> SULFUR_STAIRS = create("sulfur_stairs");

    /**
     * Key for {@code minecraft:sulfur_wall}.
     */
    public static final TypedKey<Item> SULFUR_WALL = create("sulfur_wall");

    /**
     * Key for {@code minecraft:sunflower}.
     */
    public static final TypedKey<Item> SUNFLOWER = create("sunflower");

    /**
     * Key for {@code minecraft:suspicious_gravel}.
     */
    public static final TypedKey<Item> SUSPICIOUS_GRAVEL = create("suspicious_gravel");

    /**
     * Key for {@code minecraft:suspicious_sand}.
     */
    public static final TypedKey<Item> SUSPICIOUS_SAND = create("suspicious_sand");

    /**
     * Key for {@code minecraft:suspicious_stew}.
     */
    public static final TypedKey<Item> SUSPICIOUS_STEW = create("suspicious_stew");

    /**
     * Key for {@code minecraft:swamp_hut_map}.
     */
    public static final TypedKey<Item> SWAMP_HUT_MAP = create("swamp_hut_map");

    /**
     * Key for {@code minecraft:sweet_berries}.
     */
    public static final TypedKey<Item> SWEET_BERRIES = create("sweet_berries");

    /**
     * Key for {@code minecraft:tadpole_bucket}.
     */
    public static final TypedKey<Item> TADPOLE_BUCKET = create("tadpole_bucket");

    /**
     * Key for {@code minecraft:tadpole_spawn_egg}.
     */
    public static final TypedKey<Item> TADPOLE_SPAWN_EGG = create("tadpole_spawn_egg");

    /**
     * Key for {@code minecraft:taiga_village_map}.
     */
    public static final TypedKey<Item> TAIGA_VILLAGE_MAP = create("taiga_village_map");

    /**
     * Key for {@code minecraft:tall_dry_grass}.
     */
    public static final TypedKey<Item> TALL_DRY_GRASS = create("tall_dry_grass");

    /**
     * Key for {@code minecraft:tall_grass}.
     */
    public static final TypedKey<Item> TALL_GRASS = create("tall_grass");

    /**
     * Key for {@code minecraft:target}.
     */
    public static final TypedKey<Item> TARGET = create("target");

    /**
     * Key for {@code minecraft:terracotta}.
     */
    public static final TypedKey<Item> TERRACOTTA = create("terracotta");

    /**
     * Key for {@code minecraft:test_block}.
     */
    public static final TypedKey<Item> TEST_BLOCK = create("test_block");

    /**
     * Key for {@code minecraft:test_instance_block}.
     */
    public static final TypedKey<Item> TEST_INSTANCE_BLOCK = create("test_instance_block");

    /**
     * Key for {@code minecraft:tide_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> TIDE_ARMOR_TRIM_SMITHING_TEMPLATE = create("tide_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:tinted_glass}.
     */
    public static final TypedKey<Item> TINTED_GLASS = create("tinted_glass");

    /**
     * Key for {@code minecraft:tipped_arrow}.
     */
    public static final TypedKey<Item> TIPPED_ARROW = create("tipped_arrow");

    /**
     * Key for {@code minecraft:tnt}.
     */
    public static final TypedKey<Item> TNT = create("tnt");

    /**
     * Key for {@code minecraft:tnt_minecart}.
     */
    public static final TypedKey<Item> TNT_MINECART = create("tnt_minecart");

    /**
     * Key for {@code minecraft:torch}.
     */
    public static final TypedKey<Item> TORCH = create("torch");

    /**
     * Key for {@code minecraft:torchflower}.
     */
    public static final TypedKey<Item> TORCHFLOWER = create("torchflower");

    /**
     * Key for {@code minecraft:torchflower_seeds}.
     */
    public static final TypedKey<Item> TORCHFLOWER_SEEDS = create("torchflower_seeds");

    /**
     * Key for {@code minecraft:totem_of_undying}.
     */
    public static final TypedKey<Item> TOTEM_OF_UNDYING = create("totem_of_undying");

    /**
     * Key for {@code minecraft:trader_llama_spawn_egg}.
     */
    public static final TypedKey<Item> TRADER_LLAMA_SPAWN_EGG = create("trader_llama_spawn_egg");

    /**
     * Key for {@code minecraft:trapped_chest}.
     */
    public static final TypedKey<Item> TRAPPED_CHEST = create("trapped_chest");

    /**
     * Key for {@code minecraft:trial_key}.
     */
    public static final TypedKey<Item> TRIAL_KEY = create("trial_key");

    /**
     * Key for {@code minecraft:trial_spawner}.
     */
    public static final TypedKey<Item> TRIAL_SPAWNER = create("trial_spawner");

    /**
     * Key for {@code minecraft:trident}.
     */
    public static final TypedKey<Item> TRIDENT = create("trident");

    /**
     * Key for {@code minecraft:tripwire_hook}.
     */
    public static final TypedKey<Item> TRIPWIRE_HOOK = create("tripwire_hook");

    /**
     * Key for {@code minecraft:tropical_fish}.
     */
    public static final TypedKey<Item> TROPICAL_FISH = create("tropical_fish");

    /**
     * Key for {@code minecraft:tropical_fish_bucket}.
     */
    public static final TypedKey<Item> TROPICAL_FISH_BUCKET = create("tropical_fish_bucket");

    /**
     * Key for {@code minecraft:tropical_fish_spawn_egg}.
     */
    public static final TypedKey<Item> TROPICAL_FISH_SPAWN_EGG = create("tropical_fish_spawn_egg");

    /**
     * Key for {@code minecraft:tube_coral}.
     */
    public static final TypedKey<Item> TUBE_CORAL = create("tube_coral");

    /**
     * Key for {@code minecraft:tube_coral_block}.
     */
    public static final TypedKey<Item> TUBE_CORAL_BLOCK = create("tube_coral_block");

    /**
     * Key for {@code minecraft:tube_coral_fan}.
     */
    public static final TypedKey<Item> TUBE_CORAL_FAN = create("tube_coral_fan");

    /**
     * Key for {@code minecraft:tuff}.
     */
    public static final TypedKey<Item> TUFF = create("tuff");

    /**
     * Key for {@code minecraft:tuff_brick_slab}.
     */
    public static final TypedKey<Item> TUFF_BRICK_SLAB = create("tuff_brick_slab");

    /**
     * Key for {@code minecraft:tuff_brick_stairs}.
     */
    public static final TypedKey<Item> TUFF_BRICK_STAIRS = create("tuff_brick_stairs");

    /**
     * Key for {@code minecraft:tuff_brick_wall}.
     */
    public static final TypedKey<Item> TUFF_BRICK_WALL = create("tuff_brick_wall");

    /**
     * Key for {@code minecraft:tuff_bricks}.
     */
    public static final TypedKey<Item> TUFF_BRICKS = create("tuff_bricks");

    /**
     * Key for {@code minecraft:tuff_slab}.
     */
    public static final TypedKey<Item> TUFF_SLAB = create("tuff_slab");

    /**
     * Key for {@code minecraft:tuff_stairs}.
     */
    public static final TypedKey<Item> TUFF_STAIRS = create("tuff_stairs");

    /**
     * Key for {@code minecraft:tuff_wall}.
     */
    public static final TypedKey<Item> TUFF_WALL = create("tuff_wall");

    /**
     * Key for {@code minecraft:turtle_egg}.
     */
    public static final TypedKey<Item> TURTLE_EGG = create("turtle_egg");

    /**
     * Key for {@code minecraft:turtle_helmet}.
     */
    public static final TypedKey<Item> TURTLE_HELMET = create("turtle_helmet");

    /**
     * Key for {@code minecraft:turtle_scute}.
     */
    public static final TypedKey<Item> TURTLE_SCUTE = create("turtle_scute");

    /**
     * Key for {@code minecraft:turtle_spawn_egg}.
     */
    public static final TypedKey<Item> TURTLE_SPAWN_EGG = create("turtle_spawn_egg");

    /**
     * Key for {@code minecraft:twisting_vines}.
     */
    public static final TypedKey<Item> TWISTING_VINES = create("twisting_vines");

    /**
     * Key for {@code minecraft:vault}.
     */
    public static final TypedKey<Item> VAULT = create("vault");

    /**
     * Key for {@code minecraft:verdant_froglight}.
     */
    public static final TypedKey<Item> VERDANT_FROGLIGHT = create("verdant_froglight");

    /**
     * Key for {@code minecraft:vex_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> VEX_ARMOR_TRIM_SMITHING_TEMPLATE = create("vex_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:vex_spawn_egg}.
     */
    public static final TypedKey<Item> VEX_SPAWN_EGG = create("vex_spawn_egg");

    /**
     * Key for {@code minecraft:villager_spawn_egg}.
     */
    public static final TypedKey<Item> VILLAGER_SPAWN_EGG = create("villager_spawn_egg");

    /**
     * Key for {@code minecraft:vindicator_spawn_egg}.
     */
    public static final TypedKey<Item> VINDICATOR_SPAWN_EGG = create("vindicator_spawn_egg");

    /**
     * Key for {@code minecraft:vine}.
     */
    public static final TypedKey<Item> VINE = create("vine");

    /**
     * Key for {@code minecraft:wandering_trader_spawn_egg}.
     */
    public static final TypedKey<Item> WANDERING_TRADER_SPAWN_EGG = create("wandering_trader_spawn_egg");

    /**
     * Key for {@code minecraft:ward_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> WARD_ARMOR_TRIM_SMITHING_TEMPLATE = create("ward_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:warden_spawn_egg}.
     */
    public static final TypedKey<Item> WARDEN_SPAWN_EGG = create("warden_spawn_egg");

    /**
     * Key for {@code minecraft:warm_ocean_ruins_map}.
     */
    public static final TypedKey<Item> WARM_OCEAN_RUINS_MAP = create("warm_ocean_ruins_map");

    /**
     * Key for {@code minecraft:warped_button}.
     */
    public static final TypedKey<Item> WARPED_BUTTON = create("warped_button");

    /**
     * Key for {@code minecraft:warped_door}.
     */
    public static final TypedKey<Item> WARPED_DOOR = create("warped_door");

    /**
     * Key for {@code minecraft:warped_fence}.
     */
    public static final TypedKey<Item> WARPED_FENCE = create("warped_fence");

    /**
     * Key for {@code minecraft:warped_fence_gate}.
     */
    public static final TypedKey<Item> WARPED_FENCE_GATE = create("warped_fence_gate");

    /**
     * Key for {@code minecraft:warped_fungus}.
     */
    public static final TypedKey<Item> WARPED_FUNGUS = create("warped_fungus");

    /**
     * Key for {@code minecraft:warped_fungus_on_a_stick}.
     */
    public static final TypedKey<Item> WARPED_FUNGUS_ON_A_STICK = create("warped_fungus_on_a_stick");

    /**
     * Key for {@code minecraft:warped_hanging_sign}.
     */
    public static final TypedKey<Item> WARPED_HANGING_SIGN = create("warped_hanging_sign");

    /**
     * Key for {@code minecraft:warped_hyphae}.
     */
    public static final TypedKey<Item> WARPED_HYPHAE = create("warped_hyphae");

    /**
     * Key for {@code minecraft:warped_nylium}.
     */
    public static final TypedKey<Item> WARPED_NYLIUM = create("warped_nylium");

    /**
     * Key for {@code minecraft:warped_planks}.
     */
    public static final TypedKey<Item> WARPED_PLANKS = create("warped_planks");

    /**
     * Key for {@code minecraft:warped_pressure_plate}.
     */
    public static final TypedKey<Item> WARPED_PRESSURE_PLATE = create("warped_pressure_plate");

    /**
     * Key for {@code minecraft:warped_roots}.
     */
    public static final TypedKey<Item> WARPED_ROOTS = create("warped_roots");

    /**
     * Key for {@code minecraft:warped_shelf}.
     */
    public static final TypedKey<Item> WARPED_SHELF = create("warped_shelf");

    /**
     * Key for {@code minecraft:warped_sign}.
     */
    public static final TypedKey<Item> WARPED_SIGN = create("warped_sign");

    /**
     * Key for {@code minecraft:warped_slab}.
     */
    public static final TypedKey<Item> WARPED_SLAB = create("warped_slab");

    /**
     * Key for {@code minecraft:warped_stairs}.
     */
    public static final TypedKey<Item> WARPED_STAIRS = create("warped_stairs");

    /**
     * Key for {@code minecraft:warped_stem}.
     */
    public static final TypedKey<Item> WARPED_STEM = create("warped_stem");

    /**
     * Key for {@code minecraft:warped_trapdoor}.
     */
    public static final TypedKey<Item> WARPED_TRAPDOOR = create("warped_trapdoor");

    /**
     * Key for {@code minecraft:warped_wart_block}.
     */
    public static final TypedKey<Item> WARPED_WART_BLOCK = create("warped_wart_block");

    /**
     * Key for {@code minecraft:water_bucket}.
     */
    public static final TypedKey<Item> WATER_BUCKET = create("water_bucket");

    /**
     * Key for {@code minecraft:waxed_chiseled_copper}.
     */
    public static final TypedKey<Item> WAXED_CHISELED_COPPER = create("waxed_chiseled_copper");

    /**
     * Key for {@code minecraft:waxed_copper_bars}.
     */
    public static final TypedKey<Item> WAXED_COPPER_BARS = create("waxed_copper_bars");

    /**
     * Key for {@code minecraft:waxed_copper_block}.
     */
    public static final TypedKey<Item> WAXED_COPPER_BLOCK = create("waxed_copper_block");

    /**
     * Key for {@code minecraft:waxed_copper_bulb}.
     */
    public static final TypedKey<Item> WAXED_COPPER_BULB = create("waxed_copper_bulb");

    /**
     * Key for {@code minecraft:waxed_copper_chain}.
     */
    public static final TypedKey<Item> WAXED_COPPER_CHAIN = create("waxed_copper_chain");

    /**
     * Key for {@code minecraft:waxed_copper_chest}.
     */
    public static final TypedKey<Item> WAXED_COPPER_CHEST = create("waxed_copper_chest");

    /**
     * Key for {@code minecraft:waxed_copper_door}.
     */
    public static final TypedKey<Item> WAXED_COPPER_DOOR = create("waxed_copper_door");

    /**
     * Key for {@code minecraft:waxed_copper_golem_statue}.
     */
    public static final TypedKey<Item> WAXED_COPPER_GOLEM_STATUE = create("waxed_copper_golem_statue");

    /**
     * Key for {@code minecraft:waxed_copper_grate}.
     */
    public static final TypedKey<Item> WAXED_COPPER_GRATE = create("waxed_copper_grate");

    /**
     * Key for {@code minecraft:waxed_copper_lantern}.
     */
    public static final TypedKey<Item> WAXED_COPPER_LANTERN = create("waxed_copper_lantern");

    /**
     * Key for {@code minecraft:waxed_copper_trapdoor}.
     */
    public static final TypedKey<Item> WAXED_COPPER_TRAPDOOR = create("waxed_copper_trapdoor");

    /**
     * Key for {@code minecraft:waxed_cut_copper}.
     */
    public static final TypedKey<Item> WAXED_CUT_COPPER = create("waxed_cut_copper");

    /**
     * Key for {@code minecraft:waxed_cut_copper_slab}.
     */
    public static final TypedKey<Item> WAXED_CUT_COPPER_SLAB = create("waxed_cut_copper_slab");

    /**
     * Key for {@code minecraft:waxed_cut_copper_stairs}.
     */
    public static final TypedKey<Item> WAXED_CUT_COPPER_STAIRS = create("waxed_cut_copper_stairs");

    /**
     * Key for {@code minecraft:waxed_exposed_chiseled_copper}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_CHISELED_COPPER = create("waxed_exposed_chiseled_copper");

    /**
     * Key for {@code minecraft:waxed_exposed_copper}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER = create("waxed_exposed_copper");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_bars}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_BARS = create("waxed_exposed_copper_bars");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_bulb}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_BULB = create("waxed_exposed_copper_bulb");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_chain}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_CHAIN = create("waxed_exposed_copper_chain");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_chest}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_CHEST = create("waxed_exposed_copper_chest");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_door}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_DOOR = create("waxed_exposed_copper_door");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_golem_statue}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_GOLEM_STATUE = create("waxed_exposed_copper_golem_statue");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_grate}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_GRATE = create("waxed_exposed_copper_grate");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_lantern}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_LANTERN = create("waxed_exposed_copper_lantern");

    /**
     * Key for {@code minecraft:waxed_exposed_copper_trapdoor}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_COPPER_TRAPDOOR = create("waxed_exposed_copper_trapdoor");

    /**
     * Key for {@code minecraft:waxed_exposed_cut_copper}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_CUT_COPPER = create("waxed_exposed_cut_copper");

    /**
     * Key for {@code minecraft:waxed_exposed_cut_copper_slab}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_CUT_COPPER_SLAB = create("waxed_exposed_cut_copper_slab");

    /**
     * Key for {@code minecraft:waxed_exposed_cut_copper_stairs}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_CUT_COPPER_STAIRS = create("waxed_exposed_cut_copper_stairs");

    /**
     * Key for {@code minecraft:waxed_exposed_lightning_rod}.
     */
    public static final TypedKey<Item> WAXED_EXPOSED_LIGHTNING_ROD = create("waxed_exposed_lightning_rod");

    /**
     * Key for {@code minecraft:waxed_lightning_rod}.
     */
    public static final TypedKey<Item> WAXED_LIGHTNING_ROD = create("waxed_lightning_rod");

    /**
     * Key for {@code minecraft:waxed_oxidized_chiseled_copper}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_CHISELED_COPPER = create("waxed_oxidized_chiseled_copper");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER = create("waxed_oxidized_copper");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_bars}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_BARS = create("waxed_oxidized_copper_bars");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_bulb}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_BULB = create("waxed_oxidized_copper_bulb");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_chain}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_CHAIN = create("waxed_oxidized_copper_chain");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_chest}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_CHEST = create("waxed_oxidized_copper_chest");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_door}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_DOOR = create("waxed_oxidized_copper_door");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_golem_statue}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_GOLEM_STATUE = create("waxed_oxidized_copper_golem_statue");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_grate}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_GRATE = create("waxed_oxidized_copper_grate");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_lantern}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_LANTERN = create("waxed_oxidized_copper_lantern");

    /**
     * Key for {@code minecraft:waxed_oxidized_copper_trapdoor}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_COPPER_TRAPDOOR = create("waxed_oxidized_copper_trapdoor");

    /**
     * Key for {@code minecraft:waxed_oxidized_cut_copper}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_CUT_COPPER = create("waxed_oxidized_cut_copper");

    /**
     * Key for {@code minecraft:waxed_oxidized_cut_copper_slab}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_CUT_COPPER_SLAB = create("waxed_oxidized_cut_copper_slab");

    /**
     * Key for {@code minecraft:waxed_oxidized_cut_copper_stairs}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_CUT_COPPER_STAIRS = create("waxed_oxidized_cut_copper_stairs");

    /**
     * Key for {@code minecraft:waxed_oxidized_lightning_rod}.
     */
    public static final TypedKey<Item> WAXED_OXIDIZED_LIGHTNING_ROD = create("waxed_oxidized_lightning_rod");

    /**
     * Key for {@code minecraft:waxed_weathered_chiseled_copper}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_CHISELED_COPPER = create("waxed_weathered_chiseled_copper");

    /**
     * Key for {@code minecraft:waxed_weathered_copper}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER = create("waxed_weathered_copper");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_bars}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_BARS = create("waxed_weathered_copper_bars");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_bulb}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_BULB = create("waxed_weathered_copper_bulb");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_chain}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_CHAIN = create("waxed_weathered_copper_chain");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_chest}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_CHEST = create("waxed_weathered_copper_chest");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_door}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_DOOR = create("waxed_weathered_copper_door");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_golem_statue}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_GOLEM_STATUE = create("waxed_weathered_copper_golem_statue");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_grate}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_GRATE = create("waxed_weathered_copper_grate");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_lantern}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_LANTERN = create("waxed_weathered_copper_lantern");

    /**
     * Key for {@code minecraft:waxed_weathered_copper_trapdoor}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_COPPER_TRAPDOOR = create("waxed_weathered_copper_trapdoor");

    /**
     * Key for {@code minecraft:waxed_weathered_cut_copper}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_CUT_COPPER = create("waxed_weathered_cut_copper");

    /**
     * Key for {@code minecraft:waxed_weathered_cut_copper_slab}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_CUT_COPPER_SLAB = create("waxed_weathered_cut_copper_slab");

    /**
     * Key for {@code minecraft:waxed_weathered_cut_copper_stairs}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_CUT_COPPER_STAIRS = create("waxed_weathered_cut_copper_stairs");

    /**
     * Key for {@code minecraft:waxed_weathered_lightning_rod}.
     */
    public static final TypedKey<Item> WAXED_WEATHERED_LIGHTNING_ROD = create("waxed_weathered_lightning_rod");

    /**
     * Key for {@code minecraft:wayfinder_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE = create("wayfinder_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:weathered_chiseled_copper}.
     */
    public static final TypedKey<Item> WEATHERED_CHISELED_COPPER = create("weathered_chiseled_copper");

    /**
     * Key for {@code minecraft:weathered_copper}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER = create("weathered_copper");

    /**
     * Key for {@code minecraft:weathered_copper_bars}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_BARS = create("weathered_copper_bars");

    /**
     * Key for {@code minecraft:weathered_copper_bulb}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_BULB = create("weathered_copper_bulb");

    /**
     * Key for {@code minecraft:weathered_copper_chain}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_CHAIN = create("weathered_copper_chain");

    /**
     * Key for {@code minecraft:weathered_copper_chest}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_CHEST = create("weathered_copper_chest");

    /**
     * Key for {@code minecraft:weathered_copper_door}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_DOOR = create("weathered_copper_door");

    /**
     * Key for {@code minecraft:weathered_copper_golem_statue}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_GOLEM_STATUE = create("weathered_copper_golem_statue");

    /**
     * Key for {@code minecraft:weathered_copper_grate}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_GRATE = create("weathered_copper_grate");

    /**
     * Key for {@code minecraft:weathered_copper_lantern}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_LANTERN = create("weathered_copper_lantern");

    /**
     * Key for {@code minecraft:weathered_copper_trapdoor}.
     */
    public static final TypedKey<Item> WEATHERED_COPPER_TRAPDOOR = create("weathered_copper_trapdoor");

    /**
     * Key for {@code minecraft:weathered_cut_copper}.
     */
    public static final TypedKey<Item> WEATHERED_CUT_COPPER = create("weathered_cut_copper");

    /**
     * Key for {@code minecraft:weathered_cut_copper_slab}.
     */
    public static final TypedKey<Item> WEATHERED_CUT_COPPER_SLAB = create("weathered_cut_copper_slab");

    /**
     * Key for {@code minecraft:weathered_cut_copper_stairs}.
     */
    public static final TypedKey<Item> WEATHERED_CUT_COPPER_STAIRS = create("weathered_cut_copper_stairs");

    /**
     * Key for {@code minecraft:weathered_lightning_rod}.
     */
    public static final TypedKey<Item> WEATHERED_LIGHTNING_ROD = create("weathered_lightning_rod");

    /**
     * Key for {@code minecraft:weeping_vines}.
     */
    public static final TypedKey<Item> WEEPING_VINES = create("weeping_vines");

    /**
     * Key for {@code minecraft:wet_sponge}.
     */
    public static final TypedKey<Item> WET_SPONGE = create("wet_sponge");

    /**
     * Key for {@code minecraft:wheat}.
     */
    public static final TypedKey<Item> WHEAT = create("wheat");

    /**
     * Key for {@code minecraft:wheat_seeds}.
     */
    public static final TypedKey<Item> WHEAT_SEEDS = create("wheat_seeds");

    /**
     * Key for {@code minecraft:white_banner}.
     */
    public static final TypedKey<Item> WHITE_BANNER = create("white_banner");

    /**
     * Key for {@code minecraft:white_bed}.
     */
    public static final TypedKey<Item> WHITE_BED = create("white_bed");

    /**
     * Key for {@code minecraft:white_bundle}.
     */
    public static final TypedKey<Item> WHITE_BUNDLE = create("white_bundle");

    /**
     * Key for {@code minecraft:white_candle}.
     */
    public static final TypedKey<Item> WHITE_CANDLE = create("white_candle");

    /**
     * Key for {@code minecraft:white_carpet}.
     */
    public static final TypedKey<Item> WHITE_CARPET = create("white_carpet");

    /**
     * Key for {@code minecraft:white_concrete}.
     */
    public static final TypedKey<Item> WHITE_CONCRETE = create("white_concrete");

    /**
     * Key for {@code minecraft:white_concrete_powder}.
     */
    public static final TypedKey<Item> WHITE_CONCRETE_POWDER = create("white_concrete_powder");

    /**
     * Key for {@code minecraft:white_concrete_slab}.
     */
    public static final TypedKey<Item> WHITE_CONCRETE_SLAB = create("white_concrete_slab");

    /**
     * Key for {@code minecraft:white_concrete_stairs}.
     */
    public static final TypedKey<Item> WHITE_CONCRETE_STAIRS = create("white_concrete_stairs");

    /**
     * Key for {@code minecraft:white_cushion}.
     */
    public static final TypedKey<Item> WHITE_CUSHION = create("white_cushion");

    /**
     * Key for {@code minecraft:white_dye}.
     */
    public static final TypedKey<Item> WHITE_DYE = create("white_dye");

    /**
     * Key for {@code minecraft:white_glazed_terracotta}.
     */
    public static final TypedKey<Item> WHITE_GLAZED_TERRACOTTA = create("white_glazed_terracotta");

    /**
     * Key for {@code minecraft:white_harness}.
     */
    public static final TypedKey<Item> WHITE_HARNESS = create("white_harness");

    /**
     * Key for {@code minecraft:white_shulker_box}.
     */
    public static final TypedKey<Item> WHITE_SHULKER_BOX = create("white_shulker_box");

    /**
     * Key for {@code minecraft:white_stained_glass}.
     */
    public static final TypedKey<Item> WHITE_STAINED_GLASS = create("white_stained_glass");

    /**
     * Key for {@code minecraft:white_stained_glass_pane}.
     */
    public static final TypedKey<Item> WHITE_STAINED_GLASS_PANE = create("white_stained_glass_pane");

    /**
     * Key for {@code minecraft:white_terracotta}.
     */
    public static final TypedKey<Item> WHITE_TERRACOTTA = create("white_terracotta");

    /**
     * Key for {@code minecraft:white_tulip}.
     */
    public static final TypedKey<Item> WHITE_TULIP = create("white_tulip");

    /**
     * Key for {@code minecraft:white_wool}.
     */
    public static final TypedKey<Item> WHITE_WOOL = create("white_wool");

    /**
     * Key for {@code minecraft:white_wool_slab}.
     */
    public static final TypedKey<Item> WHITE_WOOL_SLAB = create("white_wool_slab");

    /**
     * Key for {@code minecraft:white_wool_stairs}.
     */
    public static final TypedKey<Item> WHITE_WOOL_STAIRS = create("white_wool_stairs");

    /**
     * Key for {@code minecraft:wild_armor_trim_smithing_template}.
     */
    public static final TypedKey<Item> WILD_ARMOR_TRIM_SMITHING_TEMPLATE = create("wild_armor_trim_smithing_template");

    /**
     * Key for {@code minecraft:wildflowers}.
     */
    public static final TypedKey<Item> WILDFLOWERS = create("wildflowers");

    /**
     * Key for {@code minecraft:wind_charge}.
     */
    public static final TypedKey<Item> WIND_CHARGE = create("wind_charge");

    /**
     * Key for {@code minecraft:witch_spawn_egg}.
     */
    public static final TypedKey<Item> WITCH_SPAWN_EGG = create("witch_spawn_egg");

    /**
     * Key for {@code minecraft:wither_rose}.
     */
    public static final TypedKey<Item> WITHER_ROSE = create("wither_rose");

    /**
     * Key for {@code minecraft:wither_skeleton_skull}.
     */
    public static final TypedKey<Item> WITHER_SKELETON_SKULL = create("wither_skeleton_skull");

    /**
     * Key for {@code minecraft:wither_skeleton_spawn_egg}.
     */
    public static final TypedKey<Item> WITHER_SKELETON_SPAWN_EGG = create("wither_skeleton_spawn_egg");

    /**
     * Key for {@code minecraft:wither_spawn_egg}.
     */
    public static final TypedKey<Item> WITHER_SPAWN_EGG = create("wither_spawn_egg");

    /**
     * Key for {@code minecraft:wolf_armor}.
     */
    public static final TypedKey<Item> WOLF_ARMOR = create("wolf_armor");

    /**
     * Key for {@code minecraft:wolf_spawn_egg}.
     */
    public static final TypedKey<Item> WOLF_SPAWN_EGG = create("wolf_spawn_egg");

    /**
     * Key for {@code minecraft:wooden_axe}.
     */
    public static final TypedKey<Item> WOODEN_AXE = create("wooden_axe");

    /**
     * Key for {@code minecraft:wooden_hoe}.
     */
    public static final TypedKey<Item> WOODEN_HOE = create("wooden_hoe");

    /**
     * Key for {@code minecraft:wooden_pickaxe}.
     */
    public static final TypedKey<Item> WOODEN_PICKAXE = create("wooden_pickaxe");

    /**
     * Key for {@code minecraft:wooden_shovel}.
     */
    public static final TypedKey<Item> WOODEN_SHOVEL = create("wooden_shovel");

    /**
     * Key for {@code minecraft:wooden_spear}.
     */
    public static final TypedKey<Item> WOODEN_SPEAR = create("wooden_spear");

    /**
     * Key for {@code minecraft:wooden_sword}.
     */
    public static final TypedKey<Item> WOODEN_SWORD = create("wooden_sword");

    /**
     * Key for {@code minecraft:woodland_mansion_map}.
     */
    public static final TypedKey<Item> WOODLAND_MANSION_MAP = create("woodland_mansion_map");

    /**
     * Key for {@code minecraft:writable_book}.
     */
    public static final TypedKey<Item> WRITABLE_BOOK = create("writable_book");

    /**
     * Key for {@code minecraft:written_book}.
     */
    public static final TypedKey<Item> WRITTEN_BOOK = create("written_book");

    /**
     * Key for {@code minecraft:yellow_banner}.
     */
    public static final TypedKey<Item> YELLOW_BANNER = create("yellow_banner");

    /**
     * Key for {@code minecraft:yellow_bed}.
     */
    public static final TypedKey<Item> YELLOW_BED = create("yellow_bed");

    /**
     * Key for {@code minecraft:yellow_bundle}.
     */
    public static final TypedKey<Item> YELLOW_BUNDLE = create("yellow_bundle");

    /**
     * Key for {@code minecraft:yellow_candle}.
     */
    public static final TypedKey<Item> YELLOW_CANDLE = create("yellow_candle");

    /**
     * Key for {@code minecraft:yellow_carpet}.
     */
    public static final TypedKey<Item> YELLOW_CARPET = create("yellow_carpet");

    /**
     * Key for {@code minecraft:yellow_concrete}.
     */
    public static final TypedKey<Item> YELLOW_CONCRETE = create("yellow_concrete");

    /**
     * Key for {@code minecraft:yellow_concrete_powder}.
     */
    public static final TypedKey<Item> YELLOW_CONCRETE_POWDER = create("yellow_concrete_powder");

    /**
     * Key for {@code minecraft:yellow_concrete_slab}.
     */
    public static final TypedKey<Item> YELLOW_CONCRETE_SLAB = create("yellow_concrete_slab");

    /**
     * Key for {@code minecraft:yellow_concrete_stairs}.
     */
    public static final TypedKey<Item> YELLOW_CONCRETE_STAIRS = create("yellow_concrete_stairs");

    /**
     * Key for {@code minecraft:yellow_cushion}.
     */
    public static final TypedKey<Item> YELLOW_CUSHION = create("yellow_cushion");

    /**
     * Key for {@code minecraft:yellow_dye}.
     */
    public static final TypedKey<Item> YELLOW_DYE = create("yellow_dye");

    /**
     * Key for {@code minecraft:yellow_glazed_terracotta}.
     */
    public static final TypedKey<Item> YELLOW_GLAZED_TERRACOTTA = create("yellow_glazed_terracotta");

    /**
     * Key for {@code minecraft:yellow_harness}.
     */
    public static final TypedKey<Item> YELLOW_HARNESS = create("yellow_harness");

    /**
     * Key for {@code minecraft:yellow_poplar_leaves}.
     */
    public static final TypedKey<Item> YELLOW_POPLAR_LEAVES = create("yellow_poplar_leaves");

    /**
     * Key for {@code minecraft:yellow_shulker_box}.
     */
    public static final TypedKey<Item> YELLOW_SHULKER_BOX = create("yellow_shulker_box");

    /**
     * Key for {@code minecraft:yellow_stained_glass}.
     */
    public static final TypedKey<Item> YELLOW_STAINED_GLASS = create("yellow_stained_glass");

    /**
     * Key for {@code minecraft:yellow_stained_glass_pane}.
     */
    public static final TypedKey<Item> YELLOW_STAINED_GLASS_PANE = create("yellow_stained_glass_pane");

    /**
     * Key for {@code minecraft:yellow_terracotta}.
     */
    public static final TypedKey<Item> YELLOW_TERRACOTTA = create("yellow_terracotta");

    /**
     * Key for {@code minecraft:yellow_wool}.
     */
    public static final TypedKey<Item> YELLOW_WOOL = create("yellow_wool");

    /**
     * Key for {@code minecraft:yellow_wool_slab}.
     */
    public static final TypedKey<Item> YELLOW_WOOL_SLAB = create("yellow_wool_slab");

    /**
     * Key for {@code minecraft:yellow_wool_stairs}.
     */
    public static final TypedKey<Item> YELLOW_WOOL_STAIRS = create("yellow_wool_stairs");

    /**
     * Key for {@code minecraft:zoglin_spawn_egg}.
     */
    public static final TypedKey<Item> ZOGLIN_SPAWN_EGG = create("zoglin_spawn_egg");

    /**
     * Key for {@code minecraft:zombie_head}.
     */
    public static final TypedKey<Item> ZOMBIE_HEAD = create("zombie_head");

    /**
     * Key for {@code minecraft:zombie_horse_spawn_egg}.
     */
    public static final TypedKey<Item> ZOMBIE_HORSE_SPAWN_EGG = create("zombie_horse_spawn_egg");

    /**
     * Key for {@code minecraft:zombie_nautilus_spawn_egg}.
     */
    public static final TypedKey<Item> ZOMBIE_NAUTILUS_SPAWN_EGG = create("zombie_nautilus_spawn_egg");

    /**
     * Key for {@code minecraft:zombie_spawn_egg}.
     */
    public static final TypedKey<Item> ZOMBIE_SPAWN_EGG = create("zombie_spawn_egg");

    /**
     * Key for {@code minecraft:zombie_villager_spawn_egg}.
     */
    public static final TypedKey<Item> ZOMBIE_VILLAGER_SPAWN_EGG = create("zombie_villager_spawn_egg");

    /**
     * Key for {@code minecraft:zombified_piglin_spawn_egg}.
     */
    public static final TypedKey<Item> ZOMBIFIED_PIGLIN_SPAWN_EGG = create("zombified_piglin_spawn_egg");

    private static final List<TypedKey<Item>> VALUES = List.of(
        ABANDONED_CAMP_MAP,
        ACACIA_BOAT,
        ACACIA_BUTTON,
        ACACIA_CHEST_BOAT,
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
        ACACIA_WOOD,
        ACTIVATOR_RAIL,
        AIR,
        ALLAY_SPAWN_EGG,
        ALLIUM,
        AMETHYST_BLOCK,
        AMETHYST_CLUSTER,
        AMETHYST_SHARD,
        ANCIENT_DEBRIS,
        ANDESITE,
        ANDESITE_SLAB,
        ANDESITE_STAIRS,
        ANDESITE_WALL,
        ANGLER_POTTERY_SHERD,
        ANVIL,
        APPLE,
        ARCHER_POTTERY_SHERD,
        ARMADILLO_SCUTE,
        ARMADILLO_SPAWN_EGG,
        ARMOR_STAND,
        ARMS_UP_POTTERY_SHERD,
        ARROW,
        AXOLOTL_BUCKET,
        AXOLOTL_SPAWN_EGG,
        AZALEA,
        AZALEA_LEAVES,
        AZURE_BLUET,
        BAKED_POTATO,
        BAMBOO,
        BAMBOO_BLOCK,
        BAMBOO_BUTTON,
        BAMBOO_CHEST_RAFT,
        BAMBOO_DOOR,
        BAMBOO_FENCE,
        BAMBOO_FENCE_GATE,
        BAMBOO_HANGING_SIGN,
        BAMBOO_MOSAIC,
        BAMBOO_MOSAIC_SLAB,
        BAMBOO_MOSAIC_STAIRS,
        BAMBOO_PLANKS,
        BAMBOO_PRESSURE_PLATE,
        BAMBOO_RAFT,
        BAMBOO_SHELF,
        BAMBOO_SIGN,
        BAMBOO_SLAB,
        BAMBOO_STAIRS,
        BAMBOO_TRAPDOOR,
        BARREL,
        BARRIER,
        BASALT,
        BAT_SPAWN_EGG,
        BEACON,
        BEDROCK,
        BEE_NEST,
        BEE_SPAWN_EGG,
        BEEF,
        BEEHIVE,
        BEETROOT,
        BEETROOT_SEEDS,
        BEETROOT_SOUP,
        BELL,
        BIG_DRIPLEAF,
        BIRCH_BOAT,
        BIRCH_BUTTON,
        BIRCH_CHEST_BOAT,
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
        BIRCH_WOOD,
        BLACK_BANNER,
        BLACK_BED,
        BLACK_BUNDLE,
        BLACK_CANDLE,
        BLACK_CARPET,
        BLACK_CONCRETE,
        BLACK_CONCRETE_POWDER,
        BLACK_CONCRETE_SLAB,
        BLACK_CONCRETE_STAIRS,
        BLACK_CUSHION,
        BLACK_DYE,
        BLACK_GLAZED_TERRACOTTA,
        BLACK_HARNESS,
        BLACK_SHULKER_BOX,
        BLACK_STAINED_GLASS,
        BLACK_STAINED_GLASS_PANE,
        BLACK_TERRACOTTA,
        BLACK_WOOL,
        BLACK_WOOL_SLAB,
        BLACK_WOOL_STAIRS,
        BLACKSTONE,
        BLACKSTONE_SLAB,
        BLACKSTONE_STAIRS,
        BLACKSTONE_WALL,
        BLADE_POTTERY_SHERD,
        BLAST_FURNACE,
        BLAZE_POWDER,
        BLAZE_ROD,
        BLAZE_SPAWN_EGG,
        BLUE_BANNER,
        BLUE_BED,
        BLUE_BUNDLE,
        BLUE_CANDLE,
        BLUE_CARPET,
        BLUE_CONCRETE,
        BLUE_CONCRETE_POWDER,
        BLUE_CONCRETE_SLAB,
        BLUE_CONCRETE_STAIRS,
        BLUE_CUSHION,
        BLUE_DYE,
        BLUE_EGG,
        BLUE_GLAZED_TERRACOTTA,
        BLUE_HARNESS,
        BLUE_ICE,
        BLUE_ORCHID,
        BLUE_SHULKER_BOX,
        BLUE_STAINED_GLASS,
        BLUE_STAINED_GLASS_PANE,
        BLUE_TERRACOTTA,
        BLUE_WOOL,
        BLUE_WOOL_SLAB,
        BLUE_WOOL_STAIRS,
        BOGGED_SPAWN_EGG,
        BOLT_ARMOR_TRIM_SMITHING_TEMPLATE,
        BONE,
        BONE_BLOCK,
        BONE_MEAL,
        BOOK,
        BOOKSHELF,
        BORDURE_INDENTED_BANNER_PATTERN,
        BOW,
        BOWL,
        BRAIN_CORAL,
        BRAIN_CORAL_BLOCK,
        BRAIN_CORAL_FAN,
        BREAD,
        BREEZE_ROD,
        BREEZE_SPAWN_EGG,
        BREWER_POTTERY_SHERD,
        BREWING_STAND,
        BRICK,
        BRICK_SLAB,
        BRICK_STAIRS,
        BRICK_WALL,
        BRICKS,
        BROWN_BANNER,
        BROWN_BED,
        BROWN_BUNDLE,
        BROWN_CANDLE,
        BROWN_CARPET,
        BROWN_CONCRETE,
        BROWN_CONCRETE_POWDER,
        BROWN_CONCRETE_SLAB,
        BROWN_CONCRETE_STAIRS,
        BROWN_CUSHION,
        BROWN_DYE,
        BROWN_EGG,
        BROWN_GLAZED_TERRACOTTA,
        BROWN_HARNESS,
        BROWN_MUSHROOM,
        BROWN_MUSHROOM_BLOCK,
        BROWN_SHULKER_BOX,
        BROWN_STAINED_GLASS,
        BROWN_STAINED_GLASS_PANE,
        BROWN_TERRACOTTA,
        BROWN_WOOL,
        BROWN_WOOL_SLAB,
        BROWN_WOOL_STAIRS,
        BRUSH,
        BUBBLE_CORAL,
        BUBBLE_CORAL_BLOCK,
        BUBBLE_CORAL_FAN,
        BUCKET,
        BUDDING_AMETHYST,
        BUNDLE,
        BURIED_ANCIENT_CITY_MAP,
        BURIED_MINESHAFT_MAP,
        BURIED_TREASURE_MAP,
        BURIED_TRIAL_CHAMBERS_MAP,
        BURN_POTTERY_SHERD,
        BUSH,
        CACTUS,
        CACTUS_FLOWER,
        CAKE,
        CALCITE,
        CALIBRATED_SCULK_SENSOR,
        CAMEL_HUSK_SPAWN_EGG,
        CAMEL_SPAWN_EGG,
        CAMPFIRE,
        CANDLE,
        CARROT,
        CARROT_ON_A_STICK,
        CARTOGRAPHY_TABLE,
        CARVED_PUMPKIN,
        CAT_SPAWN_EGG,
        CAULDRON,
        CAVE_SPIDER_SPAWN_EGG,
        CHAIN_COMMAND_BLOCK,
        CHAINMAIL_BOOTS,
        CHAINMAIL_CHESTPLATE,
        CHAINMAIL_HELMET,
        CHAINMAIL_LEGGINGS,
        CHARCOAL,
        CHERRY_BOAT,
        CHERRY_BUTTON,
        CHERRY_CHEST_BOAT,
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
        CHERRY_WOOD,
        CHEST,
        CHEST_MINECART,
        CHICKEN,
        CHICKEN_SPAWN_EGG,
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
        CHORUS_FRUIT,
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
        CLAY_BALL,
        CLOCK,
        CLOSED_EYEBLOSSOM,
        COAL,
        COAL_BLOCK,
        COAL_ORE,
        COARSE_DIRT,
        COAST_ARMOR_TRIM_SMITHING_TEMPLATE,
        COBBLED_DEEPSLATE,
        COBBLED_DEEPSLATE_SLAB,
        COBBLED_DEEPSLATE_STAIRS,
        COBBLED_DEEPSLATE_WALL,
        COBBLESTONE,
        COBBLESTONE_SLAB,
        COBBLESTONE_STAIRS,
        COBBLESTONE_WALL,
        COBWEB,
        COCOA_BEANS,
        COD,
        COD_BUCKET,
        COD_SPAWN_EGG,
        COMMAND_BLOCK,
        COMMAND_BLOCK_MINECART,
        COMPARATOR,
        COMPASS,
        COMPOSTER,
        CONDUIT,
        COOKED_BEEF,
        COOKED_CHICKEN,
        COOKED_COD,
        COOKED_MUTTON,
        COOKED_PORKCHOP,
        COOKED_RABBIT,
        COOKED_SALMON,
        COOKIE,
        COPPER_AXE,
        COPPER_BARS,
        COPPER_BLOCK,
        COPPER_BOOTS,
        COPPER_BULB,
        COPPER_CHAIN,
        COPPER_CHEST,
        COPPER_CHESTPLATE,
        COPPER_DOOR,
        COPPER_GOLEM_SPAWN_EGG,
        COPPER_GOLEM_STATUE,
        COPPER_GRATE,
        COPPER_HELMET,
        COPPER_HOE,
        COPPER_HORSE_ARMOR,
        COPPER_INGOT,
        COPPER_LANTERN,
        COPPER_LEGGINGS,
        COPPER_NAUTILUS_ARMOR,
        COPPER_NUGGET,
        COPPER_ORE,
        COPPER_PICKAXE,
        COPPER_SHOVEL,
        COPPER_SPEAR,
        COPPER_SWORD,
        COPPER_TORCH,
        COPPER_TRAPDOOR,
        CORNFLOWER,
        COW_SPAWN_EGG,
        CRACKED_DEEPSLATE_BRICKS,
        CRACKED_DEEPSLATE_TILES,
        CRACKED_NETHER_BRICKS,
        CRACKED_POLISHED_BLACKSTONE_BRICKS,
        CRACKED_STONE_BRICKS,
        CRAFTER,
        CRAFTING_TABLE,
        CREAKING_HEART,
        CREAKING_SPAWN_EGG,
        CREEPER_BANNER_PATTERN,
        CREEPER_HEAD,
        CREEPER_SPAWN_EGG,
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
        CROSSBOW,
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
        CYAN_BUNDLE,
        CYAN_CANDLE,
        CYAN_CARPET,
        CYAN_CONCRETE,
        CYAN_CONCRETE_POWDER,
        CYAN_CONCRETE_SLAB,
        CYAN_CONCRETE_STAIRS,
        CYAN_CUSHION,
        CYAN_DYE,
        CYAN_GLAZED_TERRACOTTA,
        CYAN_HARNESS,
        CYAN_SHULKER_BOX,
        CYAN_STAINED_GLASS,
        CYAN_STAINED_GLASS_PANE,
        CYAN_TERRACOTTA,
        CYAN_WOOL,
        CYAN_WOOL_SLAB,
        CYAN_WOOL_STAIRS,
        DAMAGED_ANVIL,
        DANDELION,
        DANGER_POTTERY_SHERD,
        DARK_OAK_BOAT,
        DARK_OAK_BUTTON,
        DARK_OAK_CHEST_BOAT,
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
        DARK_OAK_WOOD,
        DARK_PRISMARINE,
        DARK_PRISMARINE_SLAB,
        DARK_PRISMARINE_STAIRS,
        DAYLIGHT_DETECTOR,
        DEAD_BRAIN_CORAL,
        DEAD_BRAIN_CORAL_BLOCK,
        DEAD_BRAIN_CORAL_FAN,
        DEAD_BUBBLE_CORAL,
        DEAD_BUBBLE_CORAL_BLOCK,
        DEAD_BUBBLE_CORAL_FAN,
        DEAD_BUSH,
        DEAD_FIRE_CORAL,
        DEAD_FIRE_CORAL_BLOCK,
        DEAD_FIRE_CORAL_FAN,
        DEAD_HORN_CORAL,
        DEAD_HORN_CORAL_BLOCK,
        DEAD_HORN_CORAL_FAN,
        DEAD_TUBE_CORAL,
        DEAD_TUBE_CORAL_BLOCK,
        DEAD_TUBE_CORAL_FAN,
        DEBUG_STICK,
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
        DESERT_PYRAMID_MAP,
        DESERT_VILLAGE_MAP,
        DETECTOR_RAIL,
        DIAMOND,
        DIAMOND_AXE,
        DIAMOND_BLOCK,
        DIAMOND_BOOTS,
        DIAMOND_CHESTPLATE,
        DIAMOND_HELMET,
        DIAMOND_HOE,
        DIAMOND_HORSE_ARMOR,
        DIAMOND_LEGGINGS,
        DIAMOND_NAUTILUS_ARMOR,
        DIAMOND_ORE,
        DIAMOND_PICKAXE,
        DIAMOND_SHOVEL,
        DIAMOND_SPEAR,
        DIAMOND_SWORD,
        DIORITE,
        DIORITE_SLAB,
        DIORITE_STAIRS,
        DIORITE_WALL,
        DIRT,
        DIRT_PATH,
        DISC_FRAGMENT_5,
        DISPENSER,
        DOLPHIN_SPAWN_EGG,
        DONKEY_SPAWN_EGG,
        DRAGON_BREATH,
        DRAGON_EGG,
        DRAGON_HEAD,
        DRIED_GHAST,
        DRIED_KELP,
        DRIED_KELP_BLOCK,
        DRIPSTONE_BLOCK,
        DROPPER,
        DROWNED_SPAWN_EGG,
        DUNE_ARMOR_TRIM_SMITHING_TEMPLATE,
        ECHO_SHARD,
        EGG,
        ELDER_GUARDIAN_SPAWN_EGG,
        ELYTRA,
        EMERALD,
        EMERALD_BLOCK,
        EMERALD_ORE,
        ENCHANTED_BOOK,
        ENCHANTED_GOLDEN_APPLE,
        ENCHANTING_TABLE,
        END_CRYSTAL,
        END_PORTAL_FRAME,
        END_ROD,
        END_STONE,
        END_STONE_BRICK_SLAB,
        END_STONE_BRICK_STAIRS,
        END_STONE_BRICK_WALL,
        END_STONE_BRICKS,
        ENDER_CHEST,
        ENDER_DRAGON_SPAWN_EGG,
        ENDER_EYE,
        ENDER_PEARL,
        ENDERMAN_SPAWN_EGG,
        ENDERMITE_SPAWN_EGG,
        EVOKER_SPAWN_EGG,
        EXPERIENCE_BOTTLE,
        EXPLORER_POTTERY_SHERD,
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
        EYE_ARMOR_TRIM_SMITHING_TEMPLATE,
        FARMLAND,
        FEATHER,
        FERMENTED_SPIDER_EYE,
        FERN,
        FIELD_MASONED_BANNER_PATTERN,
        FILLED_MAP,
        FIRE_CHARGE,
        FIRE_CORAL,
        FIRE_CORAL_BLOCK,
        FIRE_CORAL_FAN,
        FIREFLY_BUSH,
        FIREWORK_ROCKET,
        FIREWORK_STAR,
        FISHING_ROD,
        FLETCHING_TABLE,
        FLINT,
        FLINT_AND_STEEL,
        FLOW_ARMOR_TRIM_SMITHING_TEMPLATE,
        FLOW_BANNER_PATTERN,
        FLOW_POTTERY_SHERD,
        FLOWER_BANNER_PATTERN,
        FLOWER_POT,
        FLOWERING_AZALEA,
        FLOWERING_AZALEA_LEAVES,
        FOX_SPAWN_EGG,
        FRIEND_POTTERY_SHERD,
        FROG_SPAWN_EGG,
        FROGSPAWN,
        FURNACE,
        FURNACE_MINECART,
        GHAST_SPAWN_EGG,
        GHAST_TEAR,
        GILDED_BLACKSTONE,
        GLASS,
        GLASS_BOTTLE,
        GLASS_PANE,
        GLISTERING_MELON_SLICE,
        GLOBE_BANNER_PATTERN,
        GLOW_BERRIES,
        GLOW_INK_SAC,
        GLOW_ITEM_FRAME,
        GLOW_LICHEN,
        GLOW_SQUID_SPAWN_EGG,
        GLOWSTONE,
        GLOWSTONE_DUST,
        GOAT_HORN,
        GOAT_SPAWN_EGG,
        GOLD_BLOCK,
        GOLD_INGOT,
        GOLD_NUGGET,
        GOLD_ORE,
        GOLDEN_APPLE,
        GOLDEN_AXE,
        GOLDEN_BOOTS,
        GOLDEN_CARROT,
        GOLDEN_CHESTPLATE,
        GOLDEN_DANDELION,
        GOLDEN_HELMET,
        GOLDEN_HOE,
        GOLDEN_HORSE_ARMOR,
        GOLDEN_LEGGINGS,
        GOLDEN_NAUTILUS_ARMOR,
        GOLDEN_PICKAXE,
        GOLDEN_SHOVEL,
        GOLDEN_SPEAR,
        GOLDEN_SWORD,
        GRANITE,
        GRANITE_SLAB,
        GRANITE_STAIRS,
        GRANITE_WALL,
        GRASS_BLOCK,
        GRAVEL,
        GRAY_BANNER,
        GRAY_BED,
        GRAY_BUNDLE,
        GRAY_CANDLE,
        GRAY_CARPET,
        GRAY_CONCRETE,
        GRAY_CONCRETE_POWDER,
        GRAY_CONCRETE_SLAB,
        GRAY_CONCRETE_STAIRS,
        GRAY_CUSHION,
        GRAY_DYE,
        GRAY_GLAZED_TERRACOTTA,
        GRAY_HARNESS,
        GRAY_SHULKER_BOX,
        GRAY_STAINED_GLASS,
        GRAY_STAINED_GLASS_PANE,
        GRAY_TERRACOTTA,
        GRAY_WOOL,
        GRAY_WOOL_SLAB,
        GRAY_WOOL_STAIRS,
        GREEN_BANNER,
        GREEN_BED,
        GREEN_BUNDLE,
        GREEN_CANDLE,
        GREEN_CARPET,
        GREEN_CONCRETE,
        GREEN_CONCRETE_POWDER,
        GREEN_CONCRETE_SLAB,
        GREEN_CONCRETE_STAIRS,
        GREEN_CUSHION,
        GREEN_DYE,
        GREEN_GLAZED_TERRACOTTA,
        GREEN_HARNESS,
        GREEN_SHULKER_BOX,
        GREEN_STAINED_GLASS,
        GREEN_STAINED_GLASS_PANE,
        GREEN_TERRACOTTA,
        GREEN_WOOL,
        GREEN_WOOL_SLAB,
        GREEN_WOOL_STAIRS,
        GRINDSTONE,
        GUARDIAN_SPAWN_EGG,
        GUNPOWDER,
        GUSTER_BANNER_PATTERN,
        GUSTER_POTTERY_SHERD,
        HANGING_ROOTS,
        HAPPY_GHAST_SPAWN_EGG,
        HAY_BLOCK,
        HEART_OF_THE_SEA,
        HEART_POTTERY_SHERD,
        HEARTBREAK_POTTERY_SHERD,
        HEAVY_CORE,
        HEAVY_WEIGHTED_PRESSURE_PLATE,
        HOGLIN_SPAWN_EGG,
        HONEY_BLOCK,
        HONEY_BOTTLE,
        HONEYCOMB,
        HONEYCOMB_BLOCK,
        HOPPER,
        HOPPER_MINECART,
        HORN_CORAL,
        HORN_CORAL_BLOCK,
        HORN_CORAL_FAN,
        HORSE_SPAWN_EGG,
        HOST_ARMOR_TRIM_SMITHING_TEMPLATE,
        HOWL_POTTERY_SHERD,
        HUSK_SPAWN_EGG,
        ICE,
        INFESTED_CHISELED_STONE_BRICKS,
        INFESTED_COBBLESTONE,
        INFESTED_CRACKED_STONE_BRICKS,
        INFESTED_DEEPSLATE,
        INFESTED_MOSSY_STONE_BRICKS,
        INFESTED_STONE,
        INFESTED_STONE_BRICKS,
        INK_SAC,
        IRON_AXE,
        IRON_BARS,
        IRON_BLOCK,
        IRON_BOOTS,
        IRON_CHAIN,
        IRON_CHESTPLATE,
        IRON_DOOR,
        IRON_GOLEM_SPAWN_EGG,
        IRON_HELMET,
        IRON_HOE,
        IRON_HORSE_ARMOR,
        IRON_INGOT,
        IRON_LEGGINGS,
        IRON_NAUTILUS_ARMOR,
        IRON_NUGGET,
        IRON_ORE,
        IRON_PICKAXE,
        IRON_SHOVEL,
        IRON_SPEAR,
        IRON_SWORD,
        IRON_TRAPDOOR,
        ITEM_FRAME,
        JACK_O_LANTERN,
        JIGSAW,
        JUKEBOX,
        JUNGLE_BOAT,
        JUNGLE_BUTTON,
        JUNGLE_CHEST_BOAT,
        JUNGLE_DOOR,
        JUNGLE_FENCE,
        JUNGLE_FENCE_GATE,
        JUNGLE_HANGING_SIGN,
        JUNGLE_LEAVES,
        JUNGLE_LOG,
        JUNGLE_PLANKS,
        JUNGLE_PRESSURE_PLATE,
        JUNGLE_PYRAMID_MAP,
        JUNGLE_SAPLING,
        JUNGLE_SHELF,
        JUNGLE_SIGN,
        JUNGLE_SLAB,
        JUNGLE_STAIRS,
        JUNGLE_TRAPDOOR,
        JUNGLE_WOOD,
        KELP,
        KNOWLEDGE_BOOK,
        LADDER,
        LANTERN,
        LAPIS_BLOCK,
        LAPIS_LAZULI,
        LAPIS_ORE,
        LARGE_AMETHYST_BUD,
        LARGE_FERN,
        LAVA_BUCKET,
        LEAD,
        LEAF_LITTER,
        LEATHER,
        LEATHER_BOOTS,
        LEATHER_CHESTPLATE,
        LEATHER_HELMET,
        LEATHER_HORSE_ARMOR,
        LEATHER_LEGGINGS,
        LECTERN,
        LEVER,
        LIGHT,
        LIGHT_BLUE_BANNER,
        LIGHT_BLUE_BED,
        LIGHT_BLUE_BUNDLE,
        LIGHT_BLUE_CANDLE,
        LIGHT_BLUE_CARPET,
        LIGHT_BLUE_CONCRETE,
        LIGHT_BLUE_CONCRETE_POWDER,
        LIGHT_BLUE_CONCRETE_SLAB,
        LIGHT_BLUE_CONCRETE_STAIRS,
        LIGHT_BLUE_CUSHION,
        LIGHT_BLUE_DYE,
        LIGHT_BLUE_GLAZED_TERRACOTTA,
        LIGHT_BLUE_HARNESS,
        LIGHT_BLUE_SHULKER_BOX,
        LIGHT_BLUE_STAINED_GLASS,
        LIGHT_BLUE_STAINED_GLASS_PANE,
        LIGHT_BLUE_TERRACOTTA,
        LIGHT_BLUE_WOOL,
        LIGHT_BLUE_WOOL_SLAB,
        LIGHT_BLUE_WOOL_STAIRS,
        LIGHT_GRAY_BANNER,
        LIGHT_GRAY_BED,
        LIGHT_GRAY_BUNDLE,
        LIGHT_GRAY_CANDLE,
        LIGHT_GRAY_CARPET,
        LIGHT_GRAY_CONCRETE,
        LIGHT_GRAY_CONCRETE_POWDER,
        LIGHT_GRAY_CONCRETE_SLAB,
        LIGHT_GRAY_CONCRETE_STAIRS,
        LIGHT_GRAY_CUSHION,
        LIGHT_GRAY_DYE,
        LIGHT_GRAY_GLAZED_TERRACOTTA,
        LIGHT_GRAY_HARNESS,
        LIGHT_GRAY_SHULKER_BOX,
        LIGHT_GRAY_STAINED_GLASS,
        LIGHT_GRAY_STAINED_GLASS_PANE,
        LIGHT_GRAY_TERRACOTTA,
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
        LIME_BUNDLE,
        LIME_CANDLE,
        LIME_CARPET,
        LIME_CONCRETE,
        LIME_CONCRETE_POWDER,
        LIME_CONCRETE_SLAB,
        LIME_CONCRETE_STAIRS,
        LIME_CUSHION,
        LIME_DYE,
        LIME_GLAZED_TERRACOTTA,
        LIME_HARNESS,
        LIME_SHULKER_BOX,
        LIME_STAINED_GLASS,
        LIME_STAINED_GLASS_PANE,
        LIME_TERRACOTTA,
        LIME_WOOL,
        LIME_WOOL_SLAB,
        LIME_WOOL_STAIRS,
        LINGERING_POTION,
        LLAMA_SPAWN_EGG,
        LODESTONE,
        LOOM,
        MACE,
        MAGENTA_BANNER,
        MAGENTA_BED,
        MAGENTA_BUNDLE,
        MAGENTA_CANDLE,
        MAGENTA_CARPET,
        MAGENTA_CONCRETE,
        MAGENTA_CONCRETE_POWDER,
        MAGENTA_CONCRETE_SLAB,
        MAGENTA_CONCRETE_STAIRS,
        MAGENTA_CUSHION,
        MAGENTA_DYE,
        MAGENTA_GLAZED_TERRACOTTA,
        MAGENTA_HARNESS,
        MAGENTA_SHULKER_BOX,
        MAGENTA_STAINED_GLASS,
        MAGENTA_STAINED_GLASS_PANE,
        MAGENTA_TERRACOTTA,
        MAGENTA_WOOL,
        MAGENTA_WOOL_SLAB,
        MAGENTA_WOOL_STAIRS,
        MAGMA_BLOCK,
        MAGMA_CREAM,
        MAGMA_CUBE_SPAWN_EGG,
        MANGROVE_BOAT,
        MANGROVE_BUTTON,
        MANGROVE_CHEST_BOAT,
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
        MANGROVE_WOOD,
        MAP,
        MEDIUM_AMETHYST_BUD,
        MELON,
        MELON_SEEDS,
        MELON_SLICE,
        MILK_BUCKET,
        MINECART,
        MINER_POTTERY_SHERD,
        MOJANG_BANNER_PATTERN,
        MOOSHROOM_SPAWN_EGG,
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
        MOURNER_POTTERY_SHERD,
        MUD,
        MUD_BRICK_SLAB,
        MUD_BRICK_STAIRS,
        MUD_BRICK_WALL,
        MUD_BRICKS,
        MUDDY_MANGROVE_ROOTS,
        MULE_SPAWN_EGG,
        MUSHROOM_STEM,
        MUSHROOM_STEW,
        MUSIC_DISC_11,
        MUSIC_DISC_13,
        MUSIC_DISC_5,
        MUSIC_DISC_BLOCKS,
        MUSIC_DISC_BOUNCE,
        MUSIC_DISC_CAT,
        MUSIC_DISC_CHIRP,
        MUSIC_DISC_CREATOR,
        MUSIC_DISC_CREATOR_MUSIC_BOX,
        MUSIC_DISC_FAR,
        MUSIC_DISC_LAVA_CHICKEN,
        MUSIC_DISC_MALL,
        MUSIC_DISC_MELLOHI,
        MUSIC_DISC_OTHERSIDE,
        MUSIC_DISC_PIGSTEP,
        MUSIC_DISC_PRECIPICE,
        MUSIC_DISC_RELIC,
        MUSIC_DISC_STAL,
        MUSIC_DISC_STRAD,
        MUSIC_DISC_TEARS,
        MUSIC_DISC_WAIT,
        MUSIC_DISC_WARD,
        MUTTON,
        MYCELIUM,
        NAME_TAG,
        NAUTILUS_SHELL,
        NAUTILUS_SPAWN_EGG,
        NETHER_BRICK,
        NETHER_BRICK_FENCE,
        NETHER_BRICK_SLAB,
        NETHER_BRICK_STAIRS,
        NETHER_BRICK_WALL,
        NETHER_BRICKS,
        NETHER_GOLD_ORE,
        NETHER_QUARTZ_ORE,
        NETHER_SPROUTS,
        NETHER_STAR,
        NETHER_WART,
        NETHER_WART_BLOCK,
        NETHERITE_AXE,
        NETHERITE_BLOCK,
        NETHERITE_BOOTS,
        NETHERITE_CHESTPLATE,
        NETHERITE_HELMET,
        NETHERITE_HOE,
        NETHERITE_HORSE_ARMOR,
        NETHERITE_INGOT,
        NETHERITE_LEGGINGS,
        NETHERITE_NAUTILUS_ARMOR,
        NETHERITE_PICKAXE,
        NETHERITE_SCRAP,
        NETHERITE_SHOVEL,
        NETHERITE_SPEAR,
        NETHERITE_SWORD,
        NETHERITE_UPGRADE_SMITHING_TEMPLATE,
        NETHERRACK,
        NOTE_BLOCK,
        OAK_BOAT,
        OAK_BUTTON,
        OAK_CHEST_BOAT,
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
        OAK_WOOD,
        OBSERVER,
        OBSIDIAN,
        OCEAN_MONUMENT_MAP,
        OCELOT_SPAWN_EGG,
        OCHRE_FROGLIGHT,
        OMINOUS_BOTTLE,
        OMINOUS_TRIAL_KEY,
        OPEN_EYEBLOSSOM,
        ORANGE_BANNER,
        ORANGE_BED,
        ORANGE_BUNDLE,
        ORANGE_CANDLE,
        ORANGE_CARPET,
        ORANGE_CONCRETE,
        ORANGE_CONCRETE_POWDER,
        ORANGE_CONCRETE_SLAB,
        ORANGE_CONCRETE_STAIRS,
        ORANGE_CUSHION,
        ORANGE_DYE,
        ORANGE_GLAZED_TERRACOTTA,
        ORANGE_HARNESS,
        ORANGE_POPLAR_LEAVES,
        ORANGE_SHULKER_BOX,
        ORANGE_STAINED_GLASS,
        ORANGE_STAINED_GLASS_PANE,
        ORANGE_TERRACOTTA,
        ORANGE_TULIP,
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
        PAINTING,
        PALE_HANGING_MOSS,
        PALE_MOSS_BLOCK,
        PALE_MOSS_CARPET,
        PALE_OAK_BOAT,
        PALE_OAK_BUTTON,
        PALE_OAK_CHEST_BOAT,
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
        PALE_OAK_WOOD,
        PANDA_SPAWN_EGG,
        PAPER,
        PARCHED_SPAWN_EGG,
        PARROT_SPAWN_EGG,
        PEARLESCENT_FROGLIGHT,
        PEONY,
        PETRIFIED_OAK_SLAB,
        PHANTOM_MEMBRANE,
        PHANTOM_SPAWN_EGG,
        PIG_SPAWN_EGG,
        PIGLIN_BANNER_PATTERN,
        PIGLIN_BRUTE_SPAWN_EGG,
        PIGLIN_HEAD,
        PIGLIN_SPAWN_EGG,
        PILLAGER_SPAWN_EGG,
        PINK_BANNER,
        PINK_BED,
        PINK_BUNDLE,
        PINK_CANDLE,
        PINK_CARPET,
        PINK_CONCRETE,
        PINK_CONCRETE_POWDER,
        PINK_CONCRETE_SLAB,
        PINK_CONCRETE_STAIRS,
        PINK_CUSHION,
        PINK_DYE,
        PINK_GLAZED_TERRACOTTA,
        PINK_HARNESS,
        PINK_PETALS,
        PINK_SHULKER_BOX,
        PINK_STAINED_GLASS,
        PINK_STAINED_GLASS_PANE,
        PINK_TERRACOTTA,
        PINK_TULIP,
        PINK_WOOL,
        PINK_WOOL_SLAB,
        PINK_WOOL_STAIRS,
        PISTON,
        PITCHER_PLANT,
        PITCHER_POD,
        PLAINS_VILLAGE_MAP,
        PLAYER_HEAD,
        PLENTY_POTTERY_SHERD,
        PODZOL,
        POINTED_DRIPSTONE,
        POISONOUS_POTATO,
        POLAR_BEAR_SPAWN_EGG,
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
        POPLAR_BOAT,
        POPLAR_BUTTON,
        POPLAR_CHEST_BOAT,
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
        POPLAR_WOOD,
        POPPED_CHORUS_FRUIT,
        POPPY,
        PORKCHOP,
        POTATO,
        POTENT_SULFUR,
        POTION,
        POWDER_SNOW_BUCKET,
        POWERED_RAIL,
        PRISMARINE,
        PRISMARINE_BRICK_SLAB,
        PRISMARINE_BRICK_STAIRS,
        PRISMARINE_BRICKS,
        PRISMARINE_CRYSTALS,
        PRISMARINE_SHARD,
        PRISMARINE_SLAB,
        PRISMARINE_STAIRS,
        PRISMARINE_WALL,
        PRIZE_POTTERY_SHERD,
        PUFFERFISH,
        PUFFERFISH_BUCKET,
        PUFFERFISH_SPAWN_EGG,
        PUMPKIN,
        PUMPKIN_PIE,
        PUMPKIN_SEEDS,
        PURPLE_BANNER,
        PURPLE_BED,
        PURPLE_BUNDLE,
        PURPLE_CANDLE,
        PURPLE_CARPET,
        PURPLE_CONCRETE,
        PURPLE_CONCRETE_POWDER,
        PURPLE_CONCRETE_SLAB,
        PURPLE_CONCRETE_STAIRS,
        PURPLE_CUSHION,
        PURPLE_DYE,
        PURPLE_GLAZED_TERRACOTTA,
        PURPLE_HARNESS,
        PURPLE_SHULKER_BOX,
        PURPLE_STAINED_GLASS,
        PURPLE_STAINED_GLASS_PANE,
        PURPLE_TERRACOTTA,
        PURPLE_WOOL,
        PURPLE_WOOL_SLAB,
        PURPLE_WOOL_STAIRS,
        PURPUR_BLOCK,
        PURPUR_PILLAR,
        PURPUR_SLAB,
        PURPUR_STAIRS,
        QUARTZ,
        QUARTZ_BLOCK,
        QUARTZ_BRICKS,
        QUARTZ_PILLAR,
        QUARTZ_SLAB,
        QUARTZ_STAIRS,
        RABBIT,
        RABBIT_FOOT,
        RABBIT_HIDE,
        RABBIT_SPAWN_EGG,
        RABBIT_STEW,
        RAIL,
        RAISER_ARMOR_TRIM_SMITHING_TEMPLATE,
        RAVAGER_SPAWN_EGG,
        RAW_COPPER,
        RAW_COPPER_BLOCK,
        RAW_GOLD,
        RAW_GOLD_BLOCK,
        RAW_IRON,
        RAW_IRON_BLOCK,
        RECOVERY_COMPASS,
        RED_BANNER,
        RED_BED,
        RED_BUNDLE,
        RED_CANDLE,
        RED_CARPET,
        RED_CONCRETE,
        RED_CONCRETE_POWDER,
        RED_CONCRETE_SLAB,
        RED_CONCRETE_STAIRS,
        RED_CUSHION,
        RED_DYE,
        RED_GLAZED_TERRACOTTA,
        RED_HARNESS,
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
        RED_WOOL,
        RED_WOOL_SLAB,
        RED_WOOL_STAIRS,
        REDSTONE,
        REDSTONE_BLOCK,
        REDSTONE_LAMP,
        REDSTONE_ORE,
        REDSTONE_TORCH,
        REINFORCED_DEEPSLATE,
        REPEATER,
        REPEATING_COMMAND_BLOCK,
        RESIN_BLOCK,
        RESIN_BRICK,
        RESIN_BRICK_SLAB,
        RESIN_BRICK_STAIRS,
        RESIN_BRICK_WALL,
        RESIN_BRICKS,
        RESIN_CLUMP,
        RESPAWN_ANCHOR,
        RIB_ARMOR_TRIM_SMITHING_TEMPLATE,
        ROOTED_DIRT,
        ROSE_BUSH,
        ROTTEN_FLESH,
        SADDLE,
        SALMON,
        SALMON_BUCKET,
        SALMON_SPAWN_EGG,
        SAND,
        SANDSTONE,
        SANDSTONE_SLAB,
        SANDSTONE_STAIRS,
        SANDSTONE_WALL,
        SAVANNA_VILLAGE_MAP,
        SCAFFOLDING,
        SCRAPE_POTTERY_SHERD,
        SCULK,
        SCULK_CATALYST,
        SCULK_SENSOR,
        SCULK_SHRIEKER,
        SCULK_VEIN,
        SEA_LANTERN,
        SEA_PICKLE,
        SEAGRASS,
        SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE,
        SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE,
        SHEAF_POTTERY_SHERD,
        SHEARS,
        SHEEP_SPAWN_EGG,
        SHELF_MUSHROOM,
        SHELTER_POTTERY_SHERD,
        SHIELD,
        SHORT_DRY_GRASS,
        SHORT_GRASS,
        SHROOMLIGHT,
        SHULKER_BOX,
        SHULKER_SHELL,
        SHULKER_SPAWN_EGG,
        SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
        SILVERFISH_SPAWN_EGG,
        SKELETON_HORSE_SPAWN_EGG,
        SKELETON_SKULL,
        SKELETON_SPAWN_EGG,
        SKULL_BANNER_PATTERN,
        SKULL_POTTERY_SHERD,
        SLIME_BALL,
        SLIME_BLOCK,
        SLIME_SPAWN_EGG,
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
        SNIFFER_SPAWN_EGG,
        SNORT_POTTERY_SHERD,
        SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE,
        SNOW,
        SNOW_BLOCK,
        SNOW_GOLEM_SPAWN_EGG,
        SNOWBALL,
        SNOWY_VILLAGE_MAP,
        SOUL_CAMPFIRE,
        SOUL_LANTERN,
        SOUL_SAND,
        SOUL_SOIL,
        SOUL_TORCH,
        SPAWNER,
        SPECTRAL_ARROW,
        SPIDER_EYE,
        SPIDER_SPAWN_EGG,
        SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE,
        SPLASH_POTION,
        SPONGE,
        SPORE_BLOSSOM,
        SPRUCE_BOAT,
        SPRUCE_BUTTON,
        SPRUCE_CHEST_BOAT,
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
        SPRUCE_WOOD,
        SPYGLASS,
        SQUID_SPAWN_EGG,
        STICK,
        STICKY_PISTON,
        STONE,
        STONE_AXE,
        STONE_BRICK_SLAB,
        STONE_BRICK_STAIRS,
        STONE_BRICK_WALL,
        STONE_BRICKS,
        STONE_BUTTON,
        STONE_HOE,
        STONE_PICKAXE,
        STONE_PRESSURE_PLATE,
        STONE_SHOVEL,
        STONE_SLAB,
        STONE_SPEAR,
        STONE_STAIRS,
        STONE_SWORD,
        STONECUTTER,
        STRAW_BED,
        STRAY_SPAWN_EGG,
        STRIDER_SPAWN_EGG,
        STRING,
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
        SUGAR,
        SUGAR_CANE,
        SULFUR,
        SULFUR_BRICK_SLAB,
        SULFUR_BRICK_STAIRS,
        SULFUR_BRICK_WALL,
        SULFUR_BRICKS,
        SULFUR_CUBE_BUCKET,
        SULFUR_CUBE_SPAWN_EGG,
        SULFUR_SLAB,
        SULFUR_SPIKE,
        SULFUR_STAIRS,
        SULFUR_WALL,
        SUNFLOWER,
        SUSPICIOUS_GRAVEL,
        SUSPICIOUS_SAND,
        SUSPICIOUS_STEW,
        SWAMP_HUT_MAP,
        SWEET_BERRIES,
        TADPOLE_BUCKET,
        TADPOLE_SPAWN_EGG,
        TAIGA_VILLAGE_MAP,
        TALL_DRY_GRASS,
        TALL_GRASS,
        TARGET,
        TERRACOTTA,
        TEST_BLOCK,
        TEST_INSTANCE_BLOCK,
        TIDE_ARMOR_TRIM_SMITHING_TEMPLATE,
        TINTED_GLASS,
        TIPPED_ARROW,
        TNT,
        TNT_MINECART,
        TORCH,
        TORCHFLOWER,
        TORCHFLOWER_SEEDS,
        TOTEM_OF_UNDYING,
        TRADER_LLAMA_SPAWN_EGG,
        TRAPPED_CHEST,
        TRIAL_KEY,
        TRIAL_SPAWNER,
        TRIDENT,
        TRIPWIRE_HOOK,
        TROPICAL_FISH,
        TROPICAL_FISH_BUCKET,
        TROPICAL_FISH_SPAWN_EGG,
        TUBE_CORAL,
        TUBE_CORAL_BLOCK,
        TUBE_CORAL_FAN,
        TUFF,
        TUFF_BRICK_SLAB,
        TUFF_BRICK_STAIRS,
        TUFF_BRICK_WALL,
        TUFF_BRICKS,
        TUFF_SLAB,
        TUFF_STAIRS,
        TUFF_WALL,
        TURTLE_EGG,
        TURTLE_HELMET,
        TURTLE_SCUTE,
        TURTLE_SPAWN_EGG,
        TWISTING_VINES,
        VAULT,
        VERDANT_FROGLIGHT,
        VEX_ARMOR_TRIM_SMITHING_TEMPLATE,
        VEX_SPAWN_EGG,
        VILLAGER_SPAWN_EGG,
        VINDICATOR_SPAWN_EGG,
        VINE,
        WANDERING_TRADER_SPAWN_EGG,
        WARD_ARMOR_TRIM_SMITHING_TEMPLATE,
        WARDEN_SPAWN_EGG,
        WARM_OCEAN_RUINS_MAP,
        WARPED_BUTTON,
        WARPED_DOOR,
        WARPED_FENCE,
        WARPED_FENCE_GATE,
        WARPED_FUNGUS,
        WARPED_FUNGUS_ON_A_STICK,
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
        WARPED_WART_BLOCK,
        WATER_BUCKET,
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
        WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE,
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
        WET_SPONGE,
        WHEAT,
        WHEAT_SEEDS,
        WHITE_BANNER,
        WHITE_BED,
        WHITE_BUNDLE,
        WHITE_CANDLE,
        WHITE_CARPET,
        WHITE_CONCRETE,
        WHITE_CONCRETE_POWDER,
        WHITE_CONCRETE_SLAB,
        WHITE_CONCRETE_STAIRS,
        WHITE_CUSHION,
        WHITE_DYE,
        WHITE_GLAZED_TERRACOTTA,
        WHITE_HARNESS,
        WHITE_SHULKER_BOX,
        WHITE_STAINED_GLASS,
        WHITE_STAINED_GLASS_PANE,
        WHITE_TERRACOTTA,
        WHITE_TULIP,
        WHITE_WOOL,
        WHITE_WOOL_SLAB,
        WHITE_WOOL_STAIRS,
        WILD_ARMOR_TRIM_SMITHING_TEMPLATE,
        WILDFLOWERS,
        WIND_CHARGE,
        WITCH_SPAWN_EGG,
        WITHER_ROSE,
        WITHER_SKELETON_SKULL,
        WITHER_SKELETON_SPAWN_EGG,
        WITHER_SPAWN_EGG,
        WOLF_ARMOR,
        WOLF_SPAWN_EGG,
        WOODEN_AXE,
        WOODEN_HOE,
        WOODEN_PICKAXE,
        WOODEN_SHOVEL,
        WOODEN_SPEAR,
        WOODEN_SWORD,
        WOODLAND_MANSION_MAP,
        WRITABLE_BOOK,
        WRITTEN_BOOK,
        YELLOW_BANNER,
        YELLOW_BED,
        YELLOW_BUNDLE,
        YELLOW_CANDLE,
        YELLOW_CARPET,
        YELLOW_CONCRETE,
        YELLOW_CONCRETE_POWDER,
        YELLOW_CONCRETE_SLAB,
        YELLOW_CONCRETE_STAIRS,
        YELLOW_CUSHION,
        YELLOW_DYE,
        YELLOW_GLAZED_TERRACOTTA,
        YELLOW_HARNESS,
        YELLOW_POPLAR_LEAVES,
        YELLOW_SHULKER_BOX,
        YELLOW_STAINED_GLASS,
        YELLOW_STAINED_GLASS_PANE,
        YELLOW_TERRACOTTA,
        YELLOW_WOOL,
        YELLOW_WOOL_SLAB,
        YELLOW_WOOL_STAIRS,
        ZOGLIN_SPAWN_EGG,
        ZOMBIE_HEAD,
        ZOMBIE_HORSE_SPAWN_EGG,
        ZOMBIE_NAUTILUS_SPAWN_EGG,
        ZOMBIE_SPAWN_EGG,
        ZOMBIE_VILLAGER_SPAWN_EGG,
        ZOMBIFIED_PIGLIN_SPAWN_EGG
    );

    private ItemKeys() {
        throw new UnsupportedOperationException("ItemKeys cannot be instantiated.");
    }

    private static TypedKey<Item> create(@KeyPattern final String value) {
        return TypedKey.create(RegistryKey.ITEM, value);
    }

    /**
     * Returns a stream containing all keys declared by this class.
     *
     * @return a stream of registry keys
     */
    public static Stream<TypedKey<Item>> values() {
        return VALUES.stream();
    }
}
