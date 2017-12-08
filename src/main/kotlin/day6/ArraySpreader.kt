package day6

import getWrappedIndex
import setWrappedIndex

class ArraySpreader {

    fun stepsUntilRepeat(blocks: List<Int>): Int = processJumps(blocks.toMutableList(), mutableListOf(), 0)

    fun stepsUntilRepeat2(blocks: List<Int>): Int {
        val states = processJumps2(blocks.toMutableList(), mutableListOf())
        val repeatingState = states.groupBy({it}, {it}).values.filter { it.size > 1 }.last()[0]

        return states.lastIndexOf(repeatingState) - states.indexOfFirst { it.equals(repeatingState) }


    }


    tailrec private fun processJumps(blocks: MutableList<Int>, previousStates: MutableList<String>, jumps: Int): Int {

        val newJumps = jumps + 1
        val newBlocks = iterateBlocks(blocks)
        previousStates.add(newBlocks.toString())
        return when {
            endCondition(previousStates) -> newJumps
            else -> processJumps(newBlocks, previousStates, newJumps)
        }
    }

    tailrec private fun processJumps2(blocks: MutableList<Int>, previousStates: MutableList<String>): MutableList<String> {

        val newBlocks = iterateBlocks(blocks)
        previousStates.add(newBlocks.toString())
        return when {
            endCondition(previousStates) -> previousStates
            else -> processJumps2(newBlocks, previousStates)
        }
    }

    private fun endCondition(previousStates: MutableList<String>): Boolean = previousStates.containsDuplicates()

    private fun iterateBlocks(blocks: MutableList<Int>): MutableList<Int> {
        val maxSize = blocks.max()!!
        val startingIndex = blocks.indexOfFirst { it == maxSize }
        blocks[startingIndex] = 0
        (1..maxSize).map { it + startingIndex }.forEach { blocks.setWrappedIndex(it, blocks.getWrappedIndex(it) + 1) }
            return blocks
    }

}

private fun <E> Collection<E>.containsDuplicates(): Boolean = this.groupBy({it}, {it}).values.any { it.size > 1 }
