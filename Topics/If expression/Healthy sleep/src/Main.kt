fun main() {
    val a = readln().toInt()
    val b = readln().toInt()
    val h = readln().toInt()

    println(if (h < a) "Deficiency" else if (h > b) "Excess" else "Normal")
}