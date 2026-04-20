package com.sample.project.di

import org.koin.core.KoinApplication


object KoinHolder {
    private var koinApp: KoinApplication? = null

    fun init() {
        if (koinApp != null) return

        initKoin()




    }
}