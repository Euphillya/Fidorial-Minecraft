package fr.euphyllia.fidorial.server.moderation;

import com.google.common.net.InetAddresses;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import fr.fidorial.moderation.BanEntry;
import fr.fidorial.moderation.BanManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public final class FidorialBanManager implements BanManager {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(FidorialBanManager.class);

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Path profileFile;
    private final Path addressFile;
    private final Map<UUID, BanEntry.Profile> profiles = new ConcurrentHashMap<>();
    private final Map<InetAddress, BanEntry.Address> addresses = new ConcurrentHashMap<>();

    public FidorialBanManager(final Path profileFile, final Path addressFile) {
        this.profileFile = Objects.requireNonNull(profileFile, "profileFile");
        this.addressFile = Objects.requireNonNull(addressFile, "addressFile");
    }

    public void load() {
        profiles.clear();
        addresses.clear();

        read(profileFile, FidorialBanManager::readProfile, entry -> profiles.put(entry.uuid(), entry));
        read(addressFile, FidorialBanManager::readAddress, entry -> addresses.put(entry.address(), entry));

        LOGGER.debug("There are currently {} active ban(s)", profiles.size() + addresses.size());
    }

    public synchronized void save() {
        write(profileFile, profiles.values(), FidorialBanManager::writeProfile);
        write(addressFile, addresses.values(), FidorialBanManager::writeAddress);
    }

    public int purgeExpired() {
        final int removed = purgeExpired(profiles) + purgeExpired(addresses);

        if (removed > 0) {
            save();
        }

        return removed;
    }

    @Override
    public Optional<BanEntry.Profile> find(final UUID uuid) {
        return lookup(profiles, uuid);
    }

    @Override
    public Optional<BanEntry.Address> find(final InetAddress address) {
        return lookup(addresses, address);
    }

    @Override
    public Optional<BanEntry> findByName(final String name) {
        return active()
                .filter(entry -> entry.name() != null && entry.name().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public boolean ban(final BanEntry entry) {
        Objects.requireNonNull(entry, "entry");

        final boolean isNew = switch (entry) {
            case final BanEntry.Profile profile -> store(profiles, profile.uuid(), profile);
            case final BanEntry.Address address -> store(addresses, address.address(), address);
        };

        save();

        return isNew;
    }

    @Override
    public boolean pardon(final UUID uuid) {
        return lift(profiles, Objects.requireNonNull(uuid, "uuid"));
    }

    @Override
    public boolean pardon(final InetAddress address) {
        return lift(addresses, Objects.requireNonNull(address, "address"));
    }

    @Override
    public Stream<BanEntry> bans() {
        return recent(Stream.concat(profiles.values().stream(), addresses.values().stream()));
    }

    @Override
    public Stream<BanEntry.Profile> profileBans() {
        return recent(profiles.values().stream());
    }

    @Override
    public Stream<BanEntry.Address> ipBans() {
        return recent(addresses.values().stream());
    }

    @Override
    public Component disconnectMessage(final BanEntry entry) {
        Objects.requireNonNull(entry, "entry");

        final String key = switch (entry) {
            case BanEntry.Profile _ -> "multiplayer.disconnect.banned";
            case BanEntry.Address _ -> "multiplayer.disconnect.banned_ip";
        };

        final Component reason = entry.reason() == null
                ? Component.translatable(key)
                : Component.translatable(key + ".reason", entry.reason());

        return entry.permanent()
                ? reason
                : reason.appendNewline()
                  .append(Component.translatable(
                          key + ".expiration",
                          Component.text(entry.expiresLabel())));
    }

    @Override
    public int totalBans() {
        return (int) active().count();
    }

    private static <E extends BanEntry> Stream<E> recent(final Stream<E> entries) {
        return entries.filter(entry -> !entry.expired())
                .sorted(Comparator.comparing(BanEntry::created).reversed());
    }

    private Stream<BanEntry> active() {
        return Stream.<BanEntry>concat(profiles.values().stream(), addresses.values().stream())
                .filter(entry -> !entry.expired());
    }

    private <K, E extends BanEntry> Optional<E> lookup(final Map<K, E> bans, final K key) {
        final E entry = bans.get(key);

        if (entry == null) {
            return Optional.empty();
        }

        if (entry.expired()) {
            if (bans.remove(key, entry)) {
                save();
            }
            return Optional.empty();
        }

        return Optional.of(entry);
    }

    private <K, E extends BanEntry> boolean store(final Map<K, E> bans, final K key, final E entry) {
        final E previous = bans.put(key, entry);

        return previous == null || previous.expired();
    }

    private <K, E extends BanEntry> boolean lift(final Map<K, E> bans, final K key) {
        if (bans.remove(key) == null) {
            return false;
        }

        save();

        return true;
    }

    private static <K, E extends BanEntry> int purgeExpired(final Map<K, E> bans) {
        final Iterator<Map.Entry<K, E>> iterator = bans.entrySet().iterator();
        int removed = 0;

        while (iterator.hasNext()) {
            if (iterator.next().getValue().expired()) {
                iterator.remove();
                removed++;
            }
        }

        return removed;
    }

    private static <E extends BanEntry> void read(
            final Path file,
            final Function<RawEntry, E> reader,
            final Consumer<E> sink
    ) {
        try {
            if (!Files.exists(file) || Files.size(file) == 0) {
                return;
            }
        } catch (final IOException e) {
            LOGGER.error("Unable to read {}", file, e);
            return;
        }

        try (final JsonReader in = new JsonReader(Files.newBufferedReader(file, StandardCharsets.UTF_8))) {
            in.beginArray();

            while (in.hasNext()) {
                final RawEntry raw = readEntry(in);
                try {
                    final E entry = reader.apply(raw);

                    if (!entry.expired()) {
                        sink.accept(entry);
                    }
                } catch (final RuntimeException e) {
                    LOGGER.warn("Skipping malformed entry in {}", file, e);
                }
            }
            in.endArray();
        } catch (final IOException | RuntimeException e) {
            LOGGER.error("Unable to read {}", file, e);
        }
    }

    private static RawEntry readEntry(final JsonReader in) throws IOException {
        String uuid = null;
        String ip = null;
        String name = null;
        String source = null;
        String created = null;
        String expires = null;
        JsonElement reason = null;

        in.beginObject();

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "uuid" -> uuid = string(in);
                case "ip" -> ip = string(in);
                case "name" -> name = string(in);
                case "source" -> source = string(in);
                case "created" -> created = string(in);
                case "expires" -> expires = string(in);
                case "reason" -> reason = JsonParser.parseReader(in);
                default -> in.skipValue();
            }
        }

        in.endObject();

        return new RawEntry(uuid, ip, name, reason, source, created, expires);
    }

    private static <E extends BanEntry> void write(
            final Path file,
            final Collection<E> entries,
            final Function<E, JsonObject> writer
    ) {
        final JsonArray array = new JsonArray();
        entries.forEach(entry -> array.add(writer.apply(entry)));

        try (final Writer out = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            GSON.toJson(array, out);
        } catch (final IOException e) {
            LOGGER.error("Unable to save {}", file, e);
        }
    }

    private static BanEntry.Profile readProfile(final RawEntry raw) {
        return new BanEntry.Profile(
                UUID.fromString(Objects.requireNonNull(raw.uuid(), "uuid")),
                raw.name(),
                reason(raw.reason()),
                uuid(raw.source()),
                Instant.parse(Objects.requireNonNull(raw.created(), "created")),
                instant(raw.expires())
        );
    }

    private static BanEntry.Address readAddress(final RawEntry raw) {
        return new BanEntry.Address(
                InetAddresses.forString(Objects.requireNonNull(raw.ip(), "ip")),
                raw.name(),
                reason(raw.reason()),
                uuid(raw.source()),
                Instant.parse(Objects.requireNonNull(raw.created(), "created")),
                instant(raw.expires()));
    }

    private static JsonObject writeProfile(final BanEntry.Profile entry) {
        final JsonObject json = new JsonObject();

        json.addProperty("uuid", entry.uuid().toString());
        json.addProperty("name", entry.name());

        return common(json, entry);
    }

    private static JsonObject writeAddress(final BanEntry.Address entry) {
        final JsonObject json = new JsonObject();

        json.addProperty("ip", entry.address().getHostAddress());
        json.addProperty("name", entry.name());

        return common(json, entry);
    }

    private static JsonObject common(final JsonObject json, final BanEntry entry) {
        if (entry.reason() != null) {
            json.add("reason", GsonComponentSerializer.gson().serializeToTree(entry.reason()));
        }

        json.addProperty("source", entry.source() == null ? null : entry.source().toString());
        json.addProperty("created", entry.created().toString());

        if (entry.expires() != null) {
            json.addProperty("expires", entry.expires().toString());
        }

        return json;
    }

    private static @Nullable String string(final JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        return in.nextString();
    }

    private static @Nullable UUID uuid(@Nullable final String value) {
        return value == null ? null : UUID.fromString(value);
    }

    private static @Nullable Instant instant(@Nullable final String value) {
        return value == null ? null : Instant.parse(value);
    }

    private static @Nullable Component reason(@Nullable final JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        }

        return GsonComponentSerializer.gson().deserializeFromTree(element);
    }

    private record RawEntry(
            @Nullable String uuid,
            @Nullable String ip,
            @Nullable String name,
            @Nullable JsonElement reason,
            @Nullable String source,
            @Nullable String created,
            @Nullable String expires
    ) {
    }
}
