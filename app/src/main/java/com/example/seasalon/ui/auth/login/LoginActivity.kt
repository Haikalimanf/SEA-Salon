package com.example.seasalon.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.seasalon.databinding.ActivityLoginBinding
import com.example.seasalon.ui.auth.register.RegisterActivity
import com.example.seasalon.ui.auth.register.RegisterViewModel
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

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("error", "signInWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                        pref.apply {
                            setEmail(user?.email.toString())
                            setName(user?.displayName.toString())
                            setPhone(user?.phoneNumber.toString())
                            setLoggedIn(true)
                        }
                        viewModel.checkUserFromEmail(this, email)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("error", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }
}