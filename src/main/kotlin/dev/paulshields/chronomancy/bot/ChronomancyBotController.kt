package dev.paulshields.chronomancy.bot

import com.jessecorbett.diskord.api.model.Message

class ChronomancyBotController {
    fun handleReceivedMessage(message: Message) {
        println(message.content)
    }
}
