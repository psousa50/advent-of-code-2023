import Parsers.split

typealias Node = String

class Day08 : AdventOfCode {
    override val day = 8

    override fun partOne(input: SolutionInput): SolutionResult {
        val parts = input.lines.split { it.isBlank() }
        val directions = parts[0].first().toList().map { Direction from it }
        val documents = parts[1].toDocuments()

        val firstNode = documents["AAA"]?.node ?: ""
        return pathToZ(firstNode, directions, documents) {
            it == "ZZZ"
        }.count().asSolution()
    }

    override fun partTwo(input: SolutionInput): SolutionResult {
        val parts = input.lines.split { it.isBlank() }
        val directions = parts[0].first().toList().map { Direction from it }
        val documents = parts[1].toDocuments()

        val firstNodes = documents.keys.filter { it.endsWith("A") }.map { documents[it]?.node ?: "" }
        val pathCounts = firstNodes.map { firstNode ->
            pathToZ(firstNode, directions, documents) {
                it.endsWith("Z")
            }.count().toLong()
        }

        return lmc(pathCounts).asSolution()
    }

    private fun List<String>.toDocuments(): Map<String, Document> {
        val parseRegex = Regex("[A-Z0-9]{3}")
        return associate { line ->
            val (node, left, right) = parseRegex.findAll(line).map { it.value }.toList()
            node to Document(
                node, mapOf(
                    Direction.LEFT to left,
                    Direction.RIGHT to right,
                )
            )
        }
    }

    private fun pathToZ(
        firstNode: Node,
        directions: List<Direction>,
        documents: Map<String, Document>,
        endNode: (Node) -> Boolean
    ): Sequence<Node> =
        generateSequence(0 to firstNode) { (i, node) ->
            val direction = directions[i % directions.size]
            val nextNode = documents[node]?.nextNodes?.getValue(direction) ?: ""
            (i + 1) to nextNode
        }.map { it.second }.takeWhile { !endNode(it) }


    private fun lmc(numbers: List<Long>): Long {
        return numbers.reduce { acc, i -> acc * i / gcd(acc, i) }
    }

    private fun gcd(n1: Long, n2: Long): Long {
        if (n1 == 0L) {
            return n2
        }
        return gcd(n2 % n1, n1)
    }

    data class Document(
        val node: Node,
        val nextNodes: Map<Direction, Node>
    )

    enum class Direction(val c: Char) {
        LEFT('L'),
        RIGHT('R');

        companion object {
            infix fun from(c: Char) = entries.first { it.c == c }
        }
    }
}
