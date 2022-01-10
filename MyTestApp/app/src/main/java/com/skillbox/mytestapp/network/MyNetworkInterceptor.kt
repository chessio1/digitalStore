package com.skillbox.mytestapp.network

import okhttp3.Interceptor
import okhttp3.Response

class MyNetworkInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-apikey", "2aa8e910f6c4ade81a84c9333ffc7bf6a398e")
            .build()
        return chain.proceed(request)
    }
}