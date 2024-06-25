package com.example.seasalon.ui.tracker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.seasalon.R
import com.example.seasalon.databinding.ActivityNavigationBinding
import com.example.seasalon.ui.booking.fragment_booking
import com.example.seasalon.ui.home.FragmentHome
import com.example.seasalon.ui.profile.ProfileFragment
import com.example.seasalon.utils.SharedReference
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(FragmentHome())


        binding.bottomNavigator.setOnNavigationItemSelectedListener{item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragment = FragmentHome()
                    binding.bottomNavigator.visibility = View.VISIBLE
                }

                R.id.navigation_schedule -> {
                    fragment = fragment_booking()
                    binding.bottomNavigator.visibility = View.VISIBLE
                }

                R.id.navigation_profile -> {
                    fragment = ProfileFragment()
                    binding.bottomNavigator.visibility = View.VISIBLE
                }
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment?) {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_navigation, fragment)
                .commit()
        }
    }
}