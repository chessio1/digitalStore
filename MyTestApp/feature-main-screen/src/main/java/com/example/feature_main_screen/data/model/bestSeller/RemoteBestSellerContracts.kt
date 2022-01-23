package com.example.feature_main_screen.data.model.bestSeller

object RemoteBestSellerContracts {
    const val TABLE_NAME = "remote_best_sellers"

    object Columns {
        const val ID = "id"
        const val DISCOUNT_PRICE ="discount_price"
        const val IS_FAVOURITES="is_favourites"
        const val PICTURE="picture"
        const val PRICE_WITHOUT_DISCOUNT="price_without_discount"
        const val TITLE="title"
    }
}