import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

group = "dev.paulshields.chronomancy"
version = "1.0-SNAPSHOT"

val mainClassLocation = "dev.paulshields.chronomancy.DependencyInjectionKt"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    application

    kotlin("jvm") version "1.4.21"

    id("io.gitlab.arturbosch.detekt").version("1.16.0-RC1")
    id("org.jlleitschuh.gradle.ktlint").version("10.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

configure<KtlintExtension> {
    verbose.set(true)
}

detekt {
    config = files("detekt-config.yml")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.koin:koin-core:2.2.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("io.ktor:ktor-server-netty:1.3.2")
    implementation("com.jessecorbett:diskord:1.8.1")

    // Temporary due to the need to downgrade ktor, due to the version conflict with the diskord transitive dependency
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.21")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    testImplementation("io.mockk:mockk:1.10.5")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23")
    testImplementation("org.koin:koin-test:2.2.2")
    testImplementation("io.ktor:ktor-server-test-host:1.3.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

application {
    mainClass.set(mainClassLocation)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = mainClassLocation
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks {
    create("stage") {
        dependsOn("build", "clean")
    }

    "build" {
        mustRunAfter("clean")
    }
}
