package fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play;

import fr.euphyllia.fidorial.server.network.protocol.packet.listener.PlayPacketListener;
import fr.fidorial.protocol.PacketListener;
import fr.fidorial.protocol.ServerboundPacket;

// https://minecraft.wiki/w/Java_Edition_protocol/Packets#Acknowledge_Configuration
public record ServerboundAcknowledgeConfigurationPacket() implements ServerboundPacket {

    @Override
    public void handle(PacketListener listener) {
        ((PlayPacketListener) listener).handleAcknowledgeConfiguration(this);
    }
}
