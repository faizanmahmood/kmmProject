package com.sample.project.network

actual fun nativeNetworkClientConfig(): NetworkClientConfig =
    NetworkClientConfig(enableLogging = BuildConfig.DEBUG)
