import org.jetbrains.kotlin.gradle.plugin.mpp.*

plugins {
    kotlin("multiplatform")
    `maven-publish`
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://dl.bintray.com/kotlin/kotlinx/")
    }
}

kotlin {

    val targetAttribute = Attribute.of("${project.group}.${project.name}.target", String::class.java)

    val commonNative = linuxX64("commonNative")

    targets.withType<KotlinNativeTarget> {
        attributes {
            attribute(targetAttribute, name)
        }
    }

    targets.withType<KotlinNativeTarget>().matching { it != commonNative }.all {
        compilations.all {
            if (!target.publishable) {
                defaultSourceSet.kotlin.setSrcDirs(emptyList<Any>())
            }
            defaultSourceSet {
                val main by commonNative.compilations
                dependsOn(main.defaultSourceSet)
            }
        }
    }


    with(commonNative) {
        val main by compilations
        main.defaultSourceSet {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.14.0")
            }
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
