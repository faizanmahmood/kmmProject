package com.sample.project.utils

object NativeConfig {

    init {
        System.loadLibrary("native-lib")
    }

    external fun getBaseUrl(): String
    external fun getApiKey(): String
    external fun getClientId(): String
    external fun getSecretKey(): String
    external fun getAnotherKey(): String
}