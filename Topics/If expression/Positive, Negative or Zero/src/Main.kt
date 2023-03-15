fun main() {
    val v = readln().toInt()
    println(if (v < 0) "negative" else if (v > 0) "positive" else "zero")
}