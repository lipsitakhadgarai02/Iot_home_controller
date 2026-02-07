package com.example.smart_home_iot_controller

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AutomationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automation)
        
        // Simple back navigation
        findViewById<ImageView>(R.id.ivBack)?.setOnClickListener { finish() }
    }
}