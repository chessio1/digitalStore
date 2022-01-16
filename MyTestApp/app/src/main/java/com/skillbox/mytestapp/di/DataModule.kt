package com.skillbox.mytestapp.di

import com.example.feature_cart_screen.data.CartRepositoryImpl
import com.example.feature_cart_screen.domain.repository.CartRepository
import com.example.feature_cart_screen.presentation.CartViewModel
import com.example.feature_details_screen.domain.DetailsRepository
import com.example.feature_details_screen.presentation.DetailsViewModel
import com.example.feature_main_screen.data.ElectronicsRepositoryImpl
import com.example.feature_main_screen.domain.repository.ElectronicsRepository
import com.example.feature_main_screen.presentation.MainScreenViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    viewModel<MainScreenViewModel> {
        MainScreenViewModel(repository = get())
    }

    viewModel<DetailsViewModel>{
        DetailsViewModel(repository = get())
    }

    viewModel<CartViewModel>{
        CartViewModel(repository = get())
    }

    single<ElectronicsRepository> {
        ElectronicsRepositoryImpl(get())
    }

    single<DetailsRepository> {
        com.example.feature_details_screen.data.DetailsRepositoryImpl(get(), androidContext())
    }

    single<CartRepository>{
        CartRepositoryImpl(get())
    }
}