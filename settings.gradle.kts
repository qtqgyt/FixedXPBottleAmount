pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
    }
}

plugins {
    id("fabric-loom") version "1.14-SNAPSHOT"
    id("dev.kikugie.stonecutter") version "0.8-beta.3"
}

rootProject.name = "FixedXPBottleAmount"

stonecutter {
    create(rootProject) {
        versions("1.21.11", "1.21.10")
    }
}
