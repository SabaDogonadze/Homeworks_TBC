package com.example.classwork8real.presentation.map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.classwork8real.R
import com.example.classwork8real.data.common.Resource
import com.example.classwork8real.databinding.FragmentMapBinding
import com.example.classwork8real.domain.MapResponse
import com.example.classwork8real.presentation.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate) {

    private val mapViewModel: MapViewModel by viewModels()
    private lateinit var locationPermissionRequest: ActivityResultLauncher<Array<String>>
    private var googleMap: GoogleMap? = null  // Store GoogleMap instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MapFragment", "onCreate called")
        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
                    Log.d("MapFragment", "Precise location granted")
                    Toast.makeText(requireContext(), "Precise location granted", Toast.LENGTH_SHORT).show()
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                    Log.d("MapFragment", "Approximate location granted")
                    Toast.makeText(requireContext(), "Approximate location granted", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Log.d("MapFragment", "Location permission denied")
                    Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MapFragment", "onViewCreated called")
        setUp()
    }

    override fun setUp() {
        Log.d("MapFragment", "setUp called")
        requestLocationPermissions()
        // Use childFragmentManager to find the map fragment within your fragment's layout
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        if (mapFragment == null) {
            Log.e("MapFragment", "MapFragment is null. Check the fragment id in your layout.")
        }
        mapFragment?.getMapAsync { map ->
            googleMap = map

            map.setOnMarkerClickListener { marker ->
                Log.d("MapFragment", "Marker clicked: ${marker.title}")
                setUpBottomSheet(title =marker.title?:"", address = "", lan = marker.position.longitude.toFloat(), lat = marker.position.latitude.toFloat())
                Toast.makeText(requireContext(), "Marker clicked: ${marker.title}", Toast.LENGTH_SHORT).show()
                true
            }
            fetchLocationsAndAddMarkers()  // Fetch locations and add markers when map is ready
        }

        // Check if location services are enabled; if not, open settings
        if (!isLocationEnabled(requireContext())) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        clickListeners()

    }

    private fun clickListeners(){
        binding.btnZoomIn.setOnClickListener {
            googleMap?.let { map ->
                val currentZoom = map.cameraPosition.zoom
                val newZoom = currentZoom + 1f
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(map.cameraPosition.target, newZoom))
            }
        }

        binding.btnZoomOut.setOnClickListener {
            googleMap?.let { map ->
                val currentZoom = map.cameraPosition.zoom
                val newZoom = currentZoom - 1f
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(map.cameraPosition.target, newZoom))
            }
        }
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            locationManager.isLocationEnabled
        } else {
            val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            gpsEnabled || networkEnabled
        }
    }

    private fun requestLocationPermissions() {
        Log.d("MapFragment", "Requesting location permissions")
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun fetchLocationsAndAddMarkers() {
        Log.d("MapFragment", "Starting to fetch locations")
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mapViewModel.getLocations() // Trigger the Retrofit call
                mapViewModel.locationFlow.collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            resource.dataSuccess?.let { places ->
                                Log.d("MapFragment", "Received ${places.size} locations")

                                addMarkers(places)
                                /*places.forEach { location ->
                                    // Ensure you're using the correct property for longitude (assuming it should be 'lng')
                                    googleMap?.addMarker(
                                        MarkerOptions()
                                            .position(LatLng(location.lat, location.lan))
                                            .title("Marker at ${location.title}, ${location.address}")
                                    )
                                }*/
                            }
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Log.e("MapFragment", "Error fetching locations: ${resource.error}")
                            Toast.makeText(context, "Error: ${resource.error}", Toast.LENGTH_LONG).show()
                        }
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            Log.d("MapFragment", "Loading locations...")
                        }
                        null -> Unit
                    }
                }
            }
        }
    }

    private fun addMarkers(places: List<MapResponse>) {
        googleMap?.let { map ->
            places.forEach { place ->
                // Correcting property name: assuming 'lng' is the correct field for longitude
                val position = LatLng(place.lat, place.lan)
                map.addMarker(
                    MarkerOptions()
                        .title(place.title)
                        .position(position)
                )
            }
            // Optionally, adjust camera to the first marker
            places.firstOrNull()?.let { firstPlace ->
                val firstPosition = LatLng(firstPlace.lat, firstPlace.lan)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(firstPosition, 10f))
            }
        } ?: Log.e("MapFragment", "GoogleMap instance is null; cannot add markers")
    }

    private fun setUpBottomSheet(title:String,address:String,lan:Float,lat:Float){
        findNavController().navigate(MapFragmentDirections.actionMapFragmentToBottomSheetFragment(
            title = title,
            destination = address,
            lan = lan,
            lat = lat
        ))
    }
}


