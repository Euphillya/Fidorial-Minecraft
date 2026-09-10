package fr.euphyllia.fidorial.server.network.protocol.catalog;

import java.util.Map;
import net.kyori.adventure.key.Key;

/**
 * Network IDs for the {@code configuration/clientbound} state/direction.
 *
 * <p>Generated from Mojang's packets report; do not edit.</p>
 */
public interface ConfigurationClientboundPacketIds {
    /**
     * Returned by {@link #id(Key)} when the identifier is unknown.
     */
    int UNKNOWN = -1;

    /**
     * {@code minecraft:clear_dialog}
     */
    int CLEAR_DIALOG_ID = 18;

    /**
     * {@code minecraft:code_of_conduct}
     */
    int CODE_OF_CONDUCT_ID = 20;

    /**
     * {@code minecraft:cookie_request}
     */
    int COOKIE_REQUEST_ID = 0;

    /**
     * {@code minecraft:custom_payload}
     */
    int CUSTOM_PAYLOAD_ID = 1;

    /**
     * {@code minecraft:custom_report_details}
     */
    int CUSTOM_REPORT_DETAILS_ID = 16;

    /**
     * {@code minecraft:disconnect}
     */
    int DISCONNECT_ID = 2;

    /**
     * {@code minecraft:finish_configuration}
     */
    int FINISH_CONFIGURATION_ID = 3;

    /**
     * {@code minecraft:keep_alive}
     */
    int KEEP_ALIVE_ID = 4;

    /**
     * {@code minecraft:ping}
     */
    int PING_ID = 5;

    /**
     * {@code minecraft:post_effects}
     */
    int POST_EFFECTS_ID = 10;

    /**
     * {@code minecraft:registry_data}
     */
    int REGISTRY_DATA_ID = 7;

    /**
     * {@code minecraft:reset_chat}
     */
    int RESET_CHAT_ID = 6;

    /**
     * {@code minecraft:resource_pack_pop}
     */
    int RESOURCE_PACK_POP_ID = 8;

    /**
     * {@code minecraft:resource_pack_push}
     */
    int RESOURCE_PACK_PUSH_ID = 9;

    /**
     * {@code minecraft:select_known_packs}
     */
    int SELECT_KNOWN_PACKS_ID = 15;

    /**
     * {@code minecraft:server_links}
     */
    int SERVER_LINKS_ID = 17;

    /**
     * {@code minecraft:show_dialog}
     */
    int SHOW_DIALOG_ID = 19;

    /**
     * {@code minecraft:store_cookie}
     */
    int STORE_COOKIE_ID = 11;

    /**
     * {@code minecraft:transfer}
     */
    int TRANSFER_ID = 12;

    /**
     * {@code minecraft:update_enabled_features}
     */
    int UPDATE_ENABLED_FEATURES_ID = 13;

    /**
     * {@code minecraft:update_tags}
     */
    int UPDATE_TAGS_ID = 14;

    /**
     * Immutable identifier to protocol ID lookup table.
     */
    Map<Key, Integer> BY_IDENTIFIER = Map.ofEntries(
        Map.entry(Key.key("clear_dialog"), CLEAR_DIALOG_ID),
        Map.entry(Key.key("code_of_conduct"), CODE_OF_CONDUCT_ID),
        Map.entry(Key.key("cookie_request"), COOKIE_REQUEST_ID),
        Map.entry(Key.key("custom_payload"), CUSTOM_PAYLOAD_ID),
        Map.entry(Key.key("custom_report_details"), CUSTOM_REPORT_DETAILS_ID),
        Map.entry(Key.key("disconnect"), DISCONNECT_ID),
        Map.entry(Key.key("finish_configuration"), FINISH_CONFIGURATION_ID),
        Map.entry(Key.key("keep_alive"), KEEP_ALIVE_ID),
        Map.entry(Key.key("ping"), PING_ID),
        Map.entry(Key.key("post_effects"), POST_EFFECTS_ID),
        Map.entry(Key.key("registry_data"), REGISTRY_DATA_ID),
        Map.entry(Key.key("reset_chat"), RESET_CHAT_ID),
        Map.entry(Key.key("resource_pack_pop"), RESOURCE_PACK_POP_ID),
        Map.entry(Key.key("resource_pack_push"), RESOURCE_PACK_PUSH_ID),
        Map.entry(Key.key("select_known_packs"), SELECT_KNOWN_PACKS_ID),
        Map.entry(Key.key("server_links"), SERVER_LINKS_ID),
        Map.entry(Key.key("show_dialog"), SHOW_DIALOG_ID),
        Map.entry(Key.key("store_cookie"), STORE_COOKIE_ID),
        Map.entry(Key.key("transfer"), TRANSFER_ID),
        Map.entry(Key.key("update_enabled_features"), UPDATE_ENABLED_FEATURES_ID),
        Map.entry(Key.key("update_tags"), UPDATE_TAGS_ID)
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
