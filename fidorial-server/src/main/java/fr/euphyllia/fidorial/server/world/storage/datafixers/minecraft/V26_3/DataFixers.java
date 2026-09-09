package fr.euphyllia.fidorial.server.world.storage.datafixers.minecraft.V26_3;

import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixer;
import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerBuilder;
import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerType;
import fr.euphyllia.fidorial.server.world.storage.datafixers.minecraft.V26_3.block_entity.V4996_1;
import fr.euphyllia.fidorial.server.world.storage.datafixers.minecraft.V26_3.item.V4996;

public final class DataFixers {

    private DataFixers() {
        throw new UnsupportedOperationException("DataFixers cannot be instantiated.");
    }

    public static DataFixer buildDataFixers() {
        return new DataFixerBuilder()
                .addFixer(DataFixerType.ITEM_STACK, new V4996())
                .addFixer(DataFixerType.BLOCK_ENTITY, new V4996_1())
                .nestList(DataFixerType.BLOCK_ENTITY, "Items", DataFixerType.ITEM_STACK)
                .nestList(DataFixerType.CHUNK, "block_entities", DataFixerType.BLOCK_ENTITY)
                .nestList(DataFixerType.PLAYER, "Inventory", DataFixerType.ITEM_STACK)
                .nestList(DataFixerType.PLAYER, "EnderItems", DataFixerType.ITEM_STACK)
                .build();
    }
}
