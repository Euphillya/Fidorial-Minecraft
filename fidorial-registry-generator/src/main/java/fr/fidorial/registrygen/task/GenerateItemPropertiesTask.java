package fr.fidorial.registrygen.task;

import fr.fidorial.registrygen.generate.RegistryGenerator;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.PathSensitive;
import org.gradle.api.tasks.PathSensitivity;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Generates {@code ItemProperties} — stack size, durability, repair materials — by
 * joining Mojang's item registry with PrismarineJS's items report.
 *
 * @since 0.1.0
 */
@CacheableTask
public abstract class GenerateItemPropertiesTask extends DefaultTask {

    @InputFile
    @PathSensitive(PathSensitivity.NONE)
    public abstract RegularFileProperty getRegistriesReport();

    @InputFile
    @PathSensitive(PathSensitivity.NONE)
    public abstract RegularFileProperty getPrismarineItemsReport();

    @Input
    public abstract Property<String> getRegistryDataPackage();

    @Input
    public abstract Property<String> getItemKeysPackage();

    @OutputDirectory
    public abstract DirectoryProperty getGeneratedSourcesDirectory();

    @Inject
    public GenerateItemPropertiesTask() {
    }

    @TaskAction
    public void generate() {
        try {
            new RegistryGenerator().generateItemProperties(
                    getRegistriesReport().get().getAsFile().toPath(),
                    getPrismarineItemsReport().get().getAsFile().toPath(),
                    getGeneratedSourcesDirectory().get().getAsFile().toPath(),
                    getRegistryDataPackage().get(),
                    getItemKeysPackage().get());
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to generate item properties", e);
        }
    }
}
