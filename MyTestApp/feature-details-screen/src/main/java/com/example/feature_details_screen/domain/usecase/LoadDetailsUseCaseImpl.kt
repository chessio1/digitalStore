package com.example.feature_details_screen.domain.usecase

import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsRepository

class LoadDetailsUseCaseImpl(private val repository:DetailsRepository):LoadDetailsUseCase {
    override suspend fun loadDetails(detailsId:String):RemotePhoneDetailsItem{
        val details = repository.loadDetails(detailsId)
        val images = listOf<String>(
            "https://www.ixbt.com/mobile/images/samsung-s5/foto/sgs5-0101.jpg",
            "https://mobile-review.com/review/image/samsung/galaxy-s5/color1.jpg",
            details.images[0],
            "https://tech-today.ru/wp-content/uploads/2014/10/01_problemy_samsung_galaxy_s5.jpg",
            "https://ae04.alicdn.com/kf/HTB1I89ycnJYBeNjy1zeq6yhzVXaZ.jpg"
        )
        return details.copy(images = images)
    }
}