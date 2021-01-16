data class Client(val name: String, val age: Int, val balance: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        return result
    }
}

fun main(args: Array<String>) {
    println(
            Client(readLine()!!, readLine()!!.toInt(), readLine()!!.toInt())
                    == Client(readLine()!!, readLine()!!.toInt(), readLine()!!.toInt())
    )
}