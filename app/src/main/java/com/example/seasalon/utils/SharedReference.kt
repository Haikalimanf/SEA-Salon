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

    fun setEmail(email: String?) {
        editor.putString(EMAIL, email)
        editor.apply()
    }

    fun getName(): String? {
        return sharedPreferences.getString(NAME, "")
    }

    fun setName(name: String) {
        editor.putString(NAME, name)
        editor.apply()
    }

    fun getPhone(): String? {
        return sharedPreferences.getString(PHONE, "")
    }

    fun setPhone(phone: String) {
        editor.putString(PHONE, phone)
        editor.apply()
    }

    fun getRole(): String? {
        return sharedPreferences.getString(ROLE, "")
    }

    fun setRole(role: String) {
        editor.putString(ROLE, role)
        editor.apply()
    }


    fun getDate(): String? {
        return sharedPreferences.getString(DATE, "")
    }

    fun setDate(date: String) {
        editor.putString(DATE, date)
        editor.apply()
    }

    fun getTime(): String? {
        return sharedPreferences.getString(TIME, "")
    }

    fun setTime(time: String) {
        editor.putString(TIME, time)
        editor.apply()
    }

    fun getService(): String? {
        return sharedPreferences.getString(SERVICE, "")
    }

    fun setService(service: String) {
        editor.putString(SERVICE, service)
        editor.apply()
    }

    fun getUID(): String? {
        return sharedPreferences.getString(UID, "")
    }

    fun setUID(uid: String) {
        editor.putString(UID, uid)
        editor.apply()
    }

    fun clearData(){
        editor.putString(PHONE, "")
        editor.putString(UID, "")
        editor.putString(NAME, "")
        editor.putString(EMAIL, "")
        editor.putString(DATE, "")
        editor.putString(TIME, "")
        editor.putString(SERVICE, "")
        editor.putString(ROLE, "")
        editor.apply()
    }


    companion object {
        private const val LOGIN = "login"
        private const val EMAIL = "email"
        private const val NAME = "name"
        private const val ROLE = "role"
        private const val PHONE ="phone"
        private const val DATE ="date"
        private const val TIME ="time"
        private const val SERVICE ="service"
        private const val UID = "uid"
    }
}
