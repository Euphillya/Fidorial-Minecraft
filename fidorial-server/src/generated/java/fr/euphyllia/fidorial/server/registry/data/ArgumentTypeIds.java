package fr.euphyllia.fidorial.server.registry.data;

import java.util.Map;
import net.kyori.adventure.key.Key;

/**
 * Network IDs for entries in the {@code minecraft:command_argument_type} registry.
 *
 * <p>Generated from Mojang's registry report; do not edit.</p>
 */
public interface ArgumentTypeIds {
    /**
     * Returned by {@link #id(Key)} when the identifier is unknown.
     */
    int UNKNOWN = -1;

    /**
     * {@code brigadier:bool}
     */
    int BOOL_ARGUMENT_ID = 0;

    /**
     * {@code brigadier:double}
     */
    int DOUBLE_ARGUMENT_ID = 2;

    /**
     * {@code brigadier:float}
     */
    int FLOAT_ARGUMENT_ID = 1;

    /**
     * {@code brigadier:integer}
     */
    int INTEGER_ARGUMENT_ID = 3;

    /**
     * {@code brigadier:long}
     */
    int LONG_ARGUMENT_ID = 4;

    /**
     * {@code brigadier:string}
     */
    int STRING_ARGUMENT_ID = 5;

    /**
     * {@code minecraft:angle}
     */
    int ANGLE_ARGUMENT_ID = 28;

    /**
     * {@code minecraft:block_pos}
     */
    int BLOCK_POS_ARGUMENT_ID = 8;

    /**
     * {@code minecraft:block_predicate}
     */
    int BLOCK_PREDICATE_ARGUMENT_ID = 13;

    /**
     * {@code minecraft:block_state}
     */
    int BLOCK_STATE_ARGUMENT_ID = 12;

    /**
     * {@code minecraft:column_pos}
     */
    int COLUMN_POS_ARGUMENT_ID = 9;

    /**
     * {@code minecraft:component}
     */
    int COMPONENT_ARGUMENT_ID = 18;

    /**
     * {@code minecraft:context_float_provider}
     */
    int CONTEXT_FLOAT_PROVIDER_ARGUMENT_ID = 55;

    /**
     * {@code minecraft:context_int_provider}
     */
    int CONTEXT_INT_PROVIDER_ARGUMENT_ID = 56;

    /**
     * {@code minecraft:dialog}
     */
    int DIALOG_ARGUMENT_ID = 58;

    /**
     * {@code minecraft:dimension}
     */
    int DIMENSION_ARGUMENT_ID = 41;

    /**
     * {@code minecraft:entity}
     */
    int ENTITY_ARGUMENT_ID = 6;

    /**
     * {@code minecraft:entity_anchor}
     */
    int ENTITY_ANCHOR_ARGUMENT_ID = 38;

    /**
     * {@code minecraft:feature}
     */
    int FEATURE_ARGUMENT_ID = 59;

    /**
     * {@code minecraft:float_range}
     */
    int FLOAT_RANGE_ARGUMENT_ID = 40;

    /**
     * {@code minecraft:function}
     */
    int FUNCTION_ARGUMENT_ID = 37;

    /**
     * {@code minecraft:game_profile}
     */
    int GAME_PROFILE_ARGUMENT_ID = 7;

    /**
     * {@code minecraft:gamemode}
     */
    int GAMEMODE_ARGUMENT_ID = 42;

    /**
     * {@code minecraft:heightmap}
     */
    int HEIGHTMAP_ARGUMENT_ID = 51;

    /**
     * {@code minecraft:hex_color}
     */
    int HEX_COLOR_ARGUMENT_ID = 17;

    /**
     * {@code minecraft:int_range}
     */
    int INT_RANGE_ARGUMENT_ID = 39;

    /**
     * {@code minecraft:item_predicate}
     */
    int ITEM_PREDICATE_ARGUMENT_ID = 15;

    /**
     * {@code minecraft:item_slot}
     */
    int ITEM_SLOT_ARGUMENT_ID = 34;

    /**
     * {@code minecraft:item_slots}
     */
    int ITEM_SLOTS_ARGUMENT_ID = 35;

    /**
     * {@code minecraft:item_stack}
     */
    int ITEM_STACK_ARGUMENT_ID = 14;

    /**
     * {@code minecraft:loot_modifier}
     */
    int LOOT_MODIFIER_ARGUMENT_ID = 54;

    /**
     * {@code minecraft:loot_predicate}
     */
    int LOOT_PREDICATE_ARGUMENT_ID = 53;

    /**
     * {@code minecraft:loot_table}
     */
    int LOOT_TABLE_ARGUMENT_ID = 52;

    /**
     * {@code minecraft:message}
     */
    int MESSAGE_ARGUMENT_ID = 20;

    /**
     * {@code minecraft:nbt_compound_tag}
     */
    int NBT_COMPOUND_TAG_ARGUMENT_ID = 21;

    /**
     * {@code minecraft:nbt_path}
     */
    int NBT_PATH_ARGUMENT_ID = 23;

    /**
     * {@code minecraft:nbt_tag}
     */
    int NBT_TAG_ARGUMENT_ID = 22;

    /**
     * {@code minecraft:objective}
     */
    int OBJECTIVE_ARGUMENT_ID = 24;

    /**
     * {@code minecraft:objective_criteria}
     */
    int OBJECTIVE_CRITERIA_ARGUMENT_ID = 25;

    /**
     * {@code minecraft:operation}
     */
    int OPERATION_ARGUMENT_ID = 26;

    /**
     * {@code minecraft:particle}
     */
    int PARTICLE_ARGUMENT_ID = 27;

    /**
     * {@code minecraft:resource}
     */
    int RESOURCE_ARGUMENT_ID = 46;

    /**
     * {@code minecraft:resource_key}
     */
    int RESOURCE_KEY_ARGUMENT_ID = 47;

    /**
     * {@code minecraft:resource_location}
     */
    int RESOURCE_LOCATION_ARGUMENT_ID = 36;

    /**
     * {@code minecraft:resource_or_tag}
     */
    int RESOURCE_OR_TAG_ARGUMENT_ID = 44;

    /**
     * {@code minecraft:resource_or_tag_key}
     */
    int RESOURCE_OR_TAG_KEY_ARGUMENT_ID = 45;

    /**
     * {@code minecraft:resource_selector}
     */
    int RESOURCE_SELECTOR_ARGUMENT_ID = 48;

    /**
     * {@code minecraft:rotation}
     */
    int ROTATION_ARGUMENT_ID = 29;

    /**
     * {@code minecraft:score_holder}
     */
    int SCORE_HOLDER_ARGUMENT_ID = 31;

    /**
     * {@code minecraft:scoreboard_slot}
     */
    int SCOREBOARD_SLOT_ARGUMENT_ID = 30;

    /**
     * {@code minecraft:slot_source}
     */
    int SLOT_SOURCE_ARGUMENT_ID = 57;

    /**
     * {@code minecraft:style}
     */
    int STYLE_ARGUMENT_ID = 19;

    /**
     * {@code minecraft:swing_animation}
     */
    int SWING_ANIMATION_ARGUMENT_ID = 60;

    /**
     * {@code minecraft:swizzle}
     */
    int SWIZZLE_ARGUMENT_ID = 32;

    /**
     * {@code minecraft:team}
     */
    int TEAM_ARGUMENT_ID = 33;

    /**
     * {@code minecraft:team_color}
     */
    int TEAM_COLOR_ARGUMENT_ID = 16;

    /**
     * {@code minecraft:template_mirror}
     */
    int TEMPLATE_MIRROR_ARGUMENT_ID = 49;

    /**
     * {@code minecraft:template_rotation}
     */
    int TEMPLATE_ROTATION_ARGUMENT_ID = 50;

    /**
     * {@code minecraft:time}
     */
    int TIME_ARGUMENT_ID = 43;

    /**
     * {@code minecraft:uuid}
     */
    int UUID_ARGUMENT_ID = 61;

    /**
     * {@code minecraft:vec2}
     */
    int VEC2_ARGUMENT_ID = 11;

    /**
     * {@code minecraft:vec3}
     */
    int VEC3_ARGUMENT_ID = 10;

    /**
     * Immutable identifier to protocol ID lookup table.
     */
    Map<Key, Integer> BY_IDENTIFIER = Map.ofEntries(
        Map.entry(Key.key("brigadier", "bool"), BOOL_ARGUMENT_ID),
        Map.entry(Key.key("brigadier", "double"), DOUBLE_ARGUMENT_ID),
        Map.entry(Key.key("brigadier", "float"), FLOAT_ARGUMENT_ID),
        Map.entry(Key.key("brigadier", "integer"), INTEGER_ARGUMENT_ID),
        Map.entry(Key.key("brigadier", "long"), LONG_ARGUMENT_ID),
        Map.entry(Key.key("brigadier", "string"), STRING_ARGUMENT_ID),
        Map.entry(Key.key("angle"), ANGLE_ARGUMENT_ID),
        Map.entry(Key.key("block_pos"), BLOCK_POS_ARGUMENT_ID),
        Map.entry(Key.key("block_predicate"), BLOCK_PREDICATE_ARGUMENT_ID),
        Map.entry(Key.key("block_state"), BLOCK_STATE_ARGUMENT_ID),
        Map.entry(Key.key("column_pos"), COLUMN_POS_ARGUMENT_ID),
        Map.entry(Key.key("component"), COMPONENT_ARGUMENT_ID),
        Map.entry(Key.key("context_float_provider"), CONTEXT_FLOAT_PROVIDER_ARGUMENT_ID),
        Map.entry(Key.key("context_int_provider"), CONTEXT_INT_PROVIDER_ARGUMENT_ID),
        Map.entry(Key.key("dialog"), DIALOG_ARGUMENT_ID),
        Map.entry(Key.key("dimension"), DIMENSION_ARGUMENT_ID),
        Map.entry(Key.key("entity"), ENTITY_ARGUMENT_ID),
        Map.entry(Key.key("entity_anchor"), ENTITY_ANCHOR_ARGUMENT_ID),
        Map.entry(Key.key("feature"), FEATURE_ARGUMENT_ID),
        Map.entry(Key.key("float_range"), FLOAT_RANGE_ARGUMENT_ID),
        Map.entry(Key.key("function"), FUNCTION_ARGUMENT_ID),
        Map.entry(Key.key("game_profile"), GAME_PROFILE_ARGUMENT_ID),
        Map.entry(Key.key("gamemode"), GAMEMODE_ARGUMENT_ID),
        Map.entry(Key.key("heightmap"), HEIGHTMAP_ARGUMENT_ID),
        Map.entry(Key.key("hex_color"), HEX_COLOR_ARGUMENT_ID),
        Map.entry(Key.key("int_range"), INT_RANGE_ARGUMENT_ID),
        Map.entry(Key.key("item_predicate"), ITEM_PREDICATE_ARGUMENT_ID),
        Map.entry(Key.key("item_slot"), ITEM_SLOT_ARGUMENT_ID),
        Map.entry(Key.key("item_slots"), ITEM_SLOTS_ARGUMENT_ID),
        Map.entry(Key.key("item_stack"), ITEM_STACK_ARGUMENT_ID),
        Map.entry(Key.key("loot_modifier"), LOOT_MODIFIER_ARGUMENT_ID),
        Map.entry(Key.key("loot_predicate"), LOOT_PREDICATE_ARGUMENT_ID),
        Map.entry(Key.key("loot_table"), LOOT_TABLE_ARGUMENT_ID),
        Map.entry(Key.key("message"), MESSAGE_ARGUMENT_ID),
        Map.entry(Key.key("nbt_compound_tag"), NBT_COMPOUND_TAG_ARGUMENT_ID),
        Map.entry(Key.key("nbt_path"), NBT_PATH_ARGUMENT_ID),
        Map.entry(Key.key("nbt_tag"), NBT_TAG_ARGUMENT_ID),
        Map.entry(Key.key("objective"), OBJECTIVE_ARGUMENT_ID),
        Map.entry(Key.key("objective_criteria"), OBJECTIVE_CRITERIA_ARGUMENT_ID),
        Map.entry(Key.key("operation"), OPERATION_ARGUMENT_ID),
        Map.entry(Key.key("particle"), PARTICLE_ARGUMENT_ID),
        Map.entry(Key.key("resource"), RESOURCE_ARGUMENT_ID),
        Map.entry(Key.key("resource_key"), RESOURCE_KEY_ARGUMENT_ID),
        Map.entry(Key.key("resource_location"), RESOURCE_LOCATION_ARGUMENT_ID),
        Map.entry(Key.key("resource_or_tag"), RESOURCE_OR_TAG_ARGUMENT_ID),
        Map.entry(Key.key("resource_or_tag_key"), RESOURCE_OR_TAG_KEY_ARGUMENT_ID),
        Map.entry(Key.key("resource_selector"), RESOURCE_SELECTOR_ARGUMENT_ID),
        Map.entry(Key.key("rotation"), ROTATION_ARGUMENT_ID),
        Map.entry(Key.key("score_holder"), SCORE_HOLDER_ARGUMENT_ID),
        Map.entry(Key.key("scoreboard_slot"), SCOREBOARD_SLOT_ARGUMENT_ID),
        Map.entry(Key.key("slot_source"), SLOT_SOURCE_ARGUMENT_ID),
        Map.entry(Key.key("style"), STYLE_ARGUMENT_ID),
        Map.entry(Key.key("swing_animation"), SWING_ANIMATION_ARGUMENT_ID),
        Map.entry(Key.key("swizzle"), SWIZZLE_ARGUMENT_ID),
        Map.entry(Key.key("team"), TEAM_ARGUMENT_ID),
        Map.entry(Key.key("team_color"), TEAM_COLOR_ARGUMENT_ID),
        Map.entry(Key.key("template_mirror"), TEMPLATE_MIRROR_ARGUMENT_ID),
        Map.entry(Key.key("template_rotation"), TEMPLATE_ROTATION_ARGUMENT_ID),
        Map.entry(Key.key("time"), TIME_ARGUMENT_ID),
        Map.entry(Key.key("uuid"), UUID_ARGUMENT_ID),
        Map.entry(Key.key("vec2"), VEC2_ARGUMENT_ID),
        Map.entry(Key.key("vec3"), VEC3_ARGUMENT_ID)
    );

    /**
     * Resolves the protocol ID for a namespaced identifier.
     *
     * @param identifier namespaced identifier, e.g. {@code Key.key("minecraft", "chest")}
     * @return the protocol ID, or {@link #UNKNOWN} when the identifier is unknown
     */
    static int id(final Key identifier) {
        return BY_IDENTIFIER.getOrDefault(identifier, UNKNOWN);
    }
}
