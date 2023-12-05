import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day05Test : FunSpec({
    context("Day Day05") {
        test("Part 1") {
            Day05().runPart(1).asInt shouldBe 650599855
        }
        test("Part 2") {
            Day05().runPart(2).asInt shouldBe 1240035
        }
    }
})