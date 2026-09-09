package fr.euphyllia.fidorial.server.world.storage.datafixers.fidorial.V26_3;

import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixer;
import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerBuilder;
import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerType;
import fr.euphyllia.fidorial.server.world.storage.datafixers.fidorial.V26_3.level.V5015;

public final class DataFixers {

    private DataFixers() {
        throw new UnsupportedOperationException("DataFixers cannot be instantiated.");
    }

    public static DataFixer buildDataFixers() {
        return new DataFixerBuilder()
                .addFixer(DataFixerType.LEVEL, new V5015())
                .build();
    }
}
