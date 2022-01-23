package com.skillbox.mytestapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_cart_screen.data.dao.CartDao
import com.example.feature_cart_screen.data.model.Basket
import com.example.feature_cart_screen.data.model.Cart
import com.example.feature_details_screen.data.dao.DetailsDao
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_main_screen.data.dao.MainScreenDao
import com.example.feature_main_screen.data.model.RemoteBestSeller
import com.example.feature_main_screen.data.model.RemoteHomeStore

@Database(
    entities = [
        RemoteBestSeller::class,
        RemoteHomeStore::class,
        RemotePhoneDetailsItem::class,
        Cart::class,
        Basket::class
    ], version = 1, exportSchema = false
)
abstract class StoreDataBase : RoomDatabase() {
    abstract fun mainScreenDao(): MainScreenDao
    abstract fun detailsDao(): DetailsDao
    abstract fun cartDao(): CartDao
}