import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::AdventOfCodeArgs).run {
        val solution = AdventOfCode.days[day] ?: throw IllegalArgumentException("Day: $day not implemented")
        if (part == 0 || part == 1) {
            solution.showResult(1, sample)
        }
        if (part == 0 || part == 2) {
            solution.showResult(2, sample)
        }
    }
}

fun AdventOfCode.runPart(part: Int, sample: Boolean = false): SolutionResult {
    val file = if (sample) "sample$part" else "input"
    val inputFilename = "Day${"%02d".format(day)}/$file.txt"
    val input = SolutionInput(Parsers.readLinesFromResource(inputFilename))
    if (input.lines.isEmpty()) {
        throw IllegalArgumentException("No input found for $inputFilename")
    }
    return when (part) {
        1 -> partOne(input)
        2 -> partTwo(input)
        else -> throw IllegalArgumentException("Invalid part $part")
    }
}

fun AdventOfCode.showResult(part: Int, sample: Boolean = false) {
    val result = measureExecutionTime {  runPart(part, sample)}
    println("Day $day Part $part: ${result.first} (${result.second}ms)")
}

fun <T> measureExecutionTime(block: () -> T): Pair<T, Long> {
    var result: T?
    val executionTime = measureTimeMillis {
        result = block()
    }
    return Pair(result!!, executionTime)
}

class AdventOfCodeArgs(parser: ArgParser) {
    val sample by parser.flagging(
        "-S", "--sample",
        help = "run on sample input"
    )
    val day by parser.storing(
        "-D", "--day",
        help = "day number"
    ) { toInt() }

    val part by parser.storing(
        "-P", "--part",
        help = "part number"
    ) { toInt() }.default(0)
}
