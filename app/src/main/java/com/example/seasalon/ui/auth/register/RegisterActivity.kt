package com.example.seasalon.ui.auth.register

import android.app.ProgressDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.seasalon.R
import com.example.seasalon.databinding.ActivityLoginBinding
import com.example.seasalon.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    lateinit var progressDialog: ProgressDialog

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnSignUp.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val phone = binding.etNotelp.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Loading")
            progressDialog.setMessage("Menyimpan")

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty()) {
                viewModel.saveData(this, progressDialog, username, email, phone)
                viewModel.registerUser(this, email, password)
            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
