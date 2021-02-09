package dev.paulshields.chronomancy.config

import dev.paulshields.chronomancy.config.service.EnvironmentConfigurationStore

class ChronomancyConfiguration(private val environmentConfigurationStore: EnvironmentConfigurationStore) {
    val discordBotKey by lazy { environmentConfigurationStore.getProperty("DISCORD_BOT_KEY") }
    val httpServerPort by lazy { environmentConfigurationStore.getProperty("HTTP_SERVER_PORT").toInt() }

    init {
        environmentConfigurationStore.loadFile("/local.properties")
    }
}
