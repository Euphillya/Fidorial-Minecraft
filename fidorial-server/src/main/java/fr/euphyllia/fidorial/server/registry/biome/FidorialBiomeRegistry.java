package fr.euphyllia.fidorial.server.registry.biome;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.codecs.world.BiomeCodecs;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.registry.RegistryEntry;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.fidorial.registry.Registry;
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.Biome;
import fr.fidorial.world.biome.BiomeDefinition;
import fr.fidorial.world.biome.BiomeRegistry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public final class FidorialBiomeRegistry implements BiomeRegistry, Registry<Biome> {

    public static final Key REGISTRY_NAME = Key.key("worldgen/biome");

    private static final ComponentLogger LOGGER = ComponentLogger.logger(FidorialBiomeRegistry.class);

    private final Key fallback;
    public final AtomicBoolean started = new AtomicBoolean(false);

    private volatile Snapshot snapshot;

    private FidorialBiomeRegistry(final Key fallback, final List<Key> vanilla) {
        this.fallback = fallback;

        final Map<Key, @Nullable BiomeDefinition> initial = new LinkedHashMap<>();
        for (final Key key : vanilla) {
            initial.put(key, null);
        }
        initial.putIfAbsent(fallback, null);
        this.snapshot = Snapshot.of(initial);
    }

    public static FidorialBiomeRegistry bootstrap(final RegistryHolder dynamic, final Key fallback) {
        final fr.euphyllia.fidorial.server.registry.Registry source = dynamic.get(REGISTRY_NAME);
        final List<Key> entries = source == null ? List.of() : source.entries();

        if (entries.isEmpty()) {
            LOGGER.warn("No vanilla biome found in the registry dump, starting with '{}' only.", fallback.asString());
        }

        return new FidorialBiomeRegistry(fallback, entries);
    }

    @Override
    public BiomeDefinition register(final BiomeDefinition definition) {
        Objects.requireNonNull(definition, "definition");
        synchronized (this) {
            if (snapshot.definitions.containsKey(definition.key()) || snapshot.ids.containsKey(definition.key())) {
                throw new IllegalStateException("A biome is already registered under " + definition.key().asString()
                        + "; use overwrite(BiomeDefinition) to replace it.");
            }
            final Map<Key, @Nullable BiomeDefinition> next = snapshot.mutableCopy();
            next.put(definition.key(), definition);
            publish(next, "registered", definition.key());
        }
        return definition;
    }

    @Override
    public BiomeDefinition registerFromJson(final Key key, final String json) {
        return register(BiomeCodecs.fromJson(key, json));
    }

    @Override
    public Optional<BiomeDefinition> overwrite(final BiomeDefinition definition) {
        Objects.requireNonNull(definition, "definition");
        synchronized (this) {
            final BiomeDefinition previous = snapshot.definitions.get(definition.key());
            final Map<Key, @Nullable BiomeDefinition> next = snapshot.mutableCopy();
            next.put(definition.key(), definition);
            publish(next, previous == null && !snapshot.ids.containsKey(definition.key())
                    ? "registered" : "redefined", definition.key());
            return Optional.ofNullable(previous);
        }
    }

    @Override
    public boolean unregister(final Key key) {
        Objects.requireNonNull(key, "key");
        if (key.equals(fallback)) {
            throw new IllegalArgumentException("The fallback biome " + fallback.asString() + " cannot be unregistered.");
        }
        synchronized (this) {
            if (!snapshot.ids.containsKey(key)) {
                return false;
            }
            final Map<Key, @Nullable BiomeDefinition> next = snapshot.mutableCopy();
            next.remove(key);
            publish(next, "unregistered", key);
            return true;
        }
    }

    @Override
    public Optional<BiomeDefinition> definition(final Key key) {
        return Optional.ofNullable(snapshot.definitions.get(key));
    }

    @Override
    public boolean contains(final Key key) {
        return snapshot.ids.containsKey(key);
    }

    @Override
    public boolean isCustom(final Key key) {
        return snapshot.definitions.containsKey(key);
    }

    @Override
    public Collection<Key> keys() {
        return snapshot.order;
    }

    @Override
    public Collection<BiomeDefinition> definitions() {
        return snapshot.definitions.values();
    }

    @Override
    public int networkId(final Key key) {
        final Integer id = snapshot.ids.get(key);
        return id == null ? -1 : id;
    }

    @Override
    public Key fallback() {
        return fallback;
    }

    @Override
    public int totalRegistered() {
        return snapshot.order.size();
    }

    public int networkIdOrFallback(final Key key) {
        final Snapshot current = snapshot;
        final Integer id = current.ids.get(key);
        if (id != null) {
            return id;
        }
        final Integer fallbackId = current.ids.get(fallback);
        return fallbackId == null ? 0 : fallbackId;
    }

    public List<RegistryEntry> networkEntries() {
        final Snapshot current = snapshot;
        final List<RegistryEntry> entries = new ArrayList<>(current.order.size());

        for (final Key key : current.order) {
            entries.add(new RegistryEntry(key, current.payloads.get(key)));
        }

        return entries;
    }


    @Override
    public RegistryKey<Biome> registryKey() {
        return RegistryKey.BIOME;
    }

    @Override
    public Biome get(final TypedKey<Biome> key) {
        return snapshot.values.get(key.key());
    }

    @Override
    public Optional<Biome> find(final TypedKey<Biome> key) {
        return Optional.ofNullable(snapshot.values.get(key.key()));
    }

    @Override
    public TypedKey<Biome> key(final Biome value) {
        return TypedKey.create(RegistryKey.BIOME, value.key());
    }

    @Override
    public Collection<Biome> values() {
        return snapshot.valueList;
    }

    @Override
    public Stream<Biome> stream() {
        return snapshot.valueList.stream();
    }

    private void publish(final Map<Key, @Nullable BiomeDefinition> next, final String action, final Key key) {
        this.snapshot = Snapshot.of(next);
        if (started.get()) {
            LOGGER.warn("Biome {} {} after startup: clients will be sent to the configuration phase to see the change.",
                    key.asString(), action);
            FidorialServer.getInstance().players().forEach(ServerPlayer::enterConfigurationPhase);
        } else {
            LOGGER.debug("Biome {} {}.", key.asString(), action);
        }
    }

    private record Snapshot(
            List<Key> order,
            Map<Key, Integer> ids,
            Map<Key, BiomeDefinition> definitions,
            Map<Key, CompoundBinaryTag> payloads,
            Map<Key, Biome> values,
            List<Biome> valueList
    ) {

        static Snapshot of(final Map<Key, @Nullable BiomeDefinition> source) {
            final List<Key> order = new ArrayList<>(source.size());
            final Map<Key, Integer> ids = new LinkedHashMap<>(source.size());
            final Map<Key, BiomeDefinition> definitions = new LinkedHashMap<>();
            final Map<Key, CompoundBinaryTag> payloads = new LinkedHashMap<>();
            final Map<Key, Biome> values = new LinkedHashMap<>(source.size());
            final List<Biome> valueList = new ArrayList<>(source.size());

            int index = 0;
            for (final Map.Entry<Key, @Nullable BiomeDefinition> entry : source.entrySet()) {
                final Key key = entry.getKey();
                final BiomeDefinition definition = entry.getValue();
                final Biome value = definition != null ? definition : new VanillaBiome(key);

                order.add(key);
                ids.put(key, index++);
                if (definition != null) {
                    definitions.put(key, definition);
                    payloads.put(key, BiomeCodecs.encodeNbt(definition));
                }
                values.put(key, value);
                valueList.add(value);
            }

            return new Snapshot(
                    List.copyOf(order),
                    Map.copyOf(ids),
                    java.util.Collections.unmodifiableMap(definitions),
                    Map.copyOf(payloads),
                    Map.copyOf(values),
                    List.copyOf(valueList));
        }

        Map<Key, @Nullable BiomeDefinition> mutableCopy() {
            final Map<Key, @Nullable BiomeDefinition> copy = new LinkedHashMap<>();
            for (final Key key : order) {
                copy.put(key, definitions.get(key));
            }
            return copy;
        }
    }

    private record VanillaBiome(Key key) implements Biome {
    }
}
