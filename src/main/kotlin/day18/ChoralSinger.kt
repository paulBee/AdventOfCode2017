package day18

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeout
import splitWhitespace

class ChoralSinger(private val sender: Channel<Long>,
                   private val receiver: Channel<Long>,
                   private val progId: Long,
                   private val waitTimeout: Long = 1000L) {

    private val registers = mutableMapOf("p" to progId).withDefault { 0 }
    private var currentInstruction = 0
    var sendCount = 0

    private fun registerOrValue(unParsed: String) : Long {
        return try {
            java.lang.Long.parseLong(unParsed)
        } catch (e: NumberFormatException) {
            registers.getValue(unParsed)
        }
    }

    private suspend fun executeInstruction(instruction: String) {
        val bits = instruction.splitWhitespace()
        val instructionName = bits[0]

        when(instructionName) {
            "snd" -> {
                sendCount += 1
                sender.send(registerOrValue(bits[1]))
            }
            "set" -> registers[bits[1]] = registerOrValue(bits[2])
            "add" -> registers[bits[1]] = registerOrValue(bits[1]) + registerOrValue(bits[2])
            "mul" -> registers[bits[1]] = registerOrValue(bits[1]) * registerOrValue(bits[2])
            "mod" -> registers[bits[1]] = registerOrValue(bits[1]) % registerOrValue(bits[2])
            "rcv" -> withTimeout(waitTimeout){
                registers[bits[1]] = receiver.receive()
            }
            "jgz" -> if (registerOrValue(bits[1]) > 0L) {
                currentInstruction += (registerOrValue(bits[2]).toInt() - 1)
            }
            else -> throw IllegalArgumentException("No such instruction $instructionName")
        }
    }

    suspend fun andAOneAndATwo(instructions: List<String>) {
        do {
            instructions.getOrNull(currentInstruction)?.let {
                println(it + "  on singer $progId")
                executeInstruction(it)
                currentInstruction += 1
            }
        } while (currentInstruction in 0 until instructions.size)
    }
}

suspend fun singMeADuet(instructions: List<String>, bufferSize: Int = 100): Int = runBlocking {
    val noughtToOne = Channel<Long>(bufferSize)
    val oneToNought = Channel<Long>(bufferSize)

    val singer0 = ChoralSinger(noughtToOne, oneToNought, 0)
    val singer1 = ChoralSinger(oneToNought, noughtToOne, 1)

    listOf(singer0, singer1)
            .map { async { it.andAOneAndATwo(instructions)} }
            .map { it.join() }

    singer1.sendCount
}