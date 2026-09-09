package fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.packet.listener.PlayPacketListener;
import fr.fidorial.protocol.PacketListener;
import fr.fidorial.protocol.ServerboundPacket;

/**
 * Sent by the client when it closes a window.
 *
 * <p>Format:
 *
 * <pre>
 *   Window ID     VarInt
 * </pre>
 *
 * <p>Also sent with Window ID 0 when the client closes its own inventory, even though no
 * {@code open_screen} was ever sent for it.
 *
 * <p>https://minecraft.wiki/w/Java_Edition_protocol/Packets#Close_Container_2
 */
public record ServerboundContainerClosePacket(int windowId) implements ServerboundPacket {

    public static ServerboundContainerClosePacket read(final PacketBuffer buf) {
        return new ServerboundContainerClosePacket(buf.readVarInt());
    }

    @Override
    public void handle(final PacketListener listener) {
        ((PlayPacketListener) listener).handleContainerClose(this);
    }
}
