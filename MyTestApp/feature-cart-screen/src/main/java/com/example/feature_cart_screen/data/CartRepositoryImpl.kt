package com.example.feature_cart_screen.data

import com.example.feature_cart_screen.domain.repository.CartRepository
import com.example.feature_main_screen.data.network.CartApi
import com.example.feature_cart_screen.data.model.RemoteCart
import java.lang.NullPointerException

class CartRepositoryImpl(private val api: CartApi) : CartRepository {
    override suspend fun loadCart(): RemoteCart {
        try {
            return api.loadCart().first()
        }catch (t:NullPointerException){
            error("NoData")
        }

    }
}
