package com.example.seasalon.ui.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.seasalon.data.repository

class LoginViewModel : ViewModel() {
    private val repository = repository()

    fun checkUserFromEmail(
        activity: Context,
        email: String
    ){
        repository.getUserFromAuth(activity ,email = email)
    }
}