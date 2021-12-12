package com.example.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    lateinit var newPassword : EditText
    lateinit var changePasswordButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_change_activity)

        newPassword = findViewById(R.id.newPassword)
        changePasswordButton = findViewById(R.id.changePasswordButton)

        changePasswordButton.setOnClickListener {
            val password = newPassword.text.toString()
            if (password.isEmpty() || password.length < 8){
                Toast.makeText(this,"Password field is empty or it's less than 8", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().currentUser?.updatePassword(password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Password was changed", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}