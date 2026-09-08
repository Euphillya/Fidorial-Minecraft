package fr.euphyllia.fidorial.server.codecs.container;

import fr.euphyllia.fidorial.server.world.chunk.AnvilChunkSerializer;
import fr.fidorial.inventory.EnderChestInventory;

import java.io.IOException;

public final class PlayerEnderChestCodec {

    private static final String ROOT_NAME = "PlayerEnderChest";
    private static final String LIST_NAME = "EnderItems";

    private PlayerEnderChestCodec() {
    }

    public static byte[] encode(final EnderChestInventory enderChest) throws IOException {
        return ContainerCodecs.encode(ROOT_NAME, LIST_NAME, AnvilChunkSerializer.DATA_VERSION_26_2,
                ContainerCodecs.containerCodec(EnderChestInventory::new), enderChest);
    }

    public static EnderChestInventory decode(final byte[] payload) throws IOException {
        return ContainerCodecs.decode(payload, LIST_NAME, ContainerCodecs.containerCodec(EnderChestInventory::new));
    }
}
