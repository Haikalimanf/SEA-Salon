package com.example.seasalon.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seasalon.R
import com.example.seasalon.databinding.FragmentHomeBinding
import com.example.seasalon.databinding.FragmentScheduleBinding
import com.example.seasalon.ui.home.ListServiceAdapter
import com.example.seasalon.utils.SharedReference

class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListServiceAdapter
    private lateinit var pref: SharedReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}