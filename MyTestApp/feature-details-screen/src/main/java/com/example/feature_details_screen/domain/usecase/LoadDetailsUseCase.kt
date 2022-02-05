package com.example.feature_details_screen.domain.usecase

import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import io.reactivex.Observable


interface LoadDetailsUseCase {
    fun loadDetails(detailsId:String,successCallback:(details:RemotePhoneDetailsItem)->Unit)
}