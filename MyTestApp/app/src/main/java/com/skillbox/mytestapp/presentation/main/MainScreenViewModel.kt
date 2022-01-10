package com.skillbox.mytestapp.presentation.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.mytestapp.data.ElectronicsRepository
import com.skillbox.mytestapp.data.models.MainScreen
import com.skillbox.mytestapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber


class MainScreenViewModel(private val repository: ElectronicsRepository) : ViewModel() {

    private val _remoteMainScreen: SingleLiveEvent<MainScreen> = SingleLiveEvent()
    val remoteMainScreen: LiveData<MainScreen>
        get() = _remoteMainScreen

    fun getMainScreen() {
        Timber.d("TESTER2")
        viewModelScope.launch {
            val mainScreen = repository.loadStartScreen()
            Timber.d("TESTER2")
            _remoteMainScreen.postValue(mainScreen)
        }
    }

    fun searchPhones(query: String) {
        viewModelScope.launch {
            repository.searchPhones(query)
        }
    }


}