const val N = 4

fun main() {
    List(N) { readln()[0] }.map { it.isDigit() }.map { println(it) }
}