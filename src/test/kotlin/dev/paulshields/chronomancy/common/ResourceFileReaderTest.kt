package dev.paulshields.chronomancy.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.Test

class ResourceFileReaderTest {
    private val validFileLocation = "/ResourceFileReaderTestFile.txt"
    private val validFileContents = "Some text that is in a file\n"

    private val target = ResourceFileReader()

    @Test
    fun `should read file from resources as string`() {
        val result = target.readFileAsString(validFileLocation)

        assertThat(result).isEqualTo(validFileContents)
    }

    @Test
    fun `should handle file not found when reading file from resource as string`() {
        val result = target.readFileAsString("InvalidFile.txt")

        assertThat(result).isNull()
    }
}
