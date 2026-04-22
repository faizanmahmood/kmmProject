package com.sample.project.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

internal actual fun hasActiveNetwork(): Boolean {
    val context: Context = NetworkAndroidHolder.contextOrNull() ?: return true
    val connectivity =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: return true
    val network = connectivity.activeNetwork ?: return false
    val capabilities = connectivity.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
