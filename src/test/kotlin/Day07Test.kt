import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day07Test : FunSpec({
    context("Day Day07") {
        test("Part 1") {
            Day07().runPart(1).asInt shouldBe 250453939
        }
        test("Part 2") {
            Day07().runPart(2).asInt shouldBe 248652697
        }
    }

    context("Hand Type") {
        test("Five of a kind") {
            val hand = Day07.Hand("AAAAA")
            hand.handType shouldBe Day07.HandType.FIVE_OF_A_KIND
        }
        test("Four of a kind") {
            val hand = Day07.Hand("AAAA2")
            hand.handType shouldBe Day07.HandType.FOUR_OF_A_KIND
        }
        test("Full house") {
            val hand = Day07.Hand("AAA22")
            hand.handType shouldBe Day07.HandType.FULL_HOUSE
        }
        test("Three of a kind") {
            val hand = Day07.Hand("AAA23")
            hand.handType shouldBe Day07.HandType.THREE_OF_A_KIND
        }
        test("Two pairs") {
            val hand = Day07.Hand("AA223")
            hand.handType shouldBe Day07.HandType.TWO_PAIRS
        }
        test("One pair") {
            val hand = Day07.Hand("AA234")
            hand.handType shouldBe Day07.HandType.ONE_PAIR
        }
        test("Nothing") {
            val hand = Day07.Hand("A2345")
            hand.handType shouldBe Day07.HandType.NOTHING
        }
    }

    context("Normal Strategy") {
        context("Hand Rank") {
            test("23456") {
                val hand = Day07.Hand("2233J")
                Day07.NormalStrategy().rank(hand) shouldBe
                    Day07.HandType.TWO_PAIRS.value * 20 * 20 * 20 * 20 * 20 +
                    2 * 20 * 20 * 20 * 20 +
                    2 * 20 * 20 * 20 +
                    3 * 20 * 20 +
                    3 * 20 +
                    11
            }
        }
    }

    context("Joker Strategy") {
        context("Hand type") {
            withData(
                "22233" to Day07.HandType.FULL_HOUSE,
                "22J22" to Day07.HandType.FIVE_OF_A_KIND,
                "3J444" to Day07.HandType.FOUR_OF_A_KIND,
                "J455J" to Day07.HandType.FOUR_OF_A_KIND,
                "JJJJJ" to Day07.HandType.FIVE_OF_A_KIND,
                "JJAJJ" to Day07.HandType.FIVE_OF_A_KIND,
            ) { (cards, expectedHandType) ->
                Day07.JokerStrategy().bestHand(Day07.Hand(cards)).handType shouldBe expectedHandType
            }
        }
    }
})