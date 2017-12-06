package day5

class JumpInstructions {

    fun countSteps(jumpList: List<Int>): Int {
        val initialState  = State(0, jumpList.toMutableList())
        return processJumps(initialState, ::type1)

    }

    fun countSteps2(jumpList: List<Int>): Int {
        val initialState  = State(0, jumpList.toMutableList())
        return processJumps(initialState, ::type2)
    }

    private fun type1(before: Int): Int = before + 1

    private fun type2(before: Int): Int = when {
        before > 2 -> before - 1
        else -> before + 1
    }

    tailrec private fun processJumps(state: State, jumpChanger: (Int) -> Int): Int {
        val position = state.position

        state.jumps = state.jumps + 1
        state.position = position + state.jumpList[position]
        state.jumpList[position] = jumpChanger(state.jumpList[position])

        return when {
            endCondition(state) -> state.jumps
            else -> processJumps(state, jumpChanger)
        }
    }

    private fun endCondition(state: State): Boolean = when {
        state.position < 0 -> true
        state.position >= state.jumpList.size -> true
        else -> false
    }

    data class State(var position: Int, var jumpList: MutableList<Int>, var jumps: Int = 0)
}