package com.skillbox.mytestapp.data

import android.graphics.drawable.Drawable
import com.skillbox.mytestapp.data.models.details.PhoneDetailsItem

interface DetailsRepository {
    suspend fun loadDetails(itemId:Int):PhoneDetailsItem
    suspend fun loadDrawables(imagesUrl:List<String>):List<Drawable>
}
