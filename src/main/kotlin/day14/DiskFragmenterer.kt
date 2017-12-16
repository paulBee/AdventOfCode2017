package day14

import day10.Knotter

object DiskFragmenterer {

    fun countUsedData(input: String): Int =
            BuildBinaryArray(input)
                .flatMap{ it }
                .sum()

    fun countRegions(input: String, printSolution: Boolean = false): Int {

        val binaryMap = convertToCoOrdsMap(BuildBinaryArray(input))

        var groupNum = 0
        while(binaryMap.values.any { it.usedButNotPlaced() }) {
            groupNum++

            val rootOfNewGroup = binaryMap.filter { it.value.usedButNotPlaced() }.keys.first()

            buildGroupFromElement(binaryMap, rootOfNewGroup, groupNum)
        }

        if(printSolution) {
            val paddingSize = groupNum.toString().length + 1
            (0..127).forEach { xCoord ->
            (0..127).map { Pair(xCoord, it) }
                    .map { binaryMap[it]!! }
                    .map { it.second?.toString() ?: "." }
                    .forEach { print(it.padStart(paddingSize/2,' ').padEnd(paddingSize, ' ')) }
                println()
            }
        }
        return groupNum
    }

    private fun buildGroupFromElement(binaryMap: MutableMap<Pair<Int, Int>, Pair<Boolean, Int?>>, elementKey: Pair<Int, Int>, groupNum: Int) {
        val (isUsed, groupNumber) = binaryMap[elementKey]?: return
         when {
             !isUsed -> return
             groupNumber != null -> return
             else -> {
                 binaryMap.set(elementKey, Pair(isUsed, groupNum))
                 buildGroupFromElement(binaryMap, Pair(elementKey.first + 1, elementKey.second), groupNum)
                 buildGroupFromElement(binaryMap, Pair(elementKey.first - 1, elementKey.second), groupNum)
                 buildGroupFromElement(binaryMap, Pair(elementKey.first, elementKey.second + 1), groupNum)
                 buildGroupFromElement(binaryMap, Pair(elementKey.first, elementKey.second - 1), groupNum)
             }
         }
    }

    private fun convertToCoOrdsMap(binaryLists: List<List<Int>>): MutableMap<Pair<Int, Int>, Pair<Boolean, Int?>> {
        val map: MutableMap<Pair<Int, Int>, Pair<Boolean, Int?>> = mutableMapOf()

        binaryLists.forEachIndexed { x, list ->
            list.forEachIndexed { y, booleanInt ->
                map.put(Pair(x,y), Pair(booleanInt == 1, null))
            }
        }

        return map
    }

    private fun BuildBinaryArray(input: String): List<List<Int>> {
        return (0..127).map { "$input-$it" }
                .map { Knotter.hash(it) }
                .map {
                    it.chunked(1)
                            .flatMap { it.toBinaryArrayRepresentation() }
                }
    }
}

private fun Pair<Boolean, Int?>.usedButNotPlaced(): Boolean = first && second == null

private fun String.toBinaryArrayRepresentation(): List<Int> =
        when(this) {
            "0" -> listOf(0,0,0,0)
            "1" -> listOf(0,0,0,1)
            "2" -> listOf(0,0,1,0)
            "3" -> listOf(0,0,1,1)
            "4" -> listOf(0,1,0,0)
            "5" -> listOf(0,1,0,1)
            "6" -> listOf(0,1,1,0)
            "7" -> listOf(0,1,1,1)
            "8" -> listOf(1,0,0,0)
            "9" -> listOf(1,0,0,1)
            "a" -> listOf(1,0,1,0)
            "b" -> listOf(1,0,1,1)
            "c" -> listOf(1,1,0,0)
            "d" -> listOf(1,1,0,1)
            "e" -> listOf(1,1,1,0)
            "f" -> listOf(1,1,1,1)
            else -> throw IllegalStateException("Not a HEX string fool! - $this")
        }
