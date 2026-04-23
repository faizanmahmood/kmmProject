package com.sample.project.network

// Offline gating is implemented on Android; iOS can be extended with NWPathMonitor from the app shell if needed.
internal actual fun hasActiveNetwork(): Boolean = true
