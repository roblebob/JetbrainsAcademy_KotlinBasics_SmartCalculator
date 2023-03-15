fun checkSpeed(speed: Int, limitInt: Int = 60, limitString: String = "no limit") {
    // write your code here

    val limit: Int = try {
        limitString.toInt()
    } catch(e: Exception) {
        limitInt
    }
    println( if (speed > limit) {
        "Exceeds the limit by ${speed - limit} kilometers per hour"
    } else {
        "Within the limit"
    })
}
