package dev.paulshields.chronomancy

import dev.paulshields.chronomancy.api.ChronomancyApi
import dev.paulshields.chronomancy.api.keepalive.KeepAliveApiRoutes
import dev.paulshields.chronomancy.bot.ChronomancyBot
import dev.paulshields.chronomancy.bot.ChronomancyBotController
import dev.paulshields.chronomancy.bot.discord.DiscordBotFactory
import dev.paulshields.chronomancy.common.ResourceFileReader
import dev.paulshields.chronomancy.common.getInstance
import dev.paulshields.chronomancy.config.ChronomancyConfiguration
import dev.paulshields.chronomancy.config.service.EnvironmentConfigurationStore
import dev.paulshields.chronomancy.config.service.PropertyFileParser
import org.koin.core.context.startKoin
import org.koin.dsl.module

val dependencies = module {
    single { ChronomancyApplication(get(), get()) }

    single { ChronomancyConfiguration(get()) }
    factory { EnvironmentConfigurationStore(get()) }
    single { PropertyFileParser(get()) }
    single { ResourceFileReader() }

    single { ChronomancyBot(get(), get(), get()) }
    single { DiscordBotFactory() }
    single { ChronomancyBotController() }

    single { ChronomancyApi(get(), get()) }
    single { KeepAliveApiRoutes() }
}

suspend fun main() {
    val koinContext = startKoin {
        modules(dependencies)
    }

    val app = koinContext.getInstance<ChronomancyApplication>()
    app.start()
}
