package com.example.feature_details_screen.domain

import android.graphics.drawable.Drawable
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem

interface DetailsRepository {
    suspend fun loadDetails(itemId:String): RemotePhoneDetailsItem
}
