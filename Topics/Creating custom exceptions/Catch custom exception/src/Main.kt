fun main() {
    try {
        aToZ()
    } catch (e: InvalidInput){
        println(e.message)
    }
}