package com.example.featuremapscreen.data.datasources

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.core.utils.exceptions.NoLastPositionException
import com.example.featuremapscreen.domain.data.datasources.MyLastLocationSource
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.suspendCancellableCoroutine

class MyLastLocationSourceImpl(private val context: Context):MyLastLocationSource {
    override suspend fun getMyLastLocation(): LatLng? {
        if (!checkPermission()) return null
        return suspendCancellableCoroutine { coroutine ->
            LocationServices.getFusedLocationProviderClient(context)
                .lastLocation
                .addOnSuccessListener {nullableLocation->
                    nullableLocation?.let { location ->
                       coroutine.resumeWith(Result.success(LatLng(location.latitude, location.longitude)))
                    } ?: toast("No last location founded")
                }
                .addOnCanceledListener {
                    toast("get location cancelled")
                    coroutine.cancel()
                }
                .addOnFailureListener {
                    toast("get location failed")
                    coroutine.resumeWith(Result.failure(NoLastPositionException()))
                }
        }


    }

    private fun toast(toastText:String) {
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT)
            .show()
    }

    private fun checkPermission():Boolean{
        return ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
    }
}