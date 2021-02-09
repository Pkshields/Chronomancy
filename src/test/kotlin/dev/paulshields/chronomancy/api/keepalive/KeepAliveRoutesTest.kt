package dev.paulshields.chronomancy.api.keepalive

import dev.paulshields.chronomancy.testcommon.assertRequestSucceeded
import dev.paulshields.chronomancy.testcommon.withTestServer
import io.ktor.http.HttpMethod
import io.ktor.server.testing.handleRequest
import org.junit.Test

class KeepAliveRoutesTest {
    private val target = KeepAliveApiRoutes()

    @Test
    fun `should return a successful response when querying the server for it's status`() = withTestServer(target) {
        val apiCall = handleRequest(HttpMethod.Get, "/status")

        assertRequestSucceeded(apiCall)
    }
}
