package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import net.kyori.adventure.key.Key;

/**
 * https://minecraft.wiki/w/Java_Edition_protocol/Packets#Set_Entity_Velocity
 */
public record ClientboundSetEntityMotionPacket(int entityId, PositionData.VelocityVec3D velocity)
        implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.SET_ENTITY_MOTION;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarInt(entityId);
        velocity.writeTo(buf);
    }
}
