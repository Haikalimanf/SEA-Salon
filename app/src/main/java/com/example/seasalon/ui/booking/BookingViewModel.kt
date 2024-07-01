package com.example.seasalon.ui.booking

import android.app.ProgressDialog
import androidx.lifecycle.ViewModel
import com.example.seasalon.data.repository

class BookingViewModel: ViewModel() {
    private val repository = repository()

    fun saveBooking(
        activity: BookingActivity,
        progressDialog: ProgressDialog,
        name: String,
        date: String,
        service: String,
        timeBooking: String
    )
    {
        repository.saveDataBooking(activity,progressDialog,name,date,service,timeBooking)
    }
}