package com.example.featuremapscreen.presentation

import androidx.lifecycle.ViewModel
import com.example.featuremapscreen.domain.usecase.GetMarkerUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapViewModel(private val getMarkersUseCase: GetMarkerUseCase):ViewModel() {

    private val _markers = MutableStateFlow<List<LatLng>>(listOf())
    val markers = _markers.asStateFlow()

    fun getMarkers(){
        _markers.value = getMarkersUseCase.getMarker()
    }

}