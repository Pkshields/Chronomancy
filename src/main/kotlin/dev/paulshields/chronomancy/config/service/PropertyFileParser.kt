package dev.paulshields.chronomancy.config.service

import dev.paulshields.chronomancy.common.ResourceFileReader

class PropertyFileParser(private val resourceFileReader: ResourceFileReader) {
    private val keyValueRegex = Regex("(.+) ?= ?(.+)")

    fun parse(fileLocation: String): Map<String, String> {
        val fileContents = resourceFileReader.readFileAsString(fileLocation) ?: ""
        return fileContents
            .lines()
            .filter { !it.startsWith("#") }
            .mapNotNull(::extractKeyValuePairFromLine)
            .toMap()
    }

    private fun extractKeyValuePairFromLine(line: String) = keyValueRegex
        .find(line)
        ?.let {
            Pair(it.destructured.component1(), it.destructured.component2())
        }
}
