package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils;

import fr.fidorial.world.Location;
import org.jspecify.annotations.Nullable;

public final class LocationPositionData {

    private LocationPositionData() {
    }

    public static PositionData.Vec3D vec3(final Location location) {
        return new PositionData.Vec3D(location.x(), location.y(), location.z());
    }

    public static PositionData.@Nullable DeltaVec3D deltaVec3(final Location previous, final Location location) {
        return PositionData.DeltaVec3D.between(vec3(previous), vec3(location));
    }

    public static PositionData.VelocityVec3D velocityVec3D(final Location location) {
        return new PositionData.VelocityVec3D(location.x(), location.y(), location.z());
    }

    public static PositionData.FloatRotation floatRotation(final Location location) {
        return new PositionData.FloatRotation(location.yaw(), location.pitch());
    }

    public static PositionData.AngleRotation angleRotation(final Location location) {
        return new PositionData.AngleRotation(location.yaw(), location.pitch());
    }
}
