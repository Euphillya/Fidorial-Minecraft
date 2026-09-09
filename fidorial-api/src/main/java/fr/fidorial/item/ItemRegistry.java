package fr.fidorial.item;

import fr.fidorial.plugin.Plugin;
import net.kyori.adventure.key.Key;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Where items are declared: the vanilla ones, and the ones plugins invent.
 *
 * @since 0.1.0
 */
public interface ItemRegistry {

    /**
     * Declares an item of its own.
     *
     * @param definition the item to declare
     * @param owner      the plugin declaring it
     * @throws IllegalArgumentException if {@link ItemDefinition#networkType()} is not a known item,
     *                                  or if the key shadows a vanilla item
     * @since 0.1.0
     */
    void register(ItemDefinition definition, Plugin owner);

    /**
     * Drops a definition declared through {@link #register(ItemDefinition, Plugin)}.
     *
     * @param itemType the key to drop
     * @return {@code true} if a definition was registered under that key
     * @since 0.1.0
     */
    boolean unregister(Key itemType);

    /**
     * Drops every definition a plugin declared.
     *
     * @param owner the plugin to clean up after
     * @since 0.1.0
     */
    void unregisterAll(Plugin owner);

    /**
     * @param itemType the key to look up
     * @return the definition registered under that key, empty for a vanilla item
     * @since 0.1.0
     */
    Optional<ItemDefinition> definition(Key itemType);

    /**
     * @return every definition registered by a plugin
     * @since 0.1.0
     */
    Collection<ItemDefinition> definitions();

    /**
     * @return every key that can be created, vanilla items included
     * @since 0.1.0
     */
    Set<Key> types();

    /**
     * @param itemType the key to test
     * @return {@code true} when a stack can be created under that key
     * @since 0.1.0
     */
    boolean isItem(Key itemType);

    /**
     * @param itemType the key to test
     * @return {@code true} when a plugin declared this key
     * @since 0.1.0
     */
    boolean isCustom(Key itemType);

    /**
     * Resolves the vanilla item the client is sent for a key.
     *
     * @param itemType the key to resolve
     * @return the declared {@link ItemDefinition#networkType()}, or the key itself when vanilla
     * @since 0.1.0
     */
    Key networkType(Key itemType);

    /**
     * Creates a stack of one.
     *
     * @param itemType the item to create
     * @return the stack, {@link ItemStack#EMPTY} when the key is unknown
     * @since 0.1.0
     */
    default ItemStack create(final Key itemType) {
        return create(itemType, 1);
    }

    /**
     * Creates a stack, pre-filled with the definition's components for a declared item.
     *
     * @param itemType the item to create
     * @param count    how many
     * @return the stack, {@link ItemStack#EMPTY} when the key is unknown
     * @since 0.1.0
     */
    ItemStack create(Key itemType, int count);
}
