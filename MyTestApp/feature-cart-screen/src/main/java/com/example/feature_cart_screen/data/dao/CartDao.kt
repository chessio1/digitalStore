package com.example.feature_cart_screen.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.feature_cart_screen.data.model.*

@Dao
interface CartDao {

    @Transaction
    @Query("SELECT * FROM ${CartContracts.TABLE_NAME}")
    suspend fun getCart():CartWithBasket?

    @Transaction
    suspend fun addCartWithBasketToDB(cart: Cart,baskets: List<Basket>){
        addCartToDB(cart)
        addBasketsToDB(baskets)
    }

    @Insert
    suspend fun addBasketsToDB(baskets:List<Basket>)

    @Insert
    suspend fun addCartToDB(cart:Cart)

}