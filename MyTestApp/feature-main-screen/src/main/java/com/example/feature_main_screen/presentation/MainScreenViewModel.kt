package com.example.feature_main_screen.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.utils.SingleLiveEvent
import com.example.feature_main_screen.data.model.MainScreen
import com.example.feature_main_screen.domain.repository.ElectronicsRepository
import kotlinx.coroutines.launch


class MainScreenViewModel(private val repository: ElectronicsRepository) : ViewModel() {

    private val _remoteMainScreen: SingleLiveEvent<MainScreen> =
        SingleLiveEvent()
    val remoteMainScreen: LiveData<MainScreen>
        get() = _remoteMainScreen

    fun getMainScreen() {
        viewModelScope.launch {
            val mainScreen = repository.loadStartScreen()
            _remoteMainScreen.postValue(mainScreen)
        }
    }

    fun searchPhones(query: String) {
        viewModelScope.launch {
            repository.searchPhones(query)
        }
    }


}