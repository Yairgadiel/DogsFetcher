package com.gy.demo.dogsScreen.model.network

/**
 * Generic result of a network request, containing optional [data] representing the response's body,
 * and optional [message] representing result's extra info
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)

    class Loading<T>() : NetworkResult<T>()
}
