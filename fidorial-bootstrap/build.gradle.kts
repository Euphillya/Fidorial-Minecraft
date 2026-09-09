plugins {
    id("fidorial-spotless")
    id("fidorial-build-conventions")
}

description = "Zero-dependency launcher: resolves Fidorial's runtime libraries, then hands over to the server."

tasks.jar {
    manifest {
        attributes(
            "Automatic-Module-Name" to "fr.fidorial.bootstrap",
        )
    }
}
