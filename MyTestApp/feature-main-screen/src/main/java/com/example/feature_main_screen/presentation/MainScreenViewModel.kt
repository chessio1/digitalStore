package com.example.feature_main_screen.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.utils.SingleLiveEvent
import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.domain.interactor.MainScreenInteractor
import com.example.feature_main_screen.domain.repository.ElectronicsRepository
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val repository: ElectronicsRepository,
    private val interactor:MainScreenInteractor

    ) : ViewModel() {

    private val _remoteMainScreen: SingleLiveEvent<RemoteMainScreen> =
        SingleLiveEvent()
    val remoteMainScreen: LiveData<RemoteMainScreen>
        get() = _remoteMainScreen

    fun getMainScreen() {
        viewModelScope.launch {
            val mainScreen = interactor.getSortedMainScreen()
            _remoteMainScreen.postValue(mainScreen)
        }
    }

    fun searchPhones(query: String) {
        viewModelScope.launch {
            repository.searchPhones(query)
        }
    }


}