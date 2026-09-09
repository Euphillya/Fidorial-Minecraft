plugins {
    id("fidorial-spotless")
    id("fidorial-build-conventions")
}

dependencies {
    api(libs.gson)
    implementation(libs.slf4j.api)
    implementation(libs.jspecify)
}

fidorialBuild {
    readUnnamedModules = setOf("fr.fidorial.auth")
}
