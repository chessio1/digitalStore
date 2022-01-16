package com.example.feature_details_screen.domain

import com.example.feature_details_screen.data.model.PhoneDetailsItem
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {
    @GET("detail/{objectid}")
    suspend fun getDetails(@Path("objectid") id:String): PhoneDetailsItem
}