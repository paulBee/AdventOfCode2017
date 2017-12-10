package day10

import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat

class Day10Tests {

    val knotter = Knotter()

    @Test
    fun test1() {
        val lengths = """3,4,1,5""".split(",").map { it.toInt() }
        val list = (0..4).toMutableList()
        knotter.performHash(list,lengths)
        assertThat(list[0] * list[1]).isEqualTo(12)
    }

    @Test
    fun actual1() {
        val lengths = """192,69,168,160,78,1,166,28,0,83,198,2,254,255,41,12""".split(",").map { it.toInt() }
        val list = (0..255).toMutableList()
        knotter.performHash(list, lengths)
        println(list[0] * list[1])
    }

    @Test
    fun actual2() {
        val lengths = """192,69,168,160,78,1,166,28,0,83,198,2,254,255,41,12"""

        println(knotter.hash(lengths))
    }

    @Test
    fun test2() {
        val result = knotter.hash("")
        assertThat(result).isEqualTo("a2582a3a0e66e6e86e3812dcb672a272")
    }

    @Test
    fun test3() {
        val result = knotter.hash("AoC 2017")
        assertThat(result).isEqualTo("33efeb34ea91902bb2f59c9920caa6cd")
    }

    @Test
    fun test4() {
        val result = knotter.hash("1,2,3")
        assertThat(result).isEqualTo("3efbe78a8d82f29979031a4aa0b16a9d")
    }

    @Test
    fun test5() {
        val result = knotter.hash("1,2,4")
        assertThat(result).isEqualTo("63960835bcdc130f0b66d7ff4f6a5a8e")
    }



    @Test
    fun testDenseHash() {
        val list = listOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22)

        assertThat(knotter.denseHash(list)).isEqualTo("40")
    }
}