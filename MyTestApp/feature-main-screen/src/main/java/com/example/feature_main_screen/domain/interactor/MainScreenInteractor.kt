package com.example.feature_main_screen.domain.interactor

import com.example.feature_main_screen.data.model.DeviceSelectionItem
import com.example.feature_main_screen.data.model.RemoteMainScreen

interface MainScreenInteractor {
    suspend fun getMainScreen(): RemoteMainScreen
    suspend fun getSortedMainScreen(): RemoteMainScreen
    fun getSearchResult()
    suspend fun getToken()
    fun getDevicesList(): List<DeviceSelectionItem>
}