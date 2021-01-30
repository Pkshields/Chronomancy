package dev.paulshields.chronomancy.common

fun readSystemVariable(key: String): String? = System.getenv(key)
