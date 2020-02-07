package sample.win

import sample.common.*

fun main() {
    println(hello())
    println(Stuff(
        id = "asdf",
        num = 123,
        data = ""
    ).ser())
}
