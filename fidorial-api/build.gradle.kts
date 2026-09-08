extra.set("readUnnamedModules", setOf("fr.fidorial"))

plugins {
    `maven-publish`
    id("fr.fidorial.registry-generator")
}

dependencies {
    api(libs.adventure.text.serializer.ansi)
    api(libs.adventure.text.serializer.plain)
    api(libs.brigadier)
    api(libs.bundles.adventure)
    api(libs.gson)
    api(libs.guava)
    api(libs.jspecify)
    api(libs.slf4j.api)
    api(platform(libs.adventure.bom))
    api(libs.fastutil)
    compileOnly(libs.jetbrains.annotations)
}

java {
    withJavadocJar()
    withSourcesJar()
    sourceSets.main {
        java.srcDir("src/generated/java")
        resources.srcDir("src/generated/resources")
    }
}

tasks.javadoc {
    val opt = options as StandardJavadocDocletOptions

    opt.encoding = "UTF-8"
    opt.docEncoding = "UTF-8"
    opt.charSet = "UTF-8"
    opt.docTitle = "Fidorial API ${project.version}"
    opt.windowTitle = "fidorial-api ${project.version}"
    opt.addBooleanOption("html5", true)
    opt.noTimestamp(true)
    opt.addStringOption("Xdoclint:all,-missing", "-quiet")
    opt.tags("apiNote:a:API Note:", "sinceMinecraft:a:Since Minecraft:")

    opt.links(
        "https://docs.oracle.com/en/java/javase/25/docs/api/",
        "https://jd.papermc.io/adventure/5.2.0/",
        "https://www.slf4j.org/apidocs/",
        "https://jspecify.dev/docs/api/",
    )

    opt.bottom(
        "MIT © 2026 Euphyllia Bierque — " +
                "<a href=\"https://github.com/Euphillya/Fidorial\">GitHub</a>"
    )
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name = "fidorial-api"
                description = "Plugin API for the Fidorial Minecraft server"
                url = "https://repo.euphyllia.moe"
            }
        }
    }
    repositories {
        maven {
            name = "Euphyllia"
            val releases = uri("https://repo.euphyllia.moe/repository/maven-releases/")
            val snapshots = uri("https://repo.euphyllia.moe/repository/maven-snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshots else releases
            credentials {
                username = providers.environmentVariable("NEXUS_USERNAME").orNull ?: ""
                password = providers.environmentVariable("NEXUS_PASSWORD").orNull ?: ""
            }
        }
    }
}


val verifyRegistryDataset = tasks.register("verifyRegistryDataset") {
    group = "verification"
    description = "Fails if the generated registry dataset is missing from the packaged resources."

    dependsOn(tasks.processResources)

    val datasetDirectory = sourceSets.main.get().output.resourcesDir!!.resolve("fidorial-data")
    val expected = listOf("registries_frozen.json", "registries_dynamic.json")

    doLast {
        val missing = expected.filter { datasetDirectory.resolve(it).length() <= 2 }
        if (missing.isNotEmpty()) {
            throw GradleException(
                "Registry dataset missing or empty: ${missing.joinToString()}. " +
                        "Run ':fidorial-api:generateRegistries'."
            )
        }
    }
}

tasks.named("check") { dependsOn(verifyRegistryDataset) }

fidorialRegistryGenerator {
    minecraftVersion.set("26.2")
    prismarineMinecraftData.set("26.1")

    generatedSourcesDirectory.set(
        layout.projectDirectory.dir(
            "src/generated/java"
        )
    )

    // "--server" additionally dumps the vanilla tag files the dataset is built from.
    dataGeneratorArguments.set(
        listOf("--reports", "--server")
    )

    registriesDatasetDirectory.set(
        layout.projectDirectory.dir(
            "src/generated/resources/fidorial-data"
        )
    )
}
