package com.example.seasalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.seasalon.ui.auth.login.LoginActivity
import com.example.seasalon.ui.tracker.NavigationActivity
import com.example.seasalon.utils.SharedReference

class MainActivity : AppCompatActivity() {

    private lateinit var pref: SharedReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        pref = SharedReference(this)

        Handler().postDelayed({
            if (pref.getLoggedIn()) {
                val intent = Intent(this@MainActivity, NavigationActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}