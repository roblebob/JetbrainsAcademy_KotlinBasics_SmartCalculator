//val test = listOf(1, 2, 4, 1, 2, 3, 5, 7, 8, 9, 10, 11)


fun main() {
    // write your code here
    val n = readln().toInt()
    val list = mutableListOf<Int>()
    repeat(n) {
        list.add(readln().toInt())
    }

//    val list = test.toMutableList()

    var biggestCountSoFar = 0
    var count = 0

    val iter = list.listIterator()

    while (iter.hasNext()) {
        val i = iter.nextIndex()
        val v = iter.next()

        if (i == 0) {
            count++
        } else {
            if (v >= list[i-1]) {
                count++
            } else {
                if (count > biggestCountSoFar) {
                    biggestCountSoFar = count
                }
                count = 1
            }
        }
    }
    println(
        if (count > biggestCountSoFar) {
            count
        } else {
            biggestCountSoFar
        }
    )
}