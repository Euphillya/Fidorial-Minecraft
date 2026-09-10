package fr.euphyllia.fidorial.server.network.protocol.catalog;

import java.util.Map;
import net.kyori.adventure.key.Key;

/**
 * Network IDs for the {@code play/serverbound} state/direction.
 *
 * <p>Generated from Mojang's packets report; do not edit.</p>
 */
public interface PlayServerboundPacketIds {
    /**
     * Returned by {@link #id(Key)} when the identifier is unknown.
     */
    int UNKNOWN = -1;

    /**
     * {@code minecraft:accept_teleportation}
     */
    int ACCEPT_TELEPORTATION_ID = 0;

    /**
     * {@code minecraft:attack}
     */
    int ATTACK_ID = 1;

    /**
     * {@code minecraft:block_entity_tag_query}
     */
    int BLOCK_ENTITY_TAG_QUERY_ID = 2;

    /**
     * {@code minecraft:bundle_item_selected}
     */
    int BUNDLE_ITEM_SELECTED_ID = 3;

    /**
     * {@code minecraft:change_difficulty}
     */
    int CHANGE_DIFFICULTY_ID = 4;

    /**
     * {@code minecraft:change_game_mode}
     */
    int CHANGE_GAME_MODE_ID = 5;

    /**
     * {@code minecraft:chat}
     */
    int CHAT_ID = 9;

    /**
     * {@code minecraft:chat_ack}
     */
    int CHAT_ACK_ID = 6;

    /**
     * {@code minecraft:chat_command}
     */
    int CHAT_COMMAND_ID = 7;

    /**
     * {@code minecraft:chat_command_signed}
     */
    int CHAT_COMMAND_SIGNED_ID = 8;

    /**
     * {@code minecraft:chat_session_update}
     */
    int CHAT_SESSION_UPDATE_ID = 10;

    /**
     * {@code minecraft:chunk_batch_received}
     */
    int CHUNK_BATCH_RECEIVED_ID = 11;

    /**
     * {@code minecraft:client_command}
     */
    int CLIENT_COMMAND_ID = 12;

    /**
     * {@code minecraft:client_information}
     */
    int CLIENT_INFORMATION_ID = 14;

    /**
     * {@code minecraft:client_tick_end}
     */
    int CLIENT_TICK_END_ID = 13;

    /**
     * {@code minecraft:command_suggestion}
     */
    int COMMAND_SUGGESTION_ID = 15;

    /**
     * {@code minecraft:configuration_acknowledged}
     */
    int CONFIGURATION_ACKNOWLEDGED_ID = 16;

    /**
     * {@code minecraft:container_button_click}
     */
    int CONTAINER_BUTTON_CLICK_ID = 17;

    /**
     * {@code minecraft:container_click}
     */
    int CONTAINER_CLICK_ID = 18;

    /**
     * {@code minecraft:container_close}
     */
    int CONTAINER_CLOSE_ID = 19;

    /**
     * {@code minecraft:container_slot_state_changed}
     */
    int CONTAINER_SLOT_STATE_CHANGED_ID = 20;

    /**
     * {@code minecraft:cookie_response}
     */
    int COOKIE_RESPONSE_ID = 21;

    /**
     * {@code minecraft:custom_click_action}
     */
    int CUSTOM_CLICK_ACTION_ID = 68;

    /**
     * {@code minecraft:custom_payload}
     */
    int CUSTOM_PAYLOAD_ID = 22;

    /**
     * {@code minecraft:debug_subscription_request}
     */
    int DEBUG_SUBSCRIPTION_REQUEST_ID = 23;

    /**
     * {@code minecraft:edit_book}
     */
    int EDIT_BOOK_ID = 24;

    /**
     * {@code minecraft:entity_tag_query}
     */
    int ENTITY_TAG_QUERY_ID = 25;

    /**
     * {@code minecraft:interact}
     */
    int INTERACT_ID = 26;

    /**
     * {@code minecraft:jigsaw_generate}
     */
    int JIGSAW_GENERATE_ID = 27;

    /**
     * {@code minecraft:keep_alive}
     */
    int KEEP_ALIVE_ID = 28;

    /**
     * {@code minecraft:lock_difficulty}
     */
    int LOCK_DIFFICULTY_ID = 29;

    /**
     * {@code minecraft:move_player_pos}
     */
    int MOVE_PLAYER_POS_ID = 30;

    /**
     * {@code minecraft:move_player_pos_rot}
     */
    int MOVE_PLAYER_POS_ROT_ID = 31;

    /**
     * {@code minecraft:move_player_rot}
     */
    int MOVE_PLAYER_ROT_ID = 32;

    /**
     * {@code minecraft:move_player_status_only}
     */
    int MOVE_PLAYER_STATUS_ONLY_ID = 33;

    /**
     * {@code minecraft:move_vehicle}
     */
    int MOVE_VEHICLE_ID = 34;

    /**
     * {@code minecraft:paddle_boat}
     */
    int PADDLE_BOAT_ID = 35;

    /**
     * {@code minecraft:pick_item_from_block}
     */
    int PICK_ITEM_FROM_BLOCK_ID = 36;

    /**
     * {@code minecraft:pick_item_from_entity}
     */
    int PICK_ITEM_FROM_ENTITY_ID = 37;

    /**
     * {@code minecraft:ping_request}
     */
    int PING_REQUEST_ID = 38;

    /**
     * {@code minecraft:place_recipe}
     */
    int PLACE_RECIPE_ID = 39;

    /**
     * {@code minecraft:player_abilities}
     */
    int PLAYER_ABILITIES_ID = 40;

    /**
     * {@code minecraft:player_action}
     */
    int PLAYER_ACTION_ID = 41;

    /**
     * {@code minecraft:player_command}
     */
    int PLAYER_COMMAND_ID = 42;

    /**
     * {@code minecraft:player_input}
     */
    int PLAYER_INPUT_ID = 43;

    /**
     * {@code minecraft:player_loaded}
     */
    int PLAYER_LOADED_ID = 44;

    /**
     * {@code minecraft:pong}
     */
    int PONG_ID = 45;

    /**
     * {@code minecraft:punch}
     */
    int PUNCH_ID = 46;

    /**
     * {@code minecraft:recipe_book_change_settings}
     */
    int RECIPE_BOOK_CHANGE_SETTINGS_ID = 47;

    /**
     * {@code minecraft:recipe_book_seen_recipe}
     */
    int RECIPE_BOOK_SEEN_RECIPE_ID = 48;

    /**
     * {@code minecraft:rename_item}
     */
    int RENAME_ITEM_ID = 49;

    /**
     * {@code minecraft:resource_pack}
     */
    int RESOURCE_PACK_ID = 50;

    /**
     * {@code minecraft:seen_advancements}
     */
    int SEEN_ADVANCEMENTS_ID = 51;

    /**
     * {@code minecraft:select_trade}
     */
    int SELECT_TRADE_ID = 52;

    /**
     * {@code minecraft:set_beacon}
     */
    int SET_BEACON_ID = 53;

    /**
     * {@code minecraft:set_carried_item}
     */
    int SET_CARRIED_ITEM_ID = 54;

    /**
     * {@code minecraft:set_command_block}
     */
    int SET_COMMAND_BLOCK_ID = 55;

    /**
     * {@code minecraft:set_command_minecart}
     */
    int SET_COMMAND_MINECART_ID = 56;

    /**
     * {@code minecraft:set_creative_mode_slot}
     */
    int SET_CREATIVE_MODE_SLOT_ID = 57;

    /**
     * {@code minecraft:set_game_rule}
     */
    int SET_GAME_RULE_ID = 58;

    /**
     * {@code minecraft:set_jigsaw_block}
     */
    int SET_JIGSAW_BLOCK_ID = 59;

    /**
     * {@code minecraft:set_structure_block}
     */
    int SET_STRUCTURE_BLOCK_ID = 60;

    /**
     * {@code minecraft:set_test_block}
     */
    int SET_TEST_BLOCK_ID = 61;

    /**
     * {@code minecraft:sign_update}
     */
    int SIGN_UPDATE_ID = 62;

    /**
     * {@code minecraft:spectator_action}
     */
    int SPECTATOR_ACTION_ID = 63;

    /**
     * {@code minecraft:teleport_to_entity}
     */
    int TELEPORT_TO_ENTITY_ID = 64;

    /**
     * {@code minecraft:test_instance_block_action}
     */
    int TEST_INSTANCE_BLOCK_ACTION_ID = 65;

    /**
     * {@code minecraft:use_item}
     */
    int USE_ITEM_ID = 67;

    /**
     * {@code minecraft:use_item_on}
     */
    int USE_ITEM_ON_ID = 66;

    /**
     * Immutable identifier to protocol ID lookup table.
     */
    Map<Key, Integer> BY_IDENTIFIER = Map.ofEntries(
        Map.entry(Key.key("accept_teleportation"), ACCEPT_TELEPORTATION_ID),
        Map.entry(Key.key("attack"), ATTACK_ID),
        Map.entry(Key.key("block_entity_tag_query"), BLOCK_ENTITY_TAG_QUERY_ID),
        Map.entry(Key.key("bundle_item_selected"), BUNDLE_ITEM_SELECTED_ID),
        Map.entry(Key.key("change_difficulty"), CHANGE_DIFFICULTY_ID),
        Map.entry(Key.key("change_game_mode"), CHANGE_GAME_MODE_ID),
        Map.entry(Key.key("chat"), CHAT_ID),
        Map.entry(Key.key("chat_ack"), CHAT_ACK_ID),
        Map.entry(Key.key("chat_command"), CHAT_COMMAND_ID),
        Map.entry(Key.key("chat_command_signed"), CHAT_COMMAND_SIGNED_ID),
        Map.entry(Key.key("chat_session_update"), CHAT_SESSION_UPDATE_ID),
        Map.entry(Key.key("chunk_batch_received"), CHUNK_BATCH_RECEIVED_ID),
        Map.entry(Key.key("client_command"), CLIENT_COMMAND_ID),
        Map.entry(Key.key("client_information"), CLIENT_INFORMATION_ID),
        Map.entry(Key.key("client_tick_end"), CLIENT_TICK_END_ID),
        Map.entry(Key.key("command_suggestion"), COMMAND_SUGGESTION_ID),
        Map.entry(Key.key("configuration_acknowledged"), CONFIGURATION_ACKNOWLEDGED_ID),
        Map.entry(Key.key("container_button_click"), CONTAINER_BUTTON_CLICK_ID),
        Map.entry(Key.key("container_click"), CONTAINER_CLICK_ID),
        Map.entry(Key.key("container_close"), CONTAINER_CLOSE_ID),
        Map.entry(Key.key("container_slot_state_changed"), CONTAINER_SLOT_STATE_CHANGED_ID),
        Map.entry(Key.key("cookie_response"), COOKIE_RESPONSE_ID),
        Map.entry(Key.key("custom_click_action"), CUSTOM_CLICK_ACTION_ID),
        Map.entry(Key.key("custom_payload"), CUSTOM_PAYLOAD_ID),
        Map.entry(Key.key("debug_subscription_request"), DEBUG_SUBSCRIPTION_REQUEST_ID),
        Map.entry(Key.key("edit_book"), EDIT_BOOK_ID),
        Map.entry(Key.key("entity_tag_query"), ENTITY_TAG_QUERY_ID),
        Map.entry(Key.key("interact"), INTERACT_ID),
        Map.entry(Key.key("jigsaw_generate"), JIGSAW_GENERATE_ID),
        Map.entry(Key.key("keep_alive"), KEEP_ALIVE_ID),
        Map.entry(Key.key("lock_difficulty"), LOCK_DIFFICULTY_ID),
        Map.entry(Key.key("move_player_pos"), MOVE_PLAYER_POS_ID),
        Map.entry(Key.key("move_player_pos_rot"), MOVE_PLAYER_POS_ROT_ID),
        Map.entry(Key.key("move_player_rot"), MOVE_PLAYER_ROT_ID),
        Map.entry(Key.key("move_player_status_only"), MOVE_PLAYER_STATUS_ONLY_ID),
        Map.entry(Key.key("move_vehicle"), MOVE_VEHICLE_ID),
        Map.entry(Key.key("paddle_boat"), PADDLE_BOAT_ID),
        Map.entry(Key.key("pick_item_from_block"), PICK_ITEM_FROM_BLOCK_ID),
        Map.entry(Key.key("pick_item_from_entity"), PICK_ITEM_FROM_ENTITY_ID),
        Map.entry(Key.key("ping_request"), PING_REQUEST_ID),
        Map.entry(Key.key("place_recipe"), PLACE_RECIPE_ID),
        Map.entry(Key.key("player_abilities"), PLAYER_ABILITIES_ID),
        Map.entry(Key.key("player_action"), PLAYER_ACTION_ID),
        Map.entry(Key.key("player_command"), PLAYER_COMMAND_ID),
        Map.entry(Key.key("player_input"), PLAYER_INPUT_ID),
        Map.entry(Key.key("player_loaded"), PLAYER_LOADED_ID),
        Map.entry(Key.key("pong"), PONG_ID),
        Map.entry(Key.key("punch"), PUNCH_ID),
        Map.entry(Key.key("recipe_book_change_settings"), RECIPE_BOOK_CHANGE_SETTINGS_ID),
        Map.entry(Key.key("recipe_book_seen_recipe"), RECIPE_BOOK_SEEN_RECIPE_ID),
        Map.entry(Key.key("rename_item"), RENAME_ITEM_ID),
        Map.entry(Key.key("resource_pack"), RESOURCE_PACK_ID),
        Map.entry(Key.key("seen_advancements"), SEEN_ADVANCEMENTS_ID),
        Map.entry(Key.key("select_trade"), SELECT_TRADE_ID),
        Map.entry(Key.key("set_beacon"), SET_BEACON_ID),
        Map.entry(Key.key("set_carried_item"), SET_CARRIED_ITEM_ID),
        Map.entry(Key.key("set_command_block"), SET_COMMAND_BLOCK_ID),
        Map.entry(Key.key("set_command_minecart"), SET_COMMAND_MINECART_ID),
        Map.entry(Key.key("set_creative_mode_slot"), SET_CREATIVE_MODE_SLOT_ID),
        Map.entry(Key.key("set_game_rule"), SET_GAME_RULE_ID),
        Map.entry(Key.key("set_jigsaw_block"), SET_JIGSAW_BLOCK_ID),
        Map.entry(Key.key("set_structure_block"), SET_STRUCTURE_BLOCK_ID),
        Map.entry(Key.key("set_test_block"), SET_TEST_BLOCK_ID),
        Map.entry(Key.key("sign_update"), SIGN_UPDATE_ID),
        Map.entry(Key.key("spectator_action"), SPECTATOR_ACTION_ID),
        Map.entry(Key.key("teleport_to_entity"), TELEPORT_TO_ENTITY_ID),
        Map.entry(Key.key("test_instance_block_action"), TEST_INSTANCE_BLOCK_ACTION_ID),
        Map.entry(Key.key("use_item"), USE_ITEM_ID),
        Map.entry(Key.key("use_item_on"), USE_ITEM_ON_ID)
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
