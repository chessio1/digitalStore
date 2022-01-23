package com.skillbox.mytestapp.di


import androidx.room.Room
import com.example.feature_cart_screen.data.dao.CartDao
import com.example.feature_details_screen.data.dao.DetailsDao
import com.example.feature_main_screen.data.dao.MainScreenDao
import com.skillbox.mytestapp.data.database.StoreDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {

    single<StoreDataBase> {
        Room.databaseBuilder(
            androidApplication(),
            StoreDataBase::class.java, "store_database"
        ).build()
    }

    single<MainScreenDao>{
        get<StoreDataBase>().mainScreenDao()
    }

    single<DetailsDao>{
        get<StoreDataBase>().detailsDao()
    }

    single <CartDao>{
        get<StoreDataBase>().cartDao()
    }

}