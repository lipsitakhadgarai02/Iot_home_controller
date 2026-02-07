package com.example.smart_home_iot_controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        
        findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            finish()
        }

        // --- Setup Notifications List ---
        val rvNotifications = findViewById<RecyclerView>(R.id.rvNotifications)
        rvNotifications.layoutManager = LinearLayoutManager(this)
        rvNotifications.adapter = NotificationAdapter(listOf(
            "Motion detected in Living Room",
            "Smart Light turned ON in Kitchen",
            "Temperature reached 24°C",
            "Door locked at 10:30 PM",
            "AC set to 22°C",
            "New device 'Smart Plug' added"
        ))
    }

    private inner class NotificationAdapter(private val notifications: List<String>) : 
        RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
        
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvText: TextView = view.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvText.text = notifications[position]
        }

        override fun getItemCount() = notifications.size
    }
}