package parking

class ParkingLot(val countSpots: Int) {
    private val lot = mutableMapOf<Int, Car?>()

    init {
        (1..countSpots).forEach { lot[it] = null }
    }

    fun freeSpots() = lot.filter { it.value == null }.keys.sorted()

    fun occupiedSpots() = lot.filter { it.value != null }.toList().sortedBy { it.first }

    fun park(car: Car): Int =
            freeSpots().firstOrNull()?.also { lot[it] = car }
                    ?: throw ParkingError("Sorry, the parking lot is full.")

    fun leave(spot: Int): Car =
            lot[spot].also { lot[spot] = null }
                    ?: throw ParkingError("There is no car in spot $spot.")
}