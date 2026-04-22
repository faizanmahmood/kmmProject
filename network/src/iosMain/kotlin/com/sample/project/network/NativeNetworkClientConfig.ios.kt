package com.sample.project.network

import platform.Foundation.NSBundle

actual fun nativeNetworkClientConfig(): NetworkClientConfig {
    val path = NSBundle.mainBundle.bundlePath
    val isDebugInstall =
        path.contains("Debug-iphoneos", ignoreCase = true) ||
            path.contains("Debug-iphonesimulator", ignoreCase = true)
    return NetworkClientConfig(enableLogging = isDebugInstall)
}
