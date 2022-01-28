package com.example.featuremapscreen.data

import com.example.featuremapscreen.domain.data.MapRepository
import com.example.featuremapscreen.domain.usecase.MyLastLocationUseCase
import com.google.android.gms.maps.model.LatLng

class MyLastLocationUseCaseImpl(private val repository: MapRepository): MyLastLocationUseCase {
    override suspend fun getMyLastLocation():LatLng?{
        return repository.getMyLastLocation()
    }
}