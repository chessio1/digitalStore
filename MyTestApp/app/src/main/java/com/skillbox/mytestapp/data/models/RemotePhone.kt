package com.skillbox.mytestapp.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemotePhone(
    val CPU: String,
    val _id: String,
    val camera: String,
    val capacity: String,
    val color: String,
    val images: String,
    val is_favorites: Boolean,
    val price: Int,
    val rating: Int,
    val sd: String,
    val ssd: String,
    val title: String
)