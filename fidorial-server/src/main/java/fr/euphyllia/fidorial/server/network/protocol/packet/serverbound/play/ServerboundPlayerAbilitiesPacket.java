package fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.packet.listener.PlayPacketListener;
import fr.fidorial.protocol.PacketListener;
import fr.fidorial.protocol.ServerboundPacket;

// currently no-op
// https://minecraft.wiki/w/Java_Edition_protocol/Packets#Player_Abilities_(serverbound)
public record ServerboundPlayerAbilitiesPacket(boolean flying) implements ServerboundPacket {

    public static final byte FLAG_FLYING = 0x02;

    public static ServerboundPlayerAbilitiesPacket read(final PacketBuffer buf) {
        final byte flags = buf.readByte();
        return new ServerboundPlayerAbilitiesPacket((flags & FLAG_FLYING) != 0);
    }

    @Override
    public void handle(final PacketListener listener) {
        ((PlayPacketListener) listener).handlePlayerAbilities(this);
    }
}
