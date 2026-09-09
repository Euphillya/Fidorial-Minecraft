package fr.euphyllia.fidorial.server.entity.mob.monster;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.entity.Category;
import fr.euphyllia.fidorial.server.entity.EntityTypes;
import fr.euphyllia.fidorial.server.entity.ai.BlockView;
import fr.euphyllia.fidorial.server.entity.ai.goal.BreakDoorGoal;
import fr.euphyllia.fidorial.server.entity.ai.goal.LookAtTargetGoal;
import fr.euphyllia.fidorial.server.entity.ai.goal.MeleeAttackGoal;
import fr.euphyllia.fidorial.server.entity.ai.goal.RandomStrollGoal;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMob;
import fr.euphyllia.fidorial.server.entity.mob.AbstractPathfinderMob;
import fr.euphyllia.fidorial.server.entity.mob.MobFactories;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.network.ClientConnection;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetEntityMetadataPacket;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.euphyllia.fidorial.server.world.storage.LevelData;
import fr.fidorial.combat.DamageSource;
import fr.fidorial.entity.EntityType;
import fr.fidorial.sound.SoundEvents;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.sound.Sound;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Zombie extends AbstractPathfinderMob implements Category.Monster {

    public static final float MAX_HEALTH = 20f;

    private static final double BASE_FOLLOW_RANGE = 35.0;

    private static final double ADULT_SPEED = 0.23;
    private static final double BABY_SPEED = 0.35;

    private static final double ADULT_HEIGHT = 1.95;
    private static final double BABY_HEIGHT = 0.98;
    private static final double ADULT_WIDTH = 0.6;
    private static final double BABY_WIDTH = 0.49;

    private static final float ARMOR_POINTS = 2f;

    private static final double BABY_CHANCE = 0.05;
    private static final double LEADER_CHANCE = 0.05;
    private static final double DOOR_BREAKER_CHANCE = 0.10;
    private static final float LEADER_BONUS_HEALTH_MIN = 20f;
    private static final float LEADER_BONUS_HEALTH_MAX = 80f;

    private static final double REINFORCEMENT_CHANCE_MAX = 0.10;

    private static final double LEADER_REINFORCEMENT_BONUS_MIN = 0.50;
    private static final double LEADER_REINFORCEMENT_BONUS_MAX = 0.75;

    private static final double REINFORCEMENT_DECAY = 0.05;

    private static final int REINFORCEMENT_ATTEMPTS = 50;
    private static final int REINFORCEMENT_MIN_OFFSET = 7;
    private static final int REINFORCEMENT_MAX_OFFSET = 40;
    private static final double REINFORCEMENT_PLAYER_CLEARANCE = 7.0;

    private static final double HELP_CALL_RADIUS = 33.5;
    private static final double HELP_CALL_HEIGHT = 10.0;
    private static final int HELP_CALL_CHUNK_RADIUS = 3;

    private static final long BURN_START_TICK = 23_460L;
    private static final long BURN_END_TICK = 12_010L;
    private static final int SUN_CHECK_INTERVAL = 20;
    private static final int FIRE_TICKS_ON_IGNITE = 160;
    private static final float BURN_DAMAGE = 1.0f;

    private static final int WATER_TICKS_BEFORE_CONVERSION = 600;

    private static final int DROWNED_CONVERSION_TICKS = 300;

    private static final int AMBIENT_CHANCE = 80;

    private static final int MD_SHARED_FLAGS = 0;
    private static final int MD_BABY = 16;
    private static final int MD_CONVERTING_TO_DROWNED = 18;
    private static final int FLAG_ON_FIRE = 0x01;

    private final boolean baby;
    private final boolean leader;
    private final boolean canBreakDoors;
    private final double knockbackResistance;
    private final double followRange;

    private double reinforcementChance;

    private int fireTicks;
    private int inWaterTicks = -1;
    private int drownedConversionTicks = -1;
    private boolean metadataSent;
    private boolean sentOnFire;

    public Zombie(final int entityId, final World world, final Location location) {
        this(entityId, EntityTypes.ZOMBIE, world, location, SpawnData.roll());
    }

    protected Zombie(final int entityId, final EntityType type, final World world,
                     final Location location, final SpawnData data) {
        super(entityId, UUID.randomUUID(), type, world, location, data.maxHealth());

        this.baby = data.baby();
        this.leader = data.leader();
        this.canBreakDoors = data.canBreakDoors();
        this.knockbackResistance = data.knockbackResistance();
        this.followRange = data.followRange();
        this.reinforcementChance = data.reinforcementChance();

        if (canBreakDoors) {
            goals.add(new BreakDoorGoal(this, 0));
        }
        goals.add(new MeleeAttackGoal(this, 1, movementSpeed(), width(), this::attack));
        goals.add(new RandomStrollGoal(this, 2, movementSpeed() * 0.6));
        goals.add(new LookAtTargetGoal(this, 3, 8.0));
    }

    private static int randomOffset(final ThreadLocalRandom random) {
        if (random.nextBoolean()) {
            return 0;
        }
        final int magnitude = random.nextInt(REINFORCEMENT_MIN_OFFSET, REINFORCEMENT_MAX_OFFSET + 1);
        return random.nextBoolean() ? magnitude : -magnitude;
    }

    public final boolean isBaby() {
        return baby;
    }

    public final boolean isLeader() {
        return leader;
    }

    public final boolean canBreakDoors() {
        return canBreakDoors;
    }

    @Override
    public double armor() {
        return ARMOR_POINTS;
    }

    @Override
    public double knockbackResistance() {
        return knockbackResistance;
    }

    @Override
    protected Sound.Type hurtSound() {
        return SoundEvents.ZOMBIE_HURT;
    }

    @Override
    protected Sound.Type deathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    @Override
    public double movementSpeed() {
        return baby ? BABY_SPEED : ADULT_SPEED;
    }

    @Override
    public double width() {
        return baby ? BABY_WIDTH : ADULT_WIDTH;
    }

    @Override
    public double height() {
        return baby ? BABY_HEIGHT : ADULT_HEIGHT;
    }

    @Override
    protected double defaultFollowRange() {
        return followRange;
    }

    @Override
    public float attackDamage() {
        return switch (difficulty()) {
            case 0 -> 0f;
            case 1 -> 2.5f;
            case 3 -> 4.5f;
            default -> 3f;
        };
    }

    @Override
    public void tick(final long currentTick) {
        super.tick(currentTick);
        if (isRemoved() || isDead()) {
            return;
        }

        if (!metadataSent) {
            metadataSent = true;
            sendBaseMetadata();
        }

        tickAmbientSound();
        tickSunlight(currentTick);
        tickDrowning();
        tickFire();
    }

    private void tickAmbientSound() {
        if (ThreadLocalRandom.current().nextInt(AMBIENT_CHANCE) == 0) {
            playSound(SoundEvents.ZOMBIE_AMBIENT, 1.0f, voicePitch());
        }
    }

    private void tickSunlight(final long currentTick) {
        if (currentTick % SUN_CHECK_INTERVAL != 0 || drownedConversionTicks >= 0) {
            return;
        }
        if (!isDaylight() || isHeadInWater() || !canSeeSky()) {
            return;
        }
        fireTicks = Math.max(fireTicks, FIRE_TICKS_ON_IGNITE);
    }

    private void tickFire() {
        if (fireTicks <= 0) {
            if (sentOnFire) {
                setOnFireFlag(false);
            }
            return;
        }
        if (!sentOnFire) {
            setOnFireFlag(true);
        }
        if (isHeadInWater()) {
            fireTicks = 0;
            setOnFireFlag(false);
            return;
        }
        if (fireTicks % 20 == 0) {
            damage(DamageSource.onFire(), BURN_DAMAGE);
        }
        fireTicks--;
    }

    private void tickDrowning() {
        if (drownedConversionTicks >= 0) {
            if (--drownedConversionTicks <= 0) {
                convertToDrowned();
            }
            return;
        }

        if (isHeadInWater()) {
            inWaterTicks++;
            if (inWaterTicks >= WATER_TICKS_BEFORE_CONVERSION) {
                startDrownedConversion();
            }
        } else {
            inWaterTicks = -1;
        }
    }

    private void startDrownedConversion() {
        drownedConversionTicks = DROWNED_CONVERSION_TICKS;
        inWaterTicks = -1;
        fireTicks = 0;
        sendToTrackers(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofBoolean(MD_CONVERTING_TO_DROWNED, true)));
        playSound(SoundEvents.ZOMBIE_CONVERTED_TO_DROWNED, 2.0f, voicePitch());
    }

    protected void convertToDrowned() {
        final Location loc = location();
        final AbstractMob drowned = MobFactories.create(EntityTypes.DROWNED, server().entityIds().allocate(),
                world(), loc);
        server().despawnEntity(this);
        server().spawnEntity(drowned);
    }

    protected void attack(final ServerPlayer target) {
        final float damage = attackDamage();
        if (damage <= 0f) {
            return;
        }
        server().combat().damage(target, DamageSource.mobAttack(this), damage);

        if (fireTicks > 0
                && ThreadLocalRandom.current().nextDouble() < 0.30 * clampedRegionalDifficulty()) {
            // TODO : Apply the fire effect to the player
            playSound(SoundEvents.GENERIC_BURN, 1.0f, 1.0f);
        }
    }

    @Override
    public void onHurt(final DamageSource source, final float amount) {
        if (!(source.causingEntity() instanceof final ServerPlayer attacker)) {
            return;
        }
        callForHelp(attacker);
        if (difficulty() == 3) {
            trySpawnReinforcement(attacker);
        }
    }

    private void callForHelp(final ServerPlayer attacker) {
        final Location self = location();
        final ChunkPos center = self.chunk();
        serverWorld().entityManager().forEachInChunkRange(center.x(), center.z(), HELP_CALL_CHUNK_RADIUS, entity -> {
            if (!(entity instanceof final Zombie other) || other == this
                    || other.isRemoved() || other.isDead()) {
                return;
            }
            if (other.type() != this.type()) {
                return;
            }
            final Location pos = other.location();
            if (Math.abs(pos.x() - self.x()) > HELP_CALL_RADIUS
                    || Math.abs(pos.z() - self.z()) > HELP_CALL_RADIUS
                    || Math.abs(pos.y() - self.y()) > HELP_CALL_HEIGHT) {
                return;
            }
            other.setTarget(attacker);
        });
    }

    private void trySpawnReinforcement(final ServerPlayer attacker) {
        if (reinforcementChance <= 0.0
                || ThreadLocalRandom.current().nextDouble() >= reinforcementChance) {
            return;
        }

        final ServerWorld world = serverWorld();
        final Location self = location();
        final ThreadLocalRandom random = ThreadLocalRandom.current();

        final List<ServerPlayer> players = server().players();

        for (int attempt = 0; attempt < REINFORCEMENT_ATTEMPTS; attempt++) {
            final int x = (int) Math.floor(self.x()) + randomOffset(random);
            final int y = (int) Math.floor(self.y()) + randomOffset(random);
            final int z = (int) Math.floor(self.z()) + randomOffset(random);

            if (!isValidReinforcementSpot(world, players, x, y, z)) {
                continue;
            }

            final Zombie reinforcement = new Zombie(server().entityIds().allocate(), world,
                    new Location(x, y, z, 0f, 0f));
            reinforcement.reinforcementChance =
                    Math.max(0.0, reinforcement.reinforcementChance - REINFORCEMENT_DECAY);
            reinforcement.setTarget(attacker);
            server().spawnEntity(reinforcement);

            this.reinforcementChance = Math.max(0.0, this.reinforcementChance - REINFORCEMENT_DECAY);
            return;
        }
    }

    private boolean isValidReinforcementSpot(final ServerWorld world, final List<ServerPlayer> players,
                                             final int x, final int y, final int z) {
        if (!BlockView.isSolidGround(world, x, y - 1, z)) {
            return false;
        }
        if (!BlockView.isPassable(world, x, y, z) || !BlockView.isPassable(world, x, y + 1, z)) {
            return false;
        }
        for (int i = 0, size = players.size(); i < size; i++) {
            final ServerPlayer player = players.get(i);
            if (player.world() != world) {
                continue;
            }
            final Location pos = player.location();
            final double dx = pos.x() - x;
            final double dy = pos.y() - y;
            final double dz = pos.z() - z;
            if (dx * dx + dy * dy + dz * dz
                    < REINFORCEMENT_PLAYER_CLEARANCE * REINFORCEMENT_PLAYER_CLEARANCE) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onDeath() {
        // TODO: drops (rotten flesh 0-2, iron ingot / carrot / potato 0.83%)
        //       and experience (5 adult, 12 baby)
        super.onDeath();
    }

    @Override
    protected void onStep() {
        playSound(SoundEvents.ZOMBIE_STEP, 0.15f, 1.0f);
    }

    private LevelData levelData() {
        return server().worldManager().levelData();
    }

    protected int difficulty() {
        return levelData().difficulty;
    }

    protected double clampedRegionalDifficulty() {
        final int difficulty = difficulty();
        if (difficulty <= 0) {
            return 0.0;
        }
        final long worldTime = levelData().time;
        final double ageFactor = Math.min(worldTime / 3_600_000.0, 1.0);
        return Math.clamp(difficulty / 3.0 * (0.75 + ageFactor * 0.25), 0.0, 1.0);
    }

    private boolean isDaylight() {
        final int dayTime = world().dayNightCycle().timeOfDay();
        return dayTime < BURN_END_TICK || dayTime > BURN_START_TICK;
    }

    private boolean isHeadInWater() {
        final Location loc = location();
        final BlockState state = BlockView.blockAt(serverWorld(),
                (int) Math.floor(loc.x()),
                (int) Math.floor(loc.y() + height() * 0.85),
                (int) Math.floor(loc.z()));
        return state != null && state.name().equals("minecraft:water");
    }

    private boolean canSeeSky() {
        final ServerWorld world = serverWorld();
        final Location loc = location();
        final int x = (int) Math.floor(loc.x());
        final int z = (int) Math.floor(loc.z());
        final int from = (int) Math.floor(loc.y() + height());
        final int top = world.minY() + world.height();

        for (int y = from; y < top; y++) {
            if (!BlockView.isPassable(world, x, y, z)) {
                return false;
            }
        }
        return true;
    }

    private void sendBaseMetadata() {
        sendToTrackers(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofBoolean(MD_BABY, baby)));
    }

    private void setOnFireFlag(final boolean onFire) {
        sentOnFire = onFire;
        sendToTrackers(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofByte(MD_SHARED_FLAGS,
                        onFire ? FLAG_ON_FIRE : 0)));
    }

    @Override
    public void sendSpawnPackets(final ClientConnection connection) {
        super.sendSpawnPackets(connection);
        connection.send(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofBoolean(MD_BABY, baby)));
        if (sentOnFire) {
            connection.send(ClientboundSetEntityMetadataPacket.of(entityId(),
                    ClientboundSetEntityMetadataPacket.Entry.ofByte(MD_SHARED_FLAGS, FLAG_ON_FIRE)));
        }
        if (drownedConversionTicks >= 0) {
            connection.send(ClientboundSetEntityMetadataPacket.of(entityId(),
                    ClientboundSetEntityMetadataPacket.Entry.ofBoolean(MD_CONVERTING_TO_DROWNED, true)));
        }
    }

    @Override
    protected float voicePitch() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return baby
                ? 1.3f + random.nextFloat() * 0.4f
                : 0.8f + random.nextFloat() * 0.4f;
    }

    protected record SpawnData(boolean baby, boolean leader, boolean canBreakDoors,
                               float maxHealth, double knockbackResistance,
                               double followRange, double reinforcementChance) {

        public static SpawnData roll() {
            final ThreadLocalRandom random = ThreadLocalRandom.current();
            final int difficulty = FidorialDifficulty.current();

            final boolean baby = random.nextDouble() < BABY_CHANCE;

            final boolean leader = difficulty >= 2 && random.nextDouble() < LEADER_CHANCE;

            final boolean canBreakDoors = difficulty == 3 && random.nextDouble() < DOOR_BREAKER_CHANCE;

            float maxHealth = MAX_HEALTH;
            double reinforcementChance = random.nextDouble() * REINFORCEMENT_CHANCE_MAX;
            if (leader) {
                maxHealth += LEADER_BONUS_HEALTH_MIN
                        + random.nextFloat() * (LEADER_BONUS_HEALTH_MAX - LEADER_BONUS_HEALTH_MIN);
                reinforcementChance += LEADER_REINFORCEMENT_BONUS_MIN
                        + random.nextDouble()
                        * (LEADER_REINFORCEMENT_BONUS_MAX - LEADER_REINFORCEMENT_BONUS_MIN);
            }

            final double knockbackResistance = random.nextDouble() * 0.05;

            final double followRange = BASE_FOLLOW_RANGE * (0.95 + random.nextDouble() * 0.10);

            return new SpawnData(baby, leader, canBreakDoors, maxHealth,
                    knockbackResistance, followRange, reinforcementChance);
        }
    }

    private static final class FidorialDifficulty {
        private FidorialDifficulty() {
        }

        static int current() {
            return FidorialServer.getInstance()
                    .worldManager().levelData().difficulty;
        }
    }
}
