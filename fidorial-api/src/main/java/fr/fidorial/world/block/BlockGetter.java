package fr.fidorial.world.block;

import fr.fidorial.world.BlockPos;
import org.jspecify.annotations.Nullable;

@FunctionalInterface
public interface BlockGetter {

    @Nullable BlockData blockAt(BlockPos pos);
}
