package fr.euphyllia.fidorial.server.entity;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMob;
import fr.euphyllia.fidorial.server.network.ClientConnection;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundAddEntityPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundEntityPositionSyncPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.LocationPositionData;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.fidorial.command.CommandSender;
import fr.fidorial.entity.Entity;
import fr.fidorial.entity.EntityType;
import fr.fidorial.scheduler.RegionizedScheduler;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.UnaryOperator;

public abstract class AbstractEntity implements Entity {

    public static final ComponentLogger LOGGER = ComponentLogger.logger(AbstractEntity.class);

    private final int entityId;
    private final EntityType type;
    private final AtomicBoolean removed = new AtomicBoolean(false);
    private UUID uuid;
    private volatile World world;
    private volatile Location location;

    protected AbstractEntity(final int entityId, final UUID uuid, final EntityType type, final World world, final Location location) {
        this.entityId = entityId;
        this.uuid = uuid;
        this.type = type;
        this.world = world;
        this.location = location;
    }

    @Override
    public final int entityId() {
        return entityId;
    }

    @Override
    public UUID uuid() {
        return uuid;
    }

    @Override
    public Component displayName() {
        return Component.translatable("entity." + type.key().asString().replace(':', '.'));
    }

    public final void restoreUuid(final UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public final EntityType type() {
        return type;
    }

    @Override
    public final World world() {
        return world;
    }

    @Override
    public final Location location() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public void setWorld(final World world) {
        this.world = world;
    }

    @Override
    public final boolean isRemoved() {
        return removed.get();
    }

    @Override
    public FidorialServer server() {
        return FidorialServer.getInstance();
    }

    @Override
    public final void remove() {
        if (removed.compareAndSet(false, true)) {
            onRemoved();
        }
    }

    protected void onRemoved() {
    }

    public void tick(final long currentTick) {
    }

    public final void sendToTrackers(final ClientboundPacket packet) {
        server().entityTracker().sendToViewers(this, packet);
    }

    public void sendSpawnPackets(final ClientConnection connection) {
        connection.send(ClientboundAddEntityPacket.of(this));
    }

    @Override
    public final boolean equals(final Object o) {
        return o instanceof final AbstractEntity other && other.entityId == entityId;
    }

    @Override
    public final int hashCode() {
        return Integer.hashCode(entityId);
    }

    @Override
    public String toString() {
        return type.key() + "#" + entityId;
    }

    @Override
    public CommandSender sender() {
        return null;
    }

    @Override
    public @Nullable Entity executor() {
        return this;
    }

    @Override
    public boolean teleport(final Location location) {
        return teleport(world(), location);
    }

    @Override
    public boolean teleport(final World destination, final Location location) {
        if (isRemoved() || !(destination instanceof final ServerWorld target)) {
            return false;
        }

        try {
            final World from = world();
            final Location previous = location();

            if (from == target) {
                setLocation(location);
                target.entityMoved(this, previous.chunk(), location.chunk());
            } else {
                if (from instanceof final ServerWorld old) {
                    old.removeEntity(this);
                    if (this instanceof AbstractMob) {
                        server().regionizer().removeTicket(old.key(), previous.chunk());
                    }
                }
                setWorld(target);
                setLocation(location);
                target.addEntity(this);
                if (this instanceof AbstractMob) {
                    server().regionizer().addTicket(target.key(), location.chunk());
                }
            }

            sendToTrackers(new ClientboundEntityPositionSyncPacket(
                    entityId(),
                    new PositionData.LinearPositionPath(LocationPositionData.vec3(location)),
                    LocationPositionData.floatRotation(location),
                    false));
            server().entityTracker().update(this, server().players());
            return true;
        } catch (final Exception exception) {
            LOGGER.error("An error occurred while teleporting the player : ", exception);
            return false;
        }
    }

    @Override
    public HoverEvent<HoverEvent.ShowEntity> asHoverEvent(final UnaryOperator<HoverEvent.ShowEntity> op) {
        return HoverEvent.showEntity(op.apply(HoverEvent.ShowEntity.showEntity(type().key(), uuid(), displayName())));
    }

    @Override
    public boolean execute(final Runnable task) {
        if (isRemoved()) {
            return false;
        }
        server().scheduler().execute(world().key(), chunk(), task);
        return true;
    }

    @Override
    public boolean executeDelayed(final Runnable task, final long delayTicks) {
        if (isRemoved()) {
            return false;
        }
        server().scheduler().executeDelayed(world().key(), chunk(), () -> runOrChase(task), delayTicks);
        return true;
    }

    @Override
    public boolean isOwnedByCurrentThread() {
        return !isRemoved() && server().scheduler().isOwnedByCurrentThread(world().key(), chunk());
    }

    private void runOrChase(final Runnable task) {
        if (isRemoved()) {
            return;
        }
        final RegionizedScheduler scheduler = server().scheduler();
        if (scheduler.isOwnedByCurrentThread(world().key(), chunk())) {
            task.run();
        } else {
            scheduler.execute(world().key(), chunk(), () -> runOrChase(task));
        }
    }
}
