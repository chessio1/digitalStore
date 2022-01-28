package com.example.featuremapscreen.domain.data.datasources

import com.google.android.gms.maps.model.LatLng

interface MyLastLocationSource {
    suspend fun getMyLastLocation(): LatLng?
}