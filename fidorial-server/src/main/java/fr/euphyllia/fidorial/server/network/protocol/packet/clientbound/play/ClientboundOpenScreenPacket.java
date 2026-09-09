package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;

/**
 * Tells the client to open a container window.
 *
 * <p>Format:
 *
 * <pre>
 *   Window ID     VarInt
 *   Window Type   VarInt          (index into the minecraft:menu registry)
 *   Window Title  Text Component
 * </pre>
 *
 * <p>Never sent for the player's own inventory, which is always considered open server-side.
 *
 * <p>https://minecraft.wiki/w/Java_Edition_protocol/Packets#Open_Screen
 */
public record ClientboundOpenScreenPacket(int windowId, int menuTypeId, Component title)
        implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.OPEN_SCREEN;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeVarInt(windowId);
        buf.writeVarInt(menuTypeId);
        buf.writeComponent(title);
    }
}
