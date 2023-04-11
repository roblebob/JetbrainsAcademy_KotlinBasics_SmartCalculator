package calculator

fun main() {
    while (true){
        val input = readln()

        when  {
            input.contains("/exit") -> break
            input.contains("/help") -> {
                println("The program calculates the sum of numbers")
                continue
            }
            input.isEmpty() -> continue
        }

        val list = input.split(" ").toList().map { it.toInt() }
        println(list.sum())
    }
    println("Bye!")
}
