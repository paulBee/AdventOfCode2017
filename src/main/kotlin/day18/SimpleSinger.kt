package day18

import splitWhitespace
import java.lang.Long.parseLong

class SimpleSinger() {

    val registers = mutableMapOf<String, Long>().withDefault { 0 }
    var lastSound: Long? = null
    var recovered: Long? = null
    var currentInstruction = 0

    private fun registerOrValue(unParsed: String) : Long {
        try {
            return parseLong(unParsed)
        } catch (e: NumberFormatException) {
            return registers.getValue(unParsed)
        }

    }

    private fun executeInstruction(instruction: String) {
        val bits = instruction.splitWhitespace()
        val instructionName = bits[0]

        when(instructionName) {
            "snd" -> lastSound = registerOrValue(bits[1])
            "set" -> registers[bits[1]] = registerOrValue(bits[2])
            "add" -> registers[bits[1]] = registerOrValue(bits[1]) + registerOrValue(bits[2])
            "mul" -> registers[bits[1]] = registerOrValue(bits[1]) * registerOrValue(bits[2])
            "mod" -> registers[bits[1]] = registerOrValue(bits[1]) % registerOrValue(bits[2])
            "rcv" -> if (registerOrValue(bits[1]) != 0L) {
                recovered = lastSound
            }
            "jgz" -> if (registerOrValue(bits[1]) > 0L) {
                currentInstruction += (registerOrValue(bits[2]).toInt() - 1)
            }
            else -> throw IllegalArgumentException("No such instruction $instructionName")
        }
    }

    fun singMeASong(instructions: List<String>): Long {
        do {
            instructions.getOrNull(currentInstruction)?.let {
                println(it)
                executeInstruction(it)
                currentInstruction += 1
            }
        } while (currentInstruction in 0 until instructions.size && recovered == null)
        return recovered!!
    }
}