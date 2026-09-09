package fr.euphyllia.fidorial.server.world;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.registry.biome.FidorialBiomeRegistry;
import fr.euphyllia.fidorial.server.world.block.blockentity.BlockEntity;
import fr.euphyllia.fidorial.server.world.chunk.BitPacking;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.euphyllia.fidorial.server.world.chunk.ChunkColumn;
import fr.euphyllia.fidorial.server.world.chunk.ChunkSection;
import fr.euphyllia.fidorial.server.world.chunk.PalettedContainer;
import fr.euphyllia.fidorial.server.world.light.ChunkLightData;
import fr.fidorial.world.light.LightType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class ChunkNetworkSerializer {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ChunkNetworkSerializer.class);

    private final BlockStateRegistry blockRegistry;
    private final FidorialBiomeRegistry biomes;
    private static final int MAX_INDIRECT_BIOME_BITS = 3;

    public ChunkNetworkSerializer(final BlockStateRegistry blockRegistry, final FidorialBiomeRegistry biomes) {
        this.blockRegistry = blockRegistry;
        this.biomes = biomes;
    }

    public void writeChunk(final PacketBuffer p, final ByteBufAllocator alloc, final ChunkColumn chunk, final boolean hasSkylight) {
        p.writeInt(chunk.chunkX());
        p.writeInt(chunk.chunkZ());
        p.writeVarInt(0); // heightmaps : 0 → le client recalcule

        final byte[] sections = buildSections(alloc, chunk);
        p.writeByteArray(sections);

        writeBlockEntities(p, chunk);

        writeLightData(p, chunk, hasSkylight);
    }

    /**
     * Writes the {@code Block Entities} prefixed array of
     * {@code level_chunk_with_light}.
     *
     * <p>Each entry is a packed XZ byte, the absolute height as a short, the
     * {@code minecraft:block_entity_type} protocol ID as a VarInt, and the block
     * entity NBT stripped of its X, Y and Z values.</p>
     */
    private void writeBlockEntities(final PacketBuffer p, final ChunkColumn chunk) {

        final List<BlockEntity> encodable = new ArrayList<>();

        for (final BlockEntity blockEntity : chunk.blockEntities()) {
            if (blockEntity.isKnown()) {
                encodable.add(blockEntity);
            } else {
                LOGGER.warn("Skipping block entity with unknown type '{}' at {},{},{} in chunk {},{}.",
                        blockEntity.type(),
                        blockEntity.localX(),
                        blockEntity.y(),
                        blockEntity.localZ(),
                        chunk.chunkX(),
                        chunk.chunkZ());
            }
        }

        p.writeVarInt(encodable.size());

        for (final BlockEntity blockEntity : encodable) {
            p.writeByte(blockEntity.packedXz());
            p.writeShort(blockEntity.y());
            p.writeVarInt(blockEntity.protocolId());
            p.writeNbt(blockEntity.data());
            LOGGER.debug("BlockEntity : {} protocolId : {}", blockEntity.type(), blockEntity.protocolId());
        }
    }

    private byte[] buildSections(final ByteBufAllocator alloc, final ChunkColumn chunk) {
        final ByteBuf sec = alloc.buffer();
        try {
            final PacketBuffer sp = new PacketBuffer(sec);
            for (final ChunkSection section : chunk.sections()) {
                writeSection(sp, section);
            }
            final byte[] out = new byte[sec.readableBytes()];
            sec.readBytes(out);
            return out;
        } finally {
            sec.release();
        }
    }

    private void writeSection(final PacketBuffer sp, final ChunkSection section) {
        final PalettedContainer.PalettedContainerSnapshot<BlockState> snap = section.blocks().snapshot();

        final int[] occurrences = new int[snap.palette().size()];
        for (final int index : snap.data()) {
            occurrences[index]++;
        }

        int nonAir = 0;
        int fluid = 0;
        for (int index = 0; index < occurrences.length; index++) {
            final BlockState state = snap.palette().get(index);
            if (!state.isAir()) {
                nonAir += occurrences[index];
            }
            if (state.isFluid()) {
                fluid += occurrences[index];
            }
        }

        sp.writeShort(nonAir);   // nonEmptyBlockCount
        sp.writeShort(fluid);    // fluidCount


        if (snap.palette().size() == 1) {
            final int stateId = blockRegistry.networkId(snap.palette().getFirst());
            sp.writeByte(0);           // bitsPerEntry = 0 (single value)
            sp.writeVarInt(stateId);   // valeur unique, pas de tableau de longs
        } else {
            writeIndirectSection(sp, snap);
        }

        writeBiomes(sp, section.biomes());
    }

    private void writeIndirectSection(final PacketBuffer sp, final PalettedContainer.PalettedContainerSnapshot<BlockState> snap) {
        final int size = snap.palette().size();
        final int bits = Math.max(4, BitPacking.bitsFor(size, snap.minBits()));

        sp.writeByte(bits);
        sp.writeVarInt(size);
        for (final BlockState state : snap.palette()) {
            sp.writeVarInt(blockRegistry.networkId(state));
        }
        writeLongs(sp, BitPacking.pack(snap.data(), bits), bits, ChunkSection.BLOCK_COUNT);
    }

    private void writeBiomes(final PacketBuffer sp, final PalettedContainer<Key> container) {
        final PalettedContainer.PalettedContainerSnapshot<Key> snap = container.snapshot();
        final List<Key> palette = snap.palette();

        if (palette.size() == 1) {
            sp.writeByte(0);
            sp.writeVarInt(biomes.networkIdOrFallback(palette.getFirst()));
            return;
        }

        final int bits = BitPacking.bitsFor(palette.size(), 1);

        if (bits <= MAX_INDIRECT_BIOME_BITS) {
            sp.writeByte(bits);
            sp.writeVarInt(palette.size());
            for (final Key biome : palette) {
                sp.writeVarInt(biomes.networkIdOrFallback(biome));
            }
            writeLongs(sp, BitPacking.pack(snap.data(), bits), bits, ChunkSection.BIOME_COUNT);
            return;
        }

        final int directBits = BitPacking.bitsFor(biomes.totalRegistered(), 1);
        sp.writeByte(directBits);
        final int[] global = new int[snap.data().length];
        for (int i = 0; i < global.length; i++) {
            global[i] = biomes.networkIdOrFallback(snap.get(i));
        }
        writeLongs(sp, BitPacking.pack(global, directBits), directBits, ChunkSection.BIOME_COUNT);
    }

    public byte[] buildBiomes(final ByteBufAllocator alloc, final ChunkColumn chunk) {
        final ByteBuf buffer = alloc.buffer();
        try {
            final PacketBuffer out = new PacketBuffer(buffer);
            for (final ChunkSection section : chunk.sections()) {
                writeBiomes(out, section.biomes());
            }
            final byte[] encoded = new byte[buffer.readableBytes()];
            buffer.readBytes(encoded);
            return encoded;
        } finally {
            buffer.release();
        }
    }

    private static void writeLongs(final PacketBuffer sp, final long @Nullable [] data, final int bits, final int entries) {
        final int entriesPerLong = 64 / bits;
        final int expectedLongs = (entries + entriesPerLong - 1) / entriesPerLong;
        for (int i = 0; i < expectedLongs; i++) {
            sp.writeLong(data != null && i < data.length ? data[i] : 0L);
        }
    }

    public void writeLightData(final PacketBuffer p, final ChunkColumn chunk, final boolean hasSkylight) {
        writeLightData(p, chunk, hasSkylight, ChunkLightData.ALL_SECTIONS);
    }

    public void writeLightData(final PacketBuffer p, final ChunkColumn chunk, final boolean hasSkylight,
                               final long sectionMask) {
        final int worldSections = chunk.sectionCount();
        final int lightSections = worldSections + 2;

        final ChunkLightData light = chunk.lightData();

        final long[] skyMask = new long[(lightSections + 63) >> 6];
        final long[] blockMask = new long[skyMask.length];
        final long[] emptySkyMask = new long[skyMask.length];
        final long[] emptyBlockMask = new long[skyMask.length];

        final List<byte[]> skyArrays = new ArrayList<>();
        final List<byte[]> blockArrays = new ArrayList<>();

        final boolean lit = chunk.lightPopulated();

        final int minSectionY = light.minY() >> 4;
        final int highestBlocking = lit ? light.highestBlockingY() : Integer.MAX_VALUE;

        final boolean fullSend = sectionMask == ChunkLightData.ALL_SECTIONS;

        for (int i = 0; i < lightSections; i++) {
            final int sectionBottomY = (minSectionY + i - 1) << 4;
            final boolean insideWorld = i >= 1 && i <= worldSections;

            if (!fullSend) {
                final int storageIndex = i - 1;
                if (storageIndex < 0 || storageIndex >= 64
                        || (sectionMask & (1L << storageIndex)) == 0L) {
                    continue;
                }
            }

            if (hasSkylight) {
                if (sectionBottomY <= highestBlocking) {
                    final byte @Nullable [] sky = light.materializeSkySection(i - 1);
                    if (sky == null || isAllZero(sky)) {
                        setBit(emptySkyMask, i);
                    } else {
                        setBit(skyMask, i);
                        skyArrays.add(sky);
                    }
                }
            }

            final byte @Nullable [] block = insideWorld
                    ? light.sectionArray(LightType.BLOCK, i - 1)
                    : null;

            if (block != null && !isAllZero(block)) {
                setBit(blockMask, i);
                blockArrays.add(block);
            } else if (lit && insideWorld) {
                setBit(emptyBlockMask, i);
            }
        }

        p.writeBitSet(skyMask);
        p.writeBitSet(blockMask);
        p.writeBitSet(emptySkyMask);
        p.writeBitSet(emptyBlockMask);

        p.writeVarInt(skyArrays.size());
        for (final byte[] array : skyArrays) {
            p.writeVarInt(ChunkLightData.SECTION_BYTES);
            p.writeRawBytes(array);
        }

        p.writeVarInt(blockArrays.size());
        for (final byte[] array : blockArrays) {
            p.writeVarInt(ChunkLightData.SECTION_BYTES);
            p.writeRawBytes(array);
        }
    }

    private static void setBit(final long[] words, final int bit) {
        words[bit >> 6] |= 1L << (bit & 63);
    }

    private static boolean isAllZero(final byte[] a) {
        for (final byte b : a) if (b != 0) return false;
        return true;
    }

}
