import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.51"
}

group = "eu.lumpy"
version = "1.0-SNAPSHOT"

kotlin {
    experimental {
        coroutines
    }
}

repositories {
    jcenter()
    mavenCentral()
    maven {setUrl("http://dl.bintray.com/kotlin/ktor")}
    maven {setUrl("http://dl.bintray.com/kotlin/kotlinx")}
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("io.ktor:ktor:0.9.5")
    compile("io.ktor:ktor-html-builder:0.9.5")
    compile("io.ktor:ktor-gson:0.9.5")
    compile("io.ktor:ktor-server-netty:0.9.5")
    compile("io.ktor:ktor-client-core:0.9.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
