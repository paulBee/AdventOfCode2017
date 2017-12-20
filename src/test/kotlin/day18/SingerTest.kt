package day18

import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test
import splitNewline
import utils.FileOpener


class SingerTest {
    @Test
    fun test1() {
        val instructions = """set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2""".splitNewline()

        val duet = SimpleSinger()
       assertThat(duet.singMeASong(instructions)).isEqualTo(4)
    }

    @Test
    fun actual1() {
        val instructions = FileOpener().getFile("/day18.txt")

        val duet = SimpleSinger()
        println(duet.singMeASong(instructions))
    }

    @Test
    fun actual2() {
        val instructions = FileOpener().getFile("/day18.txt")
        println(runBlocking {
            singMeADuet(instructions)
        })

    }
}