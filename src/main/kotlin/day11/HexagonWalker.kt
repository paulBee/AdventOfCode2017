package day11

import kotlin.math.absoluteValue


fun distanceWalked(directions: List<String>, initalPostion: Triple<Int, Int, Int> = Triple(0,0,0)): Int =
        directions.map { it.toDirection() }
                .fold(initalPostion, {a, d ->  a.sum(d)})
                .maxAbsoluteValue()

fun maximumDistance(directions: List<String>, initalPostion: Triple<Int, Int, Int> = Triple(0,0,0)): Int =
        directions.map { it.toDirection() }
                .fold(Pair(initalPostion, 0), {a, d ->  Pair(a.first.sum(d), maxOf(a.second, a.first.maxAbsoluteValue()))})
                .let { it.second }

private fun Triple<Int, Int, Int>.maxAbsoluteValue(): Int =
        this.toList().map{ it.absoluteValue }.max()?:throw IllegalStateException("Didn't find a max value?")


private fun Triple<Int, Int, Int>.sum(d: Triple<Int, Int, Int>): Triple<Int, Int, Int> =
        Triple(this.first + d.first, this.second + d.second, this.third + d.third)

private fun String.toDirection(): Triple<Int, Int, Int> =
        when(this.toUpperCase()) {
            "N" -> Triple(1, -1, 0)
            "S" -> Triple(-1,1,0)
            "NE" -> Triple(1,0,-1)
            "SW" -> Triple(-1, 0, 1)
            "NW" -> Triple(0, -1, 1)
            "SE" -> Triple(0, 1, -1)
            else -> throw IllegalArgumentException("Unrecognised driection ${this}")
        }


/*
Hexagonal Co-ordinate system
       N
      ____+x
 NW  /    \  NE
    /      \
  +y\      /
 SW  \____/  SE
          +z
       S
 */