fun main() {
    val n = readln().toInt()
    println(
        if (n == 1) {
            readln().toInt()
        } else {
            var max1 = 0
            var max2 = 0
            for (i in 1..n) {
                val new = readln().toInt()
                if (max1 < new) {
                    max2 = max1
                    max1 = new
                } else if (max2 < new) {
                    max2 = new
                }
            }
            max1 * max2
        }
    )
}
