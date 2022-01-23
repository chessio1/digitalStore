package com.example.feature_main_screen.data.dao

import androidx.room.*
import com.example.feature_main_screen.data.model.RemoteBestSeller
import com.example.feature_main_screen.data.model.RemoteHomeStore
import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.data.model.bestSeller.RemoteBestSellerContracts
import com.example.feature_main_screen.data.model.homeStore.RemoteHomeStoreContracts

@Dao
interface MainScreenDao {

    @Transaction
    suspend fun insertMainScreenFields(remoteMainScreen: RemoteMainScreen) {
        addHomeStores(remoteMainScreen.home_store)
        addBestSellers(remoteMainScreen.best_seller)
    }

    @Insert
    suspend fun addHomeStores(homeStores: List<RemoteHomeStore>)

    @Insert
    suspend fun addBestSellers(bestSellers: List<RemoteBestSeller>)

    @Query("SELECT * FROM ${RemoteBestSellerContracts.TABLE_NAME}")
    suspend fun getBestSellers(): List<RemoteBestSeller>

    @Query("SELECT * FROM ${RemoteHomeStoreContracts.TABLE_NAME}")
    suspend fun getHomeStores(): List<RemoteHomeStore>

    @Transaction
    suspend fun getMainScreen(): RemoteMainScreen? {
        val bestSeller = getBestSellers()
        if (bestSeller.isEmpty()) return null
        val homeStores = getHomeStores()
        return RemoteMainScreen("1", bestSeller, homeStores)
    }
}