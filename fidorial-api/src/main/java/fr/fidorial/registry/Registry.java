package fr.fidorial.registry;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface Registry<T> {

    RegistryKey<T> registryKey();

    T get(TypedKey<T> key);

    Optional<T> find(TypedKey<T> key);

    TypedKey<T> key(T value);

    Collection<T> values();

    Stream<T> stream();
}
