package dev.paulshields.chronomancy.testcommon

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import dev.paulshields.chronomancy.api.ApiRoutes
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationCall
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication

fun <R> withTestServer(apiRoutes: ApiRoutes, test: TestApplicationEngine.() -> R) =
    withTestApplication({ apiRoutes.bind(this) }, test)

fun assertRequestCompleted(apiCall: TestApplicationCall, statusCode: HttpStatusCode) {
    assertThat(apiCall.requestHandled).isTrue()
    assertThat(apiCall.response.status()).isEqualTo(statusCode)
}

fun assertRequestSucceeded(apiCall: TestApplicationCall) = assertRequestCompleted(apiCall, HttpStatusCode.OK)
