package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import net.kyori.adventure.key.Key;

// https://minecraft.wiki/w/Java_Edition_protocol/Packets#Update_Entity_Position
public record ClientboundMoveEntityPosPacket(int entityId, PositionData.DeltaVec3D delta, boolean onGround)
        implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.MOVE_ENTITY_POS;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarInt(entityId);
        buf.writeBoolean(onGround);
        delta.writeTo(buf);
    }
}
