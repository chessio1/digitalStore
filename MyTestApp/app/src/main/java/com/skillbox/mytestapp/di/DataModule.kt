package com.skillbox.mytestapp.di

import com.example.feature_cart_screen.data.CartRepositoryImpl
import com.example.feature_cart_screen.data.datasources.CartDataSource
import com.example.feature_cart_screen.data.datasources.LocalCartDataSourceImpl
import com.example.feature_cart_screen.data.datasources.RemoteCartDataSourceImpl
import com.example.feature_cart_screen.domain.repository.CartRepository
import com.example.feature_cart_screen.domain.usecases.GetCartUseCase
import com.example.feature_cart_screen.domain.usecases.GetCartUseCaseImpl
import com.example.feature_cart_screen.presentation.CartViewModel
import com.example.feature_details_screen.data.datasources.LocalPhoneDetailsItemDataImpl
import com.example.feature_details_screen.data.datasources.PhoneDetailsSource
import com.example.feature_details_screen.data.datasources.RemotePhoneDetailsItemDataImpl
import com.example.feature_details_screen.domain.DetailsRepository
import com.example.feature_details_screen.domain.usecase.LoadDetailsUseCase
import com.example.feature_details_screen.domain.usecase.LoadDetailsUseCaseImpl
import com.example.feature_details_screen.presentation.DetailsViewModel
import com.example.feature_main_screen.data.ElectronicsRepositoryImpl
import com.example.feature_main_screen.data.datasources.*
import com.example.feature_main_screen.domain.interactor.MainScreenInteractor
import com.example.feature_main_screen.domain.interactor.MainScreenInteractorImpl
import com.example.feature_main_screen.domain.repository.ElectronicsRepository
import com.example.feature_main_screen.presentation.MainScreenViewModel
import com.example.featuremapscreen.data.MapRepositoryImpl
import com.example.featuremapscreen.domain.GetMarkerUseCaseImpl
import com.example.featuremapscreen.domain.data.MapRepository
import com.example.featuremapscreen.domain.usecase.GetMarkerUseCase
import com.example.featuremapscreen.presentation.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    viewModel<MainScreenViewModel> {
        MainScreenViewModel(
            interactor = get()
        )
    }

    viewModel<DetailsViewModel> {
        DetailsViewModel(loadDetailsUseCase = get())
    }

    single<LoadDetailsUseCase> {
        LoadDetailsUseCaseImpl(repository = get())
    }

    viewModel<CartViewModel> {
        CartViewModel(getCartUseCase = get())
    }

    single<GetCartUseCase> {
        GetCartUseCaseImpl(repository = get())
    }

    single<MainScreenDataSource>(named("local")) {
        LocalMainScreenDataSourceImpl(
            mainScreenDao = get()
        )
    }

    single<MainScreenDataSource>(named("remote")) {
        RemoteMainScreenDataSourceImpl(
            mainScreenApi = get()
        )
    }

    single<ElectronicsRepository> {
        val localMainScreenDataSource: MainScreenDataSource by inject(named("local"))
        val remoteMainScreenDataSource: MainScreenDataSource by inject(named("remote"))
        ElectronicsRepositoryImpl(
            localMainScreenDataSource = localMainScreenDataSource,
            remoteMainScreenDataSource = remoteMainScreenDataSource
        )
    }

    single<PhoneDetailsSource>(named("local")) {
        LocalPhoneDetailsItemDataImpl(detailsDao = get())
    }

    single<PhoneDetailsSource>(named("remote")) {
        RemotePhoneDetailsItemDataImpl(detailsApi = get())
    }

    single<DetailsRepository> {
        val localDetailsSource: PhoneDetailsSource by inject(named("local"))
        val remoteDetailsSource: PhoneDetailsSource by inject(named("remote"))
        com.example.feature_details_screen.data.DetailsRepositoryImpl(
            localDetailsSource = localDetailsSource,
            remoteDetailsSource = remoteDetailsSource
        )
    }

    single<CartDataSource>(named("local")) {
        LocalCartDataSourceImpl(cartDao = get())
    }

    single<CartDataSource>(named("remote")) {
        RemoteCartDataSourceImpl(cartApi = get())
    }

    single<CartRepository> {

        val localCartSource: CartDataSource by inject(named("local"))
        val remoteCartSource: CartDataSource by inject(named("remote"))
        CartRepositoryImpl(
             localCartDataSource = localCartSource,
             remoteCartDataSource = remoteCartSource
        )

    }

    single<MainScreenInteractor> {
        MainScreenInteractorImpl(
            repository = get()
        )
    }

    single<MapRepository>{
        MapRepositoryImpl()
    }

    single<GetMarkerUseCase>{
        GetMarkerUseCaseImpl(repository = get())
    }

    viewModel<MapViewModel> {
        MapViewModel(getMarkersUseCase = get())
    }
}