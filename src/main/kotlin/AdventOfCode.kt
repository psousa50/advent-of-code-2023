interface AdventOfCode {
    val day: Int

    fun partOne(input: SolutionInput): SolutionResult {
        TODO("Not yet implemented")
    }

    fun partTwo(input: SolutionInput): SolutionResult {
        TODO("Not yet implemented")
    }

    companion object {
        val days = mapOf(
            1 to Day01(),
            2 to Day02(),
            3 to Day03(),
            4 to Day04(),
            5 to Day05(),
            6 to Day06(),
            7 to Day07(),
            8 to Day08(),
            9 to Day09(),
            // {{ NextDay }}
        )
    }
}

data class SolutionInput(
    val lines: List<String>
)

data class SolutionResult(val value: String) {
    val asInt get() = value.toInt()
    val asLong get() = value.toLong()
    override fun toString() = value

}

fun Int.asSolution() = SolutionResult(this.toString())
fun Long.asSolution() = SolutionResult(this.toString())
