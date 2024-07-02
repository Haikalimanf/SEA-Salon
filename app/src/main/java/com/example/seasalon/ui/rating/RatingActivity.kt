package com.example.seasalon.ui.rating

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.seasalon.R
import com.example.seasalon.databinding.ActivityBookingBinding
import com.example.seasalon.databinding.ActivityRatingBinding
import com.example.seasalon.ui.auth.register.RegisterViewModel
import com.example.seasalon.ui.tracker.NavigationActivity
import com.example.seasalon.utils.SharedReference
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class RatingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRatingBinding
    private lateinit var pref: SharedReference
    private val viewModel: RatingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = SharedReference(this)
        lateinit var progressDialog: ProgressDialog


        binding.btnSubmitRating.setOnClickListener {
            val name = pref.getName().toString()
            val email = pref.getEmail().toString()
            val rating = binding.ratingBar.rating.toString()
            val comment = binding.etComment.text.toString()

            progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Loading")
            progressDialog.setMessage("Menyimpan")

            viewModel.saveDataReviewUser(activity = this, progressDialog = progressDialog, name = name, email = email, rating= rating, commentUser = comment)
        }


        binding.imgBackArrow.setOnClickListener{
            val intent = Intent(this@RatingActivity, NavigationActivity::class.java)
            startActivity(intent)
        }

    }
}