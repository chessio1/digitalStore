package com.example.feature_cart_screen.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.koin.core.qualifier.named

@JsonClass(generateAdapter = true)
data class Basket(
    val id:Int?,
    @Json(name = "images")
    val images: String,
    val price: Int,
    val title: String
)