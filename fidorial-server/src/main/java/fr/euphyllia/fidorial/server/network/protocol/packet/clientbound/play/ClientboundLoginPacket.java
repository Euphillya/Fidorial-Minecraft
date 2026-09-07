package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import net.kyori.adventure.key.Key;

// https://minecraft.wiki/w/Java_Edition_protocol/Packets#Login_(play)
public record ClientboundLoginPacket(
        int entityId,
        boolean isHardcore,
        Key[] dimensions,
        Key dimensionKey,
        int dimensionTypeId,
        long hashedSeed,
        int viewDistance,
        int gameMode,
        boolean isDebug,
        boolean isFlat,
        boolean onlineMode
) implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.LOGIN;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeInt(entityId);
        buf.writeBoolean(isHardcore); // hardcore
        buf.writeKeyArray(dimensions); // liste des dimensions
        buf.writeVarInt(0); // maxPlayers (obsolete)
        buf.writeVarInt(viewDistance);
        buf.writeVarInt(viewDistance); // simulationDistance
        buf.writeBoolean(false); // reducedDebugInfo
        buf.writeBoolean(true); // enableRespawnScreen
        buf.writeBoolean(false); // doLimitedCrafting
        buf.writeVarInt(dimensionTypeId);
        buf.writeKey(dimensionKey);
        buf.writeLong(hashedSeed); // hashedSeed
        buf.writeVarInt(gameMode); // gameMode (survie)
        buf.writeByte(0); // previousGameMode
        buf.writeBoolean(isDebug); // isDebug
        buf.writeBoolean(isFlat); // isFlat
        buf.writeBoolean(false); // hasDeathLocation
        buf.writeVarInt(0); // portalCooldown
        buf.writeVarInt(63); // seaLevel
        buf.writeBoolean(onlineMode);
        buf.writeBoolean(false);
    }
}
