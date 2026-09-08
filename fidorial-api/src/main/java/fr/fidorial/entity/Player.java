package fr.fidorial.entity;

import fr.fidorial.Server;
import fr.fidorial.command.CommandSender;
import fr.fidorial.command.CommandSource;
import fr.fidorial.event.player.PlayerJoinEvent;
import fr.fidorial.event.player.PlayerQuitEvent;
import fr.fidorial.inventory.EnderChestInventory;
import fr.fidorial.inventory.PlayerInventory;
import fr.fidorial.item.ItemStack;
import fr.fidorial.permission.PermissionHolder;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.bossbar.BossBarViewer;
import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.object.ObjectContentsLike;
import org.jspecify.annotations.Nullable;

import java.net.InetAddress;
import java.util.UUID;

/**
 * Represents a player currently connected to the server.
 * <p>
 * A {@code Player} reference is not stable across the server lifecycle, as entering the
 * CONFIGURATION phase (see {@link #enterConfigurationPhase()}) discards the
 * underlying player and recreates it, which invalidates every reference held
 * before that point.
 * {@code Player} references should not be cached, as a reference obtained from an event or lookup is only
 * guaranteed valid for the duration of that call. Instead, re-fetch by UUID or name via
 * {@link Server#player(UUID)} or {@link Server#player(String)}.
 *
 * @since 0.1.0
 */
public interface Player extends LivingEntity, PermissionHolder, CommandSource, CommandSender, Identified, BossBarViewer, ObjectContentsLike {

    void refreshCommands();

    PlayerProfile profile();

    /**
     * Gets the offline handle for this player's identity.
     *
     * @return the handle for this player's identity
     * @since 0.1.0
     */
    default OfflinePlayer offline() {
        return server().offlinePlayers().of(this);
    }

    @Override
    default Identity identity() {
        return Identity.identity(this.uuid());
    }

    @Override
    default UUID uuid() {
        return profile().uuid();
    }

    @Override
    default String name() {
        return profile().name();
    }

    /**
     * Gets the address this player is connected from.
     *
     * @return the client address
     * @throws IllegalStateException if the connection has no resolvable IP address
     *
     * @since 0.1.0
     */
    InetAddress address();

    /**
     * Gets the round-trip time between the server and this player's client.
     *
     * @return the round-trip time in milliseconds
     * @since 0.1.0
     */
    int ping();

    void kick(Component reason);

    PlayerInventory inventory();

    EnderChestInventory enderChest();

    /**
     * @return the hotbar slot ({@code 0}-{@code 8}) currently selected by this player
     * @since 0.1.0
     */
    int selectedSlot();

    /**
     * @param slot the hotbar slot to select, {@code 0}-{@code 8}
     * @throws IllegalArgumentException if {@code slot} is out of {@code [0, 8]}
     * @since 0.1.0
     */
    void setSelectedSlot(int slot);

    /**
     * @return the stack in the currently {@linkplain #selectedSlot() selected} hotbar slot,
     * never {@code null} ({@link ItemStack#EMPTY} when nothing is held)
     * @since 0.1.0
     */
    default ItemStack heldItem() {
        return inventory().get(selectedSlot());
    }

    /**
     * Replaces the stack in the currently {@linkplain #selectedSlot() selected} hotbar slot.
     *
     * @param stack the new stack, {@code null} is normalized to {@link ItemStack#EMPTY}
     * @since 0.1.0
     */
    default void setHeldItem(final @Nullable ItemStack stack) {
        inventory().set(selectedSlot(), stack);
    }

    GameMode gameMode();

    void setGameMode(GameMode gameMode);

    /**
     * @return {@code true} while this player is dead and still on the respawn screen
     * @since 0.1.0
     */
    boolean isAwaitingRespawn();

    /**
     * Respawns this player, exactly as if they had clicked the button on the death screen.
     *
     * <p>The respawn is queued on the region owning the player and happens on the next tick, so it
     * is safe to call from a {@code PlayerDeathEvent} listener to skip the death screen entirely.
     * The player comes back at their {@linkplain #respawnPoint() respawn point}, or at the world
     * spawn when they have none, and a {@code PlayerRespawnEvent} is fired as usual.</p>
     *
     * @return {@code true} if the respawn was queued, {@code false} when the player is not waiting
     * to respawn or has left the server
     * @since 0.1.0
     */
    boolean respawn();

    /**
     * @return where this player respawns, or {@code null} when they use the world spawn
     * @since 0.1.0
     */
    @Nullable RespawnPoint respawnPoint();

    /**
     * Sets where this player respawns. The point is saved with the rest of their data and restored
     * on their next login.
     *
     * @param point the point to respawn at, or {@code null} to fall back to the world spawn
     * @since 0.1.0
     */
    void setRespawnPoint(@Nullable RespawnPoint point);

    /**
     * @param world    the world to respawn in
     * @param location the position to respawn at
     * @since 0.1.0
     */
    default void setRespawnPoint(final World world, final Location location) {
        setRespawnPoint(new RespawnPoint(world, location));
    }

    /**
     * @param location the position to respawn at, in the player's current world
     * @since 0.1.0
     */
    default void setRespawnPoint(final Location location) {
        setRespawnPoint(new RespawnPoint(world(), location));
    }

    /**
     * Switches this player's connection status from PLAY to CONFIGURATION.
     * Useful for resending data synced during the CONFIGURATION phase.
     *
     * @since 0.1.0
     * @apiNote This removes the player from the world fully and creates them anew,
     * firing {@link PlayerQuitEvent}, {@link PlayerJoinEvent}, and saving their data to disk.
     * Any reference to this {@link Player} held before this call should be considered stale.
     */
    void enterConfigurationPhase();

    /**
     * Updates the player's inventory on the client side.
     *
     * <p>This is useful when the server has changed the inventory contents, and the client needs to be
     * informed of those changes. This method should be called after any modifications to the player's
     * inventory, such as adding or removing items, to ensure that the client sees the correct state.
     *
     * @since 0.1.0
     */
    void updateInventory();
}
