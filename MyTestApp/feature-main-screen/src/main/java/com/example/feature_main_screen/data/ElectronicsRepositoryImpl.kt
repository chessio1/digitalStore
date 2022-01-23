package com.example.feature_main_screen.data

import com.example.core.utils.exceptions.NoDataException
import com.example.feature_main_screen.data.datasources.*
import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.domain.repository.ElectronicsRepository


class ElectronicsRepositoryImpl(
    private val localMainScreenDataSource: MainScreenDataSource,
    private val remoteMainScreenDataSource: MainScreenDataSource,
    ) : ElectronicsRepository {

    override suspend fun loadMainScreen(): RemoteMainScreen {
            return localMainScreenDataSource.getMainScreenData()
                ?: remoteMainScreenDataSource.getMainScreenData()?.also {
                    saveData(it)
                }
                ?: throw NoDataException()
    }

    private suspend fun saveData(remoteMainScreen:RemoteMainScreen){
        (localMainScreenDataSource as LocalMainScreenDataSourceImpl).saveMainScreenData(remoteMainScreen)
    }

    override suspend fun searchPhones(query: String) {
        //TODO("Создать запрос телефонов")
    }
}
