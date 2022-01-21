package com.example.feature_main_screen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_main_screen.data.model.bestSeller.BestSeller
import com.example.feature_main_screen.data.model.homeStore.HomeStore
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class RemoteBestSeller(
    @ColumnInfo(name = "discount_price")
    val discount_price: Int,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "is_favourites")
    val is_favorites: Boolean?,
    @ColumnInfo(name = "picture")
    val picture: String,
    @ColumnInfo(name = "price_without_discount")
    val price_without_discount: Int,
    @ColumnInfo(name = "title")
    val title: String
) {
    fun toBestSeller(mainScreenId: String):BestSeller {
        return BestSeller(
            mainScreenId,
            discount_price,
            id,
            is_favorites,
            picture,
            price_without_discount,
            title
        )
    }
}