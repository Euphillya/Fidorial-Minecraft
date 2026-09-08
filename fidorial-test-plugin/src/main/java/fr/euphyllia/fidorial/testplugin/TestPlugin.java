package fr.euphyllia.fidorial.testplugin;

import fr.euphyllia.fidorial.testplugin.command.ApiTestCommand;
import fr.euphyllia.fidorial.testplugin.command.BiomeCommand;
import fr.euphyllia.fidorial.testplugin.command.CustomMobCommand;
import fr.euphyllia.fidorial.testplugin.command.DialogCommand;
import fr.euphyllia.fidorial.testplugin.command.ItemCommand;
import fr.euphyllia.fidorial.testplugin.command.PregenCommand;
import fr.euphyllia.fidorial.testplugin.command.WorldgenCommand;
import fr.euphyllia.fidorial.testplugin.dialog.TestDialogs;
import fr.euphyllia.fidorial.testplugin.mob.BullMobs;
import fr.euphyllia.fidorial.testplugin.mob.CompanionMobs;
import fr.euphyllia.fidorial.testplugin.pregen.PregenTask;
import fr.euphyllia.fidorial.testplugin.terrain.TestBiomes;
import fr.euphyllia.fidorial.testplugin.terrain.TestDimensionTypes;
import fr.euphyllia.fidorial.testplugin.worldgen.GeneratorSettings;
import fr.euphyllia.fidorial.testplugin.worldgen.OverworldGenerator;
import fr.fidorial.Server;
import fr.fidorial.command.CommandRegistry;
import fr.fidorial.command.CommandSender;
import fr.fidorial.entity.Player;
import fr.fidorial.event.EventPriority;
import fr.fidorial.event.player.BlockBreakEvent;
import fr.fidorial.event.player.BlockPlaceEvent;
import fr.fidorial.event.player.PlayerChatEvent;
import fr.fidorial.event.player.PlayerDialogActionEvent;
import fr.fidorial.event.player.PlayerJoinEvent;
import fr.fidorial.event.player.PlayerLoginAttemptEvent;
import fr.fidorial.event.player.PlayerQuitEvent;
import fr.fidorial.event.server.ServerStartedEvent;
import fr.fidorial.event.server.ServerStatusRequestEvent;
import fr.fidorial.event.server.ServerStoppingEvent;
import fr.fidorial.plugin.Plugin;
import fr.fidorial.plugin.PluginContext;
import fr.fidorial.service.ServicePriority;
import fr.fidorial.status.ServerStatus;
import fr.fidorial.world.generation.WorldGenerator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jspecify.annotations.Nullable;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public final class TestPlugin implements Plugin {

    private static final String SEED_PROPERTY = "fidorial.worldgen.seed";

    private static final long DEFAULT_SEED = 20260716L;

    private static final MiniMessage MM = MiniMessage.miniMessage();
    private static final PlainTextComponentSerializer PLAIN = PlainTextComponentSerializer.plainText();

    private final AtomicLong eventCount = new AtomicLong();
    public @Nullable ComponentLogger logger;
    public @Nullable Server server;
    private @Nullable PluginContext context;
    private volatile @Nullable PregenTask task;
    private @Nullable OverworldGenerator generator;

    private static long resolveSeed(final ComponentLogger logger) {
        final String property = System.getProperty(SEED_PROPERTY);
        if (property == null || property.isBlank()) {
            return DEFAULT_SEED;
        }
        if (property.equalsIgnoreCase("random")) {
            final long random = new Random().nextLong();
            logger.info("[TestPlugin] Randomly selected seed: {}", random);
            return random;
        }
        try {
            return Long.parseLong(property.trim());
        } catch (final NumberFormatException invalid) {
            return property.hashCode();
        }
    }

    public @Nullable OverworldGenerator generator() {
        return generator;
    }

    public PregenTask getTask() {
        return task;
    }

    public void setTask(final PregenTask task) {
        this.task = task;
    }

    public Server server() {
        return server;
    }

    public long eventCount() {
        return eventCount.get();
    }

    @Override
    public void onLoad(final PluginContext context) {
        this.context = context;
        this.logger = context.logger();
        this.server = context.server();

        TestBiomes.registerAll(context.server().biomes(), context.logger());

        TestDialogs.registerAll(context.server().dialogs(), context.logger());

        TestDimensionTypes.registerAll(context.server().dimensionTypes(), context.logger());

//        BullMobs.attachToCows(context.server().mobs(), this, context.logger());
       BullMobs.registerBull(context.server().mobs(), this, context.logger());
        CompanionMobs.register(context.server().mobs(), this, context.logger());

        final long seed = resolveSeed(context.logger());
        this.generator = new OverworldGenerator(GeneratorSettings.defaults(seed));

        context.services().register(WorldGenerator.class, generator, this);
        context.logger().info(
                "[TestPlugin] Overworld generator saving (seed={}, sea level={})",
                seed,
                generator.settings().seaLevel());

        final int[] spawn = generator.findSpawn(0, 0, 4000, -64, 319);
        if (spawn == null) {
            context.logger().warn("[TestPlugin] No spawn point found near the origin.");
        } else {
            context.logger().info(
                    "[TestPlugin] Spawn point coordinates: spawn-x={} spawn-y={} spawn-z={}",
                    spawn[0] + 0.5,
                    spawn[1],
                    spawn[2] + 0.5);
        }

        logger.info(
                "[TestPlugin] onLoad OK - id={} version={} dataFolder={}",
                context.meta().id(),
                context.meta().version(),
                context.dataFolder());
    }

    @Override
    public void onEnable() {
        logger.info("[TestPlugin] onEnable - Starting API tests");

        registerServices();
        registerEvents();
        registerCommands();
        TestPluginTranslations.register(context);

        logger.info("[TestPlugin] Ready. Type /apitest to launch the interactive tests.");
    }

    @Override
    public void onDisable() {
        logger.info("[TestPlugin] onDisable - {} event(s) observed during the session", eventCount.get());
        server.commands().unregisterNamespace(context.meta());
        TestBiomes.unregisterAll(server.biomes());
        TestDialogs.unregisterAll(server.dialogs());
        TestDimensionTypes.unregisterAll(context.server().dimensionTypes());
        BullMobs.unregisterAll(server.mobs(), this);
        server.mobs().unregisterAll(this);
        TestPluginTranslations.unregister();
    }

    public void msg(final CommandSender sender, final String miniMessageText) {
        sender.sendMessage(MM.deserialize(miniMessageText));
    }

    private void msg(final Player player, final String miniMessageText) {
        player.sendMessage(MM.deserialize(miniMessageText));
    }

    private void registerServices() {
        final AtomicLong counter = new AtomicLong();
        final CounterService impl = new CounterService() {
            @Override
            public long increment() {
                return counter.incrementAndGet();
            }

            @Override
            public long current() {
                return counter.get();
            }
        };
        server.services().register(CounterService.class, impl, this, ServicePriority.NORMAL);
        logger.info(
                "[TestPlugin] ServiceRegistry: CounterService register = {}",
                server.services().find(CounterService.class).isPresent());
    }

    private void registerEvents() {
        final var events = context.events();

        events.subscribe(ServerStatusRequestEvent.class, event -> {
            event.status(event.status().toBuilder()
                    .description(Component.text("HELLO!!!"))
                    .enforceSecureChat(true)
                    .samplePlayer(new ServerStatus.SamplePlayer("test", UUID.randomUUID()))
                    .maxPlayers(-999)
                    .players(999)
                    .version(new ServerStatus.Version(
                            "§aIDK §cXOXO",
                            event.status().version().protocolVersion()
                    ))
                    .build());
        });

        events.subscribe(ServerStartedEvent.class, e ->
                logger.info("[TestPlugin][event] ServerStartedEvent received, MC version {}",
                        e.server().minecraftVersion()));

        events.subscribe(ServerStoppingEvent.class, e -> logger.info("[TestPlugin][event] ServerStoppingEvent received"));

        events.subscribe(PlayerJoinEvent.class, e -> {
            eventCount.incrementAndGet();
            logger.info("[TestPlugin][event] PlayerJoin: {}", e.player().name());
            msg(e.player(), "[TestPlugin] Welcome " + e.player().name() + "! Type /apitest to test the API.");
        });

        events.subscribe(PlayerQuitEvent.class, e -> {
            eventCount.incrementAndGet();
            logger.info("[TestPlugin][event] PlayerQuit de {}", e.player().name());
        });

        events.subscribe(PlayerChatEvent.class, EventPriority.HIGH, e -> {
            eventCount.incrementAndGet();
            final String raw = PLAIN.serialize(e.message());
            if (raw.equalsIgnoreCase("!cancel")) {
                e.setCancelled(true);
                msg(e.player(), "[TestPlugin] Message cancelled (Cancellable test OK).");
            } else if (raw.startsWith("!upper ")) {
                e.setMessage(Component.text(raw.substring(7).toUpperCase(Locale.ROOT)));
            }
        });

        events.subscribe(BlockBreakEvent.class, e -> {
            eventCount.incrementAndGet();
        });

        events.subscribe(BlockPlaceEvent.class, e -> {
            eventCount.incrementAndGet();
        });
        events.subscribe(PlayerDialogActionEvent.class, e -> {
            eventCount.incrementAndGet();

            // The client sends these on its own initiative, so filter on the id before trusting anything.
            if (!e.id().equals(TestDialogs.SUBMIT_ID)) {
                return;
            }

            final var response = e.response();
            logger.info("[TestPlugin][event] dialogue submitted by {} : {}", e.player().name(), response.keys());

            if (response.isEmpty()) {
                msg(e.player(), "[TestPlugin] Custom action received, no value (no input in this dialog).");
                return;
            }

            msg(e.player(), "[TestPlugin] username=%s notify=%s color=%s volume=%s button=%s".formatted(
                    response.text("username").orElse("?"),
                    response.bool("notify").orElse(false),
                    response.text("color").orElse("?"),
                    response.number("volume").orElse(-1),
                    response.text("button").orElse("?")));
        });

        final boolean cancelLogin = false;
        events.subscribe(PlayerLoginAttemptEvent.class, e -> {
            eventCount.incrementAndGet();
            logger.info("[TestPlugin][event] login attempt de {} (auth={})", e.profile().name(), e.authenticated());
            if (!cancelLogin) return;
            e.setCancelled(cancelLogin);
            e.refuse(Component.text("Server under maintenance", NamedTextColor.RED));
        });
    }

    private void registerCommands() {
        final CommandRegistry registry = server.commands();
        registry.register(context.meta(), new PregenCommand(this).create());
        registry.register(context.meta(), new ApiTestCommand(this).create());
        registry.register(context.meta(), new BiomeCommand(this).create());
        registry.register(context.meta(), new WorldgenCommand(this).create());
        registry.register(context.meta(), new DialogCommand(this).create());
        registry.register(context.meta(), new CustomMobCommand(this).create());
        registry.register(context.meta(), new ItemCommand(this).create());
    }
}
