import fr.euphyllia.fidorial.gradle.libraries.GenerateApiPackageIndexTask
import fr.euphyllia.fidorial.gradle.libraries.PrepareBootstrapPayloadTask
import fr.fidorial.registrygen.task.GenerateBlockStatesTask
import fr.fidorial.registrygen.task.GenerateItemPropertiesTask

plugins {
    application
    alias(libs.plugins.blossom)
    id("fidorial-spotless")
    id("fidorial-build-conventions")
    id("fr.fidorial.dependency-patcher")
    id("fr.fidorial.registry-generator")
}

repositories {
    maven("https://repo.faststats.dev/releases")
    maven("https://repo.lucko.me/")
}

dependencies {
    implementation(projects.fidorialBootstrap)
    implementation(libs.faststats.config)
    implementation(libs.faststats.core)
    implementation(libs.jline.ffm)
    implementation(libs.jline.reader)
    implementation(libs.logback.classic)
    implementation(libs.netty.all)
    implementation(libs.classgraph)
    implementation(projects.fidorialApi)
    implementation(projects.fidorialAuth)
    implementation(libs.dfu)
    implementation(libs.adventure.nbt.dfu)
    implementation(libs.spark.common) {
        exclude(group = "net.kyori", module = "adventure-api")
        exclude(group = "net.kyori", module = "adventure-key")
        exclude(group = "net.kyori", module = "adventure-text-serializer-gson")
        exclude(group = "net.kyori", module = "adventure-text-serializer-legacy")
        exclude(group = "com.google.code.gson", module = "gson")
        exclude(group = "com.google.guava", module = "guava")
        exclude(group = "org.jspecify", module = "jspecify")
    }
    implementation(libs.spark.api)
    implementation(libs.leafpile)

    runtimeOnly(libs.netty.epoll)
    runtimeOnly(libs.netty.iouring)
    runtimeOnly(libs.netty.kqueue)

    annotationProcessor(projects.fidorialAnnotationProcessor)
}

fidorialBuild {
    readUnnamedModules = setOf("fr.fidorial", "fr.fidorial.server")
}

application {
    mainClass.set("fr.euphyllia.fidorial.server.Main")
}

sourceSets.main {
    blossom.javaSources {
        property("minecraftVersionId", providers.gradleProperty("minecraftVersion"))
        property("minecraftVersionName", providers.gradleProperty("minecraftVersionName"))
        property(
            "isRelease",
            providers
                .gradleProperty("minecraftVersion")
                .map { it.matches(Regex("\\d+\\.\\d+(?:\\.\\d+)?")).toString() },
        ) // MAJOR.MINOR or MAJOR.MINOR.PATCH
        property("protocolVersion", providers.gradleProperty("protocolVersion"))
        property("dataVersion", providers.gradleProperty("dataVersion"))
    }
}

java {
    sourceSets.main {
        java.srcDir("src/generated/java")
        resources.srcDir("src/generated/resources")
    }
}

val apiSurface =
    configurations.register("apiSurface") {
        isCanBeConsumed = false
        isCanBeResolved = true
        description = "fidorial-api and everything it re-exports to plugins"
    }

dependencies {
    apiSurface(projects.fidorialApi)
}

val generateApiPackageIndex =
    tasks.register<GenerateApiPackageIndexTask>("generateApiPackageIndex") {
        group = "build"
        description = "Records which packages plugins must always load from the server."
        apiSurface.from(configurations.named("apiSurface"))
        outputDirectory.set(layout.buildDirectory.dir("generated/fidorial-api-index"))
    }

sourceSets.main {
    resources.srcDir(generateApiPackageIndex)
}

val bootstrapLauncher =
    configurations.register("bootstrapLauncher") {
        isCanBeConsumed = false
        isCanBeResolved = true
        isTransitive = false
        description = "The launcher classes that sit at the root of the release jar"
    }

dependencies {
    bootstrapLauncher(projects.fidorialBootstrap)
}

val bootstrapPayload =
    tasks.register<PrepareBootstrapPayloadTask>("prepareBootstrapPayload") {
        group = "build"
        description = "Splits the runtime classpath into bundled jars and downloadable libraries."

        val runtimeClasspath = configurations.named("runtimeClasspath")
        runtimeArtifacts.set(runtimeClasspath.flatMap { it.incoming.artifacts.resolvedArtifacts })
        runtimeFiles.from(runtimeClasspath)

        extraBundled.from(tasks.named("jar"))

        excludedModules.set(setOf("com.mojang:brigadier"))

        pinSnapshots.set(false)

        repositories.set(emptyList<String>())

        outputDirectory.set(layout.buildDirectory.dir("bootstrap-payload"))
    }

val bootstrapJar =
    tasks.register<Jar>("bootstrapJar") {
        group = "build"
        description = "The distributable server jar: launcher + Fidorial's own code, no third-party libraries."

        archiveBaseName.set("Fidorial")
        archiveClassifier.set("")

        from(zipTree(bootstrapLauncher.flatMap { it.elements.map { it.single().asFile } }))
        into("META-INF/fidorial") {
            from(bootstrapPayload)
        }

        manifest {
            attributes(
                "Main-Class" to "fr.euphyllia.fidorial.bootstrap.Main",
                "Enable-Native-Access" to "ALL-UNNAMED",
                "Implementation-Title" to "Fidorial",
                "Implementation-Version" to project.version,
            )
        }
    }

tasks.assemble {
    dependsOn(bootstrapJar)
}

tasks.run {
    description = "Spin up a test server without assembling a jar"
    standardInput = System.`in`
    classpath(sourceSets.main.map { it.runtimeClasspath })
    workingDir = project.file("run")
    jvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
    dependsOn(":fidorial-test-plugin:deployToRun")
    doFirst {
        workingDir.mkdirs()
    }
}

tasks.register<JavaExec>("testScenarios") {
    description = "Run scenario tests against a real server"
    group = "verification"

    val pluginsDir = layout.projectDirectory.dir("run/plugins").asFile

    standardInput = System.`in`
    classpath(sourceSets.main.map { it.runtimeClasspath })
    workingDir = layout.projectDirectory.file("build/tmp/scenario-tests").asFile
    jvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
    mainClass = "fr.euphyllia.fidorial.server.testing.ScenarioTestMain"
    args = listOf("fr.euphyllia.fidorial.server.tests", "fr.euphyllia.fidorial.testplugin.tests")
    dependsOn(":fidorial-test-plugin:deployToRun")
    doFirst {
        workingDir.deleteRecursively()
        workingDir.mkdirs()
        pluginsDir.resolve("TestPlugin.jar").copyTo(workingDir.resolve("plugins/TestPlugin.jar"))
    }
}

tasks.test {
    dependsOn("testScenarios")
}

tasks.withType<GenerateBlockStatesTask>().configureEach {
    blockPackage.set("fr.fidorial.world.block")
    blockTypeKeysPackage.set("fr.fidorial.registry.keys")
}

tasks.withType<GenerateItemPropertiesTask>().configureEach {
    itemKeysPackage.set("fr.fidorial.registry.keys")
}

fidorialRegistryGenerator {
    minecraftVersion.set(providers.gradleProperty("minecraftVersion"))
    prismarineMinecraftData.set("26.3-snapshot-10")
    prismarineDataRepository.set("Toffikk/minecraft-data") // PrismarineJS/minecraft-data
    prismarineDataRef.set("ver/26.3-snapshot-10") // master

    generatedPackage.set(
        "fr.euphyllia.fidorial.server",
    )

    registryDataPackage.set(
        "fr.euphyllia.fidorial.server.registry.data",
    )

    registryKeysPackage.set(
        "fr.euphyllia.fidorial.server.registry.keys",
    )

    generatedSourcesDirectory.set(
        layout.projectDirectory.dir(
            "src/generated/java",
        ),
    )

    dataGeneratorArguments.set(
        listOf("--reports", "--server"),
    )

    registries.set(
        mapOf(
            "minecraft:command_argument_type" to "ArgumentType",
            "minecraft:block_entity_type" to "BlockEntityType",
        ),
    )

    frozenRegistries.set(
        listOf(
            "minecraft:item",
            "minecraft:data_component_type",
            "minecraft:menu",
            "minecraft:attribute",
        ),
    )

    generateRegistryKey = false
    generatePacketCatalogs = true
    generateBlockStates = true
}

dependencyPatcher {
    patchSet("brigadier") {
        library.set(libs.brigadier)
        autoRebuild = true
    }
}
