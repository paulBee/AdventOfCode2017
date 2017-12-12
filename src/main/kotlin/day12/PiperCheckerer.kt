package day12

class PiperCheckerer {

    fun calc(input: List<String>): Int {
        val processedInput = processInput(input)

        val nodes = processedInput.map { Node(it.name) }

        val node0 = nodes.first { it.name == "0" }

        populateLinks(nodes, node0, processedInput)

        return nodes.filter { it.seenInGraph }.size
    }

    fun calc2(input: List<String>): Int {
        val processedInput = processInput(input)
        val nodes = processedInput.map { Node(it.name) }

        var groupsFound = 0
        while (nodes.any { !it.seenInGraph }) {
            groupsFound++
            val firstOfNewGroup = nodes.first { !it.seenInGraph }
            populateLinks(nodes, firstOfNewGroup, processedInput)
        }

        return groupsFound
    }

    private fun populateLinks(nodes: List<Node>, nodeToPopulate: Node, parsedInput: List<Parsed>) {
        val linksToNames = parsedInput.first { it.name == nodeToPopulate.name }.linksToNames
        nodeToPopulate.seenInGraph = true
        nodeToPopulate.linksTo.addAll(nodes.filter { linksToNames.contains(it.name)}.onEach {
            if(!it.seenInGraph) populateLinks(nodes, it, parsedInput)
        })

    }

    private fun processInput(unprocessedInput: List<String>): List<Parsed> =
            unprocessedInput.map { regex.find(it)!! }.map {
                val(name, links) = it.destructured
                Parsed(name, links.split(Regex(",\\s*")))
            }


    //  b <-> a,b, c
    private val regex = Regex("""^(\w+) <-> (.*)""")

    private data class Parsed(val name: String, val linksToNames: List<String>)
    private data class Node(val name: String, val linksTo: MutableList<Node> = emptyList<Node>().toMutableList(), var seenInGraph: Boolean = false)
}