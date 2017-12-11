package day11

import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat
import utils.FileOpener

class Day11Tests {

    @Test
    fun test1() {
        val directions = "ne,ne,ne".split(",")
        assertThat(distanceWalked(directions)).isEqualTo(3)
    }

    @Test
    fun test2() {
        val directions = "ne,ne,sw,sw".split(",")
        assertThat(distanceWalked(directions)).isEqualTo(0)
    }

    @Test
    fun test3() {
        val directions = "ne,ne,s,s".split(",")
        assertThat(distanceWalked(directions)).isEqualTo(2)
    }

    @Test
    fun test4() {
        val directions = "se,sw,se,sw,sw".split(",")
        assertThat(distanceWalked(directions)).isEqualTo(3)
    }

    @Test
    fun actual1() {
        val directions = FileOpener().getFile("/day11.txt")[0].split(',')
        println(distanceWalked(directions))
    }

    @Test
    fun actual2() {
        val directions = FileOpener().getFile("/day11.txt")[0].split(',')
        println(maximumDistance(directions))
    }
}