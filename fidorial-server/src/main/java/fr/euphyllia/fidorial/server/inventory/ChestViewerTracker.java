package fr.euphyllia.fidorial.server.inventory;

import fr.fidorial.world.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.ObjIntConsumer;

public final class ChestViewerTracker {

    private final Map<BlockPos, Integer> viewers = new ConcurrentHashMap<>();

    /**
     * Records an opening and notifies the callback with the new viewer count.
     */
    public void open(final BlockPos pos, final ObjIntConsumer<BlockPos> onChanged) {
        final int count = viewers.merge(pos, 1, Integer::sum);
        onChanged.accept(pos, count);
    }

    /**
     * Records a closing and notifies the callback with the new viewer count.
     */
    public void close(final BlockPos pos, final ObjIntConsumer<BlockPos> onChanged) {
        final Integer remaining = viewers.compute(pos, (key, current) -> {
            if (current == null || current <= 1) {
                return null;
            }
            return current - 1;
        });
        onChanged.accept(pos, remaining == null ? 0 : remaining);
    }

    public void forget(final BlockPos pos) {
        viewers.remove(pos);
    }

    public int count(final BlockPos pos) {
        return viewers.getOrDefault(pos, 0);
    }
}
