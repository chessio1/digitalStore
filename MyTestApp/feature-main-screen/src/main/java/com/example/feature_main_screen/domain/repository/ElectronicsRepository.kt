package com.example.feature_main_screen.domain.repository

import com.example.feature_main_screen.data.model.MainScreen

interface ElectronicsRepository {
    suspend fun loadStartScreen(): MainScreen
    suspend fun searchPhones(query: String)
    val some:Int
}