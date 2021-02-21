package dev.paulshields.chronomancy.bot.time

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import dev.paulshields.chronomancy.testcommon.parameterizedTest
import dev.paulshields.chronomancy.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class TimeServiceTest {
    private val target = TimeService()

    @TestFactory
    fun `should parse all valid times from string input`() = parameterizedTest(
        "9PM" to ZonelessTime(21, 0),
        "9 AM" to ZonelessTime(9, 0),
        "9:30PM" to ZonelessTime(21, 30),
        "21:45" to ZonelessTime(21, 45),
        "9pm" to ZonelessTime(21, 0),
        "9 am" to ZonelessTime(9, 0),
        "9:30pm" to ZonelessTime(21, 30),
        "9" to ZonelessTime(9, 0)
    ).runTest { (input, expectedOutput) ->
        val output = target.extractTimeFromMessage(input)

        assertThat(output).isEqualTo(expectedOutput)
    }

    @TestFactory
    fun `should return null when input does not contain a valid time`() = parameterizedTest(
        "Not a time",
        "1234567890",
        "!@£$%^&*()"
    ).runTest { input ->
        val output = target.extractTimeFromMessage(input)

        assertThat(output).isNull()
    }
}
