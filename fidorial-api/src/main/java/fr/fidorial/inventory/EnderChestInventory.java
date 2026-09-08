package fr.fidorial.inventory;

import fr.fidorial.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;

public class EnderChestInventory implements Container {

    public static final int SIZE = 27;

    private final ItemStack[] slots = new ItemStack[SIZE];

    public EnderChestInventory() {
        Arrays.fill(slots, ItemStack.EMPTY);
    }

    private static void checkSlot(final int slot) {
        if (slot < 0 || slot >= SIZE) {
            throw new IndexOutOfBoundsException("Invalid slot: " + slot);
        }
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public ItemStack get(final int slot) {
        checkSlot(slot);
        return slots[slot];
    }

    @Override
    public void set(final int slot, @Nullable final ItemStack stack) {
        checkSlot(slot);
        slots[slot] = stack == null ? ItemStack.EMPTY : stack;
    }

    @Override
    public void clear() {
        Arrays.fill(slots, ItemStack.EMPTY);
    }

    @Override
    public boolean isEmpty() {
        for (final ItemStack stack : slots) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public ItemStack[] getAllItems() {
        return Arrays.copyOf(slots, SIZE);
    }

    public void setAllItems(final ItemStack @Nullable [] contents) {
        if (contents == null) {
            return;
        }
        clear();
        final int limit = Math.min(contents.length, SIZE);
        for (int slot = 0; slot < limit; slot++) {
            set(slot, contents[slot]);
        }
    }
}
