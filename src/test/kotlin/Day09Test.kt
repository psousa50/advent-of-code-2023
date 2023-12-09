import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day09Test : FunSpec({
    context("Day Day09") {
        test("Part 1") {
            Day09().runPart(1).asInt shouldBe 1842168671
        }
        test("Part 2") {
            Day09().runPart(2).asInt shouldBe 903
        }
    }
})