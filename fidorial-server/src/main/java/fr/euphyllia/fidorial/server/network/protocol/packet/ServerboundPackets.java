package fr.euphyllia.fidorial.server.network.protocol.packet;

import fr.euphyllia.fidorial.server.network.ConnectionState;
import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.ConfigurationServerboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.catalog.HandshakeServerboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.catalog.LoginServerboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayServerboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.catalog.StatusServerboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.common.ServerboundClientInformationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundAcceptCodeOfConductPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundFinishConfigurationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundResourcePackPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundSelectKnownPacksPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.handshake.ServerboundIntentionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.login.ServerboundCustomQueryAnswerPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.login.ServerboundHelloPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.login.ServerboundKeyPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.login.ServerboundLoginAcknowledgedPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundAcceptTeleportationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundAttackPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundChatCommandPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundChatPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundClientCommandPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundCommandSuggestionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundContainerClickPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundContainerClosePacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundCustomClickActionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundInteractPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundKeepAlivePacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundMovePlayerPosPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundMovePlayerPosRotPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerActionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerInputPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerLoadedPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundSetCarriedItemPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundSetCreativeModeSlotPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundSwingPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundUseItemOnPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.status.ServerboundPingRequestPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.status.ServerboundStatusRequestPacket;
import fr.fidorial.protocol.ServerboundPacket;
import net.kyori.adventure.key.Key;
import org.jspecify.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ServerboundPackets {

    private static final Map<ConnectionState, Map<Key, Reader>> READERS = new EnumMap<>(ConnectionState.class);

    static {
        register(ConnectionState.HANDSHAKE, HandshakeServerboundPackets.INTENTION, ServerboundIntentionPacket::read);

        register(ConnectionState.STATUS, StatusServerboundPackets.STATUS_REQUEST, ServerboundStatusRequestPacket::read);
        register(ConnectionState.STATUS, StatusServerboundPackets.PING_REQUEST, ServerboundPingRequestPacket::read);

        register(ConnectionState.LOGIN, LoginServerboundPackets.HELLO, ServerboundHelloPacket::read);
        register(ConnectionState.LOGIN, LoginServerboundPackets.KEY, ServerboundKeyPacket::read);
        register(
                ConnectionState.LOGIN,
                LoginServerboundPackets.CUSTOM_QUERY_ANSWER,
                ServerboundCustomQueryAnswerPacket::read);
        register(
                ConnectionState.LOGIN,
                LoginServerboundPackets.LOGIN_ACKNOWLEDGED,
                ServerboundLoginAcknowledgedPacket::read);

        register(
                ConnectionState.CONFIGURATION,
                ConfigurationServerboundPackets.SELECT_KNOWN_PACKS,
                ServerboundSelectKnownPacksPacket::read);
        register(
                ConnectionState.CONFIGURATION,
                ConfigurationServerboundPackets.CLIENT_INFORMATION,
                ServerboundClientInformationPacket::read);
        register(
                ConnectionState.CONFIGURATION,
                ConfigurationServerboundPackets.CUSTOM_CLICK_ACTION,
                fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundCustomClickActionPacket::read);
        register(
                ConnectionState.CONFIGURATION,
                ConfigurationServerboundPackets.RESOURCE_PACK,
                ServerboundResourcePackPacket::read);
        register(
                ConnectionState.CONFIGURATION,
                ConfigurationServerboundPackets.FINISH_CONFIGURATION,
                ServerboundFinishConfigurationPacket::read);
        register(
                ConnectionState.CONFIGURATION,
                ConfigurationServerboundPackets.ACCEPT_CODE_OF_CONDUCT,
                ServerboundAcceptCodeOfConductPacket::read);

        register(ConnectionState.PLAY, PlayServerboundPackets.PLAYER_LOADED, ServerboundPlayerLoadedPacket::read);
        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.ACCEPT_TELEPORTATION,
                ServerboundAcceptTeleportationPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.KEEP_ALIVE, ServerboundKeepAlivePacket::read);
        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.SET_CREATIVE_MODE_SLOT,
                ServerboundSetCreativeModeSlotPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.USE_ITEM_ON, ServerboundUseItemOnPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.PLAYER_ACTION, ServerboundPlayerActionPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.SET_CARRIED_ITEM, ServerboundSetCarriedItemPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.MOVE_PLAYER_POS, ServerboundMovePlayerPosPacket::read);
        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.MOVE_PLAYER_POS_ROT,
                ServerboundMovePlayerPosRotPacket::read);
        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.CLIENT_INFORMATION,
                ServerboundClientInformationPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.CHAT_COMMAND, ServerboundChatCommandPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.CHAT, ServerboundChatPacket::read);
        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.COMMAND_SUGGESTION,
                ServerboundCommandSuggestionPacket::read);
        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.CONTAINER_CLICK,
                ServerboundContainerClickPacket::read);
        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.CONTAINER_CLOSE,
                ServerboundContainerClosePacket::read);

        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.CUSTOM_CLICK_ACTION,
                ServerboundCustomClickActionPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.RESOURCE_PACK,
                fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundResourcePackPacket::read);

        register(ConnectionState.PLAY, PlayServerboundPackets.ATTACK, ServerboundAttackPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.INTERACT, ServerboundInteractPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.SWING, ServerboundSwingPacket::read);
        register(
                ConnectionState.PLAY,
                PlayServerboundPackets.CLIENT_COMMAND,
                ServerboundClientCommandPacket::read);
        register(ConnectionState.PLAY, PlayServerboundPackets.PLAYER_INPUT, ServerboundPlayerInputPacket::read);
    }

    private ServerboundPackets() {
    }

    private static void register(final ConnectionState state, final Key name, final Reader reader) {
        READERS.computeIfAbsent(state, s -> new HashMap<>()).put(name, reader);
    }

    public static @Nullable ServerboundPacket decode(final ConnectionState state, final Key name, final PacketBuffer buf) {
        final Reader reader = READERS.getOrDefault(state, Map.of()).get(name);
        return reader == null ? null : reader.read(buf);
    }

    @FunctionalInterface
    public interface Reader {
        ServerboundPacket read(PacketBuffer buf);
    }
}
