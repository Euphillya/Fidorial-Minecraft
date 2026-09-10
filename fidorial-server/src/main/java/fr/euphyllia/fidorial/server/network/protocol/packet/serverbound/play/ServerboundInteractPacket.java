package fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import fr.euphyllia.fidorial.server.network.protocol.packet.listener.PlayPacketListener;
import fr.fidorial.protocol.PacketListener;
import fr.fidorial.protocol.ServerboundPacket;

/**
 * Sent when the player right-clicks an entity.
 *
 * <p><a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets#Interact">Interact</a></p>
 *
 * @param entityId the entity being used
 * @param hand     {@link #HAND_MAIN} or {@link #HAND_OFF}
 * @param relativePosition   the hit position relative to the target
 * @param sneaking whether the player was holding the sneak key, which suppresses most interactions
 */
public record ServerboundInteractPacket(
        int entityId, int hand, PositionData.VelocityVec3D relativePosition, boolean sneaking)
        implements ServerboundPacket {

    public static final int HAND_MAIN = 0;
    public static final int HAND_OFF = 1;

    public static ServerboundInteractPacket read(final PacketBuffer buf) {
        final int entityId = buf.readVarInt();
        final int hand = buf.readVarInt();
        final PositionData.VelocityVec3D relativePosition = PositionData.VelocityVec3D.readFrom(buf);
        final boolean sneaking = buf.readBoolean();
        return new ServerboundInteractPacket(entityId, hand, relativePosition, sneaking);
    }

    public boolean isOffHand() {
        return hand == HAND_OFF;
    }

    @Override
    public void handle(final PacketListener listener) {
        ((PlayPacketListener) listener).handleInteract(this);
    }
}
