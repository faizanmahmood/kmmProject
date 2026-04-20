package com.sample.project.utils

actual object ConfigProvider {
    actual fun getBaseUrl(): String {
        return "https://api.example.com/"
    }

    actual fun getApiKey(): String {
        return "API_KEY_123"
    }

    actual fun getClientId(): String {
        return "CLIENT_ID_456"
    }

    actual fun getSecretKey(): String {
        return "SECRET_789"
    }

    actual fun getAnotherKey(): String {
        return "KEY_000"
    }
}
