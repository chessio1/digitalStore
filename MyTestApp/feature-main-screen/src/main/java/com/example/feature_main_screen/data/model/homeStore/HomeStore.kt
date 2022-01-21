package com.example.feature_main_screen.data.model.homeStore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_main_screen.data.model.RemoteHomeStore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = HomeStoreContracts.TABLE_NAME)
data class HomeStore(
    @ColumnInfo(name = HomeStoreContracts.Columns.MAIN_SCREEN_ID)
    val main_screen_id: String,
    @PrimaryKey
    @ColumnInfo(name = HomeStoreContracts.Columns.ID)
    val id: Int,
    @ColumnInfo(name = HomeStoreContracts.Columns.IS_BUY)
    val is_buy: Boolean,
    @ColumnInfo(name = HomeStoreContracts.Columns.IS_FAVOURITES)
    val is_favorites: Boolean?,
    @ColumnInfo(name = HomeStoreContracts.Columns.IS_NEW)
    val is_new: Boolean?,
    @ColumnInfo(name = HomeStoreContracts.Columns.PICTURE)
    val picture: String,
    @ColumnInfo(name = HomeStoreContracts.Columns.SUBTITLES)
    val subtitle: String?,
    @ColumnInfo(name = HomeStoreContracts.Columns.TITLE)
    val title: String
) {
    fun toRemoteHomeStore(): RemoteHomeStore {
        return RemoteHomeStore(
            id,
            is_buy,
            is_favorites,
            is_new,
            picture,
            subtitle,
            title
        )
    }
}