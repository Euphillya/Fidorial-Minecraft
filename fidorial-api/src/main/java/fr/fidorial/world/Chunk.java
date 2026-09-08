package fr.fidorial.world;

import fr.fidorial.scheduler.SchedulerSource;

public interface Chunk extends SchedulerSource {

    World world();

    int chunkX();

    int chunkZ();

    default ChunkPos pos() {
        return new ChunkPos(chunkX(), chunkZ());
    }

    int minY();

    int height();

    int getBlockStateId(int localX, int worldY, int localZ);

    boolean setBlockStateId(int localX, int worldY, int localZ, int stateId);

    int blockLight(int localX, int worldY, int localZ);

    int skyLight(int localX, int worldY, int localZ);

    int lightLevel(int localX, int worldY, int localZ);
}
