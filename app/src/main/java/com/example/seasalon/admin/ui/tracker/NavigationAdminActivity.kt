package com.example.seasalon.admin.ui.tracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.seasalon.admin.ui.profile.ProfileAdminActivity
import com.example.seasalon.admin.ui.tracker.ui.main.SectionsPagerAdapter
import com.example.seasalon.databinding.ActivityNavigationAdminBinding
import com.example.seasalon.ui.tracker.NavigationActivity
import com.example.seasalon.utils.SharedReference

class NavigationAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationAdminBinding
    private lateinit var pref: SharedReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = SharedReference(this)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)


        binding.imgIconProfile.setOnClickListener{
            val intent = Intent(this@NavigationAdminActivity, ProfileAdminActivity::class.java)
            startActivity(intent)
        }




    }
}