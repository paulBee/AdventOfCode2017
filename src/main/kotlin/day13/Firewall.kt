package day13

class Firewall {

    fun part1(input: List<String>, delay: Int = 0): Int = input
            .map { regex.find(it)!!.destructured }
            .map{ Pair(it.component1().toInt(), it.component2().toInt())}
            .fold(0, {acc, (index, range) ->
                   when {
                       (index + delay) % ((range-1)*2) == 0 -> acc + (range * index)
                       else -> acc
                   }

                })




    private val regex = Regex("""(\d+): (\d+)""")
}