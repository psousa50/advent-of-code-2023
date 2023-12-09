import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day08Test : FunSpec({
    context("Day Day08") {
        test("Part 1") {
            Day08().runPart(1).asInt shouldBe 12083
        }
        test("Part 2") {
            Day08().runPart(2).asLong shouldBe 13385272668829
        }
    }
})