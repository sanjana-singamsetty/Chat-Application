package com.example.chattingapplication

class User {
    //class used to store values of user

    var name: String?= null
    var email: String?=null
    var uid :String?=null

    constructor(){//firebasw needs  empty constructor to work with
         }

        constructor(name: String?,email: String?,uid: String?){
            this.name=name
            this.email=email
            this.uid=uid

        }
    //to store users we need a recycler view and we need a layout for displaying recycler view
}