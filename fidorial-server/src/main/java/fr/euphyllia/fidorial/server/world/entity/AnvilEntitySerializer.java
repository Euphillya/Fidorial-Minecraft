package fr.euphyllia.fidorial.server.world.entity;

import fr.euphyllia.fidorial.server.entity.AbstractEntity;
import fr.euphyllia.fidorial.server.entity.EntityTypes;
import fr.euphyllia.fidorial.server.entity.mob.AbstractMob;
import fr.euphyllia.fidorial.server.entity.mob.AbstractPathfinderMob;
import fr.euphyllia.fidorial.server.entity.mob.MobFactories;
import fr.euphyllia.fidorial.server.entity.mob.PluginMob;
import fr.euphyllia.fidorial.server.world.chunk.AnvilChunkSerializer;
import fr.fidorial.entity.EntityType;
import fr.fidorial.entity.LivingEntity;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;
import net.kyori.adventure.nbt.FloatBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.IntSupplier;

public class AnvilEntitySerializer {

    private final int dataVersion;

    public AnvilEntitySerializer() {
        this(AnvilChunkSerializer.DATA_VERSION_26_2);
    }

    public AnvilEntitySerializer(final int dataVersion) {
        this.dataVersion = dataVersion;
    }

    public static boolean isPersistable(final AbstractEntity entity) {
        if (!(entity instanceof AbstractMob) || entity.isRemoved()) {
            return false;
        }
        return !(entity instanceof final PluginMob plugin) || plugin.isPersistent();
    }

    public CompoundBinaryTag toChunkNbt(final int chunkX, final int chunkZ, final Collection<? extends AbstractEntity> entities) {
        final CompoundBinaryTag.Builder root = CompoundBinaryTag.builder();
        root.putInt("DataVersion", dataVersion);
        root.putIntArray("Position", new int[] {chunkX, chunkZ});

        final ListBinaryTag.Builder<BinaryTag> entitiesTag = ListBinaryTag.builder();
        for (final AbstractEntity entity : entities) {
            if (isPersistable(entity)) {
                entitiesTag.add(toNbt(entity));
            }
        }
        root.put("Entities", entitiesTag.build());
        return root.build();
    }

    public CompoundBinaryTag toNbt(final AbstractEntity entity) {
        final CompoundBinaryTag.Builder c = CompoundBinaryTag.builder();
        c.putString("id", entity.type().key().asString());
        c.putIntArray("UUID", uuidToInts(entity.uuid()));

        final Location loc = entity.location();
        c.put("Pos", doubleList(loc.x(), loc.y(), loc.z()));

        if (entity instanceof final AbstractPathfinderMob mob) {
            c.put("Motion", doubleList(mob.velocityX(), mob.velocityY(), mob.velocityZ()));
            c.putBoolean("OnGround", mob.onGround());
        } else {
            c.put("Motion", doubleList(0.0, 0.0, 0.0));
            c.putBoolean("OnGround", false);
        }
        c.put("Rotation", floatList(loc.yaw(), loc.pitch()));

        c.putFloat("FallDistance", 0f);
        c.putShort("Fire", (short) -20);
        c.putShort("Air", (short) 300);

        if (entity instanceof final LivingEntity living) {
            c.putFloat("Health", living.health());
        }
        return c.build();
    }

    public List<AbstractEntity> fromChunkNbt(final CompoundBinaryTag root, final World world, final IntSupplier idAllocator) {
        final List<AbstractEntity> result = new ArrayList<>();

        final ListBinaryTag entities = root.getList("Entities");

        for (final BinaryTag tag : entities) {
            if (tag instanceof final CompoundBinaryTag entry) {
                final AbstractEntity entity = fromNbt(entry, world, idAllocator);
                if (entity != null) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    @SuppressWarnings("PatternValidation")
    public @Nullable AbstractEntity fromNbt(final CompoundBinaryTag c, final World world, final IntSupplier idAllocator) {
        final String id = c.getString("id");
        if (id.isEmpty()) {
            return null;
        }

        final EntityType type = EntityTypes.get(Key.key(id));
        if (type == null || !MobFactories.isMob(type)) {
            return null;
        }

        final ListBinaryTag pos = c.getList("Pos");
        final ListBinaryTag rot = c.getList("Rotation");
        final double x = doubleAt(pos, 0);
        final double y = doubleAt(pos, 1);
        final double z = doubleAt(pos, 2);
        final float yaw = floatAt(rot, 0);
        final float pitch = floatAt(rot, 1);
        final Location location = new Location(x, y, z, yaw, pitch);

        final AbstractMob mob = MobFactories.create(type, idAllocator.getAsInt(), world, location);

        final int[] uuid = c.getIntArray("UUID");
        if (uuid.length == 4) {
            mob.restoreUuid(uuidFromInts(uuid));
        }

        if (c.contains("Health")) {
            final float health = c.getFloat("Health");
            if (health > 0f) {
                mob.setHealth(health);
            }
        }

        if (mob instanceof final AbstractPathfinderMob pathfinder) {
            final ListBinaryTag motion = c.getList("Motion");
            if (motion.size() == 3) {
                pathfinder.setVelocity(doubleAt(motion, 0), doubleAt(motion, 1), doubleAt(motion, 2));
            }
            if (c.contains("OnGround")) {
                pathfinder.setOnGround(c.getBoolean("OnGround"));
            }
        }
        return mob;
    }

    private static ListBinaryTag doubleList(final double a, final double b, final double c) {
        return ListBinaryTag.builder()
                .add(DoubleBinaryTag.doubleBinaryTag(a))
                .add(DoubleBinaryTag.doubleBinaryTag(b))
                .add(DoubleBinaryTag.doubleBinaryTag(c))
                .build();
    }

    private static ListBinaryTag floatList(final float a, final float b) {
        return ListBinaryTag.builder()
                .add(FloatBinaryTag.floatBinaryTag(a))
                .add(FloatBinaryTag.floatBinaryTag(b))
                .build();
    }

    private static double doubleAt(final ListBinaryTag list, final int index) {
        if (index >= list.size()) {
            return 0.0;
        }
        return list.get(index) instanceof final DoubleBinaryTag tag ? tag.value() : 0.0;
    }

    private static float floatAt(final ListBinaryTag list, final int index) {
        if (index >= list.size()) {
            return 0f;
        }
        return list.get(index) instanceof final FloatBinaryTag tag ? tag.value() : 0f;
    }

    public static int[] uuidToInts(final UUID uuid) {
        final long msb = uuid.getMostSignificantBits();
        final long lsb = uuid.getLeastSignificantBits();
        return new int[] {(int) (msb >> 32), (int) msb, (int) (lsb >> 32), (int) lsb};
    }

    public static UUID uuidFromInts(final int[] ints) {
        final long msb = ((long) ints[0] << 32) | (ints[1] & 0xFFFFFFFFL);
        final long lsb = ((long) ints[2] << 32) | (ints[3] & 0xFFFFFFFFL);
        return new UUID(msb, lsb);
    }
}
