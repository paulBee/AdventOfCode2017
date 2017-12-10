package day10

import getWrappedIndex
import setWrappedIndex

class Knotter {

    fun performHash(list: MutableList<Int>, lengths: List<Int>) {
        var currentPosition = 0
        var skipSize = 0
        lengths.forEach {
            list.reverseWrappedSlice(currentPosition, (it -1 ))
            currentPosition = (currentPosition + it + skipSize) % list.size
            skipSize++
        }
    }

    fun MutableList<Int>.reverseWrappedSlice(startingFrom: Int, sliceSize: Int) =
        (startingFrom..startingFrom + sliceSize)
        .map { this.getWrappedIndex(it) }
        .reversed()
        .forEachIndexed { index, value -> this.setWrappedIndex(startingFrom + index, value) }

    fun computeHashString(list: MutableList<Int>): String = list.chunked(16).map { denseHash(it) }.reduce { acc, s -> acc + s }


    fun denseHash(sparseHashChunk: List<Int>): String =
            sparseHashChunk.reduce{int1, int2 -> int1.xor(int2) }
                    .let { Integer.toHexString(it) }
                    .let { when(it.length) {
                        1 -> "0" + it
                        else -> it
                    } }

    fun hash(string: String): String {
        val list = (0..255).toMutableList()
        val repeatedLengths = constructLenghtList(string)
        performHash(list, repeatedLengths)
        return computeHashString(list)
    }

    private fun constructLenghtList(string: String): List<Int> {
        val lengths = string.map { it.toInt() }.toMutableList()
        val additionalElements = listOf(17, 31, 73, 47, 23)
        lengths.addAll(additionalElements)
        return duplicate(lengths, 64)
    }

    private fun duplicate(lengths: List<Int>, repeatNumber: Int): List<Int> = (1..repeatNumber).flatMap { lengths }



}
