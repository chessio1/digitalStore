package com.skillbox.mytestapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.feature_cart_screen.data.CartRepositoryImpl
import com.example.feature_cart_screen.data.dao.CartDao
import com.example.feature_cart_screen.domain.repository.CartRepository
import com.example.feature_cart_screen.presentation.CartViewModel
import com.example.feature_details_screen.data.DetailsRepositoryImpl
import com.example.feature_details_screen.data.dao.DetailsDao
import com.example.feature_details_screen.domain.DetailsRepository
import com.example.feature_details_screen.presentation.DetailsViewModel
import com.example.feature_main_screen.data.ElectronicsRepositoryImpl
import com.example.feature_main_screen.data.dao.MainScreenDao
import com.example.feature_main_screen.domain.interactor.MainScreenInteractor
import com.example.feature_main_screen.domain.interactor.MainScreenInteractorImpl
import com.example.feature_main_screen.domain.repository.ElectronicsRepository
import com.example.feature_main_screen.presentation.MainScreenViewModel
import com.skillbox.mytestapp.data.database.StoreDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
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