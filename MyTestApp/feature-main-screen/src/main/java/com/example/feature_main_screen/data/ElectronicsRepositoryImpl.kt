package com.example.feature_main_screen.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.feature_main_screen.data.dao.MainScreenDao
import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.data.network.HomeApi
import com.example.feature_main_screen.domain.repository.ElectronicsRepository
import timber.log.Timber

class ElectronicsRepositoryImpl(
    private val dbDao: MainScreenDao,
    private val api: HomeApi,
    private val context: Context
    ) :
    ElectronicsRepository {

    private val settings = context.getSharedPreferences("settings",Context.MODE_PRIVATE)

    override suspend fun loadMainScreen(): RemoteMainScreen {

        val mainScreenId = settings.getString(MAIN_SCREEN_ID, "null")

        val mainScreen = if (mainScreenId == null || mainScreenId == "null") {
            Timber.d("start load")
            val mainScreen = getFromNetwork()
            saveMainScreenToDB(mainScreen)
            mainScreen
        } else {
            Timber.d("get from db")
            getMainScreenFromDB(mainScreenId)
        }

        return mainScreen

    }

    private suspend fun getFromNetwork(): RemoteMainScreen {
        return api.getMain().firstOrNull() ?: error("no main screen founded in network")
    }

    override suspend fun searchPhones(query: String) {
        //TODO("Создать запрос телефонов")
    }

    override suspend fun getMainScreenFromDB(mainScreenId: String): RemoteMainScreen {
        return dbDao.getMainScreen(mainScreenId)
    }

    override val some = 1



    private suspend fun saveMainScreenToDB(mainScreen:RemoteMainScreen) {

        val homeStores = mainScreen.home_store.map { remoteHomeStore->
            remoteHomeStore.toHomeStore(mainScreen._id)
        }
        val bestSellers = mainScreen.best_seller.map { remoteBestSeller->
            remoteBestSeller.toBestSeller(mainScreen._id)
        }

        dbDao.insertMainScreenFields(homeStores,bestSellers)
        saveMainScreenId(mainScreen._id)
    }

    private fun saveMainScreenId(mainScreenId: String) {
        settings.edit {
            putString(MAIN_SCREEN_ID, mainScreenId)
            apply()
        }
    }

    private val MAIN_SCREEN_ID = "main_screen_id"

}
