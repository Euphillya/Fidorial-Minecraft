package fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import fr.euphyllia.fidorial.server.network.protocol.packet.listener.PlayPacketListener;
import fr.fidorial.protocol.PacketListener;
import fr.fidorial.protocol.ServerboundPacket;

public record ServerboundMovePlayerPosPacket(PositionData.Vec3D position, int flags)
        implements ServerboundPacket {

    public static ServerboundMovePlayerPosPacket read(final PacketBuffer buf) {
        final PositionData.Vec3D position = PositionData.Vec3D.readFrom(buf);
        final int flags = buf.readUByte();
        return new ServerboundMovePlayerPosPacket(position, flags);
    }

    @Override
    public void handle(final PacketListener listener) {
        ((PlayPacketListener) listener).handleMovePlayerPos(this);
    }
}
