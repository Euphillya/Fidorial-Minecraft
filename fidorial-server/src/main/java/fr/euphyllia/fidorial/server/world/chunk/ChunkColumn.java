package fr.euphyllia.fidorial.server.world.chunk;

import fr.euphyllia.fidorial.server.util.ConcurrentInt2ObjectMap;
import fr.euphyllia.fidorial.server.world.block.blockentity.BlockEntity;
import fr.euphyllia.fidorial.server.world.block.blockentity.BlockEntityTypes;
import fr.euphyllia.fidorial.server.world.light.ChunkLightData;
import fr.fidorial.registry.keys.BlockTypeKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.UnknownNullability;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

public final class ChunkColumn {

    private final int chunkX;
    private final int chunkZ;
    private final int minY;
    private final int height;
    private final int minSectionY;
    private final int sectionCount;
    private final @Nullable ChunkSection[] sections;
    private final ConcurrentInt2ObjectMap<BlockEntity> blockEntities = new ConcurrentInt2ObjectMap<>();

    private long inhabitedTime;
    private long lastUpdate;
    private Key status = Key.key("full");

    private volatile boolean lightPopulated;
    private @Nullable ChunkLightData lightData;

    public ChunkColumn(final int chunkX, final int chunkZ, final int minY, final int height, final BlockState fillBlock, final @UnknownNullability Key fillBiome) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.minY = minY;
        this.height = height;
        this.minSectionY = minY >> 4;
        this.sectionCount = height >> 4;
        this.sections = new ChunkSection[sectionCount];
        for (int i = 0; i < sectionCount; i++) {
            sections[i] = new ChunkSection(minSectionY + i, fillBlock, fillBiome);
        }
    }

    public int chunkX() {
        return chunkX;
    }

    public int chunkZ() {
        return chunkZ;
    }

    public int minY() {
        return minY;
    }

    public int height() {
        return height;
    }

    public int minSectionY() {
        return minSectionY;
    }

    public int sectionCount() {
        return sectionCount;
    }

    public @Nullable ChunkSection[] sections() {
        return sections;
    }

    public void putSection(final ChunkSection s) {
        final int idx = s.sectionY() - minSectionY;
        if (idx >= 0 && idx < sectionCount) {
            sections[idx] = s;
        }
    }

    public boolean setBiome(final int localX, final int worldY, final int localZ, final Key biome) {
        final ChunkSection section = sectionForY(worldY);
        if (section == null) {
            return false;
        }

        final int bx = localX >> 2;
        final int by = (worldY & 15) >> 2;
        final int bz = localZ >> 2;

        if (biome.equals(section.getBiome(bx, by, bz))) {
            return false;
        }

        section.setBiome(bx, by, bz, biome);
        return true;
    }

    public @Nullable Key getBiome(final int localX, final int worldY, final int localZ) {
        final ChunkSection chunkSection = sectionForY(worldY);
        return chunkSection == null ? null : chunkSection.getBiome(localX >> 2, (worldY & 15) >> 2, localZ >> 2);
    }

    public long inhabitedTime() {
        return inhabitedTime;
    }

    public void setInhabitedTime(final long t) {
        this.inhabitedTime = t;
    }

    public long lastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(final long t) {
        this.lastUpdate = t;
    }

    public Key status() {
        return status;
    }

    public void setStatus(final Key s) {
        this.status = s;
    }

    public synchronized ChunkLightData lightData() {
        ChunkLightData data = lightData;
        if (data == null) {
            data = new ChunkLightData(minY, height);
            lightData = data;
        }
        return data;
    }

    public boolean lightPopulated() {
        return lightPopulated;
    }

    public void setLightPopulated(final boolean populated) {
        this.lightPopulated = populated;
    }

    /**
     * Returns every block entity of this column, in no particular order.
     *
     * @return an unmodifiable snapshot of the block entities
     */
    public Collection<BlockEntity> blockEntities() {
        return Collections.unmodifiableCollection(blockEntities.valuesSnapshot());
    }

    public int blockEntityCount() {
        return blockEntities.size();
    }

    public @Nullable BlockEntity blockEntity(final int localX, final int worldY, final int localZ) {
        return blockEntities.get(BlockEntity.positionKey(localX, worldY, localZ));
    }

    /**
     * Adds or replaces a block entity.
     *
     * @param blockEntity the block entity to store
     */
    public void putBlockEntity(final BlockEntity blockEntity) {
        blockEntities.put(blockEntity.positionKey(), blockEntity);
    }

    public @Nullable BlockEntity removeBlockEntity(final int localX, final int worldY, final int localZ) {
        if (blockEntities.isEmpty()) {
            return null;
        }
        return blockEntities.remove(BlockEntity.positionKey(localX, worldY, localZ));
    }

    public void clearBlockEntities() {
        blockEntities.clear();
    }

    private @Nullable ChunkSection sectionForY(final int worldY) {
        final int idx = (worldY >> 4) - minSectionY;
        if (idx < 0 || idx >= sectionCount) return null;
        return sections[idx];
    }

    public void setBlock(final int localX, final int worldY, final int localZ, final BlockState state) {
        setBlock(localX, worldY, localZ, state, null);
    }

    public void setBlock(final int localX, final int worldY, final int localZ, final BlockState state, final @Nullable CompoundBinaryTag data) {
        final ChunkSection s = sectionForY(worldY);
        if (s == null) {
            return;
        }

        final BlockState previous = s.getBlock(localX, worldY & 15, localZ);
        s.setBlock(localX, worldY & 15, localZ, state);

        if (previous.name().equals(state.name())) {
            return;
        }

        removeBlockEntity(localX, worldY, localZ);

        BlockEntityTypes.typeIdentifier(state.name())
                .ifPresent(type -> putBlockEntity(new BlockEntity(localX, worldY, localZ, type, data)));
    }

    public BlockState getBlock(final int localX, final int worldY, final int localZ) {
        final ChunkSection s = sectionForY(worldY);
        return s == null ? BlockState.of(BlockTypeKeys.AIR.key()) : s.getBlock(localX, worldY & 15, localZ);
    }

    public static final Predicate<BlockState> WORLD_SURFACE = state -> !state.isAir();
    public static final Predicate<BlockState> MOTION_BLOCKING = state -> !state.isAir();

    public int heightmapBits() {
        return BitPacking.bitsFor(height + 1, 1);
    }

    public long[] computeHeightmap(final Predicate<BlockState> occupied) {
        final int[] values = new int[256];
        int remaining = 256;

        for (int i = sections.length - 1; i >= 0 && remaining > 0; i--) {
            final ChunkSection section = sections[i];
            if (section == null || section.isEmpty()) continue;

            final int sectionBaseY = (minSectionY + i) << 4;
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x < 16; x++) {
                    final int col = z * 16 + x;
                    if (values[col] != 0) continue; // already found from a higher section

                    for (int ly = 15; ly >= 0; ly--) {
                        if (occupied.test(section.getBlock(x, ly, z))) {
                            values[col] = sectionBaseY + ly + 1 - minY;
                            remaining--;
                            break;
                        }
                    }
                }
            }
        }
        return BitPacking.pack(values, heightmapBits());
    }

    public long[] computeMotionBlockingHeightmap() {
        return computeHeightmap(MOTION_BLOCKING);
    }

    public long[] computeWorldSurfaceHeightmap() {
        return computeHeightmap(WORLD_SURFACE);
    }

    public int topNonEmptySectionY() {
        for (int i = sectionCount - 1; i >= 0; i--) {
            final ChunkSection section = sections[i];
            if (section != null && !section.isEmpty()) {
                return minSectionY + i;
            }
        }
        return minSectionY - 1;
    }
}
