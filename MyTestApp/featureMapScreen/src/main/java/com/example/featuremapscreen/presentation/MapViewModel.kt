package com.example.featuremapscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.featuremapscreen.domain.usecase.GetMarkerUseCase
import com.example.featuremapscreen.domain.usecase.MyLastLocationUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MapViewModel(
    private val getMarkersUseCase: GetMarkerUseCase,
    private val myLastLocationUseCase: MyLastLocationUseCase,
):ViewModel() {

    private val _markers = MutableStateFlow<List<LatLng>>(listOf())
    val markers = _markers.asStateFlow()

    private val _lastLocation = MutableStateFlow<LatLng?>(null)
    val myLastLocation = _lastLocation.asStateFlow()

    fun getMarkers(){
        _markers.value = getMarkersUseCase.getMarker()
    }

    fun getLastLocation() {
        viewModelScope.launch {
            Timber.d("AskPermission getLastLocation")
            _lastLocation.value = null
            _lastLocation.value = myLastLocationUseCase.getMyLastLocation()
        }
    }

}