package fr.fidorial.registrygen.generate;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.CodeBlock;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.ParameterSpec;
import com.palantir.javapoet.ParameterizedTypeName;
import com.palantir.javapoet.TypeName;
import com.palantir.javapoet.TypeSpec;
import fr.fidorial.registrygen.GenerationUtils;
import fr.fidorial.registrygen.model.PrismarineItemDefinition;
import fr.fidorial.registrygen.model.RegistryEntryDefinition;
import fr.fidorial.registrygen.model.SupportedRegistries;
import net.kyori.adventure.key.Key;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Generates {@code ItemProperties} — per-item stack size, durability and repair
 * materials — by joining Mojang's item registry against PrismarineJS's items report.
 *
 * @since 0.1.0
 */
public final class ItemPropertiesGenerator {

    private static final String CLASS_NAME = "ItemProperties";

    private static final int DEFAULT_STACK_SIZE = 64;
    private static final int DEFAULT_MAX_DAMAGE = 0;

    /**
     * Registrations per generated method. The JVM caps a method body at 64 KB, and a
     * single {@code register(...)} line is roughly 80 bytes of bytecode, so 200 leaves
     * a wide margin even if the line grows.
     */
    private static final int ITEMS_PER_METHOD = 200;

    private static final ClassName LIST = ClassName.get(List.class);
    private static final ClassName OBJECT_2_INT_OPEN_HASH_MAP =
            ClassName.get("it.unimi.dsi.fastutil.objects", "Object2IntOpenHashMap");
    private static final ClassName OBJECT_2_OBJECT_OPEN_HASH_MAP =
            ClassName.get("it.unimi.dsi.fastutil.objects", "Object2ObjectOpenHashMap");

    /**
     * Generates the {@code ItemProperties} class.
     *
     * @param items               Mojang's item registry entries, which decide what exists
     * @param prismarineItems     Prismarine metadata keyed by plain item name
     * @param registryDataPackage package the class is written into
     * @param itemKeysPackage     package holding the generated {@code ItemKeys} class
     * @param outputDirectory     generated Java source root
     *
     * @throws IOException if the generated file cannot be written
     */
    public void generate(final List<RegistryEntryDefinition> items,
                         final Map<String, PrismarineItemDefinition> prismarineItems,
                         final String registryDataPackage,
                         final String itemKeysPackage,
                         final Path outputDirectory) throws IOException {

        Objects.requireNonNull(items, "items");
        Objects.requireNonNull(prismarineItems, "prismarineItems");
        Objects.requireNonNull(registryDataPackage, "registryDataPackage");
        Objects.requireNonNull(itemKeysPackage, "itemKeysPackage");
        Objects.requireNonNull(outputDirectory, "outputDirectory");

        final ClassName itemKeys = ClassName.get(
                SupportedRegistries.ITEM.keysPackage(itemKeysPackage),
                SupportedRegistries.ITEM.keysClassName());

        final ParameterizedTypeName intMapType =
                ParameterizedTypeName.get(OBJECT_2_INT_OPEN_HASH_MAP, ClassName.get(Key.class));

        final ParameterizedTypeName keyListType = ParameterizedTypeName.get(LIST, ClassName.get(Key.class));
        final ParameterizedTypeName repairMapType =
                ParameterizedTypeName.get(OBJECT_2_OBJECT_OPEN_HASH_MAP, ClassName.get(Key.class), keyListType);

        final TypeSpec.Builder type = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addJavadoc("Per-item stack size, durability and repair materials.\n\n")
                .addJavadoc("<p>Joined from Mojang's item registry report and PrismarineJS's\n")
                .addJavadoc("{@code minecraft-data} items report; do not edit.</p>\n\n")
                .addJavadoc("<p>These are the item's <em>defaults</em>. A stack that patches\n")
                .addJavadoc("{@code max_stack_size} or {@code max_damage} overrides them — read\n")
                .addJavadoc("{@code ItemStack#maxStackSize()} rather than this class when you have\n")
                .addJavadoc("a stack in hand.</p>\n")
                .addField(FieldSpec.builder(intMapType, "STACK_SIZE", Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("new $T<>()", OBJECT_2_INT_OPEN_HASH_MAP)
                        .build())
                .addField(FieldSpec.builder(intMapType, "MAX_DAMAGE", Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("new $T<>()", OBJECT_2_INT_OPEN_HASH_MAP)
                        .build())
                .addField(FieldSpec.builder(repairMapType, "REPAIR_MATERIALS", Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("new $T<>()", OBJECT_2_OBJECT_OPEN_HASH_MAP)
                        .build())
                .addStaticBlock(CodeBlock.builder()
                        .addStatement("STACK_SIZE.defaultReturnValue($L)", DEFAULT_STACK_SIZE)
                        .addStatement("MAX_DAMAGE.defaultReturnValue($L)", DEFAULT_MAX_DAMAGE)
                        .build())
                .addMethod(privateConstructor())
                .addMethod(intAccessor("maxStackSize", "STACK_SIZE", DEFAULT_STACK_SIZE,
                        "how many of this item fit in one slot"))
                .addMethod(intAccessor("maxDamage", "MAX_DAMAGE", DEFAULT_MAX_DAMAGE,
                        "total durability, or {@code 0} when the item cannot break"))
                .addMethod(damageablePredicate())
                .addMethod(repairMaterialsAccessor(keyListType))
                .addMethod(registerHelper(keyListType));

        addRegistrationMethods(type, items, prismarineItems, itemKeys);

        JavaFile.builder(registryDataPackage, type.build())
                .indent("    ")
                .skipJavaLangImports(true)
                .build()
                .writeTo(outputDirectory);
    }

    private static MethodSpec privateConstructor() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addStatement("throw new $T($S)", UnsupportedOperationException.class,
                        CLASS_NAME + " cannot be instantiated.")
                .build();
    }

    private static MethodSpec intAccessor(final String methodName,
                                          final String fieldName,
                                          final int defaultValue,
                                          final String description) {

        return MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.INT)
                .addParameter(ParameterSpec.builder(ClassName.get(Key.class), "item", Modifier.FINAL).build())
                .addJavadoc("@param item namespaced item identifier\n")
                .addJavadoc("@return " + description + "; {@code " + defaultValue
                        + "} when the item has no recorded data\n")
                .addStatement("return $N.getInt($N)", fieldName, "item")
                .build();
    }

    private static MethodSpec damageablePredicate() {
        return MethodSpec.methodBuilder("isDamageable")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.BOOLEAN)
                .addParameter(ParameterSpec.builder(ClassName.get(Key.class), "item", Modifier.FINAL).build())
                .addJavadoc("@param item namespaced item identifier\n")
                .addJavadoc("@return {@code true} when the item has durability to lose\n")
                .addStatement("return MAX_DAMAGE.getInt($N) > 0", "item")
                .build();
    }

    private static MethodSpec repairMaterialsAccessor(final ParameterizedTypeName keyListType) {
        return MethodSpec.methodBuilder("repairMaterials")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(keyListType)
                .addParameter(ParameterSpec.builder(ClassName.get(Key.class), "item", Modifier.FINAL).build())
                .addJavadoc("@param item namespaced item identifier\n")
                .addJavadoc("@return the materials that repair this item on an anvil, "
                        + "empty when it cannot be repaired that way\n")
                .addStatement("return REPAIR_MATERIALS.getOrDefault($N, $T.of())", "item", LIST)
                .build();
    }

    private static MethodSpec registerHelper(final ParameterizedTypeName keyListType) {
        return MethodSpec.methodBuilder("register")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .addParameter(ParameterSpec.builder(ClassName.get(Key.class), "item", Modifier.FINAL).build())
                .addParameter(ParameterSpec.builder(int.class, "stackSize", Modifier.FINAL).build())
                .addParameter(ParameterSpec.builder(int.class, "maxDamage", Modifier.FINAL).build())
                .addParameter(ParameterSpec.builder(keyListType, "repairMaterials", Modifier.FINAL).build())
                .addStatement("STACK_SIZE.put($N, $N)", "item", "stackSize")
                .beginControlFlow("if ($N > 0)", "maxDamage")
                .addStatement("MAX_DAMAGE.put($N, $N)", "item", "maxDamage")
                .endControlFlow()
                .beginControlFlow("if (!$N.isEmpty())", "repairMaterials")
                .addStatement("REPAIR_MATERIALS.put($N, $N)", "item", "repairMaterials")
                .endControlFlow()
                .build();
    }

    private static void addRegistrationMethods(final TypeSpec.Builder type,
                                               final List<RegistryEntryDefinition> items,
                                               final Map<String, PrismarineItemDefinition> prismarineItems,
                                               final ClassName itemKeys) {

        final List<MethodSpec> methods = new ArrayList<>();

        MethodSpec.Builder current = null;
        int inCurrent = 0;

        for (final RegistryEntryDefinition item : items) {

            final String path = GenerationUtils.path(item.identifier());
            final PrismarineItemDefinition prismarine = prismarineItems.get(path);

            if (prismarine == null) {
                continue; // Prismarine hasn't caught up with this Minecraft version yet
            }

            if (current == null) {
                current = MethodSpec.methodBuilder("registerItems" + methods.size())
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC);
            }

            current.addStatement("register($T.$L.key(), $L, $L, $L)",
                    itemKeys,
                    GenerationUtils.constantName(item.identifier()),
                    prismarine.stackSize(),
                    prismarine.maxDurability(),
                    repairMaterialsLiteral(prismarine, itemKeys));

            if (++inCurrent >= ITEMS_PER_METHOD) {
                methods.add(current.build());
                current = null;
                inCurrent = 0;
            }
        }

        if (current != null) {
            methods.add(current.build());
        }

        methods.forEach(type::addMethod);

        final MethodSpec.Builder bootstrap = MethodSpec.methodBuilder("bootstrap")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addJavadoc("Fills the lookup tables. Called once during server start-up;\n")
                .addJavadoc("calling it again is harmless but pointless.\n");

        for (final MethodSpec method : methods) {
            bootstrap.addStatement("$N()", method.name());
        }

        type.addMethod(bootstrap.build());
    }

    private static CodeBlock repairMaterialsLiteral(final PrismarineItemDefinition item, final ClassName itemKeys) {

        if (item.repairWith().isEmpty()) {
            return CodeBlock.of("$T.of()", LIST);
        }

        final CodeBlock.Builder arguments = CodeBlock.builder();
        boolean first = true;

        for (final String material : item.repairWith()) {
            if (!first) {
                arguments.add(", ");
            }
            arguments.add("$T.$L.key()", itemKeys, GenerationUtils.constantName(material));
            first = false;
        }

        return CodeBlock.of("$T.of($L)", LIST, arguments.build());
    }
}
