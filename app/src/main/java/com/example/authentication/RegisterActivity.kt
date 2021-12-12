package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var registerMail : EditText
    lateinit var registerPassword : EditText
    lateinit var registerButton : Button
    lateinit var regLoginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        init()
    }

    private fun init(){
        registerMail = findViewById(R.id.registerMail)
        registerPassword = findViewById(R.id.registerPassword)
        registerButton = findViewById(R.id.registerButton)
        regLoginButton = findViewById(R.id.regLoginButton)

        registerButton.setOnClickListener {
            val regEmail = registerMail.text.toString()
            val regPassword = registerPassword.text.toString()

            if (regEmail.isEmpty() || regPassword.isEmpty()){
                Toast.makeText(this,"Blanks are empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(regEmail, regPassword)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}