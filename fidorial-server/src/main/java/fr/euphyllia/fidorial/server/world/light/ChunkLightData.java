package fr.euphyllia.fidorial.server.world.light;

import fr.fidorial.world.light.LightType;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;

public class ChunkLightData {
    public static final int SECTION_BYTES = 2048;

    private final int minY;
    private final int height;
    private final int minSectionY;
    private final int sectionCount;

    private final byte[] @Nullable [] blockLight;
    private final byte[] @Nullable [] skyLight;

    private final int[] heightmap;
    private int skyFullFromY = Integer.MAX_VALUE;

    public ChunkLightData(final int minY, final int height) {
        this.minY = minY;
        this.height = height;
        this.minSectionY = minY >> 4;
        this.sectionCount = height >> 4;
        this.blockLight = new byte[sectionCount][];
        this.skyLight = new byte[sectionCount][];
        this.heightmap = new int[256];
        Arrays.fill(this.heightmap, minY + height);
    }

    public int minY() {
        return minY;
    }

    private static int nibbleIndex(final int localX, final int localY, final int localZ) {
        return (localY << 8) | (localZ << 4) | localX;
    }

    private byte[] @Nullable [] layer(final LightType type) {
        return type == LightType.BLOCK ? blockLight : skyLight;
    }

    private int sectionIndexForY(final int worldY) {
        return (worldY >> 4) - minSectionY;
    }

    public int topOpaqueY(final int localX, final int localZ) {
        return heightmap[(localZ & 15) << 4 | (localX & 15)];
    }

    public void setTopOpaqueY(final int localX, final int localZ, final int y) {
        heightmap[(localZ & 15) << 4 | (localX & 15)] = y;
    }

    public int get(final LightType type, final int localX, final int worldY, final int localZ) {
        if (type == LightType.SKY) {
            final int top = topOpaqueY(localX & 15, localZ & 15);
            if (worldY > top) {
                return 15;
            }
        }
        final int section = sectionIndexForY(worldY);
        if (section < 0 || section >= sectionCount) {
            return 0;
        }
        final byte[] @Nullable [] layerType = layer(type);
        final byte[] data = layerType[section];
        if (data == null) {
            return 0;
        }
        final int index = nibbleIndex(localX & 15, worldY & 15, localZ & 15);
        final int b = data[index >> 1] & 0xFF;
        return (index & 1) == 0 ? (b & 0x0F) : (b >> 4);
    }

    public void set(final LightType type, final int localX, final int worldY, final int localZ, final int level) {
        final int section = sectionIndexForY(worldY);
        if (section < 0 || section >= sectionCount) {
            return;
        }
        final byte[] @Nullable [] layerType = layer(type);
        byte[] data = layerType[section];
        if (data == null) {
            if (level == 0) {
                return;
            }
            data = new byte[SECTION_BYTES];
            layerType[section] = data;
        }

        final int index = nibbleIndex(localX & 15, worldY & 15, localZ & 15);
        final int byteIndex = index >> 1;
        final int current = data[byteIndex] & 0xFF;
        final int clamped = level & 0x0F;
        if ((index & 1) == 0) {
            data[byteIndex] = (byte) ((current & 0xF0) | clamped);
        } else {
            data[byteIndex] = (byte) ((current & 0x0F) | (clamped << 4));
        }
    }

    public byte @Nullable [] sectionArray(final LightType type, final int sectionIndex) {
        if (sectionIndex < 0 || sectionIndex >= sectionCount) {
            return null;
        }
        final byte[] @Nullable [] layerType = layer(type);
        return layerType[sectionIndex];
    }

    public void setSectionArray(final LightType type, final int sectionIndex, final byte @Nullable [] data) {
        if (sectionIndex < 0 || sectionIndex >= sectionCount) {
            return;
        }
        final byte[] @Nullable [] layerType = layer(type);
        if (data == null) {
            layerType[sectionIndex] = null;
        } else if (data.length == SECTION_BYTES) {
            layerType[sectionIndex] = data;
        }
    }

    public int skyFullFromY() {
        return skyFullFromY;
    }

    public void setSkyFullFromY(final int worldY) {
        this.skyFullFromY = worldY;
    }

    public int[] heightmapSnapshot() {
        return heightmap.clone();
    }

    public void restoreHeightmap(final int[] values) {
        if (values.length == heightmap.length) {
            System.arraycopy(values, 0, heightmap, 0, heightmap.length);
        }
    }

    public byte @Nullable [] materializeSkySection(final int sectionIndex) {
        if (sectionIndex < 0 || sectionIndex >= sectionCount) return null;
        final byte[] stored = skyLight[sectionIndex];
        final int sectionBottomY = (minSectionY + sectionIndex) << 4;
        byte[] out = null;
        for (int lx = 0; lx < 16; lx++) {
            for (int lz = 0; lz < 16; lz++) {
                final int top = topOpaqueY(lx, lz);
                for (int ly = 0; ly < 16; ly++) {
                    final int worldY = sectionBottomY + ly;
                    final int level;
                    if (worldY > top) {
                        level = 15;
                    } else if (stored != null) {
                        final int idx = nibbleIndex(lx, ly, lz);
                        final int b = stored[idx >> 1] & 0xFF;
                        level = (idx & 1) == 0 ? (b & 0x0F) : (b >> 4);
                    } else {
                        level = 0;
                    }
                    if (level != 0) {
                        if (out == null) out = new byte[SECTION_BYTES];
                        final int idx = nibbleIndex(lx, ly, lz);
                        final int bi = idx >> 1;
                        if ((idx & 1) == 0) {
                            out[bi] = (byte) ((out[bi] & 0xF0) | level);
                        } else {
                            out[bi] = (byte) ((out[bi] & 0x0F) | (level << 4));
                        }
                    }
                }
            }
        }
        return out;
    }

    public void clear() {
        for (int i = 0; i < sectionCount; i++) {
            blockLight[i] = null;
            skyLight[i] = null;
        }
        Arrays.fill(heightmap, minY + height);
        skyFullFromY = Integer.MAX_VALUE;
    }
}
