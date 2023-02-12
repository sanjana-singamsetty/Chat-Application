package com.example.chattingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var userrecyclerview: RecyclerView
    private lateinit var userlist: ArrayList<User>
    private lateinit var adaptor: UserAdaptor
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mDBref :DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth= FirebaseAuth.getInstance()
        mDBref=FirebaseDatabase.getInstance().getReference()
        userlist = ArrayList()
        adaptor= UserAdaptor(this,userlist)
        //users and showing tem
        userrecyclerview=findViewById(R.id.userrecyclerview)
        userrecyclerview.layoutManager=LinearLayoutManager(this)
        userrecyclerview.adapter=adaptor
        mDBref.child("user").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userlist.clear()
                for (postSnapshot in snapshot.children){
                    val currentUser =postSnapshot.getValue(User::class.java)
                    if(mAuth.currentUser?.uid!=currentUser?.uid)
                    {
                        userlist.add(currentUser!!)
                    }
                }
                adaptor.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.Logout){
            //LOGIC FOR LOGOUT
            mAuth.signOut()
            val intent = Intent(this@MainActivity,Login::class.java)
            finish()
            startActivity(intent)

            return true
        }
        return true
    }
}