package com.sample.project.network

data class NetworkClientConfig(
    val enableLogging: Boolean = false,
    val enableConnectivityCheck: Boolean = true,
    val connectTimeoutMillis: Long = 30_000L,
    val socketTimeoutMillis: Long = 30_000L,
    val requestTimeoutMillis: Long = 60_000L,
    val maxRetries: Int = 2,
    val retryDelayMillis: Long = 300L,
    val authTokenProvider: AuthTokenProvider? = null,
    /** Prefix for the Authorization header value, e.g. `"Bearer"` → `Bearer <token>`. */
    val authorizationScheme: String = "Bearer",
)
