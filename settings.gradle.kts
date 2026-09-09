pluginManagement {
    includeBuild("build-logic")
    includeBuild("fidorial-registry-generator")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "fidorial"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("fidorial-api")
include("fidorial-auth")
include("fidorial-bootstrap")
include("fidorial-server")
include("fidorial-test-plugin")
include("fidorial-annotation-processor")
