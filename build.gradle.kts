import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

val modVersion = DateTimeFormatter.ofPattern("yyyyMMdd").format(Instant.now().atZone(ZoneOffset.UTC))

plugins {
    id("fabric-loom")
    id("me.modmuss50.mod-publish-plugin") version "1.1.0"
}

version = "${modVersion}+${stonecutter.current.version}"
base.archivesName = property("mod.id") as String

val requiredJava = when {
    stonecutter.eval(stonecutter.current.version, ">=1.21") -> JavaVersion.VERSION_21
    else -> JavaVersion.VERSION_1_8 // add more java versions later
}

loom {
    fabricModJsonPath = rootProject.file("src/main/resources/fabric.mod.json")

    decompilerOptions.named("vineflower") {
        options.put("mark-corresponding-synthetics", "1")
    }

    runConfigs.all {
        ideConfigGenerated(true)
        vmArgs("-Dmixin.debug.export=true")
        runDir = "../../run"
    }
}

fabricApi {
    configureDataGeneration {
        client = true
    }
}

loom {
    splitEnvironmentSourceSets()
}

dependencies {
    minecraft("com.mojang:minecraft:${stonecutter.current.version}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
}

java {
    withSourcesJar()
    targetCompatibility = requiredJava
    sourceCompatibility = requiredJava
}

tasks {
    processResources {
        filesMatching("fabric.mod.json") {
            expand(
                "mod_id" to project.property("mod.id").toString(),
                "mod_name" to project.property("mod.name").toString(),
                "version" to version,
                "minecraft_version" to stonecutter.current.version,
                "loader_version" to project.property("deps.fabric_loader").toString()
            )
        }
    }
    register<Copy>("buildAndCollect") {
        group = "build"
        from(remapJar.map { it.archiveFile }, remapSourcesJar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${modVersion}"))
        dependsOn("build")
    }
}

publishMods {
    file = tasks.remapJar.map { it.archiveFile.get() }
    additionalFiles.from(tasks.remapSourcesJar.map { it.archiveFile.get() })
    displayName = "${property("mod.name")} ${modVersion} [${stonecutter.current.version}]"
    version = "${modVersion}+${stonecutter.current.version}"
    changelog = "¯\\_(ツ)_/¯"
    type = STABLE
    modLoaders.add("fabric")

    dryRun = providers.environmentVariable("MODRINTH_TOKEN").getOrNull() == null

    modrinth {
        projectId = property("publish.modrinth") as String
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        minecraftVersions.addAll(property("mod.targets").toString().split(' '))
        requires {
            slug = "fabric-api"
            version = property("deps.fabric_api").toString()
        }
    }
}