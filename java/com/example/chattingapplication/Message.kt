package com.example.chattingapplication

import android.content.IntentSender

class Message {
    var message: String?=""
    var senderId: String?=""
    constructor(message: String?,senderId : String?){
        this.message=message
        this.senderId=senderId

    }
    constructor()
}