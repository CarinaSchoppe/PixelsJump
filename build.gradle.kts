/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 12:29 by Carina The Latest changes made by Carina on 28.03.22, 12:15.
 *  All contents of "build.gradle.kts" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "+"
    id("java")
    id("com.github.johnrengelman.shadow") version "+"
    id("xyz.jpenilla.run-paper") version "+"
    id("idea")
}

group = "me.carina"
version = "1.0.0"
description = "PixelsJump Remastered Paper Plugin"

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    implementation("com.google.code.gson:gson:+")
    compileOnly("io.papermc.paper:paper-api:+")
    testImplementation(kotlin("test"))
}
java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
    toolchain.languageVersion.set(JavaLanguageVersion.of(18))
}
tasks {
    runServer {
        minecraftVersion("1.19.2")
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
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xuse-k2",
                "-Xjdk-release=18"
            )
            jvmTarget = "18"
            languageVersion = "1.7"
        }
    }
    test {
        useJUnitPlatform()
    }
}



