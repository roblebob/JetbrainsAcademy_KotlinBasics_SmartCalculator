package calculator

import java.lang.Exception

class Value(
    var left: Value? = null,
    var right: Value? = null,
    var op: String? = null,
    var value: Int? = null,
){

    constructor(value: Int) : this() {
        this.value = value
    }

    constructor(left: Value, right: Value, op: String) : this() {
        this.left = left
        this.right = right
        this.op = when {
            op!!.contains("+") || op!!.contains("-") && op!!.count {it == '-'} % 2 == 0 -> "+"
            op!!.contains("-") -> "-"
            else -> op
        }
        this.value = when(this.op) {
            "+" -> (this.left?.value?:0) + (this.right?.value?:0)
            "-" -> (this.left?.value?:0) - (this.right?.value?:0)
            else -> 0
        }
    }




}



fun main() {
    var value = Value(value = 0)
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

        val list = input.split(" ").toMutableList()




        if (list.size >= 1) {
            value = Value(value = list.removeFirst().toInt())
        }

        if (list.size == 1 || list.size % 2 == 1) {
            throw Exception("Invalid input")
        }

        while (list.isNotEmpty()) {

            value = Value(
                left = value,
                op = list.removeFirst(),
                right = Value(value = list.removeFirst().toInt())
            )
        }

        println(value.value)
    }
    println("Bye!")
}
