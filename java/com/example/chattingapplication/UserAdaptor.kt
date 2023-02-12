package com.example.chattingapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdaptor(val context: Context, val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAdaptor.UserVeiwHolder>() {
    /*class UserVeiwHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }
*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVeiwHolder {

        val veiw: View=LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserVeiwHolder(veiw)
    }

    override fun onBindViewHolder(holder: UserVeiwHolder, position: Int) {
        val currentUser =userList[position]
        holder.textname.text=currentUser.name
        holder.itemView.setOnClickListener{
            val intent=Intent(context, ChatActivity::class.java)

            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return userList.size
    }
    class UserVeiwHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val textname =itemView.findViewById<TextView>(R.id.txtname)


    }
}