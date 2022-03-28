import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    java
    id("xyz.jpenilla.run-paper") version "1.0.6"
    `java-library`
}

group = "me.carina"
version = "1.0.0"
description ="PixelsJump Remastered Paper Plugin"

repositories {
    gradlePluginPortal()
    mavenCentral()
        maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
}
java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
tasks {
    runServer {

        minecraftVersion("1.18.2")
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }


}
tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}
