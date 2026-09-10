package fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.packet.listener.PlayPacketListener;
import fr.fidorial.protocol.PacketListener;
import fr.fidorial.protocol.ServerboundPacket;

public record ServerboundPunchPacket() implements ServerboundPacket {

    public static ServerboundPunchPacket read(final PacketBuffer buf) {
        return new ServerboundPunchPacket();
    }

    @Override
    public void handle(final PacketListener listener) {
        ((PlayPacketListener) listener).handlePunch(this);
    }
}
