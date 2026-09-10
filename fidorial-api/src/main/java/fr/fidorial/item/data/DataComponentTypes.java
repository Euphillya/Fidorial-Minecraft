package fr.fidorial.item.data;

import fr.fidorial.item.component.ItemLore;
import fr.fidorial.item.component.SwingAnimation;
import fr.fidorial.registry.keys.DataComponentTypeKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DataComponentTypes {

    private static final Map<Key, DataComponentType<?>> BY_KEY = new LinkedHashMap<>();

    /**
     * How many of this item fit in one slot, {@code 1}-{@code 99}.
     */
    public static final DataComponentType<Integer> MAX_STACK_SIZE =
            register(DataComponentTypeKeys.MAX_STACK_SIZE.key(), Integer.class);

    /**
     * Total durability. Absent on items that cannot break.
     */
    public static final DataComponentType<Integer> MAX_DAMAGE =
            register(DataComponentTypeKeys.MAX_DAMAGE.key(), Integer.class);

    /**
     * Durability already spent. {@code 0} is pristine.
     */
    public static final DataComponentType<Integer> DAMAGE =
            register(DataComponentTypeKeys.DAMAGE.key(), Integer.class);

    /**
     * A player-assigned name, drawn in italics. This is what an anvil sets.
     */
    public static final DataComponentType<Component> CUSTOM_NAME =
            register(DataComponentTypeKeys.CUSTOM_NAME.key(), Component.class);

    /**
     * The item's own name, drawn upright. Unlike {@link #CUSTOM_NAME} it survives
     * being renamed and does not mark the item as user-named.
     */
    public static final DataComponentType<Component> ITEM_NAME =
            register(DataComponentTypeKeys.ITEM_NAME.key(), Component.class);

    /**
     * The model a resource pack draws for this item, overriding the one implied by
     * the item id.
     */
    public static final DataComponentType<Key> ITEM_MODEL =
            register(DataComponentTypeKeys.ITEM_MODEL.key(), Key.class);

    /**
     * Extra tooltip lines, drawn under the item's name. Capped at
     * {@value ItemLore#MAX_LINES} lines.
     */
    public static final DataComponentType<ItemLore> LORE =
            register(DataComponentTypeKeys.LORE.key(), ItemLore.class);

    /**
     * The animation to play when this item is used to attack something.
     */
    public static final DataComponentType<SwingAnimation> ATTACK_ANIMATION =
            register(DataComponentTypeKeys.ATTACK_ANIMATION.key(), SwingAnimation.class);

    /**
     * The animation to play when this item is used to interact with something.
     */
    public static final DataComponentType<SwingAnimation> INTERACT_ANIMATION =
            register(DataComponentTypeKeys.INTERACT_ANIMATION.key(), SwingAnimation.class);

    private DataComponentTypes() {
        throw new UnsupportedOperationException("DataComponentTypes cannot be instantiated.");
    }

    private static <T> DataComponentType<T> register(final Key key, final Class<T> valueType) {
        final DataComponentType<T> type = DataComponentType.of(key, valueType);
        BY_KEY.put(key, type);
        return type;
    }

    /**
     * Looks up a modelled component by its registry key.
     *
     * @param key the registry key, e.g. {@code minecraft:lore}
     * @return the component type, or {@code null} when Fidorial does not model it
     */
    public static @Nullable DataComponentType<?> byKey(final Key key) {
        return BY_KEY.get(Objects.requireNonNull(key, "key"));
    }

    /**
     * @param key the registry key
     * @return {@code true} when Fidorial has a typed handle for that component
     */
    public static boolean isModelled(final Key key) {
        return BY_KEY.containsKey(Objects.requireNonNull(key, "key"));
    }

    /**
     * @return every modelled component type, in declaration order
     */
    public static Collection<DataComponentType<?>> values() {
        return Collections.unmodifiableCollection(BY_KEY.values());
    }


}
