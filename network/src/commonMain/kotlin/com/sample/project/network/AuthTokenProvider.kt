package com.sample.project.network

fun interface AuthTokenProvider {
    suspend fun getToken(): String?
}

object NoOpAuthTokenProvider : AuthTokenProvider {
    override suspend fun getToken(): String? = null
}
