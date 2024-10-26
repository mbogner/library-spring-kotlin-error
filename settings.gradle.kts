rootProject.name = "spring-kotlin-error"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        val kotlinVersion: String by System.getProperties()
        val sonarqubeVersion: String by System.getProperties()
        val dokkaVersion: String by System.getProperties()

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("plugin.noarg") version kotlinVersion
        id("org.sonarqube") version sonarqubeVersion
        id("org.jetbrains.dokka") version dokkaVersion
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val bomVersion: String by System.getProperties()
            version("bom", bomVersion)
            library("bom", "dev.mbo", "spring-boot-bom").versionRef("bom")

            val kotlinLoggingVersion: String by System.getProperties()
            version("kotlin-logging", kotlinLoggingVersion)
            library("kotlin-logging", "dev.mbo", "kotlin-logging")
                .versionRef("kotlin-logging")

            val springKotlinReflectionVersion: String by System.getProperties()
            version("spring-kotlin-reflection", springKotlinReflectionVersion)
            library("spring-kotlin-reflection", "dev.mbo", "spring-kotlin-reflection")
                .versionRef("spring-kotlin-reflection")
        }
    }
}