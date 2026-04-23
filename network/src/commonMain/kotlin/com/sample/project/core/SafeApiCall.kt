package com.sample.project.core

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResult<T> {
    return try {
        val result = apiCall()
        ApiResult.Success(result)
    } catch (e: NoConnectivityException) {
        ApiResult.Error(e.message ?: "No internet connection")
    } catch (e: IOException) {
        ApiResult.Error("Network error: ${e.message ?: "Unknown IO error"}")
    } catch (e: TimeoutCancellationException) {
        ApiResult.Error("Request timed out")
    } catch (e: ClientRequestException) {
        ApiResult.Error("Client error: ${e.response.status.value}")
    } catch (e: ServerResponseException) {
        ApiResult.Error("Server error: ${e.response.status.value}")
    } catch (e: Exception) {
        ApiResult.Error("Unexpected error: ${e.message ?: "Unknown error"}")
    }
}
