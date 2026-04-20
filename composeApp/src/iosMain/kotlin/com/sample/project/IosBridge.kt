package com.sample.project

import androidx.compose.ui.window.ComposeUIViewController
import com.sample.project.di.KoinHolder
import platform.UIKit.UIViewController

fun createComposeRootController(): UIViewController = ComposeUIViewController {
    KoinHolder.init()
    SampleApplication()
}
