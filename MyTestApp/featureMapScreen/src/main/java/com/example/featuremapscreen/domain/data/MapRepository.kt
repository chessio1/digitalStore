package com.example.featuremapscreen.domain.data

import com.google.android.gms.maps.model.LatLng

interface MapRepository {
    fun getMarkers():List<LatLng>
}