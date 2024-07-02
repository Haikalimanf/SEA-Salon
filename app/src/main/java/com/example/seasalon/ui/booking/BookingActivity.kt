package com.example.seasalon.ui.booking

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.seasalon.databinding.ActivityBookingBinding
import com.example.seasalon.ui.auth.register.RegisterActivity
import com.example.seasalon.ui.auth.register.RegisterViewModel
import com.example.seasalon.ui.tracker.NavigationActivity
import com.example.seasalon.utils.SharedReference
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.Calendar


class BookingActivity : AppCompatActivity(), ListTimeAdapter.OnTimeSelectedListener {
    private lateinit var binding: ActivityBookingBinding
    private lateinit var adapter: ListTimeAdapter
    private var selectedDate: Calendar = Calendar.getInstance()
    private var dateReservation = ""
    private var time: String= ""
    private var date: String= ""
    private lateinit var pref: SharedReference
    lateinit var progressDialog: ProgressDialog
    private val db: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = SharedReference(this)
        val timeList = mutableListOf<String>()
        for (hour in 9..21) {
            val formattedTime = String.format("%02d:00", hour)
            timeList.add(formattedTime)
        }
        adapter = ListTimeAdapter(this, items = timeList, selectedDate = selectedDate, callback = this)


        binding.rvBookingTime.adapter = adapter
        binding.rvBookingTime.setHasFixedSize(true)
        binding.rvBookingTime.layoutManager = GridLayoutManager(this,3)

        binding.imgBackArrow.setOnClickListener{
            val intent = Intent(this@BookingActivity, NavigationActivity::class.java)
            startActivity(intent)
        }

        binding.btnCalender.setOnClickListener {
            showDatePickerDialog()
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Menyimpan")

        binding.btnBooking.setOnClickListener {
            var name = pref.getName().toString()
            val service = "Service Name"
//            viewModel.saveBooking(this, progressDialog, name, date, service ,time)
            saveDataBooking(this, progressDialog, name, date, service ,time)

        }

    }

    fun saveDataBooking(
        activity: BookingActivity,
        progressDialog: ProgressDialog,
        name: String,
        date: String,
        service: String = "",
        timeBooking: String,
    ) {

        val email = pref.getEmail()
        val bookingsData = hashMapOf(
            "email" to email,
            "name" to name,
            "service" to service,
            "date" to date,
            "time" to timeBooking,
            "status" to "upcoming"
        )

        db.collection("bookings")
            .add(bookingsData)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(activity.applicationContext, "Successfully", Toast.LENGTH_SHORT).show()
                Log.d("paebj", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("paebj", "Error adding document", e)
            }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedDateDialog =
                    String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                binding.tvShowSelectedDate.text = selectedDateDialog
                dateReservation = selectedDateDialog
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                updateAdapterWithSelectedDate(selectedDate)
                date = selectedDateDialog

            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateAdapterWithSelectedDate(selectedDate: Calendar) {
        adapter.setDate(selectedDate)
        adapter.notifyDataSetChanged()
    }

    companion object{

    }

    override fun onTimeSelected(timeData: String) {
        time = timeData
        Log.d("dewa", "onTimeSelected: $time")
    }


}