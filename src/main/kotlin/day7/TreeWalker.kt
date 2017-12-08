package day7

class TreeWalker {

    //fwft (72) -> ktlj, cntj, xhth

    private val regex = Regex("""(\w+) \((\d+)\)(?: -> ([\w, ]+))?""")

    fun calc1(inputs: List<String>): String {

        val leaves = processInput(inputs)

        return findRoot(leaves)
    }

    fun calc2(inputs: List<String>): Pair<Int, Int> {

        val leaves = processInput(inputs)
        val root = findRoot(leaves)
        val rootNode = buildNode(leaves, root)


        return iterate(rootNode, 0)
    }

    private fun buildNode(leaves: List<Leaf>, name: String): Node = leaves
            .findByName(name)
            .let { Node(it.name, it.weight, it.children.map { buildNode(leaves, it) })}


    private fun iterate(node: Node, incorrectBy: Int): Pair<Int, Int> =
        when {
            node.childrenBalanced() -> Pair(node.weight, incorrectBy)
            else -> iterate(node.unBalancedChild(), node.childrenWrongBy())
        }


    private data class Node(val name: String, val weight: Int, val children: List<Node>) {

        fun unBalancedChild(): Node = this.children.groupBy { it.totalWeight() }.minBy { it.value.size }?.value?.first()!!

        fun totalWeight(): Int = this.weight + this.children.sumBy { it.totalWeight() }

        fun childrenBalanced(): Boolean = this.children.map { it.totalWeight() }.distinct().size == 1

        fun childrenWrongBy(): Int = this.children.map { it.totalWeight() }.distinct().reduce { acc, weight -> acc - weight }
    }


    private fun findRoot(leaves: List<Leaf>): String {
        val names = leaves.map { it.name }
        val references = leaves.flatMap { it.children }


        return names.first { !references.contains(it) }
    }

    private fun processInput(inputs: List<String>) = inputs.map { it.toLeaf() }


    fun String.toLeaf(): Leaf = regex.find(this)!!
            .groupValues
            .let {
                Leaf(it[1], it[2].toInt(), extractChildren(it))
            }

    private fun extractChildren(it: List<String>) = when {
        it.size < 4 -> emptyList()
        it[3].isBlank() -> emptyList()
        else -> it[3].split(Regex(", "))
    }

    private fun List<Leaf>.findByName(name: String) = this.first { it.name == name }
    data class Leaf(val name: String, val weight: Int, val children: List<String>)

}