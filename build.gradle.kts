import org.gradle.internal.impldep.aQute.bnd.osgi.resource.Filters
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0")
    }
}


apply {
    plugin("org.junit.platform.gradle.plugin")
}


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
    maven {setUrl("http://dl.bintray.com/kotlin/kotlinx")}
    maven {setUrl("http://dl.bintray.com/jetbrains/spek")}
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("khttp:khttp:0.1.0")
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testCompile("junit:junit:4.11")
    testCompile("org.assertj:assertj-core:3.4.1")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
