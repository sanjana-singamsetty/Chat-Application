package com.example.chattingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {
    private lateinit var chatrecylerview: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton : ImageView
    private lateinit var messageAdaptor: MessageAdaptor
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDBref:DatabaseReference
    var reciverRoom: String?=null
    var senderRoom: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        chatrecylerview =findViewById(R.id.chatrecyclerview)
        messageBox=findViewById(R.id.messagebox)
        sendButton=findViewById(R.id.sendbutton)
        messageList=ArrayList()
        messageAdaptor=MessageAdaptor(this,messageList)

       chatrecylerview.layoutManager=LinearLayoutManager(this)
        chatrecylerview.adapter=messageAdaptor

        val name=intent.getStringExtra("name")
        val reciveruid=intent.getStringExtra("uid")
        val senderuid=FirebaseAuth.getInstance().currentUser?.uid
        mDBref=FirebaseDatabase.getInstance().getReference()

        senderRoom= reciveruid+senderuid
        reciverRoom=senderuid+reciveruid
        supportActionBar?.title=name
        //logic for adding data to recycler view
        mDBref.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for(postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdaptor.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })


        //adding message to data base
        sendButton.setOnClickListener{

         val message = messageBox.text.toString()
            val messageObject = Message(message,senderuid)
            mDBref.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDBref.child("chats").child(reciverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            messageBox.setText("")

        }

    }
}