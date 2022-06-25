package com.islam.music.common.di

import com.islam.music.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class URLInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val editedURL = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("format", "json")
            .build()

        val request = chain.request()
            .newBuilder()
            .url(editedURL)
            .build()
       return chain.proceed(request)
    }
}