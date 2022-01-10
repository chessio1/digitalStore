package com.skillbox.mytestapp.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainScreen(
    val _id: String,
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)