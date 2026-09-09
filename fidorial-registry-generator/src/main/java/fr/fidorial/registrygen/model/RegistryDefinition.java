package fr.fidorial.registrygen.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an immutable definition for a registry in a registry system.
 * Each registry is identified by a unique identifier and contains a set of registry entries,
 * a protocol ID, and an optional default entry.
 *
 * @since 0.1.0
 */
public record RegistryDefinition(String identifier,
                                 int protocolId,
                                 String defaultEntry,
                                 List<RegistryEntryDefinition> entries) {

    public RegistryDefinition {

        Objects.requireNonNull(identifier, "identifier");
        Objects.requireNonNull(entries, "entries");

        if (identifier.isBlank()) {
            throw new IllegalArgumentException("Registry identifier cannot be blank.");
        }

        if (protocolId < 0) {
            throw new IllegalArgumentException("Registry protocol ID cannot be negative.");
        }
        entries = List.copyOf(entries);
    }

    public Optional<String> defaultEntryOptional() {
        return Optional.ofNullable(defaultEntry);
    }
}
