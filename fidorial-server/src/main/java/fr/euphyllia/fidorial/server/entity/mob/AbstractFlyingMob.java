package fr.euphyllia.fidorial.server.entity.mob;

import fr.fidorial.entity.EntityType;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;

import java.util.UUID;


public abstract class AbstractFlyingMob extends AbstractMovingMob {

    private static final double HORIZONTAL_DRAG = 0.91;
    private static final double VERTICAL_DRAG = 0.91;
    private static final double MIN_VELOCITY = 1.0E-3;
    private static final double VOID_MARGIN = 64.0;

    protected AbstractFlyingMob(final int entityId, final UUID uuid, final EntityType type, final World world,
                                final Location location, final float maxHealth) {
        super(entityId, uuid, type, world, location, maxHealth);
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
            final Location falling = location();
            applyFlightPhysics();
            updateChunkMembership(falling, location());
            syncToClients();
            return;
        }

        goals.tick();
        tickBehaviours(currentTick);
        tickFlight(currentTick);

        final Location before = location();
        applyFlightPhysics();
        final Location after = location();

        updateChunkMembership(before, after);
        syncToClients();

        if (after.y() < serverWorld().minY() - VOID_MARGIN) {
            server().despawnEntity(this);
        }
    }

    @Override
    protected void onDeath() {
        goals.stopAll();
        super.onDeath();
    }

    @Override
    protected void onDeathAnimationFinished() {
        server().despawnEntity(this);
    }

    protected void tickFlight(final long currentTick) {
    }

    protected boolean isFlightEnabled() {
        return true;
    }

    protected double horizontalDrag() {
        return HORIZONTAL_DRAG;
    }

    protected double verticalDrag() {
        return VERTICAL_DRAG;
    }

    protected double gravity() {
        return 0.0;
    }

    private void applyFlightPhysics() {
        if (!isFlightEnabled()) {
            setVelocity(0.0, 0.0, 0.0);
            return;
        }

        final Location current = location();
        final double x = current.x();
        final double y = current.y();
        final double z = current.z();

        double velocityX = velocityX();
        double velocityY = velocityY() - gravity();
        double velocityZ = velocityZ();

        double newX = x + velocityX;
        if (velocityX != 0.0 && isBoxBlocked(newX, y, z)) {
            newX = x;
            velocityX = 0.0;
        }

        double newZ = z + velocityZ;
        if (velocityZ != 0.0 && isBoxBlocked(newX, y, newZ)) {
            newZ = z;
            velocityZ = 0.0;
        }

        double newY = y + velocityY;
        if (velocityY != 0.0 && isBoxBlocked(newX, newY, newZ)) {
            newY = y;
            velocityY = 0.0;
        }

        setOnGround(isBoxBlocked(newX, newY - 0.001, newZ));

        velocityX *= horizontalDrag();
        velocityZ *= horizontalDrag();
        velocityY *= verticalDrag();
        if (Math.abs(velocityX) < MIN_VELOCITY) velocityX = 0.0;
        if (Math.abs(velocityY) < MIN_VELOCITY) velocityY = 0.0;
        if (Math.abs(velocityZ) < MIN_VELOCITY) velocityZ = 0.0;

        setVelocity(velocityX, velocityY, velocityZ);

        if (newX != x || newY != y || newZ != z
                || yaw() != current.yaw() || pitch() != current.pitch()) {
            setLocation(new Location(newX, newY, newZ, yaw(), pitch()));
        }
    }
}
