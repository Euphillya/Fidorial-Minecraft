package fr.euphyllia.fidorial.server.network.listener;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.ServerConfig;
import fr.euphyllia.fidorial.server.adventure.ClickCallbackManager;
import fr.euphyllia.fidorial.server.entity.AbstractEntity;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMob;
import fr.euphyllia.fidorial.server.entity.player.InventorySlots;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.inventory.ContainerMenu;
import fr.euphyllia.fidorial.server.inventory.EnderChestMenu;
import fr.euphyllia.fidorial.server.inventory.PlayerInventoryMenu;
import fr.euphyllia.fidorial.server.network.ClientConnection;
import fr.euphyllia.fidorial.server.network.ConnectionState;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundAnimatePacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundBlockChangedAckPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundBlockEventPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundCommandSuggestionsPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundEntityPositionSyncPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundGameEventPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundLoginPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundPlayerAbilitiesPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundPlayerInfoRemovePacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundPlayerInfoUpdatePacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundPlayerPositionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundRespawnPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundRotateHeadPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetEntityMetadataPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetEntityMetadataPacket.Entry;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetHealthPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSoundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundStartConfigurationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSystemChatPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.listener.PlayPacketListener;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.common.ServerboundClientInformationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundAcceptTeleportationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundAcknowledgeConfigurationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundAttackPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundChatCommandPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundChatPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundClientCommandPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundCommandSuggestionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundContainerClickPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundContainerClosePacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundCustomClickActionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundInteractPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundKeepAlivePacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundMovePlayerPosPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundMovePlayerPosRotPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerAbilitiesPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerActionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerInputPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundPlayerLoadedPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundResourcePackPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundSetCarriedItemPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundSetCreativeModeSlotPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundSwingPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundUseItemOnPacket;
import fr.euphyllia.fidorial.server.network.session.ChunkViewTracker;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.euphyllia.fidorial.server.world.ChunkGeneratorConfig;
import fr.euphyllia.fidorial.server.world.ChunkNetworkSerializer;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.WorldManager;
import fr.euphyllia.fidorial.server.world.block.EnderChestBlock;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.dialog.DialogResponse;
import fr.fidorial.entity.GameMode;
import fr.fidorial.entity.PlayerProfile;
import fr.fidorial.entity.RespawnPoint;
import fr.fidorial.event.player.BlockBreakEvent;
import fr.fidorial.event.player.BlockPlaceEvent;
import fr.fidorial.event.player.PlayerChatEvent;
import fr.fidorial.event.player.PlayerDialogActionEvent;
import fr.fidorial.event.player.PlayerJoinEvent;
import fr.fidorial.event.player.PlayerOpenEnderChestEvent;
import fr.fidorial.event.player.PlayerQuitEvent;
import fr.fidorial.event.player.PlayerRespawnEvent;
import fr.fidorial.inventory.EnderChestInventory;
import fr.fidorial.inventory.EquipmentSlotGroup;
import fr.fidorial.inventory.PlayerInventory;
import fr.fidorial.item.ItemDefaults;
import fr.fidorial.item.ItemStack;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.storage.player.PlayerDataStorage;
import fr.fidorial.world.BlockFace;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.Location;
import fr.fidorial.world.block.BlockPlaceContext;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

public final class PlayPacketHandler implements PlayPacketListener {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(PlayPacketHandler.class);

    private final ClientConnection connection;
    private final FidorialServer server;
    private final ServerConfig config;

    private @Nullable ServerPlayer player;
    private @Nullable ChunkViewTracker chunkView;
    private @Nullable ChunkPos ticket;

    public PlayPacketHandler(final ClientConnection connection) {
        this.connection = connection;
        this.server = connection.server();
        this.config = server.config();
    }

    @Override
    public void onEnter() {
        final RegistryHolder dynamic = server.dynamicRegistries();
        if (dynamic.isEmpty()) {
            LOGGER.error("Missing dynamic registries (GeneratedRegistryData empty): unable to join the game");
            connection.close();
            return;
        }

        final ServerWorld world = server.worldManager().overworld(); // FIXME: dont hardcode
        final Location spawn = new Location(config.spawnX(), config.spawnY(), config.spawnZ(), 0f, 0f); // we should preserve the player's loc in NBT
        this.player = createPlayer(world, spawn);
        connection.setPlayer(player);
        world.addEntity(player);

        sendLoginSequence();
        openChunkView(world, dynamic, spawn.chunk());
        spawnPlayer(spawn);

        connection.flushPendingMessages();
        connection.startKeepAlive();
        server.addPlayerConnection(connection);
        for (final ServerPlayer other : server.players()) {
            if (other == player) continue;
            connection.send(new ClientboundPlayerInfoUpdatePacket(other.profile(), other.gameMode().id(), 0));
            other.connection().send(new ClientboundPlayerInfoUpdatePacket(player.profile(), player.gameMode().id(), 0));
        }
        server.events().post(new PlayerJoinEvent(player));
        LOGGER.info("{} logged with uuid {}", player.name(), player.uuid());
    }

    @Override
    public void onDisconnect() {
        if (chunkView != null) {
            chunkView.close();
            chunkView.world().removeViewer(chunkView);
            chunkView = null;
        }
        if (ticket != null) {
            server.regionizer().removeTicket(worldId(), ticket);
            ticket = null;
        }
        if (player != null) {
            closeOpenMenu(false);
            server.events().post(new PlayerQuitEvent(player));
            serverWorld().removeEntity(player);
            player.permissions().revokeAll();
            player.remove();
            server.entityTracker().untrack(player);
            for (final ServerPlayer other : server.players()) {
                if (other == player) continue;
                other.connection().send(new ClientboundPlayerInfoRemovePacket(player.uuid()));
            }
        }
    }

    private ServerPlayer createPlayer(final ServerWorld world, final Location spawn) {
        final PlayerProfile profile = connection.profile();
        if (profile == null) {
            throw new IllegalStateException(
                    "Attempt to create a player without an authenticated profile (incomplete login)");
        }
        final PlayerDataStorage.PlayerData data = loadPlayerData(profile);
        final ServerPlayer created = new ServerPlayer(
                server.entityIds().allocate(),
                profile,
                loadInventory(profile),
                loadEnderChest(profile),
                data.gameMode(),
                connection,
                world,
                spawn);
        created.setRespawnPoint(restoreRespawnPoint(profile, data));
        return created;
    }

    private @Nullable RespawnPoint restoreRespawnPoint(
            final PlayerProfile profile, final PlayerDataStorage.PlayerData data) {
        final Key worldKey = data.respawnWorld();
        final Location location = data.respawnLocation();
        if (worldKey == null || location == null) {
            return null;
        }
        final ServerWorld world = server.worldManager().world(worldKey);
        if (world == null) {
            LOGGER.warn("Respawn point of {} targets the unknown world {}, dropped", profile.name(), worldKey);
            return null;
        }
        return new RespawnPoint(world, location);
    }

    private EnderChestInventory loadEnderChest(final PlayerProfile profile) {
        try {
            return server.playerEnderChestStorage().load(profile.uuid());
        } catch (final Exception e) {
            LOGGER.error("Chargement de l'ender chest de {} impossible, conteneur vide utilise", profile.name(), e);
            return new EnderChestInventory();
        }
    }

    private PlayerInventory loadInventory(final PlayerProfile profile) {
        try {
            final PlayerInventory inventory = server.playerInventoryStorage().load(profile.uuid());
            if (!inventory.isEmpty()) {
                LOGGER.debug("Inventaire de {} recharge", profile.name());
            }
            return inventory;
        } catch (final Exception e) {
            LOGGER.error("Chargement de l'inventaire de {} impossible, inventaire vide utilise", profile.name(), e);
            return new PlayerInventory();
        }
    }

    private PlayerDataStorage.PlayerData loadPlayerData(final PlayerProfile profile) {
        final PlayerDataStorage.PlayerData defaults = new PlayerDataStorage.PlayerData(config.defaultGameMode(), null, null);
        try {
            return server.playerDataStorage().load(profile.uuid(), defaults);
        } catch (final Exception e) {
            LOGGER.error("Chargement des donnees de {} impossible, valeurs par defaut utilisees", profile.name(), e);
            return defaults;
        }
    }

    private void sendLoginSequence() {
        final int dimensionType = server.dimensionTypes().networkId(worldManager().world(worldId()).generator.dimensionType().key());
        final Key[] dimensions = worldManager().worlds().stream().map(ServerWorld::key).toArray(Key[]::new);
        connection.send(new ClientboundLoginPacket(
                player.entityId(),
                worldManager().levelData().hardcore,
                dimensions,
                worldId(),
                dimensionType,
                config.viewDistance(),
                player.gameMode().id(),
                describeGenerator(worldId()) instanceof ChunkGeneratorConfig.Debug,
                describeGenerator(worldId()) instanceof ChunkGeneratorConfig.Flat,
                server.config().onlineMode()));
        connection.send(new ClientboundPlayerInfoUpdatePacket(
                player.profile(), player.gameMode().id(), 0));
        connection.send(ClientboundPlayerAbilitiesPacket.forGameMode(player.gameMode()));
        connection.send(ClientboundSetEntityMetadataPacket.of(
                player.entityId(),
                Entry.ofByte(ServerPlayer.MD_DISPLAYED_SKIN_PARTS, connection.displayedSkinParts())));
        player.invalidatePermissions();
        connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.START_WAITING_FOR_CHUNKS, 0f));
        server.weatherEngine().syncTo(connection::send);
        server.dayNightEngine().syncTo(worldManager().world(worldId()), connection::send);
        server.bossBarRegistry().syncTo(player);
    }

    private void openChunkView(final ServerWorld world, final RegistryHolder dynamic, final ChunkPos spawnChunk) {
        this.chunkView = new ChunkViewTracker(
                connection,
                server.chunkWorker(),
                world,
                new ChunkNetworkSerializer(server.blockStateRegistry(), server.biomeRegistry()),
                config.sendDistance(),
                config.viewDistance());
        this.ticket = spawnChunk;
        world.addViewer(chunkView);
        server.regionizer().addTicket(worldId(), ticket);
        chunkView.init(spawnChunk);
    }

    private void spawnPlayer(final Location spawn) {
        connection.send(new ClientboundPlayerPositionPacket(player.nextTeleportId(), spawn.x(), spawn.y(), spawn.z()));
        connection.send(player.inventoryMenu().buildSyncPacket(server.registries().network()));
    }

    @Override
    public void handlePlayerLoaded(final ServerboundPlayerLoadedPacket packet) {
        LOGGER.debug("{} a fini de charger le terrain", player.name());
    }

    @Override
    public void handleAcceptTeleportation(final ServerboundAcceptTeleportationPacket packet) {
        // Confirmation du client : rien a faire tant que l'anti-cheat n'existe pas.
    }

    public void enterConfiguration() {
        LOGGER.debug("Switching player {}, uuid {} from PLAY -> CONFIGURATION phase", player.name(), player.uuid());
        // vanilla removes the player fully and creates a new one, so we do the same
        onDisconnect();
        connection.pauseKeepAlive();
        connection.saveInventoryOnDisconnect().thenRunAsync(() -> {
            connection.send(new ClientboundStartConfigurationPacket());
            connection.setState(ConnectionState.CONFIGURATION);
        }, connection::execute);
    }

    @Override
    public void handleAcknowledgeConfiguration(final ServerboundAcknowledgeConfigurationPacket packet) {
        // Confirmation du client
    }

    @Override
    public void handleKeepAlive(final ServerboundKeepAlivePacket packet) {
        connection.acknowledgeKeepAlive(packet.id());
    }

    @Override
    public void handleClientInformation(final ServerboundClientInformationPacket packet) {
        connection.setLocale(Locale.forLanguageTag(packet.language().replace('_', '-')));
        connection.setDisplayedSkinParts(packet.displayedSkinParts());
        if (player != null) {
            player.setLocale(packet.language());
            connection.send(ClientboundSetEntityMetadataPacket.of(
                    player.entityId(),
                    Entry.ofByte(ServerPlayer.MD_DISPLAYED_SKIN_PARTS, packet.displayedSkinParts())));
        }
    }

    @Override
    public void handleSetCarriedItem(final ServerboundSetCarriedItemPacket packet) {
        final int slot = packet.slot();
        if (slot < 0 || slot > 8) {
            LOGGER.debug("{} annonce un slot de hotbar invalide : {}", player.name(), slot);
            return;
        }
        player.setSelectedSlot(slot);
    }

    @Override
    public void handleSetCreativeModeSlot(final ServerboundSetCreativeModeSlotPacket packet) {
        if (player.gameMode() != GameMode.CREATIVE) {
            LOGGER.debug("{} envoie un paquet creatif hors mode creatif (ignore)", player.name());
            return;
        }
        final int slot = InventorySlots.fromWindow(packet.slot());
        if (slot == InventorySlots.INVALID || slot >= player.inventory().size()) {
            return;
        }

        final ItemStack stack = packet.stack();

        if (stack.isEmpty()) {
            player.inventory().set(slot, ItemStack.EMPTY);
            return;
        }

        final int maxCount = Math.max(1, ItemDefaults.maxStackSize(stack.id(), stack));
        player.inventory().set(slot, stack.count() > maxCount ? stack.withCount(maxCount) : stack);
    }

    @Override
    public void handleChatCommand(final ServerboundChatCommandPacket packet) {
        server.commandManager().dispatchAsync(player, packet.command());
    }

    @Override
    public void handleChat(final ServerboundChatPacket packet) {
        if (player == null) {
            return;
        }
        final Component message = packet.message();
        if (message.equals(Component.empty())) {
            return;
        }

        final Component formatted = Component.text("\\<" + player.name() + "> ").append(message);

        final PlayerChatEvent event = server.events().post(new PlayerChatEvent(player, formatted));
        if (event.isCancelled()) {
            return;
        }

        LOGGER.debug(Component.text("<" + player.name() + ">").appendSpace().append(event.message()));
        server.broadcast(new ClientboundSystemChatPacket(event.message(), false));
    }

    @Override
    public void handleUseItemOn(final ServerboundUseItemOnPacket packet) {
        if (player.gameMode() == GameMode.SPECTATOR) {
            connection.send(new ClientboundBlockChangedAckPacket(packet.sequence()));
            return;
        }
        if (interactWithBlock(packet.target())) {
            connection.send(new ClientboundBlockChangedAckPacket(packet.sequence()));
            return;
        }
        final BlockFace clickedFace = BlockFace.byId(packet.face());
        final BlockPos target = packet.target().relative(clickedFace);
        final ItemStack held = player.inventory().get(player.selectedSlot());
        final BlockState state = held.isEmpty() ? null : blockToPlace(held, target, clickedFace, packet.cursorY());

        if (state != null) {
            final BlockPlaceEvent event = server.events()
                    .post(new BlockPlaceEvent(
                            player, target, server.blockStateRegistry().networkId(state)));
            if (!event.isCancelled()) {
                server.blockEdits().set(serverWorld(), target, state);
            }
        }
        connection.send(new ClientboundBlockChangedAckPacket(packet.sequence()));
    }

    private @Nullable BlockState blockToPlace(
            final ItemStack held, final BlockPos target, final BlockFace clickedFace, final float cursorY) {
        final BlockState state = server.blockStateRegistry().blockForItem(held.id());
        if (state == null) {
            return null;
        }
        final ServerWorld world = serverWorld();
        final BlockPlaceContext context = new BlockPlaceContext(
                target, clickedFace, player.location(), server.blockStateRegistry().view(world), cursorY);
        return server.blockStateRegistry().placementState(state, context);
    }

    private boolean interactWithBlock(final BlockPos pos) {
        final BlockState state;
        try {
            state = serverWorld().getBlock(pos.x(), pos.y(), pos.z());
        } catch (final IOException e) {
            LOGGER.debug("Lecture du bloc {} impossible", pos, e);
            return false;
        }
        if (!EnderChestBlock.is(state)) {
            return false;
        }
        openEnderChest(pos);
        return true;
    }

    private void openEnderChest(final BlockPos pos) {
        if (EnderChestBlock.isBlockedAbove(serverWorld(), pos)) {
            return;
        }

        final PlayerOpenEnderChestEvent event =
                server.events().post(new PlayerOpenEnderChestEvent(player, pos, player.enderChest()));
        if (event.isCancelled()) {
            return;
        }

        final EnderChestMenu menu = new EnderChestMenu(player, player.allocateWindowId(), pos);
        player.openMenu(menu);

        server.chestViewers().open(pos, this::broadcastLid);
        broadcastChestSound(pos, "block.ender_chest.open");
    }

    private void broadcastLid(final BlockPos pos, final int viewers) {
        server.broadcast(ClientboundBlockEventPacket.chestViewers(pos, viewers));
    }

    @SuppressWarnings("PatternValidation")
    private void broadcastChestSound(final BlockPos pos, final String soundId) {
        final Sound sound = Sound.sound(Key.key(soundId), Sound.Source.BLOCK, 0.5f, 1.0f);
        server.broadcast(new ClientboundSoundPacket(sound, pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5));
    }

    private void closeOpenMenu(final boolean notifyClient) {
        final ContainerMenu menu = player.openMenu();
        if (menu == null) {
            return;
        }
        player.closeMenu(notifyClient);

        if (menu instanceof final EnderChestMenu enderChest) {
            server.chestViewers().close(enderChest.position(), this::broadcastLid);
            broadcastChestSound(enderChest.position(), "block.ender_chest.close");
        }
        connection.send(player.inventoryMenu().buildSyncPacket(server.registries().network()));
    }

    private @Nullable ContainerMenu menuFor(final int windowId) {
        if (windowId == PlayerInventoryMenu.WINDOW_ID) {
            return player.inventoryMenu();
        }
        final ContainerMenu menu = player.openMenu();
        return menu != null && menu.windowId() == windowId ? menu : null;
    }

    @Override
    public void handleContainerClick(final ServerboundContainerClickPacket packet) {
        final ContainerMenu menu = menuFor(packet.windowId());
        if (menu == null) {
            connection.send(player.inventoryMenu().buildSyncPacket(server.registries().network()));
            return;
        }
        menu.click(packet);
        connection.send(menu.buildSyncPacket(server.registries().network()));
    }

    @Override
    public void handleContainerClose(final ServerboundContainerClosePacket packet) {
        if (packet.windowId() == PlayerInventoryMenu.WINDOW_ID) {
            final PlayerInventoryMenu inventoryMenu = player.inventoryMenu();
            inventoryMenu.returnCarried();
            inventoryMenu.onClosed();
            connection.send(inventoryMenu.buildSyncPacket(server.registries().network()));
            return;
        }
        closeOpenMenu(false);
    }

    @Override
    public void handleCustomClickAction(final ServerboundCustomClickActionPacket packet) {
        if (player == null) {
            return;
        }

        if (!ClickCallbackManager.KEY.equals(packet.id())) {
            final DialogResponse response = packet.payload() instanceof final CompoundBinaryTag values
                    ? new DialogResponse(values)
                    : DialogResponse.EMPTY;
            server.events().post(new PlayerDialogActionEvent(player, packet.id(), response));
            return;
        }

        if (!(packet.payload() instanceof final CompoundBinaryTag nbt)) {
            LOGGER.debug("{} sent a non-compound click callback payload for {}: {}",
                    player.name(), packet.id(), packet.payload());
            return;
        }

        final UUID uuid;
        try {
            uuid = ClickCallbackManager.uuidFromPayload(nbt);
        } catch (final IllegalArgumentException e) {
            LOGGER.debug("{} sent an invalid click callback payload for {}", player.name(), packet.id(), e);
            return;
        }
        server.clickCallbacksManager().handleClick(player, packet.id(), uuid);
    }

    @Override
    public void handlePlayerAction(final ServerboundPlayerActionPacket packet) {
        final int status = packet.status();
        final boolean breaking =
                switch (player.gameMode()) {
                    case CREATIVE -> status == ServerboundPlayerActionPacket.START_DESTROY_BLOCK;
                    case SURVIVAL ->
                            status == ServerboundPlayerActionPacket.START_DESTROY_BLOCK && instantMine(packet.position())
                                    || status == ServerboundPlayerActionPacket.FINISH_DESTROY_BLOCK;
                    case ADVENTURE, SPECTATOR -> false;
                };
        if (breaking) {
            final BlockBreakEvent event = server.events().post(new BlockBreakEvent(player, packet.position()));
            if (!event.isCancelled()) {
                onBlockDestroyed(packet.position());
                server.blockEdits().set(serverWorld(), packet.position(), BlockState.of(BlockTypeKeys.AIR.key()));
            }
        }
        connection.send(new ClientboundBlockChangedAckPacket(packet.sequence()));
    }

    private void onBlockDestroyed(final BlockPos position) {
        final ContainerMenu menu = player.openMenu();
        if (menu instanceof final EnderChestMenu enderChest && enderChest.position().equals(position)) {
            closeOpenMenu(true);
        }
        server.chestViewers().forget(position);
    }

    @Override
    public void handleCommandSuggestion(final ServerboundCommandSuggestionPacket packet) {
        String input = packet.text();
        final boolean slash = input.startsWith("/");

        if (slash) {
            input = input.substring(1);
        }

        final int offset = slash ? 1 : 0;

        server.commandManager().offerSuggestions(player, input).thenAccept(suggestions -> {
            final var entries = suggestions.getList().stream()
                    .map(suggestion -> new ClientboundCommandSuggestionsPacket.Entry(
                            suggestion.getText(), suggestion.getTooltip()))
                    .toList();

            connection.send(new ClientboundCommandSuggestionsPacket(
                    packet.id(),
                    suggestions.getRange().getStart() + offset,
                    suggestions.getRange().getLength(),
                    entries));
        });
    }

    @Override
    public void handlePlayerAbilities(final ServerboundPlayerAbilitiesPacket packet) {
        final ServerPlayer player = connection.player();

        player.setFlying(packet.isFlying());
    }

    private boolean instantMine(final BlockPos position) {
        return false;
    }

    @Override
    public void handleMovePlayerPos(final ServerboundMovePlayerPosPacket packet) {
        final Location old = player.location();
        onMoved(packet.x(), packet.y(), packet.z(), old.yaw(), old.pitch());
    }

    @Override
    public void handleMovePlayerPosRot(final ServerboundMovePlayerPosRotPacket packet) {
        onMoved(packet.x(), packet.y(), packet.z(), packet.yaw(), packet.pitch());
    }

    private void onMoved(final double x, final double y, final double z, final float yaw, final float pitch) {
        final Location previous = player.location();
        final Location current = new Location(x, y, z, yaw, pitch);
        trackFall(previous, current);
        player.setLocation(current);
        serverWorld().entityManager().moved(player, previous.chunk(), current.chunk());

        player.sendToTrackers(new ClientboundEntityPositionSyncPacket(
                player.entityId(), x, y, z, 0, 0, 0, yaw, pitch, false));
        player.sendToTrackers(new ClientboundRotateHeadPacket(player.entityId(), yaw));
        server.entityTracker().update(player, server.players());

        final ChunkPos chunk = current.chunk();
        if (!chunkView.moveTo(chunk.x(), chunk.z())) {
            return;
        }
        server.regionizer().moveTicket(worldId(), ticket, chunk);
        ticket = chunk;
    }

    public boolean teleport(final ServerWorld target, final Location location) {
        if (player == null) {
            return false;
        }
        final ServerWorld from = (ServerWorld) player.world();
        final ChunkPos destChunk = location.chunk();

        if (from == target) {
            final Location previous = player.location();
            player.setLocation(location);
            from.entityManager().moved(player, previous.chunk(), destChunk);
            connection.send(new ClientboundPlayerPositionPacket(
                    player.nextTeleportId(), location.x(), location.y(), location.z()));

            player.sendToTrackers(new ClientboundEntityPositionSyncPacket(
                    player.entityId(), location.x(), location.y(), location.z(),
                    0, 0, 0, location.yaw(), location.pitch(), false));
            player.sendToTrackers(new ClientboundRotateHeadPacket(player.entityId(), location.yaw()));

            if (chunkView != null && chunkView.moveTo(destChunk.x(), destChunk.z()) && ticket != null) {
                server.regionizer().moveTicket(from.dimension().id(), ticket, destChunk);
                ticket = destChunk;
            }
            server.entityTracker().update(player, server.players());
            return true;
        }
        return teleportCrossWorld(from, target, location, destChunk);
    }

    private boolean teleportCrossWorld(
            final ServerWorld from, final ServerWorld target, final Location location, final ChunkPos destChunk) {
        if (player == null) {
            throw new RuntimeException("Attempt to teleport a player who does not exist!");
        }
        if (chunkView != null) {
            chunkView.close();
            from.removeViewer(chunkView);
            chunkView = null;
        }
        if (ticket != null) {
            server.regionizer().removeTicket(from.dimension().id(), ticket);
            ticket = null;
        }
        from.removeEntity(player);
        server.entityTracker().untrack(player);

        player.setWorld(target);
        player.setLocation(location);
        target.addEntity(player);

        final RegistryHolder dynamic = server.dynamicRegistries();
        final int dimensionType = server.dimensionTypes().networkId(target.generator.dimensionType().key());
        connection.send(new ClientboundRespawnPacket(
                target.dimension().id(),
                dimensionType,
                player.gameMode().id(),
                ClientboundRespawnPacket.KEEP_ALL,
                describeGenerator(target.dimension().id()) instanceof ChunkGeneratorConfig.Debug,
                describeGenerator(target.dimension().id()) instanceof ChunkGeneratorConfig.Flat));
        connection.send(ClientboundPlayerAbilitiesPacket.forGameMode(player.gameMode()));
        connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.START_WAITING_FOR_CHUNKS, 0f));
        openChunkView(target, dynamic, destChunk);
        connection.send(new ClientboundPlayerPositionPacket(
                player.nextTeleportId(), location.x(), location.y(), location.z()));
        server.dayNightEngine().syncTo(target, connection::send);
        server.entityTracker().update(player, server.players());
        return true;
    }

    @Override
    public void handleAttack(final ServerboundAttackPacket packet) {
        if (player == null || player.isDead()) {
            return;
        }
        final AbstractEntity target = serverWorld().entityManager().byId(packet.entityId());
        if (target == null) {
            LOGGER.debug("{} is attacking the entity {} which does not exist or no longer exists.", player.name(), packet.entityId());
            return;
        }
        server.combat().attack(player, target);
    }

    @Override
    public void handleInteract(final ServerboundInteractPacket packet) {
        if (player == null || player.isDead()) {
            return;
        }
        final AbstractEntity target = serverWorld().entityManager().byId(packet.entityId());
        if (!(target instanceof final AbstractMob mob) || mob.isRemoved()) {
            LOGGER.debug("{} interacts with the entity {} which does not exist or no longer exists.", player.name(), packet.entityId());
            return;
        }
        mob.onInteract(player, packet.isOffHand() ? EquipmentSlotGroup.OFF_HAND : EquipmentSlotGroup.MAIN_HAND);
    }

    @Override
    public void handleSwing(final ServerboundSwingPacket packet) {
        if (player == null) {
            return;
        }
        player.resetAttackCooldown();
        player.sendToTrackers(ClientboundAnimatePacket.swing(
                player.entityId(), packet.hand() == ServerboundInteractPacket.HAND_OFF));
    }

    @Override
    public void handlePlayerInput(final ServerboundPlayerInputPacket packet) {
        if (player == null) {
            return;
        }
        player.setSprinting(packet.sprinting());
        player.setSneaking(packet.sneaking());
    }

    @Override
    public void handleClientCommand(final ServerboundClientCommandPacket packet) {
        LOGGER.debug("{} sent client_command action={}", player == null ? "?" : player.name(), packet.action());
        if (packet.action() == ServerboundClientCommandPacket.PERFORM_RESPAWN) {
            respawn(PlayerRespawnEvent.Cause.DEATH_SCREEN);
        }
    }

    @Override
    public void handleResourcePackResponse(final ServerboundResourcePackPacket packet) {
        connection.notifyResourcePackResponse(packet.id(), packet.status());
    }

    public boolean respawn(final PlayerRespawnEvent.Cause cause) {
        if (player == null) {
            LOGGER.debug("Respawn requested without a player");
            return false;
        }
        if (player.isRemoved() || (!player.isDead() && !player.isAwaitingRespawn())) {
            LOGGER.debug("{} requested a respawn while alive (health={})", player.name(), player.health());
            return false;
        }
        final ServerWorld defaultWorld = server.worldManager().overworld(); // FIXME: dont hardcode
        final Location defaultSpawn =
                new Location(config.spawnX(), config.spawnY(), config.spawnZ(), 0f, 0f);

        ServerWorld requestedWorld = defaultWorld;
        Location requestedSpawn = defaultSpawn;
        boolean usedRespawnPoint = false;

        final RespawnPoint point = player.respawnPoint();
        if (point != null) {
            final ServerWorld target = server.worldManager().world(point.world().key());
            if (target != null) {
                requestedWorld = target;
                requestedSpawn = point.location();
                usedRespawnPoint = true;
            } else {
                LOGGER.warn(
                        "Respawn point of {} targets the unloaded world {}, world spawn used instead",
                        player.name(),
                        point.world().key());
                player.setRespawnPoint((RespawnPoint) null);
            }
        }

        final PlayerRespawnEvent event = server.events()
                .post(new PlayerRespawnEvent(player, requestedWorld, requestedSpawn, cause, usedRespawnPoint));
        final ServerWorld world =
                event.world() instanceof final ServerWorld target ? target : defaultWorld;
        final Location spawn = event.location();

        player.resetOnRespawn();

        final int dimensionType = server.dimensionTypes().networkId(world.generator.dimensionType().key());
        connection.send(new ClientboundRespawnPacket(
                world.dimension().id(),
                dimensionType,
                player.gameMode().id(),
                ClientboundRespawnPacket.KEEP_NOTHING,
                describeGenerator(world.dimension().id()) instanceof ChunkGeneratorConfig.Debug,
                describeGenerator(world.dimension().id()) instanceof ChunkGeneratorConfig.Flat));
        connection.send(ClientboundPlayerAbilitiesPacket.forGameMode(player.gameMode()));
        connection.send(new ClientboundSetHealthPacket(player.health(), 20, 5.0f));
        connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.START_WAITING_FOR_CHUNKS, 0f));

        moveToRespawnPoint(world, spawn);

        connection.send(new ClientboundPlayerPositionPacket(
                player.nextTeleportId(), spawn.x(), spawn.y(), spawn.z()));
        server.dayNightEngine().syncTo(world, connection::send);
        server.entityTracker().update(player, server.players());
        LOGGER.debug("{} respawned at {}", player.name(), spawn);
        return true;
    }


    private void moveToRespawnPoint(final ServerWorld world, final Location spawn) {
        final ServerWorld from = (ServerWorld) player.world();
        final ChunkPos destination = spawn.chunk();

        if (from == world) {
            final Location previous = player.location();
            player.setLocation(spawn);
            from.entityManager().moved(player, previous.chunk(), destination);
            if (chunkView != null) {
                chunkView.resend(destination);
            }
            if (ticket != null && !ticket.equals(destination)) {
                server.regionizer().moveTicket(from.dimension().id(), ticket, destination);
                ticket = destination;
            }
            return;
        }

        if (chunkView != null) {
            chunkView.close();
            from.removeViewer(chunkView);
            chunkView = null;
        }
        if (ticket != null) {
            server.regionizer().removeTicket(from.dimension().id(), ticket);
            ticket = null;
        }
        from.removeEntity(player);
        server.entityTracker().untrack(player);
        player.setWorld(world);
        player.setLocation(spawn);
        world.addEntity(player);
        openChunkView(world, server.dynamicRegistries(), destination);
    }

    private void trackFall(final Location previous, final Location current) {
        if (player.gameMode() == GameMode.CREATIVE || player.gameMode() == GameMode.SPECTATOR) {
            player.setFallDistance(0.0);
            player.setFalling(false);
            return;
        }
        final double dy = current.y() - previous.y();
        if (dy < 0.0) {
            player.setFallDistance(player.fallDistance() - dy);
            player.setFalling(true);
            return;
        }

        if (player.isFalling()) {
            player.landAfterFall();
        }
    }

    private Key worldId() {
        return player != null ? player.world().key() : server.worldManager().overworld().dimension().id();
    }

    private ServerWorld serverWorld() {
        return player != null ? server.worldManager().world(player.world().key()) : server.worldManager().overworld();
    }

    private WorldManager worldManager() {
        return server.worldManager();
    }

    private @Nullable ChunkGeneratorConfig describeGenerator(final Key dimensionKey) {
        final ServerWorld world = worldManager().world(dimensionKey);
        return world == null ? null : world.generator.describeForSave();
    }
}
