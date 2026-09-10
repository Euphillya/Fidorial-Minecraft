package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import fr.euphyllia.fidorial.server.entity.AbstractEntity;
import fr.euphyllia.fidorial.server.entity.EntityTypes;
import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.LocationPositionData;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import fr.fidorial.entity.LivingEntity;
import fr.fidorial.world.Location;
import net.kyori.adventure.key.Key;

import java.util.UUID;

// https://minecraft.wiki/w/Java_Edition_protocol/Packets#Spawn_Entity
public record ClientboundAddEntityPacket(
        int entityId,
        UUID uuid,
        int typeNetworkId,
        PositionData.Vec3D position,
        PositionData.VelocityVec3D velocity,
        float pitch,
        float yaw,
        float headYaw,
        int data)
        implements ClientboundPacket {

    public static ClientboundAddEntityPacket of(AbstractEntity entity) {
        Location location = entity.location();
        return new ClientboundAddEntityPacket(
                entity.entityId(),
                entity.uuid(),
                EntityTypes.networkId(entity.type()),
                LocationPositionData.vec3(location),
                new PositionData.VelocityVec3D(0.0, 0.0, 0.0),
                location.pitch(),
                location.yaw(),
                entity instanceof LivingEntity living ? living.headYaw() : 0.0f,
                0);
    }

    @Override
    public Key name() {
        return PlayClientboundPackets.ADD_ENTITY;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarInt(entityId);
        buf.writeUuid(uuid);
        buf.writeVarInt(typeNetworkId);
        position.writeTo(buf);
        velocity.writeTo(buf);
        buf.writeAngle(pitch);
        buf.writeAngle(yaw);
        buf.writeAngle(headYaw);
        buf.writeVarInt(data);
    }
}
