package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var loginMail : EditText
    lateinit var loginPassword : EditText
    lateinit var loginButton : Button
    lateinit var registrationLoginButton : Button
    lateinit var forgotPasswordButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        init()
        listeners()

    }

    private fun init(){
        loginMail = findViewById(R.id.loginMail)
        loginPassword = findViewById(R.id.loginPassword)
        loginButton = findViewById(R.id.loginButton)
        registrationLoginButton = findViewById(R.id.registrationLoginButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
    }

    fun listeners(){
        loginButton.setOnClickListener {

            val email = loginMail.text.toString()
            val password = loginPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Fill blanks", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this,ProfileActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"Password or Mail is incorrect", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        registrationLoginButton.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        forgotPasswordButton.setOnClickListener {
            startActivity(Intent(this,PasswordForgotActivity::class.java))
        }
    }
}