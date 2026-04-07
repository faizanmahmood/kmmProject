package com.sample.project.core

import com.sample.project.di.initKoin
import org.koin.core.KoinApplication

object KoinHolder {
    private var koinApp: KoinApplication? = null

    fun init() {
        if (koinApp != null) return

        initKoin()
    }
}