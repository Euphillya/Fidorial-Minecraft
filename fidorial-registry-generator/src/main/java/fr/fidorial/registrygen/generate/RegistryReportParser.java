package fr.fidorial.registrygen.generate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.fidorial.registrygen.model.RegistriesHolder;
import fr.fidorial.registrygen.model.RegistryDefinition;
import fr.fidorial.registrygen.model.RegistryEntryDefinition;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Parses Mojang's generated {@code reports/registries.json} file.
 *
 * @since 0.1.0
 */
public final class RegistryReportParser {

    /**
     * Parses a Mojang registry report.
     *
     * @param registriesJson path to {@code registries.json}
     * @return parsed registry definitions
     * @throws IOException if the file cannot be read or contains an
     *                     unexpected structure
     */
    public RegistriesHolder parse(final Path registriesJson) throws IOException {

        Objects.requireNonNull(registriesJson, "registriesJson");

        if (!Files.exists(registriesJson)) {
            throw new IOException("Registry report does not exist: " + registriesJson);
        }

        if (!Files.isRegularFile(registriesJson)) {
            throw new IOException("Registry report is not a regular file: " + registriesJson);
        }

        try (final Reader reader = Files.newBufferedReader(registriesJson, StandardCharsets.UTF_8)) {
            return parse(reader);
        }
    }

    private RegistriesHolder parseRoot(final JsonObject root) throws IOException {

        final List<RegistryDefinition> registries = new ArrayList<>();
        for (final Map.Entry<String, JsonElement> registryEntry : root.entrySet()) {
            final String registryIdentifier = registryEntry.getKey();

            final JsonElement registryElement = registryEntry.getValue();

            if (!registryElement.isJsonObject()) {
                throw new IOException("Registry '" + registryIdentifier + "' must be a JSON object.");
            }

            registries.add(parseRegistry(registryIdentifier, registryElement.getAsJsonObject()));
        }

        registries.sort(Comparator.comparing(RegistryDefinition::identifier));

        return new RegistriesHolder(registries);
    }

    private RegistriesHolder parse(final Reader reader) throws IOException {

        final JsonElement rootElement;

        try {
            rootElement = JsonParser.parseReader(reader);
        } catch (final RuntimeException exception) {
            throw new IOException("Failed to parse registry report.", exception);
        }

        if (!rootElement.isJsonObject()) {
            throw new IOException("Registry report root must be a JSON object.");
        }
        return parseRoot(rootElement.getAsJsonObject());
    }

    private RegistryDefinition parseRegistry(final String identifier, final JsonObject registryObject) throws IOException {

        final int protocolId = requireInt(registryObject, "protocol_id", "Registry '" + identifier + "'");
        final String defaultEntry = optionalString(registryObject, "default", "Registry '" + identifier + "'");
        final JsonObject entriesObject = requireObject(registryObject, "entries", "Registry '" + identifier + "'");

        final List<RegistryEntryDefinition> entries = parseEntries(identifier, entriesObject);

        return new RegistryDefinition(identifier, protocolId, defaultEntry, entries);
    }

    private List<RegistryEntryDefinition> parseEntries(final String registryIdentifier, final JsonObject entriesObject) throws IOException {

        final List<RegistryEntryDefinition> entries = new ArrayList<>();
        for (final Map.Entry<String, JsonElement> entry : entriesObject.entrySet()) {

            final String entryIdentifier = entry.getKey();
            final JsonElement entryElement = entry.getValue();

            if (!entryElement.isJsonObject()) {
                throw new IOException("Entry '" + entryIdentifier + "' in registry '" + registryIdentifier + "' must be a JSON object.");
            }

            final JsonObject entryObject = entryElement.getAsJsonObject();

            final int protocolId = requireInt(entryObject, "protocol_id", "Entry '" + entryIdentifier + "' in registry '" + registryIdentifier + "'");

            entries.add(new RegistryEntryDefinition(entryIdentifier, protocolId));
        }

        /*
         * Sort alphabetically by registry entry identifier so generated
         * key constants are emitted in a predictable, readable order.
         */
        entries.sort(Comparator.comparing(RegistryEntryDefinition::identifier));

        return List.copyOf(entries);
    }

    private static JsonObject requireObject(final JsonObject parent, final String property, final String context) throws IOException {

        final JsonElement element = parent.get(property);
        if (element == null || !element.isJsonObject()) {
            throw new IOException(context + " does not contain a valid '" + property + "' object.");
        }
        return element.getAsJsonObject();
    }

    private static int requireInt(final JsonObject parent, final String property, final String context) throws IOException {

        final JsonElement element = parent.get(property);
        if (element == null || !element.isJsonPrimitive() || !element.getAsJsonPrimitive().isNumber()) {
            throw new IOException(context + " does not contain a valid numeric '" + property + "' property.");
        }

        try {
            return element.getAsInt();
        } catch (final NumberFormatException exception) {
            throw new IOException(context + " contains an invalid integer '" + property + "' property.", exception);
        }
    }

    private static String optionalString(final JsonObject parent, final String property, final String context) throws IOException {

        final JsonElement element = parent.get(property);

        if (element == null || element.isJsonNull()) {
            return null;
        }

        if (!element.isJsonPrimitive() || !element.getAsJsonPrimitive().isString()) {
            throw new IOException(context + " contains a non-string '" + property + "' property.");
        }

        final String value = element.getAsString();
        if (value.isBlank()) {
            throw new IOException(context + " contains a blank '" + property + "' property.");
        }
        return value;
    }
}
