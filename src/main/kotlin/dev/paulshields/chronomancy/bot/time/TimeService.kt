package dev.paulshields.chronomancy.bot.time

class TimeService {
    private val timeExtractionRegex = Regex("(\\d+):?(\\d+)? ?([APM]{2})?", RegexOption.IGNORE_CASE)

    fun extractTimeFromMessage(message: String) = timeExtractionRegex
        .find(message)
        ?.destructured
        ?.takeUnless(::justANumberDetected)
        ?.let {
            buildZonelessTime(
                it.component1().toInt(),
                it.component2().toIntOrNull() ?: 0,
                it.component3())
        }

    private fun justANumberDetected(match: MatchResult.Destructured) =
        match.component2().isEmpty() && match.component3().isEmpty()

    private fun buildZonelessTime(hour: Int, minute: Int, ampm: String): ZonelessTime? {
        return if (ampm.isNotEmpty()) {
            ZonelessTime.fromTwelveHourTime(hour, minute, ampm)
        } else {
            ZonelessTime.fromTwentyFourHourTime(hour, minute)
        }
    }
}
