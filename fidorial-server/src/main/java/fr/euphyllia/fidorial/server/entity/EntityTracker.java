package fr.euphyllia.fidorial.server.entity;

import ca.spottedleaf.concurrentutil.collection.iterator.BaseObjectIterator;
import ca.spottedleaf.concurrentutil.list.COWArrayList;
import ca.spottedleaf.concurrentutil.map.concurrent.ints.ConcurrentChainedInt2ReferenceHashTable;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.network.ClientConnection;
import fr.euphyllia.fidorial.server.network.ConnectionState;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundRemoveEntitiesPacket;
import fr.fidorial.entity.Entity;
import fr.fidorial.world.Location;

import java.util.Collection;

public final class EntityTracker {

    public static final double MAX_TRACKING_RANGE = 48.0;

    private static final double UNTRACK_MARGIN = 8.0;

    public static final int UPDATE_INTERVAL_TICKS = 2;

    private final ConcurrentChainedInt2ReferenceHashTable<COWArrayList<ClientConnection>> viewers =
            ConcurrentChainedInt2ReferenceHashTable.createWithExpected(512);

    private final double trackDistanceSq;
    private final double untrackDistanceSq;

    public EntityTracker(final int sendDistanceChunks) {
        final double range = Math.min(MAX_TRACKING_RANGE, Math.max(1, sendDistanceChunks) * 16.0);
        this.trackDistanceSq = range * range;
        this.untrackDistanceSq = (range + UNTRACK_MARGIN) * (range + UNTRACK_MARGIN);
    }

    public static boolean shouldUpdate(final Entity entity, final long currentTick) {
        return Math.floorMod(currentTick + entity.entityId(), UPDATE_INTERVAL_TICKS) == 0;
    }


    public void update(final Entity entity, final Collection<ServerPlayer> players) {
        if (entity.isRemoved()) {
            untrack(entity);
            return;
        }
        if (!EntityTypes.hasNetworkId(entity.type())) {
            return;
        }

        final AbstractEntity abstractEntity = (AbstractEntity) entity;

        final COWArrayList<ClientConnection> current = viewers.computeIfAbsent(
                abstractEntity.entityId(), _ -> new COWArrayList<>(ClientConnection.class));
        final Location self = abstractEntity.location();

        for (final ServerPlayer player : players) {
            if (player.isRemoved() || player == entity) {
                continue;
            }
            final ClientConnection connection = player.connection();
            if (connection.state() != ConnectionState.PLAY) {
                continue; // re-configuring
            }
            final boolean tracked = current.contains(connection);
            final double limit = tracked ? untrackDistanceSq : trackDistanceSq;
            final boolean visible =
                    player.world() == abstractEntity.world() && distanceSq(self, player.location()) <= limit;

            if (visible && !tracked) {
                if (addIfAbsent(current, connection)) {
                    abstractEntity.sendSpawnPackets(connection);
                }
            } else if (!visible && tracked) {
                if (current.remove(connection)) {
                    connection.send(new ClientboundRemoveEntitiesPacket(abstractEntity.entityId()));
                }
            }
        }
    }

    public void sendToViewers(final AbstractEntity entity, final ClientboundPacket packet) {
        final COWArrayList<ClientConnection> current = viewers.get(entity.entityId());
        if (current == null) {
            return;
        }
        for (final ClientConnection connection : current.getArray()) {
            connection.send(packet);
        }
    }

    public void untrack(final Entity entity) {
        final COWArrayList<ClientConnection> current = viewers.remove(entity.entityId());
        if (current == null) {
            return;
        }
        final ClientConnection[] snapshot = current.getArray();
        if (snapshot.length == 0) {
            return;
        }
        final ClientboundRemoveEntitiesPacket packet = new ClientboundRemoveEntitiesPacket(entity.entityId());
        for (final ClientConnection connection : snapshot) {
            connection.send(packet);
        }
        current.clear();
    }

    public void removeViewer(final ClientConnection connection) {
        final BaseObjectIterator<COWArrayList<ClientConnection>> it = viewers.valueIterator();
        while (it.hasNext()) {
            it.next().remove(connection);
        }
    }

    public int viewerCount(final AbstractEntity entity) {
        final COWArrayList<ClientConnection> current = viewers.get(entity.entityId());
        return current == null ? 0 : current.getArray().length;
    }

    public int trackedCount() {
        return viewers.size();
    }

    private static boolean addIfAbsent(final COWArrayList<ClientConnection> list, final ClientConnection connection) {
        if (list.contains(connection)) {
            return false;
        }
        list.add(connection);
        return true;
    }

    private static double distanceSq(final Location a, final Location b) {
        final double dx = a.x() - b.x();
        final double dy = a.y() - b.y();
        final double dz = a.z() - b.z();
        return dx * dx + dy * dy + dz * dz;
    }
}
