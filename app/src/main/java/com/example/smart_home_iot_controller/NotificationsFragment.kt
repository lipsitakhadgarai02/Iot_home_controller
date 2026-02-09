package com.example.smart_home_iot_controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        view.findViewById<View>(R.id.ivBack)?.visibility = View.GONE

        val rvNotifications = view.findViewById<RecyclerView>(R.id.rvNotifications)
        rvNotifications.layoutManager = LinearLayoutManager(requireContext())
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