package fr.fidorial.world.block.data;

import fr.fidorial.world.block.BlockData;

public interface Rotatable extends BlockData {

    default int getRotation() {
        return Integer.parseInt(get("rotation"));
    }

    default Rotatable setRotation(int rotation) {
        return (Rotatable) with("rotation", String.valueOf(rotation));
    }
}
