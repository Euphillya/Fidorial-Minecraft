package fr.fidorial.storage.player;

import fr.fidorial.entity.GameMode;
import fr.fidorial.world.Location;
import net.kyori.adventure.key.Key;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.util.UUID;

public interface PlayerDataStorage {

    PlayerData load(UUID uuid, PlayerData defaults) throws IOException;

    void save(UUID uuid, PlayerData data) throws IOException;

    /**
     * Checks whether saved data exists for an identity, without loading it.
     *
     * @param uuid the player identity
     * @return {@code true} if data has been saved for that identity
     * @throws IOException if the check fails
     */
    default boolean exists(final UUID uuid) throws IOException {
        return true;
    }

    /**
     * @param gameMode        the mode the player left in
     * @param respawnWorld    the key of the world the player respawns in, or {@code null} for the
     *                        world spawn
     * @param respawnLocation the position the player respawns at, or {@code null} for the world
     *                        spawn
     * @param world           the key of the world the player was last in, or {@code null} if
     *                        never saved (i.e. a first join)
     * @param location        the position the player was last at, or {@code null} if never saved
     */
    record PlayerData(GameMode gameMode,
                      @Nullable Key respawnWorld,
                      @Nullable Location respawnLocation,
                      @Nullable Key world,
                      @Nullable Location location) {

        /**
         * @return {@code true} when a custom respawn point was saved
         */
        public boolean hasRespawnPoint() {
            return respawnWorld != null && respawnLocation != null;
        }

        /**
         * @return {@code true} when a last-played position was saved
         */
        public boolean hasLastLocation() {
            return world != null && location != null;
        }
    }
}
