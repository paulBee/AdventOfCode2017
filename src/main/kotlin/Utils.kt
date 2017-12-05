fun Char.integerValue(): Int = this.toString().toInt()

fun String.wrappedIndex(index: Int): Char = this[index % this.length]

fun Int.takeGreater(other: Int): Int = if (this > other) this else other
fun Int.takeLesser(other: Int): Int = if (this < other) this else other
fun Int.differenceTo(other: Int) = Math.abs(this - other)

fun Double.ceilOdd(): Int = let {
    when (this % 2) {
        0.0 -> this + 1
        else -> this
    }.toInt()
}