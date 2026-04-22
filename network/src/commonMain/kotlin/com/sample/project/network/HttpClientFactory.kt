package com.sample.project.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import com.sample.project.core.NoConnectivityException
import io.ktor.client.plugins.plugin
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException as KtorIOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.json.Json

internal expect fun createHttpClientEngine(): HttpClientEngine

fun createNetworkHttpClient(config: NetworkClientConfig = NetworkClientConfig()): HttpClient {
    val client = HttpClient(createHttpClientEngine()) {
        if (config.maxRetries > 0) {
            install(HttpRequestRetry) {
                noRetry()
                maxRetries = config.maxRetries
                retryIf { _, response ->
                    response.status.value in 500..599
                }
                retryOnExceptionIf { _, cause ->
                    when {
                        cause is NoConnectivityException -> false
                        cause is CancellationException -> false
                        cause is ClientRequestException -> false
                        cause is TimeoutCancellationException -> true
                        cause is HttpRequestTimeoutException -> true
                        cause is ConnectTimeoutException -> true
                        cause is SocketTimeoutException -> true
                        cause is KtorIOException -> true
                        else -> false
                    }
                }
                delayMillis { retry ->
                    (config.retryDelayMillis * retry).coerceAtLeast(config.retryDelayMillis)
                }
            }
        }

        install(HttpTimeout) {
            connectTimeoutMillis = config.connectTimeoutMillis
            socketTimeoutMillis = config.socketTimeoutMillis
            requestTimeoutMillis = config.requestTimeoutMillis
        }

        if (config.enableLogging) {
            install(Logging) {
                level = LogLevel.BODY
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }

    config.authTokenProvider?.let { provider ->
        client.plugin(HttpSend.Plugin).intercept { request ->
            val token = provider.getToken()
            if (!token.isNullOrBlank()) {
                request.headers.remove(HttpHeaders.Authorization)
                val scheme = config.authorizationScheme.trim()
                val value = if (scheme.isEmpty()) token else "$scheme $token"
                request.headers.append(HttpHeaders.Authorization, value)
            }
            execute(request)
        }
    }

    if (config.enableConnectivityCheck) {
        client.plugin(HttpSend.Plugin).intercept { request ->
            if (!hasActiveNetwork()) {
                throw NoConnectivityException()
            }
            execute(request)
        }
    }

    return client
}
