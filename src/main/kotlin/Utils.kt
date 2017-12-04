fun Char.integerValue(): Int = this.toString().toInt()

fun String.wrappedIndex(index: Int): Char = this[index % this.length]

fun Int.takeGreater(other: Int): Int = if (this > other) this else other
fun Int.takeLesser(other: Int): Int = if (this < other) this else other