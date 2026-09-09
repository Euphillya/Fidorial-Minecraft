package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.fidorial.world.BlockPos;
import net.kyori.adventure.key.Key;

/**
 * <p>0–9 are the displayable destroy stages and each other number means that there is no animation on this coordinate. Block break animations can still be applied on air; the animation will remain visible, although there is no block being broken.</p>
 * <p>However, if this is applied to a transparent block, odd graphical effects may happen, including water losing its transparency.</p>
 * <p>(An effect similar to this can be seen in normal gameplay when breaking ice blocks) If you need to display several break animations at the same time, you have to give each of them a unique Entity ID. The entity ID does not need to correspond to an actual entity on the client. It is valid to use a randomly generated number. When removing the break animation, you must use the ID of the entity that set it.</p>
 *
 * <p><b>Packet ID:</b> Play = 5 (0x05)</p>
 * <p><b>Source:</b> <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets#Set_Block_Destroy_Stage">Set Block Destroy Stage</a></p>
 *
 * <h4>Packet structure</h4>
 * <table>
 *   <caption>Fields, in write/read order</caption>
 *   <thead>
 *     <tr><th>#</th><th>Field</th><th>Type</th><th>Description</th></tr>
 *   </thead>
 *   <tbody>
 *     <tr><td>0</td><td>Entity ID</td><td>VarInt</td><td>The ID of the entity breaking the block.</td></tr>
 *     <tr><td>1</td><td>Location</td><td>Position</td><td>Block Position.</td></tr>
 *     <tr><td>2</td><td>Destroy Stage</td><td>Unsigned Byte</td><td>0–9 to set it, any other value to remove it.</td></tr>
 *   </tbody>
 * </table>
 */
public record ClientboundBlockDestructionPacket(int entityId, BlockPos location,
                                                int destroyStage) implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.BLOCK_DESTRUCTION;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeVarInt(entityId);
        buf.writePosition(location.x(), location.y(), location.z());
        buf.writeByte(destroyStage);
    }
}
