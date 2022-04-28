import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("io.qameta.allure").version("2.9.6")
    application
}


group = "me.denni"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val allureVersion = "2.17.2"
val logbackVersion = "1.2.3"
val selenideVersion = "6.4.0"
val aeonbitsVersion = "1.0.12"
val junitVersion = "5.8.2"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.codeborne:selenide:$selenideVersion")
    implementation("org.aeonbits.owner:owner:$aeonbitsVersion")
    implementation("io.github.microutils:kotlin-logging:1.12.5")
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    implementation("io.qameta.allure:allure-java-commons:$allureVersion")
    implementation("io.qameta.allure:allure-selenide:$allureVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.github.serpro69:kotlin-faker:1.8.0")
    implementation("com.github.javafaker:javafaker:1.0.2")
}

allure {
    version.set(allureVersion)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}

val runSmokeTestsTask = tasks.register<Test>("runSmokeTestSet") {
    useJUnitPlatform {
        includeTags("Smoke")
    }
}