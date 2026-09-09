package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import net.kyori.adventure.key.Key;

import java.util.UUID;

public record ClientboundPlayerInfoRemovePacket(UUID uuid) implements ClientboundPacket {
    @Override
    public Key name() {
        return PlayClientboundPackets.PLAYER_INFO_REMOVE;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeVarInt(1);
        buf.writeUuid(uuid);
    }
}
