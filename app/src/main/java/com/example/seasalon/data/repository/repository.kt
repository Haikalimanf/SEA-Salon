package com.example.seasalon.data

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.seasalon.ui.auth.login.LoginActivity
import com.example.seasalon.ui.auth.register.RegisterActivity
import com.example.seasalon.ui.home.FragmentHome
import com.example.seasalon.utils.SharedReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class repository {

    private val db: FirebaseFirestore = Firebase.firestore
    private val auth: FirebaseAuth = Firebase.auth
    private lateinit var pref: SharedReference


    fun saveDataUser(
        activity: RegisterActivity,
        progressDialog: ProgressDialog,
        name: String,
        email: String,
        noPhone: String,
        user: String = "user"
    ) {
        val users = hashMapOf(
            "name" to name,
            "noPhone" to noPhone,
            "email" to email,
            "role" to user
        )

        db.collection("users")
            .add(users)
            .addOnSuccessListener { documentReference ->
                progressDialog.dismiss()
                Toast.makeText(activity.applicationContext, "Successfully", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                activity.finish()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(activity.applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }

    fun registerUser(
        activity: RegisterActivity,
        email: String,
        password: String
    ){

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("error", "createUserWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("error", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        activity.baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
            .addOnFailureListener { }
    }

    fun getUserFromAuth(
        context: Context,
        email: String ,
    ) {
        pref = SharedReference(context)
        db.collection("users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.getString("name")
                    if (name != null) {
                        Log.d("CEK", name)
                        pref.setName(name)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("coba", "Error getting documents.", exception)
            }
    }
}
