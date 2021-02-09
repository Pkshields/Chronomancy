package dev.paulshields.chronomancy.bot

import dev.paulshields.chronomancy.bot.discord.DiscordBotFactory
import dev.paulshields.chronomancy.config.ChronomancyConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChronomancyBot(
    private val config: ChronomancyConfiguration,
    private val discordBotFactory: DiscordBotFactory,
    private val chronomancyBotController: ChronomancyBotController) {

    suspend fun startListening() = withContext(Dispatchers.Default) {
        val bot = discordBotFactory.createBot(config.discordBotKey)
        bot.messageCreated(chronomancyBotController::handleReceivedMessage)
        bot.start()
    }
}
