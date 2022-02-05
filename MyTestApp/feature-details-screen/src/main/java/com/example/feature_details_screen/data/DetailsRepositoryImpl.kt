package com.example.feature_details_screen.data

import com.example.core.utils.exceptions.NoDataException
import com.example.feature_details_screen.data.datasources.LocalPhoneDetailsItemDataImpl
import com.example.feature_details_screen.data.datasources.PhoneDetailsSource
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsRepository
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DetailsRepositoryImpl(
    private val localDetailsSource: PhoneDetailsSource,
    private val remoteDetailsSource: PhoneDetailsSource
) : DetailsRepository {

    override fun loadDetails(
        itemId: String,
        successCallback: (details: RemotePhoneDetailsItem) -> Unit
    ) { Timber.d("HEY1")
        localDetailsSource.getPhoneDetailsItem(itemId){localDetails->
            if (localDetails!=null){successCallback(
               adjustDetails(localDetails)
            )}
            else {remoteDetailsSource.getPhoneDetailsItem(itemId){remoteDetails->
                if (remoteDetails!=null){
                    (localDetailsSource as LocalPhoneDetailsItemDataImpl)
                        .saveDetailsItem(adjustDetails(remoteDetails) )
                    successCallback(adjustDetails(remoteDetails) )
                }
            }}
        }
    }


    private fun adjustDetails(details: RemotePhoneDetailsItem): RemotePhoneDetailsItem {
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