package com.example.feature_main_screen.data.dao

import androidx.room.*
import com.example.feature_main_screen.data.model.RemoteBestSeller
import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.data.model.bestSeller.BestSeller
import com.example.feature_main_screen.data.model.bestSeller.BestSellerContracts
import com.example.feature_main_screen.data.model.homeStore.HomeStore
import com.example.feature_main_screen.data.model.homeStore.HomeStoreContracts

@Dao
interface MainScreenDao {

    @Transaction
    suspend fun insertMainScreenFields(homeStores: List<HomeStore>, bestSellers: List<BestSeller>) {
        deleteAllHomeStores()
        deleteAllBestSellers()
        addHomeStores(homeStores)
        addBestSellers(bestSellers)
    }

    @Query("DELETE FROM ${HomeStoreContracts.TABLE_NAME}")
    suspend fun deleteAllHomeStores()

    @Query("DELETE FROM ${BestSellerContracts.TABLE_NAME}")
    suspend fun deleteAllBestSellers()

    @Insert
    suspend fun addHomeStores(homeStores: List<HomeStore>)

    @Insert
    suspend fun addBestSellers(bestSellers: List<BestSeller>)

    @Query("SELECT * FROM ${BestSellerContracts.TABLE_NAME} WHERE ${BestSellerContracts.Columns.MAIN_SCREEN_ID} = :id")
    suspend fun getBestSellers(id: String): List<BestSeller>

    @Query("SELECT * FROM ${HomeStoreContracts.TABLE_NAME} WHERE ${HomeStoreContracts.Columns.MAIN_SCREEN_ID} = :id")
    suspend fun getHomeStores(id: String): List<HomeStore>

    @Transaction
    suspend fun getMainScreen(mainScreenId: String): RemoteMainScreen {
        val bestSeller = getBestSellers(mainScreenId)
        val homeStores = getHomeStores(mainScreenId)
        val remoteBestSellers = bestSeller.map {bestSeller->
            bestSeller.convertToRemote()}.toList()
        val remoteHomeStores = homeStores.map {homeStore ->
            homeStore.toRemoteHomeStore()
        }.toList()
        return RemoteMainScreen(mainScreenId.toString(), remoteBestSellers, remoteHomeStores)
    }
}