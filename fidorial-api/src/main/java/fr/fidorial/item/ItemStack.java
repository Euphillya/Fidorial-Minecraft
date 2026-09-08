package fr.fidorial.item;

import fr.fidorial.item.data.DataComponentEditor;
import fr.fidorial.item.data.DataComponentHolder;
import fr.fidorial.item.data.DataComponentMap;
import fr.fidorial.item.data.DataComponentType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.translation.Translatable;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * An item identifier, a count, and a patch over that item's default components.
 *
 * @param id         the item identifier, e.g. {@code minecraft:diamond_sword}
 * @param count      how many; {@code 0} or less means {@linkplain #isEmpty() empty}
 * @param components the patch over the item's defaults
 * @since 0.1.0
 */
public record ItemStack(Key id, int count, DataComponentMap components)
        implements DataComponentHolder, Translatable, HoverEventSource<HoverEvent.ShowItem> {

    private static final Key AIR = Key.key("air");

    /**
     * The absence of an item. Slots hold this rather than {@code null}.
     */
    public static final ItemStack EMPTY = new ItemStack(AIR, 0, DataComponentMap.EMPTY);

    public ItemStack {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(components, "components");
    }

    /**
     * @param id    the item identifier
     * @param count how many
     */
    public ItemStack(final Key id, final int count) {
        this(id, count, DataComponentMap.EMPTY);
    }

    /**
     * @param key   the item identifier
     * @param count how many
     * @return a stack with no components patched
     */
    public static ItemStack of(final Key key, final int count) {
        return new ItemStack(key, count);
    }

    /**
     * @param key the item identifier
     * @return a stack of one, with no components patched
     */
    public static ItemStack of(final Key key) {
        return new ItemStack(key, 1);
    }

    /**
     * @param key the item identifier
     * @return a builder for a stack of one
     */
    public static Builder builder(final Key key) {
        return new Builder(key, 1, DataComponentEditor.empty());
    }

    /**
     * @return a builder pre-populated with this stack
     */
    public Builder toBuilder() {
        return new Builder(id, count, editor());
    }

    /**
     * Hands back an editor over this stack's components. Changing it does
     * <em>not</em> change this stack; feed it back through
     * {@link #withComponents(DataComponentEditor)} when you are done, or use
     * {@link #edit(Consumer)} to do both at once.
     *
     * @return a fresh editor
     * @since 0.1.0
     */
    public DataComponentEditor editor() {
        return DataComponentEditor.of(components);
    }

    /**
     * @param editor the editor whose components to take
     * @return a new stack carrying those components, or this one when nothing changed
     * @since 0.1.0
     */
    public ItemStack withComponents(final DataComponentEditor editor) {
        final DataComponentMap patched = Objects.requireNonNull(editor, "editor").components();
        return patched.equals(components) ? this : new ItemStack(id, count, patched);
    }

    /**
     * Reads an editor, hands it to {@code editor}, and returns the resulting stack.
     *
     * @param editor given an editor over this stack's components
     * @return a new stack, or this one when nothing changed
     * @since 0.1.0
     */
    public ItemStack edit(final Consumer<DataComponentEditor> editor) {
        final DataComponentEditor components = editor();
        editor.accept(components);
        return withComponents(components);
    }

    /**
     * @param type  the component to set
     * @param value the value
     * @param <T>   the component's value type
     * @return a new stack
     */
    public <T> ItemStack with(final DataComponentType<T> type, final T value) {
        return new ItemStack(id, count, components.with(type, value));
    }

    /**
     * Removes the item's default for a component. See
     * {@link DataComponentMap#without(DataComponentType)} for why this differs from
     * {@link #reset(DataComponentType)}.
     *
     * @param type the component to remove
     * @return a new stack
     */
    public ItemStack without(final DataComponentType<?> type) {
        return new ItemStack(id, count, components.without(type));
    }

    /**
     * @param type the component to stop patching
     * @return a new stack
     */
    public ItemStack reset(final DataComponentType<?> type) {
        return new ItemStack(id, count, components.reset(type));
    }

    /**
     * @return {@code true} when this stack holds nothing
     */
    public boolean isEmpty() {
        return count <= 0 || id.equals(AIR);
    }

    /**
     * Whether two stacks would merge into one slot — same item, same components,
     * count ignored.
     *
     * @param other the stack to compare against, may be {@code null}
     * @return {@code true} when the two are stackable together
     */
    public boolean isSimilar(final @Nullable ItemStack other) {
        if (other == null) {
            return false;
        }
        if (isEmpty() || other.isEmpty()) {
            return isEmpty() && other.isEmpty();
        }
        return id.equals(other.id) && components.equals(other.components);
    }

    /**
     * @param newCount the new count
     * @return a new stack, or this one when the count is unchanged
     */
    public ItemStack withCount(final int newCount) {
        return newCount == count ? this : new ItemStack(id, newCount, components);
    }

    /**
     * @param delta how much to add to the count; may be negative
     * @return a new stack, clamped at {@code 0}
     */
    public ItemStack plus(final int delta) {
        return withCount(Math.max(0, count + delta));
    }

    @Override
    public HoverEvent<HoverEvent.ShowItem> asHoverEvent(final UnaryOperator<HoverEvent.ShowItem> op) {
        return HoverEvent.showItem(op.apply(HoverEvent.ShowItem.showItem(id, count)));
    }

    @Override
    public String translationKey() {
        return "item." + id.asString().replace(":", ".");
    }

    @Override
    public String toString() {
        return "ItemStack{" + id.asString() + " x" + count
                + (components.isEmpty() ? "" : ", " + components)
                + "}";
    }

    /**
     * Builds a stack a component at a time.
     *
     * <p>The builder is mutable and is not safe to share between threads; the
     * {@link ItemStack} it produces is.
     *
     * @since 0.1.0
     */
    public static final class Builder {

        private final Key id;
        private final DataComponentEditor components;
        private int count;

        private Builder(final Key id, final int count, final DataComponentEditor components) {
            this.id = Objects.requireNonNull(id, "id");
            this.count = count;
            this.components = Objects.requireNonNull(components, "components");
        }

        /**
         * @param newCount how many
         * @return this builder
         */
        public Builder count(final int newCount) {
            this.count = newCount;
            return this;
        }

        /**
         * Everything past the identifier and the count is described here, so there
         * is one place to learn rather than three that drift apart.
         *
         * @param editor given the editor this builder is filling in
         * @return this builder
         * @since 0.1.0
         */
        public Builder edit(final Consumer<DataComponentEditor> editor) {
            editor.accept(components);
            return this;
        }

        /**
         * @param type  the component to set
         * @param value the value
         * @param <T>   the component's value type
         * @return this builder
         */
        public <T> Builder set(final DataComponentType<T> type, final T value) {
            components.set(type, value);
            return this;
        }

        /**
         * @param type the component whose default to remove
         * @return this builder
         */
        public Builder remove(final DataComponentType<?> type) {
            components.remove(type);
            return this;
        }

        /**
         * @param type the component to stop patching
         * @return this builder
         * @since 0.1.0
         */
        public Builder reset(final DataComponentType<?> type) {
            components.reset(type);
            return this;
        }

        /**
         * @return the built stack
         */
        public ItemStack build() {
            return new ItemStack(id, count, components.components());
        }
    }
}
