package day7
import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat
import splitNewline
import java.nio.file.Files
import java.nio.file.Paths

class day7Tests {
    val treeWalker = TreeWalker()
    @Test
    fun test1() {
        val result = treeWalker.calc1("""
pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)

        """.trimIndent().splitNewline())

        assertThat(result).isEqualTo("tknk")
    }

    @Test
    fun actual1() {
        val inputs = Files.readAllLines(Paths.get(javaClass.getResource("/day7.txt").toURI()))
        println(treeWalker.calc1(inputs))
    }

    @Test
    fun actual2() {
        val inputs = Files.readAllLines(Paths.get(javaClass.getResource("/day7.txt").toURI()))
        println(treeWalker.calc2(inputs))
    }
}