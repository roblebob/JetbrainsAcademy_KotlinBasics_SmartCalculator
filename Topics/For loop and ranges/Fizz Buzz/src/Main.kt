const val DIV1 = 3
const val DIV2 = 5

fun main() {
    val i1 = readln().toInt()
    val i2 = readln().toInt()
    for (i in i1..i2) {
        println(
            when {
                i % DIV1 == 0 && i % DIV2 == 0 -> "FizzBuzz"
                i % DIV1 == 0 -> "Fizz"
                i % DIV2 == 0 -> "Buzz"
                else -> i
            }
        )
    }
}