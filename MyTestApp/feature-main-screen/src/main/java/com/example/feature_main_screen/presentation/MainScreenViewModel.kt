package com.example.feature_main_screen.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.exceptions.NoDataException
import com.example.core.utils.utils.SingleLiveEvent
import com.example.feature_main_screen.data.model.DeviceSelectionItem
import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.domain.interactor.MainScreenInteractor
import com.example.feature_main_screen.domain.repository.ElectronicsRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber


class MainScreenViewModel(
    private val interactor: MainScreenInteractor
) : ViewModel() {

    private val _remoteMainScreen: MutableLiveData<RemoteMainScreen> =
        MutableLiveData()
    val remoteMainScreen: LiveData<RemoteMainScreen>
        get() = _remoteMainScreen

    private val _devices: MutableLiveData<List<DeviceSelectionItem>> =  MutableLiveData()
    val devices: LiveData<List<DeviceSelectionItem>>
        get() = _devices

    fun getMainScreen() {
        viewModelScope.launch {
            val mainScreen = interactor.getSortedMainScreen()
            _remoteMainScreen.postValue(mainScreen)
        }
    }

    fun getDevices(){
        _devices.postValue(interactor.getDevicesList())
    }

    fun searchPhones(query: String) {
        interactor.getSearchResult()
    }

    fun getToken() {
        viewModelScope.launch {
            interactor.getToken()
        }
    }


}