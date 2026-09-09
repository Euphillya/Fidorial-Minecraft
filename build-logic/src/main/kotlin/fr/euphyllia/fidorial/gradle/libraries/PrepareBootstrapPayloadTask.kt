package fr.euphyllia.fidorial.gradle.libraries

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.SetProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Splits the server's runtime classpath in two and writes what the launcher needs to
 * rebuild it at boot:
 *
 *  - artifacts with Maven coordinates go into `libraries.list` and are downloaded at runtime;
 *  - everything else (Fidorial's own modules, the patched Brigadier, plain file deps) is
 *    copied next to the lists and ships inside the launcher jar.
 *
 * Both lists carry a SHA-256 so the runtime can verify what it reads back.
 */
@CacheableTask
abstract class PrepareBootstrapPayloadTask : DefaultTask() {

    /** Resolved runtime artifacts, including their Maven coordinates. */
    @get:Nested
    abstract val runtime: ResolvedLibrariesList

    /** Jars to bundle that are not on the runtime classpath — chiefly the server's own jar. */
    @get:Classpath
    abstract val extraBundled: ConfigurableFileCollection

    /**
     * `group:artifact` pairs that must never be downloaded because a bundled jar replaces
     * them. `com.mojang:brigadier` belongs here: the dependency patcher rebuilds it.
     */
    @get:Input
    abstract val excludedModules: SetProperty<String>

    /**
     * Optional. Leave empty — the default — and the launcher falls back to the mirror chain
     * hardcoded in `Repositories.DEFAULTS`, which stays the single source of truth. Set this
     * only to pin a build to a specific mirror, e.g. for an internal distribution.
     */
    @get:Input
    abstract val repositories: ListProperty<String>

    /**
     * When true, snapshot dependencies are downloaded under their pinned timestamped version
     * instead of being bundled. Smaller jar, but a released Fidorial then depends on a
     * third-party snapshot surviving that repository's cleanup policy. Off by default.
     */
    @get:Input
    abstract val pinSnapshots: Property<Boolean>

    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    @TaskAction
    fun prepare() {
        val output = outputDirectory.get().asFile
        output.deleteRecursively()
        output.mkdirs()

        val excluded = excludedModules.get()
        val external = mutableMapOf<String, File>()
        val bundled = LinkedHashMap<String, File>()
        val snapshots = mutableListOf<String>()
        val pinnedSnapshots = mutableListOf<String>()

        // The server's own jars go on the classpath first, so a patched dependency always
        // wins over whatever a repository might serve under the same package name.
        extraBundled.files.forEach { bundled[it.name] = it }

        runtime.artifacts().forEach { artifact ->
            val identifier = artifact.identifier.componentIdentifier

            if (identifier is ModuleComponentIdentifier) {
                if ("${identifier.group}:${identifier.module}" in excluded) {
                    return@forEach
                }

                // A -SNAPSHOT is stored remotely under a timestamped name
                // (foo-1.0-20250114.093312-7.jar) that only maven-metadata.xml knows about,
                // while Gradle hands us a file named foo-1.0-SNAPSHOT.jar from its cache.
                // Deriving a download URL from the coordinates would 404 at boot, so these
                // ship inside the launcher instead. It also keeps a release reproducible:
                // a snapshot that moves under our feet cannot change what we already shipped.
                if (identifier.version.endsWith("-SNAPSHOT")) {
                    val pinned = if (pinSnapshots.get()) {
                        LibraryLists.timestampedVersionOf(identifier.module, artifact.file)
                    } else {
                        null
                    }

                    if (pinned == null) {
                        snapshots += "${identifier.group}:${identifier.module}:${identifier.version}"
                        bundled[artifact.file.name] = artifact.file
                    } else {
                        val coordinates = "${identifier.group}:${identifier.module}:$pinned"
                        external[coordinates] = artifact.file
                        pinnedSnapshots += coordinates
                    }

                    return@forEach
                }

                val coordinates = LibraryLists.coordinatesOf(identifier, artifact.file)
                val previous = external.put(coordinates, artifact.file)

                if (previous != null && previous != artifact.file) {
                    throw GradleException(
                        "two files resolved for $coordinates: $previous and ${artifact.file}",
                    )
                }
            } else {
                val previous = bundled.put(artifact.file.name, artifact.file)

                if (previous != null && previous != artifact.file) {
                    throw GradleException(
                        "two bundled jars are named ${artifact.file.name} ($previous and ${artifact.file}); " +
                                "rename one, the launcher unpacks them into a single flat directory",
                    )
                }
            }
        }

        if (pinnedSnapshots.isNotEmpty()) {
            logger.lifecycle(
                "Pinned ${pinnedSnapshots.size} snapshot dependenc(y/ies) to a timestamped version: " +
                        "${pinnedSnapshots.sorted().joinToString(", ")}. These are downloaded at boot, so a " +
                        "released jar stops working if the upstream repository prunes them.",
            )
        }

        if (snapshots.isNotEmpty()) {
            logger.warn(
                "Bundled ${snapshots.size} snapshot dependenc(y/ies) into the release jar instead of " +
                        "downloading them: ${snapshots.sorted().joinToString(", ")}. " +
                        "Pin them to a release version to shrink the jar further.",
            )
        }

        val bundledLines = bundled.map { (name, file) ->
            file.copyTo(output.resolve(name), overwrite = true)
            "$name ${LibraryLists.sha256(file)} ${file.length()}"
        }

        val externalLines = external.toSortedMap().map { (coordinates, file) ->
            "$coordinates ${LibraryLists.sha256(file)} ${file.length()}"
        }

        output.resolve("bundled.list").writeText(
            LibraryLists.header(
                "Jars shipped inside the launcher, unpacked into libraries/.fidorial/.",
                name,
            ) + bundledLines.joinToString("\n", postfix = "\n"),
        )

        output.resolve("libraries.list").writeText(
            LibraryLists.header(
                "Downloaded at boot. Format: group:artifact:version[:classifier] sha256 size",
                name,
            ) + externalLines.joinToString("\n", postfix = "\n"),
        )

        // Written only when explicitly configured: an embedded list takes precedence over
        // the launcher's own defaults, so emitting one unconditionally would silently
        // disable every fallback mirror.
        if (repositories.get().isNotEmpty()) {
            output.resolve("repositories.list").writeText(
                LibraryLists.header("Mirrors pinned at build time.", name) +
                        repositories.get().joinToString("\n", postfix = "\n"),
            )
        }

        logger.lifecycle(
            "Bootstrap payload: ${bundled.size} bundled jar(s), ${external.size} downloaded librar(y/ies) " +
                    "(${external.values.sumOf { it.length() } / (1024 * 1024)} MiB kept out of the release jar)",
        )
    }
}
