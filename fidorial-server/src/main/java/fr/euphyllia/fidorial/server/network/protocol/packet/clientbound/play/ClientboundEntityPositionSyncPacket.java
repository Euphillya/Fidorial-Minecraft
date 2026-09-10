package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import net.kyori.adventure.key.Key;

// https://minecraft.wiki/w/Java_Edition_protocol/Packets#Teleport_Entity
public record ClientboundEntityPositionSyncPacket(
        int entityId,
        PositionData.LinearPositionPath position,
        PositionData.FloatRotation rotation,
        boolean onGround)
        implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.ENTITY_POSITION_SYNC;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarInt(entityId);
        position.writeTo(buf);
        rotation.writeTo(buf);
        buf.writeBoolean(onGround);
    }
}
