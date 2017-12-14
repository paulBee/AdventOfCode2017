package day8

import toDigit

class Instructions {

    var currMax = 0

    fun calc(unprocessedInstructions: List<String>): Int {
        val instructionList = buildInstructionList(unprocessedInstructions)
        val registers = mutableMapOf<String, Int>().withDefault { 0 }
        instructionList.forEach { applyInstruction(it, registers) }

        return registers.values.max()!!

    }

    fun calc2(unprocessedInstructions: List<String>): Int {
        val instructionList = buildInstructionList(unprocessedInstructions)
        val registers = mutableMapOf<String, Int>().withDefault { 0 }
        instructionList.forEach { applyInstruction(it, registers) }

        return currMax

    }

    private fun applyInstruction(instruction: Instruction, registers: MutableMap<String, Int>) {
        if(instruction.predicate(registers.getValue(instruction.predicateAppliedTo))) {
            val newValue = registers.getValue(instruction.appliedTo) + instruction.incrementBy
            registers.put(instruction.appliedTo, newValue)
            currMax = maxOf(currMax, newValue)
        }
    }

    //  b inc 5 if a > 1
    private val regex = Regex("""^(\w+) (inc|dec) (-?\d+) if (\w+) (>|>=|<|<=|==|!=) (-?\d+)""")

    private fun buildInstructionList(unprocessedInstructions: List<String>): List<Instruction> = unprocessedInstructions
            .onEach { println(it) }
                .map{ regex.find(it)!! }
                .map{
                    val(appliedTo, incOrDec, incOrDecMagnitude, predicateAppliedTo, predicateOperator, predicateValue)= it.destructured

                    Instruction(appliedTo,
                            buildIncrementBy(incOrDec, incOrDecMagnitude.toDigit()),
                            predicateAppliedTo,
                            buildPredicate(predicateOperator, predicateValue.toDigit())
                    )
                }

    private fun buildPredicate(predicateOperator: String, predicateValue: Int): (Int) -> Boolean =
            when(predicateOperator) {
                ">" -> {toCompare: Int -> toCompare > predicateValue}
                ">=" -> {toCompare: Int -> toCompare >= predicateValue}
                "<" -> {toCompare: Int -> toCompare < predicateValue}
                "<=" -> {toCompare: Int -> toCompare <= predicateValue}
                "==" -> {toCompare: Int -> toCompare == predicateValue}
                "!=" -> {toCompare: Int -> toCompare != predicateValue}
                else -> throw IllegalArgumentException("Unrecognised predicate operator found $predicateOperator")
            }

    private fun buildIncrementBy(incOrDec: String, magnidute: Int): Int =
            when(incOrDec){
                "inc" -> magnidute
                "dec" -> - magnidute
                else -> throw IllegalArgumentException("Only expecting inc or dec but received $incOrDec")
            }

    private fun possibleNegate(negative: String, magnidute: Int): Int =
            when(negative) {
                "" -> magnidute
                "-" -> - magnidute
                else -> throw IllegalArgumentException("odd negate string found $negative")
            }


    data class Instruction(val appliedTo: String, val incrementBy: Int, val predicateAppliedTo: String, val predicate: (Int) -> Boolean)
}

