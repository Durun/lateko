import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.github.durun"
version = "0.1-SNAPSHOT"

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

plugins {
	maven
	kotlin("jvm") version "1.3.71"
	application
	// Use shadowJar for generating fatJar.
	id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
	jcenter()
}

dependencies {
	// Align versions of all Kotlin components.
	implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

	implementation(kotlin("reflect"))

	// Use the Kotlin JDK 8 standard library.
	implementation(kotlin("stdlib-jdk8"))

	// Use the Kotlin Script library.
	implementation(kotlin("script-runtime"))
	implementation(kotlin("script-util"))
	implementation(kotlin("compiler-embeddable"))
	implementation(kotlin("scripting-compiler-embeddable"))

	// Use the Kotlin test library.
	val kotestVersion = "4.0.0-BETA1"
	testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
}

application {
	// Define the main class for the application.
	mainClassName = "io.github.durun.lateko.AppKt"
}

tasks {
	withType<KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}
	withType<Test> {
		useJUnitPlatform()
	}
}