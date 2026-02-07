package com.example.smart_home_iot_controller

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private val sessionManager by lazy { SessionManager(this) }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_dashboard)

        drawerLayout = findViewById(R.id.main)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val ivMenu = findViewById<ImageView>(R.id.ivMenu)

        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        checkPermissions()

        // --- Setup RecyclerView for Rooms ---
        val rvRooms = findViewById<RecyclerView>(R.id.rvRooms)
        rvRooms.layoutManager = LinearLayoutManager(this)
        rvRooms.adapter = RoomAdapter(listOf(
            Room("Living Room", "6 devices"),
            Room("Bedroom", "4 devices"),
            Room("Kitchen", "2 devices")
        ))

        ivMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> drawerLayout.closeDrawers()
                R.id.nav_devices -> startActivity(Intent(this, DevicesActivity::class.java))
                R.id.nav_add_device -> startActivity(Intent(this, AddDeviceActivity::class.java))
                R.id.nav_automation -> startActivity(Intent(this, AutomationActivity::class.java))
                R.id.nav_sensors -> startActivity(Intent(this, SensorMonitorActivity::class.java))
                R.id.nav_notifications -> startActivity(Intent(this, NotificationsActivity::class.java))
                R.id.nav_theme_toggle -> toggleTheme()
                R.id.nav_logout -> logout()
            }
            drawerLayout.closeDrawers()
            true
        }

        findViewById<ImageView>(R.id.nav_devices).setOnClickListener {
            startActivity(Intent(this, DevicesActivity::class.java))
        }
        findViewById<ImageView>(R.id.nav_notifications).setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }
        findViewById<ImageView>(R.id.nav_settings).setOnClickListener {
            startActivity(Intent(this, AutomationActivity::class.java))
        }
    }

    private fun checkPermissions() {
        val permissions = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        val neededPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (neededPermissions.isNotEmpty()) {
            requestPermissionLauncher.launch(neededPermissions.toTypedArray())
        }
    }

    private fun toggleTheme() {
        val mode = if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun logout() {
        sessionManager.logout()
        startActivity(Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }

    // --- Simple Adapter and Data Class ---
    data class Room(val name: String, val devices: String)

    private inner class RoomAdapter(private val rooms: List<Room>) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvRoomName)
            val tvCount: TextView = view.findViewById(R.id.tvDeviceCount)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room_card, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvName.text = rooms[position].name
            holder.tvCount.text = rooms[position].devices
            holder.itemView.setOnClickListener {
                startActivity(Intent(this@MainActivity, RoomDetailsActivity::class.java))
            }
        }

        override fun getItemCount() = rooms.size
    }
}