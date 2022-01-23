package com.example.feature_details_screen.data

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.example.core.utils.exceptions.NoDataException
import com.example.feature_details_screen.data.dao.DetailsDao
import com.example.feature_details_screen.data.datasources.LocalPhoneDetailsItemDataImpl
import com.example.feature_details_screen.data.datasources.PhoneDetailsSource
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsApi
import com.example.feature_details_screen.domain.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsRepositoryImpl(
    private val localDetailsSource:PhoneDetailsSource,
    private val remoteDetailsSource:PhoneDetailsSource
) : DetailsRepository {

    override suspend fun loadDetails(itemId: String): RemotePhoneDetailsItem {
        return localDetailsSource.getPhoneDetailsItem("61dd5a78d4fd1466000a280a")
            ?: remoteDetailsSource.getPhoneDetailsItem("61dd5a78d4fd1466000a280a")?.also {
                (localDetailsSource as LocalPhoneDetailsItemDataImpl).savePhoneDetailsItem(it)
            }
            ?: throw NoDataException()

    }

}