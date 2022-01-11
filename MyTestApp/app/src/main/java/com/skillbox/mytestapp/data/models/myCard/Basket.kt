package com.skillbox.mytestapp.data.models.myCard

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.koin.core.qualifier.named

@JsonClass(generateAdapter = true)
data class Basket(
    val id:Int?,
    @Json(name = "images")
    val image: String,
    val price: Int,
    val title: String
)