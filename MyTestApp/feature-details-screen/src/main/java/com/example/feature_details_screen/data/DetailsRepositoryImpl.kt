package com.example.feature_details_screen.data

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.example.feature_details_screen.data.model.PhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsApi
import com.example.feature_details_screen.domain.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsRepositoryImpl(private val api: DetailsApi, private val context:Context):
    DetailsRepository {

    override suspend fun loadDetails(itemId: Int): PhoneDetailsItem {
        return api.getDetails("61dd5a78d4fd1466000a280a")

    }

    override suspend fun loadDrawables(imagesUrl:List<String>): List<Drawable> {

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