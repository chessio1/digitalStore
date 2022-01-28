package com.example.feature_main_screen.data

import com.example.core.utils.exceptions.NoDataException
import com.example.feature_main_screen.data.datasources.*
import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.domain.repository.ElectronicsRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber


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

    override suspend fun getToken() {
        val token = suspendCancellableCoroutine<String?> { coroutione ->
            FirebaseMessaging.getInstance().token.addOnSuccessListener {
                coroutione.resumeWith(Result.success(it))
            }.addOnFailureListener {
                coroutione.resumeWith(Result.failure(NoDataException()))
            }
        }
        Timber.d("$token")
    }
}
