package com.example.feature_cart_screen.data.datasources

import com.example.feature_cart_screen.data.dao.CartDao
import com.example.feature_cart_screen.data.model.Cart
import com.example.feature_cart_screen.data.model.RemoteCart
import com.example.feature_main_screen.data.network.CartApi

class LocalCartDataSourceImpl(private val cartDao: CartDao):CartDataSource {
    override suspend fun getCart(): RemoteCart? {
        val cartWithBasket = cartDao.getCart() ?: return null

        return RemoteCart(
            cartWithBasket.cart.delivery,
            cartWithBasket.cart._id,
            cartWithBasket.basket.map {
                it.toRemoteBasket()
            },
            cartWithBasket.cart.total
        )
    }

    suspend fun saveCart(remoteCart: RemoteCart) {
        cartDao.addCartWithBasketToDB(
            Cart(
                remoteCart.delivery,
                remoteCart._id,
                remoteCart.total
            ),
            remoteCart.basket.map { it.toBasket(remoteCart._id) }
        )
    }
}