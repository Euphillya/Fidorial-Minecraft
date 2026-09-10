package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import net.kyori.adventure.key.Key;

public record ClientboundPlayerPositionPacket(int teleportId, PositionData.PositionMoveRotationData moveData)
        implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.PLAYER_POSITION;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarInt(teleportId);
        moveData.writeTo(buf);
        buf.writeInt(0);                                    // flags (tout absolu)
    }
}
