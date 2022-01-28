package com.skillbox.mytestapp.di

import com.example.featuremapscreen.data.MapRepositoryImpl
import com.example.featuremapscreen.data.GetMarkerUseCaseImpl
import com.example.featuremapscreen.data.MyLastLocationUseCaseImpl
import com.example.featuremapscreen.data.datasources.MyLastLocationSourceImpl
import com.example.featuremapscreen.domain.data.MapRepository
import com.example.featuremapscreen.domain.data.datasources.MyLastLocationSource
import com.example.featuremapscreen.domain.usecase.GetMarkerUseCase
import com.example.featuremapscreen.domain.usecase.MyLastLocationUseCase
import com.example.featuremapscreen.presentation.MapViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataMapModule = module {

    single<MyLastLocationSource> {
        MyLastLocationSourceImpl(
            context = androidContext()
        )
    }

    single<MapRepository>{
        MapRepositoryImpl(myLastLocationSource = get())
    }

    single<GetMarkerUseCase>{
        GetMarkerUseCaseImpl(repository = get())
    }

    single<MyLastLocationUseCase> {
        MyLastLocationUseCaseImpl(repository = get())
    }

    viewModel<MapViewModel> {
        MapViewModel(getMarkersUseCase = get(), myLastLocationUseCase = get())
    }
}