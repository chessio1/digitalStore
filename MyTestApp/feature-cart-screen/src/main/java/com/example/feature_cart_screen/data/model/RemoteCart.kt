package com.example.feature_cart_screen.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteCart(
    val delivery: String,
    val _id: String,
    val basket: List<Basket>,
    val total: Int
)