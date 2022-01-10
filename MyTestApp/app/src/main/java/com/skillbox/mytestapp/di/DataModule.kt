package com.skillbox.mytestapp.di

import com.skillbox.mytestapp.data.*
import com.skillbox.mytestapp.presentation.card.CartViewModel
import com.skillbox.mytestapp.presentation.details.DetailsViewModel
import com.skillbox.mytestapp.presentation.main.MainScreenViewModel
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
        DetailsRepositoryImpl(get(),androidContext())
    }

    single<CartRepository>{
        CartRepositoryImpl(get())
    }
}