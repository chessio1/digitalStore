package com.example.feature_main_screen.domain.repository

import com.example.feature_main_screen.data.model.DeviceSelectionItem
import com.example.feature_main_screen.data.model.RemoteMainScreen

interface ElectronicsRepository {
    suspend fun loadMainScreen(): RemoteMainScreen
    suspend fun searchPhones(query: String)
    suspend fun getToken()
    fun getDeviceList(): List<DeviceSelectionItem>
}