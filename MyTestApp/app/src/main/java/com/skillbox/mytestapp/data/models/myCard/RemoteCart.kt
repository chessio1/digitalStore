package com.skillbox.mytestapp.data.models.myCard

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteCart(
    val Delivery: String,
    val _id: String,
    val basket: List<Basket>,
    val total: Int
)