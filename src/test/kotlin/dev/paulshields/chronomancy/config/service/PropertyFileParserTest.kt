package dev.paulshields.chronomancy.config.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.chronomancy.common.ResourceFileReader
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class PropertyFileParserTest {
    private val fileLocation = "FileLocation.properties"
    private val validPropertiesFileContents = "SessionMoth=Martina"
    private val commentedOutPropertyInFileContents = "#SessionMoth=Martina"
    private val invalidPropertiesFileContents = "#SessionMothMartina"
    private val parsedFileContents = mapOf("SessionMoth" to "Martina")

    private val resourceFileReader = mockk<ResourceFileReader>()

    private val target = PropertyFileParser(resourceFileReader)

    @Test
    fun `should parse a valid properties file`() {
        every { resourceFileReader.readFileAsString(fileLocation) } returns validPropertiesFileContents

        val result = target.parse(fileLocation)

        assertThat(result).isEqualTo(parsedFileContents)
    }

    @Test
    fun `should remove comments from file`() {
        every { resourceFileReader.readFileAsString(fileLocation) } returns commentedOutPropertyInFileContents

        val result = target.parse(fileLocation)

        assertThat(result).isEqualTo(emptyMap())
    }

    @Test
    fun `should handle non-properties files`() {
        every { resourceFileReader.readFileAsString(fileLocation) } returns invalidPropertiesFileContents

        val result = target.parse(fileLocation)

        assertThat(result).isEqualTo(emptyMap())
    }

    @Test
    fun `should handle a file not existing`() {
        every { resourceFileReader.readFileAsString(fileLocation) } returns null

        val result = target.parse(fileLocation)

        assertThat(result).isEqualTo(emptyMap())
    }
}
