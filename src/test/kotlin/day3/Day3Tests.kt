package day3

import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat

class Day3Tests {

    val manhattanDist = ManhattanDistance()

    @Test
    fun test1() {
        assertThat(manhattanDist.calc(1)).isEqualTo(0)
    }

    @Test
    fun test2() {
        assertThat(manhattanDist.calc(2)).isEqualTo(1)
    }

    @Test
    fun test3() {
        assertThat(manhattanDist.calc(3)).isEqualTo(2)
    }

    @Test
    fun test4() {
        assertThat(manhattanDist.calc(4)).isEqualTo(1)
    }

    @Test
    fun test5() {
        assertThat(manhattanDist.calc(5)).isEqualTo(2)
    }

    @Test
    fun test6() {
        assertThat(manhattanDist.calc(6)).isEqualTo(1)
    }
    @Test
    fun test7() {
        assertThat(manhattanDist.calc(7)).isEqualTo(2)
    }

    @Test
    fun test8() {
        assertThat(manhattanDist.calc(8)).isEqualTo(1)
    }

    @Test
    fun test9() {
        assertThat(manhattanDist.calc(9)).isEqualTo(2)
    }

    @Test
    fun test10() {
        assertThat(manhattanDist.calc(10)).isEqualTo(3)
    }

    @Test
    fun test11() {
        assertThat(manhattanDist.calc(11)).isEqualTo(2)
    }

    @Test
    fun test12() {
        assertThat(manhattanDist.calc(12)).isEqualTo(3)
    }

    @Test
    fun test13() {
        assertThat(manhattanDist.calc(13)).isEqualTo(4)
    }

    @Test
    fun test14() {
        assertThat(manhattanDist.calc(14)).isEqualTo(3)
    }

    @Test
    fun test15() {
        assertThat(manhattanDist.calc(15)).isEqualTo(2)
    }

    @Test
    fun test16() {
        assertThat(manhattanDist.calc(16)).isEqualTo(3)
    }

    @Test
    fun test17() {
        assertThat(manhattanDist.calc(17)).isEqualTo(4)
    }

    @Test
    fun test18() {
        assertThat(manhattanDist.calc(18)).isEqualTo(3)
    }

    @Test
    fun test19() {
        assertThat(manhattanDist.calc(19)).isEqualTo(2)
    }

    @Test
    fun test20() {
        assertThat(manhattanDist.calc(20)).isEqualTo(3)
    }

    @Test
    fun test21() {
        assertThat(manhattanDist.calc(21)).isEqualTo(4)
    }

    @Test
    fun test22() {
        assertThat(manhattanDist.calc(22)).isEqualTo(3)
    }

    @Test
    fun test23() {
        assertThat(manhattanDist.calc(23)).isEqualTo(2)
    }

    @Test
    fun test24() {
        assertThat(manhattanDist.calc(24)).isEqualTo(3)
    }

    @Test
    fun test25() {
        assertThat(manhattanDist.calc(25)).isEqualTo(4)
    }

    @Test
    fun test26() {
        assertThat(manhattanDist.calc(26)).isEqualTo(5)
    }

    @Test
    fun test27() {
        assertThat(manhattanDist.calc(27)).isEqualTo(4)
    }

    @Test
    fun testActual1() {
        println(manhattanDist.calc(368078))
    }


    @Test
    fun foo() {
        println(manhattanDist.calc2(368078))
    }



}