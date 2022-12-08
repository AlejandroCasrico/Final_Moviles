package fragments


import HousesModel
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.pmovil2.R
import com.example.pmovil2.VolleySingleton
import com.google.gson.Gson
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [info.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var  house: HousesModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_info, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        val lvData = view.findViewById<ListView>(R.id.lvDatos)
        getHouses(lvData, view)
        swipe.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                Log.d("reloading", "hola")
                getHouses(lvData, view)
                swipe.isRefreshing =false
            },2000)

        }
    }
    fun getHouses( layout :ListView, view: View)  {
        val url = "https://calm-red-coyote-tie.cyclic.app/houses/findProperty"
        house = HousesModel()

        // Post parameters
        // Form fields and values
        var arrayAdapter: ArrayAdapter<*>
        var houses = mutableListOf("")
        // Post parameters
        // Form fields and values
        val params = HashMap<String,String>()
        params[""] = ""
        val jsonObject = JSONObject(params as Map<*, *>?)

        // Volley post request with parameters
        val request = JsonObjectRequest(
            Request.Method.POST,url, jsonObject,
            { response ->
                // Process the json
                try {
                    // Toast.makeText(this, "${response["message"]}", Toast.LENGTH_LONG).show()
                    val responseObject = Gson().fromJson(response.toString(), HousesModel::class.java)
                    house = responseObject

                    for (house in responseObject.obj) {
                        houses.add(house.houseName.toString())

                    }
                    arrayAdapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, houses)
                    layout.adapter = arrayAdapter


                } catch (e: Exception) {
                    Log.d("Mi2","${e.message}")

                    Toast.makeText(this.context, "Ocurrio algún error", Toast.LENGTH_LONG).show()
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
        VolleySingleton.getInstance(this.requireActivity())
            .addToRequestQueue(request)

    }
}

