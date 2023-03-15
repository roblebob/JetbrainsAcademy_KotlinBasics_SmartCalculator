fun main() {
    // put your code here
    val x = readln().toInt()
    val yList = listOf(2 ,3, 5, 6)

    for (y in yList) {
        if (x % y == 0) {
            println("Divided by $y")
        }
    }
}