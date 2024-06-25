package com.example.seasalon.ui.auth.register

import android.app.ProgressDialog
import androidx.lifecycle.ViewModel
import com.example.seasalon.data.repository

class RegisterViewModel : ViewModel() {
    private val repository = repository()

    fun saveData(
        activity: RegisterActivity,
        progressDialog: ProgressDialog,
        name: String,
        email: String,
        noPhone: String
    ) {
        repository.saveDataUser(activity, progressDialog, name = name, email = email, noPhone =  noPhone)
    }

    fun registerUser(
        activity: RegisterActivity,
        email: String,
        password: String
    ) {
        repository.registerUser(activity, email = email, password =  password)
    }
}