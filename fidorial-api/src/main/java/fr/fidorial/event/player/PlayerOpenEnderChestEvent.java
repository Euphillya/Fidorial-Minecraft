package fr.fidorial.event.player;

import fr.fidorial.entity.Player;
import fr.fidorial.event.Cancellable;
import fr.fidorial.inventory.EnderChestInventory;
import fr.fidorial.world.BlockPos;

/**
 * Fired right before a player opens an ender chest.
 */
public final class PlayerOpenEnderChestEvent implements PlayerEvent, Cancellable {

    private final Player player;
    private final BlockPos position;
    private final EnderChestInventory enderChest;
    private boolean cancelled;

    public PlayerOpenEnderChestEvent(final Player player, final BlockPos position, final EnderChestInventory enderChest) {
        this.player = player;
        this.position = position;
        this.enderChest = enderChest;
    }

    @Override
    public Player player() {
        return player;
    }

    /**
     * Position of the opened block.
     */
    public BlockPos position() {
        return position;
    }

    /**
     * The container about to be displayed, mutable.
     */
    public EnderChestInventory enderChest() {
        return enderChest;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
