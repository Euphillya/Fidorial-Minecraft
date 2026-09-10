package fr.euphyllia.fidorial.server.world.storage;

import ca.spottedleaf.converter.types.MapType;
import fr.euphyllia.fidorial.server.world.anvil.RegionConstants;
import fr.euphyllia.fidorial.server.world.anvil.RegionFile;
import fr.euphyllia.fidorial.server.world.chunk.AnvilChunkSerializer;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.euphyllia.fidorial.server.world.chunk.ChunkColumn;
import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerType;
import fr.euphyllia.fidorial.server.world.storage.datafixers.registry.DataFixersRegistry;
import fr.euphyllia.fidorial.server.world.storage.datafixers.util.nbt.NbtMapType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ChunkStorage implements AutoCloseable {

    private final WorldPaths paths;
    private final AnvilChunkSerializer serializer;
    private final BlockState defaultBlock;
    private final Key defaultBiome;

    private final Map<RegionKey, RegionFile> regionCache = new ConcurrentHashMap<>();

    public ChunkStorage(
            final WorldPaths paths,
            final AnvilChunkSerializer serializer,
            final BlockState defaultBlock,
            final Key defaultBiome
    ) {
        this.paths = paths;
        this.serializer = serializer;
        this.defaultBlock = defaultBlock;
        this.defaultBiome = defaultBiome;
    }

    private record RegionKey(Key dimension, int regionX, int regionZ) {
    }

    private RegionFile region(final Dimension dim, final int chunkX, final int chunkZ) {
        final int rx = RegionConstants.chunkToRegion(chunkX);
        final int rz = RegionConstants.chunkToRegion(chunkZ);
        final RegionKey key = new RegionKey(dim.id(), rx, rz);
        return regionCache.computeIfAbsent(key, k -> {
            final Path file = paths.regionDir(dim).resolve(RegionConstants.fileName(rx, rz));
            try {
                return new RegionFile(file);
            } catch (final IOException e) {
                throw new RuntimeException("Unable to open the region file: " + file, e);
            }
        });
    }

    public @Nullable ChunkColumn load(final Dimension dim, final int chunkX, final int chunkZ, final int minY, final int height) throws IOException {
        final RegionFile rf = region(dim, chunkX, chunkZ);
        synchronized (rf) {
            if (!rf.hasChunk(chunkX, chunkZ)) return null;
            CompoundBinaryTag nbt = rf.readChunk(chunkX, chunkZ);
            if (nbt == null) return null;

            final int sourceVersion = nbt.getInt("DataVersion");
            final int latest = DataFixersRegistry.latestDataFixerVersion();
            if (sourceVersion < latest) {
                final MapType fixed = DataFixersRegistry.update(
                        DataFixerType.CHUNK, NbtMapType.of(nbt), sourceVersion);
                nbt = ((NbtMapType) fixed).toCompound().putInt("DataVersion", latest);
            }

            return serializer.fromNbt(nbt, minY, height, defaultBlock, defaultBiome);
        }
    }

    public void save(final Dimension dim, final ChunkColumn chunk) throws IOException {
        chunk.setLastUpdate(System.currentTimeMillis() / 20L); // en ticks approx.
        final CompoundBinaryTag nbt = serializer.toNbt(chunk);
        final RegionFile rf = region(dim, chunk.chunkX(), chunk.chunkZ());
        synchronized (rf) {
            rf.writeChunk(chunk.chunkX(), chunk.chunkZ(), nbt);
        }
    }

    @Override
    public void close() {
        for (final RegionFile rf : regionCache.values()) {
            synchronized (rf) {
                try {
                    rf.close();
                } catch (final IOException ignored) {
                }
            }
        }
        regionCache.clear();
    }
}
