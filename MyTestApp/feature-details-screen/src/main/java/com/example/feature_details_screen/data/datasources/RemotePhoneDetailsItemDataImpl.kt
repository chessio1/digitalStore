package com.example.feature_details_screen.data.datasources

import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsApi

class RemotePhoneDetailsItemDataImpl(private val detailsApi: DetailsApi):PhoneDetailsSource {
    override suspend fun getPhoneDetailsItem(itemId:String):RemotePhoneDetailsItem?{
        return detailsApi.getDetails(itemId)
    }
}