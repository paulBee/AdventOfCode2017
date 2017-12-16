package day14


import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test

class DiskFragmentererTest{
    @Test
    fun test1() {
        val result = DiskFragmenterer.countUsedData("flqrgnkx")

        assertThat(result).isEqualTo(8108)
    }

    @Test
    fun test2() {
        val result = DiskFragmenterer.countRegions("flqrgnkx")

        assertThat(result).isEqualTo(1242)
    }

    @Test
    fun actual() {
        println(DiskFragmenterer.countUsedData("ugkiagan"))
    }

    @Test
    fun actual2() {
        println(DiskFragmenterer.countRegions("ugkiagan", true))
    }
}