package com.sample.nativeconfig

internal object NativeConfigJni {
    init {
        System.loadLibrary("nativeconfig")
    }

    external fun getBaseUrl(): String
    external fun getApiKey(): String
    external fun getClientId(): String
    external fun getSecretKey(): String
    external fun getAnotherKey(): String
}
