package com.sample.project.di

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual fun httpClientEngineFactory(): HttpClientEngineFactory<*> = CIO
