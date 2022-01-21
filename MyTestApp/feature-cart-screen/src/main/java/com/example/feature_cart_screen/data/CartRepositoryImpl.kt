package com.example.feature_cart_screen.data

import com.example.feature_cart_screen.data.dao.CartDao
import com.example.feature_cart_screen.data.model.Cart
import com.example.feature_cart_screen.domain.repository.CartRepository
import com.example.feature_main_screen.data.network.CartApi
import com.example.feature_cart_screen.data.model.RemoteCart
import timber.log.Timber

class CartRepositoryImpl(private val api: CartApi,private val cartDao: CartDao) : CartRepository {
    override suspend fun loadCart(): RemoteCart {
        val cartFromDB = getCartFromDB()
        return if (cartFromDB==null){
            val remoteCart = getCartFromNetwork()
            saveCartToDB(remoteCart)
            remoteCart
        }else{
            cartFromDB
        }
    }

    private suspend fun getCartFromNetwork():RemoteCart{
        Timber.d("cart didNot Founded in db")
        return api.loadCart().firstOrNull() ?: error("NO cart found in network")
    }

    private suspend fun getCartFromDB():RemoteCart?{
        val cart = cartDao.getCart() ?: return null
        val remoteBasket = cart.basket.map {
            it.toRemoteBasket()
        }
        return RemoteCart(cart.cart.delivery,cart.cart._id, remoteBasket,cart.cart.total)
    }

    private suspend fun saveCartToDB(remoteCart: RemoteCart){
        cartDao.addCartWithBasketToDB(
            Cart(
                remoteCart.delivery,
                remoteCart._id,
                remoteCart.total
            ),
            remoteCart.basket.map {
                it.toBasket(remoteCart._id)
            }
        )
    }
}
