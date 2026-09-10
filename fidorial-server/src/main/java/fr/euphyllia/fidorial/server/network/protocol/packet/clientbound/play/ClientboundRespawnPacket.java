package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import net.kyori.adventure.key.Key;

public record ClientboundRespawnPacket(Key dimensionKey, int dimensionTypeId, long hashedSeed, int gameMode, int dataToKeep, boolean isDebug, boolean isFlat)
        implements ClientboundPacket {

    /**
     * Keep nothing across the dimension change.
     */
    public static final int KEEP_NOTHING = 0;
    /**
     * Keep the player's attributes across the dimension change.
     */
    public static final int KEEP_ATTRIBUTES = 1;
    /**
     * Keep the player's entity metadata across the dimension change.
     */
    public static final int KEEP_METADATA = 2;
    /**
     * Keep both attributes and metadata across the dimension change.
     */
    public static final int KEEP_ALL = KEEP_ATTRIBUTES | KEEP_METADATA;

    public ClientboundRespawnPacket(final Key dimensionKey, final int dimensionTypeId, final long hashedSeed, final int gameMode, final boolean isDebug, final boolean isFlat) {
        this(dimensionKey, dimensionTypeId, hashedSeed, gameMode, KEEP_NOTHING, isDebug, isFlat);
    }

    @Override
    public Key name() {
        return PlayClientboundPackets.RESPAWN;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeVarInt(dimensionTypeId); // dimension type (minecraft:dimension_type registry id)
        buf.writeKey(dimensionKey); // dimension name
        buf.writeLong(hashedSeed); // hashed seed (biome noise only)
        buf.writeVarInt(gameMode); // game mode (unsigned byte)
        buf.writeVarInt(0); // previous game mode (0 = undefined)
        buf.writeBoolean(isDebug); // isDebug
        buf.writeBoolean(isFlat); // isFlat
        buf.writeBoolean(false); // has death location
        buf.writeVarInt(0); // portal cooldown ticks
        buf.writeVarInt(63); // sea level
        buf.writeByte(dataToKeep);
    }
}
