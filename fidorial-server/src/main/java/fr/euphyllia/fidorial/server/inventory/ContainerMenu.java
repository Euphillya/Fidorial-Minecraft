package fr.euphyllia.fidorial.server.inventory;

import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundContainerSetContentPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.play.ServerboundContainerClickPacket;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.fidorial.inventory.Container;
import fr.fidorial.inventory.PlayerInventory;
import fr.fidorial.item.ItemStack;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public abstract class ContainerMenu {

    private static final int MAIN_FIRST = 9;

    private static final int MAIN_LAST = 35;
    private static final int MAIN_COUNT = MAIN_LAST - MAIN_FIRST + 1;

    private static final int HOTBAR_FIRST = 0;

    private static final int HOTBAR_COUNT = 9;

    private static final int OFFHAND_SLOT = 40;

    private static final int DEFAULT_MAX_STACK = 64;

    protected final ServerPlayer player;
    private final int windowId;

    private final Set<Integer> dragSlots = new LinkedHashSet<>();
    private ItemStack carried = ItemStack.EMPTY;
    private int stateId;
    private int dragButton = -1;
    private boolean dragging;

    protected ContainerMenu(final ServerPlayer player, final int windowId) {
        this.player = player;
        this.windowId = windowId;
    }

    private static boolean canStack(final ItemStack a, final ItemStack b) {
        if (a.isEmpty() || b.isEmpty()) {
            return false;
        }

        return a.id().equals(b.id())
                && a.components().equals(b.components())
                && a.count() < maxStackSize(a);
    }

    private static int maxStackSize(final ItemStack stack) {
        return DEFAULT_MAX_STACK;
    }

    public final int windowId() {
        return windowId;
    }

    public final int stateId() {
        return stateId;
    }

    public final ItemStack carried() {
        return carried;
    }

    protected abstract Container top();

    public abstract int menuTypeId(RegistryHolder frozen);

    public abstract Component title();

    public void onClosed() {
    }

    public final int topSize() {
        return top().size();
    }

    public final int slotCount() {
        return topSize() + MAIN_COUNT + HOTBAR_COUNT;
    }

    private ItemStack read(final int windowSlot) {
        final int top = topSize();
        if (windowSlot < top) {
            return top().get(windowSlot);
        }
        return player.inventory().get(toInventorySlot(windowSlot));
    }

    private void write(final int windowSlot, final ItemStack stack) {
        final int top = topSize();
        if (windowSlot < top) {
            top().set(windowSlot, stack);
        } else {
            player.inventory().set(toInventorySlot(windowSlot), stack);
        }
    }

    private int toInventorySlot(final int windowSlot) {
        final int offset = windowSlot - topSize();
        if (offset < MAIN_COUNT) {
            return MAIN_FIRST + offset;
        }
        return HOTBAR_FIRST + (offset - MAIN_COUNT);
    }

    private boolean isTopSlot(final int windowSlot) {
        return windowSlot < topSize();
    }

    private boolean isValidSlot(final int windowSlot) {
        return windowSlot >= 0 && windowSlot < slotCount();
    }

    public final void click(final ServerboundContainerClickPacket packet) {
        final int slot = packet.slot();
        final int button = packet.button();

        if (packet.mode() != ServerboundContainerClickPacket.MODE_QUICK_CRAFT) {
            resetDrag();
        }

        switch (packet.mode()) {
            case ServerboundContainerClickPacket.MODE_PICKUP -> pickup(slot, button);
            case ServerboundContainerClickPacket.MODE_QUICK_MOVE -> quickMove(slot);
            case ServerboundContainerClickPacket.MODE_SWAP -> swap(slot, button);
            case ServerboundContainerClickPacket.MODE_CLONE -> clone(slot);
            case ServerboundContainerClickPacket.MODE_THROW -> throwOut(slot);
            case ServerboundContainerClickPacket.MODE_QUICK_CRAFT -> quickCraft(slot, button);
            case ServerboundContainerClickPacket.MODE_PICKUP_ALL -> pickupAll(slot);
            default -> {
                // Unknown mode: touch nothing, the resync will set the client straight.
            }
        }
    }

    private void pickup(final int slot, final int button) {
        if (slot == ServerboundContainerClickPacket.SLOT_OUTSIDE) {
            dropCarried(button == 0);
            return;
        }
        if (!isValidSlot(slot)) {
            return;
        }
        final ItemStack inSlot = read(slot);
        final boolean leftClick = button == 0;

        if (carried.isEmpty()) {
            if (inSlot.isEmpty()) {
                return;
            }
            if (leftClick) {
                carried = inSlot;
                write(slot, ItemStack.EMPTY);
            } else {
                final int taken = (inSlot.count() + 1) / 2;
                carried = inSlot.withCount(taken);
                write(slot, remainderOrEmpty(inSlot, inSlot.count() - taken));
            }
            return;
        }

        if (inSlot.isEmpty()) {
            if (leftClick) {
                write(slot, carried);
                carried = ItemStack.EMPTY;
            } else {
                write(slot, carried.withCount(1));
                carried = remainderOrEmpty(carried, carried.count() - 1);
            }
            return;
        }

        if (canStack(inSlot, carried)) {
            final int max = maxStackSize(inSlot);
            final int room = max - inSlot.count();
            if (room <= 0) {
                return;
            }
            final int moved = leftClick ? Math.min(room, carried.count()) : Math.min(room, 1);
            write(slot, inSlot.withCount(inSlot.count() + moved));
            carried = remainderOrEmpty(carried, carried.count() - moved);
            return;
        }

        if (leftClick) {
            write(slot, carried);
            carried = inSlot;
        }
    }

    private void quickMove(final int slot) {
        if (!isValidSlot(slot)) {
            return;
        }
        final ItemStack stack = read(slot);
        if (stack.isEmpty()) {
            return;
        }
        final ItemStack leftover =
                isTopSlot(slot) ? insert(stack, topSize(), slotCount()) : insert(stack, 0, topSize());
        write(slot, leftover);
    }

    private void swap(final int slot, final int button) {
        if (!isValidSlot(slot)) {
            return;
        }
        final int target;
        if (button >= 0 && button < HOTBAR_COUNT) {
            target = HOTBAR_FIRST + button;
        } else if (button == 40) {
            target = OFFHAND_SLOT;
        } else {
            return;
        }
        final PlayerInventory inventory = player.inventory();
        final ItemStack inSlot = read(slot);
        final ItemStack inTarget = inventory.get(target);
        write(slot, inTarget);
        inventory.set(target, inSlot);
    }


    private void clone(final int slot) {
        if (!isValidSlot(slot) || player.gameMode() != fr.fidorial.entity.GameMode.CREATIVE) {
            return;
        }
        if (!carried.isEmpty()) {
            return;
        }
        final ItemStack inSlot = read(slot);
        if (inSlot.isEmpty()) {
            return;
        }
        carried = inSlot.withCount(maxStackSize(inSlot));
    }

    private void throwOut(final int slot) {
        if (!isValidSlot(slot)) {
        }
    }

    private void quickCraft(final int slot, final int button) {
        switch (button) {
            case 0, 4 -> {
                if (carried.isEmpty()) {
                    resetDrag();
                    return;
                }
                dragging = true;
                dragButton = button;
                dragSlots.clear();
            }
            case 1, 5 -> {
                if (!dragging || button != dragButton + 1 || !isValidSlot(slot)) {
                    return;
                }
                final ItemStack inSlot = read(slot);
                if (inSlot.isEmpty() || canStack(inSlot, carried)) {
                    dragSlots.add(slot);
                }
            }
            case 2, 6 -> {
                if (dragging && button == dragButton + 2) {
                    applyDrag();
                }
                resetDrag();
            }
            default -> resetDrag();
        }
    }

    private void applyDrag() {
        if (carried.isEmpty() || dragSlots.isEmpty()) {
            return;
        }
        final boolean leftDrag = dragButton == 0;
        final int available = carried.count();
        final int perSlot = leftDrag ? Math.max(1, available / dragSlots.size()) : 1;
        int remaining = available;

        for (final int slot : dragSlots) {
            if (remaining <= 0) {
                break;
            }
            final ItemStack inSlot = read(slot);
            final int max = maxStackSize(carried);
            final int current = inSlot.isEmpty() ? 0 : inSlot.count();
            final int moved = Math.min(perSlot, Math.min(remaining, max - current));
            if (moved <= 0) {
                continue;
            }
            write(slot, carried.withCount(current + moved));
            remaining -= moved;
        }
        carried = remainderOrEmpty(carried, remaining);
    }

    private void resetDrag() {
        dragging = false;
        dragButton = -1;
        dragSlots.clear();
    }

    private void pickupAll(final int slot) {
        if (carried.isEmpty()) {
            return;
        }
        final int max = maxStackSize(carried);
        for (int pass = 0; pass < 2 && carried.count() < max; pass++) {
            for (int i = 0; i < slotCount() && carried.count() < max; i++) {
                final ItemStack inSlot = read(i);
                if (!canStack(inSlot, carried)) {
                    continue;
                }
                final boolean full = inSlot.count() >= max;
                if ((pass == 0) == full) {
                    continue;
                }
                final int moved = Math.min(max - carried.count(), inSlot.count());
                carried = carried.withCount(carried.count() + moved);
                write(i, remainderOrEmpty(inSlot, inSlot.count() - moved));
            }
        }
    }

    private void dropCarried(final boolean wholeStack) {
        // Same reason as throwOut: without a ground item entity, we prefer to lose nothing.
    }

    private ItemStack remainderOrEmpty(final ItemStack source, final int count) {
        return count <= 0 ? ItemStack.EMPTY : source.withCount(count);
    }

    private ItemStack insert(final ItemStack stack, final int from, final int to) {
        ItemStack remaining = stack;

        for (int i = from; i < to && !remaining.isEmpty(); i++) {
            final ItemStack inSlot = read(i);
            if (!canStack(inSlot, remaining)) {
                continue;
            }
            final int max = maxStackSize(inSlot);
            final int room = max - inSlot.count();
            if (room <= 0) {
                continue;
            }
            final int moved = Math.min(room, remaining.count());
            write(i, inSlot.withCount(inSlot.count() + moved));
            remaining = remainderOrEmpty(remaining, remaining.count() - moved);
        }

        for (int i = from; i < to && !remaining.isEmpty(); i++) {
            if (read(i).isEmpty()) {
                write(i, remaining);
                remaining = ItemStack.EMPTY;
            }
        }
        return remaining;
    }

    public final ClientboundContainerSetContentPacket buildSyncPacket(final RegistryHolder frozen) {
        stateId++;
        final List<ItemStack> slots = new ArrayList<>(slotCount());
        for (int i = 0; i < slotCount(); i++) {
            slots.add(read(i));
        }
        return new ClientboundContainerSetContentPacket(windowId, stateId, slots, carried, frozen);
    }

    public final void returnCarried() {
        if (carried.isEmpty()) {
            return;
        }
        carried = insert(carried, topSize(), slotCount());
    }
}
