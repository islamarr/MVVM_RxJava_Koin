package com.islam.music.common.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * To handle Http Exceptions Like no internet connection and Time out
 */
class SafeServiceCall<T>(
    private val dispatcher: CoroutineDispatcher,
    private val apiCall: (suspend () -> T?)? = null,
    private val cacheCall: (suspend () -> T?)? = null
) {

    suspend fun safeCall(): DataResponse<T> {
        cacheCall?.let {
            safeCacheCall(it)
        }

        apiCall?.let {
            return safeApiCall(it)
        } ?: return safeCacheCall(cacheCall)
    }

    private suspend fun safeApiCall(
        apiCall: (suspend () -> T?)?
    ): DataResponse<T> {
        return withContext(dispatcher) {
            try {
                val result = apiCall?.invoke()
                result?.let {
                    DataResponse.Success(it)
                } ?: DataResponse.Failure()

            } catch (throwable: Throwable) {
                cacheCall?.let {
                    safeCacheCall(it)
                } ?: when (throwable) {
                    is HttpException -> {
                        DataResponse.Failure(throwable.response()?.errorBody().toString())
                    }
                    else -> {
                        DataResponse.Failure(throwable.message)
                    }
                }
            }
        }
    }

    private suspend fun safeCacheCall(
        cacheCall: (suspend () -> T?)?
    ): DataResponse<T> {
        return withContext(dispatcher) {
            try {
                val result = cacheCall?.invoke()
                result?.let {
                    DataResponse.Success(it)
                } ?: DataResponse.Failure()
            } catch (e: Exception) {
                DataResponse.Failure(e.message)
            }
        }
    }

}