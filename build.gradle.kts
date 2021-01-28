group = "dev.paulshields.chronomancy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    kotlin("jvm") version "1.4.21"

    id("io.gitlab.arturbosch.detekt").version("1.16.0-RC1")
    id("org.jlleitschuh.gradle.ktlint").version("9.4.1")
}

dependencies {
    implementation(kotlin("stdlib"))
}
