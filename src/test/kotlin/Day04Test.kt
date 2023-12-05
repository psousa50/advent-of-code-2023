import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day04Test : FunSpec({
    context("Day Day04") {
        test("Part 1") {
            Day04().runPart(1).asInt shouldBe 15268
        }
        test("Part 2") {
            Day04().runPart(2).asInt shouldBe 6283755
        }
    }
})