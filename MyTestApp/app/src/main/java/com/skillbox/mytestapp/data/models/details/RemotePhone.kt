package com.skillbox.mytestapp.data.models.details

data class RemotePhone(
    val CPU: String,
    val _id: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val images: List<String>,
    val is_favorites: Boolean,
    val price: Int,
    val rating: Double,
    val sd: String,
    val ssd: String,
    val title: String
)