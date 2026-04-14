package com.sample.project.utils

actual object ConfigProvider {
    actual fun getBaseUrl(): String {
        NativeConfig.getBaseUrl()
    }

    actual fun getApiKey(): String {
        NativeConfig.getApiKey()
    }

    actual fun getClientId(): String {
        NativeConfig.getClientId()
    }

    actual fun getSecretKey(): String {
        NativeConfig.getSecretKey()
    }

    actual fun getAnotherKey(): String {
        NativeConfig.getAnotherKey()
    }
}