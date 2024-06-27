package com.example.seasalon.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalon.data.DataReference
import com.example.seasalon.data.DataReference.item
import com.example.seasalon.databinding.FragmentHomeBinding
import com.example.seasalon.ui.auth.login.LoginViewModel
import com.example.seasalon.ui.booking.BookingActivity
import com.example.seasalon.ui.call.CallActivity
import com.example.seasalon.ui.tracker.NavigationActivity
import com.example.seasalon.utils.SharedReference
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListServiceAdapter
    private lateinit var pref: SharedReference



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListServiceAdapter()
        binding.rvService.adapter = adapter
        binding.rvService.setHasFixedSize(true)
        binding.rvService.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(item)
        pref = SharedReference(requireContext())
        var name = pref.getName().toString()
        binding.tvGreetingUser.text = "Hallo,$name"

        binding.fabCalling.setOnClickListener {
            val intent = Intent(context, CallActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}