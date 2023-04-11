package calculator

fun main() {
    val inputs = readln().split(" ").toList().map { it.toInt() }
    println(inputs.sum())
}
