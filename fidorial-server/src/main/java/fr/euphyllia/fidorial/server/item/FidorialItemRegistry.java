package fr.euphyllia.fidorial.server.item;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.fidorial.item.ItemDefinition;
import fr.fidorial.item.ItemRegistry;
import fr.fidorial.item.ItemStack;
import fr.fidorial.item.data.DataComponentMap;
import fr.fidorial.item.data.DataComponentType;
import fr.fidorial.plugin.Plugin;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class FidorialItemRegistry implements ItemRegistry {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(FidorialItemRegistry.class);

    private static final Key ITEM_REGISTRY = Key.key("item");

    private final Map<Key, ItemDefinition> definitions = new ConcurrentHashMap<>();
    private final Map<Key, Object> definitionOwners = new ConcurrentHashMap<>();

    @Override
    public void register(final ItemDefinition definition, final Plugin owner) {

        final RegistryHolder frozen = FidorialServer.getInstance().registries().network();

        if (frozen.networkId(ITEM_REGISTRY, definition.networkType()) < 0) {
            throw new IllegalArgumentException(
                    "Unknown network item " + definition.networkType() + " for the item " + definition.key());
        }

        if (frozen.networkId(ITEM_REGISTRY, definition.key()) >= 0) {
            throw new IllegalArgumentException(
                    "Item " + definition.key() + " is a vanilla item and cannot be redeclared");
        }

        definitions.put(definition.key(), definition);
        definitionOwners.put(definition.key(), owner);

        LOGGER.info("Item {} registered by {} (rendered as {})",
                definition.key(), ownerName(owner), definition.networkType());
    }

    @Override
    public boolean unregister(final Key itemType) {

        final ItemDefinition removed = definitions.remove(itemType);

        if (removed == null) {
            return false;
        }

        definitionOwners.remove(itemType);

        LOGGER.info("Item {} unregistered", removed.key());
        return true;
    }

    @Override
    public void unregisterAll(final Plugin owner) {
        for (final Map.Entry<Key, Object> entry : definitionOwners.entrySet()) {
            if (entry.getValue() == owner) {
                unregister(entry.getKey());
            }
        }
    }

    @Override
    public Optional<ItemDefinition> definition(final Key itemType) {
        return Optional.ofNullable(definitions.get(itemType));
    }

    @Override
    public Collection<ItemDefinition> definitions() {
        return List.copyOf(definitions.values());
    }

    @Override
    public Set<Key> types() {

        final RegistryHolder frozen = FidorialServer.getInstance().registries().network();
        final fr.euphyllia.fidorial.server.registry.Registry items = frozen.get(ITEM_REGISTRY);

        final Set<Key> keys = new LinkedHashSet<>(items == null ? List.of() : items.entries());
        keys.addAll(definitions.keySet());

        return Set.copyOf(keys);
    }

    @Override
    public boolean isItem(final Key itemType) {
        return definitions.containsKey(itemType)
                || FidorialServer.getInstance().registries().network().networkId(ITEM_REGISTRY, itemType) >= 0;
    }

    @Override
    public boolean isCustom(final Key itemType) {
        return definitions.containsKey(itemType);
    }

    @Override
    public Key networkType(final Key itemType) {
        final ItemDefinition definition = definitions.get(itemType);
        return definition != null ? definition.networkType() : itemType;
    }

    /**
     * Lays a declared item's components underneath a stack's own, so anything the
     * caller patched wins.
     *
     * @param definition the declared item
     * @param patch      the stack's own components
     * @return the components to put on the wire
     */
    public static DataComponentMap withDefaults(final ItemDefinition definition, final DataComponentMap patch) {

        final DataComponentMap.Builder components = definition.components().toBuilder();

        for (final Map.Entry<DataComponentType<?>, Object> entry : patch.entries()) {
            setErased(components, entry.getKey(), entry.getValue());
        }

        for (final DataComponentType<?> removed : patch.removedTypes()) {
            components.remove(removed);
        }

        return components.build();
    }

    @SuppressWarnings("unchecked")
    private static <T> void setErased(final DataComponentMap.Builder components,
                                      final DataComponentType<T> type,
                                      final Object value) {
        components.set(type, (T) type.valueType().cast(value));
    }

    @Override
    public ItemStack create(final Key itemType, final int count) {

        final ItemDefinition definition = definitions.get(itemType);

        if (definition != null) {
            return new ItemStack(itemType, count, withDefaults(definition, DataComponentMap.EMPTY));
        }

        return isItem(itemType) ? ItemStack.of(itemType, count) : ItemStack.EMPTY;
    }

    private static String ownerName(final Object owner) {
        return owner == null ? "an unknown plugin" : owner.getClass().getSimpleName();
    }
}
