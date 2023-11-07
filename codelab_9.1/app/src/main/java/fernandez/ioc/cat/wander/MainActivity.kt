package fernandez.ioc.cat.wander

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import fernandez.ioc.cat.wander.databinding.ActivityMapsBinding
import java.util.Locale

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, mapFragment).commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val lat = 41.86436
        val lng = 3.16234
        val home = LatLng(lat, lng)
        val zoom = 15F
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, zoom))

        val homeOverlay =
            GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.android))
                .position(home, 100F)

        mMap.addGroundOverlay(homeOverlay)

        mMap.addMarker(
            MarkerOptions().position(home).title("Marker in Home")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )
        setMapLongClick(mMap)
        setPoiClick(mMap)
        setInfoWindowClickToPanorama(mMap)
        enableMyLocation()

        try {
            val success =
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))

            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: $e")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.normal_map -> mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.hybrid_map -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            R.id.satellite_map -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            R.id.terrain_map -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMapLongClick(map: GoogleMap) {
        mMap.setOnMapLongClickListener {
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                it.latitude,
                it.longitude
            )

            map.addMarker(
                MarkerOptions()
                    .position(it)
                    .title(getString(R.string.dropped_pin))
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
    }


    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener {
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(it.latLng)
                    .title(it.name)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
            poiMarker?.tag = "poi"
            poiMarker?.showInfoWindow()
        }
    }


    private fun enableMyLocation() {

        // Versio >= M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
                || checkSelfPermission(ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED
            ) {
                mMap.isMyLocationEnabled = true

            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION), 1
                )
            }
            // Versions inferiors a M
        } else {
            if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) ==
                PERMISSION_GRANTED
            ) {
                mMap.isMyLocationEnabled = true
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        ACCESS_COARSE_LOCATION,
                        ACCESS_FINE_LOCATION
                    ), 1
                )
            }
        }
    }

    private fun setInfoWindowClickToPanorama(map: GoogleMap) {
        map.setOnInfoWindowClickListener {
            if (it.tag == "poi") {
                val options = StreetViewPanoramaOptions().position(it.position)
                val streetViewFragment =
                    SupportStreetViewPanoramaFragment.newInstance(options)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, streetViewFragment)
                    .addToBackStack(null).commit()
            }
        }
    }
}