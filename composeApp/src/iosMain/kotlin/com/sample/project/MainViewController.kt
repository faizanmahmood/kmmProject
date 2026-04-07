package com.sample.project

import androidx.compose.ui.window.ComposeUIViewController
import com.sample.project.core.KoinHolder

fun MainViewController() = ComposeUIViewController {
    KoinHolder.init()
    SampleApplication()
}