package com.example.feature_main_screen.data.model.homeStore

object HomeStoreContracts {
    const val TABLE_NAME="home_stores"
    object Columns{
        const val ID = "id"
        const val MAIN_SCREEN_ID = "main_screen_id"
        const val IS_BUY = "is_buy"
        const val IS_FAVOURITES = "is_favourites"
        const val IS_NEW="is_new"
        const val PICTURE="picture"
        const val SUBTITLES="subtitles"
        const val TITLE="title"
    }
}