package com.example.feature_main_screen.data.model.bestSeller

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

object BestSellerContracts {
    const val TABLE_NAME = "best_sellers"

    object Columns {
        const val ID = "id"
        const val MAIN_SCREEN_ID="main_screen_id"
        const val DISCOUNT_PRICE ="discount_price"
        const val IS_FAVOURITES="is_favourites"
        const val PICTURE="picture"
        const val PRICE_WITHOUT_DISCOUNT="price_without_discount"
        const val TITLE="title"
    }
}

//@ColumnInfo(name = BestSellerContracts.Columns.MAIN_SCREEN_ID)
//val mainScreen_id:Int,
//@ColumnInfo(name = "discount_price")
//val discount_price: Int,
//@PrimaryKey
//@ColumnInfo(name = "id")
//val id: Int,
//@ColumnInfo(name = "is_favourites")
//val is_favorites: Boolean?,
//@ColumnInfo(name = "picture")
//val picture: String,
//@ColumnInfo(name = "price_without_discount")
//val price_without_discount: Int,
//@ColumnInfo(name = "title")
//val title: String