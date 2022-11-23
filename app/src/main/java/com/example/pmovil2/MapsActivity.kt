package com.example.pmovil2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        val mMap = googleMap
        val zoomLevel = 1.0f
        val direccion = LatLng(28.622839645061102, -106.11506317116468)
        print(mMap.isMyLocationEnabled)
        for(i in 0..2){
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(28.622839645061102 +i, -106.11506317116468 +i))
                    .title("Marker")
            )
        }
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(direccion,zoomLevel)
        )
    }



}