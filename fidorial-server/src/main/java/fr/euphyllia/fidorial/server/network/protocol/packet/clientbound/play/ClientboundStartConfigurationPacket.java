package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import net.kyori.adventure.key.Key;

// https://minecraft.wiki/w/Java_Edition_protocol/Packets#Start_Configuration
// used when registries are updated to resync their contents to clients
public record ClientboundStartConfigurationPacket() implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.START_CONFIGURATION;
    }

    @Override
    public void write(PacketBuffer buf) {
    }
}
