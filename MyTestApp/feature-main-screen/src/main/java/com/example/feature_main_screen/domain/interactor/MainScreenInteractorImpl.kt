package com.example.feature_main_screen.domain.interactor

import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.domain.repository.ElectronicsRepository

class MainScreenInteractorImpl(private val repository: ElectronicsRepository) :
    MainScreenInteractor {
    override suspend fun getMainScreen(): RemoteMainScreen {
        return repository.loadMainScreen()
    }

    override suspend fun getSortedMainScreen(): RemoteMainScreen {
        val unsortedMainScreen: RemoteMainScreen = repository.loadMainScreen()
        val sortedBestSeller = unsortedMainScreen.best_seller.sortedBy {
            it.title
        }
        val sortedHomeStore = unsortedMainScreen.home_store.sortedBy {
            it.title
        }
        return unsortedMainScreen.copy(
            best_seller = sortedBestSeller,
            home_store = sortedHomeStore
        )
    }

}