package fr.fidorial.item;

import fr.fidorial.item.data.DataComponentHolder;
import fr.fidorial.item.data.DataComponentTypes;
import net.kyori.adventure.key.Key;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Per-item defaults, as opposed to what a particular stack patches through its
 * components.
 *
 * @since 0.1.0
 */
public final class ItemDefaults {

    /**
     * An item's defaults.
     *
     * @param maxStackSize how many of this item fit in one slot
     * @param maxDamage    total durability, {@code 0} when the item cannot break
     */
    public record Properties(int maxStackSize, int maxDamage) {

        public Properties {
            if (maxStackSize < 1) {
                throw new IllegalArgumentException("maxStackSize must be at least 1, got " + maxStackSize);
            }

            if (maxDamage < 0) {
                throw new IllegalArgumentException("maxDamage cannot be negative, got " + maxDamage);
            }
        }
    }

    /**
     * Supplies the defaults of items the server knows about. Implemented over the
     * generated table.
     */
    public interface Source {

        /**
         * @param item namespaced item identifier
         * @return how many of this item fit in one slot
         */
        int maxStackSize(Key item);

        /**
         * @param item namespaced item identifier
         * @return total durability, or {@code 0} when the item cannot break
         */
        int maxDamage(Key item);
    }

    private static final int DEFAULT_STACK_SIZE = 64;

    /**
     * Used until the server installs the generated table: everything stacks to 64
     * and nothing breaks.
     */
    private static final Source FALLBACK = new Source() {

        @Override
        public int maxStackSize(final Key item) {
            return DEFAULT_STACK_SIZE;
        }

        @Override
        public int maxDamage(final Key item) {
            return 0;
        }
    };

    private static final Map<Key, Properties> OVERRIDES = new ConcurrentHashMap<>();

    private static volatile Source source = FALLBACK;

    private ItemDefaults() {
        throw new UnsupportedOperationException("ItemDefaults cannot be instantiated.");
    }

    /**
     * Installs the table consulted for items that have no registered override.
     *
     * @param source the table to read from; replaces whatever was installed before
     */
    public static void install(final Source source) {
        ItemDefaults.source = Objects.requireNonNull(source, "source");
    }

    /**
     * Records an item's defaults, taking precedence over the installed source. Use
     * this to declare a custom item, or to override a vanilla one.
     *
     * @param item       namespaced item identifier
     * @param properties the defaults to record
     */
    public static void register(final Key item, final Properties properties) {
        OVERRIDES.put(Objects.requireNonNull(item, "item"), Objects.requireNonNull(properties, "properties"));
    }

    /**
     * Drops a previously registered override, falling back to the installed source.
     *
     * @param item namespaced item identifier
     * @return {@code true} when an override was removed
     */
    public static boolean unregister(final Key item) {
        return OVERRIDES.remove(Objects.requireNonNull(item, "item")) != null;
    }

    /**
     * @param item namespaced item identifier
     * @return {@code true} when this item has registered defaults of its own
     */
    public static boolean isRegistered(final Key item) {
        return OVERRIDES.containsKey(Objects.requireNonNull(item, "item"));
    }

    /**
     * @param item namespaced item identifier
     * @return how many of this item fit in one slot
     */
    public static int maxStackSize(final Key item) {
        final Properties override = OVERRIDES.get(item);
        return override != null ? override.maxStackSize() : source.maxStackSize(item);
    }

    /**
     * @param item namespaced item identifier
     * @return total durability, or {@code 0} when the item cannot break
     */
    public static int maxDamage(final Key item) {
        final Properties override = OVERRIDES.get(item);
        return override != null ? override.maxDamage() : source.maxDamage(item);
    }

    /**
     * Resolves how many fit in one slot: what {@code patch} says, and failing that
     * the item's own limit.
     *
     * @param item  namespaced item identifier
     * @param patch the stack's components
     * @return how many of this item fit in one slot
     * @since 0.1.0
     */
    public static int maxStackSize(final Key item, final DataComponentHolder patch) {
        final Integer patched = patch.get(DataComponentTypes.MAX_STACK_SIZE);
        return patched != null ? patched : maxStackSize(item);
    }

    /**
     * Resolves total durability: what {@code patch} says, and failing that the
     * item's own.
     *
     * @param item  namespaced item identifier
     * @param patch the stack's components
     * @return total durability, or {@code 0} when the item cannot break
     * @since 0.1.0
     */
    public static int maxDamage(final Key item, final DataComponentHolder patch) {
        final Integer patched = patch.get(DataComponentTypes.MAX_DAMAGE);
        return patched != null ? patched : maxDamage(item);
    }
}
