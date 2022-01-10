package com.skillbox.mytestapp.data.models.myCard

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Basket(
    val id:Int?,
    val image: String,
    val price: Int,
    val title: String
)