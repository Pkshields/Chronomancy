package dev.paulshields.chronomancy.testcommon

import assertk.Assert
import assertk.assertions.contains

fun Assert<CharSequence>.containsAll(vararg needles: String) = needles.forEach { this.contains(it) }
