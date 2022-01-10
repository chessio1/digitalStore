package com.skillbox.mytestapp.di

import com.skillbox.mytestapp.network.MyNetworkInterceptor
import com.skillbox.mytestapp.network.PhoneApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val networkModule = module {

    single <OkHttpClient>{
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addNetworkInterceptor (
                MyNetworkInterceptor()
            )
            .build()
    }

    single <Converter.Factory>{
        MoshiConverterFactory.create()
    }

    single <Retrofit>{
        Retrofit.Builder()
            .baseUrl("https://db2021ecom-edca.restdb.io/rest/")
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    single <PhoneApi> {
        val retrofit:Retrofit = get()
        retrofit.create(PhoneApi::class.java)
    }
}