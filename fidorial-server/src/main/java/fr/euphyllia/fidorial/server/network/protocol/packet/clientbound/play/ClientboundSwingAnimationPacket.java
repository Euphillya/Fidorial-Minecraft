package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.fidorial.item.component.SwingAnimation;
import net.kyori.adventure.key.Key;

public record ClientboundSwingAnimationPacket(int entityId, boolean mainHand, SwingAnimation animation) implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.SWING_ANIMATION;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeVarInt(entityId);
        buf.writeVarInt(mainHand ? 0 : 1); // 0 = main_hand, 1 = off_hand
        buf.writeVarInt(animation.type() == SwingAnimation.SwingAnimationType.STAB ? 2 : 1);
        buf.writeVarInt(animation.duration());
    }
}
