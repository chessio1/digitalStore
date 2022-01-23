package com.example.feature_cart_screen.data

import com.example.core.utils.exceptions.NoDataException
import com.example.feature_cart_screen.data.dao.CartDao
import com.example.feature_cart_screen.data.datasources.CartDataSource
import com.example.feature_cart_screen.data.datasources.LocalCartDataSourceImpl
import com.example.feature_cart_screen.data.model.Cart
import com.example.feature_cart_screen.domain.repository.CartRepository
import com.example.feature_main_screen.data.network.CartApi
import com.example.feature_cart_screen.data.model.RemoteCart
import timber.log.Timber

class CartRepositoryImpl(
    private val remoteCartDataSource: CartDataSource,
    private val localCartDataSource: CartDataSource
    ) : CartRepository {
    override suspend fun getCart(): RemoteCart {
        return localCartDataSource.getCart()
            ?: remoteCartDataSource.getCart()?.also {
                (localCartDataSource as LocalCartDataSourceImpl).saveCart(it)
            } ?: throw NoDataException()
    }
}
