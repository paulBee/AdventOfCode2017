package day3

class ManhattenGrid () {

    private val grid = HashMap<Pair<Int, Int>, Int>()
    private var directionOfNextElement: Direction
    private var currentElement: Pair<Int, Int>
    init{
        currentElement = Pair(0,0)
        grid.put(currentElement, 1)
        directionOfNextElement = Direction.RIGHT
    }

    fun next() : Int {
        currentElement = coordsInDirection(currentElement, directionOfNextElement)
        val nextValue = currentElement.calculateValue()
        grid.put(currentElement, nextValue)
        directionOfNextElement = nextDirection()
        return nextValue
    }

    private fun nextDirection(): Direction =
        when(directionOfNextElement) {
            Direction.UP -> if(isEmptyLocation(coordsInDirection(currentElement, Direction.LEFT))) Direction.LEFT else Direction.UP
            Direction.DOWN -> if(isEmptyLocation(coordsInDirection(currentElement, Direction.RIGHT))) Direction.RIGHT else Direction.DOWN
            Direction.RIGHT -> if(isEmptyLocation(coordsInDirection(currentElement, Direction.UP))) Direction.UP else Direction.RIGHT
            Direction.LEFT -> if(isEmptyLocation(coordsInDirection(currentElement, Direction.DOWN))) Direction.DOWN else Direction.LEFT
        }


    private fun isEmptyLocation(coords: Pair<Int, Int>): Boolean = grid.getOrElse(coords, {0}) == 0




    private fun Pair<Int, Int>.calculateValue(): Int =
        (-1..1).flatMap { x -> (-1..1).map { y -> Pair(this.first + x, this.second + y) } }
                .map{ grid.getOrElse(it) {0} }
                .sum()

}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

fun coordsInDirection(currentCoords: Pair<Int, Int>, direction: Direction): Pair<Int, Int> =
        when(direction) {
            Direction.UP -> Pair(currentCoords.first, currentCoords.second + 1)
            Direction.DOWN -> Pair(currentCoords.first, currentCoords.second - 1)
            Direction.RIGHT -> Pair(currentCoords.first + 1, currentCoords.second)
            Direction.LEFT -> Pair(currentCoords.first - 1, currentCoords.second)
        }

