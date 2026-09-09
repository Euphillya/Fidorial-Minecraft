package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import net.kyori.adventure.key.Key;

/**
 * https://minecraft.wiki/w/Java_Edition_protocol/Packets#Set_Entity_Velocity
 */
public record ClientboundSetEntityMotionPacket(int entityId,
                                               double velocityX, double velocityY, double velocityZ)
        implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.SET_ENTITY_MOTION;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarInt(entityId);
        buf.writeLpVec3(velocityX, velocityY, velocityZ);
    }
}
