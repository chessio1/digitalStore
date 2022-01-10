package com.skillbox.mytestapp.data

import com.skillbox.mytestapp.data.models.MainScreen
import com.skillbox.mytestapp.network.PhoneApi
import timber.log.Timber

class ElectronicsRepositoryImpl(private val api:PhoneApi):ElectronicsRepository {

    override suspend fun loadStartScreen():MainScreen {
        val list = api.getMain()
        Timber.d(list.toString())
        return list.firstOrNull() ?: error("NO SUCH DATA")
    }

    override suspend fun searchPhones(query: String) {
        //TODO("Создать запрос телефонов")
    }

    override val some = 1
}
