// do not change this data class
data class Box(val height: Int, val length: Int, val width: Int)

fun main(args: Array<String>) =
    with(Array(4) { readLine()!!.toInt() }) {
        Box(get(0), get(1), get(3))
                .apply { println(this) }
                .copy(length = get(2))
                .apply { println(this) }
    }
