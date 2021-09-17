import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mainPkgAndClass = "com.kaique.application.Main"
group = "com.kaique"
version = "1.0.0"

plugins {
    application
    kotlin("jvm") version "1.4.10"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("io.javalin:javalin:3.13.10")
    implementation("org.koin:koin-core:1.0.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.4")
    implementation ("org.slf4j:slf4j-simple:1.7.30")
    implementation ("io.azam.ulidj:ulidj:1.0.0")
    implementation("org.litote.kmongo:kmongo:4.2.8")
    implementation("io.jsonwebtoken:jjwt:0.7.0")
    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-core:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.0")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    implementation(kotlin("stdlib-jdk8"))
}

application {
    mainClassName = mainPkgAndClass
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<CreateStartScripts> { mainClassName = mainPkgAndClass }

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes("Main-Class" to mainPkgAndClass)
        attributes("Package-Version" to archiveVersion)
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    from(sourceSets.main.get().output)
}

fun loadEnv(environment: MutableMap<String, Any>, file: File) {
    if(!file.exists()) throw IllegalArgumentException("failed to load environment from file, ${file.name} not found")

    file.readLines().forEach{ line ->
        if (line.isBlank() || line.startsWith("#")) return@forEach
        line.split("=", limit = 2)
            .takeIf { it.size == 2 && it[0].isNotBlank() }
            ?.run { Pair(this[0].trim(), this[1].trim()) }
            ?.run { environment[this.first] = this.second }
    }
}

tasks.withType<JavaExec>{
    loadEnv(environment, file("variables.env"))
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}