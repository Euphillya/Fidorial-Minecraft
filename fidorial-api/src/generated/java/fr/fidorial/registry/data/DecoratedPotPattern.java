package fr.fidorial.registry.data;

import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.ApiStatus;

/**
 * Marker type for entries in the {@code minecraft:decorated_pot_pattern} registry.
 *
 * <p>This interface is used as the generic type for typed registry keys.</p>
 *
 * <p>Calling {@link net.kyori.adventure.key.Keyed#key()} returns the entry's registry key.</p>
 */
@ApiStatus.NonExtendable
public interface DecoratedPotPattern extends Keyed {
}
