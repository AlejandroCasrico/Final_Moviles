package com.example.pmovil2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonLogin: Button= findViewById(R.id.Final)
        var buttonmapa: Button= findViewById(R.id.map)

        buttonLogin.setOnClickListener(){
            val intent = Intent (this,LoginFinal::class.java).apply{
            }
            startActivity(intent)
        }
        buttonmapa.setOnClickListener(){
            val intent = Intent (this,MapsActivity::class.java).apply{
            }
            startActivity(intent)
        }
    }
}