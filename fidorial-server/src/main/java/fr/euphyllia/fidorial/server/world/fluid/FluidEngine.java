package fr.euphyllia.fidorial.server.world.fluid;

import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundBlockUpdatePacket;
import fr.euphyllia.fidorial.server.schedulers.ThreadedRegionRegionizer;
import fr.euphyllia.fidorial.server.world.BlockStateRegistry;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.WorldManager;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.euphyllia.fidorial.server.world.storage.Dimension;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.world.BlockFace;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.ChunkPos;
import fr.fidorial.world.fluid.FluidManager;
import fr.fidorial.world.fluid.FluidState;
import fr.fidorial.world.fluid.FluidType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public final class FluidEngine implements FluidManager {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(FluidEngine.class);

    private static final BlockFace[] HORIZONTAL = {
            BlockFace.NORTH, BlockFace.SOUTH,
            BlockFace.WEST, BlockFace.EAST
    };

    private final WorldManager worlds;
    private final ThreadedRegionRegionizer regionizer;
    private final BlockStateRegistry blockRegistry;
    private final Consumer<ClientboundPacket> broadcaster;

    private final Map<Key, Set<Long>> pending = new ConcurrentHashMap<>();
    private volatile LightHook lightHook = (_, _, _, _) -> {};

    public FluidEngine(
            final WorldManager worlds,
            final ThreadedRegionRegionizer regionizer,
            final BlockStateRegistry blockRegistry,
            final Consumer<ClientboundPacket> broadcaster
    ) {
        this.worlds = worlds;
        this.regionizer = regionizer;
        this.blockRegistry = blockRegistry;
        this.broadcaster = broadcaster;
    }

    private static long pack(final int x, final int y, final int z) {
        return ((x & 0x3FFFFFFL) << 38) | ((z & 0x3FFFFFFL) << 12) | (y & 0xFFFL);
    }

    @Override
    public FluidState fluidAt(final Key world, final int x, final int y, final int z) {
        final ServerWorld w = worldByKey(world);
        if (w == null) {
            return FluidState.empty();
        }
        try {
            return FluidBlockCodec.fromBlock(w.getBlock(x, y, z));
        } catch (final IOException e) {
            LOGGER.error("Fluid reading impossible at {},{},{}", x, y, z, e);
            return FluidState.empty();
        }
    }

    @Override
    public boolean placeSource(final Key world, final int x, final int y, final int z, final FluidType type) {
        final ServerWorld w = worldByKey(world);
        if (w == null) {
            return false;
        }
        final boolean applied = setAndBroadcast(w, x, y, z, FluidBlockCodec.toBlock(FluidState.source(type)));
        if (applied) {
            schedule(world, x, y, z, type.tickDelay());
        }
        return applied;
    }

    @Override
    public boolean removeFluid(final Key world, final int x, final int y, final int z) {
        final ServerWorld w = worldByKey(world);
        if (w == null) {
            return false;
        }
        if (fluidAt(world, x, y, z).isEmpty()) {
            return false;
        }
        final boolean applied = setAndBroadcast(w, x, y, z, BlockState.of(BlockTypeKeys.AIR.key()));
        if (applied) {
            notifyBlockChanged(world, x, y, z);
        }
        return applied;
    }

    @Override
    public void scheduleUpdate(final Key world, final int x, final int y, final int z) {
        final FluidState state = fluidAt(world, x, y, z);
        if (state.type() != null) {
            schedule(world, x, y, z, state.type().tickDelay());
        }
    }

    @Override
    public void notifyBlockChanged(final Key world, final int x, final int y, final int z) {
        scheduleUpdate(world, x, y, z);
        for (final BlockFace dir : BlockFace.values()) {
            scheduleUpdate(world, x + dir.dx(), y + dir.dy(), z + dir.dz());
        }
    }

    private void schedule(final Key world, final int x, final int y, final int z, final int delayTicks) {
        final Set<Long> set = pending.computeIfAbsent(world, k -> ConcurrentHashMap.newKeySet());
        final long key = pack(x, y, z);
        if (!set.add(key)) {
            return;
        }
        regionizer.executeDelayed(
                world,
                ChunkPos.fromBlock(x, z),
                () -> {
                    set.remove(key);
                    try {
                        tick(world, x, y, z);
                    } catch (final Throwable t) {
                        LOGGER.error("Tick fluide impossible en {},{},{}", x, y, z, t);
                    }
                },
                delayTicks);
    }

    private void tick(final Key worldName, final int x, final int y, final int z) throws IOException {
        final ServerWorld world = worldByKey(worldName);
        if (world == null) {
            return;
        }

        FluidState self = FluidBlockCodec.fromBlock(world.getBlock(x, y, z));
        if (self.isEmpty()) {
            return;
        }
        final FluidType type = self.type();

        // 1) Interactions lave <-> eau : la lave touchée par de l'eau se fige.
        if (type == FluidType.LAVA && touches(world, x, y, z, FluidType.WATER)) {
            final BlockState solidified = self.isSource() ? BlockState.of(BlockTypeKeys.OBSIDIAN.key()) : BlockState.of(BlockTypeKeys.COBBLESTONE.key());
            if (setAndBroadcast(world, x, y, z, solidified)) {
                notifyBlockChanged(worldName, x, y, z);
            }
            return;
        }

        // 2) Recalcul du niveau pour les fluides en écoulement.
        if (!self.isSource()) {
            final FluidState recomputed = recomputeLevel(world, worldName, x, y, z, self);
            if (recomputed == null) {
                return; // le bloc s'est asséché
            }
            self = recomputed;
        }

        // 3) Écoulement vertical prioritaire, sinon étalement horizontal.
        if (type != null && !flowDown(world, worldName, x, y, z, type)) {
            spreadHorizontally(world, worldName, x, y, z, self);
        }
    }

    private @Nullable FluidState recomputeLevel(
            final ServerWorld world,
            final Key worldName,
            final int x,
            final int y,
            final int z,
            final FluidState self
    ) throws IOException {
        final FluidType type = self.type();
        if (type == null) {
            return null;
        }
        final FluidState above = FluidBlockCodec.fromBlock(world.getBlock(x, y + 1, z));
        final boolean fedFromAbove = above.type() == type;

        int best = Integer.MAX_VALUE;
        int adjacentSources = 0;
        for (final BlockFace dir : HORIZONTAL) {
            final FluidState n = FluidBlockCodec.fromBlock(world.getBlock(x + dir.dx(), y, z + dir.dz()));
            if (n.type() == type) {
                best = Math.min(best, n.effectiveLevel() + type.dropOff());
                if (n.isSource()) {
                    adjacentSources++;
                }
            }
        }

        // Source infinie : deux sources voisines + support en dessous.
        if (type.canFormSources() && adjacentSources >= 2) {
            final BlockState belowBlock = world.getBlock(x, y - 1, z);
            final FluidState belowFluid = FluidBlockCodec.fromBlock(belowBlock);
            final boolean supported = (!belowBlock.isAir() && belowFluid.isEmpty()) || belowFluid.isSource();
            if (supported) {
                return applyIfChanged(world, worldName, x, y, z, self, FluidState.source(type));
            }
        }

        final FluidState wanted;
        if (fedFromAbove) {
            wanted = FluidState.fallingFluid(type);
        } else if (best <= type.maxSpreadLevel()) {
            wanted = FluidState.flowing(type, best);
        } else {
            // Plus alimenté : assèchement.
            if (setAndBroadcast(world, x, y, z, BlockState.of(BlockTypeKeys.AIR.key()))) {
                notifyBlockChanged(worldName, x, y, z);
            }
            return null;
        }
        return applyIfChanged(world, worldName, x, y, z, self, wanted);
    }

    private FluidState applyIfChanged(
            final ServerWorld world, final Key worldName, final int x, final int y, final int z, final FluidState current, final FluidState wanted) {
        if (wanted.equals(current)) {
            return current;
        }
        if (setAndBroadcast(world, x, y, z, FluidBlockCodec.toBlock(wanted))) {
            notifyBlockChanged(worldName, x, y, z);
        }
        return wanted;
    }

    private boolean flowDown(final ServerWorld world, final Key worldName, final int x, final int y, final int z, final FluidType type)
            throws IOException {
        final BlockState belowBlock = world.getBlock(x, y - 1, z);
        final FluidState below = FluidBlockCodec.fromBlock(belowBlock);

        // La lave qui tombe dans l'eau se fige en pierre taillée.
        if (type == FluidType.LAVA && below.type() == FluidType.WATER) {
            if (setAndBroadcast(world, x, y - 1, z, BlockState.of(BlockTypeKeys.COBBLESTONE.key()))) {
                notifyBlockChanged(worldName, x, y - 1, z);
            }
            return false;
        }

        if (below.type() == type) {
            if (below.isSource() || below.falling()) {
                return true; // la colonne coule déjà
            }
            // Un écoulement horizontal en dessous est remplacé par une chute.
            if (setAndBroadcast(world, x, y - 1, z, FluidBlockCodec.toBlock(FluidState.fallingFluid(type)))) {
                schedule(worldName, x, y - 1, z, type.tickDelay());
            }
            return true;
        }

        if (belowBlock.isAir()) {
            if (setAndBroadcast(world, x, y - 1, z, FluidBlockCodec.toBlock(FluidState.fallingFluid(type)))) {
                schedule(worldName, x, y - 1, z, type.tickDelay());
            }
            return true;
        }
        if (below.type() != null) {
            schedule(worldName, x, y - 1, z, below.type().tickDelay());
        }
        return false; // bloqué : étalement horizontal
    }

    private void spreadHorizontally(final ServerWorld world, final Key worldName, final int x, final int y, final int z, final FluidState self) {
        final FluidType type = self.type();
        if (type == null) {
            return;
        }
        final int spreadLevel = self.effectiveLevel() + type.dropOff();
        if (spreadLevel > type.maxSpreadLevel()) {
            return;
        }
        for (final BlockFace dir : HORIZONTAL) {
            spreadTo(world, worldName, x + dir.dx(), y, z + dir.dz(), type, spreadLevel);
        }
    }

    private void spreadTo(final ServerWorld world, final Key worldName, final int tx, final int y, final int tz, final FluidType type, final int spreadLevel) {
        final ChunkPos targetChunk = ChunkPos.fromBlock(tx, tz);
        if (world.scheduler().isOwnedByCurrentThread(worldName, targetChunk)) {
            spreadToOwned(world, worldName, tx, y, tz, type, spreadLevel);
            return;
        }
        // the neighbor crosses into a region we don't currently own
        regionizer.execute(worldName, targetChunk,
                () -> spreadToOwned(world, worldName, tx, y, tz, type, spreadLevel));
    }

    private void spreadToOwned(final ServerWorld world, final Key worldName, final int tx, final int y, final int tz, final FluidType type, final int spreadLevel) {
        try {
            final BlockState targetBlock = world.getBlock(tx, y, tz);
            final FluidState target = FluidBlockCodec.fromBlock(targetBlock);

            final boolean canFlow = targetBlock.isAir()
                    || (target.type() == type && !target.isSource() && target.effectiveLevel() > spreadLevel);
            if (canFlow) {
                if (setAndBroadcast(world, tx, y, tz, FluidBlockCodec.toBlock(FluidState.flowing(type, spreadLevel)))) {
                    schedule(worldName, tx, y, tz, type.tickDelay());
                }
            } else if (target.type() != null && target.type() != type) {
                schedule(worldName, tx, y, tz, target.type().tickDelay());
            }
        } catch (final IOException e) {
            LOGGER.error("Fluid spread impossible at {},{},{}", tx, y, tz, e);
        }
    }

    private boolean touches(final ServerWorld world, final int x, final int y, final int z, final FluidType other) throws IOException {
        if (FluidBlockCodec.fromBlock(world.getBlock(x, y + 1, z)).type() == other) {
            return true;
        }
        for (final BlockFace dir : HORIZONTAL) {
            if (FluidBlockCodec.fromBlock(world.getBlock(x + dir.dx(), y, z + dir.dz()))
                    .type()
                    == other) {
                return true;
            }
        }
        return false;
    }

    private boolean setAndBroadcast(final ServerWorld world, final int x, final int y, final int z, final BlockState state) {
        try {
            if (!world.setBlock(x, y, z, state)) {
                return false;
            }
        } catch (final IOException e) {
            LOGGER.error("Fluid writing impossible in {},{},{}", x, y, z, e);
            return false;
        }
        broadcaster.accept(new ClientboundBlockUpdatePacket(new BlockPos(x, y, z), blockRegistry.networkId(state)));
        lightHook.onBlockChanged(world.dimension().id(), x, y, z);
        return true;
    }

    private ServerWorld worldByKey(@Nullable final Key key) {
        if (key == null || Dimension.OVERWORLD.id().equals(key)) {
            return worlds.overworld();
        }
        return worlds.dimension(Dimension.datapack(key));
    }

    public void setLightHook(final LightHook hook) {
        this.lightHook = hook;
    }

    @FunctionalInterface
    public interface LightHook {
        void onBlockChanged(Key world, int x, int y, int z);
    }
}
