package com.example.feature_main_screen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass


@Entity
@JsonClass(generateAdapter = true)
data class RemoteMainScreen(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val _id: String,
    @ColumnInfo(name = "best_seller")
    val best_seller: List<RemoteBestSeller>,
    @ColumnInfo(name = "home_store")
    val home_store: List<RemoteHomeStore>
)