object Parsers {
    fun readLinesFromResource(fileName: String): List<String> {
        val inputStream = javaClass.getResourceAsStream(fileName) ?: throw Exception("File $fileName not found")
        return inputStream.bufferedReader().readLines()
    }
}