package fr.fidorial.registrygen.generate;

import fr.fidorial.registrygen.model.BlockReportDefinition;
import fr.fidorial.registrygen.model.PacketCatalogs;
import fr.fidorial.registrygen.model.PrismarineBlockLightPropertiesDefinition;
import fr.fidorial.registrygen.model.PrismarineItemDefinition;
import fr.fidorial.registrygen.model.ProtocolIdRegistries;
import fr.fidorial.registrygen.model.ProtocolIdTarget;
import fr.fidorial.registrygen.model.RegistriesHolder;
import fr.fidorial.registrygen.model.RegistryDefinition;
import fr.fidorial.registrygen.model.RegistryEntryDefinition;
import fr.fidorial.registrygen.model.RegistrySync;
import fr.fidorial.registrygen.model.RegistryTagDefinition;
import fr.fidorial.registrygen.model.RegistryTypeDefinition;
import fr.fidorial.registrygen.model.SupportedRegistries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Coordinates source generation from Mojang's
 * {@code reports/registries.json} file.
 *
 * @since 0.1.0
 */
public final class RegistryGenerator {

    private final RegistryReportParser parser;
    private final RegistryTagReportParser tagReportParser;
    private final RegistryDataGenerator dataGenerator;
    private final RegistryKeysGenerator keysGenerator;
    private final RegistryDatasetGenerator datasetGenerator;
    private final RegistryKeyGenerator registryKeyGenerator;
    private final RegistryProtocolIdGenerator protocolIdGenerator;
    private final BlockReportParser blockReportParser;
    private final PrismarineBlockReportParser prismarineBlockReportParser;
    private final BlockStateGenerator blockStateGenerator;
    private final DimensionTypesGenerator dimensionTypesGenerator;
    private final PrismarineItemReportParser prismarineItemReportParser;
    private final ItemPropertiesGenerator itemPropertiesGenerator;
    private final FrozenRegistriesGenerator frozenRegistriesGenerator;

    /**
     * Creates a registry generator using the standard parser and
     * JavaPoet generators.
     */
    public RegistryGenerator() {

        this(new RegistryReportParser(),
                new RegistryTagReportParser(),
                new RegistryDataGenerator(),
                new RegistryKeysGenerator(),
                new RegistryDatasetGenerator(),
                new RegistryKeyGenerator(),
                new RegistryProtocolIdGenerator(),
                new BlockReportParser(),
                new PrismarineBlockReportParser(),
                new BlockStateGenerator(),
                new DimensionTypesGenerator());
    }

    /**
     * Creates a registry generator with explicitly supplied components.
     *
     * <p>This constructor is useful for testing or replacing individual
     * generation stages.</p>
     *
     * @param parser                      registry report parser
     * @param tagReportParser             vanilla tag file parser
     * @param dataGenerator               marker-interface generator
     * @param keysGenerator               typed registry-entry key generator
     * @param datasetGenerator            runtime registry dataset generator
     * @param registryKeyGenerator        central registry-key generator
     * @param protocolIdGenerator         raw protocol ID constant generator
     * @param blockReportParser           blocks report parser
     * @param prismarineBlockReportParser prismarine block report parser
     * @param blockStateGenerator         block type registration generator
     * @param dimensionTypesGenerator     the dimension types generator
     */
    public RegistryGenerator(final RegistryReportParser parser,
                             final RegistryTagReportParser tagReportParser,
                             final RegistryDataGenerator dataGenerator,
                             final RegistryKeysGenerator keysGenerator,
                             final RegistryDatasetGenerator datasetGenerator,
                             final RegistryKeyGenerator registryKeyGenerator,
                             final RegistryProtocolIdGenerator protocolIdGenerator,
                             final BlockReportParser blockReportParser,
                             final PrismarineBlockReportParser prismarineBlockReportParser,
                             final BlockStateGenerator blockStateGenerator,
                             final DimensionTypesGenerator dimensionTypesGenerator) {

        this.parser = Objects.requireNonNull(parser, "parser");
        this.tagReportParser = Objects.requireNonNull(tagReportParser, "tagReportParser");
        this.dataGenerator = Objects.requireNonNull(dataGenerator, "dataGenerator");
        this.keysGenerator = Objects.requireNonNull(keysGenerator, "keysGenerator");
        this.datasetGenerator = Objects.requireNonNull(datasetGenerator, "datasetGenerator");
        this.registryKeyGenerator = Objects.requireNonNull(registryKeyGenerator, "registryKeyGenerator");
        this.protocolIdGenerator = Objects.requireNonNull(protocolIdGenerator, "protocolIdGenerator");
        this.blockReportParser = Objects.requireNonNull(blockReportParser, "blockReportParser");
        this.prismarineBlockReportParser = Objects.requireNonNull(prismarineBlockReportParser, "prismarineBlockReportParser");
        this.blockStateGenerator = Objects.requireNonNull(blockStateGenerator, "blockStateGenerator");
        this.dimensionTypesGenerator = Objects.requireNonNull(dimensionTypesGenerator, "dimensionTypesGenerator");
        this.prismarineItemReportParser = new PrismarineItemReportParser();
        this.itemPropertiesGenerator = new ItemPropertiesGenerator();
        this.frozenRegistriesGenerator = new FrozenRegistriesGenerator();
    }

    /**
     * Parses a Mojang registry report and generates all configured
     * Fidorial registry source files.
     *
     * @param registriesJson      path to Mojang's {@code registries.json}
     * @param vanillaDataDirectory root of the vanilla data dump produced by the
     *                             {@code --server} data generator flag (the directory
     *                             directly containing {@code minecraft/}), used to
     *                             resolve each registry's tags. May be {@code null} or
     *                             non-existent, in which case the dataset is written
     *                             with empty tags.
     * @param outputDirectory     generated Java source root
     * @param datasetDirectory    directory receiving {@code registries_frozen.json} and
     *                            {@code registries_dynamic.json}, or {@code null} to skip them
     * @param registryTypes       the registries to generate
     * @param dataPackage         the subpackage for generated registry marker interfaces
     * @param keysPackage         the subpackage for generated registry keys
     * @param registryPackage     the package for generated registries
     * @param generateRegistryKey whether to generate the {@code RegistryKey} class
     * @throws IOException if parsing or source generation fails
     */
    public void generate(final Path registriesJson,
                         final Path vanillaDataDirectory,
                         final Path outputDirectory,
                         final Path datasetDirectory,
                         final List<RegistryTypeDefinition> registryTypes,
                         final String registryPackage,
                         final String dataPackage,
                         final String keysPackage,
                         final boolean generateRegistryKey) throws IOException {

        Objects.requireNonNull(registriesJson, "registriesJson");
        Objects.requireNonNull(outputDirectory, "outputDirectory");
        Objects.requireNonNull(registryTypes, "registryTypes");
        Objects.requireNonNull(registryPackage, "registryPackage");
        Objects.requireNonNull(dataPackage, "dataPackage");
        Objects.requireNonNull(keysPackage, "keysPackage");

        validateInput(registriesJson);

        Files.createDirectories(outputDirectory);

        final RegistriesHolder registries = parser.parse(registriesJson);

        final Map<String, RegistryDefinition> generatedRegistries = new LinkedHashMap<>();
        final Map<String, List<RegistryTagDefinition>> resolvedTags = new LinkedHashMap<>();

        for (final RegistryTypeDefinition registryType : registryTypes) {

            final Optional<RegistryDefinition> registryDefinition = registries.registry(registryType.identifier());
            if (registryDefinition.isEmpty()) {
                System.out.println("Registry missing from report: " + registryType.identifier());
                continue;
            }

            /*
             * Registries that only ever travel over the wire as a VarInt are
             * emitted as plain int constants instead of typed keys.
             */
            final Optional<ProtocolIdTarget> protocolIdTarget =
                    ProtocolIdRegistries.byIdentifier(registryType.identifier());

            if (protocolIdTarget.isPresent()) {
                protocolIdGenerator.generate(registryDefinition.get(), protocolIdTarget.get(), outputDirectory);
                continue;
            }

            generatedRegistries.put(registryType.identifier(), registryDefinition.get());

            if (registryType.sync() != RegistrySync.NONE) {

                final Set<String> knownEntryIdentifiers = registryDefinition.get().entries().stream()
                        .map(RegistryEntryDefinition::identifier)
                        .collect(Collectors.toUnmodifiableSet());

                resolvedTags.put(registryType.identifier(),
                        tagReportParser.parse(vanillaDataDirectory, registryType.path(), knownEntryIdentifiers));
            }

            dataGenerator.generate(registryType, registryType.dataPackage(dataPackage), outputDirectory);
            keysGenerator.generate(
                    registryType, registryDefinition.get(),
                    registryPackage, registryType.dataPackage(dataPackage),
                    registryType.keysPackage(keysPackage), outputDirectory
            );

            if (registryType.identifier().equals(SupportedRegistries.DIMENSION_TYPE.identifier())) {
                dimensionTypesGenerator.generate(registryType, registryDefinition.get(), registryType.keysPackage(keysPackage), outputDirectory);
            }
        }

        /*
         * Entries and tags ship as data rather than as bytecode: inlining them into the
         * generated *Keys classes overflows the JVM's 64 KiB per-method limit on large
         * registries, and it is the server, not the API, that needs them at runtime.
         */
        if (datasetDirectory != null) {
            datasetGenerator.generate(generatedRegistries, resolvedTags, registryTypes, datasetDirectory);
        }

        /*
         * Protocol-ID-only registries have no marker type in the data package,
         * so they must not appear in RegistryKey.
         */
        final List<RegistryTypeDefinition> keyedRegistryTypes = registryTypes.stream()
                .filter(registryType -> ProtocolIdRegistries.byIdentifier(registryType.identifier()).isEmpty())
                .toList();

        if (!generateRegistryKey) {
            return;
        }

        registryKeyGenerator.generate(keyedRegistryTypes, registryPackage, dataPackage, outputDirectory);
    }

    /**
     * Parses a Mojang packets report and generates packet identifier
     * catalog classes.
     *
     * @param packetsJson     path to {@code packets.json}
     * @param outputDirectory generated Java source root
     * @throws IOException if parsing or source generation fails
     */
    public void generatePackets(final Path packetsJson, final Path outputDirectory) throws IOException {

        final List<RegistryDefinition> packetCatalogs = new PacketReportParser().parse(packetsJson);

        for (final RegistryDefinition catalog : packetCatalogs) {
            final List<ProtocolIdTarget> targets = PacketCatalogs.byIdentifier(catalog.identifier());
            if (targets.isEmpty()) {
                System.out.println("No PacketCatalogs target configured for: " + catalog.identifier());
                continue;
            }
            for (final ProtocolIdTarget target : targets) {
                protocolIdGenerator.generate(catalog, target, outputDirectory);
            }
        }
    }

    /**
     * Parses a Mojang blocks report (and, optionally, a Prismarine {@code minecraft-data} blocks
     * report for light emission/opacity) and generates {@code BlockStateIds}/{@code BlockStateProperties}
     * (and, when Prismarine data is supplied, {@code BlockStateLightProperties}).
     *
     * @param blocksJson           path to Mojang's {@code blocks.json}
     * @param prismarineBlocksJson path to Prismarine's {@code blocks.json}, or {@code null} to skip lighting
     * @param outputDirectory      generated Java source root
     * @param blockPackage         package holding the {@code BlockType}, {@code BlockProperty}, and
     *                             {@code BlockRegistry} classes
     * @param generatedPackage     root package; {@code BlockState} resolves to {@code <generatedPackage>.world.chunk}
     * @param registryDataPackage  package for {@code BlockStateIds}/{@code BlockStateProperties}/
     *                             {@code BlockStateLightProperties}
     * @param blockTypeKeysPackage package holding the typed {@code BlockType} keys class (e.g. {@code BlockTypeKeys})
     * @throws IOException if parsing or source generation fails
     */
    public void generateBlockStates(final Path blocksJson,
                                    final Path prismarineBlocksJson,
                                    final Path outputDirectory,
                                    final String blockPackage,
                                    final String generatedPackage,
                                    final String registryDataPackage,
                                    final String blockTypeKeysPackage) throws IOException {

        final List<BlockReportDefinition> blocks = blockReportParser.parse(blocksJson);

        final Map<String, PrismarineBlockLightPropertiesDefinition> lighting = (prismarineBlocksJson != null)
                ? prismarineBlockReportParser.parse(prismarineBlocksJson)
                : Map.of();

        blockStateGenerator.generate(blocks, lighting, blockPackage, generatedPackage, registryDataPackage, blockTypeKeysPackage, outputDirectory);
    }

    /**
     * Generates {@code ItemProperties} from Mojang's item registry and Prismarine's
     * items report.
     *
     * @param registriesJson      path to Mojang's {@code registries.json}
     * @param prismarineItemsJson path to Prismarine's {@code items.json}
     * @param outputDirectory     generated Java source root
     * @param registryDataPackage package the generated class is written into
     * @param itemKeysPackage     package holding the generated {@code ItemKeys} class
     *
     * @throws IOException if parsing or source generation fails
     */
    public void generateItemProperties(final Path registriesJson,
                                       final Path prismarineItemsJson,
                                       final Path outputDirectory,
                                       final String registryDataPackage,
                                       final String itemKeysPackage) throws IOException {

        Objects.requireNonNull(registriesJson, "registriesJson");
        Objects.requireNonNull(prismarineItemsJson, "prismarineItemsJson");
        Objects.requireNonNull(outputDirectory, "outputDirectory");

        validateInput(registriesJson);

        final RegistriesHolder registries = parser.parse(registriesJson);
        final Optional<RegistryDefinition> items = registries.registry(SupportedRegistries.ITEM.identifier());

        if (items.isEmpty()) {
            System.out.println("Registry missing from report: " + SupportedRegistries.ITEM.identifier());
            return;
        }

        final Map<String, PrismarineItemDefinition> prismarineItems =
                prismarineItemReportParser.parse(prismarineItemsJson);

        Files.createDirectories(outputDirectory);

        itemPropertiesGenerator.generate(
                items.get().entries(),
                prismarineItems,
                registryDataPackage,
                itemKeysPackage,
                outputDirectory);
    }

    /**
     * Generates {@code FrozenRegistries} from Mojang's registry report.
     *
     * @param registriesJson      path to Mojang's {@code registries.json}
     * @param outputDirectory     generated Java source root
     * @param registryDataPackage package the class is written into
     * @param frozenRegistries    namespaced identifiers of the registries to emit
     * @throws IOException if a registry is missing, or if generation fails
     */
    public void generateFrozenRegistries(final Path registriesJson,
                                         final Path outputDirectory,
                                         final String registryDataPackage,
                                         final List<String> frozenRegistries) throws IOException {

        Objects.requireNonNull(registriesJson, "registriesJson");
        Objects.requireNonNull(outputDirectory, "outputDirectory");
        Objects.requireNonNull(registryDataPackage, "registryDataPackage");
        Objects.requireNonNull(frozenRegistries, "frozenRegistries");

        validateInput(registriesJson);

        final RegistriesHolder registries = parser.parse(registriesJson);
        final List<RegistryDefinition> resolved = new ArrayList<>();

        for (final String identifier : frozenRegistries) {
            resolved.add(registries.registry(identifier).orElseThrow(() -> new IOException(
                    "Registry '" + identifier + "' is absent from " + registriesJson
                            + "; it cannot be emitted as a frozen registry.")));
        }

        Files.createDirectories(outputDirectory);

        frozenRegistriesGenerator.generate(resolved, registryDataPackage, outputDirectory);
    }

    /**
     * Verifies that the registry report exists and can be read.
     */
    private static void validateInput(final Path registriesJson) throws IOException {

        if (!Files.exists(registriesJson)) {
            throw new IOException("Mojang registry report does not exist: " + registriesJson);
        }

        if (!Files.isRegularFile(registriesJson)) {
            throw new IOException("Mojang registry report is not a regular file: " + registriesJson);
        }

        if (!Files.isReadable(registriesJson)) {
            throw new IOException("Mojang registry report is not readable: " + registriesJson);
        }
    }
}
