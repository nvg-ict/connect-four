plugins {
    kotlin("jvm") version "2.3.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
}

group = "nl.craftsmen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Cucumber (keep versions aligned)
    testImplementation("io.cucumber:cucumber-java:7.34.2")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.34.2")

    // JUnit Platform Suite annotations (nice runner style)
    testImplementation("org.junit.platform:junit-platform-suite:1.10.2")
}

kotlin {
    jvmToolchain(17)
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$rootDir/config/detekt/detekt.yml")
}

tasks.test {
    useJUnitPlatform()
}

tasks.check {
    dependsOn(tasks.detekt)
}