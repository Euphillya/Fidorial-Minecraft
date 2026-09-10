package fr.euphyllia.fidorial.server.world;

import ca.spottedleaf.concurrentutil.collection.iterator.BaseLongIterator;
import ca.spottedleaf.concurrentutil.collection.iterator.BaseObjectIterator;
import ca.spottedleaf.concurrentutil.list.COWArrayList;
import ca.spottedleaf.concurrentutil.map.concurrent.longs.ConcurrentChainedLong2ReferenceHashTable;
import fr.euphyllia.fidorial.server.entity.AbstractEntity;
import fr.euphyllia.fidorial.server.entity.EntityManager;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMob;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.schedulers.LightUpdateDispatcher;
import fr.euphyllia.fidorial.server.schedulers.ThreadedRegionRegionizer;
import fr.euphyllia.fidorial.server.util.ConcurrentLongSet;
import fr.euphyllia.fidorial.server.util.threading.ThreadContexts;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.euphyllia.fidorial.server.world.chunk.ChunkColumn;
import fr.euphyllia.fidorial.server.world.chunk.ChunkSection;
import fr.euphyllia.fidorial.server.world.entity.AnvilEntitySerializer;
import fr.euphyllia.fidorial.server.world.light.ChunkLightData;
import fr.euphyllia.fidorial.server.world.light.FloodFillLightEngine;
import fr.euphyllia.fidorial.server.world.light.LightAccess;
import fr.euphyllia.fidorial.server.world.light.LightEngine;
import fr.euphyllia.fidorial.server.world.light.WorldLightManager;
import fr.euphyllia.fidorial.server.world.storage.ChunkStorage;
import fr.euphyllia.fidorial.server.world.storage.Dimension;
import fr.euphyllia.fidorial.server.world.storage.EntityRegionStorage;
import fr.euphyllia.fidorial.server.world.time.WorldClocks;
import fr.euphyllia.fidorial.server.world.time.WorldTimeEngine;
import fr.fidorial.entity.Entity;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.scheduler.RegionizedScheduler;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.Chunk;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.World;
import fr.fidorial.world.dimension.DimensionTypeDefinition;
import fr.fidorial.world.entity.EntitySpawnBridge;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongIterable;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

public final class ServerWorld implements World {

    private final Dimension dimension;
    private final ChunkStorage storage;
    private final EntityRegionStorage entityStorage;
    private final AnvilEntitySerializer entitySerializer;
    public final ChunkGenerator generator;
    private final BlockStateRegistry blockStates;
    private final EntityManager entities = new EntityManager();
    private final WorldTimeEngine dayNightCycle;
    private final DimensionTypeDefinition dimensionType;
    private final int minY;
    private final int height;
    private final WorldLightManager lightManager;
    private volatile @Nullable LightUpdateDispatcher lightDispatcher;
    private final FloodFillLightEngine fallbackEngine;
    private final ThreadedRegionRegionizer scheduler;

    private final ConcurrentChainedLong2ReferenceHashTable<ChunkColumn> loaded =
            ConcurrentChainedLong2ReferenceHashTable.createWithExpected(1024);

    private final ConcurrentLongSet dirty = new ConcurrentLongSet();
    private final ConcurrentLongSet entitiesDirty = new ConcurrentLongSet();
    private final ConcurrentLongSet entitiesLoaded = new ConcurrentLongSet();

    private final COWArrayList<ChunkViewSource> viewers = new COWArrayList<>(ChunkViewSource.class);
    private volatile @Nullable AsyncChunkLoader chunkLoader;
    private volatile @Nullable IntSupplier entityIdSupplier;
    private volatile @Nullable EntitySpawnBridge entityBridge;
    private volatile @Nullable Iterable<? extends Audience> adventure$audiences;

    public ServerWorld(
            final Dimension dimension,
            final ChunkStorage storage,
            final EntityRegionStorage entityStorage,
            final AnvilEntitySerializer entitySerializer,
            final ChunkGenerator generator,
            final BlockStateRegistry blockStates,
            final ThreadedRegionRegionizer scheduler
    ) {
        this.dimension = dimension;
        this.storage = storage;
        this.entityStorage = entityStorage;
        this.entitySerializer = entitySerializer;
        this.generator = generator;
        this.blockStates = blockStates;
        this.dayNightCycle = new WorldTimeEngine(WorldClocks.forDimension(dimension));
        this.dimensionType = generator.dimensionType();
        this.minY = dimensionType.minY();
        this.height = dimensionType.height();
        this.lightManager = new WorldLightManager(new WorldLightAccess());
        this.fallbackEngine = new FloodFillLightEngine(minY, height);
        this.scheduler = scheduler;
    }

    public void setEntityBridge(final IntSupplier entityIdSupplier, final EntitySpawnBridge entityBridge) {
        this.entityIdSupplier = entityIdSupplier;
        this.entityBridge = entityBridge;
    }

    public Dimension dimension() {
        return dimension;
    }

    public EntityManager entityManager() {
        return entities;
    }

    @Override
    public WorldTimeEngine dayNightCycle() {
        return dayNightCycle;
    }

    @Override
    public Key key() {
        return dimension.id();
    }

    @Override
    public int minY() {
        return minY;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public DimensionTypeDefinition dimensionType() {
        return dimensionType;
    }

    public void setChunkLoader(final AsyncChunkLoader loader) {
        this.chunkLoader = loader;
    }

    @Override
    public RegionizedScheduler scheduler() {
        return scheduler;
    }

    @Override
    public CompletableFuture<Chunk> getChunkAsync(final int chunkX, final int chunkZ) {
        final ChunkColumn cached = loaded.get(ChunkPos.chunkKey(chunkX, chunkZ));
        if (cached != null) {
            return CompletableFuture.completedFuture(wrap(cached));
        }
        final AsyncChunkLoader loader = this.chunkLoader;
        if (loader == null) {
            try {
                return CompletableFuture.completedFuture(wrap(getChunk(chunkX, chunkZ)));
            } catch (final IOException e) {
                return CompletableFuture.failedFuture(e);
            }
        }
        return loader.loadAsync(this, chunkX, chunkZ).thenApply(this::wrap);
    }

    @Override
    public Optional<Chunk> getChunkIfLoaded(final int chunkX, final int chunkZ) {
        final ChunkColumn cached = loaded.get(ChunkPos.chunkKey(chunkX, chunkZ));
        return Optional.ofNullable(cached).map(this::wrap);
    }

    @Override
    public Optional<Key> blockKeyAt(final BlockPos pos) {
        try {
            final BlockState state = getBlock(pos.x(), pos.y(), pos.z());
            if (state.isAir()) {
                return Optional.empty();
            }
            return Optional.of(state.name());
        } catch (final IOException e) {
            throw new UncheckedIOException("Unable to read block at " + pos, e);
        }
    }

    private ServerChunk wrap(final ChunkColumn column) {
        return new ServerChunk(this, column, blockStates);
    }

    @Override
    public int getBlockStateId(final BlockPos pos) {
        try {
            return blockStates.networkId(getBlock(pos.x(), pos.y(), pos.z()));
        } catch (final IOException e) {
            throw new UncheckedIOException("Lecture du bloc " + pos + " impossible", e);
        }
    }

    @Override
    public boolean setBlockStateId(final BlockPos pos, final int stateId) {
        ThreadContexts.checkOwnedByCurrentThread(this, pos, "setBlockStateId");
        try {
            return setBlock(pos.x(), pos.y(), pos.z(), blockStates.byId(stateId));
        } catch (final IOException e) {
            throw new UncheckedIOException("Ecriture du bloc " + pos + " impossible", e);
        }
    }

    @Override
    public int blockLight(final BlockPos pos) {
        return blockLightAt(pos.x(), pos.y(), pos.z());
    }

    @Override
    public int skyLight(final BlockPos pos) {
        return skyLightAt(pos.x(), pos.y(), pos.z());
    }

    @Override
    public int lightLevel(final BlockPos pos) {
        return lightLevelAt(pos.x(), pos.y(), pos.z());
    }

    @Override
    public Collection<? extends Entity> entities() {
        return entities.all();
    }

    @Override
    public Entity entity(final UUID uuid) {
        return entities.byUuid(uuid);
    }

    @Override
    public Entity entity(final int entityId) {
        return entities.byId(entityId);
    }

    public void addEntity(final AbstractEntity entity) {
        ThreadContexts.checkOwnedByCurrentThread(entity, "addEntity");
        entities.add(entity);
        markEntitiesDirty(entity.chunk().x(), entity.chunk().z());
        if (entity instanceof ServerPlayer) {
            invalidateAudiences();
        }
    }

    public void removeEntity(final AbstractEntity entity) {
        ThreadContexts.checkOwnedByCurrentThread(entity, "removeEntity");
        entities.remove(entity);
        markEntitiesDirty(entity.chunk().x(), entity.chunk().z());
        if (entity instanceof ServerPlayer) {
            invalidateAudiences();
        }
    }

    public void entityMoved(final AbstractEntity entity, final ChunkPos from, final ChunkPos to) {
        ThreadContexts.checkOwnedByCurrentThread(this, from, "entityMoved");
        entities.moved(entity, from, to);
        if (!from.equals(to)) {
            markEntitiesDirty(from.x(), from.z());
            markEntitiesDirty(to.x(), to.z());
            if (entity instanceof AbstractMob) {
                scheduler.moveTicket(key(), from, to);
            }
        }
    }

    public ChunkColumn getChunk(final int chunkX, final int chunkZ) throws IOException {
        final long k = ChunkPos.chunkKey(chunkX, chunkZ);
        final ChunkColumn cached = loaded.get(k);
        if (cached != null) {
            return cached;
        }
        final ChunkColumn column;
        try {
            column = loaded.computeIfAbsent(k, _ -> {
                try {
                    final ChunkColumn fromDisk = storage.load(dimension, chunkX, chunkZ, dimensionType.minY(), dimensionType.height());
                    if (fromDisk != null) {
                        return fromDisk;
                    }
                    dirty.add(k);
                    return generator.generate(chunkX, chunkZ);
                } catch (final IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } catch (final UncheckedIOException e) {
            throw e.getCause();
        }

        ensureEntitiesLoaded(chunkX, chunkZ);
        ensureLight(column, chunkX, chunkZ);
        return column;
    }

    private void ensureLight(final ChunkColumn column, final int chunkX, final int chunkZ) {
        if (column.lightPopulated()) {
            return;
        }

        final LightUpdateDispatcher dispatcher = lightDispatcher;
        if (dispatcher != null) {
            dispatcher.queueChunkLoad(dimension.id(), chunkX, chunkZ);
        } else {
            lightManager.lightChunkIfNeeded(column, chunkX, chunkZ, fallbackEngine);
        }
    }

    public void refreshBlockLight(final int x, final int y, final int z) {
        final LightUpdateDispatcher dispatcher = lightDispatcher;
        if (dispatcher != null) {
            dispatcher.queueBlockChange(dimension.id(), x, y, z);
        } else {
            lightManager.checkBlock(x, y, z, fallbackEngine);
        }
    }

    private void ensureEntitiesLoaded(final int chunkX, final int chunkZ) {
        final long k = ChunkPos.chunkKey(chunkX, chunkZ);
        if (!entitiesLoaded.add(k)) {
            return;
        }
        final IntSupplier idSupplier = this.entityIdSupplier;
        if (idSupplier == null) {
            entitiesLoaded.remove(k);
            return;
        }
        try {
            final CompoundBinaryTag root = entityStorage.load(dimension, chunkX, chunkZ);
            if (root == null) {
                return;
            }
            final List<AbstractEntity> restored = entitySerializer.fromChunkNbt(root, this, idSupplier);
            final EntitySpawnBridge bridge = this.entityBridge;
            for (final AbstractEntity entity : restored) {
                entities.add(entity);

                if (bridge != null) {
                    bridge.onEntityAppear(entity);
                }
            }
        } catch (final IOException e) {
            entitiesLoaded.remove(k);
            throw new UncheckedIOException("Unable to load entities for chunk " + chunkX + "," + chunkZ, e);
        }
    }

    private void saveChunkEntities(final int chunkX, final int chunkZ, final List<AbstractEntity> inChunk) throws IOException {
        if (inChunk.isEmpty() && !entityStorage.hasChunk(dimension, chunkX, chunkZ)) {
            return;
        }
        final CompoundBinaryTag root = entitySerializer.toChunkNbt(chunkX, chunkZ, inChunk);
        entityStorage.save(dimension, chunkX, chunkZ, root);
    }

    private List<AbstractEntity> persistableEntities(final int chunkX, final int chunkZ) {
        final List<AbstractEntity> result = new ArrayList<>();
        for (final AbstractEntity entity : entities.inChunk(new ChunkPos(chunkX, chunkZ))) {
            if (AnvilEntitySerializer.isPersistable(entity)) {
                result.add(entity);
            }
        }
        return result;
    }

    private Long2ObjectOpenHashMap<List<AbstractEntity>> bucketPersistableEntities() {
        final Long2ObjectOpenHashMap<List<AbstractEntity>> byChunk = new Long2ObjectOpenHashMap<>();
        for (final AbstractEntity entity : entities.all()) {
            if (!AnvilEntitySerializer.isPersistable(entity)) {
                continue;
            }
            final ChunkPos pos = entity.chunk();
            final long key = ChunkPos.chunkKey(pos.x(), pos.z());
            byChunk.computeIfAbsent(key, _ -> new ObjectArrayList<>()).add(entity);
        }
        return byChunk;
    }

    private void unloadChunkEntities(final int chunkX, final int chunkZ) throws IOException {
        final long k = ChunkPos.chunkKey(chunkX, chunkZ);
        if (!entitiesLoaded.remove(k)) {
            return;
        }
        final List<AbstractEntity> inChunk = persistableEntities(chunkX, chunkZ);
        saveChunkEntities(chunkX, chunkZ, inChunk);
        entitiesDirty.remove(k);

        final EntitySpawnBridge bridge = this.entityBridge;
        for (final AbstractEntity entity : inChunk) {
            entities.remove(entity);

            if (entity instanceof ServerPlayer) {
                invalidateAudiences();
            }

            if (bridge != null) {
                bridge.onEntityDisappear(entity);
            }
        }
    }

    public void markDirty(final int chunkX, final int chunkZ) {
        dirty.add(ChunkPos.chunkKey(chunkX, chunkZ));
    }

    public boolean setBlock(final int x, final int y, final int z, final BlockState state) throws IOException {
        ThreadContexts.checkOwnedByCurrentThread(this, new BlockPos(x, y, z), "setBlock");
        final ChunkColumn column = getChunk(x >> 4, z >> 4);
        if (y < column.minY() || y >= column.minY() + column.height()) {
            return false;
        }
        column.setBlock(x & 15, y, z & 15, state);
        refreshBlockLight(x, y, z);
        markDirty(x >> 4, z >> 4);
        return true;
    }

    public BlockState getBlock(final int x, final int y, final int z) throws IOException {
        final ChunkColumn column = getChunk(x >> 4, z >> 4);
        if (y < column.minY() || y >= column.minY() + column.height()) {
            return BlockState.of(BlockTypeKeys.AIR.key());
        }
        return column.getBlock(x & 15, y, z & 15);
    }

    public boolean setBiome(final int x, final int y, final int z, final Key biome) throws IOException {
        ThreadContexts.checkOwnedByCurrentThread(this, new BlockPos(x, y, z), "setBiome");
        final ChunkColumn column = getChunk(x >> 4, z >> 4);
        if (y < column.minY() || y >= column.minY() + column.height()) {
            return false;
        }
        if (!column.setBiome(x & 15, y, z & 15, biome)) {
            return false;
        }
        markDirty(x >> 4, z >> 4);
        return true;
    }

    public @Nullable Key getBiome(final int x, final int y, final int z) throws IOException {
        final ChunkColumn column = getChunk(x >> 4, z >> 4);
        if (y < column.minY() || y >= column.minY() + column.height()) {
            return null;
        }
        return column.getBiome(x & 15, y, z & 15);
    }

    public void saveDirty() throws IOException {
        final LongSet snapshot = dirty.snapshot();
        lightFlushFuture(snapshot).join();
        for (final long k : snapshot) {
            final ChunkColumn chunk = loaded.get(k);
            if (chunk != null) {
                storage.save(dimension, chunk);
            }
            dirty.remove(k);
        }
        saveLoadedEntities();
    }

    public void saveAll() throws IOException {
        final LongSet keys = new LongOpenHashSet(loaded.size());
        final BaseLongIterator loadedKeys = loaded.keyIterator();
        while (loadedKeys.hasNext()) {
            keys.add(loadedKeys.nextLong());
        }
        lightFlushFuture(keys).join();
        final BaseObjectIterator<ChunkColumn> columns = loaded.valueIterator();
        while (columns.hasNext()) {
            storage.save(dimension, columns.next());
        }
        dirty.clear();
        saveLoadedEntities();
    }

    public void markEntitiesDirty(final int chunkX, final int chunkZ) {
        entitiesDirty.add(ChunkPos.chunkKey(chunkX, chunkZ));
    }

    private void saveLoadedEntities() throws IOException {
        if (entitiesDirty.isEmpty()) {
            return;
        }
        final Long2ObjectOpenHashMap<List<AbstractEntity>> byChunk = bucketPersistableEntities();

        for (final long k : entitiesDirty.snapshot()) {
            if (!entitiesLoaded.contains(k)) {
                entitiesDirty.remove(k);
                continue;
            }
            final int cx = (int) (k >> 32);
            final int cz = (int) k;
            final List<AbstractEntity> inChunk = byChunk.getOrDefault(k, List.of());
            saveChunkEntities(cx, cz, inChunk);
            entitiesDirty.remove(k);
        }
    }

    public void addViewer(final ChunkViewSource viewer) {
        synchronized (viewers) {
            if (!viewers.contains(viewer)) {
                viewers.add(viewer);
            }
        }
    }

    public void removeViewer(final ChunkViewSource viewer) {
        viewers.remove(viewer);
    }

    public int unloadUnusedChunks() {
        if (loaded.isEmpty()) {
            return 0;
        }
        final LongSet wanted = new LongOpenHashSet();
        for (final ChunkViewSource viewer : viewers.getArray()) {
            viewer.collectViewedChunks(wanted);
        }

        final LongSet toUnload = new LongOpenHashSet();
        final BaseLongIterator loadedKeys = loaded.keyIterator();
        while (loadedKeys.hasNext()) {
            final long k = loadedKeys.nextLong();
            if (!wanted.contains(k) && !dirty.contains(k)) {
                toUnload.add(k);
            }
        }
        if (toUnload.isEmpty()) {
            return 0;
        }
        lightFlushFuture(toUnload).join();

        int unloaded = 0;
        for (final long k : toUnload) {
            if (loaded.remove(k) != null) {
                unloaded++;
                final int cx = (int) (k >> 32);
                final int cz = (int) k;
                lightManager.forgetChunk(cx, cz);
                try {
                    unloadChunkEntities(cx, cz);
                } catch (final IOException exception) {
                    throw new UncheckedIOException(
                            "Unloading entities from chunk " + cx + "," + cz + "failed.", exception);
                }
            }
        }
        return unloaded;
    }

    public void unloadChunk(final int chunkX, final int chunkZ, final boolean flushLight) throws IOException {
        if (flushLight) {
            lightFlushFuture(LongSet.of(ChunkPos.chunkKey(chunkX, chunkZ))).join();
        }
        final long k = ChunkPos.chunkKey(chunkX, chunkZ);
        if (dirty.remove(k)) {
            final ChunkColumn chunk = loaded.get(k);
            if (chunk != null) {
                storage.save(dimension, chunk);
            }
        }
        unloadChunkEntities(chunkX, chunkZ);
        loaded.remove(k);
        lightManager.forgetChunk(chunkX, chunkZ);
    }

    private void invalidateAudiences() {
        adventure$audiences = null;
    }

    @Override
    public Iterable<? extends Audience> audiences() {
        Iterable<? extends Audience> audiences = adventure$audiences;
        if (audiences == null) {
            audiences = this.entities().stream()
                    .filter(ServerPlayer.class::isInstance)
                    .map(ServerPlayer.class::cast)
                    .toList();
            adventure$audiences = audiences;
        }
        return audiences;
    }

    @Override
    public CompletableFuture<Boolean> unloadChunkAsync(final int chunkX, final int chunkZ) {
        final long k = ChunkPos.chunkKey(chunkX, chunkZ);
        if (!loaded.containsKey(k)) {
            return CompletableFuture.completedFuture(false);
        }

        final LongSet wanted = new LongOpenHashSet();
        for (final ChunkViewSource viewer : viewers.getArray()) {
            viewer.collectViewedChunks(wanted);
        }
        if (wanted.contains(k)) {
            return CompletableFuture.completedFuture(false);
        }

        return lightFlushFuture(LongSet.of(k)).thenApplyAsync(_ -> {
            try {
                unloadChunk(chunkX, chunkZ, false);
                return true;
            } catch (final IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    public LongSet collectAllViewedChunks() {
        final LongSet wanted = new LongOpenHashSet();
        for (final ChunkViewSource viewer : viewers.getArray()) {
            viewer.collectViewedChunks(wanted);
        }
        return wanted;
    }

    public @Nullable ChunkColumn loadedColumn(final int chunkX, final int chunkZ) {
        return loaded.get(ChunkPos.chunkKey(chunkX, chunkZ));
    }

    public int loadedChunkCount() {
        return loaded.size();
    }

    public void forEachLoadedChunk(final Consumer<ChunkColumn> action) {
        final BaseObjectIterator<ChunkColumn> columns = loaded.valueIterator();
        while (columns.hasNext()) {
            action.accept(columns.next());
        }
    }

    public WorldLightManager lightManager() {
        return lightManager;
    }

    public void setLightDispatcher(final LightUpdateDispatcher dispatcher) {
        this.lightDispatcher = dispatcher;
    }

    private CompletableFuture<Void> lightFlushFuture(final LongIterable chunkKeys) {
        final LightUpdateDispatcher dispatcher = lightDispatcher;
        return dispatcher == null
                ? CompletableFuture.completedFuture(null)
                : dispatcher.flush(dimension.id(), chunkKeys);
    }

    public LongSet checkBlockLight(final int x, final int y, final int z, final LightEngine engine) {
        final LongSet dirtyChunks = lightManager.checkBlock(x, y, z, engine);
        dirty.addAll(dirtyChunks);
        return dirtyChunks;
    }

    public LongSet relightChunks(final LongSet chunkKeys, final LightEngine engine) {
        final LongSet needsFullRelight = new LongOpenHashSet();
        final LongSet needsEdgeCheck = new LongOpenHashSet();

        for (final long key : chunkKeys) {
            final ChunkColumn column = loadedColumn((int) (key >> 32), (int) key);
            if (column == null) {
                continue;
            }
            (column.lightPopulated() ? needsEdgeCheck : needsFullRelight).add(key);
        }

        final LongSet dirty = new LongOpenHashSet();

        if (!needsFullRelight.isEmpty()) {
            dirty.addAll(lightManager.relightChunks(needsFullRelight, engine));
            for (final long key : needsFullRelight) {
                final ChunkColumn column = loadedColumn((int) (key >> 32), (int) key);
                if (column != null) {
                    column.setLightPopulated(true);
                }
            }
        }

        if (!needsEdgeCheck.isEmpty()) {
            dirty.addAll(lightManager.checkChunkEdges(needsEdgeCheck, engine));
        }

        this.dirty.addAll(dirty);
        return dirty;
    }

    public int blockLightAt(final int x, final int y, final int z) {
        return lightManager.blockLight(x, y, z);
    }

    public int skyLightAt(final int x, final int y, final int z) {
        return lightManager.skyLight(x, y, z);
    }

    public int lightLevelAt(final int x, final int y, final int z) {
        return lightManager.lightLevel(x, y, z);
    }

    private final class WorldLightAccess implements LightAccess {
        @Override
        public int minY() {
            return minY;
        }

        @Override
        public int height() {
            return height;
        }

        @Override
        public BlockState blockAt(final int x, final int y, final int z) {
            if (y < minY || y >= minY + height) {
                return BlockState.of(BlockTypeKeys.AIR.key());
            }
            final ChunkColumn column = loadedColumn(x >> 4, z >> 4);
            return column == null ? BlockState.of(BlockTypeKeys.AIR.key()) : column.getBlock(x & 15, y, z & 15);
        }

        @Override
        public @Nullable ChunkLightData lightAt(final int chunkX, final int chunkZ) {
            final ChunkColumn column = loadedColumn(chunkX, chunkZ);
            return column == null ? null : column.lightData();
        }

        @Override
        public int topNonEmptySectionY(final int chunkX, final int chunkZ) {
            final ChunkColumn column = loadedColumn(chunkX, chunkZ);
            return column == null ? (minY >> 4) - 1 : column.topNonEmptySectionY();
        }

        @Override
        public @Nullable BlockColumnAccess columnAt(final int chunkX, final int chunkZ) {
            final ChunkColumn column = loadedColumn(chunkX, chunkZ);
            if (column == null) return null;
            return (localX, worldY, localZ) ->
                    (worldY < minY || worldY >= minY + height)
                            ? BlockState.of(BlockTypeKeys.AIR.key())
                            : column.getBlock(localX, worldY, localZ);
        }

        @Override
        public boolean isLightPopulated(final int chunkX, final int chunkZ) {
            final ChunkColumn column = loadedColumn(chunkX, chunkZ);
            if (column == null) return false;
            return column.lightPopulated();
        }

        @Override
        public boolean sectionHasEmissiveBlocks(final int chunkX, final int sectionY, final int chunkZ) {
            final ChunkColumn column = loadedColumn(chunkX, chunkZ);
            if (column == null) {
                return true;
            }
            final int idx = sectionY - column.minSectionY();
            if (idx < 0 || idx >= column.sectionCount()) {
                return false;
            }
            final ChunkSection section = column.sections()[idx];
            return section != null && !section.isEmpty() && section.containsEmissiveBlocks();
        }
    }
}
