package week2

import takeGreater
import takeLesser

class CheckSumerer {

    fun checkTheSum(spreadSheetString: String): Int
            = wrangleSpreadsheet(spreadSheetString).map { rowCheckSum(it) }.sumBy{ it.second - it.first }

    fun checkTheSum2(spreadSheetString: String): Int
            = wrangleSpreadsheet(spreadSheetString).map { integerDivisor(it) }.sumBy{ it.first / it.second }

    //TODO why can I <Pair>.sumBy() but not <Pair>.map{}.sum()

    private fun integerDivisor(row: List<Int>): Pair<Int, Int> = row.flatMap { elem -> row.map { Pair(it, elem) } }
            .filter { (first, second) -> first % second == 0 }
            .find { (first, second) -> first != second }!!

    private fun rowCheckSum(row: List<Int>): Pair<Int, Int>
            = row.fold(Pair(Int.MAX_VALUE, Int.MIN_VALUE),
            { (first, second), elem -> Pair(first.takeLesser(elem), second.takeGreater(elem))
        })

    private fun wrangleSpreadsheet(spreadSheetString: String): List<List<Int>>
            = spreadSheetString.split("\n")
            .map { it.split("""[ \t]""".toRegex())
                    .map { it.trim().toInt() }
            }.filter { it.isNotEmpty() }

}
