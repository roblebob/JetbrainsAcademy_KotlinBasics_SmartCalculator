fun returnValue(): Int {
    val value = readln().toInt()

    // write your code here
    if (value > 0) {
        throw Exception("It's too big")
    }
    return value
}