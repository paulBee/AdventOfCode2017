package day21

import org.junit.Test
import splitNewline
import utils.FileOpener


class RuleSetsTest {

    @Test
    fun actual1() {
        val ruleSets = RuleSets(FileOpener().getFile("/day21.txt"))
        val initSquare: SquareName = ".#./..#/###"

        val squares = ruleSets.mapsToSquares(initSquare, 5)

        println(squares)
        val answer = squares.toCharArray().filter { it == '#' }.size
        println(answer)



    }

    @Test
    fun actual2() {
        val ruleSets = RuleSets(FileOpener().getFile("/day21.txt"))
        val initSquare: SquareName = ".#./..#/###"

        val squares = ruleSets.mapsToSquares(initSquare, 18)

        println(squares)
        val answer = squares.toCharArray().filter { it == '#' }.size
        println(answer)



    }

    @Test
    fun testExample() {
        val ruleSets = RuleSets("""../.# => ##./#../...
.#./..#/### => #..#/..../..../#..#""".splitNewline())
        val initSquare: SquareName = ".#./..#/###"
        val squares = ruleSets.mapsToSquares(initSquare, 2)


        println(squares)
        val answer = squares.toCharArray().filter { it == '#' }.size
        println(answer)
    }

    @Test
    fun test2() {
        val square: SquareName = ".#./..#/###"

        square.equivalents()
                .forEach { println(it) }
    }

    @Test
    fun test3() {
        val square: SquareName = "..##/##../.#.#/#.#."

        splitSquares(square.toSquare(),2).forEach { println(it) }

    }

}