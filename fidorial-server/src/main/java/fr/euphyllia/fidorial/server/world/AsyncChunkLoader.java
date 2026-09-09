package fr.euphyllia.fidorial.server.world;

import fr.euphyllia.fidorial.server.world.chunk.ChunkColumn;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface AsyncChunkLoader {

    CompletableFuture<ChunkColumn> loadAsync(ServerWorld world, int chunkX, int chunkZ);
}
