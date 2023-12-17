plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("org.graalvm.buildtools.native")
}

group = "com.gvb"
version = rootProject.file("version.txt").readLines().toString().replace(Regex("[\\[\\]]"), "")

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
	implementation(libs.picocli.spring)
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor(libs.picocli.codegen)
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-tiny:latest")
}
