package com.example.feature_main_screen.data.datasources

import com.example.feature_main_screen.data.model.RemoteMainScreen

interface MainScreenDataSource {
    suspend fun getMainScreenData():RemoteMainScreen?
}