package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Using activity_main which now contains the dashboard design
        setContentView(R.layout.activity_main)
        
        val mainView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to Room Details when clicking on the climate card
        findViewById<MaterialCardView>(R.id.cardClimateSummary).setOnClickListener {
            val intent = Intent(this, RoomDetailsActivity::class.java)
            startActivity(intent)
        }
        
        // Navigate to AC Control when clicking on Greeting
        findViewById<TextView>(R.id.tvGreeting).setOnClickListener {
            val intent = Intent(this, AcControlActivity::class.java)
            startActivity(intent)
        }
    }
}