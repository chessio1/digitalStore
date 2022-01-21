package com.example.feature_details_screen.data.model

import com.squareup.moshi.Json

object RemotePhoneDetailsItemContracts {
    const val TABLE_NAME = "phone_details_items"
    object Columns {
        const val CPU = "cpu"
        const val ID = "id"
        const val CAMERA = "camera"
        const val CAPACITY = "capacity"
        const val COLOR = "color"
        const val IMAGES = "images"
        const val IS_FAVOURITES = "is_favourites"
        const val PRICE = "price"
        const val RATING = "rating"
        const val SD = "sd"
        const val SSD = "ssd"
        const val TITLE = "title"
    }
}

