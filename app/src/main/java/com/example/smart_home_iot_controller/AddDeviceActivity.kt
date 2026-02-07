package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AddDeviceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        // Back navigation
        findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            finish()
        }

        // Navigate to Connecting screen on button click
        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            startActivity(Intent(this, ConnectingActivity::class.java))
        }

        // Placeholder for QR Scan click
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardScanQr).setOnClickListener {
            startActivity(Intent(this, ConnectingActivity::class.java))
        }
    }
}