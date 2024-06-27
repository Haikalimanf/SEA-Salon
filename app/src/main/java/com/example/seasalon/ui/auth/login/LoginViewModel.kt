package com.example.seasalon.ui.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.seasalon.data.repository

class LoginViewModel : ViewModel() {
    private val repository = repository()

    fun login(
        activity: LoginActivity,
        email: String,
        password: String,
    ){
        repository.loginWithEmailAndPassword(activity,email,password)
    }
}