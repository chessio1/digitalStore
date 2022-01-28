package com.example.featuremapscreen.domain.usecase

import com.google.android.gms.maps.model.LatLng

interface MyLastLocationUseCase {
    suspend fun getMyLastLocation():LatLng?
}