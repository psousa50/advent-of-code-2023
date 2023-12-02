typealias GameSets = List<Day02.CubeCountMap>

class Day02 : AdventOfCode {
    override val day = 2

    override fun partOne(input: SolutionInput): SolutionResult {
        val games = parse(input)
        val maxAvailableCubes = CubeCountMap.of(
            RED to 12,
            GREEN to 13,
            BLUE to 14,
        )
        val possibleGames = games.filter { it possibleWith maxAvailableCubes }
        return possibleGames.sumOf { it.id.toInt() }.asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        val games = parse(input)
        return games.sumOf { it.sets.minimumAvailableCubes().multiplyAll() }.asSolution()
    }

    private fun parse(input: SolutionInput) =
        input.lines.map { it.toGame() }


    private fun String.toGame(): Game {
        val (gameId, sets) = split(":")
        val (_, id) = gameId.split(" ")
        return Game(id, sets.split(";").map { it.toCubeCountMap() })
    }

    private fun String.toCubeCountMap() =
        CubeCountMap.of(split(",").associate { it.toCubeExtraction() })


    private fun String.toCubeExtraction(): Pair<Cube, Int> {
        val (count, cube) = trim().split(" ")
        return Cube.valueOf(cube.uppercase()) to count.toInt()
    }

    private infix fun Game.possibleWith(maxAvailableCubes: CubeCountMap): Boolean {
        val availableCubes = this.sets.minimumAvailableCubes()
        return availableCubes[RED] <= maxAvailableCubes[RED] &&
            availableCubes[GREEN] <= maxAvailableCubes[GREEN] &&
            availableCubes[BLUE] <= maxAvailableCubes[BLUE]
    }

    private fun GameSets.minimumAvailableCubes() =
        CubeCountMap.of(enumValues<Cube>().associateWith { cube -> this.maxOf { it[cube] } })


    private fun CubeCountMap.multiplyAll() = values.reduce { product, c -> product * c }

    data class Game(
        val id: String,
        val sets: GameSets
    )

    class CubeCountMap : HashMap<Cube, Int>() {
        override fun get(key: Cube) = super.get(key) ?: 0

        companion object {
            fun of(countMap: Map<Cube, Int>) = CubeCountMap().also { it.putAll(countMap) }
            fun of(vararg countMap: Pair<Cube, Int>) = CubeCountMap().also { it.putAll(countMap) }
        }
    }

    enum class Cube {
        RED,
        BLUE,
        GREEN,
    }

    companion object {
        val RED = Cube.RED
        val BLUE = Cube.BLUE
        val GREEN = Cube.GREEN
    }
}
