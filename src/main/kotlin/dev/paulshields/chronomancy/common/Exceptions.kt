// ktlint-disable filename
@file:Suppress("MatchingDeclarationName")

package dev.paulshields.chronomancy.common

class KeyNotFoundException(key: String) : RuntimeException("Key $key was not found.")
