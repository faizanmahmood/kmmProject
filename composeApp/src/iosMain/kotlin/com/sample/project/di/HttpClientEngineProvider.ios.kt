package com.sample.project.di

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun httpClientEngineFactory(): HttpClientEngineFactory<*> = Darwin
