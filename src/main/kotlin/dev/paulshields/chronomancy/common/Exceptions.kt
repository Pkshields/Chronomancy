package dev.paulshields.chronomancy.common

class KeyNotFoundException(key: String) : RuntimeException("Key $key was not found.")

class TwelveHourPostfixNotValidException(item: String) : RuntimeException("$item is neither AM or PM.")

class TimeIsNotValidException(timeAsString: String) : RuntimeException("$timeAsString is not a valid time.")
