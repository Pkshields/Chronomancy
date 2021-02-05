package dev.paulshields.chronomancy.bot

import dev.paulshields.chronomancy.bot.discord.DiscordBotFactory
import dev.paulshields.chronomancy.config.ChronomancyConfiguration

class ChronomancyBot(
    private val config: ChronomancyConfiguration,
    private val discordBotFactory: DiscordBotFactory,
    private val chronomancyBotController: ChronomancyBotController) {

    suspend fun startListening() {
        val bot = discordBotFactory.createBot(config.discordBotKey)
        bot.messageCreated(chronomancyBotController::handleReceivedMessage)
        bot.start()
    }
}
