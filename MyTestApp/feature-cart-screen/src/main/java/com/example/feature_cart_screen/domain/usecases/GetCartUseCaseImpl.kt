package com.example.feature_cart_screen.domain.usecases

import com.example.feature_cart_screen.data.model.RemoteCart
import com.example.feature_cart_screen.domain.repository.CartRepository

class GetCartUseCaseImpl(private val repository:CartRepository):GetCartUseCase {
    override suspend fun getCart():RemoteCart{
        return repository.getCart()
    }
}
