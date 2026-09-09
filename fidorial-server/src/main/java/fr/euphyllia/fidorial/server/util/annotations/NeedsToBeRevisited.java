package fr.euphyllia.fidorial.server.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method that needs to be revisited in the future, either due to possible issues with it, an incomplete implementation or something else.
 * Annotating a method with this {@code annotation} produces compile-time warnings.
 * @since 0.1.0
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface NeedsToBeRevisited {
    /**
     * The reason for why this needs to be revisited
     */
    String value();
}
