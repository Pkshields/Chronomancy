package dev.paulshields.chronomancy.bot.time

import dev.paulshields.chronomancy.common.TimeIsNotValidException

data class ZonelessTime(val hour: Int, val minute: Int) {
    companion object {
        fun fromTwentyFourHourTime(hour: Int, minute: Int): ZonelessTime? {
            if (!twentyFourHourTimeIsValid(hour, minute)) return null

            return ZonelessTime(hour, minute)
        }

        fun fromTwelveHourTime(hour: Int, minute: Int, ampm: String): ZonelessTime? {
            if (!twelveHourTimeIsValid(hour, minute, ampm)) return null

            return ZonelessTime(
                if (ampm.equals("PM", true)) hour + 12 else hour,
                minute)
        }

        private fun twentyFourHourTimeIsValid(hour: Int, minute: Int) = hour in 0..23 && minute in 0..59

        private fun twelveHourTimeIsValid(hour: Int, minute: Int, ampm: String) = hour in 0..11
                && minute in 0..59
                && (ampm.equals("AM", true) || ampm.equals("PM", true))
    }

    init {
        if (!twentyFourHourTimeIsValid(hour, minute)) throw TimeIsNotValidException(toString())
    }

    override fun toString() = "$hour:$minute"
}
