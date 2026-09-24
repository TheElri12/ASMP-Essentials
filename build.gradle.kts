plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.5"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

group = "de.theelri12"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.momirealms.net/releases/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    compileOnly("io.papermc.paper:paper-api:26.3.build.32-alpha")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.9")
    compileOnly("net.momirealms:craft-engine-core:26.8")
    compileOnly("net.momirealms:craft-engine-bukkit:26.8")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7") {
        isTransitive = false
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}