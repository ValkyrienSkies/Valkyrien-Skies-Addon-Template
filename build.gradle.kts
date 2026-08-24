plugins {
    kotlin("jvm") version "2.0.0"
    id("fabric-loom") version "1.9-SNAPSHOT"
    id("maven-publish")
}

version = project.property("mod_version") as String
group = project.property("maven_group") as String

base {
    archivesName.set(project.property("archives_base_name") as String)
}

val targetJavaVersion = 17
java {
    toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()
}

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.

    maven {
        name = "Valkyrien Skies Internal"
        url = uri("https://maven.valkyrienskies.org")
        content {
            excludeGroup("net.jodah")
        }
    }
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("kotlin_loader_version")}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")

    // region VS dependency
    modImplementation("org.valkyrienskies:valkyrienskies-120-fabric:${project.property("vs2_version")}") { isTransitive = false
        exclude(group = "org.valkyrienskies.core", module = "")
    }

    modImplementation("fuzs.forgeconfigapiport:forgeconfigapiport-fabric:${project.property("fcap_version")}")
    modRuntimeOnly("maven.modrinth:forge-config-screens:v8.0.2-1.20.1-Fabric")
    modRuntimeOnly("maven.modrinth:modmenu:${project.property("modmenu_version")}")

    implementation("org.valkyrienskies.core:api:${project.property("vs_core_version")}") {
        exclude(group = "org.joml", module = "")
    }
    implementation("org.valkyrienskies.core:util:${project.property("vs_core_version")}") {
        exclude(group = "org.joml", module = "")
    }
    implementation("org.valkyrienskies.core:internal:${project.property("vs_core_version")}") {
        exclude(group = "org.joml", module = "")
    }
    implementation("org.joml:joml-primitives:1.10.0")
    implementation("org.joml:joml:1.10.4")

    api("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
    api("org.jetbrains.kotlin:kotlin-reflect:2.0.0")
    // endregion
}

tasks.processResources {
    inputs.property("version", project.version)
    inputs.property("minecraft_version", project.property("minecraft_version"))
    inputs.property("loader_version", project.property("loader_version"))
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand(
            "version" to project.version,
            "minecraft_version" to project.property("minecraft_version")!!,
            "loader_version" to project.property("loader_version")!!,
            "mod_name" to project.property("mod_name")!!,
            "description" to project.property("description")!!,
            "author" to project.property("author")!!,
            "license" to project.property("license")!!
        )
    }
}

tasks.withType<JavaCompile>().configureEach {
    // ensure that the encoding is set to UTF-8, no matter what the system default is
    // this fixes some edge cases with special characters not displaying correctly
    // see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
    // If Javadoc is generated, this must be specified in that task too.
    options.encoding = "UTF-8"
    options.release.set(targetJavaVersion)
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.base.archivesName.get()}" }
    }
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.property("archives_base_name") as String
            from(components["java"])
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}
