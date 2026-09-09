package fr.euphyllia.fidorial.server.combat;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.entity.AbstractEntity;
import fr.euphyllia.fidorial.server.entity.AbstractLivingEntity;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMob;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMovingMob;
import fr.euphyllia.fidorial.server.entity.mob.AbstractPathfinderMob;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundAnimatePacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundDamageEventPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundEntityEventPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundHurtAnimationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundPlayerCombatKillPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetEntityMotionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetHealthPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSystemChatPacket;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.fidorial.combat.CombatService;
import fr.fidorial.combat.DamageSource;
import fr.fidorial.entity.Entity;
import fr.fidorial.entity.GameMode;
import fr.fidorial.entity.LivingEntity;
import fr.fidorial.entity.Player;
import fr.fidorial.event.entity.EntityDamageEvent;
import fr.fidorial.event.entity.EntityDeathEvent;
import fr.fidorial.event.player.PlayerAttackEntityEvent;
import fr.fidorial.event.player.PlayerDeathEvent;
import fr.fidorial.item.ItemStack;
import fr.fidorial.sound.SoundEvents;
import fr.fidorial.world.Location;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class CombatEngine implements CombatService {

    private static final double SURVIVAL_REACH = 3.0;
    private static final double CREATIVE_REACH = 5.0;
    private static final double REACH_TOLERANCE = 1.0;

    private static final double BASE_KNOCKBACK = 0.4;
    private static final double SPRINT_KNOCKBACK = 0.5;

    private static final float CRITICAL_MULTIPLIER = 1.5f;

    private static final float FULL_CHARGE_THRESHOLD = 0.9f;

    private static final double SWEEP_RADIUS = 1.0;
    private static final double SWEEP_HEIGHT = 0.25;
    private static final float SWEEP_DAMAGE = 1.0f;

    private static final double MAX_UPWARD_KNOCKBACK = 0.4;

    private static final byte ENTITY_EVENT_DEATH = 3;

    private static final ComponentLogger LOGGER = ComponentLogger.logger(CombatEngine.class);

    private final FidorialServer server;
    // Todo : This is a temporary hack to make the combat system work without implementing item stats yet.
    private final int fakeItemDamage = 1;
    private final int fakeAttackSpeed = 1;
    public CombatEngine(final FidorialServer server) {
        this.server = server;
    }

    @Override
    public boolean attack(final Player attacker, final Entity target) {
        if (!(attacker instanceof final ServerPlayer player) || !(target instanceof final AbstractLivingEntity victim)) {
            return false;
        }
        if (player.gameMode() == GameMode.SPECTATOR || player.isDead() || player.isRemoved()) {
            return false;
        }
        if (!victim.isAlive() || victim == player) {
            return false;
        }
        if (!canHarm(player, victim) || !isWithinReach(player, victim)) {
            return false;
        }

        final ItemStack weapon = player.heldItem();
        final float strength = attackStrengthScale(player);
        final boolean critical = isCriticalHit(player, strength);
        final boolean sweeping = isSweepingHit(player, weapon, strength);

        float damage = fakeItemDamage * CombatRules.attackDamageScale(strength);
        if (critical) {
            damage *= CRITICAL_MULTIPLIER;
        }

        final PlayerAttackEntityEvent event = server.events().post(new PlayerAttackEntityEvent(
                player, victim, damage, attackKnockback(player), strength, critical, sweeping));
        player.resetAttackCooldown();
        if (event.isCancelled()) {
            return false;
        }

        final DamageSource source = DamageSource.playerAttack(player);
        final boolean hurt = applyDamage(victim, source, event.damage(), event.knockback());
        if (!hurt) {
            player.playSound(Sound.sound(SoundEvents.PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 1.0f, 1.0f));
            return false;
        }

        if (critical) {
            sendToViewersAndSelf(victim, ClientboundAnimatePacket.criticalHit(victim.entityId()));
        }
        playAttackSound(player, strength, critical, event.knockback() > BASE_KNOCKBACK);

        if (event.isSweeping()) {
            sweep(player, victim, source);
        }
        return true;
    }

    @Override
    public boolean damage(final LivingEntity target, final DamageSource source, final float amount) {
        if (!(target instanceof final AbstractLivingEntity victim)) {
            return false;
        }
        return applyDamage(victim, source, amount, source.noKnockback() ? 0.0 : BASE_KNOCKBACK);
    }

    private boolean applyDamage(
            final AbstractLivingEntity victim,
            final DamageSource source,
            final float amount,
            final double knockback) {
        if (amount <= 0f || victim.isRemoved() || victim.isDead()) {
            return false;
        }
        if (victim instanceof final ServerPlayer player
                && player.isInvulnerableToDamage()
                && !source.bypassesInvulnerability()) {
            return false;
        }

        final boolean withinInvulnerability = !source.bypassesInvulnerability()
                && victim.invulnerableTicks() > AbstractLivingEntity.INVULNERABILITY_OVERRIDE_THRESHOLD;

        float incoming = amount;
        if (withinInvulnerability) {
            if (amount <= victim.lastDamage()) {
                return false;
            }
            incoming = amount - victim.lastDamage();
        }

        final EntityDamageEvent event =
                server.events().post(new EntityDamageEvent(victim, source, incoming, knockback));
        if (event.isCancelled()) {
            return false;
        }

        final float reduced = reduce(victim, source, event.damage());
        if (reduced <= 0f) {
            return false;
        }

        victim.setLastDamage(amount);

        if (!withinInvulnerability) {
            victim.setInvulnerableTicks(AbstractLivingEntity.INVULNERABILITY_TICKS);
        }

        final float dealt = soakWithAbsorption(victim, reduced);
        broadcastHurt(victim, source);

        if (dealt > 0f) {
            victim.setHealth(victim.health() - dealt);
        }
        syncHealth(victim);

        if (event.knockback() > 0.0) {
            final Location origin = knockbackOrigin(source, victim);
            if (origin != null) {
                knockback(victim, event.knockback(), origin.x(), origin.z());
            }
        }

        if (victim.isDead()) {
            die(victim, source.causingEntity());
        } else if (victim instanceof final AbstractMob mob) {
            mob.playHurtSound();
            mob.onHurt(source, dealt);
            aggro(mob, source.causingEntity());
        }
        return true;
    }

    private float reduce(final AbstractLivingEntity victim, final DamageSource source, final float amount) {
        float damage = amount;
        if (!source.bypassesArmor()) {
            damage = CombatRules.damageAfterArmor(damage, (float) victim.armor(), (float) victim.armorToughness());
        }
        damage = CombatRules.damageAfterProtection(damage, 0f);
        return Math.max(0f, damage);
    }

    private float soakWithAbsorption(final AbstractLivingEntity victim, final float damage) {
        final float absorption = victim.absorptionAmount();
        if (absorption <= 0f) {
            return damage;
        }
        final float soaked = Math.min(absorption, damage);
        victim.setAbsorptionAmount(absorption - soaked);
        return damage - soaked;
    }

    @Override
    public void knockback(final LivingEntity target, final double strength, final double fromX, final double fromZ) {
        if (!(target instanceof final AbstractLivingEntity victim)) {
            return;
        }
        final double scaled = strength * (1.0 - Math.clamp(victim.knockbackResistance(), 0.0, 1.0));
        if (scaled <= 0.0) {
            return;
        }

        final Location location = victim.location();
        double dx = location.x() - fromX;
        double dz = location.z() - fromZ;
        final double length = Math.sqrt(dx * dx + dz * dz);
        if (length < 1.0E-4) {
            dx = 0.0;
            dz = 1.0;
        } else {
            dx /= length;
            dz /= length;
        }

        if (victim instanceof final AbstractMovingMob mob) {
            final double newX = mob.velocityX() / 2.0 + dx * scaled;
            final double newZ = mob.velocityZ() / 2.0 + dz * scaled;
            final double newY = mob.onGround()
                    ? Math.min(MAX_UPWARD_KNOCKBACK, mob.velocityY() / 2.0 + scaled)
                    : mob.velocityY();
            mob.setVelocity(newX, newY, newZ);
            mob.sendToTrackers(new ClientboundSetEntityMotionPacket(mob.entityId(), newX, newY, newZ));
            return;
        }

        if (victim instanceof final ServerPlayer player) {
            player.connection().send(new ClientboundSetEntityMotionPacket(
                    player.entityId(), dx * scaled, MAX_UPWARD_KNOCKBACK, dz * scaled));
        }
    }

    @Override
    public void kill(final LivingEntity target, final @Nullable Entity killer) {
        if (!(target instanceof final AbstractLivingEntity victim) || victim.isDead()) {
            return;
        }
        victim.setAbsorptionAmount(0f);
        victim.setHealth(0f);
        syncHealth(victim);
        die(victim, killer);
    }

    private void die(final AbstractLivingEntity victim, final @Nullable Entity killer) {
        if (victim instanceof final ServerPlayer player) {
            killPlayer(player, killer);
            return;
        }
        if (!(victim instanceof AbstractMob)) {
            sendToViewersAndSelf(victim, new ClientboundEntityEventPacket(victim.entityId(), ENTITY_EVENT_DEATH));
        }
        server.events().post(new EntityDeathEvent(victim, killer));
    }

    private void killPlayer(final ServerPlayer player, final @Nullable Entity killer) {
        if (player.health() <= 0) {
            return;
        }

        final Component message = deathMessage(player, killer);
        final PlayerDeathEvent event = server.events().post(new PlayerDeathEvent(player, killer, message));

        sendToViewersAndSelf(player, new ClientboundEntityEventPacket(player.entityId(), ENTITY_EVENT_DEATH));
        player.playSound(Sound.sound(SoundEvents.PLAYER_DEATH, Sound.Source.PLAYER, 1.0f, 1.0f));
        player.connection().send(new ClientboundPlayerCombatKillPacket(
                player.entityId(), event.deathMessage() == null ? Component.empty() : event.deathMessage()));

        if (event.deathMessage() != null) {
            server.broadcast(new ClientboundSystemChatPacket(event.deathMessage(), false));
        }
    }

    private Component deathMessage(final ServerPlayer player, final @Nullable Entity killer) {
        if (killer == null) {
            return Component.text(player.name() + " died");
        }
        return Component.text(player.name() + " was slain by ").append(killer.displayName());
    }

    private void aggro(final AbstractMob mob, final @Nullable Entity attacker) {
        if (mob instanceof final AbstractPathfinderMob pathfinder && attacker instanceof final ServerPlayer player) {
            pathfinder.setTarget(player);
        }
    }

    private void broadcastHurt(final AbstractLivingEntity victim, final DamageSource source) {
        final int damageTypeId =
                server.dynamicRegistries().networkId(Key.key("minecraft", "damage_type"), source.type().key());
        if (damageTypeId >= 0) {
            sendToViewersAndSelf(victim, new ClientboundDamageEventPacket(
                    victim.entityId(),
                    damageTypeId,
                    source.causingEntity() == null ? null : source.causingEntity().entityId(),
                    source.directEntity() == null ? null : source.directEntity().entityId(),
                    null));
        } else {
            LOGGER.debug("Type de degat inconnu du registre : {}", source.type().key());
        }
        sendToViewersAndSelf(victim, new ClientboundHurtAnimationPacket(victim.entityId(), hurtYaw(victim, source)));
    }

    private float hurtYaw(final AbstractLivingEntity victim, final DamageSource source) {
        final Location origin = knockbackOrigin(source, victim);
        if (origin == null) {
            return 0f;
        }
        final Location self = victim.location();
        return (float) (Math.toDegrees(Math.atan2(origin.z() - self.z(), origin.x() - self.x())) - 90.0);
    }

    private @Nullable Location knockbackOrigin(final DamageSource source, final AbstractLivingEntity victim) {
        if (source.position() != null) {
            return source.position();
        }
        final Entity direct = source.directEntity() != null ? source.directEntity() : source.causingEntity();
        return direct == null || direct == victim ? null : direct.location();
    }

    private void sendToViewersAndSelf(final AbstractLivingEntity victim, final ClientboundPacket packet) {
        victim.sendToTrackers(packet);
        if (victim instanceof final ServerPlayer player) {
            player.connection().send(packet);
        }
    }

    private void syncHealth(final AbstractLivingEntity victim) {
        if (victim instanceof final ServerPlayer player) {
            player.connection().send(new ClientboundSetHealthPacket(player.health(), 20, 5.0f));
        }
    }

    private void sweep(final ServerPlayer attacker, final AbstractLivingEntity primary, final DamageSource source) {
        final Location center = primary.location();
        for (final AbstractLivingEntity nearby : nearbyLivingEntities(attacker, center)) {
            if (nearby == primary || nearby == attacker || !nearby.isAlive() || !canHarm(attacker, nearby)) {
                continue;
            }
            applyDamage(nearby, source, SWEEP_DAMAGE, BASE_KNOCKBACK);
        }
        attacker.playSound(Sound.sound(SoundEvents.PLAYER_ATTACK_SWEEP, Sound.Source.PLAYER, 1.0f, 1.0f));
    }

    private List<AbstractLivingEntity> nearbyLivingEntities(final ServerPlayer attacker, final Location center) {
        final List<AbstractLivingEntity> found = new ArrayList<>();
        for (final ServerPlayer player : server.players()) {
            if (player.world() == attacker.world() && withinSweepBox(center, player.location())) {
                found.add(player);
            }
        }
        ((ServerWorld) attacker.world()).entityManager().forEachInChunkRange(
                center.chunk().x(), center.chunk().z(), 1, entity -> {
                    if (entity instanceof final AbstractMob mob && withinSweepBox(center, mob.location())) {
                        found.add(mob);
                    }
                });
        return found;
    }

    private boolean withinSweepBox(final Location center, final Location other) {
        return Math.abs(other.x() - center.x()) <= SWEEP_RADIUS
                && Math.abs(other.z() - center.z()) <= SWEEP_RADIUS
                && Math.abs(other.y() - center.y()) <= SWEEP_HEIGHT + 1.0;
    }

    @Override
    public float attackDamage(final Player attacker) {
        if (!(attacker instanceof final ServerPlayer player)) {
            return fakeItemDamage;
        }
        return fakeItemDamage;
    }

    @Override
    public double attackKnockback(final Player attacker) {
        final boolean sprinting = attacker instanceof final ServerPlayer player && player.isSprinting();
        return BASE_KNOCKBACK + (sprinting ? SPRINT_KNOCKBACK : 0.0);
    }

    @Override
    public float attackStrengthScale(final Player attacker) {
        if (!(attacker instanceof final ServerPlayer player)) {
            return 1.0f;
        }
        final int cooldown = attackCooldownTicks(player);
        if (cooldown <= 0) {
            return 1.0f;
        }
        return Math.clamp((player.ticksSinceLastAttack() + 0.5f) / cooldown, 0.0f, 1.0f);
    }

    @Override
    public int attackCooldownTicks(final Player attacker) {
        final ItemStack weapon =
                attacker instanceof final ServerPlayer player ? player.heldItem() : ItemStack.EMPTY;
        final float speed = fakeAttackSpeed;
        return speed <= 0f ? 1 : Math.max(1, Math.round(20.0f / speed));
    }

    @Override
    public boolean pvpEnabled() {
        return server.config().pvp();
    }

    private boolean canHarm(final ServerPlayer attacker, final AbstractLivingEntity victim) {
        if (!(victim instanceof final ServerPlayer other)) {
            return true;
        }
        return pvpEnabled() && !other.isInvulnerableToDamage();
    }

    private boolean isWithinReach(final ServerPlayer attacker, final AbstractEntity victim) {
        final double reach =
                (attacker.gameMode() == GameMode.CREATIVE ? CREATIVE_REACH : SURVIVAL_REACH) + REACH_TOLERANCE;
        final Location from = attacker.location();
        final Location to = victim.location();
        final double dx = from.x() - to.x();
        final double dy = from.y() - to.y();
        final double dz = from.z() - to.z();
        return dx * dx + dy * dy + dz * dz <= reach * reach;
    }

    private boolean isCriticalHit(final ServerPlayer attacker, final float strength) {
        return strength > FULL_CHARGE_THRESHOLD
                && attacker.isFalling()
                && attacker.fallDistance() > 0.0
                && !attacker.isSprinting()
                && attacker.gameMode() != GameMode.CREATIVE;
    }

    private boolean isSweepingHit(final ServerPlayer attacker, final ItemStack weapon, final float strength) {
        return strength > FULL_CHARGE_THRESHOLD
                && !attacker.isSprinting()
                && !attacker.isFalling()
                //&& Todo : check weapon is sword)
                ;
    }

    private void playAttackSound(
            final ServerPlayer attacker, final float strength, final boolean critical, final boolean knockedBack) {
        final Sound.Type type;
        if (critical) {
            type = SoundEvents.PLAYER_ATTACK_CRIT;
        } else if (knockedBack) {
            type = SoundEvents.PLAYER_ATTACK_KNOCKBACK;
        } else if (strength > FULL_CHARGE_THRESHOLD) {
            type = SoundEvents.PLAYER_ATTACK_STRONG;
        } else {
            type = SoundEvents.PLAYER_ATTACK_WEAK;
        }
        attacker.playSound(Sound.sound(type, Sound.Source.PLAYER, 1.0f, 1.0f));
    }
}
