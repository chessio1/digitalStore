package com.example.feature_main_screen.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MainScreen(
    val _id: String,
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)