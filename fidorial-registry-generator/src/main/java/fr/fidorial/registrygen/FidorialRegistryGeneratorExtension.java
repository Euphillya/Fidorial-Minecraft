package fr.fidorial.registrygen;

import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.provider.Property;

/**
 * Configuration exposed by the plugin.
 *
 * @since 0.1.0
 */
public abstract class FidorialRegistryGeneratorExtension {

    /**
     * Minecraft version to resolve from Mojang's official version manifest.
     */
    public abstract Property<String> getMinecraftVersion();

    /**
     * Persistent workspace root. Defaults to {@code build/working}.
     */
    public abstract DirectoryProperty getWorkingDirectory();

    /**
     * Final generated Java source root.
     */
    public abstract DirectoryProperty getGeneratedSourcesDirectory();

    /**
     * Package used for generated classes.
     */
    public abstract Property<String> getGeneratedPackage();

    /**
     * Subpackage used for generated registry keys.
     */
    public abstract Property<String> getRegistryKeysPackage();

    /**
     * Subpackage used for generated registry data.
     */
    public abstract Property<String> getRegistryDataPackage();

    /**
     * Registries whose network IDs the client hard-codes, emitted as {@code FrozenRegistries}
     * in {@code protocol_id} order.
     */
    public abstract ListProperty<String> getFrozenRegistries();

    /**
     * Registry identifier to generated class name.
     * Example: {@code minecraft:entity_type -> EntityTypes}.
     */
    public abstract MapProperty<String, String> getRegistries();

    /**
     * Directory receiving the runtime registry dataset: {@code registries_frozen.json}
     * and {@code registries_dynamic.json}, holding the protocol-ordered entries and the
     * tags of every registry that reaches the client.
     */
    public abstract DirectoryProperty getRegistriesDatasetDirectory();

    /**
     * Whether the {@code generatePacketCatalogs} task should be registered.
     */
    public abstract Property<Boolean> getGeneratePacketCatalogs();

    /**
     * Whether the {@code generateBlockStates} task should be registered.
     */
    public abstract Property<Boolean> getGenerateBlockStates();

    /**
     * Additional arguments passed to Mojang's data generator.
     */
    public abstract ListProperty<String> getDataGeneratorArguments();

    /**
     * Whether to generate the central {@code RegistryKey} class.
     *
     * <p>Defaults to {@code true}. Set this to {@code false} if you supply
     * your own {@code RegistryKey} type, or only generate protocol-ID-only
     * registries that don't need typed keys.</p>
     */
    public abstract Property<Boolean> getGenerateRegistryKey();

    /**
     * Optional PrismarineJS {@code minecraft-data} version directory (e.g. {@code "26.1"}),
     * used to source data that Mojang's own reports don't expose.
     *
     * <p>Leave unset to skip Prismarine enrichment entirely
     */
    public abstract Property<String> getPrismarineMinecraftData();

    /**
     * Repository hosting {@code minecraft-data}, as {@code owner/name}. Set this to
     * a fork when a version isn't available upstream yet.
     *
     * <p>Defaults to {@code "PrismarineJS/minecraft-data"}.</p>
     */
    public abstract Property<String> getPrismarineDataRepository();

    /**
     * Git ref (branch, tag or commit SHA) of {@link #getPrismarineDataRepository()} to pull
     * {@link #getPrismarineMinecraftData()} from. Defaults to {@code "master"}.
     */
    public abstract Property<String> getPrismarineDataRef();
}
