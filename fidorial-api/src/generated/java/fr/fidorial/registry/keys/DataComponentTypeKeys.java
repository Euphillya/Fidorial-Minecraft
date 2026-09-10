package fr.fidorial.registry.keys;

import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.DataComponentType;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.adventure.key.KeyPattern;

/**
 * Typed keys for entries in the {@code minecraft:data_component_type} registry.
 */
public final class DataComponentTypeKeys {
    /**
     * Key for {@code minecraft:additional_trade_cost}.
     */
    public static final TypedKey<DataComponentType> ADDITIONAL_TRADE_COST = create("additional_trade_cost");

    /**
     * Key for {@code minecraft:attack_animation}.
     */
    public static final TypedKey<DataComponentType> ATTACK_ANIMATION = create("attack_animation");

    /**
     * Key for {@code minecraft:attack_range}.
     */
    public static final TypedKey<DataComponentType> ATTACK_RANGE = create("attack_range");

    /**
     * Key for {@code minecraft:attribute_modifiers}.
     */
    public static final TypedKey<DataComponentType> ATTRIBUTE_MODIFIERS = create("attribute_modifiers");

    /**
     * Key for {@code minecraft:axolotl/variant}.
     */
    public static final TypedKey<DataComponentType> AXOLOTL_VARIANT = create("axolotl/variant");

    /**
     * Key for {@code minecraft:banner_patterns}.
     */
    public static final TypedKey<DataComponentType> BANNER_PATTERNS = create("banner_patterns");

    /**
     * Key for {@code minecraft:base_color}.
     */
    public static final TypedKey<DataComponentType> BASE_COLOR = create("base_color");

    /**
     * Key for {@code minecraft:bees}.
     */
    public static final TypedKey<DataComponentType> BEES = create("bees");

    /**
     * Key for {@code minecraft:block_entity_data}.
     */
    public static final TypedKey<DataComponentType> BLOCK_ENTITY_DATA = create("block_entity_data");

    /**
     * Key for {@code minecraft:block_state}.
     */
    public static final TypedKey<DataComponentType> BLOCK_STATE = create("block_state");

    /**
     * Key for {@code minecraft:block_transformer}.
     */
    public static final TypedKey<DataComponentType> BLOCK_TRANSFORMER = create("block_transformer");

    /**
     * Key for {@code minecraft:blocks_attacks}.
     */
    public static final TypedKey<DataComponentType> BLOCKS_ATTACKS = create("blocks_attacks");

    /**
     * Key for {@code minecraft:break_sound}.
     */
    public static final TypedKey<DataComponentType> BREAK_SOUND = create("break_sound");

    /**
     * Key for {@code minecraft:brewing_fuel}.
     */
    public static final TypedKey<DataComponentType> BREWING_FUEL = create("brewing_fuel");

    /**
     * Key for {@code minecraft:bucket_entity_data}.
     */
    public static final TypedKey<DataComponentType> BUCKET_ENTITY_DATA = create("bucket_entity_data");

    /**
     * Key for {@code minecraft:bundle_contents}.
     */
    public static final TypedKey<DataComponentType> BUNDLE_CONTENTS = create("bundle_contents");

    /**
     * Key for {@code minecraft:can_break}.
     */
    public static final TypedKey<DataComponentType> CAN_BREAK = create("can_break");

    /**
     * Key for {@code minecraft:can_place_on}.
     */
    public static final TypedKey<DataComponentType> CAN_PLACE_ON = create("can_place_on");

    /**
     * Key for {@code minecraft:cat/collar}.
     */
    public static final TypedKey<DataComponentType> CAT_COLLAR = create("cat/collar");

    /**
     * Key for {@code minecraft:cat/sound_variant}.
     */
    public static final TypedKey<DataComponentType> CAT_SOUND_VARIANT = create("cat/sound_variant");

    /**
     * Key for {@code minecraft:cat/variant}.
     */
    public static final TypedKey<DataComponentType> CAT_VARIANT = create("cat/variant");

    /**
     * Key for {@code minecraft:charged_projectiles}.
     */
    public static final TypedKey<DataComponentType> CHARGED_PROJECTILES = create("charged_projectiles");

    /**
     * Key for {@code minecraft:chicken/sound_variant}.
     */
    public static final TypedKey<DataComponentType> CHICKEN_SOUND_VARIANT = create("chicken/sound_variant");

    /**
     * Key for {@code minecraft:chicken/variant}.
     */
    public static final TypedKey<DataComponentType> CHICKEN_VARIANT = create("chicken/variant");

    /**
     * Key for {@code minecraft:compostable}.
     */
    public static final TypedKey<DataComponentType> COMPOSTABLE = create("compostable");

    /**
     * Key for {@code minecraft:consumable}.
     */
    public static final TypedKey<DataComponentType> CONSUMABLE = create("consumable");

    /**
     * Key for {@code minecraft:container}.
     */
    public static final TypedKey<DataComponentType> CONTAINER = create("container");

    /**
     * Key for {@code minecraft:container_loot}.
     */
    public static final TypedKey<DataComponentType> CONTAINER_LOOT = create("container_loot");

    /**
     * Key for {@code minecraft:cooking_fuel}.
     */
    public static final TypedKey<DataComponentType> COOKING_FUEL = create("cooking_fuel");

    /**
     * Key for {@code minecraft:cow/sound_variant}.
     */
    public static final TypedKey<DataComponentType> COW_SOUND_VARIANT = create("cow/sound_variant");

    /**
     * Key for {@code minecraft:cow/variant}.
     */
    public static final TypedKey<DataComponentType> COW_VARIANT = create("cow/variant");

    /**
     * Key for {@code minecraft:creative_slot_lock}.
     */
    public static final TypedKey<DataComponentType> CREATIVE_SLOT_LOCK = create("creative_slot_lock");

    /**
     * Key for {@code minecraft:cushion/color}.
     */
    public static final TypedKey<DataComponentType> CUSHION_COLOR = create("cushion/color");

    /**
     * Key for {@code minecraft:custom_data}.
     */
    public static final TypedKey<DataComponentType> CUSTOM_DATA = create("custom_data");

    /**
     * Key for {@code minecraft:custom_model_data}.
     */
    public static final TypedKey<DataComponentType> CUSTOM_MODEL_DATA = create("custom_model_data");

    /**
     * Key for {@code minecraft:custom_name}.
     */
    public static final TypedKey<DataComponentType> CUSTOM_NAME = create("custom_name");

    /**
     * Key for {@code minecraft:damage}.
     */
    public static final TypedKey<DataComponentType> DAMAGE = create("damage");

    /**
     * Key for {@code minecraft:damage_resistant}.
     */
    public static final TypedKey<DataComponentType> DAMAGE_RESISTANT = create("damage_resistant");

    /**
     * Key for {@code minecraft:damage_type}.
     */
    public static final TypedKey<DataComponentType> DAMAGE_TYPE = create("damage_type");

    /**
     * Key for {@code minecraft:death_protection}.
     */
    public static final TypedKey<DataComponentType> DEATH_PROTECTION = create("death_protection");

    /**
     * Key for {@code minecraft:debug_stick_state}.
     */
    public static final TypedKey<DataComponentType> DEBUG_STICK_STATE = create("debug_stick_state");

    /**
     * Key for {@code minecraft:dye}.
     */
    public static final TypedKey<DataComponentType> DYE = create("dye");

    /**
     * Key for {@code minecraft:dyed_color}.
     */
    public static final TypedKey<DataComponentType> DYED_COLOR = create("dyed_color");

    /**
     * Key for {@code minecraft:enchantable}.
     */
    public static final TypedKey<DataComponentType> ENCHANTABLE = create("enchantable");

    /**
     * Key for {@code minecraft:enchantment_glint_override}.
     */
    public static final TypedKey<DataComponentType> ENCHANTMENT_GLINT_OVERRIDE = create("enchantment_glint_override");

    /**
     * Key for {@code minecraft:enchantments}.
     */
    public static final TypedKey<DataComponentType> ENCHANTMENTS = create("enchantments");

    /**
     * Key for {@code minecraft:entity_data}.
     */
    public static final TypedKey<DataComponentType> ENTITY_DATA = create("entity_data");

    /**
     * Key for {@code minecraft:equippable}.
     */
    public static final TypedKey<DataComponentType> EQUIPPABLE = create("equippable");

    /**
     * Key for {@code minecraft:firework_explosion}.
     */
    public static final TypedKey<DataComponentType> FIREWORK_EXPLOSION = create("firework_explosion");

    /**
     * Key for {@code minecraft:fireworks}.
     */
    public static final TypedKey<DataComponentType> FIREWORKS = create("fireworks");

    /**
     * Key for {@code minecraft:food}.
     */
    public static final TypedKey<DataComponentType> FOOD = create("food");

    /**
     * Key for {@code minecraft:fox/variant}.
     */
    public static final TypedKey<DataComponentType> FOX_VARIANT = create("fox/variant");

    /**
     * Key for {@code minecraft:frog/variant}.
     */
    public static final TypedKey<DataComponentType> FROG_VARIANT = create("frog/variant");

    /**
     * Key for {@code minecraft:glider}.
     */
    public static final TypedKey<DataComponentType> GLIDER = create("glider");

    /**
     * Key for {@code minecraft:horse/variant}.
     */
    public static final TypedKey<DataComponentType> HORSE_VARIANT = create("horse/variant");

    /**
     * Key for {@code minecraft:instrument}.
     */
    public static final TypedKey<DataComponentType> INSTRUMENT = create("instrument");

    /**
     * Key for {@code minecraft:intangible_projectile}.
     */
    public static final TypedKey<DataComponentType> INTANGIBLE_PROJECTILE = create("intangible_projectile");

    /**
     * Key for {@code minecraft:interact_animation}.
     */
    public static final TypedKey<DataComponentType> INTERACT_ANIMATION = create("interact_animation");

    /**
     * Key for {@code minecraft:item_model}.
     */
    public static final TypedKey<DataComponentType> ITEM_MODEL = create("item_model");

    /**
     * Key for {@code minecraft:item_name}.
     */
    public static final TypedKey<DataComponentType> ITEM_NAME = create("item_name");

    /**
     * Key for {@code minecraft:jukebox_playable}.
     */
    public static final TypedKey<DataComponentType> JUKEBOX_PLAYABLE = create("jukebox_playable");

    /**
     * Key for {@code minecraft:kinetic_weapon}.
     */
    public static final TypedKey<DataComponentType> KINETIC_WEAPON = create("kinetic_weapon");

    /**
     * Key for {@code minecraft:llama/variant}.
     */
    public static final TypedKey<DataComponentType> LLAMA_VARIANT = create("llama/variant");

    /**
     * Key for {@code minecraft:lock}.
     */
    public static final TypedKey<DataComponentType> LOCK = create("lock");

    /**
     * Key for {@code minecraft:lodestone_tracker}.
     */
    public static final TypedKey<DataComponentType> LODESTONE_TRACKER = create("lodestone_tracker");

    /**
     * Key for {@code minecraft:lore}.
     */
    public static final TypedKey<DataComponentType> LORE = create("lore");

    /**
     * Key for {@code minecraft:map_decorations}.
     */
    public static final TypedKey<DataComponentType> MAP_DECORATIONS = create("map_decorations");

    /**
     * Key for {@code minecraft:map_id}.
     */
    public static final TypedKey<DataComponentType> MAP_ID = create("map_id");

    /**
     * Key for {@code minecraft:map_post_processing}.
     */
    public static final TypedKey<DataComponentType> MAP_POST_PROCESSING = create("map_post_processing");

    /**
     * Key for {@code minecraft:max_damage}.
     */
    public static final TypedKey<DataComponentType> MAX_DAMAGE = create("max_damage");

    /**
     * Key for {@code minecraft:max_stack_size}.
     */
    public static final TypedKey<DataComponentType> MAX_STACK_SIZE = create("max_stack_size");

    /**
     * Key for {@code minecraft:minimum_attack_charge}.
     */
    public static final TypedKey<DataComponentType> MINIMUM_ATTACK_CHARGE = create("minimum_attack_charge");

    /**
     * Key for {@code minecraft:mob_visibility}.
     */
    public static final TypedKey<DataComponentType> MOB_VISIBILITY = create("mob_visibility");

    /**
     * Key for {@code minecraft:mooshroom/variant}.
     */
    public static final TypedKey<DataComponentType> MOOSHROOM_VARIANT = create("mooshroom/variant");

    /**
     * Key for {@code minecraft:note_block_sound}.
     */
    public static final TypedKey<DataComponentType> NOTE_BLOCK_SOUND = create("note_block_sound");

    /**
     * Key for {@code minecraft:ominous_bottle_amplifier}.
     */
    public static final TypedKey<DataComponentType> OMINOUS_BOTTLE_AMPLIFIER = create("ominous_bottle_amplifier");

    /**
     * Key for {@code minecraft:painting/variant}.
     */
    public static final TypedKey<DataComponentType> PAINTING_VARIANT = create("painting/variant");

    /**
     * Key for {@code minecraft:parrot/variant}.
     */
    public static final TypedKey<DataComponentType> PARROT_VARIANT = create("parrot/variant");

    /**
     * Key for {@code minecraft:piercing_weapon}.
     */
    public static final TypedKey<DataComponentType> PIERCING_WEAPON = create("piercing_weapon");

    /**
     * Key for {@code minecraft:pig/sound_variant}.
     */
    public static final TypedKey<DataComponentType> PIG_SOUND_VARIANT = create("pig/sound_variant");

    /**
     * Key for {@code minecraft:pig/variant}.
     */
    public static final TypedKey<DataComponentType> PIG_VARIANT = create("pig/variant");

    /**
     * Key for {@code minecraft:pot_decorations}.
     */
    public static final TypedKey<DataComponentType> POT_DECORATIONS = create("pot_decorations");

    /**
     * Key for {@code minecraft:potion_contents}.
     */
    public static final TypedKey<DataComponentType> POTION_CONTENTS = create("potion_contents");

    /**
     * Key for {@code minecraft:potion_duration_scale}.
     */
    public static final TypedKey<DataComponentType> POTION_DURATION_SCALE = create("potion_duration_scale");

    /**
     * Key for {@code minecraft:profile}.
     */
    public static final TypedKey<DataComponentType> PROFILE = create("profile");

    /**
     * Key for {@code minecraft:provides_banner_patterns}.
     */
    public static final TypedKey<DataComponentType> PROVIDES_BANNER_PATTERNS = create("provides_banner_patterns");

    /**
     * Key for {@code minecraft:provides_pottery_pattern}.
     */
    public static final TypedKey<DataComponentType> PROVIDES_POTTERY_PATTERN = create("provides_pottery_pattern");

    /**
     * Key for {@code minecraft:provides_trim_material}.
     */
    public static final TypedKey<DataComponentType> PROVIDES_TRIM_MATERIAL = create("provides_trim_material");

    /**
     * Key for {@code minecraft:rabbit/variant}.
     */
    public static final TypedKey<DataComponentType> RABBIT_VARIANT = create("rabbit/variant");

    /**
     * Key for {@code minecraft:rarity}.
     */
    public static final TypedKey<DataComponentType> RARITY = create("rarity");

    /**
     * Key for {@code minecraft:recipes}.
     */
    public static final TypedKey<DataComponentType> RECIPES = create("recipes");

    /**
     * Key for {@code minecraft:repair_cost}.
     */
    public static final TypedKey<DataComponentType> REPAIR_COST = create("repair_cost");

    /**
     * Key for {@code minecraft:repairable}.
     */
    public static final TypedKey<DataComponentType> REPAIRABLE = create("repairable");

    /**
     * Key for {@code minecraft:salmon/size}.
     */
    public static final TypedKey<DataComponentType> SALMON_SIZE = create("salmon/size");

    /**
     * Key for {@code minecraft:sheep/color}.
     */
    public static final TypedKey<DataComponentType> SHEEP_COLOR = create("sheep/color");

    /**
     * Key for {@code minecraft:shulker/color}.
     */
    public static final TypedKey<DataComponentType> SHULKER_COLOR = create("shulker/color");

    /**
     * Key for {@code minecraft:sign_text_back}.
     */
    public static final TypedKey<DataComponentType> SIGN_TEXT_BACK = create("sign_text_back");

    /**
     * Key for {@code minecraft:sign_text_front}.
     */
    public static final TypedKey<DataComponentType> SIGN_TEXT_FRONT = create("sign_text_front");

    /**
     * Key for {@code minecraft:stored_enchantments}.
     */
    public static final TypedKey<DataComponentType> STORED_ENCHANTMENTS = create("stored_enchantments");

    /**
     * Key for {@code minecraft:sulfur_cube_content}.
     */
    public static final TypedKey<DataComponentType> SULFUR_CUBE_CONTENT = create("sulfur_cube_content");

    /**
     * Key for {@code minecraft:suspicious_stew_effects}.
     */
    public static final TypedKey<DataComponentType> SUSPICIOUS_STEW_EFFECTS = create("suspicious_stew_effects");

    /**
     * Key for {@code minecraft:tool}.
     */
    public static final TypedKey<DataComponentType> TOOL = create("tool");

    /**
     * Key for {@code minecraft:tooltip_display}.
     */
    public static final TypedKey<DataComponentType> TOOLTIP_DISPLAY = create("tooltip_display");

    /**
     * Key for {@code minecraft:tooltip_style}.
     */
    public static final TypedKey<DataComponentType> TOOLTIP_STYLE = create("tooltip_style");

    /**
     * Key for {@code minecraft:trim}.
     */
    public static final TypedKey<DataComponentType> TRIM = create("trim");

    /**
     * Key for {@code minecraft:tropical_fish/base_color}.
     */
    public static final TypedKey<DataComponentType> TROPICAL_FISH_BASE_COLOR = create("tropical_fish/base_color");

    /**
     * Key for {@code minecraft:tropical_fish/pattern}.
     */
    public static final TypedKey<DataComponentType> TROPICAL_FISH_PATTERN = create("tropical_fish/pattern");

    /**
     * Key for {@code minecraft:tropical_fish/pattern_color}.
     */
    public static final TypedKey<DataComponentType> TROPICAL_FISH_PATTERN_COLOR = create("tropical_fish/pattern_color");

    /**
     * Key for {@code minecraft:unbreakable}.
     */
    public static final TypedKey<DataComponentType> UNBREAKABLE = create("unbreakable");

    /**
     * Key for {@code minecraft:use_cooldown}.
     */
    public static final TypedKey<DataComponentType> USE_COOLDOWN = create("use_cooldown");

    /**
     * Key for {@code minecraft:use_effects}.
     */
    public static final TypedKey<DataComponentType> USE_EFFECTS = create("use_effects");

    /**
     * Key for {@code minecraft:use_remainder}.
     */
    public static final TypedKey<DataComponentType> USE_REMAINDER = create("use_remainder");

    /**
     * Key for {@code minecraft:villager/variant}.
     */
    public static final TypedKey<DataComponentType> VILLAGER_VARIANT = create("villager/variant");

    /**
     * Key for {@code minecraft:villager_food}.
     */
    public static final TypedKey<DataComponentType> VILLAGER_FOOD = create("villager_food");

    /**
     * Key for {@code minecraft:waxed}.
     */
    public static final TypedKey<DataComponentType> WAXED = create("waxed");

    /**
     * Key for {@code minecraft:weapon}.
     */
    public static final TypedKey<DataComponentType> WEAPON = create("weapon");

    /**
     * Key for {@code minecraft:wolf/collar}.
     */
    public static final TypedKey<DataComponentType> WOLF_COLLAR = create("wolf/collar");

    /**
     * Key for {@code minecraft:wolf/sound_variant}.
     */
    public static final TypedKey<DataComponentType> WOLF_SOUND_VARIANT = create("wolf/sound_variant");

    /**
     * Key for {@code minecraft:wolf/variant}.
     */
    public static final TypedKey<DataComponentType> WOLF_VARIANT = create("wolf/variant");

    /**
     * Key for {@code minecraft:writable_book_content}.
     */
    public static final TypedKey<DataComponentType> WRITABLE_BOOK_CONTENT = create("writable_book_content");

    /**
     * Key for {@code minecraft:written_book_content}.
     */
    public static final TypedKey<DataComponentType> WRITTEN_BOOK_CONTENT = create("written_book_content");

    /**
     * Key for {@code minecraft:zombie_nautilus/variant}.
     */
    public static final TypedKey<DataComponentType> ZOMBIE_NAUTILUS_VARIANT = create("zombie_nautilus/variant");

    private static final List<TypedKey<DataComponentType>> VALUES = List.of(
        ADDITIONAL_TRADE_COST,
        ATTACK_ANIMATION,
        ATTACK_RANGE,
        ATTRIBUTE_MODIFIERS,
        AXOLOTL_VARIANT,
        BANNER_PATTERNS,
        BASE_COLOR,
        BEES,
        BLOCK_ENTITY_DATA,
        BLOCK_STATE,
        BLOCK_TRANSFORMER,
        BLOCKS_ATTACKS,
        BREAK_SOUND,
        BREWING_FUEL,
        BUCKET_ENTITY_DATA,
        BUNDLE_CONTENTS,
        CAN_BREAK,
        CAN_PLACE_ON,
        CAT_COLLAR,
        CAT_SOUND_VARIANT,
        CAT_VARIANT,
        CHARGED_PROJECTILES,
        CHICKEN_SOUND_VARIANT,
        CHICKEN_VARIANT,
        COMPOSTABLE,
        CONSUMABLE,
        CONTAINER,
        CONTAINER_LOOT,
        COOKING_FUEL,
        COW_SOUND_VARIANT,
        COW_VARIANT,
        CREATIVE_SLOT_LOCK,
        CUSHION_COLOR,
        CUSTOM_DATA,
        CUSTOM_MODEL_DATA,
        CUSTOM_NAME,
        DAMAGE,
        DAMAGE_RESISTANT,
        DAMAGE_TYPE,
        DEATH_PROTECTION,
        DEBUG_STICK_STATE,
        DYE,
        DYED_COLOR,
        ENCHANTABLE,
        ENCHANTMENT_GLINT_OVERRIDE,
        ENCHANTMENTS,
        ENTITY_DATA,
        EQUIPPABLE,
        FIREWORK_EXPLOSION,
        FIREWORKS,
        FOOD,
        FOX_VARIANT,
        FROG_VARIANT,
        GLIDER,
        HORSE_VARIANT,
        INSTRUMENT,
        INTANGIBLE_PROJECTILE,
        INTERACT_ANIMATION,
        ITEM_MODEL,
        ITEM_NAME,
        JUKEBOX_PLAYABLE,
        KINETIC_WEAPON,
        LLAMA_VARIANT,
        LOCK,
        LODESTONE_TRACKER,
        LORE,
        MAP_DECORATIONS,
        MAP_ID,
        MAP_POST_PROCESSING,
        MAX_DAMAGE,
        MAX_STACK_SIZE,
        MINIMUM_ATTACK_CHARGE,
        MOB_VISIBILITY,
        MOOSHROOM_VARIANT,
        NOTE_BLOCK_SOUND,
        OMINOUS_BOTTLE_AMPLIFIER,
        PAINTING_VARIANT,
        PARROT_VARIANT,
        PIERCING_WEAPON,
        PIG_SOUND_VARIANT,
        PIG_VARIANT,
        POT_DECORATIONS,
        POTION_CONTENTS,
        POTION_DURATION_SCALE,
        PROFILE,
        PROVIDES_BANNER_PATTERNS,
        PROVIDES_POTTERY_PATTERN,
        PROVIDES_TRIM_MATERIAL,
        RABBIT_VARIANT,
        RARITY,
        RECIPES,
        REPAIR_COST,
        REPAIRABLE,
        SALMON_SIZE,
        SHEEP_COLOR,
        SHULKER_COLOR,
        SIGN_TEXT_BACK,
        SIGN_TEXT_FRONT,
        STORED_ENCHANTMENTS,
        SULFUR_CUBE_CONTENT,
        SUSPICIOUS_STEW_EFFECTS,
        TOOL,
        TOOLTIP_DISPLAY,
        TOOLTIP_STYLE,
        TRIM,
        TROPICAL_FISH_BASE_COLOR,
        TROPICAL_FISH_PATTERN,
        TROPICAL_FISH_PATTERN_COLOR,
        UNBREAKABLE,
        USE_COOLDOWN,
        USE_EFFECTS,
        USE_REMAINDER,
        VILLAGER_VARIANT,
        VILLAGER_FOOD,
        WAXED,
        WEAPON,
        WOLF_COLLAR,
        WOLF_SOUND_VARIANT,
        WOLF_VARIANT,
        WRITABLE_BOOK_CONTENT,
        WRITTEN_BOOK_CONTENT,
        ZOMBIE_NAUTILUS_VARIANT
    );

    private DataComponentTypeKeys() {
        throw new UnsupportedOperationException("DataComponentTypeKeys cannot be instantiated.");
    }

    private static TypedKey<DataComponentType> create(@KeyPattern final String value) {
        return TypedKey.create(RegistryKey.DATA_COMPONENT_TYPE, value);
    }

    /**
     * Returns a stream containing all keys declared by this class.
     *
     * @return a stream of registry keys
     */
    public static Stream<TypedKey<DataComponentType>> values() {
        return VALUES.stream();
    }
}
