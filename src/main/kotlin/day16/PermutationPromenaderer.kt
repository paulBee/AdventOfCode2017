package day16

import duplicate

object PermutationPromenaderer {

    fun doTheThing(instructions: List<String>, programs: Programs): String {
        return instructions.fold(programs, {acc, ins -> acc.instruct(ins)}).reduce{a,v -> a + v}
    }

    fun howManyMonkeysOnHowManyTypeWriters(instructions: List<String>): String {
        println(instructions.size)
        var IStillHaventFoundWhatImLookingFor = true
        var programs = "abcdefghijklmnop".chunked(1).toMutableList()
        var loopNumber = 0
        while(IStillHaventFoundWhatImLookingFor) {
            loopNumber++
            instructions.forEach { programs = programs.instruct(it) }
            if (programs == "abcdefghijklmnop".chunked(1)) {
                println("Now I've found what Im looking for!! loopNum: $loopNumber")
                IStillHaventFoundWhatImLookingFor = false
            }
        }
        val loopsLeft = 1_000_000_000 % loopNumber
        val instructionsToGo = duplicate(instructions, loopsLeft)

        return doTheThing(instructionsToGo, programs)
    }
}

typealias Programs = MutableList<String>

fun Programs.instruct(instruction: String): Programs {
    val (type, input) = Regex("([sxp])(.*)").find(instruction)!!.destructured
    return when (type.toLowerCase()) {
        ("s") -> this.spin(input.toInt())
        ("x") -> {
            val (index1, index2) = input.split("/").map { it.toInt() }
            this.exchange(index1, index2)
        }
        ("p") -> {
            val (prog1, prog2) = input.split("/")
            this.partner(prog1, prog2)
        }
        else -> throw IllegalStateException("This is not the program you are looking for: $type")
    }
}

fun Programs.partner(prog1: String, prog2:String): Programs {
    val index1 = this.indexOf(prog1)
    val index2 = this.indexOf(prog2)
    this[index1] = prog2
    this[index2] = prog1
    return this
}

fun Programs.exchange(index1: Int, index2: Int): Programs {
    val newValue2 = this[index1]
    val newValue1 = this[index2]
    this[index1] = newValue1
    this[index2] = newValue2
    return this
}

private fun Programs.spin(splitIndex: Int): Programs =
        (this.takeLast(splitIndex) + this.dropLast(splitIndex)).toMutableList()


