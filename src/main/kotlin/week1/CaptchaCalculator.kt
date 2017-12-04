package week1

import integerValue
import wrappedIndex

class CaptchaCalculator {

    fun calc (input: String) : Int = input
            .filterIndexed{index, value -> value == input.wrappedIndex(index + 1) }
            .map { it.integerValue() }
            .sum()

    fun calc2 (input: String) : Int = input
            .filterIndexed{index, value -> value == input.wrappedIndex(index + input.length/2)}
            .map { it.integerValue() }
            .sum()
}