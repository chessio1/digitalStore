package com.example.feature_details_screen.data.datasources

import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem

interface PhoneDetailsSource {
    suspend fun getPhoneDetailsItem(itemId:String):RemotePhoneDetailsItem?
}