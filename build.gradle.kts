plugins {
    java
    id("fidorial-spotless") apply false
    alias(libs.plugins.shadow) apply false
}

repositories {
    mavenCentral()
}
