package com.example.seasalon.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.seasalon.admin.ui.tracker.NavigationAdminActivity
import com.example.seasalon.databinding.ActivityLoginBinding
import com.example.seasalon.ui.auth.register.RegisterActivity
import com.example.seasalon.ui.tracker.NavigationActivity
import com.example.seasalon.utils.SharedReference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var pref: SharedReference
    private val viewModel: LoginViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegisterHere.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        pref = SharedReference(this)
        auth = Firebase.auth

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.login(activity = this,email,password)
            var roleUser : String = pref.getRole().toString()
            Log.d("King", "onCreate: $roleUser")
            if (roleUser == "user") {
                val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                startActivity(intent)
                finish()
            } else if (roleUser == "admin") {
                val intent = Intent(this@LoginActivity, NavigationAdminActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}