package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.ItemStackWriter;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.inventory.PlayerInventory;
import fr.fidorial.item.ItemStack;
import fr.fidorial.registry.keys.BlockTypeKeys;
import net.kyori.adventure.key.Key;

import java.util.Arrays;
import java.util.List;

/**
 * Fully replaces the contents of a window.
 *
 * <p>Format:
 *
 * <pre>
 *   Window ID     VarInt
 *   State ID      VarInt
 *   Slots         Prefixed Array of Slot
 *   Carried item  Slot
 * </pre>
 *
 * <p>Also resent to resynchronize the client after each click, mirroring what the vanilla server
 * does when a State ID does not match.
 *
 * <p>https://minecraft.wiki/w/Java_Edition_protocol/Packets#Set_Container_Content
 */
public record ClientboundContainerSetContentPacket(
        int windowId, int stateId, List<ItemStack> slots, ItemStack carried, RegistryHolder frozen)
        implements ClientboundPacket {

    public static final int PLAYER_INVENTORY_WINDOW = 0;
    private static final int PLAYER_WINDOW_SLOTS = 46;

    public ClientboundContainerSetContentPacket {
        slots = List.copyOf(slots);
    }

    /**
     * Builds the packet for window 0 (player inventory), reordering the internal slots into the
     * network numbering.
     */
    public static ClientboundContainerSetContentPacket ofPlayerInventory(
            final PlayerInventory inventory, final int stateId, final ItemStack carried, final RegistryHolder frozen) {
        final ItemStack[] window = new ItemStack[PLAYER_WINDOW_SLOTS];
        Arrays.fill(window, ItemStack.EMPTY);
        for (int slot = 0; slot < inventory.size(); slot++) {
            final int windowSlot = toWindowSlot(slot);
            if (windowSlot >= 0 && windowSlot < PLAYER_WINDOW_SLOTS) {
                window[windowSlot] = inventory.get(slot);
            }
        }
        return new ClientboundContainerSetContentPacket(
                PLAYER_INVENTORY_WINDOW, stateId, List.of(window), carried, frozen);
    }

    private static int toWindowSlot(final int slot) {
        if (slot >= 0 && slot <= 8) return slot + 36; // hotbar -> 36..44
        if (slot >= 9 && slot <= 35) return slot; // main inventory -> identity
        if (slot >= 36 && slot <= 39) return 44 - slot; // armor: 36->8 (boots)..39->5 (helmet)
        if (slot == 40) return 45; // off hand
        return -1;
    }

    @Override
    public Key name() {
        return PlayClientboundPackets.CONTAINER_SET_CONTENT;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeVarInt(windowId); // Window ID
        buf.writeVarInt(stateId); // State ID
        buf.writeVarInt(slots.size()); // slot count
        for (final ItemStack stack : slots) {
            ItemStackWriter.writeSlot(buf, stack, frozen);
        }
        ItemStackWriter.writeSlot(buf, carried, frozen); // Carried item (cursor)
    }

    private int itemNetworkId(final ItemStack stack) {
        final int id = frozen.networkId(Key.key("minecraft", "item"), stack.id());
        return Math.max(id, FidorialServer.getInstance().blockStateRegistry().networkId(BlockState.of(BlockTypeKeys.AIR.key()))); // air fallback
    }
}
