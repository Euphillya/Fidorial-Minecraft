package fr.fidorial.world.block.data;

import fr.fidorial.world.block.BlockData;

public interface Openable extends BlockData {

    default boolean isOpen() {
        return Boolean.parseBoolean(get("open"));
    }

    default Openable setOpen(boolean open) {
        return (Openable) with("open", String.valueOf(open));
    }
}
