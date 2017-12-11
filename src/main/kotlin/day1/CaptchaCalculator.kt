package day1

import integerValue
import wrappedIndex


fun sumAdjacent(input: String) : Int = input
        .filterIndexed{index, value -> value == input.wrappedIndex(index + 1) }
        .map { it.integerValue() }
        .sum()

fun sumOpposite (input: String) : Int = input
        .filterIndexed{index, value -> value == input.wrappedIndex(index + input.length/2)}
        .map { it.integerValue() }
        .sum()
