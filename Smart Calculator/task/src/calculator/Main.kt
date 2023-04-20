package calculator


object Assigner {
    private val map  = mutableMapOf<String, Int>()


    fun process(input: String) {
        val list = input.replace("[\\s]+".toRegex(), "").split("=".toRegex(), 2)

        val lhs = list[0]
        val rhs = list[1]

        if (!lhs.matches("[a-zA-Z]+".toRegex())) {
            println("Invalid identifier")
            return
        }

        if (map.containsKey(rhs)) {
            map[lhs] = map[rhs]!!
            return
        }

        if (rhs.toIntOrNull() != null) {
            map[lhs] = rhs.toInt()
            return
        }

        println("Invalid assignment")
    }

    fun has(key: String) = map.contains(key)

    fun get(key: String) = map[key]
}



fun main() {

    var value = Value(value = 0)
    loop@ while (true){
        val input = readln()

        when  {
            input.isEmpty() -> continue

            // ... as commands
            input.contains("/exit") -> break
            input.contains("/help") -> {
                println("The program calculates the sum of numbers")
                continue
            }
            input[0] == '/' -> {
                println("Unknown command")
                continue
            }

            // ... as assignment
            input.contains("=") -> {
                Assigner.process(input)
                continue
            }
        }

        // ... as expression

        val list = input.split(" ").toMutableList()

        if (!check1(list)) {
            println("Invalid expression")
            continue
        }

        if (!check2(list)) {
            println("Unknown variable")
            continue
        }

        if (list.size >= 1) {

            val first = list.removeFirst()

            val x: Int? = first.toIntOrNull() ?: Assigner.get(first)

            if (x == null) {
                println("Unknown variable")
                continue
            }
            value = Value(value = x)
        }

        while (list.isNotEmpty()) {

            val op = list.removeFirst()

            val s = list.removeFirst()

            value = Value(
                left = value,
                op = op,
                right = Value(value = s.toIntOrNull() ?: Assigner.get(s))
            )
        }

        println(value.value)
    }
    println("Bye!")
}























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



fun check1(list: List<String>): Boolean {
    if (list.size % 2 == 0) {
        return false
    }

    var res = true
    for (i in list.indices) {

        res = res && when (i % 2 == 0) {
            true -> list[i].toIntOrNull() != null || "[a-zA-Z]+".toRegex().matches(list[i])

            false -> "([+]+|[-]+)".toRegex().matches(list[i])
        }
    }

    return res
}

fun check2(list: List<String>): Boolean {
    if (list.size % 2 == 0) {
        return false
    }

    var res = true
    for (i in list.indices) {

        res = res && when (i % 2 == 0) {
            true -> list[i].toIntOrNull() != null || Assigner.has(list[i])

            false -> "([+]+|[-]+)".toRegex().matches(list[i])
        }
    }

    return res
}