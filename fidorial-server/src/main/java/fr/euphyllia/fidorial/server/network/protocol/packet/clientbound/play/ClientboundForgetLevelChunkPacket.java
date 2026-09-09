package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import net.kyori.adventure.key.Key;

// https://minecraft.wiki/w/Java_Edition_protocol/Packets#Unload_Chunk
public record ClientboundForgetLevelChunkPacket(int chunkX, int chunkZ) implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.FORGET_LEVEL_CHUNK;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeInt(chunkZ).writeInt(chunkX);
    }
}
