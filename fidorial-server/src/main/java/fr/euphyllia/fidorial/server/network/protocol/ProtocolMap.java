package fr.euphyllia.fidorial.server.network.protocol;

import fr.euphyllia.fidorial.server.network.ConnectionState;
import fr.euphyllia.fidorial.server.network.protocol.catalog.ConfigurationClientboundPacketIds;
import fr.euphyllia.fidorial.server.network.protocol.catalog.ConfigurationServerboundPacketIds;
import fr.euphyllia.fidorial.server.network.protocol.catalog.HandshakeServerboundPacketIds;
import fr.euphyllia.fidorial.server.network.protocol.catalog.LoginClientboundPacketIds;
import fr.euphyllia.fidorial.server.network.protocol.catalog.LoginServerboundPacketIds;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPacketIds;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayServerboundPacketIds;
import fr.euphyllia.fidorial.server.network.protocol.catalog.StatusClientboundPacketIds;
import fr.euphyllia.fidorial.server.network.protocol.catalog.StatusServerboundPacketIds;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static fr.euphyllia.fidorial.server.VersionConstants.MINECRAFT_VERSION_ID;

public final class ProtocolMap {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ProtocolMap.class);
    private final Map<ConnectionState, Map<Boolean, Direction>> table = new EnumMap<>(ConnectionState.class);

    private ProtocolMap() {
        put(ConnectionState.HANDSHAKE, false, HandshakeServerboundPacketIds.BY_IDENTIFIER);
        put(ConnectionState.STATUS, true, StatusClientboundPacketIds.BY_IDENTIFIER);
        put(ConnectionState.STATUS, false, StatusServerboundPacketIds.BY_IDENTIFIER);
        put(ConnectionState.LOGIN, true, LoginClientboundPacketIds.BY_IDENTIFIER);
        put(ConnectionState.LOGIN, false, LoginServerboundPacketIds.BY_IDENTIFIER);
        put(ConnectionState.CONFIGURATION, true, ConfigurationClientboundPacketIds.BY_IDENTIFIER);
        put(ConnectionState.CONFIGURATION, false, ConfigurationServerboundPacketIds.BY_IDENTIFIER);
        put(ConnectionState.PLAY, true, PlayClientboundPacketIds.BY_IDENTIFIER);
        put(ConnectionState.PLAY, false, PlayServerboundPacketIds.BY_IDENTIFIER);
    }

    public static ProtocolMap load() {
        final ProtocolMap map = new ProtocolMap();
        LOGGER.info(
                "Loaded {} protocol table from the generated packet catalogs.",
                MINECRAFT_VERSION_ID);
        return map;
    }

    private void put(final ConnectionState state, final boolean clientbound, final Map<Key, Integer> byName) {
        table.computeIfAbsent(state, s -> new HashMap<>()).put(clientbound, Direction.of(byName));
    }

    public boolean isAvailable() {
        return true;
    }

    private Direction dir(final ConnectionState state, final boolean clientbound) {
        return table.getOrDefault(state, Map.of()).getOrDefault(clientbound, Direction.empty());
    }

    public int clientboundId(final ConnectionState state, final Key name) {
        final Integer id = dir(state, true).byName().get(name);
        if (id == null)
            throw new IllegalStateException("Paquet clientbound inconnu dans la table : " + state + "/" + name);
        return id;
    }

    public @Nullable Key serverboundName(final ConnectionState state, final int id) {
        return dir(state, false).byId().get(id);
    }

    public int serverboundId(final ConnectionState state, final Key name) {
        final Integer id = dir(state, false).byName().get(name);
        if (id == null)
            throw new IllegalStateException("Paquet serverbound inconnu dans la table : " + state + "/" + name);
        return id;
    }

    private record Direction(Map<Key, Integer> byName, Map<Integer, Key> byId) {
        static Direction empty() {
            return new Direction(Map.of(), Map.of());
        }

        static Direction of(final Map<Key, Integer> byName) {
            final Map<Integer, Key> byId = new HashMap<>();
            byName.forEach((n, i) -> byId.put(i, n));
            return new Direction(Map.copyOf(byName), Map.copyOf(byId));
        }
    }
}
