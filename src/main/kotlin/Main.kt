import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::MyArgs).run {
        val solution = AdventOfCode.days[day] ?: throw IllegalArgumentException("Day: $day not implemented")
        if (part == 0 || part == 1) {
            solution.runPart(1, sample)
        }
        if (part == 0 || part == 2) {
            solution.runPart(2, sample)
        }
    }
}

fun AdventOfCode.runPart(part: Int, sample: Boolean = false): SolutionResult {
    val file = if (sample) "sample$part" else "input"
    val inputFilename = "Day${"%02d".format(day)}/$file.txt"
    val input = SolutionInput(Parsers.readLines(inputFilename))
    return when (part) {
        1 -> partOne(input)
        2 -> partTwo(input)
        else -> throw IllegalArgumentException("Invalid part $part")
    }
}

class MyArgs(parser: ArgParser) {
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
