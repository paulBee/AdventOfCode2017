package day12

import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat
import splitNewline
import utils.FileOpener

class Day12Tests {

    val pipeChecker = PiperCheckerer()

    @Test
    fun test1() {
        val list = """0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5""".splitNewline()
        assertThat(pipeChecker.calc(list)).isEqualTo(6)
    }

    @Test
    fun actual1() {
        val list = FileOpener().getFile("/day12.txt")
        println(pipeChecker.calc(list))
    }

    @Test
    fun actual2() {
        val list = FileOpener().getFile("/day12.txt")
        println(pipeChecker.calc2(list))
    }
}