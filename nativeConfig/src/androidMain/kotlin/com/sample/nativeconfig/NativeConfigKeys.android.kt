package com.sample.nativeconfig

actual object NativeConfigKeys {
    actual fun getBaseUrl(): String = NativeConfigJni.getBaseUrl()
    actual fun getApiKey(): String = NativeConfigJni.getApiKey()
    actual fun getClientId(): String = NativeConfigJni.getClientId()
    actual fun getSecretKey(): String = NativeConfigJni.getSecretKey()
    actual fun getAnotherKey(): String = NativeConfigJni.getAnotherKey()
}
