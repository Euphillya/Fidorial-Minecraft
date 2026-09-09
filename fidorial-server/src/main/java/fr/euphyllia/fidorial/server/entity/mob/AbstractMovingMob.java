package fr.euphyllia.fidorial.server.entity.mob;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.entity.ai.BlockView;
import fr.euphyllia.fidorial.server.entity.ai.GoalSelector;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundEntityPositionSyncPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundMoveEntityPosPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundMoveEntityPosRotPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundMoveEntityRotPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundRotateHeadPacket;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.fidorial.entity.Entity;
import fr.fidorial.entity.EntityType;
import fr.fidorial.entity.GameMode;
import fr.fidorial.entity.Player;
import fr.fidorial.entity.ai.Goals;
import fr.fidorial.entity.ai.Navigator;
import fr.fidorial.entity.mob.Mob;
import fr.fidorial.entity.mob.MobDefinition;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractMovingMob extends AbstractMob implements Mob {

    private static final int POSITION_SYNC_INTERVAL = 100;
    private static final double MAX_RELATIVE_DELTA = 7.9;

    private static final double DEFAULT_HALF_WIDTH = 0.3;
    private static final double DEFAULT_HEIGHT = 1.7;
    private static final double DEFAULT_MOVEMENT_SPEED = 0.25;

    protected final GoalSelector goals = new GoalSelector();

    private @Nullable ServerPlayer target;
    private double moveSpeed;
    private double followRangeOverride = -1.0;

    private double velocityX;
    private double velocityY;
    private double velocityZ;
    private boolean onGround;
    private float yaw;
    private float pitch;

    private double sentX;
    private double sentY;
    private double sentZ;
    private float sentYaw;
    private float sentPitch;
    private float sentHeadYaw;
    private int ticksSinceSync;

    protected AbstractMovingMob(final int entityId, final UUID uuid, final EntityType type, final World world,
                                final Location location, final float maxHealth) {
        super(entityId, uuid, type, world, location, maxHealth);
        this.yaw = location.yaw();
        this.pitch = location.pitch();
        this.sentX = location.x();
        this.sentY = location.y();
        this.sentZ = location.z();
        this.sentYaw = yaw;
        this.sentPitch = pitch;
        this.sentHeadYaw = yaw;
    }

    public final ServerWorld serverWorld() {
        return (ServerWorld) world();
    }

    @Override
    public final FidorialServer server() {
        return FidorialServer.getInstance();
    }

    @Override
    public Optional<MobDefinition> definition() {
        return Optional.empty();
    }

    @Override
    public final Goals goals() {
        return goals;
    }

    @Override
    public Navigator navigation() {
        return Navigator.NONE;
    }

    @Override
    public final @Nullable ServerPlayer target() {
        return target;
    }

    @Override
    public final void setTarget(final @Nullable Player target) {
        this.target = target instanceof final ServerPlayer serverPlayer ? serverPlayer : null;
    }

    @Override
    public final double followRange() {
        return followRangeOverride >= 0.0 ? followRangeOverride : defaultFollowRange();
    }

    @Override
    public final void setFollowRange(final double range) {
        this.followRangeOverride = range;
    }

    protected double defaultFollowRange() {
        return 16.0;
    }

    @Override
    public double movementSpeed() {
        return DEFAULT_MOVEMENT_SPEED;
    }

    @Override
    public final void setMoveSpeed(final double speed) {
        this.moveSpeed = speed;
    }

    protected final double moveSpeed() {
        return moveSpeed;
    }

    @Override
    public double height() {
        return DEFAULT_HEIGHT;
    }

    @Override
    public double width() {
        return halfWidth() * 2.0;
    }

    protected double halfWidth() {
        return DEFAULT_HALF_WIDTH;
    }

    protected final boolean isBoxBlocked(final double x, final double y, final double z) {
        final double half = halfWidth();
        final int minBlockY = (int) Math.floor(y);
        final int maxBlockY = (int) Math.floor(y + height() - 0.01);
        final ServerWorld world = serverWorld();
        for (int blockY = minBlockY; blockY <= maxBlockY; blockY++) {
            if (!BlockView.isPassable(world, (int) Math.floor(x - half), blockY, (int) Math.floor(z - half))
                    || !BlockView.isPassable(world, (int) Math.floor(x + half), blockY, (int) Math.floor(z - half))
                    || !BlockView.isPassable(world, (int) Math.floor(x - half), blockY, (int) Math.floor(z + half))
                    || !BlockView.isPassable(world, (int) Math.floor(x + half), blockY, (int) Math.floor(z + half))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final double velocityX() {
        return this.velocityX;
    }

    @Override
    public final double velocityY() {
        return this.velocityY;
    }

    @Override
    public final double velocityZ() {
        return this.velocityZ;
    }

    @Override
    public final void setVelocity(final double x, final double y, final double z) {
        this.velocityX = x;
        this.velocityY = y;
        this.velocityZ = z;
    }

    @Override
    public final boolean onGround() {
        return this.onGround;
    }

    public final void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }

    public final float yaw() {
        return this.yaw;
    }

    public final float pitch() {
        return this.pitch;
    }

    protected final void setRotation(final float yaw, final float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public final void lookAt(final double x, final double y, final double z) {
        final Location self = location();
        final double dx = x - self.x();
        final double dy = y - (self.y() + 1.2);
        final double dz = z - self.z();
        final double horizontal = Math.sqrt(dx * dx + dz * dz);
        if (horizontal > 1.0E-4 || Math.abs(dy) > 1.0E-4) {
            this.yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
            this.pitch = (float) Math.toDegrees(-Math.atan2(dy, horizontal));
        }
    }

    @Override
    public final double distanceSqTo(final Entity entity) {
        final Location self = location();
        final Location other = entity.location();
        final double dx = self.x() - other.x();
        final double dy = self.y() - other.y();
        final double dz = self.z() - other.z();
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public final @Nullable ServerPlayer nearestPlayer(final double maxDistance) {
        final List<ServerPlayer> players = server().players();
        final double maxDistSq = maxDistance < 0.0 ? Double.MAX_VALUE : maxDistance * maxDistance;
        ServerPlayer best = null;
        double bestDistSq = Double.MAX_VALUE;
        for (int i = 0, size = players.size(); i < size; i++) {
            final ServerPlayer player = players.get(i);
            if (player.isRemoved() || player.isDead()
                    || player.gameMode() == GameMode.SPECTATOR
                    || player.world() != world()) {
                continue;
            }
            final double distSq = distanceSqTo(player);
            if (distSq <= maxDistSq && distSq < bestDistSq) {
                bestDistSq = distSq;
                best = player;
            }
        }
        return best;
    }

    @Override
    public final boolean hasLineOfSightTo(final Entity entity) {
        final Location self = location();
        final Location other = entity.location();
        return BlockView.hasLineOfSight(serverWorld(),
                self.x(), self.y() + 1.2, self.z(),
                other.x(), other.y() + 1.5, other.z());
    }

    protected final void updateChunkMembership(final Location before, final Location after) {
        final ChunkPos fromChunk = before.chunk();
        final ChunkPos toChunk = after.chunk();
        if (!fromChunk.equals(toChunk)) {
            serverWorld().entityMoved(this, fromChunk, toChunk);
            server().regionizer().moveTicket(serverWorld().dimension().id(), fromChunk, toChunk);
        }
    }

    protected final void syncToClients() {
        final Location current = location();
        final double dx = current.x() - sentX;
        final double dy = current.y() - sentY;
        final double dz = current.z() - sentZ;
        final boolean moved = Math.abs(dx) + Math.abs(dy) + Math.abs(dz) > 1.0 / 4096.0;
        final boolean rotated = Math.abs(yaw - sentYaw) > 1.0f || Math.abs(pitch - sentPitch) > 1.0f;
        ticksSinceSync++;

        final boolean needsAbsoluteSync = ticksSinceSync >= POSITION_SYNC_INTERVAL
                || Math.abs(dx) > MAX_RELATIVE_DELTA
                || Math.abs(dy) > MAX_RELATIVE_DELTA
                || Math.abs(dz) > MAX_RELATIVE_DELTA;

        if (needsAbsoluteSync && (moved || rotated || ticksSinceSync >= POSITION_SYNC_INTERVAL)) {
            sendToTrackers(new ClientboundEntityPositionSyncPacket(
                    entityId(),
                    current.x(),
                    current.y(),
                    current.z(),
                    velocityX,
                    velocityY,
                    velocityZ,
                    yaw,
                    pitch,
                    onGround));
            sentX = current.x();
            sentY = current.y();
            sentZ = current.z();
            sentYaw = yaw;
            sentPitch = pitch;
            ticksSinceSync = 0;
        } else if (moved) {
            final short qx = (short) Math.round(dx * 4096.0);
            final short qy = (short) Math.round(dy * 4096.0);
            final short qz = (short) Math.round(dz * 4096.0);
            if (rotated) {
                sendToTrackers(new ClientboundMoveEntityPosRotPacket(entityId(), qx, qy, qz, yaw, pitch, onGround));
                sentYaw = yaw;
                sentPitch = pitch;
            } else {
                sendToTrackers(new ClientboundMoveEntityPosPacket(entityId(), qx, qy, qz, onGround));
            }
            sentX += qx / 4096.0;
            sentY += qy / 4096.0;
            sentZ += qz / 4096.0;
        } else if (rotated) {
            sendToTrackers(new ClientboundMoveEntityRotPacket(entityId(), yaw, pitch, onGround));
            sentYaw = yaw;
            sentPitch = pitch;
        }

        if (Math.abs(yaw - sentHeadYaw) > 1.0f) {
            sendToTrackers(new ClientboundRotateHeadPacket(entityId(), yaw));
            sentHeadYaw = yaw;
        }
    }
}
