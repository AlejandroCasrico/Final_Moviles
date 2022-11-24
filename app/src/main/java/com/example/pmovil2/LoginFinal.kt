package com.example.pmovil2


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject


class LoginFinal : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var edtuserName: EditText
    private lateinit var edtPassword: EditText
    private lateinit var txtError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_final)
        val dialogView = layoutInflater.inflate(R.layout.errodialoglogin, null)
        val dialog = AlertDialog.Builder(this).setView(dialogView).setTitle(" ").create()

        edtuserName = findViewById(R.id.edtTxtNameLogin)
        edtPassword = findViewById(R.id.editTextTextPassword)
        btnLogin = findViewById(R.id.buttonL)
        txtError =  dialogView.findViewById(R.id.txtErrorLogin)
        btnLogin.setOnClickListener {


            // Disable the button itself
            it.isEnabled = false

            val url = "https://calm-red-coyote-tie.cyclic.app/users/login"

            // Post parameters
            // Form fields and values
            val params = HashMap<String,String>()
            params["User"] = edtuserName.text.toString()
            params["pass"] = edtPassword.text.toString()
            val jsonObject = JSONObject(params as Map<*, *>?)

            // Volley post request with parameters
            val request = JsonObjectRequest(
                Request.Method.POST,url,jsonObject,
                { response ->
                    // Process the json
                    try {
                        val intent= Intent(this,MainActivity::class.java).apply {  }
                        startActivity(intent)
                        Log.d("hola", "${response["message"]}")
                        Toast.makeText(this, "${response["message"]}", Toast.LENGTH_LONG).show()


                    } catch (e: Exception) {
                        Toast.makeText(this, "Hubo algún error", Toast.LENGTH_LONG).show()
                    }

                }, {
                    // Error in request
//                    "Volley error: $it"
//                    Log.d("Mi2",)
                    if (it.networkResponse.statusCode == 400) {
                        txtError.text = getString(R.string.userPasswordError)

                    }else {
                        txtError.text = getString(R.string.serverError)

                    }
                    dialog.show()
                    btnLogin.isEnabled = true
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
}