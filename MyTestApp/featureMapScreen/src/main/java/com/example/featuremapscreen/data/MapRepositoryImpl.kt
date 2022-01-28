package com.example.featuremapscreen.data

import com.example.featuremapscreen.domain.data.MapRepository
import com.example.featuremapscreen.domain.data.datasources.MyLastLocationSource
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.LatLng

class MapRepositoryImpl(private val myLastLocationSource: MyLastLocationSource) : MapRepository {
    override fun getMarkers(): List<LatLng> {
        return listOf(
            LatLng(15.16281419860561, -12.465649209916593),
            LatLng(37.41223289716749, -122.0888375490904),
            LatLng(37.41856562326387, -122.08955571055411),
            LatLng(37.430314672368404, -122.08902496844532),
            LatLng(37.42888947353105, -122.0788972824812),
            LatLng(37.41685553907989, -122.08086367696522),
            LatLng(37.41202145087906, -122.08311103284358),
            LatLng(37.422841994220505, -122.08552937954666)
        )
    }

    override suspend fun getMyLastLocation(): LatLng? {
        return myLastLocationSource.getMyLastLocation()
    }
}