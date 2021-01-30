package dev.paulshields.chronomancy.common

import java.time.LocalDateTime

@Suppress("TooManyFunctions")
class Logger(private val className: String) {
    fun trace(message: String) = println(buildMessage("TRACE", message))
    fun debug(message: String) = println(buildMessage("DEBUG", message))
    fun info(message: String) = println(buildMessage("INFO", message))
    fun warn(message: String) = println(buildMessage("WARN", message))
    fun error(message: String) = System.err.println(buildMessage("ERROR", message))

    fun trace(message: String, exception: Exception) {
        trace(message)
        exception.printStackTrace()
    }

    fun debug(message: String, exception: Exception) {
        debug(message)
        exception.printStackTrace()
    }

    fun info(message: String, exception: Exception) {
        info(message)
        exception.printStackTrace()
    }

    fun warn(message: String, exception: Exception) {
        warn(message)
        exception.printStackTrace()
    }

    fun error(message: String, exception: Exception) {
        error(message)
        exception.printStackTrace()
    }

    private fun buildMessage(logLevel: String, message: String) =
        "${LocalDateTime.now()} $logLevel @ $className: $message"
}

fun <TAnyClass : Any> TAnyClass.logger(): Lazy<Logger> {
    return lazy { Logger(this::class.qualifiedName ?: "") }
}
