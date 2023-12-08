class Day07 : AdventOfCode {
    override val day = 7

    override fun partOne(input: SolutionInput): SolutionResult {
        val hands = input.lines.map { it.toHandBid(NormalStrategy()) }

        return hands.sortedBy { it.rank }
            .mapIndexed { i, hand -> hand.bid * (i + 1) }.sum().asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        val hands = input.lines.map { it.toHandBid(JokerStrategy()) }

        return hands.sortedBy { it.rank }
            .mapIndexed { i, hand -> hand.bid * (i + 1) }.sum().asSolution()
    }

    private fun String.toHandBid(strategy: RankStrategy): HandBid {
        val (cards, bid) = split(" ")
        val hand = Hand(cards)
        return HandBid(hand, bid.toLong(), strategy.rank(hand))
    }

    data class HandBid(
        val hand: Hand,
        val bid: Long,
        val rank: Long,
    )

    data class Hand(
        val cards: String,
    ) {
        val handType = calcHandType()

        private fun calcHandType(): HandType {
            val groups = cards.toList().groupBy { it }
            val numberOfGroups = groups.size
            val maxGroupCount = groups.values.maxOf { it.size }
            return when {
                numberOfGroups == 1 -> HandType.FIVE_OF_A_KIND
                numberOfGroups == 2 && maxGroupCount == 4 -> HandType.FOUR_OF_A_KIND
                numberOfGroups == 2 && maxGroupCount == 3 -> HandType.FULL_HOUSE
                numberOfGroups == 3 && maxGroupCount == 3 -> HandType.THREE_OF_A_KIND
                numberOfGroups == 3 && maxGroupCount == 2 -> HandType.TWO_PAIRS
                numberOfGroups == 4 -> HandType.ONE_PAIR
                else -> HandType.NOTHING
            }
        }
    }

    enum class HandType(val value: Int) {
        NOTHING(0),
        ONE_PAIR(1),
        TWO_PAIRS(2),
        THREE_OF_A_KIND(3),
        FULL_HOUSE(4),
        FOUR_OF_A_KIND(5),
        FIVE_OF_A_KIND(6),
    }

    interface RankStrategy {
        fun rank(hand: Hand): Long
        fun cardValue(c: Char): Int

        fun calcRank(handType: HandType, cards: String): Long {
            val digits = (listOf(handType.value) + cards.toList().map { cardValue(it) })
            return digits.fold(0L) { acc, c -> 20 * acc + c }
        }
    }

    class NormalStrategy : RankStrategy {
        override fun rank(hand: Hand): Long {
            return calcRank(hand.handType, hand.cards)
        }

        override fun cardValue(c: Char) =
            when (c) {
                'A' -> 14
                'K' -> 13
                'Q' -> 12
                'J' -> 11
                'T' -> 10
                else -> c.toString().toInt()
            }
    }

    class JokerStrategy : RankStrategy {
        override fun rank(hand: Hand): Long {
            return calcRank(bestHand(hand).handType, hand.cards)
        }

        override fun cardValue(c: Char) =
            when (c) {
                'A' -> 14
                'K' -> 13
                'Q' -> 12
                'J' -> 1
                'T' -> 10
                else -> c.toString().toInt()
            }

        fun bestHand(hand: Hand): Hand {
            val jokerSubstitutions = Regex("J")
                .findAll(hand.cards)
                .map { it.range.first }
                .map { it to hand.cards.toList().distinct() }
                .toList()

            val allHands = applyTransformations(jokerSubstitutions, 0, hand.cards, listOf(hand.cards))

            return allHands.map { Hand(it) }.maxBy { NormalStrategy().rank(it) }
        }

        private fun applyTransformations(
            jokerSubstitutions: List<Pair<Int, List<Char>>>,
            i: Int,
            cards: String,
            generated: List<String>
        ): List<String> {
            if (i >= jokerSubstitutions.size) return generated

            val (index, substitutions) = jokerSubstitutions[i]
            val newGenerated = substitutions.map { cards.replaceChar(it, index) }
            return newGenerated.filter { it !in generated }.map {
                applyTransformations(jokerSubstitutions, i + 1, it, generated + it)
            }.flatten().ifEmpty { generated }
        }

        private fun String.replaceChar(c: Char, i: Int) = replaceRange(i, i + 1, c.toString())
    }
}


