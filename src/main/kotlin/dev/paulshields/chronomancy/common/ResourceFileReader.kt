package dev.paulshields.chronomancy.common

class ResourceFileReader {
    private val logger by logger()

    fun readFileAsString(fileLocation: String): String? {
        if (!fileLocation.startsWith("/")) {
            logger.warn("$fileLocation is a relative path which may not resolve correctly from an external class.")
        }

        return this::class.java
            .getResource(fileLocation)
            ?.readText()
    }
}
