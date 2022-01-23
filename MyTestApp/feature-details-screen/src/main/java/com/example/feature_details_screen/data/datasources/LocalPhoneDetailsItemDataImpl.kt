package com.example.feature_details_screen.data.datasources

import com.example.feature_details_screen.data.dao.DetailsDao
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem

class LocalPhoneDetailsItemDataImpl(private val detailsDao:DetailsDao):PhoneDetailsSource {
    override suspend fun getPhoneDetailsItem(itemId:String):RemotePhoneDetailsItem?{
        return detailsDao.getDetails(itemId)
    }

    suspend fun savePhoneDetailsItem(details:RemotePhoneDetailsItem){
        detailsDao.addDetails(details)
    }
}