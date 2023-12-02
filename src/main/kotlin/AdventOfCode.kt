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
            // {{ NextDay }}
        )
    }
}

data class SolutionInput(
    val lines: List<String>
)

data class SolutionResult(val value: String) {
    val intValue get() = value.toInt()
    val longValue get() = value.toLong()
    override fun toString() = value
}

fun Int.bind() = SolutionResult(this.toString())
fun Long.bind() = SolutionResult(this.toString())
fun String.bind() = SolutionResult(this)
