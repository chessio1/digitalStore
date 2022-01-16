package com.example.feature_main_screen.data

import com.example.feature_main_screen.data.model.MainScreen
import com.example.feature_main_screen.data.network.HomeApi
import com.example.feature_main_screen.domain.repository.ElectronicsRepository

class ElectronicsRepositoryImpl(private val api:HomeApi): ElectronicsRepository {

    override suspend fun loadStartScreen(): MainScreen {
        val list = api.getMain()
        return list.firstOrNull() ?: error("NO SUCH DATA")
    }

    override suspend fun searchPhones(query: String) {
        //TODO("Создать запрос телефонов")
    }

    override val some = 1
}
