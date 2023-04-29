package calculator

import java.math.BigInteger

val isOperand =  { s: String -> s.matches("([+]|[-])*[0-9]+".toRegex()) || Assigner.has(s) }


val isNumericOperand = { s: String -> s.matches("([+]|[-])*[0-9]+".toRegex()) }

val isOperator = { s: String -> s.matches("([+]|[-]|[*]|[/])".toRegex()) }

object Assigner {
    private val map  = mutableMapOf<String, BigInteger>()

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

        if (rhs.toBigIntegerOrNull() != null) {
            map[lhs] = rhs.toBigInteger()
            return
        }

        println("Invalid assignment")
    }

    fun has(key: String) = map.contains(key)

    fun get(key: String) = map[key]
}



fun main() {

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

            input.matches("[a-zA-Z]+".toRegex()) && !Assigner.has(input) -> {
                println("Unknown variable")
                continue
            }


            // ... as assignment
            input.contains("=") -> {
                Assigner.process(input)
                continue
            }

            // ... as expression
            else -> {
                val postfix = infixToPostfix(input
                    .replace("(", " ( ")
                    .replace(")", " ) ")
                    .split("[\\s]+".toRegex())
                    .filter { it.matches("[\\S]+".toRegex()) }
                    .map { it ->
                        when {
                            it.matches("[+]+".toRegex()) ||
                            it.matches("[-]+".toRegex()) && it.count { el -> el == '-' } % 2 == 0 -> "+"
                            it.matches("[-]+".toRegex()) && it.count { el -> el == '-' } % 2 == 1 -> "-"
                            else -> it
                        }
                    }
                    .toMutableList())

                if (postfix.isEmpty()) {
                    println("Invalid expression")
                    continue
                }

                println(evalPostfix(postfix) ?: "Invalid expression")

                continue
            }
        }
    }
    println("Bye!")
}


fun infixToPostfix(infix: MutableList<String>): MutableList<String> {
    // prep
    val stack = mutableListOf<String>()
    val postfix = mutableListOf<String>()

    val hasPrecedence = {op: String ->
        assert(isOperator(op))
        assert(isOperator(stack.last()))
        when {
            op.matches("([*]|[/])".toRegex()) && stack.last().matches("([+]|[-])".toRegex()) -> true
            else -> false
        }
    }

    while (infix.isNotEmpty()) {

        when {
            isOperand(infix.first()) -> postfix.add(infix.removeFirst())

            isOperator(infix.first()) -> {
                while (stack.isNotEmpty() && stack.last() != "(" && !hasPrecedence(infix.first())) {
                    postfix.add(stack.removeLast())
                }
                stack.add(infix.removeFirst())
            }

            infix.first() == "(" ->  stack.add(infix.removeFirst())



            infix.first() == ")" -> {
                while (stack.isNotEmpty() && stack.last() != "(") {
                    postfix.add(stack.removeLast())
                }
                // Discard the pair of parentheses.
                if (stack.isNotEmpty() && stack.last() == "(") {
                    stack.removeLast()
                } else {
                    return mutableListOf()
                }
                infix.removeFirst()
            }

            else -> {
                return mutableListOf<String>()
            }
        }

    }
    // At the end of the expression, pop the stack and add all operators to the result.
    while (stack.isNotEmpty()) {
        postfix.add(stack.removeLast())
    }
    // No parentheses should remain on the stack. Otherwise, the expression has unbalanced brackets. It is a syntax error.

    return postfix
}


fun evalPostfix(postfix: MutableList<String>): BigInteger? {

    val stack = mutableListOf<BigInteger?>()

    while (postfix.isNotEmpty()) {
        when {
            isNumericOperand(postfix.first()) -> stack.add(postfix.removeFirst().toBigInteger())

            Assigner.has(postfix.first()) -> stack.add(Assigner.get(postfix.removeFirst())!!)

            isOperator(postfix.first()) -> {
                val arg2 = stack.removeLast()
                val arg1 = stack.removeLast()

                stack.add(when (postfix.removeFirst()) {
                    "+" -> arg1?.plus(arg2!!)
                    "-" -> arg1?.minus(arg2!!)
                    "*" -> arg1?.times(arg2!!)
                    "/" -> arg1?.div(arg2!!)
                    else -> null
                })
            }

            else -> return null
        }
    }
    return stack.last()
}