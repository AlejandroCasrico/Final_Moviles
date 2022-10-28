package com.example.pmovil2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

import org.json.JSONObject



class LoginFinal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_final)

        val button = findViewById<Button>(R.id.buttonL)
        val textView = findViewById<TextView>(R.id.textView1)

        textView.movementMethod = ScrollingMovementMethod()

        button.setOnClickListener {
            // Disable the button itself
            it.isEnabled = false

            val url = "https://postman-echo.com/post"
            textView.text = ""

            // Post parameters
            // Form fields and values
            val params = HashMap<String,String>()
            params["foo1"] = "bar1"
            params["foo2"] = "bar2"
            val jsonObject = JSONObject(params as Map<*, *>?)

            // Volley post request with parameters
            val request = JsonObjectRequest(
                Request.Method.POST,url,jsonObject,
                { response ->
                    // Process the json
                    try {
                        textView.text = "Response: $response"
                    } catch (e: Exception) {
                        textView.text = "Exception: $e"
                    }

                }, {
                    // Error in request
                    textView.text = "Volley error: $it"
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
//            VolleySingleton.getInstance(this)
//                .addToRequestQueue(request)
        }
    }
}