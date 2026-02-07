package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial

class DevicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        findViewById<ImageView>(R.id.ivBack)?.setOnClickListener { finish() }

        val rvDevices = findViewById<RecyclerView>(R.id.rvDevices)
        rvDevices.layoutManager = LinearLayoutManager(this)
        rvDevices.adapter = DeviceAdapter(listOf(
            Device("Smart Light", "On", true),
            Device("Smart Light", "On", true),
            Device("Smart Light", "On", true)
        ))

        // Bottom Navigation Logic
        findViewById<ImageView>(R.id.nav_home)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
            finish()
        }
    }

    data class Device(val name: String, val status: String, val isOn: Boolean)

    private inner class DeviceAdapter(private val devices: List<Device>) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvDeviceName)
            val tvStatus: TextView = view.findViewById(R.id.tvDeviceStatus)
            val switch: SwitchMaterial = view.findViewById(R.id.switchDevice)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_device_card, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val device = devices[position]
            holder.tvName.text = device.name
            holder.tvStatus.text = device.status
            holder.switch.isChecked = device.isOn
            
            holder.itemView.setOnClickListener {
                startActivity(Intent(this@DevicesActivity, SmartLightActivity::class.java))
            }
        }

        override fun getItemCount() = devices.size
    }
}