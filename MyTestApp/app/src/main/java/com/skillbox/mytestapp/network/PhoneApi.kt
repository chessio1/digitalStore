package com.skillbox.mytestapp.network

import com.skillbox.mytestapp.data.models.MainScreen
import com.skillbox.mytestapp.data.models.details.PhoneDetailsItem
import com.skillbox.mytestapp.data.models.myCard.RemoteCart
import retrofit2.http.GET
import retrofit2.http.Path


interface PhoneApi {

    @GET("home")
    suspend fun getMain():Array<MainScreen>

    @GET("detail/{objectid}")
    suspend fun getDetails(@Path("objectid") id:String):PhoneDetailsItem

    @GET("cart")
    suspend fun loadCart():Array<RemoteCart>


}
