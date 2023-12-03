import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day03Test : FunSpec({
    context("Day Day03") {
        test("Part 1") {
            Day03().runPart(1).asInt shouldBe 550064
        }
        test("Part 2") {
            Day03().runPart(2).asInt shouldBe 85010461
        }
    }
})