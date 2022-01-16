package com.skillbox.mytestapp.di

import com.example.feature_details_screen.domain.DetailsApi
import com.example.feature_main_screen.data.network.CartApi
import com.example.feature_main_screen.data.network.HomeApi
import com.example.core.utils.network.MyNetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        GsonConverterFactory.create()
    }

    single <Retrofit>{
        Retrofit.Builder()
            .baseUrl("https://shopapi-0575.restdb.io/rest/")
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    single <HomeApi> {
        val retrofit:Retrofit = get()
        retrofit.create(HomeApi::class.java)
    }
    single <CartApi> {
        val retrofit:Retrofit = get()
        retrofit.create(CartApi::class.java)
    }
    single <DetailsApi> {
        val retrofit:Retrofit = get()
        retrofit.create(DetailsApi::class.java)
    }
}