package fr.euphyllia.fidorial.server;

import fr.euphyllia.fidorial.server.world.storage.datafixers.registry.DataFixersRegistry;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

public final class Main {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(Main.class);

    private Main() {
    }

    static void main(final String[] args) {
        try {
            DataFixersRegistry.bootstrap();
            final FidorialServer server = new FidorialServer();
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown, "fidorial-shutdown"));
            server.start();
        } catch (final Throwable t) {
            LOGGER.error("Fidorial could not start : ", t);
            System.exit(1);
        }
    }
}
