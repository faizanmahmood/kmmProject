package com.sample.project.network

/**
 * Platform-specific defaults for [NetworkClientConfig] (e.g. debug logging flags differ on Android vs iOS).
 */
expect fun nativeNetworkClientConfig(): NetworkClientConfig
