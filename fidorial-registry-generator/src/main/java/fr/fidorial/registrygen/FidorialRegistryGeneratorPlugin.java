package fr.fidorial.registrygen;

import fr.fidorial.registrygen.task.DownloadPrismarineDataTask;
import fr.fidorial.registrygen.task.DownloadServerJarTask;
import fr.fidorial.registrygen.task.GenerateBlockStatesTask;
import fr.fidorial.registrygen.task.GenerateFrozenRegistriesTask;
import fr.fidorial.registrygen.task.GenerateItemPropertiesTask;
import fr.fidorial.registrygen.task.GeneratePacketsTask;
import fr.fidorial.registrygen.task.GenerateRegistriesTask;
import fr.fidorial.registrygen.task.GenerateReportsTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Gradle plugin that downloads the Minecraft server, generates Mojang reports,
 * and generates typed registry source files.
 *
 * @since 0.1.0
 */
public final class FidorialRegistryGeneratorPlugin implements Plugin<Project> {

    public static final String DOWNLOAD_MANIFEST_URL = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json";

    public static final String EXTENSION_NAME = "fidorialRegistryGenerator";
    public static final String DOWNLOAD_TASK_NAME = "downloadMinecraftServer";
    public static final String REPORTS_TASK_NAME = "generateMinecraftReports";
    public static final String REGISTRIES_TASK_NAME = "generateRegistries";
    public static final String PACKET_CATALOGS_TASK_NAME = "generatePacketCatalogs";
    public static final String BLOCK_STATES_TASK_NAME = "generateBlockStates";
    public static final String ITEM_PROPERTIES_TASK_NAME = "generateItemProperties";
    public static final String FROZEN_REGISTRIES_TASK_NAME = "generateFrozenRegistries";

    @Override
    public void apply(final Project project) {
        final FidorialRegistryGeneratorExtension extension = project.getExtensions().create(EXTENSION_NAME,
                FidorialRegistryGeneratorExtension.class);

        configureDefaults(project, extension);

        final TaskProvider<DownloadServerJarTask> downloadTask = registerDownloadTask(project, extension);
        final TaskProvider<DownloadPrismarineDataTask> prismarineTask = registerPrismarineDataTask(project, extension);
        final TaskProvider<GenerateReportsTask> reportsTask = registerReportsTask(project, extension, downloadTask);
        final TaskProvider<GenerateRegistriesTask> registriesTask = registerRegistriesTask(project, extension, reportsTask);
        final TaskProvider<GeneratePacketsTask> packetsTask = registerPacketsTask(project, extension, reportsTask);
        final TaskProvider<GenerateBlockStatesTask> blockStatesTask = registerBlockStatesTask(project, extension, reportsTask, prismarineTask);

        final TaskProvider<GenerateItemPropertiesTask> itemPropertiesTask = registerItemPropertiesTask(project, extension, reportsTask, prismarineTask);
        final TaskProvider<GenerateFrozenRegistriesTask> frozenRegistriesTask = registerFrozenRegistriesTask(project, extension, reportsTask);

        registerLifecycleTask(project, registriesTask, packetsTask, blockStatesTask, itemPropertiesTask, frozenRegistriesTask);
    }

    private static void configureDefaults(final Project project, final FidorialRegistryGeneratorExtension extension) {

        extension.getWorkingDirectory().convention(project.getLayout().getBuildDirectory().dir("working"));

        extension.getGeneratedSourcesDirectory().convention(project.getLayout()
                .getBuildDirectory()
                .dir("generated/sources/fidorialRegistries/"));

        extension.getGeneratedPackage().convention("fr.fidorial.registry");
        extension.getRegistryDataPackage().convention(extension.getGeneratedPackage().map(p -> p + ".data"));
        extension.getRegistryKeysPackage().convention(extension.getGeneratedPackage().map(p -> p + ".keys"));
        extension.getRegistries().convention(Map.of());
        extension.getFrozenRegistries().convention(List.of());
        extension.getDataGeneratorArguments().convention(List.of("--reports"));
        extension.getPrismarineDataRepository().convention("PrismarineJS/minecraft-data");
        extension.getPrismarineDataRef().convention("master");
    }

    private static TaskProvider<DownloadServerJarTask> registerDownloadTask(final Project project,
                                                                            final FidorialRegistryGeneratorExtension extension) {

        return project.getTasks().register(DOWNLOAD_TASK_NAME, DownloadServerJarTask.class, task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Downloads the official Minecraft server JAR.");

            task.getMinecraftVersion().set(extension.getMinecraftVersion());
            task.getServerJar().set(extension.getWorkingDirectory()
                    .file(extension.getMinecraftVersion().map(version -> "minecraft/" + version + "/jar/server.jar")));
        });
    }

    private static TaskProvider<DownloadPrismarineDataTask> registerPrismarineDataTask(final Project project,
                                                                                       final FidorialRegistryGeneratorExtension extension) {

        return project.getTasks().register("downloadPrismarineData", DownloadPrismarineDataTask.class, task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Downloads PrismarineJS minecraft-data's full pc/<version> data directory.");

            task.onlyIf(_ -> extension.getPrismarineMinecraftData().isPresent());

            task.getPrismarineMinecraftData().set(extension.getPrismarineMinecraftData());
            task.getRepository().set(extension.getPrismarineDataRepository());
            task.getRef().set(extension.getPrismarineDataRef());
            task.getDataDirectory().set(extension.getWorkingDirectory()
                    .dir(extension.getPrismarineMinecraftData().map(version -> "prismarine/" + version)));
        });
    }

    private static TaskProvider<GenerateReportsTask> registerReportsTask(final Project project,
                                                                         final FidorialRegistryGeneratorExtension extension,
                                                                         final TaskProvider<DownloadServerJarTask> downloadTask) {

        return project.getTasks().register(REPORTS_TASK_NAME, GenerateReportsTask.class, task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Runs Mojang's data generator.");
            task.dependsOn(downloadTask);

            task.getMinecraftVersion().set(extension.getMinecraftVersion());

            task.getJavaExecutable().convention(Path.of(System.getProperty("java.home"), "bin", executableName("java")).toString());

            task.getDataGeneratorArguments().set(extension.getDataGeneratorArguments());
            task.getServerJar().set(downloadTask.flatMap(DownloadServerJarTask::getServerJar));

            task.getDataDirectory().set(extension.getWorkingDirectory().dir(extension.getMinecraftVersion()
                    .map(version -> "minecraft/" + version + "/data")));
        });
    }

    private static TaskProvider<GenerateRegistriesTask> registerRegistriesTask(final Project project,
                                                                               final FidorialRegistryGeneratorExtension extension,
                                                                               final TaskProvider<GenerateReportsTask> reportsTask) {

        return project.getTasks().register(REGISTRIES_TASK_NAME, GenerateRegistriesTask.class, task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Generates typed registry Java sources.");
            task.dependsOn(reportsTask);

            task.getMinecraftVersion().set(extension.getMinecraftVersion());
            task.getGeneratedPackage().set(extension.getGeneratedPackage());
            task.getRegistryDataPackage().set(extension.getRegistryDataPackage());
            task.getRegistryKeysPackage().set(extension.getRegistryKeysPackage());
            task.getRegistries().set(extension.getRegistries());
            task.getGenerateRegistryKey().set(extension.getGenerateRegistryKey().orElse(true));

            task.getReportsDirectory().set(reportsTask.flatMap(GenerateReportsTask::getDataDirectory)
                    .map(directory -> directory.dir("generated/reports")));

            task.getVanillaDataDirectory().set(reportsTask.flatMap(GenerateReportsTask::getDataDirectory)
                    .map(directory -> directory.dir("generated/data")));

            task.getGeneratedSourcesDirectory().set(extension.getGeneratedSourcesDirectory());
            task.getRegistriesDatasetDirectory().set(extension.getRegistriesDatasetDirectory());
        });
    }

    private static TaskProvider<GeneratePacketsTask> registerPacketsTask(final Project project,
                                                                         final FidorialRegistryGeneratorExtension extension,
                                                                         final TaskProvider<GenerateReportsTask> reportsTask) {

        return project.getTasks().register(PACKET_CATALOGS_TASK_NAME, GeneratePacketsTask.class, task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Generates packet identifier catalog classes.");
            task.dependsOn(reportsTask);

            task.onlyIf(_ -> extension.getGeneratePacketCatalogs().getOrElse(false));

            task.getPacketsReport().set(reportsTask.flatMap(GenerateReportsTask::getDataDirectory)
                    .map(dir -> dir.file("generated/reports/packets.json")));

            task.getGeneratedSourcesDirectory().set(extension.getGeneratedSourcesDirectory());
        });
    }

    private static TaskProvider<GenerateBlockStatesTask> registerBlockStatesTask(final Project project,
                                                                                 final FidorialRegistryGeneratorExtension extension,
                                                                                 final TaskProvider<GenerateReportsTask> reportsTask,
                                                                                 final TaskProvider<DownloadPrismarineDataTask> prismarineTask) {

        return project.getTasks().register(BLOCK_STATES_TASK_NAME, GenerateBlockStatesTask.class, task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Generates BlockType registrations from Mojang's blocks report.");
            task.dependsOn(reportsTask);

            task.onlyIf(_ -> extension.getGenerateBlockStates().getOrElse(false));

            task.getBlocksReport().set(reportsTask.flatMap(GenerateReportsTask::getDataDirectory)
                    .map(dir -> dir.file("generated/reports/blocks.json")));

            task.getPrismarineBlocksReport().set(extension.getPrismarineMinecraftData()
                    .flatMap(_ -> prismarineTask.flatMap(DownloadPrismarineDataTask::getDataDirectory))
                    .map(dir -> dir.file("blocks.json")));

            task.getGeneratedPackage().set(extension.getGeneratedPackage());
            task.getRegistryDataPackage().set(extension.getRegistryDataPackage());
            task.getBlockTypeKeysPackage().convention(extension.getRegistryKeysPackage());
            task.getBlockPackage().convention(extension.getGeneratedPackage().map(p -> p + ".world.block"));

            task.getGeneratedSourcesDirectory().set(extension.getGeneratedSourcesDirectory());
        });
    }

    private static TaskProvider<GenerateItemPropertiesTask> registerItemPropertiesTask(final Project project,
                                                                                       final FidorialRegistryGeneratorExtension extension,
                                                                                       final TaskProvider<GenerateReportsTask> reportsTask,
                                                                                       final TaskProvider<DownloadPrismarineDataTask> prismarineTask) {

        return project.getTasks().register(ITEM_PROPERTIES_TASK_NAME, GenerateItemPropertiesTask.class, task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Generates ItemProperties from Mojang's item registry and Prismarine's items report.");
            task.dependsOn(reportsTask, prismarineTask);

            task.onlyIf(_ -> extension.getPrismarineMinecraftData().isPresent());

            task.getRegistriesReport().set(reportsTask.flatMap(GenerateReportsTask::getDataDirectory)
                    .map(dir -> dir.file("generated/reports/registries.json")));

            task.getPrismarineItemsReport().set(extension.getPrismarineMinecraftData()
                    .flatMap(_ -> prismarineTask.flatMap(DownloadPrismarineDataTask::getDataDirectory))
                    .map(dir -> dir.file("items.json")));

            task.getRegistryDataPackage().set(extension.getRegistryDataPackage());
            task.getItemKeysPackage().convention(extension.getRegistryKeysPackage());

            task.getGeneratedSourcesDirectory().set(extension.getGeneratedSourcesDirectory());
        });
    }

    private static TaskProvider<GenerateFrozenRegistriesTask> registerFrozenRegistriesTask(final Project project,
                                                                                           final FidorialRegistryGeneratorExtension extension,
                                                                                           final TaskProvider<GenerateReportsTask> reportsTask) {

        return project.getTasks().register(FROZEN_REGISTRIES_TASK_NAME, GenerateFrozenRegistriesTask.class, task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Generates the registries whose network IDs the client hard-codes.");
            task.dependsOn(reportsTask);

            task.onlyIf(_ -> !extension.getFrozenRegistries().getOrElse(List.of()).isEmpty());

            task.getRegistriesReport().set(reportsTask.flatMap(GenerateReportsTask::getDataDirectory)
                    .map(dir -> dir.file("generated/reports/registries.json")));

            task.getFrozenRegistries().set(extension.getFrozenRegistries());
            task.getRegistryDataPackage().set(extension.getRegistryDataPackage());

            task.getGeneratedSourcesDirectory().set(extension.getGeneratedSourcesDirectory());
        });
    }

    private static void registerLifecycleTask(final Project project,
                                              final TaskProvider<GenerateRegistriesTask> registriesTask,
                                              final TaskProvider<GeneratePacketsTask> packetsTask,
                                              final TaskProvider<GenerateBlockStatesTask> blockStatesTask,
                                              final TaskProvider<GenerateItemPropertiesTask> itemPropertiesTask,
                                              final TaskProvider<GenerateFrozenRegistriesTask> frozenRegistriesTask) {

        project.getTasks().register("generateAll", task -> {
            task.setGroup("fidorial registry generation");
            task.setDescription("Runs the complete generation pipeline.");
            task.dependsOn(registriesTask, packetsTask, blockStatesTask, itemPropertiesTask, frozenRegistriesTask);
        });
    }

    private static String executableName(final String executable) {
        return (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win")) ? executable + ".exe" : executable;
    }
}
