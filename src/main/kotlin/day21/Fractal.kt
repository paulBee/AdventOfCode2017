package day21

import kotlin.math.sqrt

typealias Rule = Pair<SquareName, SquareName>
typealias Square = List<String>
typealias SquareName = String

class RuleSets(input: List<String>) {

    protected val rules: List<Rule> = input.map { parseRule(it) }

    fun parseRule(input:String): Rule = input
            .split(" => ")
            .map { it.trim() }
            .let {
                val (identity, goesTo) = it
                Rule(identity, goesTo)
            }

    tailrec fun mapsToSquares(square: SquareName, iterationsRemaining: Int): SquareName =
            when(iterationsRemaining) {
                1 -> square.runIteration()
                else -> mapsToSquares(square.runIteration(), iterationsRemaining.dec())
            }


    fun SquareName.runIteration(): SquareName =
        this.toSquare().toSubSquareNames()
                .map { it.toRule() }
                .map { it.second }
                .map { it.toSquare() }
                .let { it.toBigSquare() }
                .let { it.toSquareName() }

    fun SquareName.toRule() : Rule {
        val equivalents = this.equivalents()
        val matching = rules.filter { equivalents.contains(it.first) }
        if (matching.size != 1) throw IllegalStateException("Ambiguous Match")
        return matching[0]
    }

    fun List<Square>.toBigSquare() : Square {
        val squaresPerSide = sqrt(this.size.toFloat()).toInt()
        val squareSize = this.maxBy { it.size }!!.size
        return this
                .chunked(squaresPerSide)
                .flatMap { chunk -> (0 until squareSize)
                        .map { rowNum -> chunk
                                .map { it[rowNum] }
                                .reduce{acc, s -> acc + s}
                        }
                }
    }

    fun Square.toSubSquareNames() : List<SquareName> {
        val size = this.size
        return when(size % 2) {
            0 -> splitSquares(this, 2)
            else -> splitSquares(this, 3)
        }
    }
}


fun splitSquares(square: Square, sizes: Int): List<SquareName> {
    val squaresASide = square.size / sizes
    return square.chunked(sizes)
            .flatMap {
                val parts = it.map { it.chunked(sizes) }
                (0 until squaresASide)
                        .map { colNum -> parts.map { it[colNum] } }
            }
            .map { it.toSquareName() }
}

private fun buildColumn(sizes: Int, parts: List<List<String>>, columnNum: Int) =
        ((columnNum*sizes) until ((columnNum + 1)*sizes)).map { index -> parts.map { it[index] } }

fun String.equivalents(): List<String> {
    val identity = this.split("/")
    return listOf(identity, flipHorizontal(identity), flipVertical(identity))
            .flatMap { getRotations(it) }
            .map { it.toSquareName() }
}

fun getRotations(identity: List<String>): List<List<String>> =
        (1..3).fold(listOf(identity), { all, _ -> all + listOf(rotate(all.last()))})

fun rotate(identity: List<String>): List<String> =
        (0 until identity.size)
                .map { rowNum -> identity
                        .reversed()
                        .map { it[rowNum] }
                        .joinToString("")
                }
fun flipVertical(identity: List<String>): List<String> = identity.map { it.reversed() }
fun flipHorizontal(identity: List<String>): List<String> = identity.reversed()

fun Square.toSquareName(): SquareName = this.reduce { acc, s -> acc + "/" + s }
fun SquareName.toSquare(): Square = this.split("/")



