package com.example.feature_main_screen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_main_screen.data.model.bestSeller.RemoteBestSellerContracts
import com.squareup.moshi.JsonClass

@Entity(tableName = RemoteBestSellerContracts.TABLE_NAME)
@JsonClass(generateAdapter = true)
data class RemoteBestSeller(
    @ColumnInfo(name = RemoteBestSellerContracts.Columns.DISCOUNT_PRICE)
    val discount_price: Int,
    @PrimaryKey
    @ColumnInfo(name = RemoteBestSellerContracts.Columns.ID)
    val id: Int,
    @ColumnInfo(name = RemoteBestSellerContracts.Columns.IS_FAVOURITES)
    val is_favorites: Boolean?,
    @ColumnInfo(name = RemoteBestSellerContracts.Columns.PICTURE)
    val picture: String,
    @ColumnInfo(name = RemoteBestSellerContracts.Columns.PRICE_WITHOUT_DISCOUNT)
    val price_without_discount: Int,
    @ColumnInfo(name = RemoteBestSellerContracts.Columns.TITLE)
    val title: String
):ListItem()