package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_login)

        val sessionManager = SessionManager(this)

        // Login -> Home Dashboard (MainActivity)
        findViewById<Button>(R.id.btnGetOtp).setOnClickListener {
            // Save login session
            sessionManager.setLogin(true)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Prevents going back to Login
        }
    }
}