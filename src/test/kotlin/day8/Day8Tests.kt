package day8

import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat
import splitNewline
import java.nio.file.Files
import java.nio.file.Paths

class Day8Tests {
    val instructions = Instructions()

    @Test
    fun test1() {
        val result = instructions.calc("""b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10""".splitNewline())

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun actual1() {
        val testInstr = Files.readAllLines(Paths.get(javaClass.getResource("/day8.txt").toURI()))
        println(instructions.calc2(testInstr))
    }
}