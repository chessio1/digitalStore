package com.skillbox.mytestapp.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class MyNetworkInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-apikey", "61ddae2e95cb716ea5ee48e4")
            .build()
        return chain.proceed(request)
    }
}