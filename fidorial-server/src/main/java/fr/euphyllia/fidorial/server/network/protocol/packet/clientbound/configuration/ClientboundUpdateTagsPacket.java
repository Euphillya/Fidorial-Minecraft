package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.configuration;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.ConfigurationClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.registry.Registry;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.euphyllia.fidorial.server.registry.biome.FidorialBiomeRegistry;
import fr.euphyllia.fidorial.server.registry.dialog.FidorialDialogRegistry;
import fr.euphyllia.fidorial.server.registry.dimension.FidorialDimensionTypeRegistry;
import net.kyori.adventure.key.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

public record ClientboundUpdateTagsPacket(
        RegistryHolder network,
        FidorialBiomeRegistry biomes,
        FidorialDialogRegistry dialogs,
        FidorialDimensionTypeRegistry dimensionTypes
) implements ClientboundPacket {

    @Override
    public Key name() {
        return ConfigurationClientboundPackets.UPDATE_TAGS;
    }

    @Override
    public void write(final PacketBuffer buf) {
        buf.writeVarInt(network.size());

        for (final Registry reg : network.all()) {
            final Map<Key, List<Key>> tags;
            final ToIntFunction<Key> networkId;

            if (reg.name().equals(FidorialBiomeRegistry.REGISTRY_NAME)) {
                tags = reg.tags();
                networkId = biomes::networkId;
            } else if (reg.name().equals(FidorialDialogRegistry.REGISTRY_NAME)) {
                tags = dialogs.networkTags();
                networkId = dialogs::networkId;
            } else if (reg.name().equals(FidorialDimensionTypeRegistry.REGISTRY_NAME)) {
                tags = reg.tags();
                networkId = dimensionTypes::networkId;
            } else {
                tags = reg.tags();
                final List<Key> entries = reg.entries();
                networkId = entries::indexOf;
            }

            buf.writeKey(reg.name());
            buf.writeVarInt(tags.size());

            for (final Map.Entry<Key, List<Key>> tag : tags.entrySet()) {
                final List<Integer> ids = new ArrayList<>(tag.getValue().size());

                for (final Key entry : tag.getValue()) {
                    final int id = networkId.applyAsInt(entry);
                    if (id >= 0) {
                        ids.add(id);
                    }
                }

                buf.writeKey(tag.getKey());
                buf.writeVarInt(ids.size());
                for (final int id : ids) {
                    buf.writeVarInt(id);
                }
            }
        }
    }
}
