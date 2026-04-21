@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package com.sample.nativeconfig

import com.sample.nativeconfig.interop.getAnotherKey as interopGetAnotherKey
import com.sample.nativeconfig.interop.getApiKey as interopGetApiKey
import com.sample.nativeconfig.interop.getBaseUrl as interopGetBaseUrl
import com.sample.nativeconfig.interop.getClientId as interopGetClientId
import com.sample.nativeconfig.interop.getSecretKey as interopGetSecretKey
import kotlinx.cinterop.toKString

actual object NativeConfigKeys {
    actual fun getBaseUrl(): String = interopGetBaseUrl()?.toKString().orEmpty()
    actual fun getApiKey(): String = interopGetApiKey()?.toKString().orEmpty()
    actual fun getClientId(): String = interopGetClientId()?.toKString().orEmpty()
    actual fun getSecretKey(): String = interopGetSecretKey()?.toKString().orEmpty()
    actual fun getAnotherKey(): String = interopGetAnotherKey()?.toKString().orEmpty()
}
