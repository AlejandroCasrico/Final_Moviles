package com.example.pmovil2

import HousesModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import org.json.JSONObject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var  houses : HousesModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
        getLocation()
    }
    override fun onMapReady(googleMap: GoogleMap) {
        val mMap = googleMap
        val zoomLevel = 1.0f
        val direccion = LatLng(28.622839645061102, -106.11506317116468)
        print(mMap.isMyLocationEnabled)
        for(i in houses.obj){
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(i.location?.latitude!!, i.location?.longitude!! ))
                    .title("Marker")
            )
        }
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(direccion,zoomLevel)
        )
    }
    fun getLocation() {
        val url = "https://calm-red-coyote-tie.cyclic.app/houses/findProperty"

        // Post parameters
        // Form fields and values
        val params = HashMap<String,String>()
        params["type"] = ""
        val jsonObject = JSONObject(params as Map<*, *>?)

        // Volley post request with parameters
        val request = JsonObjectRequest(
            Request.Method.POST,url, jsonObject,
            { response ->
                // Process the json
                try {
                    // Toast.makeText(this, "${response["message"]}", Toast.LENGTH_LONG).show()
                    val responseObject = Gson().fromJson(response.toString(), HousesModel::class.java)
                    houses = responseObject
                    Log.d("value", responseObject.message.toString())
                    Log.d("value", responseObject.obj[0].location?.latitude.toString())
                    Log.d("value", responseObject.obj[0].location?.longitude.toString())
                    Log.d("value", responseObject.obj[0].houseName.toString())

                    for (house in responseObject.obj) {
                        Log.d("value",house.location?.latitude.toString())
                        Log.d("value",house.location?.longitude.toString())
                    }

                } catch (e: Exception) {
                    Log.d("Mi2","${e.message}")

                    Toast.makeText(this, "Hubo algún error", Toast.LENGTH_LONG).show()
                }

            }, {
                // Error in request
//                    "Volley error: $it"
                Log.d("Mi2","$it")
            })


        // Volley request policy, only one time request to
        // avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this)
            .addToRequestQueue(request)
    }



}