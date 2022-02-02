package com.example.feature_main_screen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_main_screen.data.model.homeStore.RemoteHomeStoreContracts
import com.squareup.moshi.JsonClass

@Entity(tableName = RemoteHomeStoreContracts.TABLE_NAME)
@JsonClass(generateAdapter = true)
data class RemoteHomeStore(
    @PrimaryKey
    @ColumnInfo(name = RemoteHomeStoreContracts.Columns.ID)
    val id: Int,
    @ColumnInfo(name = RemoteHomeStoreContracts.Columns.IS_BUY)
    val is_buy: Boolean,
    @ColumnInfo(name = RemoteHomeStoreContracts.Columns.IS_FAVOURITES)
    val is_favorites: Boolean?,
    @ColumnInfo(name = RemoteHomeStoreContracts.Columns.IS_NEW)
    val is_new: Boolean?,
    @ColumnInfo(name = RemoteHomeStoreContracts.Columns.PICTURE)
    val picture: String,
    @ColumnInfo(name = RemoteHomeStoreContracts.Columns.SUBTITLES)
    val subtitle: String?,
    @ColumnInfo(name = RemoteHomeStoreContracts.Columns.TITLE)
    val title: String
):ListItem()