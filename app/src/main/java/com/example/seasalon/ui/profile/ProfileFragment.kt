package com.example.seasalon.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seasalon.R
import com.example.seasalon.databinding.FragmentHomeBinding
import com.example.seasalon.databinding.FragmentProfileBinding
import com.example.seasalon.ui.auth.login.LoginActivity
import com.example.seasalon.utils.SharedReference
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.firestore
import com.orhanobut.hawk.Hawk

class ProfileFragment : Fragment() {
    private lateinit var pref: SharedReference
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = SharedReference(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUsername.text = pref.getName()
        binding.tvEmail.text = pref.getEmail()
        binding.tvNotelp.text = pref.getPhone().toString()

        binding.btnLogout.setOnClickListener{
            logout()
        }
    }
    private fun logout(){
            Firebase.auth.signOut()
            pref.clearData()
            pref.setLoggedIn(false)
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}