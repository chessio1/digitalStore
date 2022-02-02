package com.example.feature_main_screen.data

import com.example.core.utils.exceptions.NoDataException
import com.example.feature_main_screen.R
import com.example.feature_main_screen.data.datasources.*
import com.example.feature_main_screen.data.model.DeviceSelectionItem
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

    override fun getDeviceList(): List<DeviceSelectionItem> {
        return listOf<DeviceSelectionItem>(
            DeviceSelectionItem(
                id = 132341,
                name = "phones",
                isSelected = true,
                R.drawable.ic_phones
            ),
            DeviceSelectionItem(
                id = 132342,
                name = "desctops",
                isSelected = false,
                R.drawable.ic_desctops
            ),
            DeviceSelectionItem(
                id = 132343,
                name = "health",
                isSelected = false,
                R.drawable.ic_health_devices
            ),
            DeviceSelectionItem(
                id = 132344,
                name = "books",
                isSelected = false,
                R.drawable.ic_books
            ),
            DeviceSelectionItem(
                id = 132345,
                name = "tools",
                isSelected = false,
                R.drawable.ic_phones
            )
        )
    }
}
