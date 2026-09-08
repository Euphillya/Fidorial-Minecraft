package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.utils;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.codecs.item.component.DataComponentNetworkCodecs;
import fr.euphyllia.fidorial.server.item.FidorialItemRegistry;
import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.fidorial.item.ItemDefinition;
import fr.fidorial.item.ItemStack;
import fr.fidorial.item.data.DataComponentType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ItemStackWriter {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ItemStackWriter.class);

    private static final Key ITEM_REGISTRY = Key.key("item");
    private static final Key DATA_COMPONENT_TYPE_REGISTRY = Key.key("data_component_type");

    private ItemStackWriter() {
        throw new UnsupportedOperationException("ItemStackWriter cannot be instantiated.");
    }

    public static void writeSlot(final PacketBuffer buf, final ItemStack stack, final RegistryHolder frozen) {

        if (stack.isEmpty()) {
            buf.writeVarInt(0);
            return;
        }

        final ItemDefinition definition = definition(stack);
        final ItemStack wire = definition == null
                ? stack
                : new ItemStack(definition.networkType(), stack.count(),
                FidorialItemRegistry.withDefaults(definition, stack.components()));

        final int itemId = frozen.networkId(ITEM_REGISTRY, wire.id());
        if (itemId < 0) {
            LOGGER.warn("Item {} is not in the item registry; sending an empty slot instead", stack.id().asString());
            buf.writeVarInt(0);
            return;
        }

        final List<Map.Entry<DataComponentType<?>, Object>> added = encodableAdditions(wire, frozen);
        final List<Integer> removed = encodableRemovals(wire, frozen);

        buf.writeVarInt(wire.count());
        buf.writeVarInt(itemId);
        buf.writeVarInt(added.size());
        buf.writeVarInt(removed.size());

        for (final Map.Entry<DataComponentType<?>, Object> entry : added) {
            final DataComponentType<?> type = entry.getKey();
            buf.writeVarInt(frozen.networkId(DATA_COMPONENT_TYPE_REGISTRY, type.key()));
            DataComponentNetworkCodecs.writeErased(buf, frozen, type, entry.getValue());
        }

        for (final int typeId : removed) {
            buf.writeVarInt(typeId);
        }
    }

    public static int networkId(final ItemStack stack, final RegistryHolder frozen) {
        final ItemDefinition definition = definition(stack);
        final Key item = definition == null ? stack.id() : definition.networkType();
        return frozen.networkId(ITEM_REGISTRY, item);
    }

    private static @Nullable ItemDefinition definition(final ItemStack stack) {
        return FidorialServer.getInstance().items().definition(stack.id()).orElse(null);
    }

    private static List<Map.Entry<DataComponentType<?>, Object>> encodableAdditions(
            final ItemStack stack, final RegistryHolder frozen) {

        final List<Map.Entry<DataComponentType<?>, Object>> encodable = new ArrayList<>();

        for (final Map.Entry<DataComponentType<?>, Object> entry : stack.components().entries()) {
            final DataComponentType<?> type = entry.getKey();

            if (!DataComponentNetworkCodecs.hasCodec(type)) {
                LOGGER.debug("Dropping component {} on {}: no wire codec", type, stack.id().asString());
                continue;
            }
            if (frozen.networkId(DATA_COMPONENT_TYPE_REGISTRY, type.key()) < 0) {
                LOGGER.debug("Dropping component {} on {}: absent from the registry", type, stack.id().asString());
                continue;
            }
            encodable.add(entry);
        }

        return encodable;
    }

    private static List<Integer> encodableRemovals(final ItemStack stack, final RegistryHolder frozen) {

        final List<Integer> ids = new ArrayList<>();

        for (final DataComponentType<?> type : stack.components().removedTypes()) {
            final int id = frozen.networkId(DATA_COMPONENT_TYPE_REGISTRY, type.key());
            if (id >= 0) {
                ids.add(id);
            }
        }

        return ids;
    }
}
