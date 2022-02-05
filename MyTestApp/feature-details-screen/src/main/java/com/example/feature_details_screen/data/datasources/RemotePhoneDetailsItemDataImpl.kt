package com.example.feature_details_screen.data.datasources

import com.example.core.utils.exceptions.NoDataException
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsApi
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class RemotePhoneDetailsItemDataImpl(private val detailsApi: DetailsApi):PhoneDetailsSource {
    override fun getPhoneDetailsItem(itemId:String,successCallback:(details:RemotePhoneDetailsItem?)->Unit) {
        detailsApi.getDetails(itemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<RemotePhoneDetailsItem> {
                override fun onSubscribe(d: Disposable) {
                    //TODO("Not yet implemented")
                }

                override fun onSuccess(t: RemotePhoneDetailsItem) {
                    Timber.d("testDetails remote success")
                    successCallback(t)
                }

                override fun onError(e: Throwable) {
                    Timber.d("testDetails remote error")
                    throw NoDataException()
                }

            })

    }
}