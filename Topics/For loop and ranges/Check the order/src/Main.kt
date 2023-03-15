fun main() {
    val n = readln().toInt()
    var cond = true
    var old = Int.MIN_VALUE
    for (i in 1..n) {
        val new = readln().toInt()
        cond = cond && old <= new
        old = new
    }
    println(if (cond) "YES" else "NO")
}