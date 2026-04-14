package com.sample.project.utils

expect object ConfigProvider {
    fun getBaseUrl(): String
    fun getApiKey(): String
    fun getClientId(): String
    fun getSecretKey(): String
    fun getAnotherKey(): String
}