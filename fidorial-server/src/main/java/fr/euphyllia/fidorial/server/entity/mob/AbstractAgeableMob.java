package fr.euphyllia.fidorial.server.entity.mob;

import fr.euphyllia.fidorial.server.network.ClientConnection;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundSetEntityMetadataPacket;
import fr.fidorial.entity.EntityType;
import fr.fidorial.world.Location;
import fr.fidorial.world.World;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractAgeableMob extends AbstractPathfinderMob {

    public static final int GROW_UP_TICKS = 24_000;

    private static final int MD_BABY = 16;

    private static final double FEED_GROWTH_FRACTION = 0.10;

    private boolean baby;
    private int growthTicks;
    private boolean growthFrozen;
    private boolean babyMetadataSent;

    protected AbstractAgeableMob(final int entityId, final UUID uuid, final EntityType type, final World world,
                                 final Location location, final float maxHealth, final boolean baby) {
        super(entityId, uuid, type, world, location, maxHealth);
        this.baby = baby;
        this.growthTicks = baby ? GROW_UP_TICKS : 0;
    }

    @Override
    public void tick(final long currentTick) {
        super.tick(currentTick);
        if (isRemoved() || isDead()) {
            return;
        }

        if (!babyMetadataSent) {
            babyMetadataSent = true;
            sendBabyMetadata();
        }

        if (baby && !growthFrozen && --growthTicks <= 0) {
            growUp();
        }
    }

    public final boolean isBaby() {
        return baby;
    }

    public final int growthTicks() {
        return growthTicks;
    }

    public final void restoreAge(final boolean baby, final int growthTicks) {
        this.baby = baby;
        this.growthTicks = baby ? Math.max(0, growthTicks) : 0;
        sendBabyMetadata();
    }

    public final void accelerateGrowth() {
        if (!baby) {
            return;
        }
        growthFrozen = false;
        growthTicks -= (int) Math.ceil(growthTicks * FEED_GROWTH_FRACTION);
        if (growthTicks <= 0) {
            growUp();
        }
    }

    public final void toggleGrowthFreeze() {
        if (!baby) {
            return;
        }
        if (growthFrozen) {
            growthFrozen = false;
        } else {
            growthFrozen = true;
            growthTicks = GROW_UP_TICKS;
        }
    }

    public final boolean isGrowthFrozen() {
        return growthFrozen;
    }

    protected void growUp() {
        if (!baby) {
            return;
        }
        baby = false;
        growthTicks = 0;
        growthFrozen = false;
        sendBabyMetadata();
        onGrownUp();
    }

    protected void onGrownUp() {
    }

    protected abstract double adultHeight();

    protected abstract double adultWidth();

    protected double babyHeight() {
        return adultHeight() * 0.5;
    }

    protected double babyWidth() {
        return adultWidth() * 0.5;
    }

    @Override
    public final double height() {
        return baby ? babyHeight() : adultHeight();
    }

    @Override
    public final double width() {
        return baby ? babyWidth() : adultWidth();
    }

    @Override
    protected final double halfWidth() {
        return width() * 0.5;
    }


    protected final float voicePitch(final float min, final float max, final float babyBonus) {
        final float base = min + ThreadLocalRandom.current().nextFloat() * (max - min);
        return baby ? base + babyBonus : base;
    }

    private void sendBabyMetadata() {
        sendToTrackers(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofBoolean(MD_BABY, baby)));
    }

    @Override
    public void sendSpawnPackets(final ClientConnection connection) {
        super.sendSpawnPackets(connection);
        connection.send(ClientboundSetEntityMetadataPacket.of(entityId(),
                ClientboundSetEntityMetadataPacket.Entry.ofBoolean(MD_BABY, baby)));
    }
}
