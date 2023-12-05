class Day04 : AdventOfCode {
    override val day = 4

    override fun partOne(input: SolutionInput): SolutionResult {
        val cards = input.lines.map { it.toCard() }
        return cards.sumOf { it.winners.powerOfTwo() }.asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        val cards = input.lines.map { it.toCard() }
        val cardsWinningNumbersCount = cards.associate { it.id to it.winners.count() }.withDefault { 0 }
        val cardsCount = (1..cards.size).associateWith { 1 }.toMutableMap().withDefault { 0 }
        cardsCount.forEach { (cardId, count) ->
            for (i in 1..cardsWinningNumbersCount.getValue(cardId)) {
                cardsCount[cardId + i] = cardsCount.getValue(cardId + i) + count
            }
        }

        return cardsCount.values.sumOf { it }.asSolution()
    }

    private fun List<Int>.powerOfTwo() = fold(0) { acc, _ -> if (acc == 0) 1 else acc * 2 }

    private fun String.toCard(): Card {
        val (id, numbers) = split(":")
        val (_, cardId) = id.split(" ").filter { it.isNotBlank() }
        val (winningNumbers, ownNumbers) = numbers.split("|")
        return Card(
            cardId.toInt(),
            winningNumbers.split(" ").filter { it.isNotBlank() }.map { it.toInt() },
            ownNumbers.split(" ").filter { it.isNotBlank() }.map { it.toInt() })
    }

    data class Card(
        val id: Int,
        val winningNumbers: List<Int>,
        val ownNumbers: List<Int>,
    ) {
        val winners by lazy { ownNumbers.filter { number -> number in winningNumbers } }
    }
}
