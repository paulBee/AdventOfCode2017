package day6

import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat
import splitWhitespace

class Day6Tests {
    val spreader = ArraySpreader()

    @Test
    fun test1() {
        assertThat(spreader.stepsUntilRepeat(listOf(0,2,7,0))).isEqualTo(5)
    }

    @Test
    fun actual1() {
        println(spreader.stepsUntilRepeat2("""4	10	4	1	8	4	9	14	5	1	14	15	0	15	3	5""".splitWhitespace().map { it.toInt() }))
    }
}