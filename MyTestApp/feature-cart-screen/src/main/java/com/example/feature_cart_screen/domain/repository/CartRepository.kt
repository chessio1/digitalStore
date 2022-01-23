package com.example.feature_cart_screen.domain.repository

import com.example.feature_cart_screen.data.model.RemoteCart

interface CartRepository {
    suspend fun getCart(): RemoteCart
}
