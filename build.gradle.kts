import org.gradle.internal.impldep.aQute.bnd.osgi.resource.Filters
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

buildscript {
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0")
    }
}


apply {
    plugin("org.junit.platform.gradle.plugin")
}


plugins {
    kotlin("jvm").version("1.2.51")
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
    compile("io.vertx:vertx-core:3.5.3")
    compile("io.vertx:vertx-web:3.5.3")
    compile("io.vertx:vertx-lang-kotlin:3.5.3")
    compile("org.litote.kmongo:kmongo:3.8.3")
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testCompile("junit:junit:4.12")
    testCompile("org.assertj:assertj-core:3.4.1")
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")
    testCompile("org.junit.platform:junit-platform-runner:1.3.1")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
