plugins {
    kotlin("jvm") version "2.3.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
    id("com.gradleup.shadow") version "8.3.1"
}

apply<TechDebtPlugin>()

group = "nl.craftsmen"
version = findProperty("appVersion")?.toString() ?: "dev"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.cucumber:cucumber-java:7.34.2")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.34.2")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("io.mockk:mockk:1.13.8")
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
    dependsOn("techDebtTodoCheck")
}

tasks.shadowJar {
    archiveFileName.set("connect-four.jar")
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}