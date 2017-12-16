package day15


import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test

class GeneratorBattleTest {

    @Test
    fun test1() {
        val result = GeneratorBattle.letBattleCommence(65, 8921)
        assertThat(result).isEqualTo(588)
    }

    @Test
    fun actual1() {
        val result = GeneratorBattle.letBattleCommence(591, 393)
        println(result)
    }

    @Test
    fun test2() {
        val result = GeneratorBattle.letBattleCommence2(65, 8921)
        assertThat(result).isEqualTo(309)
    }

    @Test
    fun actual2() {
        val result = GeneratorBattle.letBattleCommence2(591, 393)
        println(result)
    }

}