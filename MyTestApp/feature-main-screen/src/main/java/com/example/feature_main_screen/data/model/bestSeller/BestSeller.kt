package com.example.feature_main_screen.data.model.bestSeller

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_main_screen.data.model.RemoteBestSeller
import com.squareup.moshi.JsonClass

@Entity(tableName = BestSellerContracts.TABLE_NAME)
data class BestSeller(
    @ColumnInfo(name = BestSellerContracts.Columns.MAIN_SCREEN_ID)
    val main_screen_id:String,
    @ColumnInfo(name = BestSellerContracts.Columns.DISCOUNT_PRICE)
    val discount_price: Int,
    @PrimaryKey
    @ColumnInfo(name = BestSellerContracts.Columns.ID)
    val id: Int,
    @ColumnInfo(name = BestSellerContracts.Columns.IS_FAVOURITES)
    val is_favorites: Boolean?,
    @ColumnInfo(name = BestSellerContracts.Columns.PICTURE)
    val picture: String,
    @ColumnInfo(name = BestSellerContracts.Columns.PRICE_WITHOUT_DISCOUNT)
    val price_without_discount: Int,
    @ColumnInfo(name = BestSellerContracts.Columns.TITLE)
    val title: String
){
    fun convertToRemote():RemoteBestSeller{
        return RemoteBestSeller(
            discount_price,
            id,
            is_favorites,
            picture,
            price_without_discount,
            title
        )
    }
}