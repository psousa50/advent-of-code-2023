class Day01 : AdventOfCode {
    override val day = 1

    override fun partOne(input: SolutionInput): SolutionResult {
        return input.lines.sumOf { it.numberWithFirstAndLastOccurrenceOf(digitNames) }.asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        return input.lines.sumOf { it.numberWithFirstAndLastOccurrenceOf(digitNames + digitFullNames) }.asSolution()
    }

    private fun String.numberWithFirstAndLastOccurrenceOf(names: Map<String, String>): Int {
        val indicesForFirstOccurrence = names.keys.map { names[it] to this.indexOf(it) }.filter { it.second >= 0 }
        val firstOccurrenceAsDigit = indicesForFirstOccurrence.minBy { it.second }.first ?: ""

        val indicesForLastOccurrence = names.keys.map { names[it] to this.lastIndexOf(it) }.filter { it.second >= 0 }
        val lastOccurrenceAsDigit = indicesForLastOccurrence.maxBy { it.second }.first ?: ""

        return "$firstOccurrenceAsDigit$lastOccurrenceAsDigit".toInt()
    }

    companion object {
        private val digitNames = (0..9).map { it.toString() }.associateWith { it }

        private val digitFullNames = mapOf(
            "zero" to "0",
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )
    }
}
