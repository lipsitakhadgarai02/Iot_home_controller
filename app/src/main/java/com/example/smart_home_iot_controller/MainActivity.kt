package com.example.smart_home_iot_controller

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dashboard)

        // In a real drawer setup, activity_home_dashboard.xml must have a DrawerLayout as root
        // and a NavigationView. If not, this logic assumes standard IDs.
        drawerLayout = findViewById(R.id.main) as? DrawerLayout ?: return
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val ivMenu = findViewById<ImageView>(R.id.ivMenu)

        ivMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> drawerLayout.closeDrawers()
                R.id.nav_devices -> startActivity(Intent(this, DevicesActivity::class.java))
                R.id.nav_add_device -> startActivity(Intent(this, AddDeviceActivity::class.java))
                R.id.nav_automation -> startActivity(Intent(this, AutomationActivity::class.java))
                R.id.nav_sensors -> startActivity(Intent(this, SensorMonitorActivity::class.java))
                R.id.nav_notifications -> startActivity(Intent(this, NotificationsActivity::class.java))
                R.id.nav_logout -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        findViewById<MaterialCardView>(R.id.cardClimateSummary).setOnClickListener {
            startActivity(Intent(this, RoomDetailsActivity::class.java))
        }
    }
}