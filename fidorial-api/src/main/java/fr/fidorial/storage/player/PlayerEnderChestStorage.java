package fr.fidorial.storage.player;

import fr.fidorial.inventory.EnderChestInventory;

import java.io.IOException;
import java.util.UUID;

/**
 * Persistence backend for enderchests, following the same model as
 * {@link PlayerInventoryStorage} and {@link PlayerDataStorage}.
 */
public interface PlayerEnderChestStorage {

    /**
     * Loads a player's enderchest. Returns an empty container if the player never had one.
     */
    EnderChestInventory load(UUID uuid) throws IOException;

    void save(UUID uuid, EnderChestInventory enderChest) throws IOException;
}
