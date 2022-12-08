package fragments

import HousesModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.pmovil2.R
import com.example.pmovil2.VolleySingleton
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [baseline.newInstance] factory method to
 * create an instance of this fragment.
 */
class BaselineFragment : Fragment() {
    lateinit var  house: HousesModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View= inflater.inflate(R.layout.fragment_baseline, container, false)
        return view
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (childFragmentManager.findFragmentById(
            R.id.maps
        ) as? SupportMapFragment)?.getMapAsync { googleMap ->


            val mMap = googleMap
            val zoomLevel = 13.0f
            val direccion = LatLng(28.622839645061102, -106.11506317116468)
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(direccion,zoomLevel)
            )

            getLocation(mMap)
            print(mMap.isMyLocationEnabled)
            Log.d("valuedhouse", house.toString())
        }
    }



    fun getLocation(map: GoogleMap)  {
        val url = "https://calm-red-coyote-tie.cyclic.app/houses/findProperty"
        house = HousesModel()

        // Post parameters
        // Form fields and values
        val params = HashMap<String,String>()
        params["type"] = ""
        val jsonObject = JSONObject(params as Map<*, *>?)

        // Volley post request with parameters
        val request: JsonObjectRequest = object : JsonObjectRequest(
            Request.Method.POST,url, jsonObject,
            { response ->
                // Process the json
                try {
                    // Toast.makeText(this, "${response["message"]}", Toast.LENGTH_LONG).show()
                    val responseObject = Gson().fromJson(response.toString(), HousesModel::class.java)
                    house = responseObject

                    for (house in responseObject.obj) {
                        map.addMarker(
                            MarkerOptions()
                                .position(LatLng(house.location?.latitude!!, house.location?.longitude!! ))
                                .title(house.houseName.toString())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconb)).anchor(0.0f,0.0f)

                        )

                    }

                } catch (e: Exception) {
                    Log.d("Mi2","${e.message}")

                    Toast.makeText(this.context, "Hubo algún error", Toast.LENGTH_LONG).show()
                }

            }, {
                // Error in request
//                    "Volley error: $it"
                Log.d("Mi2","$it")
            }){
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()

                return headers
            }}



        // Volley request policy, only one time request to
        // avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            2, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this.requireActivity())
            .addToRequestQueue(request)
    }
}