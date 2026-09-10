package fr.euphyllia.fidorial.server.network.protocol.catalog;

import java.util.Map;
import net.kyori.adventure.key.Key;

/**
 * Network IDs for the {@code play/clientbound} state/direction.
 *
 * <p>Generated from Mojang's packets report; do not edit.</p>
 */
public interface PlayClientboundPacketIds {
    /**
     * Returned by {@link #id(Key)} when the identifier is unknown.
     */
    int UNKNOWN = -1;

    /**
     * {@code minecraft:add_entity}
     */
    int ADD_ENTITY_ID = 1;

    /**
     * {@code minecraft:add_transient_block}
     */
    int ADD_TRANSIENT_BLOCK_ID = 37;

    /**
     * {@code minecraft:animate}
     */
    int ANIMATE_ID = 2;

    /**
     * {@code minecraft:award_stats}
     */
    int AWARD_STATS_ID = 3;

    /**
     * {@code minecraft:block_changed_ack}
     */
    int BLOCK_CHANGED_ACK_ID = 4;

    /**
     * {@code minecraft:block_destruction}
     */
    int BLOCK_DESTRUCTION_ID = 5;

    /**
     * {@code minecraft:block_entity_data}
     */
    int BLOCK_ENTITY_DATA_ID = 6;

    /**
     * {@code minecraft:block_event}
     */
    int BLOCK_EVENT_ID = 7;

    /**
     * {@code minecraft:block_update}
     */
    int BLOCK_UPDATE_ID = 8;

    /**
     * {@code minecraft:boss_event}
     */
    int BOSS_EVENT_ID = 9;

    /**
     * {@code minecraft:bundle_delimiter}
     */
    int BUNDLE_DELIMITER_ID = 0;

    /**
     * {@code minecraft:change_difficulty}
     */
    int CHANGE_DIFFICULTY_ID = 10;

    /**
     * {@code minecraft:chunk_batch_finished}
     */
    int CHUNK_BATCH_FINISHED_ID = 11;

    /**
     * {@code minecraft:chunk_batch_start}
     */
    int CHUNK_BATCH_START_ID = 12;

    /**
     * {@code minecraft:chunks_biomes}
     */
    int CHUNKS_BIOMES_ID = 13;

    /**
     * {@code minecraft:clear_dialog}
     */
    int CLEAR_DIALOG_ID = 142;

    /**
     * {@code minecraft:clear_titles}
     */
    int CLEAR_TITLES_ID = 14;

    /**
     * {@code minecraft:command_suggestions}
     */
    int COMMAND_SUGGESTIONS_ID = 15;

    /**
     * {@code minecraft:commands}
     */
    int COMMANDS_ID = 16;

    /**
     * {@code minecraft:container_close}
     */
    int CONTAINER_CLOSE_ID = 17;

    /**
     * {@code minecraft:container_set_content}
     */
    int CONTAINER_SET_CONTENT_ID = 18;

    /**
     * {@code minecraft:container_set_data}
     */
    int CONTAINER_SET_DATA_ID = 19;

    /**
     * {@code minecraft:container_set_slot}
     */
    int CONTAINER_SET_SLOT_ID = 20;

    /**
     * {@code minecraft:cookie_request}
     */
    int COOKIE_REQUEST_ID = 21;

    /**
     * {@code minecraft:cooldown}
     */
    int COOLDOWN_ID = 22;

    /**
     * {@code minecraft:custom_chat_completions}
     */
    int CUSTOM_CHAT_COMPLETIONS_ID = 23;

    /**
     * {@code minecraft:custom_payload}
     */
    int CUSTOM_PAYLOAD_ID = 24;

    /**
     * {@code minecraft:custom_report_details}
     */
    int CUSTOM_REPORT_DETAILS_ID = 139;

    /**
     * {@code minecraft:damage_event}
     */
    int DAMAGE_EVENT_ID = 25;

    /**
     * {@code minecraft:debug/block_value}
     */
    int DEBUG_BLOCK_VALUE_ID = 26;

    /**
     * {@code minecraft:debug/chunk_value}
     */
    int DEBUG_CHUNK_VALUE_ID = 27;

    /**
     * {@code minecraft:debug/entity_value}
     */
    int DEBUG_ENTITY_VALUE_ID = 28;

    /**
     * {@code minecraft:debug/event}
     */
    int DEBUG_EVENT_ID = 29;

    /**
     * {@code minecraft:debug_sample}
     */
    int DEBUG_SAMPLE_ID = 30;

    /**
     * {@code minecraft:delete_chat}
     */
    int DELETE_CHAT_ID = 31;

    /**
     * {@code minecraft:disconnect}
     */
    int DISCONNECT_ID = 32;

    /**
     * {@code minecraft:disguised_chat}
     */
    int DISGUISED_CHAT_ID = 33;

    /**
     * {@code minecraft:entity_event}
     */
    int ENTITY_EVENT_ID = 34;

    /**
     * {@code minecraft:entity_position_sync}
     */
    int ENTITY_POSITION_SYNC_ID = 35;

    /**
     * {@code minecraft:explode}
     */
    int EXPLODE_ID = 36;

    /**
     * {@code minecraft:forget_level_chunk}
     */
    int FORGET_LEVEL_CHUNK_ID = 38;

    /**
     * {@code minecraft:game_event}
     */
    int GAME_EVENT_ID = 39;

    /**
     * {@code minecraft:game_rule_values}
     */
    int GAME_RULE_VALUES_ID = 40;

    /**
     * {@code minecraft:game_test_highlight_pos}
     */
    int GAME_TEST_HIGHLIGHT_POS_ID = 41;

    /**
     * {@code minecraft:hurt_animation}
     */
    int HURT_ANIMATION_ID = 43;

    /**
     * {@code minecraft:initialize_border}
     */
    int INITIALIZE_BORDER_ID = 44;

    /**
     * {@code minecraft:keep_alive}
     */
    int KEEP_ALIVE_ID = 45;

    /**
     * {@code minecraft:level_chunk_with_light}
     */
    int LEVEL_CHUNK_WITH_LIGHT_ID = 46;

    /**
     * {@code minecraft:level_event}
     */
    int LEVEL_EVENT_ID = 47;

    /**
     * {@code minecraft:level_particles}
     */
    int LEVEL_PARTICLES_ID = 48;

    /**
     * {@code minecraft:light_update}
     */
    int LIGHT_UPDATE_ID = 49;

    /**
     * {@code minecraft:login}
     */
    int LOGIN_ID = 50;

    /**
     * {@code minecraft:low_disk_space_warning}
     */
    int LOW_DISK_SPACE_WARNING_ID = 51;

    /**
     * {@code minecraft:map_item_data}
     */
    int MAP_ITEM_DATA_ID = 52;

    /**
     * {@code minecraft:merchant_offers}
     */
    int MERCHANT_OFFERS_ID = 53;

    /**
     * {@code minecraft:mount_screen_open}
     */
    int MOUNT_SCREEN_OPEN_ID = 42;

    /**
     * {@code minecraft:move_entity_pos}
     */
    int MOVE_ENTITY_POS_ID = 54;

    /**
     * {@code minecraft:move_entity_pos_rot}
     */
    int MOVE_ENTITY_POS_ROT_ID = 55;

    /**
     * {@code minecraft:move_entity_rot}
     */
    int MOVE_ENTITY_ROT_ID = 57;

    /**
     * {@code minecraft:move_minecart_along_track}
     */
    int MOVE_MINECART_ALONG_TRACK_ID = 56;

    /**
     * {@code minecraft:move_vehicle}
     */
    int MOVE_VEHICLE_ID = 58;

    /**
     * {@code minecraft:open_book}
     */
    int OPEN_BOOK_ID = 59;

    /**
     * {@code minecraft:open_screen}
     */
    int OPEN_SCREEN_ID = 60;

    /**
     * {@code minecraft:open_sign_editor}
     */
    int OPEN_SIGN_EDITOR_ID = 61;

    /**
     * {@code minecraft:ping}
     */
    int PING_ID = 62;

    /**
     * {@code minecraft:place_ghost_recipe}
     */
    int PLACE_GHOST_RECIPE_ID = 64;

    /**
     * {@code minecraft:player_abilities}
     */
    int PLAYER_ABILITIES_ID = 65;

    /**
     * {@code minecraft:player_chat}
     */
    int PLAYER_CHAT_ID = 66;

    /**
     * {@code minecraft:player_combat_end}
     */
    int PLAYER_COMBAT_END_ID = 67;

    /**
     * {@code minecraft:player_combat_enter}
     */
    int PLAYER_COMBAT_ENTER_ID = 68;

    /**
     * {@code minecraft:player_combat_kill}
     */
    int PLAYER_COMBAT_KILL_ID = 69;

    /**
     * {@code minecraft:player_info_remove}
     */
    int PLAYER_INFO_REMOVE_ID = 70;

    /**
     * {@code minecraft:player_info_update}
     */
    int PLAYER_INFO_UPDATE_ID = 71;

    /**
     * {@code minecraft:player_look_at}
     */
    int PLAYER_LOOK_AT_ID = 72;

    /**
     * {@code minecraft:player_position}
     */
    int PLAYER_POSITION_ID = 73;

    /**
     * {@code minecraft:player_rotation}
     */
    int PLAYER_ROTATION_ID = 74;

    /**
     * {@code minecraft:pong_response}
     */
    int PONG_RESPONSE_ID = 63;

    /**
     * {@code minecraft:post_effects}
     */
    int POST_EFFECTS_ID = 83;

    /**
     * {@code minecraft:projectile_power}
     */
    int PROJECTILE_POWER_ID = 138;

    /**
     * {@code minecraft:recipe_book_add}
     */
    int RECIPE_BOOK_ADD_ID = 75;

    /**
     * {@code minecraft:recipe_book_remove}
     */
    int RECIPE_BOOK_REMOVE_ID = 76;

    /**
     * {@code minecraft:recipe_book_settings}
     */
    int RECIPE_BOOK_SETTINGS_ID = 77;

    /**
     * {@code minecraft:remove_entities}
     */
    int REMOVE_ENTITIES_ID = 78;

    /**
     * {@code minecraft:remove_mob_effect}
     */
    int REMOVE_MOB_EFFECT_ID = 79;

    /**
     * {@code minecraft:reset_score}
     */
    int RESET_SCORE_ID = 80;

    /**
     * {@code minecraft:resource_pack_pop}
     */
    int RESOURCE_PACK_POP_ID = 81;

    /**
     * {@code minecraft:resource_pack_push}
     */
    int RESOURCE_PACK_PUSH_ID = 82;

    /**
     * {@code minecraft:respawn}
     */
    int RESPAWN_ID = 84;

    /**
     * {@code minecraft:rotate_head}
     */
    int ROTATE_HEAD_ID = 85;

    /**
     * {@code minecraft:section_blocks_update}
     */
    int SECTION_BLOCKS_UPDATE_ID = 86;

    /**
     * {@code minecraft:select_advancements_tab}
     */
    int SELECT_ADVANCEMENTS_TAB_ID = 87;

    /**
     * {@code minecraft:server_data}
     */
    int SERVER_DATA_ID = 88;

    /**
     * {@code minecraft:server_links}
     */
    int SERVER_LINKS_ID = 140;

    /**
     * {@code minecraft:set_action_bar_text}
     */
    int SET_ACTION_BAR_TEXT_ID = 89;

    /**
     * {@code minecraft:set_border_center}
     */
    int SET_BORDER_CENTER_ID = 90;

    /**
     * {@code minecraft:set_border_lerp_size}
     */
    int SET_BORDER_LERP_SIZE_ID = 91;

    /**
     * {@code minecraft:set_border_size}
     */
    int SET_BORDER_SIZE_ID = 92;

    /**
     * {@code minecraft:set_border_warning_delay}
     */
    int SET_BORDER_WARNING_DELAY_ID = 93;

    /**
     * {@code minecraft:set_border_warning_distance}
     */
    int SET_BORDER_WARNING_DISTANCE_ID = 94;

    /**
     * {@code minecraft:set_camera}
     */
    int SET_CAMERA_ID = 95;

    /**
     * {@code minecraft:set_chunk_cache_center}
     */
    int SET_CHUNK_CACHE_CENTER_ID = 96;

    /**
     * {@code minecraft:set_chunk_cache_radius}
     */
    int SET_CHUNK_CACHE_RADIUS_ID = 97;

    /**
     * {@code minecraft:set_cursor_item}
     */
    int SET_CURSOR_ITEM_ID = 98;

    /**
     * {@code minecraft:set_default_spawn_position}
     */
    int SET_DEFAULT_SPAWN_POSITION_ID = 99;

    /**
     * {@code minecraft:set_display_objective}
     */
    int SET_DISPLAY_OBJECTIVE_ID = 100;

    /**
     * {@code minecraft:set_entity_data}
     */
    int SET_ENTITY_DATA_ID = 101;

    /**
     * {@code minecraft:set_entity_link}
     */
    int SET_ENTITY_LINK_ID = 102;

    /**
     * {@code minecraft:set_entity_motion}
     */
    int SET_ENTITY_MOTION_ID = 103;

    /**
     * {@code minecraft:set_equipment}
     */
    int SET_EQUIPMENT_ID = 104;

    /**
     * {@code minecraft:set_experience}
     */
    int SET_EXPERIENCE_ID = 105;

    /**
     * {@code minecraft:set_health}
     */
    int SET_HEALTH_ID = 106;

    /**
     * {@code minecraft:set_held_slot}
     */
    int SET_HELD_SLOT_ID = 107;

    /**
     * {@code minecraft:set_objective}
     */
    int SET_OBJECTIVE_ID = 108;

    /**
     * {@code minecraft:set_passengers}
     */
    int SET_PASSENGERS_ID = 109;

    /**
     * {@code minecraft:set_player_inventory}
     */
    int SET_PLAYER_INVENTORY_ID = 110;

    /**
     * {@code minecraft:set_player_team}
     */
    int SET_PLAYER_TEAM_ID = 111;

    /**
     * {@code minecraft:set_score}
     */
    int SET_SCORE_ID = 112;

    /**
     * {@code minecraft:set_simulation_distance}
     */
    int SET_SIMULATION_DISTANCE_ID = 113;

    /**
     * {@code minecraft:set_subtitle_text}
     */
    int SET_SUBTITLE_TEXT_ID = 114;

    /**
     * {@code minecraft:set_time}
     */
    int SET_TIME_ID = 115;

    /**
     * {@code minecraft:set_title_text}
     */
    int SET_TITLE_TEXT_ID = 116;

    /**
     * {@code minecraft:set_titles_animation}
     */
    int SET_TITLES_ANIMATION_ID = 117;

    /**
     * {@code minecraft:show_dialog}
     */
    int SHOW_DIALOG_ID = 143;

    /**
     * {@code minecraft:sound}
     */
    int SOUND_ID = 119;

    /**
     * {@code minecraft:sound_entity}
     */
    int SOUND_ENTITY_ID = 118;

    /**
     * {@code minecraft:start_configuration}
     */
    int START_CONFIGURATION_ID = 120;

    /**
     * {@code minecraft:stop_sound}
     */
    int STOP_SOUND_ID = 121;

    /**
     * {@code minecraft:store_cookie}
     */
    int STORE_COOKIE_ID = 122;

    /**
     * {@code minecraft:swing_animation}
     */
    int SWING_ANIMATION_ID = 123;

    /**
     * {@code minecraft:system_chat}
     */
    int SYSTEM_CHAT_ID = 124;

    /**
     * {@code minecraft:tab_list}
     */
    int TAB_LIST_ID = 125;

    /**
     * {@code minecraft:tag_query}
     */
    int TAG_QUERY_ID = 126;

    /**
     * {@code minecraft:take_item_entity}
     */
    int TAKE_ITEM_ENTITY_ID = 127;

    /**
     * {@code minecraft:teleport_entity}
     */
    int TELEPORT_ENTITY_ID = 128;

    /**
     * {@code minecraft:test_instance_block_status}
     */
    int TEST_INSTANCE_BLOCK_STATUS_ID = 129;

    /**
     * {@code minecraft:ticking_state}
     */
    int TICKING_STATE_ID = 130;

    /**
     * {@code minecraft:ticking_step}
     */
    int TICKING_STEP_ID = 131;

    /**
     * {@code minecraft:transfer}
     */
    int TRANSFER_ID = 132;

    /**
     * {@code minecraft:update_advancements}
     */
    int UPDATE_ADVANCEMENTS_ID = 133;

    /**
     * {@code minecraft:update_attributes}
     */
    int UPDATE_ATTRIBUTES_ID = 134;

    /**
     * {@code minecraft:update_mob_effect}
     */
    int UPDATE_MOB_EFFECT_ID = 135;

    /**
     * {@code minecraft:update_recipes}
     */
    int UPDATE_RECIPES_ID = 136;

    /**
     * {@code minecraft:update_tags}
     */
    int UPDATE_TAGS_ID = 137;

    /**
     * {@code minecraft:waypoint}
     */
    int WAYPOINT_ID = 141;

    /**
     * Immutable identifier to protocol ID lookup table.
     */
    Map<Key, Integer> BY_IDENTIFIER = Map.ofEntries(
        Map.entry(Key.key("add_entity"), ADD_ENTITY_ID),
        Map.entry(Key.key("add_transient_block"), ADD_TRANSIENT_BLOCK_ID),
        Map.entry(Key.key("animate"), ANIMATE_ID),
        Map.entry(Key.key("award_stats"), AWARD_STATS_ID),
        Map.entry(Key.key("block_changed_ack"), BLOCK_CHANGED_ACK_ID),
        Map.entry(Key.key("block_destruction"), BLOCK_DESTRUCTION_ID),
        Map.entry(Key.key("block_entity_data"), BLOCK_ENTITY_DATA_ID),
        Map.entry(Key.key("block_event"), BLOCK_EVENT_ID),
        Map.entry(Key.key("block_update"), BLOCK_UPDATE_ID),
        Map.entry(Key.key("boss_event"), BOSS_EVENT_ID),
        Map.entry(Key.key("bundle_delimiter"), BUNDLE_DELIMITER_ID),
        Map.entry(Key.key("change_difficulty"), CHANGE_DIFFICULTY_ID),
        Map.entry(Key.key("chunk_batch_finished"), CHUNK_BATCH_FINISHED_ID),
        Map.entry(Key.key("chunk_batch_start"), CHUNK_BATCH_START_ID),
        Map.entry(Key.key("chunks_biomes"), CHUNKS_BIOMES_ID),
        Map.entry(Key.key("clear_dialog"), CLEAR_DIALOG_ID),
        Map.entry(Key.key("clear_titles"), CLEAR_TITLES_ID),
        Map.entry(Key.key("command_suggestions"), COMMAND_SUGGESTIONS_ID),
        Map.entry(Key.key("commands"), COMMANDS_ID),
        Map.entry(Key.key("container_close"), CONTAINER_CLOSE_ID),
        Map.entry(Key.key("container_set_content"), CONTAINER_SET_CONTENT_ID),
        Map.entry(Key.key("container_set_data"), CONTAINER_SET_DATA_ID),
        Map.entry(Key.key("container_set_slot"), CONTAINER_SET_SLOT_ID),
        Map.entry(Key.key("cookie_request"), COOKIE_REQUEST_ID),
        Map.entry(Key.key("cooldown"), COOLDOWN_ID),
        Map.entry(Key.key("custom_chat_completions"), CUSTOM_CHAT_COMPLETIONS_ID),
        Map.entry(Key.key("custom_payload"), CUSTOM_PAYLOAD_ID),
        Map.entry(Key.key("custom_report_details"), CUSTOM_REPORT_DETAILS_ID),
        Map.entry(Key.key("damage_event"), DAMAGE_EVENT_ID),
        Map.entry(Key.key("debug/block_value"), DEBUG_BLOCK_VALUE_ID),
        Map.entry(Key.key("debug/chunk_value"), DEBUG_CHUNK_VALUE_ID),
        Map.entry(Key.key("debug/entity_value"), DEBUG_ENTITY_VALUE_ID),
        Map.entry(Key.key("debug/event"), DEBUG_EVENT_ID),
        Map.entry(Key.key("debug_sample"), DEBUG_SAMPLE_ID),
        Map.entry(Key.key("delete_chat"), DELETE_CHAT_ID),
        Map.entry(Key.key("disconnect"), DISCONNECT_ID),
        Map.entry(Key.key("disguised_chat"), DISGUISED_CHAT_ID),
        Map.entry(Key.key("entity_event"), ENTITY_EVENT_ID),
        Map.entry(Key.key("entity_position_sync"), ENTITY_POSITION_SYNC_ID),
        Map.entry(Key.key("explode"), EXPLODE_ID),
        Map.entry(Key.key("forget_level_chunk"), FORGET_LEVEL_CHUNK_ID),
        Map.entry(Key.key("game_event"), GAME_EVENT_ID),
        Map.entry(Key.key("game_rule_values"), GAME_RULE_VALUES_ID),
        Map.entry(Key.key("game_test_highlight_pos"), GAME_TEST_HIGHLIGHT_POS_ID),
        Map.entry(Key.key("hurt_animation"), HURT_ANIMATION_ID),
        Map.entry(Key.key("initialize_border"), INITIALIZE_BORDER_ID),
        Map.entry(Key.key("keep_alive"), KEEP_ALIVE_ID),
        Map.entry(Key.key("level_chunk_with_light"), LEVEL_CHUNK_WITH_LIGHT_ID),
        Map.entry(Key.key("level_event"), LEVEL_EVENT_ID),
        Map.entry(Key.key("level_particles"), LEVEL_PARTICLES_ID),
        Map.entry(Key.key("light_update"), LIGHT_UPDATE_ID),
        Map.entry(Key.key("login"), LOGIN_ID),
        Map.entry(Key.key("low_disk_space_warning"), LOW_DISK_SPACE_WARNING_ID),
        Map.entry(Key.key("map_item_data"), MAP_ITEM_DATA_ID),
        Map.entry(Key.key("merchant_offers"), MERCHANT_OFFERS_ID),
        Map.entry(Key.key("mount_screen_open"), MOUNT_SCREEN_OPEN_ID),
        Map.entry(Key.key("move_entity_pos"), MOVE_ENTITY_POS_ID),
        Map.entry(Key.key("move_entity_pos_rot"), MOVE_ENTITY_POS_ROT_ID),
        Map.entry(Key.key("move_entity_rot"), MOVE_ENTITY_ROT_ID),
        Map.entry(Key.key("move_minecart_along_track"), MOVE_MINECART_ALONG_TRACK_ID),
        Map.entry(Key.key("move_vehicle"), MOVE_VEHICLE_ID),
        Map.entry(Key.key("open_book"), OPEN_BOOK_ID),
        Map.entry(Key.key("open_screen"), OPEN_SCREEN_ID),
        Map.entry(Key.key("open_sign_editor"), OPEN_SIGN_EDITOR_ID),
        Map.entry(Key.key("ping"), PING_ID),
        Map.entry(Key.key("place_ghost_recipe"), PLACE_GHOST_RECIPE_ID),
        Map.entry(Key.key("player_abilities"), PLAYER_ABILITIES_ID),
        Map.entry(Key.key("player_chat"), PLAYER_CHAT_ID),
        Map.entry(Key.key("player_combat_end"), PLAYER_COMBAT_END_ID),
        Map.entry(Key.key("player_combat_enter"), PLAYER_COMBAT_ENTER_ID),
        Map.entry(Key.key("player_combat_kill"), PLAYER_COMBAT_KILL_ID),
        Map.entry(Key.key("player_info_remove"), PLAYER_INFO_REMOVE_ID),
        Map.entry(Key.key("player_info_update"), PLAYER_INFO_UPDATE_ID),
        Map.entry(Key.key("player_look_at"), PLAYER_LOOK_AT_ID),
        Map.entry(Key.key("player_position"), PLAYER_POSITION_ID),
        Map.entry(Key.key("player_rotation"), PLAYER_ROTATION_ID),
        Map.entry(Key.key("pong_response"), PONG_RESPONSE_ID),
        Map.entry(Key.key("post_effects"), POST_EFFECTS_ID),
        Map.entry(Key.key("projectile_power"), PROJECTILE_POWER_ID),
        Map.entry(Key.key("recipe_book_add"), RECIPE_BOOK_ADD_ID),
        Map.entry(Key.key("recipe_book_remove"), RECIPE_BOOK_REMOVE_ID),
        Map.entry(Key.key("recipe_book_settings"), RECIPE_BOOK_SETTINGS_ID),
        Map.entry(Key.key("remove_entities"), REMOVE_ENTITIES_ID),
        Map.entry(Key.key("remove_mob_effect"), REMOVE_MOB_EFFECT_ID),
        Map.entry(Key.key("reset_score"), RESET_SCORE_ID),
        Map.entry(Key.key("resource_pack_pop"), RESOURCE_PACK_POP_ID),
        Map.entry(Key.key("resource_pack_push"), RESOURCE_PACK_PUSH_ID),
        Map.entry(Key.key("respawn"), RESPAWN_ID),
        Map.entry(Key.key("rotate_head"), ROTATE_HEAD_ID),
        Map.entry(Key.key("section_blocks_update"), SECTION_BLOCKS_UPDATE_ID),
        Map.entry(Key.key("select_advancements_tab"), SELECT_ADVANCEMENTS_TAB_ID),
        Map.entry(Key.key("server_data"), SERVER_DATA_ID),
        Map.entry(Key.key("server_links"), SERVER_LINKS_ID),
        Map.entry(Key.key("set_action_bar_text"), SET_ACTION_BAR_TEXT_ID),
        Map.entry(Key.key("set_border_center"), SET_BORDER_CENTER_ID),
        Map.entry(Key.key("set_border_lerp_size"), SET_BORDER_LERP_SIZE_ID),
        Map.entry(Key.key("set_border_size"), SET_BORDER_SIZE_ID),
        Map.entry(Key.key("set_border_warning_delay"), SET_BORDER_WARNING_DELAY_ID),
        Map.entry(Key.key("set_border_warning_distance"), SET_BORDER_WARNING_DISTANCE_ID),
        Map.entry(Key.key("set_camera"), SET_CAMERA_ID),
        Map.entry(Key.key("set_chunk_cache_center"), SET_CHUNK_CACHE_CENTER_ID),
        Map.entry(Key.key("set_chunk_cache_radius"), SET_CHUNK_CACHE_RADIUS_ID),
        Map.entry(Key.key("set_cursor_item"), SET_CURSOR_ITEM_ID),
        Map.entry(Key.key("set_default_spawn_position"), SET_DEFAULT_SPAWN_POSITION_ID),
        Map.entry(Key.key("set_display_objective"), SET_DISPLAY_OBJECTIVE_ID),
        Map.entry(Key.key("set_entity_data"), SET_ENTITY_DATA_ID),
        Map.entry(Key.key("set_entity_link"), SET_ENTITY_LINK_ID),
        Map.entry(Key.key("set_entity_motion"), SET_ENTITY_MOTION_ID),
        Map.entry(Key.key("set_equipment"), SET_EQUIPMENT_ID),
        Map.entry(Key.key("set_experience"), SET_EXPERIENCE_ID),
        Map.entry(Key.key("set_health"), SET_HEALTH_ID),
        Map.entry(Key.key("set_held_slot"), SET_HELD_SLOT_ID),
        Map.entry(Key.key("set_objective"), SET_OBJECTIVE_ID),
        Map.entry(Key.key("set_passengers"), SET_PASSENGERS_ID),
        Map.entry(Key.key("set_player_inventory"), SET_PLAYER_INVENTORY_ID),
        Map.entry(Key.key("set_player_team"), SET_PLAYER_TEAM_ID),
        Map.entry(Key.key("set_score"), SET_SCORE_ID),
        Map.entry(Key.key("set_simulation_distance"), SET_SIMULATION_DISTANCE_ID),
        Map.entry(Key.key("set_subtitle_text"), SET_SUBTITLE_TEXT_ID),
        Map.entry(Key.key("set_time"), SET_TIME_ID),
        Map.entry(Key.key("set_title_text"), SET_TITLE_TEXT_ID),
        Map.entry(Key.key("set_titles_animation"), SET_TITLES_ANIMATION_ID),
        Map.entry(Key.key("show_dialog"), SHOW_DIALOG_ID),
        Map.entry(Key.key("sound"), SOUND_ID),
        Map.entry(Key.key("sound_entity"), SOUND_ENTITY_ID),
        Map.entry(Key.key("start_configuration"), START_CONFIGURATION_ID),
        Map.entry(Key.key("stop_sound"), STOP_SOUND_ID),
        Map.entry(Key.key("store_cookie"), STORE_COOKIE_ID),
        Map.entry(Key.key("swing_animation"), SWING_ANIMATION_ID),
        Map.entry(Key.key("system_chat"), SYSTEM_CHAT_ID),
        Map.entry(Key.key("tab_list"), TAB_LIST_ID),
        Map.entry(Key.key("tag_query"), TAG_QUERY_ID),
        Map.entry(Key.key("take_item_entity"), TAKE_ITEM_ENTITY_ID),
        Map.entry(Key.key("teleport_entity"), TELEPORT_ENTITY_ID),
        Map.entry(Key.key("test_instance_block_status"), TEST_INSTANCE_BLOCK_STATUS_ID),
        Map.entry(Key.key("ticking_state"), TICKING_STATE_ID),
        Map.entry(Key.key("ticking_step"), TICKING_STEP_ID),
        Map.entry(Key.key("transfer"), TRANSFER_ID),
        Map.entry(Key.key("update_advancements"), UPDATE_ADVANCEMENTS_ID),
        Map.entry(Key.key("update_attributes"), UPDATE_ATTRIBUTES_ID),
        Map.entry(Key.key("update_mob_effect"), UPDATE_MOB_EFFECT_ID),
        Map.entry(Key.key("update_recipes"), UPDATE_RECIPES_ID),
        Map.entry(Key.key("update_tags"), UPDATE_TAGS_ID),
        Map.entry(Key.key("waypoint"), WAYPOINT_ID)
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
