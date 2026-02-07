package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DevicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        findViewById<ImageView>(R.id.ivBack).setOnClickListener { finish() }

        // Click on "+" icon to add a device
        findViewById<ImageView>(R.id.ivAddDevice).setOnClickListener {
            startActivity(Intent(this, AddDeviceActivity::class.java))
        }

        // Navigation
        findViewById<ImageView>(R.id.nav_home).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}