package com.example.featuremapscreen.domain

import com.example.featuremapscreen.domain.data.MapRepository
import com.example.featuremapscreen.domain.usecase.GetMarkerUseCase
import com.google.android.gms.maps.model.LatLng

class GetMarkerUseCaseImpl(private val repository: MapRepository):GetMarkerUseCase {
    override fun getMarker():List<LatLng>{
        return repository.getMarkers()
    }
}
