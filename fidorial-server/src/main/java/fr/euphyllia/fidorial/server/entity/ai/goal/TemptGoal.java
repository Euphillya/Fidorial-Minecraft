package fr.euphyllia.fidorial.server.entity.ai.goal;

import fr.euphyllia.fidorial.server.entity.mob.AbstractPathfinderMob;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.fidorial.entity.GameMode;
import fr.fidorial.entity.ai.Goal;
import fr.fidorial.item.ItemStack;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.Location;
import net.kyori.adventure.key.Key;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Set;

public final class TemptGoal implements Goal {

    private static final int OFFHAND_SLOT = 40;

    private static final int SCAN_INTERVAL_TICKS = 10;
    private static final double REACH_DISTANCE = 2.5;

    private final AbstractPathfinderMob mob;
    private final int priority;
    private final double speed;
    private final Set<Key> temptingItems;
    private final double rangeSq;
    private final double stopRangeSq;

    private @Nullable ServerPlayer tempter;
    private int scanCooldown;


    public TemptGoal(final AbstractPathfinderMob mob, final int priority, final double speed,
                     final Set<Key> temptingItems, final double range, final double stopRange) {
        this.mob = mob;
        this.priority = priority;
        this.speed = speed;
        this.temptingItems = Set.copyOf(temptingItems);
        this.rangeSq = range * range;
        this.stopRangeSq = stopRange * stopRange;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public boolean canStart() {
        if (--scanCooldown > 0) {
            return false;
        }
        scanCooldown = SCAN_INTERVAL_TICKS;
        tempter = findTempter(rangeSq);
        return tempter != null;
    }

    @Override
    public boolean shouldContinue() {
        final ServerPlayer current = tempter;
        if (current == null || !isTempting(current)) {
            return false;
        }
        return mob.distanceSqTo(current) <= stopRangeSq;
    }

    @Override
    public void stop() {
        tempter = null;
        mob.navigation().stop();
    }

    @Override
    public void tick() {
        final ServerPlayer current = tempter;
        if (current == null) {
            return;
        }
        mob.lookAt(current);

        if (mob.distanceSqTo(current) <= REACH_DISTANCE * REACH_DISTANCE) {
            mob.navigation().stop();
            return;
        }

        mob.setMoveSpeed(speed);
        final Location target = current.location();
        mob.navigation().moveTo(mob.location(), new BlockPos(
                (int) Math.floor(target.x()), (int) Math.floor(target.y()), (int) Math.floor(target.z())));
    }

    public @Nullable ServerPlayer tempter() {
        return tempter;
    }

    private @Nullable ServerPlayer findTempter(final double maxDistSq) {
        final List<ServerPlayer> players = mob.server().players();
        ServerPlayer best = null;
        double bestDistSq = Double.MAX_VALUE;
        for (int i = 0, size = players.size(); i < size; i++) {
            final ServerPlayer player = players.get(i);
            if (!isTempting(player)) {
                continue;
            }
            final double distSq = mob.distanceSqTo(player);
            if (distSq <= maxDistSq && distSq < bestDistSq) {
                bestDistSq = distSq;
                best = player;
            }
        }
        return best;
    }

    private boolean isTempting(final ServerPlayer player) {
        if (player.isRemoved() || player.isDead()
                || player.gameMode() == GameMode.SPECTATOR
                || player.world() != mob.world()) {
            return false;
        }
        return holdsTemptingItem(player);
    }

    private boolean holdsTemptingItem(final ServerPlayer player) {
        return isTempting(player.inventory().get(player.selectedSlot()))
                || isTempting(player.inventory().get(OFFHAND_SLOT));
    }

    private boolean isTempting(final @Nullable ItemStack stack) {
        return stack != null && !stack.isEmpty() && temptingItems.contains(stack.id());
    }


}
