import java.lang.Exception

const val e = "The race can't be won with this car"
const val ok = "I will definitely win!"

fun findCar(): String {
    val maxSpeed = readln().toInt()
    val accTime = readln().toInt()

    // write your code here
    if (maxSpeed >= 120 && accTime <= 4 && accTime >= 1) {
        return ok
    } else {
        throw Exception(e)
    }
}