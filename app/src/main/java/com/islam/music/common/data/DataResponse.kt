package com.islam.music.common.data


sealed class DataResponse<out T> {
    data class Success<T>(
        val data: T?,
    ) : DataResponse<T>()

    data class Failure(
        val reason: String? = null,
    ) : DataResponse<Nothing>()

  //  object Loading : DataResponse<Nothing>() //TODO uncomment this
}