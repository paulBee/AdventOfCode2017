package day9

import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat
import utils.FileOpener


class Day9Test {
    val fileOpener = FileOpener()
    val groupCounter = GroupStreams()

    @Test
    fun test1() {
        val input = "{}".toCharArray().toList()
        val result = groupCounter.calc1(input)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun test2() {
        val input = "{{{}}}".toCharArray().toList()
        val result = groupCounter.calc1(input)
        assertThat(result).isEqualTo(6)
    }

    @Test
    fun test3() {
        val input = "{{},{}}".toCharArray().toList()
        val result = groupCounter.calc1(input)
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun test4() {
        val input = "{{{},{},{{}}}}".toCharArray().toList()
        val result = groupCounter.calc1(input)
        assertThat(result).isEqualTo(16)
    }

    @Test
    fun test5() {
        val input = "{<a>,<a>,<a>,<a>}".toCharArray().toList()
        val result = groupCounter.calc1(input)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun test6() {
        val input = "{{<ab>},{<ab>},{<ab>},{<ab>}}".toCharArray().toList()
        val result = groupCounter.calc1(input)
        assertThat(result).isEqualTo(9)
    }

    @Test
    fun test7() {
        val input = "{{<!!>},{<!!>},{<!!>},{<!!>}}".toCharArray().toList()
        val result = groupCounter.calc1(input)
        assertThat(result).isEqualTo(9)
    }

    @Test
    fun test8() {
        val input = "{{<a!>},{<a!>},{<a!>},{<ab>}}".toCharArray().toList()
        val result = groupCounter.calc1(input)
        assertThat(result).isEqualTo(3)
    }

    @Test
    fun actual1() {
        val input = fileOpener.getFile("/day9.txt")[0].toCharArray().toList()
        println(groupCounter.calc1(input))
    }

    @Test
    fun actual2() {
        val input = fileOpener.getFile("/day9.txt")[0].toCharArray().toList()
        groupCounter.cleanRubbish(input)
        println(groupCounter.rubbishCount)
    }

    @Test
    fun test11() {
        groupCounter.rubbishCount = 0
        val input = "{}".toCharArray().toList()
        val result = groupCounter.cleanRubbish(input)
        assertThat(groupCounter.rubbishCount).isEqualTo(0)
    }

    @Test
    fun test12() {
        groupCounter.rubbishCount = 0
        val input = "<random characters>".toCharArray().toList()
        val result = groupCounter.cleanRubbish(input)
        assertThat(groupCounter.rubbishCount).isEqualTo(17)
    }

    @Test
    fun test13() {
        groupCounter.rubbishCount = 0
        val input = "<<<<>".toCharArray().toList()
        val result = groupCounter.cleanRubbish(input)
        assertThat(groupCounter.rubbishCount).isEqualTo(3)
    }

    @Test
    fun test14() {
        groupCounter.rubbishCount = 0
        val input = "{{{},{},{{}}}}".toCharArray().toList()
        val result = groupCounter.cleanRubbish(input)
        assertThat(groupCounter.rubbishCount).isEqualTo(16)
    }

    @Test
    fun test15() {
        groupCounter.rubbishCount = 0
        val input = "{<a>,<a>,<a>,<a>}".toCharArray().toList()
        val result = groupCounter.cleanRubbish(input)
        assertThat(groupCounter.rubbishCount).isEqualTo(1)
    }

    @Test
    fun test16() {
        groupCounter.rubbishCount = 0
        val input = "{{<ab>},{<ab>},{<ab>},{<ab>}}".toCharArray().toList()
        val result = groupCounter.cleanRubbish(input)
        assertThat(groupCounter.rubbishCount).isEqualTo(9)
    }

    @Test
    fun test17() {
        groupCounter.rubbishCount = 0
        val input = "{{<!!>},{<!!>},{<!!>},{<!!>}}".toCharArray().toList()
        val result = groupCounter.cleanRubbish(input)
        assertThat(groupCounter.rubbishCount).isEqualTo(9)
    }

    @Test
    fun test18() {
        groupCounter.rubbishCount = 0
        val input = "{{<a!>},{<a!>},{<a!>},{<ab>}}".toCharArray().toList()
        val result = groupCounter.cleanRubbish(input)
        assertThat(groupCounter.rubbishCount).isEqualTo(3)
    }
}