package com.example.smart_home_iot_controller

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RoomDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room_details)
        
        findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            finish()
        }
    }
}