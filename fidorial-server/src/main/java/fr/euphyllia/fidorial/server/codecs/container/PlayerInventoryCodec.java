package fr.euphyllia.fidorial.server.codecs.container;

import fr.euphyllia.fidorial.server.VersionConstants;
import fr.fidorial.inventory.PlayerInventory;

import java.io.IOException;

public final class PlayerInventoryCodec {

    private static final String ROOT_NAME = "PlayerInventory";
    private static final String LIST_NAME = "Inventory";

    private PlayerInventoryCodec() {
    }

    public static byte[] encode(final PlayerInventory inventory) throws IOException {
        return ContainerCodecs.encode(ROOT_NAME, LIST_NAME, VersionConstants.DATA_VERSION,
                ContainerCodecs.containerCodec(PlayerInventory::new), inventory);
    }

    public static PlayerInventory decode(final byte[] payload) throws IOException {
        return ContainerCodecs.decode(payload, LIST_NAME, ContainerCodecs.containerCodec(PlayerInventory::new));
    }
}
