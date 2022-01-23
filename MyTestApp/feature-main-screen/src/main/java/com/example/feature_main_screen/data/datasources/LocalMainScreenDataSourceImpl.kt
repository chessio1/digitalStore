package com.example.feature_main_screen.data.datasources

import com.example.feature_main_screen.data.dao.MainScreenDao
import com.example.feature_main_screen.data.model.RemoteMainScreen

class LocalMainScreenDataSourceImpl(private val mainScreenDao: MainScreenDao):MainScreenDataSource {
    override suspend fun getMainScreenData(): RemoteMainScreen? {
        return mainScreenDao.getMainScreen()
    }

    suspend fun saveMainScreenData(remoteMainScreen:RemoteMainScreen){
        mainScreenDao.insertMainScreenFields(remoteMainScreen)
    }
}