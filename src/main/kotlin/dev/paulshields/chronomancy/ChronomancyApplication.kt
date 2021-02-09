package dev.paulshields.chronomancy

import dev.paulshields.chronomancy.api.ChronomancyApi
import dev.paulshields.chronomancy.bot.ChronomancyBot
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ChronomancyApplication(
    private val chronomancyBot: ChronomancyBot,
    private val chronomancyApi: ChronomancyApi) {

    suspend fun start() = coroutineScope {
        launch { chronomancyBot.startListening() }
        launch { chronomancyApi.start() }
    }
}
