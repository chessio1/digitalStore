package com.example.feature_main_screen.data.model

import com.example.feature_main_screen.data.model.homeStore.HomeStore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RemoteHomeStore(
    val id: Int,
    val is_buy: Boolean,
    val is_favorites: Boolean?,
    val is_new: Boolean?,
    val picture: String,
    val subtitle: String?,
    val title: String
){
    fun toHomeStore(mainScreenId:String):HomeStore{
        return HomeStore(
            mainScreenId,
            id,
            is_buy,
            is_favorites,
            is_new,
            picture,
            subtitle,
            title
        )
    }
}