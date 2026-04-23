package com.sample.project

import android.app.Application
import com.sample.project.core.KoinHolder
import com.sample.project.network.NetworkAndroidHolder
import com.sample.project.network.nativeNetworkClientConfig

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkAndroidHolder.init(this)
        KoinHolder.init(nativeNetworkClientConfig())
    }
}