package dev.paulshields.chronomancy.api

import dev.paulshields.chronomancy.api.keepalive.KeepAliveApiRoutes
import dev.paulshields.chronomancy.config.ChronomancyConfiguration
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChronomancyApi(
    private val configuration: ChronomancyConfiguration,
    private val keepAliveRoutes: KeepAliveApiRoutes) {

    suspend fun start() = withContext(Dispatchers.Default) {
        embeddedServer(Netty, port = configuration.httpServerPort) {
            keepAliveRoutes.bind(this)
        }.start(wait = true)
    }
}
