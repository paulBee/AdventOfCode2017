package day3

import ceilOdd
import differenceTo
import java.lang.Math.*

class ManhattanDistance {

    fun calc(number: Int): Int = widthOfGrid(number).let{ calcStepsToEdge(it) + calcStepsFromMidPoint(it, number) }

    fun calc2(number: Int): Int{
        val grid = ManhattenGrid()
        return generateSequence{ grid.next() }.takeWhile { it < number }.last()
    }

    private fun calcStepsToEdge(cityWidth: Int): Int = halfWidth(cityWidth)

    private fun calcStepsFromMidPoint(it: Int, number: Int) = midPointValues(it).map { it.differenceTo(number) }.min()!!

    private fun midPointValues(cityWidth: Int): List<Int> = intArrayOf(0, 1, 2, 3).map{ midpointStepsFormula(cityWidth)(it)}

    private fun widthOfGrid(number: Int): Int = ceil(sqrt(number.toDouble())).ceilOdd()

    private fun midpointStepsFormula(cityWidth: Int): (Int) -> Int = { cityWidth * cityWidth - halfWidth(cityWidth) * (it*2 + 1)}

    private fun halfWidth(cityWidth: Int) = ((cityWidth - 1) / 2)
}