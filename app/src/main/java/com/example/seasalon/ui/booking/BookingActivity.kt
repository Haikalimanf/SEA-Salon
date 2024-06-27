package com.example.seasalon.ui.booking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalon.data.DataReference.time
import com.example.seasalon.databinding.ActivityBookingBinding
import com.example.seasalon.ui.auth.register.RegisterActivity
import com.example.seasalon.ui.tracker.NavigationActivity


class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private lateinit var adapter: ListTimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ListTimeAdapter()
        binding.rvBookingTime.adapter = adapter
        binding.rvBookingTime.setHasFixedSize(true)
        binding.rvBookingTime.layoutManager = GridLayoutManager(this,3)
        adapter.submitList(time)


        binding.imgBackArrow.setOnClickListener{
            val intent = Intent(this@BookingActivity, NavigationActivity::class.java)
            startActivity(intent)
        }
    }

    companion object{

    }


}