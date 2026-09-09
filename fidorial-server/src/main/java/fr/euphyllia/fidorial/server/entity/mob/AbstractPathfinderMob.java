package fr.euphyllia.fidorial.server.entity.mob;

import fr.euphyllia.fidorial.server.entity.ai.Navigation;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.fidorial.entity.EntityType;
import fr.fidorial.entity.GameMode;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;

import java.util.List;
import java.util.UUID;

public abstract class AbstractPathfinderMob extends AbstractMovingMob {

    private static final double GRAVITY = 0.08;
    private static final double VERTICAL_DRAG = 0.98;
    private static final double GROUND_FRICTION = 0.6;
    private static final double JUMP_VELOCITY = 0.42;
    private static final double MAX_FALL_SPEED = 3.0;

    private static final double STEP_INTERVAL = 1.5;

    private static final int TARGET_SCAN_INTERVAL = 10;

    protected final Navigation navigation;

    private double stepDistance;

    protected AbstractPathfinderMob(final int entityId, final UUID uuid, final EntityType type, final World world,
                                    final Location location, final float maxHealth) {
        super(entityId, uuid, type, world, location, maxHealth);
        this.navigation = new Navigation(serverWorld());
    }

    @Override
    public void tick(final long currentTick) {
        if (isRemoved()) {
            return;
        }
        tickLiving(currentTick);
        if (isRemoved()) {
            return;
        }

        if (isDead()) {
            setMoveSpeed(0.0);
            final Location fallingFrom = location();
            applyPhysics();
            updateChunkMembership(fallingFrom, location());
            syncToClients();
            return;
        }

        final ServerPlayer currentTarget = target();
        if (currentTick % TARGET_SCAN_INTERVAL == 0) {
            updateTarget();
        } else if (currentTarget != null && !isValidTarget(currentTarget, dropRangeSq())) {
            setTarget(null);
        }

        setMoveSpeed(0.0);
        goals.tick();
        tickBehaviours(currentTick);

        final Location before = location();
        applyPhysics();
        final Location after = location();

        updateChunkMembership(before, after);

        syncToClients();

        if (after.y() < serverWorld().minY() - 64) {
            server().despawnEntity(this);
        }
    }

    @Override
    protected void onDeath() {
        navigation.stop();
        goals.stopAll();
        setTarget(null);
        super.onDeath();
    }

    @Override
    protected void onDeathAnimationFinished() {
        server().despawnEntity(this);
    }

    private double dropRangeSq() {
        final double range = followRange() * 1.25;
        return range * range;
    }

    private void updateTarget() {
        final List<ServerPlayer> players = server().players();
        if (players.isEmpty()) {
            setTarget(null);
            return;
        }

        final double acquireSq = followRange() * followRange();
        ServerPlayer best = null;
        double bestDistSq = Double.MAX_VALUE;
        final ServerPlayer current = target();
        if (current != null && isTargetable(current)) {
            final double distSq = distanceSqTo(current);
            if (distSq <= dropRangeSq()) {
                best = current;
                bestDistSq = distSq;
            }
        }
        for (int i = 0, size = players.size(); i < size; i++) {
            final ServerPlayer player = players.get(i);
            if (player == best || !isTargetable(player)) {
                continue;
            }
            final double distSq = distanceSqTo(player);
            if (distSq <= acquireSq && distSq < bestDistSq) {
                bestDistSq = distSq;
                best = player;
            }
        }
        setTarget(best);
    }

    private boolean isTargetable(final ServerPlayer player) {
        if (player.isRemoved() || player.isDead()) {
            return false;
        }
        final GameMode mode = player.gameMode();
        if (mode == GameMode.CREATIVE || mode == GameMode.SPECTATOR) {
            return false;
        }
        return player.world() == world();
    }

    private boolean isValidTarget(final ServerPlayer player, final double maxDistSq) {
        return isTargetable(player) && distanceSqTo(player) <= maxDistSq;
    }

    @Override
    public final Navigation navigation() {
        return navigation;
    }

    private void applyPhysics() {
        final Location current = location();
        final double x = current.x();
        final double y = current.y();
        final double z = current.z();

        navigation.tick(x, z);

        double velocityX = velocityX();
        double velocityY = velocityY();
        double velocityZ = velocityZ();

        float yaw = yaw();
        float pitch = pitch();
        boolean onGround = onGround();

        double inputX = 0.0;
        double inputZ = 0.0;
        final double speed = moveSpeed();
        final var waypoint = navigation.currentWaypoint();
        if (waypoint != null && speed > 0.0) {
            final double dx = waypoint.x() + 0.5 - x;
            final double dz = waypoint.z() + 0.5 - z;
            final double length = Math.sqrt(dx * dx + dz * dz);
            if (length > 1.0E-4) {
                inputX = dx / length * speed;
                inputZ = dz / length * speed;
                yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
                pitch = 0f;
            }
        }

        velocityX = velocityX * GROUND_FRICTION + inputX;
        velocityZ = velocityZ * GROUND_FRICTION + inputZ;
        if (Math.abs(velocityX) < 1.0E-3) velocityX = 0.0;
        if (Math.abs(velocityZ) < 1.0E-3) velocityZ = 0.0;

        velocityY = (velocityY - GRAVITY) * VERTICAL_DRAG;
        velocityY = Math.max(velocityY, -MAX_FALL_SPEED);

        if (velocityY < 0.0 && !onGround) {
            velocityY *= fallDrag();
        }

        double newX = x + velocityX;
        final boolean blockedX = velocityX != 0.0 && isBoxBlocked(newX, y, z);
        if (blockedX) {
            newX = x;
        }

        double newZ = z + velocityZ;
        final boolean blockedZ = velocityZ != 0.0 && isBoxBlocked(newX, y, newZ);
        if (blockedZ) {
            newZ = z;
        }

        if ((blockedX || blockedZ) && onGround && velocityY <= 0.0 && !isBoxBlocked(x, y + 1.0, z)) {
            velocityY = JUMP_VELOCITY;
        }
        if (blockedX) velocityX = 0.0;
        if (blockedZ) velocityZ = 0.0;

        double newY = y + velocityY;
        if (velocityY < 0.0) {
            if (isBoxBlocked(newX, newY, newZ)) {
                newY = Math.floor(newY) + 1.0;
                if (isBoxBlocked(newX, newY, newZ)) {
                    newY = y; // coince : ne pas s'enfoncer
                }
                velocityY = 0.0;
                onGround = true;
            } else {
                onGround = false;
            }
        } else if (velocityY > 0.0) {
            onGround = false;
            if (isBoxBlocked(newX, newY, newZ)) {
                newY = y;
                velocityY = 0.0;
            }
        } else {
            onGround = isBoxBlocked(newX, newY - 0.001, newZ);
        }

        if (onGround) {
            final double stepDx = newX - x;
            final double stepDz = newZ - z;
            stepDistance += Math.sqrt(stepDx * stepDx + stepDz * stepDz);
            if (stepDistance >= STEP_INTERVAL) {
                stepDistance = 0.0;
                onStep();
            }

        }

        setVelocity(velocityX, velocityY, velocityZ);
        setOnGround(onGround);
        setRotation(yaw, pitch);

        if (newX != x || newY != y || newZ != z
                || yaw != current.yaw() || pitch != current.pitch()) {
            setLocation(new Location(newX, newY, newZ, yaw, pitch));
        }
    }

    protected double fallDrag() {
        return 1.0;
    }

    protected void onStep() {
    }
}
