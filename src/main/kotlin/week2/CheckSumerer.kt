package week2

import splitNewline
import splitWhitespace
import takeGreater
import takeLesser

class CheckSumerer {

    fun checkTheSum(spreadSheetString: String): Int
            = wrangleSpreadsheet(spreadSheetString).map { rowCheckSum(it) }.sumBy{ it.second - it.first }

    fun checkTheSum2(spreadSheetString: String): Int
            = wrangleSpreadsheet(spreadSheetString).map { integerDivisor(it) }.sumBy{ it.first / it.second }

    private fun integerDivisor(row: List<Int>): Pair<Int, Int> = row.flatMap { elem -> row.map { Pair(it, elem) } }
            .filter { (first, second) -> first % second == 0 }
            .find { (first, second) -> first != second }!!

    private fun rowCheckSum(row: List<Int>): Pair<Int, Int>
            = row.fold(Pair(Int.MAX_VALUE, Int.MIN_VALUE),
            { (first, second), elem -> Pair(first.takeLesser(elem), second.takeGreater(elem))
        })

    private fun wrangleSpreadsheet(spreadSheetString: String): List<List<Int>>
            = spreadSheetString.splitNewline()
            .map { it.splitWhitespace()
                    .map { it.trim().toInt() }
            }.filter { it.isNotEmpty() }

}
