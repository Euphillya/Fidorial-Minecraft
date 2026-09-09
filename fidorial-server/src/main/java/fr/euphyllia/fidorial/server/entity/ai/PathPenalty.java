package fr.euphyllia.fidorial.server.entity.ai;

import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import net.kyori.adventure.key.Key;

@FunctionalInterface
public interface PathPenalty {

    Key WATER = Key.key("water");
    double BLOCKED = Double.POSITIVE_INFINITY;


    PathPenalty LAND_ANIMAL = (world, x, y, z) -> {
        final BlockState state = BlockView.blockAt(world, x, y, z);
        if (state == null) {
            return 0.0;
        }
        final Key name = state.name();
        if (name.equals(WATER)) {
            return 8.0;
        }
        if (name.value().endsWith("rail")) {
            return 16.0;
        }
        return 0.0;
    };

    double cost(ServerWorld world, int x, int y, int z);
}
