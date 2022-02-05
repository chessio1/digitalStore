package com.example.feature_details_screen.domain

import android.graphics.drawable.Drawable
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import io.reactivex.Observable

interface DetailsRepository {
   fun loadDetails(itemId: String,successCallback: (details: RemotePhoneDetailsItem) -> Unit)
}
