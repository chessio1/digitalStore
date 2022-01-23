package com.example.feature_cart_screen.domain.usecases

import com.example.feature_cart_screen.data.model.RemoteCart

interface GetCartUseCase {
    suspend fun getCart():RemoteCart
}
