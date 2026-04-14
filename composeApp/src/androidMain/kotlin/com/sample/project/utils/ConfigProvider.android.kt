package com.sample.project.utils

actual object ConfigProvider {
    actual fun getBaseUrl(): String {
       return NativeConfig.getBaseUrl()
    }

    actual fun getApiKey(): String {
        return NativeConfig.getApiKey()
    }

    actual fun getClientId(): String {
       return NativeConfig.getClientId()
    }

    actual fun getSecretKey(): String {
        return NativeConfig.getSecretKey()
    }

    actual fun getAnotherKey(): String {
       return NativeConfig.getAnotherKey()
    }
}