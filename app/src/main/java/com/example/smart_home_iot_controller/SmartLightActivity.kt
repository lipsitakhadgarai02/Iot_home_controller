package com.example.smart_home_iot_controller

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class SmartLightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_light)

        val ivBack = findViewById<ImageView>(R.id.ivBack)
        val switchLight = findViewById<SwitchMaterial>(R.id.switchLight)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        ivBack?.setOnClickListener { finish() }

        switchLight?.setOnCheckedChangeListener { _, isChecked ->
            tvStatus?.text = if (isChecked) "On" else "Off"
        }
    }
}