package dev.paulshields.chronomancy.common

import org.koin.core.KoinApplication

inline fun <reified TClass : Any> KoinApplication.getInstance() = this.koin.get<TClass>()
