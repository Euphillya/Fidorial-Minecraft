package fr.fidorial.registrygen.generate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.fidorial.registrygen.model.PrismarineItemDefinition;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses PrismarineJS's {@code minecraft-data} {@code items.json} report.
 *
 * @since 0.1.0
 */
public final class PrismarineItemReportParser {

    private static final int DEFAULT_STACK_SIZE = 64;

    /**
     * Parses a Prismarine {@code items.json} file into a name-keyed lookup.
     *
     * @param itemsJson path to Prismarine's {@code items.json}
     * @return item definitions keyed by plain item name, in report order
     *
     * @throws IOException if the file cannot be read or isn't the expected array shape
     */
    public Map<String, PrismarineItemDefinition> parse(final Path itemsJson) throws IOException {

        final JsonArray root;
        try (final Reader reader = Files.newBufferedReader(itemsJson, StandardCharsets.UTF_8)) {
            root = JsonParser.parseReader(reader).getAsJsonArray();
        }

        final Map<String, PrismarineItemDefinition> items = new LinkedHashMap<>();

        for (final JsonElement element : root) {

            final JsonObject itemObject = element.getAsJsonObject();
            final String name = itemObject.get("name").getAsString();

            final String displayName = itemObject.has("displayName")
                    ? itemObject.get("displayName").getAsString()
                    : name;

            final int protocolId = itemObject.has("id") ? itemObject.get("id").getAsInt() : -1;

            final int stackSize = itemObject.has("stackSize")
                    ? itemObject.get("stackSize").getAsInt()
                    : DEFAULT_STACK_SIZE;

            final int maxDurability = itemObject.has("maxDurability")
                    ? itemObject.get("maxDurability").getAsInt()
                    : 0;

            final List<String> repairWith = new ArrayList<>();
            if (itemObject.has("repairWith")) {
                for (final JsonElement material : itemObject.get("repairWith").getAsJsonArray()) {
                    repairWith.add(material.getAsString());
                }
            }

            items.put(name, new PrismarineItemDefinition(name, displayName, protocolId, stackSize, maxDurability, repairWith));
        }

        return Map.copyOf(items);
    }
}
