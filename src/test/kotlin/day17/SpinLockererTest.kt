package day17

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test
import valueAfterValue


class SpinLockererTest {

    @Test
    fun test1() {
        val circularBuffer = SpinLockerer.buildCircularBuffer(3)
        val answer = circularBuffer.valueAfterValue(2017)
        assertThat(answer).isEqualTo(638)
    }

    @Test
    fun actual1() {
        val circularBuffer = SpinLockerer.buildCircularBuffer(370)
        val answer = circularBuffer.valueAfterValue(2017)
       println(answer)
    }

    @Test
    fun actual2() {
        println(SpinLockerer.stepsThatWillInsertAfter0(370, 50_000_001))

    }
}