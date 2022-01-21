package com.example.feature_main_screen.data.network

import com.example.feature_main_screen.data.model.RemoteMainScreen
import retrofit2.http.GET

interface HomeApi {
        @GET("home")
        suspend fun getMain():Array<RemoteMainScreen>
}