package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class ConnectingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connecting)

        // Fake loading for 2 seconds, then navigate to Devices
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DevicesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }, 2000)
    }
}