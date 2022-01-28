package com.example.featuremapscreen.presentation

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.utils.utils.autoCleared
import com.example.featuremapscreen.R
import com.example.featuremapscreen.databinding.FragmentMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class MapFragment : Fragment(R.layout.fragment_map) {

    private val binding: FragmentMapBinding by viewBinding()
    private var maps by autoCleared<SupportMapFragment>()
    private var myMarker :Marker? = null
    private val vm: MapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.getMarkers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        maps = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        initObservers()
        maps.getMapAsync { map ->
            binding.getMyPositionButton.setOnClickListener {
                askPermissionAndGetMyLastLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            vm.markers.collect { markers ->
                maps.getMapAsync { map ->
                    markers.forEach {
                        map.addMarker(MarkerOptions().apply { position(it) })
                    }
                }
            }

        }
        lifecycleScope.launchWhenCreated {
            vm.myLastLocation.collect { coords ->
                Timber.d("Collected")
                if (coords == null) return@collect
                maps.getMapAsync {
                    if (myMarker != null) myMarker!!.remove()
                    val requestBuilder =
                        BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location)
                    val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                        LatLng(coords.latitude, coords.longitude),
                        15f
                    )
                    it.animateCamera(cameraUpdate)
                    myMarker = it.addMarker(MarkerOptions().icon(requestBuilder).position(coords))!!
                }
            }
        }


    }


    private val askPermissionAndGetMyLastLocation =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                Timber.d("AskPermissionGranted")
                vm.getLastLocation()
            } else {
                Toast.makeText(
                    requireContext(),
                    "permission denied You cannot get your position",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        myMarker = null
    }

}