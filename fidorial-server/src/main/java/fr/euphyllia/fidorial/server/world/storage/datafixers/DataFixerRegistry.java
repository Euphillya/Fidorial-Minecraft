package fr.euphyllia.fidorial.server.world.storage.datafixers;

import ca.spottedleaf.converter.DataConverter;
import ca.spottedleaf.converter.types.MapType;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DataFixerRegistry {

    private final List<DataConverter<MapType, MapType>> fixers = new ArrayList<>();
    private boolean sorted = true;

    public DataFixerRegistry register(final DataConverter<MapType, MapType> fixer) {
        fixers.add(Objects.requireNonNull(fixer, "fixer"));
        sorted = false;
        return this;
    }

    @Nullable public MapType apply(final @Nullable MapType data, final int sourceDataVersion) {
        if (data == null) {
            return null;
        }
        if (!sorted) {
            fixers.sort(DataConverter.LOWEST_VERSION_COMPARATOR);
            sorted = true;
        }
        MapType current = data;
        for (final DataConverter<MapType, MapType> fixer : fixers) {
            if (fixer.getToVersion() > sourceDataVersion) {
                current = fixer.convert(current, sourceDataVersion, fixer.getToVersion());
            }
        }
        return current;
    }

    public int size() {
        return fixers.size();
    }
}
