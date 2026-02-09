package com.example.smart_home_iot_controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle Hamburger/Grid Menu Click
        view.findViewById<ImageView>(R.id.ivMenu)?.setOnClickListener {
            (activity as? MainActivity)?.openDrawer()
        }

        val rvRooms = view.findViewById<RecyclerView>(R.id.rvRooms)
        rvRooms.layoutManager = LinearLayoutManager(requireContext())
        rvRooms.adapter = RoomAdapter(listOf(
            Room("Living Room", "6 devices"),
            Room("Bedroom", "4 devices"),
            Room("Kitchen", "2 devices")
        ))
    }

    data class Room(val name: String, val devices: String)

    private inner class RoomAdapter(private val rooms: List<Room>) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvRoomName)
            val tvCount: TextView = view.findViewById(R.id.tvDeviceCount)
            val cardRoom: MaterialCardView = view.findViewById(R.id.cardRoom)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room_card, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val room = rooms[position]
            holder.tvName.text = room.name
            holder.tvCount.text = room.devices
            
            holder.cardRoom.setOnClickListener {
                val intent = Intent(requireContext(), RoomDetailsActivity::class.java)
                intent.putExtra("ROOM_NAME", room.name)
                startActivity(intent)
            }
        }

        override fun getItemCount() = rooms.size
    }
}