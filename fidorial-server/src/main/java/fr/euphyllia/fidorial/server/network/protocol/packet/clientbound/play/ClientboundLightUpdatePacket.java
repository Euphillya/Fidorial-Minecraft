package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.world.ChunkNetworkSerializer;
import fr.euphyllia.fidorial.server.world.chunk.ChunkColumn;
import fr.euphyllia.fidorial.server.world.light.ChunkLightData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import net.kyori.adventure.key.Key;


public final class ClientboundLightUpdatePacket implements ClientboundPacket {

    private final byte[] payload;

    public ClientboundLightUpdatePacket(final ChunkNetworkSerializer serializer, final ChunkColumn column, final boolean hasSkylight) {
        this(serializer, column, ByteBufAllocator.DEFAULT, hasSkylight, ChunkLightData.ALL_SECTIONS);
    }

    public ClientboundLightUpdatePacket(
            final ChunkNetworkSerializer serializer,
            final ChunkColumn column,
            final ByteBufAllocator allocator,
            final boolean hasSkylight,
            final long sectionMask) {

        final ByteBuf scratch = allocator.buffer();
        try {
            final PacketBuffer out = new PacketBuffer(scratch);
            out.writeVarInt(column.chunkX());
            out.writeVarInt(column.chunkZ());
            serializer.writeLightData(out, column, hasSkylight, sectionMask);
            this.payload = new byte[scratch.readableBytes()];
            scratch.readBytes(this.payload);
        } finally {
            scratch.release();
        }
    }

    @Override
    public Key name() {
        return PlayClientboundPackets.LIGHT_UPDATE;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeRawBytes(payload);
    }
}
