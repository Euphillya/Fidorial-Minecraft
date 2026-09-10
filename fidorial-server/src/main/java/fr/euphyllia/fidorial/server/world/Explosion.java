package fr.euphyllia.fidorial.server.world;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.entity.AbstractEntity;
import fr.euphyllia.fidorial.server.entity.ai.BlockView;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMob;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundHurtAnimationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundLevelEventPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetEntityMotionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetHealthPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSoundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils.PositionData;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.entity.GameMode;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.sound.SoundEvents;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.Location;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Source used: <a href="https://minecraft.wiki/w/Explosion">Explosion Wiki</a>
 */
public final class Explosion {

    private static final float STEP = 0.3f;
    private static final float AIR_ATTENUATION = 0.225f;

    private static final float DEFAULT_BLAST_RESISTANCE = 0.5f;
    // TODO : Add the `BLAST_RESISTANCE` method to BlockState.
    private static final Map<String, Float> BLAST_RESISTANCE = Map.ofEntries(
            Map.entry("minecraft:bedrock", 3_600_000f),
            Map.entry("minecraft:barrier", 3_600_000f),
            Map.entry("minecraft:command_block", 3_600_000f),
            Map.entry("minecraft:end_portal_frame", 3_600_000f),
            Map.entry("minecraft:obsidian", 1_200f),
            Map.entry("minecraft:crying_obsidian", 1_200f),
            Map.entry("minecraft:respawn_anchor", 1_200f),
            Map.entry("minecraft:reinforced_deepslate", 1_200f),
            Map.entry("minecraft:ancient_debris", 1_200f),
            Map.entry("minecraft:netherite_block", 1_200f),
            Map.entry("minecraft:enchanting_table", 1_200f),
            Map.entry("minecraft:ender_chest", 600f),
            Map.entry("minecraft:water", 100f),
            Map.entry("minecraft:lava", 100f),
            Map.entry("minecraft:end_stone", 9f),
            Map.entry("minecraft:stone", 6f),
            Map.entry("minecraft:cobblestone", 6f),
            Map.entry("minecraft:deepslate", 6f),
            Map.entry("minecraft:granite", 6f),
            Map.entry("minecraft:diorite", 6f),
            Map.entry("minecraft:andesite", 6f),
            Map.entry("minecraft:bricks", 6f),
            Map.entry("minecraft:planks", 3f),
            Map.entry("minecraft:glass", 0.3f),
            Map.entry("minecraft:leaves", 0.2f));

    private Explosion() {
    }

    public static void explode(final ServerWorld world, final Location center, final float power, final AbstractEntity source) {
        final FidorialServer server = FidorialServer.getInstance();
        playExplosionSound(server, world, center);
        destroyBlocks(server, world, center, power);
        damageEntities(server, world, center, power, source);
    }

    private static void playExplosionSound(final FidorialServer server, final ServerWorld world, final Location center) {
        final float pitch = (1.0f
                        + (ThreadLocalRandom.current().nextFloat()
                                        - ThreadLocalRandom.current().nextFloat())
                                * 0.2f)
                * 0.7f;
        server.broadcastNear(world, center.x(), center.y(), center.z(),
                new ClientboundSoundPacket(
                        Sound.sound(SoundEvents.GENERIC_EXPLODE, Sound.Source.BLOCK, 4.0f, pitch),
                        center.x(),
                        center.y(),
                        center.z()));
    }

    private static void destroyBlocks(final FidorialServer server, final ServerWorld world, final Location center, final float power) {
        final Set<BlockPos> toDestroy = collectExplodedBlocks(world, center, power);
        final List<BlockPos> destroyed = new ArrayList<>(toDestroy.size());

        for (final BlockPos pos : toDestroy) {
            if (server.blockEdits().set(world, pos, BlockState.of(BlockTypeKeys.AIR.key()))) {
                destroyed.add(pos);
            }
            if (ThreadLocalRandom.current().nextFloat() < 1.0f / power) {
                // TODO : drop item
            }
        }

        for (int i = 0; i < destroyed.size(); i += 5) {
            final BlockPos broken = destroyed.get(i);
            server.broadcastNear(world, broken.x() + 0.5, broken.y() + 0.5, broken.z() + 0.5,
                    new ClientboundLevelEventPacket(
                            ClientboundLevelEventPacket.BLOCK_BREAK, broken, 0, false));
        }
    }

    private static Set<BlockPos> collectExplodedBlocks(final ServerWorld world, final Location center, final float power) {
        final Set<BlockPos> out = new HashSet<>();
        final var random = ThreadLocalRandom.current();
        final double ox = center.x();
        final double oy = center.y();
        final double oz = center.z();

        for (int j = 0; j < 16; j++) {
            for (int k = 0; k < 16; k++) {
                for (int l = 0; l < 16; l++) {
                    if (j != 0 && j != 15 && k != 0 && k != 15 && l != 0 && l != 15) {
                        continue;
                    }
                    double dx = j / 15.0 * 2.0 - 1.0;
                    double dy = k / 15.0 * 2.0 - 1.0;
                    double dz = l / 15.0 * 2.0 - 1.0;
                    final double norm = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    dx /= norm;
                    dy /= norm;
                    dz /= norm;

                    float intensity = power * (0.7f + random.nextFloat() * 0.6f);
                    double px = ox, py = oy, pz = oz;

                    for (; intensity > 0.0f; intensity -= AIR_ATTENUATION) {
                        final int bx = (int) Math.floor(px);
                        final int by = (int) Math.floor(py);
                        final int bz = (int) Math.floor(pz);
                        final BlockState state = BlockView.blockAt(world, bx, by, bz);
                        if (state != null && !state.isAir()) {
                            intensity -= (resistanceOf(state) + 0.3f) * 0.3f;
                            if (intensity > 0.0f) {
                                out.add(new BlockPos(bx, by, bz));
                            }
                        }
                        px += dx * STEP;
                        py += dy * STEP;
                        pz += dz * STEP;
                    }
                }
            }
        }
        return out;
    }

    private static float resistanceOf(final BlockState state) {
        return BLAST_RESISTANCE.getOrDefault(
                state.name(), DEFAULT_BLAST_RESISTANCE); // Todo : Add the `BLAST_RESISTANCE` method to BlockState.
    }

    private static void damageEntities(
            final FidorialServer server,
            final ServerWorld world,
            final Location center,
            final float power,
            final AbstractEntity source
    ) {
        final double range = power * 2.0;
        final double rangeSq = range * range;
        final double cx = center.x();
        final double cy = center.y();
        final double cz = center.z();

        final List<AbstractEntity> affected = new ArrayList<>();
        world.entityManager().forEachNear(center.chunk(), range, affected::add);

        for (final AbstractEntity abstractEntity : affected) {
            if (abstractEntity == source || abstractEntity.isRemoved()) {
                continue;
            }
            final Location pos = abstractEntity.location();

            final double fx = pos.x() - cx;
            final double fy = pos.y() - cy;
            final double fz = pos.z() - cz;
            final double feetDistSq = fx * fx + fy * fy + fz * fz;
            if (feetDistSq > rangeSq) {
                continue;
            }
            final double feetDist = Math.sqrt(feetDistSq);
            final double exposure = getExposure(world, center, abstractEntity);
            final double impact = (1.0 - feetDist / range) * exposure;
            final float damage = (float) ((impact * impact + impact) / 2.0 * 7.0 * range + 1.0);

            final double eye = eyeHeight(abstractEntity);
            final double ex = pos.x() - cx;
            final double ey = pos.y() + eye - cy;
            final double ez = pos.z() - cz;
            final double eyeDist = Math.sqrt(ex * ex + ey * ey + ez * ez);
            double knockX = 0.0, knockY = 0.0, knockZ = 0.0;
            if (eyeDist > 1.0E-4) {
                knockX = ex / eyeDist * impact;
                knockY = ey / eyeDist * impact;
                knockZ = ez / eyeDist * impact;
            }

            switch (abstractEntity) {
                case final ServerPlayer player -> {
                    final GameMode mode = player.gameMode();
                    if (mode == GameMode.CREATIVE || mode == GameMode.SPECTATOR) {
                        continue;
                    }

                    final float finalDamage = damage;
                    // Todo : Uncomment once implemented.
                    //                    switch (world.difficulty()) {
                    //                        case PEACEFUL -> { continue; }
                    //                        case EASY     -> finalDamage = Math.min(damage / 2f + 1f, damage);
                    //                        case NORMAL   -> finalDamage = damage;
                    //                        case HARD     -> finalDamage = damage * 1.5f;
                    //                    }

                    player.setHealth(player.health() - finalDamage);
                    player.connection().send(new ClientboundSetHealthPacket(player.health(), 20, 5f));
                    player.connection()
                            .send(new ClientboundSetEntityMotionPacket(player.entityId(), new PositionData.VelocityVec3D(knockX, knockY, knockZ)));
                    server.broadcastNear(world, pos.x(), pos.y(), pos.z(),
                            new ClientboundHurtAnimationPacket(player.entityId(), pos.yaw()));
                }
                case final AbstractMob mob -> {
                    mob.setHealth(mob.health() - damage);
                    if (!mob.isRemoved()) {
                        mob.sendToTrackers(new ClientboundHurtAnimationPacket(mob.entityId(), 0f));
                    }
                }
                default -> {
                }
            }
        }
    }

    private static float getExposure(final ServerWorld world, final Location center, final AbstractEntity entity) {
        final double[] box = boundingBox(entity);
        final double width = box[3] - box[0];
        final double height = box[4] - box[1];

        final double stepX = 1.0 / (width * 2.0 + 1.0);
        final double stepY = 1.0 / (height * 2.0 + 1.0);
        final double stepZ = stepX;
        if (stepX <= 0.0 || stepY <= 0.0) {
            return 0.0f;
        }
        final double offX = (1.0 - Math.floor(1.0 / stepX) * stepX) / 2.0;
        final double offZ = (1.0 - Math.floor(1.0 / stepZ) * stepZ) / 2.0;

        int clear = 0, total = 0;
        for (double fx = 0.0; fx <= 1.0; fx += stepX) {
            for (double fy = 0.0; fy <= 1.0; fy += stepY) {
                for (double fz = 0.0; fz <= 1.0; fz += stepZ) {
                    final double sx = lerp(fx, box[0], box[3]) + offX;
                    final double sy = lerp(fy, box[1], box[4]);
                    final double sz = lerp(fz, box[2], box[5]) + offZ;
                    if (clearLineOfSight(world, sx, sy, sz, center.x(), center.y(), center.z())) {
                        clear++;
                    }
                    total++;
                }
            }
        }
        return total == 0 ? 0.0f : (float) clear / total;
    }

    private static boolean clearLineOfSight(
            final ServerWorld world,
            final double sx,
            final double sy,
            final double sz,
            final double cx,
            final double cy,
            final double cz
    ) {

        final double dx = cx - sx;
        final double dy = cy - sy;
        final double dz = cz - sz;
        final double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (dist < 1.0E-6) {
            return true;
        }
        final int steps = (int) Math.ceil(dist / 0.3);
        final double stepX = dx / steps;
        final double stepY = dy / steps;
        final double stepZ = dz / steps;
        double x = sx, y = sy, z = sz;
        for (int i = 0; i < steps; i++) {
            x += stepX;
            y += stepY;
            z += stepZ;
            final BlockState state = BlockView.blockAt(world, (int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
            if (state != null && !state.isAir() && !isFluid(state)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFluid(final BlockState state) {
        final Key name = state.name();
        return name.equals(Key.key("water")) || name.equals(Key.key("lava")); // Todo : Add isFluid to BlockState
    }

    private static double eyeHeight(final AbstractEntity entity) {
        final double[] box = boundingBox(entity);
        return (box[4] - box[1]) * 0.85;
    }

    private static double[] boundingBox(final AbstractEntity entity) {
        double width = 0.6;
        double height = 1.8;
        if (entity instanceof ServerPlayer) {
            width = 0.6;
            height = 1.8;
        } else if (entity instanceof AbstractMob) {
            width = 0.6;
            height = 1.7;
        }
        final Location loc = entity.location();
        final double half = width / 2.0;
        return new double[] {loc.x() - half, loc.y(), loc.z() - half, loc.x() + half, loc.y() + height, loc.z() + half};
    }

    private static double lerp(final double t, final double a, final double b) {
        return a + (b - a) * t;
    }
}
