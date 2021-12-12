package com.example.authentication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class PasswordForgotActivity : AppCompatActivity()  {

    lateinit var forgotPasswordMail : EditText
    lateinit var sendMailButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_lost_activity)

        init()

    }

    private fun init(){
        forgotPasswordMail = findViewById(R.id.forgotPasswordMail)
        sendMailButton = findViewById(R.id.sendMailButton)

        sendMailButton.setOnClickListener {
            val email = forgotPasswordMail.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this,"Email is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Mail was sent", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}