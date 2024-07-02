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
import com.example.seasalon.ui.reservation.done.DataReservationDone
import com.example.seasalon.ui.reservation.done.ListReservationDoneAdapter
import com.example.seasalon.ui.reservation.upcoming.DataReservation
import com.example.seasalon.ui.reservation.upcoming.ListReservationAdapter
import com.example.seasalon.utils.SharedReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReservationFragment : Fragment() {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListReservationAdapter
    private lateinit var adapterDone: ListReservationDoneAdapter
    private val db: FirebaseFirestore = Firebase.firestore
    private lateinit var pref: SharedReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListReservationAdapter()
        adapterDone = ListReservationDoneAdapter()
        binding.rvReservation.adapter = adapter
        binding.rvHistory.adapter = adapterDone
        binding.rvReservation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())

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
        val statusReservationUser = if (isUpcoming) "upcoming" else "done"
        db.collection("bookings")
            .whereEqualTo("email", email)
            .whereEqualTo("status", statusReservationUser)
            .get()
            .addOnSuccessListener { result ->
                if (isUpcoming) {
                    val reservations = mutableListOf<DataReservation>()
                    for (document in result) {
                        val date = document.getString("date") ?: ""
                        val time = document.getString("time") ?: ""
                        val service = document.getString("service") ?: ""
                        val status = document.getString("status") ?: ""
                        val reservation = DataReservation(
                            name = pref.getName(),
                            service = service,
                            date = date,
                            time = time,
                            status = status,
                            isUpcoming = isUpcoming
                        )
                        reservations.add(reservation)
                    }
                    adapter.submitList(reservations)
                    binding.rvReservation.visibility = View.VISIBLE
                    binding.rvHistory.visibility = View.GONE
                } else {
                    val reservationsDone = mutableListOf<DataReservationDone>()
                    for (document in result) {
                        val date = document.getString("date") ?: ""
                        val time = document.getString("time") ?: ""
                        val service = document.getString("service") ?: ""
                        val status = document.getString("status") ?: ""
                        val reservation = DataReservationDone(
                            name = pref.getName(),
                            service = service,
                            date = date,
                            time = time,
                            status = status,
                            isUpcoming = isUpcoming
                        )
                        reservationsDone.add(reservation)
                    }
                    adapterDone.submitList(reservationsDone)
                    binding.rvReservation.visibility = View.GONE
                    binding.rvHistory.visibility = View.VISIBLE
                }
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
