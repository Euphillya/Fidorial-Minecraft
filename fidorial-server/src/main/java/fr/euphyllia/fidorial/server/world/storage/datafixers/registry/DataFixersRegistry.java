package fr.euphyllia.fidorial.server.world.storage.datafixers.registry;

import ca.spottedleaf.converter.types.ListType;
import ca.spottedleaf.converter.types.MapType;
import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixer;
import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerType;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public final class DataFixersRegistry {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(DataFixersRegistry.class);

    private static final List<CompletableFuture<DataFixer>> CHAIN = new ArrayList<>();

    private static final CompletableFuture<DataFixer> V26_3 = register("26.3",
            fr.euphyllia.fidorial.server.world.storage.datafixers.minecraft.V26_3.DataFixers::buildDataFixers);
    private static final CompletableFuture<DataFixer> FIDORIAL_V26_3 = register("fidorial-26.3",
            fr.euphyllia.fidorial.server.world.storage.datafixers.fidorial.V26_3.DataFixers::buildDataFixers);

    private DataFixersRegistry() {
        throw new UnsupportedOperationException("DataFixersRegistry cannot be instantiated.");
    }

    public static void initialize() {
    }

    public static int latestDataFixerVersion() {
        int max = 0;
        for (final CompletableFuture<DataFixer> future : CHAIN) {
            max = Math.max(max, future.join().targetDataVersion());
        }
        return max;
    }

    @Nullable public static MapType update(final DataFixerType type, final @Nullable MapType data, final int sourceDataVersion) {
        if (data == null) {
            return null;
        }
        MapType current = data;
        for (final CompletableFuture<DataFixer> future : CHAIN) {
            current = future.join().update(type, current, sourceDataVersion);
        }
        return current;
    }

    public static void updateList(final DataFixerType type, final @Nullable ListType list, final int sourceDataVersion) {
        if (list == null) {
            return;
        }
        for (int i = 0, len = list.size(); i < len; ++i) {
            final MapType element = list.getMap(i, null);
            if (element != null) {
                update(type, element, sourceDataVersion);
            }
        }
    }

    private static CompletableFuture<DataFixer> register(final String name, final Supplier<DataFixer> builder) {
        final CompletableFuture<DataFixer> future = CompletableFuture.supplyAsync(() -> {
            final long start = System.nanoTime();
            final DataFixer fixer = builder.get();
            LOGGER.debug("{} datafixers built in {}ms (target DataVersion {})",
                    name, (System.nanoTime() - start) / 1_000_000, fixer.targetDataVersion());
            return fixer;
        }, r -> Thread.ofPlatform().name("fidorial-datafixer-bootstrap-" + name).start(r));
        CHAIN.add(future);
        return future;
    }
}
