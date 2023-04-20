fun main() {
    // put your code here
    val n = readln().toInt()
    val k = readln().toInt()
    if (n <= 10_000 && k <= 10_000) {
        println(k % n)
    }
}