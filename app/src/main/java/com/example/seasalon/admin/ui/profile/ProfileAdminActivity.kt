package com.example.seasalon.admin.ui.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seasalon.R
import com.example.seasalon.admin.ui.tracker.NavigationAdminActivity
import com.example.seasalon.databinding.ActivityProfileAdminBinding
import com.example.seasalon.ui.auth.login.LoginActivity
import com.example.seasalon.ui.tracker.NavigationActivity

import com.example.seasalon.utils.SharedReference
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ProfileAdminActivity : AppCompatActivity() {
    private lateinit var pref: SharedReference
    private lateinit var binding : ActivityProfileAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_admin)
        binding = ActivityProfileAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = SharedReference(this)
        binding.tvUsername.text = pref.getName()
        binding.tvEmail.text = pref.getEmail()

        binding.btnLogout.setOnClickListener{
            logout()
        }

        binding.imgbackArrow.setOnClickListener{
            val intent = Intent(this@ProfileAdminActivity, NavigationAdminActivity::class.java)
            startActivity(intent)
        }

    }

    private fun logout(){
        Firebase.auth.signOut()
        pref.clearData()
        pref.setLoggedIn(false)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}