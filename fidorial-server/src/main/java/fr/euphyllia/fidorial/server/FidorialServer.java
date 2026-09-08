package fr.euphyllia.fidorial.server;

import com.google.common.collect.Iterables;
import dev.faststats.ErrorTracker;
import dev.faststats.Metrics;
import fr.euphyllia.fidorial.auth.EncryptionUtils;
import fr.euphyllia.fidorial.auth.MojangSessionService;
import fr.euphyllia.fidorial.server.adventure.ClickCallbackManager;
import fr.euphyllia.fidorial.server.combat.CombatEngine;
import fr.euphyllia.fidorial.server.command.CommandManager;
import fr.euphyllia.fidorial.server.command.ConsoleSender;
import fr.euphyllia.fidorial.server.console.command.ConsoleCommandReader;
import fr.euphyllia.fidorial.server.entity.AbstractEntity;
import fr.euphyllia.fidorial.server.entity.EntityIdAllocator;
import fr.euphyllia.fidorial.server.entity.EntityTickHandler;
import fr.euphyllia.fidorial.server.entity.EntityTracker;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMob;
import fr.euphyllia.fidorial.server.entity.mob.FidorialMobRegistry;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.entity.player.profile.FidorialOfflinePlayers;
import fr.euphyllia.fidorial.server.entity.player.storage.NbtPlayerDataStorage;
import fr.euphyllia.fidorial.server.entity.player.storage.NbtPlayerEnderChestStorage;
import fr.euphyllia.fidorial.server.entity.player.storage.NbtPlayerInventoryStorage;
import fr.euphyllia.fidorial.server.events.SimpleEventBus;
import fr.euphyllia.fidorial.server.inventory.ChestViewerTracker;
import fr.euphyllia.fidorial.server.item.FidorialItemRegistry;
import fr.euphyllia.fidorial.server.metrics.FidorialContext;
import fr.euphyllia.fidorial.server.moderation.CodeOfConductManager;
import fr.euphyllia.fidorial.server.moderation.FidorialBanManager;
import fr.euphyllia.fidorial.server.moderation.FidorialWhitelist;
import fr.euphyllia.fidorial.server.network.ClientConnection;
import fr.euphyllia.fidorial.server.network.NettyServer;
import fr.euphyllia.fidorial.server.network.protocol.ProtocolConstants;
import fr.euphyllia.fidorial.server.network.protocol.ProtocolMap;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundBlockUpdatePacket;
import fr.euphyllia.fidorial.server.permission.DefaultPermissions;
import fr.euphyllia.fidorial.server.permission.FidorialPermissionRegistry;
import fr.euphyllia.fidorial.server.permission.OperatorList;
import fr.euphyllia.fidorial.server.plugin.JavaPluginManager;
import fr.euphyllia.fidorial.server.registry.Registries;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.euphyllia.fidorial.server.registry.biome.FidorialBiomeRegistry;
import fr.euphyllia.fidorial.server.registry.data.BlockStateIds;
import fr.euphyllia.fidorial.server.registry.data.BlockStateLightProperties;
import fr.euphyllia.fidorial.server.registry.data.ItemProperties;
import fr.euphyllia.fidorial.server.registry.dialog.FidorialDialogRegistry;
import fr.euphyllia.fidorial.server.registry.dimension.FidorialDimensionTypeRegistry;
import fr.euphyllia.fidorial.server.schedulers.AiWorker;
import fr.euphyllia.fidorial.server.schedulers.DayNightThread;
import fr.euphyllia.fidorial.server.schedulers.LightUpdateDispatcher;
import fr.euphyllia.fidorial.server.schedulers.ThreadedChunkWorker;
import fr.euphyllia.fidorial.server.schedulers.ThreadedRegionRegionizer;
import fr.euphyllia.fidorial.server.service.SimpleServiceRegistry;
import fr.euphyllia.fidorial.server.spark.SparkService;
import fr.euphyllia.fidorial.server.translation.BuiltInTranslationStore;
import fr.euphyllia.fidorial.server.world.BlockEditService;
import fr.euphyllia.fidorial.server.world.BlockStateRegistry;
import fr.euphyllia.fidorial.server.world.BossBarRegistry;
import fr.euphyllia.fidorial.server.world.ChunkNetworkSerializer;
import fr.euphyllia.fidorial.server.world.FlatChunkGenerator;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.ServiceBackedChunkGenerator;
import fr.euphyllia.fidorial.server.world.WorldManager;
import fr.euphyllia.fidorial.server.world.block.FidorialBlockRegistry;
import fr.euphyllia.fidorial.server.world.chunk.BlockStateProperties;
import fr.euphyllia.fidorial.server.world.fluid.FluidEngine;
import fr.euphyllia.fidorial.server.world.weather.WeatherEngine;
import fr.fidorial.Server;
import fr.fidorial.combat.CombatService;
import fr.fidorial.command.CommandRegistry;
import fr.fidorial.entity.Entity;
import fr.fidorial.entity.OfflinePlayers;
import fr.fidorial.entity.Player;
import fr.fidorial.entity.mob.MobRegistry;
import fr.fidorial.event.EventBus;
import fr.fidorial.event.server.ServerStartedEvent;
import fr.fidorial.event.server.ServerStoppingEvent;
import fr.fidorial.item.ItemDefaults;
import fr.fidorial.moderation.BanManager;
import fr.fidorial.moderation.WhitelistManager;
import fr.fidorial.permission.PermissionRegistry;
import fr.fidorial.plugin.PluginManager;
import fr.fidorial.scheduler.RegionizedScheduler;
import fr.fidorial.service.ServicePriority;
import fr.fidorial.service.ServiceRegistry;
import fr.fidorial.status.Favicon;
import fr.fidorial.storage.player.PlayerDataStorage;
import fr.fidorial.storage.player.PlayerEnderChestStorage;
import fr.fidorial.storage.player.PlayerInventoryStorage;
import fr.fidorial.translation.TranslationStore;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import fr.fidorial.world.WorldBuilder;
import fr.fidorial.world.biome.BiomeRegistry;
import fr.fidorial.world.block.Blocks;
import fr.fidorial.world.dimension.types.VanillaDimensionTypes;
import fr.fidorial.world.entity.EntitySpawnBridge;
import fr.fidorial.world.fluid.FluidManager;
import fr.fidorial.world.weather.WeatherManager;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FidorialServer implements Server {

    public static final ComponentLogger LOGGER = ComponentLogger.logger(FidorialServer.class);
    private static final ErrorTracker ERROR_TRACKER = ErrorTracker.contextAware();

    private static final Duration PROFILE_CACHE_TTL = Duration.ofDays(30);
    private static final int PROFILE_CACHE_MAX_ENTRIES = 65_536;

    private static @Nullable FidorialServer instance;

    private final ServerConfig config = ServerConfig.load();
    private final AtomicBoolean running = new AtomicBoolean(false);

    private final KeyPair keyPair = EncryptionUtils.generateServerKeyPair();
    private final MojangSessionService sessionService = new MojangSessionService();
    private final FidorialBlockRegistry blockRegistry = bootstrapBlocks();
    private final BlockStateRegistry blockStateRegistry = new BlockStateRegistry(blockRegistry);
    private final EntityIdAllocator entityIds = new EntityIdAllocator();
    private final EntityTracker entityTracker = new EntityTracker(config.sendDistance());
    private final SimpleEventBus events = new SimpleEventBus();
    private final CombatEngine combat = new CombatEngine(this);
    private final ServiceRegistry services = new SimpleServiceRegistry();
    private final Set<ClientConnection> connections = ConcurrentHashMap.newKeySet();
    private volatile List<ServerPlayer> playerSnapshot = List.of();
    private final BuiltInTranslationStore builtInTranslationStore = new BuiltInTranslationStore();

    private final ProtocolMap protocolMap = ProtocolMap.load();
    private final Registries registries = Registries.load();
    private final CommandManager commandManager;
    private final ClickCallbackManager clickCallbackManager = new ClickCallbackManager();
    private final CodeOfConductManager codeOfConduct =
            new CodeOfConductManager(config.enableCodeOfConduct(), config.codeOfConductPath());

    private final ThreadedRegionRegionizer regionizer = new ThreadedRegionRegionizer(config.regionWorkers(), config.regionShift());
    private final ThreadedChunkWorker chunkWorker = new ThreadedChunkWorker(config.chunkWorkers());
    private final AiWorker aiWorker = new AiWorker(config.aiWorkers());
    private final ScheduledExecutorService autoSave = Executors.newSingleThreadScheduledExecutor(
            r -> Thread.ofPlatform().name("fidorial-autosave").unstarted(r));

    private final NbtPlayerInventoryStorage defaultInventoryStorage =
            new NbtPlayerInventoryStorage(config.worldPath().resolve("player"), false);
    private final NbtPlayerDataStorage defaultPlayerDataStorage =
            new NbtPlayerDataStorage(config.worldPath().resolve("player"), false);
    private final NbtPlayerEnderChestStorage defaultEnderChestStorage =
            new NbtPlayerEnderChestStorage(config.worldPath().resolve("player"), false);
    private final ChestViewerTracker chestViewers = new ChestViewerTracker();
    private final WorldManager worldManager = WorldManager.openOrCreate(config.worldPath(), blockStateRegistry, regionizer);
    private final FluidEngine fluidEngine =
            new FluidEngine(worldManager, regionizer, blockStateRegistry, this::broadcast);
    private final WeatherEngine weatherEngine = new WeatherEngine(worldManager.levelData(), this::broadcast);
    private final BossBarRegistry bossBarRegistry = new BossBarRegistry(worldManager.levelData(), this::players);
    private final DayNightThread dayNightEngine = new DayNightThread(worldManager, registries.dynamic());
    private final ChunkNetworkSerializer chunkSerializer = new ChunkNetworkSerializer(blockStateRegistry, registries.biomes());
    private final LightUpdateDispatcher lightDispatcher = new LightUpdateDispatcher(
            config.lightWorkers(), this::broadcast, chunkSerializer, worldManager::world);
    private final BlockEditService blockEdits = new BlockEditService(
            blockStateRegistry,
            (pos, stateId) -> broadcast(new ClientboundBlockUpdatePacket(pos, stateId)),
            fluidEngine::notifyBlockChanged,
            lightDispatcher::queueBlockChange);
    private final FidorialPermissionRegistry permissionRegistry = new FidorialPermissionRegistry();
    private final FidorialItemRegistry itemRegistry = new FidorialItemRegistry();
    private final FidorialMobRegistry mobRegistry = new FidorialMobRegistry();
    private final JavaPluginManager pluginManager =
            new JavaPluginManager(this, events, services, permissionRegistry, config.pluginsPath());
    private final OperatorList operators = new OperatorList(Path.of("ops.json"));
    private final FidorialBanManager fidorialBanManager = new FidorialBanManager(Path.of("banned-players.json"), Path.of("banned-ips.json"));
    private final FidorialWhitelist fidorialWhitelist = new FidorialWhitelist(Path.of("whitelist.json"));
    private final FidorialOfflinePlayers offlinePlayers = new FidorialOfflinePlayers(
            this,
            config.worldPath().resolve("player").resolve("profiles.fop"),
            PROFILE_CACHE_TTL,
            PROFILE_CACHE_MAX_ENTRIES,
            config.onlineMode());
    private final NettyServer network = new NettyServer(this, config.port());
    private final FidorialContext metrics = new FidorialContext.Factory("6c8c21fe427163e998ea50f54a0ce855")
            .errorTrackerService(ERROR_TRACKER)
            .metrics(Metrics.Factory::create)
            .create();
    private final ConsoleSender console = new ConsoleSender(this);
    private final @Nullable SparkService spark =
            config.sparkEnabled() ? new SparkService(this, config.sparkPath()) : null;
    private volatile @Nullable Iterable<? extends Audience> adventure$audiences;

    private @Nullable Favicon favicon = loadFavicon();
    private Component description = MiniMessage
            .miniMessage(MiniMessage.Preset.FORMATTED_TEXT)
            .deserialize(config.motd());
    private int maxPlayers = config.maxPlayers();
    private final boolean headless;

    public FidorialServer() throws IOException {
        this(false);
    }

    public FidorialServer(final boolean headless) throws IOException {
        if (instance != null) {
            throw new IllegalStateException("FidorialServer is already initialized");
        }
        bootstrapItems();
        this.headless = headless;
        instance = this;
        commandManager = new CommandManager();
    }

    public static FidorialServer getInstance() {
        if (instance == null) {
            throw new RuntimeException("FidorialServer is not initialized");
        }
        return instance;
    }

    private static void bootstrapItems() {

        ItemProperties.bootstrap();

        ItemDefaults.install(new ItemDefaults.Source() {

            @Override
            public int maxStackSize(final Key item) {
                return ItemProperties.maxStackSize(item);
            }

            @Override
            public int maxDamage(final Key item) {
                return ItemProperties.maxDamage(item);
            }
        });
    }

    private static FidorialBlockRegistry bootstrapBlocks() {
        final FidorialBlockRegistry registry = new FidorialBlockRegistry();
        BlockStateIds.registerAll(registry);
        BlockStateProperties.bootstrap();
        BlockStateLightProperties.bootstrap();
        Blocks.bootstrap(registry);
        LOGGER.info("{} blocks defined in code", registry.definedCount());
        return registry;
    }

    public void start() throws Exception {
        if (!running.compareAndSet(false, true)) {
            return;
        }
        LOGGER.info("Starting Fidorial (Minecraft {} / protocol {})", minecraftVersion(), protocolVersion());
        try {
            metrics.ready();
            loadData();
            registerDefaultServices();
            enableSpark();
            loadPlugins();
            openWorlds();
            regionizer.registerTickHandler(new EntityTickHandler(worldManager, this));
            syncServerStatusToRegistries(true);
            if (!headless) {
                network.bind();
                startAutoSave();
                console.setLocale(Locale.getDefault());
                new ConsoleCommandReader(commandManager, running::get).start();
                pluginManager.enableAll();
                LOGGER.info("Listening on port {}", config.port());
            } else {
                pluginManager.enableAll();
            }
            events.post(new ServerStartedEvent(this));
        } catch (final Exception e) {
            LOGGER.error("Startup interrupted, shutting down", e);
            shutdown();
            throw e;
        }
    }

    private void syncServerStatusToRegistries(final boolean started) {
        biomeRegistry().started.getAndSet(started);
        dimensionTypes().started.getAndSet(started);
    }

    @Override
    public void shutdown() {
        if (!running.compareAndSet(true, false)) {
            return;
        }

        LOGGER.info("Stopping the Fidorial server...");
        events.post(new ServerStoppingEvent(this));
        onlinePlayers().forEach(player -> player.kick(Component.translatable("commands.stop.stopping")));
        syncServerStatusToRegistries(false);

        closeQuietly("plugins", pluginManager::close);
        closeQuietly("spark", this::disableSpark);
        closeQuietly("commands", commandManager::shutdown);
        closeQuietly("click callbacks", clickCallbackManager::close);
        closeQuietly("bossbars", bossBarRegistry::close);
        closeQuietly("day/night cycle", dayNightEngine::close);
        closeQuietly("network", network::shutdown);
        closeQuietly("light engine", lightDispatcher::shutdown);
        closeQuietly("auto-save", autoSave::shutdownNow);
        closeQuietly("ai", aiWorker::shutdown);
        closeQuietly("regions", regionizer::shutdown);
        closeQuietly("chunks", chunkWorker::shutdown);
        closeQuietly("weather", weatherEngine::close);
        closeQuietly("profiles", offlinePlayers::close);
        closeQuietly("worlds", worldManager::close);
        closeQuietly("metrics", metrics::shutdown);

        LOGGER.info("Fidorial shut down correctly.");
    }

    private @Nullable Favicon loadFavicon() {
        final Path serverIcon = Path.of("server-icon.png");
        if (Files.isRegularFile(serverIcon)) try {
            return Favicon.read(serverIcon);
        } catch (final Exception e) {
            LOGGER.warn("Could not load server icon", e);
        }
        return null;
    }

    private void loadData() {
        TranslationStore.setStore(builtInTranslationStore);
        operators.load();
        fidorialBanManager.load();
        fidorialWhitelist.load();
        try {
            offlinePlayers.load();
        } catch (final IOException e) {
            LOGGER.error("Profile cache unreadable, starting with an empty one", e);
        }
    }

    private void openWorlds() {
        worldManager.setChunkLoader(chunkWorker);
        worldManager.setLightDispatcher(lightDispatcher);
        fluidEngine.setLightHook(lightDispatcher::queueBlockChange);
        worldManager.setEntityBridge(entityIds::allocate, new EntitySpawnBridge() {
            @Override
            public void onEntityAppear(final Entity entity) {
                if (entity instanceof AbstractMob && entity.world() instanceof final ServerWorld world) {
                    regionizer.addTicket(world.dimension().id(), entity.chunk());
                }
                entityTracker.update(entity, players());
            }

            @Override
            public void onEntityDisappear(final Entity entity) {
                if (entity instanceof AbstractMob && entity.world() instanceof final ServerWorld world) {
                    regionizer.removeTicket(world.dimension().id(), entity.chunk());
                }
                entityTracker.untrack(entity);
            }
        });
        worldManager.setDefaultGenerator(new ServiceBackedChunkGenerator(
                services,
                FlatChunkGenerator.cobblestone(VanillaDimensionTypes.OVERWORLD)));
        worldManager.overworld();
        weatherEngine.start();
        dayNightEngine.start();
        bossBarRegistry.loadFromLevelData();
    }

    private void registerDefaultServices() {
        services.register(PermissionRegistry.class, permissionRegistry, this, ServicePriority.LOWEST);
        services.register(FluidManager.class, fluidEngine, this, ServicePriority.LOWEST);
        services.register(WeatherManager.class, weatherEngine, this, ServicePriority.LOWEST);
        services.register(CombatService.class, combat, this, ServicePriority.LOWEST);
        services.register(BlockEditService.class, blockEdits, this, ServicePriority.LOWEST); // Todo : Currently, plugins cannot implement their own system.
        services.register(CommandRegistry.class, commandManager, this, ServicePriority.LOWEST);
        services.register(PlayerInventoryStorage.class, defaultInventoryStorage, this, ServicePriority.LOWEST);
        services.register(PlayerDataStorage.class, defaultPlayerDataStorage, this, ServicePriority.LOWEST);
        services.register(PlayerEnderChestStorage.class, defaultEnderChestStorage, this, ServicePriority.LOWEST);
        services.register(BossBarRegistry.class, bossBarRegistry, this, ServicePriority.LOWEST); // Todo : Currently, plugins cannot implement their own system.
        services.register(OfflinePlayers.class, offlinePlayers, this, ServicePriority.LOWEST);
        services.register(BanManager.class, fidorialBanManager, this, ServicePriority.LOWEST);
        services.register(WhitelistManager.class, fidorialWhitelist, this, ServicePriority.LOWEST);
        services.register(MobRegistry.class, mobRegistry, this, ServicePriority.LOWEST);
    }

    private void enableSpark() {
        if (spark == null) {
            LOGGER.debug("spark is disabled by configuration");
            return;
        }
        spark.enable();
    }

    private void disableSpark() {
        if (spark != null) {
            spark.disable();
        }
    }

    public @Nullable SparkService spark() {
        return spark;
    }

    private void loadPlugins() throws IOException {
        DefaultPermissions.register(permissionRegistry);
        pluginManager.loadAll();
    }

    private void startAutoSave() {
        autoSave.scheduleAtFixedRate(
                () -> {
                    try {
                        worldManager.saveDirty();
                        offlinePlayers.maintain();
                        fidorialBanManager.purgeExpired();
                        final int n = worldManager.unloadUnusedChunks();
                        if (n > 0) LOGGER.debug("{} unloaded chunks", n);
                    } catch (final Throwable t) {
                        LOGGER.error("An error occurred during the automatic save:", t);
                    }
                },
                config.autoSaveSeconds(),
                config.autoSaveSeconds(),
                TimeUnit.SECONDS);
    }

    private void closeQuietly(final String what, final ThrowingRunnable action) {
        try {
            action.run();
        } catch (final Throwable t) {
            LOGGER.error("Stopping subsystem '{}' due to an error", what, t);
        }
    }

    private void invalidateAudiences() {
        adventure$audiences = null;
    }

    @Override
    public Iterable<? extends Audience> audiences() {
        Iterable<? extends Audience> audiences = this.adventure$audiences;
        if (audiences == null) {
            audiences = Iterables.concat(
                    Collections.singleton(console), playerSnapshot);
            this.adventure$audiences = audiences;
        }
        return audiences;
    }

    @Override
    public String brandName() {
        return "Fidorial";
    }

    @Override
    public String minecraftVersion() {
        return ProtocolConstants.MINECRAFT_VERSION;
    }

    @Override
    public int protocolVersion() {
        return ProtocolConstants.PROTOCOL_VERSION;
    }

    @Override
    public RegionizedScheduler scheduler() {
        return regionizer;
    }

    @Override
    public EventBus events() {
        return events;
    }

    @Override
    public Optional<Favicon> favicon() {
        return Optional.ofNullable(favicon);
    }

    @Override
    public void favicon(final Favicon favicon) {
        this.favicon = favicon;
    }

    @Override
    public Component description() {
        return description;
    }

    @Override
    public void description(final Component description) {
        this.description = description;
    }

    @Override
    public int maxPlayers() {
        return maxPlayers;
    }

    @Override
    public void maxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public ServiceRegistry services() {
        return services;
    }

    public OperatorList operators() {
        return operators;
    }

    @Override
    public BanManager ban() {
        return fidorialBanManager;
    }

    @Override
    public WhitelistManager whitelist() {
        return fidorialWhitelist;
    }

    @Override
    public PermissionRegistry permissions() {
        return permissionRegistry;
    }

    @Override
    public BiomeRegistry biomes() {
        return registries.biomes();
    }

    @Override
    public FidorialDialogRegistry dialogs() {
        return registries.dialogs();
    }

    @Override
    public FidorialDimensionTypeRegistry dimensionTypes() {
        return registries.dimensionTypes();
    }

    @Override
    public FidorialMobRegistry mobs() {
        return mobRegistry;
    }

    @Override
    public FidorialItemRegistry items() {
        return itemRegistry;
    }

    public FidorialBiomeRegistry biomeRegistry() {
        return registries.biomes();
    }

    public PluginManager plugins() {
        return pluginManager;
    }

    @Override
    public Collection<? extends World> worlds() {
        return worldManager.worlds();
    }

    @Override
    public Optional<? extends World> world(final Key key) {
        return worlds().stream().filter(w -> w.key().equals(key)).findFirst();
    }

    @Override
    public World createWorld(final WorldBuilder spec) {
        return worldManager.createWorld(spec.key(), spec.seed(), spec.generator().orElse(null));
    }

    @Override
    public boolean unloadWorld(final Key key, final boolean save) {
        try {
            return worldManager.unloadWorld(key, save) != null;
        } catch (final IOException e) {
            LOGGER.error("Saving world {} before unloading failed", key, e);
            return false;
        }
    }

    @Override
    public Collection<? extends Player> onlinePlayers() {
        return playerSnapshot;
    }

    @Override
    public Optional<? extends Player> player(final UUID uuid) {
        return onlinePlayers().stream().filter(p -> p.uuid().equals(uuid)).findFirst();
    }

    @Override
    public Optional<? extends Player> player(final String name) {
        return onlinePlayers().stream()
                .filter(p -> p.name().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public FidorialOfflinePlayers offlinePlayers() {
        return offlinePlayers;
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    public ServerConfig config() {
        return config;
    }

    public KeyPair keyPair() {
        return keyPair;
    }

    public MojangSessionService sessionService() {
        return sessionService;
    }

    public ProtocolMap protocolMap() {
        return protocolMap;
    }

    public Registries registries() {
        return registries;
    }

    public RegistryHolder dynamicRegistries() {
        return registries.dynamic();
    }

    public ChunkNetworkSerializer chunkSerializer() {
        return chunkSerializer;
    }

    public ThreadedRegionRegionizer regionizer() {
        return regionizer;
    }

    public ThreadedChunkWorker chunkWorker() {
        return chunkWorker;
    }

    public AiWorker aiWorker() {
        return aiWorker;
    }

    public CommandManager commandManager() {
        return commandManager;
    }

    public ClickCallbackManager clickCallbacksManager() {
        return clickCallbackManager;
    }

    public CodeOfConductManager codeOfConduct() {
        return codeOfConduct;
    }

    @Override
    public CommandRegistry commands() {
        return commandManager;
    }

    public WorldManager worldManager() {
        return worldManager;
    }

    public PlayerInventoryStorage playerInventoryStorage() {
        return services.find(PlayerInventoryStorage.class).orElse(defaultInventoryStorage);
    }

    public ChestViewerTracker chestViewers() {
        return chestViewers;
    }

    public PlayerEnderChestStorage playerEnderChestStorage() {
        return services.find(PlayerEnderChestStorage.class).orElse(defaultEnderChestStorage);
    }

    public PlayerDataStorage playerDataStorage() {
        return services.find(PlayerDataStorage.class).orElse(defaultPlayerDataStorage);
    }

    public BossBarRegistry bossBarRegistry() {
        return services.find(BossBarRegistry.class).orElse(bossBarRegistry);
    }

    public BlockStateRegistry blockStateRegistry() {
        return blockStateRegistry;
    }

    public ConsoleSender getConsole() {
        return console;
    }

    public WeatherEngine weatherEngine() {
        return weatherEngine;
    }

    public DayNightThread dayNightEngine() {
        return dayNightEngine;
    }

    public BlockEditService blockEdits() {
        return blockEdits;
    }

    public EntityIdAllocator entityIds() {
        return entityIds;
    }

    public void spawnEntity(final AbstractEntity entity) {
        if (!(entity.world() instanceof final ServerWorld world)) {
            throw new IllegalArgumentException("Cannot summon an entity into a non-existent or unloaded world :" + entity);
        }

        world.addEntity(entity);

        if (entity instanceof AbstractMob) {
            regionizer.addTicket(world.dimension().id(), entity.chunk());
        }

        entityTracker.update(entity, players());
    }

    public void despawnEntity(final AbstractEntity entity) {
        if (entity.world() instanceof final ServerWorld world) {
            world.removeEntity(entity);

            if (entity instanceof AbstractMob) {
                regionizer.removeTicket(world.dimension().id(), entity.chunk());
            }
        }

        entity.remove();

        entityTracker.untrack(entity);
    }

    public void addPlayerConnection(final ClientConnection connection) {
        connections.add(connection);
        refreshPlayerSnapshot();
    }

    public void removePlayerConnection(final ClientConnection connection) {
        if (connection.player() != null) {
            connection.player().clearActiveBossBars();
        }
        connections.remove(connection);
        entityTracker.removeViewer(connection);
        refreshPlayerSnapshot();
    }

    public EntityTracker entityTracker() {
        return entityTracker;
    }

    public List<ServerPlayer> players() {
        return playerSnapshot;
    }

    private void refreshPlayerSnapshot() {
        final List<ServerPlayer> snapshot = new ArrayList<>(connections.size());
        for (final ClientConnection connection : connections) {
            final ServerPlayer player = connection.player();
            if (player != null) {
                snapshot.add(player);
            }
        }
        this.playerSnapshot = List.copyOf(snapshot);
        invalidateAudiences();
    }

    public void broadcastNear(
            final World world, final double x, final double y, final double z, final ClientboundPacket packet) {
        final double radius = config.sendDistance() * 16.0 + 16.0;
        final double radiusSq = radius * radius;
        for (final ServerPlayer player : players()) {
            if (player.isRemoved() || player.world() != world) {
                continue;
            }
            final Location loc = player.location();
            final double dx = loc.x() - x;
            final double dy = loc.y() - y;
            final double dz = loc.z() - z;
            if (dx * dx + dy * dy + dz * dz <= radiusSq) {
                player.connection().send(packet);
            }
        }
    }

    public void broadcast(final ClientboundPacket packet) {
        for (final ClientConnection connection : connections) {
            connection.send(packet);
        }
    }

    @Override
    public int playerCount() {
        return connections.size();
    }

    public CombatEngine combat() {
        return combat;
    }

    @Override
    public TranslationStore translationStore() {
        return TranslationStore.current();
    }

    @FunctionalInterface
    private interface ThrowingRunnable {
        void run() throws Exception;
    }
}
