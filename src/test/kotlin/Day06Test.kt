import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day06Test : FunSpec({
    context("Day Day06") {
        test("Part 1") {
            Day06().runPart(1).asInt shouldBe 741000
        }
        test("Part 2") {
            Day06().runPart(2).asInt shouldBe 38220708
        }
    }
})