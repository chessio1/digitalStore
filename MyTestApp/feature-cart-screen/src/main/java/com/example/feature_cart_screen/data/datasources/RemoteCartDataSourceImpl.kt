package com.example.feature_cart_screen.data.datasources

import com.example.feature_cart_screen.data.model.RemoteCart
import com.example.feature_main_screen.data.network.CartApi

class RemoteCartDataSourceImpl(private val cartApi: CartApi) : CartDataSource {
    override suspend fun getCart(): RemoteCart? {
        return cartApi.loadCart().firstOrNull()
    }
}