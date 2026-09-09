package fr.euphyllia.fidorial.server.entity.mob;

import fr.euphyllia.fidorial.server.entity.AbstractLivingEntity;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundEntityEventPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSoundEntityPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSoundPacket;
import fr.fidorial.combat.DamageSource;
import fr.fidorial.entity.EntityType;
import fr.fidorial.entity.Player;
import fr.fidorial.entity.mob.MobBehaviour;
import fr.fidorial.inventory.EquipmentSlotGroup;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.sound.Sound;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public abstract class AbstractMob extends AbstractLivingEntity {

    private static final byte ENTITY_EVENT_DEATH = 3;

    private final List<MobBehaviour> behaviours = new CopyOnWriteArrayList<>();

    protected AbstractMob(final int entityId, final UUID uuid, final EntityType type, final World world, final Location location, final float maxHealth) {
        super(entityId, uuid, type, world, location, maxHealth);
    }

    @Override
    public final void setHealth(final float health) {
        super.setHealth(health);
        if (health() <= 0f && !isDying()) {
            onDeath();
        }
    }

    protected void onDeath() {
        sendToTrackers(new ClientboundEntityEventPacket(entityId(), ENTITY_EVENT_DEATH));
        startDeathAnimation();
        dispatch(MobBehaviour::onDeath, "onDeath");
    }

    @Override
    protected void onRemoved() {
        dispatch(MobBehaviour::onRemove, "onRemove");
    }

    protected Sound.@Nullable Type hurtSound() {
        return null;
    }

    protected Sound.@Nullable Type deathSound() {
        return null;
    }

    protected float voicePitch() {
        return 0.8f + ThreadLocalRandom.current().nextFloat() * 0.4f;
    }

    public float attackDamage() {
        return 0f;
    }

    protected float soundVolume() {
        return 1.0f;
    }

    public void playHurtSound() {
        final Sound.Type sound = hurtSound();
        if (sound != null) {
            playSound(sound, soundVolume(), voicePitch());
        }
    }

    public final void notifyHurt(final DamageSource source, final float amount) {
        onHurt(source, amount);
        dispatch(behaviour -> behaviour.onHurt(source, amount), "onHurt");
    }

    public void onHurt(final DamageSource source, final float amount) {
    }

    public final boolean onInteract(final Player player, final EquipmentSlotGroup hand) {
        boolean handled = false;
        for (final MobBehaviour behaviour : behaviours) {
            try {
                handled |= behaviour.onInteract(player, hand);
            } catch (final Throwable throwable) {
                LOGGER.error("Error in the onInteract of {} on {}", behaviour.getClass().getName(), this, throwable);
            }
        }
        return handled;
    }

    public final List<MobBehaviour> behaviours() {
        return List.copyOf(behaviours);
    }

    public final <T extends MobBehaviour> Optional<T> behaviour(final Class<T> type) {
        for (final MobBehaviour behaviour : behaviours) {
            if (type.isInstance(behaviour)) {
                return Optional.of(type.cast(behaviour));
            }
        }
        return Optional.empty();
    }

    public final void addBehaviour(final MobBehaviour behaviour) {
        behaviours.add(behaviour);
        try {
            behaviour.onSpawn();
        } catch (final Throwable throwable) {
            LOGGER.error("Error in the onSpawn of {} on {}", behaviour.getClass().getName(), this, throwable);
        }
    }

    public final boolean hasBehaviours() {
        return !behaviours.isEmpty();
    }

    protected final void tickBehaviours(final long currentTick) {
        if (behaviours.isEmpty()) {
            return;
        }
        dispatch(behaviour -> behaviour.onTick(currentTick), "onTick");
    }

    public final void playPositionalSound(final Sound.Type type, final float volume, final float pitch) {
        final Location loc = location();
        sendToTrackers(new ClientboundSoundPacket(
                Sound.sound(type, soundSource(), volume, pitch), loc.x(), loc.y(), loc.z()));
    }

    public final void playSound(final Sound.Type type, final float volume, final float pitch) {
        sendToTrackers(new ClientboundSoundEntityPacket(
                Sound.sound(type, soundSource(), volume, pitch), entityId()));
    }

    private void dispatch(final Consumer<MobBehaviour> action, final String hook) {
        for (final MobBehaviour behaviour : behaviours) {
            try {
                action.accept(behaviour);
            } catch (final Throwable throwable) {
                LOGGER.error("Error in the {} of {} on {}", hook, behaviour.getClass().getName(), this, throwable);
            }
        }
    }
}
