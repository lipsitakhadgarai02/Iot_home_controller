package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConnectingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connecting)

        val tvPercent = findViewById<TextView>(R.id.tvPercent)
        
        // Simulating connection progress
        var progress = 0
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                if (progress <= 100) {
                    tvPercent.text = "$progress%"
                    progress += 10
                    handler.postDelayed(this, 300)
                } else {
                    // Navigate to Dashboard after connection
                    startActivity(Intent(this@ConnectingActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
        handler.post(runnable)
    }
}