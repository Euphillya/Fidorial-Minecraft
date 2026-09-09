plugins {
    id("fidorial-spotless")
    id("com.gradleup.shadow")
    id("fidorial-build-conventions")
    id("fr.fidorial.plugin-libraries")
}

repositories {
    maven("https://jitpack.io/")
}

fidorialBuild {
    readUnnamedModules = setOf("fr.fidorial.test", "fr.fidorial")
}

dependencies {
    compileOnly(libs.brigadier)
    compileOnly(projects.fidorialApi)

    fidorialLibrary("org.apache.commons:commons-text:1.12.0")
}

fidorialBuild {
    readUnnamedModules = setOf("fr.fidorial.test", "fr.fidorial")
}

tasks.jar {
    enabled = false
}

tasks.shadowJar {
    archiveBaseName.set("TestPlugin")
    archiveClassifier.set("")
}

val deployToRun =
    tasks.register<Copy>("deployToRun") {
        from(tasks.shadowJar)
        rename { "TestPlugin.jar" }
        val destination =
            isolated.rootProject.projectDirectory
                .dir("fidorial-server/run/plugins")
                .asFile
        into(destination)
        doFirst {
            destination.resolve("TestPlugin.jar").delete()
        }
    }
