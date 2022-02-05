package com.example.feature_details_screen.domain

import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {
    @GET("detail/{objectid}")
    fun getDetails(@Path("objectid") id:String): Single<RemotePhoneDetailsItem>
}