fun Char.integerValue(): Int = this.toString().toInt()

fun String.wrappedIndex(index: Int): Char = this[index % this.length]
fun String.splitWhitespace(): List<String> = this.split(Regex("\\s+"))
fun String.splitNewline(): List<String> = this.split("\n").filter { it.isNotEmpty() }
fun Collection<Any>.isSingleton(): Boolean = this.size == 1

fun Int.takeGreater(other: Int): Int = if (this > other) this else other
fun Int.takeLesser(other: Int): Int = if (this < other) this else other
fun Int.differenceTo(other: Int) = Math.abs(this - other)

fun Double.ceilOdd(): Int = let {
    when (this % 2) {
        0.0 -> this + 1
        else -> this
    }.toInt()
}