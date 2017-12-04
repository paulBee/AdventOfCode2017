package week1

class CaptchaCalculator {

    fun calc (input: String) : Int = input
            .filterIndexed{index, value -> compareToNext(value, input, index) }
            .map { charToInt(it) }
            .sum()

    fun calc2 (input: String) : Int = input
            .filterIndexed{index, value -> compareSkipping(value, index, input, input.length/2)}
            .map { charToInt(it) }
            .sum()

    private fun charToInt(it: Char) = it.toString().toInt()

    private fun compareToNext(value: Char, input: String, index: Int) = compareSkipping(value, index, input, 1);

    private fun compareSkipping(value: Char, currIndex: Int, fullString: String, skipNumber: Int) =
            value == fullString[(currIndex + skipNumber) % fullString.length]
}