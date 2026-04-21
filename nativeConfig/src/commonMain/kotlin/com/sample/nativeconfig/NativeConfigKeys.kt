package com.sample.nativeconfig

expect object NativeConfigKeys {
    fun getBaseUrl(): String
    fun getApiKey(): String
    fun getClientId(): String
    fun getSecretKey(): String
    fun getAnotherKey(): String
}
