import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.9.0"
	kotlin("plugin.spring") version "1.9.0"
	kotlin("plugin.jpa") version "1.9.0"
	kotlin("plugin.serialization") version "1.9.0"
}

group = "br.kotlin"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_20
}

repositories {
	mavenCentral()
}

springBoot {
	buildInfo()
	mainClass.set("br.kotlin.consultacepservice.ConsultaCepServiceApplication")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.graphql:spring-graphql:1.2.3")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.4")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")

	implementation("org.springdoc:springdoc-openapi-data-rest:1.7.0")
	implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.7.0")

	runtimeOnly("com.h2database:h2:2.2.224")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
	implementation("io.ktor:ktor-client-core:2.3.6")
	implementation("io.ktor:ktor-client-cio:2.3.6")
	implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.6")
	implementation("io.ktor:ktor-client-content-negotiation:2.3.6")

	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")

	implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")

	runtimeOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

	compileOnly("org.projectlombok:lombok:1.18.28")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "20"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


