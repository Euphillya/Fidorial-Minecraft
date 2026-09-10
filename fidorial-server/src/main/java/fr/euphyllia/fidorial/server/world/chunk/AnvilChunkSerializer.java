package fr.euphyllia.fidorial.server.world.chunk;

import fr.euphyllia.fidorial.server.VersionConstants;
import fr.euphyllia.fidorial.server.world.block.blockentity.BlockEntity;
import fr.euphyllia.fidorial.server.world.light.ChunkLightData;
import fr.fidorial.world.light.LightType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AnvilChunkSerializer {

    private final int dataVersion;

    public AnvilChunkSerializer() {
        this(VersionConstants.DATA_VERSION);
    }

    public AnvilChunkSerializer(final int dataVersion) {
        this.dataVersion = dataVersion;
    }

    public CompoundBinaryTag toNbt(final ChunkColumn chunk) {
        final CompoundBinaryTag.Builder root = CompoundBinaryTag.builder();
        root.putInt("DataVersion", dataVersion);
        root.putInt("xPos", chunk.chunkX());
        root.putInt("zPos", chunk.chunkZ());
        root.putInt("yPos", chunk.minSectionY());
        root.putString("Status", chunk.status().asString());
        root.putLong("LastUpdate", chunk.lastUpdate());
        root.putLong("InhabitedTime", chunk.inhabitedTime());
        root.putBoolean("isLightOn", chunk.lightPopulated());

        final ChunkLightData light = chunk.lightData();
        final int minSectionY = chunk.minSectionY();

        final ListBinaryTag.Builder<BinaryTag> sections = ListBinaryTag.builder();
        for (final ChunkSection section : chunk.sections()) {
            final int lightIndex = section.sectionY() - minSectionY;
            final byte[] blockLight = light.sectionArray(LightType.BLOCK, lightIndex);
            final byte[] skyLight = light.sectionArray(LightType.SKY, lightIndex);
            sections.add(sectionToNbt(section, blockLight, skyLight));
        }
        root.put("sections", sections.build());

        root.putIntArray("LightHeightmap", light.heightmapSnapshot());

        final CompoundBinaryTag.Builder heightmaps = CompoundBinaryTag.builder();
        final long[] motionBlocking = chunk.computeMotionBlockingHeightmap();
        heightmaps.putLongArray("MOTION_BLOCKING", motionBlocking);
        heightmaps.putLongArray("WORLD_SURFACE", motionBlocking.clone());
        root.put("Heightmaps", heightmaps.build());

        root.put("block_entities", blockEntitiesToNbt(chunk));
        root.put("block_ticks", ListBinaryTag.empty());
        root.put("fluid_ticks", ListBinaryTag.empty());
        return root.build();
    }

    /**
     * Keys owned by the Anvil container itself; they are stripped from the
     * payload kept in memory because the protocol sends the block entity data
     * without its X, Y and Z values.
     */
    private static final Set<String> BLOCK_ENTITY_CONTAINER_KEYS = Set.of("id", "x", "y", "z", "keepPacked");

    private ListBinaryTag blockEntitiesToNbt(final ChunkColumn chunk) {
        final ListBinaryTag.Builder<BinaryTag> list = ListBinaryTag.builder();

        for (final BlockEntity blockEntity : chunk.blockEntities()) {
            final CompoundBinaryTag.Builder compound = CompoundBinaryTag.builder();

            final CompoundBinaryTag data = blockEntity.data();
            if (data != null) {
                for (final String key : data.keySet()) {
                    if (!BLOCK_ENTITY_CONTAINER_KEYS.contains(key)) {
                        compound.put(key, data.get(key));
                    }
                }
            }

            compound.putString("id", blockEntity.type().asString());
            compound.putInt("x", (chunk.chunkX() << 4) + blockEntity.localX());
            compound.putInt("y", blockEntity.y());
            compound.putInt("z", (chunk.chunkZ() << 4) + blockEntity.localZ());
            compound.putBoolean("keepPacked", false);

            list.add(compound.build());
        }

        return list.build();
    }

    private void blockEntitiesFromNbt(final CompoundBinaryTag root, final ChunkColumn chunk) {
        for (final BinaryTag tag : root.getList("block_entities")) {
            if (!(tag instanceof final CompoundBinaryTag compound) || !compound.contains("id")) {
                continue;
            }

            final int localX = compound.getInt("x") & 15;
            final int localZ = compound.getInt("z") & 15;
            final int y = compound.getInt("y");

            CompoundBinaryTag.Builder data = CompoundBinaryTag.builder();
            for (final String key : compound.keySet()) {
                if (!BLOCK_ENTITY_CONTAINER_KEYS.contains(key)) {
                    data.put(key, compound.get(key));
                }
            }
            final CompoundBinaryTag built = data.build();

            chunk.putBlockEntity(new BlockEntity(localX,
                    y,
                    localZ,
                    Key.key(compound.getString("id")),
                    built.keySet().isEmpty() ? null : built));
        }
    }

    private CompoundBinaryTag sectionToNbt(final ChunkSection section, final byte @Nullable [] blockLight, final byte @Nullable [] skyLight) {
        final CompoundBinaryTag.Builder c = CompoundBinaryTag.builder();
        c.putByte("Y", (byte) section.sectionY());

        if (blockLight != null) {
            c.putByteArray("BlockLight", blockLight);
        }
        if (skyLight != null) {
            c.putByteArray("SkyLight", skyLight);
        }

        // block_states
        final CompoundBinaryTag.Builder blockStates = CompoundBinaryTag.builder();
        final ListBinaryTag.Builder<BinaryTag> blockPalette = ListBinaryTag.builder();
        for (final BlockState state : section.blocks().palette()) {
            blockPalette.add(blockStateToNbt(state));
        }
        blockStates.put("palette", blockPalette.build());
        final long[] blockData = section.blocks().packedData();
        if (blockData != null) {
            blockStates.putLongArray("tool/data", blockData);
        }
        c.put("block_states", blockStates.build());

        // biomes
        final CompoundBinaryTag.Builder biomes = CompoundBinaryTag.builder();
        final ListBinaryTag.Builder<BinaryTag> biomePalette = ListBinaryTag.builder();
        for (final Key biome : section.biomes().palette()) {
            biomePalette.add(StringBinaryTag.stringBinaryTag(biome.asString()));
        }
        biomes.put("palette", biomePalette.build());
        final long[] biomeData = section.biomes().packedData();
        if (biomeData != null) {
            biomes.putLongArray("tool/data", biomeData);
        }
        c.put("biomes", biomes.build());

        return c.build();
    }

    private CompoundBinaryTag blockStateToNbt(final BlockState state) {
        final CompoundBinaryTag.Builder c = CompoundBinaryTag.builder();
        c.putString("Name", state.name().asString());
        if (!state.properties().isEmpty()) {
            final CompoundBinaryTag.Builder props = CompoundBinaryTag.builder();
            for (final var e : state.properties().entrySet()) {
                props.putString(e.getKey(), e.getValue());
            }
            c.put("Properties", props.build());
        }
        return c.build();
    }

    public ChunkColumn fromNbt(final CompoundBinaryTag root, final int minY, final int height, final BlockState defaultBlock, final Key defaultBiome) {
        final int chunkX = root.getInt("xPos");
        final int chunkZ = root.getInt("zPos");

        final ChunkColumn chunk = new ChunkColumn(chunkX, chunkZ, minY, height, defaultBlock, defaultBiome);
        chunk.setStatus(root.contains("Status") ? Key.key(root.getString("Status")) : Key.key("full"));
        chunk.setInhabitedTime(root.getLong("InhabitedTime"));
        chunk.setLastUpdate(root.getLong("LastUpdate"));

        final ChunkLightData light = chunk.lightData();
        final int minSectionY = chunk.minSectionY();

        for (final BinaryTag tag : root.getList("sections")) {
            if (tag instanceof final CompoundBinaryTag sc) {
                final ChunkSection section = sectionFromNbt(sc, defaultBlock, defaultBiome);
                if (section != null) {
                    chunk.putSection(section);
                    final int lightIndex = section.sectionY() - minSectionY;
                    if (sc.contains("BlockLight")) {
                        light.setSectionArray(LightType.BLOCK, lightIndex, sc.getByteArray("BlockLight"));
                    }
                    if (sc.contains("SkyLight")) {
                        light.setSectionArray(LightType.SKY, lightIndex, sc.getByteArray("SkyLight"));
                    }
                }
            }
        }

        blockEntitiesFromNbt(root, chunk);

        if (root.contains("LightHeightmap")) {
            light.restoreHeightmap(root.getIntArray("LightHeightmap"));
            chunk.setLightPopulated(root.getBoolean("isLightOn"));
        }

        return chunk;
    }

    private @Nullable ChunkSection sectionFromNbt(final CompoundBinaryTag c, final BlockState defaultBlock, final Key defaultBiome) {
        if (!c.contains("Y")) return null;
        final int sectionY = c.getByte("Y");

        // block_states
        final List<BlockState> blockPalette = new ArrayList<>();
        final CompoundBinaryTag bs = c.getCompound("block_states");
        for (final BinaryTag t : bs.getList("palette")) {
            if (t instanceof final CompoundBinaryTag entry) {
                blockPalette.add(blockStateFromNbt(entry));
            }
        }
        if (blockPalette.isEmpty()) blockPalette.add(defaultBlock);
        final PalettedContainer<BlockState> blocks =
                PalettedContainer.fromNbt(ChunkSection.BLOCK_COUNT, 4, blockPalette, bs.getLongArray("tool/data"));

        // biomes
        final List<Key> biomePalette = new ArrayList<>();
        final CompoundBinaryTag bio = c.getCompound("biomes");
        for (final BinaryTag t : bio.getList("palette")) {
            if (t instanceof StringBinaryTag st) biomePalette.add(Key.key(st.value()));
        }
        if (biomePalette.isEmpty()) biomePalette.add(defaultBiome);
        final PalettedContainer<Key> biomes =
                PalettedContainer.fromNbt(ChunkSection.BIOME_COUNT, 1, biomePalette, bio.getLongArray("tool/data"));

        return new ChunkSection(sectionY, blocks, biomes);
    }

    private BlockState blockStateFromNbt(final CompoundBinaryTag c) {
        final String name = c.getString("Name");
        final CompoundBinaryTag props = c.getCompound("Properties");
        if (props.keySet().isEmpty()) {
            return BlockState.of(Key.key(name));
        }
        final Map<String, String> map = new TreeMap<>();
        for (final String key : props.keySet()) {
            if (props.get(key) instanceof StringBinaryTag st) {
                map.put(key, st.value());
            }
        }
        return BlockState.of(Key.key(name), map);
    }
}
