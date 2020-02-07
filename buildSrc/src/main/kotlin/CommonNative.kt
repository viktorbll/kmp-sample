import org.gradle.api.attributes.*
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.*
import org.jetbrains.kotlin.gradle.plugin.*
import org.jetbrains.kotlin.gradle.plugin.mpp.*


private val targetAttribute = Attribute.of("com.example.kotlin.target", String::class.java)

fun KotlinMultiplatformExtension.commonNative(
    configure: KotlinNativeTargetWithTests.() -> Unit = { }
) = linuxX64("commonNative") {
    attributes.attribute(targetAttribute, name)
    val commonSourceSet = defaultSourceSet
    targets.withType<KotlinNativeTarget>().matching { it != this }.all {
        with(mainCompilation) {
            defaultSourceSet {
                if (!target.publishable) {
                    kotlin.setSrcDirs(emptyList<Any>())
                }
                dependsOn(commonSourceSet)
            }
        }
    }
    configure()
}

val KotlinNativeTarget.mainCompilation: KotlinNativeCompilation get() = compilations["main"]

val KotlinNativeTarget.defaultSourceSet: KotlinSourceSet get() = mainCompilation.defaultSourceSet
