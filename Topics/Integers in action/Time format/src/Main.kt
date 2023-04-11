fun main() {
    val totalSeconds = System.currentTimeMillis() / 1000 // do not change this line
    // enter your code
    val days = totalSeconds / (60 * 60 * 24)
    var restSeconds = totalSeconds % (60 * 60 * 24)
    val hours = restSeconds / (60 * 60)
    restSeconds = restSeconds % (60 * 60)
    val minutes = restSeconds / (60)
    val seconds = restSeconds % (60)
    println("$hours:$minutes:$seconds")
}