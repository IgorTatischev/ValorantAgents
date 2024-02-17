package com.agents.main.data.util

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(api: suspend () -> Response<T>): Resource<T> {
    try {
        val response = api()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let { return Resource.Success(body) }
                ?: return Resource.Error(message = "Body is empty")
        } else {
            return Resource.Error(message = "${response.code()} ${response.message()}")
        }
    } catch (throwable: Throwable) {
        return when (throwable) {
            is HttpException -> Resource.Error(message = "${throwable.code()} ${throwable.message()}")
            is IOException -> Resource.Error(message = throwable.message.toString())
            else -> Resource.Error(message = throwable.message.toString())
        }
    }
}