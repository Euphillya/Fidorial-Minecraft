package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.ItemStackWriter;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.fidorial.item.ItemStack;
import net.kyori.adventure.key.Key;

/**
 * Updates a single slot of a window.
 *
 * <p>Format:
 *
 * <pre>
 *   Window ID   VarInt
 *   State ID    VarInt
 *   Slot        Short
 *   Slot Data   Slot
 * </pre>
 *
 * <p>https://minecraft.wiki/w/Java_Edition_protocol/Packets#Set_Container_Slot
 */
public record ClientboundContainerSetSlotPacket(
        int windowId, int stateId, int slot, ItemStack stack, RegistryHolder frozen)
        implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.CONTAINER_SET_SLOT;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeVarInt(windowId);
        buf.writeVarInt(stateId);
        buf.writeShort(slot);
        ItemStackWriter.writeSlot(buf, stack, frozen);
    }
}
