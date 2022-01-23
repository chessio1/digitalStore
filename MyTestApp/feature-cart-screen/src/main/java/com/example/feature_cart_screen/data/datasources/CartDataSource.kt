package com.example.feature_cart_screen.data.datasources

import com.example.feature_cart_screen.data.model.RemoteCart

interface CartDataSource {
    suspend fun getCart():RemoteCart?
}