package com.skillbox.mytestapp.data

import com.skillbox.mytestapp.data.models.myCard.RemoteCart

interface CartRepository {
    suspend fun loadCart():RemoteCart
}
