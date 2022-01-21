package com.example.feature_details_screen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = RemotePhoneDetailsItemContracts.TABLE_NAME)
@TypeConverters(
    RemotePhoneDetailsConverters::class
)
@JsonClass(generateAdapter = true)
data class RemotePhoneDetailsItem(
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.CPU)
    val CPU: String,
    @PrimaryKey
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.ID)
    val _id: String,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.CAMERA)
    val camera: String,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.CAPACITY)
    val capacity: List<String>,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.COLOR)
    val color: List<String>,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.IMAGES)
    val images: List<String>,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.IS_FAVOURITES)
    @Json(name = "isFavorites")
    val is_favorites: Boolean,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.PRICE)
    val price: Int,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.RATING)
    val rating: Double,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.SD)
    val sd: String,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.SSD)
    val ssd: String,
    @ColumnInfo(name = RemotePhoneDetailsItemContracts.Columns.TITLE)
    val title: String
)