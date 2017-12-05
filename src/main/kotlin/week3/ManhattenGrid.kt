package week3

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

    private fun nextDirection(): ManhattenGrid.Direction =
        when(directionOfNextElement) {
            Direction.UP -> if(isEmptyLocation(coordsInDirection(currentElement, Direction.LEFT))) Direction.LEFT else Direction.UP
            Direction.DOWN -> if(isEmptyLocation(coordsInDirection(currentElement, Direction.RIGHT))) Direction.RIGHT else Direction.DOWN
            Direction.RIGHT -> if(isEmptyLocation(coordsInDirection(currentElement, Direction.UP))) Direction.UP else Direction.RIGHT
            Direction.LEFT -> if(isEmptyLocation(coordsInDirection(currentElement, Direction.DOWN))) Direction.DOWN else Direction.LEFT
            else -> error("which way to the beach!")
        }


    private fun isEmptyLocation(coords: Pair<Int, Int>): Boolean = grid.getOrElse(coords, {0}) == 0


    private fun coordsInDirection(currentCoords: Pair<Int, Int>, direction: Direction): Pair<Int, Int> =
        when(direction) {
            Direction.UP -> Pair(currentCoords.first, currentCoords.second + 1)
            Direction.DOWN -> Pair(currentCoords.first, currentCoords.second - 1)
            Direction.RIGHT -> Pair(currentCoords.first + 1, currentCoords.second)
            Direction.LEFT -> Pair(currentCoords.first - 1, currentCoords.second)
            Direction.UPRIGHT -> Pair(currentCoords.first + 1, currentCoords.second + 1)
            Direction.UPLEFT -> Pair(currentCoords.first - 1, currentCoords.second + 1)
            Direction.DOWNRIGHT -> Pair(currentCoords.first + 1, currentCoords.second - 1)
            Direction.DOWNLEFT -> Pair(currentCoords.first - 1, currentCoords.second - 1)
        }


    enum class Direction {
        UP, DOWN, LEFT, RIGHT, UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT
    }

    private fun Pair<Int, Int>.calculateValue(): Int {
        return grid.getOrElse(coordsInDirection(this, Direction.UP), {0}) +
        grid.getOrElse(coordsInDirection(this, Direction.DOWN), {0}) +
        grid.getOrElse(coordsInDirection(this, Direction.LEFT), {0}) +
        grid.getOrElse(coordsInDirection(this, Direction.RIGHT), {0}) +
        grid.getOrElse(coordsInDirection(this, Direction.UPRIGHT), {0}) +
        grid.getOrElse(coordsInDirection(this, Direction.UPLEFT), {0}) +
        grid.getOrElse(coordsInDirection(this, Direction.DOWNRIGHT), {0}) +
        grid.getOrElse(coordsInDirection(this, Direction.DOWNLEFT), {0})
    }
}


