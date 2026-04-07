package com.sample.project

import android.app.Application
import com.sample.project.core.KoinHolder

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        KoinHolder.init()
    }
}