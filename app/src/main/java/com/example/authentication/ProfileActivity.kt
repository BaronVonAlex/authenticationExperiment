package com.example.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    lateinit var profileLogoutButton : Button
    lateinit var profileChangePasswordButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        profileLogoutButton = findViewById(R.id.profileLogoutButton)
        profileChangePasswordButton = findViewById(R.id.profileChangePasswordButton)

        val profileMail = findViewById<TextView>(R.id.profileMail)
        profileMail.text = FirebaseAuth.getInstance().currentUser?.email

        listeners()

    }

    private fun listeners(){
        profileLogoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        profileChangePasswordButton.setOnClickListener {
            startActivity(Intent(this, PasswordForgotActivity::class.java))

        }

    }

}