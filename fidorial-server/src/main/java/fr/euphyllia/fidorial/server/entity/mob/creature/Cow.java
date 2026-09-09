package fr.euphyllia.fidorial.server.entity.mob.creature;

import fr.euphyllia.fidorial.server.entity.Category;
import fr.euphyllia.fidorial.server.entity.EntityTypes;
import fr.euphyllia.fidorial.server.entity.ai.BlockView;
import fr.euphyllia.fidorial.server.entity.ai.PathPenalty;
import fr.euphyllia.fidorial.server.entity.ai.goal.FollowParentGoal;
import fr.euphyllia.fidorial.server.entity.ai.goal.PanicGoal;
import fr.euphyllia.fidorial.server.entity.ai.goal.RandomStrollGoal;
import fr.euphyllia.fidorial.server.entity.ai.goal.TemptGoal;
import fr.euphyllia.fidorial.server.entity.mob.AbstractAgeableMob;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.combat.DamageSource;
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.CowSoundVariant;
import fr.fidorial.registry.data.CowVariant;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.registry.keys.CowSoundVariantKeys;
import fr.fidorial.registry.keys.CowVariantKeys;
import fr.fidorial.registry.keys.ItemKeys;
import fr.fidorial.sound.SoundEvents;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.jspecify.annotations.Nullable;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


public final class Cow extends AbstractAgeableMob implements Category.Neutral {

    public static final float MAX_HEALTH = 10f;
    public static final double BABY_SPAWN_CHANCE = 0.05;
    public static final int MIN_SPAWN_LIGHT = 9;

    private static final double ADULT_HEIGHT = 1.4;
    private static final double ADULT_WIDTH = 0.9;
    private static final double BABY_HEIGHT = 0.7;
    private static final double BABY_WIDTH = 0.45;

    private static final double MOVEMENT_SPEED = 0.2;
    private static final double PANIC_SPEED = MOVEMENT_SPEED * 2.0;
    private static final double TEMPT_SPEED = MOVEMENT_SPEED * 1.25;
    private static final double FOLLOW_PARENT_SPEED = MOVEMENT_SPEED * 1.1;
    private static final double STROLL_SPEED = MOVEMENT_SPEED * 0.8;

    private static final double TEMPT_RANGE = 6.0;
    private static final double TEMPT_STOP_RANGE = 10.0;

    private static final int AMBIENT_SOUND_DELAY = 80;
    private static final int AMBIENT_SOUND_RANGE = 1000;
    private static final float SOUND_VOLUME = 0.4f;
    private static final float STEP_VOLUME = 0.15f;
    private static final float PITCH_MIN = 0.8f;
    private static final float PITCH_MAX = 1.2f;
    private static final float BABY_PITCH_BONUS = 0.5f;

    private static final Set<Key> WARM_BIOMES = Set.of(
            Key.key("savanna"),
            Key.key("savanna_plateau"),
            Key.key("windswept_savanna"),
            Key.key("jungle"),
            Key.key("sparse_jungle"),
            Key.key("bamboo_jungle"),
            Key.key("badlands"),
            Key.key("eroded_badlands"),
            Key.key("wooded_badlands"));

    private static final Set<Key> COLD_BIOMES = Set.of(
            Key.key("taiga"),
            Key.key("snowy_taiga"),
            Key.key("old_growth_pine_taiga"),
            Key.key("old_growth_spruce_taiga"),
            Key.key("windswept_hills"),
            Key.key("windswept_gravelly_hills"),
            Key.key("windswept_forest"),
            Key.key("dappled_forest"));


    private static final Set<Key> EXCLUDED_SPAWN_BIOMES = Set.of(
            Key.key("snowy_plains"),
            Key.key("meadow"),
            Key.key("cherry_grove"),
            Key.key("mangrove_swamp"),
            Key.key("pale_garden"));

    private final PanicGoal panicGoal;

    private TypedKey<CowVariant> variant;
    private TypedKey<CowSoundVariant> soundVariant;
    private int ambientSoundChance;


    public Cow(final int entityId, final World world, final Location location) {
        this(entityId, world, location, ThreadLocalRandom.current().nextDouble() < BABY_SPAWN_CHANCE);
    }

    public Cow(final int entityId, final World world, final Location location, final boolean baby) {
        super(entityId, UUID.randomUUID(), EntityTypes.COW, world, location, MAX_HEALTH, baby);

        this.soundVariant = ThreadLocalRandom.current().nextBoolean()
                ? CowSoundVariantKeys.CLASSIC
                : CowSoundVariantKeys.MOODY;
        this.variant = variantForBiome(BlockView.biomeAt(serverWorld(),
                (int) Math.floor(location.x()), (int) Math.floor(location.y()), (int) Math.floor(location.z())));
        navigation.setPathPenalty(PathPenalty.LAND_ANIMAL);

        this.panicGoal = new PanicGoal(this, 0, PANIC_SPEED);
        goals.add(panicGoal);
        goals.add(new FollowParentGoal(this, 1, FOLLOW_PARENT_SPEED));
        goals.add(new TemptGoal(this, 2, TEMPT_SPEED, Set.of(ItemKeys.WHEAT.key()), TEMPT_RANGE, TEMPT_STOP_RANGE));
        goals.add(new RandomStrollGoal(this, 3, STROLL_SPEED));
    }

    public static boolean canSpawn(final ServerWorld world, final int x, final int y, final int z) {
        final BlockState ground = BlockView.blockAt(world, x, y - 1, z);
        if (ground == null || !ground.name().equals(BlockTypeKeys.GRASS_BLOCK.key())) {
            return false;
        }
        if (!BlockView.isPassable(world, x, y, z) || !BlockView.isPassable(world, x, y + 1, z)) {
            return false;
        }
        if (world.lightLevelAt(x, y, z) < MIN_SPAWN_LIGHT) {
            return false;
        }
        final Key biome = BlockView.biomeAt(world, x, y, z);
        return biome == null || !EXCLUDED_SPAWN_BIOMES.contains(biome);
    }

    public static TypedKey<CowVariant> variantForBiome(final @Nullable Key biome) {
        if (biome == null) {
            return CowVariantKeys.TEMPERATE;
        }
        if (WARM_BIOMES.contains(biome)) {
            return CowVariantKeys.WARM;
        }
        if (COLD_BIOMES.contains(biome)) {
            return CowVariantKeys.COLD;
        }
        return CowVariantKeys.TEMPERATE;
    }

    @Override
    protected double adultHeight() {
        return ADULT_HEIGHT;
    }

    @Override
    protected double adultWidth() {
        return ADULT_WIDTH;
    }

    @Override
    protected double babyHeight() {
        return BABY_HEIGHT;
    }

    @Override
    protected double babyWidth() {
        return BABY_WIDTH;
    }

    @Override
    protected double defaultFollowRange() {
        return 0.0;
    }

    public TypedKey<CowVariant> variant() {
        return variant;
    }

    public void setVariant(final TypedKey<CowVariant> variant) {
        this.variant = variant;
    }

    public TypedKey<CowSoundVariant> soundVariant() {
        return soundVariant;
    }

    public void setSoundVariant(final TypedKey<CowSoundVariant> soundVariant) {
        this.soundVariant = soundVariant;
    }

    public int variantNetworkId() {
        final Key entry = variant.key();
        final int dynamicId = server().registries().dynamic().networkId(RegistryKey.COW_VARIANT.key(), entry);
        return dynamicId >= 0
                ? dynamicId
                : server().registries().frozen().networkId(RegistryKey.COW_VARIANT.key(), entry);
    }

    @Override
    public void tick(final long currentTick) {
        super.tick(currentTick);
        if (isRemoved() || isDead()) {
            return;
        }
        tickAmbientSound();
    }

    private void tickAmbientSound() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        if (random.nextInt(AMBIENT_SOUND_RANGE) >= ambientSoundChance++) {
            return;
        }
        ambientSoundChance = -AMBIENT_SOUND_DELAY;
        playSound(isMoody() ? SoundEvents.COW_MOODY_AMBIENT : SoundEvents.COW_AMBIENT,
                SOUND_VOLUME, voicePitch());
    }

    @Override
    protected void onStep() {
        playSound(SoundEvents.COW_STEP, STEP_VOLUME, 1.0f);
    }

    @Override
    public void onHurt(final DamageSource source, final float amount) {
        panicGoal.panic();
    }

    @Override
    protected float soundVolume() {
        return SOUND_VOLUME;
    }

    @Override
    protected Sound.Type hurtSound() {
        return isMoody() ? SoundEvents.COW_MOODY_HURT : SoundEvents.COW_HURT;
    }

    @Override
    protected Sound.Type deathSound() {
        return isMoody() ? SoundEvents.COW_MOODY_DEATH : SoundEvents.COW_DEATH;
    }

    @Override
    protected void onDeath() {
        super.onDeath();
    }

    public boolean milk() {
        if (isBaby() || isRemoved() || isDead()) {
            return false;
        }

        playSound(SoundEvents.COW_MILK, 1.0f, 1.0f);
        return true;
    }

    public boolean isPanicking() {
        return panicGoal.isPanicking();
    }

    private boolean isMoody() {
        return soundVariant.equals(CowSoundVariantKeys.MOODY);
    }

    @Override
    protected float voicePitch() {
        return voicePitch(PITCH_MIN, PITCH_MAX, BABY_PITCH_BONUS);
    }

}
