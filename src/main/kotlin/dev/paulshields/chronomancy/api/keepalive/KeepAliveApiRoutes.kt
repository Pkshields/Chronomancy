package dev.paulshields.chronomancy.api.keepalive

import dev.paulshields.chronomancy.api.ApiRoutes
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

class KeepAliveApiRoutes : ApiRoutes {
    override fun bind(ktorApplication: Application): Unit = with(ktorApplication) {
        routing {
            get("/status") {
                call.respondText("OK!")
            }
        }
    }
}
