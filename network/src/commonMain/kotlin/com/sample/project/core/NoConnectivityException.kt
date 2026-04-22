package com.sample.project.core

import io.ktor.utils.io.errors.IOException

class NoConnectivityException(
    message: String = "No internet connection available."
) : IOException(message)
