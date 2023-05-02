plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "com.mjs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:5.6.1")
    testImplementation("com.michaelstrasser:kotest-html-reporter:0.6.3")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}