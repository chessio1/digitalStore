package com.skillbox.mytestapp.data

import android.content.Context
import com.skillbox.mytestapp.data.models.myCard.RemoteCart
import com.skillbox.mytestapp.network.PhoneApi
import timber.log.Timber
import java.lang.NullPointerException

class CartRepositoryImpl(private val api: PhoneApi) : CartRepository {
    override suspend fun loadCart():RemoteCart {
        try {
            return api.loadCart().first()
        }catch (t:NullPointerException){
            error("NoData")
        }

    }
}
