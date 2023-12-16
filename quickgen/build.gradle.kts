plugins {
	java
	id("org.springframework.boot") version "3.1.6"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "com.gvp"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("info.picocli:picocli-spring-boot-starter:4.7.5")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("info.picocli:picocli-codegen:4.7.5")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-tiny:latest")
}
