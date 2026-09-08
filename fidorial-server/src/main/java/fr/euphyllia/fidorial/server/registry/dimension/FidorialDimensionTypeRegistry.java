package fr.euphyllia.fidorial.server.registry.dimension;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.codecs.world.DimensionTypeCodecs;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.registry.RegistryEntry;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.fidorial.registry.Registry;
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.DimensionType;
import fr.fidorial.world.dimension.DimensionTypeDefinition;
import fr.fidorial.world.dimension.DimensionTypeRegistry;
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

public final class FidorialDimensionTypeRegistry implements DimensionTypeRegistry, Registry<DimensionType> {

    public static final Key REGISTRY_NAME = Key.key("dimension_type");

    private static final ComponentLogger LOGGER = ComponentLogger.logger(FidorialDimensionTypeRegistry.class);

    public final AtomicBoolean started = new AtomicBoolean(false);

    private volatile Snapshot snapshot;

    private FidorialDimensionTypeRegistry(final List<Key> vanilla) {
        final Map<Key, @Nullable DimensionTypeDefinition> initial = new LinkedHashMap<>();
        for (final Key key : vanilla) {
            initial.put(key, null);
        }
        this.snapshot = Snapshot.of(initial);
    }

    public static FidorialDimensionTypeRegistry bootstrap(final RegistryHolder dynamic) {
        final fr.euphyllia.fidorial.server.registry.Registry source = dynamic.get(REGISTRY_NAME);
        final List<Key> entries = source == null ? List.of() : source.entries();

        if (entries.isEmpty()) {
            LOGGER.warn("No vanilla dimension type found in the registry dump, starting empty.");
        }

        return new FidorialDimensionTypeRegistry(entries);
    }

    @Override
    public DimensionTypeDefinition register(final DimensionTypeDefinition definition) {
        Objects.requireNonNull(definition, "definition");
        synchronized (this) {
            if (snapshot.definitions.containsKey(definition.key()) || snapshot.ids.containsKey(definition.key())) {
                throw new IllegalStateException("A dimension type is already registered under "
                        + definition.key().asString() + "; use overwrite(DimensionTypeDefinition) to replace it.");
            }
            final Map<Key, @Nullable DimensionTypeDefinition> next = snapshot.mutableCopy();
            next.put(definition.key(), definition);
            publish(next, "registered", definition.key());
        }
        return definition;
    }

    @Override
    public DimensionTypeDefinition registerFromJson(final Key key, final String json) {
        return register(DimensionTypeCodecs.fromJson(key, json));
    }

    @Override
    public Optional<DimensionTypeDefinition> overwrite(final DimensionTypeDefinition definition) {
        Objects.requireNonNull(definition, "definition");
        synchronized (this) {
            final DimensionTypeDefinition previous = snapshot.definitions.get(definition.key());
            final Map<Key, @Nullable DimensionTypeDefinition> next = snapshot.mutableCopy();
            next.put(definition.key(), definition);
            publish(next, previous == null && !snapshot.ids.containsKey(definition.key())
                    ? "registered" : "redefined", definition.key());
            return Optional.ofNullable(previous);
        }
    }

    @Override
    public boolean unregister(final Key key) {
        Objects.requireNonNull(key, "key");
        synchronized (this) {
            if (!snapshot.ids.containsKey(key)) {
                return false;
            }
            final Map<Key, @Nullable DimensionTypeDefinition> next = snapshot.mutableCopy();
            next.remove(key);
            publish(next, "unregistered", key);
            return true;
        }
    }

    @Override
    public Optional<DimensionTypeDefinition> definition(final Key key) {
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
    public Collection<DimensionTypeDefinition> definitions() {
        return snapshot.definitions.values();
    }

    @Override
    public int networkId(final Key key) {
        final Integer id = snapshot.ids.get(key);
        return id == null ? -1 : id;
    }

    @Override
    public int totalRegistered() {
        return snapshot.order.size();
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
    public RegistryKey<DimensionType> registryKey() {
        return RegistryKey.DIMENSION_TYPE;
    }

    @Override
    public DimensionType get(final TypedKey<DimensionType> key) {
        return snapshot.values.get(key.key());
    }

    @Override
    public Optional<DimensionType> find(final TypedKey<DimensionType> key) {
        return Optional.ofNullable(snapshot.values.get(key.key()));
    }

    @Override
    public TypedKey<DimensionType> key(final DimensionType value) {
        return TypedKey.create(RegistryKey.DIMENSION_TYPE, value.key());
    }

    @Override
    public Collection<DimensionType> values() {
        return snapshot.valueList;
    }

    @Override
    public Stream<DimensionType> stream() {
        return snapshot.valueList.stream();
    }

    private void publish(final Map<Key, @Nullable DimensionTypeDefinition> next, final String action, final Key key) {
        this.snapshot = Snapshot.of(next);
        if (started.get()) {
            LOGGER.warn("Dimension type {} {} after startup: clients will be sent to the configuration phase to see the change.",
                    key.asString(), action);
            FidorialServer.getInstance().players().forEach(ServerPlayer::enterConfigurationPhase);
        } else {
            LOGGER.debug("Dimension type {} {}.", key.asString(), action);
        }
    }

    private record Snapshot(
            List<Key> order,
            Map<Key, Integer> ids,
            Map<Key, DimensionTypeDefinition> definitions,
            Map<Key, CompoundBinaryTag> payloads,
            Map<Key, DimensionType> values,
            List<DimensionType> valueList
    ) {

        static Snapshot of(final Map<Key, @Nullable DimensionTypeDefinition> source) {
            final List<Key> order = new ArrayList<>(source.size());
            final Map<Key, Integer> ids = new LinkedHashMap<>(source.size());
            final Map<Key, DimensionTypeDefinition> definitions = new LinkedHashMap<>();
            final Map<Key, CompoundBinaryTag> payloads = new LinkedHashMap<>();
            final Map<Key, DimensionType> values = new LinkedHashMap<>(source.size());
            final List<DimensionType> valueList = new ArrayList<>(source.size());

            int index = 0;
            for (final Map.Entry<Key, @Nullable DimensionTypeDefinition> entry : source.entrySet()) {
                final Key key = entry.getKey();
                final DimensionTypeDefinition definition = entry.getValue();
                final DimensionType value = definition != null ? definition : new VanillaDimensionType(key);

                order.add(key);
                ids.put(key, index++);
                if (definition != null) {
                    definitions.put(key, definition);
                    payloads.put(key, DimensionTypeCodecs.encodeNbt(definition));
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

        Map<Key, @Nullable DimensionTypeDefinition> mutableCopy() {
            final Map<Key, @Nullable DimensionTypeDefinition> copy = new LinkedHashMap<>();
            for (final Key key : order) {
                copy.put(key, definitions.get(key));
            }
            return copy;
        }
    }

    private record VanillaDimensionType(Key key) implements DimensionType {
    }
}
