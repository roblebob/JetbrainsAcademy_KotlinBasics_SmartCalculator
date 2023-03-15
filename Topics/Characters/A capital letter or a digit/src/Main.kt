fun main() {
    val c = readln()[0]
    println(c.isDigit() && c != '0' || c.isUpperCase())
}