package parking

object ConsoleInterface {
    private var parking: ParkingLot? = null
        get() {
            return field ?: println("Sorry, a parking lot has not been created.").let { null }
        }

    private fun create(countSpots: String) {
        parking = ParkingLot(countSpots.toInt())
        println("Created a parking lot with $countSpots spots.")
    }

    private fun park(number: String, color: String) =
            parking?.apply {
                val car = Car(number, color)
                val spot = park(car)
                println("${car.color} car parked in spot $spot.")
            }


    private fun leave(spot: String) =
            parking?.apply {
                leave(spot.toInt())
                println("Spot $spot is free.")
            }


    private fun status() =
            parking?.apply {
                occupiedSpots().takeIf { it.isEmpty() }?.let { println("Parking lot is empty.") }
                occupiedSpots().forEach { println("${it.first} ${it.second?.number} ${it.second?.color}") }
            }


    private fun toString(filter: (car: Car) -> Boolean, printed: (spot: Int, car: Car) -> String): String? =
            parking?.occupiedSpots()
                    ?.filter { filter(it.second!!) }
                    ?.joinToString { printed(it.first, it.second!!) }

    private fun regByColor(color: String) =
            toString({ it.color.toUpperCase() == color.toUpperCase() }, { spot, car -> car.number })?.apply {
                takeIf { it.isEmpty() }
                        ?.let { println("No cars with color $color were found.") }
                        ?: println(this)
            }

    private fun spotByColor(color: String) =
            toString({ it.color.toUpperCase() == color.toUpperCase() }, { spot, car -> spot.toString() })?.apply {
                takeIf { it.isEmpty() }
                        ?.let { println("No cars with color $color were found.") }
                        ?: println(this)
            }

    private fun spotByReg(number: String) =
            toString({ it.number == number }, { spot, car -> spot.toString() })?.apply {
                takeIf { it.isEmpty() }
                        ?.let { println("No cars with registration number $number were found.") }
                        ?: println(this)
            }

    fun run() {
        while (true) {
            val command = readLine()!!.split(" ")
            try {
                when (command[0]) {
                    "create" -> create(command[1])
                    "park" -> park(command[1], command[2])
                    "leave" -> leave(command[1])
                    "status" -> status()
                    "reg_by_color" -> regByColor(command[1])
                    "spot_by_color" -> spotByColor(command[1])
                    "spot_by_reg" -> spotByReg(command[1])
                    "exit" -> return
                    else -> println("Unknow command")
                }
            } catch (e: ParkingError) {
                println(e.message)
            }
        }
    }
}