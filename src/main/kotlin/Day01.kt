class Day01: AdventOfCode {
    override val day = 1

    override fun partOne(input: SolutionInput): SolutionResult {
        return input.lines.sumOf { it.firstAndLastOccurrenceOf(digitNames) }.bind()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        return input.lines.sumOf { it.firstAndLastOccurrenceOf(digitNames + digitFullNames) }.bind()
    }

    private fun String.firstAndLastOccurrenceOf(names: Map<String, String>): Int {
        val firstIndices = names.keys.map { names[it] to this.indexOf(it) }.filter { it.second >= 0 }
        val first = firstIndices.minBy { it.second }.first ?: ""
        val lastIndices = names.keys.map { names[it] to this.lastIndexOf(it) }.filter { it.second >= 0 }
        val last = lastIndices.maxBy { it.second }.first ?: ""
        return "$first$last".toInt()
    }

    companion object {
        private val digitNames = (0..9).associate { it.toString() to it.toString() }

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
