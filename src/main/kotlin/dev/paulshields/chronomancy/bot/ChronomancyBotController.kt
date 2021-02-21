package dev.paulshields.chronomancy.bot

import com.jessecorbett.diskord.api.model.Message
import dev.paulshields.chronomancy.bot.time.TimeService
import dev.paulshields.chronomancy.common.logger

class ChronomancyBotController(private val timeService: TimeService) {
    private val logger by logger()

    fun handleReceivedMessage(message: Message) {
        timeService.extractTimeFromMessage(message.content)
            ?.let { logger.debug(it.toString()) }
            ?: run { logger.debug("No time found in message ${message.content}") }
    }
}
