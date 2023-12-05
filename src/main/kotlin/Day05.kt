import Parsers.split

class Day05 : AdventOfCode {
    override val day = 5

    override fun partOne(input: SolutionInput): SolutionResult {
        val parts = input.lines.split { it.isBlank() }
        val seedRanges = parts.first().first().toSeeds().map { Range(it, it) }
        val categoryTransformations = parts.drop(1).map { it.toCategoryTransformation() }

        return transformSeeds(seedRanges, categoryTransformations).minOf { it.start }.asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        val parts = input.lines.split { it.isBlank() }
        val seedRanges = parts.first().first().toSeedRanges()
        val categoryTransformations = parts.drop(1).map { it.toCategoryTransformation() }

        return transformSeeds(seedRanges, categoryTransformations).minOf { it.start }.asSolution()
    }

    private fun transformSeeds(seedRanges: List<Range>, categoryTransformations: List<CategoryTransformation>) =
        seedRanges.map { seedRange ->
            categoryTransformations.fold(listOf(seedRange)) { transformedSeeds, categoryMap ->
                categoryMap.transform(transformedSeeds)
            }
        }.flatten()

    private fun String.toSeeds(): List<Long> {
        val (_, seeds) = split(":")
        return seeds.trim().split(" ").map { it.toLong() }
    }

    private fun String.toSeedRanges(): List<Range> {
        val (_, seeds) = split(":")
        return seeds.trim().split(" ").chunked(2).map {
            Range(it[0].toLong(), it[0].toLong() + it[1].toLong() - 1)
        }
    }

    private fun List<String>.toCategoryTransformation(): CategoryTransformation {
        val (name, _) = first().split(" ")
        return CategoryTransformation(name, drop(1).map { it.toCategoryMap() })
    }

    private fun String.toCategoryMap(): CategoryMap {
        val (targetStart, sourceStart, length) = split(" ").map { it.toLong() }
        val sourceRange = Range(sourceStart, sourceStart + length - 1)
        val targetRange = Range(targetStart, targetStart + length - 1)
        return CategoryMap(sourceRange, targetRange)
    }

    data class CategoryTransformation(
        val name: String,
        val maps: List<CategoryMap>,
    ) {
        fun transform(ranges: List<Range>) =
            ranges.map { range ->
                maps.filter { range.intersects(it.sourceRange) }
                    .map {
                        it.sourceRange.intersect(range) + (it.targetRange.start - it.sourceRange.start)
                    }.ifEmpty { listOf(range) }
            }.flatten()
    }

    data class CategoryMap(
        val sourceRange: Range,
        val targetRange: Range,
    )

    data class Range(
        val start: Long,
        val end: Long,
    ) {
        fun intersects(other: Range) = start <= other.end && end > other.start
        fun intersect(other: Range) = Range(maxOf(start, other.start), minOf(end, other.end))
        operator fun plus(d: Long) = Range(start + d, end + d)
    }

}
