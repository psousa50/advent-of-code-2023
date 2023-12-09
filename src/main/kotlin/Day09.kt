class Day09 : AdventOfCode {
    override val day = 9

    override fun partOne(input: SolutionInput): SolutionResult {
        val sequences = input.lines.map { it.split(" ").map { n -> n.toLong() } }
        return sequences.sumOf { it.next() }.asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        val sequences = input.lines.map { it.split(" ").map { n -> n.toLong() } }
        return sequences.map { it.reversed()}.sumOf { it.next() }.asSolution()
    }

    private fun List<Long>.next(): Long {
        val differences = this.drop(1).mapIndexed() { index, value -> value - this[index] }
        return this.last() + if (differences.toSet().count() == 1) {
            differences.first()
        } else {
            differences.next()
        }
    }
}
