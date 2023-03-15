fun main() {
    List(4) { readln().toCharArray()[0] }.map { it - 1 }.map { println(it) }
}