package com.example.feature_details_screen.data.datasources

import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import io.reactivex.Maybe
import io.reactivex.Single

interface PhoneDetailsSource {
    fun getPhoneDetailsItem(
        itemId:String,
        successCallback:(details:RemotePhoneDetailsItem?)->Unit
    )
}