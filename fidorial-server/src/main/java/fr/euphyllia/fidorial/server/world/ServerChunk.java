package fr.euphyllia.fidorial.server.world;

import fr.euphyllia.fidorial.server.util.threading.ThreadContexts;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.euphyllia.fidorial.server.world.chunk.ChunkColumn;
import fr.fidorial.world.Chunk;
import fr.fidorial.world.World;

public final class ServerChunk implements Chunk {

    private final ServerWorld world;
    private final ChunkColumn column;
    private final BlockStateRegistry blockStates;

    public ServerChunk(final ServerWorld world, final ChunkColumn column, final BlockStateRegistry blockStates) {
        this.world = world;
        this.column = column;
        this.blockStates = blockStates;
    }

    public ChunkColumn column() {
        return column;
    }

    @Override
    public World world() {
        return world;
    }

    @Override
    public int chunkX() {
        return column.chunkX();
    }

    @Override
    public int chunkZ() {
        return column.chunkZ();
    }

    @Override
    public int minY() {
        return column.minY();
    }

    @Override
    public int height() {
        return column.height();
    }

    @Override
    public int getBlockStateId(final int localX, final int worldY, final int localZ) {
        return blockStates.networkId(column.getBlock(localX & 15, worldY, localZ & 15));
    }

    @Override
    public boolean setBlockStateId(final int localX, final int worldY, final int localZ, final int stateId) {
        ThreadContexts.checkOwnedByCurrentThread(this, "setBlockStateId");
        if (worldY < column.minY() || worldY >= column.minY() + column.height()) {
            return false;
        }
        final BlockState state = blockStates.byId(stateId);
        column.setBlock(localX & 15, worldY, localZ & 15, state);
        world.markDirty(column.chunkX(), column.chunkZ());
        return true;
    }

    @Override
    public int blockLight(final int localX, final int worldY, final int localZ) {
        return world.blockLightAt(worldX(localX), worldY, worldZ(localZ));
    }

    @Override
    public int skyLight(final int localX, final int worldY, final int localZ) {
        return world.skyLightAt(worldX(localX), worldY, worldZ(localZ));
    }

    @Override
    public int lightLevel(final int localX, final int worldY, final int localZ) {
        return world.lightLevelAt(worldX(localX), worldY, worldZ(localZ));
    }

    private int worldX(final int localX) {
        return (column.chunkX() << 4) | (localX & 15);
    }

    private int worldZ(final int localZ) {
        return (column.chunkZ() << 4) | (localZ & 15);
    }

    @Override
    public boolean execute(final Runnable task) {
        if (world.isChunkLoaded(column.chunkX(), column.chunkZ())) {
            world().scheduler().execute(world().key(), pos(), task);
            return true;
        }
        return false;
    }

    @Override
    public boolean executeDelayed(final Runnable task, final long delayTicks) {
        if (world.isChunkLoaded(column.chunkX(), column.chunkZ())) {
            world().scheduler().executeDelayed(world().key(), pos(), task, delayTicks);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOwnedByCurrentThread() {
        return world().scheduler().isOwnedByCurrentThread(world().key(), pos());
    }
}
