package com.example.featuremapscreen.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.utils.utils.autoCleared
import com.example.featuremapscreen.R
import com.example.featuremapscreen.databinding.FragmentMapBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.flow.collect

import timber.log.Timber

class MapFragment : Fragment(R.layout.fragment_map) {

    private val binding: FragmentMapBinding by viewBinding()
    private var maps by autoCleared<SupportMapFragment>()
    private val vm:MapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.getMarkers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        maps = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        initObservers()
        maps.getMapAsync {map->
            binding.getMyPositionButton.setOnClickListener {
                askPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            vm.markers.collect { markers->
                maps.getMapAsync { map->
                    markers.forEach {
                        map.addMarker(MarkerOptions().apply { position(it) })
                    }
                }
            }
        }

    }


    private val askPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            val permission = result
            if (result) {
                Timber.d("permission accepted")
                getLastLocation()
            } else {
                Timber.d("permission denied")
            }
        }


    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LocationServices.getFusedLocationProviderClient(requireContext())
                .lastLocation
                .addOnSuccessListener {
                    it?.let { location ->
                        maps.getMapAsync {
                            val latLng = LatLng(location.latitude, location.longitude)
                            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,15f)
                            it.animateCamera(cameraUpdate)
                        }
                    } ?: Toast.makeText(
                        requireContext(),
                        "No last location founded",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnCanceledListener {
                    Toast.makeText(requireContext(), "get location cancelled", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "get location failed", Toast.LENGTH_SHORT).show()
                }
        } else {
            askPermission
        }
    }


}