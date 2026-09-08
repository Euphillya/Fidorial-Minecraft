package fr.euphyllia.fidorial.testplugin.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fr.euphyllia.fidorial.testplugin.CounterService;
import fr.euphyllia.fidorial.testplugin.TestPlugin;
import fr.euphyllia.fidorial.testplugin.command.argument.BaguetteArgument;
import fr.euphyllia.fidorial.testplugin.terrain.HillsGenerator;
import fr.euphyllia.fidorial.testplugin.terrain.SwamplandGenerator;
import fr.euphyllia.fidorial.testplugin.worldgen.OverworldGenerator;
import fr.fidorial.command.CommandSender;
import fr.fidorial.command.CommandSource;
import fr.fidorial.command.MessageComponentSerializer;
import fr.fidorial.command.argument.ArgumentTypes;
import fr.fidorial.command.argument.resolvers.BlockPosResolver;
import fr.fidorial.command.argument.resolvers.NbtPathResolver;
import fr.fidorial.entity.Player;
import fr.fidorial.item.ItemStack;
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.data.SoundEvent;
import fr.fidorial.scheduler.RegionTps;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import fr.fidorial.world.WorldBuilder;
import fr.fidorial.world.generation.WorldGenerator;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.resource.ResourcePackCallback;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.BlockNBTComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static fr.fidorial.command.Commands.argument;
import static fr.fidorial.command.Commands.literal;

public final class ApiTestCommand {
    private static TestPlugin plugin;

    private final Map<UUID, Map<Key, BossBar>> playerBossBars = new ConcurrentHashMap<>();

    private static final List<String> GENERATOR_NAMES = List.of(
            "swampland", "hills", "overworld"
    );

    public ApiTestCommand(final TestPlugin plugin) {
        ApiTestCommand.plugin = plugin;
    }

    private Map<Key, BossBar> bossBarsOf(final Player player) {
        return playerBossBars.getOrDefault(player.uuid(), Map.of());
    }

    private static final DynamicCommandExceptionType ERROR_BOSSBAR_EXISTS =
            new DynamicCommandExceptionType(
                    id -> MessageComponentSerializer.message().serialize(
                            Component.translatable(
                                    "commands.bossbar.create.failed",
                                    Component.text(id.toString())
                            )
                    )
            );

    private static final DynamicCommandExceptionType ERROR_BOSSBAR_UNKNOWN =
            new DynamicCommandExceptionType(
                    id -> MessageComponentSerializer.message().serialize(
                            Component.translatable(
                                    "commands.bossbar.unknown",
                                    Component.text(id.toString())
                            )
                    )
            );

    public LiteralCommandNode<CommandSource> create() {
        return literal("apitest")
                .then(literal("info").executes(ctx -> info(plugin, ctx)))
                .then(literal("tps").executes(ctx -> tps(plugin, ctx)))
                .then(literal("worlds").executes(ctx -> worlds(plugin, ctx)))
                .then(literal("players").executes(ctx -> players(plugin, ctx)))
                .then(literal("service").executes(ctx -> service(plugin, ctx)))
                .then(literal("schedule").executes(ctx -> schedule(plugin, ctx)))
                .then(literal("perms").executes(ApiTestCommand::perms))
                .then(literal("teleport")
                        .then(argument("x", ArgumentTypes.doubleArg())
                                .then(argument("y", ArgumentTypes.doubleArg())
                                        .then(argument("z", ArgumentTypes.doubleArg())
                                                .executes(ApiTestCommand::tp)))))
                .then(literal("tpworld")
                        .executes(ctx -> tpWorld(plugin, ctx, Key.key("overworld")))
                        .then(argument("name", ArgumentTypes.world())
                                .executes(ctx -> tpWorld(plugin, ctx, ctx.getArgument("name", World.class).key()))))
                .then(literal("createworld")
                        .executes(ctx -> createWorld(ctx, Key.key(UUID.randomUUID().toString()), "overworld"))
                        .then(argument("name", ArgumentTypes.key())
                                .executes(ctx -> createWorld(ctx, ctx.getArgument("name", Key.class), "overworld"))
                                .then(argument("generator", ArgumentTypes.greedyString())
                                        .suggests((_, builder) -> {
                                            final String remaining = builder.getRemaining().toLowerCase(Locale.ROOT);
                                            for (final String name : GENERATOR_NAMES) {
                                                if (name.startsWith(remaining)) {
                                                    builder.suggest(name);
                                                }
                                            }
                                            return builder.buildFuture();
                                        })
                                        .executes(ctx -> createWorld(
                                                ctx,
                                                ctx.getArgument("name", Key.class),
                                                ctx.getArgument("generator", String.class))))))
                .then(literal("unloadworld")
                        .executes(ctx -> unloadWorld(ctx, Key.key(UUID.randomUUID().toString())))
                        .then(argument("name", ArgumentTypes.world())
                                .executes(ctx -> unloadWorld(ctx, ctx.getArgument("name", World.class).key()))))
                .then(literal("sound")
                        .executes(ApiTestCommand::soundDemo)
                        .then(argument("key", ArgumentTypes.resource(RegistryKey.SOUND_EVENT))
                                .executes(ApiTestCommand::soundDefault)
                                .then(argument("volume", ArgumentTypes.floatArg())
                                        .executes(ApiTestCommand::soundVolume)
                                        .then(argument("pitch", ArgumentTypes.floatArg())
                                                .executes(ApiTestCommand::soundPitch)))))
                .then(literal("stopsound")
                        .executes(ApiTestCommand::stopAllSound)
                        .then(argument("key", ArgumentTypes.key()).executes(ApiTestCommand::stopSound)))
                .then(literal("callback")
                        .executes(ApiTestCommand::clickCallback))
                .then(literal("baguette")
                        .then(argument("type", BaguetteArgument.baguette())
                                .executes(ApiTestCommand::baguette)))
                .then(literal("itemhover").executes(ApiTestCommand::itemHover))
                .then(literal("playerhead").executes(ApiTestCommand::playerHead))
                .then(literal("resourcepack")
                        .executes(ApiTestCommand::resourcePackBroadcast))
                .then(literal("tablist")
                        .then(literal("broadcast").executes(ApiTestCommand::tabListBroadcast)))
                // TODO: should be replaced by server-side /data command
                .then(literal("nbt")
                        .then(literal("entity")
                                .executes(ApiTestCommand::nbtEntitySelf)
                                .then(argument("path", ArgumentTypes.nbtPath())
                                        .executes(ApiTestCommand::nbtEntityPath)))
                        .then(literal("block")
                                .then(argument("pos", ArgumentTypes.blockPosition())
                                        .executes(ApiTestCommand::nbtBlockAtPos)
                                        .then(argument("path", ArgumentTypes.nbtPath())
                                                .executes(ApiTestCommand::nbtBlockAtPosWithPath))))
                        .then(literal("storage")
                                .executes(ApiTestCommand::nbtStorage))
                )
                .then(literal("bossbar")
                        .then(literal("show")
                                .then(argument("name", ArgumentTypes.key())
                                        .executes(this::bossBarShow)
                                        .then(argument("progress", ArgumentTypes.floatArg(0f, 1f))
                                                .executes(this::bossBarShowWithProgress)
                                                .then(argument("color", ArgumentTypes.bossBarColor())
                                                        .then(argument("overlay", ArgumentTypes.bossBarOverlay())
                                                                .then(argument("flag", ArgumentTypes.bossBarFlag())
                                                                        .executes(this::bossBarShowCustom)))))))
                        .then(literal("hide")
                                .requires(source -> {
                                    final CommandSender sender = source.sender();
                                    if (!(sender instanceof final Player player)) {
                                        return false;
                                    }

                                    return !bossBarsOf(player).isEmpty();
                                })
                                .then(argument("name", ArgumentTypes.key())
                                        .suggests((ctx, builder) -> {
                                            final CommandSender sender = ctx.getSource().sender();

                                            if (!(sender instanceof final Player player)) {
                                                return builder.buildFuture();
                                            }

                                            bossBarsOf(player).keySet().forEach(key ->
                                                    builder.suggest(key.asString())
                                            );

                                            return builder.buildFuture();
                                        })
                                        .executes(this::bossBarHide)))
                )
                .then(literal("reenter_configuration")
                        .executes(ApiTestCommand::reenterConfigurationPhase))
                .build();
    }

    private static int reenterConfigurationPhase(CommandContext<CommandSource> ctx) {
        plugin.server().sendMessage(Component.text("[TestPlugin] Resending all players to the CONFIGURATION phase..."));
        plugin.server().onlinePlayers().forEach(Player::enterConfigurationPhase);
        plugin.server().sendMessage(Component.text("[TestPlugin] Resent ")
                .append(Component.text(plugin.server().onlinePlayers().size()).decorate(TextDecoration.BOLD)
                        .append(Component.text(" player(s)."))).color(NamedTextColor.GREEN));
        return Command.SINGLE_SUCCESS;
    }

    private static int tabListBroadcast(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        final Component header = MiniMessage.miniMessage().deserialize(
                "<gradient:aqua:blue><bold>Fidorial</bold></gradient> <gray>| Test Plugin</gray>");
        final Component footer = Component.text(
                "Online: " + plugin.server().playerCount(), NamedTextColor.DARK_GRAY);

        plugin.server().sendPlayerListHeaderAndFooter(header, footer);

        plugin.msg(sender, "[TestPlugin] Broadcasted tab list header/footer to "
                + plugin.server().playerCount() + " player(s).");

        return Command.SINGLE_SUCCESS;
    }

    private static int nbtEntitySelf(final CommandContext<CommandSource> ctx) {
        return nbtEntity(ctx, "");
    }

    private static int nbtEntityPath(final CommandContext<CommandSource> ctx) {
        final NbtPathResolver path = ctx.getArgument("path", NbtPathResolver.class);
        return nbtEntity(ctx, path.asString());
    }

    private static int nbtEntity(final CommandContext<CommandSource> ctx, final String path) {
        final CommandSender sender = ctx.getSource().sender();
        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return 0;
        }

        final Component nbtComponent = Component.entityNBT()
                .selector("@s")
                .nbtPath(path)
                .interpret(true)
                .build();

        player.sendMessage(nbtComponent);
        return Command.SINGLE_SUCCESS;
    }

    private static int nbtBlockAtPos(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        return nbtBlock(ctx, "");
    }

    private static int nbtBlockAtPosWithPath(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        final NbtPathResolver path = ctx.getArgument("path", NbtPathResolver.class);
        return nbtBlock(ctx, path.asString());
    }

    private static int nbtBlock(final CommandContext<CommandSource> ctx, final String path) throws CommandSyntaxException {
        final CommandSender sender = ctx.getSource().sender();
        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return 0;
        }

        final BlockPos pos = ctx.getArgument("pos", BlockPosResolver.class).resolve(ctx.getSource());

        final BlockNBTComponent nbtComponent = Component.blockNBT()
                .absoluteWorldPos(pos.x(), pos.y(), pos.z())
                .nbtPath(path)
                .interpret(true)
                .build();

        player.sendMessage(nbtComponent);
        return Command.SINGLE_SUCCESS;
    }

    private static int nbtStorage(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        final Component nbtComponent = Component.storageNBT()
                .storage(Key.key("fidorial", "test"))
                .nbtPath("")
                .interpret(true)
                .build();

        sender.sendMessage(nbtComponent);
        return Command.SINGLE_SUCCESS;
    }

    private static int soundDefault(final CommandContext<CommandSource> ctx) {
        return playSound(ctx, 1.0f, 1.0f);
    }

    private static int soundVolume(final CommandContext<CommandSource> ctx) {
        final Float volume = ctx.getArgument("volume", Float.class);

        return playSound(ctx, volume, 1.0f);
    }

    private static int soundPitch(final CommandContext<CommandSource> ctx) {
        final Float volume = ctx.getArgument("volume", Float.class);
        final Float pitch = ctx.getArgument("pitch", Float.class);

        return playSound(ctx, volume, pitch);
    }

    private static int playSound(final CommandContext<CommandSource> ctx, final float volume, final float pitch) {
        final CommandSender sender = ctx.getSource().sender();

        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final SoundEvent sound = ctx.getArgument("key", SoundEvent.class);
        final Key soundKey = sound.key();

        player.playSound(Sound.sound(soundKey, Sound.Source.MASTER, volume, pitch));

        plugin.msg(player, "[TestPlugin] Played sound " + soundKey + " (volume=" + volume + ", pitch=" + pitch + ")");

        return Command.SINGLE_SUCCESS;
    }

    private static int soundDemo(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        player.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.PLAYER, 1.0f, 1.0f));

        player.playSound(
                Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.MASTER, 0.8f, 1.4f),
                Sound.Emitter.self());

        player.playSound(
                Sound.sound(Key.key("block.bell.use"), Sound.Source.BLOCK, 1.0f, 0.8f), 0.0, 64.0, 0.0);

        plugin.msg(player, "[TestPlugin] Sound demo executed.");

        return Command.SINGLE_SUCCESS;
    }

    private static int stopSound(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final Key key = ctx.getArgument("key", Key.class);

        player.stopSound(SoundStop.named(key));

        plugin.msg(player, "[TestPlugin] Stopped sound " + key);

        return Command.SINGLE_SUCCESS;
    }

    private static int stopAllSound(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        player.stopSound(SoundStop.all());

        plugin.msg(player, "[TestPlugin] Stopped all sounds.");

        return Command.SINGLE_SUCCESS;
    }

    private static int info(final TestPlugin plugin, final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        plugin.msg(
                sender,
                "[TestPlugin] MC " + plugin.server().minecraftVersion()
                        + " | protocole " + plugin.server().protocolVersion()
                        + " | running=" + plugin.server().isRunning()
                        + " | plugins=" + plugin.server().plugins().loaded().size()
                        + " | events=" + plugin.eventCount());

        return Command.SINGLE_SUCCESS;
    }

    private static int tps(final TestPlugin plugin, final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        final List<? extends RegionTps> snapshots = plugin.server().scheduler().tpsSnapshots();

        if (snapshots.isEmpty()) {
            plugin.msg(sender, "[TestPlugin] Aucune region active.");
            return Command.SINGLE_SUCCESS;
        }

        for (final RegionTps tps : snapshots) {
            plugin.msg(
                    sender,
                    String.format(
                            Locale.ROOT,
                            "[TestPlugin] %s section(%d,%d) tps=%.1f mspt=%.2f queued=%d",
                            tps.world(),
                            tps.sectionX(),
                            tps.sectionZ(),
                            tps.tps(),
                            tps.msptAvg(),
                            tps.queuedTasks()));
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int worlds(final TestPlugin plugin, final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        final String worlds =
                plugin.server().worlds().stream().map(w -> w.key().toString()).collect(Collectors.joining(", "));

        plugin.msg(sender, "[TestPlugin] " + plugin.server().worlds().size() + " monde(s): " + worlds);

        return Command.SINGLE_SUCCESS;
    }


    private static int tp(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();
        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final double x = ctx.getArgument("x", Double.class);
        final double y = ctx.getArgument("y", Double.class);
        final double z = ctx.getArgument("z", Double.class);

        final boolean ok = player.teleport(x, y, z);
        plugin.msg(player, "[TestPlugin] Teleportation " + (ok ? "OK" : "refusee") + " vers " + x + ", " + y + ", " + z);
        return Command.SINGLE_SUCCESS;
    }

    private static int tpWorld(
            final TestPlugin plugin, final CommandContext<CommandSource> ctx, final Key key) {
        final CommandSender sender = ctx.getSource().sender();
        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final Collection<? extends World> worlds = plugin.server().worlds();
        final World target = plugin.server().world(key).orElse(null);
        if (target == null) {

            plugin.msg(player, "[TestPlugin] Monde " + key + " inexistant.");
            plugin.msg(player, "Liste des mondes : ");

            for (final World world : worlds) {
                plugin.msg(player, world.key().asString());
            }

            return Command.SINGLE_SUCCESS;
        }

        final Location destination = new Location(8.5, 100.0, 8.5, 0f, 0f);
        final boolean ok = player.teleport(target, destination);
        plugin.msg(player, "[TestPlugin] Teleportation inter-monde " + (ok ? "OK" : "refusee") + " vers " + key);
        return Command.SINGLE_SUCCESS;
    }

    private static int players(final TestPlugin plugin, final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        final var players = plugin.server().onlinePlayers();

        plugin.msg(
                sender,
                "[TestPlugin] "
                        + players.size()
                        + " joueur(s): "
                        + players.stream().map(Player::name).collect(Collectors.joining(", ")));

        return Command.SINGLE_SUCCESS;
    }

    private static int service(final TestPlugin plugin, final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        final var service = plugin.server().services().find(CounterService.class);

        if (service.isEmpty()) {
            plugin.msg(sender, "<red>CounterService introuvable.</red>");
            return Command.SINGLE_SUCCESS;
        }

        plugin.msg(sender, "[TestPlugin] compteur = " + service.get().increment());

        return Command.SINGLE_SUCCESS;
    }

    private static int schedule(final TestPlugin plugin, final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        final World world = plugin.server().worlds().stream().findFirst().orElse(null);

        if (world == null) {
            plugin.msg(sender, "[TestPlugin] Aucun monde.");
            return Command.SINGLE_SUCCESS;
        }

        plugin.server()
                .scheduler()
                .executeDelayed(
                        world.key(), new ChunkPos(0, 0), () -> plugin.msg(sender, "[TestPlugin] Scheduler OK"), 40L);

        return Command.SINGLE_SUCCESS;
    }

    private static int perms(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        plugin.msg(
                sender,
                sender.name()
                        + " | console=" + sender.name().equals("Console")
                        + " | testplugin.use=" + sender.hasPermission("testplugin.use")
                        + " | testplugin.admin=" + sender.hasPermission("testplugin.admin"));

        return Command.SINGLE_SUCCESS;
    }

    private static WorldGenerator resolveGenerator(final String name, final long seed) {
        return switch (name.toLowerCase(Locale.ROOT)) {
            case "swampland" -> new SwamplandGenerator(seed, 68, 5, 62, 110);
            case "hills" -> new HillsGenerator(seed, 64, 24, 60);
            case "overworld" -> new OverworldGenerator(seed);
            default -> throw new IllegalArgumentException("Unknown generator: " + name);
        };
    }

    private static int createWorld(
            final CommandContext<CommandSource> ctx, final Key key, final String generatorName) {
        final CommandSender sender = ctx.getSource().sender();

        if (plugin.server().world(key).isPresent()) {
            plugin.msg(sender, "[TestPlugin] Le monde " + key + " existe deja (test d'idempotence OK).");
            return Command.SINGLE_SUCCESS;
        }

        final long seed = 20260716L;
        final WorldGenerator generator = resolveGenerator(generatorName, seed);
        final WorldBuilder spec = WorldBuilder.builder(key)
                .seed(seed)
                .generator(generator)
                .build();

        final World world = plugin.server().createWorld(spec);

        plugin.msg(
                sender,
                "[TestPlugin] Monde cree: " + world.key()
                        + " | minY=" + world.minY()
                        + " | height=" + world.height()
                        + " | type=" + world.dimensionType().key()
                        + " | total=" + plugin.server().worlds().size());

        return Command.SINGLE_SUCCESS;
    }

    private static int unloadWorld(
            final CommandContext<CommandSource> ctx, final Key key) {
        final CommandSender sender = ctx.getSource().sender();

        final boolean unloaded = plugin.server().unloadWorld(key, true);
        if (unloaded) {
            plugin.msg(
                    sender,
                    "[TestPlugin] Monde decharge: " + key
                            + " | total=" + plugin.server().worlds().size());
        } else {
            plugin.msg(
                    sender,
                    "[TestPlugin] Dechargement refuse pour " + key
                            + " (monde inexistant, monde principal, ou joueurs presents).");
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int clickCallback(final CommandContext<CommandSource> ctx) {
        final ClickCallback<Player> narrow = clicker -> {
            clicker.sendMessage(Component.text("Callback fired for " + clicker.name(), NamedTextColor.YELLOW));
            plugin.logger.info("Click callback consumed by {}", clicker.name());
        };

        final ClickCallback<Audience> callback = ClickCallback.widen(narrow, Player.class);
        final ClickCallback.Options options = ClickCallback.Options.builder()
                .uses(ClickCallback.UNLIMITED_USES)
                .lifetime(ClickCallback.DEFAULT_LIFETIME)
                .build();

        final Component callbackComponent = Component.text("[Click me!]", NamedTextColor.GREEN).clickEvent(ClickEvent.callback(callback, options));
        ctx.getSource().sender().sendMessage(callbackComponent);
        return Command.SINGLE_SUCCESS;
    }

    private static int itemHover(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        final ItemStack diamond = ItemStack.of(Key.key("diamond"), 64);

        final Component item = Component.translatable(diamond)
                .color(NamedTextColor.AQUA)
                .hoverEvent(diamond);

        sender.sendMessage(Component.text("[TestPlugin] Hover me: ").append(item));

        plugin.msg(sender, "[TestPlugin] Translation key = " + diamond.translationKey());
        sender.sendMessage(Component.text("[TestPlugin] Rendered key = ").append(Component.translatable(diamond.translationKey())));

        return Command.SINGLE_SUCCESS;
    }

    private static int playerHead(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();

        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final Component head = Component.object(player);

        sender.sendMessage(Component.text("[TestPlugin] Your head: ").append(head));

        return Command.SINGLE_SUCCESS;
    }

    private static int resourcePackBroadcast(final CommandContext<CommandSource> ctx) {
        final var server = ctx.getSource().server();

        final ResourcePackInfo pack = ResourcePackInfo.resourcePackInfo(
                UUID.fromString("2e26aec4-e14c-4947-bdc1-99c391d6d257"),
                URI.create("https://download.mc-packs.net/pack/e7d205a0f0c8bef05cdd540e08b0437fbdb973d2.zip"),
                "e7d205a0f0c8bef05cdd540e08b0437fbdb973d2");

        final ResourcePackRequest request = ResourcePackRequest.resourcePackRequest()
                .packs(pack)
                .required(false)
                .prompt(MiniMessage.miniMessage().deserialize(
                        "<gradient:blue:white:red>[TestPlugin] Try the best custom resource pack!</gradient>"))
                .callback(ResourcePackCallback.onTerminal(
                        (id, audience) -> audience.sendMessage(Component.text("Pack loaded, thanks!", NamedTextColor.GREEN)),
                        (id, audience) -> audience.sendMessage(Component.text("Pack failed or declined.", NamedTextColor.RED))))
                .build();

        server.sendResourcePacks(request);

        plugin.msg(ctx.getSource().sender(), "[TestPlugin] Resource pack request sent to " + server.playerCount() + " player(s).");

        return Command.SINGLE_SUCCESS;
    }

    private int bossBarShow(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        return createBossBar(ctx, ctx.getArgument("name", Key.class), 1.0f);
    }

    private int bossBarShowCustom(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        return createBossBar(
                ctx,
                ctx.getArgument("name", Key.class),
                ctx.getArgument("progress", Float.class),
                ctx.getArgument("color", BossBar.Color.class),
                ctx.getArgument("overlay", BossBar.Overlay.class),
                Set.of(ctx.getArgument("flag", BossBar.Flag.class))
        );
    }

    private int bossBarShowWithProgress(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        return createBossBar(ctx, ctx.getArgument("name", Key.class), ctx.getArgument("progress", Float.class));
    }

    private int createBossBar(final CommandContext<CommandSource> ctx, final Key name, final float progress) throws CommandSyntaxException {
        return createBossBar(
                ctx,
                name,
                progress,
                BossBar.Color.RED,
                BossBar.Overlay.PROGRESS,
                Set.of(BossBar.Flag.DARKEN_SCREEN)
        );
    }

    private int createBossBar(
            final CommandContext<CommandSource> ctx,
            final Key name,
            final float progress,
            final BossBar.Color color,
            final BossBar.Overlay overlay,
            final Set<BossBar.Flag> flags
    ) throws CommandSyntaxException {
        final CommandSender sender = ctx.getSource().sender();

        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final Map<Key, BossBar> bars = playerBossBars.computeIfAbsent(
                player.uuid(),
                _ -> new ConcurrentHashMap<>()
        );

        if (bars.containsKey(name)) {
            throw ERROR_BOSSBAR_EXISTS.create(name);
        }

        final BossBar bar = BossBar.bossBar(
                Component.text("Test Boss Bar", NamedTextColor.RED),
                Math.clamp(progress, 0.0f, 1.0f),
                color,
                overlay,
                flags
        );

        bars.put(name, bar);
        player.showBossBar(bar);
        player.refreshCommands();

        player.sendMessage(
                Component.translatable(
                        "commands.bossbar.create.success",
                        Component.text(name.toString())
                )
        );

        return Command.SINGLE_SUCCESS;
    }

    private int bossBarHide(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        final CommandSender sender = ctx.getSource().sender();

        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final Key key = ctx.getArgument("name", Key.class);

        final Map<Key, BossBar> bars = playerBossBars.get(player.uuid());
        final BossBar bar = bars == null ? null : bars.remove(key);

        if (bar == null) {
            throw ERROR_BOSSBAR_UNKNOWN.create(key);
        }

        player.hideBossBar(bar);
        player.refreshCommands();

        player.sendMessage(
                Component.translatable(
                        "commands.bossbar.hide.success",
                        Component.text(key.toString())
                )
        );

        return Command.SINGLE_SUCCESS;
    }

    private static int baguette(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();
        final BaguetteArgument.Baguette baguette = ctx.getArgument("type", BaguetteArgument.Baguette.class);

        plugin.msg(sender, "[TestPlugin] You chose: " + baguette.name().toLowerCase(Locale.ROOT));

        return Command.SINGLE_SUCCESS;
    }
}
