plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    `maven-publish`
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://dl.bintray.com/kotlin/kotlinx/")
    }
}

kotlin {
    commonNative {
        defaultSourceSet.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.14.0")
        }
    }

    linuxX64()
    macosX64()
    mingwX64 {
        binaries {
            executable {
                entryPoint = "sample.win.main"
            }
        }
    }
}
