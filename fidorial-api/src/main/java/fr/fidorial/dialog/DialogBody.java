package fr.fidorial.dialog;

import fr.fidorial.item.ItemStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

/**
 * A single element of a dialog body, that is the contents shown between the title and the
 * inputs or the action buttons.
 *
 * @see DialogBase#body()
 * @since 0.1.0
 */

public sealed interface DialogBody permits DialogBody.PlainMessage, DialogBody.Item {

    /**
     * Largest width the client accepts for a body element.
     *
     * @since 0.1.0
     */
    int MAX_WIDTH = 1024;

    /**
     * Default width of a body element.
     *
     * @since 0.1.0
     */
    int DEFAULT_WIDTH = 200;

    /**
     * Creates a multiline label of the default width.
     *
     * @param contents the text to display
     * @return the body element
     * @since 0.1.0
     */
    @Contract("_ -> new")
    static PlainMessage message(final ComponentLike contents) {
        return new PlainMessage(contents.asComponent(), DEFAULT_WIDTH);
    }

    /**
     * Creates a multiline label.
     *
     * @param contents the text to display
     * @param width    the maximum width of the message, between 1 and {@value #MAX_WIDTH}
     * @return the body element
     * @since 0.1.0
     */
    @Contract("_, _ -> new")
    static PlainMessage message(final ComponentLike contents, final int width) {
        return new PlainMessage(contents.asComponent(), width);
    }

    /**
     * Starts building an item body element.
     *
     * @param item the stack to display
     * @return a fresh builder
     * @since 0.1.0
     */
    @Contract("_ -> new")
    static Item.Builder item(final ItemStack item) {
        return new Item.Builder(item);
    }

    /**
     * A multiline label.
     *
     * @param contents the text to display
     * @param width    the maximum width of the message, between 1 and {@value DialogBody#MAX_WIDTH}
     * @since 0.1.0
     */
    record PlainMessage(Component contents, int width) implements DialogBody {

        /**
         * @param contents the text to display
         * @param width    the maximum width of the message, between 1 and {@value DialogBody#MAX_WIDTH}
         * @since 0.1.0
         */
        public PlainMessage {
            Objects.requireNonNull(contents, "contents");
            DialogValidation.width(width, MAX_WIDTH, "width");
        }
    }

    /**
     * An item, rendered as if it were sitting in an inventory slot.
     *
     * @param item           the stack to display
     * @param description    an optional label shown next to the item
     * @param showDecoration whether the stack count and damage bar are drawn over the item
     * @param showTooltip    whether hovering the item pops its tooltip up
     * @param width          horizontal size of the element, between 1 and {@value #MAX_ITEM_SIZE}
     * @param height         vertical size of the element, between 1 and {@value #MAX_ITEM_SIZE}
     * @since 0.1.0
     */
    record Item(
            ItemStack item,
            @Nullable PlainMessage description,
            boolean showDecoration,
            boolean showTooltip,
            int width,
            int height
    ) implements DialogBody {

        /**
         * Largest width or height the client accepts for an item body element.
         *
         * @since 0.1.0
         */
        public static final int MAX_ITEM_SIZE = 256;

        /**
         * Default width and height of an item body element, matching a vanilla inventory slot.
         *
         * @since 0.1.0
         */
        public static final int DEFAULT_ITEM_SIZE = 16;

        /**
         * @param item           the stack to display
         * @param description    an optional label shown next to the item
         * @param showDecoration whether the stack count and damage bar are drawn over the item
         * @param showTooltip    whether hovering the item pops its tooltip up
         * @param width          horizontal size of the element
         * @param height         vertical size of the element
         * @since 0.1.0
         */
        public Item {
            Objects.requireNonNull(item, "item");
            DialogValidation.width(width, MAX_ITEM_SIZE, "width");
            DialogValidation.width(height, MAX_ITEM_SIZE, "height");
        }

        /**
         * Fluent builder for {@link Item}.
         *
         * @since 0.1.0
         */
        public static final class Builder {

            private ItemStack item;
            private @Nullable PlainMessage description;
            private boolean showDecoration = true;
            private boolean showTooltip = true;
            private int width = DEFAULT_ITEM_SIZE;
            private int height = DEFAULT_ITEM_SIZE;

            Builder(final ItemStack item) {
                this.item = Objects.requireNonNull(item, "item");
            }

            /**
             * @param item the stack to display
             * @return this builder
             * @since 0.1.0
             */
            @Contract("_ -> this")
            public Builder item(final ItemStack item) {
                this.item = Objects.requireNonNull(item, "item");
                return this;
            }

            /**
             * @param description the label shown next to the item
             * @return this builder
             * @since 0.1.0
             */
            @Contract("_ -> this")
            public Builder description(final ComponentLike description) {
                this.description = message(description);
                return this;
            }

            /**
             * @param description the label shown next to the item, or {@code null} for none
             * @return this builder
             * @since 0.1.0
             */
            @Contract("_ -> this")
            public Builder description(final @Nullable PlainMessage description) {
                this.description = description;
                return this;
            }

            /**
             * @param showDecoration whether the stack count and damage bar are drawn over the item
             * @return this builder
             * @since 0.1.0
             */
            @Contract("_ -> this")
            public Builder showDecoration(final boolean showDecoration) {
                this.showDecoration = showDecoration;
                return this;
            }

            /**
             * @param showTooltip whether hovering the item pops its tooltip up
             * @return this builder
             * @since 0.1.0
             */
            @Contract("_ -> this")
            public Builder showTooltip(final boolean showTooltip) {
                this.showTooltip = showTooltip;
                return this;
            }

            /**
             * @param width  horizontal size of the element
             * @param height vertical size of the element
             * @return this builder
             * @since 0.1.0
             */
            @Contract("_, _ -> this")
            public Builder size(final int width, final int height) {
                this.width = width;
                this.height = height;
                return this;
            }

            /**
             * {@return the built body element}
             *
             * @since 0.1.0
             */
            @Contract("-> new")
            public Item build() {
                return new Item(item, description, showDecoration, showTooltip, width, height);
            }
        }
    }
}
