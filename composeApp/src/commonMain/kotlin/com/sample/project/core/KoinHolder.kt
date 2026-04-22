package com.sample.project.core

import com.sample.project.di.initKoin
import com.sample.project.network.NetworkClientConfig
import org.koin.core.KoinApplication

object KoinHolder {
    private var koinApp: KoinApplication? = null

    fun init(networkClientConfig: NetworkClientConfig = NetworkClientConfig()) {
        if (koinApp != null) return

        initKoin(networkClientConfig)



        
    }
}