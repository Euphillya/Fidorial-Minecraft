import fr.euphyllia.fidorial.gradle.FidorialBuildExtension

plugins {
    `java-library`
}

val fidorialBuild = extensions.create<FidorialBuildExtension>("fidorialBuild")

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
    maven("https://repo.papermc.io/repository/maven-public/")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = Charsets.UTF_8.name()
    options.release = 25

    fidorialBuild.readUnnamedModules.get().forEach {
        options.compilerArgs.addAll(
            listOf(
                "--add-reads",
                "$it=ALL-UNNAMED"
            )
        )
    }
}

tasks.withType<Test>().configureEach {
    fidorialBuild.readUnnamedModules.get().forEach {
        jvmArgs("--add-reads", "$it=ALL-UNNAMED")
    }
}

tasks.withType<JavaExec>().configureEach {
    fidorialBuild.readUnnamedModules.get().forEach {
        jvmArgs("--add-reads", "$it=ALL-UNNAMED")
    }
}

tasks.withType<Javadoc>().configureEach {
    options.encoding = Charsets.UTF_8.name()

    val options = options as StandardJavadocDocletOptions
    fidorialBuild.readUnnamedModules.get().forEach {
        options.addStringOption(
            "-add-reads",
            "$it=ALL-UNNAMED"
        )
    }
}
