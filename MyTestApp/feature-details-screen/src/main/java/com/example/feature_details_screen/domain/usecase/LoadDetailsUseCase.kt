package com.example.feature_details_screen.domain.usecase

import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem

interface LoadDetailsUseCase {
    suspend fun loadDetails(detailsId:String):RemotePhoneDetailsItem
}