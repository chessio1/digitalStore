package com.example.feature_main_screen.data.model

import androidx.annotation.DrawableRes

data class DeviceSelectionItem(
    val id:Int,
    val name:String,
    val isSelected:Boolean,
    @DrawableRes
    val picture:Int
):ListItem()