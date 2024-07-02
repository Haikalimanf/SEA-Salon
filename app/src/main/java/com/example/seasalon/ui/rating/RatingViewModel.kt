package com.example.seasalon.ui.rating

import android.app.ProgressDialog
import androidx.lifecycle.ViewModel
import com.example.seasalon.data.repository

class RatingViewModel : ViewModel() {
    private val repository = repository()

    fun saveDataReviewUser(
        activity: RatingActivity,
        progressDialog: ProgressDialog,
        name: String,
        email: String,
        commentUser: String,
        rating : String
    ) {
        repository.saveDataReview(activity, progressDialog, name = name, email = email, comment = commentUser, rating = rating )
    }
}