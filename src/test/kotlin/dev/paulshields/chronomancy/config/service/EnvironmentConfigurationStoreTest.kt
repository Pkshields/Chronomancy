package dev.paulshields.chronomancy.config.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import assertk.assertions.isNotEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import dev.paulshields.chronomancy.common.KeyNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class EnvironmentConfigurationStoreTest {
    private val fileLocation = "File.properties"
    private val systemKey = "PATH"
    private val systemValue = "BCool"
    private val fileKey = "SessionMoth"
    private val fileValue = "Martina"

    private val fileContents = mapOf(systemKey to systemValue, fileKey to fileValue)

    private val propertyFileParser = mockk<PropertyFileParser>()

    private val target = EnvironmentConfigurationStore(propertyFileParser)

    @Test
    fun `should load properties file`() {
        every { propertyFileParser.parse(any()) } returns emptyMap()

        target.loadFile(fileLocation)

        verify { propertyFileParser.parse(fileLocation) }
    }

    @Test
    fun `should read property from system as priority`() {
        givenPropertyFileHasBeenLoaded()

        val result = target.getProperty(systemKey)

        assertThat(result).isNotEqualTo(systemValue)
        assertThat(result).isNotNull()
        assertThat(result).isNotEmpty()
    }

    @Test
    fun `should read property from file as backup`() {
        givenPropertyFileHasBeenLoaded()

        val result = target.getProperty(fileKey)

        assertThat(result).isEqualTo(fileValue)
    }

    @Test
    fun `should throw if property does not exist anywhere`() {
        assertThrows<KeyNotFoundException> { target.getProperty(fileKey) }
    }

    @Test
    fun `should return null if property does not exist anywhere`() {
        val result = target.getPropertyOrNull(fileKey)

        assertThat(result).isNull()
    }

    private fun givenPropertyFileHasBeenLoaded() {
        every { propertyFileParser.parse(fileLocation) } returns fileContents
        target.loadFile(fileLocation)
    }
}
