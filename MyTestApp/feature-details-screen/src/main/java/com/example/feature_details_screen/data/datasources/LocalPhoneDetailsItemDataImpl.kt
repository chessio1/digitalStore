package com.example.feature_details_screen.data.datasources

import com.example.feature_details_screen.data.dao.DetailsDao
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class LocalPhoneDetailsItemDataImpl(private val detailsDao: DetailsDao) : PhoneDetailsSource {

    override fun getPhoneDetailsItem(itemId: String,successCallback:(details:RemotePhoneDetailsItem?)->Unit) {
        return detailsDao.getDetails(itemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<RemotePhoneDetailsItem> {
                override fun onSubscribe(d: Disposable) {
                    //TODO("Not yet implemented")
                }

                override fun onSuccess(t: RemotePhoneDetailsItem) {
                    Timber.d("testDetails local success")
                    successCallback(t)
                }

                override fun onError(e: Throwable) {
                    Timber.d("testDetails local error")
                    successCallback(null)
                }
            })
    }

    fun saveDetailsItem(details: RemotePhoneDetailsItem) {
        Completable.fromAction(object : Action {
            override fun run() {
                detailsDao.addDetails(details)
            }
        }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    //TODO("Not yet implemented")
                }

                override fun onComplete() {
                    Timber.d("testSave SAVED")
                }

                override fun onError(e: Throwable) {
                    Timber.d("testSave NOT SAVED $e")
                }

            })

    }


}