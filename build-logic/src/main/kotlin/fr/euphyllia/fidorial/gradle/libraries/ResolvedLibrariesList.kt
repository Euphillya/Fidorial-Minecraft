package fr.euphyllia.fidorial.gradle.libraries

import org.gradle.api.artifacts.ArtifactCollection
import org.gradle.api.artifacts.component.ComponentArtifactIdentifier
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import java.io.File
import javax.inject.Inject

abstract class ResolvedLibrariesList @Inject constructor(
    objects: ObjectFactory,
) {

    @get:Internal
    abstract val filesByIdentifier: MapProperty<ComponentArtifactIdentifier, File>

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    val contents: ConfigurableFileCollection = objects.fileCollection()

    fun setFrom(artifacts: Provider<ArtifactCollection>) {
        contents.setFrom(artifacts.map { it.artifactFiles })

        filesByIdentifier.set(
            artifacts.flatMap { collection ->
                collection.resolvedArtifacts.map { resolvedArtifacts ->
                    resolvedArtifacts.associate { result ->
                        result.id to result.file
                    }
                }
            },
        )
    }

    fun artifacts(): List<Artifact> {
        return filesByIdentifier.get()
            .toSortedMap(compareBy { it.displayName })
            .map { (identifier, file) ->
                Artifact(identifier, file)
            }
    }

    data class Artifact(
        val identifier: ComponentArtifactIdentifier,
        val file: File,
    )
}
