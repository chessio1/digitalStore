package com.skillbox.mytestapp.data

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.Carousel
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.skillbox.mytestapp.data.models.details.PhoneDetailsItem
import com.skillbox.mytestapp.network.PhoneApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import timber.log.Timber

class DetailsRepositoryImpl(private val api: PhoneApi,private val context:Context): DetailsRepository {

    override suspend fun loadDetails(itemId: Int): PhoneDetailsItem {
        return api.getDetails("612a529bd943be7d000794b6")
    }

    override suspend fun loadDrawables(imagesUrl:List<String>): List<Drawable> {
        Timber.d("ts $imagesUrl")

        val images = listOf<String>(
            "https://www.ixbt.com/mobile/images/samsung-s5/foto/sgs5-0101.jpg",
            "https://mobile-review.com/review/image/samsung/galaxy-s5/color1.jpg",
            imagesUrl[0],
            "https://tech-today.ru/wp-content/uploads/2014/10/01_problemy_samsung_galaxy_s5.jpg",
            "https://ae04.alicdn.com/kf/HTB1I89ycnJYBeNjy1zeq6yhzVXaZ.jpg"
        )

        return withContext(Dispatchers.IO) {
            images.map {
                Glide.with(context).load(it).submit().get()
            }
        }
    }
}