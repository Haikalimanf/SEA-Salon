package com.example.seasalon.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalon.R
import com.example.seasalon.databinding.FragmentReservationBinding
import com.example.seasalon.utils.SharedReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReservationFragment : Fragment() {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListReservationAdapter
    private val db: FirebaseFirestore = Firebase.firestore
    private lateinit var pref: SharedReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListReservationAdapter()
        binding.rvReservation.adapter = adapter
        binding.rvReservation.layoutManager = LinearLayoutManager(requireContext())
        pref = SharedReference(requireContext())
        loadReservations(true) // Load upcoming reservations by default

        binding.btnHistory.setOnClickListener {
            binding.btnHistory.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue_sea))
            binding.btnUpcoming.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue_sea_2))
            loadReservations(false)
        }

        binding.btnUpcoming.setOnClickListener {
            binding.btnUpcoming.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue_sea))
            binding.btnHistory.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue_sea_2))
            loadReservations(true)
        }
    }

    private fun loadReservations(isUpcoming: Boolean) {
        val email = pref.getEmail().toString()
        db.collection("bookings")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { result ->
                val reservations = mutableListOf<DataReservation>()
                for (document in result) {
                    val date = document.getString("date") ?: ""
                    val time = document.getString("time") ?: ""
                    val service = document.getString("service") ?: ""
                    val reservation = DataReservation(
                        name = pref.getName(),
                        service = service,
                        date = date,
                        time = time,
                        isUpcoming = isUpcoming
                    )
                    reservations.add(reservation)
                }
                adapter.submitList(reservations)
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
