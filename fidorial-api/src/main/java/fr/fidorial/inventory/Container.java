package fr.fidorial.inventory;

import fr.fidorial.item.ItemStack;
import org.jspecify.annotations.Nullable;

public interface Container {

    /**
     * Number of slots exposed by this container.
     */
    int size();

    /**
     * Returns the contents of the requested slot, never {@code null}.
     *
     * @throws IndexOutOfBoundsException if the slot is out of bounds
     */
    ItemStack get(int slot);

    /**
     * Replaces the contents of a slot. A {@code null} is normalized to {@link ItemStack#EMPTY}.
     *
     * @throws IndexOutOfBoundsException if the slot is out of bounds
     */
    void set(int slot, @Nullable ItemStack stack);

    /**
     * Clears every slot.
     */
    void clear();

    /**
     * {@code true} if every slot is empty.
     */
    boolean isEmpty();
}
