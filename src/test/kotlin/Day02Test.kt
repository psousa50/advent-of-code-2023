import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Test : FunSpec({
    context("Day Day02") {
        test("Part 1") {
            Day02().runPart(1).intValue shouldBe 0
        }
        test("Part 2") {
            Day02().runPart(2).intValue shouldBe 0
        }
    }
})