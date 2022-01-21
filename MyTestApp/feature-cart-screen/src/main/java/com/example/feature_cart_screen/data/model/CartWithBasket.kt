package com.example.feature_cart_screen.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CartWithBasket (
    @Embedded val cart:Cart,
    @Relation(
        parentColumn = CartContracts.Columns.ID,
        entityColumn = BasketContracts.Columns.CART_OWNER_ID
    )
    val basket :List<Basket>,
)