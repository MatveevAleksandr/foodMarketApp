package com.example.foodmarketapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.nav_view)
        val bottomNavController =
            Navigation.findNavController(activity = this, viewId = R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavView, bottomNavController)
        supportActionBar?.hide()
    }
}