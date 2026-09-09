package fr.euphyllia.fidorial.server.entity.ai.goal;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.entity.ai.BlockView;
import fr.euphyllia.fidorial.server.entity.mob.AbstractPathfinderMob;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundBlockDestructionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundLevelEventPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSoundPacket;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.entity.ai.Goal;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.sound.SoundEvents;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.Location;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public final class BreakDoorGoal implements Goal {

    private static final int BREAK_TICKS = 240;

    private static final int SHAKE_INTERVAL = 20;

    private static final int SEARCH_RADIUS = 2;

    private static final int NO_DESTROY_STAGE = -1;

    private static final int DESTROY_STAGES = 10;

    private final AbstractPathfinderMob mob;
    private final int priority;

    private @Nullable BlockPos door;
    private int progress;
    private int sentStage = NO_DESTROY_STAGE;

    public BreakDoorGoal(final AbstractPathfinderMob mob, final int priority) {
        this.mob = mob;
        this.priority = priority;
    }

    public static boolean isBreakableDoor(final BlockState state) {
        final Key name = state.name();
        return name.asString().endsWith("_door") && !name.equals(Key.key("iron_door"));
    }

    private static boolean isClosed(final BlockState state) {
        return !"true".equals(state.properties().get("open"));
    }

    private static boolean isUpperHalf(final BlockState state) {
        return "upper".equals(state.properties().get("half"));
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public boolean canStart() {
        if (mob.target() == null) {
            return false;
        }
        final BlockPos found = findDoor();
        if (found == null) {
            return false;
        }
        this.door = found;
        return true;
    }

    @Override
    public boolean shouldContinue() {
        final BlockPos pos = this.door;
        if (mob.target() == null || pos == null || !(mob.world() instanceof final ServerWorld world)) {
            return false;
        }
        final BlockState state = BlockView.blockAt(world, pos.x(), pos.y(), pos.z());
        return state != null && isBreakableDoor(state) && isClosed(state) && progress < BREAK_TICKS;
    }

    @Override
    public void start() {
        progress = 0;
        sentStage = NO_DESTROY_STAGE;
    }

    @Override
    public void stop() {
        clearDestroyStage();
        door = null;
        progress = 0;
        mob.navigation().stop();
    }

    @Override
    public void tick() {
        final BlockPos target = this.door;
        if (target == null) {
            return;
        }

        mob.navigation().stop();
        mob.setMoveSpeed(0.0);
        mob.lookAt(target.x() + 0.5, target.y() + 0.5, target.z() + 0.5);

        progress++;

        if (progress % SHAKE_INTERVAL == 0) {
            playSound(SoundEvents.ZOMBIE_ATTACK_WOODEN_DOOR, 2.0f);
        }

        final int stage = Math.min(DESTROY_STAGES - 1, progress * DESTROY_STAGES / BREAK_TICKS);
        if (stage != sentStage) {
            sendDestroyStage(target, stage);
        }

        if (progress >= BREAK_TICKS) {
            breakDoor(target);
        }
    }

    private void breakDoor(final BlockPos pos) {
        if (!(mob.world() instanceof final ServerWorld world)) {
            return;
        }
        final FidorialServer server = FidorialServer.getInstance();
        final BlockState state = BlockView.blockAt(world, pos.x(), pos.y(), pos.z());
        if (state == null) {
            return;
        }

        clearDestroyStage();

        final BlockPos other = isUpperHalf(state) ? pos.offset(0, -1, 0) : pos.offset(0, 1, 0);
        server.blockEdits().set(world, pos, BlockState.of(BlockTypeKeys.AIR.key()));
        server.blockEdits().set(world, other, BlockState.of(BlockTypeKeys.AIR.key()));

        server.broadcastNear(world, pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5,
                new ClientboundLevelEventPacket(ClientboundLevelEventPacket.BLOCK_BREAK, pos, 0, false));
        playSound(SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, 2.0f);

        door = null;
        progress = 0;
    }

    /**
     * Cherche la moitie haute d'une porte fermee a portee. On ignore volontairement la
     * moitie basse : un zombie qui fait face au bas d'une porte ne peut pas la casser.
     */
    private @Nullable BlockPos findDoor() {
        if (!(mob.world() instanceof final ServerWorld world)) {
            return null;
        }
        final Location loc = mob.location();
        final int baseX = (int) Math.floor(loc.x());
        final int baseY = (int) Math.floor(loc.y());
        final int baseZ = (int) Math.floor(loc.z());

        BlockPos best = null;
        double bestDistSq = Double.MAX_VALUE;

        for (int dx = -SEARCH_RADIUS; dx <= SEARCH_RADIUS; dx++) {
            for (int dz = -SEARCH_RADIUS; dz <= SEARCH_RADIUS; dz++) {
                for (int dy = 0; dy <= 2; dy++) {
                    final int x = baseX + dx;
                    final int y = baseY + dy;
                    final int z = baseZ + dz;
                    final BlockState state = BlockView.blockAt(world, x, y, z);
                    if (state == null || !isBreakableDoor(state)
                            || !isClosed(state) || !isUpperHalf(state)) {
                        continue;
                    }
                    final double distSq = dx * dx + dy * dy + dz * dz;
                    if (distSq < bestDistSq) {
                        bestDistSq = distSq;
                        best = new BlockPos(x, y, z);
                    }
                }
            }
        }
        return best;
    }

    private void sendDestroyStage(final BlockPos pos, final int stage) {
        sentStage = stage;
        mob.sendToTrackers(new ClientboundBlockDestructionPacket(mob.entityId(), pos, stage));
    }

    private void clearDestroyStage() {
        final BlockPos pos = this.door;
        final boolean needsClear = pos != null && sentStage != NO_DESTROY_STAGE;
        sentStage = NO_DESTROY_STAGE;
        if (needsClear) {
            mob.sendToTrackers(new ClientboundBlockDestructionPacket(mob.entityId(), pos, NO_DESTROY_STAGE));
        }
    }

    private void playSound(final Sound.Type type, final float volume) {
        final BlockPos pos = this.door;
        if (pos == null) {
            return;
        }
        final float pitch = 0.8f + ThreadLocalRandom.current().nextFloat() * 0.4f;
        mob.sendToTrackers(new ClientboundSoundPacket(
                Sound.sound(type, Sound.Source.HOSTILE, volume, pitch),
                pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5));
    }
}
