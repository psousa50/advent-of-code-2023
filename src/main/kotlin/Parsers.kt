object Parsers {
    fun readLines(fileName: String): List<String> {
        val inputStream = javaClass.getResourceAsStream(fileName) ?: throw Exception("File $fileName not found")
        val lineList = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        return lineList
    }
}