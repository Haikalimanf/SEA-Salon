package com.example.seasalon.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalon.data.DataReference.item
import com.example.seasalon.databinding.FragmentHomeBinding
import com.example.seasalon.ui.auth.login.LoginViewModel
import com.example.seasalon.utils.SharedReference
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListServiceAdapter
    private lateinit var pref: SharedReference
    private val db: FirebaseFirestore = Firebase.firestore



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
        adapter = ListServiceAdapter(item)
        binding.rvService.adapter = adapter
        binding.rvService.setHasFixedSize(true)
        binding.rvService.layoutManager = LinearLayoutManager(requireContext())
        pref = SharedReference(requireContext())
        var name = pref.getName().toString()
        Log.d("coba", "$name")
        binding.tvGreetingUser.text = name

//        db.collection("users")
//            .whereEqualTo("email", pref.getEmail())
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val name = document.getString("name")
//                    if (name != null) {
//                        pref.setName(name)
//                        Log.d("coba get email", "$pref.getEmail()")
//                    }
//                    Log.d("coba", "${document.id} => ${document.data}")
//                    Log.d("coba", "$name")
//                    binding.tvGreetingUser.text = name
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("coba", "Error getting documents.", exception)
//            }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}