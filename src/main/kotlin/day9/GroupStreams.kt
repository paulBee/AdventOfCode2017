package day9

class GroupStreams {

    var rubbishCount = 0

    fun calc1(stream: List<Char>): Int {

        val cleanStream = cleanRubbish(stream)

        return countGroups(cleanStream)
    }

    private fun countGroups(cleanStream: List<Char>): Int {
        var currentDepth = 0

        fun convertToGroupNumber(it: Char): Int =
            when(it) {
                '{' -> {
                    currentDepth++
                    0
                }
                '}' -> {
                    currentDepth--
                    currentDepth + 1
                }
                else -> 0
            }


        return cleanStream.map{ convertToGroupNumber(it)}.sum()
    }


    fun calc2(stream: List<Char>): Int {

        return 0
    }


    fun cleanRubbish(stream: List<Char>): List<Char> {
        var inRubbish = false
        var ignoreNext = false

        fun checkForRubbish(it: Char): Boolean {
            if(ignoreNext) {
                ignoreNext = false
                return false
            }
            when(it) {
                '<' -> {
                    if(inRubbish) {
                        rubbishCount++
                    }
                    inRubbish = true
                    return false
                }
                '!' -> {
                    ignoreNext = true
                    return false
                }
                '>' -> {
                    inRubbish = false
                    return false
                }
                else -> {
                    if(inRubbish) {
                        rubbishCount++
                    }
                    return !inRubbish
                }
            }
        }

        return stream.filter { checkForRubbish(it) }
    }
}