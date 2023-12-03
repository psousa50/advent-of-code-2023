class Day03 : AdventOfCode {
    override val day = 3

    override fun partOne(input: SolutionInput): SolutionResult {
        val schematic = Schematic(input.lines)
        return schematic.numbers()
            .filter { schematic.symbolCells().any { symbol -> it.rectangle().contains(symbol) } }
            .sumOf { it.number }
            .asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        val schematic = Schematic(input.lines)
        val starSymbols = schematic.symbolCells().filter { it.char == '*' }
        val numberCells = schematic.numbers()
        val starSymbolAdjacentNumbers = starSymbols.map { starSymbol ->
            numberCells.filter { it.rectangle().contains(starSymbol) }
        }
        val gears = starSymbolAdjacentNumbers.filter { it.size == 2 }
        return gears.map { it[0].number * it[1].number }.sumOf { it }.asSolution()
    }

    companion object {
        private val digitsRegex = Regex("[0-9]+")
        const val EMPTY_CELL = '.'
    }

    class Schematic(private val lines: List<String>) {
        operator fun get(x: Int, y: Int) = lines[y][x]
        operator fun get(cell: Cell) = this[cell.x, cell.y]


        private fun allCells() = lines.flatMapIndexed { y, line ->
            line.mapIndexed { x, _ -> Cell(x, y, this[x, y]) }
        }

        private fun isSymbol(c: Char) = c != EMPTY_CELL && !c.isDigit()

        fun symbolCells() = allCells().filter { cell -> isSymbol(cell.char) }

        fun numbers(): List<NumberCell> =
            lines.mapIndexed() { y, line ->
                digitsRegex.findAll(line)
                    .map {
                        NumberCell(
                            cell = Cell(it.range.first, y),
                            length = it.range.last - it.range.first + 1,
                            number = it.value.toInt()
                        )
                    }
                    .toList()
            }.flatten()
    }

    data class Cell(val x: Int, val y: Int, val char: Char = EMPTY_CELL)

    data class NumberCell(val cell: Cell, val length: Int, val number: Int) {
        fun rectangle() = Rectangle(
            Cell(
                cell.x - 1,
                cell.y - 1
            ),
            Cell(
                cell.x + 1 + length - 1,
                cell.y + 1
            )
        )
    }

    data class Rectangle(val topLeft: Cell, val bottomRight: Cell) {
        fun contains(cell: Cell) =
            cell.x >= topLeft.x && cell.x <= bottomRight.x &&
                cell.y >= topLeft.y && cell.y <= bottomRight.y
    }
}
