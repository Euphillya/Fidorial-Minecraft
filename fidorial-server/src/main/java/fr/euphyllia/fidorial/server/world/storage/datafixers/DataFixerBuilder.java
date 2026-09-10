package fr.euphyllia.fidorial.server.world.storage.datafixers;

import ca.spottedleaf.converter.DataConverter;
import ca.spottedleaf.converter.datatypes.DataWalker;
import ca.spottedleaf.converter.types.MapType;
import fr.euphyllia.fidorial.server.world.storage.datafixers.util.NestedType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class DataFixerBuilder {

    private final Map<DataFixerType, List<DataConverter<MapType, MapType>>> fixers = new EnumMap<>(DataFixerType.class);
    private final Map<DataFixerType, List<DataWalker<MapType>>> walkers = new EnumMap<>(DataFixerType.class);
    private final Map<DataFixerType, List<NestedType>> nesting = new EnumMap<>(DataFixerType.class);
    private final Map<DataFixerType, List<NestedType>> nestedMaps = new EnumMap<>(DataFixerType.class);

    public DataFixerBuilder addFixer(final DataFixerType type, final DataConverter<MapType, MapType> fixer) {
        fixers.computeIfAbsent(type, _ -> new ArrayList<>()).add(Objects.requireNonNull(fixer, "fixer"));
        return this;
    }

    /**
     * @param type   the compound's type
     * @param walker the walker to run after {@code type}'s fixer chain
     */
    public DataFixerBuilder addWalker(final DataFixerType type, final DataWalker<MapType> walker) {
        walkers.computeIfAbsent(type, _ -> new ArrayList<>())
                .add(Objects.requireNonNull(walker, "walker"));
        return this;
    }

    /**
     * @param type       the containing compound's type
     * @param key        the list field's name
     * @param nestedType the type of each element in that list
     */
    public DataFixerBuilder nestList(final DataFixerType type, final String key, final DataFixerType nestedType) {
        nesting.computeIfAbsent(type, _ -> new ArrayList<>())
                .add(new NestedType(Objects.requireNonNull(key, "key"), Objects.requireNonNull(nestedType, "nestedType")));
        return this;
    }

    /**
     * @param type       the containing compound's type
     * @param key        the map field's name
     * @param nestedType the type of the compound stored under {@code key}
     */
    public DataFixerBuilder nestMap(final DataFixerType type, final String key, final DataFixerType nestedType) {
        nestedMaps.computeIfAbsent(type, _ -> new ArrayList<>())
                .add(new NestedType(Objects.requireNonNull(key), Objects.requireNonNull(nestedType)));
        return this;
    }

    public DataFixer build() {
        final int targetDataVersion = highestToVersion();

        final Map<DataFixerType, DataFixerRegistry> registries = new EnumMap<>(DataFixerType.class);
        for (final Map.Entry<DataFixerType, List<DataConverter<MapType, MapType>>> entry : fixers.entrySet()) {
            final DataFixerRegistry registry = new DataFixerRegistry();
            entry.getValue().forEach(registry::register);
            registries.put(entry.getKey(), registry);
        }

        final Map<DataFixerType, List<NestedType>> copiedNesting = new EnumMap<>(DataFixerType.class);
        for (final Map.Entry<DataFixerType, List<NestedType>> entry : nesting.entrySet()) {
            copiedNesting.put(entry.getKey(), List.copyOf(entry.getValue()));
        }

        final Map<DataFixerType, List<NestedType>> copiedNestedMaps = new EnumMap<>(DataFixerType.class);
        for (final Map.Entry<DataFixerType, List<NestedType>> entry : nestedMaps.entrySet()) {
            copiedNestedMaps.put(entry.getKey(), List.copyOf(entry.getValue()));
        }

        return new DataFixer(targetDataVersion, Map.copyOf(registries), Map.copyOf(walkers), Map.copyOf(copiedNesting), Map.copyOf(copiedNestedMaps));
    }

    private int highestToVersion() {
        int highest = 0;
        for (final List<DataConverter<MapType, MapType>> list : fixers.values()) {
            for (final DataConverter<MapType, MapType> fixer : list) {
                highest = Math.max(highest, fixer.getToVersion());
            }
        }
        if (highest == 0) {
            throw new IllegalStateException("No data fixers registered; cannot derive a target data version.");
        }
        return highest;
    }
}
