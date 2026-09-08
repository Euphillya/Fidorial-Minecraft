package fr.euphyllia.fidorial.server.registry;

import net.kyori.adventure.key.Key;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RegistryHolder {

    private static final RegistryHolder EMPTY = new RegistryHolder(Map.of());

    private final Map<Key, Registry> registries;

    private RegistryHolder(final Map<Key, Registry> registries) {
        this.registries = registries;
    }

    public static RegistryHolder of(final Map<Key, Registry> registries) {
        return new RegistryHolder(Collections.unmodifiableMap(new LinkedHashMap<>(registries)));
    }

    public static RegistryHolder merged(final RegistryHolder... holders) {
        final Map<Key, Registry> merged = new LinkedHashMap<>();
        for (final RegistryHolder holder : holders) {
            merged.putAll(holder.registries);
        }
        return new RegistryHolder(Collections.unmodifiableMap(merged));
    }

    public static RegistryHolder empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return registries.isEmpty();
    }

    public int size() {
        return registries.size();
    }

    public Collection<Registry> all() {
        return registries.values();
    }

    public @Nullable Registry get(final Key name) {
        return registries.get(name);
    }

    public int networkId(final Key registry, final Key entry) {
        final Registry reg = registries.get(registry);
        return reg == null ? -1 : reg.networkId(entry);
    }

    public int tagCount() {
        return registries.values().stream().mapToInt(r -> r.tags().size()).sum();
    }
}
