package com.example.feature_main_screen.data.network

import com.example.feature_cart_screen.data.model.RemoteCart
import retrofit2.http.GET

interface CartApi {
        @GET("cart")
        suspend fun loadCart():Array<RemoteCart>
}