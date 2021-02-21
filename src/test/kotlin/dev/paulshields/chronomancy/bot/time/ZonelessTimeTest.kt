package dev.paulshields.chronomancy.bot.time

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import dev.paulshields.chronomancy.common.TimeIsNotValidException
import dev.paulshields.chronomancy.testcommon.parameterizedTest
import dev.paulshields.chronomancy.testcommon.runTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows

class ZonelessTimeTest {
    @TestFactory
    fun `should initialize from a valid time data`() = parameterizedTest(
        Pair(9, 0),
        Pair(18, 0),
        Pair(0, 0),
        Pair(23, 30)
    ).runTest { (hour, minute) ->
        val target = ZonelessTime(hour, minute)

        assertThat(target.hour).isEqualTo(hour)
        assertThat(target.minute).isEqualTo(minute)
    }

    @TestFactory
    fun `should throw when time data is invalid`() = parameterizedTest(
        Pair(-1, 0),
        Pair(99, 0),
        Pair(9, 5000),
        Pair(23, -999)
    ).runTest { (hour, minute) ->
        assertThrows<TimeIsNotValidException> { ZonelessTime(hour, minute) }
    }

    @TestFactory
    fun `should initialize from a valid 24 hour time`() = parameterizedTest(
        Pair(9, 0),
        Pair(18, 0),
        Pair(0, 0),
        Pair(23, 30)
    ).runTest { (hour, minute) ->
        val target = ZonelessTime.fromTwentyFourHourTime(hour, minute)

        assertThat(target?.hour).isEqualTo(hour)
        assertThat(target?.minute).isEqualTo(minute)
    }

    @TestFactory
    fun `should return null when the 24 hour time is invalid`() = parameterizedTest(
        Pair(-1, 0),
        Pair(99, 0),
        Pair(9, 5000),
        Pair(23, -999)
    ).runTest { (hour, minute) ->
        val target = ZonelessTime.fromTwentyFourHourTime(hour, minute)

        assertThat(target).isNull()
    }

    @TestFactory
    fun `should initialize from a valid 12 hour time`() = parameterizedTest(
        Triple(9, 0, "am") to Pair(9, 0),
        Triple(6, 0, "pm") to Pair(18, 0),
        Triple(0, 0, "AM") to Pair(0, 0),
        Triple(11, 30, "PM") to Pair(23, 30)
    ).runTest { (input, expected) ->
        val (hour, minute, ampm) = input

        val target = ZonelessTime.fromTwelveHourTime(hour, minute, ampm)

        val (expectedHour, expectedMinute) = expected
        assertThat(target?.hour).isEqualTo(expectedHour)
        assertThat(target?.minute).isEqualTo(expectedMinute)
    }

    @TestFactory
    fun `should throw when a 12 hour time is invalid`() = parameterizedTest(
        Triple(-1, 0, "am"),
        Triple(9999, 0, "pm"),
        Triple(13, 0, "AM"),
        Triple(23, 0, "PM"),
        Triple(9, -1, "am"),
        Triple(9, 60, "pm"),
        Triple(9, 0, "Nothing"),
        Triple(9, 0, "")
    ).runTest { (hour, minute, ampm) ->
        val target = ZonelessTime.fromTwelveHourTime(hour, minute, ampm)

        assertThat(target).isNull()
    }

    @TestFactory
    fun `should print correct to string`() = parameterizedTest(
        Pair(9, 0),
        Pair(18, 0),
        Pair(0, 0),
        Pair(23, 30)
    ).runTest { (hour, minute) ->
        val target = ZonelessTime(hour, minute)

        assertThat(target.toString()).isEqualTo("$hour:$minute")
    }
}
