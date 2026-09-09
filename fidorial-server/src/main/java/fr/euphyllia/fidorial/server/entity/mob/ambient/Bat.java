package fr.euphyllia.fidorial.server.entity.mob.ambient;

import fr.euphyllia.fidorial.server.entity.Category;
import fr.euphyllia.fidorial.server.entity.EntityTypes;
import fr.euphyllia.fidorial.server.entity.ai.BlockView;
import fr.euphyllia.fidorial.server.entity.mob.AbstractFlyingMob;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.network.ClientConnection;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundLevelEventPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetEntityMetadataPacket;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.sound.SoundEvents;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.key.Key;
import org.jspecify.annotations.Nullable;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


public final class Bat extends AbstractFlyingMob implements Category.Ambient {

    public static final float MAX_HEALTH = 6f;

    public static final int MAX_SPAWN_LIGHT = 3;

    private static final Set<Key> SPAWNABLE_ON = Set.of(
            Key.key("stone"),
            Key.key("granite"),
            Key.key("diorite"),
            Key.key("andesite"),
            Key.key("tuff"),
            Key.key("deepslate")
    );

    private static final Key LAVA = Key.key("lava");
    private static final Key MAGMA_BLOCK = Key.key("magma_block");
    private static final Key COBWEB = Key.key("cobweb");

    private static final double HITBOX_HEIGHT = 0.9;
    private static final double HITBOX_HALF_WIDTH = 0.25;
    private static final double FLOATING_DRAG = 0.6;

    private static final int MD_SHARED_FLAGS = 0;
    private static final int MD_BAT_FLAGS = 16;
    private static final int FLAG_ON_FIRE = 0x01;
    private static final int FLAG_RESTING = 0x01;

    /**
     * Evenement de monde joue par le client quand une chauve-souris s'envole.
     */
    private static final int LEVEL_EVENT_BAT_TAKEOFF = 1025;

    private static final double WAKE_UP_RADIUS = 4.0;
    private static final double DESPAWN_DISTANCE = 32.0;
    private static final int DESPAWN_CHECK_INTERVAL = 20;

    private static final int ROOST_CHANCE = 100;
    private static final int RETARGET_CHANCE = 30;
    private static final int HEAD_TURN_CHANCE = 200;
    private static final double TARGET_REACHED_DISTANCE = 2.0;

    private static final int WANDER_HORIZONTAL_RANGE = 7;
    private static final int WANDER_VERTICAL_RANGE = 6;
    private static final int WANDER_VERTICAL_OFFSET = 2;

    private static final double STEER_STRENGTH = 0.1;
    private static final double HORIZONTAL_TARGET_SPEED = 0.5;
    private static final double VERTICAL_TARGET_SPEED = 0.7;

    private static final int AMBIENT_SOUND_DELAY = 80;
    private static final int AMBIENT_SOUND_RANGE = 1000;
    private static final int ROOSTING_SILENCE_CHANCE = 4;
    private static final float SOUND_VOLUME = 0.1f;

    private static final int LAVA_FIRE_TICKS = 300;
    private static final int LAVA_DAMAGE_INTERVAL = 10;
    private static final float LAVA_DAMAGE = 4f;
    private static final float FIRE_DAMAGE = 1f;
    private static final int FIRE_DAMAGE_INTERVAL = 20;
    private static final float MAGMA_DAMAGE = 1f;
    private static final int MAGMA_DAMAGE_INTERVAL = 20;

    private static final double COBWEB_HORIZONTAL_DRAG = 0.25;
    private static final double COBWEB_VERTICAL_DRAG = 0.05;

    private boolean resting;
    private boolean persistent;
    private @Nullable BlockPos wanderTarget;
    private int ambientSoundChance;
    private int fireTicks;
    private boolean sentOnFire;
    private boolean metadataSent;

    public Bat(final int entityId, final World world, final Location location) {
        super(entityId, UUID.randomUUID(), EntityTypes.BAT, world, location, MAX_HEALTH);
    }


    public static boolean canSpawn(final ServerWorld world, final int x, final int y, final int z) {
        final BlockState ground = BlockView.blockAt(world, x, y - 1, z);
        if (ground == null || !SPAWNABLE_ON.contains(ground.name())) {
            return false;
        }
        if (!BlockView.isPassable(world, x, y, z) || !BlockView.isPassable(world, x, y + 1, z)) {
            return false;
        }
        if (world.lightLevelAt(x, y, z) > MAX_SPAWN_LIGHT) {
            return false;
        }
        return !isExposedToSky(world, x, y, z);
    }

    private static boolean isExposedToSky(final ServerWorld world, final int x, final int y, final int z) {
        final int top = world.minY() + world.height();
        for (int currentY = y + 1; currentY < top; currentY++) {
            if (!BlockView.isPassable(world, x, currentY, z)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected float voicePitch() {
        return 0.76f + ThreadLocalRandom.current().nextFloat() * 0.38f;
    }

    @Override
    public double height() {
        return HITBOX_HEIGHT;
    }

    @Override
    protected double halfWidth() {
        return HITBOX_HALF_WIDTH;
    }

    @Override
    protected double verticalDrag() {
        return FLOATING_DRAG;
    }

    @Override
    protected boolean isFlightEnabled() {
        return !resting;
    }

    public boolean isResting() {
        return resting;
    }

    @Override
    public void tick(final long currentTick) {
        super.tick(currentTick);
        if (isRemoved() || isDead()) {
            return;
        }

        if (!metadataSent) {
            metadataSent = true;
            sendBatFlags();
        }

        tickAmbientSound();
        tickEnvironment(currentTick);

        if (currentTick % DESPAWN_CHECK_INTERVAL == 0) {
            tickDespawn();
        }
    }

    @Override
    protected void tickFlight(final long currentTick) {
        if (resting) {
            tickResting();
        } else {
            tickFlying();
        }
    }

    private void tickResting() {
        final Location self = location();
        final int blockX = (int) Math.floor(self.x());
        final int blockY = (int) Math.floor(self.y());
        final int blockZ = (int) Math.floor(self.z());

        // TODO : BlockView does not yet distinguish between "solid" blocks and transparent or partial blocks,
        //  onto which the wiki prohibits attaching items.
        if (!BlockView.isSolidGround(serverWorld(), blockX, blockY + 1, blockZ)) {
            setResting(false);
            return;
        }

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        if (random.nextInt(HEAD_TURN_CHANCE) == 0) {
            setRotation(random.nextInt(360), 0f);
        }

        if (nearestPlayer(WAKE_UP_RADIUS) != null) {
            setResting(false);
            return;
        }

        setVelocity(0.0, 0.0, 0.0);
        final double restingY = blockY + 1.0 - height();
        if (self.y() != restingY) {
            setLocation(new Location(self.x(), restingY, self.z(), yaw(), pitch()));
        }
    }

    private void tickFlying() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final Location self = location();

        pickWanderTargetIfNeeded(self, random);

        final BlockPos target = this.wanderTarget;
        if (target == null) {
            return;
        }

        final double dx = target.x() + 0.5 - self.x();
        final double dy = target.y() + 0.1 - self.y();
        final double dz = target.z() + 0.5 - self.z();


        final double newVelocityX = velocityX()
                + (Math.signum(dx) * HORIZONTAL_TARGET_SPEED - velocityX()) * STEER_STRENGTH;
        final double newVelocityY = velocityY()
                + (Math.signum(dy) * VERTICAL_TARGET_SPEED - velocityY()) * STEER_STRENGTH;
        final double newVelocityZ = velocityZ()
                + (Math.signum(dz) * HORIZONTAL_TARGET_SPEED - velocityZ()) * STEER_STRENGTH;
        setVelocity(newVelocityX, newVelocityY, newVelocityZ);

        if (Math.abs(newVelocityX) > 1.0E-4 || Math.abs(newVelocityZ) > 1.0E-4) {
            setRotation((float) Math.toDegrees(Math.atan2(-newVelocityX, newVelocityZ)), 0f);
        }

        applyCobwebSlowdown();

        final int blockX = (int) Math.floor(self.x());
        final int blockY = (int) Math.floor(self.y());
        final int blockZ = (int) Math.floor(self.z());
        if (random.nextInt(ROOST_CHANCE) == 0
                && BlockView.isSolidGround(serverWorld(), blockX, blockY + 1, blockZ)) {
            setResting(true);
        }
    }

    private void pickWanderTargetIfNeeded(final Location self, final ThreadLocalRandom random) {
        final BlockPos current = this.wanderTarget;
        if (current != null
                && (!BlockView.isPassable(serverWorld(), current.x(), current.y(), current.z())
                || current.y() <= serverWorld().minY())) {
            this.wanderTarget = null;
        }

        final BlockPos target = this.wanderTarget;
        if (target != null
                && !isWithin(target, self, TARGET_REACHED_DISTANCE)
                && random.nextInt(RETARGET_CHANCE) != 0) {
            return;
        }

        this.wanderTarget = new BlockPos(
                (int) self.x() + random.nextInt(WANDER_HORIZONTAL_RANGE) - random.nextInt(WANDER_HORIZONTAL_RANGE),
                (int) self.y() + random.nextInt(WANDER_VERTICAL_RANGE) - WANDER_VERTICAL_OFFSET,
                (int) self.z() + random.nextInt(WANDER_HORIZONTAL_RANGE) - random.nextInt(WANDER_HORIZONTAL_RANGE));
    }

    private boolean isWithin(final BlockPos pos, final Location location, final double distance) {
        final double dx = pos.x() + 0.5 - location.x();
        final double dy = pos.y() + 0.5 - location.y();
        final double dz = pos.z() + 0.5 - location.z();
        return dx * dx + dy * dy + dz * dz < distance * distance;
    }

    private void applyCobwebSlowdown() {
        final BlockState state = blockAtFeet();
        if (state != null && state.name().equals(COBWEB)) {
            setVelocity(velocityX() * COBWEB_HORIZONTAL_DRAG,
                    velocityY() * COBWEB_VERTICAL_DRAG,
                    velocityZ() * COBWEB_HORIZONTAL_DRAG);
        }
    }

    private void setResting(final boolean resting) {
        if (this.resting == resting) {
            return;
        }
        this.resting = resting;
        sendBatFlags();

        if (!resting) {
            final Location self = location();
            sendToTrackers(new ClientboundLevelEventPacket(
                    LEVEL_EVENT_BAT_TAKEOFF,
                    new BlockPos((int) Math.floor(self.x()), (int) Math.floor(self.y()), (int) Math.floor(self.z())),
                    0,
                    false));
        }
    }

    private void tickAmbientSound() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        if (random.nextInt(AMBIENT_SOUND_RANGE) >= ambientSoundChance++) {
            return;
        }
        ambientSoundChance = -AMBIENT_SOUND_DELAY;

        if (resting && random.nextInt(ROOSTING_SILENCE_CHANCE) != 0) {
            return;
        }
        playSound(SoundEvents.BAT_DEATH, SOUND_VOLUME, voicePitch());
    }

    private void tickEnvironment(final long currentTick) {
        final BlockState state = blockAtFeet();

        if (state != null && state.name().equals(LAVA)) {
            setResting(false);
            fireTicks = Math.max(fireTicks, LAVA_FIRE_TICKS);
            if (currentTick % LAVA_DAMAGE_INTERVAL == 0) {
                hurt(LAVA_DAMAGE);
            }
        }

        if (onGround() && currentTick % MAGMA_DAMAGE_INTERVAL == 0) {
            final Location self = location();
            final BlockState below = BlockView.blockAt(serverWorld(),
                    (int) Math.floor(self.x()), (int) Math.floor(self.y() - 0.1), (int) Math.floor(self.z()));
            if (below != null && below.name().equals(MAGMA_BLOCK)) {
                hurt(MAGMA_DAMAGE);
            }
        }

        tickFire(currentTick);
    }

    private void tickFire(final long currentTick) {
        if (fireTicks <= 0) {
            if (sentOnFire) {
                setOnFireFlag(false);
            }
            return;
        }
        if (!sentOnFire) {
            setOnFireFlag(true);
        }
        if (currentTick % FIRE_DAMAGE_INTERVAL == 0) {
            hurt(FIRE_DAMAGE);
        }
        fireTicks--;
    }

    public void setPersistent(final boolean persistent) {
        this.persistent = persistent;
    }

    public boolean isPersistent() {
        return persistent;
    }

    private void tickDespawn() {
        if (persistent) {
            return;
        }
        final ServerPlayer nearest = nearestPlayer(-1.0);
        if (nearest == null || distanceSqTo(nearest) > DESPAWN_DISTANCE * DESPAWN_DISTANCE) {
            server().despawnEntity(this);
        }
    }

    private @Nullable BlockState blockAtFeet() {
        final Location self = location();
        return BlockView.blockAt(serverWorld(),
                (int) Math.floor(self.x()), (int) Math.floor(self.y()), (int) Math.floor(self.z()));
    }

    public void hurt(final float amount) {
        if (isRemoved() || isDead() || amount <= 0f) {
            return;
        }
        setResting(false);

        final float remaining = health() - amount;
        if (remaining > 0f) {
            playSound(SoundEvents.BAT_HURT, SOUND_VOLUME, voicePitch());
        }
        setHealth(remaining);
    }

    @Override
    protected void onDeath() {
        playSound(SoundEvents.BAT_DEATH, SOUND_VOLUME, voicePitch());
        // Aucun objet ni experience : la chauve-souris ne laisse rien tomber.
        super.onDeath();
    }

    private void sendBatFlags() {
        sendToTrackers(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofByte(MD_BAT_FLAGS, resting ? FLAG_RESTING : 0)));
    }

    private void setOnFireFlag(final boolean onFire) {
        sentOnFire = onFire;
        sendToTrackers(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofByte(MD_SHARED_FLAGS, onFire ? FLAG_ON_FIRE : 0)));
    }

    @Override
    public void sendSpawnPackets(final ClientConnection connection) {
        super.sendSpawnPackets(connection);
        connection.send(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofByte(MD_BAT_FLAGS, resting ? FLAG_RESTING : 0)));
        if (sentOnFire) {
            connection.send(ClientboundSetEntityMetadataPacket.of(entityId(),
                    ClientboundSetEntityMetadataPacket.Entry.ofByte(MD_SHARED_FLAGS, FLAG_ON_FIRE)));
        }
    }
}
