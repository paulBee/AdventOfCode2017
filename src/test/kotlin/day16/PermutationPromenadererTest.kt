package day16

import duplicate
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test
import utils.FileOpener


class PermutationPromenadererTest {
    @Test
    fun actual1() {
        val instructions = FileOpener().getFile("/day16.txt")[0].split(",")
        println(PermutationPromenaderer.doTheThing(instructions, "abcdefghijklmnop".chunked(1).toMutableList()))

    }

    @Test
    fun actual2() {
        val instructions = FileOpener().getFile("/day16.txt")[0].split(",")
        println(PermutationPromenaderer.howManyMonkeysOnHowManyTypeWriters(instructions))

    }

    @Test
    fun test1() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("s1")
        assertThat(result).isEqualTo(listOf("e","a","b","c","d"))
    }

    @Test
    fun test2() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("s3")
        assertThat(result).isEqualTo(listOf("c","d","e","a","b"))
    }

    @Test
    fun test3() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("s5")
        assertThat(result).isEqualTo(listOf("a","b","c","d","e"))
    }

    @Test
    fun test4() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("x3/4")
        assertThat(result).isEqualTo(listOf("a","b","c","e","d"))
    }

    @Test
    fun test5() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("x1/4")
        assertThat(result).isEqualTo(listOf("a","e","c","d","b"))
    }

    @Test
    fun test6() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("x0/3")
        assertThat(result).isEqualTo(listOf("d","b","c","a","e"))
    }

    @Test
    fun test7() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("pe/b")
        assertThat(result).isEqualTo(listOf("a","e","c","d","b"))
    }

    @Test
    fun test8() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("pa/b")
        assertThat(result).isEqualTo(listOf("b","a","c","d","e"))
    }

    @Test
    fun test9() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val result = testProgs.instruct("pb/b")
        assertThat(result).isEqualTo(listOf("a","b","c","d","e"))
    }

    @Test
    fun test10() {
        val testProgs = "abcde".chunked(1).toMutableList()
        val testInstr = listOf("s1","x3/4","pe/b")
        val result = PermutationPromenaderer.doTheThing(testInstr, testProgs)
        assertThat(result).isEqualTo("baedc")
    }

}