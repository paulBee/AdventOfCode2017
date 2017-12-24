package day19

import day3.Direction.*
import day3.coordsInDirection

class Maze(input: List<String>) {

    val height = input.size
    val width = input.map { it.length }.max()!!
    val maze = input.map { it.padEnd(width, ' ').toCharArray().toList() }

    var currentCoords = Pair(maze[0].indexOf('|'), 0)
    var nextDirection = UP

    private fun spaceIsNotEmpty(coords: Pair<Int, Int>): Boolean {
        val (x,y) = coords
        return (x in 0 until width) && (y in 0 until height) && maze[y][x] != ' '
    }

    fun nextElement(): Char {
        val (x, y) = coordsInDirection(currentCoords, nextDirection)
        currentCoords = Pair(x,y)
        return maze[y][x].let {
            if(it == '+') {
                turn()
            }
            it
        }
    }

    fun mazePath(): Sequence<Char> {
        return generateSequence { nextElement() }.takeWhile { spaceIsNotEmpty(currentCoords) }
    }

    private fun turn() {
        when(nextDirection) {
            UP, DOWN ->  nextDirection = if (spaceIsNotEmpty(coordsInDirection(currentCoords, LEFT))) LEFT else RIGHT
            RIGHT, LEFT -> nextDirection = if (spaceIsNotEmpty(coordsInDirection(currentCoords, UP))) UP else DOWN
        }
    }

}