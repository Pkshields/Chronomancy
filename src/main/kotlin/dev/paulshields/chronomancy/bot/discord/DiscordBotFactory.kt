package dev.paulshields.chronomancy.bot.discord

import com.jessecorbett.diskord.dsl.Bot

class DiscordBotFactory {
    fun createBot(token: String) = Bot(token)
}
