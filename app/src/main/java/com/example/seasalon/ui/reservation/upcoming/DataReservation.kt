package com.example.seasalon.ui.reservation.upcoming

data class DataReservation(
    val name: String?,
    val service: String?,
    val date: String?,
    val time: String?,
    val status: String?,
    val isUpcoming: Boolean,
)
