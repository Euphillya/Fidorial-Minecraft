package fr.fidorial.registrygen.generate;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.CodeBlock;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.ParameterSpec;
import com.palantir.javapoet.ParameterizedTypeName;
import com.palantir.javapoet.TypeSpec;
import fr.fidorial.registrygen.GenerationUtils;
import fr.fidorial.registrygen.model.RegistryDefinition;
import fr.fidorial.registrygen.model.RegistryEntryDefinition;
import net.kyori.adventure.key.Key;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * Generates {@code FrozenRegistries} — the entries of every registry whose network IDs
 * the client hard-codes, ordered by {@code protocol_id}.
 *
 * <p>These registries are never sent in {@code registry_data}, so the server has to
 * agree with the client on their ordering by itself. The order comes from Mojang's
 * {@code protocol_id} rather than from the identifier: {@code RegistryReportParser}
 * sorts entries alphabetically, which is the wrong order for the wire.</p>
 *
 * @since 0.1.0
 */
public final class FrozenRegistriesGenerator {

    private static final String CLASS_NAME = "FrozenRegistries";
    private static final String ENTRIES_FIELD = "ENTRIES";
    private static final String ENTRIES_PARAMETER = "entries";

    /**
     * Entries per generated method, matching {@code ItemPropertiesGenerator}. The JVM caps
     * a method body at 64 KB and one {@code entries.add(...)} line is a dozen bytes, so this
     * leaves a very wide margin.
     */
    private static final int ENTRIES_PER_METHOD = 200;

    /**
     * Placeholder for an ID Mojang leaves unused. Network IDs resolve to a list index,
     * so a gap has to be padded rather than skipped.
     */
    private static final String GAP_NAMESPACE = "fidorial";
    private static final String GAP_PATH = "unused_";

    /**
     * Generates the {@code FrozenRegistries} class.
     *
     * @param registries          the registries to emit, in the order they should be declared
     * @param registryDataPackage package the class is written into
     * @param outputDirectory     generated Java source root
     * @throws IOException if the generated file cannot be written
     */
    public void generate(final List<RegistryDefinition> registries,
                         final String registryDataPackage,
                         final Path outputDirectory) throws IOException {

        Objects.requireNonNull(registries, "registries");
        Objects.requireNonNull(registryDataPackage, "registryDataPackage");
        Objects.requireNonNull(outputDirectory, "outputDirectory");

        final ParameterizedTypeName keyListType =
                ParameterizedTypeName.get(ClassName.get(List.class), ClassName.get(Key.class));

        final ParameterizedTypeName entriesMapType = ParameterizedTypeName.get(
                ClassName.get(Map.class), ClassName.get(Key.class), keyListType);

        final Map<String, String> accessorsByRegistry = accessorNames(registries);

        final TypeSpec.Builder type = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addJavadoc("Entries of the registries whose network IDs the client hard-codes,\n")
                .addJavadoc("in {@code protocol_id} order.\n\n")
                .addJavadoc("<p>Generated from Mojang's registry report; do not edit.</p>\n\n")
                .addJavadoc("<p>Every list is indexed by network ID. An ID Mojang leaves unused is\n")
                .addJavadoc("padded with a {@code $L:$L<id>} placeholder so the indices stay aligned.</p>\n",
                        GAP_NAMESPACE, GAP_PATH)
                .addField(entriesField(entriesMapType, registries, accessorsByRegistry))
                .addMethod(privateConstructor())
                .addMethod(entriesAccessor(entriesMapType));

        for (final RegistryDefinition registry : registries) {
            addRegistryMethods(type, registry, accessorsByRegistry.get(registry.identifier()), keyListType);
        }

        JavaFile.builder(registryDataPackage, type.build())
                .indent("    ")
                .skipJavaLangImports(true)
                .build()
                .writeTo(outputDirectory);
    }

    private static FieldSpec entriesField(final ParameterizedTypeName entriesMapType,
                                          final List<RegistryDefinition> registries,
                                          final Map<String, String> accessorsByRegistry) {

        final ClassName map = ClassName.get(Map.class);
        final CodeBlock.Builder initializer = CodeBlock.builder().add("$T.ofEntries(\n", map);

        for (int index = 0; index < registries.size(); index++) {

            final RegistryDefinition registry = registries.get(index);

            initializer.add("    $T.entry($L, $N())", map,
                    keyInitializer(registry.identifier()),
                    accessorsByRegistry.get(registry.identifier()));

            initializer.add(index < registries.size() - 1 ? ",\n" : "\n");
        }

        initializer.add(")");

        return FieldSpec.builder(entriesMapType, ENTRIES_FIELD,
                        Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer(initializer.build())
                .build();
    }

    private static MethodSpec entriesAccessor(final ParameterizedTypeName entriesMapType) {
        return MethodSpec.methodBuilder("entries")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(entriesMapType)
                .addJavadoc("@return every frozen registry, keyed by registry identifier;\n")
                .addJavadoc("        each list is indexed by network ID\n")
                .addStatement("return $N", ENTRIES_FIELD)
                .build();
    }

    private static MethodSpec privateConstructor() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addStatement("throw new $T($S)", UnsupportedOperationException.class,
                        CLASS_NAME + " cannot be instantiated.")
                .build();
    }

    private static void addRegistryMethods(final TypeSpec.Builder type,
                                           final RegistryDefinition registry,
                                           final String accessor,
                                           final ParameterizedTypeName keyListType) {

        final List<String> ordered = orderedEntries(registry);

        final ParameterSpec entries = ParameterSpec
                .builder(keyListType, ENTRIES_PARAMETER, Modifier.FINAL)
                .build();

        final List<String> chunkNames = new ArrayList<>();
        MethodSpec.Builder current = null;

        for (int index = 0; index < ordered.size(); index++) {

            if (current == null) {
                final String name = accessor + chunkNames.size();
                chunkNames.add(name);
                current = MethodSpec.methodBuilder(name)
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                        .addParameter(entries);
            }

            current.addStatement("$N.add($L)", ENTRIES_PARAMETER, keyInitializer(ordered.get(index)));

            if ((index + 1) % ENTRIES_PER_METHOD == 0 || index == ordered.size() - 1) {
                type.addMethod(current.build());
                current = null;
            }
        }

        final MethodSpec.Builder assembler = MethodSpec.methodBuilder(accessor)
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .returns(keyListType)
                .addJavadoc("@return {@code $L}, indexed by network ID\n", registry.identifier())
                .addStatement("final $T $N = new $T<>($L)", keyListType, ENTRIES_PARAMETER,
                        ClassName.get(ArrayList.class), ordered.size());

        for (final String chunkName : chunkNames) {
            assembler.addStatement("$N($N)", chunkName, ENTRIES_PARAMETER);
        }

        type.addMethod(assembler
                .addStatement("return $T.copyOf($N)", ClassName.get(List.class), ENTRIES_PARAMETER)
                .build());
    }

    /**
     * Expands a registry into a list indexed by network ID, padding unused IDs.
     */
    private static List<String> orderedEntries(final RegistryDefinition registry) {

        final Map<Integer, String> byProtocolId = new HashMap<>();
        int highest = -1;

        for (final RegistryEntryDefinition entry : registry.entries()) {

            final String clash = byProtocolId.put(entry.protocolId(), entry.identifier());

            if (clash != null) {
                throw new IllegalStateException("Entries '" + clash + "' and '" + entry.identifier()
                        + "' in '" + registry.identifier() + "' share protocol ID " + entry.protocolId() + '.');
            }

            highest = Math.max(highest, entry.protocolId());
        }

        final List<String> ordered = new ArrayList<>(highest + 1);

        for (int id = 0; id <= highest; id++) {
            ordered.add(byProtocolId.getOrDefault(id, GAP_NAMESPACE + ':' + GAP_PATH + id));
        }

        return ordered;
    }

    /**
     * Derives one accessor method name per registry, e.g. {@code minecraft:data_component_type}
     * &rarr; {@code dataComponentType}.
     */
    private static Map<String, String> accessorNames(final List<RegistryDefinition> registries) {

        final Map<String, String> names = new LinkedHashMap<>();

        for (final RegistryDefinition registry : registries) {

            final String className = GenerationUtils.className(registry.identifier());
            final String accessor = className.substring(0, 1).toLowerCase(Locale.ROOT) + className.substring(1);

            if (names.containsValue(accessor)) {
                throw new IllegalStateException("Registry '" + registry.identifier()
                        + "' collides with another frozen registry on accessor name '" + accessor + "'.");
            }

            names.put(registry.identifier(), accessor);
        }

        return names;
    }

    private static CodeBlock keyInitializer(final String identifier) {

        final int separator = identifier.indexOf(':');

        if (separator < 0) {
            return CodeBlock.of("$T.key($S)", Key.class, identifier);
        }

        final String namespace = identifier.substring(0, separator);
        final String path = identifier.substring(separator + 1);

        if (namespace.equalsIgnoreCase(Key.MINECRAFT_NAMESPACE)) {
            return CodeBlock.of("$T.key($S)", Key.class, path);
        }

        return CodeBlock.of("$T.key($S, $S)", Key.class, namespace, path);
    }
}
