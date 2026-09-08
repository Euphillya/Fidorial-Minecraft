extra.set("readUnnamedModules", setOf<String>())

plugins {
    id("java-library")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
