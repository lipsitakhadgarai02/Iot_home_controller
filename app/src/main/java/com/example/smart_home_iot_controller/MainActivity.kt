package com.example.smart_home_iot_controller

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup Bottom Navigation
        bottomNav.setupWithNavController(navController)
        
        // Setup Side Navigation (Drawer)
        NavigationUI.setupWithNavController(navView, navController)

        // Handle Bottom Nav visibility and Back Stack
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.devicesFragment, R.id.notificationsFragment, R.id.automationFragment -> {
                    bottomNav.visibility = View.VISIBLE
                }
                else -> {
                    bottomNav.visibility = View.GONE
                }
            }
        }

        setupBackPress()
    }

    fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun setupBackPress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else if (navController.currentDestination?.id == R.id.homeFragment) {
                    if (backPressedTime + 2000 > System.currentTimeMillis()) {
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Press back again to exit", Toast.LENGTH_SHORT).show()
                        backPressedTime = System.currentTimeMillis()
                    }
                } else {
                    if (!navController.popBackStack()) {
                        finish()
                    }
                }
            }
        })
    }
}