package com.example.seasalon.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedReference(context: Context) {
    private var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = sharedPreferences.edit()

    fun getLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(LOGIN, false)
    }

    fun setLoggedIn(loggedIn: Boolean) {
        editor.putBoolean(LOGIN, loggedIn)
        editor.apply()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString(EMAIL, "")
    }

    fun setEmail(email: String) {
        editor.putString(EMAIL, email)
        editor.apply()
    }

    fun getName(): String? {
        return sharedPreferences.getString(NAME, "")
    }

    fun setName(email: String) {
        editor.putString(NAME, email)
        editor.apply()
    }

    fun getPhone(): Int {
        return sharedPreferences.getInt(PHONE, 0)
    }

    fun setPhone(phone: String) {
        editor.putString(PHONE, phone)
        editor.apply()
    }

    fun getUID(): String? {
        return sharedPreferences.getString(UID, "")
    }

    fun setUID(uid: String) {
        editor.putString(UID, uid)
        editor.apply()
    }


    companion object {
        private const val LOGIN = "login"
        private const val EMAIL = "email"
        private const val NAME = "name"
        private const val PHOTO = "photo"
        private const val PHONE ="phone"
        private const val UID = "uid"
    }
}
