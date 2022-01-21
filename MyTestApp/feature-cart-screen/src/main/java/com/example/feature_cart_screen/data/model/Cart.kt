package com.example.feature_cart_screen.data.model

import androidx.room.*

@Entity(tableName = CartContracts.TABLE_NAME)
data class Cart (
    @ColumnInfo(name = CartContracts.Columns.DELIVERY)
    val delivery: String,
    @PrimaryKey
    @ColumnInfo(name = CartContracts.Columns.ID)
    val _id: String,
    @ColumnInfo(name = CartContracts.Columns.TOTAL)
    val total: Int
    )

