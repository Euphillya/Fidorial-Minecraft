package fr.fidorial.registrygen.generate;

import fr.fidorial.registrygen.model.RegistryDefinition;
import fr.fidorial.registrygen.model.RegistryEntryDefinition;
import fr.fidorial.registrygen.model.RegistryTypeDefinition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Synthesizes a {@link RegistryDefinition} for a datapack-driven registry by scanning
 * the vanilla data dump's {@code data/minecraft/<registry path>/} directory.
 *
 * @since 0.1.0
 */
public final class DatapackRegistryDiscoverer {

    private static final String MINECRAFT_NAMESPACE = "minecraft";
    private static final String JSON_EXTENSION = ".json";

    /**
     * Scans the vanilla data dump for a registry's entries.
     *
     * @param vanillaDataDirectory root of the extracted vanilla data (the {@code data}
     *                             directory produced by the {@code --server} data
     *                             generator flag), or {@code null}/non-existent to skip
     * @param registryType         the registry to scan for
     * @return the synthesized registry definition, or empty when the registry's directory
     * doesn't exist in the vanilla data dump either
     * @throws IOException if a matching directory exists but cannot be read
     */
    public Optional<RegistryDefinition> scan(final Path vanillaDataDirectory,
                                             final RegistryTypeDefinition registryType) throws IOException {

        if (vanillaDataDirectory == null || !Files.isDirectory(vanillaDataDirectory)) {
            return Optional.empty();
        }

        final Path registryDirectory = vanillaDataDirectory.resolve(MINECRAFT_NAMESPACE).resolve(registryType.path());

        if (!Files.isDirectory(registryDirectory)) {
            return Optional.empty();
        }

        final List<String> identifiers = new ArrayList<>();

        try (final Stream<Path> walk = Files.walk(registryDirectory)) {

            walk.filter(path -> Files.isRegularFile(path) && path.toString().endsWith(JSON_EXTENSION))
                    .forEach(file -> identifiers.add(identifierOf(registryDirectory, file)));
        }

        if (identifiers.isEmpty()) {
            return Optional.empty();
        }

        identifiers.sort(Comparator.naturalOrder());

        final List<RegistryEntryDefinition> entries = new ArrayList<>();
        for (int index = 0; index < identifiers.size(); index++) {
            entries.add(new RegistryEntryDefinition(identifiers.get(index), index));
        }

        return Optional.of(new RegistryDefinition(registryType.identifier(), 0, null, entries));
    }

    private static String identifierOf(final Path registryDirectory, final Path file) {

        final String relative = registryDirectory.relativize(file).toString().replace('\\', '/');
        final String path = relative.substring(0, relative.length() - JSON_EXTENSION.length());

        return MINECRAFT_NAMESPACE + ':' + path;
    }
}
