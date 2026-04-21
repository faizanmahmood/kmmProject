package com.sample.project.di

import io.ktor.client.engine.HttpClientEngineFactory

expect fun httpClientEngineFactory(): HttpClientEngineFactory<*>
