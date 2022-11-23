package com.example.pmovil2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.BaselineFragment
import fragments.InfoFragment
import fragments.SettingFragment

class MainActivity : AppCompatActivity() {
    private val infoFragment= InfoFragment()
    private val settingFragment= SettingFragment()
    private val baselineFragment= BaselineFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(infoFragment)
        var bottom_navigation :BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener(){
            val sharedPreference = getSharedPreferences("pref", Context.MODE_PRIVATE)
            sharedPreference.getString("username","defaultName")
            Log.d("shared",sharedPreference.getString("username","defaultName").toString())
            when(it.itemId){
                R.id.ic_info -> replaceFragment(infoFragment)
                R.id.ic_settin -> replaceFragment(settingFragment)
                R.id.ic_baseline_cottage_24 -> replaceFragment(baselineFragment)
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        if (fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }
}