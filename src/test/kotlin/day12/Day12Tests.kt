package day12

import org.junit.Test
import org.assertj.core.api.KotlinAssertions.assertThat
import splitNewline
import utils.FileOpener

class Day12Tests {

    @Test
    fun test1() {
        val graphFactory = GraphFactory("""0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5""".splitNewline())
        val graph = graphFactory.getGraphWithNode("0")
        assertThat(graph.getNodes().size).isEqualTo(6)
    }

    @Test
    fun test2() {
        val graphFactory = GraphFactory("""0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5""".splitNewline())
        assertThat(graphFactory.getGraphs().size).isEqualTo(2)
    }

    @Test
    fun actual1() {
        val graphFactory = GraphFactory(FileOpener().getFile("/day12.txt"))
        val graph = graphFactory.getGraphWithNode("0")
        println(graph.getNodes().size)
    }

    @Test
    fun actual2() {
        val graphFactory = GraphFactory(FileOpener().getFile("/day12.txt"))
        println(graphFactory.getGraphs().size)
    }
}