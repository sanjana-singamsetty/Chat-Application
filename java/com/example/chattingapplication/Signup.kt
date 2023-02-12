package com.example.chattingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    private lateinit var edtname :EditText
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnsignup: Button
    //firebase auth related data
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDBRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        edtname=findViewById(R.id.edt_name)
        mAuth= FirebaseAuth.getInstance()
        //INITIALISING FIREBASE AUTHENTICATION
        edtemail=findViewById(R.id.edt_email)
        edtpassword=findViewById(R.id.edt_pas)
        btnsignup=findViewById(R.id.signuo)

        btnsignup.setOnClickListener {
            val name =edtname.text.toString()
            val email=edtemail.text.toString()
            val password = edtpassword.text.toString()

            //metho helps us signup
            signUp(name,email,password)
        }
    }
    private fun signUp(name:String,email: String,password: String){
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success,jumping to home
                    addUserToDataBase(name,email,mAuth.currentUser?.uid!!)
                    val intent =Intent(this@Signup,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Signup, "ERROR PLEASE TRY AGAIN", Toast.LENGTH_SHORT).show()

                }
            }

    }


    private fun  addUserToDataBase(name:String,email: String,uid:String){
        mDBRef=FirebaseDatabase.getInstance().getReference()
        mDBRef.child("user").child(uid).setValue(User(name, email, uid))

    }
}