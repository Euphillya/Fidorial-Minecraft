package fr.fidorial.registrygen.task;

import fr.fidorial.registrygen.generate.RegistryGenerator;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.ListProperty;
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
 * Generates {@code FrozenRegistries} — the entries of the registries whose network IDs
 * the client hard-codes, ordered by Mojang's {@code protocol_id}.
 *
 * @since 0.1.0
 */
@CacheableTask
public abstract class GenerateFrozenRegistriesTask extends DefaultTask {

    @InputFile
    @PathSensitive(PathSensitivity.NONE)
    public abstract RegularFileProperty getRegistriesReport();

    @Input
    public abstract ListProperty<String> getFrozenRegistries();

    @Input
    public abstract Property<String> getRegistryDataPackage();

    @OutputDirectory
    public abstract DirectoryProperty getGeneratedSourcesDirectory();

    @Inject
    public GenerateFrozenRegistriesTask() {
    }

    @TaskAction
    public void generate() {
        try {
            new RegistryGenerator().generateFrozenRegistries(
                    getRegistriesReport().get().getAsFile().toPath(),
                    getGeneratedSourcesDirectory().get().getAsFile().toPath(),
                    getRegistryDataPackage().get(),
                    getFrozenRegistries().get());
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to generate frozen registries", exception);
        }
    }
}
