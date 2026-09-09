package fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.packet.listener.PlayPacketListener;
import fr.fidorial.protocol.PacketListener;
import fr.fidorial.protocol.ServerboundPacket;

/**
 * A player clicking a slot in a window.
 *
 * <p>Format (26.2):
 *
 * <pre>
 *   Window ID     VarInt
 *   State ID      VarInt
 *   Slot          Short          (-999 = outside the window)
 *   Button        Byte
 *   Mode          VarInt Enum
 *   Changed slots Prefixed Array (128) of (Short, Hashed Slot)
 *   Carried item  Hashed Slot
 * </pre>
 *
 *
 * <p>https://minecraft.wiki/w/Java_Edition_protocol/Packets#Click_Container
 */
public record ServerboundContainerClickPacket(int windowId, int stateId, short slot, byte button, int mode)
        implements ServerboundPacket {

    public static final short SLOT_OUTSIDE = -999;

    // Modes (see the Mode/Button table in the protocol).
    public static final int MODE_PICKUP = 0;
    public static final int MODE_QUICK_MOVE = 1;
    public static final int MODE_SWAP = 2;
    public static final int MODE_CLONE = 3;
    public static final int MODE_THROW = 4;
    public static final int MODE_QUICK_CRAFT = 5;
    public static final int MODE_PICKUP_ALL = 6;

    public static ServerboundContainerClickPacket read(final PacketBuffer buf) {
        final int windowId = buf.readVarInt();
        final int stateId = buf.readVarInt();
        final short slot = buf.readShort();
        final byte button = buf.readByte();
        final int mode = buf.readVarInt();

        // Changed slots + carried item: ignored, we fully resynchronize afterwards.
        if (buf.readableBytes() > 0) {
            buf.readRemainingBytes();
        }
        return new ServerboundContainerClickPacket(windowId, stateId, slot, button, mode);
    }

    @Override
    public void handle(final PacketListener listener) {
        ((PlayPacketListener) listener).handleContainerClick(this);
    }
}
