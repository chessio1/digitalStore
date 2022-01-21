package com.example.feature_cart_screen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = BasketContracts.TABLE_NAME)
data class Basket(
    @ColumnInfo(name = BasketContracts.Columns.CART_OWNER_ID)
    val cartId :String,
    @PrimaryKey
    @ColumnInfo(name = BasketContracts.Columns.ID)
    val id:Int?,
    @ColumnInfo(name = BasketContracts.Columns.IMAGES)
    val images: String,
    @ColumnInfo(name = BasketContracts.Columns.PRICE)
    val price: Int,
    @ColumnInfo(name = BasketContracts.Columns.TITLE)
    val title: String
) {
    fun toRemoteBasket():RemoteBasket {
        return RemoteBasket(
            id,
            images,
            price,
            title
        )
    }
}

