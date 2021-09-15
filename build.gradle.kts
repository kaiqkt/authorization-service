import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "me.kaique"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.koin:koin-core:1.0.0")
    implementation ("org.slf4j:slf4j-simple:1.7.30")
    implementation ("io.azam.ulidj:ulidj:1.0.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.4")
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}