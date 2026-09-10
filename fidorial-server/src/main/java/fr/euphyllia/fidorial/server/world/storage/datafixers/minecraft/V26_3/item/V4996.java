package fr.euphyllia.fidorial.server.world.storage.datafixers.minecraft.V26_3.item;

import ca.spottedleaf.converter.DataConverter;
import ca.spottedleaf.converter.types.ListType;
import ca.spottedleaf.converter.types.MapType;
import org.jspecify.annotations.Nullable;

public final class V4996 extends DataConverter<MapType, MapType> {

    public static final int TO_VERSION = 4996;
    private static final String[] SIDES = { "back", "left", "right", "front" };

    public V4996() {
        super(TO_VERSION);
    }

    @Override
    @Nullable public MapType convert(final @Nullable MapType itemStack, final long sourceVersion, final long toVersion) {
        if (itemStack == null) {
            return null;
        }

        final MapType components = itemStack.getMap("components", null);
        if (components == null || !components.hasKey("minecraft:pot_decorations")) {
            return itemStack;
        }

        final ListType oldList = components.getListUnchecked("minecraft:pot_decorations");
        if (oldList == null) {
            // nothing to upgrade
            return itemStack;
        }

        final MapType replacement = components.createEmptyMap();
        for (int i = 0, len = Math.min(oldList.size(), SIDES.length); i < len; ++i) {
            final String id = oldList.getString(i, null);
            if (id == null || id.isEmpty()) {
                continue;
            }
            final MapType stack = replacement.createEmptyMap();
            stack.setString("id", id);
            stack.setInt("count", 1);
            replacement.setMap(SIDES[i], stack);
        }

        components.setMap("minecraft:pot_decorations", replacement);
        return itemStack;
    }
}
