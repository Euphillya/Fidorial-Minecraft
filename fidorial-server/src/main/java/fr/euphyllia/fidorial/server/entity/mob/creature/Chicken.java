package fr.euphyllia.fidorial.server.entity.mob.creature;

import fr.euphyllia.fidorial.server.entity.Category;
import fr.euphyllia.fidorial.server.entity.EntityTypes;
import fr.euphyllia.fidorial.server.entity.ai.goal.LookAtTargetGoal;
import fr.euphyllia.fidorial.server.entity.ai.goal.RandomStrollGoal;
import fr.euphyllia.fidorial.server.entity.mob.AbstractPathfinderMob;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.ChickenVariant;
import fr.fidorial.registry.keys.ChickenVariantKeys;
import fr.fidorial.sound.SoundEvents;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.sound.Sound;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class Chicken extends AbstractPathfinderMob implements Category.Neutral {

    public static final float MAX_HEALTH = 4f;

    private static final double HITBOX_HEIGHT = 0.7;

    private static final double FLUTTER_FALL_DRAG = 0.6;

    private static final int EGG_MIN_TICKS = 6000;
    private static final int EGG_MAX_TICKS = 12000;

    private static final int AMBIENT_CHANCE = 240;

    private static final double STROLL_SPEED = 0.10;

    private TypedKey<ChickenVariant> variant = ChickenVariantKeys.TEMPERATE;
    private int eggTimer = nextEggDelay();


    public Chicken(final int entityId, final World world, final Location location) {
        super(entityId, UUID.randomUUID(), EntityTypes.CHICKEN, world, location, MAX_HEALTH);

        goals.add(new RandomStrollGoal(this, 1, STROLL_SPEED));
        goals.add(new LookAtTargetGoal(this, 2, 6.0));
    }

    private static int nextEggDelay() {
        return ThreadLocalRandom.current().nextInt(EGG_MIN_TICKS, EGG_MAX_TICKS + 1);
    }

    @Override
    protected float voicePitch() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f;
    }

    @Override
    public void tick(final long currentTick) {
        super.tick(currentTick);
        if (isRemoved() || isDead()) {
            return;
        }

        if (ThreadLocalRandom.current().nextInt(AMBIENT_CHANCE) == 0) {
            playSound(SoundEvents.CHICKEN_AMBIENT, 1.0f, voicePitch());
        }

        if (--eggTimer <= 0) {
            playSound(SoundEvents.CHICKEN_EGG, 1.0f, voicePitch());
            eggTimer = nextEggDelay();
        }
    }

    @Override
    protected void onStep() {
        playSound(SoundEvents.CHICKEN_STEP, 0.15f, 1.0f);
    }


    @Override
    protected Sound.Type hurtSound() {
        return SoundEvents.CHICKEN_HURT;
    }

    @Override
    protected Sound.Type deathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    protected void onDeath() {
        super.onDeath();
    }

    @Override
    public double height() {
        return HITBOX_HEIGHT;
    }

    @Override
    protected double fallDrag() {
        return FLUTTER_FALL_DRAG;
    }

    public TypedKey<ChickenVariant> variant() {
        return variant;
    }

    public void setVariant(final TypedKey<ChickenVariant> variant) {
        this.variant = variant;
    }
}
