package com.example.feature_main_screen.data.datasources

import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.data.network.MainScreenApi

class RemoteMainScreenDataSourceImpl(
    private val mainScreenApi: MainScreenApi,
):MainScreenDataSource {
    override suspend fun getMainScreenData(): RemoteMainScreen? {
        return mainScreenApi.getMain().firstOrNull()
    }
}