package com.sample.project.network

import android.content.Context

object NetworkAndroidHolder {
    private var applicationContext: Context? = null

    fun init(context: Context) {
        applicationContext = context.applicationContext
    }

    internal fun contextOrNull(): Context? = applicationContext
}
