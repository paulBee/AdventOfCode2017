package day13
import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat
import splitNewline
import utils.FileOpener

class Day13Tests {
    val firewall = Firewall()

    @Test
    fun test1() {
        val list = """
            |0: 3
            |1: 2
            |4: 4
            |6: 4
        """.trimMargin().splitNewline().filter { it.isNotEmpty() }
        val result = firewall.part1(list)
        assertThat(result).isEqualTo(24)
    }

    @Test
    fun test2() {
        val list = """
            |0: 3
            |1: 2
            |4: 4
            |6: 4
        """.trimMargin().splitNewline().filter { it.isNotEmpty() }
        val result = generateSequence(0) { it + 1 }.takeWhile { firewall.part1(list, it) != 0 }.last() + 1
        assertThat(result).isEqualTo(10)
    }

    @Test
    fun actual1() {
        val list = FileOpener().getFile("/day13.txt")
        println(firewall.part1(list))
    }

    @Test
    fun actual2() {
        val list = FileOpener().getFile("/day13.txt")
        val result = generateSequence(0) { it + 1 }.takeWhile { firewall.part1(list, it) != 0 }.last() + 1
        println(result)

    }
}