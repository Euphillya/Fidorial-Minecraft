package fr.fidorial.item.component;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The extra tooltip lines a stack carries, under {@code minecraft:lore}.
 *
 * @param lines the lines, in the order they are drawn
 * @since 0.1.0
 */
public record ItemLore(List<Component> lines) {

    /**
     * The most lines the client accepts.
     */
    public static final int MAX_LINES = 256;

    /**
     * No lore at all.
     */
    public static final ItemLore EMPTY = new ItemLore(List.of());

    public ItemLore {
        Objects.requireNonNull(lines, "lines");

        if (lines.size() > MAX_LINES) {
            throw new IllegalArgumentException(
                    "Lore is limited to " + MAX_LINES + " lines, got " + lines.size());
        }

        lines = List.copyOf(lines);
    }

    /**
     * @param lines the lines, in the order they are drawn
     * @return a lore of those lines
     */
    public static ItemLore of(final Component... lines) {
        return new ItemLore(List.of(lines));
    }

    /**
     * @param lines the lines, in the order they are drawn
     * @return a lore of those lines
     */
    public static ItemLore of(final List<Component> lines) {
        return new ItemLore(lines);
    }

    /**
     * @param line the line to append
     * @return a new lore, one line longer
     */
    public ItemLore plus(final Component line) {
        Objects.requireNonNull(line, "line");

        final List<Component> copy = new ArrayList<>(lines);
        copy.add(line);

        return new ItemLore(copy);
    }

    /**
     * @return {@code true} when there is nothing to draw
     */
    public boolean isEmpty() {
        return lines.isEmpty();
    }

    /**
     * @return how many lines this lore holds
     */
    public int size() {
        return lines.size();
    }
}
