package day19

import org.junit.Test
import utils.FileOpener


class MazeTest  {

    @Test
    fun test1() {
        val maze = Maze(FileOpener().getFile("/day19.small.txt"))
        val taken = maze.mazePath()
                .filter { it.isLetter() }
                .toList()
                .fold ( "", { acc, c -> acc + c.toString() })

        println(taken)
    }

    @Test
    fun actual1() {
        val maze = Maze(FileOpener().getFile("/day19.txt"))
        val letters = maze.mazePath()
                .filter { it.isLetter() }
                .toList()
                .fold ( "", { acc, c -> acc + c.toString() })

        println(letters)
    }

    @Test
    fun actual2() {
        val maze = Maze(FileOpener().getFile("/day19.txt"))
        val steps = maze.mazePath()
                .toList()

        println(steps.last())
        println(steps.size)
    }
}