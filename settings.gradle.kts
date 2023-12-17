rootProject.name = "cli-utils"
include("dupliwise", "quickgen")


pluginManagement {
    plugins {
        id("org.springframework.boot") version "3.1.6"
        id("io.spring.dependency-management") version "1.1.4"
        id("org.graalvm.buildtools.native") version "0.9.28"
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("picocli", "4.7.5")
            library("picocli-spring", "info.picocli", "picocli-spring-boot-starter").versionRef("picocli")
            library("picocli-codegen", "info.picocli", "picocli-codegen").versionRef("picocli")
        }
    }
}