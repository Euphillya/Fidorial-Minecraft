package fr.fidorial.command.argument.predicate;

import fr.fidorial.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Predicate;

/**
 * A {@link Predicate} matching {@link ItemStack}s, produced by
 * parsing an item predicate argument.
 *
 * @since 0.1.0
 */
@ApiStatus.NonExtendable
public interface ItemStackPredicate extends Predicate<ItemStack> {
}
