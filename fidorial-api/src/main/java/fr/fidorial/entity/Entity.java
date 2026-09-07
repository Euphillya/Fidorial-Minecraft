package fr.fidorial.entity;

import fr.fidorial.command.CommandSource;
import fr.fidorial.scheduler.SchedulerSource;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEventSource;

import java.util.UUID;

public interface Entity extends CommandSource, HoverEventSource<HoverEvent.ShowEntity>, Sound.Emitter, Sound.Source.Provider, SchedulerSource {

    int entityId();

    UUID uuid();

    Component displayName();

    EntityType type();

    World world();

    Location location();

    default ChunkPos chunk() {
        return location().chunk();
    }

    boolean isRemoved();

    void remove();

    /**
     * Teleports this entity to the given location within its current {@linkplain #world() world}.
     *
     * @param location the destination position and orientation
     * @return {@code true} if the teleport happened, {@code false} if it was refused (for example
     * because the entity has been {@linkplain #isRemoved() removed})
     * @since 0.1.0
     */
    boolean teleport(Location location);

    /**
     * Teleports this entity to the given location, moving it to {@code world} when that differs from
     * its current world.
     *
     * <p>Cross-world teleports relocate the entity between worlds and, for players, trigger the
     * client-side dimension change. The call is refused, returning {@code false}, when the entity is
     * removed or the destination world cannot host it.</p>
     *
     * @param world    the destination world
     * @param location the destination position and orientation
     * @return {@code true} if the teleport happened, {@code false} if it was refused
     * @since 0.1.0
     */
    boolean teleport(World world, Location location);

    /**
     * @param x the destination x coordinate
     * @param y the destination y coordinate
     * @param z the destination z coordinate
     * @return {@code true} if the teleport happened, {@code false} if it was refused
     * @since 0.1.0
     */
    default boolean teleport(final double x, final double y, final double z) {
        final Location current = location();
        return teleport(new Location(x, y, z, current.yaw(), current.pitch()));
    }

    /**
     * @param target the entity to teleport to
     * @return {@code true} if the teleport happened, {@code false} if it was refused
     * @since 0.1.0
     */
    default boolean teleport(final Entity target) {
        return teleport(target.world(), target.location());
    }
}
