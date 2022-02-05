package com.example.feature_details_screen.domain.usecase

import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsRepository
import io.reactivex.Observable

class LoadDetailsUseCaseImpl(private val repository:DetailsRepository):LoadDetailsUseCase {
    override fun loadDetails(detailsId:String,successCallback:(details:RemotePhoneDetailsItem)->Unit){
        repository.loadDetails(detailsId){
            successCallback(it)
        }
    }
}