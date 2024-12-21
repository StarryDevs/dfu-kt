plugins {
    kotlin("jvm") version "2.1.0"
    id("maven-publish")
}

group = "starry.dfu"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://libraries.minecraft.net")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.mojang:datafixerupper:8.0.16")
    implementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

configure<PublishingExtension> {
    publications.create<MavenPublication>("maven") {
        from(components.getByName("kotlin"))
    }
}

