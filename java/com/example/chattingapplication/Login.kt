package com.example.chattingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button

    //firebase auth related data
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth= FirebaseAuth.getInstance()
        //INITIALISING FIREBASE AUTHENTICATION
        edtemail=findViewById(R.id.edt_email)
        edtpassword=findViewById(R.id.edt_pas)
        btnlogin=findViewById(R.id.btn_login)
        btnsignup=findViewById(R.id.signuo)

        btnsignup.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener{
            val email = edtemail.text.toString()
            val password = edtpassword.text.toString()

            login(email,password)
        }


    }
    private fun login(email: String,password: String){
    //logic for logging in user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, JUMPTO MAIN ACTIVITY
                    val intent =Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login, "ERROR DOES NOT EXIST", Toast.LENGTH_SHORT).show()
                }
            }

    }
}