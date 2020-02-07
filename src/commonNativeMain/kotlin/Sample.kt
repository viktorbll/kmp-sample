package sample.common

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.modules.*

fun hello(): String = "Hello, Kotlin/Native!"


val json = Json(context = EmptyModule)

@Serializable
data class Stuff(
    val id: String,
    val num: Int,
    val data: String
)


fun Stuff.ser() = json.stringify(Stuff.serializer(), this)
