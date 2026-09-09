package fr.euphyllia.fidorial.server.entity.ai.goal;

import fr.euphyllia.fidorial.server.entity.AbstractEntity;
import fr.euphyllia.fidorial.server.entity.mob.AbstractAgeableMob;
import fr.fidorial.entity.ai.Goal;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.Location;
import org.jspecify.annotations.Nullable;

public final class FollowParentGoal implements Goal {

    private static final double SEARCH_RADIUS = 16.0;
    private static final double ABANDON_DISTANCE = 24.0;
    private static final double REACH_DISTANCE = 3.0;
    private static final int SCAN_INTERVAL_TICKS = 20;

    private final AbstractAgeableMob mob;
    private final int priority;
    private final double speed;

    private @Nullable AbstractAgeableMob parent;
    private int scanCooldown;

    public FollowParentGoal(final AbstractAgeableMob mob, final int priority, final double speed) {
        this.mob = mob;
        this.priority = priority;
        this.speed = speed;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public boolean canStart() {
        if (!mob.isBaby()) {
            return false;
        }
        if (--scanCooldown > 0) {
            return false;
        }
        scanCooldown = SCAN_INTERVAL_TICKS;

        final AbstractAgeableMob candidate = findNearestAdult();
        if (candidate == null || distanceTo(candidate) < REACH_DISTANCE) {
            return false;
        }

        parent = candidate;
        return true;
    }

    @Override
    public boolean shouldContinue() {
        final AbstractAgeableMob current = parent;
        if (!mob.isBaby() || current == null || current.isRemoved() || current.isDead()) {
            return false;
        }
        final double distance = distanceTo(current);

        return distance >= REACH_DISTANCE && distance <= ABANDON_DISTANCE;
    }

    @Override
    public void stop() {
        parent = null;
        mob.navigation().stop();
    }

    @Override
    public void tick() {
        final AbstractAgeableMob current = parent;
        if (current == null) {
            return;
        }

        mob.setMoveSpeed(speed);
        final Location target = current.location();
        mob.navigation().moveTo(mob.location(), new BlockPos(
                (int) Math.floor(target.x()), (int) Math.floor(target.y()), (int) Math.floor(target.z())));
    }

    private double distanceTo(final AbstractAgeableMob other) {
        final Location self = mob.location();
        final Location pos = other.location();
        final double dx = self.x() - pos.x();
        final double dy = self.y() - pos.y();
        final double dz = self.z() - pos.z();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    private AbstractAgeableMob findNearestAdult() {
        final Location self = mob.location();
        final AbstractAgeableMob[] best = new AbstractAgeableMob[1];
        final double[] bestDistSq = {Double.MAX_VALUE};

        mob.serverWorld().entityManager().forEachNear(self.chunk(), SEARCH_RADIUS, (final AbstractEntity entity) -> {
            if (!(entity instanceof final AbstractAgeableMob other)
                    || other == mob
                    || other.isBaby()
                    || other.isRemoved()
                    || other.isDead()
                    || !other.type().equals(mob.type())) {
                return;
            }
            final Location pos = other.location();
            final double dx = self.x() - pos.x();
            final double dy = self.y() - pos.y();
            final double dz = self.z() - pos.z();
            final double distSq = dx * dx + dy * dy + dz * dz;
            if (distSq < bestDistSq[0]) {
                bestDistSq[0] = distSq;
                best[0] = other;
            }
        });

        return best[0];
    }
}
