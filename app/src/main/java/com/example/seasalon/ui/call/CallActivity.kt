package com.example.seasalon.ui.call

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seasalon.R
import com.example.seasalon.databinding.ActivityBookingBinding
import com.example.seasalon.databinding.ActivityCallBinding
import com.example.seasalon.ui.booking.ListTimeAdapter
import com.example.seasalon.ui.tracker.NavigationActivity

class CallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCallBinding
    private lateinit var adapter: ListTimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBackArrow.setOnClickListener{
            val intent = Intent(this@CallActivity, NavigationActivity::class.java)
            startActivity(intent)
        }
    }
}