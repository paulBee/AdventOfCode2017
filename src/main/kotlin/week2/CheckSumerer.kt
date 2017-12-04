package week2

import takeGreater
import takeLesser

class CheckSumerer {

    fun checkTheSum(spreadSheetString: String): Int
            = wrangleSpreadsheet(spreadSheetString).map { rowCheckSum(it) }.sum()

    fun checkTheSum2(spreadSheetString: String): Int
            = wrangleSpreadsheet(spreadSheetString).map { integerDivisor(it) }.sum()

    private fun integerDivisor(row: List<Int>): Int {
        val matchingPair = row.flatMap { elem -> row.map { Pair(it, elem) } }
                .filter { (first, second) -> first % second == 0 }
                .find { (first, second) -> first != second }
        return matchingPair!!.first / matchingPair.second
    }

    private fun rowCheckSum(row: List<Int>): Int {
        val minAndMax = row.fold(Pair(Int.MAX_VALUE, Int.MIN_VALUE), { (first, second), elem ->
            Pair(first.takeLesser(elem), second.takeGreater(elem))
        })
        return minAndMax.second - minAndMax.first
    }

    private fun wrangleSpreadsheet(spreadSheetString: String): List<List<Int>>
            = spreadSheetString.split("\n")
            .map { it.split("""[ \t]""".toRegex())
                    .map { it.trim().toInt() }
            }.filter { it.isNotEmpty() }

}
