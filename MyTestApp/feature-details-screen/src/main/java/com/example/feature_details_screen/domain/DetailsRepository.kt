package com.example.feature_details_screen.domain

import android.graphics.drawable.Drawable
import com.example.feature_details_screen.data.model.PhoneDetailsItem

interface DetailsRepository {
    suspend fun loadDetails(itemId:Int): PhoneDetailsItem
    suspend fun loadDrawables(imagesUrl:List<String>):List<Drawable>
}
