package day12

class GraphFactory(input: List<String>) {
    private val parsedInput: List<Parsed>
    private val graphs: MutableList<Graph> = mutableListOf()
    private val regex = Regex("""^(\w+) <-> (.*)""")
    init {
        parsedInput = processInput(input)
        while (parsedInput.any { !it.placedInGraph }) {
            val entryNode = parsedInput.first { !it.placedInGraph }
            buildGraphFromNode(entryNode.name)
        }
    }

    fun getGraphWithNode(nodeName: String): Graph =
            graphs.first { it.getNodes().any { it.name == nodeName } }

    fun getGraphs(): List<Graph> = graphs.toList()


    private fun buildGraphFromNode(nodeName: String): Graph {
        val graph = Graph(parsedInput, nodeName)
        graphs.add(graph)
        return graph
    }


    data class Parsed(val name: String, val linksToNames: List<String>, var placedInGraph: Boolean = false)
    data class Node(val name: String, val links: MutableList<Node> = emptyList<Node>().toMutableList())

    private fun processInput(unprocessedInput: List<String>): List<Parsed> =
            unprocessedInput
                    .map { regex.find(it)!! }
                    .map {
                val(name, links) = it.destructured
                Parsed(name, links.split(Regex(",\\s*")))
            }

    class Graph (parsedInput: List<GraphFactory.Parsed>, nodeName: String) {
        private val nodes: MutableList<Node> = mutableListOf()
        private val parsedInput = parsedInput

        init {
            buildNode(nodeName)
        }

        fun getNodes(): List<Node> = nodes.toList()


        private fun getOrBuildNode(nodeName: String): Node {
            return nodes.firstOrNull { it.name == nodeName } ?: buildNode(nodeName)
        }

        private fun buildNode(nodeName: String): Node {
            val nodeInfo = parsedInput.first { it.name == nodeName }
            nodeInfo.placedInGraph = true
            val node = Node(nodeInfo.name)
            nodes.add(node)
            node.links.addAll(nodeInfo.linksToNames.map { getOrBuildNode(it) })
            return node
        }
    }
}


