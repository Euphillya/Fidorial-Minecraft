package fr.euphyllia.fidorial.server.world.light;

import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.light.LightType;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;

import java.util.Arrays;

public class FloodFillLightEngine implements LightEngine {

    private static final int MAX_LEVEL = 15;

    // -X, +X, -Z, +Z, -Y, +Y.
    private static final int[] DX = {-1, 1, 0, 0, 0, 0};
    private static final int[] DZ = {0, 0, -1, 1, 0, 0};
    private static final int[] DY = {0, 0, 0, 0, -1, 1};

    private final int minY;
    private final int maxY;

    private final LongIntQueue scratchDecrease = new LongIntQueue();
    private final LongIntQueue scratchIncrease = new LongIntQueue();
    private final LongQueue scratchLongQueue = new LongQueue();

    public FloodFillLightEngine(final int minY, final int height) {
        this.minY = minY;
        this.maxY = minY + height;
    }

    private long packPos(final int x, final int y, final int z) {
        final long yOff = (y - minY) & 0xFFFL;
        return ((long) (x & 0x3FFFFFF) << 38) | ((long) (z & 0x3FFFFFF) << 12) | yOff;
    }

    private int unpackX(final long v) {
        final int x = (int) ((v >> 38) & 0x3FFFFFFL);
        return (x << 6) >> 6;
    }

    private int unpackZ(final long v) {
        final int z = (int) ((v >> 12) & 0x3FFFFFFL);
        return (z << 6) >> 6;
    }

    private int unpackY(final long v) {
        return (int) (v & 0xFFFL) + minY;
    }

    @Override
    public LongSet checkBlock(final int x, final int y, final int z, final LightAccess access) {
        final LongSet dirtyChunks = new LongOpenHashSet();

        checkBlockForType(LightType.BLOCK, x, y, z, access, dirtyChunks);
        checkSkyColumn(access, x, y, z, dirtyChunks);

        return dirtyChunks;
    }

    private void checkSkyColumn(final LightAccess access, final int x, final int y, final int z, final LongSet dirtyChunks) {
        final ChunkLightData data = access.lightAt(x >> 4, z >> 4);
        if (data == null) {
            return;
        }

        final int oldTop = data.topOpaqueY(x & 15, z & 15);
        final BlockState state = access.blockAt(x, y, z);
        final int opacity = BlockLightProperties.opacity(state);
        final long chunkKey = ChunkPos.chunkKey(x >> 4, z >> 4);

        if (y > oldTop) {
            if (opacity == 0) {
                return;
            }
            raiseBoundary(access, data, x, y, z, oldTop, opacity, chunkKey, dirtyChunks);
            return;
        }

        if (y == oldTop) {
            if (opacity > 0) {
                checkBlockForType(LightType.SKY, x, y, z, data, access, dirtyChunks);
                return;
            }
            lowerBoundary(access, data, x, y, z, chunkKey, dirtyChunks);
            return;
        }

        checkBlockForType(LightType.SKY, x, y, z, data, access, dirtyChunks);
    }

    private void checkBlockForType(final LightType type, final int x, final int y, final int z, final LightAccess access, final LongSet dirtyChunks) {
        final ChunkLightData data = access.lightAt(x >> 4, z >> 4);
        if (data == null) {
            return;
        }
        checkBlockForType(type, x, y, z, data, access, dirtyChunks);
    }

    private void checkBlockForType(final LightType type, final int x, final int y, final int z, final ChunkLightData data, final LightAccess access, final LongSet dirtyChunks) {
        final LongIntQueue decrease = scratchDecrease;
        final LongIntQueue increase = scratchIncrease;
        decrease.reset();
        increase.reset();

        final int oldLevel = data.get(type, x, y, z);
        if (oldLevel > 0) {
            data.set(type, x, y, z, 0);
            dirtyChunks.add(ChunkPos.chunkKey(x >> 4, z >> 4));
            decrease.push(x, y, z, oldLevel);
        }

        propagateDecrease(access, type, decrease, increase, dirtyChunks);

        final BlockState centerState = access.blockAt(x, y, z);
        final int newSourceLevel = sourceLevel(type, x, y, z, centerState, access);
        if (newSourceLevel > 0) {
            data.set(type, x, y, z, newSourceLevel);
            dirtyChunks.add(ChunkPos.chunkKey(x >> 4, z >> 4));
            increase.push(x, y, z, newSourceLevel);
        }

        for (int dir = 0; dir < 6; dir++) {
            final int nx = x + DX[dir];
            final int ny = y + DY[dir];
            final int nz = z + DZ[dir];
            if (ny < minY || ny >= maxY) {
                continue;
            }
            final ChunkLightData nd = access.lightAt(nx >> 4, nz >> 4);
            if (nd == null) {
                continue;
            }
            final int lvl = nd.get(type, nx, ny, nz);
            if (lvl > 1) {
                increase.push(nx, ny, nz, lvl);
            }
        }

        propagateIncrease(access, type, increase, dirtyChunks);
    }

    @Override
    public LongSet checkChunkEdge(final int chunkX, final int chunkZ, final int neighborChunkX, final int neighborChunkZ, final LightAccess access) {
        final LongSet dirtyChunks = new LongOpenHashSet();

        if (!access.isLightPopulated(chunkX, chunkZ) || !access.isLightPopulated(neighborChunkX, neighborChunkZ)) {
            return dirtyChunks;
        }

        final int dx = neighborChunkX - chunkX;
        final int dz = neighborChunkZ - chunkZ;
        final boolean xDirection = dx != 0;

        final int fixedThisLocal = xDirection ? (dx > 0 ? 15 : 0) : (dz > 0 ? 15 : 0);
        final int fixedNeighborLocal = xDirection ? (dx > 0 ? 0 : 15) : (dz > 0 ? 0 : 15);

        final int baseThisX = chunkX << 4, baseThisZ = chunkZ << 4;
        final int baseNeighborX = neighborChunkX << 4, baseNeighborZ = neighborChunkZ << 4;

        final ChunkLightData thisData = access.lightAt(chunkX, chunkZ);
        final ChunkLightData neighborData = access.lightAt(neighborChunkX, neighborChunkZ);
        if (thisData == null || neighborData == null) {
            return dirtyChunks;
        }

        final int blockScanTop = Math.min(maxY - 1,
                Math.max(((access.topNonEmptySectionY(chunkX, chunkZ) + 1) << 4) - 1,
                        ((access.topNonEmptySectionY(neighborChunkX, neighborChunkZ) + 1) << 4) - 1)
        );

        for (int i = 0; i < 16; i++) {
            final int thisX = xDirection ? baseThisX + fixedThisLocal : baseThisX + i;
            final int thisZ = xDirection ? baseThisZ + i : baseThisZ + fixedThisLocal;
            final int neighborX = xDirection ? baseNeighborX + fixedNeighborLocal : baseNeighborX + i;
            final int neighborZ = xDirection ? baseNeighborZ + i : baseNeighborZ + fixedNeighborLocal;

            final int thisTop = thisData.topOpaqueY(thisX & 15, thisZ & 15);
            final int neighborTop = neighborData.topOpaqueY(neighborX & 15, neighborZ & 15);

            for (int y = minY; y <= blockScanTop; y++) {
                checkEdgePosition(LightType.BLOCK, thisX, y, thisZ, thisData, access, dirtyChunks);
                checkEdgePosition(LightType.BLOCK, neighborX, y, neighborZ, neighborData, access, dirtyChunks);
                if (y <= thisTop) {
                    checkEdgePosition(LightType.SKY, thisX, y, thisZ, thisData, access, dirtyChunks);
                }
                if (y <= neighborTop) {
                    checkEdgePosition(LightType.SKY, neighborX, y, neighborZ, neighborData, access, dirtyChunks);
                }
            }
        }

        return dirtyChunks;
    }

    private void checkEdgePosition(final LightType type, final int x, final int y, final int z, final ChunkLightData data, final LightAccess access, final LongSet dirtyChunks) {
        final int current = data.get(type, x, y, z);
        if (calculateLightValue(type, x, y, z, current, access) != current) {
            checkBlockForType(type, x, y, z, data, access, dirtyChunks);
        }
    }

    private int sourceLevel(final LightType type, final int x, final int y, final int z, final BlockState centerState, final LightAccess access) {
        if (type == LightType.BLOCK) {
            return BlockLightProperties.emission(centerState);
        }

        if (BlockLightProperties.occludes(centerState)) {
            return 0;
        }

        final ChunkLightData data = access.lightAt(x >> 4, z >> 4);
        final int top = data == null ? minY - 1 : data.topOpaqueY(x & 15, z & 15);
        return y > top ? MAX_LEVEL : 0;
    }

    private void propagateIncrease(final LightAccess access, final LightType type, final LongIntQueue queue, final LongSet dirtyChunks) {
        long sourceChunkKey = Long.MIN_VALUE;
        ChunkLightData sourceData = null;
        long targetChunkKey = Long.MIN_VALUE;
        ChunkLightData targetData = null;
        LightAccess.BlockColumnAccess targetCol = null;

        while (!queue.isEmpty()) {
            final int x = queue.pollX();
            final int y = queue.pollY();
            final int z = queue.pollZ();
            final int level = queue.pollLevel();

            final long sk = ChunkPos.chunkKey(x >> 4, z >> 4);
            if (sk != sourceChunkKey) {
                sourceChunkKey = sk;
                sourceData = access.lightAt(x >> 4, z >> 4);
            }
            final ChunkLightData self = sourceData;
            if (self == null || self.get(type, x, y, z) != level) {
                continue;
            }

            for (int dir = 0; dir < 6; dir++) {
                final int nx = x + DX[dir];
                final int ny = y + DY[dir];
                final int nz = z + DZ[dir];
                if (ny < minY || ny >= maxY) {
                    continue;
                }

                final long tk = ChunkPos.chunkKey(nx >> 4, nz >> 4);
                if (tk != targetChunkKey) {
                    targetChunkKey = tk;
                    targetData = access.lightAt(nx >> 4, nz >> 4);
                    targetCol = access.columnAt(nx >> 4, nz >> 4);
                }
                final ChunkLightData target = targetData;
                if (target == null) {
                    continue;
                }

                final int current = target.get(type, nx, ny, nz);
                if (current >= level - 1) {
                    continue;
                }

                final BlockState state = targetCol != null
                        ? targetCol.blockAt(nx & 15, ny, nz & 15)
                        : access.blockAt(nx, ny, nz);
                if (BlockLightProperties.occludes(state)) {
                    continue;
                }

                final int targetLevel = level - lightDecrement(type, dir, state);
                if (targetLevel > current) {
                    target.set(type, nx, ny, nz, targetLevel);
                    dirtyChunks.add(tk);
                    if (targetLevel > 1) {
                        queue.push(nx, ny, nz, targetLevel);
                    }
                }
            }
        }
    }

    private void propagateDecrease(final LightAccess access, final LightType type, final LongIntQueue decreaseQueue, final LongIntQueue increaseQueue, final LongSet dirtyChunks) {
        long targetChunkKey = Long.MIN_VALUE;
        ChunkLightData targetData = null;
        LightAccess.BlockColumnAccess targetCol = null;

        while (!decreaseQueue.isEmpty()) {
            final int x = decreaseQueue.pollX();
            final int y = decreaseQueue.pollY();
            final int z = decreaseQueue.pollZ();
            final int oldLevel = decreaseQueue.pollLevel();

            for (int dir = 0; dir < 6; dir++) {
                final int nx = x + DX[dir];
                final int ny = y + DY[dir];
                final int nz = z + DZ[dir];
                if (ny < minY || ny >= maxY) {
                    continue;
                }

                final long tk = ChunkPos.chunkKey(nx >> 4, nz >> 4);
                if (tk != targetChunkKey) {
                    targetChunkKey = tk;
                    targetData = access.lightAt(nx >> 4, nz >> 4);
                    targetCol = access.columnAt(nx >> 4, nz >> 4);
                }
                final ChunkLightData target = targetData;
                if (target == null) {
                    continue;
                }

                final int neighbourLevel = target.get(type, nx, ny, nz);
                if (neighbourLevel == 0) {
                    continue;
                }

                final BlockState state = targetCol != null
                        ? targetCol.blockAt(nx & 15, ny, nz & 15)
                        : access.blockAt(nx, ny, nz);
                final int decrement = lightDecrement(type, dir, state);
                final int targetLevel = oldLevel - decrement;

                if (neighbourLevel <= targetLevel) {
                    target.set(type, nx, ny, nz, 0);
                    dirtyChunks.add(tk);
                    decreaseQueue.push(nx, ny, nz, neighbourLevel);
                } else {
                    increaseQueue.push(nx, ny, nz, neighbourLevel);
                }
            }
        }
    }

    @Override
    public void relight(final LongSet chunks, final LightAccess access) {
        if (chunks.isEmpty()) {
            return;
        }
        for (final long key : chunks) {
            final ChunkLightData data = access.lightAt((int) (key >> 32), (int) key);
            if (data != null) {
                data.clear();
            }
        }

        computeSky(chunks, access);
        computeBlock(chunks, access);
    }

    private void computeSky(final LongSet chunks, final LightAccess access) {
        final LongQueue queue = scratchLongQueue;
        queue.reset();

        for (final long key : chunks) {
            final int chunkX = (int) (key >> 32);
            final int chunkZ = (int) key;
            final ChunkLightData data = access.lightAt(chunkX, chunkZ);
            final LightAccess.BlockColumnAccess col = access.columnAt(chunkX, chunkZ);
            if (data == null || col == null) continue;

            final int baseX = chunkX << 4;
            final int baseZ = chunkZ << 4;
            final int topSection = access.topNonEmptySectionY(chunkX, chunkZ);
            final int scanStart = topSection < (minY >> 4)
                    ? maxY - 1
                    : Math.min(maxY - 1, ((topSection + 1) << 4) + 15);
            data.setSkyFullFromY(scanStart);

            for (int lx = 0; lx < 16; lx++) {
                for (int lz = 0; lz < 16; lz++) {
                    final int worldX = baseX + lx;
                    final int worldZ = baseZ + lz;
                    int sky = MAX_LEVEL;
                    int topOpaque = minY - 1;
                    boolean seeded = false;

                    for (int y = scanStart; y >= minY; y--) {
                        final BlockState block = col.blockAt(lx, y, lz);
                        final int opacity = BlockLightProperties.opacity(block);

                        if (opacity > 0 && topOpaque == minY - 1) {
                            topOpaque = y;
                            if (!seeded) {
                                queue.add(packPos(worldX, y + 1, worldZ), -1);
                                seeded = true;
                            }
                        }

                        if (BlockLightProperties.occludes(block)) {
                            break;
                        }
                        if (sky <= 0) break;
                        if (topOpaque != minY - 1) {
                            data.set(LightType.SKY, worldX, y, worldZ, sky);
                            if (opacity > 0) {
                                sky = Math.max(0, sky - opacity);
                                if (sky > 1) {
                                    queue.add(packPos(worldX, y, worldZ), -1);
                                }
                            }
                        }
                    }
                    data.setTopOpaqueY(lx, lz, topOpaque);
                }
            }
        }

        seedBorders(chunks, access, LightType.SKY, queue);
        propagate(chunks, access, LightType.SKY, queue);
    }

    private void computeBlock(final LongSet chunks, final LightAccess access) {
        final LongQueue queue = scratchLongQueue;
        queue.reset();

        final int minSection = minY >> 4;
        for (final long key : chunks) {
            final int chunkX = (int) (key >> 32);
            final int chunkZ = (int) key;
            final ChunkLightData data = access.lightAt(chunkX, chunkZ);
            final LightAccess.BlockColumnAccess col = access.columnAt(chunkX, chunkZ);
            if (data == null || col == null) continue;

            final int baseX = chunkX << 4;
            final int baseZ = chunkZ << 4;
            final int topSection = access.topNonEmptySectionY(chunkX, chunkZ);

            for (int sectionY = minSection; sectionY <= topSection; sectionY++) {
                if (!access.sectionHasEmissiveBlocks(chunkX, sectionY, chunkZ)) {
                    continue;
                }

                final int sectionBaseY = sectionY << 4;
                final int yStart = Math.max(minY, sectionBaseY);
                final int yEnd = Math.min(maxY - 1, sectionBaseY + 15);

                for (int lx = 0; lx < 16; lx++) {
                    for (int lz = 0; lz < 16; lz++) {
                        for (int y = yStart; y <= yEnd; y++) {
                            final BlockState block = col.blockAt(lx, y, lz);
                            final int emission = BlockLightProperties.emission(block);
                            if (emission > 0) {
                                final int worldX = baseX + lx;
                                final int worldZ = baseZ + lz;
                                data.set(LightType.BLOCK, worldX, y, worldZ, emission);
                                queue.add(packPos(worldX, y, worldZ), -1);
                            }
                        }
                    }
                }
            }
        }

        seedBorders(chunks, access, LightType.BLOCK, queue);
        propagate(chunks, access, LightType.BLOCK, queue);
    }

    private void seedBorders(final LongSet chunks, final LightAccess access, final LightType type, final LongQueue queue) {
        for (final long key : chunks) {
            final int chunkX = (int) (key >> 32);
            final int chunkZ = (int) key;

            addBorderColumn(chunks, access, type, queue, chunkX - 1, chunkZ, 15, -1);
            addBorderColumn(chunks, access, type, queue, chunkX + 1, chunkZ, 0, -1);
            addBorderColumn(chunks, access, type, queue, chunkX, chunkZ - 1, -1, 15);
            addBorderColumn(chunks, access, type, queue, chunkX, chunkZ + 1, -1, 0);
        }
    }

    private void addBorderColumn(final LongSet chunks, final LightAccess access, final LightType type, final LongQueue queue, final int neighborChunkX, final int neighborChunkZ, final int fixedLocalX, final int fixedLocalZ) {
        if (chunks.contains(ChunkPos.chunkKey(neighborChunkX, neighborChunkZ))) {
            return;
        }

        final ChunkLightData data = access.lightAt(neighborChunkX, neighborChunkZ);
        if (data == null) {
            return;
        }

        final int baseX = neighborChunkX << 4;
        final int baseZ = neighborChunkZ << 4;
        final int xStart = Math.max(fixedLocalX, 0);
        final int xEnd = fixedLocalX >= 0 ? fixedLocalX : 15;
        final int zStart = Math.max(fixedLocalZ, 0);
        final int zEnd = fixedLocalZ >= 0 ? fixedLocalZ : 15;
        for (int lx = xStart; lx <= xEnd; lx++) {
            for (int lz = zStart; lz <= zEnd; lz++) {
                final int worldX = baseX + lx;
                final int worldZ = baseZ + lz;
                for (int y = minY; y < maxY; y++) {
                    final int level = data.get(type, worldX, y, worldZ);
                    if (level > 1) {
                        queue.add(packPos(worldX, y, worldZ), -1);
                    }
                }
            }
        }
    }

    private void propagate(final LongSet chunks, final LightAccess access, final LightType type, final LongQueue queue) {
        long sourceKey = Long.MIN_VALUE;
        ChunkLightData sourceData = null;
        long targetKey = Long.MIN_VALUE;
        ChunkLightData targetData = null;
        LightAccess.BlockColumnAccess targetCol = null;

        while (!queue.isEmpty()) {
            final long packed = queue.poll();
            final int skipDir = queue.lastSkipDir();
            final int x = unpackX(packed);
            final int y = unpackY(packed);
            final int z = unpackZ(packed);

            final long sk = ChunkPos.chunkKey(x >> 4, z >> 4);
            if (sk != sourceKey) {
                sourceKey = sk;
                sourceData = access.lightAt(x >> 4, z >> 4);
            }
            final ChunkLightData source = sourceData;
            if (source == null) continue;
            final int level = source.get(type, x, y, z);
            if (level <= 1) continue;

            for (int dir = 0; dir < 6; dir++) {
                if (dir == skipDir) continue;
                final int nx = x + DX[dir];
                final int ny = y + DY[dir];
                final int nz = z + DZ[dir];
                if (ny < minY || ny >= maxY) continue;
                final long nChunkKey = ChunkPos.chunkKey(nx >> 4, nz >> 4);
                if (!chunks.contains(nChunkKey)) continue;

                if (nChunkKey != targetKey) {
                    targetKey = nChunkKey;
                    targetData = access.lightAt(nx >> 4, nz >> 4);
                    targetCol = access.columnAt(nx >> 4, nz >> 4);
                }
                final ChunkLightData target = targetData;
                if (target == null) continue;

                final BlockState block = targetCol != null
                        ? targetCol.blockAt(nx & 15, ny, nz & 15)
                        : access.blockAt(nx, ny, nz);
                if (BlockLightProperties.occludes(block)) continue;
                final int candidate = level - lightDecrement(type, dir, block);
                if (candidate <= 0) continue;
                if (candidate > target.get(type, nx, ny, nz)) {
                    target.set(type, nx, ny, nz, candidate);
                    if (candidate > 1) {
                        queue.add(packPos(nx, ny, nz), dir ^ 1);
                    }
                }
            }
        }
    }

    private int lightDecrement(final LightType type, final int dir, final BlockState state) {
        final int opacity = BlockLightProperties.opacity(state);
        if (type == LightType.SKY && dir == 4) {
            return opacity;
        }
        return Math.max(1, opacity);
    }

    private int calculateLightValue(final LightType type, final int x, final int y, final int z, final int expect, final LightAccess access) {
        final BlockState centerState = access.blockAt(x, y, z);
        int level = sourceLevel(type, x, y, z, centerState, access);
        if (level > expect) {
            return level;
        }

        if (BlockLightProperties.occludes(centerState)) {
            return level;
        }

        long cachedChunkKey = Long.MIN_VALUE;
        ChunkLightData cachedData = null;

        for (int dir = 0; dir < 6; dir++) {
            final int nx = x + DX[dir];
            final int ny = y + DY[dir];
            final int nz = z + DZ[dir];
            if (ny < minY || ny >= maxY) {
                continue;
            }

            final long chunkKey = ChunkPos.chunkKey(nx >> 4, nz >> 4);
            if (chunkKey != cachedChunkKey) {
                cachedChunkKey = chunkKey;
                cachedData = access.lightAt(nx >> 4, nz >> 4);
            }
            final ChunkLightData nd = cachedData;
            if (nd == null) {
                continue;
            }

            final int neighbourLevel = nd.get(type, nx, ny, nz);
            if (neighbourLevel - 1 <= level) {
                continue;
            }

            final int decrement = lightDecrement(type, dir ^ 1, centerState);
            final int calculated = neighbourLevel - decrement;
            if (calculated > level) {
                level = calculated;
                if (level > expect) {
                    return level;
                }
            }
        }

        return level;
    }

    private void raiseBoundary(final LightAccess access, final ChunkLightData data, final int x, final int y, final int z, final int oldTop, final int opacity, final long chunkKey, final LongSet dirtyChunks) {
        final LongIntQueue decrease = scratchDecrease;
        final LongIntQueue increase = scratchIncrease;
        decrease.reset();
        increase.reset();

        if (oldTop >= minY) {
            final int oldBoundaryLevel = data.get(LightType.SKY, x, oldTop, z);
            if (oldBoundaryLevel > 0) {
                data.set(LightType.SKY, x, oldTop, z, 0);
                decrease.push(x, oldTop, z, oldBoundaryLevel);
            }
        }

        data.setTopOpaqueY(x & 15, z & 15, y);
        dirtyChunks.add(chunkKey);

        if (y + 1 < maxY) {
            increase.push(x, y + 1, z, MAX_LEVEL);
        } else {
            final int level = MAX_LEVEL - opacity;
            if (level > 0) {
                data.set(LightType.SKY, x, y, z, level);
                if (level > 1) {
                    increase.push(x, y, z, level);
                }
            }
        }

        propagateDecrease(access, LightType.SKY, decrease, increase, dirtyChunks);
        propagateIncrease(access, LightType.SKY, increase, dirtyChunks);
    }

    private void lowerBoundary(final LightAccess access, final ChunkLightData data, final int x, final int y, final int z, final long chunkKey, final LongSet dirtyChunks) {
        int newTop = y - 1;
        while (newTop >= minY && BlockLightProperties.opacity(access.blockAt(x, newTop, z)) == 0) {
            newTop--;
        }

        data.setTopOpaqueY(x & 15, z & 15, newTop);
        dirtyChunks.add(chunkKey);

        final LongIntQueue increase = scratchIncrease;
        increase.reset();
        increase.push(x, newTop + 1, z, MAX_LEVEL);
        propagateIncrease(access, LightType.SKY, increase, dirtyChunks);
    }

    private static final class LongQueue {
        private long[] data = new long[1024];
        private byte[] dirs = new byte[1024];
        private int head;
        private int tail;
        private byte lastDir;

        void reset() {
            head = 0;
            tail = 0;
        }

        void add(final long value, final int skipDir) {
            if (tail == data.length) grow();
            data[tail] = value;
            dirs[tail] = (byte) skipDir;
            tail++;
        }

        long poll() {
            lastDir = dirs[head];
            return data[head++];
        }

        int lastSkipDir() {
            return lastDir;
        }

        boolean isEmpty() {
            return head == tail;
        }

        private void grow() {
            if (head > 0) {
                final int size = tail - head;
                System.arraycopy(data, head, data, 0, size);
                System.arraycopy(dirs, head, dirs, 0, size);
                head = 0;
                tail = size;
                if (tail < data.length) return;
            }
            data = Arrays.copyOf(data, data.length * 2);
            dirs = Arrays.copyOf(dirs, dirs.length * 2);
        }
    }
}
