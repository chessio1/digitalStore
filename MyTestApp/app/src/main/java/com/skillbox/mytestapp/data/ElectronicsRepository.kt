package com.skillbox.mytestapp.data

import com.skillbox.mytestapp.data.models.MainScreen

interface ElectronicsRepository {

    suspend fun loadStartScreen():MainScreen
    suspend fun searchPhones(query: String)
    val some:Int
}