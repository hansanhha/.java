plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "playground"
include("theme-park-status", "theme-park-api")
