package fr.euphyllia.fidorial.server.world.storage.datafixers.minecraft.V26_3.block_entity;

import ca.spottedleaf.converter.DataConverter;
import ca.spottedleaf.converter.types.ListType;
import ca.spottedleaf.converter.types.MapType;
import org.jspecify.annotations.Nullable;

public final class V4996_1 extends DataConverter<MapType, MapType> {

    public static final int TO_VERSION = 4996;
    private static final String[] SIDES = { "back", "left", "right", "front" };

    public V4996_1() {
        super(TO_VERSION, 1);
    }

    @Override
    @Nullable public MapType convert(final @Nullable MapType blockEntity, final long sourceVersion, final long toVersion) {
        if (blockEntity == null || !"minecraft:decorated_pot".equals(blockEntity.getString("id"))) {
            return blockEntity;
        }

        if (!blockEntity.hasKey("sherds")) {
            return blockEntity;
        }

        final ListType oldList = blockEntity.getListUnchecked("sherds");
        if (oldList == null) {
            // nothing to upgrade
            return blockEntity;
        }

        final MapType replacement = blockEntity.createEmptyMap();
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

        blockEntity.setMap("sherds", replacement);
        return blockEntity;
    }
}
