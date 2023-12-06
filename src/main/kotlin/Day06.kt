import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

class Day06 : AdventOfCode {
    override val day = 6

    override fun partOne(input: SolutionInput): SolutionResult {
        val times = input.lines[0].toNumbers()
        val winningDistances = input.lines[1].toNumbers()

        val winningRacesCount = times.mapIndexed { i, time -> countWinningRaces(time, winningDistances[i]) }

        return winningRacesCount.reduce { acc, i -> acc * i }.asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        val time = input.lines[0].toNumbersIgnoringSpaces()
        val winningDistance = input.lines[1].toNumbersIgnoringSpaces()

        val winningRacesCount = countWinningRaces(time, winningDistance)

        return winningRacesCount.asSolution()
    }

    private fun String.toNumbers() =
        Regex("\\b\\d+\\b")
            .findAll(this)
            .map { it.value.toLong() }
            .toList()

    private fun String.toNumbersIgnoringSpaces(): Long {
        val (_, numbers) = split(":")
        return numbers.replace(" ", "").toLong()
    }

    private fun countWinningRaces(time: Long, winningDistance: Long): Long {
        val sq = sqrt(time * time - 4 * winningDistance.toDouble())
        val x1 = (time + sq) / 2
        val x2 = (time - sq) / 2

        return if (x1 < x2)
            ceil(x2).toLong() - floor(x1).toLong() - 1 else
            ceil(x1).toLong() - floor(x2).toLong() - 1
    }
}
