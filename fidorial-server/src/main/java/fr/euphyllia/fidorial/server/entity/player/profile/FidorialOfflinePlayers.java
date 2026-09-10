package fr.euphyllia.fidorial.server.entity.player.profile;

import fr.euphyllia.fidorial.auth.GameProfile;
import fr.euphyllia.fidorial.auth.MojangProfileRepository;
import fr.euphyllia.fidorial.server.FidorialServer;
import fr.fidorial.entity.OfflinePlayer;
import fr.fidorial.entity.OfflinePlayerSnapshot;
import fr.fidorial.entity.OfflinePlayers;
import fr.fidorial.entity.Player;
import fr.fidorial.entity.PlayerProfile;
import fr.fidorial.inventory.EnderChestInventory;
import fr.fidorial.inventory.PlayerInventory;
import fr.fidorial.storage.player.PlayerDataStorage;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public final class FidorialOfflinePlayers implements OfflinePlayers, Closeable {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(FidorialOfflinePlayers.class);
    private static final Pattern VALID_NAME = Pattern.compile("[A-Za-z0-9_]{1,16}");

    private static final long NEGATIVE_TTL_MILLIS = Duration.ofMinutes(5).toMillis();

    private final FidorialServer server;
    private final ProfileCache cache;
    private final MojangProfileRepository profiles;
    private final boolean onlineMode;
    private final ExecutorService io;

    private final Map<String, CompletableFuture<Optional<OfflinePlayer>>> inFlight = new ConcurrentHashMap<>();
    private final Map<String, Long> negative = new ConcurrentHashMap<>();

    /**
     * Creates a directory backed by the given cache file.
     *
     * @param server     the owning server
     * @param cacheFile  the profile journal file
     * @param ttl        how long an identity is retained after it was last observed
     * @param maxEntries the maximum number of identities to retain
     * @param onlineMode whether the server authenticates logins, which determines if identities are
     *                   resolved remotely or derived from the name
     */
    public FidorialOfflinePlayers(
            final FidorialServer server,
            final Path cacheFile,
            final Duration ttl,
            final int maxEntries,
            final boolean onlineMode
    ) {
        this.server = server;
        this.cache = new ProfileCache(cacheFile, ttl, maxEntries);
        this.profiles = new MojangProfileRepository();
        this.onlineMode = onlineMode;
        this.io = Executors.newThreadPerTaskExecutor(
                Thread.ofVirtual().name("fidorial-profiles-", 0).factory());
    }

    /**
     * Derives the identity an unauthenticated server assigns to a name.
     *
     * @param name the player name
     * @return the derived identity
     */
    public static UUID offlineUuid(final String name) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Loads the profile cache. Called once during startup.
     *
     * @throws IOException if the cache cannot be read
     */
    public void load() throws IOException {
        cache.load();
    }

    /**
     * Records an identity in the profile cache.
     *
     * @param profile the authenticated profile
     */
    public void remember(final PlayerProfile profile) {
        cache.remember(profile.uuid(), profile.name());
    }

    /**
     * Discards expired entries and compacts the profile journal. Intended to run on the automatic
     * save cycle.
     */
    public void maintain() {
        cache.maintain();
        final long now = System.currentTimeMillis();
        negative.values().removeIf(expiry -> expiry < now);
    }

    /**
     * Gets the underlying profile cache.
     *
     * @return the profile cache
     */
    public ProfileCache cache() {
        return cache;
    }

    /**
     * Gets the executor on which storage reads are performed.
     *
     * @return the storage executor
     */
    public Executor ioExecutor() {
        return io;
    }

    @Override
    public OfflinePlayer of(final UUID uuid) {
        return cache.byId(uuid).map(this::toHandle).orElseGet(() -> OfflinePlayer.of(this, uuid, null, 0L, 0L));
    }

    @Override
    public OfflinePlayer of(final Player player) {
        return cache.byId(player.uuid())
                .map(this::toHandle)
                .orElseGet(() -> OfflinePlayer.of(this, player.uuid(), player.name(), 0L, System.currentTimeMillis()));
    }

    @Override
    public Optional<OfflinePlayer> cached(final String name) {
        final Optional<? extends Player> live = onlineByName(name);
        return live.map(this::of).or(() -> cache.byName(name).map(this::toHandle));
    }

    @Override
    public Optional<OfflinePlayer> cached(final UUID uuid) {
        return cache.byId(uuid).map(this::toHandle);
    }

    @Override
    public CompletableFuture<Optional<OfflinePlayer>> lookup(final String name) {
        if (!VALID_NAME.matcher(name).matches()) {
            return CompletableFuture.completedFuture(Optional.empty());
        }

        final Optional<OfflinePlayer> hit = cached(name);
        if (hit.isPresent()) {
            return CompletableFuture.completedFuture(hit);
        }

        final String key = name.toLowerCase(Locale.ROOT);

        final Long until = negative.get(key);
        if (until != null && until > System.currentTimeMillis()) {
            return CompletableFuture.completedFuture(Optional.empty());
        }

        if (!onlineMode) {
            final UUID uuid = offlineUuid(name);
            return CompletableFuture.completedFuture(Optional.of(toHandle(cache.remember(uuid, name))));
        }

        return inFlight.computeIfAbsent(key, k -> profiles.byName(name)
                .thenApply(profile -> profile
                        .map(found -> toHandle(cache.remember(found.uuid(), found.name()))))
                .whenComplete((result, error) -> {
                    inFlight.remove(k);
                    if (error == null && result.isEmpty()) {
                        negative.put(k, System.currentTimeMillis() + NEGATIVE_TTL_MILLIS);
                    }
                }));
    }

    @Override
    public CompletableFuture<Map<String, OfflinePlayer>> lookup(final Collection<String> names) {
        final Map<String, OfflinePlayer> resolved = new HashMap<>();
        final List<String> missing = new ArrayList<>();

        for (final String name : names) {
            if (!VALID_NAME.matcher(name).matches()) {
                continue;
            }
            final Optional<OfflinePlayer> hit = cached(name);
            if (hit.isPresent()) {
                resolved.put(name, hit.get());
            } else if (onlineMode) {
                missing.add(name);
            } else {
                resolved.put(name, toHandle(cache.remember(offlineUuid(name), name)));
            }
        }

        if (missing.isEmpty()) {
            return CompletableFuture.completedFuture(Map.copyOf(resolved));
        }

        return profiles.byNames(missing).thenApply(found -> {
            for (final String name : missing) {
                final GameProfile profile = found.get(name.toLowerCase(Locale.ROOT));
                if (profile == null) {
                    negative.put(name.toLowerCase(Locale.ROOT),
                            System.currentTimeMillis() + NEGATIVE_TTL_MILLIS);
                    continue;
                }
                resolved.put(name, toHandle(cache.remember(profile.uuid(), profile.name())));
            }
            return Map.copyOf(resolved);
        });
    }

    @Override
    public CompletableFuture<OfflinePlayer> refresh(final UUID uuid) {
        final Optional<? extends Player> live = server.player(uuid);
        if (live.isPresent()) {
            return CompletableFuture.completedFuture(of(live.get()));
        }

        if (!onlineMode) {
            return CompletableFuture.completedFuture(of(uuid));
        }

        return profiles.byId(uuid)
                .thenApply(profile -> profile
                        .map(found -> toHandle(cache.remember(found.uuid(), found.name())))
                        .orElseGet(() -> of(uuid)));
    }

    @Override
    public Collection<OfflinePlayer> known() {
        return cache.entries().stream().map(this::toHandle).toList();
    }

    @Override
    public Optional<? extends Player> online(final UUID uuid) {
        return server.player(uuid);
    }

    @Override
    public boolean isOperator(final UUID uuid) {
        return server.operators().isOp(uuid);
    }

    @Override
    public void setOperator(final UUID uuid, final boolean operator) {
        final String name = cache.byId(uuid).map(ProfileEntry::name).orElseGet(uuid::toString);
        server.operators().setOp(uuid, name, operator);
        server.player(uuid).ifPresent(Player::invalidatePermissions);
    }

    @Override
    public boolean isBanned(final UUID uuid) {
        return server.ban().isBanned(uuid);
    }

    @Override
    public boolean isWhitelisted(final UUID uuid) {
        return server.whitelist().contains(uuid);
    }

    @Override
    public CompletableFuture<Optional<OfflinePlayerSnapshot>> snapshot(final UUID uuid) {
        final Optional<? extends Player> live = server.player(uuid);
        if (live.isPresent()) {
            final Player player = live.get();
            return CompletableFuture.completedFuture(Optional.of(new OfflinePlayerSnapshot(
                    uuid,
                    player.gameMode(),
                    copyOf(player.inventory()),
                    copyOf(player.enderChest()),
                    true)));
        }

        return CompletableFuture.supplyAsync(() -> {
            try {
                final PlayerDataStorage data = server.playerDataStorage();
                if (!data.exists(uuid)) {
                    return Optional.empty();
                }
                final PlayerDataStorage.PlayerData loaded =
                        data.load(uuid, new PlayerDataStorage.PlayerData(server.config().defaultGameMode(), null, null, null, null));
                return Optional.of(new OfflinePlayerSnapshot(
                        uuid,
                        loaded.gameMode(),
                        server.playerInventoryStorage().load(uuid),
                        server.playerEnderChestStorage().load(uuid),
                        false));
            } catch (final IOException e) {
                LOGGER.error("Unable to read the saved data of {}", uuid, e);
                return Optional.empty();
            }
        }, io);
    }

    /**
     * Shuts down the storage executor and closes the profile cache.
     *
     * @throws IOException if the cache cannot be flushed
     */
    @Override
    public void close() throws IOException {
        io.shutdown();
        cache.close();
    }

    private OfflinePlayer toHandle(final ProfileEntry entry) {
        return OfflinePlayer.of(this, entry.uuid(), entry.name(), entry.firstSeen(), entry.lastSeen());
    }

    private Optional<? extends Player> onlineByName(final String name) {
        return server.player(name);
    }

    private static PlayerInventory copyOf(final PlayerInventory source) {
        final PlayerInventory copy = new PlayerInventory();
        for (int slot = 0; slot < PlayerInventory.SIZE; slot++) {
            copy.set(slot, source.get(slot));
        }
        return copy;
    }

    private static EnderChestInventory copyOf(final EnderChestInventory source) {
        final EnderChestInventory copy = new EnderChestInventory();
        for (int slot = 0; slot < EnderChestInventory.SIZE; slot++) {
            copy.set(slot, source.get(slot));
        }
        return copy;
    }
}
