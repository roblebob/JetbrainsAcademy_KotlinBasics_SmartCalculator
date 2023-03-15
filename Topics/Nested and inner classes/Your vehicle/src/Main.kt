class Vehicle {
    inner class Engine {
        fun start() {
            println("RRRrrrrrrr....")
        }
    }
}
// do not touch the class above

fun main() {
    // start your vehicle, put your code here
    val car: Vehicle = Vehicle()
    val engine: Vehicle.Engine = car.Engine()
    engine.start()
}