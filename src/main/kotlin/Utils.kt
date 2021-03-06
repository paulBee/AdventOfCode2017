import java.nio.file.Files
import java.nio.file.Paths

fun Char.integerValue(): Int = this.toString().toInt()

fun String.wrappedIndex(index: Int): Char = this[index % this.length]
fun String.splitWhitespace(): List<String> = this.split(Regex("\\s+"))
fun String.splitNewline(): List<String> = this.split("\n").filter { it.isNotEmpty() }

fun String.toDigit(): Int = Regex("""(-)?(\d+)""").find(this)
        ?.destructured
        ?.let { when(it.component1()) {
            "-" ->  - it.component2().toInt()
            else -> it.component2().toInt()
        } }
        ?: throw IllegalStateException("attempted to convert $this to a digit")


fun Collection<Any>.isSingleton(): Boolean = this.size == 1

fun MutableList<Int>.setWrappedIndex(index: Int, newValue: Int) {
    val unwrappedIndex = index % this.size
    this[unwrappedIndex] = newValue
}

fun List<Int>.getWrappedIndex(index: Int): Int = this[index % this.size]

fun Int.takeGreater(other: Int): Int = if (this > other) this else other
fun Int.takeLesser(other: Int): Int = if (this < other) this else other
fun Int.differenceTo(other: Int) = Math.abs(this - other)

fun Double.ceilOdd(): Int = let {
    when (this % 2) {
        0.0 -> this + 1
        else -> this
    }.toInt()
}

fun <T> duplicate(lengths: List<T>, repeatNumber: Int): List<T> = (1..repeatNumber).flatMap { lengths }

fun List<Int>.valueAfterValue(searchValue: Int): Int =this[this.indexOf(searchValue) + 1]


