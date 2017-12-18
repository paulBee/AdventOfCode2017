package day17

object SpinLockerer {

    fun buildCircularBuffer(stepLength: Int, numberOfSteps: Int = 2018) : List<Int> =

        (1..numberOfSteps -1)
                .fold(Pair(listOf(0),0), { (circularList, currPosition), stepNumber ->
                    val insertPosition = ((currPosition + stepLength) % circularList.size + 1)
                    val newList = circularList.take(insertPosition) + (stepNumber) + circularList.drop(insertPosition)
                    Pair(newList, insertPosition)
        }).first

    fun stepsThatWillInsertAfter0(stepLength: Int, numberOfSteps: Int = 2018): Int =
        generateSequence(Pair(0,0), {(currentPosition, stepNumber) ->
            val insertNumberAndCurrentListSize = stepNumber + 1
            val insertPosition = ((currentPosition + stepLength) % insertNumberAndCurrentListSize) + 1
            Pair(insertPosition, insertNumberAndCurrentListSize)
        }).take(numberOfSteps)
                .filter{ it.first == 1}
                .onEach { println(it) }

                .last().second



}