package fr.euphyllia.fidorial.server.network.protocol.packet.listener;

import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.common.ServerboundClientInformationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundAcceptTeleportationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundAcknowledgeConfigurationPacket;
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
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerAbilitiesPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerActionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerInputPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerLoadedPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPunchPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundResourcePackPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundSetCarriedItemPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundSetCreativeModeSlotPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundUseItemOnPacket;
import fr.fidorial.protocol.PacketListener;

public interface PlayPacketListener extends PacketListener {
    void handlePlayerLoaded(ServerboundPlayerLoadedPacket packet);

    void handleAcceptTeleportation(ServerboundAcceptTeleportationPacket packet);

    void handleAcknowledgeConfiguration(ServerboundAcknowledgeConfigurationPacket packet);

    void handleKeepAlive(ServerboundKeepAlivePacket packet);

    void handleSetCreativeModeSlot(ServerboundSetCreativeModeSlotPacket packet);

    void handleSetCarriedItem(ServerboundSetCarriedItemPacket packet);

    void handleContainerClick(ServerboundContainerClickPacket packet);

    void handleContainerClose(ServerboundContainerClosePacket packet);

    void handleCustomClickAction(ServerboundCustomClickActionPacket packet);

    void handleUseItemOn(ServerboundUseItemOnPacket packet);

    void handlePlayerAction(ServerboundPlayerActionPacket packet);

    void handleCommandSuggestion(ServerboundCommandSuggestionPacket packet);

    void handlePlayerAbilities(ServerboundPlayerAbilitiesPacket packet);

    void handleMovePlayerPos(ServerboundMovePlayerPosPacket packet);

    void handleMovePlayerPosRot(ServerboundMovePlayerPosRotPacket packet);

    void handleClientInformation(ServerboundClientInformationPacket packet);

    void handleChatCommand(ServerboundChatCommandPacket packet);

    void handleChat(ServerboundChatPacket packet);

    void handleAttack(ServerboundAttackPacket packet);

    void handleInteract(ServerboundInteractPacket packet);

    void handlePunch(ServerboundPunchPacket packet);

    void handleClientCommand(ServerboundClientCommandPacket packet);

    void handleResourcePackResponse(ServerboundResourcePackPacket packet);

    void handlePlayerInput(ServerboundPlayerInputPacket packet);
}
