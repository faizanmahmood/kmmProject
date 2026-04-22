package com.sample.project

import androidx.compose.ui.window.ComposeUIViewController
import com.sample.project.core.KoinHolder
import com.sample.project.network.nativeNetworkClientConfig

fun MainViewController() = ComposeUIViewController {
    KoinHolder.init(nativeNetworkClientConfig())
    SampleApplication()
}