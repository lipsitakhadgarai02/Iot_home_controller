package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial

class DevicesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.ivBack)?.visibility = View.GONE
        view.findViewById<View>(R.id.bottomNavContainer)?.visibility = View.GONE

        val rvDevices = view.findViewById<RecyclerView>(R.id.rvDevices)
        rvDevices.layoutManager = LinearLayoutManager(requireContext())
        rvDevices.adapter = DeviceAdapter(listOf(
            Device("Smart Light", "On", true),
            Device("Air Conditioner", "24°C", true),
            Device("Smart TV", "Off", false),
            Device("Door Lock", "Locked", true)
        ))
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
                startActivity(Intent(requireContext(), SmartLightActivity::class.java))
            }
        }

        override fun getItemCount() = devices.size
    }
}