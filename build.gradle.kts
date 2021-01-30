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

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23")
    testImplementation("org.koin:koin-test:2.2.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}
