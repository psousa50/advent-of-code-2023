import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Test : FunSpec({
    context("Day Day02") {
        test("Part 1") {
            Day02().runPart(1).asInt shouldBe 2879
        }
        test("Part 2") {
            Day02().runPart(2).asInt shouldBe 65122
        }
    }
})