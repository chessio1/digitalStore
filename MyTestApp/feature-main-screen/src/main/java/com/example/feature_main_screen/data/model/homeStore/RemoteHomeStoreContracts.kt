package com.example.feature_main_screen.data.model.homeStore

object RemoteHomeStoreContracts {
    const val TABLE_NAME="remote_home_stores"
    object Columns{
        const val ID = "id"
        const val IS_BUY = "is_buy"
        const val IS_FAVOURITES = "is_favourites"
        const val IS_NEW="is_new"
        const val PICTURE="picture"
        const val SUBTITLES="subtitles"
        const val TITLE="title"
    }
}