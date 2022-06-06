package com.example.campuschat.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.campuschat.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_home_icon.*
import kotlinx.android.synthetic.main.layout_message_icon.*
import kotlinx.android.synthetic.main.layout_notation_icon.*
import kotlinx.android.synthetic.main.layout_search_icon.*

class HomeActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var toolBar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private var currentFragment: Int = R.id.homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // UI Initialize
        toolbar_icon.visibility = View.VISIBLE
        toolbar_search.visibility = View.GONE
        toolbar_notation.visibility = View.GONE
        toolbar_message.visibility = View.GONE

        // Tool Bar Setting
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.blue_more_two)
        }

        // Drawer Setting
        drawerLayout = findViewById(R.id.drawer_layout_home)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        // Tab bar switch
        navController = findNavController(R.id.home_fragment)
        val destinationMap = mapOf(
            R.id.homeFragment to homeIconMotionLayout,
            R.id.searchFragment to searchIconMotionLayout,
            R.id.notationFragment to notationIconMotionLayout,
            R.id.messageFragment to messageIconMotionLayout
        )
        destinationMap.forEach { map ->
            map.value.setOnClickListener {
                navController.navigate(map.key)
                when (map.key) {
                    R.id.homeFragment -> {
                        toolBar.menu.clear()
                        toolBar.inflateMenu(R.menu.toolbar_home)
                        toolbar_icon.visibility = View.VISIBLE
                        toolbar_search.visibility = View.GONE
                        toolbar_notation.visibility = View.GONE
                        toolbar_message.visibility = View.GONE
                    }
                    R.id.searchFragment -> {
                        toolBar.menu.clear()
                        toolBar.inflateMenu(R.menu.toolbar_search)
                        toolbar_icon.visibility = View.GONE
                        toolbar_search.visibility = View.VISIBLE
                        toolbar_notation.visibility = View.GONE
                        toolbar_message.visibility = View.GONE
                    }
                    R.id.notationFragment -> {
                        toolBar.menu.clear()
                        toolBar.inflateMenu(R.menu.toolbar_notation)
                        toolbar_icon.visibility = View.GONE
                        toolbar_search.visibility = View.GONE
                        toolbar_notation.visibility = View.VISIBLE
                        toolbar_message.visibility = View.GONE
                    }
                    R.id.messageFragment -> {
                        toolBar.menu.clear()
                        toolBar.inflateMenu(R.menu.toolbar_message)
                        toolbar_icon.visibility = View.GONE
                        toolbar_search.visibility = View.GONE
                        toolbar_notation.visibility = View.GONE
                        toolbar_message.visibility = View.VISIBLE
                    }
                }
            }
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            controller.popBackStack()
            destinationMap.values.forEach { it.progress = 0f }
            destinationMap[destination.id]?.transitionToEnd()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.recommand_mode_home -> Toast.makeText(this, "Recommand Mode Changes",
                Toast.LENGTH_SHORT).show()
            R.id.settings_search -> Toast.makeText(this, "Search Settings",
                Toast.LENGTH_SHORT).show()
            R.id.settings_notation -> Toast.makeText(this, "Notation Settings",
                Toast.LENGTH_SHORT).show()
            R.id.settings_message -> Toast.makeText(this, "Message Settings",
                Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}