import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day01Test : FunSpec({
    context("Day 01") {
        test("Part 1") {
            Day01().runPart(1).intValue shouldBe 54708
        }
        test("Part 2") {
            Day01().runPart(2).intValue shouldBe 54087
        }
    }
})