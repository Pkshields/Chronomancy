package dev.paulshields.chronomancy

import dev.paulshields.chronomancy.bot.ChronomancyBot

class Application(private val chronomancyBot: ChronomancyBot) {
    suspend fun start() {
        chronomancyBot.startListening()
    }
}
