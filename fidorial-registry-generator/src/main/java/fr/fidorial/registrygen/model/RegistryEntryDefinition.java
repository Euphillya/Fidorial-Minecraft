package fr.fidorial.registrygen.model;

import java.util.Objects;

/**
 * The RegistryEntryDefinition record represents an immutable definition for a single
 * registry entry in a registry system. It encapsulates a unique identifier for the entry
 * and its associated protocol ID.
 *
 * @since 0.1.0
 */
public record RegistryEntryDefinition(String identifier, int protocolId) {

    public RegistryEntryDefinition {

        Objects.requireNonNull(identifier, "identifier");

        if (identifier.isBlank()) {
            throw new IllegalArgumentException("Registry entry identifier cannot be blank.");
        }

        if (protocolId < 0) {
            throw new IllegalArgumentException("Registry entry protocol ID cannot be negative.");
        }
    }
}
